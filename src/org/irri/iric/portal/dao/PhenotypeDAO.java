package org.irri.iric.portal.dao;

import java.util.List;

import org.irri.iric.portal.domain.Variety;

public interface PhenotypeDAO {
	
	public List findPhenotypesByVariety(Variety var);
	public List findPhenotypesByVarietyNameLike(String name);
	
}
