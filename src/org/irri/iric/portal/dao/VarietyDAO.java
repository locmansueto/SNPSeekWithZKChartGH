package org.irri.iric.portal.dao;

import java.util.Set;

import org.irri.iric.portal.domain.Variety;


public interface VarietyDAO {

	public Set findAllVariety();
	public Set findAllVarietyByCountry(String country);
	public Set findAllVarietyBySubpopulation(String subpopulation);
	//public Set findAllVarietyByExample(Variety germplasm);

	public Set findAllVarietyByCountryAndSubpopulation(String country, String subpopulation);

	public Variety findVarietyByName(String name);
	public Variety findVarietyByNameLike(String name);
	public Variety findVarietyByIrisId(String name);
	public Variety findVarietyById(Integer id);
	
	
}
