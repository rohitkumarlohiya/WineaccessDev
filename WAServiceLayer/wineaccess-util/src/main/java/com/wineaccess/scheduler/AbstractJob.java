package com.wineaccess.scheduler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 
 * @author jyoti.yadav@globallogic.com
 */
public abstract class AbstractJob implements Job {
	
	private static Log logger = LogFactory.getLog(AbstractJob.class);
	
	/**
	 * The Unique ETL Job Id.
	 * @return
	 */
	public abstract int getJobId();
	
	/**
	 * The unique name of the ETL Job.
	 * @return
	 */
	public abstract String getJobName();

	/**
	 * @see Job#execute(JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext jobContext) throws JobExecutionException {
		JobExecContext jobExecutionContext = new JobExecContext(jobContext);
		try {
			execute(jobExecutionContext);
		} catch (Exception e) {
			logger.error("Error executing JOB- " + jobExecutionContext.getName(), e);
		}
	}
	
	/**
	 * <p>
	 * Implementors of the Jobs must provide this method implementation, which
	 * will contain the logic which will be executed at the scheduled time
	 * interval.
	 * </p>
	 * 
	 * @param jobContext
	 *            jobContext containing the jobParameters set while scheduling
	 *            the Job
	 * @throws BusinessException
	 *             Any exception thrown while executing the job.
	 * 
	 */
	public abstract void execute(JobExecContext jobContext) throws Exception;
}
