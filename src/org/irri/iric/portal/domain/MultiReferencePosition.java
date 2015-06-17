package org.irri.iric.portal.domain;

import java.math.BigDecimal;

public interface MultiReferencePosition {

	String getOrganism();
	String getContig();
	BigDecimal getPosition();
	String getRefcall();

}
