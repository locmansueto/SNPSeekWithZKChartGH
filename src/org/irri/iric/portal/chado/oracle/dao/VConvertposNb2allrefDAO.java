package org.irri.iric.portal.chado.oracle.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.portal.chado.oracle.domain.VConvertposNb2allref;
import org.irri.iric.portal.dao.MultipleReferenceConverterDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VConvertposNb2allref entities.
 * 
 */
public interface VConvertposNb2allrefDAO extends JpaDao<VConvertposNb2allref> ,  MultipleReferenceConverterDAO {

	/**
	 * JPQL Query - findVConvertposNb2allrefByToRefcallContaining
	 *
	 */
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByToRefcallContaining(String toRefcall) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefByToRefcallContaining
	 *
	 */
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByToRefcallContaining(String toRefcall, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefByToRefcall
	 *
	 */
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByToRefcall(String toRefcall_1) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefByToRefcall
	 *
	 */
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByToRefcall(String toRefcall_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefByToContigId
	 *
	 */
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByToContigId(BigDecimal toContigId) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefByToContigId
	 *
	 */
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByToContigId(BigDecimal toContigId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefByToOrganismId
	 *
	 */
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByToOrganismId(java.math.BigDecimal toOrganismId) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefByToOrganismId
	 *
	 */
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByToOrganismId(BigDecimal toOrganismId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefByFromRefcall
	 *
	 */
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByFromRefcall(String fromRefcall) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefByFromRefcall
	 *
	 */
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByFromRefcall(String fromRefcall, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefByFromPosition
	 *
	 */
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByFromPosition(BigDecimal fromPosition) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefByFromPosition
	 *
	 */
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByFromPosition(BigDecimal fromPosition, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefByPrimaryKey
	 *
	 */
	public VConvertposNb2allref findVConvertposNb2allrefByPrimaryKey(BigDecimal snpFeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefByPrimaryKey
	 *
	 */
	public VConvertposNb2allref findVConvertposNb2allrefByPrimaryKey(BigDecimal snpFeatureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefByFromOrganismId
	 *
	 */
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByFromOrganismId(BigDecimal fromOrganismId) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefByFromOrganismId
	 *
	 */
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByFromOrganismId(BigDecimal fromOrganismId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefByFromContigId
	 *
	 */
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByFromContigId(BigDecimal fromContigId) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefByFromContigId
	 *
	 */
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByFromContigId(BigDecimal fromContigId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefBySnpFeatureId
	 *
	 */
	public VConvertposNb2allref findVConvertposNb2allrefBySnpFeatureId(BigDecimal snpFeatureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefBySnpFeatureId
	 *
	 */
	public VConvertposNb2allref findVConvertposNb2allrefBySnpFeatureId(BigDecimal snpFeatureId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefByFromRefcallContaining
	 *
	 */
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByFromRefcallContaining(String fromRefcall_1) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefByFromRefcallContaining
	 *
	 */
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByFromRefcallContaining(String fromRefcall_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVConvertposNb2allrefs
	 *
	 */
	public Set<VConvertposNb2allref> findAllVConvertposNb2allrefs() throws DataAccessException;

	/**
	 * JPQL Query - findAllVConvertposNb2allrefs
	 *
	 */
	public Set<VConvertposNb2allref> findAllVConvertposNb2allrefs(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefByToPosition
	 *
	 */
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByToPosition(BigDecimal toPosition) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefByToPosition
	 *
	 */
	public Set<VConvertposNb2allref> findVConvertposNb2allrefByToPosition(BigDecimal toPosition, int startResult, int maxRows) throws DataAccessException;

}