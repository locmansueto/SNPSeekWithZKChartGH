package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.Set;

import org.irri.iric.portal.chado.postgres.domain.VIricstocksByPassport;
import org.irri.iric.portal.dao.VarietyByPassportDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VIricstocksByPassport entities.
 * 
 */
public interface VIricstocksByPassportDAO extends JpaDao<VIricstocksByPassport>, VarietyByPassportDAO  {

	/**
	 * JPQL Query - findVIricstocksByPassportByNameContaining
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByNameContaining
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByNameContaining(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByTypeId
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByTypeId(Integer typeId) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByTypeId
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByTypeId(Integer typeId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByOriCountry
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByOriCountry(String oriCountry) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByOriCountry
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByOriCountry(String oriCountry, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstocksByPassports
	 *
	 */
	public Set<VIricstocksByPassport> findAllVIricstocksByPassports() throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstocksByPassports
	 *
	 */
	public Set<VIricstocksByPassport> findAllVIricstocksByPassports(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportBySubpopulation
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportBySubpopulation(String subpopulation) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportBySubpopulation
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportBySubpopulation(String subpopulation, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByName
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByName
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByName(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByValue
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByValue(String value) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByValue
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByValue(String value, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByIricStockId
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByIricStockId(Integer iricStockId) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByIricStockId
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByIricStockId(Integer iricStockId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportBySubpopulationContaining
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportBySubpopulationContaining(String subpopulation_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportBySubpopulationContaining
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportBySubpopulationContaining(String subpopulation_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByIricStockpropId
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByIricStockpropId(Integer iricStockpropId) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByIricStockpropId
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByIricStockpropId(Integer iricStockpropId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByIrisUniqueIdContaining
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByIrisUniqueIdContaining(String irisUniqueId) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByIrisUniqueIdContaining
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByIrisUniqueIdContaining(String irisUniqueId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByIrisUniqueId
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByIrisUniqueId(String irisUniqueId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByIrisUniqueId
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByIrisUniqueId(String irisUniqueId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByOriCountryContaining
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByOriCountryContaining(String oriCountry_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByOriCountryContaining
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByOriCountryContaining(String oriCountry_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByPrimaryKey
	 *
	 */
	public VIricstocksByPassport findVIricstocksByPassportByPrimaryKey(Integer iricStockpropId_1, Integer iricStockId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByPrimaryKey
	 *
	 */
	public VIricstocksByPassport findVIricstocksByPassportByPrimaryKey(Integer iricStockpropId_1, Integer iricStockId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByValueContaining
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByValueContaining(String value_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPassportByValueContaining
	 *
	 */
	public Set<VIricstocksByPassport> findVIricstocksByPassportByValueContaining(String value_1, int startResult, int maxRows) throws DataAccessException;

}