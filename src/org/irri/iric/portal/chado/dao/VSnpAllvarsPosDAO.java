package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Query;

import org.irri.iric.portal.chado.domain.VSnpAllvarsPos;
import org.irri.iric.portal.dao.SnpsAllvarsPosDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnpAllvarsPos entities.
 * 
 */
public interface VSnpAllvarsPosDAO extends JpaDao<VSnpAllvarsPos>, SnpsAllvarsPosDAO {

	/**
	 * JPQL Query - findAllVSnpAllvarsPoss
	 *
	 */
	public Set<VSnpAllvarsPos> findAllVSnpAllvarsPoss() throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpAllvarsPoss
	 *
	 */
	public Set<VSnpAllvarsPos> findAllVSnpAllvarsPoss(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsPosBySnpFeatureId
	 *
	 */
	public VSnpAllvarsPos findVSnpAllvarsPosBySnpFeatureId(Integer snpFeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsPosBySnpFeatureId
	 *
	 */
	public VSnpAllvarsPos findVSnpAllvarsPosBySnpFeatureId(Integer snpFeatureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsPosByRefnucContaining
	 *
	 */
	public Set<VSnpAllvarsPos> findVSnpAllvarsPosByRefnucContaining(String refnuc) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsPosByRefnucContaining
	 *
	 */
	public Set<VSnpAllvarsPos> findVSnpAllvarsPosByRefnucContaining(String refnuc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsPosByPrimaryKey
	 *
	 */
	public VSnpAllvarsPos findVSnpAllvarsPosByPrimaryKey(BigDecimal snpFeatureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsPosByPrimaryKey
	 *
	 */
	public VSnpAllvarsPos findVSnpAllvarsPosByPrimaryKey(BigDecimal snpFeatureId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsPosByChr
	 *
	 */
	public Set<VSnpAllvarsPos> findVSnpAllvarsPosByChr(Integer chr) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsPosByChr
	 *
	 */
	public Set<VSnpAllvarsPos> findVSnpAllvarsPosByChr(Integer chr, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsPosByRefnuc
	 *
	 */
	public Set<VSnpAllvarsPos> findVSnpAllvarsPosByRefnuc(String refnuc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsPosByRefnuc
	 *
	 */
	public Set<VSnpAllvarsPos> findVSnpAllvarsPosByRefnuc(String refnuc_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsPosByPos
	 *
	 */
	public Set<VSnpAllvarsPos> findVSnpAllvarsPosByPos(java.math.BigDecimal pos) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsPosByPos
	 *
	 */
	public Set<VSnpAllvarsPos> findVSnpAllvarsPosByPos(BigDecimal pos, int startResult, int maxRows) throws DataAccessException;
	
	

	public Set<VSnpAllvarsPos> findVSnpAllvarsPosByChrPosBetween(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end) throws DataAccessException;
	public Set<VSnpAllvarsPos> findVSnpAllvarsPosByChrPosBetween(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end, int startResult, int maxRows) throws DataAccessException;

	

}