package org.irri.iric.portal.chado.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VSnpAllvars;

/**
 * Spring service that handles CRUD requests for VSnpAllvars entities
 * 
 */
public interface VSnpAllvarsService {

	/**
	 * Delete an existing VSnpAllvars entity
	 * 
	 */
	public void deleteVSnpAllvars(VSnpAllvars vsnpallvars);

	/**
	 * Return all VSnpAllvars entity
	 * 
	 */
	public List<VSnpAllvars> findAllVSnpAllvarss(Integer startResult, Integer maxRows);

	/**
	 * Save an existing VSnpAllvars entity
	 * 
	 */
	public void saveVSnpAllvars(VSnpAllvars vsnpallvars_1);

	/**
	 * Load an existing VSnpAllvars entity
	 * 
	 */
	public Set<VSnpAllvars> loadVSnpAllvarss();

	/**
	 */
	public VSnpAllvars findVSnpAllvarsByPrimaryKey(Integer snpGenotypeId);

	/**
	 * Return a count of all VSnpAllvars entity
	 * 
	 */
	public Integer countVSnpAllvarss();
}