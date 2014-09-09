package org.irri.iric.portal.genotype.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.genotype.dao.VlSnp2varsDAO;

import org.irri.iric.portal.genotype.domain.VlSnp2vars;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for VlSnp2vars entities
 * 
 */

@Service("VlSnp2varsService")
@Transactional
public class VlSnp2varsServiceImpl implements VlSnp2varsService {

	/**
	 * DAO injected by Spring that manages VlSnp2vars entities
	 * 
	 */
	@Autowired
	private VlSnp2varsDAO vlSnp2varsDAO;

	/**
	 * Instantiates a new VlSnp2varsServiceImpl.
	 *
	 */
	public VlSnp2varsServiceImpl() {
	}

	/**
	 * Load an existing VlSnp2vars entity
	 * 
	 */
	@Transactional
	public Set<VlSnp2vars> loadVlSnp2varss() {
		return vlSnp2varsDAO.findAllVlSnp2varss();
	}

	/**
	 * Return all VlSnp2vars entity
	 * 
	 */
	@Transactional
	public List<VlSnp2vars> findAllVlSnp2varss(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<VlSnp2vars>(vlSnp2varsDAO.findAllVlSnp2varss(startResult, maxRows));
	}

	/**
	 * Save an existing VlSnp2vars entity
	 * 
	 */
	@Transactional
	public void saveVlSnp2vars(VlSnp2vars vlsnp2vars) {
		VlSnp2vars existingVlSnp2vars = vlSnp2varsDAO.findVlSnp2varsByPrimaryKey(vlsnp2vars.getVar1(), vlsnp2vars.getVar2(), vlsnp2vars.getSnpFeatureId().intValueExact());

		if (existingVlSnp2vars != null) {
			if (existingVlSnp2vars != vlsnp2vars) {
				existingVlSnp2vars.setVar1(vlsnp2vars.getVar1());
				existingVlSnp2vars.setVar2(vlsnp2vars.getVar2());
				existingVlSnp2vars.setSnpFeatureId(vlsnp2vars.getSnpFeatureId());
				existingVlSnp2vars.setChr(vlsnp2vars.getChr());
				existingVlSnp2vars.setPos(vlsnp2vars.getPos());
				existingVlSnp2vars.setRefnuc(vlsnp2vars.getRefnuc());
				existingVlSnp2vars.setVar1nuc(vlsnp2vars.getVar1nuc());
				existingVlSnp2vars.setVar2nuc(vlsnp2vars.getVar2nuc());
			}
			vlsnp2vars = vlSnp2varsDAO.store(existingVlSnp2vars);
		} else {
			vlsnp2vars = vlSnp2varsDAO.store(vlsnp2vars);
		}
		vlSnp2varsDAO.flush();
	}

	/**
	 * Delete an existing VlSnp2vars entity
	 * 
	 */
	@Transactional
	public void deleteVlSnp2vars(VlSnp2vars vlsnp2vars) {
		vlSnp2varsDAO.remove(vlsnp2vars);
		vlSnp2varsDAO.flush();
	}

	/**
	 */
	@Transactional
	public VlSnp2vars findVlSnp2varsByPrimaryKey(Integer var1, Integer var2, Integer snpFeatureId) {
		return vlSnp2varsDAO.findVlSnp2varsByPrimaryKey(var1, var2, snpFeatureId);
	}

	/**
	 * Return a count of all VlSnp2vars entity
	 * 
	 */
	@Transactional
	public Integer countVlSnp2varss() {
		return ((Long) vlSnp2varsDAO.createQuerySingleResult("select count(*) from VlSnp2vars o").getSingleResult()).intValue();
	}
}
