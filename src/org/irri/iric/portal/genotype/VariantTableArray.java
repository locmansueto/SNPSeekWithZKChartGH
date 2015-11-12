package org.irri.iric.portal.genotype;

import java.math.BigDecimal;

import org.irri.iric.portal.domain.Position;

public interface VariantTableArray extends VariantTable {


	public Position[] getPosition();

	public String[] getReference();

	public Object[][] getVaralleles();

	public Long[] getVarid();

	public String[] getVarname();

	public Double[] getVarmismatch();
}
