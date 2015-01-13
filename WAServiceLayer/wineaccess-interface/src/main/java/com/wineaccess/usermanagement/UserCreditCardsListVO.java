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
@XmlType(name="CreditCardsList")

public class UserCreditCardsListVO implements Serializable { 
	private static final long serialVersionUID = -7060983258610066202L;
	@XmlElementWrapper(name="creditCards")
	@XmlElement(name="creditCard")
	private List<CreditCardResultModel> creditCardsList;

	public List<CreditCardResultModel> getCreditCardsList() {
		return creditCardsList;
	}

	public void setCreditCardsList(List<CreditCardResultModel> creditCardsList) {
		this.creditCardsList = creditCardsList;
	}
	

}
