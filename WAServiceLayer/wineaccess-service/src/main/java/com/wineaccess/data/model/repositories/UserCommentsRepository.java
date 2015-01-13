package com.wineaccess.data.model.repositories;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wineaccess.application.constants.NamedQueryConstants;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.user.UserComments;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.persistence.dao.GenericDAO;
import com.wineaccess.persistence.exception.PersistenceException;

/**
 * @author gaurav.agarwal1
 *
 */
public class UserCommentsRepository {
	
	@PersistenceContext
	EntityManager em;
	
	public static void save(UserComments userComments) {
		GenericDAO<UserComments> genericDao = (GenericDAO<UserComments>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.create(userComments);
	}
	
	public static UserComments getByUserIdCommentId(Long userId,Long commentId) {
		GenericDAO<UserComments> genericDao = (GenericDAO<UserComments>)  CoreBeanFactory.getBean("genericDAO");
		List<UserComments> modelList = genericDao.findByNamedQuery(NamedQueryConstants.GET_BY_USER_ID_COMMENT_ID, new String [] {"userId", "commentId"}, userId, commentId);
		if(modelList!= null & !modelList.isEmpty()){
			return modelList.get(0);
		}else{
			return null;
		}
	}
	
	public static UserComments update(UserComments userComments) {
		GenericDAO<UserComments> genericDao = (GenericDAO<UserComments>)  CoreBeanFactory.getBean("genericDAO");
		return genericDao.update(userComments);
	}
	
	public static BulkDeleteModel<UserComments> multipleDeleteComments(String multipleCommentIds,Boolean confirmStatus) {
		GenericDAO<UserComments> genericDAO = (GenericDAO<UserComments>) CoreBeanFactory.getBean("genericDAO");

		List<Serializable> id = new ArrayList<Serializable>();

		String[] commentIds = multipleCommentIds.split(",");
		for (int i = 0; i < commentIds.length; i++) {
			id.add(Long.parseLong(commentIds[i]));
		}

		BulkDeleteModel<UserComments> bulkDeleteModel = new BulkDeleteModel<UserComments>();
		try {
			bulkDeleteModel = genericDAO.performBulkDelete(id,
					UserComments.class, "UserComments",null,null,null,confirmStatus);
		} catch (Exception e) {
			if (e instanceof PersistenceException) {
				PersistenceException persistenceException = (PersistenceException) e;
				bulkDeleteModel = (BulkDeleteModel<UserComments>) (persistenceException.getData());
			}
		} finally {
			return bulkDeleteModel;
		}

	}
	
	public static List<UserComments> getCommentsList(Long userId,Integer offset, Integer limit){
		GenericDAO genericDao = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
	    return genericDao.findByQuery("from UserComments c where c.userId = "+userId+" order by createdDate desc", offset, limit,null);

	}
	
	public static List<UserComments> getCommentsByUserId(Long userId){
		GenericDAO genericDao = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
	    return genericDao.findByNamedQuery(NamedQueryConstants.GET_COMMENTS_BY_USER_ID, new String [] {"userId"},userId);

	}
	
	public static void delete(UserComments userComments) {
		GenericDAO<UserComments> genericDao = (GenericDAO<UserComments>) CoreBeanFactory.getBean("genericDAO");
		genericDao.delete(UserComments.class, userComments.getId());
	}

}
