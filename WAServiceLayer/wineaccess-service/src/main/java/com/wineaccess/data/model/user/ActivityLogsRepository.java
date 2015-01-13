package com.wineaccess.data.model.user;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.persistence.dao.GenericDAO;


public class ActivityLogsRepository {
	
	private static MongoTemplate mongoTemplate = (MongoTemplate)  CoreBeanFactory.getBean("mongoTemplate");
	
	public static List<UserActivityLogs> getSessionActivityLogs(Long sessionId, int offset, int limit) {
		GenericDAO<UserActivityLogs> genericDao = (GenericDAO<UserActivityLogs>)  CoreBeanFactory.getBean("genericDAO");
		Query q = new Query();
		q.addCriteria(Criteria.where("userSessionId").is(sessionId));
		q.limit(limit);
		q.skip(offset);
		return mongoTemplate.find(q, UserActivityLogs.class);
	}
	
	public static List<UserActivityLogs> getSessionActivityLogs(Long sessionId) {
		GenericDAO<UserActivityLogs> genericDao = (GenericDAO<UserActivityLogs>)  CoreBeanFactory.getBean("genericDAO");
		Query q = new Query();
		q.addCriteria(Criteria.where("userSessionId").is(sessionId));
		return mongoTemplate.find(q, UserActivityLogs.class);
	}
}
