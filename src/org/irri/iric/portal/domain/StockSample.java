package org.irri.iric.portal.domain;

import java.math.BigDecimal;

public interface StockSample extends Variety{

	BigDecimal getStockSampleId();
	String getAssay();
	Integer getHdf5Index();

}
