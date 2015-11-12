package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;








import org.irri.iric.portal.domain.IndelsAllvars;
import org.irri.iric.portal.domain.IndelsAllvarsPos;
import org.irri.iric.portal.domain.SnpsAllvarsPos;

public interface IndelsAllvarsDAO {

	public Set<IndelsAllvars> getAllIndelCalls();

	public Set<IndelsAllvars> findIndelAllvarsByChrPosBetween(String chr, BigDecimal start, BigDecimal end, List listpos);

	public Set<IndelsAllvars> findIndelAllvarsByVarChrPosBetween(Collection varids, String chr, BigDecimal start, BigDecimal end,List listpos);

	public Set<IndelsAllvars> findIndelAllvarsByVarChrPosIn(Collection varList, String chr, int posIdx[][], List listpos);
	public Set<IndelsAllvars> findIndelAllvarsByChrPosIn(String chr, int posIdx[][], List listpos);
	public Set<IndelsAllvars> findIndelAllvarsByVarChrPosIn(Collection varList, String chr, Collection posList, List listpos);
	public Set<IndelsAllvars> findIndelAllvarsByChrPosIn(String chr,  Collection posList, List listpos);

}
