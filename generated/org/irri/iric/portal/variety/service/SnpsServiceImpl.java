package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.dao.GenotypingDAO;
import org.irri.iric.portal.variety.dao.SnpsDAO;

import org.irri.iric.portal.variety.domain.Genotyping;
import org.irri.iric.portal.variety.domain.Snps;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for Snps entities
 * 
 */

@Service("SnpsService")
@Transactional
public class SnpsServiceImpl implements SnpsService {

	/**
	 * DAO injected by Spring that manages Genotyping entities
	 * 
	 */
	@Autowired
	private GenotypingDAO genotypingDAO;

	/**
	 * DAO injected by Spring that manages Snps entities
	 * 
	 */
	@Autowired
	private SnpsDAO snpsDAO;

	/**
	 * Instantiates a new SnpsServiceImpl.
	 *
	 */
	public SnpsServiceImpl() {
	}

	/**
	 * Return all Snps entity
	 * 
	 */
	@Transactional
	public List<Snps> findAllSnpss(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<Snps>(snpsDAO.findAllSnpss(startResult, maxRows));
	}

	/**
	 * Return a count of all Snps entity
	 * 
	 */
	@Transactional
	public Integer countSnpss() {
		return ((Long) snpsDAO.createQuerySingleResult("select count(o) from Snps o").getSingleResult()).intValue();
	}

	/**
	 * Delete an existing Genotyping entity
	 * 
	 */
	@Transactional
	public Snps deleteSnpsGenotypings(String snps_snpId, String related_genotypings_snpId, Integer related_genotypings_nsftvId) {
		Genotyping related_genotypings = genotypingDAO.findGenotypingByPrimaryKey(related_genotypings_snpId, related_genotypings_nsftvId, -1, -1);

		Snps snps = snpsDAO.findSnpsByPrimaryKey(snps_snpId, -1, -1);

		related_genotypings.setSnps(null);
		snps.getGenotypings().remove(related_genotypings);

		genotypingDAO.remove(related_genotypings);
		genotypingDAO.flush();

		return snps;
	}

	/**
	 * Load an existing Snps entity
	 * 
	 */
	@Transactional
	public Set<Snps> loadSnpss() {
		return snpsDAO.findAllSnpss();
	}

	/**
	 * Save an existing Snps entity
	 * 
	 */
	@Transactional
	public void saveSnps(Snps snps) {
		Snps existingSnps = snpsDAO.findSnpsByPrimaryKey(snps.getSnpId());

		if (existingSnps != null) {
			if (existingSnps != snps) {
				existingSnps.setSnpId(snps.getSnpId());
				existingSnps.setChrom(snps.getChrom());
				existingSnps.setPos(snps.getPos());
				existingSnps.setMaxal(snps.getMaxal());
				existingSnps.setMinal(snps.getMinal());
			}
			snps = snpsDAO.store(existingSnps);
		} else {
			snps = snpsDAO.store(snps);
		}
		snpsDAO.flush();
	}

	/**
	 * Delete an existing Snps entity
	 * 
	 */
	@Transactional
	public void deleteSnps(Snps snps) {
		snpsDAO.remove(snps);
		snpsDAO.flush();
	}

	/**
	 */
	@Transactional
	public Snps findSnpsByPrimaryKey(String snpId) {
		return snpsDAO.findSnpsByPrimaryKey(snpId);
	}

	/**
	 * Save an existing Genotyping entity
	 * 
	 */
	@Transactional
	public Snps saveSnpsGenotypings(String snpId, Genotyping related_genotypings) {
		Snps snps = snpsDAO.findSnpsByPrimaryKey(snpId, -1, -1);
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

		related_genotypings.setSnps(snps);
		snps.getGenotypings().add(related_genotypings);
		related_genotypings = genotypingDAO.store(related_genotypings);
		genotypingDAO.flush();

		snps = snpsDAO.store(snps);
		snpsDAO.flush();

		return snps;
	}
}
