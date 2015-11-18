package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.List;



public interface VarietyByPhenotypeDAO {
	
	/**
	 * Get varieties with numeric phenotype value less than value
	 * @param phen
	 * @param value
	 * @return
	 */
	List findVarietyByQuanPhenotypeLessThan(BigDecimal phen, double value);

	/**
	 * Get varieties with numeric phenotype value greater than value
	 * @param phen
	 * @param value
	 * @return
	 */
	List findVarietyByQuanPhenotypeGreaterThan(BigDecimal phen, double value);
	
	/**
	 * Get varieties with numeric phenotype value equal to value
	 * @param phen
	 * @param value
	 * @return
	 */
	List findVarietyByQuanPhenotypeEquals(BigDecimal phen, double value);
	
	/**
	 * Get varieties with qualitative phenotype value equal to value
	 * @param phen
	 * @param value
	 * @return
	 */
	List findVarietyByQualPhenotypeEquals(BigDecimal phen, String value);
	
	/**
	 * Get phenotype values for all varieties
	 * @param phen
	 * @return
	 */
	List findVarietyByPhenotype(BigDecimal phen);
	

}
