package org.irri.iric.portal.domain;

import java.util.Map;
import java.util.Set;


public interface SnpsStringAllvars extends SnpsAllvarsRefMismatch, SnpsAllvars {
	
	//public boolean[] getIsnonsyn();
	public Map<Integer, Character> getMapPosIdx2Allele2();
	public Set getNonsynIdxset();
	
}
