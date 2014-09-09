package org.irri.iric.portal.variety.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.variety.domain.Germplasm;
import org.irri.iric.portal.variety.domain.List3k;

public interface VarietyFacade {
	public java.util.List getVarietyNames();
	public java.util.List getIrisIds();
	public Variety getGermplasmByName(String name);
	public Variety getGermplasmByNameLike(String name);
	public Variety getGermplasmByIrisId(String name);
	public java.util.Set getGermplasmByCountry(String country); 
	public java.util.Set getGermplasmBySubpopulation(String subpopulation); 

	//public java.util.Set getGermplasmByExample(Variety germplasm); 

	
	public java.util.List getCountries();

	//public Set getPhenotypesByGermplasmNsftid(Integer id);
	//public Set getPassportByVarietyid(Integer id); 
	
	public List getPhenotypesByGermplasm(Variety var);
	public List getPhenotypesByGermplasmName(String name);

	public java.util.List getSubpopulations();
	
	public String constructPhylotree(String varnames, String scale);
	public String constructPhylotree(Set<BigDecimal> germplasms, String scale);
	
	public Set getGermplasmByExample(Variety germplasm);
	
	public java.util.Map<String, Variety> getMapVarname2Variety();
	public Map<BigDecimal, Variety> getMapId2Variety();
	public Set getPassportByVarietyid(BigDecimal id);
	
}
