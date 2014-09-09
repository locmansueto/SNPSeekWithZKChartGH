package org.irri.iric.portal.genotype.dao;

import java.util.Set;

import org.irri.iric.portal.dao.VarietyDAO;
import org.irri.iric.portal.genotype.domain.VlVariety3k;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VlVariety3k entities.
 * 
 */
public interface VlVariety3kDAO extends JpaDao<VlVariety3k>, VarietyDAO {

	/**
	 * JPQL Query - findAllVlVariety3ks
	 *
	 */
	public Set<VlVariety3k> findAllVlVariety3ks() throws DataAccessException;

	/**
	 * JPQL Query - findAllVlVariety3ks
	 *
	 */
	public Set<VlVariety3k> findAllVlVariety3ks(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlVariety3kByNameContaining
	 *
	 */
	public Set<VlVariety3k> findVlVariety3kByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVlVariety3kByNameContaining
	 *
	 */
	public Set<VlVariety3k> findVlVariety3kByNameContaining(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlVariety3kByIrisUniqueIdContaining
	 *
	 */
	public Set<VlVariety3k> findVlVariety3kByIrisUniqueIdContaining(String irisUniqueId) throws DataAccessException;

	/**
	 * JPQL Query - findVlVariety3kByIrisUniqueIdContaining
	 *
	 */
	public Set<VlVariety3k> findVlVariety3kByIrisUniqueIdContaining(String irisUniqueId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlVariety3kByOriCountryContaining
	 *
	 */
	public Set<VlVariety3k> findVlVariety3kByOriCountryContaining(String oriCountry) throws DataAccessException;

	/**
	 * JPQL Query - findVlVariety3kByOriCountryContaining
	 *
	 */
	public Set<VlVariety3k> findVlVariety3kByOriCountryContaining(String oriCountry, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlVariety3kByPrimaryKey
	 *
	 */
	public VlVariety3k findVlVariety3kByPrimaryKey(Integer varId) throws DataAccessException;

	/**
	 * JPQL Query - findVlVariety3kByPrimaryKey
	 *
	 */
	public VlVariety3k findVlVariety3kByPrimaryKey(Integer varId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlVariety3kByIrisUniqueId
	 *
	 */
	public Set<VlVariety3k> findVlVariety3kByIrisUniqueId(String irisUniqueId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVlVariety3kByIrisUniqueId
	 *
	 */
	public Set<VlVariety3k> findVlVariety3kByIrisUniqueId(String irisUniqueId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlVariety3kByOriCountry
	 *
	 */
	public Set<VlVariety3k> findVlVariety3kByOriCountry(String oriCountry_1) throws DataAccessException;

	/**
	 * JPQL Query - findVlVariety3kByOriCountry
	 *
	 */
	public Set<VlVariety3k> findVlVariety3kByOriCountry(String oriCountry_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlVariety3kByVarId
	 *
	 */
	public VlVariety3k findVlVariety3kByVarId(Integer varId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVlVariety3kByVarId
	 *
	 */
	public VlVariety3k findVlVariety3kByVarId(Integer varId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlVariety3kByName
	 *
	 */
	public Set<VlVariety3k> findVlVariety3kByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVlVariety3kByName
	 *
	 */
	public Set<VlVariety3k> findVlVariety3kByName(String name_1, int startResult, int maxRows) throws DataAccessException;

}