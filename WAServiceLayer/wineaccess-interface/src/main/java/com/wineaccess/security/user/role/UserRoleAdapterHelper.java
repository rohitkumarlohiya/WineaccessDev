package com.wineaccess.security.user.role;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.security.TokenModel;
import com.wineaccess.data.model.security.TokenModelRepository;
import com.wineaccess.data.model.user.UserRoles;
import com.wineaccess.data.model.user.UserRolesRepository;
import com.wineaccess.response.FailureResponse;
import com.wineaccess.response.Response;
import com.wineaccess.response.WineaccessError;

/**
 * 
 * @author rohit.lohiya
 *  
 */
public class UserRoleAdapterHelper {
	

	/**
	 * 
	 * @param userRolePO -input parameter contains role name
	 * @return success if added successfully otherwise return error code
	 */
	public static Map<String, Object> addUserRole(UserRolePO userRolePO,String wineAccessToken) {
		
		Long userId = getUserIDByToken(wineAccessToken);
		 
		Map<String, Object> output = new HashMap<String, Object>();		
		
		if (userRolePO.getRoleName() == null || userRolePO.getRoleName().equalsIgnoreCase(""))			//check for null or empty string
		{
			WineaccessError error = new WineaccessError(
					SystemErrorCode.USER_ROLE_NAME_REQUIRED,
					SystemErrorCode.USER_ROLE_NAME_REQUIRED_TEXT);

			Response failedResponse = new FailureResponse();
			failedResponse.setStatus(200);
			failedResponse.addError(error);

			output.put("FINAL-RESPONSE", failedResponse);
		}
		else if(!isValidString(userRolePO.getRoleName()))			//check for invalid string like role name only accepts alphabets
		{
			WineaccessError error = new WineaccessError(
					SystemErrorCode.INVALID_ROLE_NAME,
					SystemErrorCode.INVALID_ROLE_NAME_TEXT);

			Response failedResponse = new FailureResponse();
			failedResponse.setStatus(200);
			failedResponse.addError(error);

			output.put("FINAL-RESPONSE", failedResponse);
		}
		else
		{
			List<UserRoles> userRolesList = UserRolesRepository.getByRoleName(userRolePO.getRoleName());

			if (userRolesList.isEmpty() || userRolesList.size() == 0) 
			{
				
				UserRoles userRoles = new UserRoles();
				
				userRoles.setRoleName(userRolePO.getRoleName());
				userRoles.setStatus(true);
				
				userRoles.setCreatedDate(new Date());
				if(userId == null){
					userRoles.setCreatedBy(null);
				}else{
					userRoles.setCreatedBy(userId);
				}
				// insert into database
				UserRolesRepository.save(userRoles);

				UserRoleAddVO userRoleVO = new UserRoleAddVO(userRoles);
				//UserRoleAddVO userRoleVO = new UserRoleAddVO(userRoles.getRoleName(), userRoles.isStatus(), "successfully created");

				Response response = new com.wineaccess.response.SuccessResponse(userRoleVO, 200);

				output.put("FINAL-RESPONSE", response);

			}
			else 
			{				
				UserRoles userRoles = userRolesList.get(0);				
				
				if(userRoles.isStatus()) // return already exist in database			
				{				
					WineaccessError error = new WineaccessError(
							SystemErrorCode.USER_ROLE_NAME_ALREADY_EXISTS,
							SystemErrorCode.USER_ROLE_NAME_ALREADY_EXISTS_TEXT);

					Response failedResponse = new FailureResponse();
					failedResponse.setStatus(200);
					failedResponse.addError(error);

					output.put("FINAL-RESPONSE", failedResponse);
				}
				else   //if already present but status is inactive
				{
					userRoles.setStatus(true);
					
					userRoles.setModifiedDate(new Date());
					if(userId == null){
						userRoles.setModifiedBy(null);
					}else{
						userRoles.setModifiedBy(userId);
					}
					
					//update in database				
					UserRolesRepository.update(userRoles);
					
					UserRoleAddVO userRoleVO = new UserRoleAddVO(userRoles);
					
					Response response = new com.wineaccess.response.SuccessResponse(userRoleVO, 200);

					output.put("FINAL-RESPONSE", response);
				}
			}
				
		}
					
		
		/*

		if (userRolePO.getRoleName() != null && userRolePO.getRoleName() != "") {
			
			List<UserRoles> userRolesList = UserRolesRepository
					.getByRoleName(userRolePO.getRoleName());

			if (userRolesList.isEmpty() || userRolesList.size() == 0) {
				
				UserRoles userRoles = new UserRoles();
				userRoles.setRoleName(userRolePO.getRoleName());
				userRoles.setCreatedDate(new Date());
				if(userId == null){
					userRoles.setCreatedBy(null);
				}else{
				userRoles.setCreatedBy(""+userId);
				}
				// insert into database
				UserRolesRepository.save(userRoles);

				UserRoleAddVO userRoleVO = new UserRoleAddVO(userRoles);
				//UserRoleAddVO userRoleVO = new UserRoleAddVO(userRoles.getRoleName(), userRoles.isStatus(), "successfully created");

				Response response = new com.wineaccess.response.SuccessResponse(
						userRoleVO, 200);

				output.put("FINAL-RESPONSE", response);

			} else {
				// return already exist in database			

				WineaccessError error = new WineaccessError(
						SystemErrorCode.USER_ROLE_NAME_ALREADY_EXISTS,
						SystemErrorCode.USER_ROLE_NAME_ALREADY_EXISTS_TEXT);

				Response failedResponse = new FailureResponse();
				failedResponse.setStatus(200);
				failedResponse.addError(error);

				output.put("FINAL-RESPONSE", failedResponse);
			}
		} else {
			WineaccessError error = new WineaccessError(
					SystemErrorCode.USER_ROLE_NAME_REQUIRED,
					SystemErrorCode.USER_ROLE_NAME_REQUIRED_TEXT);

			Response failedResponse = new FailureResponse();
			failedResponse.setStatus(200);
			failedResponse.addError(error);

			output.put("FINAL-RESPONSE", failedResponse);
		}*/

		return output;
	}

	

