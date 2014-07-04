package org.irri.iric.portal.variety.dao;

import java.util.Set;

import org.irri.iric.portal.variety.domain.Genotyp700;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage Genotyp700 entities.
 * 
 */
public interface Genotyp700DAO extends JpaDao<Genotyp700> {

	/**
	 * JPQL Query - findGenotyp700ByPrimaryKey
	 *
	 */
	public Genotyp700 findGenotyp700ByPrimaryKey(Integer varId, Integer snpId) throws DataAccessException;

	/**
	 * JPQL Query - findGenotyp700ByPrimaryKey
	 *
	 */
	public Genotyp700 findGenotyp700ByPrimaryKey(Integer varId, Integer snpId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllGenotyp700s
	 *
	 */
	public Set<Genotyp700> findAllGenotyp700s() throws DataAccessException;

	/**
	 * JPQL Query - findAllGenotyp700s
	 *
	 */
	public Set<Genotyp700> findAllGenotyp700s(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGenotyp700ByTypContaining
	 *
	 */
	public Set<Genotyp700> findGenotyp700ByTypContaining(String typ) throws DataAccessException;

	/**
	 * JPQL Query - findGenotyp700ByTypContaining
	 *
	 */
	public Set<Genotyp700> findGenotyp700ByTypContaining(String typ, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGenotyp700ByAl1
	 *
	 */
	public Set<Genotyp700> findGenotyp700ByAl1(Boolean al1) throws DataAccessException;

	/**
	 * JPQL Query - findGenotyp700ByAl1
	 *
	 */
	public Set<Genotyp700> findGenotyp700ByAl1(Boolean al1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGenotyp700ByTyp
	 *
	 */
	public Set<Genotyp700> findGenotyp700ByTyp(String typ_1) throws DataAccessException;

	/**
	 * JPQL Query - findGenotyp700ByTyp
	 *
	 */
	public Set<Genotyp700> findGenotyp700ByTyp(String typ_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGenotyp700ByVarId
	 *
	 */
	public Set<Genotyp700> findGenotyp700ByVarId(Integer varId_1) throws DataAccessException;

	/**
	 * JPQL Query - findGenotyp700ByVarId
	 *
	 */
	public Set<Genotyp700> findGenotyp700ByVarId(Integer varId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGenotyp700ByAl2
	 *
	 */
	public Set<Genotyp700> findGenotyp700ByAl2(Boolean al2) throws DataAccessException;

	/**
	 * JPQL Query - findGenotyp700ByAl2
	 *
	 */
	public Set<Genotyp700> findGenotyp700ByAl2(Boolean al2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGenotyp700BySnpId
	 *
	 */
	public Set<Genotyp700> findGenotyp700BySnpId(Integer snpId_1) throws DataAccessException;

	/**
	 * JPQL Query - findGenotyp700BySnpId
	 *
	 */
	public Set<Genotyp700> findGenotyp700BySnpId(Integer snpId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGenotyp700ByQs
	 *
	 */
	public Set<Genotyp700> findGenotyp700ByQs(Integer qs) throws DataAccessException;

	/**
	 * JPQL Query - findGenotyp700ByQs
	 *
	 */
	public Set<Genotyp700> findGenotyp700ByQs(Integer qs, int startResult, int maxRows) throws DataAccessException;

}