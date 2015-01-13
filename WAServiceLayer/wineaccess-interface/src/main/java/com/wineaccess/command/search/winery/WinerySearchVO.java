package com.wineaccess.command.search.winery;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.springframework.beans.BeanUtils;

@XmlRootElement
@XmlType(name="wineries")
public class WinerySearchVO extends WinerySearchPO {

	private int totalRecordCount;
	private int count;
	
	@XmlElement(name = "winery")
	private List<WineryModelVO> wineryModel;

	public WinerySearchVO() {
	}

	public WinerySearchVO(List<WineryModelVO> wineryModel, WinerySearchPO winerySearchPO, int totalRecordCount, int count) {
		this.wineryModel = wineryModel;
		this.totalRecordCount = totalRecordCount;
		this.count = count;
		BeanUtils.copyProperties(winerySearchPO, this);
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

	public List<WineryModelVO> getWineryModel() {
		return wineryModel;
	}

	public void setWineryModel(List<WineryModelVO> wineryModel) {
		this.wineryModel = wineryModel;
	}
}
