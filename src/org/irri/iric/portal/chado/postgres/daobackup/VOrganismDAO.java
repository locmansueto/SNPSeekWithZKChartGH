package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.Set;

import org.irri.iric.portal.chado.postgres.domain.VOrganism;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VOrganism entities.
 * 
 */
public interface VOrganismDAO extends JpaDao<VOrganism>, org.irri.iric.portal.dao.OrganismDAO  {

	/**
	 * JPQL Query - findAllVOrganisms
	 *
	 */
	public Set<VOrganism> findAllVOrganisms() throws DataAccessException;

	/**
	 * JPQL Query - findAllVOrganisms
	 *
	 */
	public Set<VOrganism> findAllVOrganisms(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVOrganismByCommonName
	 *
	 */
	public Set<VOrganism> findVOrganismByCommonName(String commonName) throws DataAccessException;

	/**
	 * JPQL Query - findVOrganismByCommonName
	 *
	 */
	public Set<VOrganism> findVOrganismByCommonName(String commonName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVOrganismByCommonNameContaining
	 *
	 */
	public Set<VOrganism> findVOrganismByCommonNameContaining(String commonName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVOrganismByCommonNameContaining
	 *
	 */
	public Set<VOrganism> findVOrganismByCommonNameContaining(String commonName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVOrganismByCommentContaining
	 *
	 */
	public Set<VOrganism> findVOrganismByCommentContaining(String comment) throws DataAccessException;

	/**
	 * JPQL Query - findVOrganismByCommentContaining
	 *
	 */
	public Set<VOrganism> findVOrganismByCommentContaining(String comment, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVOrganismByGenusContaining
	 *
	 */
	public Set<VOrganism> findVOrganismByGenusContaining(String genus) throws DataAccessException;

	/**
	 * JPQL Query - findVOrganismByGenusContaining
	 *
	 */
	public Set<VOrganism> findVOrganismByGenusContaining(String genus, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVOrganismByComment
	 *
	 */
	public Set<VOrganism> findVOrganismByComment(String comment_1) throws DataAccessException;

	/**
	 * JPQL Query - findVOrganismByComment
	 *
	 */
	public Set<VOrganism> findVOrganismByComment(String comment_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVOrganismByOrganismId
	 *
	 */
	public VOrganism findVOrganismByOrganismId(Integer organismId) throws DataAccessException;

	/**
	 * JPQL Query - findVOrganismByOrganismId
	 *
	 */
	public VOrganism findVOrganismByOrganismId(Integer organismId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVOrganismByPrimaryKey
	 *
	 */
	public VOrganism findVOrganismByPrimaryKey(Integer organismId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVOrganismByPrimaryKey
	 *
	 */
	public VOrganism findVOrganismByPrimaryKey(Integer organismId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVOrganismBySpecies
	 *
	 */
	public Set<VOrganism> findVOrganismBySpecies(String species) throws DataAccessException;

	/**
	 * JPQL Query - findVOrganismBySpecies
	 *
	 */
	public Set<VOrganism> findVOrganismBySpecies(String species, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVOrganismByAbbreviation
	 *
	 */
	public Set<VOrganism> findVOrganismByAbbreviation(String abbreviation) throws DataAccessException;

	/**
	 * JPQL Query - findVOrganismByAbbreviation
	 *
	 */
	public Set<VOrganism> findVOrganismByAbbreviation(String abbreviation, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVOrganismByAbbreviationContaining
	 *
	 */
	public Set<VOrganism> findVOrganismByAbbreviationContaining(String abbreviation_1) throws DataAccessException;

	/**
	 * JPQL Query - findVOrganismByAbbreviationContaining
	 *
	 */
	public Set<VOrganism> findVOrganismByAbbreviationContaining(String abbreviation_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVOrganismBySpeciesContaining
	 *
	 */
	public Set<VOrganism> findVOrganismBySpeciesContaining(String species_1) throws DataAccessException;

	/**
	 * JPQL Query - findVOrganismBySpeciesContaining
	 *
	 */
	public Set<VOrganism> findVOrganismBySpeciesContaining(String species_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVOrganismByGenus
	 *
	 */
	public Set<VOrganism> findVOrganismByGenus(String genus_1) throws DataAccessException;

	/**
	 * JPQL Query - findVOrganismByGenus
	 *
	 */
	public Set<VOrganism> findVOrganismByGenus(String genus_1, int startResult, int maxRows) throws DataAccessException;

}