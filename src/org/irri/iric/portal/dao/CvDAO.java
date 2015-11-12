package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Map;

import org.irri.iric.portal.domain.Cv;

public interface CvDAO {
	Map<String, Cv> getMapName2Cv();
	
}
