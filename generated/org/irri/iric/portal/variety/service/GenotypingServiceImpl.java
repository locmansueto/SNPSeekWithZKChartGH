package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.dao.GenotypingDAO;
import org.irri.iric.portal.variety.dao.GermplasmDAO;
import org.irri.iric.portal.variety.dao.SnpsDAO;

import org.irri.iric.portal.variety.domain.Genotyping;
import org.irri.iric.portal.variety.domain.Germplasm;
import org.irri.iric.portal.variety.domain.Snps;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for Genotyping entities
 * 
 */

@Service("GenotypingService")
@Transactional
public class GenotypingServiceImpl implements GenotypingService {

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
	 * DAO injected by Spring that manages Snps entities
	 * 
	 */
	@Autowired
	private SnpsDAO snpsDAO;

	/**
	 * Instantiates a new GenotypingServiceImpl.
	 *
	 */
	public GenotypingServiceImpl() {
	}

	/**
	 */
	@Transactional
	public Genotyping findGenotypingByPrimaryKey(String snpId, Integer nsftvId) {
		return genotypingDAO.findGenotypingByPrimaryKey(snpId, nsftvId);
	}

	/**
	 * Delete an existing Snps entity
	 * 
	 */
	@Transactional
	public Genotyping deleteGenotypingSnps(String genotyping_snpId, Integer genotyping_nsftvId, String related_snps_snpId) {
		Genotyping genotyping = genotypingDAO.findGenotypingByPrimaryKey(genotyping_snpId, genotyping_nsftvId, -1, -1);
		Snps related_snps = snpsDAO.findSnpsByPrimaryKey(related_snps_snpId, -1, -1);

		genotyping.setSnps(null);
		related_snps.getGenotypings().remove(genotyping);
		genotyping = genotypingDAO.store(genotyping);
		genotypingDAO.flush();

		related_snps = snpsDAO.store(related_snps);
		snpsDAO.flush();

		snpsDAO.remove(related_snps);
		snpsDAO.flush();

		return genotyping;
	}

	/**
	 * Save an existing Genotyping entity
	 * 
	 */
	@Transactional
	public void saveGenotyping(Genotyping genotyping) {
		Genotyping existingGenotyping = genotypingDAO.findGenotypingByPrimaryKey(genotyping.getSnpId(), genotyping.getNsftvId());

		if (existingGenotyping != null) {
			if (existingGenotyping != genotyping) {
				existingGenotyping.setSnpId(genotyping.getSnpId());
				existingGenotyping.setNsftvId(genotyping.getNsftvId());
				existingGenotyping.setVar1(genotyping.getVar1());
				existingGenotyping.setVar2(genotyping.getVar2());
			}
			genotyping = genotypingDAO.store(existingGenotyping);
		} else {
			genotyping = genotypingDAO.store(genotyping);
		}
		genotypingDAO.flush();
	}

	/**
	 * Save an existing Germplasm entity
	 * 
	 */
	@Transactional
	public Genotyping saveGenotypingGermplasm(String snpId, Integer nsftvId, Germplasm related_germplasm) {
		Genotyping genotyping = genotypingDAO.findGenotypingByPrimaryKey(snpId, nsftvId, -1, -1);
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

		genotyping.setGermplasm(related_germplasm);
		related_germplasm.getGenotypings().add(genotyping);
		genotyping = genotypingDAO.store(genotyping);
		genotypingDAO.flush();

		related_germplasm = germplasmDAO.store(related_germplasm);
		germplasmDAO.flush();

		return genotyping;
	}

	/**
	 * Return all Genotyping entity
	 * 
	 */
	@Transactional
	public List<Genotyping> findAllGenotypings(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<Genotyping>(genotypingDAO.findAllGenotypings(startResult, maxRows));
	}

	/**
	 * Save an existing Snps entity
	 * 
	 */
	@Transactional
	public Genotyping saveGenotypingSnps(String snpId, Integer nsftvId, Snps related_snps) {
		Genotyping genotyping = genotypingDAO.findGenotypingByPrimaryKey(snpId, nsftvId, -1, -1);
		Snps existingsnps = snpsDAO.findSnpsByPrimaryKey(related_snps.getSnpId());

		// copy into the existing record to preserve existing relationships
		if (existingsnps != null) {
			existingsnps.setSnpId(related_snps.getSnpId());
			existingsnps.setChrom(related_snps.getChrom());
			existingsnps.setPos(related_snps.getPos());
			existingsnps.setMaxal(related_snps.getMaxal());
			existingsnps.setMinal(related_snps.getMinal());
			related_snps = existingsnps;
		} else {
			related_snps = snpsDAO.store(related_snps);
			snpsDAO.flush();
		}

		genotyping.setSnps(related_snps);
		related_snps.getGenotypings().add(genotyping);
		genotyping = genotypingDAO.store(genotyping);
		genotypingDAO.flush();

		related_snps = snpsDAO.store(related_snps);
		snpsDAO.flush();

		return genotyping;
	}

	/**
	 * Return a count of all Genotyping entity
	 * 
	 */
	@Transactional
	public Integer countGenotypings() {
		return ((Long) genotypingDAO.createQuerySingleResult("select count(*) from Genotyping o").getSingleResult()).intValue();
	}

	/**
	 * Load an existing Genotyping entity
	 * 
	 */
	@Transactional
	public Set<Genotyping> loadGenotypings() {
		return genotypingDAO.findAllGenotypings();
	}

	/**
	 * Delete an existing Germplasm entity
	 * 
	 */
	@Transactional
	public Genotyping deleteGenotypingGermplasm(String genotyping_snpId, Integer genotyping_nsftvId, Integer related_germplasm_nsftvId) {
		Genotyping genotyping = genotypingDAO.findGenotypingByPrimaryKey(genotyping_snpId, genotyping_nsftvId, -1, -1);
		Germplasm related_germplasm = germplasmDAO.findGermplasmByPrimaryKey(related_germplasm_nsftvId, -1, -1);

		genotyping.setGermplasm(null);
		related_germplasm.getGenotypings().remove(genotyping);
		genotyping = genotypingDAO.store(genotyping);
		genotypingDAO.flush();

		related_germplasm = germplasmDAO.store(related_germplasm);
		germplasmDAO.flush();

		germplasmDAO.remove(related_germplasm);
		germplasmDAO.flush();

		return genotyping;
	}

	/**
	 * Delete an existing Genotyping entity
	 * 
	 */
	@Transactional
	public void deleteGenotyping(Genotyping genotyping) {
		genotypingDAO.remove(genotyping);
		genotypingDAO.flush();
	}
}
