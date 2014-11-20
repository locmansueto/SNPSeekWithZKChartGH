package org.irri.iric.portal.dao;

import java.util.List;

import org.irri.iric.portal.domain.Variety;

public interface PhenotypeDAO {
	
	/**
	 * Get list of phenotypes/values for variety
	 * @param var
	 * @return
	 */
	public List findPhenotypesByVariety(Variety var);
	
	/**
	 * Get list of phenotypes/values for variety with name like (used to bridge legacy and new schema)
	 * @param var
	 * @return
	 */
	public List findPhenotypesByVarietyNameLike(String name);
	
	

	
}
