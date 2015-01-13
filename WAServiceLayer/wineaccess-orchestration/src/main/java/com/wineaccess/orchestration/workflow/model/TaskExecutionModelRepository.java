package com.wineaccess.orchestration.workflow.model;


/**
 * @author jyoti.yadav@globallogic.com
 */
public class TaskExecutionModelRepository {
	
	/*public static List<TaskExecutionModel> getByRequestIdAndCurrentTask(String requestId, String currentTask) {
		GenericDAO<TaskExecutionModel> genericDao = (GenericDAO<TaskExecutionModel>)  CoreBeanFactory.getBean("genericDAO");
		return genericDao.findByNamedQuery("getByRequestIdAndCurrentTask", new String [] {"requestId", "currentTask"}, requestId, currentTask);
	}
	
	
	public static List<TaskExecutionModel> getByRequestIdAndNextTask(String requestId, String nextTask, String taskStatus) {
		GenericDAO<TaskExecutionModel> genericDao = (GenericDAO<TaskExecutionModel>)  CoreBeanFactory.getBean("genericDAO");
		return genericDao.findByNamedQuery("getByRequestIdAndNextTask", new String [] {"requestId", "nextTask", "taskStatus"}, requestId, nextTask, taskStatus);
	}
	
	public static void saveAll(Collection<TaskExecutionModel> taskExecutionModels) {
		GenericDAO<TaskExecutionModel> genericDao = (GenericDAO<TaskExecutionModel>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.saveAll(taskExecutionModels);
	}
	
	public static void update(TaskExecutionModel taskExecutionModel) {
		GenericDAO<TaskExecutionModel> genericDao = (GenericDAO<TaskExecutionModel>)  CoreBeanFactory.getBean("genericDAO");
		genericDao.update(taskExecutionModel);
	}*/
}
