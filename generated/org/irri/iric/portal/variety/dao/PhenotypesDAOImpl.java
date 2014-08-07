package org.irri.iric.portal.variety.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.variety.domain.Phenotypes;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage Phenotypes entities.
 * 
 */
@Repository("PhenotypesDAO")
@Transactional
public class PhenotypesDAOImpl extends AbstractJpaDao<Phenotypes> implements
		PhenotypesDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { Phenotypes.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Development
	 *
	 */
	@PersistenceContext(unitName = "Development")
	//@Resource(name =  "Development")
	private EntityManager entityManager;

	/**
	 * Instantiates a new PhenotypesDAOImpl
	 *
	 */
	public PhenotypesDAOImpl() {
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
	 * JPQL Query - findAllPhenotypess
	 *
	 */
	@Transactional
	public Set<Phenotypes> findAllPhenotypess() throws DataAccessException {

		return findAllPhenotypess(-1, -1);
	}

	/**
	 * JPQL Query - findAllPhenotypess
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Phenotypes> findAllPhenotypess(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllPhenotypess", startResult, maxRows);
		return new LinkedHashSet<Phenotypes>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenotypesByPrimaryKey
	 *
	 */
	@Transactional
	public Phenotypes findPhenotypesByPrimaryKey(Integer nsftvId, String trait) throws DataAccessException {

		return findPhenotypesByPrimaryKey(nsftvId, trait, -1, -1);
	}

	/**
	 * JPQL Query - findPhenotypesByPrimaryKey
	 *
	 */

	@Transactional
	public Phenotypes findPhenotypesByPrimaryKey(Integer nsftvId, String trait, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findPhenotypesByPrimaryKey", startResult, maxRows, nsftvId, trait);
			return (org.irri.iric.portal.variety.domain.Phenotypes) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findPhenotypesByTrait
	 *
	 */
	@Transactional
	public Set<Phenotypes> findPhenotypesByTrait(String trait) throws DataAccessException {

		return findPhenotypesByTrait(trait, -1, -1);
	}

	/**
	 * JPQL Query - findPhenotypesByTrait
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Phenotypes> findPhenotypesByTrait(String trait, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenotypesByTrait", startResult, maxRows, trait);
		return new LinkedHashSet<Phenotypes>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenotypesByNsftvId
	 *
	 */
	@Transactional
	public Set<Phenotypes> findPhenotypesByNsftvId(Integer nsftvId) throws DataAccessException {

		return findPhenotypesByNsftvId(nsftvId, -1, -1);
	}

	/**
	 * JPQL Query - findPhenotypesByNsftvId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Phenotypes> findPhenotypesByNsftvId(Integer nsftvId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenotypesByNsftvId", startResult, maxRows, nsftvId);
		return new LinkedHashSet<Phenotypes>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenotypesByTraitContaining
	 *
	 */
	@Transactional
	public Set<Phenotypes> findPhenotypesByTraitContaining(String trait) throws DataAccessException {

		return findPhenotypesByTraitContaining(trait, -1, -1);
	}

	/**
	 * JPQL Query - findPhenotypesByTraitContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Phenotypes> findPhenotypesByTraitContaining(String trait, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenotypesByTraitContaining", startResult, maxRows, trait);
		return new LinkedHashSet<Phenotypes>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenotypesByVal
	 *
	 */
	@Transactional
	public Set<Phenotypes> findPhenotypesByVal(Integer val) throws DataAccessException {

		return findPhenotypesByVal(val, -1, -1);
	}

	/**
	 * JPQL Query - findPhenotypesByVal
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Phenotypes> findPhenotypesByVal(Integer val, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenotypesByVal", startResult, maxRows, val);
		return new LinkedHashSet<Phenotypes>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(Phenotypes entity) {
		return true;
	}
}
