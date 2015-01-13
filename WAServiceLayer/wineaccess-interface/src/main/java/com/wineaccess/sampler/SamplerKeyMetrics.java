package com.wineaccess.sampler;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1
 * Sampler Key metrics
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class SamplerKeyMetrics implements Serializable {

	private static final long serialVersionUID = 7135764379217252448L;

	private long inPlayStates;
	private long totalActiveOffers;
	private long totalRevenue;
	private long revLast12Months;
	private long revLast90Days;
	private long samplersOnHand;
	private long totalOrders;
	private long totalSamplersSold;
	private long uniqueBuyers;

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

	public long getSamplersOnHand() {
		return samplersOnHand;
	}

	public void setSamplersOnHand(long samplersOnHand) {
		this.samplersOnHand = samplersOnHand;
	}

	public long getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(long totalOrders) {
		this.totalOrders = totalOrders;
	}

	public long getTotalSamplersSold() {
		return totalSamplersSold;
	}

	public void setTotalSamplersSold(long totalSamplersSold) {
		this.totalSamplersSold = totalSamplersSold;
	}

	public long getUniqueBuyers() {
		return uniqueBuyers;
	}

	public void setUniqueBuyers(long uniqueBuyers) {
		this.uniqueBuyers = uniqueBuyers;
	}

}
