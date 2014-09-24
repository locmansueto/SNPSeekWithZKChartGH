package org.irri.iric.portal.dao;

import java.util.List;

import org.irri.iric.portal.domain.Phenotype;

public interface VarietyByPhenotypeDAO {
	
	List findVarietyByQuanPhenotypeLessThan(Phenotype phen, double value);
	List findVarietyByQuanPhenotypeGreaterThan(Phenotype phen, double value);
	List findVarietyByQuanPhenotypeEquals(Phenotype phen, int value);
	List findVarietyByQualPhenotypeEquals(Phenotype phen, String value);
	

}
