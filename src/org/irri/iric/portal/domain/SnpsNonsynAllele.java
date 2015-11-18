package org.irri.iric.portal.domain;

import java.math.BigDecimal;

/**
 * Non-synonymous allele
 * @author LMansueto
 *
 */
public interface SnpsNonsynAllele {
	
	/**
	 * SNP feature ID
	 * @return
	 */
	BigDecimal getSnp();
	
	/**
	 * allele
	 * @return
	 */
	char getAllele();

}
