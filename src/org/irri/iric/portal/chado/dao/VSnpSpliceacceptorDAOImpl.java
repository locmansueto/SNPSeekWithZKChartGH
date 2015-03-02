package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.chado.domain.VSnpSpliceacceptor;
import org.irri.iric.portal.domain.SnpsSpliceAcceptor;
import org.irri.iric.portal.domain.SnpsSpliceDonor;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnpSpliceacceptor entities.
 * 
 */
@Repository("VSnpSpliceacceptorDAO")
@Transactional
public class VSnpSpliceacceptorDAOImpl extends AbstractJpaDao<VSnpSpliceacceptor>
		implements VSnpSpliceacceptorDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VSnpSpliceacceptor.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnpSpliceacceptorDAOImpl
	 *
	 */
	public VSnpSpliceacceptorDAOImpl() {
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
	 * JPQL Query - findVSnpSpliceacceptorByPosition
	 *
	 */
	@Transactional
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByPosition(Integer position) throws DataAccessException {

		return findVSnpSpliceacceptorByPosition(position, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorByPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByPosition(Integer position, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpSpliceacceptorByPosition", startResult, maxRows, position);
		return new LinkedHashSet<VSnpSpliceacceptor>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorBySnpFeatureId
	 *
	 */
	@Transactional
	public VSnpSpliceacceptor findVSnpSpliceacceptorBySnpFeatureId(Integer snpFeatureId) throws DataAccessException {

		return findVSnpSpliceacceptorBySnpFeatureId(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorBySnpFeatureId
	 *
	 */

	@Transactional
	public VSnpSpliceacceptor findVSnpSpliceacceptorBySnpFeatureId(Integer snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpSpliceacceptorBySnpFeatureId", startResult, maxRows, snpFeatureId);
			return (org.irri.iric.portal.chado.domain.VSnpSpliceacceptor) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorByChrContaining
	 *
	 */
	@Transactional
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByChrContaining(String chr) throws DataAccessException {

		return findVSnpSpliceacceptorByChrContaining(chr, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorByChrContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByChrContaining(String chr, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpSpliceacceptorByChrContaining", startResult, maxRows, chr);
		return new LinkedHashSet<VSnpSpliceacceptor>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorByPrimaryKey
	 *
	 */
	@Transactional
	public VSnpSpliceacceptor findVSnpSpliceacceptorByPrimaryKey(Integer snpFeatureId) throws DataAccessException {

		return findVSnpSpliceacceptorByPrimaryKey(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorByPrimaryKey
	 *
	 */

	@Transactional
	public VSnpSpliceacceptor findVSnpSpliceacceptorByPrimaryKey(Integer snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpSpliceacceptorByPrimaryKey", startResult, maxRows, snpFeatureId);
			return (org.irri.iric.portal.chado.domain.VSnpSpliceacceptor) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorByOrganismId
	 *
	 */
	@Transactional
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByOrganismId(java.math.BigDecimal organismId) throws DataAccessException {

		return findVSnpSpliceacceptorByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByOrganismId(java.math.BigDecimal organismId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpSpliceacceptorByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<VSnpSpliceacceptor>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorByChr
	 *
	 */
	@Transactional
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByChr(String chr) throws DataAccessException {

		return findVSnpSpliceacceptorByChr(chr, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorByChr
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByChr(String chr, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpSpliceacceptorByChr", startResult, maxRows, chr);
		return new LinkedHashSet<VSnpSpliceacceptor>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorBySrcfeatureId
	 *
	 */
	@Transactional
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorBySrcfeatureId(Integer srcfeatureId) throws DataAccessException {

		return findVSnpSpliceacceptorBySrcfeatureId(srcfeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorBySrcfeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorBySrcfeatureId(Integer srcfeatureId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpSpliceacceptorBySrcfeatureId", startResult, maxRows, srcfeatureId);
		return new LinkedHashSet<VSnpSpliceacceptor>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVSnpSpliceacceptors
	 *
	 */
	@Transactional
	public Set<VSnpSpliceacceptor> findAllVSnpSpliceacceptors() throws DataAccessException {

		return findAllVSnpSpliceacceptors(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnpSpliceacceptors
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSpliceacceptor> findAllVSnpSpliceacceptors(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVSnpSpliceacceptors", startResult, maxRows);
		return new LinkedHashSet<VSnpSpliceacceptor>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VSnpSpliceacceptor entity) {
		return true;
	}
	

	@Override
	public Set<SnpsSpliceAcceptor> findSnpSpliceAcceptorByChrPosBetween(
			Integer chr, Integer start, Integer end) throws DataAccessException {
		// TODO Auto-generated method stub
		Query query = createNamedQuery("findVSnpSpliceacceptorByChrPositionBetween", -1,-1, "Chr" + chr, BigDecimal.valueOf(start), BigDecimal.valueOf(end) );
		return new LinkedHashSet<SnpsSpliceAcceptor>(query.getResultList());
	}

	@Override
	public Set<SnpsSpliceAcceptor> findSnpSpliceAcceptorByChrPosIn(Integer chr,
			Collection listpos) throws DataAccessException {
		// TODO Auto-generated method stub
		Query query = createNamedQuery("findVSnpSpliceacceptorByChrPositionIn", -1, -1, "Chr" + chr, listpos);
		return new LinkedHashSet<SnpsSpliceAcceptor>(query.getResultList());
	}
	
	
	
	
}
