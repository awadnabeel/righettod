package com.drighetto.pp;

import java.io.File;
import java.util.concurrent.TimeUnit;

import jsr166y.ForkJoinPool;
import jsr166y.ForkJoinTask;

import com.drighetto.pp.task.FileCounterRecursiveTask;

/**
 * Second sample : Launch several tasks (the type of task used here return
 * results) and wait for their termination...<br>
 * In this sample the tasks do not have link between them and the result of a
 * task is not used by another...<br>
 * <br>
 * 
 * @see "http://gee.cs.oswego.edu/dl/jsr166/dist/jsr166ydocs/jsr166y/ForkJoinPool.html"
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class Sample02 {

	/**
	 * Entry point
	 * 
	 * @param args
	 *        Command line
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		/* Create and configure a task pool manager (task executor) */
		ForkJoinPool taskExecutor = new ForkJoinPool();
		// Indicate to the task executor to use all processor of the current
		// machine
		taskExecutor.setParallelism(Runtime.getRuntime().availableProcessors());
		// In this sample tasks used here do not joined the we can disable the
		// AsyncMode (documentation
		// extract :
		// Establishes local first-in-first-out scheduling mode for forked tasks
		// that are never joined. This mode may be more appropriate than default
		// locally stack-based mode in applications in which worker threads only
		// process asynchronous tasks. This method is designed to be invoked
		// only when the pool is quiescent, and typically only before any tasks
		// are submitted. The effects of invocations at other times may be
		// unpredictable.)
		taskExecutor.setAsyncMode(false);
		// Indicate to the task executor to maintain parallelism
		taskExecutor.setMaintainsParallelism(true);

		/* Create and launch tasks with the task executor */
		FileCounterRecursiveTask task01 = new FileCounterRecursiveTask(new File("C:\\"));
		FileCounterRecursiveTask task02 = new FileCounterRecursiveTask(new File("D:\\"));
		FileCounterRecursiveTask task03 = new FileCounterRecursiveTask(new File("E:\\"));
		// Explicit invalid path in order that the task fail !
		FileCounterRecursiveTask task04 = new FileCounterRecursiveTask(new File("A:\\"));
		taskExecutor.execute(task01);
		taskExecutor.execute(task02);
		taskExecutor.execute(task03);
		taskExecutor.execute(task04);
		System.out.println("Tasks launched...");

		/* Close the pool */
		taskExecutor.shutdown();

		/*
		 * Wait tasks termination (limit waiting to 15 minutes) and then display
		 * processing state
		 */
		boolean terminationState = taskExecutor.awaitTermination(15, TimeUnit.MINUTES);
		if (terminationState) {
			System.out.println("All tasks have finished their processing under the timeout !");
		} else {
			System.out.println("All tasks do not have finished their processing until the timeout !");
		}
		System.out.println("  ** Task01 processing status **");
		displayTaskProcessingStatus(task01);
		System.out.println("  ** Task02 processing status **");
		displayTaskProcessingStatus(task02);
		System.out.println("  ** Task03 processing status **");
		displayTaskProcessingStatus(task03);
		System.out.println("  ** Task04 processing status **");
		displayTaskProcessingStatus(task04);
	}

	/**
	 * Display the processing status of a task
	 * 
	 * @param task
	 *        source task
	 */
	@SuppressWarnings("boxing")
	private static void displayTaskProcessingStatus(ForkJoinTask<Integer> task) {
		System.out.printf("     isCompletedNormally()   : %s\n", task.isCompletedNormally());
		System.out.printf("     isDone()                : %s\n", task.isDone());
		System.out.printf("     isCancelled()           : %s\n", task.isCancelled());
		System.out.printf("     isCompletedAbnormally() : %s\n", task.isCompletedAbnormally());
		System.out.printf("     getException()          : %s\n", (task.getException() == null) ? null : task.getException().getMessage());
		System.out.printf("     getRawResult()          : %s\n", task.getRawResult());
	}

}
