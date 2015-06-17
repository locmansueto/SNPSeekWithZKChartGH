package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.List;

import org.irri.iric.portal.domain.Phenotype;

public interface VarietyByPhenotypeDAO {
	
	List findVarietyByQuanPhenotypeLessThan(BigDecimal phen, double value);
	List findVarietyByQuanPhenotypeGreaterThan(BigDecimal phen, double value);
	List findVarietyByQuanPhenotypeEquals(BigDecimal phen, double value);
	List findVarietyByQualPhenotypeEquals(BigDecimal phen, String value);
	List findVarietyByPhenotype(BigDecimal phen);
	

}
