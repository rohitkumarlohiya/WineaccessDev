package com.wineaccess.orchestration.core.concurrent.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.spi.ThreadExecutor;

/**
 * ThreadPool which use {@link ThreadExecutor} as the underlying implementation
 * for managing the threads.<br>
 * This class provides an interface to associate a {@IThreadPoolPolicy} which
 * can customize the threadPool size on runtime as required.<br>
 * ThreadPool also provide the interface to handle threads which are rejected by
 * Threadpool because of unavailable pool size and queue size.
 * 
 * @author jyoti.yadav@globallogic.com
 * @see ThreadPoolExecutor
 */
public class ThreadPool {

	/**
	 * Default keep alive time for worker threads.
	 */
	public static final long DEFAULT_KEEP_ALIVE_TIME = 10;

	/**
	 * Field logger
	 */
	private static Log logger = LogFactory.getLog(ThreadPool.class);

	/**
	 * Field threadPool
	 */
	private ThreadPoolExecutor threadPool = null;

	/**
	 * Field maxQueueSize
	 */
	private int maxQueueSize;

	/**
	 * Field poolPolicy - policy for managing threadPool size
	 */
	private IThreadPoolPolicy poolPolicy = null;

	/**
	 * Field rejectionHandler - handler for handling rejected threads
	 */
	private IRejectionHandler rejectionHandler = null;

	/**
	 * <p>
	 * Constructor for ThreadPool.
	 * <p>
	 * 
	 * @param corePoolSize
	 *            corePoolSize for this ThreadPool
	 * @param maxPoolSize
	 *            maxPoolsize for this ThreadPool
	 * @param keepAliveTime
	 *            time in milliseconds
	 * @param queue
	 *            BlockingQueue which is used by this ThreadPool
	 * @param maxQueueSize
	 *            Maximum queue size

	 */
	public ThreadPool(int corePoolSize, int maxPoolSize, long keepAliveTime,
			BlockingQueue<Runnable> queue, int maxQueueSize) {
		this.maxQueueSize = maxQueueSize;
		init(corePoolSize, maxPoolSize, keepAliveTime, queue);
	}

	private void init(int corePoolSize, int maxPoolSize, long keepAliveTime,
			BlockingQueue<Runnable> queue) {
		threadPool = new ThreadPoolExecutor(corePoolSize, maxPoolSize,
				keepAliveTime, TimeUnit.SECONDS, queue);
	}

	/**
	 * <p>
	 * Executes a {@link Runnable} task in a new thread. Following rules are
	 * followed to start this thread -<br>
	 * <li> The thread is started immediately if poolSize is less than
	 * corePoolSize
	 * <li> If poolSize is greater than corePoolSize and less than maxPoolSize
	 * then this task is queued up if queue size is not full else new thread is
	 * created for this task
	 * <li> If queue is full and poolSize is equal to maxPoolSize then this task
	 * is passed to attached {@link RejectionExecutionHandler}
	 * <p>
	 * 
	 * @param task
	 *            Runnable Task to be executed
	 */
	public void execute(Runnable task) {

		if (poolPolicy != null) {
			poolPolicy.checkPolicy(this);
		}
		if (!hasCapacity()) {

			if (this.getRejectionHandler() != null) {
				this.getRejectionHandler().handleRejected(task);
				return;
			} else {
				throw new RejectedExecutionException(
						"Max Pool-Size/QueueSize reached and there exist "
								+ "no Handler for rejected Task");
			}
		}
		threadPool.execute(task);
		logger.debug("Task count.." + threadPool.getQueue().size());
	}

	/**
	 * <p>
	 * Shutdown the threads of this ThreadPool. This will wait till the
	 * currently running threads are completed.
	 * <p>
	 */
	public void shutDown() {
		threadPool.shutdown();
	}

