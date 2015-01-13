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
public class WineAdvanceSearchVO extends WineAdvanceSearchPO {

	private static Log logger = LogFactory.getLog(WineAdvanceSearchVO.class);

	private int totalRecordCount;
	private int count;
	
	private int winerySpecificCount;

	@XmlElementWrapper(name="wineList")
	@XmlElement(name="wine")
	private List<WineSearchVO> wineryVO = new ArrayList<WineSearchVO>();

	public WineAdvanceSearchVO() {
		super();
	}

	public WineAdvanceSearchVO(int totalRecordCount, int count, int winerySpecificCount, WineAdvanceSearchPO wineAdvanceSearchPO,
			List<WineSearchVO> wineryVO) {
		super();
		this.totalRecordCount = totalRecordCount;
		this.count = count;
		this.wineryVO = wineryVO;
		this.winerySpecificCount = winerySpecificCount;
		try{
			BeanUtils.copyProperties(this, wineAdvanceSearchPO);	
		} catch(Exception e){
			logger.error("Error while copying the data to PO.");
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

	public int getWinerySpecificCount() {
	    return winerySpecificCount;
	}

	public void setWinerySpecificCount(int winerySpecificCount) {
	    this.winerySpecificCount = winerySpecificCount;
	}
}

