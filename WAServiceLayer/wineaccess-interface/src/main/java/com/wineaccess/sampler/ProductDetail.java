package com.wineaccess.sampler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductDetail implements Serializable{

	private String id;
	private String productId;
	private String productName;
	private String quantity;
	private String srpPrice;
	private List<ProductComplianceDetails> complianceDetails = new ArrayList<ProductComplianceDetails>();
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getSrpPrice() {
		return srpPrice;
	}
	public void setSrpPrice(String srpPrice) {
		this.srpPrice = srpPrice;
	}
	public List<ProductComplianceDetails> getComplianceDetails() {
		return complianceDetails;
	}
	public void setComplianceDetails(
			List<ProductComplianceDetails> complianceDetails) {
		this.complianceDetails = complianceDetails;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
}
