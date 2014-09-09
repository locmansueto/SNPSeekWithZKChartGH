package org.irri.iric.portal.genotype.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.genotype.domain.VlSnpAllvarsCntrefmismatch;

/**
 * Spring service that handles CRUD requests for VlSnpAllvarsCntrefmismatch entities
 * 
 */
public interface VlSnpAllvarsCntrefmismatchService {

	/**
	 * Save an existing VlSnpAllvarsCntrefmismatch entity
	 * 
	 */
	public void saveVlSnpAllvarsCntrefmismatch(VlSnpAllvarsCntrefmismatch vlsnpallvarscntrefmismatch);

	/**
	 * Return a count of all VlSnpAllvarsCntrefmismatch entity
	 * 
	 */
	public Integer countVlSnpAllvarsCntrefmismatchs();

	/**
	 */
	public VlSnpAllvarsCntrefmismatch findVlSnpAllvarsCntrefmismatchByPrimaryKey(Integer var);

	/**
	 * Delete an existing VlSnpAllvarsCntrefmismatch entity
	 * 
	 */
	public void deleteVlSnpAllvarsCntrefmismatch(VlSnpAllvarsCntrefmismatch vlsnpallvarscntrefmismatch_1);

	/**
	 * Return all VlSnpAllvarsCntrefmismatch entity
	 * 
	 */
	public List<VlSnpAllvarsCntrefmismatch> findAllVlSnpAllvarsCntrefmismatchs(Integer startResult, Integer maxRows);

	/**
	 * Load an existing VlSnpAllvarsCntrefmismatch entity
	 * 
	 */
	public Set<VlSnpAllvarsCntrefmismatch> loadVlSnpAllvarsCntrefmismatchs();
}