package com.wineaccess.orchestration.workflow.process.task;

import com.wineaccess.orchestration.workflow.process.context.ProcessContext;

/**
 * A {@link Task} represents a single unit of processing to be performed on the  data associated with 
 * the {@link ProcessContext}. This unit of task is a transactional unit of work which is responsible 
 * for modifying the state/data associated with the {@link ProcessContext}.This interface will exposed the
 * methods to validate and execute the task.
 * 
 * @author jyoti.yadav@globallogic.com
 */
public interface Task {
	
	/**
	 * <p>
	 * Execute a unit of processing work to be performed by the task. This also
	 * involves generation of various transitions( as defined by its
	 * getTransitions() method) at the end of successful execution of this task.
	 * </p>
	 * 
	 * This method is invoked if the task has published a desire to act on
	 * Single objects.
	 * 
	 * @param pContext :
	 *            process context.
	 * @throws BusinessException -
	 *             if we can not able to execute the task, such as e.g process
	 *             context has invalid data, task is not initialized properly
	 *             etc.
	 */
	public void execute(ProcessContext pContext) throws Exception;
}
