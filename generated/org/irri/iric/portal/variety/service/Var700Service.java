package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.domain.Genotyp700;
import org.irri.iric.portal.variety.domain.Var700;

/**
 * Spring service that handles CRUD requests for Var700 entities
 * 
 */
public interface Var700Service {

	/**
	 * Save an existing Var700 entity
	 * 
	 */
	public void saveVar700(Var700 var700);

	/**
	 * Load an existing Var700 entity
	 * 
	 */
	public Set<Var700> loadVar700s();

	/**
	 * Return a count of all Var700 entity
	 * 
	 */
	public Integer countVar700s();

	/**
	 * Return all Var700 entity
	 * 
	 */
	public List<Var700> findAllVar700s(Integer startResult, Integer maxRows);

	/**
	 */
	public Var700 findVar700ByPrimaryKey(Integer id);

	/**
	 * Save an existing Genotyp700 entity
	 * 
	 */
	public Var700 saveVar700Genotyp700s(Integer id_1, Genotyp700 related_genotyp700s);

	/**
	 * Delete an existing Genotyp700 entity
	 * 
	 */
	public Var700 deleteVar700Genotyp700s(Integer var700_id, Integer related_genotyp700s_varId, Integer related_genotyp700s_snpId);

	/**
	 * Delete an existing Var700 entity
	 * 
	 */
	public void deleteVar700(Var700 var700_1);
}