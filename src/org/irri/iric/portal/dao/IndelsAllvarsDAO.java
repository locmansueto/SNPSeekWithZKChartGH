package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;








import org.irri.iric.portal.domain.IndelsAllvars;



/**
 * Get indels for all varieties
 * @author LMansueto
 *
 */
public interface IndelsAllvarsDAO {

	/**
	 * Get all indels
	 * @return
	 */
	public Set<IndelsAllvars> getAllIndelCalls();
	
	/**
	 * Get indels in chr name, between start and end, and in position
	 * @param chr
	 * @param start
	 * @param end
	 * @param listpos
	 * @return
	 */
	public Set<IndelsAllvars> findIndelAllvarsByChrPosBetween(String chr, BigDecimal start, BigDecimal end, List listpos);
	public Set<IndelsAllvars> findIndelAllvarsByVarChrPosBetween(Collection varids, String chr, BigDecimal start, BigDecimal end,List listpos);
	public Set<IndelsAllvars> findIndelAllvarsByVarChrPosIn(Collection varList, String chr, int posIdx[][], List listpos);
	public Set<IndelsAllvars> findIndelAllvarsByChrPosIn(String chr, int posIdx[][], List listpos);
	public Set<IndelsAllvars> findIndelAllvarsByVarChrPosIn(Collection varList, String chr, Collection posList, List listpos);
	public Set<IndelsAllvars> findIndelAllvarsByChrPosIn(String chr,  Collection posList, List listpos);

}
