package com.wineaccess.user.comments;

import java.util.ArrayList;
import java.util.List;

import com.wineaccess.data.model.repositories.UserCommentsRepository;
import com.wineaccess.data.model.user.UserComments;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.data.model.user.UserRepository;
import com.wineaccess.model.BulkDeleteModel;
import com.wineaccess.property.utils.ErrorPropertyHolderUtil;
import com.wineaccess.user.activity.log.UserServiceModel;

/**
 * @author gaurav.agarwal1
 *
 */
public class UserCommentHelper {
	
	public static UserCommentListVO getUserCommentsList(final int offset, final int limit,Long userId) {
		
		UserCommentListVO commentListVO = new UserCommentListVO();
		commentListVO.setOffset(offset + 1);
		commentListVO.setLimit(limit);
		commentListVO.setUserId(userId);
		commentListVO.setTotalResultsCount((UserCommentsRepository.getCommentsByUserId(userId)).size());
	    List<UserComments> commentList = UserCommentsRepository.getCommentsList(userId,offset,limit);
	    List<UserCommentModel> listModel = new ArrayList<UserCommentModel>();
		if(commentList!= null && !commentList.isEmpty()){
			for(UserComments userComments : commentList){
				UserCommentModel commentModel = new UserCommentModel();
				commentModel.setComment(userComments.getComment());
				commentModel.setCreatedtDate(userComments.getCreatedDate());
				commentModel.setCommentId(userComments.getId());
				
				UserModel userdetails = UserRepository.findUserByUserId(userComments.getCreatedBy());
				UserServiceModel serviceModel = new UserServiceModel(userdetails.getId(), userdetails.getFirstName(), userdetails.getLastName(), userdetails.getEmail());
				commentModel.setCreatedBy(serviceModel);
				
				listModel.add(commentModel);
			}
			commentListVO.setComments(listModel);
			return commentListVO;
	    }else{
	    	return null;
	    }
	}
	
	public static UserCommentDeleteVO deleteCommentById(Long userId,Long commentId) {

		UserComments userComments = UserCommentsRepository.getByUserIdCommentId(userId, commentId);
		UserCommentDeleteVO commentDeleteVO = null;
		if (userComments != null) {
			try {
				UserCommentsRepository.delete(userComments);
			    commentDeleteVO = new UserCommentDeleteVO(ErrorPropertyHolderUtil.getStringProperty("COMMONUSER103"));
			    
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		return commentDeleteVO;
	}
	
	public static UserCommentMultiDeleteVO multipleDeleteComments(String multipleCommentIds,Boolean confirmStatus) {

		BulkDeleteModel<UserComments> bulkDeleteModel = UserCommentsRepository.multipleDeleteComments(multipleCommentIds,confirmStatus);
		List<Long> deletedCommentIds = new ArrayList<Long>();
		List<Long> notDeletedCommentIds = new ArrayList<Long>();
		if(bulkDeleteModel.getDeletedList()!=null){					
			for(UserComments deletedComments : bulkDeleteModel.getDeletedList())
			{	
				deletedCommentIds.add(deletedComments.getId());
				
			}
		}
		if(bulkDeleteModel.getNotDeletedList()!=null){
			for(UserComments notDeletedComments : bulkDeleteModel.getNotDeletedList()){
				notDeletedCommentIds.add(notDeletedComments.getId());
			}
		}
		UserCommentMultiDeleteVO commentMultiDeleteVO = new UserCommentMultiDeleteVO(new MultiDeleteCommentsForNotExists(bulkDeleteModel.getNotExistsList().size(),(List<Long>)(bulkDeleteModel.getNotExistsList())),new MultiDeleteCommentsForDependency(bulkDeleteModel.getDeletedList().size(),deletedCommentIds),new MultiDeleteCommentsForDependency(bulkDeleteModel.getNotDeletedList().size(),notDeletedCommentIds));
		return commentMultiDeleteVO;
	}

}
