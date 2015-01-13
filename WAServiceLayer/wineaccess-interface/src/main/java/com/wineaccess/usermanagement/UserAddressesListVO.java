package com.wineaccess.usermanagement;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



/**
 * @author abhishek.sharma1
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="AddressList")

public class UserAddressesListVO implements Serializable { 
	private static final long serialVersionUID = -7060983258610066202L;
	@XmlElementWrapper(name="userAddresses")
	@XmlElement(name="userAddress")
	private List<UserAddressResultModel> addressesList;

	public List<UserAddressResultModel> getAddressesList() {
		return addressesList;
	}

	public void setAddressesList(List<UserAddressResultModel> addressesList) {
		this.addressesList = addressesList;
	}


}
