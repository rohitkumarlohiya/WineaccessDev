package com.wineaccess.service.startup;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import com.globallogic.orch.ProcessDefinitionDocument;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.constants.ApplicationConstants;
import com.wineaccess.data.model.catalog.ImporterModel;
import com.wineaccess.data.model.catalog.WineryModel;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.common.MasterDataType;
import com.wineaccess.data.model.system.ApplicationKeyVersion;
import com.wineaccess.data.model.system.ApplicationKeyVersionRepository;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.data.model.user.UserRepository;
import com.wineaccess.data.model.user.UserRoles;
import com.wineaccess.job.master.data.MasterDataJob;
import com.wineaccess.job.master.location.LocationMasterDataJob;
import com.wineaccess.job.templat.type.EmailTemplateTypePersistJob;
import com.wineaccess.model.json.generator.UserModelJsonGenerator;
import com.wineaccess.orchestration.core.constants.ComponentConstants;
import com.wineaccess.orchestration.data.repository.DataRepositoryManager;
import com.wineaccess.orchestration.workflow.model.ProcessDefinitionModel;
import com.wineaccess.orchestration.workflow.model.ProcessDefinitionRepository;
import com.wineaccess.persistence.dao.GenericDAO;
import com.wineaccess.property.utils.PropertyConstants;
import com.wineaccess.property.utils.PropertyholderUtils;
import com.wineaccess.property.utils.WebServicesPropertyHolderUtils;
import com.wineaccess.security.token.MarkTokenExpireJob;
import com.wineaccess.service.IServiceLifeCycle;
import com.wineaccess.third.party.ThirdPartySessionManager;
import com.wineaccess.wine.WineRepository;
import com.wineaccess.winery.WineryRepository;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class StartupService implements StartupServiceMBean, IServiceLifeCycle {

	private static Log logger = LogFactory.getLog(StartupService.class);

	public StartupService() {
	}

	@Override
	public void start() throws Exception {
		Thread t = new Thread(new DatePopulator());
		t.start();
	}

	@Override
	public void stop() throws Exception {
	}

	public class DatePopulator implements Runnable {

		@Override
		public void run() {
			try {

				logger.info("Staring Server.. Please Wait");
				createMasterData();
				ThirdPartySessionManager.getInstance().initSendGridSession();
				RecoveryService.recoverCache();
				RecoveryService.recoverAPIAccessCode();
				RecoveryService.recoverTokens();
				RecoveryService.recoverProcessDefinition();
				startSystemJob();
				createTestData();
				// cleanLucenceIndex();
				updateUserDataInResponsys();
				logger.info("Server Started...");
			} catch (Exception ex) {
				logger.error("Error while starting the server ..", ex);
			}
		}
	}

	private void startSystemJob() {
		new Thread(new MarkTokenExpireJob(), "Idle User Timed out Job").start();
		new Thread(new MasterDataJob(), "Master Data Type Persister Job")
				.start();
		new Thread(new LocationMasterDataJob(), "Location Data Persister Job")
				.start();
		new Thread(new EmailTemplateTypePersistJob(),
				"Email Template Type Persister Job").start();
	}

	private void updateUserDataInResponsys() {

		String isResponsysEnabled = WebServicesPropertyHolderUtils
				.getStringProperty(PropertyConstants.RESPONSYS_ENABLED);
		String cronExpression = WebServicesPropertyHolderUtils
				.getStringProperty(PropertyConstants.RESPONSYS_CRON_EXPRESSION);
		try {
			if (!StringUtils.isEmpty(isResponsysEnabled)
					&& isResponsysEnabled.equalsIgnoreCase("true")
					&& !StringUtils.isEmpty(cronExpression)) {

				JobDetail job = JobBuilder
						.newJob(UpdateUserDataInResponsys.class)
						.withIdentity("ResponsysJob", "ResponsysGroup").build();
				TriggerKey key = new TriggerKey(
						"ResponsysGroup.ResponsysTrigger");
				Trigger trigger = TriggerBuilder
						.newTrigger()
						.withIdentity(key)
						.forJob(job)
						.withSchedule(
								CronScheduleBuilder
										.cronSchedule(cronExpression)).build();
				// schedule it
				Scheduler scheduler = new StdSchedulerFactory().getScheduler();
				scheduler.start();
				scheduler.scheduleJob(job, trigger);
			}
		} catch (Exception e) {
			System.out
					.println("Error while starting the user upload in responsys .."
							+ e);
			logger.error(
					"Error while starting the user upload in responsys ..", e);
		}
	}

	private void cleanLucenceIndex() {

		GenericDAO<UserModel> genericDao = (GenericDAO<UserModel>) CoreBeanFactory
				.getBean("genericDAO");
		genericDao.indexLucene();

	}

	private void createMasterData() throws Exception {

		File file = new File(System.getenv("WINEACCESS_HOME")
				+ "/ProcessDefinitions");
		String[] processDefinitions = file.list();

		for (String xml : processDefinitions) {
			ProcessDefinitionDocument processDef = ProcessDefinitionDocument.Factory
					.parse(new File(System.getenv("WINEACCESS_HOME")
							+ "/ProcessDefinitions/" + xml));

			List<ProcessDefinitionModel> processDefinition = ProcessDefinitionRepository
					.getByNameAndVersion(processDef.getProcessDefinition()
							.getName(), processDef.getProcessDefinition()
							.getVersion());

			if (processDefinition.isEmpty()) {
				ProcessDefinitionModel processDefinitionModel = new ProcessDefinitionModel(
						processDef.getProcessDefinition().getName(), processDef
								.getProcessDefinition().getVersion(),
						processDef.toString());
				ProcessDefinitionRepository.create(processDefinitionModel);
			}
		}

		DataRepositoryManager dataRepositoryManager = (DataRepositoryManager) CoreBeanFactory
				.getBean(PropertyholderUtils
						.getStringProperty(ComponentConstants.DATA_REPOSITORY_MANAGER));

		File file1 = new File(System.getenv("WINEACCESS_HOME")
				+ "/api.versions.properties");

		InputStream inputStream = new FileInputStream(file1);

		Properties properties = new Properties();
		properties.load(inputStream);

		Set<Object> keys = properties.keySet();

		for (Object key : keys) {
			String apiKey = (String) key;
			String apiVersion = properties.getProperty(apiKey);

			List<ApplicationKeyVersion> versions = ApplicationKeyVersionRepository
					.getByApiKeyAndVersion(apiKey, apiVersion);

			if (versions.isEmpty()) {
				ApplicationKeyVersion keyVersion = new ApplicationKeyVersion(
						apiKey, apiVersion);
				ApplicationKeyVersionRepository.create(keyVersion);
			}

			dataRepositoryManager.addApiVersionKeyMap(apiKey, apiVersion);
		}
	}

	private void createTestData() {
		
		
		GenericDAO genericDAO2 = (GenericDAO) CoreBeanFactory
				.getBean("genericDAO");
		
		List<Object> model = (List<Object>) genericDAO2.findByNamedQuery("WineryModel.getAll");
		
		for (Object m : model) {
			WineRepository.updateWCAndActiveWCInWinery(Long.parseLong(m.toString()));
		}
		
		GenericDAO genericDAO3 = (GenericDAO) CoreBeanFactory
				.getBean("genericDAO");
		
		List<Object> model1 = (List<Object>) genericDAO3.findByNamedQuery("ImporterModel.getAll");
		
		for (Object m : model1) {
			WineRepository.updateWCAndActiveWCInImporter(Long.parseLong(m.toString()));
		}

		List<UserModel> models = UserRepository.getAll();
				GenericDAO<UserRoles> genericDAO = (GenericDAO<UserRoles>) CoreBeanFactory
						.getBean("genericDAO");
		if (models.size() == 1) {
			
			List<UserRoles> roles = genericDAO.findByNamedQuery(
					"UserRoles.getByRoleName", new String[] { "roleName" },
					ApplicationConstants.ROLES.ROLE_RETAIL_USER.name());
			UserRoles userRole = roles.get(0);

			for (int i = 1; i < 1; i++) {
				UserModel userModel = UserModelJsonGenerator.generateData(i);
				userModel.getUserRoles().add(userRole);
				UserRepository.save(userModel);
			}
		}

		/*
		 * List<MasterDataType> masterDataType =
		 * MasterDataRepository.getMasterDataTypes();
		 * if(masterDataType.isEmpty()){
		 * 
		 * createMasterDataType("Vintage","Vintage");
		 * createMasterDataType("Winery","Winery");
		 * createMasterDataType("Credit Card Type","CardType");
		 * createMasterDataType
		 * ("Wine Access Sourcing Status","WASourcingStatus");
		 * createMasterDataType("WA Contact","WAContact");
		 * createMasterDataType("Freight Region","FreightRegion"); }
		 */

		// TODO code to be removed
		GenericDAO<UserModel> userDao = (GenericDAO<UserModel>) CoreBeanFactory
				.getBean("genericDAO");
		userDao.executeQuery("update UserModel set regStatus='Full' where regStatus is NULL");
		// GenericDAO<UserCreditCard> userCCDAO = (GenericDAO<UserCreditCard>)
		// CoreBeanFactory.getBean("genericDAO");
		// userCCDAO.executeQuery("ALTER TABLE `USER_CREDIT_CARD` CHANGE `CREDIT_CARDNO` `CREDIT_CARDNO` BIGINT( 20 ) NULL DEFAULT NULL");
		// logger.debug("alter query executed");
	}

	private void createMasterDataType(String description, String name) {
		MasterDataType masterDataType = new MasterDataType();
		masterDataType.setDescription(description);
		masterDataType.setName(name);
		masterDataType.setStatus(true);

		GenericDAO<MasterDataType> masterDataTypeDAO = (GenericDAO<MasterDataType>) CoreBeanFactory
				.getBean("genericDAO");
		masterDataTypeDAO.create(masterDataType);

		// Dummy data for Sourcing status in Importer and Winery.
		if (name.equals("WASourcingStatus")) {
			createMasterData(masterDataType, "No Contact History");
			createMasterData(masterDataType, "Qualified Interest");
			createMasterData(masterDataType, "Not Interested");
			createMasterData(masterDataType, "Active");
			createMasterData(masterDataType, "Dormant");
		}
		if (name.equals("WAContact")) {
			createMasterData(masterDataType, "WA Contact");
		}
		if (name.equals("FreightRegion")) {
			createMasterData(masterDataType, "Freight Region");
		}

	}

	private void createMasterData(MasterDataType masterDataType, String name) {
		MasterData masterData = new MasterData();
		masterData.setName(name);
		masterData.setMasterDataType(masterDataType);

		GenericDAO<MasterData> masterDataDAO = (GenericDAO<MasterData>) CoreBeanFactory
				.getBean("genericDAO");
		masterDataDAO.create(masterData);

	}

}