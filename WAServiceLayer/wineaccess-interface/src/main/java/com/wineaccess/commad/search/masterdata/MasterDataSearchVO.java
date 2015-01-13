package com.wineaccess.commad.search.masterdata;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.security.masterdata.MasterDataCustModel;

/**
 * 
 * @author rohit.lohiya
 * 
 */
@XmlRootElement
public class MasterDataSearchVO implements Serializable {

	private static final long serialVersionUID = -7743471146009565246L;

	private int count;

	private int offSet;

	private int limit;

	private String keyword;
	
	private int totalRecordCount;
	
	

	List<MasterDataCustModel> masterDatas;

	public MasterDataSearchVO() {
		super();
	}

	public MasterDataSearchVO(int count, int offSet, int limit, String keyword,
			List<MasterDataCustModel> masterDatas,int totalRecordCount) {
		super();
		this.count = count;
		this.offSet = offSet;
		this.limit = limit;
		this.keyword = keyword;
		this.masterDatas = masterDatas;
		this.totalRecordCount=totalRecordCount;
		//this.masterDataType=masterDataType;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getOffSet() {
		return offSet;
	}

	public void setOffSet(int offSet) {
		this.offSet = offSet;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	

	public List<MasterDataCustModel> getMasterDatas() {
		return masterDatas;
	}

	public void setMasterDatas(List<MasterDataCustModel> masterDatas) {
		this.masterDatas = masterDatas;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	
	
	
	
	
}
