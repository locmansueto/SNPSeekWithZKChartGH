package org.irri.iric.portal.domain;



/**
 * Locus interface
 * @author LMansueto
 *
 */
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
	public String getChr();
	
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
	
	/**
	 * Strand
	 * @return
	 */
	public Integer getStrand();
	
	/**
	 * contig name
	 * @return
	 */
	public String getContig();
	
	/**
	 * Decription
	 * @return
	 */
	public String getDescription();
	
}


