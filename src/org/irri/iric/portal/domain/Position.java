package org.irri.iric.portal.domain;

import java.math.BigDecimal;

public interface Position extends Comparable {

	/**
	 * contig name
	 * @return
	 */
	public String getContig();
	
	/**
	 * bp position
	 * @return
	 */
	public BigDecimal getPosition();
	
	/**
	 * reference nuc
	 * @return
	 */
	public String getRefcall();
	
	/**
	 * chromosome no.
	 * @return
	 */
	public Long getChr();
}
