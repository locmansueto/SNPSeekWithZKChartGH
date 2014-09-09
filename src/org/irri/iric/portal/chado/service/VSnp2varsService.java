package org.irri.iric.portal.chado.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VSnp2vars;

/**
 * Spring service that handles CRUD requests for VSnp2vars entities
 * 
 */
public interface VSnp2varsService {

	/**
	 * Return a count of all VSnp2vars entity
	 * 
	 */
	public Integer countVSnp2varss();

	/**
	 * Load an existing VSnp2vars entity
	 * 
	 */
	public Set<VSnp2vars> loadVSnp2varss();

	/**
	 * Return all VSnp2vars entity
	 * 
	 */
	public List<VSnp2vars> findAllVSnp2varss(Integer startResult, Integer maxRows);

	/**
	 * Delete an existing VSnp2vars entity
	 * 
	 */
	public void deleteVSnp2vars(VSnp2vars vsnp2vars);

	/**
	 * Save an existing VSnp2vars entity
	 * 
	 */
	public void saveVSnp2vars(VSnp2vars vsnp2vars_1);

	/**
	 */
	public VSnp2vars findVSnp2varsByPrimaryKey(Integer var1, Integer var2, Integer snpFeatureId);
}