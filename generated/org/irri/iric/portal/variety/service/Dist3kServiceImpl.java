package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.dao.Dist3kDAO;

import org.irri.iric.portal.variety.domain.Dist3k;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for Dist3k entities
 * 
 */

@Service("Dist3kService")
@Transactional
public class Dist3kServiceImpl implements Dist3kService {

	/**
	 * DAO injected by Spring that manages Dist3k entities
	 * 
	 */
	@Autowired
	private Dist3kDAO dist3kDAO;

	/**
	 * Instantiates a new Dist3kServiceImpl.
	 *
	 */
	public Dist3kServiceImpl() {
	}

	/**
	 * Return all Dist3k entity
	 * 
	 */
	@Transactional
	public List<Dist3k> findAllDist3ks(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<Dist3k>(dist3kDAO.findAllDist3ks(startResult, maxRows));
	}

	/**
	 * Load an existing Dist3k entity
	 * 
	 */
	@Transactional
	public Set<Dist3k> loadDist3ks() {
		return dist3kDAO.findAllDist3ks();
	}

	/**
	 * Return a count of all Dist3k entity
	 * 
	 */
	@Transactional
	public Integer countDist3ks() {
		return ((Long) dist3kDAO.createQuerySingleResult("select count(*) from Dist3k o").getSingleResult()).intValue();
	}

	/**
	 * Save an existing Dist3k entity
	 * 
	 */
	@Transactional
	public void saveDist3k(Dist3k dist3k) {
		Dist3k existingDist3k = dist3kDAO.findDist3kByPrimaryKey(dist3k.getNam1(), dist3k.getNam2());

		if (existingDist3k != null) {
			if (existingDist3k != dist3k) {
				existingDist3k.setNam1(dist3k.getNam1());
				existingDist3k.setNam2(dist3k.getNam2());
				existingDist3k.setDist(dist3k.getDist());
			}
			dist3k = dist3kDAO.store(existingDist3k);
		} else {
			dist3k = dist3kDAO.store(dist3k);
		}
		dist3kDAO.flush();
	}

	/**
	 * Delete an existing Dist3k entity
	 * 
	 */
	@Transactional
	public void deleteDist3k(Dist3k dist3k) {
		dist3kDAO.remove(dist3k);
		dist3kDAO.flush();
	}

	/**
	 */
	@Transactional
	public Dist3k findDist3kByPrimaryKey(String nam1, String nam2) {
		return dist3kDAO.findDist3kByPrimaryKey(nam1, nam2);
	}
}
