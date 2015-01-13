package com.wineaccess.user.activity.log;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.common.AbstractSearchPO;

@XmlRootElement(name="activityLog")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class ActivityLogSearchPO extends AbstractSearchPO {
	
	public ActivityLogSearchPO() {
		//setOffSet(0);
	}

	@Pattern(regexp = RegExConstants.SORT_FIELDS_FOR_ACTIVITY_LOG_HISTORY, message = "SESSIONSUMMARY103")
	public String getSortBy() {
		if(super.getSortBy().equals("id"))
			return "userid";
		return super.getSortBy();
	}
}
