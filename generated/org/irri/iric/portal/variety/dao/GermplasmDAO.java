package org.irri.iric.portal.variety.dao;

import java.util.Set;

import org.irri.iric.portal.variety.domain.Germplasm;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage Germplasm entities.
 * 
 */
public interface GermplasmDAO extends JpaDao<Germplasm> {

	/**
	 * JPQL Query - findGermplasmByLongitude
	 *
	 */
	public Set<Germplasm> findGermplasmByLongitude(Integer longitude) throws DataAccessException;

	/**
	 * JPQL Query - findGermplasmByLongitude
	 *
	 */
	public Set<Germplasm> findGermplasmByLongitude(Integer longitude, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGermplasmBySubpopulation
	 *
	 */
	public Set<Germplasm> findGermplasmBySubpopulation(String subpopulation) throws DataAccessException;

	/**
	 * JPQL Query - findGermplasmBySubpopulation
	 *
	 */
	public Set<Germplasm> findGermplasmBySubpopulation(String subpopulation, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGermplasmByAccessionContaining
	 *
	 */
	public Set<Germplasm> findGermplasmByAccessionContaining(String accession) throws DataAccessException;

	/**
	 * JPQL Query - findGermplasmByAccessionContaining
	 *
	 */
	public Set<Germplasm> findGermplasmByAccessionContaining(String accession, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGermplasmByAccession
	 *
	 */
	public Set<Germplasm> findGermplasmByAccession(String accession_1) throws DataAccessException;

	/**
	 * JPQL Query - findGermplasmByAccession
	 *
	 */
	public Set<Germplasm> findGermplasmByAccession(String accession_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGermplasmByPrimaryKey
	 *
	 */
	public Germplasm findGermplasmByPrimaryKey(Integer nsftvId) throws DataAccessException;

	/**
	 * JPQL Query - findGermplasmByPrimaryKey
	 *
	 */
	public Germplasm findGermplasmByPrimaryKey(Integer nsftvId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGermplasmByCountry
	 *
	 */
	public Set<Germplasm> findGermplasmByCountry(String country) throws DataAccessException;

	/**
	 * JPQL Query - findGermplasmByCountry
	 *
	 */
	public Set<Germplasm> findGermplasmByCountry(String country, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGermplasmBySubpopulationContaining
	 *
	 */
	public Set<Germplasm> findGermplasmBySubpopulationContaining(String subpopulation_1) throws DataAccessException;

	/**
	 * JPQL Query - findGermplasmBySubpopulationContaining
	 *
	 */
	public Set<Germplasm> findGermplasmBySubpopulationContaining(String subpopulation_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGermplasmByNsftvId
	 *
	 */
	public Germplasm findGermplasmByNsftvId(Integer nsftvId_1) throws DataAccessException;

	/**
	 * JPQL Query - findGermplasmByNsftvId
	 *
	 */
	public Germplasm findGermplasmByNsftvId(Integer nsftvId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGermplasmByCountryContaining
	 *
	 */
	public Set<Germplasm> findGermplasmByCountryContaining(String country_1) throws DataAccessException;

	/**
	 * JPQL Query - findGermplasmByCountryContaining
	 *
	 */
	public Set<Germplasm> findGermplasmByCountryContaining(String country_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGermplasmByLatitude
	 *
	 */
	public Set<Germplasm> findGermplasmByLatitude(Integer latitude) throws DataAccessException;

	/**
	 * JPQL Query - findGermplasmByLatitude
	 *
	 */
	public Set<Germplasm> findGermplasmByLatitude(Integer latitude, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllGermplasms
	 *
	 */
	public Set<Germplasm> findAllGermplasms() throws DataAccessException;

	/**
	 * JPQL Query - findAllGermplasms
	 *
	 */
	public Set<Germplasm> findAllGermplasms(int startResult, int maxRows) throws DataAccessException;

}