package com.wineaccess.warehouse;

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

@Entity
@Table(name = "WAREHOUSE")
@EntityListeners(EntityListener.class)
@NamedQueries({ @NamedQuery(name = "WarehouseModel.getById", query = "from WarehouseModel where id = :id"),
	@NamedQuery(name = "WarehouseModel.getByName", query = "from WarehouseModel where name = :name"),
	@NamedQuery(name = "WarehouseModel.getNonDeletedWarehouseById", query = "from WarehouseModel where id = :id and isDeleted = false")
})
public class WarehouseModel extends Persistent {

	private static final long serialVersionUID = 5550064054520579887L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME")
	private String name;

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

	@Column(name = "ZIPCODE", columnDefinition = "VARCHAR(15) NULL")
	private String zipcode;

	@Column(name = "PHONE", columnDefinition = "VARCHAR(20) NULL")
	private String phone;

	@Column(name = "IS_DELETED", columnDefinition = "TINYINT(1) NULL")
	private Boolean isDeleted = false;

	@Column(name = "EMAIL_ID", columnDefinition = "VARCHAR(255) NOT NULL")
	private String emailId;

	@Column(name = "FAX_NUMBER", columnDefinition = "VARCHAR(50) NULL")
	private String faxNumber;

	@Column(name = "FIRST_NAME", columnDefinition = "VARCHAR(255) NOT NULL")
	private String firstName;

	@Column(name = "LAST_NAME", columnDefinition = "VARCHAR(255) NOT NULL")
	private String lastName;
	
	@Column(name = "UNIQUE_ADDRESS_HASH", columnDefinition = "VARCHAR(255) NOT NULL")
	private String uniqueAddressHash;
	
	@OneToOne
	@JoinColumn(name = "FREIGHT_REGION")
	private MasterData freightRegion;
	
	@Column(name = "IS_NON_WS_TRANSPORT_CARRIER", columnDefinition = "TINYINT(1) NOT NULL")
	private Boolean isNonWSTransportCarrier;
		
	@ManyToOne
	@JoinColumn(name = "CARRIER")
	private MasterData carrier;
	
	public MasterData getCarrier() {
		return carrier;
	}

	public void setCarrier(MasterData carrier) {
		this.carrier = carrier;
	}

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

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
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

	public String getUniqueAddressHash() {
		return uniqueAddressHash;
	}

	public void setUniqueAddressHash(String uniqueAddressHash) {
		this.uniqueAddressHash = uniqueAddressHash;
	}

	public MasterData getFreightRegion() {
		return freightRegion;
	}

	public void setFreightRegion(MasterData freightRegion) {
		this.freightRegion = freightRegion;
	}

	public Boolean getIsNonWSTransportCarrier() {
		return isNonWSTransportCarrier;
	}

	public void setIsNonWSTransportCarrier(Boolean isWineShippingAddress) {
		this.isNonWSTransportCarrier = isWineShippingAddress;
	}

}
