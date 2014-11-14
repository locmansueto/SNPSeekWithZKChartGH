package org.irri.iric.portal.variety.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.irri.iric.portal.domain.Variety;

public interface VarietyFacade {
	
	
	public static String COMPARATOR_EQUALS = "=";
	public static String COMPARATOR_LESSTHAN = "<";
	public static String COMPARATOR_GREATERTHAN = ">";
	public static String COMPARATOR_LIKE = "like";
	
	
	
	/**
	 * Get list of variety names
	 * @return
	 */
	public java.util.List getVarietyNames();
	
	/**
	 * Get list of IRIS IDs
	 * @return
	 */
	public java.util.List getIrisIds();
	
	/**
	 * Get Variety object from name
	 * @param name
	 * @return
	 */
	public Variety getGermplasmByName(String name);
	
	/**
	 * 
	 * Get Variety object from name like 
	 * @param name
	 * @return
	 */
	public Variety getGermplasmByNameLike(String name);
	
	/**
	 * Get variety object from IRIS ID
	 * @param name
	 * @return
	 */
	public Variety getGermplasmByIrisId(String name);
	
	/**
	 * Get varieties from country
	 * @param country
	 * @return
	 */
	public java.util.Set getGermplasmByCountry(String country); 
	
	
	/**
	 * Get all varieties
	 * @return
	 */
	public java.util.Set getGermplasm(); 
	
	
	/**
	 * Get varieties in subpopulation
	 * @param subpopulation
	 * @return
	 */
	public java.util.Set getGermplasmBySubpopulation(String subpopulation); 


	/**
	 * Get list of countries
	 * @return
	 */
	public java.util.List getCountries();


	/**
	 * Get list of phenotypes/values for variety
	 * @param var
	 * @return
	 */
	public List getPhenotypesByGermplasm(Variety var);
	
	/**
	 * Get list of phenotypes/values for variety with name
	 * @param name
	 * @return
	 */
	public List getPhenotypesByGermplasmName(String name);

	/**
	 * Getg list of subpopulations
	 * @return
	 */
	public java.util.List getSubpopulations();
	
	/**
	 * Construct phylogenetic tree from comma separated ID list of variety IDs
	 * @param varnames
	 * @param scale
	 * @return
	 */
	public String constructPhylotree(String ids, String scale, String requestid);
	
	/**
	 * Construct phylogenetic tree from set of variety IDs
	 * @param germplasms
	 * @param scale
	 * @return
	 */
	public String constructPhylotree(Set<BigDecimal> germplasms, String scale, String requestid);
	
	public String constructPhylotree(String varids, String scale, String topN, String requestid);

	/**
	 * 
	 * @param germplasms	list of variety Ids (include even if showAll=true because it's used in ordering)
	 * @param scale
	 * @param showAll		all varieties are used
	 * @return
	 */
	public double[][] constructMDSPlot(List<BigDecimal> germplasms, String scale, boolean showAll);
	public double[][] constructMDSPlot(String ids, String scale,  boolean showAll);
	
	
	/**
	 * Get variety matching properties in example
	 * @param germplasm
	 * @return
	 */
	public Set getGermplasmByExample(Variety germplasm);
	
	/**
	 * map variety name to variety object
	 * @return
	 */
	public java.util.Map<String, Variety> getMapVarname2Variety();
	
	/**
	 * map variety ID to variety object
	 * @return
	 */
	public Map<BigDecimal, Variety> getMapId2Variety();
	
	/**
	 * Get list of passport/values for vartiety ID
	 * @param id
	 * @return
	 */
	public Set getPassportByVarietyid(BigDecimal id);

	
	/**
	 * Controlled terms/definitions for passport, phenotypes
	 * @return
	 */
	public Map<String, BigDecimal> getPassportDefinitions();
	public Map<String, BigDecimal> getPhenotypeDefinitions();

	/**
	 * Get unique values from all varieties for passport,phenotype
	 * @param definition
	 * @return
	 */
	public Set getPassportUniqueValues(String definition);
	public Set getPhenotypeUniqueValues(String definition);

	/**
	 * Get varieties with passport = value
	 * @param typeId
	 * @param value
	 * @return
	 */
	public List getVarietyByPassport(String definition, String value);
	

	/**
	 * Get varieties with phenotype, comparator, value
	 * @param phenotypeId
	 * @param comparator
	 * @param qualvalue
	 * @param quanvalue
	 * @return
	 */
	public List getVarietyByPhenotype(String definition, String comparator, String value);

	
	/**
	 * 
	 * @param names	  comma separated names or iris ids
	 * @return
	 */
	public List getGermplasmsByNameOrIrisid(String names);

	public Map getIrisId2Variety();
	
	
	
}
