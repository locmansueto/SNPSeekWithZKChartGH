package org.irri.iric.portal.genotype.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.genotype.domain.VlVariety3k;

/**
 * Spring service that handles CRUD requests for VlVariety3k entities
 * 
 */
public interface VlVariety3kService {

	/**
	 * Return a count of all VlVariety3k entity
	 * 
	 */
	public Integer countVlVariety3ks();

	/**
	 * Return all VlVariety3k entity
	 * 
	 */
	public List<VlVariety3k> findAllVlVariety3ks(Integer startResult, Integer maxRows);

	/**
	 */
	public VlVariety3k findVlVariety3kByPrimaryKey(Integer varId);

	/**
	 * Delete an existing VlVariety3k entity
	 * 
	 */
	public void deleteVlVariety3k(VlVariety3k vlvariety3k);

	/**
	 * Load an existing VlVariety3k entity
	 * 
	 */
	public Set<VlVariety3k> loadVlVariety3ks();

	/**
	 * Save an existing VlVariety3k entity
	 * 
	 */
	public void saveVlVariety3k(VlVariety3k vlvariety3k_1);
}