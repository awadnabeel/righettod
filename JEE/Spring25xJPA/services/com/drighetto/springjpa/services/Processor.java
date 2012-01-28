package com.drighetto.springjpa.services;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.drighetto.springjpa.dao.Dao;
import com.drighetto.springjpa.model.Developer;
import com.drighetto.springjpa.model.DeveloperEmployer;
import com.drighetto.springjpa.model.DeveloperLevel;

/**
 * Simple DB Actions
 * 
 * @author Dominique RIGHETTO <dominique.righetto@gmail.com>
 * 
 */
public class Processor {

	/** DAO instance */
	private Dao myDao = null;

	/**
	 * Display the list of developers
	 * 
	 * @throws Exception
	 */
	public void displayDeveloper() throws Exception {
		List<Developer> developers = this.myDao.readAllDeveloper();
		for (Developer d : developers) {
			System.out.printf("**** %s ****\n", d.getNameDeveloper());
			System.out.printf("=> %s \n", d.getIdDeveloperLevel()
					.getLabelLevel());
			System.out.printf("=> %s \n", d.getIdDeveloperEmployer()
					.getNameEmployer());
		}

	}

	/**
	 * Display the developer info
	 * 
	 * @throws Exception
	 */
	public void displayDeveloperInfo() throws Exception {
		Developer d = this.myDao.readDeveloper(BigDecimal.valueOf(1));
		System.out.printf("**** %s ****\n", d.getNameDeveloper());
		System.out.printf("=> %s \n", d.getIdDeveloperLevel().getLabelLevel());
		System.out.printf("=> %s \n", d.getIdDeveloperEmployer()
				.getNameEmployer());
	}

	/**
	 * Add a new Developer
	 * 
	 * @throws Exception
	 */
	public void addDeveloper() throws Exception {
		// Create a developer level
		DeveloperLevel developerLevel = new DeveloperLevel();
		developerLevel.setIdLevel(BigDecimal.valueOf(4));
		developerLevel.setLabelLevel("ANONYMOUS");
		// Create a developer employer
		DeveloperEmployer developerEmployer = new DeveloperEmployer();
		developerEmployer.setIdEmployer(BigDecimal.valueOf(5));
		developerEmployer.setNameEmployer("ANONYMOUS");
		// Create a developer
		Developer developer = new Developer();
		developer.setIdDeveloper(BigDecimal.valueOf(5));
		developer.setNameDeveloper("ANONYMOUS");
		developer.setIdDeveloperLevel(developerLevel);
		developer.setIdDeveloperEmployer(developerEmployer);
		// Add relations
		Set<Developer> developers = new LinkedHashSet<Developer>();
		developers.add(developer);
		developerLevel.setDeveloperCollection(developers);
		developerEmployer.setDeveloperCollection(developers);

		this.myDao.createDeveloper(developer);
		System.out.printf("Developer '%s' and relations added !", developer
				.getNameDeveloper());

	}

	/**
	 * Remove a developer and is relations
	 * 
	 * @throws Exception
	 */
	public void removeDeveloper() throws Exception {
		// All relations of this instance are deleted because the "CASCADE"
		// parameter in the bean
		// mapping in defined also on REMOVE action
		this.myDao.deleteDeveloper(BigDecimal.valueOf(5));
		System.out.println("Developer deleted !");
		/*
		 * Uncomment this 4 lines below if you want to generate a exception in
		 * order to implicate a rollback and view the Spring transaction
		 * management, because in this case the developer object above is not
		 * deleted !!!
		 */
		// this.myDao.deleteDeveloperLevel(BigDecimal.valueOf(4));
		// System.out.println("Developer level deleted !");
		// this.myDao.deleteDeveloperEmployer(BigDecimal.valueOf(5));
		// System.out.println("Developer employer deleted !");
	}

	/**
	 * Update a developer
	 * 
	 * @param newDeveloperName
	 *            New Developer Name
	 * 
	 * @throws Exception
	 */
	public void updateDeveloper(String newDeveloperName) throws Exception {
		// No update method is needed in a DAO because modifying an entity is as
		// simple as reading that entity within a transaction and changing the
		// properties of that entity and when the transaction is committed the
		// modification are sent to the underlying database...
		Developer d = this.myDao.readDeveloper(BigDecimal.valueOf(1));
		d.setNameDeveloper(newDeveloperName.trim());
	}

	/**
	 * Setter for the attribute myDao
	 * 
	 * @param myDao
	 *            The new value
	 */
	public void setMyDao(Dao myDao) {
		this.myDao = myDao;
	}

}
