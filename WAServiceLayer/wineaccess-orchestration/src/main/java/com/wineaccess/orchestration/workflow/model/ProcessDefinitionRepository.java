package com.wineaccess.orchestration.workflow.model;

import java.util.List;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.persistence.dao.GenericDAO;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class ProcessDefinitionRepository {
	
	public static List<ProcessDefinitionModel> getByNameAndVersion(String processName, String version) {
		GenericDAO<ProcessDefinitionModel> genericDao = (GenericDAO<ProcessDefinitionModel>)  CoreBeanFactory.getBean("genericDAO");
		return genericDao.findByNamedQuery("getByNameAndVersion", new String [] {"processName", "version"}, processName, version);
	}
	
	
	public static void create(ProcessDefinitionModel processDefinitionModel) {
		GenericDAO<ProcessDefinitionModel> genericDao = (GenericDAO<ProcessDefinitionModel>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.create(processDefinitionModel);
	}
	
	public static List<ProcessDefinitionModel> getAll() {
		GenericDAO<ProcessDefinitionModel> genericDao = (GenericDAO<ProcessDefinitionModel>)  CoreBeanFactory.getBean("genericDAO");
		return genericDao.findByNamedQuery("getAllProcessDefinition", null, null);
	}
}
