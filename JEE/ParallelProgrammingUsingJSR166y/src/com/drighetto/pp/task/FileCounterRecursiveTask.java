package com.drighetto.pp.task;

import java.io.File;
import java.util.LinkedList;

import jsr166y.RecursiveTask;

import org.apache.commons.io.FileUtils;

/**
 * Task to count all files in a specified location (this type of task return
 * results)<br>
 * <br>
 * 
 * @see "http://gee.cs.oswego.edu/dl/jsr166/dist/jsr166ydocs/jsr166y/RecursiveTask.html"
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class FileCounterRecursiveTask extends RecursiveTask<Integer> {

	/**
	 * Serial VUID
	 */
	private static final long serialVersionUID = -7080147846428938256L;

	/**
	 * Source location for file seaching
	 */
	private File sourceLocation = null;

	/**
	 * Constructor
	 * 
	 * @param sourceLocation
	 *        Source location for file seaching
	 */
	public FileCounterRecursiveTask(File sourceLocation) {
		super();
		this.sourceLocation = sourceLocation;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see jsr166y.RecursiveTask#compute()
	 */
	@SuppressWarnings( { "unchecked", "boxing" })
	@Override
	protected Integer compute() {
		// Count file using CommonsIO API
		LinkedList<File> files = (LinkedList) FileUtils.listFiles(this.sourceLocation, null, true);
		// Return the result
		return files.size();
	}

}
