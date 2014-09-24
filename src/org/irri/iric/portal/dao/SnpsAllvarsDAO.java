package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;


import org.irri.iric.portal.domain.SnpsAllvars;
import org.springframework.dao.DataAccessException;

public interface SnpsAllvarsDAO {

	/**
	 * Get All SNP Alleles for all varieties in region
	 * @param chr	chromosome
	 * @param start	start position
	 * @param end	end position
	 * @return
	 * @throws DataAccessException
	 */
	public Set<SnpsAllvars> findVSnpAllvarsByChrPosBetween(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end) throws DataAccessException;	
	public Set<SnpsAllvars> findVSnpAllvarsByChrPosBetween(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end , int startResult, int maxRows) throws DataAccessException;

	/**
	 * Get All SNP Alleles for varieties in vars within region
	 * @param chr	chromosome
	 * @param start	start position
	 * @param end	end position
	 * @param vars
	 * @return
	 * @throws DataAccessException
	 */
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetween(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end , Collection<BigDecimal> vars) throws DataAccessException;
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetween(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end , Collection<BigDecimal> vars, int startResult, int maxRows) throws DataAccessException;

	/**
	 * Get SNP Alleles for all varieties, which are polymorphism with the reference, within the region
	 * @param chr	chromosome
	 * @param start	start position
	 * @param end	end position
	 * @return
	 * @throws DataAccessException
	 */
	public Set<SnpsAllvars> findVSnpAllvarsByChrPosBetweenRefmismatch(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end) throws DataAccessException;	
	public Set<SnpsAllvars> findVSnpAllvarsByChrPosBetweenRefmismatch(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end , int startResult, int maxRows) throws DataAccessException;
	
	/**
	 * Get SNP Alleles for varieties in vars, which are polymorphism with the reference, within the region
	 * @param chr	chromosome
	 * @param start	start position
	 * @param end	end position
	 * @return
	 * @throws DataAccessException
	 */
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetweenRefmismatch(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end , Collection<BigDecimal> vars) throws DataAccessException;
	public Set<SnpsAllvars> findVSnpAllvarsByVarsChrPosBetweenRefmismatch(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end , Collection<BigDecimal> vars, int startResult, int maxRows) throws DataAccessException;
	
	
}
