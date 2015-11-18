package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Set;

public interface IricstockPassportDAO {

	/**
	 * Get passport data for variety
	 * @param id
	 * @return
	 */
	public Set findIricstockPassportByIricStockId(BigDecimal id);
}
