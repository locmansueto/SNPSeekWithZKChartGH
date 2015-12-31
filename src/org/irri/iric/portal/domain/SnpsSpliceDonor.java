package org.irri.iric.portal.domain;

import java.math.BigDecimal;

/**
 * SNP in splice donor site
 * @author LMansueto
 *
 */
public interface SnpsSpliceDonor extends Snp {

	
	/**
	 * Nipponbare position
	 * @return
	 */
	public BigDecimal getPos();
}
