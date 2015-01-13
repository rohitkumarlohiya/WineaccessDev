package com.wineaccess.winerylicensedetail;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotEmpty;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

@XmlRootElement(name = "viewWineryLicenseDetail")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class WineryLicenseDetailViewPO extends BasePO {

	@NotEmpty(message = "VIEW_WINERY_LICENSE_DETAIL_101")
	@Pattern(regexp = RegExConstants.DIGITS, message = "VIEW_WINERY_LICENSE_DETAIL_102")
	private String wineryId;

	public String getWineryId() {
		return wineryId;
	}

	public void setWineryId(String wineryId) {
		this.wineryId = wineryId;
	}

}
