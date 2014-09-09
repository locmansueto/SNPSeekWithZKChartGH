package org.irri.iric.portal.genotype.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.portal.dao.Snps2VarsDAO;
import org.irri.iric.portal.genotype.domain.VlSnp2vars;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VlSnp2vars entities.
 * 
 */
public interface VlSnp2varsDAO extends JpaDao<VlSnp2vars>, Snps2VarsDAO {

	/**
	 * JPQL Query - findVlSnp2varsByVar1nuc
	 *
	 */
	public Set<VlSnp2vars> findVlSnp2varsByVar1nuc(String var1nuc) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsByVar1nuc
	 *
	 */
	public Set<VlSnp2vars> findVlSnp2varsByVar1nuc(String var1nuc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsByVar2nucContaining
	 *
	 */
	public Set<VlSnp2vars> findVlSnp2varsByVar2nucContaining(String var2nuc) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsByVar2nucContaining
	 *
	 */
	public Set<VlSnp2vars> findVlSnp2varsByVar2nucContaining(String var2nuc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsByRefnuc
	 *
	 */
	public Set<VlSnp2vars> findVlSnp2varsByRefnuc(String refnuc) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsByRefnuc
	 *
	 */
	public Set<VlSnp2vars> findVlSnp2varsByRefnuc(String refnuc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsByVar1
	 *
	 */
	public Set<VlSnp2vars> findVlSnp2varsByVar1(Integer var1) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsByVar1
	 *
	 */
	public Set<VlSnp2vars> findVlSnp2varsByVar1(Integer var1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsByVar2
	 *
	 */
	public Set<VlSnp2vars> findVlSnp2varsByVar2(Integer var2) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsByVar2
	 *
	 */
	public Set<VlSnp2vars> findVlSnp2varsByVar2(Integer var2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsBySnpFeatureId
	 *
	 */
	public Set<VlSnp2vars> findVlSnp2varsBySnpFeatureId(Integer snpFeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsBySnpFeatureId
	 *
	 */
	public Set<VlSnp2vars> findVlSnp2varsBySnpFeatureId(Integer snpFeatureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsByVar1nucContaining
	 *
	 */
	public Set<VlSnp2vars> findVlSnp2varsByVar1nucContaining(String var1nuc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsByVar1nucContaining
	 *
	 */
	public Set<VlSnp2vars> findVlSnp2varsByVar1nucContaining(String var1nuc_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVlSnp2varss
	 *
	 */
	public Set<VlSnp2vars> findAllVlSnp2varss() throws DataAccessException;

	/**
	 * JPQL Query - findAllVlSnp2varss
	 *
	 */
	public Set<VlSnp2vars> findAllVlSnp2varss(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsByRefnucContaining
	 *
	 */
	public Set<VlSnp2vars> findVlSnp2varsByRefnucContaining(String refnuc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsByRefnucContaining
	 *
	 */
	public Set<VlSnp2vars> findVlSnp2varsByRefnucContaining(String refnuc_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsByPrimaryKey
	 *
	 */
	public VlSnp2vars findVlSnp2varsByPrimaryKey(Integer var1_1, Integer var2_1, Integer snpFeatureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsByPrimaryKey
	 *
	 */
	public VlSnp2vars findVlSnp2varsByPrimaryKey(Integer var1_1, Integer var2_1, Integer snpFeatureId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsByPos
	 *
	 */
	public Set<VlSnp2vars> findVlSnp2varsByPos(java.math.BigDecimal pos) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsByPos
	 *
	 */
	public Set<VlSnp2vars> findVlSnp2varsByPos(BigDecimal pos, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsByVar2nuc
	 *
	 */
	public Set<VlSnp2vars> findVlSnp2varsByVar2nuc(String var2nuc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsByVar2nuc
	 *
	 */
	public Set<VlSnp2vars> findVlSnp2varsByVar2nuc(String var2nuc_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsByChr
	 *
	 */
	public Set<VlSnp2vars> findVlSnp2varsByChr(Integer chr) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsByChr
	 *
	 */
	public Set<VlSnp2vars> findVlSnp2varsByChr(Integer chr, int startResult, int maxRows) throws DataAccessException;

}