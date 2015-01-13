package com.wineaccess.util.command;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.constants.WineaccessErrorCodes;
import com.wineaccess.data.model.user.StateModel;
import com.wineaccess.data.model.user.StateRepository;
import com.wineaccess.response.Response;

/**
 * 
 * @author arpit.vijayvargiya@globallogic.com
 * 
 */
public class StateListAdapterHelper { 
	

	/**
	 * 
	 * @return -list of states in database
	 */
	public static Map<String, Object> listState() {		

		List<StateModel> stateModelList = StateRepository.getAll();
		
		List<State> stateList = new ArrayList<State>();
		for(Object o: stateModelList){
			StateModel stateModel = (StateModel)o;
			State stateObject = new State();
			stateObject.setId(stateModel.getId());
			stateObject.setName(stateModel.getStateName());
			stateObject.setStateCode(stateModel.getStateCode());
			stateList.add(stateObject);
		}

		Map<String, Object> output = new HashMap<String, Object>();

		StateListVO stateListVO = new StateListVO(stateList);

		Response response = new com.wineaccess.response.SuccessResponse(
				stateListVO, 200);

		output.put("FINAL-RESPONSE", response);

		return output;
	}
	
	/**
	 * 
	 * @return -list of states in database
	 */
	public static Map<String, Object> listStateById(String stateId) {		
		Response response = null;
		Map<String, Object> output = new HashMap<String, Object>();
		if(!StringUtils.isNumeric(stateId)){
			response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.STATE_INVALID_PARAM_ERROR, WineaccessErrorCodes.SystemErrorCode.STATE_INVALID_PARAM_ERROR_TEXT, 200);			
		}
		else{
			List<StateModel> stateModelList = StateRepository.getById(Long.parseLong(stateId));
			
			if (stateModelList.isEmpty()) {
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.STATE_NOT_FOUND, WineaccessErrorCodes.SystemErrorCode.STATE_NOT_FOUND_TEXT, 200);
			} else {
				List<State> stateList = new ArrayList<State>();
				for(Object o: stateModelList){
					StateModel stateModel = (StateModel)o;
					State stateObject = new State();
					stateObject.setId(stateModel.getId());
					stateObject.setName(stateModel.getStateName());
					stateObject.setStateCode(stateModel.getStateCode());
					stateList.add(stateObject);
				}

				StateListVO stateListVO = new StateListVO(stateList);
				response = new com.wineaccess.response.SuccessResponse(stateListVO, 200);
			}
		}

		output.put("FINAL-RESPONSE", response);
		return output;
	}
	
	/**
	 * 
	 * @return -list of states in database
	 */
	public static Map<String, Object> listStateByCountryId(String countryId) {		
		Response response = null;
		Map<String, Object> output = new HashMap<String, Object>();
		if(null != countryId){
			if(StringUtils.isNumeric(countryId)){
				List<StateModel> stateModelList = StateRepository.getByCountryId(Long.parseLong(countryId));
				if (stateModelList.isEmpty()) {
					response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.STATE_NOT_FOUND, WineaccessErrorCodes.SystemErrorCode.STATE_NOT_FOUND_TEXT, 200);
				} else {
					List<State> stateList = new ArrayList<State>();
					for(Object o: stateModelList){
						StateModel stateModel = (StateModel)o;
						State stateObject = new State();
						stateObject.setId(stateModel.getId());
						stateObject.setName(stateModel.getStateName());
						stateObject.setStateCode(stateModel.getStateCode());
						stateList.add(stateObject);
					}

					StateListVO stateListVO = new StateListVO(stateList);	
					response = new com.wineaccess.response.SuccessResponse(
							stateListVO, 200);
				}
			}
			else{
				response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.STATE_INVALID_PARAM_ERROR, WineaccessErrorCodes.SystemErrorCode.STATE_INVALID_PARAM_ERROR_TEXT, 200);
			}		
		}
		else{
			response = ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.STATE_MANDATORY_FIELD_ERROR, WineaccessErrorCodes.SystemErrorCode.STATE_MANDATORY_FIELD_ERROR_TEXT, 200);
		}
		
		output.put("FINAL-RESPONSE", response);

		return output;
	}
	
}

