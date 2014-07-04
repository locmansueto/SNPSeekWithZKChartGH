package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.domain.Genotyp700;
import org.irri.iric.portal.variety.domain.Snp700;

/**
 * Spring service that handles CRUD requests for Snp700 entities
 * 
 */
public interface Snp700Service {

	/**
	 */
	public Snp700 findSnp700ByPrimaryKey(Integer id);

	/**
	 * Return all Snp700 entity
	 * 
	 */
	public List<Snp700> findAllSnp700s(Integer startResult, Integer maxRows);

	/**
	 * Load an existing Snp700 entity
	 * 
	 */
	public Set<Snp700> loadSnp700s();

	/**
	 * Return a count of all Snp700 entity
	 * 
	 */
	public Integer countSnp700s();

	/**
	 * Save an existing Genotyp700 entity
	 * 
	 */
	public Snp700 saveSnp700Genotyp700s(Integer id_1, Genotyp700 related_genotyp700s);

	/**
	 * Delete an existing Snp700 entity
	 * 
	 */
	public void deleteSnp700(Snp700 snp700);

	/**
	 * Save an existing Snp700 entity
	 * 
	 */
	public void saveSnp700(Snp700 snp700_1);

	/**
	 * Delete an existing Genotyp700 entity
	 * 
	 */
	public Snp700 deleteSnp700Genotyp700s(Integer snp700_id, Integer related_genotyp700s_varId, Integer related_genotyp700s_snpId);
}