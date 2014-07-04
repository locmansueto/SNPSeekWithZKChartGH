package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.domain.Genotyping;
import org.irri.iric.portal.variety.domain.Snps;

/**
 * Spring service that handles CRUD requests for Snps entities
 * 
 */
public interface SnpsService {

	/**
	 * Return all Snps entity
	 * 
	 */
	public List<Snps> findAllSnpss(Integer startResult, Integer maxRows);

	/**
	 * Return a count of all Snps entity
	 * 
	 */
	public Integer countSnpss();

	/**
	 * Delete an existing Genotyping entity
	 * 
	 */
	public Snps deleteSnpsGenotypings(String snps_snpId, String related_genotypings_snpId, Integer related_genotypings_nsftvId);

	/**
	 * Load an existing Snps entity
	 * 
	 */
	public Set<Snps> loadSnpss();

	/**
	 * Save an existing Snps entity
	 * 
	 */
	public void saveSnps(Snps snps);

	/**
	 * Delete an existing Snps entity
	 * 
	 */
	public void deleteSnps(Snps snps_1);

	/**
	 */
	public Snps findSnpsByPrimaryKey(String snpId);

	/**
	 * Save an existing Genotyping entity
	 * 
	 */
	public Snps saveSnpsGenotypings(String snpId_1, Genotyping related_genotypings);
}