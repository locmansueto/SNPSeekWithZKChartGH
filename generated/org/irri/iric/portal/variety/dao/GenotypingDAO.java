package org.irri.iric.portal.variety.dao;

import java.util.Set;

import org.irri.iric.portal.variety.domain.Genotyping;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage Genotyping entities.
 * 
 */
public interface GenotypingDAO extends JpaDao<Genotyping> {

	/**
	 * JPQL Query - findGenotypingByNsftvId
	 *
	 */
	public Set<Genotyping> findGenotypingByNsftvId(Integer nsftvId) throws DataAccessException;

	/**
	 * JPQL Query - findGenotypingByNsftvId
	 *
	 */
	public Set<Genotyping> findGenotypingByNsftvId(Integer nsftvId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGenotypingByVar1Containing
	 *
	 */
	public Set<Genotyping> findGenotypingByVar1Containing(String var1) throws DataAccessException;

	/**
	 * JPQL Query - findGenotypingByVar1Containing
	 *
	 */
	public Set<Genotyping> findGenotypingByVar1Containing(String var1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGenotypingByPrimaryKey
	 *
	 */
	public Genotyping findGenotypingByPrimaryKey(String snpId, Integer nsftvId_1) throws DataAccessException;

	/**
	 * JPQL Query - findGenotypingByPrimaryKey
	 *
	 */
	public Genotyping findGenotypingByPrimaryKey(String snpId, Integer nsftvId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGenotypingByVar2Containing
	 *
	 */
	public Set<Genotyping> findGenotypingByVar2Containing(String var2) throws DataAccessException;

	/**
	 * JPQL Query - findGenotypingByVar2Containing
	 *
	 */
	public Set<Genotyping> findGenotypingByVar2Containing(String var2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGenotypingBySnpId
	 *
	 */
	public Set<Genotyping> findGenotypingBySnpId(String snpId_1) throws DataAccessException;

	/**
	 * JPQL Query - findGenotypingBySnpId
	 *
	 */
	public Set<Genotyping> findGenotypingBySnpId(String snpId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGenotypingByVar2
	 *
	 */
	public Set<Genotyping> findGenotypingByVar2(String var2_1) throws DataAccessException;

	/**
	 * JPQL Query - findGenotypingByVar2
	 *
	 */
	public Set<Genotyping> findGenotypingByVar2(String var2_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGenotypingByVar1
	 *
	 */
	public Set<Genotyping> findGenotypingByVar1(String var1_1) throws DataAccessException;

	/**
	 * JPQL Query - findGenotypingByVar1
	 *
	 */
	public Set<Genotyping> findGenotypingByVar1(String var1_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGenotypingBySnpIdContaining
	 *
	 */
	public Set<Genotyping> findGenotypingBySnpIdContaining(String snpId_2) throws DataAccessException;

	/**
	 * JPQL Query - findGenotypingBySnpIdContaining
	 *
	 */
	public Set<Genotyping> findGenotypingBySnpIdContaining(String snpId_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllGenotypings
	 *
	 */
	public Set<Genotyping> findAllGenotypings() throws DataAccessException;

	/**
	 * JPQL Query - findAllGenotypings
	 *
	 */
	public Set<Genotyping> findAllGenotypings(int startResult, int maxRows) throws DataAccessException;

}