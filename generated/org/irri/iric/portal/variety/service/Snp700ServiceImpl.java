package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.dao.Genotyp700DAO;
import org.irri.iric.portal.variety.dao.Snp700DAO;

import org.irri.iric.portal.variety.domain.Genotyp700;
import org.irri.iric.portal.variety.domain.Snp700;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for Snp700 entities
 * 
 */

@Service("Snp700Service")
@Transactional
public class Snp700ServiceImpl implements Snp700Service {

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
	 * Instantiates a new Snp700ServiceImpl.
	 *
	 */
	public Snp700ServiceImpl() {
	}

	/**
	 */
	@Transactional
	public Snp700 findSnp700ByPrimaryKey(Integer id) {
		return snp700DAO.findSnp700ByPrimaryKey(id);
	}

	/**
	 * Return all Snp700 entity
	 * 
	 */
	@Transactional
	public List<Snp700> findAllSnp700s(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<Snp700>(snp700DAO.findAllSnp700s(startResult, maxRows));
	}

	/**
	 * Load an existing Snp700 entity
	 * 
	 */
	@Transactional
	public Set<Snp700> loadSnp700s() {
		return snp700DAO.findAllSnp700s();
	}

	/**
	 * Return a count of all Snp700 entity
	 * 
	 */
	@Transactional
	public Integer countSnp700s() {
		return ((Long) snp700DAO.createQuerySingleResult("select count(o) from Snp700 o").getSingleResult()).intValue();
	}

	/**
	 * Save an existing Genotyp700 entity
	 * 
	 */
	@Transactional
	public Snp700 saveSnp700Genotyp700s(Integer id, Genotyp700 related_genotyp700s) {
		Snp700 snp700 = snp700DAO.findSnp700ByPrimaryKey(id, -1, -1);
		Genotyp700 existinggenotyp700s = genotyp700DAO.findGenotyp700ByPrimaryKey(related_genotyp700s.getVarId(), related_genotyp700s.getSnpId());

		// copy into the existing record to preserve existing relationships
		if (existinggenotyp700s != null) {
			existinggenotyp700s.setVarId(related_genotyp700s.getVarId());
			existinggenotyp700s.setSnpId(related_genotyp700s.getSnpId());
			existinggenotyp700s.setAl1(related_genotyp700s.getAl1());
			existinggenotyp700s.setAl2(related_genotyp700s.getAl2());
			existinggenotyp700s.setQs(related_genotyp700s.getQs());
			existinggenotyp700s.setTyp(related_genotyp700s.getTyp());
			related_genotyp700s = existinggenotyp700s;
		} else {
			related_genotyp700s = genotyp700DAO.store(related_genotyp700s);
			genotyp700DAO.flush();
		}

		related_genotyp700s.setSnp700(snp700);
		snp700.getGenotyp700s().add(related_genotyp700s);
		related_genotyp700s = genotyp700DAO.store(related_genotyp700s);
		genotyp700DAO.flush();

		snp700 = snp700DAO.store(snp700);
		snp700DAO.flush();

		return snp700;
	}

	/**
	 * Delete an existing Snp700 entity
	 * 
	 */
	@Transactional
	public void deleteSnp700(Snp700 snp700) {
		snp700DAO.remove(snp700);
		snp700DAO.flush();
	}

	/**
	 * Save an existing Snp700 entity
	 * 
	 */
	@Transactional
	public void saveSnp700(Snp700 snp700) {
		Snp700 existingSnp700 = snp700DAO.findSnp700ByPrimaryKey(snp700.getId());

		if (existingSnp700 != null) {
			if (existingSnp700 != snp700) {
				existingSnp700.setId(snp700.getId());
				existingSnp700.setChr(snp700.getChr());
				existingSnp700.setPos(snp700.getPos());
				existingSnp700.setName(snp700.getName());
				existingSnp700.setRef(snp700.getRef());
				existingSnp700.setAlt(snp700.getAlt());
				existingSnp700.setQscore(snp700.getQscore());
				existingSnp700.setFilter(snp700.getFilter());
				existingSnp700.setInfo(snp700.getInfo());
				existingSnp700.setFormat(snp700.getFormat());
				existingSnp700.setHet(snp700.getHet());
				existingSnp700.setAvgQscore(snp700.getAvgQscore());
			}
			snp700 = snp700DAO.store(existingSnp700);
		} else {
			snp700 = snp700DAO.store(snp700);
		}
		snp700DAO.flush();
	}

	/**
	 * Delete an existing Genotyp700 entity
	 * 
	 */
	@Transactional
	public Snp700 deleteSnp700Genotyp700s(Integer snp700_id, Integer related_genotyp700s_varId, Integer related_genotyp700s_snpId) {
		Genotyp700 related_genotyp700s = genotyp700DAO.findGenotyp700ByPrimaryKey(related_genotyp700s_varId, related_genotyp700s_snpId, -1, -1);

		Snp700 snp700 = snp700DAO.findSnp700ByPrimaryKey(snp700_id, -1, -1);

		related_genotyp700s.setSnp700(null);
		snp700.getGenotyp700s().remove(related_genotyp700s);

		genotyp700DAO.remove(related_genotyp700s);
		genotyp700DAO.flush();

		return snp700;
	}
}
