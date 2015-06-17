package org.irri.iric.portal.domain;

import java.math.BigDecimal;

public interface MultiReferenceConversion {

	String getFromOrganism();
	String getFromContig();
	BigDecimal getFromPosition();
	String getFromRefcall();
	
	
	String getToOrganism();
	String getToContig();
	BigDecimal getToPosition();
	String getToRefcall();
	
}
