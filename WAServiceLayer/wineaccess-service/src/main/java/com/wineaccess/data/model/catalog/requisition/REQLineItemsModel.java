package com.wineaccess.data.model.catalog.requisition;

import java.math.BigDecimal;

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
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.wineaccess.data.model.EntityListener;
import com.wineaccess.data.model.catalog.wine.WineModel;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.user.Persistent;

@Entity
@Table(name = "REQ_LINE_ITEMS")
@EntityListeners(EntityListener.class)
@NamedQueries({
		@NamedQuery(name = "REQLineItemsModel.getByIdProductId", query = "from REQLineItemsModel where requisition.id = :poMasterId and wine.id =:wineId"),
		@NamedQuery(name = "REQLineItemsModel.removeWineFromRequisition", query = "delete from REQLineItemsModel where requisition.id = :requisitionId and wine.id =:wineId"),
		@NamedQuery(name = "REQLineItemsModel.getByIdAndRequisitionId", query = "from REQLineItemsModel where requisition.id = :requisitionId and id =:reqLineItemId")})
@Indexed
public class REQLineItemsModel extends Persistent {

	private static final long serialVersionUID = -8664072458950757855L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Field(name = "id", analyze = Analyze.YES, store = Store.NO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "REQ_MASTER_ID")
	private RequisitionModel requisition;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "WINE_ID")
	private WineModel wine;

	@Column(name = "QUANTITY_OF_BOTTLES")
	private Integer qtyBottles;

	@Column(name = "QUANTITY_BOXES")
	private Integer qtyBoxes;

	@ManyToOne
	@JoinColumn(name = "BOTTLE_PER_BOX")
	private MasterData bottlePerBox;

	@ManyToOne
	@JoinColumn(name = "BOTTLE_IN_ML")
	private MasterData bottleInMl;

	@Column(name = "BOTTLE_PRICE")
	private BigDecimal bottlePrice;

	@Column(name = "COST_PER_BOX")
	private BigDecimal costPerBox;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RequisitionModel getRequisition() {
		return requisition;
	}

	public void setRequisition(RequisitionModel requisition) {
		this.requisition = requisition;
	}

	public WineModel getWine() {
		return wine;
	}

	public void setWine(WineModel wine) {
		this.wine = wine;
	}

	public MasterData getBottlePerBox() {
		return bottlePerBox;
	}

	public void setBottlePerBox(MasterData bottlePerBox) {
		this.bottlePerBox = bottlePerBox;
	}

	public MasterData getBottleInMl() {
		return bottleInMl;
	}

	public void setBottleInMl(MasterData bottleInMl) {
		this.bottleInMl = bottleInMl;
	}

	public Integer getQtyBottles() {
		return qtyBottles;
	}

	public void setQtyBottles(Integer qtyOfbottles) {
		this.qtyBottles = qtyOfbottles;
	}

	public Integer getQtyBoxes() {
		return qtyBoxes;
	}

	public void setQtyBoxes(Integer qtyBoxes) {
		this.qtyBoxes = qtyBoxes;
	}

	public BigDecimal getBottlePrice() {
		return bottlePrice;
	}

	public void setBottlePrice(BigDecimal bottlePrice) {
		this.bottlePrice = bottlePrice;
	}

	public BigDecimal getCostPerBox() {
		return costPerBox;
	}

	public void setCostPerBox(BigDecimal costPerBox) {
		this.costPerBox = costPerBox;
	}

}