	/**
	 * 
	 * @return -list of user roles in database
	 */
	public static Map<String, Object> listUserRole() {		
		

		List<UserRoles> userRolesList = UserRolesRepository.getAll();		

		Map<String, Object> output = new HashMap<String, Object>();

		UserRoleListVO userRoleListVO = new UserRoleListVO(userRolesList);

		Response response = new com.wineaccess.response.SuccessResponse(
				userRoleListVO, 200);

		output.put("FINAL-RESPONSE", response);

		return output;
	}

	/**
	 * 
	 * @param userRolePO -input parameter contain roleId,updatedRoleName and status
	 * @return success if successfully  updated otherwise error code
	 */
	public static Map<String, Object> updateUserRole(UserRolePO userRolePO,String wineAccessToken) {

		Long userId = getUserIDByToken(wineAccessToken);
		
		Map<String, Object> output = new HashMap<String, Object>();		
		
		UserRoleUpdateVO userRoleUpdateVO = null;
		
		if(userRolePO.getRoleId() < 1)	//check if negative role id
		{
			WineaccessError error = new WineaccessError(
					SystemErrorCode.INVALID_ROLE_ID,
					SystemErrorCode.INVALID_ROLE_ID_TEXT);

			Response failedResponse = new FailureResponse();
			failedResponse.setStatus(200);
			failedResponse.addError(error);

			output.put("FINAL-RESPONSE", failedResponse);
		}
		else if(userRolePO.getUpdatedRoleName() == null || userRolePO.getUpdatedRoleName().equalsIgnoreCase(""))	//check for null or empty string
		{
			WineaccessError error = new WineaccessError(
					SystemErrorCode.UPDATED_USER_ROLE_NAME_REQUIRED,
					SystemErrorCode.UPDATED_USER_ROLE_NAME_REQUIRED_TEXT);

			Response failedResponse = new FailureResponse();
			failedResponse.setStatus(200);
			failedResponse.addError(error);

			output.put("FINAL-RESPONSE", failedResponse);			
		}	
		else if(!isValidString(userRolePO.getUpdatedRoleName()))			//check for invalid string 
		{
			WineaccessError error = new WineaccessError(
					SystemErrorCode.INVALID_UPDATED_ROLE_NAME,
					SystemErrorCode.INVALID_UPDATED_ROLE_NAME_TEXT);

			Response failedResponse = new FailureResponse();
			failedResponse.setStatus(200);
			failedResponse.addError(error);

			output.put("FINAL-RESPONSE", failedResponse);
		}
		else
		{
			List<UserRoles> userRolesList =  UserRolesRepository.getByRoleId(userRolePO.getRoleId());
			if(userRolesList != null && userRolesList.size() > 0)
			{				
				UserRoles userRoles =  userRolesList.get(0);				
				
				if(userRoles.getRoleName().equalsIgnoreCase(userRolePO.getUpdatedRoleName()))	//check if updated role name is already present in database
				{
					WineaccessError error = new WineaccessError(
							SystemErrorCode.USER_ROLE_NAME_ALREADY_EXISTS,
							SystemErrorCode.USER_ROLE_NAME_ALREADY_EXISTS_TEXT);

					Response failedResponse = new FailureResponse();
					failedResponse.setStatus(200);
					failedResponse.addError(error);

					output.put("FINAL-RESPONSE", failedResponse);
				}
				else
				{
					userRoles.setRoleName(userRolePO.getUpdatedRoleName());
					userRoles.setStatus(userRolePO.getStatus());
					
					userRoles.setModifiedDate(new Date());
					if(userId == null){
						userRoles.setModifiedBy(null);
					}else{
					userRoles.setModifiedBy(userId);
					}
					
					//update in database
					
					UserRolesRepository.update(userRoles);
					
					userRoleUpdateVO = new UserRoleUpdateVO(userRoles);
					
					Response response = new com.wineaccess.response.SuccessResponse(userRoleUpdateVO, 200);

					output.put("FINAL-RESPONSE", response);
				}	
			}
			else
			{
				//user role not exists in database.
				WineaccessError error = new WineaccessError(
						SystemErrorCode.USER_ROLE_ID_NOT_EXISTS,
						SystemErrorCode.USER_ROLE_ID_NOT_EXISTS_TEXT);

				Response failedResponse = new FailureResponse();
				failedResponse.setStatus(200);
				failedResponse.addError(error);

				output.put("FINAL-RESPONSE", failedResponse);				
			}			
		}	
		
		
		
		
		
		
		
		/*
		
		UserRoleUpdateVO userRoleUpdateVO = null;
		
		if(userRolePO.getRoleId() == null || userRolePO.getRoleId().equalsIgnoreCase(""))
		{
			WineaccessError error = new WineaccessError(
					SystemErrorCode.USER_ROLE_ID_REQUIRED,
					SystemErrorCode.USER_ROLE_ID_REQUIRED_TEXT);

			Response failedResponse = new FailureResponse();
			failedResponse.setStatus(200);
			failedResponse.addError(error);

			output.put("FINAL-RESPONSE", failedResponse);
		}
		else if(userRolePO.getUpdatedRoleName() == null || userRolePO.getUpdatedRoleName().equalsIgnoreCase(""))
		{
			WineaccessError error = new WineaccessError(
					SystemErrorCode.UPDATED_USER_ROLE_NAME_REQUIRED,
					SystemErrorCode.UPDATED_USER_ROLE_NAME_REQUIRED_TEXT);

			Response failedResponse = new FailureResponse();
			failedResponse.setStatus(200);
			failedResponse.addError(error);

			output.put("FINAL-RESPONSE", failedResponse);			
		}	
		else
		{
			List<UserRoles> userRolesList =  UserRolesRepository.getByRoleId(Integer.parseInt(userRolePO.getRoleId()));
			if(userRolesList != null && userRolesList.size() > 0)
			{				
				UserRoles userRoles =  userRolesList.get(0);
				
				userRoles.setRoleName(userRolePO.getUpdatedRoleName());
				userRoles.setStatus(userRolePO.getStatus());
				
				userRoles.setModifiedDate(new Date());
				if(userId == null){
					userRoles.setModifiedBy(null);
				}else{
				userRoles.setModifiedBy(""+userId);
				}
				
				//update in database
				
				UserRolesRepository.update(userRoles);
				
				userRoleUpdateVO = new UserRoleUpdateVO(userRoles);
				
				Response response = new com.wineaccess.response.SuccessResponse(userRoleUpdateVO, 200);

				output.put("FINAL-RESPONSE", response);
			}
			else
			{
				//user role not exists in database.
				WineaccessError error = new WineaccessError(
						SystemErrorCode.USER_ROLE_ID_NOT_EXISTS,
						SystemErrorCode.USER_ROLE_ID_NOT_EXISTS_TEXT);

				Response failedResponse = new FailureResponse();
				failedResponse.setStatus(200);
				failedResponse.addError(error);

				output.put("FINAL-RESPONSE", failedResponse);				
			}			
		}	*/
		
		return output;
	}
	
