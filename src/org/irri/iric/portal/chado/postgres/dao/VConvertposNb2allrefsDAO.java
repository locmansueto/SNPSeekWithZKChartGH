package org.irri.iric.portal.chado.oracle.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.portal.chado.oracle.domain.VConvertposNb2allrefs;
import org.irri.iric.portal.dao.MultipleReferenceConverterDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VConvertposNb2allrefs entities.
 * 
 */
public interface VConvertposNb2allrefsDAO extends JpaDao<VConvertposNb2allrefs>, MultipleReferenceConverterDAO {


	/**
	 * JPQL Query - findVConvertposNb2allrefsByFromContigId
	 *
	 */
	public Set<VConvertposNb2allrefs> findVConvertposNb2allrefsByFromContigId(BigDecimal fromContigId) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefsByFromContigId
	 *
	 */
	public Set<VConvertposNb2allrefs> findVConvertposNb2allrefsByFromContigId(BigDecimal fromContigId, int startResult, int maxRows) throws DataAccessException;


	/**
	 * JPQL Query - findVConvertposNb2allrefsBySnpFeatureId
	 *
	 */
	public VConvertposNb2allrefs findVConvertposNb2allrefsBySnpFeatureId(BigDecimal snpFeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefsBySnpFeatureId
	 *
	 */
	public VConvertposNb2allrefs findVConvertposNb2allrefsBySnpFeatureId(BigDecimal snpFeatureId, int startResult, int maxRows) throws DataAccessException;


	/**
	 * JPQL Query - findVConvertposNb2allrefsByFromPosition
	 *
	 */
	public Set<VConvertposNb2allrefs> findVConvertposNb2allrefsByFromPosition(BigDecimal fromPosition) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefsByFromPosition
	 *
	 */
	public Set<VConvertposNb2allrefs> findVConvertposNb2allrefsByFromPosition(BigDecimal fromPosition, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefsByFromRefcall
	 *
	 */
	public Set<VConvertposNb2allrefs> findVConvertposNb2allrefsByFromRefcall(String fromRefcall) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefsByFromRefcall
	 *
	 */
	public Set<VConvertposNb2allrefs> findVConvertposNb2allrefsByFromRefcall(String fromRefcall, int startResult, int maxRows) throws DataAccessException;


	/**
	 * JPQL Query - findVConvertposNb2allrefsByPrimaryKey
	 *
	 */
	public VConvertposNb2allrefs findVConvertposNb2allrefsByPrimaryKey(BigDecimal snpFeatureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefsByPrimaryKey
	 *
	 */
	public VConvertposNb2allrefs findVConvertposNb2allrefsByPrimaryKey(BigDecimal snpFeatureId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefsByFromRefcallContaining
	 *
	 */
	public Set<VConvertposNb2allrefs> findVConvertposNb2allrefsByFromRefcallContaining(String fromRefcall_1) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefsByFromRefcallContaining
	 *
	 */
	public Set<VConvertposNb2allrefs> findVConvertposNb2allrefsByFromRefcallContaining(String fromRefcall_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefsByFromOrganismId
	 *
	 */
	public Set<VConvertposNb2allrefs> findVConvertposNb2allrefsByFromOrganismId(java.math.BigDecimal fromOrganismId) throws DataAccessException;

	/**
	 * JPQL Query - findVConvertposNb2allrefsByFromOrganismId
	 *
	 */
	public Set<VConvertposNb2allrefs> findVConvertposNb2allrefsByFromOrganismId(BigDecimal fromOrganismId, int startResult, int maxRows) throws DataAccessException;


	/**
	 * JPQL Query - findAllVConvertposNb2allrefss
	 *
	 */
	public Set<VConvertposNb2allrefs> findAllVConvertposNb2allrefss() throws DataAccessException;

	/**
	 * JPQL Query - findAllVConvertposNb2allrefss
	 *
	 */
	public Set<VConvertposNb2allrefs> findAllVConvertposNb2allrefss(int startResult, int maxRows) throws DataAccessException;


}