package org.irri.iric.portal.dao;

import java.math.BigDecimal;

public interface SequenceDAO {

	public String getSubSequence(String featurename, long start, long stop) throws Exception;
	public String getSubSequence(BigDecimal featureid, long start, long stop) throws Exception;
	
}
