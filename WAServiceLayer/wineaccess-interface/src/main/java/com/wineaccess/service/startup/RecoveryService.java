package com.wineaccess.service.startup;

import java.util.Calendar;
import java.util.List;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.command.api.access.code.APIAccessDAO;
import com.wineaccess.crypto.util.CryptoUtil;
import com.wineaccess.data.model.security.APIAccessCode;
import com.wineaccess.data.model.security.TokenModel;
import com.wineaccess.data.model.security.TokenModelRepository;
import com.wineaccess.orchestration.core.constants.ComponentConstants;
import com.wineaccess.orchestration.data.repository.DataRepositoryManager;
import com.wineaccess.orchestration.workflow.model.ProcessDefinitionModel;
import com.wineaccess.orchestration.workflow.model.ProcessDefinitionRepository;
import com.wineaccess.orchestration.workflow.process.cache.ProcessCacheManager;
import com.wineaccess.property.utils.PropertyholderUtils;

public class RecoveryService {
	
	public static void recoverCache(){
		dataRepositoryManager.cacheMasterData();
	}
	
	/**
	 * Recover all the token after coming from the Crash or restart of the Server. 
	 */
	public static void recoverTokens() {
		List<TokenModel> models = TokenModelRepository.getActiveTokens();
		if (!models.isEmpty()) {
			
			for (TokenModel tokenModel : models) {
				tokenModel.setSessionEndTime(Calendar.getInstance().getTime());
				TokenModelRepository.update(tokenModel);
			}
		}
	}
	
	public static void recoverProcessDefinition(){
		ProcessCacheManager processCacheManager = (ProcessCacheManager) CoreBeanFactory.getBean(PropertyholderUtils.getStringProperty(ComponentConstants.PROCESS_CACHE_MANAGER));
		List<ProcessDefinitionModel> processDefinitions = ProcessDefinitionRepository.getAll();
		for(ProcessDefinitionModel processDefinition : processDefinitions){
			processCacheManager.getProcess(processDefinition.getProcessName(), processDefinition.getVersion());
		}
	}
	
	static DataRepositoryManager dataRepositoryManager = (DataRepositoryManager) CoreBeanFactory.getBean(PropertyholderUtils.getStringProperty(ComponentConstants.DATA_REPOSITORY_MANAGER));
	
	public static void recoverAPIAccessCode() throws Exception {
		@SuppressWarnings("unchecked")
		APIAccessDAO<APIAccessCode> dao = (APIAccessDAO<APIAccessCode>) CoreBeanFactory.getBean("apiAccessDAO");
		List<APIAccessCode> apiAccessCodes = dao.listAPIAccessCodes();
		for (APIAccessCode apiAccessCode : apiAccessCodes) {
			
			String apiAccessCodeEncoded = CryptoUtil.encrypt(CryptoUtil.decrypt(apiAccessCode.getSiteId()) + "#AACCESSKEY#"  + CryptoUtil.decrypt(apiAccessCode.getPrivateKey()));
			dataRepositoryManager.putApiAccessCode(apiAccessCodeEncoded, apiAccessCode);
		}
	}
}
