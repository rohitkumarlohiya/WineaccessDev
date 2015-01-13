package com.wineaccess.sampler;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

/**
 * @author gaurav.agarwal1
 * PO of the add sampler
 */
@XmlRootElement(name = "addSampler")
public class AddSamplerPO extends BasePO implements Serializable {

	private static final long serialVersionUID = 1236569444783654436L;
	
	@NotEmpty(message="ADD_SAMPLER_ERROR_101")
	@Pattern(regexp=RegExConstants.STRING_NOT_EMPTY_STRING, message="ADD_SAMPLER_ERROR_102")
	private String samplerName;
	
	@Valid
	@NotNull(message="ADD_SAMPLER_ERROR_103")
	@Size(min=2, message="ADD_SAMPLER_ERROR_104")
	private List<ProductDetails> products;

	public String getSamplerName() {
		return samplerName;
	}

	public void setSamplerName(String samplerName) {
		this.samplerName = samplerName;
	}

	public List<ProductDetails> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDetails> products) {
		this.products = products;
	}

}
