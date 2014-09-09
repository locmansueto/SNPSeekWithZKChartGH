package org.irri.iric.portal.genotype.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.genotype.domain.VlSnpAllvars;

/**
 * Spring service that handles CRUD requests for VlSnpAllvars entities
 * 
 */
public interface VlSnpAllvarsService {

	/**
	 * Delete an existing VlSnpAllvars entity
	 * 
	 */
	public void deleteVlSnpAllvars(VlSnpAllvars vlsnpallvars);

	/**
	 * Return all VlSnpAllvars entity
	 * 
	 */
	public List<VlSnpAllvars> findAllVlSnpAllvarss(Integer startResult, Integer maxRows);

	/**
	 * Load an existing VlSnpAllvars entity
	 * 
	 */
	public Set<VlSnpAllvars> loadVlSnpAllvarss();

	/**
	 */
	public VlSnpAllvars findVlSnpAllvarsByPrimaryKey(Integer snpGenotypeId);

	/**
	 * Return a count of all VlSnpAllvars entity
	 * 
	 */
	public Integer countVlSnpAllvarss();

	/**
	 * Save an existing VlSnpAllvars entity
	 * 
	 */
	public void saveVlSnpAllvars(VlSnpAllvars vlsnpallvars_1);
}