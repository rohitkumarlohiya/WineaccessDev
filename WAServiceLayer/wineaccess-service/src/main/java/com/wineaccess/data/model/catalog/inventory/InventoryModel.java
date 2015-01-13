package com.wineaccess.data.model.catalog.inventory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.wineaccess.data.model.EntityListener;
import com.wineaccess.data.model.catalog.wine.WineModel;
import com.wineaccess.data.model.user.Persistent;

@Entity
@Table(name = "INVENTORY")
@EntityListeners(EntityListener.class)
@NamedQueries({ @NamedQuery(name = "InventoryModel.getById", query = "from InventoryModel where id = :id"),
	@NamedQuery(name = "InventoryModel.getByWineId", query = "from InventoryModel where wine.id = :wineId")
	})
public class InventoryModel extends Persistent {

	private static final long serialVersionUID = 7593713860202112977L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "WINE_ID")
	private WineModel wine;

	@Column(name = "PHYSICAL_INVENTORY")
	private Integer physicalInventory = new Integer(0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public WineModel getWine() {
		return wine;
	}

	public void setWine(WineModel wine) {
		this.wine = wine;
	}

	public Integer getPhysicalInventory() {
		return physicalInventory;
	}

	public void setPhysicalInventory(Integer physicalInventory) {
		this.physicalInventory = physicalInventory;
	}

}
