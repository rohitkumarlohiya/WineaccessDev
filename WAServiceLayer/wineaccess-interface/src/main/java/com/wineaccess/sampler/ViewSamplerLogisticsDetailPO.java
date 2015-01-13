package com.wineaccess.sampler;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

@XmlRootElement(name = "viewSamplerLogisticsDetail")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class ViewSamplerLogisticsDetailPO extends BasePO {

	@NotNull(message = "VIEW_SAMPLER_LOGISTICS_DETAIL_ERROR_101")
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message = "VIEW_SAMPLER_LOGISTICS_DETAIL_ERROR_102")
	private String id;

	@Valid
	private Set<Long> productIds = new HashSet<Long>();

	public Set<Long> getProductIds() {
		return productIds;
	}

	public void setProductIds(Set<Long> productIds) {
		this.productIds = productIds;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
