package com.wineaccess.data.model.catalog.product;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.wineaccess.data.model.EntityListener;
import com.wineaccess.data.model.catalog.wine.WineModel;
import com.wineaccess.data.model.user.Persistent;

@Entity
@Table(name = "PRODUCT_MASTER")
@EntityListeners(EntityListener.class)
public class ProductMaster extends Persistent {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PRODUCT_TYPE")
	private String productType;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "PRODUCT", joinColumns = { @JoinColumn(name = "PRODUCT_ID") }, inverseJoinColumns = { @JoinColumn(name = "WINE_ID") })
	private Set<WineModel> wines = new HashSet<WineModel>();


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

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Set<WineModel> getWines() {
		return wines;
	}

	public void setWines(Set<WineModel> wines) {
		this.wines = wines;
	}
}
