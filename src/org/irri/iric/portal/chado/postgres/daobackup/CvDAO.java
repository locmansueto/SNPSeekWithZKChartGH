package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.Set;

import org.irri.iric.portal.chado.postgres.domain.Cv;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage Cv entities.
 * 
 */
public interface CvDAO extends JpaDao<Cv>, org.irri.iric.portal.dao.CvDAO {

	/**
	 * JPQL Query - findCvByDefinition
	 *
	 */
	public Set<Cv> findCvByDefinition(String definition) throws DataAccessException;

	/**
	 * JPQL Query - findCvByDefinition
	 *
	 */
	public Set<Cv> findCvByDefinition(String definition, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCvByName
	 *
	 */
	public Set<Cv> findCvByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findCvByName
	 *
	 */
	public Set<Cv> findCvByName(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllCvs
	 *
	 */
	public Set<Cv> findAllCvs() throws DataAccessException;

	/**
	 * JPQL Query - findAllCvs
	 *
	 */
	public Set<Cv> findAllCvs(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCvByDefinitionContaining
	 *
	 */
	public Set<Cv> findCvByDefinitionContaining(String definition_1) throws DataAccessException;

	/**
	 * JPQL Query - findCvByDefinitionContaining
	 *
	 */
	public Set<Cv> findCvByDefinitionContaining(String definition_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCvByPrimaryKey
	 *
	 */
	public Cv findCvByPrimaryKey(Integer cvId) throws DataAccessException;

	/**
	 * JPQL Query - findCvByPrimaryKey
	 *
	 */
	public Cv findCvByPrimaryKey(Integer cvId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCvByNameContaining
	 *
	 */
	public Set<Cv> findCvByNameContaining(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findCvByNameContaining
	 *
	 */
	public Set<Cv> findCvByNameContaining(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCvByCvId
	 *
	 */
	public Cv findCvByCvId(Integer cvId_1) throws DataAccessException;

	/**
	 * JPQL Query - findCvByCvId
	 *
	 */
	public Cv findCvByCvId(Integer cvId_1, int startResult, int maxRows) throws DataAccessException;

}