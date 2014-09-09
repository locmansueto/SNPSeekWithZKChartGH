package org.irri.iric.portal.chado.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VIricstockPhenotype;

/**
 * Spring service that handles CRUD requests for VIricstockPhenotype entities
 * 
 */
public interface VIricstockPhenotypeService {

	/**
	 * Return a count of all VIricstockPhenotype entity
	 * 
	 */
	public Integer countVIricstockPhenotypes();

	/**
	 */
	public VIricstockPhenotype findVIricstockPhenotypeByPrimaryKey(Integer iricStockPhenotypeId);

	/**
	 * Return all VIricstockPhenotype entity
	 * 
	 */
	public List<VIricstockPhenotype> findAllVIricstockPhenotypes(Integer startResult, Integer maxRows);

	/**
	 * Save an existing VIricstockPhenotype entity
	 * 
	 */
	public void saveVIricstockPhenotype(VIricstockPhenotype viricstockphenotype);

	/**
	 * Delete an existing VIricstockPhenotype entity
	 * 
	 */
	public void deleteVIricstockPhenotype(VIricstockPhenotype viricstockphenotype_1);

	/**
	 * Load an existing VIricstockPhenotype entity
	 * 
	 */
	public Set<VIricstockPhenotype> loadVIricstockPhenotypes();
}