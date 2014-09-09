package org.irri.iric.portal.chado.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.dao.VSnp2varsCountmismatchDAO;

import org.irri.iric.portal.chado.domain.VSnp2varsCountmismatch;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for VSnp2varsCountmismatch entities
 * 
 */

@Service("VSnp2varsCountmismatchService")
@Transactional
public class VSnp2varsCountmismatchServiceImpl implements
		VSnp2varsCountmismatchService {

	/**
	 * DAO injected by Spring that manages VSnp2varsCountmismatch entities
	 * 
	 */
	@Autowired
	private VSnp2varsCountmismatchDAO vSnp2varsCountmismatchDAO;

	/**
	 * Instantiates a new VSnp2varsCountmismatchServiceImpl.
	 *
	 */
	public VSnp2varsCountmismatchServiceImpl() {
	}

	/**
	 */
	@Transactional
	public VSnp2varsCountmismatch findVSnp2varsCountmismatchByPrimaryKey(Integer var1, Integer var2) {
		return vSnp2varsCountmismatchDAO.findVSnp2varsCountmismatchByPrimaryKey(var1, var2);
	}

	/**
	 * Return all VSnp2varsCountmismatch entity
	 * 
	 */
	@Transactional
	public List<VSnp2varsCountmismatch> findAllVSnp2varsCountmismatchs(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<VSnp2varsCountmismatch>(vSnp2varsCountmismatchDAO.findAllVSnp2varsCountmismatchs(startResult, maxRows));
	}

	/**
	 * Delete an existing VSnp2varsCountmismatch entity
	 * 
	 */
	@Transactional
	public void deleteVSnp2varsCountmismatch(VSnp2varsCountmismatch vsnp2varscountmismatch) {
		vSnp2varsCountmismatchDAO.remove(vsnp2varscountmismatch);
		vSnp2varsCountmismatchDAO.flush();
	}

	/**
	 * Return a count of all VSnp2varsCountmismatch entity
	 * 
	 */
	@Transactional
	public Integer countVSnp2varsCountmismatchs() {
		return ((Long) vSnp2varsCountmismatchDAO.createQuerySingleResult("select count(*) from VSnp2varsCountmismatch o").getSingleResult()).intValue();
	}

	/**
	 * Load an existing VSnp2varsCountmismatch entity
	 * 
	 */
	@Transactional
	public Set<VSnp2varsCountmismatch> loadVSnp2varsCountmismatchs() {
		return vSnp2varsCountmismatchDAO.findAllVSnp2varsCountmismatchs();
	}

	/**
	 * Save an existing VSnp2varsCountmismatch entity
	 * 
	 */
	@Transactional
	public void saveVSnp2varsCountmismatch(VSnp2varsCountmismatch vsnp2varscountmismatch) {
		VSnp2varsCountmismatch existingVSnp2varsCountmismatch = vSnp2varsCountmismatchDAO.findVSnp2varsCountmismatchByPrimaryKey(vsnp2varscountmismatch.getVar1(), vsnp2varscountmismatch.getVar2());

		if (existingVSnp2varsCountmismatch != null) {
			if (existingVSnp2varsCountmismatch != vsnp2varscountmismatch) {
				existingVSnp2varsCountmismatch.setVar1(vsnp2varscountmismatch.getVar1());
				existingVSnp2varsCountmismatch.setVar2(vsnp2varscountmismatch.getVar2());
				existingVSnp2varsCountmismatch.setMismatch(vsnp2varscountmismatch.getMismatch());
			}
			vsnp2varscountmismatch = vSnp2varsCountmismatchDAO.store(existingVSnp2varsCountmismatch);
		} else {
			vsnp2varscountmismatch = vSnp2varsCountmismatchDAO.store(vsnp2varscountmismatch);
		}
		vSnp2varsCountmismatchDAO.flush();
	}
}
