package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public interface SnpsStringDAO {

	Map readSNPString(int chr, int startIdx, int endIdx); //throws Exception;

	Map readSNPString(int chr, int[] posIdxs); // throws Exception;

	Map readSNPString(Set<BigDecimal> colVarids, int chr, int[] posIdxs); // throws Exception;

	Map readSNPString(Set<BigDecimal> colVarids, int chr, int startIdx, int endIdx); //throws Exception;


}
