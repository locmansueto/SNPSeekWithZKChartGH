package org.irri.iric.portal.variety.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.variety.domain.Dist3k;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage Dist3k entities.
 * 
 */
@Repository("Dist3kDAO")
@Transactional
public class Dist3kDAOImpl extends AbstractJpaDao<Dist3k> implements Dist3kDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { Dist3k.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Development
	 *
	 */
	@PersistenceContext(unitName = "Development")
	private EntityManager entityManager;

	/**
	 * Instantiates a new Dist3kDAOImpl
	 *
	 */
	public Dist3kDAOImpl() {
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
	 * JPQL Query - findDist3kByPrimaryKey
	 *
	 */
	@Transactional
	public Dist3k findDist3kByPrimaryKey(String nam1, String nam2) throws DataAccessException {

		return findDist3kByPrimaryKey(nam1, nam2, -1, -1);
	}

	/**
	 * JPQL Query - findDist3kByPrimaryKey
	 *
	 */

	@Transactional
	public Dist3k findDist3kByPrimaryKey(String nam1, String nam2, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findDist3kByPrimaryKey", startResult, maxRows, nam1, nam2);
			return (org.irri.iric.portal.variety.domain.Dist3k) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findDist3kByNam2
	 *
	 */
	@Transactional
	public Set<Dist3k> findDist3kByNam2(String nam2) throws DataAccessException {

		return findDist3kByNam2(nam2, -1, -1);
	}

	/**
	 * JPQL Query - findDist3kByNam2
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Dist3k> findDist3kByNam2(String nam2, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findDist3kByNam2", startResult, maxRows, nam2);
		return new LinkedHashSet<Dist3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findDist3kByNam2Containing
	 *
	 */
	@Transactional
	public Set<Dist3k> findDist3kByNam2Containing(String nam2) throws DataAccessException {

		return findDist3kByNam2Containing(nam2, -1, -1);
	}

	/**
	 * JPQL Query - findDist3kByNam2Containing
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Dist3k> findDist3kByNam2Containing(String nam2, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findDist3kByNam2Containing", startResult, maxRows, nam2);
		return new LinkedHashSet<Dist3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findDist3kByNam1Containing
	 *
	 */
	@Transactional
	public Set<Dist3k> findDist3kByNam1Containing(String nam1) throws DataAccessException {

		return findDist3kByNam1Containing(nam1, -1, -1);
	}

	/**
	 * JPQL Query - findDist3kByNam1Containing
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Dist3k> findDist3kByNam1Containing(String nam1, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findDist3kByNam1Containing", startResult, maxRows, nam1);
		return new LinkedHashSet<Dist3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findDist3kByDist
	 *
	 */
	@Transactional
	public Set<Dist3k> findDist3kByDist(Integer dist) throws DataAccessException {

		return findDist3kByDist(dist, -1, -1);
	}

	/**
	 * JPQL Query - findDist3kByDist
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Dist3k> findDist3kByDist(Integer dist, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findDist3kByDist", startResult, maxRows, dist);
		return new LinkedHashSet<Dist3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllDist3ks
	 *
	 */
	@Transactional
	public Set<Dist3k> findAllDist3ks() throws DataAccessException {

		return findAllDist3ks(-1, -1);
	}

	/**
	 * JPQL Query - findAllDist3ks
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Dist3k> findAllDist3ks(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllDist3ks", startResult, maxRows);
		return new LinkedHashSet<Dist3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findDist3kByNam1
	 *
	 */
	@Transactional
	public Set<Dist3k> findDist3kByNam1(String nam1) throws DataAccessException {

		return findDist3kByNam1(nam1, -1, -1);
	}

	/**
	 * JPQL Query - findDist3kByNam1
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Dist3k> findDist3kByNam1(String nam1, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findDist3kByNam1", startResult, maxRows, nam1);
		return new LinkedHashSet<Dist3k>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(Dist3k entity) {
		return true;
	}
}
