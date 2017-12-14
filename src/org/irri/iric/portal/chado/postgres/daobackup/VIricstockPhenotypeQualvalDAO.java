package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.Set;

import org.irri.iric.portal.chado.postgres.domain.VIricstockPhenotypeQualval;
import org.irri.iric.portal.dao.CvTermUniqueValuesDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VIricstockPhenotypeQualval entities.
 * 
 */
public interface VIricstockPhenotypeQualvalDAO extends
		JpaDao<VIricstockPhenotypeQualval> , CvTermUniqueValuesDAO{

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByPhenotypeId
	 *
	 */
	public Set<VIricstockPhenotypeQualval> findVIricstockPhenotypeQualvalByPhenotypeId(Integer phenotypeId) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByPhenotypeId
	 *
	 */
	public Set<VIricstockPhenotypeQualval> findVIricstockPhenotypeQualvalByPhenotypeId(Integer phenotypeId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstockPhenotypeQualvals
	 *
	 */
	public Set<VIricstockPhenotypeQualval> findAllVIricstockPhenotypeQualvals() throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstockPhenotypeQualvals
	 *
	 */
	public Set<VIricstockPhenotypeQualval> findAllVIricstockPhenotypeQualvals(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByPrimaryKey
	 *
	 */
	public VIricstockPhenotypeQualval findVIricstockPhenotypeQualvalByPrimaryKey(Integer phenotypeId_1, String qualValue) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByPrimaryKey
	 *
	 */
	public VIricstockPhenotypeQualval findVIricstockPhenotypeQualvalByPrimaryKey(Integer phenotypeId_1, String qualValue, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByQualValueContaining
	 *
	 */
	public Set<VIricstockPhenotypeQualval> findVIricstockPhenotypeQualvalByQualValueContaining(String qualValue_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByQualValueContaining
	 *
	 */
	public Set<VIricstockPhenotypeQualval> findVIricstockPhenotypeQualvalByQualValueContaining(String qualValue_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByQualValue
	 *
	 */
	public Set<VIricstockPhenotypeQualval> findVIricstockPhenotypeQualvalByQualValue(String qualValue_2) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByQualValue
	 *
	 */
	public Set<VIricstockPhenotypeQualval> findVIricstockPhenotypeQualvalByQualValue(String qualValue_2, int startResult, int maxRows) throws DataAccessException;

}