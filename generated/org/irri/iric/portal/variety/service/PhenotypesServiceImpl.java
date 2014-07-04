package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.dao.GermplasmDAO;
import org.irri.iric.portal.variety.dao.PhenotypesDAO;

import org.irri.iric.portal.variety.domain.Germplasm;
import org.irri.iric.portal.variety.domain.Phenotypes;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for Phenotypes entities
 * 
 */

@Service("PhenotypesService")
@Transactional
public class PhenotypesServiceImpl implements PhenotypesService {

	/**
	 * DAO injected by Spring that manages Germplasm entities
	 * 
	 */
	@Autowired
	private GermplasmDAO germplasmDAO;

	/**
	 * DAO injected by Spring that manages Phenotypes entities
	 * 
	 */
	@Autowired
	private PhenotypesDAO phenotypesDAO;

	/**
	 * Instantiates a new PhenotypesServiceImpl.
	 *
	 */
	public PhenotypesServiceImpl() {
	}

	/**
	 * Return a count of all Phenotypes entity
	 * 
	 */
	@Transactional
	public Integer countPhenotypess() {
		return ((Long) phenotypesDAO.createQuerySingleResult("select count(*) from Phenotypes o").getSingleResult()).intValue();
	}

	/**
	 * Save an existing Germplasm entity
	 * 
	 */
	@Transactional
	public Phenotypes savePhenotypesGermplasm(Integer nsftvId, String trait, Germplasm related_germplasm) {
		Phenotypes phenotypes = phenotypesDAO.findPhenotypesByPrimaryKey(nsftvId, trait, -1, -1);
		Germplasm existinggermplasm = germplasmDAO.findGermplasmByPrimaryKey(related_germplasm.getNsftvId());

		// copy into the existing record to preserve existing relationships
		if (existinggermplasm != null) {
			existinggermplasm.setNsftvId(related_germplasm.getNsftvId());
			existinggermplasm.setAccession(related_germplasm.getAccession());
			existinggermplasm.setCountry(related_germplasm.getCountry());
			existinggermplasm.setLatitude(related_germplasm.getLatitude());
			existinggermplasm.setLongitude(related_germplasm.getLongitude());
			existinggermplasm.setSubpopulation(related_germplasm.getSubpopulation());
			related_germplasm = existinggermplasm;
		} else {
			related_germplasm = germplasmDAO.store(related_germplasm);
			germplasmDAO.flush();
		}

		phenotypes.setGermplasm(related_germplasm);
		related_germplasm.getPhenotypeses().add(phenotypes);
		phenotypes = phenotypesDAO.store(phenotypes);
		phenotypesDAO.flush();

		related_germplasm = germplasmDAO.store(related_germplasm);
		germplasmDAO.flush();

		return phenotypes;
	}

	/**
	 * Load an existing Phenotypes entity
	 * 
	 */
	@Transactional
	public Set<Phenotypes> loadPhenotypess() {
		return phenotypesDAO.findAllPhenotypess();
	}

	/**
	 */
	@Transactional
	public Phenotypes findPhenotypesByPrimaryKey(Integer nsftvId, String trait) {
		return phenotypesDAO.findPhenotypesByPrimaryKey(nsftvId, trait);
	}

	/**
	 * Delete an existing Phenotypes entity
	 * 
	 */
	@Transactional
	public void deletePhenotypes(Phenotypes phenotypes) {
		phenotypesDAO.remove(phenotypes);
		phenotypesDAO.flush();
	}

	/**
	 * Return all Phenotypes entity
	 * 
	 */
	@Transactional
	public List<Phenotypes> findAllPhenotypess(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<Phenotypes>(phenotypesDAO.findAllPhenotypess(startResult, maxRows));
	}

	/**
	 * Save an existing Phenotypes entity
	 * 
	 */
	@Transactional
	public void savePhenotypes(Phenotypes phenotypes) {
		Phenotypes existingPhenotypes = phenotypesDAO.findPhenotypesByPrimaryKey(phenotypes.getNsftvId(), phenotypes.getTrait());

		if (existingPhenotypes != null) {
			if (existingPhenotypes != phenotypes) {
				existingPhenotypes.setNsftvId(phenotypes.getNsftvId());
				existingPhenotypes.setTrait(phenotypes.getTrait());
				existingPhenotypes.setVal(phenotypes.getVal());
			}
			phenotypes = phenotypesDAO.store(existingPhenotypes);
		} else {
			phenotypes = phenotypesDAO.store(phenotypes);
		}
		phenotypesDAO.flush();
	}

	/**
	 * Delete an existing Germplasm entity
	 * 
	 */
	@Transactional
	public Phenotypes deletePhenotypesGermplasm(Integer phenotypes_nsftvId, String phenotypes_trait, Integer related_germplasm_nsftvId) {
		Phenotypes phenotypes = phenotypesDAO.findPhenotypesByPrimaryKey(phenotypes_nsftvId, phenotypes_trait, -1, -1);
		Germplasm related_germplasm = germplasmDAO.findGermplasmByPrimaryKey(related_germplasm_nsftvId, -1, -1);

		phenotypes.setGermplasm(null);
		related_germplasm.getPhenotypeses().remove(phenotypes);
		phenotypes = phenotypesDAO.store(phenotypes);
		phenotypesDAO.flush();

		related_germplasm = germplasmDAO.store(related_germplasm);
		germplasmDAO.flush();

		germplasmDAO.remove(related_germplasm);
		germplasmDAO.flush();

		return phenotypes;
	}
}
