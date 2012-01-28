package eu.curia.suivi2.model;

import oracle.toplink.indirection.ValueHolder;
import oracle.toplink.indirection.ValueHolderInterface;

/**
 *  ###  G\u00E9n\u00E9r\u00E9 par Oracle TopLink Workbench 10.1.3.1.0 - Thu Sep 06 10:51:33 CEST 2007.  ###
 */

public class Developer {

	private ValueHolderInterface idDeveloperLevel;
	private String nameDeveloper;
	private Integer idDeveloper;
	private Integer optimisticLockId;

public Developer() {
	super();
	this.idDeveloperLevel = new ValueHolder();
}

public Integer getIdDeveloper() {
	return this.idDeveloper;
}

public DeveloperLevel getIdDeveloperLevel() {
	return (DeveloperLevel) this.idDeveloperLevel.getValue();
}

protected ValueHolderInterface getIdDeveloperLevelHolder() {
	return this.idDeveloperLevel;
}

public String getNameDeveloper() {
	return this.nameDeveloper;
}

public Integer getOptimisticLockId() {
	return this.optimisticLockId;
}

public void setIdDeveloper(Integer idDeveloper) {
	this.idDeveloper = idDeveloper;
}

public void setIdDeveloperLevel(DeveloperLevel idDeveloperLevel) {
	this.idDeveloperLevel.setValue(idDeveloperLevel);
}

protected void setIdDeveloperLevelHolder(ValueHolderInterface idDeveloperLevel) {
	this.idDeveloperLevel = idDeveloperLevel;
}

public void setNameDeveloper(String nameDeveloper) {
	this.nameDeveloper = nameDeveloper;
}

public void setOptimisticLockId(Integer optimisticLockId) {
	this.optimisticLockId = optimisticLockId;
}

}
