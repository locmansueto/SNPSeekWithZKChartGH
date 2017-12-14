package org.irri.iric.portal.domain;


/**
 * Variety plus one of either Passport Value or Phenotype Value
 * @author lmansueto
 *
 */
public interface VarietyPlus extends Variety {
	
	
	

	public void setValue(Object value);
	public Object getValue();
	//public String getValueName();
	//public void setValueName(String valuename);
	
}
