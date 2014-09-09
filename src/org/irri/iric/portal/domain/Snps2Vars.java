package org.irri.iric.portal.domain;

import java.math.BigDecimal;

public interface Snps2Vars {

	public BigDecimal getVar1();
	public BigDecimal getVar2();
	public BigDecimal getSnpFeatureId();
	public Integer getChr();
	public BigDecimal getPos();
	public String getRefnuc();
	public String getVar1nuc();
	public String getVar2nuc();
	
}
