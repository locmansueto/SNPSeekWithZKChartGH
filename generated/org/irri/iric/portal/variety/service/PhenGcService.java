package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.domain.PhenGc;

/**
 * Spring service that handles CRUD requests for PhenGc entities
 * 
 */
public interface PhenGcService {

	/**
	 * Save an existing PhenGc entity
	 * 
	 */
	public void savePhenGc(PhenGc phengc);

	/**
	 * Return all PhenGc entity
	 * 
	 */
	public List<PhenGc> findAllPhenGcs(Integer startResult, Integer maxRows);

	/**
	 * Load an existing PhenGc entity
	 * 
	 */
	public Set<PhenGc> loadPhenGcs();

	/**
	 */
	public PhenGc findPhenGcByPrimaryKey(Integer entno, Integer gid);

	/**
	 * Return a count of all PhenGc entity
	 * 
	 */
	public Integer countPhenGcs();

	/**
	 * Delete an existing PhenGc entity
	 * 
	 */
	public void deletePhenGc(PhenGc phengc_1);
}