package org.irri.iric.portal.chado.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.dao.VIricstockPassportDAO;

import org.irri.iric.portal.chado.domain.VIricstockPassport;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for VIricstockPassport entities
 * 
 */

@Service("VIricstockPassportService")
@Transactional
public class VIricstockPassportServiceImpl implements VIricstockPassportService {

	/**
	 * DAO injected by Spring that manages VIricstockPassport entities
	 * 
	 */
	@Autowired
	private VIricstockPassportDAO vIricstockPassportDAO;

	/**
	 * Instantiates a new VIricstockPassportServiceImpl.
	 *
	 */
	public VIricstockPassportServiceImpl() {
	}

	/**
	 * Delete an existing VIricstockPassport entity
	 * 
	 */
	@Transactional
	public void deleteVIricstockPassport(VIricstockPassport viricstockpassport) {
		vIricstockPassportDAO.remove(viricstockpassport);
		vIricstockPassportDAO.flush();
	}

	/**
	 * Return a count of all VIricstockPassport entity
	 * 
	 */
	@Transactional
	public Integer countVIricstockPassports() {
		return ((Long) vIricstockPassportDAO.createQuerySingleResult("select count(o) from VIricstockPassport o").getSingleResult()).intValue();
	}

	/**
	 */
	@Transactional
	public VIricstockPassport findVIricstockPassportByPrimaryKey(Integer iricStockpropId) {
		return vIricstockPassportDAO.findVIricstockPassportByPrimaryKey(iricStockpropId);
	}

	/**
	 * Load an existing VIricstockPassport entity
	 * 
	 */
	@Transactional
	public Set<VIricstockPassport> loadVIricstockPassports() {
		return vIricstockPassportDAO.findAllVIricstockPassports();
	}

	/**
	 * Save an existing VIricstockPassport entity
	 * 
	 */
	@Transactional
	public void saveVIricstockPassport(VIricstockPassport viricstockpassport) {
		VIricstockPassport existingVIricstockPassport = vIricstockPassportDAO.findVIricstockPassportByPrimaryKey(viricstockpassport.getIricStockpropId());

		if (existingVIricstockPassport != null) {
			if (existingVIricstockPassport != viricstockpassport) {
				existingVIricstockPassport.setIricStockpropId(viricstockpassport.getIricStockpropId());
				existingVIricstockPassport.setIricStockId(viricstockpassport.getIricStockId());
				existingVIricstockPassport.setName(viricstockpassport.getName());
				existingVIricstockPassport.setDefinition(viricstockpassport.getDefinition());
				existingVIricstockPassport.setValue(viricstockpassport.getValue());
			}
			viricstockpassport = vIricstockPassportDAO.store(existingVIricstockPassport);
		} else {
			viricstockpassport = vIricstockPassportDAO.store(viricstockpassport);
		}
		vIricstockPassportDAO.flush();
	}

	/**
	 * Return all VIricstockPassport entity
	 * 
	 */
	@Transactional
	public List<VIricstockPassport> findAllVIricstockPassports(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<VIricstockPassport>(vIricstockPassportDAO.findAllVIricstockPassports(startResult, maxRows));
	}
}
