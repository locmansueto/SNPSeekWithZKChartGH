package org.irri.iric.portal.chado.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.dao.VSnpAllvarsCountrefmismatchDAO;

import org.irri.iric.portal.chado.domain.VSnpAllvarsCountrefmismatch;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for VSnpAllvarsCountrefmismatch entities
 * 
 */

@Service("VSnpAllvarsCountrefmismatchService")
@Transactional
public class VSnpAllvarsCountrefmismatchServiceImpl implements
		VSnpAllvarsCountrefmismatchService {

	/**
	 * DAO injected by Spring that manages VSnpAllvarsCountrefmismatch entities
	 * 
	 */
	@Autowired
	private VSnpAllvarsCountrefmismatchDAO vSnpAllvarsCountrefmismatchDAO;

	/**
	 * Instantiates a new VSnpAllvarsCountrefmismatchServiceImpl.
	 *
	 */
	public VSnpAllvarsCountrefmismatchServiceImpl() {
	}

	/**
	 * Return all VSnpAllvarsCountrefmismatch entity
	 * 
	 */
	@Transactional
	public List<VSnpAllvarsCountrefmismatch> findAllVSnpAllvarsCountrefmismatchs(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<VSnpAllvarsCountrefmismatch>(vSnpAllvarsCountrefmismatchDAO.findAllVSnpAllvarsCountrefmismatchs(startResult, maxRows));
	}

	/**
	 * Delete an existing VSnpAllvarsCountrefmismatch entity
	 * 
	 */
	@Transactional
	public void deleteVSnpAllvarsCountrefmismatch(VSnpAllvarsCountrefmismatch vsnpallvarscountrefmismatch) {
		vSnpAllvarsCountrefmismatchDAO.remove(vsnpallvarscountrefmismatch);
		vSnpAllvarsCountrefmismatchDAO.flush();
	}

	/**
	 * Return a count of all VSnpAllvarsCountrefmismatch entity
	 * 
	 */
	@Transactional
	public Integer countVSnpAllvarsCountrefmismatchs() {
		return ((Long) vSnpAllvarsCountrefmismatchDAO.createQuerySingleResult("select count(o) from VSnpAllvarsCountrefmismatch o").getSingleResult()).intValue();
	}

	/**
	 * Load an existing VSnpAllvarsCountrefmismatch entity
	 * 
	 */
	@Transactional
	public Set<VSnpAllvarsCountrefmismatch> loadVSnpAllvarsCountrefmismatchs() {
		return vSnpAllvarsCountrefmismatchDAO.findAllVSnpAllvarsCountrefmismatchs();
	}

	/**
	 */
	@Transactional
	public VSnpAllvarsCountrefmismatch findVSnpAllvarsCountrefmismatchByPrimaryKey(Integer var) {
		return vSnpAllvarsCountrefmismatchDAO.findVSnpAllvarsCountrefmismatchByPrimaryKey(var);
	}

	/**
	 * Save an existing VSnpAllvarsCountrefmismatch entity
	 * 
	 */
	@Transactional
	public void saveVSnpAllvarsCountrefmismatch(VSnpAllvarsCountrefmismatch vsnpallvarscountrefmismatch) {
		VSnpAllvarsCountrefmismatch existingVSnpAllvarsCountrefmismatch = vSnpAllvarsCountrefmismatchDAO.findVSnpAllvarsCountrefmismatchByPrimaryKey(vsnpallvarscountrefmismatch.getVar());

		if (existingVSnpAllvarsCountrefmismatch != null) {
			if (existingVSnpAllvarsCountrefmismatch != vsnpallvarscountrefmismatch) {
				existingVSnpAllvarsCountrefmismatch.setVar(vsnpallvarscountrefmismatch.getVar());
				existingVSnpAllvarsCountrefmismatch.setMismatch(vsnpallvarscountrefmismatch.getMismatch());
			}
			vsnpallvarscountrefmismatch = vSnpAllvarsCountrefmismatchDAO.store(existingVSnpAllvarsCountrefmismatch);
		} else {
			vsnpallvarscountrefmismatch = vSnpAllvarsCountrefmismatchDAO.store(vsnpallvarscountrefmismatch);
		}
		vSnpAllvarsCountrefmismatchDAO.flush();
	}
}
