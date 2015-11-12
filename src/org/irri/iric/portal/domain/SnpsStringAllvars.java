package org.irri.iric.portal.domain;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;


public interface SnpsStringAllvars extends SnpsAllvarsRefMismatch { //, SnpsAllvars {
	
	/**
	 * Variety allele at the position
	 * @return
	 */
	public String getVarnuc();
	
	
	//public boolean[] getIsnonsyn();
	//public Map<Position, Character> getMapPosIdx2Allele2();
	public Map<Position, Character> getMapPos2Allele2();
	//public Set getNonsynIdxset();
	public Set getNonsynPosset();
	public Set getDonorPosset();
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
