package com.wineaccess.warehouse;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.common.NonLuceneAbstractSearchPO;

/**
 * @author gaurav.agarwal1
 * PO for list warehouse
 */
@XmlRootElement(name="listWarehouse")
public class ListWarehousePO extends NonLuceneAbstractSearchPO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Override
	@Pattern(regexp="^$|id|name|wineryCount",message="LIST_WAREHOUSE_ERROR_104")
	public String getSortBy() {
		return sortBy;
	}

}
