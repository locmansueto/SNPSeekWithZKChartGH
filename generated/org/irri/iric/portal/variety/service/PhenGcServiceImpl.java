package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.dao.PhenGcDAO;

import org.irri.iric.portal.variety.domain.PhenGc;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for PhenGc entities
 * 
 */

@Service("PhenGcService")
@Transactional
public class PhenGcServiceImpl implements PhenGcService {

	/**
	 * DAO injected by Spring that manages PhenGc entities
	 * 
	 */
	@Autowired
	private PhenGcDAO phenGcDAO;

	/**
	 * Instantiates a new PhenGcServiceImpl.
	 *
	 */
	public PhenGcServiceImpl() {
	}

	/**
	 * Save an existing PhenGc entity
	 * 
	 */
	@Transactional
	public void savePhenGc(PhenGc phengc) {
		PhenGc existingPhenGc = phenGcDAO.findPhenGcByPrimaryKey(phengc.getEntno(), phengc.getGid());

		if (existingPhenGc != null) {
			if (existingPhenGc != phengc) {
				existingPhenGc.setEntno(phengc.getEntno());
				existingPhenGc.setGid(phengc.getGid());
				existingPhenGc.setDesignation(phengc.getDesignation());
				existingPhenGc.setBarcode(phengc.getBarcode());
				existingPhenGc.setTotalGrains(phengc.getTotalGrains());
				existingPhenGc.setChalkiness(phengc.getChalkiness());
				existingPhenGc.setChk010(phengc.getChk010());
				existingPhenGc.setChk1025(phengc.getChk1025());
				existingPhenGc.setChk2550(phengc.getChk2550());
				existingPhenGc.setChk5075(phengc.getChk5075());
				existingPhenGc.setChkGt75(phengc.getChkGt75());
				existingPhenGc.setAvgLength(phengc.getAvgLength());
				existingPhenGc.setStdLength(phengc.getStdLength());
				existingPhenGc.setAvgWidth(phengc.getAvgWidth());
				existingPhenGc.setStdWidth(phengc.getStdWidth());
				existingPhenGc.setShape(phengc.getShape());
				existingPhenGc.setWaxy(phengc.getWaxy());
				existingPhenGc.setAc(phengc.getAc());
				existingPhenGc.setGtDsc(phengc.getGtDsc());
				existingPhenGc.setGc(phengc.getGc());
				existingPhenGc.setMps(phengc.getMps());
				existingPhenGc.setMpsChk(phengc.getMpsChk());
				existingPhenGc.setRemarks(phengc.getRemarks());
				existingPhenGc.setName1(phengc.getName1());
				existingPhenGc.setName2(phengc.getName2());
			}
			phengc = phenGcDAO.store(existingPhenGc);
		} else {
			phengc = phenGcDAO.store(phengc);
		}
		phenGcDAO.flush();
	}

	/**
	 * Return all PhenGc entity
	 * 
	 */
	@Transactional
	public List<PhenGc> findAllPhenGcs(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<PhenGc>(phenGcDAO.findAllPhenGcs(startResult, maxRows));
	}

	/**
	 * Load an existing PhenGc entity
	 * 
	 */
	@Transactional
	public Set<PhenGc> loadPhenGcs() {
		return phenGcDAO.findAllPhenGcs();
	}

	/**
	 */
	@Transactional
	public PhenGc findPhenGcByPrimaryKey(Integer entno, Integer gid) {
		return phenGcDAO.findPhenGcByPrimaryKey(entno, gid);
	}

	/**
	 * Return a count of all PhenGc entity
	 * 
	 */
	@Transactional
	public Integer countPhenGcs() {
		return ((Long) phenGcDAO.createQuerySingleResult("select count(*) from PhenGc o").getSingleResult()).intValue();
	}

	/**
	 * Delete an existing PhenGc entity
	 * 
	 */
	@Transactional
	public void deletePhenGc(PhenGc phengc) {
		phenGcDAO.remove(phengc);
		phenGcDAO.flush();
	}
}
