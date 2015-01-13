package com.wineaccess.data.model;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.wineaccess.data.model.common.EmailTemplate;
import com.wineaccess.data.model.common.EmailTemplateRepository;
import com.wineaccess.security.token.WineaccessSecurityUserDetails;

public class EmailTemplateEntityListner extends EntityListener {
	
	public static Long adminId = 1L;	
	
	@PrePersist
	public void onPrePersist(Object o) {

	
		super.onPrePersist(o);
		((EmailTemplate)o).getEmailTemplateType().setModifiedBy(((EmailTemplate)o).getCreatedBy());
		((EmailTemplate)o).getEmailTemplateType().setModifiedDate(((EmailTemplate)o).getCreatedDate());
		EmailTemplateRepository.updateEmailTemplateType(((EmailTemplate)o).getEmailTemplateType());
	
		
	}

	@PreUpdate
	public void onPreUpdate(Object o) {
		super.onPreUpdate(o);
		((EmailTemplate)o).getEmailTemplateType().setModifiedBy(((EmailTemplate)o).getModifiedBy());
		((EmailTemplate)o).getEmailTemplateType().setModifiedDate(((EmailTemplate)o).getCreatedDate());
		EmailTemplateRepository.updateEmailTemplateType(((EmailTemplate)o).getEmailTemplateType());
	}
	
	
	

	@PreRemove
	public void onPreDelete(Object o) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		((EmailTemplate)o).getEmailTemplateType().setModifiedBy(((WineaccessSecurityUserDetails) authentication
				.getPrincipal()).getUserId());
		((EmailTemplate)o).getEmailTemplateType().setModifiedDate(new Date());
		EmailTemplateRepository.updateEmailTemplateType(((EmailTemplate)o).getEmailTemplateType());
	}


}
