package org.irri.iric.portal.genotype.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.genotype.domain.VlSnp2vars;

/**
 * Spring service that handles CRUD requests for VlSnp2vars entities
 * 
 */
public interface VlSnp2varsService {

	/**
	 * Load an existing VlSnp2vars entity
	 * 
	 */
	public Set<VlSnp2vars> loadVlSnp2varss();

	/**
	 * Return all VlSnp2vars entity
	 * 
	 */
	public List<VlSnp2vars> findAllVlSnp2varss(Integer startResult, Integer maxRows);

	/**
	 * Save an existing VlSnp2vars entity
	 * 
	 */
	public void saveVlSnp2vars(VlSnp2vars vlsnp2vars);

	/**
	 * Delete an existing VlSnp2vars entity
	 * 
	 */
	public void deleteVlSnp2vars(VlSnp2vars vlsnp2vars_1);

	/**
	 */
	public VlSnp2vars findVlSnp2varsByPrimaryKey(Integer var1, Integer var2, Integer snpFeatureId);

	/**
	 * Return a count of all VlSnp2vars entity
	 * 
	 */
	public Integer countVlSnp2varss();
}