package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.portal.domain.Variety;


public interface VarietyDAO {

	/**
	 * Get all varieties
	 * @return
	 */
	public Set findAllVariety();
	
	/**
	 * Get varieties from country
	 * @param country
	 * @return
	 */
	public Set findAllVarietyByCountry(String country);
	
	/**
	 * Get varieties in subpopulation
	 * @param subpopulation
	 * @return
	 */
	public Set findAllVarietyBySubpopulation(String subpopulation);
	//public Set findAllVarietyByExample(Variety germplasm);

	/**
	 * Getg varieties from country and in subpopulation
	 * @param country
	 * @param subpopulation
	 * @return
	 */
	public Set findAllVarietyByCountryAndSubpopulation(String country, String subpopulation);

	/**
	 * Get variety with name
	 * @param name
	 * @return
	 */
	public Variety findVarietyByName(String name);
	
	/**
	 * Get variety with name like
	 * @param name
	 * @return
	 */
	public Variety findVarietyByNameLike(String name);
	
	/**
	 * Get variety with IRIS Id
	 * @param name
	 * @return
	 */
	public Variety findVarietyByIrisId(String name);
	
	/**
	 * Get variety with Variety ID
	 * @param id
	 * @return
	 */
	public Variety findVarietyById(BigDecimal id);
	
	
}
