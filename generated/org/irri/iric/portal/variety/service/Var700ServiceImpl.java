package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.dao.Genotyp700DAO;
import org.irri.iric.portal.variety.dao.Var700DAO;

import org.irri.iric.portal.variety.domain.Genotyp700;
import org.irri.iric.portal.variety.domain.Var700;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for Var700 entities
 * 
 */

@Service("Var700Service")
@Transactional
public class Var700ServiceImpl implements Var700Service {

	/**
	 * DAO injected by Spring that manages Genotyp700 entities
	 * 
	 */
	@Autowired
	private Genotyp700DAO genotyp700DAO;

	/**
	 * DAO injected by Spring that manages Var700 entities
	 * 
	 */
	@Autowired
	private Var700DAO var700DAO;

	/**
	 * Instantiates a new Var700ServiceImpl.
	 *
	 */
	public Var700ServiceImpl() {
	}

	/**
	 * Save an existing Var700 entity
	 * 
	 */
	@Transactional
	public void saveVar700(Var700 var700) {
		Var700 existingVar700 = var700DAO.findVar700ByPrimaryKey(var700.getId());

		if (existingVar700 != null) {
			if (existingVar700 != var700) {
				existingVar700.setId(var700.getId());
				existingVar700.setName(var700.getName());
			}
			var700 = var700DAO.store(existingVar700);
		} else {
			var700 = var700DAO.store(var700);
		}
		var700DAO.flush();
	}

	/**
	 * Load an existing Var700 entity
	 * 
	 */
	@Transactional
	public Set<Var700> loadVar700s() {
		return var700DAO.findAllVar700s();
	}

	/**
	 * Return a count of all Var700 entity
	 * 
	 */
	@Transactional
	public Integer countVar700s() {
		return ((Long) var700DAO.createQuerySingleResult("select count(o) from Var700 o").getSingleResult()).intValue();
	}

	/**
	 * Return all Var700 entity
	 * 
	 */
	@Transactional
	public List<Var700> findAllVar700s(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<Var700>(var700DAO.findAllVar700s(startResult, maxRows));
	}

	/**
	 */
	@Transactional
	public Var700 findVar700ByPrimaryKey(Integer id) {
		return var700DAO.findVar700ByPrimaryKey(id);
	}

	/**
	 * Save an existing Genotyp700 entity
	 * 
	 */
	@Transactional
	public Var700 saveVar700Genotyp700s(Integer id, Genotyp700 related_genotyp700s) {
		Var700 var700 = var700DAO.findVar700ByPrimaryKey(id, -1, -1);
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

		related_genotyp700s.setVar700(var700);
		var700.getGenotyp700s().add(related_genotyp700s);
		related_genotyp700s = genotyp700DAO.store(related_genotyp700s);
		genotyp700DAO.flush();

		var700 = var700DAO.store(var700);
		var700DAO.flush();

		return var700;
	}

	/**
	 * Delete an existing Genotyp700 entity
	 * 
	 */
	@Transactional
	public Var700 deleteVar700Genotyp700s(Integer var700_id, Integer related_genotyp700s_varId, Integer related_genotyp700s_snpId) {
		Genotyp700 related_genotyp700s = genotyp700DAO.findGenotyp700ByPrimaryKey(related_genotyp700s_varId, related_genotyp700s_snpId, -1, -1);

		Var700 var700 = var700DAO.findVar700ByPrimaryKey(var700_id, -1, -1);

		related_genotyp700s.setVar700(null);
		var700.getGenotyp700s().remove(related_genotyp700s);

		genotyp700DAO.remove(related_genotyp700s);
		genotyp700DAO.flush();

		return var700;
	}

	/**
	 * Delete an existing Var700 entity
	 * 
	 */
	@Transactional
	public void deleteVar700(Var700 var700) {
		var700DAO.remove(var700);
		var700DAO.flush();
	}
}
