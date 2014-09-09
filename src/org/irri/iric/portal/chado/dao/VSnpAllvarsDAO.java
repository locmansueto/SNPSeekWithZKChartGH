package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Query;

import org.irri.iric.portal.chado.domain.VSnpAllvars;
import org.irri.iric.portal.dao.SnpsAllvarsDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnpAllvars entities.
 * 
 */
public interface VSnpAllvarsDAO  extends JpaDao<VSnpAllvars> , SnpsAllvarsDAO {

	/**
	 * JPQL Query - findVSnpAllvarsByVarnucContaining
	 *
	 */
	public Set<VSnpAllvars> findVSnpAllvarsByVarnucContaining(String varnuc) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsByVarnucContaining
	 *
	 */
	public Set<VSnpAllvars> findVSnpAllvarsByVarnucContaining(String varnuc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsByVar
	 *
	 */
	public Set<VSnpAllvars> findVSnpAllvarsByVar(java.math.BigDecimal var) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsByVar
	 *
	 */
	public Set<VSnpAllvars> findVSnpAllvarsByVar(BigDecimal var, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsBySnpGenotypeId
	 *
	 */
	public VSnpAllvars findVSnpAllvarsBySnpGenotypeId(Integer snpGenotypeId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsBySnpGenotypeId
	 *
	 */
	public VSnpAllvars findVSnpAllvarsBySnpGenotypeId(Integer snpGenotypeId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpAllvarss
	 *
	 */
	public Set<VSnpAllvars> findAllVSnpAllvarss() throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpAllvarss
	 *
	 */
	public Set<VSnpAllvars> findAllVSnpAllvarss(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsByVarnuc
	 *
	 */
	public Set<VSnpAllvars> findVSnpAllvarsByVarnuc(String varnuc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsByVarnuc
	 *
	 */
	public Set<VSnpAllvars> findVSnpAllvarsByVarnuc(String varnuc_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsByPos
	 *
	 */
	public Set<VSnpAllvars> findVSnpAllvarsByPos(java.math.BigDecimal pos) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsByPos
	 *
	 */
	public Set<VSnpAllvars> findVSnpAllvarsByPos(BigDecimal pos, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsByRefnuc
	 *
	 */
	public Set<VSnpAllvars> findVSnpAllvarsByRefnuc(String refnuc) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsByRefnuc
	 *
	 */
	public Set<VSnpAllvars> findVSnpAllvarsByRefnuc(String refnuc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsByPrimaryKey
	 *
	 */
	public VSnpAllvars findVSnpAllvarsByPrimaryKey(Integer snpGenotypeId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsByPrimaryKey
	 *
	 */
	public VSnpAllvars findVSnpAllvarsByPrimaryKey(Integer snpGenotypeId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsByRefnucContaining
	 *
	 */
	public Set<VSnpAllvars> findVSnpAllvarsByRefnucContaining(String refnuc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsByRefnucContaining
	 *
	 */
	public Set<VSnpAllvars> findVSnpAllvarsByRefnucContaining(String refnuc_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsByChr
	 *
	 */
	public Set<VSnpAllvars> findVSnpAllvarsByChr(Integer chr) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsByChr
	 *
	 */
	public Set<VSnpAllvars> findVSnpAllvarsByChr(Integer chr, int startResult, int maxRows) throws DataAccessException;

	

	
	
}