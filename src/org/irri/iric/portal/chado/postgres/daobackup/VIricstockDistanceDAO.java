package org.irri.iric.portal.chado.postgres.daobackup;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.portal.chado.postgres.domain.VIricstockDistance;
import org.irri.iric.portal.dao.VarietyDistanceDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VIricstockDistance entities.
 * 
 */
public interface VIricstockDistanceDAO extends JpaDao<VIricstockDistance>, VarietyDistanceDAO {

	/**
	 * JPQL Query - findVIricstockDistanceByPrimaryKey
	 *
	 */
	public VIricstockDistance findVIricstockDistanceByPrimaryKey(Integer var1, Integer var2) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockDistanceByPrimaryKey
	 *
	 */
	public VIricstockDistance findVIricstockDistanceByPrimaryKey(Integer var1, Integer var2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockDistanceByVar2
	 *
	 */
	public Set<VIricstockDistance> findVIricstockDistanceByVar2(Integer var2_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockDistanceByVar2
	 *
	 */
	public Set<VIricstockDistance> findVIricstockDistanceByVar2(Integer var2_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockDistanceByVar1
	 *
	 */
	public Set<VIricstockDistance> findVIricstockDistanceByVar1(Integer var1_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockDistanceByVar1
	 *
	 */
	public Set<VIricstockDistance> findVIricstockDistanceByVar1(Integer var1_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockDistanceByDistance
	 *
	 */
	public Set<VIricstockDistance> findVIricstockDistanceByDistance(java.math.BigDecimal distance) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockDistanceByDistance
	 *
	 */
	public Set<VIricstockDistance> findVIricstockDistanceByDistance(BigDecimal distance, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstockDistances
	 *
	 */
	public Set<VIricstockDistance> findAllVIricstockDistances() throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstockDistances
	 *
	 */
	public Set<VIricstockDistance> findAllVIricstockDistances(int startResult, int maxRows) throws DataAccessException;

}