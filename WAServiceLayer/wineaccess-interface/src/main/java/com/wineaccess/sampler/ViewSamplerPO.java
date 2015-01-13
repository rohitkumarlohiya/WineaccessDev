package com.wineaccess.sampler;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.command.BasePO;

/**
 * @author gaurav.agarwal1
 * PO of view sampler
 */
@XmlRootElement(name="viewSampler")
public class ViewSamplerPO extends BasePO implements Serializable {

	private static final long serialVersionUID = 2338936250339946563L;
	
	@NotEmpty(message="VIEW_SAMPLER_ERROR_101")
	@Pattern(regexp=RegExConstants.DIGITS_NOT_EMPTY_STRING,message="VIEW_SAMPLER_ERROR_102")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
