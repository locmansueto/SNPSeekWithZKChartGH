package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.List;


public interface VarietyByPassportDAO {

	/**
	 * Get varieties with passport equal to value 
	 * @param type_id
	 * @param value
	 * @return
	 */
	List findVarietyByPassportEquals(BigDecimal type_id, String value);

	/**
	 * Get passport values for all varieties
	 * @param sPassId
	 * @return
	 */
	List findVarietyByPassport(String sPassId);

	
	//List findVIricstocksByPassportByTypeIdValueContaining(BigDecimal type_id, String value);
	
}
