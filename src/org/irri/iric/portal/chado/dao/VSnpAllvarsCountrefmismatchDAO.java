package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VSnpAllvarsCountrefmismatch;
import org.irri.iric.portal.dao.SnpsAllvarsRefMismatchDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VSnpAllvarsCountrefmismatch entities.
 * 
 */
public interface VSnpAllvarsCountrefmismatchDAO extends
		JpaDao<VSnpAllvarsCountrefmismatch> , SnpsAllvarsRefMismatchDAO {

	/**
	 * JPQL Query - findAllVSnpAllvarsCountrefmismatchs
	 *
	 */
	public Set<VSnpAllvarsCountrefmismatch> findAllVSnpAllvarsCountrefmismatchs() throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpAllvarsCountrefmismatchs
	 *
	 */
	public Set<VSnpAllvarsCountrefmismatch> findAllVSnpAllvarsCountrefmismatchs(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsCountrefmismatchByVar
	 *
	 */
	public VSnpAllvarsCountrefmismatch findVSnpAllvarsCountrefmismatchByVar(Integer var) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsCountrefmismatchByVar
	 *
	 */
	public VSnpAllvarsCountrefmismatch findVSnpAllvarsCountrefmismatchByVar(Integer var, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsCountrefmismatchByPrimaryKey
	 *
	 */
	public VSnpAllvarsCountrefmismatch findVSnpAllvarsCountrefmismatchByPrimaryKey(Integer var_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsCountrefmismatchByPrimaryKey
	 *
	 */
	public VSnpAllvarsCountrefmismatch findVSnpAllvarsCountrefmismatchByPrimaryKey(Integer var_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsCountrefmismatchByMismatch
	 *
	 */
	public Set<VSnpAllvarsCountrefmismatch> findVSnpAllvarsCountrefmismatchByMismatch(java.math.BigDecimal mismatch) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsCountrefmismatchByMismatch
	 *
	 */
	public Set<VSnpAllvarsCountrefmismatch> findVSnpAllvarsCountrefmismatchByMismatch(BigDecimal mismatch, int startResult, int maxRows) throws DataAccessException;

}