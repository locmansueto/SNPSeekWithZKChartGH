package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.chado.domain.VSnpSplicedonor;
import org.irri.iric.portal.domain.SnpsSpliceDonor;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnpSplicedonor entities.
 * 
 */
@Repository("VSnpSplicedonorDAO")
@Transactional
public class VSnpSplicedonorDAOImpl extends AbstractJpaDao<VSnpSplicedonor>
		implements VSnpSplicedonorDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VSnpSplicedonor.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnpSplicedonorDAOImpl
	 *
	 */
	public VSnpSplicedonorDAOImpl() {
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
	 * JPQL Query - findVSnpSplicedonorByPosition
	 *
	 */
	@Transactional
	public Set<VSnpSplicedonor> findVSnpSplicedonorByPosition(Integer position) throws DataAccessException {

		return findVSnpSplicedonorByPosition(position, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSplicedonorByPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSplicedonor> findVSnpSplicedonorByPosition(Integer position, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpSplicedonorByPosition", startResult, maxRows, position);
		return new LinkedHashSet<VSnpSplicedonor>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpSplicedonorByOrganismId
	 *
	 */
	@Transactional
	public Set<VSnpSplicedonor> findVSnpSplicedonorByOrganismId(java.math.BigDecimal organismId) throws DataAccessException {

		return findVSnpSplicedonorByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSplicedonorByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSplicedonor> findVSnpSplicedonorByOrganismId(java.math.BigDecimal organismId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpSplicedonorByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<VSnpSplicedonor>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpSplicedonorByPrimaryKey
	 *
	 */
	@Transactional
	public VSnpSplicedonor findVSnpSplicedonorByPrimaryKey(Integer snpFeatureId) throws DataAccessException {

		return findVSnpSplicedonorByPrimaryKey(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSplicedonorByPrimaryKey
	 *
	 */

	@Transactional
	public VSnpSplicedonor findVSnpSplicedonorByPrimaryKey(Integer snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpSplicedonorByPrimaryKey", startResult, maxRows, snpFeatureId);
			return (org.irri.iric.portal.chado.domain.VSnpSplicedonor) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpSplicedonorBySnpFeatureId
	 *
	 */
	@Transactional
	public VSnpSplicedonor findVSnpSplicedonorBySnpFeatureId(Integer snpFeatureId) throws DataAccessException {

		return findVSnpSplicedonorBySnpFeatureId(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSplicedonorBySnpFeatureId
	 *
	 */

	@Transactional
	public VSnpSplicedonor findVSnpSplicedonorBySnpFeatureId(Integer snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpSplicedonorBySnpFeatureId", startResult, maxRows, snpFeatureId);
			return (org.irri.iric.portal.chado.domain.VSnpSplicedonor) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpSplicedonorBySrcfeatureId
	 *
	 */
	@Transactional
	public Set<VSnpSplicedonor> findVSnpSplicedonorBySrcfeatureId(Integer srcfeatureId) throws DataAccessException {

		return findVSnpSplicedonorBySrcfeatureId(srcfeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSplicedonorBySrcfeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSplicedonor> findVSnpSplicedonorBySrcfeatureId(Integer srcfeatureId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpSplicedonorBySrcfeatureId", startResult, maxRows, srcfeatureId);
		return new LinkedHashSet<VSnpSplicedonor>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVSnpSplicedonors
	 *
	 */
	@Transactional
	public Set<VSnpSplicedonor> findAllVSnpSplicedonors() throws DataAccessException {

		return findAllVSnpSplicedonors(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnpSplicedonors
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSplicedonor> findAllVSnpSplicedonors(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVSnpSplicedonors", startResult, maxRows);
		return new LinkedHashSet<VSnpSplicedonor>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpSplicedonorByChrContaining
	 *
	 */
	@Transactional
	public Set<VSnpSplicedonor> findVSnpSplicedonorByChrContaining(String chr) throws DataAccessException {

		return findVSnpSplicedonorByChrContaining(chr, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSplicedonorByChrContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSplicedonor> findVSnpSplicedonorByChrContaining(String chr, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpSplicedonorByChrContaining", startResult, maxRows, chr);
		return new LinkedHashSet<VSnpSplicedonor>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpSplicedonorByChr
	 *
	 */
	@Transactional
	public Set<VSnpSplicedonor> findVSnpSplicedonorByChr(String chr) throws DataAccessException {

		return findVSnpSplicedonorByChr(chr, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSplicedonorByChr
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSplicedonor> findVSnpSplicedonorByChr(String chr, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpSplicedonorByChr", startResult, maxRows, chr);
		return new LinkedHashSet<VSnpSplicedonor>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VSnpSplicedonor entity) {
		return true;
	}

	@Override
	public Set<SnpsSpliceDonor> findSnpSpliceDonorByChrPosBetween(Integer chr,
			Integer start, Integer end) throws DataAccessException {
		// TODO Auto-generated method stub

		Query query = createNamedQuery("findVSnpSplicedonorByChrPositionBetween", -1,-1, "Chr" + chr, BigDecimal.valueOf(start), BigDecimal.valueOf(end) );
		return new LinkedHashSet<SnpsSpliceDonor>(query.getResultList());
		
	}

	@Override
	public Set<SnpsSpliceDonor> findSnpSpliceDonorByChrPosIn(Integer chr,
			Collection listpos) throws DataAccessException {
		// TODO Auto-generated method stub
		Query query = createNamedQuery("findVSnpSplicedonorByChrPositionIn", -1, -1, "Chr" + chr, listpos);
		return new LinkedHashSet<SnpsSpliceDonor>(query.getResultList());
		
	}
	
	
	
	
}
