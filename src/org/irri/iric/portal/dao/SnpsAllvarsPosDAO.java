package org.irri.iric.portal.dao;

import java.util.List;

public interface SnpsAllvarsPosDAO {
	
	public List getSNPs(String chromosome, Integer startPos, Integer endPos );
}
