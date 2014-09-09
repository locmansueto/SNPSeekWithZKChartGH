package org.irri.iric.portal.genotype.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.portal.dao.SnpsAllvarsDAO;
import org.irri.iric.portal.genotype.domain.VlSnpAllvars;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VlSnpAllvars entities.
 * 
 */
public interface VlSnpAllvarsDAO extends JpaDao<VlSnpAllvars>, SnpsAllvarsDAO {

	/**
	 * JPQL Query - findVlSnpAllvarsByRefnuc
	 *
	 */
	public Set<VlSnpAllvars> findVlSnpAllvarsByRefnuc(String refnuc) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpAllvarsByRefnuc
	 *
	 */
	public Set<VlSnpAllvars> findVlSnpAllvarsByRefnuc(String refnuc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpAllvarsByVarnuc
	 *
	 */
	public Set<VlSnpAllvars> findVlSnpAllvarsByVarnuc(String varnuc) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpAllvarsByVarnuc
	 *
	 */
	public Set<VlSnpAllvars> findVlSnpAllvarsByVarnuc(String varnuc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpAllvarsByChr
	 *
	 */
	public Set<VlSnpAllvars> findVlSnpAllvarsByChr(Integer chr) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpAllvarsByChr
	 *
	 */
	public Set<VlSnpAllvars> findVlSnpAllvarsByChr(Integer chr, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpAllvarsBySnpGenotypeId
	 *
	 */
	public VlSnpAllvars findVlSnpAllvarsBySnpGenotypeId(Integer snpGenotypeId) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpAllvarsBySnpGenotypeId
	 *
	 */
	public VlSnpAllvars findVlSnpAllvarsBySnpGenotypeId(Integer snpGenotypeId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpAllvarsByPrimaryKey
	 *
	 */
	public VlSnpAllvars findVlSnpAllvarsByPrimaryKey(Integer snpGenotypeId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpAllvarsByPrimaryKey
	 *
	 */
	public VlSnpAllvars findVlSnpAllvarsByPrimaryKey(Integer snpGenotypeId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpAllvarsByRefnucContaining
	 *
	 */
	public Set<VlSnpAllvars> findVlSnpAllvarsByRefnucContaining(String refnuc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpAllvarsByRefnucContaining
	 *
	 */
	public Set<VlSnpAllvars> findVlSnpAllvarsByRefnucContaining(String refnuc_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpAllvarsByVar
	 *
	 */
	public Set<VlSnpAllvars> findVlSnpAllvarsByVar(java.math.BigDecimal var) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpAllvarsByVar
	 *
	 */
	public Set<VlSnpAllvars> findVlSnpAllvarsByVar(BigDecimal var, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpAllvarsByVarnucContaining
	 *
	 */
	public Set<VlSnpAllvars> findVlSnpAllvarsByVarnucContaining(String varnuc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpAllvarsByVarnucContaining
	 *
	 */
	public Set<VlSnpAllvars> findVlSnpAllvarsByVarnucContaining(String varnuc_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpAllvarsByPos
	 *
	 */
	public Set<VlSnpAllvars> findVlSnpAllvarsByPos(java.math.BigDecimal pos) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpAllvarsByPos
	 *
	 */
	public Set<VlSnpAllvars> findVlSnpAllvarsByPos(BigDecimal pos, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVlSnpAllvarss
	 *
	 */
	public Set<VlSnpAllvars> findAllVlSnpAllvarss() throws DataAccessException;

	/**
	 * JPQL Query - findAllVlSnpAllvarss
	 *
	 */
	public Set<VlSnpAllvars> findAllVlSnpAllvarss(int startResult, int maxRows) throws DataAccessException;

}