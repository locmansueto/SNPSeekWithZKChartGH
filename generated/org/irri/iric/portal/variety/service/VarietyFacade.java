package org.irri.iric.portal.variety.service;

import java.util.Set;

import org.irri.iric.portal.variety.domain.Germplasm;
import org.irri.iric.portal.variety.domain.List3k;

public interface VarietyFacade {
	public java.util.List getVarietyNames();
	public java.util.List getIrisIds();
	public List3k getGermplasmByName(String name);
	public List3k getGermplasmByIrisId(String name);
	public java.util.Set getGermplasmByCountry(String country); 
	public java.util.Set getGermplasmBySubpopulation(String subpopulation); 

	public java.util.Set getGermplasmByExample(Germplasm germplasm); 

	
	public java.util.List getCountries();

	public Set getPhenotypesByGermplasmNsftid(Integer id);

	public java.util.List getSubpopulations();
	
	public String constructPhylotree(String varnames, String scale);
	public String constructPhylotree(Set<String> germplasms, String scale);
	
	public java.util.Map<String,String[]> getAccession2IRISMap() ;
	Set getGermplasmByExample(List3k germplasm);
	
}