	/**
	 * 
	 * @param roleId -input parameter for deleting role against this role Id
	 * @return success if deleted successfully otherwise error code
	 */
	public static Map<String, Object> deleteUserRole(int roleId,String wineAccessToken) {		
		
		Long userId = getUserIDByToken(wineAccessToken);
		
		Map<String, Object> output = new HashMap<String, Object>();	
				
		List<UserRoles> userRolesList = UserRolesRepository.getByRoleId(roleId);
		
		if(isUsersExistsForRoleId(String.valueOf(roleId)))
		{
			WineaccessError error = new WineaccessError(
					SystemErrorCode.USER_ROLE_ID_IS_IN_USE,
					SystemErrorCode.USER_ROLE_ID_IS_IN_USE_TEXT);

			Response failedResponse = new FailureResponse();
			failedResponse.setStatus(200);
			failedResponse.addError(error);

			output.put("FINAL-RESPONSE", failedResponse);	
		}
		else if(!isUserRoleExistsForRoleId(String.valueOf(roleId)))
		{
			//user role not exists in database.
			WineaccessError error = new WineaccessError(
					SystemErrorCode.USER_ROLE_ID_NOT_EXISTS,
					SystemErrorCode.USER_ROLE_ID_NOT_EXISTS_TEXT);

			Response failedResponse = new FailureResponse();
			failedResponse.setStatus(200);
			failedResponse.addError(error);

			output.put("FINAL-RESPONSE", failedResponse);	
		}
		else
		{
		
			UserRoles userRoles = userRolesList.get(0);			
			
			userRoles.setModifiedDate(new Date());
			if(userId == null){
				userRoles.setModifiedBy(null);
			}else{
			userRoles.setModifiedBy(userId);
			}
			
			UserRolesRepository.delete(userRoles);
			UserRoleDeleteVO userRoleDeleteVO = new UserRoleDeleteVO(userRoles);

			Response response = new com.wineaccess.response.SuccessResponse(userRoleDeleteVO, 200);

			output.put("FINAL-RESPONSE", response);			
		}
				
		return output;
	}
	
	
	
