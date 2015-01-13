package com.wineaccess.wineryimporter;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonIgnore;


/**
 * @author abhishek.sharma1
 *
 */
@XmlRootElement
@XmlType(name="WIAddressListing")
public class WISearchVO implements Serializable {

	private static final long serialVersionUID = -7743471146009565246L;

	private int count;

	private int offSet;

	private int limit;

	private String keyword;
	
	private int totalRecordCount;
	@XmlTransient
	@JsonIgnore
	private List<WIAddressResultModel> addressDetail;

	

	
	
	public WISearchVO(int count, int offSet, int limit,
			String keyword, List<WIAddressResultModel> addressDetail, int totalRecordCount) {
		super();
		this.count = count;
		this.offSet = offSet;
		this.limit = limit;
		this.keyword = keyword;
		this.addressDetail = addressDetail;
		this.totalRecordCount = totalRecordCount;
	}


	public WISearchVO(){
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



	public List<WIAddressResultModel> getAddressDetail() {
		return addressDetail;
	}


	public void setAddressDetail(List<WIAddressResultModel> addressDetail) {
		this.addressDetail = addressDetail;
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
