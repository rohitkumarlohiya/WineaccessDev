package com.wineaccess.data.model.catalog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wineaccess.data.model.EntityListener;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.user.CityModel;
import com.wineaccess.data.model.user.CountryModel;
import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.data.model.user.StateModel;


/**
 * @author abhishek.sharma1
 *
 */
@Entity
@Table(name = "WINERY_IMPORTER_ADDRESS")
@EntityListeners(EntityListener.class)
@NamedQueries({
	@NamedQuery(name = "getByWineyIdIsdefault", query = "select id from WineryImporterAddressModel wia where wineryId= :wineryId and isDefault=1"),
	@NamedQuery(name = "getByImporterIdIsdefault", query = "select id from WineryImporterAddressModel wia where importerId =:importerId and isDefault=1"),
	@NamedQuery(name = "updateIsDefault", query = "UPDATE WineryImporterAddressModel wia set isDefault=false where wia.id IN :idsList"),
	@NamedQuery(name = "findById", query = "from WineryImporterAddressModel wia where wia.id= :id "),
	@NamedQuery(name = "findByWineryIdAddrId", query = "from WineryImporterAddressModel wia where wia.id= :addrId and wia.wineryId= :wineryId "),
	@NamedQuery(name = "findByImporterIdAddrId", query = "from WineryImporterAddressModel wia where wia.id= :addrId and wia.importerId= :importerId "),
	@NamedQuery(name = "findByWineryId", query = "from WineryImporterAddressModel wia where wia.wineryId= :wineryId  "),
	@NamedQuery(name = "findByImporterId", query = "from WineryImporterAddressModel wia where wia.importerId= :importerId "),
	@NamedQuery(name = "WineryImporterAddressModel.getByAddressType", query = "from WineryImporterAddressModel where addressType.id =:addressTypeId and isDeleted=false"),
	@NamedQuery(name = "findByIdNonDeleted", query = "from WineryImporterAddressModel wia where wia.id= :id  and isDeleted=false")
	
})

public class WineryImporterAddressModel extends Persistent {

	private static final long serialVersionUID = -7248690572731436369L;

	@Id
	@Column(name = "ID", columnDefinition = "BIGINT(20) UNSIGNED ")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ADDRESS_LINE1", columnDefinition = "VARCHAR(255) NULL")
	private String addressLine1;

	@Column(name = "ADDRESS_LINE2", columnDefinition = "VARCHAR(255) NULL")
	private String addressLine2;

	@OneToOne(fetch = FetchType.EAGER )
	@JoinColumn(name="CITY_ID")
	private CityModel cityId;

	@OneToOne(fetch = FetchType.EAGER )
	@JoinColumn(name="STATE_ID")
	private StateModel stateId;

	@OneToOne(fetch = FetchType.EAGER )
	@JoinColumn(name="COUNTRY_ID")
	private CountryModel countryId;

	@Column(name = "ZIPCODE", columnDefinition = "VARCHAR(15) NULL")
	private String zipcode;

	@Column(name = "WINERY_ID", columnDefinition = "BIGINT(20) UNSIGNED  NULL")
	private Long wineryId;

	@Column(name = "IMPORTER_ID", columnDefinition = "BIGINT(20) UNSIGNED NULL")
	private Long importerId;

	@Column(name = "PHONE", columnDefinition = "VARCHAR(20) NULL")
	private String phone;

	@Column(name = "FIRST_NAME", columnDefinition = "VARCHAR(255) NULL")
	private String firstName;

	@Column(name = "LAST_NAME", columnDefinition = "VARCHAR(255) NULL")
	private String lastName;

	@OneToOne(fetch = FetchType.EAGER )
	@JoinColumn(name="ADDRESS_TYPE")
	private MasterData addressType;

	@Column(name = "IS_DEFAULT", columnDefinition = "VARCHAR(20) NULL")
	private Boolean isDefault = false;
	
	@Column(name = "IS_DELETED", columnDefinition = "VARCHAR(20) NULL")
	private Boolean isDeleted = false;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public CityModel getCityId() {
		return cityId;
	}

	public void setCityId(CityModel cityId) {
		this.cityId = cityId;
	}

	public StateModel getStateId() {
		return stateId;
	}

	public void setStateId(StateModel stateId) {
		this.stateId = stateId;
	}

	public CountryModel getCountryId() {
		return countryId;
	}

	public void setCountryId(CountryModel countryId) {
		this.countryId = countryId;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Long getWineryId() {
		return wineryId;
	}

	public void setWineryId(Long wineryId) {
		this.wineryId = wineryId;
	}

	public Long getImporterId() {
		return importerId;
	}

	public void setImporterId(Long importerId) {
		this.importerId = importerId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	

	public MasterData getAddressType() {
		return addressType;
	}

	public void setAddressType(MasterData addressType) {
		this.addressType = addressType;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
