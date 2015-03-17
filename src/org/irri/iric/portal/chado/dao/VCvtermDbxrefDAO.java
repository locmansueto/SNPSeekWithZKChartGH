package org.irri.iric.portal.chado.dao;

import java.util.Set;

import org.irri.iric.portal.chado.domain.VCvtermDbxref;
import org.irri.iric.portal.dao.CvTermDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VCvtermDbxref entities.
 * 
 */
public interface VCvtermDbxrefDAO extends JpaDao<VCvtermDbxref>, CvTermDAO {

	/**
	 * JPQL Query - findVCvtermDbxrefByDefinition
	 *
	 */
	public Set<VCvtermDbxref> findVCvtermDbxrefByDefinition(String definition) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermDbxrefByDefinition
	 *
	 */
	public Set<VCvtermDbxref> findVCvtermDbxrefByDefinition(String definition, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermDbxrefByPrimaryKey
	 *
	 */
	public VCvtermDbxref findVCvtermDbxrefByPrimaryKey(Integer cvtermId) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermDbxrefByPrimaryKey
	 *
	 */
	public VCvtermDbxref findVCvtermDbxrefByPrimaryKey(Integer cvtermId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermDbxrefByCvName
	 *
	 */
	public Set<VCvtermDbxref> findVCvtermDbxrefByCvName(String cvName) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermDbxrefByCvName
	 *
	 */
	public Set<VCvtermDbxref> findVCvtermDbxrefByCvName(String cvName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVCvtermDbxrefs
	 *
	 */
	public Set<VCvtermDbxref> findAllVCvtermDbxrefs() throws DataAccessException;

	/**
	 * JPQL Query - findAllVCvtermDbxrefs
	 *
	 */
	public Set<VCvtermDbxref> findAllVCvtermDbxrefs(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermDbxrefByCvtermId
	 *
	 */
	public VCvtermDbxref findVCvtermDbxrefByCvtermId(Integer cvtermId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermDbxrefByCvtermId
	 *
	 */
	public VCvtermDbxref findVCvtermDbxrefByCvtermId(Integer cvtermId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermDbxrefByName
	 *
	 */
	public Set<VCvtermDbxref> findVCvtermDbxrefByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermDbxrefByName
	 *
	 */
	public Set<VCvtermDbxref> findVCvtermDbxrefByName(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermDbxrefByAccessionContaining
	 *
	 */
	public Set<VCvtermDbxref> findVCvtermDbxrefByAccessionContaining(String accession) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermDbxrefByAccessionContaining
	 *
	 */
	public Set<VCvtermDbxref> findVCvtermDbxrefByAccessionContaining(String accession, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermDbxrefByNameContaining
	 *
	 */
	public Set<VCvtermDbxref> findVCvtermDbxrefByNameContaining(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermDbxrefByNameContaining
	 *
	 */
	public Set<VCvtermDbxref> findVCvtermDbxrefByNameContaining(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermDbxrefByDefinitionContaining
	 *
	 */
	public Set<VCvtermDbxref> findVCvtermDbxrefByDefinitionContaining(String definition_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermDbxrefByDefinitionContaining
	 *
	 */
	public Set<VCvtermDbxref> findVCvtermDbxrefByDefinitionContaining(String definition_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermDbxrefByCvNameContaining
	 *
	 */
	public Set<VCvtermDbxref> findVCvtermDbxrefByCvNameContaining(String cvName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermDbxrefByCvNameContaining
	 *
	 */
	public Set<VCvtermDbxref> findVCvtermDbxrefByCvNameContaining(String cvName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermDbxrefByAccession
	 *
	 */
	public Set<VCvtermDbxref> findVCvtermDbxrefByAccession(String accession_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvtermDbxrefByAccession
	 *
	 */
	public Set<VCvtermDbxref> findVCvtermDbxrefByAccession(String accession_1, int startResult, int maxRows) throws DataAccessException;

}