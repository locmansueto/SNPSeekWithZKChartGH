package org.irri.iric.portal.domain;

import java.math.BigDecimal;

/**
 * Controlled vocabulary CV
 * @author LMansueto
 *
 */
public interface Cv {
	
	/**
	 * DB primary key
	 * @return
	 */
	public BigDecimal getCvId();
	
	/**
	 * CV name
	 * @return
	 */
	public String getName();
	
	/**
	 * CV definition
	 * @return
	 */
	public String getDefinition();
	
}
