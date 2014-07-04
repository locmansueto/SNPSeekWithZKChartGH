package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.dao.GenotypingDAO;
import org.irri.iric.portal.variety.dao.GermplasmDAO;
import org.irri.iric.portal.variety.dao.PhenotypesDAO;

import org.irri.iric.portal.variety.domain.Genotyping;
import org.irri.iric.portal.variety.domain.Germplasm;
import org.irri.iric.portal.variety.domain.Phenotypes;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for Germplasm entities
 * 
 */

@Service("GermplasmService")
@Transactional
public class GermplasmServiceImpl implements GermplasmService {

	/**
	 * DAO injected by Spring that manages Genotyping entities
	 * 
	 */
	@Autowired
	private GenotypingDAO genotypingDAO;

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
	 * Instantiates a new GermplasmServiceImpl.
	 *
	 */
	public GermplasmServiceImpl() {
	}

	/**
	 * Return a count of all Germplasm entity
	 * 
	 */
	@Transactional
	public Integer countGermplasms() {
		return ((Long) germplasmDAO.createQuerySingleResult("select count(o) from Germplasm o").getSingleResult()).intValue();
	}

	/**
	 * Return all Germplasm entity
	 * 
	 */
	@Transactional
	public List<Germplasm> findAllGermplasms(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<Germplasm>(germplasmDAO.findAllGermplasms(startResult, maxRows));
	}

	/**
	 * Delete an existing Phenotypes entity
	 * 
	 */
	@Transactional
	public Germplasm deleteGermplasmPhenotypeses(Integer germplasm_nsftvId, Integer related_phenotypeses_nsftvId, String related_phenotypeses_trait) {
		Phenotypes related_phenotypeses = phenotypesDAO.findPhenotypesByPrimaryKey(related_phenotypeses_nsftvId, related_phenotypeses_trait, -1, -1);

		Germplasm germplasm = germplasmDAO.findGermplasmByPrimaryKey(germplasm_nsftvId, -1, -1);

		related_phenotypeses.setGermplasm(null);
		germplasm.getPhenotypeses().remove(related_phenotypeses);

		phenotypesDAO.remove(related_phenotypeses);
		phenotypesDAO.flush();

		return germplasm;
	}

	/**
	 * Delete an existing Genotyping entity
	 * 
	 */
	@Transactional
	public Germplasm deleteGermplasmGenotypings(Integer germplasm_nsftvId, String related_genotypings_snpId, Integer related_genotypings_nsftvId) {
		Genotyping related_genotypings = genotypingDAO.findGenotypingByPrimaryKey(related_genotypings_snpId, related_genotypings_nsftvId, -1, -1);

		Germplasm germplasm = germplasmDAO.findGermplasmByPrimaryKey(germplasm_nsftvId, -1, -1);

		related_genotypings.setGermplasm(null);
		germplasm.getGenotypings().remove(related_genotypings);

		genotypingDAO.remove(related_genotypings);
		genotypingDAO.flush();

		return germplasm;
	}

	/**
	 * Delete an existing Germplasm entity
	 * 
	 */
	@Transactional
	public void deleteGermplasm(Germplasm germplasm) {
		germplasmDAO.remove(germplasm);
		germplasmDAO.flush();
	}

	/**
	 * Load an existing Germplasm entity
	 * 
	 */
	@Transactional
	public Set<Germplasm> loadGermplasms() {
		return germplasmDAO.findAllGermplasms();
	}

	/**
	 */
	@Transactional
	public Germplasm findGermplasmByPrimaryKey(Integer nsftvId) {
		return germplasmDAO.findGermplasmByPrimaryKey(nsftvId);
	}

	/**
	 * Save an existing Germplasm entity
	 * 
	 */
	@Transactional
	public void saveGermplasm(Germplasm germplasm) {
		Germplasm existingGermplasm = germplasmDAO.findGermplasmByPrimaryKey(germplasm.getNsftvId());

		if (existingGermplasm != null) {
			if (existingGermplasm != germplasm) {
				existingGermplasm.setNsftvId(germplasm.getNsftvId());
				existingGermplasm.setAccession(germplasm.getAccession());
				existingGermplasm.setCountry(germplasm.getCountry());
				existingGermplasm.setLatitude(germplasm.getLatitude());
				existingGermplasm.setLongitude(germplasm.getLongitude());
				existingGermplasm.setSubpopulation(germplasm.getSubpopulation());
			}
			germplasm = germplasmDAO.store(existingGermplasm);
		} else {
			germplasm = germplasmDAO.store(germplasm);
		}
		germplasmDAO.flush();
	}

	/**
	 * Save an existing Genotyping entity
	 * 
	 */
	@Transactional
	public Germplasm saveGermplasmGenotypings(Integer nsftvId, Genotyping related_genotypings) {
		Germplasm germplasm = germplasmDAO.findGermplasmByPrimaryKey(nsftvId, -1, -1);
		Genotyping existinggenotypings = genotypingDAO.findGenotypingByPrimaryKey(related_genotypings.getSnpId(), related_genotypings.getNsftvId());

		// copy into the existing record to preserve existing relationships
		if (existinggenotypings != null) {
			existinggenotypings.setSnpId(related_genotypings.getSnpId());
			existinggenotypings.setNsftvId(related_genotypings.getNsftvId());
			existinggenotypings.setVar1(related_genotypings.getVar1());
			existinggenotypings.setVar2(related_genotypings.getVar2());
			related_genotypings = existinggenotypings;
		} else {
			related_genotypings = genotypingDAO.store(related_genotypings);
			genotypingDAO.flush();
		}

		related_genotypings.setGermplasm(germplasm);
		germplasm.getGenotypings().add(related_genotypings);
		related_genotypings = genotypingDAO.store(related_genotypings);
		genotypingDAO.flush();

		germplasm = germplasmDAO.store(germplasm);
		germplasmDAO.flush();

		return germplasm;
	}

	/**
	 * Save an existing Phenotypes entity
	 * 
	 */
	@Transactional
	public Germplasm saveGermplasmPhenotypeses(Integer nsftvId, Phenotypes related_phenotypeses) {
		Germplasm germplasm = germplasmDAO.findGermplasmByPrimaryKey(nsftvId, -1, -1);
		Phenotypes existingphenotypeses = phenotypesDAO.findPhenotypesByPrimaryKey(related_phenotypeses.getNsftvId(), related_phenotypeses.getTrait());

		// copy into the existing record to preserve existing relationships
		if (existingphenotypeses != null) {
			existingphenotypeses.setNsftvId(related_phenotypeses.getNsftvId());
			existingphenotypeses.setTrait(related_phenotypeses.getTrait());
			existingphenotypeses.setVal(related_phenotypeses.getVal());
			related_phenotypeses = existingphenotypeses;
		} else {
			related_phenotypeses = phenotypesDAO.store(related_phenotypeses);
			phenotypesDAO.flush();
		}

		related_phenotypeses.setGermplasm(germplasm);
		germplasm.getPhenotypeses().add(related_phenotypeses);
		related_phenotypeses = phenotypesDAO.store(related_phenotypeses);
		phenotypesDAO.flush();

		germplasm = germplasmDAO.store(germplasm);
		germplasmDAO.flush();

		return germplasm;
	}
}
