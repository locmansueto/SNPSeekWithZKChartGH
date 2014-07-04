package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.domain.Names700;

/**
 * Spring service that handles CRUD requests for Names700 entities
 * 
 */
public interface Names700Service {

	/**
	 * Load an existing Names700 entity
	 * 
	 */
	public Set<Names700> loadNames700s();

	/**
	 * Return all Names700 entity
	 * 
	 */
	public List<Names700> findAllNames700s(Integer startResult, Integer maxRows);

	/**
	 * Save an existing Names700 entity
	 * 
	 */
	public void saveNames700(Names700 names700);

	/**
	 */
	public Names700 findNames700ByPrimaryKey(String id);

	/**
	 * Delete an existing Names700 entity
	 * 
	 */
	public void deleteNames700(Names700 names700_1);

	/**
	 * Return a count of all Names700 entity
	 * 
	 */
	public Integer countNames700s();
}