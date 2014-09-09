package org.irri.iric.portal.chado.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VSnp2varsCountmismatch;

/**
 * Spring service that handles CRUD requests for VSnp2varsCountmismatch entities
 * 
 */
public interface VSnp2varsCountmismatchService {

	/**
	 */
	public VSnp2varsCountmismatch findVSnp2varsCountmismatchByPrimaryKey(Integer var1, Integer var2);

	/**
	 * Return all VSnp2varsCountmismatch entity
	 * 
	 */
	public List<VSnp2varsCountmismatch> findAllVSnp2varsCountmismatchs(Integer startResult, Integer maxRows);

	/**
	 * Delete an existing VSnp2varsCountmismatch entity
	 * 
	 */
	public void deleteVSnp2varsCountmismatch(VSnp2varsCountmismatch vsnp2varscountmismatch);

	/**
	 * Return a count of all VSnp2varsCountmismatch entity
	 * 
	 */
	public Integer countVSnp2varsCountmismatchs();

	/**
	 * Load an existing VSnp2varsCountmismatch entity
	 * 
	 */
	public Set<VSnp2varsCountmismatch> loadVSnp2varsCountmismatchs();

	/**
	 * Save an existing VSnp2varsCountmismatch entity
	 * 
	 */
	public void saveVSnp2varsCountmismatch(VSnp2varsCountmismatch vsnp2varscountmismatch_1);
}