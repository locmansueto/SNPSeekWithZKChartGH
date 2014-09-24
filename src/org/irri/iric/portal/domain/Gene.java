package org.irri.iric.portal.domain;

public interface Gene {
	
	/**
	 * Gene locus name
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
	
}
