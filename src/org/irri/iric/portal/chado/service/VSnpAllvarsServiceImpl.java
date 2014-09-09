package org.irri.iric.portal.chado.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.dao.VSnpAllvarsDAO;

import org.irri.iric.portal.chado.domain.VSnpAllvars;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for VSnpAllvars entities
 * 
 */

@Service("VSnpAllvarsService")
@Transactional
public class VSnpAllvarsServiceImpl implements VSnpAllvarsService {

	/**
	 * DAO injected by Spring that manages VSnpAllvars entities
	 * 
	 */
	@Autowired
	private VSnpAllvarsDAO vSnpAllvarsDAO;

	/**
	 * Instantiates a new VSnpAllvarsServiceImpl.
	 *
	 */
	public VSnpAllvarsServiceImpl() {
	}

	/**
	 * Delete an existing VSnpAllvars entity
	 * 
	 */
	@Transactional
	public void deleteVSnpAllvars(VSnpAllvars vsnpallvars) {
		vSnpAllvarsDAO.remove(vsnpallvars);
		vSnpAllvarsDAO.flush();
	}

	/**
	 * Return all VSnpAllvars entity
	 * 
	 */
	@Transactional
	public List<VSnpAllvars> findAllVSnpAllvarss(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<VSnpAllvars>(vSnpAllvarsDAO.findAllVSnpAllvarss(startResult, maxRows));
	}

	/**
	 * Save an existing VSnpAllvars entity
	 * 
	 */
	@Transactional
	public void saveVSnpAllvars(VSnpAllvars vsnpallvars) {
		VSnpAllvars existingVSnpAllvars = vSnpAllvarsDAO.findVSnpAllvarsByPrimaryKey(vsnpallvars.getSnpGenotypeId());

		if (existingVSnpAllvars != null) {
			if (existingVSnpAllvars != vsnpallvars) {
				existingVSnpAllvars.setSnpGenotypeId(vsnpallvars.getSnpGenotypeId());
				existingVSnpAllvars.setVar(vsnpallvars.getVar());
				existingVSnpAllvars.setChr(vsnpallvars.getChr());
				existingVSnpAllvars.setPos(vsnpallvars.getPos());
				existingVSnpAllvars.setRefnuc(vsnpallvars.getRefnuc());
				existingVSnpAllvars.setVarnuc(vsnpallvars.getVarnuc());
			}
			vsnpallvars = vSnpAllvarsDAO.store(existingVSnpAllvars);
		} else {
			vsnpallvars = vSnpAllvarsDAO.store(vsnpallvars);
		}
		vSnpAllvarsDAO.flush();
	}

	/**
	 * Load an existing VSnpAllvars entity
	 * 
	 */
	@Transactional
	public Set<VSnpAllvars> loadVSnpAllvarss() {
		return vSnpAllvarsDAO.findAllVSnpAllvarss();
	}

	/**
	 */
	@Transactional
	public VSnpAllvars findVSnpAllvarsByPrimaryKey(Integer snpGenotypeId) {
		return vSnpAllvarsDAO.findVSnpAllvarsByPrimaryKey(snpGenotypeId);
	}

	/**
	 * Return a count of all VSnpAllvars entity
	 * 
	 */
	@Transactional
	public Integer countVSnpAllvarss() {
		return ((Long) vSnpAllvarsDAO.createQuerySingleResult("select count(o) from VSnpAllvars o").getSingleResult()).intValue();
	}
}
