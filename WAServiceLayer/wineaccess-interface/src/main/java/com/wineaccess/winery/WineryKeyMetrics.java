package com.wineaccess.winery;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1
 * 
 * it will show the winery key metrics details
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class WineryKeyMetrics implements Serializable {

	private static final long serialVersionUID = 1L;

	private long noOfWines;
	private long totalActiveOffers;
	private long totalRevenue;
	private long revLast12Months;
	private long revLast90Days;
	private List<String> compliance;
	private long avgRating;
	private long expertScore;
	private long totalOrders;
	private long totalCasesSold;
	private long uniqueBuyers;
	private List<String> topSellingVintage;
	private long casesOnHand;
	private long avgOrderValue;
	private long avgBottles;
	private long avgBottlePrice;

	public long getNoOfWines() {
		return noOfWines;
	}

	public void setNoOfWines(long noOfWines) {
		this.noOfWines = noOfWines;
	}

	public long getTotalActiveOffers() {
		return totalActiveOffers;
	}

	public void setTotalActiveOffers(long totalActiveOffers) {
		this.totalActiveOffers = totalActiveOffers;
	}

	public long getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(long totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public long getRevLast12Months() {
		return revLast12Months;
	}

	public void setRevLast12Months(long revLast12Months) {
		this.revLast12Months = revLast12Months;
	}

	public long getRevLast90Days() {
		return revLast90Days;
	}

	public void setRevLast90Days(long revLast90Days) {
		this.revLast90Days = revLast90Days;
	}

	public List<String> getCompliance() {
		return compliance;
	}

	public void setCompliance(List<String> compliance) {
		this.compliance = compliance;
	}

	public long getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(long avgRating) {
		this.avgRating = avgRating;
	}

	public long getExpertScore() {
		return expertScore;
	}

	public void setExpertScore(long expertScore) {
		this.expertScore = expertScore;
	}

	public long getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(long totalOrders) {
		this.totalOrders = totalOrders;
	}

	public long getTotalCasesSold() {
		return totalCasesSold;
	}

	public void setTotalCasesSold(long totalCasesSold) {
		this.totalCasesSold = totalCasesSold;
	}

	public long getUniqueBuyers() {
		return uniqueBuyers;
	}

	public void setUniqueBuyers(long uniqueBuyers) {
		this.uniqueBuyers = uniqueBuyers;
	}

	public List<String> getTopSellingVintage() {
		return topSellingVintage;
	}

	public void setTopSellingVintage(List<String> topSellingVintage) {
		this.topSellingVintage = topSellingVintage;
	}

	public long getCasesOnHand() {
		return casesOnHand;
	}

	public void setCasesOnHand(long casesOnHand) {
		this.casesOnHand = casesOnHand;
	}

	public long getAvgOrderValue() {
		return avgOrderValue;
	}

	public void setAvgOrderValue(long avgOrderValue) {
		this.avgOrderValue = avgOrderValue;
	}

	public long getAvgBottles() {
		return avgBottles;
	}

	public void setAvgBottles(long avgBottles) {
		this.avgBottles = avgBottles;
	}

	public long getAvgBottlePrice() {
		return avgBottlePrice;
	}

	public void setAvgBottlePrice(long avgBottlePrice) {
		this.avgBottlePrice = avgBottlePrice;
	}

}