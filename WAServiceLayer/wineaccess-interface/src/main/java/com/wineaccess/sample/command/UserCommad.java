package com.wineaccess.sample.command;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Path;

import com.wineaccess.command.BaseCommand;

/**
 * @author jyoti.yadav@globallogic.com
 */
@Path("/{version}/users")
public class UserCommad extends BaseCommand {
	
	@PersistenceContext
	EntityManager em;
	
	/*@GET
	@Path("/list")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public javax.ws.rs.core.Response listAll(@HeaderParam("X-API-KEY") String apiKey, @PathParam("version") String version,@DefaultValue("30") @QueryParam("size") int size ) {
		
		if (!validateApiKeyAndVersion(apiKey, version)) {
			return javax.ws.rs.core.Response.ok(prepareFailureApiKeyResponse()).build();
		}
		
		List <UserModel> models = UserRepository.getAll();
		
		UserVO users = new UserVO(models);
		
		Response response = new SuccessResponse(users, 200); 
		return javax.ws.rs.core.Response.ok(response).build();
	}*/
	
	
	
	/*@GET
	@Path("/list/{search}/{offset}/{limit}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public javax.ws.rs.core.Response listAll(@HeaderParam("X-API-KEY") String apiKey, @PathParam("version") String version,@PathParam("search") String search, @DefaultValue("0") @PathParam("offset") Integer offset,  @DefaultValue("30") @PathParam("limit") Integer limit) {
		
		if (!validateApiKeyAndVersion(apiKey, version)) {
			return javax.ws.rs.core.Response.ok(prepareFailureApiKeyResponse()).build();
		}
		
		GenericDAO<UserModel> genericDao = (GenericDAO<UserModel>)  CoreBeanFactory.getBean("genericDAO");
		
		List<UserModel> models = (List<UserModel>) genericDao.getSearch(search, offset, limit);
		
		UserVO users = new UserVO(models);
		Response response = new SuccessResponse(users, 200);
		
		return javax.ws.rs.core.Response.ok(response).build();
	}*/
}
