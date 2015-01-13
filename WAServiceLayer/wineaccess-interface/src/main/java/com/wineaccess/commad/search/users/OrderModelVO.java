package com.wineaccess.commad.search.users;

import java.util.Date;

public class OrderModelVO {
	
	private Long noOfOrders;
	private Date lastPurchaseDate;
	private Long noOfOpenOrder;
	private float totalRevenue;
	private int noOfInteractions;
	
	public Long getNoOfOrders() {
		return noOfOrders;
	}
	public void setNoOfOrders(Long noOfOrders) {
		this.noOfOrders = noOfOrders;
	}
	public Date getLastPurchaseDate() {
		return lastPurchaseDate;
	}
	public void setLastPurchaseDate(Date lastPurchaseDate) {
		this.lastPurchaseDate = lastPurchaseDate;
	}
	public Long getNoOfOpenOrder() {
		return noOfOpenOrder;
	}
	public void setNoOfOpenOrder(Long noOfOpenOrder) {
		this.noOfOpenOrder = noOfOpenOrder;
	}
	
	public float getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(float totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	public int getNoOfInteractions() {
		return noOfInteractions;
	}
	public void setNoOfInteractions(int noOfInteractions) {
		this.noOfInteractions = noOfInteractions;
	}
	public OrderModelVO(Long noOfOrders, Date lastPurchaseDate,
			Long noOfOpenOrder, float totalRevenue, int noOfInteractions) {
		super();
		this.noOfOrders = noOfOrders;
		this.lastPurchaseDate = lastPurchaseDate;
		this.noOfOpenOrder = noOfOpenOrder;
		this.totalRevenue = totalRevenue;
		this.noOfInteractions = noOfInteractions;
	}
	public OrderModelVO() {
		super();
		this.noOfOrders = 0L;
		this.noOfOpenOrder = 0L;
		this.totalRevenue = 0.00f;
		this.noOfInteractions = 0;
	}
	
	
	
	
	
	
}
