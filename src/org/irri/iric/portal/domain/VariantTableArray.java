package org.irri.iric.portal.domain;

import java.math.BigDecimal;

public interface VariantTableArray extends VariantTable {


	public BigDecimal[] getPosition();

	public String[] getReference();

	public Object[][] getVaralleles();

	public Long[] getVarid();

	public String[] getVarname();

	public Double[] getVarmismatch();
}
