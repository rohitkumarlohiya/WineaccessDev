package com.wineaccess.data.model.catalog;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.NumericField;
import org.hibernate.search.annotations.Store;

import com.wineaccess.data.model.EntityListener;
import com.wineaccess.data.model.catalog.wine.WineModel;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.data.model.user.StateModel;
import com.wineaccess.warehouse.WarehouseModel;

@Entity
@Table(name = "WINERY")
@EntityListeners(EntityListener.class)
@NamedQueries({
		@NamedQuery(name = "WineryModel.getAll", query = "select id from WineryModel"),
		@NamedQuery(name = "WineryModel.getByWineryCode", query = "from WineryModel where wineryCode = :wineryCode"),
		@NamedQuery(name = "WineryModel.getByWineryName", query = "from WineryModel where wineryName = :wineryName"),
		@NamedQuery(name = "WineryModel.getByImporterId", query = "from WineyImpoterModel where importerId = :importerId"),
		@NamedQuery(name = "WineryModel.getImporters", query = "from WineyImpoterModel where wineryId = :wineryId and isDeleted = false"),
		@NamedQuery(name = "WineryModel.getById", query = "from WineryModel where id = :id and isDeleted = false"),
		@NamedQuery(name = "WineryModel.getCountOfWarehouses", query = "from WineryModel where warehouseId.id = :id")

})
@Indexed
public class WineryModel extends Persistent {

	private static final long serialVersionUID = 5550064054520579887L;

	@Id
	@Column(name = "ID")
	@Field(name = "id", analyze = Analyze.YES, store = Store.NO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Fields( {
		@Field(name = "wineryName", analyze = Analyze.YES, store = Store.NO),
		@Field(name = "wineryNameSort", analyze = Analyze.NO, store = Store.NO)
	})
	@Column(name = "WINERY_NAME")
	private String wineryName;

	@Field(name = "wineryCode", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "WINERY_CODE")
	private String wineryCode;

	@ManyToOne
	@JoinColumn(name = "SOURCING_STATUS")
	private MasterData sourcingStatus;

	@IndexedEmbedded
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "WA_CONTACT")
	private MasterData waContact;

	@Column(name = "CA_LICENSE_TYPE")
	private Long caLicenseTYpe;

	@Column(name = "FULL_FILLER_WINERY_CODE")
	private String fullFillerWinaryCode;

	@Column(name = "USER_COMPLIANCE")
	private Boolean userCompliance;

	@Column(name = "COMPLIANCE_ACC_NAME")
	private String complianceAccName;

	@Column(name = "IOA_RECEIVED_DATE")
	private Date iopReceivedDate;

	@Column(name = "NOTES")
	private String notes;

	@Column(name = "LICENSE_FULL_FILLMENT_PARTNER_ID")
	private Long licenseFullFillmentPartenerId;

	@Field(name = "isEnabled", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "IS_ENABLED")
	private Boolean isEnabled;

	@Column(name = "WORKFLOW_STATUS")
	private String workflowStatus;

	@Field(name = "isDeleted", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "IS_DELETED")
	private Boolean isDeleted;

