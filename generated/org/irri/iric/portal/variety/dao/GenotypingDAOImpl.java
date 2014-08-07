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

import org.irri.iric.portal.variety.domain.Genotyping;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage Genotyping entities.
 * 
 */
@Repository("GenotypingDAO")
@Transactional
public class GenotypingDAOImpl extends AbstractJpaDao<Genotyping> implements
		GenotypingDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { Genotyping.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Development
	 *
	 */
	@PersistenceContext(unitName = "Development")
	//@Resource(name =  "Development")
	private EntityManager entityManager;

	/**
	 * Instantiates a new GenotypingDAOImpl
	 *
	 */
	public GenotypingDAOImpl() {
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
	 * JPQL Query - findGenotypingByNsftvId
	 *
	 */
	@Transactional
	public Set<Genotyping> findGenotypingByNsftvId(Integer nsftvId) throws DataAccessException {

		return findGenotypingByNsftvId(nsftvId, -1, -1);
	}

	/**
	 * JPQL Query - findGenotypingByNsftvId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Genotyping> findGenotypingByNsftvId(Integer nsftvId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGenotypingByNsftvId", startResult, maxRows, nsftvId);
		return new LinkedHashSet<Genotyping>(query.getResultList());
	}

	/**
	 * JPQL Query - findGenotypingByVar1Containing
	 *
	 */
	@Transactional
	public Set<Genotyping> findGenotypingByVar1Containing(String var1) throws DataAccessException {

		return findGenotypingByVar1Containing(var1, -1, -1);
	}

	/**
	 * JPQL Query - findGenotypingByVar1Containing
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Genotyping> findGenotypingByVar1Containing(String var1, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGenotypingByVar1Containing", startResult, maxRows, var1);
		return new LinkedHashSet<Genotyping>(query.getResultList());
	}

	/**
	 * JPQL Query - findGenotypingByPrimaryKey
	 *
	 */
	@Transactional
	public Genotyping findGenotypingByPrimaryKey(String snpId, Integer nsftvId) throws DataAccessException {

		return findGenotypingByPrimaryKey(snpId, nsftvId, -1, -1);
	}

	/**
	 * JPQL Query - findGenotypingByPrimaryKey
	 *
	 */

	@Transactional
	public Genotyping findGenotypingByPrimaryKey(String snpId, Integer nsftvId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findGenotypingByPrimaryKey", startResult, maxRows, snpId, nsftvId);
			return (org.irri.iric.portal.variety.domain.Genotyping) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findGenotypingByVar2Containing
	 *
	 */
	@Transactional
	public Set<Genotyping> findGenotypingByVar2Containing(String var2) throws DataAccessException {

		return findGenotypingByVar2Containing(var2, -1, -1);
	}

	/**
	 * JPQL Query - findGenotypingByVar2Containing
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Genotyping> findGenotypingByVar2Containing(String var2, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGenotypingByVar2Containing", startResult, maxRows, var2);
		return new LinkedHashSet<Genotyping>(query.getResultList());
	}

	/**
	 * JPQL Query - findGenotypingBySnpId
	 *
	 */
	@Transactional
	public Set<Genotyping> findGenotypingBySnpId(String snpId) throws DataAccessException {

		return findGenotypingBySnpId(snpId, -1, -1);
	}

	/**
	 * JPQL Query - findGenotypingBySnpId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Genotyping> findGenotypingBySnpId(String snpId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGenotypingBySnpId", startResult, maxRows, snpId);
		return new LinkedHashSet<Genotyping>(query.getResultList());
	}

	/**
	 * JPQL Query - findGenotypingByVar2
	 *
	 */
	@Transactional
	public Set<Genotyping> findGenotypingByVar2(String var2) throws DataAccessException {

		return findGenotypingByVar2(var2, -1, -1);
	}

	/**
	 * JPQL Query - findGenotypingByVar2
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Genotyping> findGenotypingByVar2(String var2, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGenotypingByVar2", startResult, maxRows, var2);
		return new LinkedHashSet<Genotyping>(query.getResultList());
	}

	/**
	 * JPQL Query - findGenotypingByVar1
	 *
	 */
	@Transactional
	public Set<Genotyping> findGenotypingByVar1(String var1) throws DataAccessException {

		return findGenotypingByVar1(var1, -1, -1);
	}

	/**
	 * JPQL Query - findGenotypingByVar1
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Genotyping> findGenotypingByVar1(String var1, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGenotypingByVar1", startResult, maxRows, var1);
		return new LinkedHashSet<Genotyping>(query.getResultList());
	}

	/**
	 * JPQL Query - findGenotypingBySnpIdContaining
	 *
	 */
	@Transactional
	public Set<Genotyping> findGenotypingBySnpIdContaining(String snpId) throws DataAccessException {

		return findGenotypingBySnpIdContaining(snpId, -1, -1);
	}

	/**
	 * JPQL Query - findGenotypingBySnpIdContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Genotyping> findGenotypingBySnpIdContaining(String snpId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGenotypingBySnpIdContaining", startResult, maxRows, snpId);
		return new LinkedHashSet<Genotyping>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllGenotypings
	 *
	 */
	@Transactional
	public Set<Genotyping> findAllGenotypings() throws DataAccessException {

		return findAllGenotypings(-1, -1);
	}

	/**
	 * JPQL Query - findAllGenotypings
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Genotyping> findAllGenotypings(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllGenotypings", startResult, maxRows);
		return new LinkedHashSet<Genotyping>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(Genotyping entity) {
		return true;
	}
}
