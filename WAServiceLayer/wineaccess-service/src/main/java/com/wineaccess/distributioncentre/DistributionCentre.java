package com.wineaccess.distributioncentre;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wineaccess.data.model.EntityListener;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.user.CityModel;
import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.data.model.user.StateModel;

/**
 * @author gaurav.agarwal1
 *
 */

@Entity
@Table(name = "DISTRIBUTION_CENTRE")
@EntityListeners(EntityListener.class)
@NamedQueries({ @NamedQuery(name = "DistributionCentre.getById", query = "from DistributionCentre where id = :id and isDeleted = false"),
	@NamedQuery(name = "DistributionCentre.getByUniqueAddressHash", query = "from DistributionCentre where uniqueAddressHash = :uniqueHash"),
	@NamedQuery(name = "DistributionCentre.getByLocationId", query = "from DistributionCentre where stateId.id = :locationId")
	})

public class DistributionCentre extends Persistent{

	private static final long serialVersionUID = 6049267153524102046L;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "ADDRESS_LINE1")
	private String addressLine1;

	@Column(name = "ADDRESS_LINE2")
	private String addressLine2;

	@OneToOne(fetch = FetchType.EAGER )
	@JoinColumn(name="CITY_ID")
	private CityModel cityId;

	@OneToOne(fetch = FetchType.EAGER )
	@JoinColumn(name="STATE_ID")
	private StateModel stateId;

	@Column(name = "ZIPCODE")
	private String zipcode;

	@Column(name = "PHONE")
	private String phone;

	@Column(name = "EMAIL_ID")
	private String emailId;

	@Column(name = "FAX_NUMBER")
	private String faxNumber;
	
	@Column(name = "IS_DELETED")
	private Boolean isDeleted = false;
	
	@Column(name = "UNIQUE_ADDRESS_HASH")
	private String uniqueAddressHash;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getUniqueAddressHash() {
		return uniqueAddressHash;
	}

	public void setUniqueAddressHash(String uniqueAddressHash) {
		this.uniqueAddressHash = uniqueAddressHash;
	}

}
