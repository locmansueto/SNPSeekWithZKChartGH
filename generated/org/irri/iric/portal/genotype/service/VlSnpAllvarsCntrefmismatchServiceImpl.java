package org.irri.iric.portal.genotype.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.genotype.dao.VlSnpAllvarsCntrefmismatchDAO;

import org.irri.iric.portal.genotype.domain.VlSnpAllvarsCntrefmismatch;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for VlSnpAllvarsCntrefmismatch entities
 * 
 */

@Service("VlSnpAllvarsCntrefmismatchService")
@Transactional
public class VlSnpAllvarsCntrefmismatchServiceImpl implements
		VlSnpAllvarsCntrefmismatchService {

	/**
	 * DAO injected by Spring that manages VlSnpAllvarsCntrefmismatch entities
	 * 
	 */
	@Autowired
	private VlSnpAllvarsCntrefmismatchDAO vlSnpAllvarsCntrefmismatchDAO;

	/**
	 * Instantiates a new VlSnpAllvarsCntrefmismatchServiceImpl.
	 *
	 */
	public VlSnpAllvarsCntrefmismatchServiceImpl() {
	}

	/**
	 * Save an existing VlSnpAllvarsCntrefmismatch entity
	 * 
	 */
	@Transactional
	public void saveVlSnpAllvarsCntrefmismatch(VlSnpAllvarsCntrefmismatch vlsnpallvarscntrefmismatch) {
		VlSnpAllvarsCntrefmismatch existingVlSnpAllvarsCntrefmismatch = vlSnpAllvarsCntrefmismatchDAO.findVlSnpAllvarsCntrefmismatchByPrimaryKey(vlsnpallvarscntrefmismatch.getVar());

		if (existingVlSnpAllvarsCntrefmismatch != null) {
			if (existingVlSnpAllvarsCntrefmismatch != vlsnpallvarscntrefmismatch) {
				existingVlSnpAllvarsCntrefmismatch.setVar(vlsnpallvarscntrefmismatch.getVar());
				existingVlSnpAllvarsCntrefmismatch.setMismatch(vlsnpallvarscntrefmismatch.getMismatch());
			}
			vlsnpallvarscntrefmismatch = vlSnpAllvarsCntrefmismatchDAO.store(existingVlSnpAllvarsCntrefmismatch);
		} else {
			vlsnpallvarscntrefmismatch = vlSnpAllvarsCntrefmismatchDAO.store(vlsnpallvarscntrefmismatch);
		}
		vlSnpAllvarsCntrefmismatchDAO.flush();
	}

	/**
	 * Return a count of all VlSnpAllvarsCntrefmismatch entity
	 * 
	 */
	@Transactional
	public Integer countVlSnpAllvarsCntrefmismatchs() {
		return ((Long) vlSnpAllvarsCntrefmismatchDAO.createQuerySingleResult("select count(o) from VlSnpAllvarsCntrefmismatch o").getSingleResult()).intValue();
	}

	/**
	 */
	@Transactional
	public VlSnpAllvarsCntrefmismatch findVlSnpAllvarsCntrefmismatchByPrimaryKey(Integer var) {
		return vlSnpAllvarsCntrefmismatchDAO.findVlSnpAllvarsCntrefmismatchByPrimaryKey(var);
	}

	/**
	 * Delete an existing VlSnpAllvarsCntrefmismatch entity
	 * 
	 */
	@Transactional
	public void deleteVlSnpAllvarsCntrefmismatch(VlSnpAllvarsCntrefmismatch vlsnpallvarscntrefmismatch) {
		vlSnpAllvarsCntrefmismatchDAO.remove(vlsnpallvarscntrefmismatch);
		vlSnpAllvarsCntrefmismatchDAO.flush();
	}

	/**
	 * Return all VlSnpAllvarsCntrefmismatch entity
	 * 
	 */
	@Transactional
	public List<VlSnpAllvarsCntrefmismatch> findAllVlSnpAllvarsCntrefmismatchs(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<VlSnpAllvarsCntrefmismatch>(vlSnpAllvarsCntrefmismatchDAO.findAllVlSnpAllvarsCntrefmismatchs(startResult, maxRows));
	}

	/**
	 * Load an existing VlSnpAllvarsCntrefmismatch entity
	 * 
	 */
	@Transactional
	public Set<VlSnpAllvarsCntrefmismatch> loadVlSnpAllvarsCntrefmismatchs() {
		return vlSnpAllvarsCntrefmismatchDAO.findAllVlSnpAllvarsCntrefmismatchs();
	}
}
