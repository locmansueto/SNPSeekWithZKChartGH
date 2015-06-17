package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.irri.iric.portal.domain.Gene;
import org.irri.iric.portal.domain.Organism;
import org.irri.iric.portal.domain.Variety;

public interface ListItemsDAO {
	
	public static final int PHENOTYPETYPE_NONE=0;
	public static final int PHENOTYPETYPE_QUAL=1;
	public static final int PHENOTYPETYPE_QUAN=2;
	
	public List<String> getGenenames();

	Map getIrisId2Variety();

	List getVarietyNames();

	List getIrisIds();

	Variety getGermplasmByIrisId(String name);

	Variety getGermplasmByNameLike(String name);

	Variety getGermplasmByName(String name);

//	List getGermplasmsByNameOrIrisid(String names);

	Set getGermplasmByCountry(String country);

	Set getGermplasmBySubpopulation(String subpopulation);

	Set getGermplasm();

	List getCountries();

	List getSubpopulations();

	Set getGermplasmByExample(Variety germplasm);

	Map<BigDecimal, Variety> getMapId2Variety();

	Map<String, Variety> getMapVarname2Variety();

	Map<String, BigDecimal> getPhenotypeDefinitions();

	Map<String, BigDecimal> getPassportDefinitions();

	Gene findGeneFromName(String genename, String organism);

	//Integer getFeatureLength(String feature);
	
	// for multi-reference
	List getOrganisms() throws Exception;
	List getContigs(String organism);
	Long getFeatureLength(String feature, String organism);

	List getGOTermsWithLoci(String cv, String organism);

	public Collection getGenenames(String organism);

	public Organism getOrganismByName(String name);


	
	
	
}
