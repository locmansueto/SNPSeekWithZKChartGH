package org.irri.iric.portal.domain;

import java.math.BigDecimal;

public interface Locus {

	/**
	 * Locus name
	 * @return
	 */
	public String getUniquename();
	
	/**
	 * Chromosome name
	 * @return
	 */
	public Integer getChr();
	
	/**
	 * Locus start position
	 * @return
	 */
	public Integer getFmin();
	
	/**
	 * Locus stop position
	 * @return
	 */
	public Integer getFmax();
	
	public Integer getStrand();
	
	public String getContig();
	
}
