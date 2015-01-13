/**
 * 
 */
package com.wineaccess.registration;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author gaurav.agarwal1
 * 
 */
@XmlRootElement
public class SignupVO implements Serializable {

	private static final long serialVersionUID = 5385635682659975741L;
	private Long id;
	private String salutation;
	private String firstName;
	private String lastName;
	private String email;
	private String zipCode;
	private Long countryId;
	private boolean isReceivedNewsletter;
	private Boolean existsButDeleted;
	private String activationURL;
	private String activationCode;
	

	public SignupVO(){
		
	}
	
	public SignupVO(Long id, String salutation,String firstName,String lastName,String email, String zipCode,Long countryId,boolean isReceivedNewsletter){
		this.id = id;
		this.salutation = salutation;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.zipCode = zipCode;
		this.countryId = countryId;
		this.isReceivedNewsletter = isReceivedNewsletter;
	}
	
	public SignupVO(Long id, String salutation,String firstName,String lastName,String email, String zipCode,Long countryId,boolean isReceivedNewsletter,String activationURL,String activationCode){
		this.id = id;
		this.salutation = salutation;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.zipCode = zipCode;
		this.countryId = countryId;
		this.isReceivedNewsletter = isReceivedNewsletter;
		this.activationURL = activationURL;
		this.activationCode = activationCode;
	}
	
	public SignupVO(Long id, Boolean  existsButDeleted ){
		this.id = id;
		this.existsButDeleted = existsButDeleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
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

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public boolean isReceivedNewsletter() {
		return isReceivedNewsletter;
	}

	public void setReceivedNewsletter(boolean isReceivedNewsletter) {
		this.isReceivedNewsletter = isReceivedNewsletter;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getExistsButDeleted() {
		return existsButDeleted;
	}

	public void setExistsButDeleted(Boolean existsButDeleted) {
		this.existsButDeleted = existsButDeleted;
	}

	public String getActivationURL() {
		return activationURL;
	}

	public void setActivationURL(String activationURL) {
		this.activationURL = activationURL;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
	
	

}
