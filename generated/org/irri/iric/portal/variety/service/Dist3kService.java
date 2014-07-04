package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.domain.Dist3k;

/**
 * Spring service that handles CRUD requests for Dist3k entities
 * 
 */
public interface Dist3kService {

	/**
	 * Return all Dist3k entity
	 * 
	 */
	public List<Dist3k> findAllDist3ks(Integer startResult, Integer maxRows);

	/**
	 * Load an existing Dist3k entity
	 * 
	 */
	public Set<Dist3k> loadDist3ks();

	/**
	 * Return a count of all Dist3k entity
	 * 
	 */
	public Integer countDist3ks();

	/**
	 * Save an existing Dist3k entity
	 * 
	 */
	public void saveDist3k(Dist3k dist3k);

	/**
	 * Delete an existing Dist3k entity
	 * 
	 */
	public void deleteDist3k(Dist3k dist3k_1);

	/**
	 */
	public Dist3k findDist3kByPrimaryKey(String nam1, String nam2);
}