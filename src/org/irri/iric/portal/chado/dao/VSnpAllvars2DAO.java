package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;

import java.util.Set;

import org.irri.iric.portal.chado.domain.VSnpAllvars2;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VSnpAllvars2 entities.
 * 
 */
public interface VSnpAllvars2DAO extends JpaDao<VSnpAllvars2> {

	/**
	 * JPQL Query - findVSnpAllvars2ByPos
	 *
	 */
	public Set<VSnpAllvars2> findVSnpAllvars2ByPos(java.math.BigDecimal pos) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvars2ByPos
	 *
	 */
	public Set<VSnpAllvars2> findVSnpAllvars2ByPos(BigDecimal pos, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvars2ByRefnucContaining
	 *
	 */
	public Set<VSnpAllvars2> findVSnpAllvars2ByRefnucContaining(String refnuc) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvars2ByRefnucContaining
	 *
	 */
	public Set<VSnpAllvars2> findVSnpAllvars2ByRefnucContaining(String refnuc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvars2ByVarnucContaining
	 *
	 */
	public Set<VSnpAllvars2> findVSnpAllvars2ByVarnucContaining(String varnuc) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvars2ByVarnucContaining
	 *
	 */
	public Set<VSnpAllvars2> findVSnpAllvars2ByVarnucContaining(String varnuc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvars2ByIricStockId
	 *
	 */
	public Set<VSnpAllvars2> findVSnpAllvars2ByIricStockId(Integer iricStockId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvars2ByIricStockId
	 *
	 */
	public Set<VSnpAllvars2> findVSnpAllvars2ByIricStockId(Integer iricStockId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvars2ByRefnuc
	 *
	 */
	public Set<VSnpAllvars2> findVSnpAllvars2ByRefnuc(String refnuc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvars2ByRefnuc
	 *
	 */
	public Set<VSnpAllvars2> findVSnpAllvars2ByRefnuc(String refnuc_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpAllvars2s
	 *
	 */
	public Set<VSnpAllvars2> findAllVSnpAllvars2s() throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpAllvars2s
	 *
	 */
	public Set<VSnpAllvars2> findAllVSnpAllvars2s(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvars2ByPrimaryKey
	 *
	 */
	public VSnpAllvars2 findVSnpAllvars2ByPrimaryKey(Integer snpFeatureId, Integer iricStockId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvars2ByPrimaryKey
	 *
	 */
	public VSnpAllvars2 findVSnpAllvars2ByPrimaryKey(Integer snpFeatureId, Integer iricStockId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvars2BySnpFeatureId
	 *
	 */
	public Set<VSnpAllvars2> findVSnpAllvars2BySnpFeatureId(Integer snpFeatureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvars2BySnpFeatureId
	 *
	 */
	public Set<VSnpAllvars2> findVSnpAllvars2BySnpFeatureId(Integer snpFeatureId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvars2ByVarnuc
	 *
	 */
	public Set<VSnpAllvars2> findVSnpAllvars2ByVarnuc(String varnuc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvars2ByVarnuc
	 *
	 */
	public Set<VSnpAllvars2> findVSnpAllvars2ByVarnuc(String varnuc_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvars2ByChr
	 *
	 */
	public Set<VSnpAllvars2> findVSnpAllvars2ByChr(Integer chr) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvars2ByChr
	 *
	 */
	public Set<VSnpAllvars2> findVSnpAllvars2ByChr(Integer chr, int startResult, int maxRows) throws DataAccessException;

}