package com.wineaccess.wine;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.application.validation.annotation.ValidatingListAnnotation;
import com.wineaccess.command.BasePO;

/**
 * @author arpit.vijayvargiya@globallogic.com
 * 
 */
@XmlRootElement(name="wines")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class ChangeWineStatusPO extends BasePO implements Serializable {

	@NotNull(message="WINE_ERROR_151")
	@ValidatingListAnnotation(message="WINE_ERROR_151")
	@XmlElementWrapper(name="productIds")
	@XmlElement(name="productId")
	private List<Long> productId;
	
	@Pattern(regexp = RegExConstants.WINE_BULK_OPERATION, message = "WINE_ERROR_138")
	private String bulkOperation;
	
	@Pattern(regexp = RegExConstants.BOOLEAN, message = "WINE_ERROR_142")
	private String forceUpdate;
	
	public String getForceUpdate() {
		return forceUpdate;
	}
	public void setForceUpdate(String forceUpdate) {
		this.forceUpdate = forceUpdate;
	}
	public String getBulkOperation() {
		return bulkOperation;
	}
	public void setBulkOperation(String bulkOperation) {
		this.bulkOperation = bulkOperation;
	}
	public List<Long> getProductId() {
		return productId;
	}
	public void setProductId(List<Long> productId) {
		this.productId = productId;
	}
	
	
}