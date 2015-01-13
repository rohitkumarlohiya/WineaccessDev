package com.wineaccess.wine;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.common.Compliance;
import com.wineaccess.common.ExpertScore;

/**
 * @author gaurav.agarwal1
 * 
 * wine key metrics
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class WineKeyMetrics implements Serializable {

    private static final long serialVersionUID = -345501103731569047L;

    private long inPlayStates;
    private long totalActiveOffers;
    private long totalRevenue;
    private long revLast12Months;
    private long revLast90Days;
    private List<Compliance> compliance;
    private long avgRating;
    private List<ExpertScore> expertScore;
    private long totalOrders;
    private long totalCasesSold;
    private long uniqueBuyers;
    private long casesOnHand;
    private long avgOrderValue;
    private long avgBottles;
    private long avgBottlePrice;

    public long getInPlayStates() {
        return inPlayStates;
    }

    public void setInPlayStates(long inPlayStates) {
        this.inPlayStates = inPlayStates;
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

    public List<Compliance> getCompliance() {
        return compliance;
    }

    public void setCompliance(List<Compliance> compliance) {
        this.compliance = compliance;
    }

    public long getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(long avgRating) {
        this.avgRating = avgRating;
    }

    public List<ExpertScore> getExpertScore() {
        return expertScore;
    }

    public void setExpertScore(List<ExpertScore> expertScore) {
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
