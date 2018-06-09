/**
 * www.taleteller.de
 * 
 * TaletellerCore
 *   ThreadTask
 * 
 * Summary:
 *   
 * 
 * History:
 *   25.11.2017 - Cleaning of code
 *   
 * 
 * Ideas:
 *   Queue to add thread tasks dynamically.
 * 
 * Stephan Hogrefe, Edinburgh, 2017
 */
package de.taleteller.core.thread;

/**
 * 
 */
public abstract class ThreadTask {

	/** Flag to indicate if the task
	 *  should be executed in the thread.
	 *  Initialize with true */
	boolean THREAD_TASK_ACTIVE = true;
	
	/**
	 * Executed on Thread startup.
	 */
	public abstract void OnStartup();
	
	/**
	 * Executed every iteration of the thread loop
	 * with thread period.
	 */
	public abstract void InLoop();
	
	/**
	 * Executed on Thread shutdown.
	 */
	public abstract void OnShutdown();
	
	/**
	 * Stops the execution of this task next iteration.
	 * WONT TRIGGER THE OnShutdown EXECUTION.
	 */
	public void Pause() {
		THREAD_TASK_ACTIVE = false;
	}
	
	/**
	 * Resumes task execution on next thread iteration.
	 */
	public void Resume() {
		THREAD_TASK_ACTIVE = true;
	}
	
	/**
	 * Returns whether or not the task is to be executed.
	 * @return if tasks should be executed or not.
	 */
	public boolean IsPaused() {
		return !THREAD_TASK_ACTIVE;
	}
	
}
