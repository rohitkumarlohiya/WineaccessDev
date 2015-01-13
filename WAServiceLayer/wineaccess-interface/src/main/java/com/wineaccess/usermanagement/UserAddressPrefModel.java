package com.wineaccess.usermanagement;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.wineaccess.application.validation.annotation.MasterDataAnnotation;
import com.wineaccess.common.JaxbDateHourSerializer;
import com.wineaccess.constants.EnumTypes.MasterDataErrorCode;
import com.wineaccess.constants.EnumTypes.MasterDataTypeEnum;



/**
 * @author abhishek.sharma1
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class UserAddressPrefModel implements Serializable { 
	private static final long serialVersionUID = -7060983258610066202L;
	private String addrPrefId;
	private  String userAddressPrefId;
	
	@MasterDataAnnotation(masterDataTypeName=MasterDataTypeEnum.Constants.SHIPPING_CARRIER, message=MasterDataErrorCode.Constants.SHIPPING_CARRIER)
	private Long prefCarrierId;
	
	@MasterDataAnnotation(masterDataTypeName=MasterDataTypeEnum.Constants.SHIPPING_METHOD, message=MasterDataErrorCode.Constants.SHIPPING_METHOD)
	private Long prefShippingMethodId;
	
	@MasterDataAnnotation(masterDataTypeName=MasterDataTypeEnum.Constants.PACKAGE_TYPE, message=MasterDataErrorCode.Constants.PACKAGE_TYPE)
	private Long prefPackageTypeId;
	private Boolean combineShipmentWhenAppropriate;
	@XmlJavaTypeAdapter(JaxbDateHourSerializer.class)
	private Date deliveryStarting;
	@XmlJavaTypeAdapter(JaxbDateHourSerializer.class)
	private Date deliveryEnding;
	private String[] desiredDeliveryDays;


	public String getAddrPrefId() {
		return addrPrefId;
	}
	public void setAddrPrefId(String addrPrefId) {
		this.addrPrefId = addrPrefId;
	}
	public String getUserAddressPrefId() {
		return userAddressPrefId;
	}
	public void setUserAddressPrefId(String userAddressPrefId) {
		this.userAddressPrefId = userAddressPrefId;
	}

	public Long getPrefCarrierId() {
		return prefCarrierId;
	}
	public void setPrefCarrierId(Long prefCarrierId) {
		this.prefCarrierId = prefCarrierId;
	}
	public Long getPrefShippingMethodId() {
		return prefShippingMethodId;
	}
	public void setPrefShippingMethodId(Long prefShippingMethodId) {
		this.prefShippingMethodId = prefShippingMethodId;
	}
	public Long getPrefPackageTypeId() {
		return prefPackageTypeId;
	}
	public void setPrefPackageTypeId(Long prefPackageTypeId) {
		this.prefPackageTypeId = prefPackageTypeId;
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
	public String[] getDesiredDeliveryDays() {
		return desiredDeliveryDays;
	}
	public void setDesiredDeliveryDays(String[] desiredDeliveryDays) {
		this.desiredDeliveryDays = desiredDeliveryDays;
	}



}
