package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VIricstockBasicprop2;
import org.irri.iric.portal.dao.VarietyDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VIricstockBasicprop2 entities.
 * 
 */
public interface VIricstockBasicprop2DAO extends JpaDao<VIricstockBasicprop2>, VarietyDAO  {

	/**
	 * JPQL Query - findAllVIricstockBasicprop2s
	 *
	 */
	public Set<VIricstockBasicprop2> findAllVIricstockBasicprop2s() throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstockBasicprop2s
	 *
	 */
	public Set<VIricstockBasicprop2> findAllVIricstockBasicprop2s(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicprop2ByIrisUniqueIdContaining
	 *
	 */
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByIrisUniqueIdContaining(String irisUniqueId) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicprop2ByIrisUniqueIdContaining
	 *
	 */
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByIrisUniqueIdContaining(String irisUniqueId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicprop2ByOriCountryContaining
	 *
	 */
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByOriCountryContaining(String oriCountry) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicprop2ByOriCountryContaining
	 *
	 */
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByOriCountryContaining(String oriCountry, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicprop2ByBoxCode
	 *
	 */
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByBoxCode(String boxCode) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicprop2ByBoxCode
	 *
	 */
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByBoxCode(String boxCode, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicprop2ByNameContaining
	 *
	 */
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicprop2ByNameContaining
	 *
	 */
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByNameContaining(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicprop2ByBoxCodeContaining
	 *
	 */
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByBoxCodeContaining(String boxCode_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicprop2ByBoxCodeContaining
	 *
	 */
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByBoxCodeContaining(String boxCode_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicprop2ByPrimaryKey
	 *
	 */
	public VIricstockBasicprop2 findVIricstockBasicprop2ByPrimaryKey(BigDecimal iricStockId) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicprop2ByPrimaryKey
	 *
	 */
	public VIricstockBasicprop2 findVIricstockBasicprop2ByPrimaryKey(BigDecimal iricStockId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicprop2ByIrisUniqueId
	 *
	 */
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByIrisUniqueId(String irisUniqueId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicprop2ByIrisUniqueId
	 *
	 */
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByIrisUniqueId(String irisUniqueId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicprop2ByOriCountry
	 *
	 */
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByOriCountry(String oriCountry_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicprop2ByOriCountry
	 *
	 */
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByOriCountry(String oriCountry_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicprop2ByIricStockId
	 *
	 */
	public VIricstockBasicprop2 findVIricstockBasicprop2ByIricStockId(BigDecimal iricStockId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicprop2ByIricStockId
	 *
	 */
	public VIricstockBasicprop2 findVIricstockBasicprop2ByIricStockId(BigDecimal iricStockId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicprop2ByName
	 *
	 */
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicprop2ByName
	 *
	 */
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByName(String name_1, int startResult, int maxRows) throws DataAccessException;

}