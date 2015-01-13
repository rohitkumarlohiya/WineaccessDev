package com.wineaccess.service.newsletter;


import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.ApplicationUtil.ValidationUtil;
import com.wineaccess.application.constants.OperationNameEnum;
import com.wineaccess.auditManager.AuditManager.APIEvent;
import com.wineaccess.command.BaseCommand;
import com.wineaccess.constants.ApplicationConstants;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;

/**
 * @author arpit.vijayvargiya@globallogic.com
 * 
 */
@Path("/{version}/newsletter")
public class NewsletterCommand extends BaseCommand {

	final String PROCCESS_DEF_NAME = ApplicationConstants.PROCESSDEF.NEWS_LETTER
			.getProcessDefinationName();
	final int HTTP_ERROR_CODE = 400;

	/**
	 * @param apiKey -is used to validate the version of api
	 * @param version -version name of api
	 * @param -newsletterId - id of newsletter to delete
	 * @param - wineAccessToken -token provided by the administrator upon login
	 * 
	 * The purpose of this method is to get the list of all the newsletters or searching for a newsletter based on a keyword passed.
	 */
	@POST
	@Path("{search}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public javax.ws.rs.core.Response searchNewsletter(
			@HeaderParam("X-API-KEY") String apiKey,
			@HeaderParam("WINEACCESS_TOKEN") String wineAccessToken,
			@PathParam("version") String version, NewsletterSearchPO searchPO) {	
		
		boolean isValid = true;
		
		if(!(isValid
			&&		
			(ValidationUtil.validateContent(String.valueOf(searchPO.getOffSet()), "[1-9]((\\d)+)?")) 
			&&
			(ValidationUtil.validateContent(String.valueOf(searchPO.getLimit()), "[1-9]((\\d)+)?")) 
			&&
			(ValidationUtil.validateContent(String.valueOf(searchPO.getSortOrder()), "[0,1]"))
			))
		{
			return javax.ws.rs.core.Response
					.ok(ApplicationUtils
							.errorMessageGenerate(
									SystemErrorCode.NEWSLETTER_SEARCH_INVALID_PARAM,
									SystemErrorCode.NEWSLETTER_SEARCH_INVALID_PARAM_TEXT,
									200)).build();
		}

		Map<String, Object> inputParameters = new HashMap<String, Object>();

		inputParameters.put("operation", OperationNameEnum.BASIC_SEARCH_BY_KEYWORD);
		inputParameters.put("wineAccessToken", wineAccessToken);
		inputParameters.put("offSet", searchPO.getOffSet());
		inputParameters.put("limit",searchPO.getLimit());
		inputParameters.put("sortBy", searchPO.getSortBy());
		inputParameters.put("sortOrder", searchPO.getSortOrder());
		inputParameters.put("keyword", searchPO.getKeyword());
		
		return processRequest(apiKey,version,inputParameters,PROCCESS_DEF_NAME, APIEvent.SEARCH_NEWSLETTER);

	}
	
	/**
	 * @param apiKey -is used to validate the version of api
	 * @param version -version name of api
	 * @param wineAccessToken -token provided by the administrator upon login
	 * 
	 * The purpose of this method is to get the list of newsletters based on newsletter id.
	 */
	@GET
	@Path("{newsletterId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public javax.ws.rs.core.Response listNewsletterById(
			@HeaderParam("X-API-KEY") String apiKey,
			@HeaderParam("WINEACCESS_TOKEN") String wineAccessToken,
			@PathParam("version") String version,
			@PathParam("newsletterId") String newsletterId) {

		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.GET_BY_ID);
		inputParameters.put("wineAccessToken", wineAccessToken);
		inputParameters.put("newsletterId", newsletterId);

		return processRequest(apiKey, version, inputParameters,
				PROCCESS_DEF_NAME, APIEvent.NEWSLETTER_LIST_BY_ID);

	}
	
	/**
	 * @param apiKey -is used to validate the version of api
	 * @param version -version name of api
	 * @param -newsletterPO -object which shows information about added object in database
	 * @param wineAccessToken -token provided by the administrator upon login
	 * 
	 * The purpose of this method is add the newsletter.
	 */
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public javax.ws.rs.core.Response addNewsletter(
			@HeaderParam("X-API-KEY") String apiKey,@HeaderParam("WINEACCESS_TOKEN") String wineAccessToken,
			@PathParam("version") String version, NewsletterPO newsletterPO) {	

		Map<String, Object> inputParameters = new HashMap<String, Object>();
		inputParameters.put("operation", OperationNameEnum.ADD);
		inputParameters.put("wineAccessToken", wineAccessToken);
		inputParameters.put("newsletterPO", newsletterPO);
		
		
		return processRequest(apiKey,version,inputParameters,PROCCESS_DEF_NAME, APIEvent.ADD_NEWSLETTER, newsletterPO);

	}
	
	/**
	 * @param apiKey -is used to validate the version of api
	 * @param version -version name of api
	 * @param -newsletterPO -object which shows information about added object in database
	 * @param - wineAccessToken -token provided by the administrator upon login
	 * 
	 * The purpose of this method is to update the newsletter.
	 */
	@PUT
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public javax.ws.rs.core.Response updateNewsletter(
			@HeaderParam("X-API-KEY") String apiKey,@HeaderParam("WINEACCESS_TOKEN") String wineAccessToken,
			@PathParam("version") String version, NewsletterPO newsletterPO) {	

		Map<String, Object> inputParameters = new HashMap<String, Object>();

		inputParameters.put("operation", OperationNameEnum.UPDATE);
		inputParameters.put("wineAccessToken", wineAccessToken);
		inputParameters.put("newsletterPO", newsletterPO);
		
		return processRequest(apiKey,version,inputParameters,PROCCESS_DEF_NAME, APIEvent.UPDATE_NEWSLETTER, newsletterPO);

	}
	
	/**
	 * @param apiKey -is used to validate the version of api
	 * @param version -version name of api
	 * @param -newsletterId - id of newsletter to delete
	 * @param - wineAccessToken -token provided by the administrator upon login
	 * 
	 * The purpose of this method is to delete the newsletter based on the newsletter id passed.
	 */
	@PUT
	@Path("/delete")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public javax.ws.rs.core.Response deleteNewsletter(
			@HeaderParam("X-API-KEY") String apiKey,@HeaderParam("WINEACCESS_TOKEN") String wineAccessToken,
			@PathParam("version") String version, NewsletterDeletePO newsletterDeletePO) {	

		Map<String, Object> inputParameters = new HashMap<String, Object>();

		inputParameters.put("operation", OperationNameEnum.DELETE);
		inputParameters.put("wineAccessToken", wineAccessToken);
		inputParameters.put("newsletterDeletePO", newsletterDeletePO);
		
		return processRequest(apiKey,version,inputParameters,PROCCESS_DEF_NAME, APIEvent.UPDATE_NEWSLETTER);

	}
	
}
