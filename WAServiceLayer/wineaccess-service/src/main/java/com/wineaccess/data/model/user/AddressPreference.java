package com.wineaccess.data.model.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wineaccess.data.model.EntityListener;
import com.wineaccess.data.model.common.MasterData;

/**
 * @author jyoti.yadav@globallogic.com
 */
@Entity
@Table(name = "ADDRESS_PREFERENCE")
@EntityListeners(EntityListener.class)
public class AddressPreference extends Persistent {

	private static final long serialVersionUID = -8777182781305794009L;

	@Id
	@Column(name = "ID", columnDefinition = "BIGINT(20) UNSIGNED")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@ManyToOne
	@JoinColumn(name = "PREFERED_CARRIER", columnDefinition = "BIGINT(20) UNSIGNED")
	private MasterData prefferedCarrier;
	
	
	@ManyToOne
	@JoinColumn(name = "PREFERRED_SHIPPING_METHOD", columnDefinition = "BIGINT(20) UNSIGNED")
	private MasterData prefferedShippingMethod;
	
	
	@ManyToOne
	@JoinColumn(name = "PREFERED_PACKAGE_TYPE", columnDefinition = "BIGINT(20) UNSIGNED")
	private MasterData prefferedPackageType;
	
	@Column(name = "COMBINE_SHIPPMENTS_WHEN_APPROPRIATE", columnDefinition = "TINYINT(1) NULL")
	private Boolean combineShipmentWhenAppropriate;
	
	@Column(name = "DELIVERY_STARTING", columnDefinition = "TIME NULL")
	private Date deliveryStartTime;
	
	@Column(name = "DELIVERY_ENDING", columnDefinition = "TIME NULL")
	private Date deliveryEndTime;
	
	@Column(name = "DESIRED_DELIVERY_DAYS", columnDefinition = "VARCHAR(255) NULL")
	private String desiredDeliveryDays;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	
	
	public Boolean isCombineShipmentWhenAppropriate() {
		return combineShipmentWhenAppropriate;
	}


	public MasterData getPrefferedCarrier() {
		return prefferedCarrier;
	}

	public void setPrefferedCarrier(MasterData prefferedCarrier) {
		this.prefferedCarrier = prefferedCarrier;
	}

	public MasterData getPrefferedShippingMethod() {
		return prefferedShippingMethod;
	}

	public void setPrefferedShippingMethod(MasterData prefferedShippingMethod) {
		this.prefferedShippingMethod = prefferedShippingMethod;
	}

	public MasterData getPrefferedPackageType() {
		return prefferedPackageType;
	}

	public void setPrefferedPackageType(MasterData prefferedPackageType) {
		this.prefferedPackageType = prefferedPackageType;
	}

	public void setCombineShipmentWhenAppropriate(Boolean combineShipmentWhenAppropriate) {
		this.combineShipmentWhenAppropriate = combineShipmentWhenAppropriate;
	}

	public Date getDeliveryStartTime() {
		return deliveryStartTime;
	}

	public void setDeliveryStartTime(Date deliveryStartTime) {
		this.deliveryStartTime = deliveryStartTime;
	}

	public Date getDeliveryEndTime() {
		return deliveryEndTime;
	}

	public void setDeliveryEndTime(Date deliveryEndTime) {
		this.deliveryEndTime = deliveryEndTime;
	}

	public String getDesiredDeliveryDays() {
		return desiredDeliveryDays;
	}

	public void setDesiredDeliveryDays(String desiredDeliveryDays) {
		this.desiredDeliveryDays = desiredDeliveryDays;
	}

	

	
}
