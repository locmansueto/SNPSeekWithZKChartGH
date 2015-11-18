package org.irri.iric.portal.domain;

import java.math.BigDecimal;

/**
 * Synonymous SNPs
 * @author LMansueto
 *
 */
public interface SnpsSynAllele {
	
	/**
	 * SNP fetaure ID
	 * @return
	 */
	BigDecimal getSnp();
	
	/**
	 * synonymous allele
	 * @return
	 */
	char getAllele();

}
