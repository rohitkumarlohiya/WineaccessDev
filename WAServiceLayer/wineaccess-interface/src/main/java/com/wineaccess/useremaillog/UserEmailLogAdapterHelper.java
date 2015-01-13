package com.wineaccess.useremaillog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wineaccess.data.model.user.UserEmailLog;
import com.wineaccess.data.model.user.UserEmailLogRepository;
import com.wineaccess.response.Response;

public class UserEmailLogAdapterHelper {

    public static Map<String, Object> listUserEmailLogs() {

	Map<String, Object> output = new HashMap<String, Object>();

	Response response = null;

	List<UserEmailLog> userEmailLogs = UserEmailLogRepository
		.getUserEmailLogs();

	List<UserEmailLogCustomModel> userEmailLogCustomModels = getUserEmailLogCustomModels(userEmailLogs);

	UserEmailLogListVO userEmailLogListVO = new UserEmailLogListVO(
		userEmailLogCustomModels);

	response = new com.wineaccess.response.SuccessResponse(
		userEmailLogListVO, 200);

	output.put("FINAL-RESPONSE", response);

	return output;
    }

    public static Map<String, Object> getUserEmailLogsByKeyword(
	    final String fieldName, final String keyword, final String sortBy,
	     int offSet, final int limit, final int sortOrder) {
	// offSet = offSet-1;

	Map<String, Object> output = new HashMap<String, Object>();

	Response response = null;

	List<UserEmailLog> userEmailLogs = UserEmailLogRepository.getByKeyword(
		UserEmailLog.class, fieldName, keyword, sortBy, offSet-1, limit,
		sortOrder);

	List<UserEmailLogCustomModel> userEmailLogCustomModels = getUserEmailLogCustomModels(userEmailLogs);

	UserEmailLogSearchVO userEmailLogSearchVO = new UserEmailLogSearchVO(
		userEmailLogs.size(), UserEmailLogRepository.getUserEmailLogs()
		.size(), offSet, limit, keyword,
		userEmailLogCustomModels);

	response = new com.wineaccess.response.SuccessResponse(
		userEmailLogSearchVO, 200);

	output.put("FINAL-RESPONSE", response);

	return output;
    }

    private static List<UserEmailLogCustomModel> getUserEmailLogCustomModels(
	    List<UserEmailLog> userEmailLogs) {

	List<UserEmailLogCustomModel> userEmailLogCustomModels = new ArrayList<UserEmailLogCustomModel>();

	for (UserEmailLog userEmailLog : userEmailLogs) {

	    UserEmailLogCustomModel userEmailLogCustomModel = new UserEmailLogCustomModel(
		    userEmailLog.getId(), userEmailLog.getContent(),
		    userEmailLog.getSubject(), userEmailLog.getUserId(),
		    userEmailLog.isDeliveryStatus(), userEmailLog.getCreatedDate());

	    userEmailLogCustomModels.add(userEmailLogCustomModel);

	}

	return userEmailLogCustomModels;
    }

}
