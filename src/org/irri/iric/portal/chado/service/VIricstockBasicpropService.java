package org.irri.iric.portal.chado.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VIricstockBasicprop;

/**
 * Spring service that handles CRUD requests for VIricstockBasicprop entities
 * 
 */
public interface VIricstockBasicpropService {

	/**
	 * Load an existing VIricstockBasicprop entity
	 * 
	 */
	public Set<VIricstockBasicprop> loadVIricstockBasicprops();

	/**
	 */
	public VIricstockBasicprop findVIricstockBasicpropByPrimaryKey(Integer iricStockId);

	/**
	 * Delete an existing VIricstockBasicprop entity
	 * 
	 */
	public void deleteVIricstockBasicprop(VIricstockBasicprop viricstockbasicprop);

	/**
	 * Return all VIricstockBasicprop entity
	 * 
	 */
	public List<VIricstockBasicprop> findAllVIricstockBasicprops(Integer startResult, Integer maxRows);

	/**
	 * Save an existing VIricstockBasicprop entity
	 * 
	 */
	public void saveVIricstockBasicprop(VIricstockBasicprop viricstockbasicprop_1);

	/**
	 * Return a count of all VIricstockBasicprop entity
	 * 
	 */
	public Integer countVIricstockBasicprops();
}