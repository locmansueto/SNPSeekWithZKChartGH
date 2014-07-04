package org.irri.iric.portal.variety.dao;

import java.util.Set;

import org.irri.iric.portal.variety.domain.Names700;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage Names700 entities.
 * 
 */
public interface Names700DAO extends JpaDao<Names700> {

	/**
	 * JPQL Query - findNames700ByAssayIdContaining
	 *
	 */
	public Set<Names700> findNames700ByAssayIdContaining(String assayId) throws DataAccessException;

	/**
	 * JPQL Query - findNames700ByAssayIdContaining
	 *
	 */
	public Set<Names700> findNames700ByAssayIdContaining(String assayId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNames700ById
	 *
	 */
	public Names700 findNames700ById(String id) throws DataAccessException;

	/**
	 * JPQL Query - findNames700ById
	 *
	 */
	public Names700 findNames700ById(String id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNames700ByPrimaryKey
	 *
	 */
	public Names700 findNames700ByPrimaryKey(String id_1) throws DataAccessException;

	/**
	 * JPQL Query - findNames700ByPrimaryKey
	 *
	 */
	public Names700 findNames700ByPrimaryKey(String id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNames700ByAssayId
	 *
	 */
	public Set<Names700> findNames700ByAssayId(String assayId_1) throws DataAccessException;

	/**
	 * JPQL Query - findNames700ByAssayId
	 *
	 */
	public Set<Names700> findNames700ByAssayId(String assayId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNames700ByAltIdContaining
	 *
	 */
	public Set<Names700> findNames700ByAltIdContaining(String altId) throws DataAccessException;

	/**
	 * JPQL Query - findNames700ByAltIdContaining
	 *
	 */
	public Set<Names700> findNames700ByAltIdContaining(String altId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNames700BySampleId
	 *
	 */
	public Set<Names700> findNames700BySampleId(String sampleId) throws DataAccessException;

	/**
	 * JPQL Query - findNames700BySampleId
	 *
	 */
	public Set<Names700> findNames700BySampleId(String sampleId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNames700BySampleIdContaining
	 *
	 */
	public Set<Names700> findNames700BySampleIdContaining(String sampleId_1) throws DataAccessException;

	/**
	 * JPQL Query - findNames700BySampleIdContaining
	 *
	 */
	public Set<Names700> findNames700BySampleIdContaining(String sampleId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNames700ByAltId
	 *
	 */
	public Set<Names700> findNames700ByAltId(String altId_1) throws DataAccessException;

	/**
	 * JPQL Query - findNames700ByAltId
	 *
	 */
	public Set<Names700> findNames700ByAltId(String altId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNames700ByIdContaining
	 *
	 */
	public Set<Names700> findNames700ByIdContaining(String id_2) throws DataAccessException;

	/**
	 * JPQL Query - findNames700ByIdContaining
	 *
	 */
	public Set<Names700> findNames700ByIdContaining(String id_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllNames700s
	 *
	 */
	public Set<Names700> findAllNames700s() throws DataAccessException;

	/**
	 * JPQL Query - findAllNames700s
	 *
	 */
	public Set<Names700> findAllNames700s(int startResult, int maxRows) throws DataAccessException;

}