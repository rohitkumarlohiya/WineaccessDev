package com.wineaccess.commad.search.users;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.data.model.user.UserModel;

@XmlRootElement
public class UserModelVO implements Serializable {

	private Long id;

	private String firstName;

	private String lastName;

	private String email;

	private String salutation;

	private String gender;
	
	private String zipCode;
	
	private Long stateId;
	
	private String stateCode;
	
	private String stateName;
	
	private Long countryId;

	private int failureCount;	
	
	private boolean isEnabled;
	@XmlElement(name="receivedNewsLetter")
	private boolean isReceivedNewLetter;
	
	private boolean isRegistered;

	private boolean isStoreSignupUser;
	
	
	/*private String password;	
	
	private String userActivationCode;

	private Long userRefreralSourceId;

	private Long userLegecyDataId;*/

	
	private OrderModelVO orderDetail = new OrderModelVO();

	
	

	/*private Set<UserRolesVO> userRoles = new HashSet<UserRolesVO>();
	
	private Set<UserGroupsVO> userGroups = new HashSet<UserGroupsVO>();*/
	
	public OrderModelVO getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderModelVO orderDetail) {
		this.orderDetail = orderDetail;
	}

	public UserModelVO(){
	}
	
	public UserModelVO(UserModel userModel){
		this.id = userModel.getId();
		this.firstName =  userModel.getFirstName();
		this.lastName = userModel.getLastName();
		this.email = userModel.getEmail();
		//this.password = userModel.getPassword();
		this.isEnabled = userModel.isEnabled();
		this.isReceivedNewLetter = userModel.isReceivedNewLetter();
		this.salutation = userModel.getSalutation();
		this.gender  = userModel.getGender();
		//this.userActivationCode = userModel.getUserActivationCode();
		//this.userRefreralSourceId = userModel.getUserRefreralSourceId();
		this.zipCode = userModel.getZipCode();
		//this.userLegecyDataId = userModel.getUserLegecyDataId();
		this.stateId = userModel.getStateId();
		this.countryId  = userModel.getCountryId();
		this.failureCount = userModel.getFailureCount();
		this.isStoreSignupUser = userModel.isStoreSignupUser();
		this.isRegistered = userModel.getRegistered();	
		this.stateCode = userModel.getStateModel().getStateCode();
		this.stateName = userModel.getStateModel().getStateName();
		
		
		/*for (UserGroup group : userModel.getUserGroups()) {
			UserGroupsVO userGroupsVO = new UserGroupsVO();
			userGroupsVO.setId(group.getId());
			userGroupsVO.setGroupName(group.getGroupName());
			userGroupsVO.setGroupDescription(group.getGroupDescription());
			//this.userGroups.add(userGroupsVO);
		}*/
		
		/*for (UserRoles role : userModel.getUserRoles()) {
			UserRolesVO userGroupsVO = new UserRolesVO();
			userGroupsVO.setId(role.getId());
			userGroupsVO.setRoleName(role.getRoleName());
			userGroupsVO.setStatus(role.isStatus());
			this.userRoles.add(userGroupsVO);
		}*/
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public boolean isReceivedNewLetter() {
		return isReceivedNewLetter;
	}

	public void setReceivedNewLetter(boolean isReceivedNewLetter) {
		this.isReceivedNewLetter = isReceivedNewLetter;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	
	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public int getFailureCount() {
		return failureCount;
	}

	public void setFailureCount(int failureCount) {
		this.failureCount = failureCount;
	}

	public boolean isStoreSignupUser() {
		return isStoreSignupUser;
	}

	public void setStoreSignupUser(boolean isStoreSignupUser) {
		this.isStoreSignupUser = isStoreSignupUser;
	}

	public boolean isRegistered() {
		return isRegistered;
	}

	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/*public Set<UserRolesVO> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRolesVO> userRoles) {
		this.userRoles = userRoles;
	}

	public Set<UserGroupsVO> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(Set<UserGroupsVO> userGroups) {
		this.userGroups = userGroups;
	}*/
	
}

