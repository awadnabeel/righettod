package eu.curia.suivi2.model;

import java.util.Collection;
import java.util.Vector;

/**
 *  ###  G\u00E9n\u00E9r\u00E9 par Oracle TopLink Workbench 10.1.3.1.0 - Tue Sep 04 14:18:36 CEST 2007.  ###
 */

public class DeveloperLevel {

	private Collection developerCollection;
	private Integer idLevel;
	private String labelLevel;

public DeveloperLevel() {
	super();
	this.developerCollection = new Vector();
}

public void addToDeveloperCollection(Developer aDeveloper) {
	this.developerCollection.add(aDeveloper);
	aDeveloper.setIdDeveloperLevel(this);
}

protected Collection getDeveloperCollection() {
	return this.developerCollection;
}

public Integer getIdLevel() {
	return this.idLevel;
}

public String getLabelLevel() {
	return this.labelLevel;
}

public void removeFromDeveloperCollection(Developer aDeveloper) {
	this.developerCollection.remove(aDeveloper);
	aDeveloper.setIdDeveloperLevel(null);
}

protected void setDeveloperCollection(Collection developerCollection) {
	this.developerCollection = developerCollection;
}

public void setIdLevel(Integer idLevel) {
	this.idLevel = idLevel;
}

public void setLabelLevel(String labelLevel) {
	this.labelLevel = labelLevel;
}

}
