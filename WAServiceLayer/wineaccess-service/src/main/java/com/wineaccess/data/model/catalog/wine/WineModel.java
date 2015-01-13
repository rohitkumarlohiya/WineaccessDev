package com.wineaccess.data.model.catalog.wine;	

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
import com.wineaccess.data.model.catalog.ImporterModel;
import com.wineaccess.data.model.catalog.WineImporterFieldBridge;
import com.wineaccess.data.model.catalog.WineryImporterContacts;
import com.wineaccess.data.model.catalog.WineryModel;
import com.wineaccess.data.model.catalog.product.ProductItemModel;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.warehouse.WarehouseModel;

@Entity
@Table(name = "WINE")
@EntityListeners(EntityListener.class)
@NamedQueries({ @NamedQuery(name = "WineModel.getById", query = "from WineModel where id = :id and isDeleted = false"),
				@NamedQuery(name = "WineModel.getByWarehouseId", query = "from WineModel where warehouseId.id = :warehouseId")
})
@Indexed
public class WineModel extends Persistent {

	private static final long serialVersionUID = 3767851152221833322L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Field(name = "id", analyze = Analyze.YES, store = Store.NO)
	private Long id;
	
	@IndexedEmbedded
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name = "WINERY_ID")
	private WineryModel wineryId;

	@Column(name = "WINE_FULL_NAME")
	private String wineFullName;

	@Field(name = "wineName", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "WINE_NAME")
	private String wineName;

	@Field(name = "wineNameSort", analyze = Analyze.NO, store = Store.NO)
	@Column(name = "WINE_NAME", insertable = false, updatable = false)
	private String wineNameSort;

	@IndexedEmbedded
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name = "VERIETAL")
	private MasterData verietal;

	@IndexedEmbedded
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name = "VINTAGE")
	private MasterData vintage;

	@Column(name = "WINE_SHORT_NAME")
	private String wineShortName;

	@ManyToOne
	@JoinColumn(name = "BOTTLE_IN_ML")
	private MasterData bottleInMl;

	@ManyToOne
	@JoinColumn(name = "BOTTLE_PER_BOX")
	private MasterData bottlesPerBox;

	@Column(name = "ALCOHOL_PERCENTAGE")
	private Double alcoholPercentage;

	@Column(name = "NOTES")
	private String notes;

	@Column(name = "WINE_LABEL")
	private String wineLabel;

	@Column(name = "PRIVATE_LABEL")
	private String privateLabel;

	@IndexedEmbedded
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name = "WINE_TYPE")
	private MasterData wineType;

	@ManyToOne
	@JoinColumn(name = "WINE_SOURCING_ID")
	private MasterData wineSourcingId;

	@Column(name = "SEND_TO_FULL_FILLER_ON")
	private Boolean sendToFullfillerOn;

	@Column(name = "USA_ARRIVAL_DATE")
	private Date usaArrivalDate;

	@Column(name = "LICENSE_FULL_FILLMENT_PARTNER_ID")
	private Long LicenseFullfillmentPartnerId;

	@Column(name = "OPTION_SELECTED_ALT_STATES")
	private Integer optionSelectedAltstates;

	@Column(name = "SELL_IN_ALT_STATES")
	private Boolean sellInAltStates;

	@Column(name = "SELL_IN_MAIN_STATES")
	private Boolean sellInMainStates;

	@Field(name = "activeOffer", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "ACTIVE_OFFER")
	private Long activeOffer = 0L;
	
	@Field(name = "totalRevenue", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "TOTAL_REVENUE")
	@NumericField
	private Double totalRevenue = 0.0;

	@Field(name = "userReview", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "USER_REVIEW")
	private Long userReview;

	@NumericField
	@Field(name = "userRatings", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "USER_RATINGS")
	private Float userRatings;

	@Field(name = "expertScore", analyze = Analyze.NO, store = Store.NO)
	@Column(name = "EXPERT_SCORE")
	private String expertScore;

	@Field(name = "expertRating", analyze = Analyze.NO, store = Store.NO)
	@Column(name = "EXPERT_RATING")
	private String expertRating;

	@Field(name = "lastOfferDate", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "LAST_OFFER_DATE")
	private Date lastOfferDate;

	@ManyToOne
	@JoinColumn(name = "CONTACT_ID")
	private WineryImporterContacts contactId;

	@Column(name = "IS_FULL_CASE_ONLY")
	private Boolean isFullCaseOnly;

	@Column(name = "BOTTLE_WEIGHT_IN_LBS")
	private Long bottleWeightInLBS;

	@Field(name = "isDeleted", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "IS_DELETED")
	private Boolean isDeleted;

	@Column(name = "IS_IMPORTED")
	private Boolean isImported;

	@ManyToOne
	@JoinColumn(name = "IMPORTER_ID")
	private ImporterModel importerId;
	
	@Field(name = "isEnabled", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "IS_ENABLED")
	private Boolean isEnabled;	
 
	@IndexedEmbedded
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToOne(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
	@JoinColumn(name="PRODUCT_ITEM_ID")
	private ProductItemModel product; 
	
	@OneToOne
	@JoinColumn(name= "WAREHOUSE_ID")
	private WarehouseModel warehouseId;
	
	@Column( name = "MAPPED_WINERY_ID_WITH_PERMIT")
	private Long mappedWineryWithPermit;
	
	@Column(name = "IS_KACHINA")
	private Boolean isKachina;	
	
	@Column(name = "IS_WINERY_TRANSFER")
	private Boolean isWineryTransfer;	
	
	public Boolean getIsKachina() {
		return isKachina;
	}

	public void setIsKachina(Boolean isKachina) {
		this.isKachina = isKachina;
	}

	public Boolean getIsWineryTransfer() {
		return isWineryTransfer;
	}

	public void setIsWineryTransfer(Boolean isWineryTransfer) {
		this.isWineryTransfer = isWineryTransfer;
	}

	public WarehouseModel getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(WarehouseModel warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWineFullName() {
		return wineFullName;
	}

	public void setWineFullName(String wineFullName) {
		this.wineFullName = wineFullName;
	}

	public String getWineName() {
		return wineName;
	}

	public void setWineName(String wineName) {
		this.wineName = wineName;
	}

	public MasterData getVerietal() {
		return verietal;
	}

	public void setVerietal(MasterData verietal) {
		this.verietal = verietal;
	}

	public MasterData getVintage() {
		return vintage;
	}

	public void setVintage(MasterData vintage) {
		this.vintage = vintage;
	}

	public String getWineryShortName() {
		return wineShortName;
	}

	public void setWineryShortName(String wineryShortName) {
		this.wineShortName = wineryShortName;
	}

	public MasterData getBottleInMl() {
		return bottleInMl;
	}

	public void setBottleInMl(MasterData bottolInMl) {
		this.bottleInMl = bottolInMl;
	}

	public MasterData getBottlesPerBox() {
		return bottlesPerBox;
	}

	public void setBottlesPerBox(MasterData bottlesPerBox) {
		this.bottlesPerBox = bottlesPerBox;
	}

	public Double getAlcoholPercentage() {
		return alcoholPercentage;
	}

	public void setAlcoholPercentage(Double alcohalPercentage) {
		this.alcoholPercentage = alcohalPercentage;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getWineLabel() {
		return wineLabel;
	}

	public void setWineLabel(String wineLabel) {
		this.wineLabel = wineLabel;
	}

	public String getPrivateLabel() {
		return privateLabel;
	}

	public void setPrivateLabel(String privateLabel) {
		this.privateLabel = privateLabel;
	}

	public MasterData getWineType() {
		return wineType;
	}

	public void setWineType(MasterData wineType) {
		this.wineType = wineType;
	}

	public MasterData getWineSourcingId() {
		return wineSourcingId;
	}

	public void setWineSourcingId(MasterData wineSoucingId) {
		this.wineSourcingId = wineSoucingId;
	}

	public Boolean getSendToFullfillerOn() {
		return sendToFullfillerOn;
	}

	public void setSendToFullfillerOn(Boolean sendToFullfillerOn) {
		this.sendToFullfillerOn = sendToFullfillerOn;
	}

	public Date getUsaArrivalDate() {
		return usaArrivalDate;
	}

	public void setUsaArrivalDate(Date usaArrivalDate) {
		this.usaArrivalDate = usaArrivalDate;
	}

	public Long getLicenseFullfillmentPartnerId() {
		return LicenseFullfillmentPartnerId;
	}

	public void setLicenseFullfillmentPartnerId(
			Long licenseFullfillmentPartnerId) {
		LicenseFullfillmentPartnerId = licenseFullfillmentPartnerId;
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

	public WineryModel getWineryId() {
		return wineryId;
	}

	public void setWineryId(WineryModel wineryId) {
		this.wineryId = wineryId;
	}

	public Long getActiveOffer() {
		return activeOffer;
	}

	public void setActiveOffer(Long activeOffer) {
		this.activeOffer = activeOffer;
	}

	public Double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(Double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public Long getUserReview() {
		return userReview;
	}

	public void setUserReview(Long userReviews) {
		this.userReview = userReviews;
	}

	public Float getUserRatings() {
		return userRatings;
	}

	public void setUserRatings(Float userRatings) {
		this.userRatings = userRatings;
	}

	public String getExpertScore() {
		return expertScore;
	}

	public void setExpertScore(String expertScore) {
		this.expertScore = expertScore;
	}

	public String getExpertRating() {
		return expertRating;
	}

	public void setExpertRating(String expertRating) {
		this.expertRating = expertRating;
	}

	public Date getLastOfferDate() {
		return lastOfferDate;
	}

	public void setLastOfferDate(Date lastOfferDate) {
		this.lastOfferDate = lastOfferDate;
	}

	public String getWineShortName() {
		return wineShortName;
	}

	public void setWineShortName(String wineShortName) {
		this.wineShortName = wineShortName;
	}

	public WineryImporterContacts getContactId() {
		return contactId;
	}

	public void setContactId(WineryImporterContacts contactId) {
		this.contactId = contactId;
	}

	public Boolean getIsFullCaseOnly() {
		return isFullCaseOnly;
	}

	public void setIsFullCaseOnly(Boolean isFullCaseOnly) {
		this.isFullCaseOnly = isFullCaseOnly;
	}

	public Long getBottleWeightInLBS() {
		return bottleWeightInLBS;
	}

	public void setBottleWeightInLBS(Long bottleWeightInLBS) {
		this.bottleWeightInLBS = bottleWeightInLBS;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Boolean getIsImported() {
		return isImported;
	}

	public void setIsImported(Boolean isImported) {
		this.isImported = isImported;
	}

	public ImporterModel getImporterId() {
		return importerId;
	}

	public void setImporterId(ImporterModel importerId) {
		this.importerId = importerId;
	}

	public String getWineNameSort() {
		return wineNameSort;
	}

	public void setWineNameSort(String wineNameSort) {
		this.wineNameSort = wineNameSort;
	}
	
	public ProductItemModel getProduct() {
		return product;
	}

	public void setProduct(ProductItemModel product) {
		this.product = product;
	}
	


	public Long getMappedWineryWithPermit() {
	    return mappedWineryWithPermit;
	}

	public void setMappedWineryWithPermit(Long mappedWineryWithPermit) {
	    this.mappedWineryWithPermit = mappedWineryWithPermit;
	}

	@FieldBridge(impl = WineImporterFieldBridge.class)
	@Fields( {
		@Field(name = "importerName", analyze = Analyze.YES, store = Store.NO),
		@Field(name = "importerNameSort", analyze = Analyze.NO, store = Store.NO),
		@Field(name = "region", analyze = Analyze.NO, store = Store.NO)
	})
	@Transient
	public TestModel getImportedIndex() {
		if (getIsImported()) {
			return new TestModel(getIsImported(), getImporterId());
		} else {
			return null;
		}
		
	}

	@FieldBridge(impl = WineImporterFieldBridge.class)
	@Fields( {
		@Field(name = "importerIdentifier", analyze = Analyze.YES, store = Store.NO)
	})
	@Transient
	public Long getImporterIdentifier() {
		if (getImporterId() != null) {
			return getImporterId().getId();
		}
		return null;
	}
}
