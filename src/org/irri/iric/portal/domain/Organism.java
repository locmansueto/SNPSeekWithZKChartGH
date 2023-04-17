package org.irri.iric.portal.domain;

import java.math.BigDecimal;

import org.irri.iric.portal.AppContext;

/**
 * Reference genome
 * 
 * @author LMansueto
 *
 */
public interface Organism {

	//public static String REFERENCE_NIPPONBARE = AppContext.getDefaultOrganism();
	public static String REFERENCE_IR64 = "IR64-21";
	public static String REFERENCE_9311 = "93-11";
	public static String REFERENCE_DJ123 = "DJ123";
	public static String REFERENCE_KASALATH = "Kasalath";
	//public static BigDecimal REFERENCE_NIPPONBARE_ID = BigDecimal.valueOf(AppContext.getDefaultOrganismId()); // BigDecimal.valueOf(9);
																												// BigDecimal.valueOf(9);
	public static BigDecimal REFERENCE_IR64_ID = BigDecimal.valueOf(13);
	public static BigDecimal REFERENCE_9311_ID = BigDecimal.valueOf(14);
	public static BigDecimal REFERENCE_DJ123_ID = BigDecimal.valueOf(15);
	public static BigDecimal REFERENCE_KASALATH_ID = BigDecimal.valueOf(16);

	
	public static String REFERENCE_NIPPONBARE() {
		return AppContext.getDefaultOrganism();
	}
	public static  BigDecimal REFERENCE_NIPPONBARE_ID() {
		return BigDecimal.valueOf(AppContext.getDefaultOrganismId());
	}
	
	
	/**
	 * DB primary key
	 * 
	 * @return
	 */
	public BigDecimal getOrganismId();

	/**
	 * Reference genome name
	 * 
	 * @return
	 */
	public String getName();
}
