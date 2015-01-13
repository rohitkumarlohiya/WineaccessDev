package com.wineaccess.data.model.catalog;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.NumericField;
import org.hibernate.search.annotations.Store;

import com.wineaccess.data.model.EntityListener;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.user.CountryModel;
import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.warehouse.WarehouseModel;

@Entity
@Table(name = "IMPORTER")
@EntityListeners(EntityListener.class)
@NamedQueries({
	    @NamedQuery(name = "ImporterModel.getAll", query = "select id from ImporterModel"),
		@NamedQuery(name = "ImporterModel.getById", query = "from ImporterModel where id = :id and isDeleted=false"),
		@NamedQuery(name = "ImporterModel.getByImporterCode", query = "from ImporterModel where importerCode = :importerCode"),
		@NamedQuery(name = "ImporterModel.getByImporterName", query = "from ImporterModel where importerName = :importerName"),
		@NamedQuery(name = "ImporterModel.getCountOfWarehouses", query = "from ImporterModel where warehouseId.id = :id")
})
@Indexed
public class ImporterModel extends Persistent {

	private static final long serialVersionUID = -8820169607358995317L;

	@Id
	@Column(name = "ID")
	@Field(name = "id", analyze = Analyze.YES, store = Store.NO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Field(name = "importerName", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "IMPORTER_NAME")
	private String importerName;

	@Field(name = "importerNameSort", analyze = Analyze.NO, store = Store.NO)
	@Column(name = "IMPORTER_NAME", insertable = false, updatable = false)
	private String importerNameSort;

	@Field(name = "importerCode", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "IMPORTER_CODE")
	private String importerCode;

	@Field(name = "wineryCount", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "WINERY_COUNT")
	private Long wineryCount = 0L;

	@Column(name = "NOTES")
	private String notes;

	@Column(name = "DEFAULT_CONTACT_ADDRESS_ID")
	private Long defaultContactAddressId;

	@Column(name = "DEFAULT_BILLING_ADDRESS_ID")
	private Long defaultContactBillingAddressId;

	@Field(name = "importerUrl", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "IMPORTER_URL")
	private String importerUrl;

	@Field(name = "status", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "STATUS")
	private Boolean status;

	@OneToOne
	@JoinColumn(name = "COUNTRY_ID")
	@IndexedEmbedded
	@NotFound(action = NotFoundAction.IGNORE)
	private CountryModel countryId;

	@Column(name = "ACCOUNTING_CUSTOMER_NUMBER")
	private String customerNumber;

	@OneToOne
	@JoinColumn(name = "SOURCING_STATUS")
	private MasterData sourcingStatus;

	@Column(name = "WORKFLOW_STATUS")
	private String workflowStatus;

	@Field(name = "isDeleted", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "IS_DELETED")
	private Boolean isDeleted = false;

	@Field(name = "isEnabled", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "IS_ENABLED")
	private Boolean isEnabled = true;
	
	@Field(name = "isActive", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "IS_ACTIVE")
	private Boolean isActive = false;

	@ManyToMany(mappedBy = "importers", fetch = FetchType.EAGER)
	@IndexedEmbedded
	@NotFound(action = NotFoundAction.IGNORE)
	private Set<WineryModel> wineries = new HashSet<WineryModel>();

	@Field(name = "wineCount", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "WINE_COUNT")
	@NumericField
	private int wineCount = 0;

	@Field(name = "activeWineCount", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "ACTIVE_WINE_COUNT")
	@NumericField
	private int activeWineCount = 0;

	@Field(name = "revenue", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "Revenue")
	@NumericField
	private Double revenue = new Double(0.0);
	
	@Field(name = "revenue1", analyze = Analyze.YES, store = Store.NO)
	@Transient
	public String getRevenue1() {
		return String.valueOf(revenue);
	}

	@Field(name = "lastOfferDate", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "LAST_OFFER_DATE")
	private Date lastOfferDate;

	@IndexedEmbedded
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToOne
	@JoinColumn(name = "WA_CONTACT")
	private MasterData waContact;

	@OneToOne
	@JoinColumn(name = "FREIGHT_REGION")
	private MasterData freightRegion;
	
	@OneToOne
	@JoinColumn(name= "WAREHOUSE_ID")
	private WarehouseModel warehouseId;
	
	
	

	/*
	 * @ManyToMany(fetch=FetchType.LAZY)
	 * 
	 * @IndexedEmbedded
	 * 
	 * @NotFound(action = NotFoundAction.IGNORE)
	 * 
	 * @JoinTable(name = "OFFER_IMPORTER", joinColumns = { @JoinColumn(name =
	 * "IMPORTER_ID") }, inverseJoinColumns = { @JoinColumn(name = "OFFER_ID")
	 * }) private Set<OfferModel> offers = new HashSet<OfferModel>();
	 */

	public WarehouseModel getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(WarehouseModel warehouseId) {
		this.warehouseId = warehouseId;
	}

	/*
	 * @OneToMany(mappedBy="importer", cascade={CascadeType.ALL},fetch =
	 * FetchType.EAGER) private Set<ActiveImporterLogHistoryModel> logHistory =
	 * new HashSet<ActiveImporterLogHistoryModel>();
	 * 
	 * public Set<ActiveImporterLogHistoryModel> getLogHistory() { return
	 * logHistory; }
	 * 
	 * public void setLogHistory(Set<ActiveImporterLogHistoryModel> logHistory)
	 * { this.logHistory = logHistory; }
	 */
	public Set<WineryModel> getWineries() {
		return wineries;
	}

	public void setWineries(Set<WineryModel> wineries) {
		this.wineries = wineries;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImporterName() {
		return importerName;
	}

	public void setImporterName(String importerName) {
		this.importerName = importerName;
	}

	public String getImporterCode() {
		return importerCode;
	}

	public void setImporterCode(String importerCode) {
		this.importerCode = importerCode;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Long getDefaultContactAddressId() {
		return defaultContactAddressId;
	}

	public void setDefaultContactAddressId(Long defaultContactAddressId) {
		this.defaultContactAddressId = defaultContactAddressId;
	}

	public Long getDefaultContactBillingAddressId() {
		return defaultContactBillingAddressId;
	}

	public void setDefaultContactBillingAddressId(
			Long defaultContactBillingAddressId) {
		this.defaultContactBillingAddressId = defaultContactBillingAddressId;
	}

	public String getImporterUrl() {
		return importerUrl;
	}

	public void setImporterUrl(String importerUrl) {
		this.importerUrl = importerUrl;
	}

	public CountryModel getCountryId() {
		return countryId;
	}

	public void setCountryId(CountryModel countryId) {
		this.countryId = countryId;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public MasterData getSourcingStatus() {
		return sourcingStatus;
	}

	public void setSourcingStatus(MasterData sourcingStatus) {
		this.sourcingStatus = sourcingStatus;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
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

	public int getWineCount() {
		return wineCount;
	}

	public void setWineCount(int wineCount) {
		this.wineCount = wineCount;
	}

	public int getActiveWineCount() {
		return activeWineCount;
	}

	public void setActiveWineCount(int activeWineCount) {
		this.activeWineCount = activeWineCount;
	}

	/*
	 * public Set<OfferModel> getOffers() { return offers; }
	 * 
	 * public void setOffers(Set<OfferModel> offers) { this.offers = offers; }
	 */

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Double getRevenue() {
		return revenue;
	}

	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getLastOfferDate() {
		return lastOfferDate;
	}

	public void setLastOfferDate(Date lastOfferDate) {
		this.lastOfferDate = lastOfferDate;
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

	public String getImporterNameSort() {
		return importerNameSort;
	}

	public void setImporterNameSort(String importerNameSort) {
		this.importerNameSort = importerNameSort;
	}

	public Long getWineryCount() {
		return wineryCount;
	}

	public void setWineryCount(Long wineryCount) {
		this.wineryCount = wineryCount;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
	
}
