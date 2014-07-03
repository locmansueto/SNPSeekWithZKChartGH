package org.irri.iric.portal.genotype.dao;

import java.math.BigDecimal;

import java.util.Set;

import org.irri.iric.portal.genotype.domain.Gene;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage Gene entities.
 * 
 */
public interface GeneDAO extends JpaDao<Gene> {

	/**
	 * JPQL Query - findGeneByChr
	 *
	 */
	public Set<Gene> findGeneByChr(String chr) throws DataAccessException;

	/**
	 * JPQL Query - findGeneByChr
	 *
	 */
	public Set<Gene> findGeneByChr(String chr, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGeneByPrimaryKey
	 *
	 */
	public Gene findGeneByPrimaryKey(Integer featureId) throws DataAccessException;

	/**
	 * JPQL Query - findGeneByPrimaryKey
	 *
	 */
	public Gene findGeneByPrimaryKey(Integer featureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGeneByUniquenameContaining
	 *
	 */
	public Set<Gene> findGeneByUniquenameContaining(String uniquename) throws DataAccessException;

	/**
	 * JPQL Query - findGeneByUniquenameContaining
	 *
	 */
	public Set<Gene> findGeneByUniquenameContaining(String uniquename, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGeneByGeneFieldContaining
	 *
	 */
	public Set<Gene> findGeneByGeneFieldContaining(String geneField) throws DataAccessException;

	/**
	 * JPQL Query - findGeneByGeneFieldContaining
	 *
	 */
	public Set<Gene> findGeneByGeneFieldContaining(String geneField, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGeneByChrContaining
	 *
	 */
	public Set<Gene> findGeneByChrContaining(String chr_1) throws DataAccessException;

	/**
	 * JPQL Query - findGeneByChrContaining
	 *
	 */
	public Set<Gene> findGeneByChrContaining(String chr_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGeneByFmin
	 *
	 */
	public Set<Gene> findGeneByFmin(java.math.BigDecimal fmin) throws DataAccessException;

	/**
	 * JPQL Query - findGeneByFmin
	 *
	 */
	public Set<Gene> findGeneByFmin(BigDecimal fmin, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllGenes
	 *
	 */
	public Set<Gene> findAllGenes() throws DataAccessException;

	/**
	 * JPQL Query - findAllGenes
	 *
	 */
	public Set<Gene> findAllGenes(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGeneByFeatureId
	 *
	 */
	public Gene findGeneByFeatureId(Integer featureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findGeneByFeatureId
	 *
	 */
	public Gene findGeneByFeatureId(Integer featureId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGeneByFmax
	 *
	 */
	public Set<Gene> findGeneByFmax(java.math.BigDecimal fmax) throws DataAccessException;

	/**
	 * JPQL Query - findGeneByFmax
	 *
	 */
	public Set<Gene> findGeneByFmax(BigDecimal fmax, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGeneByUniquename
	 *
	 */
	public Set<Gene> findGeneByUniquename(String uniquename_1) throws DataAccessException;

	/**
	 * JPQL Query - findGeneByUniquename
	 *
	 */
	public Set<Gene> findGeneByUniquename(String uniquename_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findGeneByGeneField
	 *
	 */
	public Set<Gene> findGeneByGeneField(String geneField_1) throws DataAccessException;

	/**
	 * JPQL Query - findGeneByGeneField
	 *
	 */
	public Set<Gene> findGeneByGeneField(String geneField_1, int startResult, int maxRows) throws DataAccessException;

}