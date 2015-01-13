package com.wineaccess.data.model;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.security.token.WineaccessSecurityUserDetails;

/**
 * Listener for CREATED_DATE, CREATED_BY etc
 * 
 * @author jyoti.yadav@globallogic.com
 */
public class EntityListener {
	public static Long adminId = 1L;

	@PrePersist
	public void onPrePersist(Object o) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if (authentication != null) {
			if (String.valueOf(authentication.getPrincipal()).equals(
					"anonymousUser")) {
				((Persistent) o).setCreatedBy(adminId);
				((Persistent) o).setModifiedBy(adminId);
			} else {
				((Persistent) o)
						.setCreatedBy(((WineaccessSecurityUserDetails) authentication
								.getPrincipal()).getUserId());
				((Persistent) o)
				.setModifiedBy(((WineaccessSecurityUserDetails) authentication
						.getPrincipal()).getUserId());
			}
		}
		((Persistent) o).setCreatedDate(new Date());
		((Persistent) o).setModifiedDate(new Date());
		
	}

	@PreUpdate
	public void onPreUpdate(Object o) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if (authentication != null) {
			if (String.valueOf(authentication.getPrincipal()).equals(
					"anonymousUser")) {
				((Persistent) o).setModifiedBy(adminId);
			} else {
				((Persistent) o)
						.setModifiedBy(((WineaccessSecurityUserDetails) authentication
								.getPrincipal()).getUserId());
			}
		}
		((Persistent) o).setModifiedDate(new Date());
	}
}