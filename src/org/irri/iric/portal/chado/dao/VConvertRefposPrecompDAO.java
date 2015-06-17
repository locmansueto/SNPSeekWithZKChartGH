package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VConvertRefposPrecomp;
import org.irri.iric.portal.dao.MultipleReferenceConverterDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VConvertRefposPrecomp entities.
 * 
 */
public interface VConvertRefposPrecompDAO extends JpaDao<VConvertRefposPrecomp> ,  MultipleReferenceConverterDAO {

	/**
	 * JPQL Query - findVConvertRefposPrecompByFromPosition
	 *
	 */
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromPosition(Integer fromPosition) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByFromPosition
	 *
	 */
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromPosition(Integer fromPosition, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByFromContigContaining
	 *
	 */
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromContigContaining(String fromContig) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByFromContigContaining
	 *
	 */
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromContigContaining(String fromContig, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByToOrganismId
	 *
	 */
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByToOrganismId(java.math.BigDecimal toOrganismId) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByToOrganismId
	 *
	 */
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByToOrganismId(BigDecimal toOrganismId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByFromContig
	 *
	 */
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromContig(String fromContig_1) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByFromContig
	 *
	 */
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromContig(String fromContig_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByToPosition
	 *
	 */
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByToPosition(Integer toPosition) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByToPosition
	 *
	 */
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByToPosition(Integer toPosition, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByToContigContaining
	 *
	 */
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByToContigContaining(String toContig) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByToContigContaining
	 *
	 */
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByToContigContaining(String toContig, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByPrimaryKey
	 *
	 */
	public VConvertRefposPrecomp findVConvertRefposPrecompByPrimaryKey(Integer fromContigFeatureId, Integer fromPosition_1, Integer toContigFeatureId, Integer toPosition_1) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByPrimaryKey
	 *
	 */
	public VConvertRefposPrecomp findVConvertRefposPrecompByPrimaryKey(Integer fromContigFeatureId, Integer fromPosition_1, Integer toContigFeatureId, Integer toPosition_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByFromContigFeatureId
	 *
	 */
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromContigFeatureId(Integer fromContigFeatureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByFromContigFeatureId
	 *
	 */
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromContigFeatureId(Integer fromContigFeatureId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByFromOrganismId
	 *
	 */
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromOrganismId(java.math.BigDecimal fromOrganismId) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByFromOrganismId
	 *
	 */
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromOrganismId(BigDecimal fromOrganismId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByToRefcallContaining
	 *
	 */
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByToRefcallContaining(String toRefcall) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByToRefcallContaining
	 *
	 */
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByToRefcallContaining(String toRefcall, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByFromRefcallContaining
	 *
	 */
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromRefcallContaining(String fromRefcall) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByFromRefcallContaining
	 *
	 */
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromRefcallContaining(String fromRefcall, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByFromRefcall
	 *
	 */
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromRefcall(String fromRefcall_1) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByFromRefcall
	 *
	 */
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByFromRefcall(String fromRefcall_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByToContigFeatureId
	 *
	 */
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByToContigFeatureId(Integer toContigFeatureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByToContigFeatureId
	 *
	 */
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByToContigFeatureId(Integer toContigFeatureId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByToRefcall
	 *
	 */
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByToRefcall(String toRefcall_1) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByToRefcall
	 *
	 */
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByToRefcall(String toRefcall_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByToContig
	 *
	 */
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByToContig(String toContig_1) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertRefposPrecompByToContig
	 *
	 */
	public Set<VConvertRefposPrecomp> findVConvertRefposPrecompByToContig(String toContig_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVConvertRefposPrecomps
	 *
	 */
	public Set<VConvertRefposPrecomp> findAllVConvertRefposPrecomps() throws DataAccessException;

	/**
	 * JPQL Query - findAllVConvertRefposPrecomps
	 *
	 */
	public Set<VConvertRefposPrecomp> findAllVConvertRefposPrecomps(int startResult, int maxRows) throws DataAccessException;

}