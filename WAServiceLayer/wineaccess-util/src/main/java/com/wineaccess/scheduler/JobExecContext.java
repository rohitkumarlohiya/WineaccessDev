package com.wineaccess.scheduler;

import java.io.Serializable;

import org.quartz.JobExecutionContext;


/**
 * <p>
 * A context bundle containing handles to various environment information, that
 * is given to a Job instance as it is executed.
 * </p>
 * 
 * @author jyoti.yadav@globallogic.com
 */
@SuppressWarnings("serial")
public class JobExecContext implements Serializable {
	
	private JobExecutionContext jobExecutionContext;

	protected JobExecContext(JobExecutionContext jobContext) {
		this.jobExecutionContext = jobContext;
	}

	/**
	 * Mehod to retrieve any parameter (set while scheduling the Job).
	 * 
	 * @param key :
	 *            key.
	 */
	public Object get(Object key) {
		return jobExecutionContext.getMergedJobDataMap().get(key);
	}

	/**
	 * Method to set any parameter which might be needed by the JobInstance
	 * while its execution.
	 * 
	 * @param key :
	 *            key.
	 * @param value :
	 *            value.
	 */
	public void put(Object key, Object value) {
		jobExecutionContext.put(key, value);
	}

	/**
	 * Returns the name of the Job.
	 */
	public String getName() {
		return String.valueOf(get(JobConstants.SchedulerService.JOB_NAME));
	}

	/**
	 * Returns the name of the JobGroup.
	 */
	public String getGroup() {
		return String.valueOf(get(JobConstants.SchedulerService.JOB_GROUP));
	}
	
	public int getJobIdentifier() {
		return Integer.parseInt(String.valueOf(get(JobConstants.SchedulerService.JOB_IDENTIFIER)));
	}
	
	public int getId() {
		return Integer.parseInt(String.valueOf(get(JobConstants.SchedulerService.ID)));
	}
	
	public String getScheduleName() {
		return String.valueOf(get(JobConstants.SchedulerService.SCHEDULE_NAME));
	}
}
