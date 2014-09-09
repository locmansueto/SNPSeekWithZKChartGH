package org.irri.iric.portal.chado.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.dao.VGeneDAO;
import org.irri.iric.portal.chado.domain.VGene;
import org.irri.iric.portal.domain.Gene;
import org.irri.iric.portal.service.GeneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for VGene entities
 * 
 */

@Service("GeneService")
@Transactional
public class VGeneServiceImpl implements VGeneService, GeneService {

	/**
	 * DAO injected by Spring that manages VGene entities
	 * 
	 */
	@Autowired
	private VGeneDAO vGeneDAO;

	/**
	 * Instantiates a new VGeneServiceImpl.
	 *
	 */
	public VGeneServiceImpl() {
	}

	/**
	 * Save an existing VGene entity
	 * 
	 */
	@Transactional
	public void saveVGene(VGene vgene) {
		VGene existingVGene = vGeneDAO.findVGeneByPrimaryKey(vgene.getGeneId());

		if (existingVGene != null) {
			if (existingVGene != vgene) {
				existingVGene.setGeneId(vgene.getGeneId());
				existingVGene.setName(vgene.getName());
				existingVGene.setChr(vgene.getChr());
				existingVGene.setFmin(vgene.getFmin());
				existingVGene.setFmax(vgene.getFmax());
				existingVGene.setStrand(vgene.getStrand());
				existingVGene.setPhase(vgene.getPhase());
			}
			vgene = vGeneDAO.store(existingVGene);
		} else {
			vgene = vGeneDAO.store(vgene);
		}
		vGeneDAO.flush();
	}

	/**
	 * Return all VGene entity
	 * 
	 */
	@Transactional
	public List<VGene> findAllVGenes(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<VGene>(vGeneDAO.findAllVGenes(startResult, maxRows));
	}

	/**
	 * Return a count of all VGene entity
	 * 
	 */
	@Transactional
	public Integer countVGenes() {
		return ((Long) vGeneDAO.createQuerySingleResult("select count(o) from VGene o").getSingleResult()).intValue();
	}

	/**
	 */
	@Transactional
	public VGene findVGeneByPrimaryKey(Integer geneId) {
		return vGeneDAO.findVGeneByPrimaryKey(geneId);
	}

	/**
	 * Load an existing VGene entity
	 * 
	 */
	@Transactional
	public Set<VGene> loadVGenes() {
		return vGeneDAO.findAllVGenes();
	}

	/**
	 * Delete an existing VGene entity
	 * 
	 */
	@Transactional
	public void deleteVGene(VGene vgene) {
		vGeneDAO.remove(vgene);
		vGeneDAO.flush();
	}

	@Override
	public Gene findGeneByName(String name) {
		// TODO vGeneDAO-generated method stub
		Set setGene =  vGeneDAO.findVGeneByName(name);
		if(setGene.isEmpty()) return null;
		if(setGene.size()>1) throw new RuntimeException("Multiple genes with name " + name);
		return (Gene)setGene.iterator().next();
	}
	
	
	
}
