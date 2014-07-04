package org.irri.iric.portal.variety.dao;

import java.math.BigDecimal;

import java.util.Set;

import org.irri.iric.portal.variety.domain.Snp700;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage Snp700 entities.
 * 
 */
public interface Snp700DAO extends JpaDao<Snp700> {

	/**
	 * JPQL Query - findSnp700ByInfoContaining
	 *
	 */
	public Set<Snp700> findSnp700ByInfoContaining(String info) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByInfoContaining
	 *
	 */
	public Set<Snp700> findSnp700ByInfoContaining(String info, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByName
	 *
	 */
	public Set<Snp700> findSnp700ByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByName
	 *
	 */
	public Set<Snp700> findSnp700ByName(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByNameContaining
	 *
	 */
	public Set<Snp700> findSnp700ByNameContaining(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByNameContaining
	 *
	 */
	public Set<Snp700> findSnp700ByNameContaining(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByInfo
	 *
	 */
	public Set<Snp700> findSnp700ByInfo(String info_1) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByInfo
	 *
	 */
	public Set<Snp700> findSnp700ByInfo(String info_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByAvgQscore
	 *
	 */
	public Set<Snp700> findSnp700ByAvgQscore(Integer avgQscore) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByAvgQscore
	 *
	 */
	public Set<Snp700> findSnp700ByAvgQscore(Integer avgQscore, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByChrContaining
	 *
	 */
	public Set<Snp700> findSnp700ByChrContaining(String chr) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByChrContaining
	 *
	 */
	public Set<Snp700> findSnp700ByChrContaining(String chr, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByQscore
	 *
	 */
	public Set<Snp700> findSnp700ByQscore(Integer qscore) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByQscore
	 *
	 */
	public Set<Snp700> findSnp700ByQscore(Integer qscore, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByAltContaining
	 *
	 */
	public Set<Snp700> findSnp700ByAltContaining(String alt) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByAltContaining
	 *
	 */
	public Set<Snp700> findSnp700ByAltContaining(String alt, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByChr
	 *
	 */
	public Set<Snp700> findSnp700ByChr(String chr_1) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByChr
	 *
	 */
	public Set<Snp700> findSnp700ByChr(String chr_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByFilter
	 *
	 */
	public Set<Snp700> findSnp700ByFilter(String filter) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByFilter
	 *
	 */
	public Set<Snp700> findSnp700ByFilter(String filter, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByPos
	 *
	 */
	public Set<Snp700> findSnp700ByPos(java.math.BigDecimal pos) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByPos
	 *
	 */
	public Set<Snp700> findSnp700ByPos(BigDecimal pos, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByAlt
	 *
	 */
	public Set<Snp700> findSnp700ByAlt(String alt_1) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByAlt
	 *
	 */
	public Set<Snp700> findSnp700ByAlt(String alt_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllSnp700s
	 *
	 */
	public Set<Snp700> findAllSnp700s() throws DataAccessException;

	/**
	 * JPQL Query - findAllSnp700s
	 *
	 */
	public Set<Snp700> findAllSnp700s(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByFormat
	 *
	 */
	public Set<Snp700> findSnp700ByFormat(String format) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByFormat
	 *
	 */
	public Set<Snp700> findSnp700ByFormat(String format, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByFilterContaining
	 *
	 */
	public Set<Snp700> findSnp700ByFilterContaining(String filter_1) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByFilterContaining
	 *
	 */
	public Set<Snp700> findSnp700ByFilterContaining(String filter_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByHet
	 *
	 */
	public Set<Snp700> findSnp700ByHet(Integer het) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByHet
	 *
	 */
	public Set<Snp700> findSnp700ByHet(Integer het, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByPrimaryKey
	 *
	 */
	public Snp700 findSnp700ByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByPrimaryKey
	 *
	 */
	public Snp700 findSnp700ByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByRef
	 *
	 */
	public Set<Snp700> findSnp700ByRef(String ref) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByRef
	 *
	 */
	public Set<Snp700> findSnp700ByRef(String ref, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByFormatContaining
	 *
	 */
	public Set<Snp700> findSnp700ByFormatContaining(String format_1) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByFormatContaining
	 *
	 */
	public Set<Snp700> findSnp700ByFormatContaining(String format_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByRefContaining
	 *
	 */
	public Set<Snp700> findSnp700ByRefContaining(String ref_1) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ByRefContaining
	 *
	 */
	public Set<Snp700> findSnp700ByRefContaining(String ref_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ById
	 *
	 */
	public Snp700 findSnp700ById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findSnp700ById
	 *
	 */
	public Snp700 findSnp700ById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}