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

import org.irri.iric.portal.genotype.domain.Variety3k;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage Variety3k entities.
 * 
 */
@Repository("Variety3kDAOOld")
@Transactional
public class Variety3kDAOImpl extends AbstractJpaDao<Variety3k> implements
		Variety3kDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { Variety3k.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "Production")
	//@Resource(name =  "Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new Variety3kDAOImpl
	 *
	 */
	public Variety3kDAOImpl() {
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
	 * JPQL Query - findVariety3kByVarname
	 *
	 */
	@Transactional
	public Variety3k findVariety3kByVarname(String varname) throws DataAccessException {

		return findVariety3kByVarname(varname, -1, -1);
	}

	/**
	 * JPQL Query - findVariety3kByVarname
	 *
	 */

	@Transactional
	public Variety3k findVariety3kByVarname(String varname, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVariety3kByVarname", startResult, maxRows, varname);
			return (org.irri.iric.portal.genotype.domain.Variety3k) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllVariety3ks
	 *
	 */
	@Transactional
	public Set<Variety3k> findAllVariety3ks() throws DataAccessException {

		return findAllVariety3ks(-1, -1);
	}

	/**
	 * JPQL Query - findAllVariety3ks
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Variety3k> findAllVariety3ks(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVariety3ks", startResult, maxRows);
		return new LinkedHashSet<Variety3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findVariety3kByVarnameContaining
	 *
	 */
	@Transactional
	public Set<Variety3k> findVariety3kByVarnameContaining(String varname) throws DataAccessException {

		return findVariety3kByVarnameContaining(varname, -1, -1);
	}

	/**
	 * JPQL Query - findVariety3kByVarnameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Variety3k> findVariety3kByVarnameContaining(String varname, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVariety3kByVarnameContaining", startResult, maxRows, varname);
		return new LinkedHashSet<Variety3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findVariety3kByPrimaryKey
	 *
	 */
	@Transactional
	public Variety3k findVariety3kByPrimaryKey(String varname) throws DataAccessException {

		return findVariety3kByPrimaryKey(varname, -1, -1);
	}

	/**
	 * JPQL Query - findVariety3kByPrimaryKey
	 *
	 */

	@Transactional
	public Variety3k findVariety3kByPrimaryKey(String varname, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVariety3kByPrimaryKey", startResult, maxRows, varname);
			return (org.irri.iric.portal.genotype.domain.Variety3k) query.getSingleResult();
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
	public boolean canBeMerged(Variety3k entity) {
		return true;
	}
}
