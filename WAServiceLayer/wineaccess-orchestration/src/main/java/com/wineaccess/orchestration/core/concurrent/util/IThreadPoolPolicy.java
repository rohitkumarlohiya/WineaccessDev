package com.wineaccess.orchestration.core.concurrent.util;

/**
 * Class which defines the Policy to manage the ThreadPool. This policy is
 * checked every time before the submission of a new thread. Custom
 * implementation of this interface can have different policies to change/modify
 * the pool size as per their need.
 * 
 * @author jyoti.yadav@globallogic.com
 */
public interface IThreadPoolPolicy {

    /**
     * <p>
     * This method is executed while the submission of every new Runnable task
     * by the ThreadPool execute method.
     * <p>
     * 
     * @param threadPool
     *            the threadPool to manage
     */
    public void checkPolicy(ThreadPool threadPool);
}
