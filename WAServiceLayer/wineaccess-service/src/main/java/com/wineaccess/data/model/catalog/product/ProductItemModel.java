package com.wineaccess.data.model.catalog.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.wineaccess.data.model.EntityListener;
import com.wineaccess.data.model.user.Persistent;

@Entity
@Table(name = "PRODUCT_ITEM_MAPPING")
@EntityListeners(EntityListener.class)
@NamedQueries({
		@NamedQuery(name = "ProductItemModel.getById", query = "from ProductItemModel where id = :id"),
		@NamedQuery(name = "ProductItemModel.getByproductIds", query = "from ProductItemModel where id in (:productIds)"),
		@NamedQuery(name = "ProductItemModel.getByIdAndProduct", query = "from ProductItemModel where id = :id and productId = :productId") })
@Indexed
public class ProductItemModel extends Persistent {

	private static final long serialVersionUID = -8635697367230733661L;

	@Id
	@Field(name = "id", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Field(name = "productId", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "PRODUCT_ID")
	private Long productId;

	@Field(name = "itemId", analyze = Analyze.NO, store = Store.NO)
	@Column(name = "ITEM_ID")
	private Long itemId;
	 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	
}
