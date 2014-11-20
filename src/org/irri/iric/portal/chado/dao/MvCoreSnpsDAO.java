package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.portal.chado.domain.MvCoreSnps;
import org.irri.iric.portal.dao.SnpsAllvarsPosDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage MvCoreSnps entities.
 * 
 */
public interface MvCoreSnpsDAO extends JpaDao<MvCoreSnps>, SnpsAllvarsPosDAO {

	/**
	 * JPQL Query - findAllMvCoreSnpss
	 *
	 */
	public Set<MvCoreSnps> findAllMvCoreSnpss() throws DataAccessException;

	/**
	 * JPQL Query - findAllMvCoreSnpss
	 *
	 */
	public Set<MvCoreSnps> findAllMvCoreSnpss(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvCoreSnpsBySnpFeatureId
	 *
	 */
	public MvCoreSnps findMvCoreSnpsBySnpFeatureId(Integer snpFeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findMvCoreSnpsBySnpFeatureId
	 *
	 */
	public MvCoreSnps findMvCoreSnpsBySnpFeatureId(Integer snpFeatureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvCoreSnpsBySrcfeatureId
	 *
	 */
	public Set<MvCoreSnps> findMvCoreSnpsBySrcfeatureId(Integer srcfeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findMvCoreSnpsBySrcfeatureId
	 *
	 */
	public Set<MvCoreSnps> findMvCoreSnpsBySrcfeatureId(Integer srcfeatureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvCoreSnpsByRefcallContaining
	 *
	 */
	public Set<MvCoreSnps> findMvCoreSnpsByRefcallContaining(String refcall) throws DataAccessException;

	/**
	 * JPQL Query - findMvCoreSnpsByRefcallContaining
	 *
	 */
	public Set<MvCoreSnps> findMvCoreSnpsByRefcallContaining(String refcall, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvCoreSnpsByPrimaryKey
	 *
	 */
	public MvCoreSnps findMvCoreSnpsByPrimaryKey(Integer snpFeatureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findMvCoreSnpsByPrimaryKey
	 *
	 */
	public MvCoreSnps findMvCoreSnpsByPrimaryKey(Integer snpFeatureId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvCoreSnpsByName
	 *
	 */
	public Set<MvCoreSnps> findMvCoreSnpsByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findMvCoreSnpsByName
	 *
	 */
	public Set<MvCoreSnps> findMvCoreSnpsByName(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvCoreSnpsByNameContaining
	 *
	 */
	public Set<MvCoreSnps> findMvCoreSnpsByNameContaining(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findMvCoreSnpsByNameContaining
	 *
	 */
	public Set<MvCoreSnps> findMvCoreSnpsByNameContaining(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvCoreSnpsByPosition
	 *
	 */
	public Set<MvCoreSnps> findMvCoreSnpsByPosition(java.math.BigDecimal position) throws DataAccessException;

	/**
	 * JPQL Query - findMvCoreSnpsByPosition
	 *
	 */
	public Set<MvCoreSnps> findMvCoreSnpsByPosition(BigDecimal position, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvCoreSnpsByRefcall
	 *
	 */
	public Set<MvCoreSnps> findMvCoreSnpsByRefcall(String refcall_1) throws DataAccessException;

	/**
	 * JPQL Query - findMvCoreSnpsByRefcall
	 *
	 */
	public Set<MvCoreSnps> findMvCoreSnpsByRefcall(String refcall_1, int startResult, int maxRows) throws DataAccessException;

}