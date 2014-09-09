package org.irri.iric.portal.genotype.dao;

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
import org.irri.iric.portal.domain.Snps2VarsCountmismatch;
import org.irri.iric.portal.genotype.domain.VlSnp2varsCountmismatch;
import org.irri.iric.portal.genotype.views.ViewCount2linesMismatchId;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VlSnp2varsCountmismatch entities.
 * 
 */
@Repository("VlSnp2varsCountmismatchDAO")
@Transactional
public class VlSnp2varsCountmismatchDAOImpl extends AbstractJpaDao<VlSnp2varsCountmismatch>
		implements VlSnp2varsCountmismatchDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VlSnp2varsCountmismatch.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "Production")
	private EntityManager entityManager;


	protected Session getSession() {
		
		return entityManager.unwrap(Session.class);
	}
	
	/**
	 * Instantiates a new VlSnp2varsCountmismatchDAOImpl
	 *
	 */
	public VlSnp2varsCountmismatchDAOImpl() {
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
	 * JPQL Query - findAllVlSnp2varsCountmismatchs
	 *
	 */
	@Transactional
	public Set<VlSnp2varsCountmismatch> findAllVlSnp2varsCountmismatchs() throws DataAccessException {

		return findAllVlSnp2varsCountmismatchs(-1, -1);
	}

	/**
	 * JPQL Query - findAllVlSnp2varsCountmismatchs
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnp2varsCountmismatch> findAllVlSnp2varsCountmismatchs(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVlSnp2varsCountmismatchs", startResult, maxRows);
		return new LinkedHashSet<VlSnp2varsCountmismatch>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlSnp2varsCountmismatchByMismatch
	 *
	 */
	@Transactional
	public Set<VlSnp2varsCountmismatch> findVlSnp2varsCountmismatchByMismatch(Integer mismatch) throws DataAccessException {

		return findVlSnp2varsCountmismatchByMismatch(mismatch, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnp2varsCountmismatchByMismatch
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnp2varsCountmismatch> findVlSnp2varsCountmismatchByMismatch(Integer mismatch, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlSnp2varsCountmismatchByMismatch", startResult, maxRows, mismatch);
		return new LinkedHashSet<VlSnp2varsCountmismatch>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlSnp2varsCountmismatchByVar1
	 *
	 */
	@Transactional
	public Set<VlSnp2varsCountmismatch> findVlSnp2varsCountmismatchByVar1(Integer var1) throws DataAccessException {

		return findVlSnp2varsCountmismatchByVar1(var1, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnp2varsCountmismatchByVar1
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnp2varsCountmismatch> findVlSnp2varsCountmismatchByVar1(Integer var1, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlSnp2varsCountmismatchByVar1", startResult, maxRows, var1);
		return new LinkedHashSet<VlSnp2varsCountmismatch>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlSnp2varsCountmismatchByVar2
	 *
	 */
	@Transactional
	public Set<VlSnp2varsCountmismatch> findVlSnp2varsCountmismatchByVar2(Integer var2) throws DataAccessException {

		return findVlSnp2varsCountmismatchByVar2(var2, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnp2varsCountmismatchByVar2
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnp2varsCountmismatch> findVlSnp2varsCountmismatchByVar2(Integer var2, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlSnp2varsCountmismatchByVar2", startResult, maxRows, var2);
		return new LinkedHashSet<VlSnp2varsCountmismatch>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlSnp2varsCountmismatchByPrimaryKey
	 *
	 */
	@Transactional
	public VlSnp2varsCountmismatch findVlSnp2varsCountmismatchByPrimaryKey(Integer var1, Integer var2) throws DataAccessException {

		return findVlSnp2varsCountmismatchByPrimaryKey(var1, var2, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnp2varsCountmismatchByPrimaryKey
	 *
	 */

	@Transactional
	public VlSnp2varsCountmismatch findVlSnp2varsCountmismatchByPrimaryKey(Integer var1, Integer var2, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVlSnp2varsCountmismatchByPrimaryKey", startResult, maxRows, var1, var2);
			return (org.irri.iric.portal.genotype.domain.VlSnp2varsCountmismatch) query.getSingleResult();
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
	public boolean canBeMerged(VlSnp2varsCountmismatch entity) {
		return true;
	}

	
	public List executeSQL(String sql) {
		//System.out.println("executing :" + sql);
		//log.info("executing :" + sql);
		return getSession().createSQLQuery(sql).addEntity(ViewCount2linesMismatchId.class).list();
	}

	
	public List<Snps2VarsCountmismatch> countMismatch(Integer chr,
			BigDecimal start, BigDecimal end) {
		// TODO Auto-generated method stub
		
		String sql = "select upper(var1) var1, upper(var2) var2, count(*) mismatch from snp_2lines where " 
				+ " var1nuc<>var2nuc "
				+ " and (var1nuc is not null or var2nuc is not null) "
				+ " and chr=" + chr 
				+ " and pos between " + start.intValue() + " and " + end.intValue() 
				+ " and var1<=var2 "
				+ " group by var1, var2 "
				+ " order by var1, var2";
				
		
		return executeSQL(sql);
	}
	
	
}
