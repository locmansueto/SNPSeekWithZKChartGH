package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface SnpsAllvarsPosDAO {
	
	/**
	 * Get SNP positions for all varieties in the region
	 * @param chromosome	
	 * @param startPos
	 * @param endPos
	 * @return
	 */
	public List getSNPs(String chromosome, Integer startPos, Integer endPos , BigDecimal type);

	public List getSNPs(String chromosome, Integer startPos, Integer endPos, BigDecimal type,
			int firstRow, int maxRows);

	//public List getSNPs(Collection varids, String chromosome, Integer startPos, Integer endPos, BigDecimal type,
	//		int firstRow, int maxRows);

	//public Set getSNPsInChromosome(String chr, Set posset, BigDecimal type);

	public List getSNPsInChromosome(String chr, Collection posset, BigDecimal type);

}
