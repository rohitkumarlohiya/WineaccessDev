package com.wineaccess.data.model.user;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import com.wineaccess.application.constants.NamedQueryConstants;
import com.wineaccess.constants.EnumTypes.UserType;
import com.wineaccess.data.model.EntityListener;

/**
 * @author jyoti.yadav@globallogic.com
 */
@Entity
@Table(name = "USERS")
@EntityListeners(EntityListener.class)
@NamedQueries({
		@NamedQuery(name = "getByUserNamePassword", query = "from UserModel p where p.email = :email and p.password = :password and (isDeleted = false or isDeleted is null)"),
		@NamedQuery(name = "getAll", query = "from UserModel"),
		@NamedQuery(name = NamedQueryConstants.GET_BY_USER_NAME, query = "from UserModel um where um.email = :email "),
		@NamedQuery(name = NamedQueryConstants.FIND_ALL_USERS_BY_IDS, query = "from UserModel um where um.id IN :idsList"),
		@NamedQuery(name = NamedQueryConstants.MODIFY_STATUS_BULK, query = "UPDATE UserModel um set um.isEnabled=:status where um.id IN :idsList and (isDeleted = false or isDeleted is null)"),
		@NamedQuery(name = NamedQueryConstants.BULK_DELETE_USERS, query = "UPDATE UserModel um set um.isDeleted=true where um.id IN :idsList and (isDeleted = false or isDeleted is null)"),
		@NamedQuery(name = "UserModel.getByUserId", query = "from UserModel u where u.id = :id and (u.isDeleted = false or u.isDeleted is null)"),
		@NamedQuery(name = NamedQueryConstants.GET_BY_USER_ID_DELETED, query = "from UserModel u where u.id = :id"),
		@NamedQuery(name = NamedQueryConstants.FIND_USER_NEWSLETTER_RESPONSYS, query = " select email from UserModel where isReceivedNewLetter=true and emailType is not null and isEnabled=true and (isDeleted = false or isDeleted is null) "),
		@NamedQuery(name = NamedQueryConstants.GET_USER_BY_ACTIVATION_CODE, query = "from UserModel u where u.userActivationCode = :userActivationCode"),
		@NamedQuery(name = NamedQueryConstants.GET_USER_BY_ID, query = "from UserModel u where u.id = :id"),
})
@NamedNativeQuery(name = "getByRoleId", query = "select * from USERS user where user.ID in(select USER_ID from USER_ROLE where ROLE_ID = :roleId)", resultClass = UserModel.class)
@Indexed
public class UserModel extends Persistent {

	private static final long serialVersionUID = 2239700425733281031L;

	@Id
	@Column(name = "ID", columnDefinition = "BIGINT(20) UNSIGNED")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Field(name = "firstName", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "FIRST_NAME", columnDefinition = "VARCHAR(255) NULL")
	private String firstName;

	@Field(name = "lastName", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "LAST_NAME", columnDefinition = "VARCHAR(255) NULL")
	private String lastName;

	@Field(name = "email", analyze = Analyze.NO, store = Store.NO)
	@Column(name = "EMAIL", nullable = false, columnDefinition = "VARCHAR(255) ")
	private String email;

	@Column(name = "PASSWORD", columnDefinition = "VARCHAR(100) NULL")
	private String password;

	@Column(name = "IS_ENABLED", columnDefinition = "TINYINT(1) NULL")
	private Boolean isEnabled = true;

	@Field(name = "isReceivedNewLetter", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "IS_RECIEVED_NEWS_LETTER", columnDefinition = "TINYINT(1) NULL")
	private Boolean isReceivedNewLetter;

	@IndexedEmbedded
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private Set<UserRoles> userRoles = new HashSet<UserRoles>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USER_EMAIL_PREFERENCES", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "NEWS_LETTER_ID") })
	private Set<NewsLetter> newsletters = new HashSet<NewsLetter>();

	@Field(name = "isDeleted", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "IS_DELETED", columnDefinition = "TINYINT(1) NULL")
	private Boolean isDeleted = false;

	@Column(name = "SALUTATION", columnDefinition = "VARCHAR(55) NULL")
	private String salutation;

	@Column(name = "GENDER", columnDefinition = "VARCHAR(45) NULL")
	private String gender;

	@Column(name = "USER_ACTIVATION_CODE", columnDefinition = "VARCHAR(100) NULL")
	private String userActivationCode;

	@Column(name = "USER_REFERRAL_SOURCE_ID", columnDefinition = "BIGINT(20) UNSIGNED NULL")
	private Long userRefreralSourceId;

	@Column(name = "ZIPCODE", columnDefinition = "VARCHAR(15) NULL")
	// private Long userLegecyDataId;
	private String zipCode;

	@Column(name = "USER_LEGACY_DATA_ID", columnDefinition = "BIGINT(20) NULL")
	// private int zipCode;
	private Long userLegecyDataId;

	@Field(name = "stateId", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "STATE_ID", columnDefinition = "BIGINT(20) UNSIGNED NULL")
	private Long stateId;
	