	/**
	 * 
	 * @param multipleRoleIds-takes multiple role Ids separated by comma
	 * @return success if all role Id successfully deleted otherwise error code
	 */
	public static Map<String, Object> multipleDeleteUserRole(String multipleRoleIds,String wineAccessToken) {		
		
		Long userId = getUserIDByToken(wineAccessToken);
		
		Map<String, Object> output = new HashMap<String, Object>();	
		
		String[] roleIds = multipleRoleIds.split(",");		
		
		boolean flag = false;
		
		for(int i=0;i<roleIds.length;i++)
		{
			flag = isUsersExistsForRoleId(roleIds[i]);	
			if(flag)
			{
				break;
			}
		}
		
		if(flag)
		{
			WineaccessError error = new WineaccessError(
					SystemErrorCode.USER_ROLE_ID_IS_IN_USE,
					SystemErrorCode.USER_ROLE_ID_IS_IN_USE_TEXT);

			Response failedResponse = new FailureResponse();
			failedResponse.setStatus(200);
			failedResponse.addError(error);

			output.put("FINAL-RESPONSE", failedResponse);	
			
		}
		else
		{		
			boolean flag1 = false;
			
			for(int i=0;i<roleIds.length;i++)
			{
				flag1 = isUserRoleExistsForRoleId(roleIds[i]);	
				if(!flag1)
				{
					break;
				}
			}			
			
			if(!flag1)
			{
				WineaccessError error = new WineaccessError(
						SystemErrorCode.USER_ROLE_ID_NOT_EXISTS,
						SystemErrorCode.USER_ROLE_ID_NOT_EXISTS_TEXT);

				Response failedResponse = new FailureResponse();
				failedResponse.setStatus(200);
				failedResponse.addError(error);

				output.put("FINAL-RESPONSE", failedResponse);	
				
			}
			else
			{
				List<UserRoles> userRolesListVO = new ArrayList<UserRoles>();
				
				for(int i=0;i<roleIds.length;i++)
				{
					List<UserRoles> userRolesList = UserRolesRepository.getByRoleId(Integer.parseInt(roleIds[i]));	
					
					if(userRolesList != null && userRolesList.size() > 0)
					{
						UserRoles userRoles = userRolesList.get(0);
					
						userRoles.setModifiedDate(new Date());
						if(userId == null){
							userRoles.setModifiedBy(null);
						}else{
						userRoles.setModifiedBy(userId);
						}
						
						UserRolesRepository.delete(userRoles);
						userRolesListVO.add(userRoles);						
					}	
				}
				
				UserRoleMultipleDeleteVO userRoleMultipleDeleteVO = new UserRoleMultipleDeleteVO(userRolesListVO);
				
				Response response = new com.wineaccess.response.SuccessResponse(userRoleMultipleDeleteVO, 200);

				output.put("FINAL-RESPONSE", response);
			}	
			
		}	
				
		return output;
	}
	
