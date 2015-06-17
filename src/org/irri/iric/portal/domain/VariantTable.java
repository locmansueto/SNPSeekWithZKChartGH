package org.irri.iric.portal.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.irri.iric.portal.genotype.service.GenotypeQueryParams;

public interface VariantTable {
	
	/**
	 * Set the data from genotype database query result
	 * @param data
	 */
	//public void setVariantStringData(VariantStringData data);
	public VariantStringData getVariantStringData();
	
	/**
	 * Set messsage to display to interface
	 * @return
	 */
	public String getMessage();
	public void setMessage(String message);
	
	/**
	 * Recreate result based on param settings, without requery to the database 
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	//public VariantTable review(GenotypeQueryParams params );
	void setVariantStringData(VariantStringData data, GenotypeQueryParams params) throws Exception;

	public String[] getContigs();
	
	
//	public Long[] getPosition();
//
//	public String[] getReference();
//
//	public Object[][] getVaralleles();
//
//	public Long[] getVarid();
//
//	public String[] getVarname();
//
//	public Double[] getVarmismatch();
	
}
