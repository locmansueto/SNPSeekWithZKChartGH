package org.irri.iric.portal.dao;


import java.math.BigDecimal;
import java.util.Map;

import org.irri.iric.portal.domain.Organism;

public interface OrganismDAO {

	/**
	 * Get map of organism name to organism object
	 * @return
	 */
	public Map<String,Organism> getMapName2Organism();
	
	/**
	 * Get organism with ID
	 * @param id
	 * @return
	 */
	public Organism getOrganismByID(Integer id);

}
