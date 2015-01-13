package com.wineaccess.data.model.catalog;

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
@Table(name = "WINERY_LICENSE_PERMIT_ALT_STATES_FULLFILL")
@EntityListeners(EntityListener.class)

@NamedQueries({
	@NamedQuery(name = "getFulFilByWineryId", query = "from WineryLicenseFullfillAltStates wia where wineryId.id= :wineryId")	
})
public class WineryLicenseFullfillAltStates extends Persistent {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "WINERY_ID")
	private WineryModel wineryId;

	@Column(name = "WA_WILL_NOT_FULLFILL")
	private Boolean waWillNotFullFill = Boolean.FALSE;

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

	

	public WineryModel getWineryId() {
		return wineryId;
	}

	public void setWineryId(WineryModel wineryId) {
		this.wineryId = wineryId;
	}

	
	public Boolean getWaWillNotFullFill() {
		return waWillNotFullFill;
	}

	public void setWaWillNotFullFill(Boolean waWillNotFullFill) {
		this.waWillNotFullFill = waWillNotFullFill;
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
