package com.wineaccess.distributioncentre;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1
 * VO for distribution centre location listing 
 */
@XmlRootElement
@XmlType(name="distributionCentreListing")
public class DistributionCentreListingVO implements Serializable {

	private static final long serialVersionUID = 6098390997605870231L;

	private int count;
	private int offSet;
	private int limit;
	private String keyword;
	private int totalRecordCount;
	private List<ViewDistributionCentreVO> dcWarehouseLocationList;

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

	public List<ViewDistributionCentreVO> getDcWarehouseLocationList() {
		return dcWarehouseLocationList;
	}

	public void setDcWarehouseLocationList(
			List<ViewDistributionCentreVO> dcWarehouseLocationList) {
		this.dcWarehouseLocationList = dcWarehouseLocationList;
	}

}
