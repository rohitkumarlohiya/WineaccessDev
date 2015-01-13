/**
 * 
 */
package com.wineaccess.emailtemplate;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.google.gson.annotations.SerializedName;
import com.wineaccess.common.JaxbDateSerializer;
import com.wineaccess.user.activity.log.UserServiceModel;

/**
 * @author anurag.jain3
 *
 */
/**
 * @author anurag.jain3
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"id", "name","from", "subject","userServiceModel","modifiedDate"})
@XmlSeeAlso(UserServiceModel.class)
public class EmailTemplate implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1139999635134990368L;
	Long id;
	String name;
	@XmlElement(name = "fromEmail")
	@SerializedName("fromEmail")
	String from;
	String subject;
	
	@XmlElement(name = "modifiedBy")
	UserServiceModel userServiceModel;

	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date modifiedDate;
	
	public EmailTemplate(){
		super();
	}
	
	public EmailTemplate(Long id,String name, String from, String subject, Date modifiedDate){
		this();
		this.name = name;
		this.from = from;
		this.subject = subject;
		this.id = id;
		this.modifiedDate = modifiedDate;
				
				
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public UserServiceModel getUserServiceModel() {
		return userServiceModel;
	}

	public void setUserServiceModel(UserServiceModel userServiceModel) {
		this.userServiceModel = userServiceModel;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	
	
	
	
	

}
