package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.irri.iric.portal.domain.VarietyDistance;

public interface VarietyDistanceDAO {


	/**
	 * Get distance between varieties for variety IDs in germplasms
	 * @param germplasms
	 * @return
	 */
	List<VarietyDistance> findVarieties(Set<BigDecimal> germplasms);
	
	/**
	 * Get distance between varieties for all varieties
	 * @param germplasms
	 * @return
	 */
	List<VarietyDistance> findAllVarieties();

}
