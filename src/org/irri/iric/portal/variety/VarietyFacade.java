package org.irri.iric.portal.variety;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.irri.iric.portal.domain.Variety;


/**
 * API functions used mostly by Variety Query
 * @author LMansueto
 *
 */

public interface VarietyFacade {
	
	
	public static String COMPARATOR_EQUALS = "=";
	public static String COMPARATOR_LESSTHAN = "<";
	public static String COMPARATOR_GREATERTHAN = ">";
	public static String COMPARATOR_LIKE = "like";
	
	// variety object query methods
	
	
	/**
	 * Get list of variety names
	 * @return
	 */
	public java.util.List getVarietyNames();
	

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
	
		
	public Map getIrisId2Variety();

		/**
	 * Get varieties with passport = value
	 * @param typeId
	 * @param value
	 * @return
	 */
	public List getVarietyByPassport(String definition, String value);
		
	
	/**
	 * Get varieties that satistfy the phenotype values
	 * 
	 * @param definition
	 * @param comparator
	 * @param value
	 * @param phenotype_type   values can be (org.irri.iric.portal.dao.ListItemsDAO.PHENOTYPETYPE_NONE, PHENOTYPETYPE_QUAL, PHENOTYPETYPE_QUAN)
	 * @return
	 */
	public List getVarietyByPhenotype(String definition, String comparator,
			String value, int phenotype_type);
	public List getVarietyByPhenotype(String phenId);
	
	
	
	// User interface listbox items query methods
	
	/**
	 * Get list of IRIS IDs
	 * @return
	 */
	public java.util.List getIrisIds();
	
	
	/**
	 * Get list of countries
	 * @return
	 */
	public java.util.List getCountries();


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
	
	/**
	 * Controlled terms/definitions for passport, phenotypes
	 * @return
	 */
	public Map<String, BigDecimal> getPassportDefinitions();
	public Map<String, BigDecimal> getPhenotypeDefinitions();
	

	// Phenotypes query methods

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
	 * 
	 * @param definition
	 * @return  Object[0] is a Set of unique values
	 * 			Object[1] is an Integer with values (org.irri.iric.portal.dao.ListItemsDAO.PHENOTYPETYPE_NONE, PHENOTYPETYPE_QUAL, PHENOTYPETYPE_QUAN)
	 */
	public Object[] getPhenotypeUniqueValues(String definition);
	
	
	/**
	 * Get phenotype values for all germplasms
	 * @param phenotype
	 * @return
	 */
	public Map<BigDecimal, Object> getPhenotypeValues(String phenotype);
	
	// Passport query methods

	/**
	 * Get list of passport/values for vartiety ID
	 * @param id
	 * @return
	 */
	public Set getPassportByVarietyid(BigDecimal id);

	
	/**
	 * Get unique values from all varieties for passport,phenotype
	 * @param definition
	 * @return
	 */
	public Set getPassportUniqueValues(String definition);
	
	
	// phylogenetic tree construction methods
	public String constructPhylotree(String ids, String scale, String requestid);
	
	public String constructPhylotree(Set<BigDecimal> germplasms, String scale, String requestid);
	
	public String constructPhylotree(String varids, String scale, String topN, String requestid);

	 
	// MDS Plot construction methods
	
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
	 * Get map of variety to property
	 * @param propname 
	 * @return
	 */
	public Map getVarietyExternalURL(String propname);


	/**
	 * Check varities in database
	 * @param varstrs
	 * @return
	 */
	public Set checkVariety(String varstrs);
	
	
	//public List getVarietyByPassport(String sPassId);
	
	


	
	
	
	
}
