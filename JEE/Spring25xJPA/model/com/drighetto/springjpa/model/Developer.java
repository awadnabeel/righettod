package com.drighetto.springjpa.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "findAllDevelopers", query = "SELECT d FROM Developer d")
public class Developer implements Serializable {
	@Id
	@Column(name = "ID_DEVELOPER")
	private BigDecimal idDeveloper;

	@Column(name = "NAME_DEVELOPER")
	private String nameDeveloper;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REMOVE })
	@JoinColumn(name = "ID_DEVELOPER_LEVEL")
	private DeveloperLevel idDeveloperLevel;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REMOVE })
	@JoinColumn(name = "ID_DEVELOPER_EMPLOYER")
	private DeveloperEmployer idDeveloperEmployer;

	private static final long serialVersionUID = 1L;

	public Developer() {
		super();
	}

	public BigDecimal getIdDeveloper() {
		return this.idDeveloper;
	}

	public void setIdDeveloper(BigDecimal idDeveloper) {
		this.idDeveloper = idDeveloper;
	}

	public String getNameDeveloper() {
		return this.nameDeveloper;
	}

	public void setNameDeveloper(String nameDeveloper) {
		this.nameDeveloper = nameDeveloper;
	}

	public DeveloperLevel getIdDeveloperLevel() {
		return this.idDeveloperLevel;
	}

	public void setIdDeveloperLevel(DeveloperLevel idDeveloperLevel) {
		this.idDeveloperLevel = idDeveloperLevel;
	}

	public DeveloperEmployer getIdDeveloperEmployer() {
		return this.idDeveloperEmployer;
	}

	public void setIdDeveloperEmployer(DeveloperEmployer idDeveloperEmployer) {
		this.idDeveloperEmployer = idDeveloperEmployer;
	}

}
