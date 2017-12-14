package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Set;

public interface FeatureSynonymDAO {

	
	Set<String> getSynonyms(String name);
	Set<String> getSynonyms(BigDecimal feature_id);
}
