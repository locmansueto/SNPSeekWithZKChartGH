package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.dao.Genotyp700DAO;
import org.irri.iric.portal.variety.dao.Snp700DAO;
import org.irri.iric.portal.variety.dao.Var700DAO;

import org.irri.iric.portal.variety.domain.Genotyp700;
import org.irri.iric.portal.variety.domain.Snp700;
import org.irri.iric.portal.variety.domain.Var700;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for Genotyp700 entities
 * 
 */

@Service("Genotyp700Service")
@Transactional
public class Genotyp700ServiceImpl implements Genotyp700Service {

	/**
	 * DAO injected by Spring that manages Genotyp700 entities
	 * 
	 */
	@Autowired
	private Genotyp700DAO genotyp700DAO;

	/**
	 * DAO injected by Spring that manages Snp700 entities
	 * 
	 */
	@Autowired
	private Snp700DAO snp700DAO;

	/**
	 * DAO injected by Spring that manages Var700 entities
	 * 
	 */
	@Autowired
	private Var700DAO var700DAO;

	/**
	 * Instantiates a new Genotyp700ServiceImpl.
	 *
	 */
	public Genotyp700ServiceImpl() {
	}

	/**
	 * Return a count of all Genotyp700 entity
	 * 
	 */
	@Transactional
	public Integer countGenotyp700s() {
		return ((Long) genotyp700DAO.createQuerySingleResult("select count(*) from Genotyp700 o").getSingleResult()).intValue();
	}

	/**
	 * Delete an existing Genotyp700 entity
	 * 
	 */
	@Transactional
	public void deleteGenotyp700(Genotyp700 genotyp700) {
		genotyp700DAO.remove(genotyp700);
		genotyp700DAO.flush();
	}

	/**
	 * Return all Genotyp700 entity
	 * 
	 */
	@Transactional
	public List<Genotyp700> findAllGenotyp700s(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<Genotyp700>(genotyp700DAO.findAllGenotyp700s(startResult, maxRows));
	}

	/**
	 * Load an existing Genotyp700 entity
	 * 
	 */
	@Transactional
	public Set<Genotyp700> loadGenotyp700s() {
		return genotyp700DAO.findAllGenotyp700s();
	}

	/**
	 */
	@Transactional
	public Genotyp700 findGenotyp700ByPrimaryKey(Integer varId, Integer snpId) {
		return genotyp700DAO.findGenotyp700ByPrimaryKey(varId, snpId);
	}

	/**
	 * Save an existing Snp700 entity
	 * 
	 */
	@Transactional
	public Genotyp700 saveGenotyp700Snp700(Integer varId, Integer snpId, Snp700 related_snp700) {
		Genotyp700 genotyp700 = genotyp700DAO.findGenotyp700ByPrimaryKey(varId, snpId, -1, -1);
		Snp700 existingsnp700 = snp700DAO.findSnp700ByPrimaryKey(related_snp700.getId());

		// copy into the existing record to preserve existing relationships
		if (existingsnp700 != null) {
			existingsnp700.setId(related_snp700.getId());
			existingsnp700.setChr(related_snp700.getChr());
			existingsnp700.setPos(related_snp700.getPos());
			existingsnp700.setName(related_snp700.getName());
			existingsnp700.setRef(related_snp700.getRef());
			existingsnp700.setAlt(related_snp700.getAlt());
			existingsnp700.setQscore(related_snp700.getQscore());
			existingsnp700.setFilter(related_snp700.getFilter());
			existingsnp700.setInfo(related_snp700.getInfo());
			existingsnp700.setFormat(related_snp700.getFormat());
			existingsnp700.setHet(related_snp700.getHet());
			existingsnp700.setAvgQscore(related_snp700.getAvgQscore());
			related_snp700 = existingsnp700;
		} else {
			related_snp700 = snp700DAO.store(related_snp700);
			snp700DAO.flush();
		}

		genotyp700.setSnp700(related_snp700);
		related_snp700.getGenotyp700s().add(genotyp700);
		genotyp700 = genotyp700DAO.store(genotyp700);
		genotyp700DAO.flush();

		related_snp700 = snp700DAO.store(related_snp700);
		snp700DAO.flush();

		return genotyp700;
	}

