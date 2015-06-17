package org.irri.iric.portal.variety.service;

import java.util.Map;

public interface VarietyPropertiesService {

	
	public static String ID_ERS = "IDERS";
	public static String ID_SRA = "IDSRA";
	
	public Map getProperties(String propname);
	
}
