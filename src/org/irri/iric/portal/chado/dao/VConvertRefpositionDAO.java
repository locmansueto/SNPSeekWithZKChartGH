package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VConvertRefposition;
import org.irri.iric.portal.dao.MultipleReferenceConverterDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VConvertRefposition entities.
 * 
 */
public interface VConvertRefpositionDAO extends JpaDao<VConvertRefposition>, MultipleReferenceConverterDAO {

	/**
	 * JPQL Query - findVConvertRefpositionByFromPosition
	 *
	 */
	public Set<VConvertRefposition> findVConvertRefpositionByFromPosition(Integer fromPosition) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefpositionByFromPosition
	 *
	 */
	public Set<VConvertRefposition> findVConvertRefpositionByFromPosition(Integer fromPosition, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefpositionByToContigContaining
	 *
	 */
	public Set<VConvertRefposition> findVConvertRefpositionByToContigContaining(String toContig) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefpositionByToContigContaining
	 *
	 */
	public Set<VConvertRefposition> findVConvertRefpositionByToContigContaining(String toContig, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefpositionByToContig
	 *
	 */
	public Set<VConvertRefposition> findVConvertRefpositionByToContig(String toContig_1) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefpositionByToContig
	 *
	 */
	public Set<VConvertRefposition> findVConvertRefpositionByToContig(String toContig_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefpositionByToContigFeatureId
	 *
	 */
	public Set<VConvertRefposition> findVConvertRefpositionByToContigFeatureId(java.math.BigDecimal toContigFeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefpositionByToContigFeatureId
	 *
	 */
	public Set<VConvertRefposition> findVConvertRefpositionByToContigFeatureId(BigDecimal toContigFeatureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVConvertRefpositions
	 *
	 */
	public Set<VConvertRefposition> findAllVConvertRefpositions() throws DataAccessException;

	/**
	 * JPQL Query - findAllVConvertRefpositions
	 *
	 */
	public Set<VConvertRefposition> findAllVConvertRefpositions(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefpositionByFromOrganismId
	 *
	 */
	public Set<VConvertRefposition> findVConvertRefpositionByFromOrganismId(Integer fromOrganismId) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefpositionByFromOrganismId
	 *
	 */
	public Set<VConvertRefposition> findVConvertRefpositionByFromOrganismId(Integer fromOrganismId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefpositionByFromContig
	 *
	 */
	public Set<VConvertRefposition> findVConvertRefpositionByFromContig(String fromContig) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefpositionByFromContig
	 *
	 */
	public Set<VConvertRefposition> findVConvertRefpositionByFromContig(String fromContig, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefpositionByFromContigFeatureId
	 *
	 */
	public Set<VConvertRefposition> findVConvertRefpositionByFromContigFeatureId(Integer fromContigFeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefpositionByFromContigFeatureId
	 *
	 */
	public Set<VConvertRefposition> findVConvertRefpositionByFromContigFeatureId(Integer fromContigFeatureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefpositionByFromContigContaining
	 *
	 */
	public Set<VConvertRefposition> findVConvertRefpositionByFromContigContaining(String fromContig_1) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefpositionByFromContigContaining
	 *
	 */
	public Set<VConvertRefposition> findVConvertRefpositionByFromContigContaining(String fromContig_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefpositionByPrimaryKey
	 *
	 */
	public VConvertRefposition findVConvertRefpositionByPrimaryKey(Integer fromOrganismId_1, Integer fromContigFeatureId_1, Integer fromPosition_1) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefpositionByPrimaryKey
	 *
	 */
	public VConvertRefposition findVConvertRefpositionByPrimaryKey(Integer fromOrganismId_1, Integer fromContigFeatureId_1, Integer fromPosition_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefpositionByToOrganismId
	 *
	 */
	public Set<VConvertRefposition> findVConvertRefpositionByToOrganismId(java.math.BigDecimal toOrganismId) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefpositionByToOrganismId
	 *
	 */
	public Set<VConvertRefposition> findVConvertRefpositionByToOrganismId(BigDecimal toOrganismId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefpositionByToPosition
	 *
	 */
	public Set<VConvertRefposition> findVConvertRefpositionByToPosition(java.math.BigDecimal toPosition) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefpositionByToPosition
	 *
	 */
	public Set<VConvertRefposition> findVConvertRefpositionByToPosition(BigDecimal toPosition, int startResult, int maxRows) throws DataAccessException;

}