package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.Set;

import org.irri.iric.portal.chado.postgres.domain.VSnpSplicedonor;
import org.irri.iric.portal.dao.SnpsSpliceDonorDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VSnpSplicedonor entities.
 * 
 */
public interface VSnpSplicedonorDAO extends JpaDao<VSnpSplicedonor>, SnpsSpliceDonorDAO {

	/**
	 * JPQL Query - findVSnpSplicedonorBySrcfeatureId
	 *
	 */
	public Set<VSnpSplicedonor> findVSnpSplicedonorBySrcfeatureId(Integer srcfeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSplicedonorBySrcfeatureId
	 *
	 */
	public Set<VSnpSplicedonor> findVSnpSplicedonorBySrcfeatureId(Integer srcfeatureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSplicedonorBySnpFeatureId
	 *
	 */
	public VSnpSplicedonor findVSnpSplicedonorBySnpFeatureId(Long snpFeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSplicedonorBySnpFeatureId
	 *
	 */
	public VSnpSplicedonor findVSnpSplicedonorBySnpFeatureId(Long snpFeatureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSplicedonorByChr
	 *
	 */
	public Set<VSnpSplicedonor> findVSnpSplicedonorByChr(Integer chr) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSplicedonorByChr
	 *
	 */
	public Set<VSnpSplicedonor> findVSnpSplicedonorByChr(Integer chr, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpSplicedonors
	 *
	 */
	public Set<VSnpSplicedonor> findAllVSnpSplicedonors() throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpSplicedonors
	 *
	 */
	public Set<VSnpSplicedonor> findAllVSnpSplicedonors(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSplicedonorByOrganismId
	 *
	 */
	public Set<VSnpSplicedonor> findVSnpSplicedonorByOrganismId(Integer organismId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSplicedonorByOrganismId
	 *
	 */
	public Set<VSnpSplicedonor> findVSnpSplicedonorByOrganismId(Integer organismId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSplicedonorByPosition
	 *
	 */
	public Set<VSnpSplicedonor> findVSnpSplicedonorByPosition(Integer position) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSplicedonorByPosition
	 *
	 */
	public Set<VSnpSplicedonor> findVSnpSplicedonorByPosition(Integer position, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSplicedonorByPrimaryKey
	 *
	 */
	public VSnpSplicedonor findVSnpSplicedonorByPrimaryKey(Long snpFeatureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSplicedonorByPrimaryKey
	 *
	 */
	public VSnpSplicedonor findVSnpSplicedonorByPrimaryKey(Long snpFeatureId_1, int startResult, int maxRows) throws DataAccessException;

}