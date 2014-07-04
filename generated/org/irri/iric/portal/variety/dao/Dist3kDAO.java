package org.irri.iric.portal.variety.dao;

import java.util.Set;

import org.irri.iric.portal.variety.domain.Dist3k;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage Dist3k entities.
 * 
 */
public interface Dist3kDAO extends JpaDao<Dist3k> {

	/**
	 * JPQL Query - findDist3kByPrimaryKey
	 *
	 */
	public Dist3k findDist3kByPrimaryKey(String nam1, String nam2) throws DataAccessException;

	/**
	 * JPQL Query - findDist3kByPrimaryKey
	 *
	 */
	public Dist3k findDist3kByPrimaryKey(String nam1, String nam2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findDist3kByNam2
	 *
	 */
	public Set<Dist3k> findDist3kByNam2(String nam2_1) throws DataAccessException;

	/**
	 * JPQL Query - findDist3kByNam2
	 *
	 */
	public Set<Dist3k> findDist3kByNam2(String nam2_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findDist3kByNam2Containing
	 *
	 */
	public Set<Dist3k> findDist3kByNam2Containing(String nam2_2) throws DataAccessException;

	/**
	 * JPQL Query - findDist3kByNam2Containing
	 *
	 */
	public Set<Dist3k> findDist3kByNam2Containing(String nam2_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findDist3kByNam1Containing
	 *
	 */
	public Set<Dist3k> findDist3kByNam1Containing(String nam1_1) throws DataAccessException;

	/**
	 * JPQL Query - findDist3kByNam1Containing
	 *
	 */
	public Set<Dist3k> findDist3kByNam1Containing(String nam1_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findDist3kByDist
	 *
	 */
	public Set<Dist3k> findDist3kByDist(Integer dist) throws DataAccessException;

	/**
	 * JPQL Query - findDist3kByDist
	 *
	 */
	public Set<Dist3k> findDist3kByDist(Integer dist, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllDist3ks
	 *
	 */
	public Set<Dist3k> findAllDist3ks() throws DataAccessException;

	/**
	 * JPQL Query - findAllDist3ks
	 *
	 */
	public Set<Dist3k> findAllDist3ks(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findDist3kByNam1
	 *
	 */
	public Set<Dist3k> findDist3kByNam1(String nam1_2) throws DataAccessException;

	/**
	 * JPQL Query - findDist3kByNam1
	 *
	 */
	public Set<Dist3k> findDist3kByNam1(String nam1_2, int startResult, int maxRows) throws DataAccessException;

}