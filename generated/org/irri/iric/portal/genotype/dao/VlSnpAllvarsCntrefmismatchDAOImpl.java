package org.irri.iric.portal.genotype.dao;

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
import org.irri.iric.portal.genotype.domain.VlSnpAllvarsCntrefmismatch;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VlSnpAllvarsCntrefmismatch entities.
 * 
 */
@Repository("VlSnpAllvarsCntrefmismatchDAO")
@Transactional
public class VlSnpAllvarsCntrefmismatchDAOImpl extends AbstractJpaDao<VlSnpAllvarsCntrefmismatch>
		implements VlSnpAllvarsCntrefmismatchDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VlSnpAllvarsCntrefmismatch.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VlSnpAllvarsCntrefmismatchDAOImpl
	 *
	 */
	public VlSnpAllvarsCntrefmismatchDAOImpl() {
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
	 * JPQL Query - findVlSnpAllvarsCntrefmismatchByVar
	 *
	 */
	@Transactional
	public VlSnpAllvarsCntrefmismatch findVlSnpAllvarsCntrefmismatchByVar(Integer var) throws DataAccessException {

		return findVlSnpAllvarsCntrefmismatchByVar(var, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnpAllvarsCntrefmismatchByVar
	 *
	 */

	@Transactional
	public VlSnpAllvarsCntrefmismatch findVlSnpAllvarsCntrefmismatchByVar(Integer var, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVlSnpAllvarsCntrefmismatchByVar", startResult, maxRows, var);
			return (org.irri.iric.portal.genotype.domain.VlSnpAllvarsCntrefmismatch) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVlSnpAllvarsCntrefmismatchByPrimaryKey
	 *
	 */
	@Transactional
	public VlSnpAllvarsCntrefmismatch findVlSnpAllvarsCntrefmismatchByPrimaryKey(Integer var) throws DataAccessException {

		return findVlSnpAllvarsCntrefmismatchByPrimaryKey(var, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnpAllvarsCntrefmismatchByPrimaryKey
	 *
	 */

	@Transactional
	public VlSnpAllvarsCntrefmismatch findVlSnpAllvarsCntrefmismatchByPrimaryKey(Integer var, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVlSnpAllvarsCntrefmismatchByPrimaryKey", startResult, maxRows, var);
			return (org.irri.iric.portal.genotype.domain.VlSnpAllvarsCntrefmismatch) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVlSnpAllvarsCntrefmismatchByMismatch
	 *
	 */
	@Transactional
	public Set<VlSnpAllvarsCntrefmismatch> findVlSnpAllvarsCntrefmismatchByMismatch(Integer mismatch) throws DataAccessException {

		return findVlSnpAllvarsCntrefmismatchByMismatch(mismatch, -1, -1);
	}

	/**
	 * JPQL Query - findVlSnpAllvarsCntrefmismatchByMismatch
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnpAllvarsCntrefmismatch> findVlSnpAllvarsCntrefmismatchByMismatch(Integer mismatch, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlSnpAllvarsCntrefmismatchByMismatch", startResult, maxRows, mismatch);
		return new LinkedHashSet<VlSnpAllvarsCntrefmismatch>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVlSnpAllvarsCntrefmismatchs
	 *
	 */
	@Transactional
	public Set<VlSnpAllvarsCntrefmismatch> findAllVlSnpAllvarsCntrefmismatchs() throws DataAccessException {

		return findAllVlSnpAllvarsCntrefmismatchs(-1, -1);
	}

	/**
	 * JPQL Query - findAllVlSnpAllvarsCntrefmismatchs
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlSnpAllvarsCntrefmismatch> findAllVlSnpAllvarsCntrefmismatchs(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVlSnpAllvarsCntrefmismatchs", startResult, maxRows);
		return new LinkedHashSet<VlSnpAllvarsCntrefmismatch>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VlSnpAllvarsCntrefmismatch entity) {
		return true;
	}
	
	

	public List executeSQL(String sql) {
		//System.out.println("executing :" + sql);
		//log.info("executing :" + sql);
		return getSession().createSQLQuery(sql).addEntity(VlSnpAllvarsCntrefmismatch.class).list();
	}

	@Override
	public List countMismatches(String chr, Integer start, Integer end) {
		// TODO Auto-generated method stub

		String onlymismatchsql = " (REFNUC is not null or VARNUC is not null) and  REFNUC<>VARNUC and  ";
		
		String sql="select VAR, count(*) as mismatch from VL_SNP_ALLVARS where " +  onlymismatchsql 
				+ " chr=" + chr 
				+ " and pos between " + start + " and " + end
				+ " group by VAR  order by mismatch desc, VAR";
		
		return executeSQL(sql);
	}
	


	protected Session getSession() {
		
		return entityManager.unwrap(Session.class);
	}
	
}
