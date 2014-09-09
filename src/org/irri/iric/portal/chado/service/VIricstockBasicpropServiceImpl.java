package org.irri.iric.portal.chado.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.dao.VIricstockBasicpropDAO;
import org.irri.iric.portal.chado.domain.VIricstockBasicprop;
import org.irri.iric.portal.service.VarietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for VIricstockBasicprop entities
 * 
 */

//@Service("VIricstockBasicpropService")
@Service("VarietyService")
@Transactional
public class VIricstockBasicpropServiceImpl implements
		VIricstockBasicpropService , VarietyService {

	/**
	 * DAO injected by Spring that manages VIricstockBasicprop entities
	 * 
	 */
	@Autowired
	private VIricstockBasicpropDAO vIricstockBasicpropDAO;

	/**
	 * Instantiates a new VIricstockBasicpropServiceImpl.
	 *
	 */
	public VIricstockBasicpropServiceImpl() {
	}

	/**
	 * Load an existing VIricstockBasicprop entity
	 * 
	 */
	@Transactional
	public Set<VIricstockBasicprop> loadVIricstockBasicprops() {
		return vIricstockBasicpropDAO.findAllVIricstockBasicprops();
	}

	/**
	 */
	@Transactional
	public VIricstockBasicprop findVIricstockBasicpropByPrimaryKey(Integer iricStockId) {
		return vIricstockBasicpropDAO.findVIricstockBasicpropByPrimaryKey(iricStockId);
	}

	/**
	 * Delete an existing VIricstockBasicprop entity
	 * 
	 */
	@Transactional
	public void deleteVIricstockBasicprop(VIricstockBasicprop viricstockbasicprop) {
		vIricstockBasicpropDAO.remove(viricstockbasicprop);
		vIricstockBasicpropDAO.flush();
	}

	/**
	 * Return all VIricstockBasicprop entity
	 * 
	 */
	@Transactional
	public List<VIricstockBasicprop> findAllVIricstockBasicprops(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<VIricstockBasicprop>(vIricstockBasicpropDAO.findAllVIricstockBasicprops(startResult, maxRows));
	}

	/**
	 * Save an existing VIricstockBasicprop entity
	 * 
	 */
	@Transactional
	public void saveVIricstockBasicprop(VIricstockBasicprop viricstockbasicprop) {
		VIricstockBasicprop existingVIricstockBasicprop = vIricstockBasicpropDAO.findVIricstockBasicpropByPrimaryKey(viricstockbasicprop.getIricStockId());

		if (existingVIricstockBasicprop != null) {
			if (existingVIricstockBasicprop != viricstockbasicprop) {
				existingVIricstockBasicprop.setIricStockId(viricstockbasicprop.getIricStockId());
				existingVIricstockBasicprop.setName(viricstockbasicprop.getName());
				existingVIricstockBasicprop.setIrisUniqueId(viricstockbasicprop.getIrisUniqueId());
				existingVIricstockBasicprop.setOriCountry(viricstockbasicprop.getOriCountry());
			}
			viricstockbasicprop = vIricstockBasicpropDAO.store(existingVIricstockBasicprop);
		} else {
			viricstockbasicprop = vIricstockBasicpropDAO.store(viricstockbasicprop);
		}
		vIricstockBasicpropDAO.flush();
	}

	/**
	 * Return a count of all VIricstockBasicprop entity
	 * 
	 */
	@Transactional
	public Integer countVIricstockBasicprops() {
		return ((Long) vIricstockBasicpropDAO.createQuerySingleResult("select count(o) from VIricstockBasicprop o").getSingleResult()).intValue();
	}
}
