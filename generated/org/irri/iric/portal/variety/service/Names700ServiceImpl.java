package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.dao.Names700DAO;

import org.irri.iric.portal.variety.domain.Names700;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for Names700 entities
 * 
 */

@Service("Names700Service")
@Transactional
public class Names700ServiceImpl implements Names700Service {

	/**
	 * DAO injected by Spring that manages Names700 entities
	 * 
	 */
	@Autowired
	private Names700DAO names700DAO;

	/**
	 * Instantiates a new Names700ServiceImpl.
	 *
	 */
	public Names700ServiceImpl() {
	}

	/**
	 * Load an existing Names700 entity
	 * 
	 */
	@Transactional
	public Set<Names700> loadNames700s() {
		return names700DAO.findAllNames700s();
	}

	/**
	 * Return all Names700 entity
	 * 
	 */
	@Transactional
	public List<Names700> findAllNames700s(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<Names700>(names700DAO.findAllNames700s(startResult, maxRows));
	}

	/**
	 * Save an existing Names700 entity
	 * 
	 */
	@Transactional
	public void saveNames700(Names700 names700) {
		Names700 existingNames700 = names700DAO.findNames700ByPrimaryKey(names700.getId());

		if (existingNames700 != null) {
			if (existingNames700 != names700) {
				existingNames700.setId(names700.getId());
				existingNames700.setAssayId(names700.getAssayId());
				existingNames700.setSampleId(names700.getSampleId());
				existingNames700.setAltId(names700.getAltId());
			}
			names700 = names700DAO.store(existingNames700);
		} else {
			names700 = names700DAO.store(names700);
		}
		names700DAO.flush();
	}

	/**
	 */
	@Transactional
	public Names700 findNames700ByPrimaryKey(String id) {
		return names700DAO.findNames700ByPrimaryKey(id);
	}

	/**
	 * Delete an existing Names700 entity
	 * 
	 */
	@Transactional
	public void deleteNames700(Names700 names700) {
		names700DAO.remove(names700);
		names700DAO.flush();
	}

	/**
	 * Return a count of all Names700 entity
	 * 
	 */
	@Transactional
	public Integer countNames700s() {
		return ((Long) names700DAO.createQuerySingleResult("select count(o) from Names700 o").getSingleResult()).intValue();
	}
}
