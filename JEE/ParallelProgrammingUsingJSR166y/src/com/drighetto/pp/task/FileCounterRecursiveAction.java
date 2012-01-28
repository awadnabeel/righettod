package com.drighetto.pp.task;

import java.io.File;
import java.util.LinkedList;

import jsr166y.RecursiveAction;

import org.apache.commons.io.FileUtils;

/**
 * Action (task) to count all files in a specified location (this type of task
 * do not return results)<br>
 * <br>
 * 
 * @see "http://gee.cs.oswego.edu/dl/jsr166/dist/jsr166ydocs/jsr166y/RecursiveAction.html"
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class FileCounterRecursiveAction extends RecursiveAction {

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
	public FileCounterRecursiveAction(File sourceLocation) {
		super();
		this.sourceLocation = sourceLocation;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see jsr166y.RecursiveAction#compute()
	 */
	@SuppressWarnings( { "unchecked", "boxing" })
	@Override
	protected void compute() {
		// Count file using CommonsIO API
		LinkedList<File> files = (LinkedList) FileUtils.listFiles(this.sourceLocation, null, true);
		// Display file count into the console
		System.out.printf("There %s files in the location [%s]\n", files.size(), this.sourceLocation.getAbsolutePath());
	}

}
