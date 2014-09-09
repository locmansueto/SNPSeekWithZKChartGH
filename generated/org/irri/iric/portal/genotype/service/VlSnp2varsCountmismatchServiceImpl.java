package org.irri.iric.portal.genotype.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.genotype.dao.VlSnp2varsCountmismatchDAO;

import org.irri.iric.portal.genotype.domain.VlSnp2varsCountmismatch;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for VlSnp2varsCountmismatch entities
 * 
 */

@Service("VlSnp2varsCountmismatchService")
@Transactional
public class VlSnp2varsCountmismatchServiceImpl implements
		VlSnp2varsCountmismatchService {

	/**
	 * DAO injected by Spring that manages VlSnp2varsCountmismatch entities
	 * 
	 */
	@Autowired
	private VlSnp2varsCountmismatchDAO vlSnp2varsCountmismatchDAO;

	/**
	 * Instantiates a new VlSnp2varsCountmismatchServiceImpl.
	 *
	 */
	public VlSnp2varsCountmismatchServiceImpl() {
	}

	/**
	 */
	@Transactional
	public VlSnp2varsCountmismatch findVlSnp2varsCountmismatchByPrimaryKey(Integer var1, Integer var2) {
		return vlSnp2varsCountmismatchDAO.findVlSnp2varsCountmismatchByPrimaryKey(var1, var2);
	}

	/**
	 * Load an existing VlSnp2varsCountmismatch entity
	 * 
	 */
	@Transactional
	public Set<VlSnp2varsCountmismatch> loadVlSnp2varsCountmismatchs() {
		return vlSnp2varsCountmismatchDAO.findAllVlSnp2varsCountmismatchs();
	}

	/**
	 * Return a count of all VlSnp2varsCountmismatch entity
	 * 
	 */
	@Transactional
	public Integer countVlSnp2varsCountmismatchs() {
		return ((Long) vlSnp2varsCountmismatchDAO.createQuerySingleResult("select count(*) from VlSnp2varsCountmismatch o").getSingleResult()).intValue();
	}

	/**
	 * Delete an existing VlSnp2varsCountmismatch entity
	 * 
	 */
	@Transactional
	public void deleteVlSnp2varsCountmismatch(VlSnp2varsCountmismatch vlsnp2varscountmismatch) {
		vlSnp2varsCountmismatchDAO.remove(vlsnp2varscountmismatch);
		vlSnp2varsCountmismatchDAO.flush();
	}

	/**
	 * Return all VlSnp2varsCountmismatch entity
	 * 
	 */
	@Transactional
	public List<VlSnp2varsCountmismatch> findAllVlSnp2varsCountmismatchs(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<VlSnp2varsCountmismatch>(vlSnp2varsCountmismatchDAO.findAllVlSnp2varsCountmismatchs(startResult, maxRows));
	}

	/**
	 * Save an existing VlSnp2varsCountmismatch entity
	 * 
	 */
	@Transactional
	public void saveVlSnp2varsCountmismatch(VlSnp2varsCountmismatch vlsnp2varscountmismatch) {
		VlSnp2varsCountmismatch existingVlSnp2varsCountmismatch = vlSnp2varsCountmismatchDAO.findVlSnp2varsCountmismatchByPrimaryKey(vlsnp2varscountmismatch.getVar1(), vlsnp2varscountmismatch.getVar2());

		if (existingVlSnp2varsCountmismatch != null) {
			if (existingVlSnp2varsCountmismatch != vlsnp2varscountmismatch) {
				existingVlSnp2varsCountmismatch.setVar1(vlsnp2varscountmismatch.getVar1());
				existingVlSnp2varsCountmismatch.setVar2(vlsnp2varscountmismatch.getVar2());
				existingVlSnp2varsCountmismatch.setMismatch(vlsnp2varscountmismatch.getMismatch());
			}
			vlsnp2varscountmismatch = vlSnp2varsCountmismatchDAO.store(existingVlSnp2varsCountmismatch);
		} else {
			vlsnp2varscountmismatch = vlSnp2varsCountmismatchDAO.store(vlsnp2varscountmismatch);
		}
		vlSnp2varsCountmismatchDAO.flush();
	}
}
