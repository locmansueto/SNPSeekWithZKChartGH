package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.oracle.domain.VSnpNonsynallelePos;
import org.irri.iric.portal.domain.SnpsNonsynAllele;
import org.irri.iric.portal.domain.SnpsSynAllele;
import org.springframework.dao.DataAccessException;

public interface SnpsSynAllvarsDAO {
	
	/*
	public Set<SnpsNonsynAllele> findSnpNonsynAlleleByChrPosBetween(String chr, Integer start, Integer end) throws DataAccessException;
	public Set<SnpsNonsynAllele> findSnpNonsynAlleleByChrPosIn(String chr, Collection listpos)  throws DataAccessException;
	
	public Set<SnpsNonsynAllele> findSnpNonsynAlleleBySnpfeatureidBetween(BigDecimal start, BigDecimal end)  throws DataAccessException;
	public Set<SnpsNonsynAllele> findSnpNonsynAlleleBySnpfeatureidIn(Collection listFeatureids) throws DataAccessException;
	*/

	
	public Set<SnpsSynAllele> findSnpSynAlleleByChrPosBetween(String chr, Integer start, Integer end, BigDecimal dataset) throws DataAccessException;
	public Set<SnpsSynAllele> findSnpSynAlleleByChrPosIn(String chr, Collection listpos, BigDecimal dataset)  throws DataAccessException;
	
	public Set<SnpsSynAllele> findSnpSynAlleleBySnpfeatureidBetween(BigDecimal start, BigDecimal end, BigDecimal dataset)  throws DataAccessException;
	public Set<SnpsSynAllele> findSnpSynAlleleBySnpfeatureidIn(Collection listFeatureids, BigDecimal dataset) throws DataAccessException;
	
	
}
