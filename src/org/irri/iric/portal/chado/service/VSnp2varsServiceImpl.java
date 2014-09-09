package org.irri.iric.portal.chado.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.dao.VSnp2varsDAO;
import org.irri.iric.portal.chado.domain.VSnp2vars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for VSnp2vars entities
 * 
 */

@Service("VSnp2varsService")
@Transactional
public class VSnp2varsServiceImpl implements VSnp2varsService {

	/**
	 * DAO injected by Spring that manages VSnp2vars entities
	 * 
	 */
	@Autowired
	private VSnp2varsDAO vSnp2varsDAO;

	/**
	 * Instantiates a new VSnp2varsServiceImpl.
	 *
	 */
	public VSnp2varsServiceImpl() {
	}

	/**
	 * Return a count of all VSnp2vars entity
	 * 
	 */
	@Transactional
	public Integer countVSnp2varss() {
		return ((Long) vSnp2varsDAO.createQuerySingleResult("select count(*) from VSnp2vars o").getSingleResult()).intValue();
	}

	/**
	 * Load an existing VSnp2vars entity
	 * 
	 */
	@Transactional
	public Set<VSnp2vars> loadVSnp2varss() {
		return vSnp2varsDAO.findAllVSnp2varss();
	}

	/**
	 * Return all VSnp2vars entity
	 * 
	 */
	@Transactional
	public List<VSnp2vars> findAllVSnp2varss(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<VSnp2vars>(vSnp2varsDAO.findAllVSnp2varss(startResult, maxRows));
	}

	/**
	 * Delete an existing VSnp2vars entity
	 * 
	 */
	@Transactional
	public void deleteVSnp2vars(VSnp2vars vsnp2vars) {
		vSnp2varsDAO.remove(vsnp2vars);
		vSnp2varsDAO.flush();
	}

	/**
	 * Save an existing VSnp2vars entity
	 * 
	 */
	@Transactional
	public void saveVSnp2vars(VSnp2vars vsnp2vars) {
		VSnp2vars existingVSnp2vars = vSnp2varsDAO.findVSnp2varsByPrimaryKey(vsnp2vars.getVar1(), vsnp2vars.getVar2(), vsnp2vars.getSnpFeatureId());

		if (existingVSnp2vars != null) {
			if (existingVSnp2vars != vsnp2vars) {
				existingVSnp2vars.setVar1(vsnp2vars.getVar1());
				existingVSnp2vars.setVar2(vsnp2vars.getVar2());
				existingVSnp2vars.setSnpFeatureId(vsnp2vars.getSnpFeatureId());
				existingVSnp2vars.setChr(vsnp2vars.getChr());
				existingVSnp2vars.setPos(vsnp2vars.getPos());
				existingVSnp2vars.setRefnuc(vsnp2vars.getRefnuc());
				existingVSnp2vars.setVar1nuc(vsnp2vars.getVar1nuc());
				existingVSnp2vars.setVar2nuc(vsnp2vars.getVar2nuc());
			}
			vsnp2vars = vSnp2varsDAO.store(existingVSnp2vars);
		} else {
			vsnp2vars = vSnp2varsDAO.store(vsnp2vars);
		}
		vSnp2varsDAO.flush();
	}

	/**
	 */
	@Transactional
	public VSnp2vars findVSnp2varsByPrimaryKey(Integer var1, Integer var2, Integer snpFeatureId) {
		return vSnp2varsDAO.findVSnp2varsByPrimaryKey(var1, var2, snpFeatureId);
	}
}
