package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Collection;

import java.util.Set;



import org.irri.iric.portal.domain.SnpsSynAllele;
import org.springframework.dao.DataAccessException;

/**
 * Synonymous snps
 * @author LMansueto
 *
 */
public interface SnpsSynAllvarsDAO {
	
	
	public Set<SnpsSynAllele> findSnpSynAlleleByChrPosBetween(String chr, Integer start, Integer end, BigDecimal dataset) throws DataAccessException;
	public Set<SnpsSynAllele> findSnpSynAlleleByChrPosIn(String chr, Collection listpos, BigDecimal dataset)  throws DataAccessException;

	
}
