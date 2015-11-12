package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface SnpsStringDAO {

	/**
	 *	Get variants for all varieties 
	 *	the return is a map of variety_id to variants string\
	 *	Map<BigDecimalId, SnpString> 
	 *   
	 */
	
	Map readSNPString(String chr, int startIdx, int endIdx); //throws Exception;

	Map readSNPString(String chr, int[] posIdxs); // throws Exception;

	Map readSNPString(Set<BigDecimal> colVarids, String chr, int[] posIdxs); // throws Exception;

	Map readSNPString(Set<BigDecimal> colVarids, String chr, int startIdx, int endIdx); //throws Exception;

	Map readSNPString(Set colVarids, String chr, int[][] posidxstartend);

	Map readSNPString(String chr, int[][] posidxstartend);


}
