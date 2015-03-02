package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VIricstockBoxcode;
import org.irri.iric.portal.dao.VarietyDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VIricstockBoxcode entities.
 * 
 */
public interface VIricstockBoxcodeDAO extends JpaDao<VIricstockBoxcode>, VarietyDAO {

	/**
	 * JPQL Query - findVIricstockBoxcodeByName
	 *
	 */
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBoxcodeByName
	 *
	 */
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByName(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBoxcodeByIricStockId
	 *
	 */
	public VIricstockBoxcode findVIricstockBoxcodeByIricStockId(BigDecimal iricStockId) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBoxcodeByIricStockId
	 *
	 */
	public VIricstockBoxcode findVIricstockBoxcodeByIricStockId(BigDecimal iricStockId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBoxcodeByTypeId
	 *
	 */
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByTypeId(java.math.BigDecimal typeId) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBoxcodeByTypeId
	 *
	 */
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByTypeId(BigDecimal typeId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBoxcodeByPrimaryKey
	 *
	 */
	public VIricstockBoxcode findVIricstockBoxcodeByPrimaryKey(BigDecimal iricStockId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBoxcodeByPrimaryKey
	 *
	 */
	public VIricstockBoxcode findVIricstockBoxcodeByPrimaryKey(BigDecimal iricStockId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBoxcodeByOrganismId
	 *
	 */
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByOrganismId(java.math.BigDecimal organismId) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBoxcodeByOrganismId
	 *
	 */
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByOrganismId(BigDecimal organismId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBoxcodeByDbxrefId
	 *
	 */
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByDbxrefId(java.math.BigDecimal dbxrefId) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBoxcodeByDbxrefId
	 *
	 */
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByDbxrefId(BigDecimal dbxrefId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBoxcodeByIricStockGeolocationId
	 *
	 */
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByIricStockGeolocationId(java.math.BigDecimal iricStockGeolocationId) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBoxcodeByIricStockGeolocationId
	 *
	 */
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByIricStockGeolocationId(BigDecimal iricStockGeolocationId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBoxcodeByNameContaining
	 *
	 */
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByNameContaining(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBoxcodeByNameContaining
	 *
	 */
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByNameContaining(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstockBoxcodes
	 *
	 */
	public Set<VIricstockBoxcode> findAllVIricstockBoxcodes() throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstockBoxcodes
	 *
	 */
	public Set<VIricstockBoxcode> findAllVIricstockBoxcodes(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBoxcodeByBoxCode
	 *
	 */
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByBoxCode(String boxCode) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBoxcodeByBoxCode
	 *
	 */
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByBoxCode(String boxCode, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBoxcodeByBoxCodeContaining
	 *
	 */
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByBoxCodeContaining(String boxCode_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockBoxcodeByBoxCodeContaining
	 *
	 */
	public Set<VIricstockBoxcode> findVIricstockBoxcodeByBoxCodeContaining(String boxCode_1, int startResult, int maxRows) throws DataAccessException;

}