/**
 * 
 */
package com.wineaccess.service.user.preference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wineaccess.data.model.user.NewsLetter;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.data.model.user.UserRepository;
import com.wineaccess.service.newsletter.NewsletterRepository;
import com.wineaccess.usermanagement.UserManagementPO;

/**
 * @author arpit.vijayvargiya@globallogic.com
 *
 */
public class UserPreferenceAdapterHelper {

	private static Log logger = LogFactory.getLog(UserPreferenceAdapterHelper.class);
	
	/**
	 * This method subscribes the user to a newsletter. 
	 */
	public static List<UserPreferenceVO> subscribeUserToNewsletter(UserModel userModelObject,UserManagementPO userManagementPO) {	
		
		/**
		 * Creating a newsletter map for performance improvement.  
		 */
		Map<Long, Object> newsletterMap = new HashMap<Long, Object>();
		
		/**
		 * Getting the list of all the newsletters and adding in newsletter map.  
		 */
		List<NewsLetter> newsletterList = NewsletterRepository.getAll();
		for(NewsLetter newsletterModel: newsletterList){
			newsletterMap.put(newsletterModel.getId(), newsletterModel);
		}
		
		
		if(null != userManagementPO){
			Long userId = userModelObject.getId();
			UserModel userModel = UserRepository.getByUserId(userId);
			/**
			 * If user exists in database.
			 */
			if(null != userModel){
				List<NewsletterList> newsletterPO = userManagementPO.getNewsletterList();
				Set<NewsLetter> newsletters = new HashSet<NewsLetter>();
				if(null != newsletterPO){
					for(NewsletterList newsletterListPO : newsletterPO){
						newsletters = getNewsletters(newsletterListPO, newsletterMap);
					}
					userModel.setNewsletters(newsletters);
					/**
					 * Updating the user back.
					 */
					UserRepository.update(userModel);
					
				}
			}
		}
		
		List<UserPreferenceVO> userPreferenceVO = createNewsletterVO(userModelObject);

		return userPreferenceVO;
	}
	
	/*public static Map<String, Object> subscriptionDetails(){
		Response response = null;
		Map<String, Object> output = new HashMap<String, Object>();
		
		UserPreferenceVOList userPreferenceVOList = new UserPreferenceVOList();
		
		List<UserModel> userModelList = (List<UserModel>) UserRepository.getAll();
		for(UserModel userModel : userModelList){
			UserPreferenceDetails userPreferenceDetails = new UserPreferenceDetails();
			userPreferenceDetails.setUserId(userModel.getId());
			userPreferenceDetails.setFirstName(userModel.getFirstName());
			userPreferenceDetails.setLastName(userModel.getLastName());
			userPreferenceDetails.setEmail(userModel.getEmail());
			
			Set<NewsLetter> newsletterSet = userModel.getNewsletters();
			for (NewsLetter newsletter : newsletterSet) {
				UserPreferenceVO userPreferenceVO = new UserPreferenceVO();
				userPreferenceVO.setId(newsletter.getId());
				userPreferenceVO.setName(newsletter.getName());
				userPreferenceDetails.getUserPreferenceVO().add(userPreferenceVO);
			}
			if(!newsletterSet.isEmpty())
				userPreferenceVOList.getUserPreferenceDetails().add(userPreferenceDetails);
			
		}
		response = new com.wineaccess.response.SuccessResponse(userPreferenceVOList, 200);	
		output.put("FINAL-RESPONSE", response);
		return output;
	} */
	
	public static List<UserPreferenceVO> subscriptionDetailsById(Long userId){
		
		UserModel userModel = UserRepository.getByUserId(userId);
		List<UserPreferenceVO> userPreferenceVOList = createNewsletterVO(userModel);
		
		return userPreferenceVOList;
	} 
	
	/*public static Map<String, Object> deleteSubscription(UserPreferenceByIdPO userPreferenceByIdPO){
		Response response = null;
		Map<String, Object> output = new HashMap<String, Object>();
		
		UserPreferenceVOList userPreferenceVOList = new UserPreferenceVOList();
		
		List<Long> userIds = userPreferenceByIdPO.getUserid();
		for(Long userId : userIds){
			
			UserModel userModel = UserRepository.getByUserId(userId);
			if(null != userModel){
				UserPreferenceDetails userPreferenceDetails = new UserPreferenceDetails();
				userPreferenceDetails.setUserId(userModel.getId());
				userPreferenceDetails.setFirstName(userModel.getFirstName());
				userPreferenceDetails.setLastName(userModel.getLastName());
				userPreferenceDetails.setEmail(userModel.getEmail());
				
				Set<NewsLetter> newsletterSet = userModel.getNewsletters();
				for (NewsLetter newsletter : newsletterSet) {
					UserPreferenceVO userPreferenceVO = new UserPreferenceVO();
					userPreferenceVO.setId(newsletter.getId());
					userPreferenceVO.setName(newsletter.getName());
					userPreferenceDetails.getUserPreferenceVO().add(userPreferenceVO);
				}
				if(!newsletterSet.isEmpty())
					userPreferenceVOList.getUserPreferenceDetails().add(userPreferenceDetails);
			}
		}
		
		response = new com.wineaccess.response.SuccessResponse(userPreferenceVOList, 200);	
		output.put("FINAL-RESPONSE", response);
		return output;
	} */
	
	public static Set<NewsLetter> getNewsletters(NewsletterList newsletterListPO, Map<Long, Object> newsletterMap){
		Set<NewsLetter> newsletters = new HashSet<NewsLetter>();
		
		if(null != newsletterListPO.getNewsletterId() && !("").equals(newsletterListPO.getNewsletterId() )){
			Long[] newsletterIds = newsletterListPO.getNewsletterId();
			for(Long newsletterId: newsletterIds){
				/**
				 * If newsletter exists in database.
				 */
				if(newsletterMap.containsKey(newsletterId)){
					NewsLetter newsLetter = (NewsLetter) newsletterMap.get(newsletterId);
					newsletters.add(newsLetter);
				}	
			}										
		}
		else{
			newsletters = null;
		}
		return newsletters; 
	}
	
	
	public static List<UserPreferenceVO> createNewsletterVO(UserModel userModelObject){
		List<UserPreferenceVO> userPreferenceVOList = new ArrayList<UserPreferenceVO>();
		if(null != userModelObject){
			UserModel userModel = UserRepository.getByUserId(userModelObject.getId());
			if(null != userModel){
				Set<NewsLetter> newsletters = userModel.getNewsletters();
				for(NewsLetter newsletter: newsletters){
					UserPreferenceVO userPreferenceVO = new UserPreferenceVO();
					try{
						BeanUtils.copyProperties(userPreferenceVO, newsletter);	
					}
					catch(Exception e){
						logger.error("Error while copying the data from Model to VO.");
					}				
					userPreferenceVOList.add(userPreferenceVO);
				}	
			}			
		}	
		return userPreferenceVOList;
	}
}
