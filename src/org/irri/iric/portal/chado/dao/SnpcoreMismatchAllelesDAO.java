package org.irri.iric.portal.chado.dao;

import java.util.Set;

import org.irri.iric.portal.chado.domain.SnpcoreMismatchAlleles;
import org.irri.iric.portal.dao.SnpsStringAllvarsDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage SnpcoreMismatchAlleles entities.
 * 
 */
public interface SnpcoreMismatchAllelesDAO extends
		JpaDao<SnpcoreMismatchAlleles>, SnpsStringAllvarsDAO {

	/**
	 * JPQL Query - findSnpcoreMismatchAllelesByPrimaryKey
	 *
	 */
	public SnpcoreMismatchAlleles findSnpcoreMismatchAllelesByPrimaryKey(Integer iricStockId) throws DataAccessException;

	/**
	 * JPQL Query - findSnpcoreMismatchAllelesByPrimaryKey
	 *
	 */
	public SnpcoreMismatchAlleles findSnpcoreMismatchAllelesByPrimaryKey(Integer iricStockId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnpcoreMismatchAllelesByIricStockId
	 *
	 */
	public SnpcoreMismatchAlleles findSnpcoreMismatchAllelesByIricStockId(Integer iricStockId_1) throws DataAccessException;

	/**
	 * JPQL Query - findSnpcoreMismatchAllelesByIricStockId
	 *
	 */
	public SnpcoreMismatchAlleles findSnpcoreMismatchAllelesByIricStockId(Integer iricStockId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnpcoreMismatchAllelesByMismatch
	 *
	 */
	public Set<SnpcoreMismatchAlleles> findSnpcoreMismatchAllelesByMismatch(Integer mismatch) throws DataAccessException;

	/**
	 * JPQL Query - findSnpcoreMismatchAllelesByMismatch
	 *
	 */
	public Set<SnpcoreMismatchAlleles> findSnpcoreMismatchAllelesByMismatch(Integer mismatch, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllSnpcoreMismatchAlleless
	 *
	 */
	public Set<SnpcoreMismatchAlleles> findAllSnpcoreMismatchAlleless() throws DataAccessException;

	/**
	 * JPQL Query - findAllSnpcoreMismatchAlleless
	 *
	 */
	public Set<SnpcoreMismatchAlleles> findAllSnpcoreMismatchAlleless(int startResult, int maxRows) throws DataAccessException;

}