package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.domain.Genotyping;
import org.irri.iric.portal.variety.domain.Germplasm;
import org.irri.iric.portal.variety.domain.Phenotypes;

/**
 * Spring service that handles CRUD requests for Germplasm entities
 * 
 */
public interface GermplasmService {

	/**
	 * Return a count of all Germplasm entity
	 * 
	 */
	public Integer countGermplasms();

	/**
	 * Return all Germplasm entity
	 * 
	 */
	public List<Germplasm> findAllGermplasms(Integer startResult, Integer maxRows);

	/**
	 * Delete an existing Phenotypes entity
	 * 
	 */
	public Germplasm deleteGermplasmPhenotypeses(Integer germplasm_nsftvId, Integer related_phenotypeses_nsftvId, String related_phenotypeses_trait);

	/**
	 * Delete an existing Genotyping entity
	 * 
	 */
	public Germplasm deleteGermplasmGenotypings(Integer germplasm_nsftvId_1, String related_genotypings_snpId, Integer related_genotypings_nsftvId);

	/**
	 * Delete an existing Germplasm entity
	 * 
	 */
	public void deleteGermplasm(Germplasm germplasm);

	/**
	 * Load an existing Germplasm entity
	 * 
	 */
	public Set<Germplasm> loadGermplasms();

	/**
	 */
	public Germplasm findGermplasmByPrimaryKey(Integer nsftvId);

	/**
	 * Save an existing Germplasm entity
	 * 
	 */
	public void saveGermplasm(Germplasm germplasm_1);

	/**
	 * Save an existing Genotyping entity
	 * 
	 */
	public Germplasm saveGermplasmGenotypings(Integer nsftvId_1, Genotyping related_genotypings);

	/**
	 * Save an existing Phenotypes entity
	 * 
	 */
	public Germplasm saveGermplasmPhenotypeses(Integer nsftvId_2, Phenotypes related_phenotypeses);
}