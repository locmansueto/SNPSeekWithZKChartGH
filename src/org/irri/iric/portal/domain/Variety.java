package org.irri.iric.portal.domain;

import java.math.BigDecimal;

public interface Variety {
	
	public BigDecimal getVarietyId();
	public String getName();
	public String getIrisId();
	public String getCountry();
	public String getSubpopulation();
	
	public void setCountry(String country);
	public void setSubpopulation(String subpopulation);
	
	
}
