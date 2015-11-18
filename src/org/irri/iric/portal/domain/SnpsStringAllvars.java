package org.irri.iric.portal.domain;


import java.util.Map;
import java.util.Set;


public interface SnpsStringAllvars extends SnpsAllvarsRefMismatch { //, SnpsAllvars {
	
	/**
	 * Variety allele at the position
	 * @return
	 */
	public String getVarnuc();
	
	/**
	 * Map of position to allele2 for heterozygous SNPs
	 * @return
	 */
	public Map<Position, Character> getMapPos2Allele2();
	
	/**
	 * Positions with non-synonymous alleles
	 * @return
	 */
	public Set getNonsynPosset();
	
	/**
	 * Positions with splice donor site SNP
	 * @return
	 */
	public Set getDonorPosset();
	
	/**
	 * Positions with splice acceptor site SNP
	 * @return
	 */
	public Set getAcceptorPosset();
	
	/**
	 * contig name
	 * @return
	 */
	public String getContig();
	
	/**
	 * chromosome no.
	 * @return
	 */
	public Long getChr();
	
}
