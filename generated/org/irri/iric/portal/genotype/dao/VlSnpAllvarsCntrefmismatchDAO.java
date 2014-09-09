package org.irri.iric.portal.genotype.dao;

import java.util.Set;

import org.irri.iric.portal.dao.SnpsAllvarsRefMismatchDAO;
import org.irri.iric.portal.genotype.domain.VlSnpAllvarsCntrefmismatch;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VlSnpAllvarsCntrefmismatch entities.
 * 
 */
public interface VlSnpAllvarsCntrefmismatchDAO extends
		JpaDao<VlSnpAllvarsCntrefmismatch>, SnpsAllvarsRefMismatchDAO {

	/**
	 * JPQL Query - findVlSnpAllvarsCntrefmismatchByVar
	 *
	 */
	public VlSnpAllvarsCntrefmismatch findVlSnpAllvarsCntrefmismatchByVar(Integer var) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpAllvarsCntrefmismatchByVar
	 *
	 */
	public VlSnpAllvarsCntrefmismatch findVlSnpAllvarsCntrefmismatchByVar(Integer var, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpAllvarsCntrefmismatchByPrimaryKey
	 *
	 */
	public VlSnpAllvarsCntrefmismatch findVlSnpAllvarsCntrefmismatchByPrimaryKey(Integer var_1) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpAllvarsCntrefmismatchByPrimaryKey
	 *
	 */
	public VlSnpAllvarsCntrefmismatch findVlSnpAllvarsCntrefmismatchByPrimaryKey(Integer var_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpAllvarsCntrefmismatchByMismatch
	 *
	 */
	public Set<VlSnpAllvarsCntrefmismatch> findVlSnpAllvarsCntrefmismatchByMismatch(Integer mismatch) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpAllvarsCntrefmismatchByMismatch
	 *
	 */
	public Set<VlSnpAllvarsCntrefmismatch> findVlSnpAllvarsCntrefmismatchByMismatch(Integer mismatch, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVlSnpAllvarsCntrefmismatchs
	 *
	 */
	public Set<VlSnpAllvarsCntrefmismatch> findAllVlSnpAllvarsCntrefmismatchs() throws DataAccessException;

	/**
	 * JPQL Query - findAllVlSnpAllvarsCntrefmismatchs
	 *
	 */
	public Set<VlSnpAllvarsCntrefmismatch> findAllVlSnpAllvarsCntrefmismatchs(int startResult, int maxRows) throws DataAccessException;

}