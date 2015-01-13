package com.wineaccess.command.lucene.index;

import java.util.HashSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.command.BaseCommand;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.persistence.dao.GenericDAO;
import com.wineaccess.response.WineaccessError;

/**
 * @author jyoti.yadav@globallogic.com
 */
@Path("/lucene/index")
public class LuceneIndexCommand extends BaseCommand {
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response index() {
		
		GenericDAO<UserModel> genericDao = (GenericDAO<UserModel>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.indexLucene();
		
		com.wineaccess.response.Response response = new com.wineaccess.response.FailureResponse(new HashSet<WineaccessError>(), 200);
		
		return  javax.ws.rs.core.Response.ok(response).build();
	}
}
