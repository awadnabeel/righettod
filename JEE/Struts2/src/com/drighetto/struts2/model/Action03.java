package com.drighetto.struts2.model;

import com.opensymphony.xwork2.ActionSupport;

import java.io.File;

/**
 * Simple Action for file upload
 * 
 * @author Dominique RIGHETTO (dominique.righetto@logica.com)
 * 
 */
public class Action03 extends ActionSupport {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -6936998866189848612L;

	/** Uploaded file */
	private File uploadedFile = null;

	/** Informations about uploaded file */
	private String fileInfos = null;

	/**
	 * {@inheritDoc}
	 * 
	 * <br>
	 * 
	 * Simple action, by default the methods executed by Struts2 for a action is
	 * named "execute"
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		if (this.uploadedFile != null) {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("NAME : ").append(this.uploadedFile.getAbsolutePath()).append(" - SIZE : ").append(this.uploadedFile.length());
			this.fileInfos = sBuilder.toString();
			// We add a action message, the message is extracted from the global
			// resource bundle
			// defined in struts.xml file...
			this.addActionMessage(this.getText("page03.file.ok"));
			return SUCCESS;
		}
		// We add a action error (it's also possible to use a validator through
		// annotation), the message is extracted from the global resource bundle
		// defined in struts.xml file...
		this.addActionError(this.getText("page03.file.path.required"));
		return ERROR;
	}

	/**
	 * Getter for the attribute uploadedFile
	 * 
	 * @return The value of uploadedFile
	 */
	public File getUploadedFile() {
		return this.uploadedFile;
	}

	/**
	 * Setter for the attribute uploadedFile
	 * 
	 * @param uploadedFile The new value
	 */
	public void setUploadedFile(File uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	/**
	 * Getter for the attribute fileInfos
	 * 
	 * @return The value of fileInfos
	 */
	public String getFileInfos() {
		return this.fileInfos;
	}

}
