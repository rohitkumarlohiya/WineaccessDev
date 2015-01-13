package com.wineaccess.application.contact;

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
@XmlType(name="WIContactListing")
public class ContactSearchVO implements Serializable {

	private static final long serialVersionUID = -7743471146009565246L;

	private int count;

	private int offSet;

	private int limit;

	private String keyword;
	
	private int totalRecordCount;
	@XmlTransient
	@JsonIgnore
	private List<WineryImporterContactModel> contactDetail;

	

	
	
	public ContactSearchVO(int count, int offSet, int limit,
			String keyword, List<WineryImporterContactModel> contactDetail, int totalRecordCount) {
		super();
		this.count = count;
		this.offSet = offSet;
		this.limit = limit;
		this.keyword = keyword;
		this.contactDetail = contactDetail;
		this.totalRecordCount = totalRecordCount;
	}


	public ContactSearchVO(){
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



	public List<WineryImporterContactModel> getContactDetail() {
		return contactDetail;
	}


	public void setContactDetail(List<WineryImporterContactModel> contactDetail) {
		this.contactDetail = contactDetail;
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
