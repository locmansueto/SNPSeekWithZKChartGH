package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.Set;

import org.irri.iric.portal.chado.postgres.domain.VSnpSpliceacceptor;
import org.irri.iric.portal.dao.SnpsSpliceAcceptorDAO;
import org.irri.iric.portal.domain.SnpsSpliceAcceptor;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VSnpSpliceacceptor entities.
 * 
 */
public interface VSnpSpliceacceptorDAO extends JpaDao<VSnpSpliceacceptor> , SnpsSpliceAcceptorDAO {

	/**
	 * JPQL Query - findVSnpSpliceacceptorByPrimaryKey
	 *
	 */
	public VSnpSpliceacceptor findVSnpSpliceacceptorByPrimaryKey(Long snpFeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSpliceacceptorByPrimaryKey
	 *
	 */
	public VSnpSpliceacceptor findVSnpSpliceacceptorByPrimaryKey(Long snpFeatureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSpliceacceptorBySrcfeatureId
	 *
	 */
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorBySrcfeatureId(Integer srcfeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSpliceacceptorBySrcfeatureId
	 *
	 */
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorBySrcfeatureId(Integer srcfeatureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpSpliceacceptors
	 *
	 */
	public Set<VSnpSpliceacceptor> findAllVSnpSpliceacceptors() throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpSpliceacceptors
	 *
	 */
	public Set<VSnpSpliceacceptor> findAllVSnpSpliceacceptors(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSpliceacceptorByChr
	 *
	 */
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByChr(Integer chr) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSpliceacceptorByChr
	 *
	 */
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByChr(Integer chr, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSpliceacceptorBySnpFeatureId
	 *
	 */
	public VSnpSpliceacceptor findVSnpSpliceacceptorBySnpFeatureId(Long snpFeatureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSpliceacceptorBySnpFeatureId
	 *
	 */
	public VSnpSpliceacceptor findVSnpSpliceacceptorBySnpFeatureId(Long snpFeatureId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSpliceacceptorByPosition
	 *
	 */
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByPosition(Integer position) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSpliceacceptorByPosition
	 *
	 */
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByPosition(Integer position, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSpliceacceptorByOrganismId
	 *
	 */
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByOrganismId(Integer organismId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpSpliceacceptorByOrganismId
	 *
	 */
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByOrganismId(Integer organismId, int startResult, int maxRows) throws DataAccessException;

	

}