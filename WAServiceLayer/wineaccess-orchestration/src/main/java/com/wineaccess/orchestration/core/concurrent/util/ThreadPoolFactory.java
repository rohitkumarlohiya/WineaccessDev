package com.wineaccess.orchestration.core.concurrent.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Factory class to create new ThreadPool. This factory class can provide the
 * ThreadPool instance which can handled threads based on priority, ordered
 * execution, random execution etc.
 * 
 * @author jyoti.yadav@globallogic.com
 */
public class ThreadPoolFactory {

    /**
     * <p>
     * Returns the ThreadPool instance which acts on the Priority of the
     * Runnable processes which needs to be executed by this threadpool. The
     * Runnable instance have to implement {@link Comparable} in order to define
     * their priority.
     * <p>
     * 
     * @param corePoolSize
     *            poolsize for ThreadPoolExecutor
     * @param keepAliveTime
     *            keepAliveTime for inactive threads
     * @param queueSize
     *            Queue Size for PriorityThreadQueue
     * @param maxQueueSize
     *            maxQueue Size for PriorityThreadQueue
     * @return ThreadPool
     */
    public static ThreadPool getPriorityThreadExecutor(int corePoolSize,
            long keepAliveTime, int queueSize, int maxQueueSize) {
        final PriorityBlockingQueue<Runnable> queue = 
        	new PriorityBlockingQueue<Runnable>(
                queueSize);
        return new ThreadPool(corePoolSize, corePoolSize, keepAliveTime, 
        		queue, maxQueueSize);
    }

    /**
     * <p>
     * Returns the ThreadPool which acts on thread in the ordered manner in
     * which they are submitted for execution.
     * <p>
     * 
     * @param corePoolSize
     *            poolsize for ThreadPoolExecutor
     * @param maxPoolSize
     *            maxPoolSize for ThreadPoolExecutor
     * @param keepAliveTime
     *            keepAliveTime for inactive threads
     * @param maxQueueSize
     *            maxQueue Size for OrderedThreadQueue
     * @return ThreadPool
     */
    public static ThreadPool getOrderedThreadExecutor(int corePoolSize,
            int maxPoolSize, long keepAliveTime, int maxQueueSize) {
        final ArrayBlockingQueue<Runnable> queue = 
        	new ArrayBlockingQueue<Runnable>(
                maxQueueSize, true);
        return new ThreadPool(corePoolSize, maxPoolSize, keepAliveTime, 
        		queue, maxQueueSize);
    }

}
