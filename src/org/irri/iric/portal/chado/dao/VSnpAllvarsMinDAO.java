package org.irri.iric.portal.chado.dao;

import java.util.Set;

import org.irri.iric.portal.chado.domain.VSnpAllvarsMin;
import org.irri.iric.portal.dao.SnpsAllvarsDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VSnpAllvarsMin entities.
 * 
 */
public interface VSnpAllvarsMinDAO extends JpaDao<VSnpAllvarsMin>, SnpsAllvarsDAO {

	/**
	 * JPQL Query - findVSnpAllvarsMinByPrimaryKey
	 *
	 */
	public VSnpAllvarsMin findVSnpAllvarsMinByPrimaryKey(Integer var, Integer pos, Integer chr) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsMinByPrimaryKey
	 *
	 */
	public VSnpAllvarsMin findVSnpAllvarsMinByPrimaryKey(Integer var, Integer pos, Integer chr, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsMinByPos
	 *
	 */
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByPos(Integer pos_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsMinByPos
	 *
	 */
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByPos(Integer pos_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsMinByVarnuc
	 *
	 */
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByVarnuc(String varnuc) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsMinByVarnuc
	 *
	 */
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByVarnuc(String varnuc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsMinByVarnucContaining
	 *
	 */
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByVarnucContaining(String varnuc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsMinByVarnucContaining
	 *
	 */
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByVarnucContaining(String varnuc_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsMinByVar
	 *
	 */
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByVar(Integer var_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsMinByVar
	 *
	 */
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByVar(Integer var_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpAllvarsMins
	 *
	 */
	public Set<VSnpAllvarsMin> findAllVSnpAllvarsMins() throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpAllvarsMins
	 *
	 */
	public Set<VSnpAllvarsMin> findAllVSnpAllvarsMins(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsMinByRefnuc
	 *
	 */
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByRefnuc(String refnuc) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsMinByRefnuc
	 *
	 */
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByRefnuc(String refnuc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsMinByChr
	 *
	 */
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByChr(Integer chr_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsMinByChr
	 *
	 */
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByChr(Integer chr_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsMinByRefnucContaining
	 *
	 */
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByRefnucContaining(String refnuc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpAllvarsMinByRefnucContaining
	 *
	 */
	public Set<VSnpAllvarsMin> findVSnpAllvarsMinByRefnucContaining(String refnuc_1, int startResult, int maxRows) throws DataAccessException;

}