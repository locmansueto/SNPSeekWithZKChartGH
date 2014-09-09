package org.irri.iric.portal.genotype.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.genotype.domain.VlSnp2varsCountmismatch;

/**
 * Spring service that handles CRUD requests for VlSnp2varsCountmismatch entities
 * 
 */
public interface VlSnp2varsCountmismatchService {

	/**
	 */
	public VlSnp2varsCountmismatch findVlSnp2varsCountmismatchByPrimaryKey(Integer var1, Integer var2);

	/**
	 * Load an existing VlSnp2varsCountmismatch entity
	 * 
	 */
	public Set<VlSnp2varsCountmismatch> loadVlSnp2varsCountmismatchs();

	/**
	 * Return a count of all VlSnp2varsCountmismatch entity
	 * 
	 */
	public Integer countVlSnp2varsCountmismatchs();

	/**
	 * Delete an existing VlSnp2varsCountmismatch entity
	 * 
	 */
	public void deleteVlSnp2varsCountmismatch(VlSnp2varsCountmismatch vlsnp2varscountmismatch);

	/**
	 * Return all VlSnp2varsCountmismatch entity
	 * 
	 */
	public List<VlSnp2varsCountmismatch> findAllVlSnp2varsCountmismatchs(Integer startResult, Integer maxRows);

	/**
	 * Save an existing VlSnp2varsCountmismatch entity
	 * 
	 */
	public void saveVlSnp2varsCountmismatch(VlSnp2varsCountmismatch vlsnp2varscountmismatch_1);
}