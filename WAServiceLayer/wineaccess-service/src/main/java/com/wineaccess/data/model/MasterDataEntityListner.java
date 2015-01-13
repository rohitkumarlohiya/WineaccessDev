package com.wineaccess.data.model;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.common.MasterDataRepository;
import com.wineaccess.security.token.WineaccessSecurityUserDetails;

public class MasterDataEntityListner extends EntityListener {
	
	public static Long adminId = 1L;

	
	
	@PrePersist
	public void onPrePersist(Object o) {

	
		super.onPrePersist(o);
		((MasterData)o).getMasterDataType().setModifiedBy(((MasterData)o).getCreatedBy());
		((MasterData)o).getMasterDataType().setModifiedDate(((MasterData)o).getCreatedDate());
		MasterDataRepository.updateMasterDataType(((MasterData)o).getMasterDataType());
	
		
	}

	@PreUpdate
	public void onPreUpdate(Object o) {
		super.onPreUpdate(o);
		((MasterData)o).getMasterDataType().setModifiedBy(((MasterData)o).getModifiedBy());
		((MasterData)o).getMasterDataType().setModifiedDate(((MasterData)o).getCreatedDate());
		MasterDataRepository.updateMasterDataType(((MasterData)o).getMasterDataType());
	}
	
	
	

	@PreRemove
	public void onPreDelete(Object o) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		((MasterData)o).getMasterDataType().setModifiedBy(((WineaccessSecurityUserDetails) authentication
				.getPrincipal()).getUserId());
		((MasterData)o).getMasterDataType().setModifiedDate(new Date());
		MasterDataRepository.updateMasterDataType(((MasterData)o).getMasterDataType());
	}


}
