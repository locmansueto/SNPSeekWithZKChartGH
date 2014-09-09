package org.irri.iric.portal.chado.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VIricstockPassport;

/**
 * Spring service that handles CRUD requests for VIricstockPassport entities
 * 
 */
public interface VIricstockPassportService {

	/**
	 * Delete an existing VIricstockPassport entity
	 * 
	 */
	public void deleteVIricstockPassport(VIricstockPassport viricstockpassport);

	/**
	 * Return a count of all VIricstockPassport entity
	 * 
	 */
	public Integer countVIricstockPassports();

	/**
	 */
	public VIricstockPassport findVIricstockPassportByPrimaryKey(Integer iricStockpropId);

	/**
	 * Load an existing VIricstockPassport entity
	 * 
	 */
	public Set<VIricstockPassport> loadVIricstockPassports();

	/**
	 * Save an existing VIricstockPassport entity
	 * 
	 */
	public void saveVIricstockPassport(VIricstockPassport viricstockpassport_1);

	/**
	 * Return all VIricstockPassport entity
	 * 
	 */
	public List<VIricstockPassport> findAllVIricstockPassports(Integer startResult, Integer maxRows);
}