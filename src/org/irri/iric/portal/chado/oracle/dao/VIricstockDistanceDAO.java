package org.irri.iric.portal.chado.oracle.dao;

import java.util.Set;

import org.irri.iric.portal.chado.oracle.domain.VIricstockDistance;
import org.irri.iric.portal.dao.VarietyDistanceDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VIricstockDistance entities.
 * 
 */
public interface VIricstockDistanceDAO extends JpaDao<VIricstockDistance> , VarietyDistanceDAO {

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

	/**
	 * JPQL Query - findVIricstockDistanceByVar1
	 *
	 */
	public Set<VIricstockDistance> findVIricstockDistanceByVar1(Integer var1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockDistanceByVar1
	 *
	 */
	public Set<VIricstockDistance> findVIricstockDistanceByVar1(Integer var1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockDistanceByVar2
	 *
	 */
	public Set<VIricstockDistance> findVIricstockDistanceByVar2(Integer var2) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockDistanceByVar2
	 *
	 */
	public Set<VIricstockDistance> findVIricstockDistanceByVar2(Integer var2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockDistanceByPrimaryKey
	 *
	 */
	public VIricstockDistance findVIricstockDistanceByPrimaryKey(Integer var1_1, Integer var2_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockDistanceByPrimaryKey
	 *
	 */
	public VIricstockDistance findVIricstockDistanceByPrimaryKey(Integer var1_1, Integer var2_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockDistanceByDist
	 *
	 */
	public Set<VIricstockDistance> findVIricstockDistanceByDist(Integer dist) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockDistanceByDist
	 *
	 */
	public Set<VIricstockDistance> findVIricstockDistanceByDist(Integer dist, int startResult, int maxRows) throws DataAccessException;

}