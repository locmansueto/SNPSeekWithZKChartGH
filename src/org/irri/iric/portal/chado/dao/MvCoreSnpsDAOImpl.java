package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.chado.domain.MvCoreSnps;
import org.irri.iric.portal.chado.domain.VSnpAllvarsPos;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage MvCoreSnps entities.
 * 
 */
@Repository("MvCoreSnpsDAO")
@Transactional
public class MvCoreSnpsDAOImpl extends AbstractJpaDao<MvCoreSnps> implements
		MvCoreSnpsDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { MvCoreSnps.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new MvCoreSnpsDAOImpl
	 *
	 */
	public MvCoreSnpsDAOImpl() {
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
	 * JPQL Query - findAllMvCoreSnpss
	 *
	 */
	@Transactional
	public Set<MvCoreSnps> findAllMvCoreSnpss() throws DataAccessException {

		return findAllMvCoreSnpss(-1, -1);
	}

	/**
	 * JPQL Query - findAllMvCoreSnpss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvCoreSnps> findAllMvCoreSnpss(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllMvCoreSnpss", startResult, maxRows);
		return new LinkedHashSet<MvCoreSnps>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvCoreSnpsBySnpFeatureId
	 *
	 */
	@Transactional
	public MvCoreSnps findMvCoreSnpsBySnpFeatureId(Integer snpFeatureId) throws DataAccessException {

		return findMvCoreSnpsBySnpFeatureId(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findMvCoreSnpsBySnpFeatureId
	 *
	 */

	@Transactional
	public MvCoreSnps findMvCoreSnpsBySnpFeatureId(Integer snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findMvCoreSnpsBySnpFeatureId", startResult, maxRows, snpFeatureId);
			return (org.irri.iric.portal.chado.domain.MvCoreSnps) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findMvCoreSnpsBySrcfeatureId
	 *
	 */
	@Transactional
	public Set<MvCoreSnps> findMvCoreSnpsBySrcfeatureId(Integer srcfeatureId) throws DataAccessException {

		return findMvCoreSnpsBySrcfeatureId(srcfeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findMvCoreSnpsBySrcfeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvCoreSnps> findMvCoreSnpsBySrcfeatureId(Integer srcfeatureId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvCoreSnpsBySrcfeatureId", startResult, maxRows, srcfeatureId);
		return new LinkedHashSet<MvCoreSnps>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvCoreSnpsByRefcallContaining
	 *
	 */
	@Transactional
	public Set<MvCoreSnps> findMvCoreSnpsByRefcallContaining(String refcall) throws DataAccessException {

		return findMvCoreSnpsByRefcallContaining(refcall, -1, -1);
	}

	/**
	 * JPQL Query - findMvCoreSnpsByRefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvCoreSnps> findMvCoreSnpsByRefcallContaining(String refcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvCoreSnpsByRefcallContaining", startResult, maxRows, refcall);
		return new LinkedHashSet<MvCoreSnps>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvCoreSnpsByPrimaryKey
	 *
	 */
	@Transactional
	public MvCoreSnps findMvCoreSnpsByPrimaryKey(Integer snpFeatureId) throws DataAccessException {

		return findMvCoreSnpsByPrimaryKey(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findMvCoreSnpsByPrimaryKey
	 *
	 */

	@Transactional
	public MvCoreSnps findMvCoreSnpsByPrimaryKey(Integer snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findMvCoreSnpsByPrimaryKey", startResult, maxRows, snpFeatureId);
			return (org.irri.iric.portal.chado.domain.MvCoreSnps) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findMvCoreSnpsByName
	 *
	 */
	@Transactional
	public Set<MvCoreSnps> findMvCoreSnpsByName(String name) throws DataAccessException {

		return findMvCoreSnpsByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findMvCoreSnpsByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvCoreSnps> findMvCoreSnpsByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvCoreSnpsByName", startResult, maxRows, name);
		return new LinkedHashSet<MvCoreSnps>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvCoreSnpsByNameContaining
	 *
	 */
	@Transactional
	public Set<MvCoreSnps> findMvCoreSnpsByNameContaining(String name) throws DataAccessException {

		return findMvCoreSnpsByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findMvCoreSnpsByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvCoreSnps> findMvCoreSnpsByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvCoreSnpsByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<MvCoreSnps>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvCoreSnpsByPosition
	 *
	 */
	@Transactional
	public Set<MvCoreSnps> findMvCoreSnpsByPosition(java.math.BigDecimal position) throws DataAccessException {

		return findMvCoreSnpsByPosition(position, -1, -1);
	}

	/**
	 * JPQL Query - findMvCoreSnpsByPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvCoreSnps> findMvCoreSnpsByPosition(java.math.BigDecimal position, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvCoreSnpsByPosition", startResult, maxRows, position);
		return new LinkedHashSet<MvCoreSnps>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvCoreSnpsByRefcall
	 *
	 */
	@Transactional
	public Set<MvCoreSnps> findMvCoreSnpsByRefcall(String refcall) throws DataAccessException {

		return findMvCoreSnpsByRefcall(refcall, -1, -1);
	}

	/**
	 * JPQL Query - findMvCoreSnpsByRefcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvCoreSnps> findMvCoreSnpsByRefcall(String refcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvCoreSnpsByRefcall", startResult, maxRows, refcall);
		return new LinkedHashSet<MvCoreSnps>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(MvCoreSnps entity) {
		return true;
	}

	@Override
	public List getSNPs(String chromosome, Integer startPos, Integer endPos, BigDecimal type) {
		// TODO Auto-generated method stub
		List list = new java.util.ArrayList();
		
		list.addAll(findVSnpAllvarsPosByChrPosBetween(Integer.valueOf(chromosome), new BigDecimal(startPos), new BigDecimal(endPos)));
		
		return list;
	}
	
	
	

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvarsPos> findVSnpAllvarsPosByChrPosBetween(Integer chr, java.math.BigDecimal start, java.math.BigDecimal end) throws DataAccessException {
		
	//	System.out.println(chr + "  " +  chr.TYPE);
		Query query = createNamedQuery("findVSnpAllvarsPosBySrcfeatureidPosBetween", -1, -1, chr+2 , start, end);
		return new LinkedHashSet<VSnpAllvarsPos>(query.getResultList());
		
	//	return getSNPs(chr, start, end, -1, -1);
	}

	@Override
	public List getSNPs(String chromosome, Integer startPos, Integer endPos, BigDecimal type,
			int firstRow, int maxRows) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getSNPsInChromosome(String chr, List posset, BigDecimal type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getSNPs(Collection varids, String chromosome, Integer startPos,
			Integer endPos, BigDecimal type, int firstRow, int maxRows) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
}
