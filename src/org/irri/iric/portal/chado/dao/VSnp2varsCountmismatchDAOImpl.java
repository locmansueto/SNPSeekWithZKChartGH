package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
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

import org.hibernate.Session;
import org.irri.iric.portal.chado.domain.VSnp2varsCountmismatch;
import org.irri.iric.portal.domain.Snps2VarsCountmismatch;
import org.irri.iric.portal.genotype.views.Snp2linesId;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnp2varsCountmismatch entities.
 * 
 */
//@Repository("Snps2VarsCountMismatchDAO")
@Repository("Snps2VarsCountMismatchDAO")
@Transactional
public class VSnp2varsCountmismatchDAOImpl extends AbstractJpaDao<VSnp2varsCountmismatch>
		implements VSnp2varsCountmismatchDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VSnp2varsCountmismatch.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnp2varsCountmismatchDAOImpl
	 *
	 */
	public VSnp2varsCountmismatchDAOImpl() {
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
	 * JPQL Query - findVSnp2varsCountmismatchByMismatch
	 *
	 */
	@Transactional
	public Set<VSnp2varsCountmismatch> findVSnp2varsCountmismatchByMismatch(Integer mismatch) throws DataAccessException {

		return findVSnp2varsCountmismatchByMismatch(mismatch, -1, -1);
	}

	/**
	 * JPQL Query - findVSnp2varsCountmismatchByMismatch
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnp2varsCountmismatch> findVSnp2varsCountmismatchByMismatch(Integer mismatch, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnp2varsCountmismatchByMismatch", startResult, maxRows, mismatch);
		return new LinkedHashSet<VSnp2varsCountmismatch>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnp2varsCountmismatchByVar2
	 *
	 */
	@Transactional
	public Set<VSnp2varsCountmismatch> findVSnp2varsCountmismatchByVar2(Integer var2) throws DataAccessException {

		return findVSnp2varsCountmismatchByVar2(var2, -1, -1);
	}

	/**
	 * JPQL Query - findVSnp2varsCountmismatchByVar2
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnp2varsCountmismatch> findVSnp2varsCountmismatchByVar2(Integer var2, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnp2varsCountmismatchByVar2", startResult, maxRows, var2);
		return new LinkedHashSet<VSnp2varsCountmismatch>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVSnp2varsCountmismatchs
	 *
	 */
	@Transactional
	public Set<VSnp2varsCountmismatch> findAllVSnp2varsCountmismatchs() throws DataAccessException {

		return findAllVSnp2varsCountmismatchs(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnp2varsCountmismatchs
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnp2varsCountmismatch> findAllVSnp2varsCountmismatchs(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVSnp2varsCountmismatchs", startResult, maxRows);
		return new LinkedHashSet<VSnp2varsCountmismatch>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnp2varsCountmismatchByVar1
	 *
	 */
	@Transactional
	public Set<VSnp2varsCountmismatch> findVSnp2varsCountmismatchByVar1(Integer var1) throws DataAccessException {

		return findVSnp2varsCountmismatchByVar1(var1, -1, -1);
	}

	/**
	 * JPQL Query - findVSnp2varsCountmismatchByVar1
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnp2varsCountmismatch> findVSnp2varsCountmismatchByVar1(Integer var1, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnp2varsCountmismatchByVar1", startResult, maxRows, var1);
		return new LinkedHashSet<VSnp2varsCountmismatch>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnp2varsCountmismatchByPrimaryKey
	 *
	 */
	@Transactional
	public VSnp2varsCountmismatch findVSnp2varsCountmismatchByPrimaryKey(Integer var1, Integer var2) throws DataAccessException {

		return findVSnp2varsCountmismatchByPrimaryKey(var1, var2, -1, -1);
	}

	/**
	 * JPQL Query - findVSnp2varsCountmismatchByPrimaryKey
	 *
	 */

	@Transactional
	public VSnp2varsCountmismatch findVSnp2varsCountmismatchByPrimaryKey(Integer var1, Integer var2, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnp2varsCountmismatchByPrimaryKey", startResult, maxRows, var1, var2);
			return (org.irri.iric.portal.chado.domain.VSnp2varsCountmismatch) query.getSingleResult();
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
	public boolean canBeMerged(VSnp2varsCountmismatch entity) {
		return true;
	}
	
	
	@Override
	public List<Snps2VarsCountmismatch> countMismatch(Integer chr, BigDecimal start , BigDecimal end) {
	
		String sql = "select var1, var2, count(*) as mismatch from IRIC.VL_SNP_2VARS where " 
			+ " var1nuc<>var2nuc "
			+ " and var1nuc is not null AND var2nuc is not null "
			+ " and chr=" + chr 
			+ " and pos between " + start + " and " + end 
			+ " and var1<=var2 "
			+ " group by var1, var2 "
			+ " order by var1, var2";
			
		return executeSQL(sql);
	}
	
	@Override
	public List<Snps2VarsCountmismatch> countMismatch(Integer chr, BigDecimal start , BigDecimal end, int topN) {
	
		String sql = "select * from " 
				
			+ "(select var1, var2, count(*) as mismatch from IRIC.VL_SNP_2VARS where " 
			+ " var1nuc<>var2nuc "
			+ " and var1nuc is not null AND var2nuc is not null "
			+ " and chr=" + chr 
			+ " and pos between " + start + " and " + end 
			+ " and var1<=var2 "
			+ " group by var1, var2 "
			+ " order by mismatch desc, var1, var2) "
			
			+ " where rownum<=" + topN;
			
		return executeSQL(sql);
	}
	
	@Override
	public List<Snps2VarsCountmismatch> countMismatch(Integer chr, BigDecimal start , BigDecimal end, Set varieties) {
	
		Iterator<BigDecimal> itVar = varieties.iterator();
		StringBuffer buff = new StringBuffer();
		while(itVar.hasNext()) {
			buff.append( itVar.next() );
			if( itVar.hasNext() ) buff.append(",");
		}
		
		String sql = "select var1, var2, count(*) as mismatch from IRIC.VL_SNP_2VARS where " 
			+ " var1nuc<>var2nuc "
			+ " and var1nuc is not null AND var2nuc is not null "
			+ " and chr=" + chr 
			+ " and pos between " + start + " and " + end 
			+ " and var1<=var2 "
			+ " and var1 in (" + buff.toString() + ") "
			+ " and var2 in (" + buff.toString() + ") "
			+ " group by var1, var2 "
			+ " order by mismatch desc, var1, var2";
			
		
		return executeSQL(sql);
	}
	

	private List<Snps2VarsCountmismatch> executeSQL(String sql) {
		//System.out.println("executing :" + sql);
		//log.info("executing :" + sql);
		return  getSession().createSQLQuery(sql).addEntity(VSnp2varsCountmismatch.class).list();
	}
	
	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	
	
}
