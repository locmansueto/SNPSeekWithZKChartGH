package org.irri.iric.portal.flatfile.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

//import javassist.bytecode.Descriptor.Iterator;





import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.flatfile.domain.SnpcoreRefposindex;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage SnpcoreRefposindex entities.
 * 
 */
//@Repository("SnpcoreRefposindexDAO")
@Repository("SnpcoreRefposindexDAO")
@Transactional
public class SnpcoreRefposindexDAOImpl extends AbstractJpaDao<SnpcoreRefposindex>
		implements SnpcoreRefposindexDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SnpcoreRefposindex.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SnpcoreRefposindexDAOImpl
	 *
	 */
	public SnpcoreRefposindexDAOImpl() {
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
	 * JPQL Query - findSnpcoreRefposindexByPosition
	 *
	 */
	@Transactional
	public Set<SnpcoreRefposindex> findSnpcoreRefposindexByPosition(java.math.BigDecimal position) throws DataAccessException {

		return findSnpcoreRefposindexByPosition(position, -1, -1);
	}

	/**
	 * JPQL Query - findSnpcoreRefposindexByPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnpcoreRefposindex> findSnpcoreRefposindexByPosition(java.math.BigDecimal position, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnpcoreRefposindexByPosition", startResult, maxRows, position);
		return new LinkedHashSet<SnpcoreRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllSnpcoreRefposindexs
	 *
	 */
	@Transactional
	public Set<SnpcoreRefposindex> findAllSnpcoreRefposindexs() throws DataAccessException {

		return findAllSnpcoreRefposindexs(-1, -1);
	}

	/**
	 * JPQL Query - findAllSnpcoreRefposindexs
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnpcoreRefposindex> findAllSnpcoreRefposindexs(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSnpcoreRefposindexs", startResult, maxRows);
		return new LinkedHashSet<SnpcoreRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnpcoreRefposindexByRefcall
	 *
	 */
	@Transactional
	public Set<SnpcoreRefposindex> findSnpcoreRefposindexByRefcall(String refcall) throws DataAccessException {

		return findSnpcoreRefposindexByRefcall(refcall, -1, -1);
	}

	/**
	 * JPQL Query - findSnpcoreRefposindexByRefcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnpcoreRefposindex> findSnpcoreRefposindexByRefcall(String refcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnpcoreRefposindexByRefcall", startResult, maxRows, refcall);
		return new LinkedHashSet<SnpcoreRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnpcoreRefposindexByAllelesIndex
	 *
	 */
	@Transactional
	public Set<SnpcoreRefposindex> findSnpcoreRefposindexByAllelesIndex(java.math.BigDecimal allelesIndex) throws DataAccessException {

		return findSnpcoreRefposindexByAllelesIndex(allelesIndex, -1, -1);
	}

	/**
	 * JPQL Query - findSnpcoreRefposindexByAllelesIndex
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnpcoreRefposindex> findSnpcoreRefposindexByAllelesIndex(java.math.BigDecimal allelesIndex, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnpcoreRefposindexByAllelesIndex", startResult, maxRows, allelesIndex);
		return new LinkedHashSet<SnpcoreRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnpcoreRefposindexByPrimaryKey
	 *
	 */
	@Transactional
	public SnpcoreRefposindex findSnpcoreRefposindexByPrimaryKey(Integer snpFeatureId) throws DataAccessException {

		return findSnpcoreRefposindexByPrimaryKey(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findSnpcoreRefposindexByPrimaryKey
	 *
	 */

	@Transactional
	public SnpcoreRefposindex findSnpcoreRefposindexByPrimaryKey(Integer snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSnpcoreRefposindexByPrimaryKey", startResult, maxRows, snpFeatureId);
			return (org.irri.iric.portal.flatfile.domain.SnpcoreRefposindex) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSnpcoreRefposindexByRefcallContaining
	 *
	 */
	@Transactional
	public Set<SnpcoreRefposindex> findSnpcoreRefposindexByRefcallContaining(String refcall) throws DataAccessException {

		return findSnpcoreRefposindexByRefcallContaining(refcall, -1, -1);
	}

	/**
	 * JPQL Query - findSnpcoreRefposindexByRefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnpcoreRefposindex> findSnpcoreRefposindexByRefcallContaining(String refcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnpcoreRefposindexByRefcallContaining", startResult, maxRows, refcall);
		return new LinkedHashSet<SnpcoreRefposindex>(query.getResultList());
	}

	/**
	 * JPQL Query - findSnpcoreRefposindexBySnpFeatureId
	 *
	 */
	@Transactional
	public SnpcoreRefposindex findSnpcoreRefposindexBySnpFeatureId(Integer snpFeatureId) throws DataAccessException {

		return findSnpcoreRefposindexBySnpFeatureId(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findSnpcoreRefposindexBySnpFeatureId
	 *
	 */

	@Transactional
	public SnpcoreRefposindex findSnpcoreRefposindexBySnpFeatureId(Integer snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSnpcoreRefposindexBySnpFeatureId", startResult, maxRows, snpFeatureId);
			return (org.irri.iric.portal.flatfile.domain.SnpcoreRefposindex) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSnpcoreRefposindexByChromosome
	 *
	 */
	@Transactional
	public Set<SnpcoreRefposindex> findSnpcoreRefposindexByChromosome(java.math.BigDecimal chromosome) throws DataAccessException {

		return findSnpcoreRefposindexByChromosome(chromosome, -1, -1);
	}

	/**
	 * JPQL Query - findSnpcoreRefposindexByChromosome
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SnpcoreRefposindex> findSnpcoreRefposindexByChromosome(java.math.BigDecimal chromosome, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSnpcoreRefposindexByChromosome", startResult, maxRows, chromosome);
		return new LinkedHashSet<SnpcoreRefposindex>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(SnpcoreRefposindex entity) {
		return true;
	}
	
	
	@Override
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
	public List getSNPsInChromosome(String chr, Collection posset, BigDecimal type) {
		List offPos = new ArrayList();
		Iterator it=posset.iterator();
		while(it.hasNext()) {
			offPos.add(  BigDecimal.valueOf(  ((BigDecimal)it.next()).intValue()-1 ));
		}
		Query query = createNamedQuery("findSnpcoreRefposindexByChrPosIn", -1, -1, BigDecimal.valueOf(Long.valueOf(chr)),  posset, type);
		return query.getResultList();
		
	}

	
}
