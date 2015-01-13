package com.wineaccess.commad.search.masterdatatype;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.wineaccess.security.masterdatatype.MasterDataTypeCustomModel;

/**
 * 
 * @author rohit.lohiya
 * 
 */
@XmlRootElement
public class MasterDataTypeSearchVO implements Serializable {

	private static final long serialVersionUID = -7743471146009565246L;

	private int count;

	private int offSet;

	private int limit;

	private String keyword;
	
	private int totalRecordCount;
	@XmlTransient
	@JsonIgnore
	private List<MasterDataTypeCustomModel> masterDataTypes;

	

	
	
	public MasterDataTypeSearchVO(int count, int offSet, int limit,
			String keyword, List<MasterDataTypeCustomModel> masterDataTypes, int totalRecordCount) {
		super();
		this.count = count;
		this.offSet = offSet;
		this.limit = limit;
		this.keyword = keyword;
		this.masterDataTypes = masterDataTypes;
		this.totalRecordCount = totalRecordCount;
	}


	public MasterDataTypeSearchVO(){
		super();
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

	
	public List<MasterDataTypeCustomModel> getMasterDataTypes() {
		return masterDataTypes;
	}


	public void setMasterDataTypes(List<MasterDataTypeCustomModel> masterDataTypes) {
		this.masterDataTypes = masterDataTypes;
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
