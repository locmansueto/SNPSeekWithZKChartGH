package org.irri.iric.portal.gwas.domain;

import java.math.BigDecimal;

import org.irri.iric.portal.domain.Position;
import org.irri.iric.portal.domain.PositionLogPvalue;

public interface ManhattanPlot extends Position, PositionLogPvalue {

	BigDecimal getMarkerId();
	// BigDecimal getMinusLogP();

}
