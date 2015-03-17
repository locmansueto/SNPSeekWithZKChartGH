package org.irri.iric.portal.domain;

import java.math.BigDecimal;

public interface CvTerm {

	public BigDecimal getCvTermId();
	public String getName();
	public String getDefinition();
	
	public String getAccession();
	
}
