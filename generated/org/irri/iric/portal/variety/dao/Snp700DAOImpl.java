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

import org.irri.iric.portal.variety.domain.Snp700;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage Snp700 entities.
 * 
 */
@Repository("Snp700DAO")
@Transactional
public class Snp700DAOImpl extends AbstractJpaDao<Snp700> implements Snp700DAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { Snp700.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Development
	 *
	 */
	@PersistenceContext(unitName = "Development")
	//@Resource(name =  "Development")
	private EntityManager entityManager;

	/**
	 * Instantiates a new Snp700DAOImpl
	 *
	 */
	public Snp700DAOImpl() {
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
	 * JPQL Query - findSnp700ByInfoContaining
	 *
	 */
	@Transactional
	public Set<Snp700> findSnp700ByInfoContaining(String info) throws DataAccessException {

		return findSnp700ByInfoContaining(info, -1, -1);
	}

	/**
	 * JPQL Query - findSnp700ByInfoContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snp700> findSnp700ByInfoContaining(String info, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnp700ByInfoContaining", startResult, maxRows, info);
		return new LinkedHashSet<Snp700>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnp700ByName
	 *
	 */
	@Transactional
	public Set<Snp700> findSnp700ByName(String name) throws DataAccessException {

		return findSnp700ByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findSnp700ByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snp700> findSnp700ByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnp700ByName", startResult, maxRows, name);
		return new LinkedHashSet<Snp700>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnp700ByNameContaining
	 *
	 */
	@Transactional
	public Set<Snp700> findSnp700ByNameContaining(String name) throws DataAccessException {

		return findSnp700ByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findSnp700ByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snp700> findSnp700ByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnp700ByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<Snp700>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnp700ByInfo
	 *
	 */
	@Transactional
	public Set<Snp700> findSnp700ByInfo(String info) throws DataAccessException {

		return findSnp700ByInfo(info, -1, -1);
	}

	/**
	 * JPQL Query - findSnp700ByInfo
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snp700> findSnp700ByInfo(String info, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnp700ByInfo", startResult, maxRows, info);
		return new LinkedHashSet<Snp700>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnp700ByAvgQscore
	 *
	 */
	@Transactional
	public Set<Snp700> findSnp700ByAvgQscore(Integer avgQscore) throws DataAccessException {

		return findSnp700ByAvgQscore(avgQscore, -1, -1);
	}

	/**
	 * JPQL Query - findSnp700ByAvgQscore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snp700> findSnp700ByAvgQscore(Integer avgQscore, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnp700ByAvgQscore", startResult, maxRows, avgQscore);
		return new LinkedHashSet<Snp700>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnp700ByChrContaining
	 *
	 */
	@Transactional
	public Set<Snp700> findSnp700ByChrContaining(String chr) throws DataAccessException {

		return findSnp700ByChrContaining(chr, -1, -1);
	}

	/**
	 * JPQL Query - findSnp700ByChrContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snp700> findSnp700ByChrContaining(String chr, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnp700ByChrContaining", startResult, maxRows, chr);
		return new LinkedHashSet<Snp700>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnp700ByQscore
	 *
	 */
	@Transactional
	public Set<Snp700> findSnp700ByQscore(Integer qscore) throws DataAccessException {

		return findSnp700ByQscore(qscore, -1, -1);
	}

	/**
	 * JPQL Query - findSnp700ByQscore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snp700> findSnp700ByQscore(Integer qscore, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnp700ByQscore", startResult, maxRows, qscore);
		return new LinkedHashSet<Snp700>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnp700ByAltContaining
	 *
	 */
	@Transactional
	public Set<Snp700> findSnp700ByAltContaining(String alt) throws DataAccessException {

		return findSnp700ByAltContaining(alt, -1, -1);
	}

	/**
	 * JPQL Query - findSnp700ByAltContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snp700> findSnp700ByAltContaining(String alt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnp700ByAltContaining", startResult, maxRows, alt);
		return new LinkedHashSet<Snp700>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnp700ByChr
	 *
	 */
	@Transactional
	public Set<Snp700> findSnp700ByChr(String chr) throws DataAccessException {

		return findSnp700ByChr(chr, -1, -1);
	}

	/**
	 * JPQL Query - findSnp700ByChr
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snp700> findSnp700ByChr(String chr, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnp700ByChr", startResult, maxRows, chr);
		return new LinkedHashSet<Snp700>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnp700ByFilter
	 *
	 */
	@Transactional
	public Set<Snp700> findSnp700ByFilter(String filter) throws DataAccessException {

		return findSnp700ByFilter(filter, -1, -1);
	}

	/**
	 * JPQL Query - findSnp700ByFilter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snp700> findSnp700ByFilter(String filter, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnp700ByFilter", startResult, maxRows, filter);
		return new LinkedHashSet<Snp700>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnp700ByPos
	 *
	 */
	@Transactional
	public Set<Snp700> findSnp700ByPos(java.math.BigDecimal pos) throws DataAccessException {

		return findSnp700ByPos(pos, -1, -1);
	}

	/**
	 * JPQL Query - findSnp700ByPos
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snp700> findSnp700ByPos(java.math.BigDecimal pos, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnp700ByPos", startResult, maxRows, pos);
		return new LinkedHashSet<Snp700>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnp700ByAlt
	 *
	 */
	@Transactional
	public Set<Snp700> findSnp700ByAlt(String alt) throws DataAccessException {

		return findSnp700ByAlt(alt, -1, -1);
	}

	/**
	 * JPQL Query - findSnp700ByAlt
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snp700> findSnp700ByAlt(String alt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnp700ByAlt", startResult, maxRows, alt);
		return new LinkedHashSet<Snp700>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllSnp700s
	 *
	 */
	@Transactional
	public Set<Snp700> findAllSnp700s() throws DataAccessException {

		return findAllSnp700s(-1, -1);
	}

	/**
	 * JPQL Query - findAllSnp700s
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snp700> findAllSnp700s(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSnp700s", startResult, maxRows);
		return new LinkedHashSet<Snp700>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnp700ByFormat
	 *
	 */
	@Transactional
	public Set<Snp700> findSnp700ByFormat(String format) throws DataAccessException {

		return findSnp700ByFormat(format, -1, -1);
	}

	/**
	 * JPQL Query - findSnp700ByFormat
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snp700> findSnp700ByFormat(String format, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnp700ByFormat", startResult, maxRows, format);
		return new LinkedHashSet<Snp700>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnp700ByFilterContaining
	 *
	 */
	@Transactional
	public Set<Snp700> findSnp700ByFilterContaining(String filter) throws DataAccessException {

		return findSnp700ByFilterContaining(filter, -1, -1);
	}

	/**
	 * JPQL Query - findSnp700ByFilterContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snp700> findSnp700ByFilterContaining(String filter, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnp700ByFilterContaining", startResult, maxRows, filter);
		return new LinkedHashSet<Snp700>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnp700ByHet
	 *
	 */
	@Transactional
	public Set<Snp700> findSnp700ByHet(Integer het) throws DataAccessException {

		return findSnp700ByHet(het, -1, -1);
	}

	/**
	 * JPQL Query - findSnp700ByHet
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snp700> findSnp700ByHet(Integer het, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnp700ByHet", startResult, maxRows, het);
		return new LinkedHashSet<Snp700>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnp700ByPrimaryKey
	 *
	 */
	@Transactional
	public Snp700 findSnp700ByPrimaryKey(Integer id) throws DataAccessException {

		return findSnp700ByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findSnp700ByPrimaryKey
	 *
	 */

	@Transactional
	public Snp700 findSnp700ByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSnp700ByPrimaryKey", startResult, maxRows, id);
			return (org.irri.iric.portal.variety.domain.Snp700) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSnp700ByRef
	 *
	 */
	@Transactional
	public Set<Snp700> findSnp700ByRef(String ref) throws DataAccessException {

		return findSnp700ByRef(ref, -1, -1);
	}

	/**
	 * JPQL Query - findSnp700ByRef
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snp700> findSnp700ByRef(String ref, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnp700ByRef", startResult, maxRows, ref);
		return new LinkedHashSet<Snp700>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnp700ByFormatContaining
	 *
	 */
	@Transactional
	public Set<Snp700> findSnp700ByFormatContaining(String format) throws DataAccessException {

		return findSnp700ByFormatContaining(format, -1, -1);
	}

	/**
	 * JPQL Query - findSnp700ByFormatContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snp700> findSnp700ByFormatContaining(String format, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnp700ByFormatContaining", startResult, maxRows, format);
		return new LinkedHashSet<Snp700>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnp700ByRefContaining
	 *
	 */
	@Transactional
	public Set<Snp700> findSnp700ByRefContaining(String ref) throws DataAccessException {

		return findSnp700ByRefContaining(ref, -1, -1);
	}

	/**
	 * JPQL Query - findSnp700ByRefContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snp700> findSnp700ByRefContaining(String ref, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnp700ByRefContaining", startResult, maxRows, ref);
		return new LinkedHashSet<Snp700>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnp700ById
	 *
	 */
	@Transactional
	public Snp700 findSnp700ById(Integer id) throws DataAccessException {

		return findSnp700ById(id, -1, -1);
	}

	/**
	 * JPQL Query - findSnp700ById
	 *
	 */

	@Transactional
	public Snp700 findSnp700ById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSnp700ById", startResult, maxRows, id);
			return (org.irri.iric.portal.variety.domain.Snp700) query.getSingleResult();
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
	public boolean canBeMerged(Snp700 entity) {
		return true;
	}
}
