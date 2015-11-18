package org.irri.iric.portal.domain;

import java.math.BigDecimal;

/**
 * SNP in splice donor site
 * @author LMansueto
 *
 */
public interface SnpsSpliceDonor {

	/**
	 * SNP feature ID
	 * @return
	 */
	public BigDecimal getSnp();
	
	/**
	 * Nipponbare position
	 * @return
	 */
	public BigDecimal getPos();
}
