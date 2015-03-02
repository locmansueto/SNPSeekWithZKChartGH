package org.irri.iric.portal.domain;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public interface IndelsStringAllvars extends SnpsStringAllvars {  //SnpsAllvarsRefMismatch, IndelsAllvars {

	public BigDecimal getAllele1(BigDecimal pos);
	public BigDecimal getAllele2(BigDecimal pos);
	public Map<BigDecimal, IndelsAllvars> getMapPos2Indels();
	
	// for update in merging
	//public void setVarnuc(String varnuc);
	//public void setMismatch(BigDecimal mismatch);
	//public void setMapPosIdx2Allele2(Map mapPosidx2Allele2);
	//public void setNonsynIdxset(Set setNonsynIdx);
	
	public void delegateSnpString(SnpsStringAllvars snpstring);
	
	
	
}

