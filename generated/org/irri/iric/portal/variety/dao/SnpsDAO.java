package org.irri.iric.portal.variety.dao;

import java.math.BigDecimal;

import java.util.Set;

import org.irri.iric.portal.variety.domain.Snps;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage Snps entities.
 * 
 */
public interface SnpsDAO extends JpaDao<Snps> {

	/**
	 * JPQL Query - findSnpsByPos
	 *
	 */
	public Set<Snps> findSnpsByPos(java.math.BigDecimal pos) throws DataAccessException;

	/**
	 * JPQL Query - findSnpsByPos
	 *
	 */
	public Set<Snps> findSnpsByPos(BigDecimal pos, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnpsByMinal
	 *
	 */
	public Set<Snps> findSnpsByMinal(Integer minal) throws DataAccessException;

	/**
	 * JPQL Query - findSnpsByMinal
	 *
	 */
	public Set<Snps> findSnpsByMinal(Integer minal, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnpsByPrimaryKey
	 *
	 */
	public Snps findSnpsByPrimaryKey(String snpId) throws DataAccessException;

	/**
	 * JPQL Query - findSnpsByPrimaryKey
	 *
	 */
	public Snps findSnpsByPrimaryKey(String snpId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnpsByChrom
	 *
	 */
	public Set<Snps> findSnpsByChrom(Integer chrom) throws DataAccessException;

	/**
	 * JPQL Query - findSnpsByChrom
	 *
	 */
	public Set<Snps> findSnpsByChrom(Integer chrom, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllSnpss
	 *
	 */
	public Set<Snps> findAllSnpss() throws DataAccessException;

	/**
	 * JPQL Query - findAllSnpss
	 *
	 */
	public Set<Snps> findAllSnpss(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnpsByMaxal
	 *
	 */
	public Set<Snps> findSnpsByMaxal(Integer maxal) throws DataAccessException;

	/**
	 * JPQL Query - findSnpsByMaxal
	 *
	 */
	public Set<Snps> findSnpsByMaxal(Integer maxal, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnpsBySnpIdContaining
	 *
	 */
	public Set<Snps> findSnpsBySnpIdContaining(String snpId_1) throws DataAccessException;

	/**
	 * JPQL Query - findSnpsBySnpIdContaining
	 *
	 */
	public Set<Snps> findSnpsBySnpIdContaining(String snpId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnpsBySnpId
	 *
	 */
	public Snps findSnpsBySnpId(String snpId_2) throws DataAccessException;

	/**
	 * JPQL Query - findSnpsBySnpId
	 *
	 */
	public Snps findSnpsBySnpId(String snpId_2, int startResult, int maxRows) throws DataAccessException;

}