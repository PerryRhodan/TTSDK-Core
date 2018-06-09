/**
 * www.taleteller.de
 * 
 * TaletellerCore
 *   Thread
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

import java.util.LinkedList;
import de.taleteller.core.logging.Log;

/** 
 * Thread (which might be a poor choice of name) 
 * offers an convenient way to execute certain thread tasks
 * periodically.
 * Thread tasks execute one after each other, so
 * they are not precise in their timings, and a blocking task
 * will block all subsequent tasks!
 */
public class Thread {

	/** queue to add tasks during thread execution */
	//ConcurrentLinkedQueue<ThreadTask> adding_queue; Good idea, maybe implement this some time
	
	/** actual thread */
	java.lang.Thread actual;
	
	/** flag controlling main while loop */
	boolean running;
	
	/** flag used to trigger a run-once */
	boolean run_once;
	
	/** flag to be set once thread finishes completely (ie after being stopped) */
	boolean finished;
	
	/** list of all tasks assigned to this thread */
	LinkedList<ThreadTask> tasks;
	
	/** time to wait each loop in MS */
	int waittime_ms;
	
	/**
	 * Create new instance of a thread with given 
	 * period in milli-seconds.
	 * @param ms
	 * @return net instance
	 */
	public static Thread createNew(int ms) {
		Thread t = new Thread(ms);

		return t;
	}
	
	private Thread(int ms) {
		tasks = new LinkedList<>();
		waittime_ms = ms;
		
		// create real thread
		InnerRunnable inner = new InnerRunnable();
		actual = new java.lang.Thread(inner);
	}
	
	/* ######################################################## */
	
	private class InnerRunnable implements Runnable {
		
		@Override
		public void run() {
			
			for (ThreadTask task : tasks) {
				task.OnStartup();
			}

			while (running || run_once) {
				try {
					for (ThreadTask task : tasks) {
						if (task.IsPaused())
							continue;

						task.InLoop();
					}

					java.lang.Thread.sleep(waittime_ms);

				} catch (InterruptedException e) {
					e.printStackTrace();
					// try to run the OnShutdown methods when thread gets interrupted.
					running = false;
					for (ThreadTask task : tasks) {
						task.OnShutdown();
					}
					return;
				}
				
				run_once = false;
			}

			for (ThreadTask task : tasks) {
				task.OnShutdown();
			}
			
			finished = true;
		}
		
	}
	
	/* ######################################################## */
	
	/**
	 * Add given task to the thread.
	 * @param task
	 */
	public void AddTask(ThreadTask task) {
		if(running) {
			Log.WARN("TaletellerCore::Thread::AddTask:: Cannot add"
					+ " ThreadTask, Thread is already running!");
			return;
		} else if(finished) {
			Log.WARN("TaletellerCore::Thread::AddTask:: Thread has already finished,"
					+ " need to be restarted in order to use added ThreadTask.");
		}
		tasks.add(task);
	}
	
	/**
	 * Start periodic thread execution of its tasks.
	 */
	public void Start() {
		if(!running || finished) {
			running = true;
			finished = false;
			// recreate thread
			InnerRunnable inner = new InnerRunnable();
			actual = new java.lang.Thread(inner);
			actual.start();
		}
	}
	
	/**
	 * Start thread execution of its tasks, but run them only once.
	 */
	public void Start_RunOnce() {
		if(!running || finished) {
			running = false;		// running must be false
			run_once = true;
			finished = false;
			// recreate thread
			InnerRunnable inner = new InnerRunnable();
			actual = new java.lang.Thread(inner);
			actual.start();
		}
	}
	
    /**
     * Stops thread and execution of its tasks on next iteration.
     * Finishes off current iteration.
     */
	public void Stop() {
		running = false;
		run_once = false;
	}
	
	/**
	 * Returns whether or not the thread has finished.
	 * @return flag_finished
	 */
	public boolean IsFinished() {
		return finished;
	}
	
}
