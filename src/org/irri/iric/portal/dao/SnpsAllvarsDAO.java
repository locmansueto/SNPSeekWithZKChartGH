package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;


import org.irri.iric.portal.domain.SnpsAllvars;
import org.springframework.dao.DataAccessException;

public interface SnpsAllvarsDAO {

	public Set<SnpsAllvars> findVSnpAllvarsByChrPosBetween(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end) throws DataAccessException;	
	public Set<SnpsAllvars> findVSnpAllvarsByChrPosBetween(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end , int startResult, int maxRows) throws DataAccessException;
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetween(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end , Collection<BigDecimal> vars) throws DataAccessException;
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetween(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end , Collection<BigDecimal> vars, int startResult, int maxRows) throws DataAccessException;

	public Set<SnpsAllvars> findVSnpAllvarsByChrPosBetweenRefmismatch(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end) throws DataAccessException;	
	public Set<SnpsAllvars> findVSnpAllvarsByChrPosBetweenRefmismatch(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end , int startResult, int maxRows) throws DataAccessException;
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetweenRefmismatch(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end , Collection<BigDecimal> vars) throws DataAccessException;
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetweenRefmismatch(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end , Collection<BigDecimal> vars, int startResult, int maxRows) throws DataAccessException;
	
	
}
