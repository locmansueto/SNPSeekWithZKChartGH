package org.irri.iric.portal.variety.dao;

import java.util.Set;

import org.irri.iric.portal.variety.domain.Phenotypes;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage Phenotypes entities.
 * 
 */
public interface PhenotypesDAO extends JpaDao<Phenotypes> {

	/**
	 * JPQL Query - findAllPhenotypess
	 *
	 */
	public Set<Phenotypes> findAllPhenotypess() throws DataAccessException;

	/**
	 * JPQL Query - findAllPhenotypess
	 *
	 */
	public Set<Phenotypes> findAllPhenotypess(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenotypesByPrimaryKey
	 *
	 */
	public Phenotypes findPhenotypesByPrimaryKey(Integer nsftvId, String trait) throws DataAccessException;

	/**
	 * JPQL Query - findPhenotypesByPrimaryKey
	 *
	 */
	public Phenotypes findPhenotypesByPrimaryKey(Integer nsftvId, String trait, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenotypesByTrait
	 *
	 */
	public Set<Phenotypes> findPhenotypesByTrait(String trait_1) throws DataAccessException;

	/**
	 * JPQL Query - findPhenotypesByTrait
	 *
	 */
	public Set<Phenotypes> findPhenotypesByTrait(String trait_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenotypesByNsftvId
	 *
	 */
	public Set<Phenotypes> findPhenotypesByNsftvId(Integer nsftvId_1) throws DataAccessException;

	/**
	 * JPQL Query - findPhenotypesByNsftvId
	 *
	 */
	public Set<Phenotypes> findPhenotypesByNsftvId(Integer nsftvId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenotypesByTraitContaining
	 *
	 */
	public Set<Phenotypes> findPhenotypesByTraitContaining(String trait_2) throws DataAccessException;

	/**
	 * JPQL Query - findPhenotypesByTraitContaining
	 *
	 */
	public Set<Phenotypes> findPhenotypesByTraitContaining(String trait_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenotypesByVal
	 *
	 */
	public Set<Phenotypes> findPhenotypesByVal(Integer val) throws DataAccessException;

	/**
	 * JPQL Query - findPhenotypesByVal
	 *
	 */
	public Set<Phenotypes> findPhenotypesByVal(Integer val, int startResult, int maxRows) throws DataAccessException;

}