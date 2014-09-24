package org.irri.iric.portal.domain;

import java.math.BigDecimal;

public interface Variety {
	
	
	/**
	 * Variety Id
	 * @return
	 */
	public BigDecimal getVarietyId();
	
	/**
	 * Variety name from source
	 * @return
	 */
	public String getName();
	
	/**
	 * IRIS Unique ID
	 * @return
	 */
	public String getIrisId();
	
	/**
	 * Country of origin
	 * @return
	 */
	public String getCountry();
	
	/**
	 * Subpopulation (indica, japonica, aus, .. etc )
	 * @return
	 */
	public String getSubpopulation();
	
	
	/**
	 * Used for query by example
	 * @param country
	 */
	public void setCountry(String country);
	public void setSubpopulation(String subpopulation);
	
	
}
