package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Query;

import org.irri.iric.portal.chado.domain.VSnp2vars;
import org.irri.iric.portal.dao.Snps2VarsDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnp2vars entities.
 * 
 */
public interface VSnp2varsDAO extends JpaDao<VSnp2vars>, Snps2VarsDAO {

	/**
	 * JPQL Query - findVSnp2varsByVar2nucContaining
	 *
	 */
	public Set<VSnp2vars> findVSnp2varsByVar2nucContaining(String var2nuc) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsByVar2nucContaining
	 *
	 */
	public Set<VSnp2vars> findVSnp2varsByVar2nucContaining(String var2nuc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsByPos
	 *
	 */
	public Set<VSnp2vars> findVSnp2varsByPos(java.math.BigDecimal pos) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsByPos
	 *
	 */
	public Set<VSnp2vars> findVSnp2varsByPos(BigDecimal pos, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsByVar1nuc
	 *
	 */
	public Set<VSnp2vars> findVSnp2varsByVar1nuc(String var1nuc) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsByVar1nuc
	 *
	 */
	public Set<VSnp2vars> findVSnp2varsByVar1nuc(String var1nuc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsByVar2nuc
	 *
	 */
	public Set<VSnp2vars> findVSnp2varsByVar2nuc(String var2nuc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsByVar2nuc
	 *
	 */
	public Set<VSnp2vars> findVSnp2varsByVar2nuc(String var2nuc_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsBySnpFeatureId
	 *
	 */
	public Set<VSnp2vars> findVSnp2varsBySnpFeatureId(Integer snpFeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsBySnpFeatureId
	 *
	 */
	public Set<VSnp2vars> findVSnp2varsBySnpFeatureId(Integer snpFeatureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsByVar2
	 *
	 */
	public Set<VSnp2vars> findVSnp2varsByVar2(Integer var2) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsByVar2
	 *
	 */
	public Set<VSnp2vars> findVSnp2varsByVar2(Integer var2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsByPrimaryKey
	 *
	 */
	public VSnp2vars findVSnp2varsByPrimaryKey(BigDecimal var1, BigDecimal var2_1, BigDecimal snpFeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsByPrimaryKey
	 *
	 */
	public VSnp2vars findVSnp2varsByPrimaryKey(BigDecimal var1, BigDecimal var2_1, BigDecimal snpFeatureId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsByRefnucContaining
	 *
	 */
	public Set<VSnp2vars> findVSnp2varsByRefnucContaining(String refnuc) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsByRefnucContaining
	 *
	 */
	public Set<VSnp2vars> findVSnp2varsByRefnucContaining(String refnuc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsByChr
	 *
	 */
	public Set<VSnp2vars> findVSnp2varsByChr(Integer chr) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsByChr
	 *
	 */
	public Set<VSnp2vars> findVSnp2varsByChr(Integer chr, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsByVar1
	 *
	 */
	public Set<VSnp2vars> findVSnp2varsByVar1(Integer var1_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsByVar1
	 *
	 */
	public Set<VSnp2vars> findVSnp2varsByVar1(Integer var1_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsByRefnuc
	 *
	 */
	public Set<VSnp2vars> findVSnp2varsByRefnuc(String refnuc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsByRefnuc
	 *
	 */
	public Set<VSnp2vars> findVSnp2varsByRefnuc(String refnuc_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnp2varss
	 *
	 */
	public Set<VSnp2vars> findAllVSnp2varss() throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnp2varss
	 *
	 */
	public Set<VSnp2vars> findAllVSnp2varss(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsByVar1nucContaining
	 *
	 */
	public Set<VSnp2vars> findVSnp2varsByVar1nucContaining(String var1nuc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsByVar1nucContaining
	 *
	 */
	public Set<VSnp2vars> findVSnp2varsByVar1nucContaining(String var1nuc_1, int startResult, int maxRows) throws DataAccessException;
	
	


}