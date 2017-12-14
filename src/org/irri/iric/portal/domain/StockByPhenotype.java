package org.irri.iric.portal.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public interface StockByPhenotype extends VarietyPlus, Serializable {
	
	
	public BigDecimal getStockId();

}