	/**
	 * <p>
	 * Shutdown the threads of this ThreadPool. This will try to kill the
	 * currently running threads, and removes all queued threads.
	 * <p>
	 */
	public void shutDownImmediate() {
		threadPool.shutdownNow();
	}

	/**
	 * <p>
	 * Set the maximum size for this threadPool. This is the number of threads
	 * up to which threadpool can grow when the queue is full.
	 * <p>
	 * 
	 * @param maximumPoolSize
	 *            maxPoolSize for the ThreadPool
	 */
	public void setMaximumPoolSize(int maximumPoolSize) {
		threadPool.setMaximumPoolSize(maximumPoolSize);
	}

	/**
	 * <p>
	 * Set the corePool size for this threadPool. This is the number of threads
	 * up to which threadpool can grow before queuing any thread.
	 * <p>
	 * 
	 * @param corePoolSize
	 *            size for core pool threads.
	 */
	public void setCorePoolSize(int corePoolSize) {
		threadPool.setCorePoolSize(corePoolSize);
	}

	/**
	 * @return Return the current number of active threads in this ThreadPool
	 */
	public int getActiveCount() {
		return threadPool.getActiveCount();
	}

	/**
	 * @return Return the maximum number of threads which can be used by this
	 *         ThreadPool
	 */
	public int getMaximumPoolSize() {
		return threadPool.getMaximumPoolSize();
	}

	/**
	 * @return Return the core number of threads which can be used by this
	 *         ThreadPool
	 */
	public int getCorePoolSize() {
		return threadPool.getCorePoolSize();
	}

	/**
	 * @return return the size of the queue which is used by the threadPool to
	 *         queue the threads internally
	 */
	public int getQueueSize() {
		return threadPool.getQueue().size();
	}

	/**
	 * <p>
	 * Policy which is responsible for managing the poolsize of this threadpool.
	 * <p>
	 * 
	 * @param policy
	 *            policy for controlling the threadPool size
	 */
	public void setPolicy(IThreadPoolPolicy policy) {
		this.poolPolicy = policy;
	}

	/**
	 * @return the policy which is currently set for this ThreadPool
	 */
	public IThreadPoolPolicy getPolicy() {
		return this.poolPolicy;
	}

	/**
	 * @return Returns the rejectionHandler.
	 */
	public IRejectionHandler getRejectionHandler() {
		return rejectionHandler;
	}

	/**
	 * @param rejectionHandler
	 *            The rejectionHandler to set.
	 */
	public void setRejectionHandler(IRejectionHandler rejectionHandler) {
		this.rejectionHandler = rejectionHandler;
	}

	/**
	 * Get maxQueueSize.
	 * @return Returns the maxQueueSize.
	 */
	public int getMaxQueueSize() {
		return maxQueueSize;
	}

	/**
	 * Get total no of thread.
	 * @return Returns total no of thread. 
	 */
	public int getTotalThreads() {
		return this.getActiveCount() + this.getQueueSize();
	}

	/**
	 * @return true - if this ThreadPool can handle more Tasks. There is no
	 *         gurantee that these tasks may be directly allocated a thread from
	 *         Pool or may be queued for execution.<br>
	 *         false - If no more * task can be exeuted nor can be queued (as
	 *         per defined by maxQueueSize).
	 */
	public boolean hasCapacity() {
		if (threadPool.getPoolSize() < this.getMaximumPoolSize()
				|| this.getQueueSize() < this.getMaxQueueSize()) {
			return true;
		}
		/*
		 * if ((this.getActiveCount() == this.getMaximumPoolSize()) &&
		 * this.getQueueSize() >= this.getMaxQueueSize()) { return false; }
		 */
		return false;
	}

	/**
	 * @return
	 */
	public int getMaxCapacity() {
		return this.getMaximumPoolSize() + this.getMaxQueueSize();
	}

	/**
	 * Get the capacity.
	 * @return Return capacity.
	 */
	public int getCapacity() {
		return this.getActiveCount() + this.getQueueSize();
	}
}
