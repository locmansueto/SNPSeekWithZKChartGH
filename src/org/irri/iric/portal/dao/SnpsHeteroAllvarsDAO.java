package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;

import org.irri.iric.portal.domain.SnpsAllvars;
import org.irri.iric.portal.domain.SnpsHeteroAllele2;
import org.springframework.dao.DataAccessException;

public interface SnpsHeteroAllvarsDAO {

	
	public Set<SnpsHeteroAllele2> findSnpsHeteroVarsChrPosBetween(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end , Collection<BigDecimal> vars, BigDecimal type) throws DataAccessException;
	public Set<SnpsHeteroAllele2> findSnpsHeteroAllvarsChrPosBetween(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end, BigDecimal type) throws DataAccessException;
	
	public Set<SnpsHeteroAllele2> findSnpsHeteroVarsChrPosIn(Integer chr, Collection<BigDecimal> snps, Collection<BigDecimal> vars, BigDecimal type) throws DataAccessException;
	public Set<SnpsHeteroAllele2> findSnpsHeteroAllvarsChrPosIn(Integer chr, Collection<BigDecimal> snps, BigDecimal type) throws DataAccessException;


	
}
