package org.irri.iric.portal.domain;

import java.math.BigDecimal;

public class VarietyImpl implements Variety {

	private BigDecimal id;
	private String name;
	private String irisId;
	private String country;
	private String subpopulation;
	
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

	
	
}
