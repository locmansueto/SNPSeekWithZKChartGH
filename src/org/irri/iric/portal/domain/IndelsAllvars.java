package org.irri.iric.portal.domain;

import java.math.BigDecimal;

/**
 * Container entity for Indel-Genotype data
 * @author LMansueto
 *
 */
public interface IndelsAllvars extends SnpsAllvars {

	/**
	 * Indel allele1 ID
	 * @return
	 */
	public BigDecimal getAllele1();
	/**
	 * Indel allele2 ID
	 * @return
	 */
	public BigDecimal getAllele2();

	
}
