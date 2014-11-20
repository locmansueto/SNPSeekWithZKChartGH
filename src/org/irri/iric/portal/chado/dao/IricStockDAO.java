package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.portal.chado.domain.IricStock;
import org.irri.iric.portal.dao.VarietyDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage IricStock entities.
 * 
 */
public interface IricStockDAO extends JpaDao<IricStock>, VarietyDAO  {

	/**
	 * JPQL Query - findAllIricStocks
	 *
	 */
	public Set<IricStock> findAllIricStocks() throws DataAccessException;

	/**
	 * JPQL Query - findAllIricStocks
	 *
	 */
	public Set<IricStock> findAllIricStocks(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIricStockByPrimaryKey
	 *
	 */
	public IricStock findIricStockByPrimaryKey(java.math.BigDecimal iricStockId) throws DataAccessException;

	/**
	 * JPQL Query - findIricStockByPrimaryKey
	 *
	 */
	public IricStock findIricStockByPrimaryKey(BigDecimal iricStockId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIricStockByName
	 *
	 */
	public Set<IricStock> findIricStockByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findIricStockByName
	 *
	 */
	public Set<IricStock> findIricStockByName(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIricStockByNameContaining
	 *
	 */
	public Set<IricStock> findIricStockByNameContaining(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findIricStockByNameContaining
	 *
	 */
	public Set<IricStock> findIricStockByNameContaining(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIricStockByOrganismId
	 *
	 */
	public Set<IricStock> findIricStockByOrganismId(java.math.BigDecimal organismId) throws DataAccessException;

	/**
	 * JPQL Query - findIricStockByOrganismId
	 *
	 */
	public Set<IricStock> findIricStockByOrganismId(BigDecimal organismId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIricStockByIricStockGeolocationId
	 *
	 */
	public Set<IricStock> findIricStockByIricStockGeolocationId(java.math.BigDecimal iricStockGeolocationId) throws DataAccessException;

	/**
	 * JPQL Query - findIricStockByIricStockGeolocationId
	 *
	 */
	public Set<IricStock> findIricStockByIricStockGeolocationId(BigDecimal iricStockGeolocationId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIricStockByDbxrefId
	 *
	 */
	public Set<IricStock> findIricStockByDbxrefId(java.math.BigDecimal dbxrefId) throws DataAccessException;

	/**
	 * JPQL Query - findIricStockByDbxrefId
	 *
	 */
	public Set<IricStock> findIricStockByDbxrefId(BigDecimal dbxrefId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIricStockByPrimaryKey
	 *
	 */
	public IricStock findIricStockByPrimaryKey(Integer iricStockId) throws DataAccessException;

	/**
	 * JPQL Query - findIricStockByPrimaryKey
	 *
	 */
	public IricStock findIricStockByPrimaryKey(Integer iricStockId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIricStockByIricStockId
	 *
	 */
	public IricStock findIricStockByIricStockId(Integer iricStockId_1) throws DataAccessException;

	/**
	 * JPQL Query - findIricStockByIricStockId
	 *
	 */
	public IricStock findIricStockByIricStockId(Integer iricStockId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIricStockByIricStockId
	 *
	 */
	public IricStock findIricStockByIricStockId(java.math.BigDecimal iricStockId_1) throws DataAccessException;

	/**
	 * JPQL Query - findIricStockByIricStockId
	 *
	 */
	public IricStock findIricStockByIricStockId(BigDecimal iricStockId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIricStockByTypeId
	 *
	 */
	public Set<IricStock> findIricStockByTypeId(java.math.BigDecimal typeId) throws DataAccessException;

	/**
	 * JPQL Query - findIricStockByTypeId
	 *
	 */
	public Set<IricStock> findIricStockByTypeId(BigDecimal typeId, int startResult, int maxRows) throws DataAccessException;

}