	/**
	 * Save an existing Var700 entity
	 * 
	 */
	@Transactional
	public Genotyp700 saveGenotyp700Var700(Integer varId, Integer snpId, Var700 related_var700) {
		Genotyp700 genotyp700 = genotyp700DAO.findGenotyp700ByPrimaryKey(varId, snpId, -1, -1);
		Var700 existingvar700 = var700DAO.findVar700ByPrimaryKey(related_var700.getId());

		// copy into the existing record to preserve existing relationships
		if (existingvar700 != null) {
			existingvar700.setId(related_var700.getId());
			existingvar700.setName(related_var700.getName());
			related_var700 = existingvar700;
		} else {
			related_var700 = var700DAO.store(related_var700);
			var700DAO.flush();
		}

		genotyp700.setVar700(related_var700);
		related_var700.getGenotyp700s().add(genotyp700);
		genotyp700 = genotyp700DAO.store(genotyp700);
		genotyp700DAO.flush();

		related_var700 = var700DAO.store(related_var700);
		var700DAO.flush();

		return genotyp700;
	}

	/**
	 * Delete an existing Snp700 entity
	 * 
	 */
	@Transactional
	public Genotyp700 deleteGenotyp700Snp700(Integer genotyp700_varId, Integer genotyp700_snpId, Integer related_snp700_id) {
		Genotyp700 genotyp700 = genotyp700DAO.findGenotyp700ByPrimaryKey(genotyp700_varId, genotyp700_snpId, -1, -1);
		Snp700 related_snp700 = snp700DAO.findSnp700ByPrimaryKey(related_snp700_id, -1, -1);

		genotyp700.setSnp700(null);
		related_snp700.getGenotyp700s().remove(genotyp700);
		genotyp700 = genotyp700DAO.store(genotyp700);
		genotyp700DAO.flush();

		related_snp700 = snp700DAO.store(related_snp700);
		snp700DAO.flush();

		snp700DAO.remove(related_snp700);
		snp700DAO.flush();

		return genotyp700;
	}

	/**
	 * Delete an existing Var700 entity
	 * 
	 */
	@Transactional
	public Genotyp700 deleteGenotyp700Var700(Integer genotyp700_varId, Integer genotyp700_snpId, Integer related_var700_id) {
		Genotyp700 genotyp700 = genotyp700DAO.findGenotyp700ByPrimaryKey(genotyp700_varId, genotyp700_snpId, -1, -1);
		Var700 related_var700 = var700DAO.findVar700ByPrimaryKey(related_var700_id, -1, -1);

		genotyp700.setVar700(null);
		related_var700.getGenotyp700s().remove(genotyp700);
		genotyp700 = genotyp700DAO.store(genotyp700);
		genotyp700DAO.flush();

		related_var700 = var700DAO.store(related_var700);
		var700DAO.flush();

		var700DAO.remove(related_var700);
		var700DAO.flush();

		return genotyp700;
	}

	/**
	 * Save an existing Genotyp700 entity
	 * 
	 */
	@Transactional
	public void saveGenotyp700(Genotyp700 genotyp700) {
		Genotyp700 existingGenotyp700 = genotyp700DAO.findGenotyp700ByPrimaryKey(genotyp700.getVarId(), genotyp700.getSnpId());

		if (existingGenotyp700 != null) {
			if (existingGenotyp700 != genotyp700) {
				existingGenotyp700.setVarId(genotyp700.getVarId());
				existingGenotyp700.setSnpId(genotyp700.getSnpId());
				existingGenotyp700.setAl1(genotyp700.getAl1());
				existingGenotyp700.setAl2(genotyp700.getAl2());
				existingGenotyp700.setQs(genotyp700.getQs());
				existingGenotyp700.setTyp(genotyp700.getTyp());
			}
			genotyp700 = genotyp700DAO.store(existingGenotyp700);
		} else {
			genotyp700 = genotyp700DAO.store(genotyp700);
		}
		genotyp700DAO.flush();
	}
}
