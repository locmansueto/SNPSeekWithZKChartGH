package org.irri.iric.portal.variety.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.variety.domain.Snps;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage Snps entities.
 * 
 */
@Repository("SnpsDAO")
@Transactional
public class SnpsDAOImpl extends AbstractJpaDao<Snps> implements SnpsDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { Snps.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Development
	 *
	 */
	@PersistenceContext(unitName = "Development")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SnpsDAOImpl
	 *
	 */
	public SnpsDAOImpl() {
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
	 * JPQL Query - findSnpsByPos
	 *
	 */
	@Transactional
	public Set<Snps> findSnpsByPos(java.math.BigDecimal pos) throws DataAccessException {

		return findSnpsByPos(pos, -1, -1);
	}

	/**
	 * JPQL Query - findSnpsByPos
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snps> findSnpsByPos(java.math.BigDecimal pos, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnpsByPos", startResult, maxRows, pos);
		return new LinkedHashSet<Snps>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnpsByMinal
	 *
	 */
	@Transactional
	public Set<Snps> findSnpsByMinal(Integer minal) throws DataAccessException {

		return findSnpsByMinal(minal, -1, -1);
	}

	/**
	 * JPQL Query - findSnpsByMinal
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snps> findSnpsByMinal(Integer minal, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnpsByMinal", startResult, maxRows, minal);
		return new LinkedHashSet<Snps>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnpsByPrimaryKey
	 *
	 */
	@Transactional
	public Snps findSnpsByPrimaryKey(String snpId) throws DataAccessException {

		return findSnpsByPrimaryKey(snpId, -1, -1);
	}

	/**
	 * JPQL Query - findSnpsByPrimaryKey
	 *
	 */

	@Transactional
	public Snps findSnpsByPrimaryKey(String snpId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSnpsByPrimaryKey", startResult, maxRows, snpId);
			return (org.irri.iric.portal.variety.domain.Snps) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSnpsByChrom
	 *
	 */
	@Transactional
	public Set<Snps> findSnpsByChrom(Integer chrom) throws DataAccessException {

		return findSnpsByChrom(chrom, -1, -1);
	}

	/**
	 * JPQL Query - findSnpsByChrom
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snps> findSnpsByChrom(Integer chrom, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnpsByChrom", startResult, maxRows, chrom);
		return new LinkedHashSet<Snps>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllSnpss
	 *
	 */
	@Transactional
	public Set<Snps> findAllSnpss() throws DataAccessException {

		return findAllSnpss(-1, -1);
	}

	/**
	 * JPQL Query - findAllSnpss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snps> findAllSnpss(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSnpss", startResult, maxRows);
		return new LinkedHashSet<Snps>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnpsByMaxal
	 *
	 */
	@Transactional
	public Set<Snps> findSnpsByMaxal(Integer maxal) throws DataAccessException {

		return findSnpsByMaxal(maxal, -1, -1);
	}

	/**
	 * JPQL Query - findSnpsByMaxal
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snps> findSnpsByMaxal(Integer maxal, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnpsByMaxal", startResult, maxRows, maxal);
		return new LinkedHashSet<Snps>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnpsBySnpIdContaining
	 *
	 */
	@Transactional
	public Set<Snps> findSnpsBySnpIdContaining(String snpId) throws DataAccessException {

		return findSnpsBySnpIdContaining(snpId, -1, -1);
	}

	/**
	 * JPQL Query - findSnpsBySnpIdContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Snps> findSnpsBySnpIdContaining(String snpId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnpsBySnpIdContaining", startResult, maxRows, snpId);
		return new LinkedHashSet<Snps>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnpsBySnpId
	 *
	 */
	@Transactional
	public Snps findSnpsBySnpId(String snpId) throws DataAccessException {

		return findSnpsBySnpId(snpId, -1, -1);
	}

	/**
	 * JPQL Query - findSnpsBySnpId
	 *
	 */

	@Transactional
	public Snps findSnpsBySnpId(String snpId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSnpsBySnpId", startResult, maxRows, snpId);
			return (org.irri.iric.portal.variety.domain.Snps) query.getSingleResult();
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
	public boolean canBeMerged(Snps entity) {
		return true;
	}
}
