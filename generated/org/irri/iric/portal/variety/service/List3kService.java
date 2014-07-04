package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.domain.List3k;

/**
 * Spring service that handles CRUD requests for List3k entities
 * 
 */
public interface List3kService {

	/**
	 * Return all List3k entity
	 * 
	 */
	public List<List3k> findAllList3ks(Integer startResult, Integer maxRows);

	/**
	 * Delete an existing List3k entity
	 * 
	 */
	public void deleteList3k(List3k list3k);

	/**
	 */
	public List3k findList3kByPrimaryKey(String irisUniqueId);

	/**
	 * Save an existing List3k entity
	 * 
	 */
	public void saveList3k(List3k list3k_1);

	/**
	 * Return a count of all List3k entity
	 * 
	 */
	public Integer countList3ks();

	/**
	 * Load an existing List3k entity
	 * 
	 */
	public Set<List3k> loadList3ks();
}