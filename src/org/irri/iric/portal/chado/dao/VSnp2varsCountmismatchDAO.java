package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VSnp2varsCountmismatch;
import org.irri.iric.portal.dao.Snps2VarsCountMismatchDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VSnp2varsCountmismatch entities.
 * 
 */
public interface VSnp2varsCountmismatchDAO extends
		JpaDao<VSnp2varsCountmismatch> , Snps2VarsCountMismatchDAO {

	/**
	 * JPQL Query - findVSnp2varsCountmismatchByMismatch
	 *
	 */
	public Set<VSnp2varsCountmismatch> findVSnp2varsCountmismatchByMismatch(Integer mismatch) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsCountmismatchByMismatch
	 *
	 */
	public Set<VSnp2varsCountmismatch> findVSnp2varsCountmismatchByMismatch(Integer mismatch, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsCountmismatchByVar2
	 *
	 */
	public Set<VSnp2varsCountmismatch> findVSnp2varsCountmismatchByVar2(Integer var2) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsCountmismatchByVar2
	 *
	 */
	public Set<VSnp2varsCountmismatch> findVSnp2varsCountmismatchByVar2(Integer var2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnp2varsCountmismatchs
	 *
	 */
	public Set<VSnp2varsCountmismatch> findAllVSnp2varsCountmismatchs() throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnp2varsCountmismatchs
	 *
	 */
	public Set<VSnp2varsCountmismatch> findAllVSnp2varsCountmismatchs(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsCountmismatchByVar1
	 *
	 */
	public Set<VSnp2varsCountmismatch> findVSnp2varsCountmismatchByVar1(Integer var1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsCountmismatchByVar1
	 *
	 */
	public Set<VSnp2varsCountmismatch> findVSnp2varsCountmismatchByVar1(Integer var1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsCountmismatchByPrimaryKey
	 *
	 */
	public VSnp2varsCountmismatch findVSnp2varsCountmismatchByPrimaryKey(BigDecimal var1_1, BigDecimal var2_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnp2varsCountmismatchByPrimaryKey
	 *
	 */
	public VSnp2varsCountmismatch findVSnp2varsCountmismatchByPrimaryKey(BigDecimal var1_1, BigDecimal var2_1, int startResult, int maxRows) throws DataAccessException;

}