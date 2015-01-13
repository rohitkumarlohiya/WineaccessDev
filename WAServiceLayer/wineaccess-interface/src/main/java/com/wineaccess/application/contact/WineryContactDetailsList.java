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
public class WineryContactDetailsList {
	
	@XmlElementWrapper(name = "wineryContactDetailsList")
	@XmlElement(name = "wineryContact")
	List<WineryContactVO> wineryContactVOList = new ArrayList<WineryContactVO>();

	public List<WineryContactVO> getWineryContactVOList() {
		return wineryContactVOList;
	}

	public void setWineryContactVOList(List<WineryContactVO> wineryContactVOList) {
		this.wineryContactVOList = wineryContactVOList;
	}
	
	
}
