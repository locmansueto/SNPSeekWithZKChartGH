package org.irri.iric.portal.chado.postgres.daobackup;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.portal.chado.postgres.domain.VIricstockPhenotypeQuanval;
import org.irri.iric.portal.dao.CvTermUniqueValuesDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VIricstockPhenotypeQuanval entities.
 * 
 */
public interface VIricstockPhenotypeQuanvalDAO extends
		JpaDao<VIricstockPhenotypeQuanval>, CvTermUniqueValuesDAO {

	/**
	 * JPQL Query - findVIricstockPhenotypeQuanvalByPrimaryKey
	 *
	 */
	public VIricstockPhenotypeQuanval findVIricstockPhenotypeQuanvalByPrimaryKey(Integer phenotypeId, java.math.BigDecimal quanValue) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeQuanvalByPrimaryKey
	 *
	 */
	public VIricstockPhenotypeQuanval findVIricstockPhenotypeQuanvalByPrimaryKey(Integer phenotypeId, BigDecimal quanValue, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeQuanvalByQuanValue
	 *
	 */
	public Set<VIricstockPhenotypeQuanval> findVIricstockPhenotypeQuanvalByQuanValue(java.math.BigDecimal quanValue_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeQuanvalByQuanValue
	 *
	 */
	public Set<VIricstockPhenotypeQuanval> findVIricstockPhenotypeQuanvalByQuanValue(BigDecimal quanValue_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstockPhenotypeQuanvals
	 *
	 */
	public Set<VIricstockPhenotypeQuanval> findAllVIricstockPhenotypeQuanvals() throws DataAccessException;

	/**
	 * JPQL Query - findAllVIricstockPhenotypeQuanvals
	 *
	 */
	public Set<VIricstockPhenotypeQuanval> findAllVIricstockPhenotypeQuanvals(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeQuanvalByPhenotypeId
	 *
	 */
	public Set<VIricstockPhenotypeQuanval> findVIricstockPhenotypeQuanvalByPhenotypeId(Integer phenotypeId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIricstockPhenotypeQuanvalByPhenotypeId
	 *
	 */
	public Set<VIricstockPhenotypeQuanval> findVIricstockPhenotypeQuanvalByPhenotypeId(Integer phenotypeId_1, int startResult, int maxRows) throws DataAccessException;

}