package org.irri.iric.portal.dao;

import java.util.Map;

import org.irri.iric.portal.domain.Cv;

public interface CvDAO {
	
	/**
	 * Get map of CV name to CV object
	 * @return
	 */
	Map<String, Cv> getMapName2Cv();
	
}
