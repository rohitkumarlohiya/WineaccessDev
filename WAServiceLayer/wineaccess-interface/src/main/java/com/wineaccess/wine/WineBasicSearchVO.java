package com.wineaccess.wine;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@XmlRootElement(name="winesearchResult")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class WineBasicSearchVO extends WineBasicSearchPO {

	private static Log logger = LogFactory.getLog(WineBasicSearchVO.class);
	
	private int totalRecordCount;
	private int count;
	
	@XmlElementWrapper(name="wineList")
	@XmlElement(name="wine")
	private List<WineSearchVO> wineryVO = new ArrayList<WineSearchVO>();

	
	public WineBasicSearchVO() {
		super();
	}

	
	public WineBasicSearchVO(int totalRecordCount, int count,
			List<WineSearchVO> wineryVO, WineBasicSearchPO wineBasicSearchPO) {
		super();
		this.totalRecordCount = totalRecordCount;
		this.count = count;
		this.wineryVO = wineryVO;
		try {
			BeanUtils.copyProperties(this, wineBasicSearchPO);
		} catch (Exception e) {
			logger.error("Error while copying the data of Wine Search VO.");
		} 
	}


	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<WineSearchVO> getWineryVO() {
		return wineryVO;
	}

	public void setWineryVO(List<WineSearchVO> wineryVO) {
		this.wineryVO = wineryVO;
	}
	
}
