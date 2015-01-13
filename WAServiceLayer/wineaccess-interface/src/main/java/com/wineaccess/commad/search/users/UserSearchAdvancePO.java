package com.wineaccess.commad.search.users;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserSearchAdvancePO implements Serializable {

	private static final long serialVersionUID = -3867634744003243136L;
	
	
	
	private List<SearchCriteriaPO> searchCriterias;
	
	private RangeSearchCriteriaPO registrationRangeCriteria;
	
	private RangeSearchCriteriaPO revenueRangeCriteria;

	private List<String> roleIds;
	
	public UserSearchAdvancePO() {
	}

	public UserSearchAdvancePO(List<SearchCriteriaPO> searchCriterias) {
		this.searchCriterias = searchCriterias;
	}

	public List<SearchCriteriaPO> getSearchCriterias() {
		return searchCriterias;
	}

	public void setSearchCriterias(List<SearchCriteriaPO> searchCriterias) {
		this.searchCriterias = searchCriterias;
	}


	public RangeSearchCriteriaPO getRegistrationRangeCriteria() {
		return registrationRangeCriteria;
	}

	public void setRegistrationRangeCriteria(
			RangeSearchCriteriaPO registrationRangeCriteria) {
		this.registrationRangeCriteria = registrationRangeCriteria;
	}

	public RangeSearchCriteriaPO getRevenueRangeCriteria() {
		return revenueRangeCriteria;
	}

	public void setRevenueRangeCriteria(RangeSearchCriteriaPO revenueRangeCriteria) {
		this.revenueRangeCriteria = revenueRangeCriteria;
	}

	public List<String> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<String> roleIds) {
		this.roleIds = roleIds;
	}

	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
