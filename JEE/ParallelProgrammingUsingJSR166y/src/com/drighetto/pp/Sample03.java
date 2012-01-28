package com.drighetto.pp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jsr166y.ForkJoinPool;
import jsr166y.ForkJoinTask;
import jsr166y.Phaser;

import com.drighetto.pp.task.FileCounterRecursiveTaskWithPhaser;

/**
 * Third sample : Launch several tasks (the type of task used here return
 * results) and use a Phaser to check their processing status<br>
 * In this sample the tasks use and share a Phaser instance to indicate the end
 * of the processing to the task execution manager <br>
 * The sample here will try to count files for each drive on the current machine
 * using all letters of the alphabet as drive letter...
 * 
 * @see "http://gee.cs.oswego.edu/dl/jsr166/dist/jsr166ydocs/jsr166y/Phaser.html"
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class Sample03 {

	/**
	 * Entry point
	 * 
	 * @param args
	 *        Command line
	 * @throws Exception
	 */
	@SuppressWarnings("boxing")
	public static void main(String[] args) throws Exception {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

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

		/* Create a Phaser */
		Phaser phaser = new Phaser(alphabet.length());

		/* Create tasks */
		List<FileCounterRecursiveTaskWithPhaser> tasks = new ArrayList<FileCounterRecursiveTaskWithPhaser>(alphabet.length());
		for (char c : alphabet.toCharArray()) {
			tasks.add(new FileCounterRecursiveTaskWithPhaser(new File(c + ":\\"), phaser));
		}

		/* Launch tasks */
		for (FileCounterRecursiveTaskWithPhaser task : tasks) {
			taskExecutor.execute(task);
		}

		/*
		 * Wait tasks termination using the Phaser and then display processing
		 * state
		 */
		// There only 1 phase in our sample (phase start from zero)
		phaser.awaitAdvance(0);

		/* Display tasks processing state */
		int i = 0;
		for (FileCounterRecursiveTaskWithPhaser task : tasks) {
			System.out.printf("  ** Task for drive [%s:\\] processing status **\n", alphabet.charAt(i));
			displayTaskProcessingStatus(task);
			i++;
		}

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
