package org.irri.iric.portal.chado.oracle.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.portal.chado.oracle.domain.VSnpSynallelePos;
import org.irri.iric.portal.dao.SnpsSynAllvarsDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VSnpSynallelePos entities.
 * 
 */
public interface VSnpSynallelePosDAO extends JpaDao<VSnpSynallelePos>, SnpsSynAllvarsDAO {

	/**
	 * JPQL Query - findVSnpSynallelePosByPosition
	 *
	 */
	public Set<VSnpSynallelePos> findVSnpSynallelePosByPosition(BigDecimal position) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSynallelePosByPosition
	 *
	 */
	public Set<VSnpSynallelePos> findVSnpSynallelePosByPosition(BigDecimal position, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSynallelePosByTypeId
	 *
	 */
	public Set<VSnpSynallelePos> findVSnpSynallelePosByTypeId(BigDecimal typeId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSynallelePosByTypeId
	 *
	 */
	public Set<VSnpSynallelePos> findVSnpSynallelePosByTypeId(BigDecimal typeId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSynallelePosBySnpFeatureId
	 *
	 */
	public Set<VSnpSynallelePos> findVSnpSynallelePosBySnpFeatureId(BigDecimal snpFeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSynallelePosBySnpFeatureId
	 *
	 */
	public Set<VSnpSynallelePos> findVSnpSynallelePosBySnpFeatureId(BigDecimal snpFeatureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpSynallelePoss
	 *
	 */
	public Set<VSnpSynallelePos> findAllVSnpSynallelePoss() throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpSynallelePoss
	 *
	 */
	public Set<VSnpSynallelePos> findAllVSnpSynallelePoss(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSynallelePosByAlleleSyn
	 *
	 */
	public Set<VSnpSynallelePos> findVSnpSynallelePosByAlleleSyn(String alleleSyn) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSynallelePosByAlleleSyn
	 *
	 */
	public Set<VSnpSynallelePos> findVSnpSynallelePosByAlleleSyn(String alleleSyn, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSynallelePosByPrimaryKey
	 *
	 */
	public VSnpSynallelePos findVSnpSynallelePosByPrimaryKey(BigDecimal chr, BigDecimal position_1, String alleleSyn_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSynallelePosByPrimaryKey
	 *
	 */
	public VSnpSynallelePos findVSnpSynallelePosByPrimaryKey(BigDecimal chr, BigDecimal position_1, String alleleSyn_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSynallelePosByChr
	 *
	 */
	public Set<VSnpSynallelePos> findVSnpSynallelePosByChr(BigDecimal chr_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSynallelePosByChr
	 *
	 */
	public Set<VSnpSynallelePos> findVSnpSynallelePosByChr(BigDecimal chr_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSynallelePosByAlleleSynContaining
	 *
	 */
	public Set<VSnpSynallelePos> findVSnpSynallelePosByAlleleSynContaining(String alleleSyn_2) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSynallelePosByAlleleSynContaining
	 *
	 */
	public Set<VSnpSynallelePos> findVSnpSynallelePosByAlleleSynContaining(String alleleSyn_2, int startResult, int maxRows) throws DataAccessException;

}