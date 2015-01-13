package com.wineaccess.command.search.winery;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import org.springframework.beans.BeanUtils;

import com.google.gson.annotations.SerializedName;

public class WineryAdvanceSearchVO extends WineryAdvanceSearchPO {

	@XmlElement(name="winery")
	@SerializedName(value="winery")
	private List<WineryModelVO> wineryModels;

	private int totalRecordCount;
	private int count;
	private int winerySpecificCount = 0;
	
	
	public WineryAdvanceSearchVO() {
	}

	public WineryAdvanceSearchVO(List<WineryModelVO> wineryModels, WineryAdvanceSearchPO wineryAdvanceSearchPO, int totalRecordCount, int count,  int winerySpecificCount ) {
		this.wineryModels = wineryModels;
		BeanUtils.copyProperties(wineryAdvanceSearchPO, this);
		this.totalRecordCount = totalRecordCount;
		this.count = count;
		this.winerySpecificCount = winerySpecificCount;
	}

	public List<WineryModelVO> getWineryModels() {
		return wineryModels;
	}

	public void setWineryModels(List<WineryModelVO> wineryModels) {
		this.wineryModels = wineryModels;
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

	public int getWinerySpecificCount() {
	    return winerySpecificCount;
	}

	public void setWinerySpecificCount(int winerySpecificCount) {
	    this.winerySpecificCount = winerySpecificCount;
	}
}
