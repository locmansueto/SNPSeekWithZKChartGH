package org.irri.iric.portal.dao;

import java.util.List;

public interface SnpsAllvarsPosDAO {
	
	/**
	 * Get SNP positions for all varieties in the region
	 * @param chromosome	
	 * @param startPos
	 * @param endPos
	 * @return
	 */
	public List getSNPs(String chromosome, Integer startPos, Integer endPos );
}
