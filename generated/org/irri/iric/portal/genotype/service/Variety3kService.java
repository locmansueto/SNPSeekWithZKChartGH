package org.irri.iric.portal.genotype.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.genotype.domain.Variety3k;

/**
 * Spring service that handles CRUD requests for Variety3k entities
 * 
 */
public interface Variety3kService {

	/**
	 * Return a count of all Variety3k entity
	 * 
	 */
	public Integer countVariety3ks();

	/**
	 * Load an existing Variety3k entity
	 * 
	 */
	public Set<Variety3k> loadVariety3ks();

	/**
	 * Save an existing Variety3k entity
	 * 
	 */
	public void saveVariety3k(Variety3k variety3k);

	/**
	 * Delete an existing Variety3k entity
	 * 
	 */
	public void deleteVariety3k(Variety3k variety3k_1);

	/**
	 * Return all Variety3k entity
	 * 
	 */
	public List<Variety3k> findAllVariety3ks(Integer startResult, Integer maxRows);

	/**
	 */
	public Variety3k findVariety3kByPrimaryKey(String varname);
}