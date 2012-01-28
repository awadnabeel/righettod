package com.drighetto.springjpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DEVELOPER_EMPLOYER")
public class DeveloperEmployer implements Serializable {
	@Id
	@Column(name = "ID_EMPLOYER")
	private BigDecimal idEmployer;

	@Column(name = "NAME_EMPLOYER")
	private String nameEmployer;

	@OneToMany(mappedBy = "idDeveloperEmployer", fetch = FetchType.LAZY, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	private Set<Developer> developerCollection;

	private static final long serialVersionUID = 1L;

	public DeveloperEmployer() {
		super();
	}

	public BigDecimal getIdEmployer() {
		return this.idEmployer;
	}

	public void setIdEmployer(BigDecimal idEmployer) {
		this.idEmployer = idEmployer;
	}

	public String getNameEmployer() {
		return this.nameEmployer;
	}

	public void setNameEmployer(String nameEmployer) {
		this.nameEmployer = nameEmployer;
	}

	public Set<Developer> getDeveloperCollection() {
		return this.developerCollection;
	}

	public void setDeveloperCollection(Set<Developer> developerCollection) {
		this.developerCollection = developerCollection;
	}

}
