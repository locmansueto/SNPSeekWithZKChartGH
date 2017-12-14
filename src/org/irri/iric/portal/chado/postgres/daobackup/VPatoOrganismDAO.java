package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.Set;

import org.irri.iric.portal.chado.postgres.domain.VPatoOrganism;
import org.irri.iric.portal.dao.CvTermDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VPatoOrganism entities.
 * 
 */
public interface VPatoOrganismDAO extends JpaDao<VPatoOrganism>, CvTermDAO {

	/**
	 * JPQL Query - findVPatoOrganismByCvName
	 *
	 */
	public Set<VPatoOrganism> findVPatoOrganismByCvName(String cvName) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByCvName
	 *
	 */
	public Set<VPatoOrganism> findVPatoOrganismByCvName(String cvName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByAccession
	 *
	 */
	public Set<VPatoOrganism> findVPatoOrganismByAccession(String accession) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByAccession
	 *
	 */
	public Set<VPatoOrganism> findVPatoOrganismByAccession(String accession, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByCvterm
	 *
	 */
	public Set<VPatoOrganism> findVPatoOrganismByCvterm(String cvterm) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByCvterm
	 *
	 */
	public Set<VPatoOrganism> findVPatoOrganismByCvterm(String cvterm, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByOrganismId
	 *
	 */
	public Set<VPatoOrganism> findVPatoOrganismByOrganismId(Long organismId) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByOrganismId
	 *
	 */
	public Set<VPatoOrganism> findVPatoOrganismByOrganismId(Long organismId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByPrimaryKey
	 *
	 */
	public VPatoOrganism findVPatoOrganismByPrimaryKey(Long cvtermId) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByPrimaryKey
	 *
	 */
	public VPatoOrganism findVPatoOrganismByPrimaryKey(Long cvtermId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByCommonNameContaining
	 *
	 */
	public Set<VPatoOrganism> findVPatoOrganismByCommonNameContaining(String commonName) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByCommonNameContaining
	 *
	 */
	public Set<VPatoOrganism> findVPatoOrganismByCommonNameContaining(String commonName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByCvNameContaining
	 *
	 */
	public Set<VPatoOrganism> findVPatoOrganismByCvNameContaining(String cvName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByCvNameContaining
	 *
	 */
	public Set<VPatoOrganism> findVPatoOrganismByCvNameContaining(String cvName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVPatoOrganisms
	 *
	 */
	public Set<VPatoOrganism> findAllVPatoOrganisms() throws DataAccessException;

	/**
	 * JPQL Query - findAllVPatoOrganisms
	 *
	 */
	public Set<VPatoOrganism> findAllVPatoOrganisms(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByCommonName
	 *
	 */
	public Set<VPatoOrganism> findVPatoOrganismByCommonName(String commonName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByCommonName
	 *
	 */
	public Set<VPatoOrganism> findVPatoOrganismByCommonName(String commonName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByCvtermId
	 *
	 */
	public VPatoOrganism findVPatoOrganismByCvtermId(Long cvtermId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByCvtermId
	 *
	 */
	public VPatoOrganism findVPatoOrganismByCvtermId(Long cvtermId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByAccessionContaining
	 *
	 */
	public Set<VPatoOrganism> findVPatoOrganismByAccessionContaining(String accession_1) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByAccessionContaining
	 *
	 */
	public Set<VPatoOrganism> findVPatoOrganismByAccessionContaining(String accession_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByCvtermContaining
	 *
	 */
	public Set<VPatoOrganism> findVPatoOrganismByCvtermContaining(String cvterm_1) throws DataAccessException;

	/**
	 * JPQL Query - findVPatoOrganismByCvtermContaining
	 *
	 */
	public Set<VPatoOrganism> findVPatoOrganismByCvtermContaining(String cvterm_1, int startResult, int maxRows) throws DataAccessException;

}