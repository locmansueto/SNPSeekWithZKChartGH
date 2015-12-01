package org.irri.iric.portal.domain;

import java.math.BigDecimal;

import org.irri.iric.portal.AppContext;

/**
 * Reference genome
 * @author LMansueto
 *
 */
public interface Organism {

	public static String REFERENCE_NIPPONBARE = AppContext.getDefaultOrganism();
	public static String REFERENCE_IR64 =  "IR64-21";
	public static String REFERENCE_9311 =  "93-11";
	public static String REFERENCE_DJ123 = "DJ123";
	public static String REFERENCE_KASALATH = "Kasalath";
	
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
