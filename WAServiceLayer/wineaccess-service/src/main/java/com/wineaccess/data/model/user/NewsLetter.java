package com.wineaccess.data.model.user;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.wineaccess.data.model.EntityListener;

/**
 * @author jyoti.yadav@globallogic.com
 */

@Entity
@Table(name = "NEWS_LETTER")
@EntityListeners(EntityListener.class)
@NamedQueries({
		@NamedQuery(name = "NewsLetter.getAll", query = "from NewsLetter"),
		@NamedQuery(name = "NewsLetter.getById", query = "from NewsLetter c where c.id = :newsletterId"),
		@NamedQuery(name = "NewsLetter.getByName", query = "from NewsLetter c where c.name = :name")

})
public class NewsLetter extends Persistent {

	private static final long serialVersionUID = 2239700425733281031L;

	@Id
	@Column(name = "ID", columnDefinition = "BIGINT(20) UNSIGNED ")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME", columnDefinition = "VARCHAR(45) NOT NULL",unique=true)
	private String name;

	@Column(name = "WEB_NAME", columnDefinition = "VARCHAR(45) NULL")
	private String webName;

	@Column(name = "EFF_DATE", columnDefinition = "DATETIME DEFAULT NULL")
	private Date effDate;

	@Column(name = "END_DATE", columnDefinition = "DATETIME DEFAULT NULL")
	private Date endDate;

	@Column(name = "EMAIL_SUBJECT", columnDefinition = "VARCHAR(45) NULL")
	private String emailSubject;

	@Column(name = "FROM_EMAIL", columnDefinition = "VARCHAR(45) NULL")
	private String fromEmail;

	@Column(name = "SUBMIT_DATE", columnDefinition = "DATETIME DEFAULT NULL")
	private Date submitDate;

	@Column(name = "TITLE", columnDefinition = "VARCHAR(45) NULL")
	private String title;

	@Column(name = "IS_DELETED", columnDefinition = "TINYINT(1) NULL")
	private Boolean isDelete;

	@Column(name = "IS_DEFAULT", columnDefinition = "TINYINT(1) NULL")
	private Boolean isDefault;



	/*@OneToMany(mappedBy="newsLetterId")
	private Set<UserEmailPreferences> userEmailPref;*/
	
	@ManyToMany(mappedBy="newsletters")
    private Set<UserModel> users = new HashSet<UserModel>();
	
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

	public String getWebName() {
		return webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}

	public Date getEffDate() {
		return effDate;
	}

	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/*public Set<UserEmailPreferences> getUserEmailPref() {
		return userEmailPref;
	}

	public void setUserEmailPref(Set<UserEmailPreferences> userEmailPref) {
		this.userEmailPref = userEmailPref;
	}*/

	

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	public Set<UserModel> getUsers() {
		return users;
	}

	public void setUsers(Set<UserModel> users) {
		this.users = users;
	}

}
