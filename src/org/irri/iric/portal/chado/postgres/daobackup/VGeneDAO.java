package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.Set;

import org.irri.iric.portal.chado.postgres.domain.VGene;
import org.irri.iric.portal.dao.GeneDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VGene entities.
 * 
 */
public interface VGeneDAO extends JpaDao<VGene>, GeneDAO  {

	/**
	 * JPQL Query - findAllVGenes
	 *
	 */
	public Set<VGene> findAllVGenes() throws DataAccessException;

	/**
	 * JPQL Query - findAllVGenes
	 *
	 */
	public Set<VGene> findAllVGenes(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByName
	 *
	 */
	public Set<VGene> findVGeneByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByName
	 *
	 */
	public Set<VGene> findVGeneByName(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByNameContaining
	 *
	 */
	public Set<VGene> findVGeneByNameContaining(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByNameContaining
	 *
	 */
	public Set<VGene> findVGeneByNameContaining(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByChrContaining
	 *
	 */
	public Set<VGene> findVGeneByChrContaining(String chr) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByChrContaining
	 *
	 */
	public Set<VGene> findVGeneByChrContaining(String chr, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByPhase
	 *
	 */
	public Set<VGene> findVGeneByPhase(Integer phase) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByPhase
	 *
	 */
	public Set<VGene> findVGeneByPhase(Integer phase, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByFmax
	 *
	 */
	public Set<VGene> findVGeneByFmax(Integer fmax) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByFmax
	 *
	 */
	public Set<VGene> findVGeneByFmax(Integer fmax, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByOrganismId
	 *
	 */
	public Set<VGene> findVGeneByOrganismId(Integer organismId) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByOrganismId
	 *
	 */
	public Set<VGene> findVGeneByOrganismId(Integer organismId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByStrand
	 *
	 */
	public Set<VGene> findVGeneByStrand(Integer strand) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByStrand
	 *
	 */
	public Set<VGene> findVGeneByStrand(Integer strand, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByChr
	 *
	 */
	public Set<VGene> findVGeneByChr(String chr_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByChr
	 *
	 */
	public Set<VGene> findVGeneByChr(String chr_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByPrimaryKey
	 *
	 */
	public VGene findVGeneByPrimaryKey(Integer geneId) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByPrimaryKey
	 *
	 */
	public VGene findVGeneByPrimaryKey(Integer geneId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByGeneId
	 *
	 */
	public VGene findVGeneByGeneId(Integer geneId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByGeneId
	 *
	 */
	public VGene findVGeneByGeneId(Integer geneId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByFmin
	 *
	 */
	public Set<VGene> findVGeneByFmin(Integer fmin) throws DataAccessException;

	/**
	 * JPQL Query - findVGeneByFmin
	 *
	 */
	public Set<VGene> findVGeneByFmin(Integer fmin, int startResult, int maxRows) throws DataAccessException;

}