package org.irri.iric.portal.domain;

public interface MultiReferenceLocus {

	String getOrganism();
	String getContig();
	Long getStart();
	Long getEnd();
	Long getStrand();
	
}
