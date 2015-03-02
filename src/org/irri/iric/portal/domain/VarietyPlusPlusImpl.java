package org.irri.iric.portal.domain;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class VarietyPlusPlusImpl  implements VarietyPlusPlus  {
	
	private Variety varplus;
	private Map mapValues;

	public VarietyPlusPlusImpl(VarietyPlus varietyplus2, String val2name) {
		super();
		// TODO Auto-generated constructor stub
		this.varplus = varietyplus2;
		mapValues = new LinkedHashMap();
		mapValues.put( val2name, varietyplus2.getValue());
	}

	
	public VarietyPlusPlusImpl(Variety varietyplus1, VarietyPlus varietyplus2, String val2name) {
		super();
		// TODO Auto-generated constructor stub
		this.varplus = varietyplus1;
		mapValues = new LinkedHashMap();
		//mapValues.put( varietyplus1.getValueName(), varietyplus1.getValue());
		mapValues.put( val2name, varietyplus2.getValue());
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return  mapValues.values();
	}
	
/*
	@Override
	public String getValueName() {
		// TODO Auto-generated method stub
		return mapValues.keySet().toString();
	}
*/
	@Override
	public Map getValueMap() {
		// TODO Auto-generated method stub
		return mapValues;
	}

	public Object getValue(String field) {
		// TODO Auto-generated method stub
		return mapValues.get(field);
	}

	
	@Override
	public BigDecimal getVarietyId() {
		// TODO Auto-generated method stub
		return varplus.getVarietyId();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return varplus.getName();
	}

	@Override
	public String getIrisId() {
		// TODO Auto-generated method stub
		return varplus.getIrisId();
	}

	@Override
	public String getCountry() {
		// TODO Auto-generated method stub
		return varplus.getCountry();
	}

	@Override
	public String getSubpopulation() {
		// TODO Auto-generated method stub
		return varplus.getSubpopulation();
	}

	@Override
	public void setCountry(String country) {
		// TODO Auto-generated method stub
		varplus.setCountry(country);
		
	}

	@Override
	public void setSubpopulation(String subpopulation) {
		// TODO Auto-generated method stub
		varplus.setSubpopulation(subpopulation);
		
	}
/*
	@Override
	public void setValueName(String valuename) {
		// TODO Auto-generated method stub
		
	}
*/
	@Override
	public void addValue(String name, Object value) {
		// TODO Auto-generated method stub
		mapValues.put(name, value);
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


	@Override
	public String printFields(String delimiter) {
		// TODO Auto-generated method stub
		String irisid = getIrisId();
		if(irisid==null) irisid="";
		String subpop = getSubpopulation();
		if(subpop==null) subpop="";
		String cntr = getCountry();
		if(cntr==null) cntr="";
		
	
		StringBuffer buff = new StringBuffer();	
		buff.append(this.getName() + delimiter + irisid + delimiter + subpop + delimiter + cntr);
		Iterator<String> itName =  mapValues.keySet().iterator();
		if(itName.hasNext()) buff.append(delimiter);
		while(itName.hasNext()) {
			buff.append( mapValues.get(itName.next()) );
			if(itName.hasNext()) buff.append(delimiter);
		}
		return buff.toString();
	
	}


	@Override
	public String getBoxCode() {
		// TODO Auto-generated method stub
		return varplus.getBoxCode();
	}
	
	
	
	
}
