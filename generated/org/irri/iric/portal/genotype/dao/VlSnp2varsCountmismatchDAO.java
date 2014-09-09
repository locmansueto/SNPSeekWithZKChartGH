package org.irri.iric.portal.genotype.dao;

import java.util.Set;

import org.irri.iric.portal.dao.Snps2VarsCountMismatchDAO;
import org.irri.iric.portal.genotype.domain.VlSnp2varsCountmismatch;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VlSnp2varsCountmismatch entities.
 * 
 */
public interface VlSnp2varsCountmismatchDAO extends
		JpaDao<VlSnp2varsCountmismatch>, Snps2VarsCountMismatchDAO {

	/**
	 * JPQL Query - findAllVlSnp2varsCountmismatchs
	 *
	 */
	public Set<VlSnp2varsCountmismatch> findAllVlSnp2varsCountmismatchs() throws DataAccessException;

	/**
	 * JPQL Query - findAllVlSnp2varsCountmismatchs
	 *
	 */
	public Set<VlSnp2varsCountmismatch> findAllVlSnp2varsCountmismatchs(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsCountmismatchByMismatch
	 *
	 */
	public Set<VlSnp2varsCountmismatch> findVlSnp2varsCountmismatchByMismatch(Integer mismatch) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsCountmismatchByMismatch
	 *
	 */
	public Set<VlSnp2varsCountmismatch> findVlSnp2varsCountmismatchByMismatch(Integer mismatch, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsCountmismatchByVar1
	 *
	 */
	public Set<VlSnp2varsCountmismatch> findVlSnp2varsCountmismatchByVar1(Integer var1) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsCountmismatchByVar1
	 *
	 */
	public Set<VlSnp2varsCountmismatch> findVlSnp2varsCountmismatchByVar1(Integer var1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsCountmismatchByVar2
	 *
	 */
	public Set<VlSnp2varsCountmismatch> findVlSnp2varsCountmismatchByVar2(Integer var2) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsCountmismatchByVar2
	 *
	 */
	public Set<VlSnp2varsCountmismatch> findVlSnp2varsCountmismatchByVar2(Integer var2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsCountmismatchByPrimaryKey
	 *
	 */
	public VlSnp2varsCountmismatch findVlSnp2varsCountmismatchByPrimaryKey(Integer var1_1, Integer var2_1) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnp2varsCountmismatchByPrimaryKey
	 *
	 */
	public VlSnp2varsCountmismatch findVlSnp2varsCountmismatchByPrimaryKey(Integer var1_1, Integer var2_1, int startResult, int maxRows) throws DataAccessException;

}