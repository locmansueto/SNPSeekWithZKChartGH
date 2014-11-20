package org.irri.iric.portal.chado.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.chado.domain.VGene;
import org.irri.iric.portal.dao.GeneDAO;
import org.irri.iric.portal.domain.Gene;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VGene entities.
 * 
 */
@Repository("GeneDAO")
@Transactional
public class VGeneDAOImpl extends AbstractJpaDao<VGene> implements VGeneDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VGene.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VGeneDAOImpl
	 *
	 */
	public VGeneDAOImpl() {
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
	 * JPQL Query - findVGeneByPhase
	 *
	 */
	@Transactional
	public Set<VGene> findVGeneByPhase(java.math.BigDecimal phase) throws DataAccessException {

		return findVGeneByPhase(phase, -1, -1);
	}

	/**
	 * JPQL Query - findVGeneByPhase
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGene> findVGeneByPhase(java.math.BigDecimal phase, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVGeneByPhase", startResult, maxRows, phase);
		return new LinkedHashSet<VGene>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVGenes
	 *
	 */
	@Transactional
	public Set<VGene> findAllVGenes() throws DataAccessException {

		return findAllVGenes(-1, -1);
	}

	/**
	 * JPQL Query - findAllVGenes
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGene> findAllVGenes(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVGenes", startResult, maxRows);
		return new LinkedHashSet<VGene>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGeneByChr
	 *
	 */
	@Transactional
	public Set<VGene> findVGeneByChr(java.math.BigDecimal chr) throws DataAccessException {

		return findVGeneByChr(chr, -1, -1);
	}

	/**
	 * JPQL Query - findVGeneByChr
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGene> findVGeneByChr(java.math.BigDecimal chr, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVGeneByChr", startResult, maxRows, chr);
		return new LinkedHashSet<VGene>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGeneByName
	 *
	 */
	@Transactional
	public Set<VGene> findVGeneByName(String name) throws DataAccessException {

		return findVGeneByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVGeneByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGene> findVGeneByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVGeneByName", startResult, maxRows, name);
		return new LinkedHashSet<VGene>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGeneByFmin
	 *
	 */
	@Transactional
	public Set<VGene> findVGeneByFmin(java.math.BigDecimal fmin) throws DataAccessException {

		return findVGeneByFmin(fmin, -1, -1);
	}

	/**
	 * JPQL Query - findVGeneByFmin
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGene> findVGeneByFmin(java.math.BigDecimal fmin, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVGeneByFmin", startResult, maxRows, fmin);
		return new LinkedHashSet<VGene>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGeneByPrimaryKey
	 *
	 */
	@Transactional
	public VGene findVGeneByPrimaryKey(Integer geneId) throws DataAccessException {

		return findVGeneByPrimaryKey(geneId, -1, -1);
	}

	/**
	 * JPQL Query - findVGeneByPrimaryKey
	 *
	 */

	@Transactional
	public VGene findVGeneByPrimaryKey(Integer geneId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVGeneByPrimaryKey", startResult, maxRows, geneId);
			return (org.irri.iric.portal.chado.domain.VGene) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVGeneByFmax
	 *
	 */
	@Transactional
	public Set<VGene> findVGeneByFmax(java.math.BigDecimal fmax) throws DataAccessException {

		return findVGeneByFmax(fmax, -1, -1);
	}

	/**
	 * JPQL Query - findVGeneByFmax
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGene> findVGeneByFmax(java.math.BigDecimal fmax, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVGeneByFmax", startResult, maxRows, fmax);
		return new LinkedHashSet<VGene>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGeneByNameContaining
	 *
	 */
	@Transactional
	public Set<VGene> findVGeneByNameContaining(String name) throws DataAccessException {

		return findVGeneByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVGeneByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGene> findVGeneByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVGeneByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VGene>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGeneByGeneId
	 *
	 */
	@Transactional
	public VGene findVGeneByGeneId(Integer geneId) throws DataAccessException {

		return findVGeneByGeneId(geneId, -1, -1);
	}

	/**
	 * JPQL Query - findVGeneByGeneId
	 *
	 */

	@Transactional
	public VGene findVGeneByGeneId(Integer geneId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVGeneByGeneId", startResult, maxRows, geneId);
			return (org.irri.iric.portal.chado.domain.VGene) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVGeneByStrand
	 *
	 */
	@Transactional
	public Set<VGene> findVGeneByStrand(java.math.BigDecimal strand) throws DataAccessException {

		return findVGeneByStrand(strand, -1, -1);
	}

	/**
	 * JPQL Query - findVGeneByStrand
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGene> findVGeneByStrand(java.math.BigDecimal strand, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVGeneByStrand", startResult, maxRows, strand);
		return new LinkedHashSet<VGene>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VGene entity) {
		return true;
	}

	@Override
	public Set findAllGene() {
		// TODO Auto-generated method stub
		return this.findAllVGenes();
	}

	@Override
	public Gene findGeneByName(String name) {
		// TODO Auto-generated method stub
		Set setGene  = this.findVGeneByName(name);
		if(setGene.isEmpty()) return null;
		if(setGene.size()>1) throw new RuntimeException("Multiple values of gene with name " + name);
		return (Gene)setGene.iterator().next();
		
	}
}
