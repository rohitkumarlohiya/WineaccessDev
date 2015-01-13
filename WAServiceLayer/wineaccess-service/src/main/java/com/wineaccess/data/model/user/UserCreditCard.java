package com.wineaccess.data.model.user;

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
import javax.persistence.Transient;

import com.wineaccess.application.constants.NamedQueryConstants;
import com.wineaccess.data.model.EntityListener;
import com.wineaccess.data.model.common.MasterData;

/**
 * @author jyoti.yadav@globallogic.com
 */
@Entity
@Table(name = "USER_CREDIT_CARD")
@EntityListeners(EntityListener.class)
@NamedQueries({
	@NamedQuery(name = NamedQueryConstants.FIND_CC_BY_USERNAME, query = "from UserCreditCard ucc, UserAddress ua  where ucc.userAddressId=ua.id and ucc.userId=:userId"),
	@NamedQuery(name = NamedQueryConstants.FIND_CC_BY_USER_ID_CC_ID, query = "from UserCreditCard ucc, UserAddress ua,AddressPreference ap where ucc.userAddressId=ua.id and ucc.userId=:userId and ua.addressPreferenceId=ap.id and ucc.id = :creditCardId"),
	@NamedQuery(name = NamedQueryConstants.FIND_CC_IDS_BY_USER_ID, query = "select id from UserCreditCard ucc where ucc.userId=:userId"),
	@NamedQuery(name = NamedQueryConstants.UPDATE_USER_IDS_CC, query = "update UserCreditCard ucc set userId= :userId where ucc.id IN :idsList"),
	@NamedQuery(name = NamedQueryConstants.FIND_BY_USERID_BRAINTREEID, query = "from UserCreditCard ucc where ucc.userId=:userId and ucc.braintreeUid=:braintreeUid"),
	@NamedQuery(name = NamedQueryConstants.FIND_BY_USERID_DEFAULT_CC, query = "from UserCreditCard ucc where ucc.userId=:userId and ucc.isDefault=true"),
	@NamedQuery(name = NamedQueryConstants.FIND_CC_LIST_BY_USERID, query = "from UserCreditCard ucc  where ucc.userId=:userId"),
	@NamedQuery(name = NamedQueryConstants.FIND_CC_LIST_BY_USERID_ADDRESSID, query = "from UserCreditCard ucc  where ucc.userId=:userId AND ucc.userAddressId in :addressList")
	
	})
public class UserCreditCard extends Persistent {
	
	private static final long serialVersionUID = 2239700425733281031L;

	@Id
	@Column(name = "ID", columnDefinition = "BIGINT(20) UNSIGNED ")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "USER_ID", columnDefinition = "BIGINT(20) UNSIGNED NOT NULL")
	private Long userId;
	
	@Column(name = "CARD_HOLDER_NAME", columnDefinition = "VARCHAR(255) NULL")
	private String cardHolderName;
	
	
	@ManyToOne
	@JoinColumn(name = "CREDIT_CARD_TYPE", columnDefinition = "BIGINT(20) UNSIGNED")
	private MasterData creditCardType;
	
	@Column(name = "EXPIRATION_DATE", columnDefinition = "VARCHAR(45) NULL")
	private String expirationDate;
	
	@Column(name = "PG_PROFILE_ID", columnDefinition = "VARCHAR(45) NULL")
	private String pgProfile;
	
	@Column(name = "USER_ADDRESS_ID", columnDefinition = "BIGINT(20) UNSIGNED NOT NULL")
	private Long userAddressId;
	
	@Column(name = "IS_DEFAULT", columnDefinition = "TINYINT(1) NULL")
	private Boolean isDefault=false;
	
	@Column(name = "BRAINTREE_UID", columnDefinition = "VARCHAR(50) NULL")
	private String braintreeUid;

	@Transient 
	private UserAddress userAddress;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	
	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public MasterData getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(MasterData creditCardType) {
		this.creditCardType = creditCardType;
	}


	public String getPgProfile() {
		return pgProfile;
	}

	public void setPgProfile(String pgProfile) {
		this.pgProfile = pgProfile;
	}

	public Long getUserAddressId() {
		return userAddressId;
	}

	public void setUserAddressId(Long userAddressId) {
		this.userAddressId = userAddressId;
	}
	
	public UserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public String getBraintreeUid() {
		return braintreeUid;
	}

	public void setBraintreeUid(String braintreeUid) {
		this.braintreeUid = braintreeUid;
	}
	
}
