package org.irri.iric.portal.flatfile.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.portal.dao.SnpsAllvarsPosDAO;
import org.irri.iric.portal.flatfile.domain.SnpcoreRefposindex;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage SnpcoreRefposindex entities.
 * 
 */
public interface SnpcoreRefposindexDAO extends JpaDao<SnpcoreRefposindex>, SnpsAllvarsPosDAO {

	//public static BigDecimal TYPE_3KCORESNP = BigDecimal.ONE;
	//public static BigDecimal TYPE_3KALLSNP = new BigDecimal(2);
	public static BigDecimal TYPE_3KCORESNP = new BigDecimal(4);
	public static BigDecimal TYPE_3KALLSNP = new BigDecimal(3);
	public static BigDecimal TYPE_3KCORESNP_HDF5 = new BigDecimal(4);
	public static BigDecimal TYPE_3KALLSNP_HDF5 = new BigDecimal(3);
	
	
	/**
	 * JPQL Query - findSnpcoreRefposindexByPosition
	 *
	 */
	public Set<SnpcoreRefposindex> findSnpcoreRefposindexByPosition(java.math.BigDecimal position) throws DataAccessException;

	/**
	 * JPQL Query - findSnpcoreRefposindexByPosition
	 *
	 */
	public Set<SnpcoreRefposindex> findSnpcoreRefposindexByPosition(BigDecimal position, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllSnpcoreRefposindexs
	 *
	 */
	public Set<SnpcoreRefposindex> findAllSnpcoreRefposindexs() throws DataAccessException;

	/**
	 * JPQL Query - findAllSnpcoreRefposindexs
	 *
	 */
	public Set<SnpcoreRefposindex> findAllSnpcoreRefposindexs(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnpcoreRefposindexByRefcall
	 *
	 */
	public Set<SnpcoreRefposindex> findSnpcoreRefposindexByRefcall(String refcall) throws DataAccessException;

	/**
	 * JPQL Query - findSnpcoreRefposindexByRefcall
	 *
	 */
	public Set<SnpcoreRefposindex> findSnpcoreRefposindexByRefcall(String refcall, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnpcoreRefposindexByAllelesIndex
	 *
	 */
	public Set<SnpcoreRefposindex> findSnpcoreRefposindexByAllelesIndex(java.math.BigDecimal allelesIndex) throws DataAccessException;

	/**
	 * JPQL Query - findSnpcoreRefposindexByAllelesIndex
	 *
	 */
	public Set<SnpcoreRefposindex> findSnpcoreRefposindexByAllelesIndex(BigDecimal allelesIndex, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnpcoreRefposindexByPrimaryKey
	 *
	 */
	public SnpcoreRefposindex findSnpcoreRefposindexByPrimaryKey(Integer snpFeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findSnpcoreRefposindexByPrimaryKey
	 *
	 */
	public SnpcoreRefposindex findSnpcoreRefposindexByPrimaryKey(Integer snpFeatureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnpcoreRefposindexByRefcallContaining
	 *
	 */
	public Set<SnpcoreRefposindex> findSnpcoreRefposindexByRefcallContaining(String refcall_1) throws DataAccessException;

	/**
	 * JPQL Query - findSnpcoreRefposindexByRefcallContaining
	 *
	 */
	public Set<SnpcoreRefposindex> findSnpcoreRefposindexByRefcallContaining(String refcall_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnpcoreRefposindexBySnpFeatureId
	 *
	 */
	public SnpcoreRefposindex findSnpcoreRefposindexBySnpFeatureId(Integer snpFeatureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findSnpcoreRefposindexBySnpFeatureId
	 *
	 */
	public SnpcoreRefposindex findSnpcoreRefposindexBySnpFeatureId(Integer snpFeatureId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSnpcoreRefposindexByChromosome
	 *
	 */
	public Set<SnpcoreRefposindex> findSnpcoreRefposindexByChromosome(java.math.BigDecimal chromosome) throws DataAccessException;

	/**
	 * JPQL Query - findSnpcoreRefposindexByChromosome
	 *
	 */
	public Set<SnpcoreRefposindex> findSnpcoreRefposindexByChromosome(BigDecimal chromosome, int startResult, int maxRows) throws DataAccessException;

}