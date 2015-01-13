package com.wineaccess.sampler;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.application.validation.annotation.ValidatingListAnnotation;
import com.wineaccess.command.BasePO;

@XmlRootElement(name="deleteWineFromSampler")
public class DeleteSamplerWinePO extends BasePO {

	@NotEmpty(message="DELETE_SAMPLER_WINE_ERROR_101")
	@Pattern(regexp = RegExConstants.DIGITS_NOT_EMPTY_STRING, message = "DELETE_SAMPLER_WINE_ERROR_102")
	private String samplerId;
	
	@NotNull(message="DELETE_SAMPLER_WINE_ERROR_103")
	@ValidatingListAnnotation(message="DELETE_SAMPLER_WINE_ERROR_103")
	private List<Long> productId = new ArrayList<Long>();
	
	@Pattern(regexp = RegExConstants.BOOLEAN, message = "DELETE_SAMPLER_WINE_ERROR_104")
	private String isForceDelete;
	
	public String getSamplerId() {
		return samplerId;
	}
	public void setSamplerId(String samplerId) {
		this.samplerId = samplerId;
	}
	public List<Long> getProductId() {
		return productId;
	}
	public void setProductId(List<Long> productId) {
		this.productId = productId;
	}
	public String getIsForceDelete() {
		return isForceDelete;
	}
	public void setIsForceDelete(String isForceDelete) {
		this.isForceDelete = isForceDelete;
	} 
	
}
