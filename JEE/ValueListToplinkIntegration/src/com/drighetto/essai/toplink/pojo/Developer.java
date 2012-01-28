package com.drighetto.essai.toplink.pojo;

import java.math.BigDecimal;
import oracle.toplink.indirection.ValueHolder;
import oracle.toplink.indirection.ValueHolderInterface;

/**
 *  ###  G\u00E9n\u00E9r\u00E9 par Oracle TopLink Workbench 10.1.3.1.0 - Fri Jul 13 23:48:05 CEST 2007.  ###
 */

public class Developer {

	private BigDecimal idDeveloper;
	private ValueHolderInterface idDeveloperLevel;
	private ValueHolderInterface levelName;
	private String nameDeveloper;

public Developer() {
	super();
	this.idDeveloperLevel = new ValueHolder();
	this.levelName = new ValueHolder();
}

public BigDecimal getIdDeveloper() {
	return this.idDeveloper;
}

public DeveloperLevel getIdDeveloperLevel() {
	return (DeveloperLevel) this.idDeveloperLevel.getValue();
}

protected ValueHolderInterface getIdDeveloperLevelHolder() {
	return this.idDeveloperLevel;
}

public ValueHolderInterface getLevelName() {
	return this.levelName;
}

public String getNameDeveloper() {
	return this.nameDeveloper;
}

public void setIdDeveloper(BigDecimal idDeveloper) {
	this.idDeveloper = idDeveloper;
}

public void setIdDeveloperLevel(DeveloperLevel idDeveloperLevel) {
	this.idDeveloperLevel.setValue(idDeveloperLevel);
}

protected void setIdDeveloperLevelHolder(ValueHolderInterface idDeveloperLevel) {
	this.idDeveloperLevel = idDeveloperLevel;
}

public void setLevelName(ValueHolderInterface levelName) {
	this.levelName = levelName;
}

public void setNameDeveloper(String nameDeveloper) {
	this.nameDeveloper = nameDeveloper;
}

}
