package org.irri.iric.portal.domain;

import java.math.BigDecimal;

public class VarietyImpl implements Variety {

	protected BigDecimal id;
	protected String name;
	protected String irisId;
	protected String country;
	protected String subpopulation;
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getIrisId() {
		// TODO Auto-generated method stub
		return irisId;
	}

	@Override
	public String getCountry() {
		// TODO Auto-generated method stub
		return country;
	}

	@Override
	public String getSubpopulation() {
		// TODO Auto-generated method stub
		return subpopulation;
	}

	@Override
	public void setCountry(String country) {
		// TODO Auto-generated method stub
		this.country = country;
	}

	@Override
	public void setSubpopulation(String subpopulation) {
		// TODO Auto-generated method stub
		this.subpopulation = subpopulation;
	}

	@Override
	public BigDecimal getVarietyId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((getVarietyId() == null) ? 0 : getVarietyId().hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Variety)) //VIricstockBasicprop))
			return false;
		//VIricstockBasicprop equalCheck = (VIricstockBasicprop) obj;
		Variety equalCheck = (Variety) obj;
		
		//return iricStockId.equals(equalCheck.getIricStockId());
		
		
		if ((getVarietyId() == null && equalCheck.getVarietyId() != null) || (getVarietyId() != null && equalCheck.getVarietyId() == null))
			return false;
		if (getVarietyId() != null && !getVarietyId().equals(equalCheck.getVarietyId()))
			return false;
		return true;
		
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return getName().compareTo( ((Variety)o).getName() );
	}
	
	
	
	
}
