package com.wineaccess.job.master.location;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.user.CityModel;
import com.wineaccess.data.model.user.CountryModel;
import com.wineaccess.data.model.user.StateModel;
import com.wineaccess.data.model.user.UserRoles;
import com.wineaccess.persistence.dao.GenericDAO;

public class LocationMasterDataJob implements Runnable {
	
	private static Log logger = LogFactory.getLog(LocationMasterDataJob.class);
	
	@SuppressWarnings("unchecked")
	GenericDAO<CityModel> cityModelDAO = (GenericDAO<CityModel>)  CoreBeanFactory.getBean("genericDAO");
	
	@SuppressWarnings("unchecked")
	GenericDAO<CountryModel> countryModelDAO = (GenericDAO<CountryModel>)  CoreBeanFactory.getBean("genericDAO");

	@SuppressWarnings("unchecked")
	GenericDAO<UserRoles> userGenericDAO = (GenericDAO<UserRoles>)  CoreBeanFactory.getBean("genericDAO");
	
	public static String MASTER_DATA_TYPE_FILE = System.getenv("WINEACCESS_HOME") + "/locationMasterData.xml";
	private long lastModifiedTime = 0L;
	private boolean roleCreated = false;

	@Override
	public void run() {
		/**
		 * The logic for job is divided in to following steps:
		 * 
		 * 	1. Read masterCityData list fist and insert/update the CITY table 
		 * 
		 * 	2. Read countryList and check for valid city should be from step 1 
		 * 
		 */
		
		do {
			try {
				final File file = new File(MASTER_DATA_TYPE_FILE);
				long lastModified = file.lastModified();
				if (lastModified > this.lastModifiedTime) {
					JAXBContext context = JAXBContext.newInstance(LocationMasterDataPO.class);
					Unmarshaller un = context.createUnmarshaller();
					LocationMasterDataPO locationMasterDataPO = (LocationMasterDataPO) un.unmarshal(file);
					/*
					 * Step 1
					 */
					long sTime = System.currentTimeMillis();
					System.out.println("City Data Start");
					handleMasterCityList(locationMasterDataPO.getMasterCityData());
					long eTime = System.currentTimeMillis();
					System.out.println("City Data End -> " + (eTime - sTime));
					/*
					 *  Step 2
					 */
					System.out.println("State Country Data Start");
					handleCountryStateEntires(locationMasterDataPO.getCountryList());
					eTime = System.currentTimeMillis();
					System.out.println("State Country Data End -> " + (eTime - sTime));
					checkAndCreateSuperAdmin();
					this.lastModifiedTime = lastModified;
				}
			} catch (Exception e) {
				logger.error("Error occured while executing LocationMasterDataJob : " + ExceptionUtils.getStackTrace(e));
			}
			try {
				TimeUnit.MINUTES.sleep(2);
			} catch (Exception e) {
				logger.error("Error occured while sleeping : " + ExceptionUtils.getStackTrace(e));
			}
		} while(true);

	}

	private void checkAndCreateSuperAdmin() {
		if (!this.roleCreated) {
			userGenericDAO.createAdminUserAndRole();
			this.roleCreated = true;
		}
		
	}

	private void handleCountryStateEntires(CountryListPO countryList)  throws Exception {
		if (countryList != null && CollectionUtils.isNotEmpty(countryList.getCountry())) {
			for (CountryPO countryPO : countryList.getCountry()) {
				List<CountryModel> countryModels = countryModelDAO.findByNamedQuery("CountryModel.getByCountryCode", new String[] {"countryCode"}, countryPO.getCountryCode());
				if (CollectionUtils.isEmpty(countryModels)) {
					insertNewCountry(countryPO);
				} else {
					updateExistingRecord(countryPO, countryModels.get(0));
				}
			}
		}
	}

	private void updateExistingRecord(CountryPO countryPO, CountryModel countryModel)  throws Exception {
		countryModel.setCountryName(countryPO.getCountryName());
		countryModel.setCountryNameSort(countryPO.getCountryNameSort());
		Set<StateModel> stateModels = countryModel.getState();
		for (StatePO statePO : countryPO.getState()) {
			StateModel stateModel = getStateFromCountryModel(countryModel, statePO);
			if (stateModel == null) { // insert new state
				stateModels.add(createNewState(statePO, countryModel));
			} else { // update state
				//stateModel.setStateName(statePO.getStateName());
				//stateModel.setStateNameSort(statePO.getStateNameSort());
				//stateModel.getCities().addAll(buildCityList(statePO));
			}
		}
		countryModelDAO.update(countryModel);
	}

	private StateModel getStateFromCountryModel(CountryModel countryModel, StatePO statePO)  throws Exception {
		if (CollectionUtils.isNotEmpty(countryModel.getState())) {
			for (StateModel stateModel : countryModel.getState()) {
				if (stateModel.getStateCode().equals(statePO.getStateCode())) {
					return stateModel;
				}
			}
		}
		return null;
	}

	private void insertNewCountry(CountryPO countryPO)  throws Exception{
		CountryModel model = new CountryModel(countryPO.getCountryName(), countryPO.getCountryCode());
		model.setCountryNameSort(countryPO.getCountryNameSort());
		countryModelDAO.create(model);
		//get States
		Set<StateModel> stateList = new HashSet<StateModel>();
		for (StatePO statePO : countryPO.getState()) {
			stateList.add(createNewState(statePO, model));
		}
		model.setState(stateList);
		countryModelDAO.update(model);
	}
	
	private StateModel createNewState(StatePO statePO, CountryModel model)  throws Exception {
		StateModel stateModel = new StateModel(statePO.getStateName(), statePO.getStateCode(), model);
		stateModel.setStateNameSort(statePO.getStateNameSort());
		stateModel.setCities(buildCityList(statePO));
		return stateModel;
	}

	private Set<CityModel> buildCityList(StatePO statePO)  throws Exception {
		Set<CityModel> cities = new HashSet<CityModel>();
		if (statePO.getCities() != null && CollectionUtils.isNotEmpty(statePO.getCities().getCityCode())) {
			for (String cityCode : statePO.getCities().getCityCode()) {
				List<CityModel> list = cityModelDAO.findByNamedQuery("CityModel.getByCityCode", new String[] {"cityCode"}, cityCode);
				if (CollectionUtils.isNotEmpty(list)) {
					cities.add(list.get(0));
				}
			}
		}
		return cities;
	}

	private void handleMasterCityList(MasterCityDataPO masterCityData) throws Exception {
		if (masterCityData != null && CollectionUtils.isNotEmpty(masterCityData.getCity())) {
			for (CityPO cityPO : masterCityData.getCity()) {
				/* get city from cityCode */
				List<CityModel> list = cityModelDAO.findByNamedQuery("CityModel.getByCityCode", new String[] {"cityCode"}, cityPO.getCityCode());
				if (CollectionUtils.isEmpty(list)) { 
					/* create new entry */
					cityModelDAO.create(new CityModel(cityPO.getCityName(), cityPO.getCityCode()));
				} else { 
					/* update existing entry 
					 * list size should be 1 only as cityCode is unique
					  */
					//CityModel cityModel = list.get(0);
					//cityModel.setCityName(cityPO.getCityName());
					//cityModelDAO.update(cityModel);
				}
			}
		}
	}

}
