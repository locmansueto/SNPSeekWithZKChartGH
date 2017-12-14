package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.postgres.domain.VCvPassport;
import org.irri.iric.portal.dao.CvTermDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VCvPassport entities.
 * 
 */
public interface VCvPassportDAO extends JpaDao<VCvPassport>, CvTermDAO  {

	/**
	 * JPQL Query - findVCvPassportByCvtermId
	 *
	 */
	public VCvPassport findVCvPassportByCvtermId(Integer cvtermId) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPassportByCvtermId
	 *
	 */
	public VCvPassport findVCvPassportByCvtermId(Integer cvtermId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVCvPassports
	 *
	 */
	public List<VCvPassport> findAllVCvPassports() throws DataAccessException;

	/**
	 * JPQL Query - findAllVCvPassports
	 *
	 */
	public List<VCvPassport> findAllVCvPassports(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPassportByPrimaryKey
	 *
	 */
	public VCvPassport findVCvPassportByPrimaryKey(Integer cvtermId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPassportByPrimaryKey
	 *
	 */
	public VCvPassport findVCvPassportByPrimaryKey(Integer cvtermId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPassportByName
	 *
	 */
	public Set<VCvPassport> findVCvPassportByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPassportByName
	 *
	 */
	public Set<VCvPassport> findVCvPassportByName(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPassportByDefinition
	 *
	 */
	public Set<VCvPassport> findVCvPassportByDefinition(String definition) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPassportByDefinition
	 *
	 */
	public Set<VCvPassport> findVCvPassportByDefinition(String definition, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPassportByDefinitionContaining
	 *
	 */
	public Set<VCvPassport> findVCvPassportByDefinitionContaining(String definition_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPassportByDefinitionContaining
	 *
	 */
	public Set<VCvPassport> findVCvPassportByDefinitionContaining(String definition_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPassportByNameContaining
	 *
	 */
	public Set<VCvPassport> findVCvPassportByNameContaining(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPassportByNameContaining
	 *
	 */
	public Set<VCvPassport> findVCvPassportByNameContaining(String name_1, int startResult, int maxRows) throws DataAccessException;

}