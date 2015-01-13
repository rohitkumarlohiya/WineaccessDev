package com.wineaccess.sampler;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.common.NonLuceneAbstractSearchPO;

/**
 * @author arpit.vijayvargiya
 * PO for list products in a sampler
 */
@XmlRootElement(name="listSamplerProducts")
public class ListSamplerProductPO extends NonLuceneAbstractSearchPO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	@Pattern(regexp="id|productName|srpPrice|quantity",message="LIST_SAMPLER_PRODUCT_ERROR_102")
	public String getSortBy() {
		return sortBy;
	}
	
	@NotNull(message="LIST_SAMPLER_PRODUCT_ERROR_103")
	@Pattern(regexp=RegExConstants.DIGITS_NOT_EMPTY_STRING, message="LIST_SAMPLER_PRODUCT_ERROR_104")
	private String samplerId;

	public String getSamplerId() {
		return samplerId;
	}

	public void setSamplerId(String samplerId) {
		this.samplerId = samplerId;
	}
	
}
