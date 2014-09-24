package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VIricstockBasicprop;
import org.irri.iric.portal.dao.VarietyDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VIricstockBasicprop entities.
 * 
 */
public interface VIricstockBasicpropDAO extends JpaDao<VIricstockBasicprop> , VarietyDAO {

	/**
	 * JPQL Query - findVIricstockBasicpropByNameContaining
	 *
	 */
	public Set<VIricstockBasicprop> findVIricstockBasicpropByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicpropByNameContaining
	 *
	 */
	public Set<VIricstockBasicprop> findVIricstockBasicpropByNameContaining(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstockBasicprops
	 *
	 */
	public Set<VIricstockBasicprop> findAllVIricstockBasicprops() throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstockBasicprops
	 *
	 */
	public Set<VIricstockBasicprop> findAllVIricstockBasicprops(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicpropByPrimaryKey
	 *
	 */
	public VIricstockBasicprop findVIricstockBasicpropByPrimaryKey(BigDecimal iricStockId) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicpropByPrimaryKey
	 *
	 */
	public VIricstockBasicprop findVIricstockBasicpropByPrimaryKey(BigDecimal iricStockId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicpropByIrisUniqueIdContaining
	 *
	 */
	public Set<VIricstockBasicprop> findVIricstockBasicpropByIrisUniqueIdContaining(String irisUniqueId) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicpropByIrisUniqueIdContaining
	 *
	 */
	public Set<VIricstockBasicprop> findVIricstockBasicpropByIrisUniqueIdContaining(String irisUniqueId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicpropByIricStockId
	 *
	 */
	public VIricstockBasicprop findVIricstockBasicpropByIricStockId(Integer iricStockId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicpropByIricStockId
	 *
	 */
	public VIricstockBasicprop findVIricstockBasicpropByIricStockId(Integer iricStockId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicpropByIrisUniqueId
	 *
	 */
	public Set<VIricstockBasicprop> findVIricstockBasicpropByIrisUniqueId(String irisUniqueId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicpropByIrisUniqueId
	 *
	 */
	public Set<VIricstockBasicprop> findVIricstockBasicpropByIrisUniqueId(String irisUniqueId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicpropByOriCountryContaining
	 *
	 */
	public Set<VIricstockBasicprop> findVIricstockBasicpropByOriCountryContaining(String oriCountry) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicpropByOriCountryContaining
	 *
	 */
	public Set<VIricstockBasicprop> findVIricstockBasicpropByOriCountryContaining(String oriCountry, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicpropByName
	 *
	 */
	public Set<VIricstockBasicprop> findVIricstockBasicpropByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicpropByName
	 *
	 */
	public Set<VIricstockBasicprop> findVIricstockBasicpropByName(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicpropByOriCountry
	 *
	 */
	public Set<VIricstockBasicprop> findVIricstockBasicpropByOriCountry(String oriCountry_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBasicpropByOriCountry
	 *
	 */
	public Set<VIricstockBasicprop> findVIricstockBasicpropByOriCountry(String oriCountry_1, int startResult, int maxRows) throws DataAccessException;

}