package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.irri.iric.portal.chado.oracle.domain.VIndelRefposindex;

public interface IndelsAllvarsPosDAO extends SnpsAllvarsPosDAO {

	/**
	 * Get map of normalized Indel ID to indel object
	 * 
	 * @param chromosome
	 * @param startPos
	 * @param endPos
	 * @return
	 */
	public Map getMapIndelId2Indels(String chromosome, Integer startPos, Integer endPos, Set variantset);

	public Map getMapIndelId2Indels(String chromosome, Collection poslist, Set variantset);
	// public Map<BigDecimal, VIndelRefposindex> getMapIndelId2Indels(String
	// chromosome, Integer startPos, Integer endPos, Set variantset);

}
