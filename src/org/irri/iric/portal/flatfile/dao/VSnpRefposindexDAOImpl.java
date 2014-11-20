package org.irri.iric.portal.flatfile.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.flatfile.domain.VSnpRefposindex;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnpRefposindex entities.
 * 
 */
@Repository("VSnpRefposindexDAO")
@Transactional
public class VSnpRefposindexDAOImpl extends AbstractJpaDao<VSnpRefposindex>
		implements VSnpRefposindexDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VSnpRefposindex.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnpRefposindexDAOImpl
	 *
	 */
	public VSnpRefposindexDAOImpl() {
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
	 * JPQL Query - findAllVSnpRefposindexs
	 *
	 */
	@Transactional
	public Set<VSnpRefposindex> findAllVSnpRefposindexs() throws DataAccessException {

		return findAllVSnpRefposindexs(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnpRefposindexs
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposindex> findAllVSnpRefposindexs(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVSnpRefposindexs", startResult, maxRows);
		return new LinkedHashSet<VSnpRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpRefposindexByRefcallContaining
	 *
	 */
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByRefcallContaining(String refcall) throws DataAccessException {

		return findVSnpRefposindexByRefcallContaining(refcall, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposindexByRefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByRefcallContaining(String refcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpRefposindexByRefcallContaining", startResult, maxRows, refcall);
		return new LinkedHashSet<VSnpRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpRefposindexBySnpFeatureId
	 *
	 */
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexBySnpFeatureId(BigDecimal snpFeatureId) throws DataAccessException {

		return findVSnpRefposindexBySnpFeatureId(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposindexBySnpFeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexBySnpFeatureId(BigDecimal snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpRefposindexBySnpFeatureId", startResult, maxRows, snpFeatureId);
		return new LinkedHashSet<VSnpRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpRefposindexByTypeId
	 *
	 */
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByTypeId(BigDecimal typeId) throws DataAccessException {

		return findVSnpRefposindexByTypeId(typeId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposindexByTypeId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByTypeId(BigDecimal typeId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpRefposindexByTypeId", startResult, maxRows, typeId);
		return new LinkedHashSet<VSnpRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpRefposindexByChromosome
	 *
	 */
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByChromosome(java.math.BigDecimal chromosome) throws DataAccessException {

		return findVSnpRefposindexByChromosome(chromosome, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposindexByChromosome
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByChromosome(java.math.BigDecimal chromosome, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpRefposindexByChromosome", startResult, maxRows, chromosome);
		return new LinkedHashSet<VSnpRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpRefposindexByRefcall
	 *
	 */
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByRefcall(String refcall) throws DataAccessException {

		return findVSnpRefposindexByRefcall(refcall, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposindexByRefcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByRefcall(String refcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpRefposindexByRefcall", startResult, maxRows, refcall);
		return new LinkedHashSet<VSnpRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpRefposindexByPosition
	 *
	 */
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByPosition(java.math.BigDecimal position) throws DataAccessException {

		return findVSnpRefposindexByPosition(position, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposindexByPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByPosition(java.math.BigDecimal position, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpRefposindexByPosition", startResult, maxRows, position);
		return new LinkedHashSet<VSnpRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpRefposindexByPrimaryKey
	 *
	 */
	@Transactional
	public VSnpRefposindex findVSnpRefposindexByPrimaryKey(BigDecimal snpFeatureId, BigDecimal typeId) throws DataAccessException {

		return findVSnpRefposindexByPrimaryKey(snpFeatureId, typeId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposindexByPrimaryKey
	 *
	 */

	@Transactional
	public VSnpRefposindex findVSnpRefposindexByPrimaryKey(BigDecimal snpFeatureId, BigDecimal typeId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpRefposindexByPrimaryKey", startResult, maxRows, snpFeatureId, typeId);
			return (org.irri.iric.portal.flatfile.domain.VSnpRefposindex) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpRefposindexByAlleleIndex
	 *
	 */
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByAlleleIndex(java.math.BigDecimal alleleIndex) throws DataAccessException {

		return findVSnpRefposindexByAlleleIndex(alleleIndex, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpRefposindexByAlleleIndex
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpRefposindex> findVSnpRefposindexByAlleleIndex(java.math.BigDecimal alleleIndex, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpRefposindexByAlleleIndex", startResult, maxRows, alleleIndex);
		return new LinkedHashSet<VSnpRefposindex>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VSnpRefposindex entity) {
		return true;
	}

	@Override
	public List getSNPs(String chromosome, Integer startPos, Integer endPos,
			BigDecimal type) {
		// TODO Auto-generated method stub
		return getSNPs( chromosome,  startPos,  endPos, type, -1, -1);
	}

	@Override
	public List getSNPs(String chromosome, Integer startPos, Integer endPos,
			BigDecimal type, int firstRow, int maxRows) {
		// TODO Auto-generated method stub
		Query query = createNamedQuery("findVSnpRefposindexByChrPosBetween", firstRow, maxRows, BigDecimal.valueOf(Long.valueOf(chromosome)), BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos), type);
		return query.getResultList();
	}

	@Override
	public List getSNPsInChromosome(String chr, List posset, BigDecimal type) {
		// TODO Auto-generated method stub
		Query query = createNamedQuery("findVSnpRefposindexByChrPosIn", -1, -1, BigDecimal.valueOf(Long.valueOf(chr)),  posset, type);
		return query.getResultList();
	}
	
	
	
	/*
	 * @Override
	public List getSNPs(String chromosome, Integer startPos, Integer endPos, BigDecimal type) {
		// TODO Auto-generated method stub
		return getSNPs( chromosome,  startPos,  endPos, type, -1, -1);
	}
	
	@Override
	public List getSNPs(String chromosome, Integer startPos, Integer endPos, BigDecimal type, int firstRow, int maxRows) {
		Query query = createNamedQuery("findSnpcoreRefposindexByChrPosBetween", firstRow, maxRows, BigDecimal.valueOf(Long.valueOf(chromosome)), BigDecimal.valueOf(startPos-1), BigDecimal.valueOf(endPos), type);
		return query.getResultList();
	}
	
	
	@Override
	public List getSNPsInChromosome(String chr, List posset, BigDecimal type) {
		List offPos = new ArrayList();
		Iterator it=posset.iterator();
		while(it.hasNext()) {
			offPos.add(  BigDecimal.valueOf(  ((BigDecimal)it.next()).intValue()-1 ));
		}
		Query query = createNamedQuery("findSnpcoreRefposindexByChrPosIn", -1, -1, BigDecimal.valueOf(Long.valueOf(chr)),  posset, type);
		return query.getResultList();
		
	}
	 */
	
	
	
	
}
