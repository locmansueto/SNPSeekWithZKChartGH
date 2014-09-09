package org.irri.iric.portal.genotype.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.genotype.dao.VlSnpAllvarsDAO;

import org.irri.iric.portal.genotype.domain.VlSnpAllvars;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for VlSnpAllvars entities
 * 
 */

@Service("VlSnpAllvarsService")
@Transactional
public class VlSnpAllvarsServiceImpl implements VlSnpAllvarsService {

	/**
	 * DAO injected by Spring that manages VlSnpAllvars entities
	 * 
	 */
	@Autowired
	private VlSnpAllvarsDAO vlSnpAllvarsDAO;

	/**
	 * Instantiates a new VlSnpAllvarsServiceImpl.
	 *
	 */
	public VlSnpAllvarsServiceImpl() {
	}

	/**
	 * Delete an existing VlSnpAllvars entity
	 * 
	 */
	@Transactional
	public void deleteVlSnpAllvars(VlSnpAllvars vlsnpallvars) {
		vlSnpAllvarsDAO.remove(vlsnpallvars);
		vlSnpAllvarsDAO.flush();
	}

	/**
	 * Return all VlSnpAllvars entity
	 * 
	 */
	@Transactional
	public List<VlSnpAllvars> findAllVlSnpAllvarss(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<VlSnpAllvars>(vlSnpAllvarsDAO.findAllVlSnpAllvarss(startResult, maxRows));
	}

	/**
	 * Load an existing VlSnpAllvars entity
	 * 
	 */
	@Transactional
	public Set<VlSnpAllvars> loadVlSnpAllvarss() {
		return vlSnpAllvarsDAO.findAllVlSnpAllvarss();
	}

	/**
	 */
	@Transactional
	public VlSnpAllvars findVlSnpAllvarsByPrimaryKey(Integer snpGenotypeId) {
		return vlSnpAllvarsDAO.findVlSnpAllvarsByPrimaryKey(snpGenotypeId);
	}

	/**
	 * Return a count of all VlSnpAllvars entity
	 * 
	 */
	@Transactional
	public Integer countVlSnpAllvarss() {
		return ((Long) vlSnpAllvarsDAO.createQuerySingleResult("select count(o) from VlSnpAllvars o").getSingleResult()).intValue();
	}

	/**
	 * Save an existing VlSnpAllvars entity
	 * 
	 */
	@Transactional
	public void saveVlSnpAllvars(VlSnpAllvars vlsnpallvars) {
		VlSnpAllvars existingVlSnpAllvars = vlSnpAllvarsDAO.findVlSnpAllvarsByPrimaryKey(vlsnpallvars.getSnpGenotypeId());

		if (existingVlSnpAllvars != null) {
			if (existingVlSnpAllvars != vlsnpallvars) {
				existingVlSnpAllvars.setSnpGenotypeId(vlsnpallvars.getSnpGenotypeId());
				existingVlSnpAllvars.setVar(vlsnpallvars.getVar());
				existingVlSnpAllvars.setChr(vlsnpallvars.getChr());
				existingVlSnpAllvars.setPos(vlsnpallvars.getPos());
				existingVlSnpAllvars.setRefnuc(vlsnpallvars.getRefnuc());
				existingVlSnpAllvars.setVarnuc(vlsnpallvars.getVarnuc());
			}
			vlsnpallvars = vlSnpAllvarsDAO.store(existingVlSnpAllvars);
		} else {
			vlsnpallvars = vlSnpAllvarsDAO.store(vlsnpallvars);
		}
		vlSnpAllvarsDAO.flush();
	}
}
