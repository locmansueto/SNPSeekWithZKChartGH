package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.irri.iric.portal.domain.VarietyDistance;

public interface VarietyDistanceDAO {

	//List<VarietyDistance> findVarieties(String string);

	List<VarietyDistance> findVarieties(Set<BigDecimal> germplasms);
	List<VarietyDistance> findAllVarieties();

}
