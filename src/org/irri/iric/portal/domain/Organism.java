package org.irri.iric.portal.domain;

import java.math.BigDecimal;

/**
 * Reference genome
 * @author LMansueto
 *
 */
public interface Organism {

	/**
	 * DB primary key
	 * @return
	 */
	public BigDecimal getOrganismId();
	
	/**
	 * Reference genome name
	 * @return
	 */
	public String getName();
}
