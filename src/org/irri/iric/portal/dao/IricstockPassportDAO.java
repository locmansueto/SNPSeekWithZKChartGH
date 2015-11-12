package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Set;

public interface IricstockPassportDAO {

	public Set findIricstockPassportByIricStockId(BigDecimal id);
}
