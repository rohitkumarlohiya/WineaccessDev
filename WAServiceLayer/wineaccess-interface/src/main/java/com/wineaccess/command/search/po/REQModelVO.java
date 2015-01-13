package com.wineaccess.command.search.po;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.wineaccess.common.JsonDateSerializer;
import com.wineaccess.constants.EnumTypes;
import com.wineaccess.data.model.catalog.requisition.RequisitionModel;
import com.wineaccess.wineryimporter.Address;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class REQModelVO implements Serializable {

	private long id;
	
	private String typeOfPO;

	@JsonSerialize(using=JsonDateSerializer.class)
	private Date submittedDate;

	private String status;
	
	private String wineryName;
	
	private Address wineLocation;
	
	private String dc;
	
	private String WS;
	
	private int winesCount;

	private int bottlesCount;
	
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date expectedPickupDate;
	
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date actualPickupDate;
	
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date expectedArrivalDate;
	
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date actualArrivalDate;
	
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date expectedShippingDate;
	
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date actualShippingDate;
	
	public REQModelVO(){
	}
	
	public REQModelVO(RequisitionModel reqModel){
		this.id = reqModel.getId();
		this.typeOfPO = reqModel.getTypeOfREQ().name();
		this.submittedDate = reqModel.getSubmittedDate();
		this.status = reqModel.getStatus();
		this.wineryName = reqModel.getWinery().getWineryName();
		
		if(reqModel.getSourceWhAddressId() != null){
			Address address = new Address(reqModel.getSourceWhAddressId().getAddressLine1(), reqModel.getSourceWhAddressId().getAddressLine2(),
					reqModel.getSourceWhAddressId().getCityId(), reqModel.getSourceWhAddressId().getStateId(), null, null);
			this.wineLocation = address;
		}else{
			Address address = new Address(reqModel.getSourceDcAddressId().getAddressLine1(), reqModel.getSourceDcAddressId().getAddressLine2(),
					reqModel.getSourceDcAddressId().getCityId(), reqModel.getSourceDcAddressId().getStateId(), null, null);
			this.wineLocation = address;
		}
		
		this.dc = reqModel.getDistributionCentreId().getStateId().getStateCode();
		
		if(reqModel.getTypeOfREQ().equals(EnumTypes.REQType.PO) || reqModel.getTypeOfREQ().equals(EnumTypes.REQType.WT)){
			if(reqModel.getInboundTransport() != null){
				this.WS = "Y";
			}else{
				this.WS = "N";
			}
		}else{
			this.WS = "Y";
		}
		this.winesCount = reqModel.getWinesCount();
		this.bottlesCount = reqModel.getBottlesCount();
		this.expectedPickupDate = reqModel.getExpectedPickupDate();
		this.actualPickupDate = reqModel.getActualPickupDate();
		this.expectedArrivalDate = reqModel.getExpectedArrivalDate();
		this.actualArrivalDate = reqModel.getActualArrivalDate();
		this.expectedShippingDate = reqModel.getExpectedShippingDate();
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTypeOfPO() {
		return typeOfPO;
	}

	public void setTypeOfPO(String typeOfPO) {
		this.typeOfPO = typeOfPO;
	}

	public Date getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWineryName() {
		return wineryName;
	}

	public void setWineryName(String wineryName) {
		this.wineryName = wineryName;
	}

	public Address getWineLocation() {
		return wineLocation;
	}

	public void setWineLocation(Address wineLocation) {
		this.wineLocation = wineLocation;
	}

	public String getDc() {
		return dc;
	}

	public void setDc(String dc) {
		this.dc = dc;
	}

	public String getWS() {
		return WS;
	}

	public void setWS(String wS) {
		WS = wS;
	}

	public int getWinesCount() {
		return winesCount;
	}

	public void setWinesCount(int winesCount) {
		this.winesCount = winesCount;
	}

	public int getBottlesCount() {
		return bottlesCount;
	}

	public void setBottlesCount(int bottlesCount) {
		this.bottlesCount = bottlesCount;
	}

	public Date getExpectedPickupDate() {
		return expectedPickupDate;
	}

	public void setExpectedPickupDate(Date expectedPickupDate) {
		this.expectedPickupDate = expectedPickupDate;
	}

	public Date getActualPickupDate() {
		return actualPickupDate;
	}

	public void setActualPickupDate(Date actualPickupDate) {
		this.actualPickupDate = actualPickupDate;
	}

	public Date getExpectedArrivalDate() {
		return expectedArrivalDate;
	}

	public void setExpectedArrivalDate(Date expectedArrivalDate) {
		this.expectedArrivalDate = expectedArrivalDate;
	}

	public Date getActualArrivalDate() {
		return actualArrivalDate;
	}

	public void setActualArrivalDate(Date actualArrivalDate) {
		this.actualArrivalDate = actualArrivalDate;
	}

	public Date getExpectedShippingDate() {
		return expectedShippingDate;
	}

	public void setExpectedShippingDate(Date expectedShippingDate) {
		this.expectedShippingDate = expectedShippingDate;
	}

	public Date getActualShippingDate() {
		return actualShippingDate;
	}

	public void setActualShippingDate(Date actualShippingDate) {
		this.actualShippingDate = actualShippingDate;
	}
}
