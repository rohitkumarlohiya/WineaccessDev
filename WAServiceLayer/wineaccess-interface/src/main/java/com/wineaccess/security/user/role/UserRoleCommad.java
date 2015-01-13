package com.wineaccess.security.user.role;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wineaccess.application.constants.OperationNameEnum;
import com.wineaccess.auditManager.AuditManager.APIEvent;
import com.wineaccess.command.BaseCommand;
import com.wineaccess.constants.ApplicationConstants;


/**
 * @author rohit.lohiya
 *All request of Role API will land here first. 
 */
@Path("/{version}/user/role")
public class UserRoleCommad extends BaseCommand {

	final String PROCCESS_DEF_NAME = ApplicationConstants.PROCESSDEF.USER_ROLE.getProcessDefinationName();
	final int HTTP_ERROR_CODE=400;
	
	/**
	 * @param apiKey -is used to validate the version of api
	 * @param version -version name of api
	 * @param userRolePO -parameter object to store the user input
	 * @return -userRoleAddVO object which shows information about added object in database
	 */
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public javax.ws.rs.core.Response addUserRole(
			@HeaderParam("X-API-KEY") String apiKey,@HeaderParam("WINEACCESS_TOKEN") String wineAccessToken,
			@PathParam("version") String version, UserRolePO userRolePO) {	

		Map<String, Object> inputParameters = new HashMap<String, Object>();

		inputParameters.put("operation", OperationNameEnum.ADD);
		inputParameters.put("userRolePO", userRolePO);
		inputParameters.put("wineAccessToken", wineAccessToken);
		
		return processRequest(apiKey,version,inputParameters,PROCCESS_DEF_NAME, APIEvent.ADD_ROLE);

	}

	/**
	 * @param apiKey -is used to validate the version of api
	 * @param version -version name of api
	 * @return -UserRoleListVO object which show information about the list of objects in the database
	 */
	@GET
	@Path("/list")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public javax.ws.rs.core.Response listUserRole(
			@HeaderParam("X-API-KEY") String apiKey,
			@PathParam("version") String version) {

		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.LIST);
		
		return processRequest(apiKey,version,inputParameters,PROCCESS_DEF_NAME, APIEvent.ROLE_LIST);

	}

	/**
	 * 
	 * @param apiKey -is used to validate the version of api
	 * @param version -version name of api
	 * @param userRolePO -parameter object to store the user input
	 * @return -UserRoleUpdateVO object which show information about the updated object in database
	 */
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public javax.ws.rs.core.Response updateUserRole(
			@HeaderParam("X-API-KEY") String apiKey,
			@HeaderParam("WINEACCESS_TOKEN") String wineAccessToken,
			@PathParam("version") String version, UserRolePO userRolePO) {

		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.UPDATE);
		// inputParameters.put("roleId", roleId);
		inputParameters.put("userRolePO", userRolePO);
		inputParameters.put("wineAccessToken", wineAccessToken);
		
		return processRequest(apiKey,version,inputParameters,PROCCESS_DEF_NAME, APIEvent.UPDATE_ROLE);

	}

	/**
	 * 
	 * @param apiKey -is used to validate the version of api
	 * @param version -version name of api
	 * @param roleId -is used to delete the object based on role Id
	 * @param userRolePO -parameter object to store the user input
	 * @return -UserRoleDeleteVO object which show information about the deleted object in database
	 */
	@DELETE
	@Path("/delete/{roleId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public javax.ws.rs.core.Response deleteUserRole(
			@HeaderParam("X-API-KEY") String apiKey,
			@HeaderParam("WINEACCESS_TOKEN") String wineAccessToken,
			@PathParam("version") String version,
			@PathParam("roleId") int roleId, UserRolePO userRolePO) {

		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.DELETE);
		inputParameters.put("roleId", roleId);
		inputParameters.put("wineAccessToken", wineAccessToken);
		
		return processRequest(apiKey,version,inputParameters,PROCCESS_DEF_NAME, APIEvent.DELETE_ROLE);

	}

	/**
	 * 
	 * @param apiKey -is used to validate the version of api
	 * @param version -version name of api
	 * @param multipleRoleIds -take multiple role Id separated by comma
	 * @param userRolePO -parameter object to store the user input
	 * @return -UserRoleMultipleDeleteVO object which show information about the list of deleted object in database
	 */
	@DELETE
	@Path("/multipledelete/{multipleRoleIds}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public javax.ws.rs.core.Response multipleDeleteUserRole(
			@HeaderParam("X-API-KEY") String apiKey,
			@HeaderParam("WINEACCESS_TOKEN") String wineAccessToken,
			@PathParam("version") String version,
			@PathParam("multipleRoleIds") String multipleRoleIds,
			UserRolePO userRolePO) {
		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.MULTIPLEDELETE);
		inputParameters.put("multipleRoleIds", multipleRoleIds);
		inputParameters.put("wineAccessToken", wineAccessToken);
		
		return processRequest(apiKey,version,inputParameters,PROCCESS_DEF_NAME, APIEvent.MULTIPLE_DELETE_ROLE);
	}

}
