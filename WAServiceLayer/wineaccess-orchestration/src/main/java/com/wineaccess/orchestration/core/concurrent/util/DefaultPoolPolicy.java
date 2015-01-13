package com.wineaccess.orchestration.core.concurrent.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Default implementation of the {@link IThreadPoolPolicy}. <br>
 * 
 * @author jyoti.yadav@globallogic.com
 */
public class DefaultPoolPolicy implements IThreadPoolPolicy {

	/**
	 * Field logger
	 */
	private static Log logger = LogFactory.getLog(DefaultPoolPolicy.class);

	private static final int MAX_IDLE_POOL = 2;

	private int poolIncrementCount;

	/**
	 * Constructor to initialize poolIncrementCount.
	 * @param poolIncrementCount : int
	 */
	public DefaultPoolPolicy(int poolIncrementCount) {
		this.poolIncrementCount = poolIncrementCount;
	}

    /**
     * <p>
     * This method is executed while the submission of every new Runnable task
     * by the ThreadPool execute method.
     * <p>
     * 
     * @param threadPool
     *            the threadPool to manage
     */
	public void checkPolicy(ThreadPool threadPool) {
		logger.debug("Checking Pool availability...");
		if (!threadPool.hasCapacity()) {
			int newPoolSize = threadPool.getMaximumPoolSize()
					+ poolIncrementCount;
			threadPool.setMaximumPoolSize(newPoolSize);
			logger.debug("new Pool size = " + newPoolSize);
		} else {

			int coreSize = threadPool.getCorePoolSize();
			int activeSize = threadPool.getActiveCount();
			int maxSize = threadPool.getMaximumPoolSize();

			int idlePool = (activeSize > coreSize) ? (maxSize - activeSize)
					: (maxSize - coreSize);
			if (idlePool > MAX_IDLE_POOL) {
				int newMaxPoolSize = (activeSize > coreSize) ? (MAX_IDLE_POOL + activeSize)
						: (MAX_IDLE_POOL + coreSize);
				threadPool.setMaximumPoolSize(newMaxPoolSize);
				logger.debug("****Shrinking threadpool ****");
				logger.debug("MaxPool=" + newMaxPoolSize + "ActivePool="
						+ activeSize + "CorePool=" + coreSize);
			}

		}
	}
}
