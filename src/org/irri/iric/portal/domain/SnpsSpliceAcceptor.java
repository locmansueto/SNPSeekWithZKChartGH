package org.irri.iric.portal.domain;

import java.math.BigDecimal;

/**
 * SNP in splice acceptor sites
 * @author LMansueto
 *
 */
public interface SnpsSpliceAcceptor {

	/**
	 * SNP feature ID
	 * @return
	 */
	public BigDecimal getSnp();
	
	/**
	 * Nipponbare Position
	 * @return
	 */
	public BigDecimal getPos();
}
