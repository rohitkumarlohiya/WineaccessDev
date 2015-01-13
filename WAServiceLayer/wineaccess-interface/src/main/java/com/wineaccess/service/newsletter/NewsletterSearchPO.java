package com.wineaccess.service.newsletter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.common.SearchPO;

@XmlRootElement(name="NewsletterSearchRequest")
@XmlAccessorType(XmlAccessType.FIELD)
/*@XmlType(propOrder={"offSet","limit","keyword","sortBy","sortOrder"})*/
public class NewsletterSearchPO extends SearchPO {

	private static final long serialVersionUID = 6171217756107484451L;

	public NewsletterSearchPO() {
		super();
		
		if (getKeyword() == null) {
			setKeyword("");
		}

	}
}
