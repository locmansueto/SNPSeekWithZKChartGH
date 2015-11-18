package org.irri.iric.portal.dao;

import java.util.Collection;
import java.util.Map;


public interface IndelsAllvarsPosDAO extends SnpsAllvarsPosDAO{

	/**
	 * Get map of normalized Indel ID to indel object
	 * @param chromosome
	 * @param startPos
	 * @param endPos
	 * @return
	 */
	public Map getMapIndelId2Indels(String chromosome, Integer startPos, Integer endPos);
	public Map getMapIndelId2Indels(String chromosome, Collection poslist);

}
