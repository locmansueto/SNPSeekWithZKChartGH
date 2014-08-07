package org.irri.iric.portal.genotype.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.genotype.dao.GeneDAO;

import org.irri.iric.portal.genotype.domain.Gene;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for Gene entities
 * 
 */

@Service("GeneService")
@Transactional
public class GeneServiceImpl implements GeneService {

	/**
	 * DAO injected by Spring that manages Gene entities
	 * 
	 */
	@Autowired
	private GeneDAO geneDAO;

	/**
	 * Instantiates a new GeneServiceImpl.
	 *
	 */
	public GeneServiceImpl() {
	}

	/**
	 * Return all Gene entity
	 * 
	 */
	@Transactional
	public List<Gene> findAllGenes(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<Gene>(geneDAO.findAllGenes(startResult, maxRows));
	}

	/**
	 * Return a count of all Gene entity
	 * 
	 */
	@Transactional
	public Integer countGenes() {
		return ((Long) geneDAO.createQuerySingleResult("select count(o) from Gene o").getSingleResult()).intValue();
	}

	/**
	 * Delete an existing Gene entity
	 * 
	 */
	@Transactional
	public void deleteGene(Gene gene) {
		geneDAO.remove(gene);
		geneDAO.flush();
	}

	/**
	 */
	@Transactional
	public Gene findGeneByPrimaryKey(Integer featureId) {
		return geneDAO.findGeneByPrimaryKey(featureId);
	}

	/**
	 */
	@Transactional
	public Gene findGeneByName(String name) {
		Set ret= geneDAO.findGeneByUniquename(name);
		
		if(ret.size()>1)
		{
			System.out.println(">1 gene with name " + name);
			return (Gene)ret.iterator().next();
		} else if (ret.size()==0)
			throw new RuntimeException("findGeneByUniquename.size==0 " + name);
		else
			return (Gene)ret.iterator().next();
			
	}
	
	/**
	 * Save an existing Gene entity
	 * 
	 */
	@Transactional
	public void saveGene(Gene gene) {
		Gene existingGene = geneDAO.findGeneByPrimaryKey(gene.getFeatureId());

		if (existingGene != null) {
			if (existingGene != gene) {
				existingGene.setFeatureId(gene.getFeatureId());
				existingGene.setGeneField(gene.getGeneField());
				existingGene.setUniquename(gene.getUniquename());
				existingGene.setFmin(gene.getFmin());
				existingGene.setFmax(gene.getFmax());
				existingGene.setChr(gene.getChr());
			}
			gene = geneDAO.store(existingGene);
		} else {
			gene = geneDAO.store(gene);
		}
		geneDAO.flush();
	}

	/**
	 * Load an existing Gene entity
	 * 
	 */
	@Transactional
	public Set<Gene> loadGenes() {
		return geneDAO.findAllGenes();
	}
}
