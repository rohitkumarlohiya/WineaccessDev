package com.wineaccess.wineOWS;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.wineaccess.application.constants.RegExConstants;
import com.wineaccess.common.JaxbDateSerializer;

/**
 * @author gaurav.agarwal1
 * update PO of wine historical ows data
 */
@XmlRootElement(name="updateWineOws")
public class UpdateWineOwsPO extends WineOwsModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "WINE_OWS_ERROR_101")
	@Pattern(regexp = RegExConstants.MANDATORY_DIGITS, message = "WINE_OWS_ERROR_102")
	private String productId;


	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Override
	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	public Date getCeraCertEndDate() {
		return ceraCertEndDate;
	}
	
	@Override
	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	public Date getCeraCertStartDate() {
		return ceraCertStartDate;
	}
	

}
