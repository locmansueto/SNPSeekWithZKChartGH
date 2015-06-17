package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.List;


public interface VarietyByPassportDAO {

	List findVarietyByPassportEquals(BigDecimal type_id, String value);

	List findVIricstocksByPassportByTypeIdValueContaining(BigDecimal type_id,
			String value);

	List findVarietyByPassport(String sPassId);
	
}
