package org.irri.iric.portal.dao;

import java.util.List;
import java.util.Map;

import org.irri.iric.portal.domain.Organism;

public interface OrganismDAO {

	//public List<Organism> getOrganisms(); 
	public Map<String,Organism> getMapName2Organism();
	public Organism getOrganismByID(Integer id);
}
