package org.irri.iric.portal.domain;

public interface VariantTableArray extends VariantTable {


	public Double[] getPosition();

	public String[] getReference();

	public Object[][] getVaralleles();

	public Long[] getVarid();

	public String[] getVarname();

	public Double[] getVarmismatch();
}
