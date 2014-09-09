package org.irri.iric.portal.chado.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.dao.VSnpAllvarsPosDAO;

import org.irri.iric.portal.chado.domain.VSnpAllvarsPos;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for VSnpAllvarsPos entities
 * 
 */

@Service("VSnpAllvarsPosService")
@Transactional
public class VSnpAllvarsPosServiceImpl implements VSnpAllvarsPosService {

	/**
	 * DAO injected by Spring that manages VSnpAllvarsPos entities
	 * 
	 */
	@Autowired
	private VSnpAllvarsPosDAO vSnpAllvarsPosDAO;

	/**
	 * Instantiates a new VSnpAllvarsPosServiceImpl.
	 *
	 */
	public VSnpAllvarsPosServiceImpl() {
	}

	/**
	 * Return all VSnpAllvarsPos entity
	 * 
	 */
	@Transactional
	public List<VSnpAllvarsPos> findAllVSnpAllvarsPoss(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<VSnpAllvarsPos>(vSnpAllvarsPosDAO.findAllVSnpAllvarsPoss(startResult, maxRows));
	}

	/**
	 * Delete an existing VSnpAllvarsPos entity
	 * 
	 */
	@Transactional
	public void deleteVSnpAllvarsPos(VSnpAllvarsPos vsnpallvarspos) {
		vSnpAllvarsPosDAO.remove(vsnpallvarspos);
		vSnpAllvarsPosDAO.flush();
	}

	/**
	 */
	@Transactional
	public VSnpAllvarsPos findVSnpAllvarsPosByPrimaryKey(Integer snpFeatureId) {
		return vSnpAllvarsPosDAO.findVSnpAllvarsPosByPrimaryKey(snpFeatureId);
	}

	/**
	 * Return a count of all VSnpAllvarsPos entity
	 * 
	 */
	@Transactional
	public Integer countVSnpAllvarsPoss() {
		return ((Long) vSnpAllvarsPosDAO.createQuerySingleResult("select count(o) from VSnpAllvarsPos o").getSingleResult()).intValue();
	}

	/**
	 * Load an existing VSnpAllvarsPos entity
	 * 
	 */
	@Transactional
	public Set<VSnpAllvarsPos> loadVSnpAllvarsPoss() {
		return vSnpAllvarsPosDAO.findAllVSnpAllvarsPoss();
	}

	/**
	 * Save an existing VSnpAllvarsPos entity
	 * 
	 */
	@Transactional
	public void saveVSnpAllvarsPos(VSnpAllvarsPos vsnpallvarspos) {
		VSnpAllvarsPos existingVSnpAllvarsPos = vSnpAllvarsPosDAO.findVSnpAllvarsPosByPrimaryKey(vsnpallvarspos.getSnpFeatureId());

		if (existingVSnpAllvarsPos != null) {
			if (existingVSnpAllvarsPos != vsnpallvarspos) {
				existingVSnpAllvarsPos.setSnpFeatureId(vsnpallvarspos.getSnpFeatureId());
				existingVSnpAllvarsPos.setChr(vsnpallvarspos.getChr());
				existingVSnpAllvarsPos.setPos(vsnpallvarspos.getPos());
				existingVSnpAllvarsPos.setRefnuc(vsnpallvarspos.getRefnuc());
			}
			vsnpallvarspos = vSnpAllvarsPosDAO.store(existingVSnpAllvarsPos);
		} else {
			vsnpallvarspos = vSnpAllvarsPosDAO.store(vsnpallvarspos);
		}
		vSnpAllvarsPosDAO.flush();
	}
}
