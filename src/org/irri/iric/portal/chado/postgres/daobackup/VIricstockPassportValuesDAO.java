package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.Set;

import org.irri.iric.portal.chado.postgres.domain.VIricstockPassportValues;
import org.irri.iric.portal.dao.CvTermUniqueValuesDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VIricstockPassportValues entities.
 * 
 */
public interface VIricstockPassportValuesDAO extends
		JpaDao<VIricstockPassportValues> , CvTermUniqueValuesDAO {

	/**
	 * JPQL Query - findVIricstockPassportValuesByValue
	 *
	 */
	public Set<VIricstockPassportValues> findVIricstockPassportValuesByValue(String value) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportValuesByValue
	 *
	 */
	public Set<VIricstockPassportValues> findVIricstockPassportValuesByValue(String value, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportValuesByValueContaining
	 *
	 */
	public Set<VIricstockPassportValues> findVIricstockPassportValuesByValueContaining(String value_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportValuesByValueContaining
	 *
	 */
	public Set<VIricstockPassportValues> findVIricstockPassportValuesByValueContaining(String value_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportValuesByTypeId
	 *
	 */
	public Set<VIricstockPassportValues> findVIricstockPassportValuesByTypeId(Integer typeId) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportValuesByTypeId
	 *
	 */
	public Set<VIricstockPassportValues> findVIricstockPassportValuesByTypeId(Integer typeId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstockPassportValuess
	 *
	 */
	public Set<VIricstockPassportValues> findAllVIricstockPassportValuess() throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstockPassportValuess
	 *
	 */
	public Set<VIricstockPassportValues> findAllVIricstockPassportValuess(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportValuesByPrimaryKey
	 *
	 */
	public VIricstockPassportValues findVIricstockPassportValuesByPrimaryKey(Integer typeId_1, String value_2) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPassportValuesByPrimaryKey
	 *
	 */
	public VIricstockPassportValues findVIricstockPassportValuesByPrimaryKey(Integer typeId_1, String value_2, int startResult, int maxRows) throws DataAccessException;

}