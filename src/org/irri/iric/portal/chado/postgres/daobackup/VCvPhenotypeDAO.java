package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.postgres.domain.VCvPhenotype;
import org.irri.iric.portal.dao.CvTermDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VCvPhenotype entities.
 * 
 */
public interface VCvPhenotypeDAO extends JpaDao<VCvPhenotype> , CvTermDAO {

	/**
	 * JPQL Query - findVCvPhenotypeByPrimaryKey
	 *
	 */
	public VCvPhenotype findVCvPhenotypeByPrimaryKey(Integer cvtermId) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByPrimaryKey
	 *
	 */
	public VCvPhenotype findVCvPhenotypeByPrimaryKey(Integer cvtermId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByNameContaining
	 *
	 */
	public Set<VCvPhenotype> findVCvPhenotypeByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByNameContaining
	 *
	 */
	public Set<VCvPhenotype> findVCvPhenotypeByNameContaining(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVCvPhenotypes
	 *
	 */
	public List<VCvPhenotype> findAllVCvPhenotypes() throws DataAccessException;

	/**
	 * JPQL Query - findAllVCvPhenotypes
	 *
	 */
	public List<VCvPhenotype> findAllVCvPhenotypes(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByDefinitionContaining
	 *
	 */
	public Set<VCvPhenotype> findVCvPhenotypeByDefinitionContaining(String definition) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByDefinitionContaining
	 *
	 */
	public Set<VCvPhenotype> findVCvPhenotypeByDefinitionContaining(String definition, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByName
	 *
	 */
	public Set<VCvPhenotype> findVCvPhenotypeByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByName
	 *
	 */
	public Set<VCvPhenotype> findVCvPhenotypeByName(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByDefinition
	 *
	 */
	public Set<VCvPhenotype> findVCvPhenotypeByDefinition(String definition_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByDefinition
	 *
	 */
	public Set<VCvPhenotype> findVCvPhenotypeByDefinition(String definition_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByCvtermId
	 *
	 */
	public VCvPhenotype findVCvPhenotypeByCvtermId(Integer cvtermId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVCvPhenotypeByCvtermId
	 *
	 */
	public VCvPhenotype findVCvPhenotypeByCvtermId(Integer cvtermId_1, int startResult, int maxRows) throws DataAccessException;

}