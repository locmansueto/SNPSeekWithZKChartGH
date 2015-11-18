package org.irri.iric.portal.domain;

import java.math.BigDecimal;

/**
 * Controlled vocabulary (CV) terms
 * @author LMansueto
 *
 */
public interface CvTerm {

	/**
	 * DB primary key
	 * @return
	 */
	public BigDecimal getCvTermId();
	
	/**
	 * CV term name
	 * @return
	 */
	public String getName();
	
	/**
	 * CV term definition
	 * @return
	 */
	public String getDefinition();
	
	/**
	 * CV term accession
	 * @return
	 */
	public String getAccession();
	
}
