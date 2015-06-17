package org.irri.iric.portal.dao;

import java.util.Collection;
import java.util.Map;

public interface IndelsAllvarsPosDAO extends SnpsAllvarsPosDAO{
	

	public Map getMapIndelId2Indels(String chromosome, Integer startPos, Integer endPos);
	public Map getMapIndelId2Indels(String chromosome, Collection poslist);
	//public Map getMapIndelId2IndelsByIndelId(Collection indelids);

}