	@IndexedEmbedded
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="STATE_ID",insertable = false, updatable = false)
	private StateModel stateModel;

	@Column(name = "COUNTRY_ID", columnDefinition = "BIGINT(20) UNSIGNED NULL")
	private Long countryId;

	@Column(name = "FAILURE_COUNT", columnDefinition = "INT(2) NULL")
	private int failureCount;

	@Column(name = "IS_STORE_SIGN_UP_USER", columnDefinition = "TINYINT(1) NULL")
	private Boolean isStoreSignupUser;

	@Column(name = "REG_SOURCE", columnDefinition = "VARCHAR(20)", nullable = true)
	private String regSource;

	@Column(name = "EMAIL_TYPE", columnDefinition = "VARCHAR(20)", nullable = true)
	private String emailType;

	@Column(name = "RESET_CODE", columnDefinition = "VARCHAR(100) NULL")
	private String resetCode;
	
	@Column(name = "CHANNEL_ID")
	private Long channelId;

	public Boolean getIsReceivedNewLetter() {
		return isReceivedNewLetter;
	}

	public void setIsReceivedNewLetter(Boolean isReceivedNewLetter) {
		this.isReceivedNewLetter = isReceivedNewLetter;
	}

	public Boolean getIsStoreSignupUser() {
		return isStoreSignupUser;
	}

	public void setIsStoreSignupUser(Boolean isStoreSignupUser) {
		this.isStoreSignupUser = isStoreSignupUser;
	}

	public Set<UserRoles> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRoles> userRoles) {
		this.userRoles = userRoles;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setRevenue(Float revenue) {
		this.revenue = revenue;
	}

	@Column(name = "IS_REGISTERED", columnDefinition = "TINYINT(1) NULL")
	private Boolean registered= false;

	@Column(name = "PHONE", columnDefinition = "VARCHAR(20) NULL")
	private String phone;

	@Field(name = "regStatus", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "REG_STATUS", columnDefinition = "VARCHAR(20)", nullable = true)
	private String regStatus = "Full";

	@Enumerated(EnumType.STRING)
	@Field(name = "userType", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "USER_TYPE", columnDefinition = "VARCHAR(20)", nullable = true)
	private UserType userType = UserType.NonBuyers;

	@Field(name = "registrationDate", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "REG_DATE", columnDefinition = "DATETIME DEFAULT NULL")
	@Temporal(TemporalType.TIMESTAMP)
	private Date registrationDate = new Date();

	@Field(name = "revenue", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "REVENUE", columnDefinition = "Decimal(10,2) default '0.00'", nullable = true)
	private Float revenue = 0.00f;

	// @ManyToMany

	// @JoinTable(name="USER_ROLE",joinColumns={@JoinColumn(name="user_id")},inverseJoinColumns={@JoinColumn(name="role_id")})
	// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	// private Set<UserRoles> roles;

	@ManyToMany(fetch = FetchType.EAGER, cascade = {})
	@JoinTable(name = "GROUP_USER", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID"))
	private Set<UserGroup> userGroups = new HashSet<UserGroup>();

	
	public Boolean getRegistered() {
		return registered;
	}

	public void setRegistered(Boolean registered) {
		this.registered = registered;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Boolean isReceivedNewLetter() {
		return isReceivedNewLetter;
	}

	public void setReceivedNewLetter(Boolean isReceivedNewLetter) {
		this.isReceivedNewLetter = isReceivedNewLetter;
	}

	public String getUserActivationCode() {
		return userActivationCode;
	}

	public void setUserActivationCode(String userActivationCode) {
		this.userActivationCode = userActivationCode;
	}

	public Long getUserRefreralSourceId() {
		return userRefreralSourceId;
	}

	public void setUserRefreralSourceId(Long userRefreralSourceId) {
		this.userRefreralSourceId = userRefreralSourceId;
	}

	public Long getUserLegecyDataId() {
		return userLegecyDataId;
	}

	public void setUserLegecyDataId(Long userLegecyDataId) {
		this.userLegecyDataId = userLegecyDataId;
	}

	// public int getZipCode() {
	public String getZipCode() {
		return zipCode;
	}

	// public void setZipCode(int zipCode) {
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public int getFailureCount() {
		return failureCount;
	}

	public void setFailureCount(int failureCount) {
		this.failureCount = failureCount;
	}

	public Boolean isStoreSignupUser() {
		return isStoreSignupUser;
	}

	public void setStoreSignupUser(Boolean isStoreSignupUser) {
		this.isStoreSignupUser = isStoreSignupUser;
	}

	public Set<UserGroup> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(Set<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public Boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Boolean isIsDeleted() {
		return isDeleted;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Boolean isIsEnabled() {
		return isEnabled;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRegStatus() {
		return regStatus;
	}

	public void setRegStatus(String regStatus) {
		this.regStatus = regStatus;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public float getRevenue() {
		return revenue;
	}

	public void setRevenue(float revenue) {
		this.revenue = revenue;
	}

	public String getRegSource() {
		return regSource;
	}

	public void setRegSource(String regSource) {
		this.regSource = regSource;
	}

	public String getEmailType() {
		return emailType;

	}

	public void setEmailType(String emailType) {
		this.emailType = emailType;

	}

	public String getResetCode() {
		return resetCode;
	}

	public void setResetCode(String resetCode) {
		this.resetCode = resetCode;
	}

	public Set<NewsLetter> getNewsletters() {
		return newsletters;
	}

	public void setNewsletters(Set<NewsLetter> newsletters) {
		this.newsletters = newsletters;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public StateModel getStateModel() {
		return stateModel;
	}

	public void setStateModel(StateModel stateModel) {
		this.stateModel = stateModel;
	}
}