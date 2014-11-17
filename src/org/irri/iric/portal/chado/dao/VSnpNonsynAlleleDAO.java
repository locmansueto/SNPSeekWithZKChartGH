package org.irri.iric.portal.chado.dao;

import java.util.Set;

import org.irri.iric.portal.chado.domain.VSnpNonsynAllele;
import org.irri.iric.portal.dao.SnpsNonsynAllvarsDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VSnpNonsynAllele entities.
 * 
 */
public interface VSnpNonsynAlleleDAO extends JpaDao<VSnpNonsynAllele>, SnpsNonsynAllvarsDAO {

	/**
	 * JPQL Query - findVSnpNonsynAlleleByPrimaryKey
	 *
	 */
	public VSnpNonsynAllele findVSnpNonsynAlleleByPrimaryKey(Integer snpFeatureId, String nonSynAllele) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpNonsynAlleleByPrimaryKey
	 *
	 */
	public VSnpNonsynAllele findVSnpNonsynAlleleByPrimaryKey(Integer snpFeatureId, String nonSynAllele, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpNonsynAlleles
	 *
	 */
	public Set<VSnpNonsynAllele> findAllVSnpNonsynAlleles() throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpNonsynAlleles
	 *
	 */
	public Set<VSnpNonsynAllele> findAllVSnpNonsynAlleles(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpNonsynAlleleByNonSynAllele
	 *
	 */
	public Set<VSnpNonsynAllele> findVSnpNonsynAlleleByNonSynAllele(String nonSynAllele_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpNonsynAlleleByNonSynAllele
	 *
	 */
	public Set<VSnpNonsynAllele> findVSnpNonsynAlleleByNonSynAllele(String nonSynAllele_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpNonsynAlleleBySnpFeatureId
	 *
	 */
	public Set<VSnpNonsynAllele> findVSnpNonsynAlleleBySnpFeatureId(Integer snpFeatureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpNonsynAlleleBySnpFeatureId
	 *
	 */
	public Set<VSnpNonsynAllele> findVSnpNonsynAlleleBySnpFeatureId(Integer snpFeatureId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpNonsynAlleleByNonSynAlleleContaining
	 *
	 */
	public Set<VSnpNonsynAllele> findVSnpNonsynAlleleByNonSynAlleleContaining(String nonSynAllele_2) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpNonsynAlleleByNonSynAlleleContaining
	 *
	 */
	public Set<VSnpNonsynAllele> findVSnpNonsynAlleleByNonSynAlleleContaining(String nonSynAllele_2, int startResult, int maxRows) throws DataAccessException;

}