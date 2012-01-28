package com.drighetto.pp.task;

import java.io.File;
import java.util.LinkedList;

import jsr166y.Phaser;
import jsr166y.RecursiveTask;

import org.apache.commons.io.FileUtils;

/**
 * Task to count all files in a specified location (this type of task return
 * results)<br>
 * <br>
 * This task use a Phaser to indicate to the tasks execution manager that is
 * processing is finished...
 * 
 * @see "http://gee.cs.oswego.edu/dl/jsr166/dist/jsr166ydocs/jsr166y/RecursiveTask.html"
 * @see "http://gee.cs.oswego.edu/dl/jsr166/dist/jsr166ydocs/jsr166y/Phaser.html"
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class FileCounterRecursiveTaskWithPhaser extends RecursiveTask<Integer> {

	/**
	 * Serial VUID
	 */
	private static final long serialVersionUID = -7080147846428938256L;

	/**
	 * Source location for file seaching
	 */
	private File sourceLocation = null;

	/**
	 * Phaser instance that be used to indicate to the tasks execution manager
	 * that the processing is finished
	 */
	private Phaser phaser = null;

	/**
	 * Constructor
	 * 
	 * @param sourceLocation
	 *        Source location for file seaching
	 * @param phaser
	 *        Phaser instance that be used to indicate to the tasks execution
	 *        manager that the processing is finished
	 */
	public FileCounterRecursiveTaskWithPhaser(File sourceLocation, Phaser phaser) {
		super();
		this.sourceLocation = sourceLocation;
		this.phaser = phaser;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see jsr166y.RecursiveTask#compute()
	 */
	@SuppressWarnings( { "unchecked", "boxing" })
	@Override
	protected Integer compute() {
		int count = 0;
		try {
			// Count file using CommonsIO API
			count = ((LinkedList) FileUtils.listFiles(this.sourceLocation, null, true)).size();
		}
		// Do it in a Finally in order to ensure that the notification to the
		// task execution manager is always realized !
		finally {
			// Indicate to the tasks execution manager that the processing is
			// finished.
			// Deregister self from the task pool without awaiting for others
			// tasks to finish their processing...(see "Phaser" class javadoc
			// (the
			// url is specified in the class javadoc) for more options...)
			this.phaser.arriveAndDeregister();
		}
		// Return the result
		return count;
	}

}
