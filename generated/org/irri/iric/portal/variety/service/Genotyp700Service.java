package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.domain.Genotyp700;
import org.irri.iric.portal.variety.domain.Snp700;
import org.irri.iric.portal.variety.domain.Var700;

/**
 * Spring service that handles CRUD requests for Genotyp700 entities
 * 
 */
public interface Genotyp700Service {

	/**
	 * Return a count of all Genotyp700 entity
	 * 
	 */
	public Integer countGenotyp700s();

	/**
	 * Delete an existing Genotyp700 entity
	 * 
	 */
	public void deleteGenotyp700(Genotyp700 genotyp700);

	/**
	 * Return all Genotyp700 entity
	 * 
	 */
	public List<Genotyp700> findAllGenotyp700s(Integer startResult, Integer maxRows);

	/**
	 * Load an existing Genotyp700 entity
	 * 
	 */
	public Set<Genotyp700> loadGenotyp700s();

	/**
	 */
	public Genotyp700 findGenotyp700ByPrimaryKey(Integer varId, Integer snpId);

	/**
	 * Save an existing Snp700 entity
	 * 
	 */
	public Genotyp700 saveGenotyp700Snp700(Integer varId_1, Integer snpId_1, Snp700 related_snp700);

	/**
	 * Save an existing Var700 entity
	 * 
	 */
	public Genotyp700 saveGenotyp700Var700(Integer varId_2, Integer snpId_2, Var700 related_var700);

	/**
	 * Delete an existing Snp700 entity
	 * 
	 */
	public Genotyp700 deleteGenotyp700Snp700(Integer genotyp700_varId, Integer genotyp700_snpId, Integer related_snp700_id);

	/**
	 * Delete an existing Var700 entity
	 * 
	 */
	public Genotyp700 deleteGenotyp700Var700(Integer genotyp700_varId_1, Integer genotyp700_snpId_1, Integer related_var700_id);

	/**
	 * Save an existing Genotyp700 entity
	 * 
	 */
	public void saveGenotyp700(Genotyp700 genotyp700_1);
}