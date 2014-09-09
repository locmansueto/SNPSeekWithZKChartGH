package org.irri.iric.portal.genotype.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.genotype.domain.VlSnpPos;

/**
 * Spring service that handles CRUD requests for VlSnpPos entities
 * 
 */
public interface VlSnpPosService {

	/**
	 */
	public VlSnpPos findVlSnpPosByPrimaryKey(Integer chr, Integer pos);

	/**
	 * Return all VlSnpPos entity
	 * 
	 */
	public List<VlSnpPos> findAllVlSnpPoss(Integer startResult, Integer maxRows);

	/**
	 * Delete an existing VlSnpPos entity
	 * 
	 */
	public void deleteVlSnpPos(VlSnpPos vlsnppos);

	/**
	 * Save an existing VlSnpPos entity
	 * 
	 */
	public void saveVlSnpPos(VlSnpPos vlsnppos_1);

	/**
	 * Load an existing VlSnpPos entity
	 * 
	 */
	public Set<VlSnpPos> loadVlSnpPoss();

	/**
	 * Return a count of all VlSnpPos entity
	 * 
	 */
	public Integer countVlSnpPoss();
}