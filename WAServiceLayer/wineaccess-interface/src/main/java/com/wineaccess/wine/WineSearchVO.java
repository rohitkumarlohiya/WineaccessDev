package com.wineaccess.wine;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.codehaus.jackson.annotate.JsonProperty;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.data.model.catalog.wine.WineModel;

@XmlAccessorType(XmlAccessType.FIELD)
public class WineSearchVO implements Serializable {

	private static final long serialVersionUID = 8175796558408280038L;
	private Long id;
	private Long wineId;
	@XmlElement(name="wineName")
	@JsonProperty("wineName")
	private String name;
	private String wineryName;
	private String vintage;
	private String importerName;
	private String region;
	private String varietal;
	private Long activeOffers;
	private Double totalRevenue;
	private Long userReviews;
	private Float userRatings;
	private String expertScore;
	private String expertRating;
	private String lastOfferDate;
	private Boolean isImported;
	private String isEnabled = "";
	private Long warehouseId;

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public WineSearchVO() {
		super();
	}

	public WineSearchVO(WineModel wineModel) {
		super();
		this.wineId = wineModel.getId();
		this.id = wineModel.getProduct().getId();
		this.name = wineModel.getWineName();
		this.isImported = wineModel.getIsImported();
		this.wineryName = wineModel.getWineryId().getWineryName();
		if (this.isImported) {
			if (null != wineModel.getImportedIndex()) {
				this.region = wineModel.getImportedIndex().getActiveImporter()
						.getCountryId().getCountryCode();
				this.importerName = wineModel.getImportedIndex()
						.getActiveImporter().getImporterName();
			}
		} else {
			this.region = "USA";
		}

		this.vintage = wineModel.getVintage().getName();
		this.varietal = wineModel.getVerietal().getName();
		// TODO: dummy value for active offer.
		this.activeOffers = 0L;
		this.totalRevenue = wineModel.getTotalRevenue();
		// TODO: dummy value for user reviews.
		this.userReviews = 0L;
		this.userRatings = wineModel.getUserRatings();
		// TODO: dummy value for expert score.
		this.expertScore = wineModel.getExpertScore();
		// TODO: dummy value for expert rating.
		this.expertRating = wineModel.getExpertRating();
		if (null != wineModel.getLastOfferDate()) {
			this.lastOfferDate = ApplicationUtils.convertDateToString(wineModel
					.getLastOfferDate());
		}
		this.isEnabled = wineModel.getIsEnabled().toString();

		if (wineModel.getWarehouseId() != null) {
			this.warehouseId = wineModel.getWarehouseId().getId();
		}
	}

	public Long getWineId() {
		return wineId;
	}

	public void setWineId(Long wineId) {
		this.wineId = wineId;
	}

	public String getVarietal() {
		return varietal;
	}

	public void setVarietal(String varietal) {
		this.varietal = varietal;
	}

	public String getWineryName() {
		return wineryName;
	}

	public void setWineryName(String wineryName) {
		this.wineryName = wineryName;
	}

	public String getVintage() {
		return vintage;
	}

	public void setVintage(String vintage) {
		this.vintage = vintage;
	}

	public String getImporterName() {
		return importerName;
	}

	public void setImporterName(String importerName) {
		this.importerName = importerName;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getActiveOffers() {
		return activeOffers;
	}

	public void setActiveOffers(Long activeOffers) {
		this.activeOffers = activeOffers;
	}

	public Double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(Double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public Long getUserReviews() {
		return userReviews;
	}

	public void setUserReviews(Long userReviews) {
		this.userReviews = userReviews;
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

	public String getLastOfferDate() {
		return lastOfferDate;
	}

	public void setLastOfferDate(String lastOfferDate) {
		this.lastOfferDate = lastOfferDate;
	}

	public Boolean getIsImported() {
		return isImported;
	}

	public void setIsImported(Boolean isImported) {
		this.isImported = isImported;
	}

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}
}
