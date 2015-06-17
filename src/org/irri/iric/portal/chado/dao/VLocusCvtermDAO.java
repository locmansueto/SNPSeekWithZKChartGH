package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VLocusCvterm;
import org.irri.iric.portal.dao.CvTermDAO;
import org.irri.iric.portal.dao.LocusDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VLocusCvterm entities.
 * 
 */
public interface VLocusCvtermDAO extends JpaDao<VLocusCvterm>, LocusDAO, CvTermDAO  {

	/**
	 * JPQL Query - findVLocusCvtermByContigName
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByContigName(String contigName) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByContigName
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByContigName(String contigName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByNameContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByNameContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByNameContaining(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByFeatureId
	 *
	 */
	public VLocusCvterm findVLocusCvtermByFeatureId(Integer featureId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByFeatureId
	 *
	 */
	public VLocusCvterm findVLocusCvtermByFeatureId(Integer featureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByName
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByName
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByName(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByContigId
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByContigId(java.math.BigDecimal contigId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByContigId
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByContigId(BigDecimal contigId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByStrand
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByStrand(java.math.BigDecimal strand) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByStrand
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByStrand(BigDecimal strand, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByFmax
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByFmax(java.math.BigDecimal fmax) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByFmax
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByFmax(BigDecimal fmax, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByCvName
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByCvName(String cvName) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByCvName
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByCvName(String cvName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByContigNameContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByContigNameContaining(String contigName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByContigNameContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByContigNameContaining(String contigName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByCvterm
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByCvterm(String cvterm) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByCvterm
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByCvterm(String cvterm, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByCvNameContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByCvNameContaining(String cvName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByCvNameContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByCvNameContaining(String cvName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByFmin
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByFmin(java.math.BigDecimal fmin) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByFmin
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByFmin(BigDecimal fmin, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByAccessionContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByAccessionContaining(String accession) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByAccessionContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByAccessionContaining(String accession, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByOrganismId
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByOrganismId(java.math.BigDecimal organismId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByOrganismId
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByOrganismId(BigDecimal organismId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByCvtermId
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByCvtermId(java.math.BigDecimal cvtermId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByCvtermId
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByCvtermId(BigDecimal cvtermId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByCommonNameContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByCommonNameContaining(String commonName) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByCommonNameContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByCommonNameContaining(String commonName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByPrimaryKey
	 *
	 */
	public VLocusCvterm findVLocusCvtermByPrimaryKey(Integer featureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByPrimaryKey
	 *
	 */
	public VLocusCvterm findVLocusCvtermByPrimaryKey(Integer featureId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByAccession
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByAccession(String accession_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByAccession
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByAccession(String accession_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByCommonName
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByCommonName(String commonName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByCommonName
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByCommonName(String commonName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVLocusCvterms
	 *
	 */
	public Set<VLocusCvterm> findAllVLocusCvterms() throws DataAccessException;

	/**
	 * JPQL Query - findAllVLocusCvterms
	 *
	 */
	public Set<VLocusCvterm> findAllVLocusCvterms(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByCvtermContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByCvtermContaining(String cvterm_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByCvtermContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByCvtermContaining(String cvterm_1, int startResult, int maxRows) throws DataAccessException;

	//List getAllTerms(String organism);

}