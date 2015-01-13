package com.wineaccess.orchestration.core.concurrent.util;

/**
 * <p>
 * Handler interface for the processing of the runnable task which are rejected
 * by the ThreadPool, in the case threadPool is unable to handle them.
 * <p>
 * 
 * @author jyoti.yadav@globallogic.com
 */
public interface IRejectionHandler {

    /**
     * <p>
     * Method called when the ThreadPool is unable to handle any task because of
     * unavailable pool size or overflow of thread queue size.
     * <p>
     * 
     * @param task
     *            task which needs to be handled by the handler
     */
    public void handleRejected(Runnable task);
}
