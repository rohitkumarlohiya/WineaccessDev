package com.wineaccess.security.login.history;

import java.util.ArrayList;
import java.util.List;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.data.model.security.TokenModel;
import com.wineaccess.data.model.security.TokenModelRepository;

/**
 * @author gaurav.agarwal1
 *
 */
public class LoginHistoryHelper {
	
	public static String generateSortingCriteria(String fieldName, int sortOrder) {
		StringBuilder sortCriteria = new StringBuilder(" order by p." + fieldName);
		sortCriteria.append((sortOrder == 0) ? " desc" :" asc");
		return sortCriteria.toString();
		
	}
	
	public static String generatePagingCriteria(Integer offset, Integer limit) {
		StringBuilder criteria = new StringBuilder(" limit ");
		criteria.append(offset).append(",");
		criteria.append(limit);
		return criteria.toString();
		
	}

	public static String createCriteria( String sortingCriteria, String pagingCriteria) {

		StringBuilder criteria = new StringBuilder();
		criteria.append(" ").append(sortingCriteria).append(pagingCriteria);
		return criteria.toString();
		
	}
	
	/*public static boolean isValidLoginHistoryRequest(String sortBy, Integer sortOrder){
		boolean isValidData = false;
		String[] sortRegex = new String[]{"userid","browser","ipAddress","operatingSystem","platformDevice","sessionStartTime","sessionEndTime"};

		Arrays.sort(sortRegex);
		if(((Arrays.binarySearch(sortRegex, sortBy) >= 0) && (sortOrder==0 || sortOrder ==1))){
			isValidData = true;
		}
		
		return isValidData;
	}*/
	
	public static LoginHistoryVO populateLoginHistory(Long userId, String sortingCriteria, Integer offset, Integer limit, LoginHistoryVO historyVO){
		//Bug fix for missing first record when no offset is passed through API.
		if(null != offset && offset < 1)
			offset = 1;
		List<TokenModel> tokenModels = TokenModelRepository.getInfoByUser(userId, sortingCriteria, offset-1, limit);
		
		List<SessionHistoryVO> historyVOs = new ArrayList<SessionHistoryVO>();
		if (!tokenModels.isEmpty()) {
			for (TokenModel model : tokenModels) {
				if (userId == model.getUserid()) {
					SessionHistoryVO sessionHistoryVO = new SessionHistoryVO();
					if(model.getBrowser() == null){
						sessionHistoryVO.setBrowser("chrome");
					}else{
						sessionHistoryVO.setBrowser(model.getBrowser());
					}
					if(model.getIpAddress() == null){
						sessionHistoryVO.setIpAddress("1.1.1.1");
					}else{
						sessionHistoryVO.setIpAddress(model.getIpAddress());
					}
					if(model.getOperatingSystem() == null){
						sessionHistoryVO.setOperatingSystem("windows");
					}else{
						sessionHistoryVO.setOperatingSystem(model.getOperatingSystem());
					}
					if(model.getPlatformDevice() == null){
						sessionHistoryVO.setPlatformDevice("mobile");
					}else{
						sessionHistoryVO.setPlatformDevice(model.getPlatformDevice());
					}
					
					sessionHistoryVO.setSessionEndTime(ApplicationUtils.convertDateToString(model.getSessionEndTime()));
					sessionHistoryVO.setSessionStartTime(ApplicationUtils.convertDateToString(model.getSessionStartTime()));
					historyVOs.add(sessionHistoryVO);
				}
			}
			historyVO = new LoginHistoryVO(userId, historyVOs);
			historyVO.setCount(tokenModels.size());
			historyVO.setTotalRecordsCount(TokenModelRepository.getByUser(userId).size());
		}
		return historyVO;
	}

}
