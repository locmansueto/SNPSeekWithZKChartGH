package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;





import org.irri.iric.portal.domain.IndelsAllvars;

public interface IndelsAllvarsDAO {

	public Set<IndelsAllvars> getAllIndelCalls();

	public Set findIndelAllvarsByChrPosBetween(Integer chr, BigDecimal start,
			BigDecimal end);

	public Set findIndelAllvarsByVarChrPosBetween(Collection varids, Integer chr, BigDecimal start,
			BigDecimal end);

	public Set findIndelAllvarsByVarChrPosIn(Collection varList, Integer chr,
			Collection posList);

	public Set findIndelAllvarsByChrPosIn(Integer chr, Collection posList);

}
