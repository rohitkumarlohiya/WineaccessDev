package com.wineaccess.data.model.catalog.requisition;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.wineaccess.constants.EnumTypes.POStatus;
import com.wineaccess.constants.EnumTypes.REQType;
import com.wineaccess.data.model.EntityListener;
import com.wineaccess.data.model.catalog.WineryModel;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.distributioncentre.DistributionCentre;
import com.wineaccess.warehouse.WarehouseModel;

@Entity
@Table(name = "REQ_MASTER")
@EntityListeners(EntityListener.class)
@NamedQueries({ @NamedQuery(name = "RequisitionModel.getById", query = "from RequisitionModel where id = :id"),
	@NamedQuery(name = "RequisitionModel.getWarehouseCount", query = "from RequisitionModel where sourceWhAddressId.id = :id")
})
@Indexed
public class RequisitionModel extends Persistent {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Field(name = "id", analyze = Analyze.YES, store = Store.NO)
	private Long id;

	@FieldBridge(impl = POWineryNameFieldBridge.class)
	@Fields( {
		@Field(name = "wineryName", analyze = Analyze.YES, store = Store.NO),
		@Field(name = "wineryNameSort", analyze = Analyze.NO, store = Store.NO)
	})
	@ManyToOne
	@JoinColumn(name = "WINERY_ID")
	private WineryModel winery;

	@Field(name = "submittedDate", analyze = Analyze.NO, store = Store.NO)
	@Column(name = "SUBMITTED_DATE")
	private Date submittedDate;

	@Field(name = "status", analyze = Analyze.YES, store = Store.NO)
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private String status;

	@Field(name = "typeOfPO", analyze = Analyze.YES, store = Store.NO)
	@Enumerated(EnumType.STRING)
	@Column(name = "REQ_TYPE")
	private REQType typeOfREQ;
	
	@Field(name = "winesCount", analyze = Analyze.NO, store = Store.NO)
	@Column(name = "WINES_COUNT")
	private Integer winesCount = new Integer(0);
	
	@Field(name = "bottlesCount", analyze = Analyze.NO, store = Store.NO)
	@Column(name = "QUANTITY_BOTTOLES_COUNT")
	private Integer bottlesCount = new Integer(0);

	@Field(name = "expectedPickupDate", analyze = Analyze.NO, store = Store.NO)
	@Column(name = "EXP_PICKUP_DATE")
	private Date expectedPickupDate;
	
	
	@Field(name = "actualPickupDate", analyze = Analyze.NO, store = Store.NO)
	@Column(name = "ACTUAL_PICKUP_DATE")
	private Date actualPickupDate;

	@Field(name = "expectedArrivalDate", analyze = Analyze.NO, store = Store.NO)
	@Column(name = "EXP_ARRIVAL_DATE")
	private Date expectedArrivalDate;

	@Field(name = "actualArrivalDate", analyze = Analyze.NO, store = Store.NO)
	@Column(name = "ACTUAL_ARRIVAL_DATE")
	private Date actualArrivalDate;

	@Field(name = "expecteShippingDate", analyze = Analyze.NO, store = Store.NO)
	@Column(name = "EXP_SHIPPING_DATE")
	private Date expectedShippingDate;

	@Field(name = "actualShippingDate", analyze = Analyze.NO, store = Store.NO)
	@Column(name = "ACTUAL_SHIPPING_DATE")
	private Date actualShippingDate;

	@Column(name = "NOTES")
	private String notes;
	
	@Column(name = "REVISION")
	private String revision;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST }, mappedBy = "requisition")
	private Set<REQLineItemsModel> lineItems = new HashSet<REQLineItemsModel>();
	
	@ManyToOne
	@JoinColumn(name = "SOURCE_WH_ADDRESS_ID")
	private WarehouseModel sourceWhAddressId;
	
	@ManyToOne
	@JoinColumn(name = "SOURCE_DC_ADDRESS_ID")
	private DistributionCentre sourceDcAddressId;

	@ManyToOne
	@JoinColumn(name = "DESTINATION_DC_ID")
	private DistributionCentre distributionCentreId;
	
	@Column(name = "TOTAL_PRICE")
	private BigDecimal totalPrice;
	
	@Column(name = "ESTIMATED_FREIGHT")
	private BigDecimal estimatedFreight;
	
	@Column(name = "SOURCE_REQUISITION")
	private String sourceRequisition;
	
	@ManyToOne
	@JoinColumn(name = "INBOUND_TRANSPORT_ID")
	private MasterData inboundTransport;
	
	@Column(name = "IS_DELETED")
	private Boolean isDeleted = false;
	
	public MasterData getInboundTransport() {
		return inboundTransport;
	}

	public void setInboundTransport(MasterData inboundTransport) {
		this.inboundTransport = inboundTransport;
	}

	public String getSourceRequisition() {
		return sourceRequisition;
	}

	public void setSourceRequisition(String sourceRequisition) {
		this.sourceRequisition = sourceRequisition;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public WineryModel getWinery() {
		return winery;
	}

	public void setWinery(WineryModel winery) {
		this.winery = winery;
	}

	public Date getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Date issuedDate) {
		this.submittedDate = issuedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public REQType getTypeOfREQ() {
		return typeOfREQ;
	}

	public void setTypeOfREQ(REQType typeOfREQ) {
		this.typeOfREQ = typeOfREQ;
	}

	public Integer getWinesCount() {
		return winesCount;
	}

	public void setWinesCount(Integer winesCount) {
		this.winesCount = winesCount;
	}

	public Integer getBottlesCount() {
		return bottlesCount;
	}

	public void setBottlesCount(Integer bottlesCount) {
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public Set<REQLineItemsModel> getLineItems() {
		return lineItems;
	}

	public void setLineItems(Set<REQLineItemsModel> lineItems) {
		this.lineItems = lineItems;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getEstimatedFreight() {
		return estimatedFreight;
	}

	public void setEstimatedFreight(BigDecimal estimatedFreight) {
		this.estimatedFreight = estimatedFreight;
	}

	public WarehouseModel getSourceWhAddressId() {
		return sourceWhAddressId;
	}

	public void setSourceWhAddressId(WarehouseModel sourceWhAddressId) {
		this.sourceWhAddressId = sourceWhAddressId;
	}

	public DistributionCentre getSourceDcAddressId() {
		return sourceDcAddressId;
	}

	public void setSourceDcAddressId(DistributionCentre sourceDcAddressId) {
		this.sourceDcAddressId = sourceDcAddressId;
	}

	public DistributionCentre getDistributionCentreId() {
		return distributionCentreId;
	}

	public void setDistributionCentreId(DistributionCentre distributionCentreId) {
		this.distributionCentreId = distributionCentreId;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
