package com.wineaccess.user.comments;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.ApplicationUtil.ValidationUtil;
import com.wineaccess.base.adapter.task.WineaccessBaseTask;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.data.model.repositories.UserCommentsRepository;
import com.wineaccess.data.model.user.UserComments;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.data.model.user.UserRepository;
import com.wineaccess.orchestration.workflow.process.context.ProcessContext;
import com.wineaccess.orchestration.workflow.processdefinition.TaskConfiguration;
import com.wineaccess.property.utils.ErrorPropertyHolderUtil;
import com.wineaccess.response.Response;
import com.wineaccess.user.activity.log.UserServiceModel;

public class UserCommentsAdapter extends WineaccessBaseTask{

    private static Log logger = LogFactory.getLog(UserCommentsAdapter.class);
    public UserCommentsAdapter(String taskName,TaskConfiguration taskConfiguration) {
	super(taskName, taskConfiguration);
    }

    @Override
    protected void doExecute(ProcessContext pContext) throws Exception {

	String operationName = getOperationName(pContext);

	Map<String, Object> output = new HashMap<String, Object>();
	Response response = null;

	switch (operationName) {
	case "ADD":
	    UserCommentAddPO commentsPO = (UserCommentAddPO) getObject(pContext, UserCommentAddPO.class);
	    UserModel userModel = UserRepository.getByUserId(commentsPO.getUserId());
	    if (userModel==null) {

		response =  ApplicationUtils.errorMessageGenerate(SystemErrorCode.NO_RECORD_EXISTS_ID, SystemErrorCode.NO_RECORD_EXISTS_ID_TXT, 200);

	    } else {
		UserComments comments = new UserComments();
		comments.setUserId(commentsPO.getUserId());
		comments.setComment(commentsPO.getComment());
		try{
		    UserCommentsRepository.save(comments);
		}catch(Exception e){
		    logger.error("error during the user comment save process", e);
		}
		UserCommentAddVO commentsVO = new UserCommentAddVO(ErrorPropertyHolderUtil.getStringProperty("COMMONUSER101"));
		response = new com.wineaccess.response.SuccessResponse(commentsVO,200);

	    }
	    break;

	case "GET_BY_ID":
	    ViewCommentByCommentIdPO viewCommentByCommentIdPO = (ViewCommentByCommentIdPO) getObject(pContext, ViewCommentByCommentIdPO.class);
	    UserComments userComments = UserCommentsRepository.getByUserIdCommentId(viewCommentByCommentIdPO.getUserId(), viewCommentByCommentIdPO.getCommentId());
	    if (userComments==null) {
		response =  ApplicationUtils.errorMessageGenerate(SystemErrorCode.NO_RECORD_EXISTS_ID, SystemErrorCode.NO_RECORD_EXISTS_ID_TXT, 200);
	    } else {
		UserCommentGetByIdVO byIdVO = new UserCommentGetByIdVO();
		byIdVO.setComment(userComments.getComment());
		byIdVO.setCommentId(userComments.getId());
		byIdVO.setCreatedDate(userComments.getCreatedDate());
		byIdVO.setUserId(userComments.getUserId());

		UserModel userDetails = UserRepository.findUserByUserId(userComments.getCreatedBy());
		UserServiceModel serviceModel = new UserServiceModel(userDetails.getId(), userDetails.getFirstName(), userDetails.getLastName(), userDetails.getEmail());
		byIdVO.setCreatedBy(serviceModel);
		response = new com.wineaccess.response.SuccessResponse(byIdVO,200);
	    }
	    break;

	case "UPDATE_BY_ID":
	    UserCommentEditPO commentEditPO = (UserCommentEditPO) getObject(pContext, UserCommentEditPO.class);
	    Long.parseLong(commentEditPO.getCommentId());
	    UserComments userComment = UserCommentsRepository.getByUserIdCommentId(commentEditPO.getUserId(), Long.parseLong(commentEditPO.getCommentId()));
	    if (userComment==null) {
		response =  ApplicationUtils.errorMessageGenerate(SystemErrorCode.NO_RECORD_EXISTS_ID, SystemErrorCode.NO_RECORD_EXISTS_ID_TXT, 200);
	    } else {
		UserComments comments = new UserComments();
		comments.setId( Long.parseLong(commentEditPO.getCommentId()));
		comments.setUserId(commentEditPO.getUserId());
		comments.setComment(commentEditPO.getComment());
		try{
		    UserCommentsRepository.update(comments);
		}catch(Exception ex){
		    logger.error("error during the update user comment", ex);
		}
		UserCommentsUpdateVO updateVO = new UserCommentsUpdateVO(ErrorPropertyHolderUtil.getStringProperty("COMMONUSER102"));
		response = new com.wineaccess.response.SuccessResponse(updateVO,200);
	    }
	    break;

	case "LIST":
	    ListCommentsByUserIdPO listCommentsByUserIdPO = (ListCommentsByUserIdPO) getObject(pContext, ListCommentsByUserIdPO.class);
	    final int offset = Integer.parseInt(listCommentsByUserIdPO.getOffSet());
	    final int limit = Integer.parseInt(listCommentsByUserIdPO.getLimit());
	    Long userId = listCommentsByUserIdPO.getUserId();

	    UserCommentListVO commentListVO = UserCommentHelper.getUserCommentsList(offset-1, limit,userId);
	    if (commentListVO==null) {
		response =  ApplicationUtils.errorMessageGenerate(SystemErrorCode.NO_RECORD_EXISTS_ID, SystemErrorCode.NO_RECORD_EXISTS_ID_TXT, 200);
	    } else {
		response = new com.wineaccess.response.SuccessResponse(commentListVO, 200);
	    }
	    break;

	case "DELETE":
	    ViewCommentByCommentIdPO deleteCommentByCommentId = (ViewCommentByCommentIdPO) getObject(pContext, ViewCommentByCommentIdPO.class);
	    UserCommentDeleteVO commentDeleteVO = UserCommentHelper.deleteCommentById(deleteCommentByCommentId.getUserId(), deleteCommentByCommentId.getCommentId());
	    if (commentDeleteVO==null) {
		response =  ApplicationUtils.errorMessageGenerate(SystemErrorCode.NO_RECORD_EXISTS_ID, SystemErrorCode.NO_RECORD_EXISTS_ID_TXT, 200);
	    } else {
		response = new com.wineaccess.response.SuccessResponse(commentDeleteVO, 200);
	    }
	    break;

	case "DELETE_ALL":
	    UserCommentMultiDeletePO multiDeletePO  = (UserCommentMultiDeletePO) getObject(pContext, UserCommentMultiDeletePO.class);
	    boolean isValidFormat = ValidationUtil.validateContent(multiDeletePO.getCommentIds(), "^[-,0-9 ]+$");
	    if (!isValidFormat) {
		response =  ApplicationUtils.errorMessageGenerate(SystemErrorCode.INVALID_PARAMS, SystemErrorCode.INVALID_PARAMS_TEXT, 200);
	    } else {
		UserModel userDetails = UserRepository.getByUserId(multiDeletePO.getUserId());
		if (userDetails==null) {

		    response =  ApplicationUtils.errorMessageGenerate(SystemErrorCode.NO_RECORD_EXISTS_ID, SystemErrorCode.NO_RECORD_EXISTS_ID_TXT, 200);

		} else {
		    UserCommentMultiDeleteVO commentMultiDeleteVO = UserCommentHelper.multipleDeleteComments(multiDeletePO.getCommentIds(),multiDeletePO.getConfirmStatus());
		    response = new com.wineaccess.response.SuccessResponse(commentMultiDeleteVO, 200);
		}
	    }
	    break;

	default:
	    break;
	}

	output.put("FINAL-RESPONSE", response);
	getDataRepositoryManager().addOutput(pContext.getRequestId(), output);

    }

}
