package org.irri.iric.portal.dao;

public interface SequenceDAO {

	/**
	 * Get sub sequence for contig from start to end in organism
	 * 
	 * @param featurename
	 * @param start
	 * @param stop
	 * @param organismId
	 * @return
	 * @throws Exception
	 */
	public String getSubSequence(String featurename, long start, long stop, int organismId) throws Exception;

}
