package com.wineaccess.service.user.preference;

import java.io.Serializable;

/**
 * @author arpit.vijayvargiya@globallogic.com
 */
public class UserPreferenceVO implements Serializable {
	
	private static final long serialVersionUID = -2447308669831397125L;
	
	private Long id;
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}