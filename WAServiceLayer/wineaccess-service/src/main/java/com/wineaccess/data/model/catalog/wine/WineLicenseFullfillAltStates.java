package com.wineaccess.data.model.catalog.wine;

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
import com.wineaccess.data.model.user.Persistent;

@Entity
@Table(name = "WINE_LICENSE_PERMIT_ALT_STATES_FULLFILL")
@EntityListeners(EntityListener.class)

@NamedQueries({
	@NamedQuery(name = "getFulFilByWineId", query = "from WineLicenseFullfillAltStates wia where wineId.id= :wineId")	
})
public class WineLicenseFullfillAltStates extends Persistent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "WINE_ID",unique=true)
	private WineModel wineId;

	@Column(name = "WA_WILL_FULLFILL")
	private Boolean waWillFullFill = Boolean.FALSE;

	@Column(name = "WINERY_STORAGE_CONTRACT")
	private Boolean wineryStorageContract = Boolean.FALSE;

	@Column(name = "WA_PLATFORM_CONTRACT")
	private Boolean waPlatformContract = Boolean.FALSE;

	@Column(name = "ESCROW_CONTRACT")
	private Boolean escrowContract = Boolean.FALSE;
	
	@Column(name = "IS_SELECTED")
	private Boolean isSelected = Boolean.FALSE;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public WineModel getWineId() {
		return wineId;
	}

	public void setWineId(WineModel wineId) {
		this.wineId = wineId;
	}

	public Boolean getWaWillFullFill() {
		return waWillFullFill;
	}

	public void setWaWillFullFill(Boolean waWillFullFill) {
		this.waWillFullFill = waWillFullFill;
	}

	public Boolean getWineryStorageContract() {
		return wineryStorageContract;
	}

	public void setWineryStorageContract(Boolean wineryStorageContract) {
		this.wineryStorageContract = wineryStorageContract;
	}

	public Boolean getWaPlatformContract() {
		return waPlatformContract;
	}

	public void setWaPlatformContract(Boolean waPlatformContract) {
		this.waPlatformContract = waPlatformContract;
	}

	public Boolean getEscrowContract() {
		return escrowContract;
	}

	public void setEscrowContract(Boolean escrowContract) {
		this.escrowContract = escrowContract;
	}

	public Boolean getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}

	

	
}
