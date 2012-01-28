package com.drighetto.fdr.persistence.dao;

import com.drighetto.fdr.persistence.model.FileInformations;
import com.drighetto.fdr.transversal.FDRException;

/**
 * DAO to manage downloaded file informations storing
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public interface FileDao {

	/**
	 * Save informations of te file to the DS
	 * 
	 * @param informations
	 *        File informations
	 * @throws FDRException
	 */
	void save(FileInformations informations) throws FDRException;

}
