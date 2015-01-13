package com.wineaccess.winery;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;

import com.wineaccess.command.BasePO;

/**
 * @author gaurav.agarwal1
 * 
 * PO of the view winery and take the winery id as parameter
 */
@XmlRootElement(name="viewWinery")
public class ViewWineryPO extends BasePO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message="COMMON_01")
	@Pattern(regexp="\\d*", message="COMMON_01")
	private String wineryId;

	public String getWineryId() {
		return wineryId;
	}

	public void setWineryId(String wineryId) {
		this.wineryId = wineryId;
	}

}