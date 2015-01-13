package com.wineaccess.data.model.security;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.wineaccess.data.model.EntityListener;
import com.wineaccess.data.model.user.Persistent;

/**
 * @author jyoti.yadav@globallogic.com
 */
@Entity
@Table(name = "USER_SESSION")
@EntityListeners(EntityListener.class)
@NamedQueries({ @NamedQuery(name = "getByToken", query = "from TokenModel p where p.token = :token"),
@NamedQuery(name = "getByUserId" , query = "from TokenModel p where p.userid = :userId"),
@NamedQuery(name = "TokenModel.getUserAndSession", query = "from UserModel u, TokenModel p where u.id=p.userid"),
@NamedQuery(name = "TokenModel.getUserAndSessionSummary", query = "select t.userid,count(t.token),max(t.sessionStartTime), max(t.sessionEndTime) from TokenModel t group by t.userid"),
@NamedQuery(name="TokenModel.getCountOfTotalRecords", query="select count(distinct userid) from TokenModel"),
@NamedQuery(name="TokenModel.sortBySessionStartTime", query="from TokenModel p where p.userid = :userId order by p.sessionStartTime desc"),
@NamedQuery(name="TokenModel.getUserOtherSessions", query="from TokenModel p where p.userid = :userId and p.token <> :token"),
@NamedQuery(name="TokenModel.getUserSessions", query="from TokenModel p where p.userid = :userId and p.id = :id order by p.sessionStartTime desc"),
@NamedQuery(name="TokenModel.loggedInUserList", query="select userid,count(*) from TokenModel where userid  IN :idsList and SESSION_END_TIME is  null group by userid"), 
@NamedQuery(name="TokenModel.recoverToken", query="from TokenModel where sessionEndTime is NULL")
})
public class TokenModel extends Persistent {

	private static final long serialVersionUID = -8402083815091633047L;

	@Id
	@Column(name = "ID", columnDefinition = "BIGINT(20) UNSIGNED")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "TOKEN" , columnDefinition = "VARCHAR(255) NULL")
	private String token;

	@Column(name = "USER_ID", columnDefinition = "BIGINT(20) UNSIGNED")
	private Long userid;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SESSION_START_TIME", nullable=false)
	private Date sessionStartTime;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SESSION_END_TIME", nullable=true)
	private Date sessionEndTime;

	@Column(name = "IP_ADDRESS", columnDefinition = "VARCHAR(255) NULL")
	private String ipAddress;

	@Column(name = "BROWSER", columnDefinition = "VARCHAR(255) NULL")
	private String browser;

	@Column(name = "OPERATING_SYSTEM", columnDefinition = "VARCHAR(255) NULL")
	private String operatingSystem;

	@Column(name = "PLATFORM_DEVICE", columnDefinition = "VARCHAR(255) NULL")
	private String platformDevice;

	public TokenModel() {
	}
	
	public TokenModel(String token, Long userId, Date sessionStartTime) {
		this.userid = userId;
		this.token = token;
		this.sessionStartTime = sessionStartTime;
	}

	public TokenModel(String token, Long userId, Date sessionStartTime, String browser, String operatingSystem, String platformDevice, String ipAddress) {
		this.userid = userId;
		this.token = token;
		this.sessionStartTime = sessionStartTime;
		this.ipAddress = ipAddress;
		this.operatingSystem = operatingSystem;
		this.platformDevice = platformDevice;
		this.browser = browser;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getSessionStartTime() {
		return sessionStartTime;
	}

	public void setSessionStartTime(Date sessionStartTime) {
		this.sessionStartTime = sessionStartTime;
	}

	public Date getSessionEndTime() {
		return sessionEndTime;
	}

	public void setSessionEndTime(Date sessionEndTime) {
		this.sessionEndTime = sessionEndTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getPlatformDevice() {
		return platformDevice;
	}

	public void setPlatformDevice(String platformDevice) {
		this.platformDevice = platformDevice;
	}

	
}
