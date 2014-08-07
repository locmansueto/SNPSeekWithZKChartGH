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

import org.irri.iric.portal.variety.domain.Var700;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage Var700 entities.
 * 
 */
@Repository("Var700DAO")
@Transactional
public class Var700DAOImpl extends AbstractJpaDao<Var700> implements Var700DAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { Var700.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Development
	 *
	 */
	@PersistenceContext(unitName = "Development")
	//@Resource(name =  "Development")
	private EntityManager entityManager;

	/**
	 * Instantiates a new Var700DAOImpl
	 *
	 */
	public Var700DAOImpl() {
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
	 * JPQL Query - findVar700ByName
	 *
	 */
	@Transactional
	public Set<Var700> findVar700ByName(String name) throws DataAccessException {

		return findVar700ByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVar700ByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Var700> findVar700ByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVar700ByName", startResult, maxRows, name);
		return new LinkedHashSet<Var700>(query.getResultList());
	}

	/**
	 * JPQL Query - findVar700ByPrimaryKey
	 *
	 */
	@Transactional
	public Var700 findVar700ByPrimaryKey(Integer id) throws DataAccessException {

		return findVar700ByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findVar700ByPrimaryKey
	 *
	 */

	@Transactional
	public Var700 findVar700ByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVar700ByPrimaryKey", startResult, maxRows, id);
			return (org.irri.iric.portal.variety.domain.Var700) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVar700ByNameContaining
	 *
	 */
	@Transactional
	public Set<Var700> findVar700ByNameContaining(String name) throws DataAccessException {

		return findVar700ByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVar700ByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Var700> findVar700ByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVar700ByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<Var700>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVar700s
	 *
	 */
	@Transactional
	public Set<Var700> findAllVar700s() throws DataAccessException {

		return findAllVar700s(-1, -1);
	}

	/**
	 * JPQL Query - findAllVar700s
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Var700> findAllVar700s(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVar700s", startResult, maxRows);
		return new LinkedHashSet<Var700>(query.getResultList());
	}

	/**
	 * JPQL Query - findVar700ById
	 *
	 */
	@Transactional
	public Var700 findVar700ById(Integer id) throws DataAccessException {

		return findVar700ById(id, -1, -1);
	}

	/**
	 * JPQL Query - findVar700ById
	 *
	 */

	@Transactional
	public Var700 findVar700ById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVar700ById", startResult, maxRows, id);
			return (org.irri.iric.portal.variety.domain.Var700) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(Var700 entity) {
		return true;
	}
}
