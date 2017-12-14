package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.Set;

import org.irri.iric.portal.chado.postgres.domain.VGoOrganism;
import org.irri.iric.portal.dao.CvTermDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VGoOrganism entities.
 * 
 */
public interface VGoOrganismDAO extends JpaDao<VGoOrganism>, CvTermDAO {

	/**
	 * JPQL Query - findVGoOrganismByAccessionContaining
	 *
	 */
	public Set<VGoOrganism> findVGoOrganismByAccessionContaining(String accession) throws DataAccessException;

	/**
	 * JPQL Query - findVGoOrganismByAccessionContaining
	 *
	 */
	public Set<VGoOrganism> findVGoOrganismByAccessionContaining(String accession, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGoOrganismByCvName
	 *
	 */
	public Set<VGoOrganism> findVGoOrganismByCvName(String cvName) throws DataAccessException;

	/**
	 * JPQL Query - findVGoOrganismByCvName
	 *
	 */
	public Set<VGoOrganism> findVGoOrganismByCvName(String cvName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGoOrganismByOrganismId
	 *
	 */
	public Set<VGoOrganism> findVGoOrganismByOrganismId(Long organismId) throws DataAccessException;

	/**
	 * JPQL Query - findVGoOrganismByOrganismId
	 *
	 */
	public Set<VGoOrganism> findVGoOrganismByOrganismId(Long organismId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGoOrganismByCommonNameContaining
	 *
	 */
	public Set<VGoOrganism> findVGoOrganismByCommonNameContaining(String commonName) throws DataAccessException;

	/**
	 * JPQL Query - findVGoOrganismByCommonNameContaining
	 *
	 */
	public Set<VGoOrganism> findVGoOrganismByCommonNameContaining(String commonName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVGoOrganisms
	 *
	 */
	public Set<VGoOrganism> findAllVGoOrganisms() throws DataAccessException;

	/**
	 * JPQL Query - findAllVGoOrganisms
	 *
	 */
	public Set<VGoOrganism> findAllVGoOrganisms(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGoOrganismByPrimaryKey
	 *
	 */
	public VGoOrganism findVGoOrganismByPrimaryKey(Long cvtermId) throws DataAccessException;

	/**
	 * JPQL Query - findVGoOrganismByPrimaryKey
	 *
	 */
	public VGoOrganism findVGoOrganismByPrimaryKey(Long cvtermId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGoOrganismByCvNameContaining
	 *
	 */
	public Set<VGoOrganism> findVGoOrganismByCvNameContaining(String cvName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGoOrganismByCvNameContaining
	 *
	 */
	public Set<VGoOrganism> findVGoOrganismByCvNameContaining(String cvName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGoOrganismByCvtermId
	 *
	 */
	public VGoOrganism findVGoOrganismByCvtermId(Long cvtermId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGoOrganismByCvtermId
	 *
	 */
	public VGoOrganism findVGoOrganismByCvtermId(Long cvtermId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGoOrganismByCommonName
	 *
	 */
	public Set<VGoOrganism> findVGoOrganismByCommonName(String commonName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGoOrganismByCommonName
	 *
	 */
	public Set<VGoOrganism> findVGoOrganismByCommonName(String commonName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGoOrganismByAccession
	 *
	 */
	public Set<VGoOrganism> findVGoOrganismByAccession(String accession_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGoOrganismByAccession
	 *
	 */
	public Set<VGoOrganism> findVGoOrganismByAccession(String accession_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGoOrganismByCvtermContaining
	 *
	 */
	public Set<VGoOrganism> findVGoOrganismByCvtermContaining(String cvterm) throws DataAccessException;

	/**
	 * JPQL Query - findVGoOrganismByCvtermContaining
	 *
	 */
	public Set<VGoOrganism> findVGoOrganismByCvtermContaining(String cvterm, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGoOrganismByCvterm
	 *
	 */
	public Set<VGoOrganism> findVGoOrganismByCvterm(String cvterm_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGoOrganismByCvterm
	 *
	 */
	public Set<VGoOrganism> findVGoOrganismByCvterm(String cvterm_1, int startResult, int maxRows) throws DataAccessException;

}