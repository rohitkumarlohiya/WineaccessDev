package com.wineaccess.sampler;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

/**
 * @author gaurav.agarwal1
 * PO of the Update Sampler
 */
@XmlRootElement(name="updateSampler")
public class UpdateSamplerPO extends BasePO implements Serializable {

	private static final long serialVersionUID = 1915477004125534160L;
	
	@NotEmpty(message="UPDATE_SAMPLER_ERROR_101")
	@Pattern(regexp=RegExConstants.DIGITS,message="UPDATE_SAMPLER_ERROR_102")
	private String id;
	
	@NotNull(message="UPDATE_SAMPLER_ERROR_103")
	@Pattern(regexp=RegExConstants.MANDATORY_STRING,message="UPDATE_SAMPLER_ERROR_107")
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
