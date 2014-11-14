package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.List;

import org.irri.iric.portal.domain.SnpsStringAllvars;

public interface SnpsStringAllvarsDAO {
	

	public List<SnpsStringAllvars> getSNPsString(Integer chr, BigDecimal start,
			BigDecimal end, int firstRow, int maxRows);

}
