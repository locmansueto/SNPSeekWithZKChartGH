package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.irri.iric.portal.domain.SnpsAllvarsRefMismatch;

public interface SnpsAllvarsRefMismatchDAO {
	
	/**
	 * Count number of allele mismatches with the reference, for each variety, within the region
	 * @param chr
	 * @param start
	 * @param end
	 * @return
	 */
	public List countMismatches(String chr, Integer start, Integer end);
	
	/**
	 * Count number of allele mismatches with the reference, for set varieties, within the region
	 * @param chr
	 * @param start
	 * @param end
	 * @return
	 */
	public List countMismatchesInvars(Set<BigDecimal> varIds, String chr, Integer start, Integer end);

	public List<SnpsAllvarsRefMismatch> countMismatches(String chromosome,
			Integer startPos, Integer endPos, boolean isCore);

	public List<SnpsAllvarsRefMismatch> countMismatchesInvars(Set<BigDecimal> setvarIds,
			String chromosome, Integer startPos, Integer endPos, boolean isCore);
	
	 
}