	@IndexedEmbedded
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name = "STATE_ID")
	private StateModel region;

	@Column(name = "ACCOUNTING_CUSTOMER_NUMBER")
	private String accountingCustomerNumber;

	@Field(name = "wineryUrl", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "WINERY_URL")
	private String wineryUrl;
	
	@FieldBridge(impl = ActiveImporterFieldBridgeById.class)
	@Fields( {
		@Field(name = "activeImporterById", analyze = Analyze.YES, store = Store.NO)
	})
	@ManyToOne
	@JoinColumn(name = "ACTIVE_IMPORTER_ID")
	private ImporterModel activeImporterId;
	
	@IndexedEmbedded(depth = 1)
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, mappedBy = "wineryId")
	private Set<WineModel> wineItems = new HashSet<WineModel>();
	
	@FieldBridge(impl = ActiveImporterFieldBridge.class)
	@Fields( {
		@Field(name = "activeImporterId", analyze = Analyze.YES, store = Store.NO),
		@Field(name = "activeImporterIdSort", analyze = Analyze.NO, store = Store.NO),
		@Field(name = "activeImporterRegion", analyze = Analyze.NO, store = Store.NO)
	})
	@Transient
	public Object getActiveImporterIndex() {
		if (getActiveImporterId() == null) {
			return "";
		} else {
			return activeImporterId;
		}
	}
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "WINERY_IMPORTER", joinColumns = { @JoinColumn(name = "WINERY_ID", insertable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "IMPORTER_ID", insertable = false, updatable = false) })
	private Set<ImporterModel> importers = new HashSet<ImporterModel>(0);

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "FREIGHT_REGION")
	private MasterData freightRegion;

	@Field(name = "wineCount", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "WINE_COUNT")
	@NumericField
	private Integer wineCount = 0;

	@Field(name = "activeWineCount", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "ACTIVE_WINE_COUNT")
	@NumericField
	private Integer activeWineCount = 0;

	@Field(name = "revenue", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "Revenue")
	@NumericField
	private Double revenue = 0.0;
	
	@Field(name = "revenue1", analyze = Analyze.YES, store = Store.NO)
	@Transient
	public String getRevenue1() {
		return String.valueOf(revenue);
	}
	
	/*@Field(name = "revenueSort", analyze = Analyze.NO, store = Store.NO)
	@Column(name = "Revenue", insertable = false, updatable = false)
	@NumericField
	private Double revenueSort;*/
	
	
	@Field(name = "lastOfferDate", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "LAST_OFFER_DATE")
	private Date lastOfferDate;

	@Column(name = "OPTION_SELECTED_ALT_STATES")
	private Integer optionSelectedAltstates;

	@Column(name = "SELL_IN_ALT_STATES")
	private Boolean sellInAltStates = Boolean.FALSE;

	@Column(name = "SELL_IN_MAIN_STATES")
	private Boolean sellInMainStates = Boolean.FALSE;

	@OneToOne
	@JoinColumn(name= "WAREHOUSE_ID")
	private WarehouseModel warehouseId;
	
	public String getAccountingCustomerNumber() {
		return accountingCustomerNumber;
	}

	public Set<ImporterModel> getImporters() {
		return importers;
	}

	public void setImporters(Set<ImporterModel> importers) {
		this.importers = importers;
	}

	public void setAccountingCustomerNumber(String accountingCustomerNumber) {
		this.accountingCustomerNumber = accountingCustomerNumber;
	}

	public StateModel getRegion() {
		return region;
	}

	public void setRegion(StateModel region) {
		this.region = region;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getWorkflowStatus() {
		return workflowStatus;
	}

	public void setWorkflowStatus(String workflowStatus) {
		this.workflowStatus = workflowStatus;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWineryName() {
		return wineryName;
	}

	public void setWineryName(String wineryName) {
		this.wineryName = wineryName;
	}

	public String getWineryCode() {
		return wineryCode;
	}

	public void setWineryCode(String wineryCode) {
		this.wineryCode = wineryCode;
	}

	public ImporterModel getActiveImporterId() {
		return activeImporterId;
	}

	public void setActiveImporterId(ImporterModel activeImporterId) {
		this.activeImporterId = activeImporterId;
	}

	public MasterData getSourcingStatus() {
		return sourcingStatus;
	}

	public void setSourcingStatus(MasterData sourcingStatus) {
		this.sourcingStatus = sourcingStatus;
	}

	public Long getCaLicenseTYpe() {
		return caLicenseTYpe;
	}

	public void setCaLicenseTYpe(Long caLicenseTYpe) {
		this.caLicenseTYpe = caLicenseTYpe;
	}

	public String getFullFillerWinaryCode() {
		return fullFillerWinaryCode;
	}

	public void setFullFillerWinaryCode(String fullFillerWinaryCode) {
		this.fullFillerWinaryCode = fullFillerWinaryCode;
	}

	public Boolean getUserCompliance() {
		return userCompliance;
	}

	public void setUserCompliance(Boolean userCompliance) {
		this.userCompliance = userCompliance;
	}

	public String getComplianceAccName() {
		return complianceAccName;
	}

	public void setComplianceAccName(String complianceAccName) {
		this.complianceAccName = complianceAccName;
	}

	public Date getIopReceivedDate() {
		return iopReceivedDate;
	}

	public void setIopReceivedDate(Date iopReceivedDate) {
		this.iopReceivedDate = iopReceivedDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Long getLicenseFullFillmentPartenerId() {
		return licenseFullFillmentPartenerId;
	}

	public void setLicenseFullFillmentPartenerId(
			Long licenseFullFillmentPartenerId) {
		this.licenseFullFillmentPartenerId = licenseFullFillmentPartenerId;
	}

	public String getWineryUrl() {
		return wineryUrl;
	}

	public void setWineryUrl(String wineryUrl) {
		this.wineryUrl = wineryUrl;
	}

	public MasterData getWaContact() {
		return waContact;
	}

	public void setWaContact(MasterData waContact) {
		this.waContact = waContact;
	}

	public MasterData getFreightRegion() {
		return freightRegion;
	}

	public void setFreightRegion(MasterData freightRegion) {
		this.freightRegion = freightRegion;
	}

	
	
	public Integer getWineCount() {
		return wineCount;
	}

	public void setWineCount(Integer wineCount) {
		this.wineCount = wineCount;
	}

	public Integer getActiveWineCount() {
		return activeWineCount;
	}

	public void setActiveWineCount(Integer activeWineCount) {
		this.activeWineCount = activeWineCount;
	}

	public Double getRevenue() {
		return revenue;
	}

	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}

	public Date getLastOfferDate() {
		return lastOfferDate;
	}

	public void setLastOfferDate(Date lastOfferDate) {
		this.lastOfferDate = lastOfferDate;
	}

	public Integer getOptionSelectedAltstates() {
		return optionSelectedAltstates;
	}

	public void setOptionSelectedAltstates(Integer optionSelectedAltstates) {
		this.optionSelectedAltstates = optionSelectedAltstates;
	}

	public Boolean getSellInAltStates() {
		return sellInAltStates;
	}

	public void setSellInAltStates(Boolean sellInAltStates) {
		this.sellInAltStates = sellInAltStates;
	}

	public Boolean getSellInMainStates() {
		return sellInMainStates;
	}

	public void setSellInMainStates(Boolean sellInMainStates) {
		this.sellInMainStates = sellInMainStates;
	}

	public WarehouseModel getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(WarehouseModel warehouseId) {
		this.warehouseId = warehouseId;
	}

	/*public Set<WineModel> getWineItems() {
		return wineItems;
	}

	public void setWineItems(Set<WineModel> wineItems) {
		this.wineItems = wineItems;
	}*/
}
