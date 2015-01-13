package com.wineaccess.wineryimporter;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.command.BasePO;

@XmlRootElement(name="deleteWIAddress")
@XmlAccessorType(XmlAccessType.FIELD)

public class WineryImporterDeleteAddressPO extends BasePO implements Serializable { 
	private static final long serialVersionUID = -7060983258610066202L;
	@XmlElementWrapper(name="deleteAddressList")
	@XmlElement(name="addressId")
	private List<Long> deleteAddressList;


	private Boolean isforceDelete;

	public List<Long> getDeleteAddressList() {
		return deleteAddressList;
	}
	public void setDeleteAddressList(List<Long> deleteAddressList) {
		this.deleteAddressList = deleteAddressList;
	}

	public Boolean getIsforceDelete() {
		return isforceDelete;
	}
	public void setIsforceDelete(Boolean isforceDelete) {
		this.isforceDelete = isforceDelete;
	}

}
