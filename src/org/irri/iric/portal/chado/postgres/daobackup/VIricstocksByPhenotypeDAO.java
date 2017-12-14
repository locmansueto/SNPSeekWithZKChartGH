package org.irri.iric.portal.chado.postgres.daobackup;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.portal.chado.postgres.domain.VIricstocksByPhenotype;
import org.irri.iric.portal.dao.VarietyByPhenotypeDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VIricstocksByPhenotype entities.
 * 
 */
public interface VIricstocksByPhenotypeDAO extends
		JpaDao<VIricstocksByPhenotype> , VarietyByPhenotypeDAO {

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByIricStockPhenotypeId
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByIricStockPhenotypeId(Integer iricStockPhenotypeId) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByIricStockPhenotypeId
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByIricStockPhenotypeId(Integer iricStockPhenotypeId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeBySubpopulationContaining
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeBySubpopulationContaining(String subpopulation) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeBySubpopulationContaining
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeBySubpopulationContaining(String subpopulation, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByOriCountryContaining
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByOriCountryContaining(String oriCountry) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByOriCountryContaining
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByOriCountryContaining(String oriCountry, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstocksByPhenotypes
	 *
	 */
	public Set<VIricstocksByPhenotype> findAllVIricstocksByPhenotypes() throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstocksByPhenotypes
	 *
	 */
	public Set<VIricstocksByPhenotype> findAllVIricstocksByPhenotypes(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByIrisUniqueIdContaining
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByIrisUniqueIdContaining(String irisUniqueId) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByIrisUniqueIdContaining
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByIrisUniqueIdContaining(String irisUniqueId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByQuanValue
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByQuanValue(java.math.BigDecimal quanValue) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByQuanValue
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByQuanValue(BigDecimal quanValue, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByQualValue
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByQualValue(String qualValue) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByQualValue
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByQualValue(String qualValue, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByPhenotypeId
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByPhenotypeId(Integer phenotypeId) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByPhenotypeId
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByPhenotypeId(Integer phenotypeId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByIrisUniqueId
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByIrisUniqueId(String irisUniqueId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByIrisUniqueId
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByIrisUniqueId(String irisUniqueId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByNameContaining
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByNameContaining
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByNameContaining(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByName
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByName
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByName(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByIricStockId
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByIricStockId(Integer iricStockId) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByIricStockId
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByIricStockId(Integer iricStockId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByPrimaryKey
	 *
	 */
	public VIricstocksByPhenotype findVIricstocksByPhenotypeByPrimaryKey(Integer iricStockPhenotypeId_1, Integer iricStockId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByPrimaryKey
	 *
	 */
	public VIricstocksByPhenotype findVIricstocksByPhenotypeByPrimaryKey(Integer iricStockPhenotypeId_1, Integer iricStockId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeBySubpopulation
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeBySubpopulation(String subpopulation_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeBySubpopulation
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeBySubpopulation(String subpopulation_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByOriCountry
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByOriCountry(String oriCountry_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByOriCountry
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByOriCountry(String oriCountry_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByQualValueContaining
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByQualValueContaining(String qualValue_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByQualValueContaining
	 *
	 */
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByQualValueContaining(String qualValue_1, int startResult, int maxRows) throws DataAccessException;

}