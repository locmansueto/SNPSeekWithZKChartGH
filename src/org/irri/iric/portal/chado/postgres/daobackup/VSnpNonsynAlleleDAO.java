package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.Set;

import org.irri.iric.portal.chado.postgres.domain.VSnpNonsynAllele;
import org.irri.iric.portal.dao.SnpsNonsynAllvarsDAO;
import org.irri.iric.portal.domain.SnpsNonsynAllele;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VSnpNonsynAllele entities.
 * 
 */
public interface VSnpNonsynAlleleDAO extends JpaDao<VSnpNonsynAllele>, SnpsNonsynAllvarsDAO {

	/**
	 * JPQL Query - findVSnpNonsynAlleleByNonSynAllele
	 *
	 */
	public Set<VSnpNonsynAllele> findVSnpNonsynAlleleByNonSynAllele(String nonSynAllele) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpNonsynAlleleByNonSynAllele
	 *
	 */
	public Set<VSnpNonsynAllele> findVSnpNonsynAlleleByNonSynAllele(String nonSynAllele, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpNonsynAlleleByNonSynAlleleContaining
	 *
	 */
	public Set<VSnpNonsynAllele> findVSnpNonsynAlleleByNonSynAlleleContaining(String nonSynAllele_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpNonsynAlleleByNonSynAlleleContaining
	 *
	 */
	public Set<VSnpNonsynAllele> findVSnpNonsynAlleleByNonSynAlleleContaining(String nonSynAllele_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpNonsynAlleleBySnpFeatureId
	 *
	 */
	public Set<VSnpNonsynAllele> findVSnpNonsynAlleleBySnpFeatureId(Long snpFeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpNonsynAlleleBySnpFeatureId
	 *
	 */
	public Set<VSnpNonsynAllele> findVSnpNonsynAlleleBySnpFeatureId(Long snpFeatureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpNonsynAlleleByPrimaryKey
	 *
	 */
	public VSnpNonsynAllele findVSnpNonsynAlleleByPrimaryKey(Long snpFeatureId_1, String nonSynAllele_2) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpNonsynAlleleByPrimaryKey
	 *
	 */
	public VSnpNonsynAllele findVSnpNonsynAlleleByPrimaryKey(Long snpFeatureId_1, String nonSynAllele_2, int startResult, int maxRows) throws DataAccessException;

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

}