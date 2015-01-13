package com.wineaccess.data.model;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.wineaccess.data.model.user.UserAddress;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.data.model.user.UserRepository;

public class UserManagementEntityListner extends EntityListener {

	public static Long adminId = 1L;



	@PrePersist
	public void onPrePersist(Object o) {


		super.onPrePersist(o);
		Long userId = ((UserAddress)o).getUserId();
		UserModel userModel = UserRepository.getByUserId(userId);
		userModel.setModifiedBy(((UserAddress)o).getCreatedBy());
		userModel.setModifiedDate(((UserAddress)o).getCreatedDate());
		UserRepository.update(userModel);


	}

	@PreUpdate
	public void onPreUpdate(Object o) {
		System.out.println("checkpoint0");
		super.onPreUpdate(o);
		/*System.out.println("checkpoint1");
		Long userId = ((UserAddress)o).getUserId();
		System.out.println("checkpoint2"+userId);
		UserModel userModel = UserRepository.getByUserId(userId);
		System.out.println("checkpoint3");
		userModel.setModifiedBy(((UserAddress)o).getModifiedBy());
		userModel.setModifiedDate(((UserAddress)o).getModifiedDate());
		UserRepository.update(userModel);*/
	}



/*
	@PreRemove
	public void onPreDelete(Object o) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		((UserModel)o).setModifiedBy(((UserAddress)o).getCreatedBy());
		((UserModel)o).setModifiedDate(((UserAddress)o).getCreatedDate());
		UserRepository.update((UserModel)o);
	}*/


}
