package org.irri.iric.portal.variety.dao;

import java.util.Set;

import org.irri.iric.portal.variety.domain.Var700;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage Var700 entities.
 * 
 */
public interface Var700DAO extends JpaDao<Var700> {

	/**
	 * JPQL Query - findVar700ByName
	 *
	 */
	public Set<Var700> findVar700ByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVar700ByName
	 *
	 */
	public Set<Var700> findVar700ByName(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVar700ByPrimaryKey
	 *
	 */
	public Var700 findVar700ByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findVar700ByPrimaryKey
	 *
	 */
	public Var700 findVar700ByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVar700ByNameContaining
	 *
	 */
	public Set<Var700> findVar700ByNameContaining(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVar700ByNameContaining
	 *
	 */
	public Set<Var700> findVar700ByNameContaining(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVar700s
	 *
	 */
	public Set<Var700> findAllVar700s() throws DataAccessException;

	/**
	 * JPQL Query - findAllVar700s
	 *
	 */
	public Set<Var700> findAllVar700s(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVar700ById
	 *
	 */
	public Var700 findVar700ById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findVar700ById
	 *
	 */
	public Var700 findVar700ById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}