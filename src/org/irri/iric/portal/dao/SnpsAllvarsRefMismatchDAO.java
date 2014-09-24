package org.irri.iric.portal.dao;

import java.util.List;

public interface SnpsAllvarsRefMismatchDAO {
	
	/**
	 * Count number of allele mismatches with the reference, for each variety, within the region
	 * @param chr
	 * @param start
	 * @param end
	 * @return
	 */
	public List countMismatches(String chr, Integer start, Integer end);

}
