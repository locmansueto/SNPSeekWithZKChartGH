package org.irri.iric.portal.genotype.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.genotype.domain.Gene;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage Gene entities.
 * 
 */
@Repository("GeneDAOLegacy")
@Transactional
public class GeneDAOImpl extends AbstractJpaDao<Gene> implements GeneDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { Gene.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "Production")
	//@Resource(name =  "Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new GeneDAOImpl
	 *
	 */
	public GeneDAOImpl() {
		super();
	}

	/**
	 * Get the entity manager that manages persistence unit 
	 *
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Returns the set of entity classes managed by this DAO.
	 *
	 */
	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	/**
	 * JPQL Query - findGeneByChr
	 *
	 */
	@Transactional
	public Set<Gene> findGeneByChr(String chr) throws DataAccessException {

		return findGeneByChr(chr, -1, -1);
	}

	/**
	 * JPQL Query - findGeneByChr
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Gene> findGeneByChr(String chr, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGeneByChr", startResult, maxRows, chr);
		return new LinkedHashSet<Gene>(query.getResultList());
	}

	/**
	 * JPQL Query - findGeneByPrimaryKey
	 *
	 */
	@Transactional
	public Gene findGeneByPrimaryKey(Integer featureId) throws DataAccessException {

		return findGeneByPrimaryKey(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findGeneByPrimaryKey
	 *
	 */

	@Transactional
	public Gene findGeneByPrimaryKey(Integer featureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findGeneByPrimaryKey", startResult, maxRows, featureId);
			return (org.irri.iric.portal.genotype.domain.Gene) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findGeneByUniquenameContaining
	 *
	 */
	@Transactional
	public Set<Gene> findGeneByUniquenameContaining(String uniquename) throws DataAccessException {

		return findGeneByUniquenameContaining(uniquename, -1, -1);
	}

	/**
	 * JPQL Query - findGeneByUniquenameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Gene> findGeneByUniquenameContaining(String uniquename, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGeneByUniquenameContaining", startResult, maxRows, uniquename);
		return new LinkedHashSet<Gene>(query.getResultList());
	}

	/**
	 * JPQL Query - findGeneByGeneFieldContaining
	 *
	 */
	@Transactional
	public Set<Gene> findGeneByGeneFieldContaining(String geneField) throws DataAccessException {

		return findGeneByGeneFieldContaining(geneField, -1, -1);
	}

	/**
	 * JPQL Query - findGeneByGeneFieldContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Gene> findGeneByGeneFieldContaining(String geneField, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGeneByGeneFieldContaining", startResult, maxRows, geneField);
		return new LinkedHashSet<Gene>(query.getResultList());
	}

	/**
	 * JPQL Query - findGeneByChrContaining
	 *
	 */
	@Transactional
	public Set<Gene> findGeneByChrContaining(String chr) throws DataAccessException {

		return findGeneByChrContaining(chr, -1, -1);
	}

	/**
	 * JPQL Query - findGeneByChrContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Gene> findGeneByChrContaining(String chr, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGeneByChrContaining", startResult, maxRows, chr);
		return new LinkedHashSet<Gene>(query.getResultList());
	}

	/**
	 * JPQL Query - findGeneByFmin
	 *
	 */
	@Transactional
	public Set<Gene> findGeneByFmin(java.math.BigDecimal fmin) throws DataAccessException {

		return findGeneByFmin(fmin, -1, -1);
	}

	/**
	 * JPQL Query - findGeneByFmin
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Gene> findGeneByFmin(java.math.BigDecimal fmin, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGeneByFmin", startResult, maxRows, fmin);
		return new LinkedHashSet<Gene>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllGenes
	 *
	 */
	@Transactional
	public Set<Gene> findAllGenes() throws DataAccessException {

		return findAllGenes(-1, -1);
	}

	/**
	 * JPQL Query - findAllGenes
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Gene> findAllGenes(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllGenes", startResult, maxRows);
		return new LinkedHashSet<Gene>(query.getResultList());
	}

	/**
	 * JPQL Query - findGeneByFeatureId
	 *
	 */
	@Transactional
	public Gene findGeneByFeatureId(Integer featureId) throws DataAccessException {

		return findGeneByFeatureId(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findGeneByFeatureId
	 *
	 */

	@Transactional
	public Gene findGeneByFeatureId(Integer featureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findGeneByFeatureId", startResult, maxRows, featureId);
			return (org.irri.iric.portal.genotype.domain.Gene) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findGeneByFmax
	 *
	 */
	@Transactional
	public Set<Gene> findGeneByFmax(java.math.BigDecimal fmax) throws DataAccessException {

		return findGeneByFmax(fmax, -1, -1);
	}

	/**
	 * JPQL Query - findGeneByFmax
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Gene> findGeneByFmax(java.math.BigDecimal fmax, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGeneByFmax", startResult, maxRows, fmax);
		return new LinkedHashSet<Gene>(query.getResultList());
	}

	/**
	 * JPQL Query - findGeneByUniquename
	 *
	 */
	@Transactional
	public Set<Gene> findGeneByUniquename(String uniquename) throws DataAccessException {

		return findGeneByUniquename(uniquename, -1, -1);
	}

	/**
	 * JPQL Query - findGeneByUniquename
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Gene> findGeneByUniquename(String uniquename, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGeneByUniquename", startResult, maxRows, uniquename);
		return new LinkedHashSet<Gene>(query.getResultList());
	}

	/**
	 * JPQL Query - findGeneByGeneField
	 *
	 */
	@Transactional
	public Set<Gene> findGeneByGeneField(String geneField) throws DataAccessException {

		return findGeneByGeneField(geneField, -1, -1);
	}

	/**
	 * JPQL Query - findGeneByGeneField
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Gene> findGeneByGeneField(String geneField, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGeneByGeneField", startResult, maxRows, geneField);
		return new LinkedHashSet<Gene>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(Gene entity) {
		return true;
	}
}
