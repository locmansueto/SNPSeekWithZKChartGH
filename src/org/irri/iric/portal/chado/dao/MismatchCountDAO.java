package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.portal.chado.domain.MismatchCount;
import org.irri.iric.portal.dao.SnpsAllvarsRefMismatchDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage MismatchCount entities.
 * 
 */
public interface MismatchCountDAO extends JpaDao<MismatchCount> , SnpsAllvarsRefMismatchDAO {

	/**
	 * JPQL Query - findMismatchCountByIricStockId
	 *
	 */
	public Set<MismatchCount> findMismatchCountByIricStockId(java.math.BigDecimal iricStockId) throws DataAccessException;

	/**
	 * JPQL Query - findMismatchCountByIricStockId
	 *
	 */
	public Set<MismatchCount> findMismatchCountByIricStockId(BigDecimal iricStockId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMismatchCountByFragEnd
	 *
	 */
	public Set<MismatchCount> findMismatchCountByFragEnd(Integer fragEnd) throws DataAccessException;

	/**
	 * JPQL Query - findMismatchCountByFragEnd
	 *
	 */
	public Set<MismatchCount> findMismatchCountByFragEnd(Integer fragEnd, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMismatchCountByPrimaryKey
	 *
	 */
	public MismatchCount findMismatchCountByPrimaryKey(Integer mismatchCountId) throws DataAccessException;

	/**
	 * JPQL Query - findMismatchCountByPrimaryKey
	 *
	 */
	public MismatchCount findMismatchCountByPrimaryKey(Integer mismatchCountId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllMismatchCounts
	 *
	 */
	public Set<MismatchCount> findAllMismatchCounts() throws DataAccessException;

	/**
	 * JPQL Query - findAllMismatchCounts
	 *
	 */
	public Set<MismatchCount> findAllMismatchCounts(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMismatchCountByMismatchCountId
	 *
	 */
	public MismatchCount findMismatchCountByMismatchCountId(Integer mismatchCountId_1) throws DataAccessException;

	/**
	 * JPQL Query - findMismatchCountByMismatchCountId
	 *
	 */
	public MismatchCount findMismatchCountByMismatchCountId(Integer mismatchCountId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMismatchCountByMismatch
	 *
	 */
	public Set<MismatchCount> findMismatchCountByMismatch(Integer mismatch) throws DataAccessException;

	/**
	 * JPQL Query - findMismatchCountByMismatch
	 *
	 */
	public Set<MismatchCount> findMismatchCountByMismatch(Integer mismatch, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMismatchCountByFragStart
	 *
	 */
	public Set<MismatchCount> findMismatchCountByFragStart(Integer fragStart) throws DataAccessException;

	/**
	 * JPQL Query - findMismatchCountByFragStart
	 *
	 */
	public Set<MismatchCount> findMismatchCountByFragStart(Integer fragStart, int startResult, int maxRows) throws DataAccessException;

}