package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VIricstockPhenotypeValues;
import org.irri.iric.portal.dao.CvTermUniqueValuesDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VIricstockPhenotypeValues entities.
 * 
 */
public interface VIricstockPhenotypeValuesDAO extends
		JpaDao<VIricstockPhenotypeValues>, CvTermUniqueValuesDAO {

	/**
	 * JPQL Query - findVIricstockPhenotypeValuesByQualValueContaining
	 *
	 */
	public Set<VIricstockPhenotypeValues> findVIricstockPhenotypeValuesByQualValueContaining(String qualValue) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeValuesByQualValueContaining
	 *
	 */
	public Set<VIricstockPhenotypeValues> findVIricstockPhenotypeValuesByQualValueContaining(String qualValue, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeValuesByPhenotypeId
	 *
	 */
	public Set<VIricstockPhenotypeValues> findVIricstockPhenotypeValuesByPhenotypeId(java.math.BigDecimal phenotypeId) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeValuesByPhenotypeId
	 *
	 */
	public Set<VIricstockPhenotypeValues> findVIricstockPhenotypeValuesByPhenotypeId(BigDecimal phenotypeId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeValuesByQuanValue
	 *
	 */
	public Set<VIricstockPhenotypeValues> findVIricstockPhenotypeValuesByQuanValue(Integer quanValue) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeValuesByQuanValue
	 *
	 */
	public Set<VIricstockPhenotypeValues> findVIricstockPhenotypeValuesByQuanValue(Integer quanValue, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstockPhenotypeValuess
	 *
	 */
	public Set<VIricstockPhenotypeValues> findAllVIricstockPhenotypeValuess() throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstockPhenotypeValuess
	 *
	 */
	public Set<VIricstockPhenotypeValues> findAllVIricstockPhenotypeValuess(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeValuesByQualValue
	 *
	 */
	public Set<VIricstockPhenotypeValues> findVIricstockPhenotypeValuesByQualValue(String qualValue_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeValuesByQualValue
	 *
	 */
	public Set<VIricstockPhenotypeValues> findVIricstockPhenotypeValuesByQualValue(String qualValue_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeValuesByPrimaryKey
	 *
	 */
	public VIricstockPhenotypeValues findVIricstockPhenotypeValuesByPrimaryKey(Integer quanValue_1, String qualValue_2, java.math.BigDecimal phenotypeId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeValuesByPrimaryKey
	 *
	 */
	public VIricstockPhenotypeValues findVIricstockPhenotypeValuesByPrimaryKey(Integer quanValue_1, String qualValue_2, BigDecimal phenotypeId_1, int startResult, int maxRows) throws DataAccessException;

}