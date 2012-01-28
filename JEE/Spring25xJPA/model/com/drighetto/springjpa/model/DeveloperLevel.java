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
@Table(name = "DEVELOPER_LEVEL")
public class DeveloperLevel implements Serializable {
	@Id
	@Column(name = "ID_LEVEL")
	private BigDecimal idLevel;

	@Column(name = "LABEL_LEVEL")
	private String labelLevel;

	@OneToMany(mappedBy = "idDeveloperLevel", fetch = FetchType.LAZY, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	private Set<Developer> developerCollection;

	private static final long serialVersionUID = 1L;

	public DeveloperLevel() {
		super();
	}

	public BigDecimal getIdLevel() {
		return this.idLevel;
	}

	public void setIdLevel(BigDecimal idLevel) {
		this.idLevel = idLevel;
	}

	public String getLabelLevel() {
		return this.labelLevel;
	}

	public void setLabelLevel(String labelLevel) {
		this.labelLevel = labelLevel;
	}

	public Set<Developer> getDeveloperCollection() {
		return this.developerCollection;
	}

	public void setDeveloperCollection(Set<Developer> developerCollection) {
		this.developerCollection = developerCollection;
	}

}
