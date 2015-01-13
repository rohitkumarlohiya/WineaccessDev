package com.wineaccess.application.contact;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ImporterContactDetailsList {

	@XmlElementWrapper(name = "importerContactDetailsList")
	@XmlElement(name = "importerContact")
	List<WineryContactVO> importerContactVOList = new ArrayList<WineryContactVO>();

	public List<WineryContactVO> getImporterContactVOList() {
		return importerContactVOList;
	}

	public void setImporterContactVOList(List<WineryContactVO> importerContactVOList) {
		this.importerContactVOList = importerContactVOList;
	}

}
