package org.irri.iric.portal.genotype.dao;

import java.util.Set;

import org.irri.iric.portal.genotype.domain.Variety3k;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage Variety3k entities.
 * 
 */
public interface Variety3kDAO extends JpaDao<Variety3k> {

	/**
	 * JPQL Query - findVariety3kByVarname
	 *
	 */
	public Variety3k findVariety3kByVarname(String varname) throws DataAccessException;

	/**
	 * JPQL Query - findVariety3kByVarname
	 *
	 */
	public Variety3k findVariety3kByVarname(String varname, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVariety3ks
	 *
	 */
	public Set<Variety3k> findAllVariety3ks() throws DataAccessException;

	/**
	 * JPQL Query - findAllVariety3ks
	 *
	 */
	public Set<Variety3k> findAllVariety3ks(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVariety3kByVarnameContaining
	 *
	 */
	public Set<Variety3k> findVariety3kByVarnameContaining(String varname_1) throws DataAccessException;

	/**
	 * JPQL Query - findVariety3kByVarnameContaining
	 *
	 */
	public Set<Variety3k> findVariety3kByVarnameContaining(String varname_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVariety3kByPrimaryKey
	 *
	 */
	public Variety3k findVariety3kByPrimaryKey(String varname_2) throws DataAccessException;

	/**
	 * JPQL Query - findVariety3kByPrimaryKey
	 *
	 */
	public Variety3k findVariety3kByPrimaryKey(String varname_2, int startResult, int maxRows) throws DataAccessException;

}