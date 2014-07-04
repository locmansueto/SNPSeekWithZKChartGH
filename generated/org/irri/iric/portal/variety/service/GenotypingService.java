package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.domain.Genotyping;
import org.irri.iric.portal.variety.domain.Germplasm;
import org.irri.iric.portal.variety.domain.Snps;

/**
 * Spring service that handles CRUD requests for Genotyping entities
 * 
 */
public interface GenotypingService {

	/**
	 */
	public Genotyping findGenotypingByPrimaryKey(String snpId, Integer nsftvId);

	/**
	 * Delete an existing Snps entity
	 * 
	 */
	public Genotyping deleteGenotypingSnps(String genotyping_snpId, Integer genotyping_nsftvId, String related_snps_snpId);

	/**
	 * Save an existing Genotyping entity
	 * 
	 */
	public void saveGenotyping(Genotyping genotyping);

	/**
	 * Save an existing Germplasm entity
	 * 
	 */
	public Genotyping saveGenotypingGermplasm(String snpId_1, Integer nsftvId_1, Germplasm related_germplasm);

	/**
	 * Return all Genotyping entity
	 * 
	 */
	public List<Genotyping> findAllGenotypings(Integer startResult, Integer maxRows);

	/**
	 * Save an existing Snps entity
	 * 
	 */
	public Genotyping saveGenotypingSnps(String snpId_2, Integer nsftvId_2, Snps related_snps);

	/**
	 * Return a count of all Genotyping entity
	 * 
	 */
	public Integer countGenotypings();

	/**
	 * Load an existing Genotyping entity
	 * 
	 */
	public Set<Genotyping> loadGenotypings();

	/**
	 * Delete an existing Germplasm entity
	 * 
	 */
	public Genotyping deleteGenotypingGermplasm(String genotyping_snpId_1, Integer genotyping_nsftvId_1, Integer related_germplasm_nsftvId);

	/**
	 * Delete an existing Genotyping entity
	 * 
	 */
	public void deleteGenotyping(Genotyping genotyping_1);
}