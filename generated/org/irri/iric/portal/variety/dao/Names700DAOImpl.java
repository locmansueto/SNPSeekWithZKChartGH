package org.irri.iric.portal.variety.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.variety.domain.Names700;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage Names700 entities.
 * 
 */
@Repository("Names700DAO")
@Transactional
public class Names700DAOImpl extends AbstractJpaDao<Names700> implements
		Names700DAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { Names700.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Development
	 *
	 */
	@PersistenceContext(unitName = "Development")
	private EntityManager entityManager;

	/**
	 * Instantiates a new Names700DAOImpl
	 *
	 */
	public Names700DAOImpl() {
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
	 * JPQL Query - findNames700ByAssayIdContaining
	 *
	 */
	@Transactional
	public Set<Names700> findNames700ByAssayIdContaining(String assayId) throws DataAccessException {

		return findNames700ByAssayIdContaining(assayId, -1, -1);
	}

	/**
	 * JPQL Query - findNames700ByAssayIdContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Names700> findNames700ByAssayIdContaining(String assayId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNames700ByAssayIdContaining", startResult, maxRows, assayId);
		return new LinkedHashSet<Names700>(query.getResultList());
	}

	/**
	 * JPQL Query - findNames700ById
	 *
	 */
	@Transactional
	public Names700 findNames700ById(String id) throws DataAccessException {

		return findNames700ById(id, -1, -1);
	}

	/**
	 * JPQL Query - findNames700ById
	 *
	 */

	@Transactional
	public Names700 findNames700ById(String id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findNames700ById", startResult, maxRows, id);
			return (org.irri.iric.portal.variety.domain.Names700) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findNames700ByPrimaryKey
	 *
	 */
	@Transactional
	public Names700 findNames700ByPrimaryKey(String id) throws DataAccessException {

		return findNames700ByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findNames700ByPrimaryKey
	 *
	 */

	@Transactional
	public Names700 findNames700ByPrimaryKey(String id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findNames700ByPrimaryKey", startResult, maxRows, id);
			return (org.irri.iric.portal.variety.domain.Names700) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findNames700ByAssayId
	 *
	 */
	@Transactional
	public Set<Names700> findNames700ByAssayId(String assayId) throws DataAccessException {

		return findNames700ByAssayId(assayId, -1, -1);
	}

	/**
	 * JPQL Query - findNames700ByAssayId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Names700> findNames700ByAssayId(String assayId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNames700ByAssayId", startResult, maxRows, assayId);
		return new LinkedHashSet<Names700>(query.getResultList());
	}

	/**
	 * JPQL Query - findNames700ByAltIdContaining
	 *
	 */
	@Transactional
	public Set<Names700> findNames700ByAltIdContaining(String altId) throws DataAccessException {

		return findNames700ByAltIdContaining(altId, -1, -1);
	}

	/**
	 * JPQL Query - findNames700ByAltIdContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Names700> findNames700ByAltIdContaining(String altId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNames700ByAltIdContaining", startResult, maxRows, altId);
		return new LinkedHashSet<Names700>(query.getResultList());
	}

	/**
	 * JPQL Query - findNames700BySampleId
	 *
	 */
	@Transactional
	public Set<Names700> findNames700BySampleId(String sampleId) throws DataAccessException {

		return findNames700BySampleId(sampleId, -1, -1);
	}

	/**
	 * JPQL Query - findNames700BySampleId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Names700> findNames700BySampleId(String sampleId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNames700BySampleId", startResult, maxRows, sampleId);
		return new LinkedHashSet<Names700>(query.getResultList());
	}

	/**
	 * JPQL Query - findNames700BySampleIdContaining
	 *
	 */
	@Transactional
	public Set<Names700> findNames700BySampleIdContaining(String sampleId) throws DataAccessException {

		return findNames700BySampleIdContaining(sampleId, -1, -1);
	}

	/**
	 * JPQL Query - findNames700BySampleIdContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Names700> findNames700BySampleIdContaining(String sampleId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNames700BySampleIdContaining", startResult, maxRows, sampleId);
		return new LinkedHashSet<Names700>(query.getResultList());
	}

	/**
	 * JPQL Query - findNames700ByAltId
	 *
	 */
	@Transactional
	public Set<Names700> findNames700ByAltId(String altId) throws DataAccessException {

		return findNames700ByAltId(altId, -1, -1);
	}

	/**
	 * JPQL Query - findNames700ByAltId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Names700> findNames700ByAltId(String altId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNames700ByAltId", startResult, maxRows, altId);
		return new LinkedHashSet<Names700>(query.getResultList());
	}

	/**
	 * JPQL Query - findNames700ByIdContaining
	 *
	 */
	@Transactional
	public Set<Names700> findNames700ByIdContaining(String id) throws DataAccessException {

		return findNames700ByIdContaining(id, -1, -1);
	}

	/**
	 * JPQL Query - findNames700ByIdContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Names700> findNames700ByIdContaining(String id, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNames700ByIdContaining", startResult, maxRows, id);
		return new LinkedHashSet<Names700>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllNames700s
	 *
	 */
	@Transactional
	public Set<Names700> findAllNames700s() throws DataAccessException {

		return findAllNames700s(-1, -1);
	}

	/**
	 * JPQL Query - findAllNames700s
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Names700> findAllNames700s(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllNames700s", startResult, maxRows);
		return new LinkedHashSet<Names700>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(Names700 entity) {
		return true;
	}
}
