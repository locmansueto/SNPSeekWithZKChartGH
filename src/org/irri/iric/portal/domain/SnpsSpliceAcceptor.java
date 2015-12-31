package org.irri.iric.portal.domain;

import java.math.BigDecimal;

/**
 * SNP in splice acceptor sites
 * @author LMansueto
 *
 */
public interface SnpsSpliceAcceptor extends Snp {


	
	/**
	 * Nipponbare Position
	 * @return
	 */
	public BigDecimal getPos();
}
