package org.irri.iric.portal.domain;

import java.math.BigDecimal;

public interface Phenotype {

	/**
	 * Phenotype ID
	 * @return
	 */
	public BigDecimal getPhenotypeId();
	
	
	/**
	 * Phenotype controlled term name
	 * @return
	 */
	 public String getName();
	 
	 /**
	  * Human readable description
	  * @return
	  */
	 public String getDefinition();
	 
	 /**
	  * Quantitative value
	  * @return
	  */
	 public BigDecimal getQuanValue();
	 
	 /**
	  * Qualitative value
	  * @return
	  */
	 public String getQualValue(); 
}
