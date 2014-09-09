package org.irri.iric.portal.chado.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.chado.domain.VSnpAllvars2;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnpAllvars2 entities.
 * 
 */
@Repository("VSnpAllvars2DAO")
@Transactional
public class VSnpAllvars2DAOImpl extends AbstractJpaDao<VSnpAllvars2> implements
		VSnpAllvars2DAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VSnpAllvars2.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnpAllvars2DAOImpl
	 *
	 */
	public VSnpAllvars2DAOImpl() {
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
	 * JPQL Query - findVSnpAllvars2ByPos
	 *
	 */
	@Transactional
	public Set<VSnpAllvars2> findVSnpAllvars2ByPos(java.math.BigDecimal pos) throws DataAccessException {

		return findVSnpAllvars2ByPos(pos, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvars2ByPos
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvars2> findVSnpAllvars2ByPos(java.math.BigDecimal pos, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvars2ByPos", startResult, maxRows, pos);
		return new LinkedHashSet<VSnpAllvars2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpAllvars2ByRefnucContaining
	 *
	 */
	@Transactional
	public Set<VSnpAllvars2> findVSnpAllvars2ByRefnucContaining(String refnuc) throws DataAccessException {

		return findVSnpAllvars2ByRefnucContaining(refnuc, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvars2ByRefnucContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvars2> findVSnpAllvars2ByRefnucContaining(String refnuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvars2ByRefnucContaining", startResult, maxRows, refnuc);
		return new LinkedHashSet<VSnpAllvars2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpAllvars2ByVarnucContaining
	 *
	 */
	@Transactional
	public Set<VSnpAllvars2> findVSnpAllvars2ByVarnucContaining(String varnuc) throws DataAccessException {

		return findVSnpAllvars2ByVarnucContaining(varnuc, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvars2ByVarnucContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvars2> findVSnpAllvars2ByVarnucContaining(String varnuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvars2ByVarnucContaining", startResult, maxRows, varnuc);
		return new LinkedHashSet<VSnpAllvars2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpAllvars2ByIricStockId
	 *
	 */
	@Transactional
	public Set<VSnpAllvars2> findVSnpAllvars2ByIricStockId(Integer iricStockId) throws DataAccessException {

		return findVSnpAllvars2ByIricStockId(iricStockId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvars2ByIricStockId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvars2> findVSnpAllvars2ByIricStockId(Integer iricStockId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvars2ByIricStockId", startResult, maxRows, iricStockId);
		return new LinkedHashSet<VSnpAllvars2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpAllvars2ByRefnuc
	 *
	 */
	@Transactional
	public Set<VSnpAllvars2> findVSnpAllvars2ByRefnuc(String refnuc) throws DataAccessException {

		return findVSnpAllvars2ByRefnuc(refnuc, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvars2ByRefnuc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvars2> findVSnpAllvars2ByRefnuc(String refnuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvars2ByRefnuc", startResult, maxRows, refnuc);
		return new LinkedHashSet<VSnpAllvars2>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVSnpAllvars2s
	 *
	 */
	@Transactional
	public Set<VSnpAllvars2> findAllVSnpAllvars2s() throws DataAccessException {

		return findAllVSnpAllvars2s(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnpAllvars2s
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvars2> findAllVSnpAllvars2s(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVSnpAllvars2s", startResult, maxRows);
		return new LinkedHashSet<VSnpAllvars2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpAllvars2ByPrimaryKey
	 *
	 */
	@Transactional
	public VSnpAllvars2 findVSnpAllvars2ByPrimaryKey(Integer snpFeatureId, Integer iricStockId) throws DataAccessException {

		return findVSnpAllvars2ByPrimaryKey(snpFeatureId, iricStockId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvars2ByPrimaryKey
	 *
	 */

	@Transactional
	public VSnpAllvars2 findVSnpAllvars2ByPrimaryKey(Integer snpFeatureId, Integer iricStockId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpAllvars2ByPrimaryKey", startResult, maxRows, snpFeatureId, iricStockId);
			return (org.irri.iric.portal.chado.domain.VSnpAllvars2) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpAllvars2BySnpFeatureId
	 *
	 */
	@Transactional
	public Set<VSnpAllvars2> findVSnpAllvars2BySnpFeatureId(Integer snpFeatureId) throws DataAccessException {

		return findVSnpAllvars2BySnpFeatureId(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvars2BySnpFeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvars2> findVSnpAllvars2BySnpFeatureId(Integer snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvars2BySnpFeatureId", startResult, maxRows, snpFeatureId);
		return new LinkedHashSet<VSnpAllvars2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpAllvars2ByVarnuc
	 *
	 */
	@Transactional
	public Set<VSnpAllvars2> findVSnpAllvars2ByVarnuc(String varnuc) throws DataAccessException {

		return findVSnpAllvars2ByVarnuc(varnuc, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvars2ByVarnuc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvars2> findVSnpAllvars2ByVarnuc(String varnuc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvars2ByVarnuc", startResult, maxRows, varnuc);
		return new LinkedHashSet<VSnpAllvars2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpAllvars2ByChr
	 *
	 */
	@Transactional
	public Set<VSnpAllvars2> findVSnpAllvars2ByChr(Integer chr) throws DataAccessException {

		return findVSnpAllvars2ByChr(chr, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvars2ByChr
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvars2> findVSnpAllvars2ByChr(Integer chr, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvars2ByChr", startResult, maxRows, chr);
		return new LinkedHashSet<VSnpAllvars2>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VSnpAllvars2 entity) {
		return true;
	}
}
