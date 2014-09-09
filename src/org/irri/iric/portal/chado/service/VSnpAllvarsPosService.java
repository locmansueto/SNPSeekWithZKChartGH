package org.irri.iric.portal.chado.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VSnpAllvarsPos;

/**
 * Spring service that handles CRUD requests for VSnpAllvarsPos entities
 * 
 */
public interface VSnpAllvarsPosService {

	/**
	 * Return all VSnpAllvarsPos entity
	 * 
	 */
	public List<VSnpAllvarsPos> findAllVSnpAllvarsPoss(Integer startResult, Integer maxRows);

	/**
	 * Delete an existing VSnpAllvarsPos entity
	 * 
	 */
	public void deleteVSnpAllvarsPos(VSnpAllvarsPos vsnpallvarspos);

	/**
	 */
	public VSnpAllvarsPos findVSnpAllvarsPosByPrimaryKey(Integer snpFeatureId);

	/**
	 * Return a count of all VSnpAllvarsPos entity
	 * 
	 */
	public Integer countVSnpAllvarsPoss();

	/**
	 * Load an existing VSnpAllvarsPos entity
	 * 
	 */
	public Set<VSnpAllvarsPos> loadVSnpAllvarsPoss();

	/**
	 * Save an existing VSnpAllvarsPos entity
	 * 
	 */
	public void saveVSnpAllvarsPos(VSnpAllvarsPos vsnpallvarspos_1);
}