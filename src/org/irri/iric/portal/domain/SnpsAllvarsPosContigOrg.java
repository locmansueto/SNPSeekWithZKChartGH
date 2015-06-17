package org.irri.iric.portal.domain;

import java.math.BigDecimal;

public interface SnpsAllvarsPosContigOrg extends SnpsAllvarsPos {

	public String getContig();
	public String getOrganism();
	public BigDecimal getContigId();
	public BigDecimal getOrganismId();
}
