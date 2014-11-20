package org.irri.iric.portal.domain;

import java.util.List;
import java.util.Map;

public interface VarietyPlusPlus extends VarietyPlus {

	Map getValueMap();
	void addValue(String name, Object value);
	
}
