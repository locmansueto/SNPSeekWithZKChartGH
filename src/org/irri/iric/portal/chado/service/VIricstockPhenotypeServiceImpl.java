package org.irri.iric.portal.chado.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.dao.VIricstockPhenotypeDAO;

import org.irri.iric.portal.chado.domain.VIricstockPhenotype;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for VIricstockPhenotype entities
 * 
 */

@Service("VIricstockPhenotypeService")
@Transactional
public class VIricstockPhenotypeServiceImpl implements
		VIricstockPhenotypeService {

	/**
	 * DAO injected by Spring that manages VIricstockPhenotype entities
	 * 
	 */
	@Autowired
	private VIricstockPhenotypeDAO vIricstockPhenotypeDAO;

	/**
	 * Instantiates a new VIricstockPhenotypeServiceImpl.
	 *
	 */
	public VIricstockPhenotypeServiceImpl() {
	}

	/**
	 * Return a count of all VIricstockPhenotype entity
	 * 
	 */
	@Transactional
	public Integer countVIricstockPhenotypes() {
		return ((Long) vIricstockPhenotypeDAO.createQuerySingleResult("select count(o) from VIricstockPhenotype o").getSingleResult()).intValue();
	}

	/**
	 */
	@Transactional
	public VIricstockPhenotype findVIricstockPhenotypeByPrimaryKey(Integer iricStockPhenotypeId) {
		return vIricstockPhenotypeDAO.findVIricstockPhenotypeByPrimaryKey(iricStockPhenotypeId);
	}

	/**
	 * Return all VIricstockPhenotype entity
	 * 
	 */
	@Transactional
	public List<VIricstockPhenotype> findAllVIricstockPhenotypes(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<VIricstockPhenotype>(vIricstockPhenotypeDAO.findAllVIricstockPhenotypes(startResult, maxRows));
	}

	/**
	 * Save an existing VIricstockPhenotype entity
	 * 
	 */
	@Transactional
	public void saveVIricstockPhenotype(VIricstockPhenotype viricstockphenotype) {
		VIricstockPhenotype existingVIricstockPhenotype = vIricstockPhenotypeDAO.findVIricstockPhenotypeByPrimaryKey(viricstockphenotype.getIricStockPhenotypeId());

		if (existingVIricstockPhenotype != null) {
			if (existingVIricstockPhenotype != viricstockphenotype) {
				existingVIricstockPhenotype.setIricStockPhenotypeId(viricstockphenotype.getIricStockPhenotypeId());
				existingVIricstockPhenotype.setIricStockId(viricstockphenotype.getIricStockId());
				existingVIricstockPhenotype.setName(viricstockphenotype.getName());
				existingVIricstockPhenotype.setDefinition(viricstockphenotype.getDefinition());
				existingVIricstockPhenotype.setQuanValue(viricstockphenotype.getQuanValue());
				existingVIricstockPhenotype.setQualValue(viricstockphenotype.getQualValue());
			}
			viricstockphenotype = vIricstockPhenotypeDAO.store(existingVIricstockPhenotype);
		} else {
			viricstockphenotype = vIricstockPhenotypeDAO.store(viricstockphenotype);
		}
		vIricstockPhenotypeDAO.flush();
	}

	/**
	 * Delete an existing VIricstockPhenotype entity
	 * 
	 */
	@Transactional
	public void deleteVIricstockPhenotype(VIricstockPhenotype viricstockphenotype) {
		vIricstockPhenotypeDAO.remove(viricstockphenotype);
		vIricstockPhenotypeDAO.flush();
	}

	/**
	 * Load an existing VIricstockPhenotype entity
	 * 
	 */
	@Transactional
	public Set<VIricstockPhenotype> loadVIricstockPhenotypes() {
		return vIricstockPhenotypeDAO.findAllVIricstockPhenotypes();
	}
}
