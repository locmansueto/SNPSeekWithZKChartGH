package org.irri.iric.portal.chado.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VGene;

/**
 * Spring service that handles CRUD requests for VGene entities
 * 
 */
public interface VGeneService {

	/**
	 * Save an existing VGene entity
	 * 
	 */
	public void saveVGene(VGene vgene);

	/**
	 * Return all VGene entity
	 * 
	 */
	public List<VGene> findAllVGenes(Integer startResult, Integer maxRows);

	/**
	 * Return a count of all VGene entity
	 * 
	 */
	public Integer countVGenes();

	/**
	 */
	public VGene findVGeneByPrimaryKey(Integer geneId);

	/**
	 * Load an existing VGene entity
	 * 
	 */
	public Set<VGene> loadVGenes();

	/**
	 * Delete an existing VGene entity
	 * 
	 */
	public void deleteVGene(VGene vgene_1);
}