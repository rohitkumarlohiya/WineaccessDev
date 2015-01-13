package com.wineaccess.data.model.catalog;

import java.util.Date;

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
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.user.Persistent;

@Entity
@Table(name = "WINE_LICENSE_NO_PERMIT")
@EntityListeners(EntityListener.class)
@NamedQueries({
    @NamedQuery(name = "getNoPermitIdByWineId", query = "select wineNoPermit.id,id from WineLicenseNoPermit wia where productId.id= :wineId"),
    @NamedQuery(name = "getNoPermitByWineId", query = "from WineLicenseNoPermit wia where productId.id= :wineId"),

})
public class WineLicenseNoPermit extends Persistent {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private WineModel productId;

    @ManyToOne
    @JoinColumn(name = "WINERY_NO_PERMIT")
    private MasterData wineNoPermit;

    @Column(name = "PRICE_FILED")
    private Date priceFiled;

    @Column(name = "3T_STATUS")
    private String status ;

    @Column(name = "IS_SELECTED")
    private Boolean isSelected;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public WineModel getProductId() {
	return productId;
    }

    public void setProductId(WineModel productId) {
	this.productId = productId;
    }

    public MasterData getWineNoPermit() {
	return wineNoPermit;
    }

    public void setWineNoPermit(MasterData wineNoPermit) {
	this.wineNoPermit = wineNoPermit;
    }

    public Date getPriceFiled() {
	return priceFiled;
    }

    public void setPriceFiled(Date priceFiled) {
	this.priceFiled = priceFiled;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public Boolean getIsSelected() {
	return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
	this.isSelected = isSelected;
    }




}
