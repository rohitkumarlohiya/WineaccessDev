package com.wineaccess.data.model.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author jyoti.yadav@globallogic.com
 */
// @Entity
// @Table(name = "USER_ACTIVITY_LOG")
// @EntityListeners(EntityListener.class)
// @NamedQueries({ @NamedQuery(name = "UserActivityLogs.getActivityLogs", query
// = "from UserActivityLogs p where p.userSessionId = :token")})
@Document(collection = "AUDIT_LOG")
public class UserActivityLogs extends Persistent {

	private static final long serialVersionUID = -8706476042306947710L;

	@Id
	private String id;

	private Long userId;

	private String activity;

	private Long userSessionId;

	private String eventName;

	public UserActivityLogs() {
	}

	public UserActivityLogs(Long userId, String activiti, Long userSessionId) {
		this.userId = userId;
		this.activity = activiti;
		this.userSessionId = userSessionId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public Long getUserSessionId() {
		return userSessionId;
	}

	public void setUserSessionId(Long userSessionId) {
		this.userSessionId = userSessionId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
}
