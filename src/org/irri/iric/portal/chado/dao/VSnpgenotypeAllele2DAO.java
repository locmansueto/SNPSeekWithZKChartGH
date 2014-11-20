package org.irri.iric.portal.chado.dao;

import java.util.Set;

import org.irri.iric.portal.chado.domain.VSnpgenotypeAllele2;
import org.irri.iric.portal.dao.SnpsHeteroAllvarsDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VSnpgenotypeAllele2 entities.
 * 
 */
public interface VSnpgenotypeAllele2DAO extends JpaDao<VSnpgenotypeAllele2>, SnpsHeteroAllvarsDAO {

	/**
	 * JPQL Query - findVSnpgenotypeAllele2BySnpFeatureId
	 *
	 */
	public Set<VSnpgenotypeAllele2> findVSnpgenotypeAllele2BySnpFeatureId(Integer snpFeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpgenotypeAllele2BySnpFeatureId
	 *
	 */
	public Set<VSnpgenotypeAllele2> findVSnpgenotypeAllele2BySnpFeatureId(Integer snpFeatureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpgenotypeAllele2ByPrimaryKey
	 *
	 */
	public VSnpgenotypeAllele2 findVSnpgenotypeAllele2ByPrimaryKey(Integer snpFeatureId_1, Integer iricStockId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpgenotypeAllele2ByPrimaryKey
	 *
	 */
	public VSnpgenotypeAllele2 findVSnpgenotypeAllele2ByPrimaryKey(Integer snpFeatureId_1, Integer iricStockId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpgenotypeAllele2ByAllele2Containing
	 *
	 */
	public Set<VSnpgenotypeAllele2> findVSnpgenotypeAllele2ByAllele2Containing(String allele2) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpgenotypeAllele2ByAllele2Containing
	 *
	 */
	public Set<VSnpgenotypeAllele2> findVSnpgenotypeAllele2ByAllele2Containing(String allele2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpgenotypeAllele2s
	 *
	 */
	public Set<VSnpgenotypeAllele2> findAllVSnpgenotypeAllele2s() throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpgenotypeAllele2s
	 *
	 */
	public Set<VSnpgenotypeAllele2> findAllVSnpgenotypeAllele2s(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpgenotypeAllele2ByAllele2
	 *
	 */
	public Set<VSnpgenotypeAllele2> findVSnpgenotypeAllele2ByAllele2(String allele2_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpgenotypeAllele2ByAllele2
	 *
	 */
	public Set<VSnpgenotypeAllele2> findVSnpgenotypeAllele2ByAllele2(String allele2_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpgenotypeAllele2ByIricStockId
	 *
	 */
	public Set<VSnpgenotypeAllele2> findVSnpgenotypeAllele2ByIricStockId(Integer iricStockId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpgenotypeAllele2ByIricStockId
	 *
	 */
	public Set<VSnpgenotypeAllele2> findVSnpgenotypeAllele2ByIricStockId(Integer iricStockId_1, int startResult, int maxRows) throws DataAccessException;

}