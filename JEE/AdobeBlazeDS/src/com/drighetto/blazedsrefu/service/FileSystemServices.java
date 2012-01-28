package com.drighetto.blazedsrefu.service;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.drighetto.blazedsrefu.service.vo.FileInformations;

import flex.messaging.util.StringUtils;

/**
 * Class providing file system remote services thought BlazeDS
 * 
 * @author Dominique RIGHETTO (dominique.righetto@logica.com)
 * 
 */
public class FileSystemServices {

	/**
	 * 
	 * Constructor
	 * 
	 */
	public FileSystemServices() {
		super();
	}

	/**
	 * Method to obtain file content
	 * 
	 * @param path Full path to the file
	 * @return the file content
	 * @throws IOException
	 */
	public String obtainFileContent(String path) throws IOException {
		// Check file path
		if (StringUtils.isEmpty(path)) {
			throw new IOException("A valid path must be provided !");
		}
		File fBase = new File(path);
		if (!fBase.exists()) {
			throw new IOException("The file do not exists !");
		}
		return FileUtils.readFileToString(new File(path));
	}

	/**
	 * Method to obtains the first child of a directory
	 * 
	 * @param path Full directory path
	 * @return the list of the child element
	 * @throws IOException
	 */
	public List<FileInformations> obtainFirstLevelChildren(String path) throws IOException {
		// Check file path
		if (StringUtils.isEmpty(path)) {
			throw new IOException("A valid path must be provided !");
		}
		File fBase = new File(path);
		if (!fBase.exists()) {
			throw new IOException("The file do not exists !");
		}

		// Check if the current file is a file
		List<FileInformations> listFiles = new ArrayList<FileInformations>();
		if (fBase.isDirectory()) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:S");
			// Add a false file to offer the possibility to return to the parent
			// level
			listFiles.add(new FileInformations("..", "..", 0, false, df.format(new Date()), false, false, fBase
					.getParent()));
			// Get the first level childs
			for (File f : fBase.listFiles()) {
				listFiles.add(new FileInformations(f.getName(), f.getAbsolutePath(), f.length(), f.isDirectory(), df
						.format(new Date(f.lastModified())), f.canWrite(), f.isHidden(), f.getParent()));
			}
		}

		return listFiles;

	}
}
