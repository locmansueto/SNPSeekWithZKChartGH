package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;





import org.irri.iric.portal.domain.IndelsAllvars;

public interface IndelsAllvarsDAO {

	public Set<IndelsAllvars> getAllIndelCalls();

	public Set findIndelAllvarsByChrPosBetween(String chr, BigDecimal start,
			BigDecimal end);

	public Set findIndelAllvarsByVarChrPosBetween(Collection varids, String chr, BigDecimal start,
			BigDecimal end);

	public Set findIndelAllvarsByVarChrPosIn(Collection varList, String chr,
			Collection posList);

	public Set findIndelAllvarsByChrPosIn(String chr, Collection posList);

}
