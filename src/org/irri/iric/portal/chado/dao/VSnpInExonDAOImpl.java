package org.irri.iric.portal.chado.dao;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.domain.VSnpInExon;
import org.irri.iric.portal.dao.SnpsInExonDAO;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnpInExon entities.
 * 
 */
@Repository("SnpInExonDAO")
@Transactional
public class VSnpInExonDAOImpl extends AbstractJpaDao<VSnpInExon> implements
		VSnpInExonDAO, SnpsInExonDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VSnpInExon.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnpInExonDAOImpl
	 *
	 */
	public VSnpInExonDAOImpl() {
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
	 * JPQL Query - findVSnpInExonByPrimaryKey
	 *
	 */
	@Transactional
	public VSnpInExon findVSnpInExonByPrimaryKey(Integer snpFeatureId) throws DataAccessException {

		return findVSnpInExonByPrimaryKey(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpInExonByPrimaryKey
	 *
	 */

	@Transactional
	public VSnpInExon findVSnpInExonByPrimaryKey(Integer snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpInExonByPrimaryKey", startResult, maxRows, snpFeatureId);
			return (org.irri.iric.portal.chado.domain.VSnpInExon) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpInExonBySnpFeatureId
	 *
	 */
	@Transactional
	public VSnpInExon findVSnpInExonBySnpFeatureId(Integer snpFeatureId) throws DataAccessException {

		return findVSnpInExonBySnpFeatureId(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpInExonBySnpFeatureId
	 *
	 */

	@Transactional
	public VSnpInExon findVSnpInExonBySnpFeatureId(Integer snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpInExonBySnpFeatureId", startResult, maxRows, snpFeatureId);
			return (org.irri.iric.portal.chado.domain.VSnpInExon) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllVSnpInExons
	 *
	 */
	@Transactional
	public Set<VSnpInExon> findAllVSnpInExons() throws DataAccessException {

		return findAllVSnpInExons(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnpInExons
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpInExon> findAllVSnpInExons(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVSnpInExons", startResult, maxRows);
		return new LinkedHashSet<VSnpInExon>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VSnpInExon entity) {
		return true;
	}
	
	@Override
	public Set getSnps(Integer chr, Integer start, Integer end) {
		try {
			Query query = createNamedQuery("findVSnpInExonBySnpFeatureIdBetween", -1, -1, AppContext.convertRegion2Snpfeatureid(chr, start) , AppContext.convertRegion2Snpfeatureid(chr, end) );
			return  new LinkedHashSet<VSnpInExon>(query.getResultList());
		} catch (NoResultException nre) {
			return null;
		}
		
	}
	
	@Override
	public Set getSnps(Integer chr, Collection poslist) {
		try {
			Query query = createNamedQuery("findVSnpInExonBySnpFeatureIdIn", -1, -1, AppContext.convertRegion2Snpfeatureid(chr, poslist) );
			return new LinkedHashSet<VSnpInExon>(query.getResultList());
		} catch (NoResultException nre) {
			return null;
		}
		
	}


}
