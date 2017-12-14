package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Collection;

import java.util.Set;


import org.irri.iric.portal.domain.SnpsNonsynAllele;
import org.springframework.dao.DataAccessException;

/**
 * Get nonsynonymous SNPs from region
 * @author LMansueto
 *
 */
public interface SnpsNonsynAllvarsDAO {
	
/*	
	public Set<SnpsNonsynAllele> findSnpNonsynAlleleByChrPosBetween(String chr, Integer start, Integer end, BigDecimal dataset) throws DataAccessException;
	public Set<SnpsNonsynAllele> findSnpNonsynAlleleByChrPosIn(String chr, Collection listpos, BigDecimal dataset)  throws DataAccessException;
	*/
	public Set<SnpsNonsynAllele> findSnpNonsynAlleleByChrPosBetween(String chr, Integer start, Integer end, Set  variantset) throws DataAccessException;
	public Set<SnpsNonsynAllele> findSnpNonsynAlleleByChrPosIn(String chr, Collection listpos,  Set  variantset)  throws DataAccessException;

	public Set<SnpsNonsynAllele> findSnpNonsynAlleleByFeatureidIn(Collection featureid)  throws DataAccessException;
	public Boolean hasData(String ds);

	
	
}
