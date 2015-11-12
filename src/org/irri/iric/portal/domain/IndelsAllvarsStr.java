package org.irri.iric.portal.domain;

import java.math.BigDecimal;

public interface IndelsAllvarsStr extends IndelsAllvars {

	public String getAllele1Str();
	public String getAllele2Str();
	
	public void setAllele1(BigDecimal allele1);
	public void setAllele2(BigDecimal allele2);
	
}
