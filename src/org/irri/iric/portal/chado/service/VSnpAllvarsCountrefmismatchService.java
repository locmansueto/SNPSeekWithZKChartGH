package org.irri.iric.portal.chado.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VSnpAllvarsCountrefmismatch;

/**
 * Spring service that handles CRUD requests for VSnpAllvarsCountrefmismatch entities
 * 
 */
public interface VSnpAllvarsCountrefmismatchService {

	/**
	 * Return all VSnpAllvarsCountrefmismatch entity
	 * 
	 */
	public List<VSnpAllvarsCountrefmismatch> findAllVSnpAllvarsCountrefmismatchs(Integer startResult, Integer maxRows);

	/**
	 * Delete an existing VSnpAllvarsCountrefmismatch entity
	 * 
	 */
	public void deleteVSnpAllvarsCountrefmismatch(VSnpAllvarsCountrefmismatch vsnpallvarscountrefmismatch);

	/**
	 * Return a count of all VSnpAllvarsCountrefmismatch entity
	 * 
	 */
	public Integer countVSnpAllvarsCountrefmismatchs();

	/**
	 * Load an existing VSnpAllvarsCountrefmismatch entity
	 * 
	 */
	public Set<VSnpAllvarsCountrefmismatch> loadVSnpAllvarsCountrefmismatchs();

	/**
	 */
	public VSnpAllvarsCountrefmismatch findVSnpAllvarsCountrefmismatchByPrimaryKey(Integer var);

	/**
	 * Save an existing VSnpAllvarsCountrefmismatch entity
	 * 
	 */
	public void saveVSnpAllvarsCountrefmismatch(VSnpAllvarsCountrefmismatch vsnpallvarscountrefmismatch_1);
}