package com.wineaccess.data.model.security;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import com.wineaccess.application.constants.NamedQueryConstants;
import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.persistence.dao.GenericDAO;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class TokenModelRepository {
	
	public static List<TokenModel> getByToken(String token) {
		GenericDAO<TokenModel> genericDao = (GenericDAO<TokenModel>)  CoreBeanFactory.getBean("genericDAO");
		return genericDao.findByNamedQuery("getByToken", new String [] {"token"}, token);
	}
	
	
	public static void update(TokenModel token) {
		GenericDAO<TokenModel> genericDao = (GenericDAO<TokenModel>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.update(token);
	}
	
	public static void save(TokenModel token) {
		GenericDAO<TokenModel> genericDao = (GenericDAO<TokenModel>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.create(token);
	}
	
	public static List<TokenModel> getByUser(Long userId) {
		GenericDAO<TokenModel> genericDao = (GenericDAO<TokenModel>)  CoreBeanFactory.getBean("genericDAO");
		return genericDao.findByNamedQuery(NamedQueryConstants.GET_BY_USER_ID, new String [] {"userId"}, userId);
	}
	public static List<TokenModel> getInfoByUser(Long userId, String sortingCriteria, Integer offset, Integer limit) {
		GenericDAO<TokenModel> genericDao = (GenericDAO<TokenModel>)  CoreBeanFactory.getBean("genericDAO");
		List<TokenModel> userSession = genericDao.findByQuery("from TokenModel p where p.userid = "+userId,offset,limit,sortingCriteria);
		return userSession;
	}
	
public static List<Object[]> getUserAndSession(Integer offset, Integer limit){
		GenericDAO genericDao = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
		List<Object[]> userSession =(List<Object[]>) genericDao.findByNamedQuery("TokenModel.getUserAndSession");
		return userSession;
		}
	
	public static List<Object[]> getUserAndSessionInfo(Integer offset, Integer limit){
		GenericDAO genericDao = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
		List<Object[]> userSession =(List<Object[]>) genericDao.findByNamedQuery("TokenModel.getUserAndSessionSummary", offset,limit);
		return userSession;
		}
	
		public static Long getUserSessionCount(){
		GenericDAO genericDao = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
		
		List<Long> userSessionCount =(List<Long>) genericDao.findByNamedQuery("TokenModel.getCountOfTotalRecords");
		return userSessionCount.get(0);
		}
		
		public static List<Object[]> getUserAndSessionInfo(Integer offset, Integer limit, String sortingCriteria){
			sortingCriteria = sortingCriteria.replace("userid", "t.userid");
			GenericDAO genericDao = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
			List<Object[]> userSession =(List<Object[]>) genericDao.findByQuery("select t.userid,count(t.token) as totalSessions,max(t.sessionStartTime) as lastLogin, max(t.sessionEndTime) as lastLogout from TokenModel t, UserModel u where t.userid=u.id and u.isDeleted =0 group by t.userid", offset, limit, sortingCriteria);
			return userSession;
			}
		public static List<Object[]> getUserAndSessionInfo(Integer offset, Integer limit, String sortingCriteria, Integer keyword){
			sortingCriteria = sortingCriteria.replace("userid", "t.userid");
			GenericDAO genericDao = (GenericDAO)  CoreBeanFactory.getBean("genericDAO");
			List<Object[]> userSession =(List<Object[]>) genericDao.findByQuery("select t.userid,count(t.token) as totalSessions,max(t.sessionStartTime) as lastLogin, max(t.sessionEndTime) as lastLogout from TokenModel t, UserModel u where t.userid=u.id and u.isDeleted =0 and u.id like '%"+keyword+"%' group by t.userid", offset, limit, sortingCriteria);
			return userSession;
			}
	public static TokenModel getUserLatestLoginSession(Long userid){
			
			GenericDAO<TokenModel> genericDao = (GenericDAO<TokenModel>)  CoreBeanFactory.getBean("genericDAO");
			return genericDao.getFirstRecord("TokenModel.sortBySessionStartTime", new String [] {"userId"}, userid);
			
			
			}


		public static List<TokenModel> getUserOtherSessions(Long userId, String token) {
			
			GenericDAO<TokenModel> genericDao = (GenericDAO<TokenModel>)  CoreBeanFactory.getBean("genericDAO");
			return genericDao.findByNamedQuery("TokenModel.getUserOtherSessions", new String [] {"userId","token"}, userId,token);
			
		}


		public static TokenModel getUserSession(Long userId, Long tokenId) {
			GenericDAO<TokenModel> genericDao = (GenericDAO<TokenModel>)  CoreBeanFactory.getBean("genericDAO");
			return genericDao.getFirstRecord("TokenModel.getUserSessions", new String [] {"userId","id"}, userId,tokenId);
			
		}
		
		public static List loggedInUserList(List<? extends Serializable> idsList){
			GenericDAO<UserModel> genericDao = (GenericDAO<UserModel>)  CoreBeanFactory.getBean("genericDAO");
			List<UserModel> userModelList = genericDao.findByNamedQuery("TokenModel.loggedInUserList", new String [] {"idsList"}, idsList);
			return userModelList;
		}
		
		
		public static void updateEndtime(List<String> tokens) {
			GenericDAO<TokenModel> genericDao = (GenericDAO<TokenModel>)  CoreBeanFactory.getBean("genericDAO");
			for (String token : tokens) {
				List<TokenModel> tokenModels = getByToken(token);
				if (!tokenModels.isEmpty()) {
					tokenModels.get(0).setSessionEndTime(Calendar.getInstance().getTime());
					genericDao.update(tokenModels.get(0));
				}
			}
		}
		
		public static List<TokenModel> getActiveTokens() {
			GenericDAO<TokenModel> genericDao = (GenericDAO<TokenModel>)  CoreBeanFactory.getBean("genericDAO");
			return genericDao.findByNamedQuery("TokenModel.recoverToken", null, null);
		}
}
