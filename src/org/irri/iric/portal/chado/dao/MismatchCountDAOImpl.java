package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.irri.iric.portal.chado.domain.MismatchCount;
import org.irri.iric.portal.chado.domain.VSnp2varsCountmismatch;
import org.irri.iric.portal.domain.Snps2VarsCountmismatch;
import org.irri.iric.portal.domain.SnpsAllvarsRefMismatch;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage MismatchCount entities.
 * 
 */
@Repository("MismatchCountDAO")
@Transactional
public class MismatchCountDAOImpl extends AbstractJpaDao<MismatchCount>
		implements MismatchCountDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { MismatchCount.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new MismatchCountDAOImpl
	 *
	 */
	public MismatchCountDAOImpl() {
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
	 * JPQL Query - findMismatchCountByIricStockId
	 *
	 */
	@Transactional
	public Set<MismatchCount> findMismatchCountByIricStockId(java.math.BigDecimal iricStockId) throws DataAccessException {

		return findMismatchCountByIricStockId(iricStockId, -1, -1);
	}

	/**
	 * JPQL Query - findMismatchCountByIricStockId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MismatchCount> findMismatchCountByIricStockId(java.math.BigDecimal iricStockId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMismatchCountByIricStockId", startResult, maxRows, iricStockId);
		return new LinkedHashSet<MismatchCount>(query.getResultList());
	}

	/**
	 * JPQL Query - findMismatchCountByFragEnd
	 *
	 */
	@Transactional
	public Set<MismatchCount> findMismatchCountByFragEnd(Integer fragEnd) throws DataAccessException {

		return findMismatchCountByFragEnd(fragEnd, -1, -1);
	}

	/**
	 * JPQL Query - findMismatchCountByFragEnd
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MismatchCount> findMismatchCountByFragEnd(Integer fragEnd, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMismatchCountByFragEnd", startResult, maxRows, fragEnd);
		return new LinkedHashSet<MismatchCount>(query.getResultList());
	}

	/**
	 * JPQL Query - findMismatchCountByPrimaryKey
	 *
	 */
	@Transactional
	public MismatchCount findMismatchCountByPrimaryKey(Integer mismatchCountId) throws DataAccessException {

		return findMismatchCountByPrimaryKey(mismatchCountId, -1, -1);
	}

	/**
	 * JPQL Query - findMismatchCountByPrimaryKey
	 *
	 */

	@Transactional
	public MismatchCount findMismatchCountByPrimaryKey(Integer mismatchCountId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findMismatchCountByPrimaryKey", startResult, maxRows, mismatchCountId);
			return (org.irri.iric.portal.chado.domain.MismatchCount) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllMismatchCounts
	 *
	 */
	@Transactional
	public Set<MismatchCount> findAllMismatchCounts() throws DataAccessException {

		return findAllMismatchCounts(-1, -1);
	}

	/**
	 * JPQL Query - findAllMismatchCounts
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MismatchCount> findAllMismatchCounts(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllMismatchCounts", startResult, maxRows);
		return new LinkedHashSet<MismatchCount>(query.getResultList());
	}

	/**
	 * JPQL Query - findMismatchCountByMismatchCountId
	 *
	 */
	@Transactional
	public MismatchCount findMismatchCountByMismatchCountId(Integer mismatchCountId) throws DataAccessException {

		return findMismatchCountByMismatchCountId(mismatchCountId, -1, -1);
	}

	/**
	 * JPQL Query - findMismatchCountByMismatchCountId
	 *
	 */

	@Transactional
	public MismatchCount findMismatchCountByMismatchCountId(Integer mismatchCountId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findMismatchCountByMismatchCountId", startResult, maxRows, mismatchCountId);
			return (org.irri.iric.portal.chado.domain.MismatchCount) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findMismatchCountByMismatch
	 *
	 */
	@Transactional
	public Set<MismatchCount> findMismatchCountByMismatch(Integer mismatch) throws DataAccessException {

		return findMismatchCountByMismatch(mismatch, -1, -1);
	}

	/**
	 * JPQL Query - findMismatchCountByMismatch
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MismatchCount> findMismatchCountByMismatch(Integer mismatch, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMismatchCountByMismatch", startResult, maxRows, mismatch);
		return new LinkedHashSet<MismatchCount>(query.getResultList());
	}

	/**
	 * JPQL Query - findMismatchCountByFragStart
	 *
	 */
	@Transactional
	public Set<MismatchCount> findMismatchCountByFragStart(Integer fragStart) throws DataAccessException {

		return findMismatchCountByFragStart(fragStart, -1, -1);
	}

	/**
	 * JPQL Query - findMismatchCountByFragStart
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MismatchCount> findMismatchCountByFragStart(Integer fragStart, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMismatchCountByFragStart", startResult, maxRows, fragStart);
		return new LinkedHashSet<MismatchCount>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(MismatchCount entity) {
		return true;
	}

	@Override
	public List countMismatches(String chr, Integer start, Integer end) {
		// TODO Auto-generated method stub
		
		return countMismatches( chr, start, end, true);
	}

	@Override
	public List countMismatchesInvars(Set<BigDecimal> varIds, String chr,
			Integer start, Integer end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SnpsAllvarsRefMismatch> countMismatches(String chromosome,
			Integer startPos, Integer endPos, boolean isCore) {
		// TODO Auto-generated method stub
		
		//if(!chromosome.equals("7")) throw new RuntimeException("Use chromosme 7 only!");
		/*
		Query query = createNamedQuery("findMismatchCountByFragBetween", -1, -1,  BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos));
		return query.getResultList();
		
		*/
		
		String sql = "select MISMATCH_COUNT_ID, FRAG_START, IRIC_STOCK_ID, MISMATCH, FRAG_END FROM LMANSUETO.MISMATCH_COUNT WHERE FRAG_START<=" + startPos 
				+ " and FRAG_END>=" + endPos
				+ " order by MISMATCH desc";
		
		return executeSQL(sql);
	}

	@Override
	public List<SnpsAllvarsRefMismatch> countMismatchesInvars(
			Set<BigDecimal> setvarIds, String chromosome, Integer startPos,
			Integer endPos, boolean isCore) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private List<SnpsAllvarsRefMismatch> executeSQL(String sql) {
		//System.out.println("executing :" + sql);
		//log.info("executing :" + sql);
		return  getSession().createSQLQuery(sql).addEntity(MismatchCount.class).list();
	}
	
	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
	
}
