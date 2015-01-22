package org.irri.iric.portal.genotype.service;

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
	
	public VariantTable  fillVariantTable(VariantTable table,  GenotypeQueryParams params) throws Exception;
	
}
