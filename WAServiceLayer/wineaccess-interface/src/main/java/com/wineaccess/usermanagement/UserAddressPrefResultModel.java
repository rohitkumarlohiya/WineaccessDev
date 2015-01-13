package com.wineaccess.usermanagement;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.wineaccess.common.JaxbDateHourSerializer;


/**
 * @author abhishek.sharma1
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class UserAddressPrefResultModel implements Serializable { 
	private static final long serialVersionUID = -7060983258610066202L;
	private String addrPrefId;
	private Map<String,String> prefCarrier;
	private Map<String,String> prefShippingMethod;
	private Map<String,String> prefPackageType;
	private Boolean combineShipmentWhenAppropriate;
	@XmlJavaTypeAdapter(JaxbDateHourSerializer.class)
	private Date deliveryStarting;
	@XmlJavaTypeAdapter(JaxbDateHourSerializer.class)
	private Date deliveryEnding;
	private List<DesiredDeliveryDaysModel> desiredDeliveryDays;


	public String getAddrPrefId() {
		return addrPrefId;
	}
	public void setAddrPrefId(String addrPrefId) {
		this.addrPrefId = addrPrefId;
	}


	public Map<String, String> getPrefCarrier() {
		return prefCarrier;
	}
	public void setPrefCarrier(Map<String, String> prefCarrier) {
		this.prefCarrier = prefCarrier;
	}

	public Map<String, String> getPrefShippingMethod() {
		return prefShippingMethod;
	}
	public void setPrefShippingMethod(Map<String, String> prefShippingMethod) {
		this.prefShippingMethod = prefShippingMethod;
	}
	public Map<String, String> getPrefPackageType() {
		return prefPackageType;
	}
	public void setPrefPackageType(Map<String, String> prefPackageType) {
		this.prefPackageType = prefPackageType;
	}
	public Boolean getCombineShipmentWhenAppropriate() {
		return combineShipmentWhenAppropriate;
	}
	public void setCombineShipmentWhenAppropriate(
			Boolean combineShipmentWhenAppropriate) {
		this.combineShipmentWhenAppropriate = combineShipmentWhenAppropriate;
	}
	public Date getDeliveryStarting() {
		return deliveryStarting;
	}
	public void setDeliveryStarting(Date deliveryStarting) {
		this.deliveryStarting = deliveryStarting;
	}
	public Date getDeliveryEnding() {
		return deliveryEnding;
	}
	public void setDeliveryEnding(Date deliveryEnding) {
		this.deliveryEnding = deliveryEnding;
	}
	public List<DesiredDeliveryDaysModel> getDesiredDeliveryDays() {
		return desiredDeliveryDays;
	}
	public void setDesiredDeliveryDays(
			List<DesiredDeliveryDaysModel> desiredDeliveryDays) {
		this.desiredDeliveryDays = desiredDeliveryDays;
	}




}
