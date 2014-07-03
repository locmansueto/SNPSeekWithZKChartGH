package org.irri.iric.portal.genotype.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.genotype.domain.Gene;

/**
 * Spring service that handles CRUD requests for Gene entities
 * 
 */
public interface GeneService {

	/**
	 * Return all Gene entity
	 * 
	 */
	public List<Gene> findAllGenes(Integer startResult, Integer maxRows);

	/**
	 * Return a count of all Gene entity
	 * 
	 */
	public Integer countGenes();

	/**
	 * Delete an existing Gene entity
	 * 
	 */
	public void deleteGene(Gene gene);

	/**
	 */
	public Gene findGeneByPrimaryKey(Integer featureId);

	/**
	 * Save an existing Gene entity
	 * 
	 */
	public void saveGene(Gene gene_1);

	/**
	 * Load an existing Gene entity
	 * 
	 */
	public Set<Gene> loadGenes();
	
	
	public Gene findGeneByName(String name);
	
}