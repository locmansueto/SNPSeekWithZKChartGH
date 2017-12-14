package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.Set;

import org.irri.iric.portal.chado.postgres.domain.VSnpRefposindex;
import org.irri.iric.portal.dao.SnpsAllvarsPosDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VSnpRefposindex entities.
 * 
 */
public interface VSnpRefposindexDAO extends JpaDao<VSnpRefposindex> , SnpsAllvarsPosDAO {

	/**
	 * JPQL Query - findVSnpRefposindexByPosition
	 *
	 */
	public Set<VSnpRefposindex> findVSnpRefposindexByPosition(Integer position) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexByPosition
	 *
	 */
	public Set<VSnpRefposindex> findVSnpRefposindexByPosition(Integer position, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexByTypeId
	 *
	 */
	public Set<VSnpRefposindex> findVSnpRefposindexByTypeId(Integer typeId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexByTypeId
	 *
	 */
	public Set<VSnpRefposindex> findVSnpRefposindexByTypeId(Integer typeId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexByChromosome
	 *
	 */
	public Set<VSnpRefposindex> findVSnpRefposindexByChromosome(Integer chromosome) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexByChromosome
	 *
	 */
	public Set<VSnpRefposindex> findVSnpRefposindexByChromosome(Integer chromosome, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexByRefcallContaining
	 *
	 */
	public Set<VSnpRefposindex> findVSnpRefposindexByRefcallContaining(String refcall) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexByRefcallContaining
	 *
	 */
	public Set<VSnpRefposindex> findVSnpRefposindexByRefcallContaining(String refcall, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexByPrimaryKey
	 *
	 */
	public VSnpRefposindex findVSnpRefposindexByPrimaryKey(Long snpFeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexByPrimaryKey
	 *
	 */
	public VSnpRefposindex findVSnpRefposindexByPrimaryKey(Long snpFeatureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexByRefcall
	 *
	 */
	public Set<VSnpRefposindex> findVSnpRefposindexByRefcall(String refcall_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexByRefcall
	 *
	 */
	public Set<VSnpRefposindex> findVSnpRefposindexByRefcall(String refcall_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpRefposindexs
	 *
	 */
	public Set<VSnpRefposindex> findAllVSnpRefposindexs() throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpRefposindexs
	 *
	 */
	public Set<VSnpRefposindex> findAllVSnpRefposindexs(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexBySnpFeatureId
	 *
	 */
	public VSnpRefposindex findVSnpRefposindexBySnpFeatureId(Long snpFeatureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexBySnpFeatureId
	 *
	 */
	public VSnpRefposindex findVSnpRefposindexBySnpFeatureId(Long snpFeatureId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexByAlleleIndex
	 *
	 */
	public Set<VSnpRefposindex> findVSnpRefposindexByAlleleIndex(Integer alleleIndex) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposindexByAlleleIndex
	 *
	 */
	public Set<VSnpRefposindex> findVSnpRefposindexByAlleleIndex(Integer alleleIndex, int startResult, int maxRows) throws DataAccessException;

}