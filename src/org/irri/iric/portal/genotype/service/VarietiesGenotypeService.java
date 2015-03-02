package org.irri.iric.portal.genotype.service;

import java.math.BigDecimal;

import org.irri.iric.portal.domain.VariantStringData;
import org.irri.iric.portal.domain.VariantTable;

public interface VarietiesGenotypeService {
	
	/**
	 * File the table with the data, depending on the type of table
	 * @param table
	 * @param params
	 * @return
	 * @throws Exception
	 */
	
	//public VariantTable  fillVariantTable(VariantTable table,  GenotypeQueryParams params) throws Exception;

	public VariantTable fillVariantTable(VariantTable table, VariantStringData data,
			GenotypeQueryParams params) throws Exception;

	public VariantStringData queryVariantStringData(GenotypeQueryParams params)
			throws Exception;


	VariantStringData compare2VariantStrings(BigDecimal var1, BigDecimal var2,
			GenotypeQueryParams params) throws Exception;
	
}
