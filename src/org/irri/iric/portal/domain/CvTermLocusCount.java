package org.irri.iric.portal.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface CvTermLocusCount {


	public String getAccession();
	public String getName();
	public BigDecimal getCount();
	public String getCV();
}
