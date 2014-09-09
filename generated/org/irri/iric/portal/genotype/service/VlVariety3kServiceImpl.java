package org.irri.iric.portal.genotype.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.genotype.dao.VlVariety3kDAO;

import org.irri.iric.portal.genotype.domain.VlVariety3k;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for VlVariety3k entities
 * 
 */

@Service("VlVariety3kService")
@Transactional
public class VlVariety3kServiceImpl implements VlVariety3kService {

	/**
	 * DAO injected by Spring that manages VlVariety3k entities
	 * 
	 */
	@Autowired
	private VlVariety3kDAO vlVariety3kDAO;

	/**
	 * Instantiates a new VlVariety3kServiceImpl.
	 *
	 */
	public VlVariety3kServiceImpl() {
	}

	/**
	 * Return a count of all VlVariety3k entity
	 * 
	 */
	@Transactional
	public Integer countVlVariety3ks() {
		return ((Long) vlVariety3kDAO.createQuerySingleResult("select count(o) from VlVariety3k o").getSingleResult()).intValue();
	}

	/**
	 * Return all VlVariety3k entity
	 * 
	 */
	@Transactional
	public List<VlVariety3k> findAllVlVariety3ks(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<VlVariety3k>(vlVariety3kDAO.findAllVlVariety3ks(startResult, maxRows));
	}

	/**
	 */
	@Transactional
	public VlVariety3k findVlVariety3kByPrimaryKey(Integer varId) {
		return vlVariety3kDAO.findVlVariety3kByPrimaryKey(varId);
	}

	/**
	 * Delete an existing VlVariety3k entity
	 * 
	 */
	@Transactional
	public void deleteVlVariety3k(VlVariety3k vlvariety3k) {
		vlVariety3kDAO.remove(vlvariety3k);
		vlVariety3kDAO.flush();
	}

	/**
	 * Load an existing VlVariety3k entity
	 * 
	 */
	@Transactional
	public Set<VlVariety3k> loadVlVariety3ks() {
		return vlVariety3kDAO.findAllVlVariety3ks();
	}

	/**
	 * Save an existing VlVariety3k entity
	 * 
	 */
	@Transactional
	public void saveVlVariety3k(VlVariety3k vlvariety3k) {
		VlVariety3k existingVlVariety3k = vlVariety3kDAO.findVlVariety3kByPrimaryKey(vlvariety3k.getVarId());

		if (existingVlVariety3k != null) {
			if (existingVlVariety3k != vlvariety3k) {
				existingVlVariety3k.setVarId(vlvariety3k.getVarId());
				existingVlVariety3k.setName(vlvariety3k.getName());
				existingVlVariety3k.setIrisUniqueId(vlvariety3k.getIrisUniqueId());
				existingVlVariety3k.setOriCountry(vlvariety3k.getOriCountry());
			}
			vlvariety3k = vlVariety3kDAO.store(existingVlVariety3k);
		} else {
			vlvariety3k = vlVariety3kDAO.store(vlvariety3k);
		}
		vlVariety3kDAO.flush();
	}
}
