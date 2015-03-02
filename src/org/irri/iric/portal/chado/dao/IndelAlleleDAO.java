package org.irri.iric.portal.chado.dao;

import java.util.Map;
import java.util.Set;

import org.irri.iric.portal.chado.domain.IndelAllele;
import org.irri.iric.portal.dao.IndelsAllvarsPosDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage IndelAllele entities.
 * 
 */
public interface IndelAlleleDAO extends JpaDao<IndelAllele> , IndelsAllvarsPosDAO {

	/**
	 * JPQL Query - findAllIndelAlleles
	 *
	 */
	public Set<IndelAllele> findAllIndelAlleles() throws DataAccessException;

	/**
	 * JPQL Query - findAllIndelAlleles
	 *
	 */
	public Set<IndelAllele> findAllIndelAlleles(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIndelAlleleByInsStrContaining
	 *
	 */
	public Set<IndelAllele> findIndelAlleleByInsStrContaining(String insStr) throws DataAccessException;

	/**
	 * JPQL Query - findIndelAlleleByInsStrContaining
	 *
	 */
	public Set<IndelAllele> findIndelAlleleByInsStrContaining(String insStr, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIndelAlleleBySrcFeatureId
	 *
	 */
	public Set<IndelAllele> findIndelAlleleBySrcFeatureId(Integer srcFeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findIndelAlleleBySrcFeatureId
	 *
	 */
	public Set<IndelAllele> findIndelAlleleBySrcFeatureId(Integer srcFeatureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIndelAlleleByPos
	 *
	 */
	public Set<IndelAllele> findIndelAlleleByPos(Integer pos) throws DataAccessException;

	/**
	 * JPQL Query - findIndelAlleleByPos
	 *
	 */
	public Set<IndelAllele> findIndelAlleleByPos(Integer pos, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIndelAlleleByInsStr
	 *
	 */
	public Set<IndelAllele> findIndelAlleleByInsStr(String insStr_1) throws DataAccessException;

	/**
	 * JPQL Query - findIndelAlleleByInsStr
	 *
	 */
	public Set<IndelAllele> findIndelAlleleByInsStr(String insStr_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIndelAlleleByDelLen
	 *
	 */
	public Set<IndelAllele> findIndelAlleleByDelLen(Integer delLen) throws DataAccessException;

	/**
	 * JPQL Query - findIndelAlleleByDelLen
	 *
	 */
	public Set<IndelAllele> findIndelAlleleByDelLen(Integer delLen, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIndelAlleleByMethod
	 *
	 */
	public Set<IndelAllele> findIndelAlleleByMethod(Integer method) throws DataAccessException;

	/**
	 * JPQL Query - findIndelAlleleByMethod
	 *
	 */
	public Set<IndelAllele> findIndelAlleleByMethod(Integer method, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIndelAlleleByIndelId
	 *
	 */
	public IndelAllele findIndelAlleleByIndelId(Integer indelId) throws DataAccessException;

	/**
	 * JPQL Query - findIndelAlleleByIndelId
	 *
	 */
	public IndelAllele findIndelAlleleByIndelId(Integer indelId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIndelAlleleByPrimaryKey
	 *
	 */
	public IndelAllele findIndelAlleleByPrimaryKey(Integer indelId_1) throws DataAccessException;

	/**
	 * JPQL Query - findIndelAlleleByPrimaryKey
	 *
	 */
	public IndelAllele findIndelAlleleByPrimaryKey(Integer indelId_1, int startResult, int maxRows) throws DataAccessException;

	

}