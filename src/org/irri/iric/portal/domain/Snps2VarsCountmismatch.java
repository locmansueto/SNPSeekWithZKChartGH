package org.irri.iric.portal.domain;

import java.math.BigDecimal;

/**
 * Container entity for allele mismatch count.
 * The actual query should be constrained by chromosome and position range
 * @author lmansueto
 *
 */
public interface Snps2VarsCountmismatch {

		/**
		 * Variety1 Id
		 * @return
		 */
		BigDecimal getVar1();
		
		/**
		 * Variety2 Id
		 * @return
		 */
		BigDecimal getVar2();
		
		/**
		 * Allele mismatches between varieties 1 and 2
		 * @return
		 */
		BigDecimal getMismatch();
}
