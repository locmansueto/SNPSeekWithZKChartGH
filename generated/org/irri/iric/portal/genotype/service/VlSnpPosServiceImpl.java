package org.irri.iric.portal.genotype.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.genotype.dao.VlSnpPosDAO;

import org.irri.iric.portal.genotype.domain.VlSnpPos;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for VlSnpPos entities
 * 
 */

@Service("VlSnpPosService")
@Transactional
public class VlSnpPosServiceImpl implements VlSnpPosService {

	/**
	 * DAO injected by Spring that manages VlSnpPos entities
	 * 
	 */
	@Autowired
	private VlSnpPosDAO vlSnpPosDAO;

	/**
	 * Instantiates a new VlSnpPosServiceImpl.
	 *
	 */
	public VlSnpPosServiceImpl() {
	}

	/**
	 */
	@Transactional
	public VlSnpPos findVlSnpPosByPrimaryKey(Integer chr, Integer pos) {
		return vlSnpPosDAO.findVlSnpPosByPrimaryKey(chr, pos);
	}

	/**
	 * Return all VlSnpPos entity
	 * 
	 */
	@Transactional
	public List<VlSnpPos> findAllVlSnpPoss(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<VlSnpPos>(vlSnpPosDAO.findAllVlSnpPoss(startResult, maxRows));
	}

	/**
	 * Delete an existing VlSnpPos entity
	 * 
	 */
	@Transactional
	public void deleteVlSnpPos(VlSnpPos vlsnppos) {
		vlSnpPosDAO.remove(vlsnppos);
		vlSnpPosDAO.flush();
	}

	/**
	 * Save an existing VlSnpPos entity
	 * 
	 */
	@Transactional
	public void saveVlSnpPos(VlSnpPos vlsnppos) {
		VlSnpPos existingVlSnpPos = vlSnpPosDAO.findVlSnpPosByPrimaryKey(vlsnppos.getChr(), vlsnppos.getPos());

		if (existingVlSnpPos != null) {
			if (existingVlSnpPos != vlsnppos) {
				existingVlSnpPos.setChr(vlsnppos.getChr());
				existingVlSnpPos.setPos(vlsnppos.getPos());
				existingVlSnpPos.setRefnuc(vlsnppos.getRefnuc());
			}
			vlsnppos = vlSnpPosDAO.store(existingVlSnpPos);
		} else {
			vlsnppos = vlSnpPosDAO.store(vlsnppos);
		}
		vlSnpPosDAO.flush();
	}

	/**
	 * Load an existing VlSnpPos entity
	 * 
	 */
	@Transactional
	public Set<VlSnpPos> loadVlSnpPoss() {
		return vlSnpPosDAO.findAllVlSnpPoss();
	}

	/**
	 * Return a count of all VlSnpPos entity
	 * 
	 */
	@Transactional
	public Integer countVlSnpPoss() {
		return ((Long) vlSnpPosDAO.createQuerySingleResult("select count(*) from VlSnpPos o").getSingleResult()).intValue();
	}
}