	/**
	 * This method check if there is any user exists for this role Id
	 * @param roleId
	 * @return true if exits otherwise false
	 */
	private static boolean isUsersExistsForRoleId(String roleId)
	{
		
		return UserRolesRepository.isUserExistForRole(Long.parseLong(roleId));
		
	}
	

	/**
	 * This method checks if there is any user role for this role Id
	 * @param roleId
	 * @return true if exists otherwise false
	 */
	private static boolean isUserRoleExistsForRoleId(String roleId)
	{
		List<UserRoles>  userRolesList = UserRolesRepository.getByRoleId(Integer.parseInt(roleId));
	
		boolean isUserRolesExistsForRoleId = false;
		
		if(userRolesList != null && userRolesList.size() > 0)
		{	
			isUserRolesExistsForRoleId = true;							
			
		}
		
		return isUserRolesExistsForRoleId;
	}
	
	private static Long getUserIDByToken(String wineAccessToken) {
		Long UserId = null;
		List<TokenModel> tokenModelList =  TokenModelRepository.getByToken(wineAccessToken);
		if(tokenModelList != null && !tokenModelList.isEmpty())
		{
			UserId = tokenModelList.get(0).getUserid();
		}
		return UserId;
	}
	
	
	public static boolean isValidString(String stringToCheck){

		boolean isValid = stringToCheck.matches("[a-z A-Z]+$") ? true : false; 
		return isValid;
		} 

}
