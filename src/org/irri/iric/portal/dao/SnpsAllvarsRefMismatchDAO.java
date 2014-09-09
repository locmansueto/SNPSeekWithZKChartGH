package org.irri.iric.portal.dao;

import java.util.List;

public interface SnpsAllvarsRefMismatchDAO {
	
	public List countMismatches(String chr, Integer start, Integer end);

}
