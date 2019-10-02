package org.brapi.client;

import java.math.BigDecimal;

import org.codehaus.jettison.json.JSONObject;
import org.irri.iric.portal.domain.Variety;

public class BrapiVariety extends BrapiObject implements Variety{

	public BrapiVariety(JSONObject json) {
		super(json);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BigDecimal getVarietyId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIrisId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCountry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSubpopulation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAccession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCountry(String country) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSubpopulation(String subpopulation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAccession(String accession) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String printFields(String delimiter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBoxCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDataset() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
