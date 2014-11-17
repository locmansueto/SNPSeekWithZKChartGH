package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	List getGermplasmsByNameOrIrisid(String names);

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
}
