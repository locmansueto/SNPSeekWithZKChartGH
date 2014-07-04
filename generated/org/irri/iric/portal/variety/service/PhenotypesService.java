package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.domain.Germplasm;
import org.irri.iric.portal.variety.domain.Phenotypes;

/**
 * Spring service that handles CRUD requests for Phenotypes entities
 * 
 */
public interface PhenotypesService {

	/**
	 * Return a count of all Phenotypes entity
	 * 
	 */
	public Integer countPhenotypess();

	/**
	 * Save an existing Germplasm entity
	 * 
	 */
	public Phenotypes savePhenotypesGermplasm(Integer nsftvId, String trait, Germplasm related_germplasm);

	/**
	 * Load an existing Phenotypes entity
	 * 
	 */
	public Set<Phenotypes> loadPhenotypess();

	/**
	 */
	public Phenotypes findPhenotypesByPrimaryKey(Integer nsftvId_1, String trait_1);

	/**
	 * Delete an existing Phenotypes entity
	 * 
	 */
	public void deletePhenotypes(Phenotypes phenotypes);

	/**
	 * Return all Phenotypes entity
	 * 
	 */
	public List<Phenotypes> findAllPhenotypess(Integer startResult, Integer maxRows);

	/**
	 * Save an existing Phenotypes entity
	 * 
	 */
	public void savePhenotypes(Phenotypes phenotypes_1);

	/**
	 * Delete an existing Germplasm entity
	 * 
	 */
	public Phenotypes deletePhenotypesGermplasm(Integer phenotypes_nsftvId, String phenotypes_trait, Integer related_germplasm_nsftvId);
}