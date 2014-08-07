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

import org.irri.iric.portal.variety.domain.Genotyp700;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage Genotyp700 entities.
 * 
 */
@Repository("Genotyp700DAO")
@Transactional
public class Genotyp700DAOImpl extends AbstractJpaDao<Genotyp700> implements
		Genotyp700DAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { Genotyp700.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Development
	 *
	 */
	@PersistenceContext(unitName = "Development")
	//@Resource(name =  "Development")
	private EntityManager entityManager;

	/**
	 * Instantiates a new Genotyp700DAOImpl
	 *
	 */
	public Genotyp700DAOImpl() {
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
	 * JPQL Query - findGenotyp700ByPrimaryKey
	 *
	 */
	@Transactional
	public Genotyp700 findGenotyp700ByPrimaryKey(Integer varId, Integer snpId) throws DataAccessException {

		return findGenotyp700ByPrimaryKey(varId, snpId, -1, -1);
	}

	/**
	 * JPQL Query - findGenotyp700ByPrimaryKey
	 *
	 */

	@Transactional
	public Genotyp700 findGenotyp700ByPrimaryKey(Integer varId, Integer snpId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findGenotyp700ByPrimaryKey", startResult, maxRows, varId, snpId);
			return (org.irri.iric.portal.variety.domain.Genotyp700) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllGenotyp700s
	 *
	 */
	@Transactional
	public Set<Genotyp700> findAllGenotyp700s() throws DataAccessException {

		return findAllGenotyp700s(-1, -1);
	}

	/**
	 * JPQL Query - findAllGenotyp700s
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Genotyp700> findAllGenotyp700s(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllGenotyp700s", startResult, maxRows);
		return new LinkedHashSet<Genotyp700>(query.getResultList());
	}

	/**
	 * JPQL Query - findGenotyp700ByTypContaining
	 *
	 */
	@Transactional
	public Set<Genotyp700> findGenotyp700ByTypContaining(String typ) throws DataAccessException {

		return findGenotyp700ByTypContaining(typ, -1, -1);
	}

	/**
	 * JPQL Query - findGenotyp700ByTypContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Genotyp700> findGenotyp700ByTypContaining(String typ, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGenotyp700ByTypContaining", startResult, maxRows, typ);
		return new LinkedHashSet<Genotyp700>(query.getResultList());
	}

	/**
	 * JPQL Query - findGenotyp700ByAl1
	 *
	 */
	@Transactional
	public Set<Genotyp700> findGenotyp700ByAl1(Boolean al1) throws DataAccessException {

		return findGenotyp700ByAl1(al1, -1, -1);
	}

	/**
	 * JPQL Query - findGenotyp700ByAl1
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Genotyp700> findGenotyp700ByAl1(Boolean al1, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGenotyp700ByAl1", startResult, maxRows, al1);
		return new LinkedHashSet<Genotyp700>(query.getResultList());
	}

	/**
	 * JPQL Query - findGenotyp700ByTyp
	 *
	 */
	@Transactional
	public Set<Genotyp700> findGenotyp700ByTyp(String typ) throws DataAccessException {

		return findGenotyp700ByTyp(typ, -1, -1);
	}

	/**
	 * JPQL Query - findGenotyp700ByTyp
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Genotyp700> findGenotyp700ByTyp(String typ, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGenotyp700ByTyp", startResult, maxRows, typ);
		return new LinkedHashSet<Genotyp700>(query.getResultList());
	}

	/**
	 * JPQL Query - findGenotyp700ByVarId
	 *
	 */
	@Transactional
	public Set<Genotyp700> findGenotyp700ByVarId(Integer varId) throws DataAccessException {

		return findGenotyp700ByVarId(varId, -1, -1);
	}

	/**
	 * JPQL Query - findGenotyp700ByVarId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Genotyp700> findGenotyp700ByVarId(Integer varId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGenotyp700ByVarId", startResult, maxRows, varId);
		return new LinkedHashSet<Genotyp700>(query.getResultList());
	}

	/**
	 * JPQL Query - findGenotyp700ByAl2
	 *
	 */
	@Transactional
	public Set<Genotyp700> findGenotyp700ByAl2(Boolean al2) throws DataAccessException {

		return findGenotyp700ByAl2(al2, -1, -1);
	}

	/**
	 * JPQL Query - findGenotyp700ByAl2
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Genotyp700> findGenotyp700ByAl2(Boolean al2, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGenotyp700ByAl2", startResult, maxRows, al2);
		return new LinkedHashSet<Genotyp700>(query.getResultList());
	}

	/**
	 * JPQL Query - findGenotyp700BySnpId
	 *
	 */
	@Transactional
	public Set<Genotyp700> findGenotyp700BySnpId(Integer snpId) throws DataAccessException {

		return findGenotyp700BySnpId(snpId, -1, -1);
	}

	/**
	 * JPQL Query - findGenotyp700BySnpId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Genotyp700> findGenotyp700BySnpId(Integer snpId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGenotyp700BySnpId", startResult, maxRows, snpId);
		return new LinkedHashSet<Genotyp700>(query.getResultList());
	}

	/**
	 * JPQL Query - findGenotyp700ByQs
	 *
	 */
	@Transactional
	public Set<Genotyp700> findGenotyp700ByQs(Integer qs) throws DataAccessException {

		return findGenotyp700ByQs(qs, -1, -1);
	}

	/**
	 * JPQL Query - findGenotyp700ByQs
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Genotyp700> findGenotyp700ByQs(Integer qs, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGenotyp700ByQs", startResult, maxRows, qs);
		return new LinkedHashSet<Genotyp700>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(Genotyp700 entity) {
		return true;
	}
}
