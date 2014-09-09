package org.irri.iric.portal.chado.dao;

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
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.domain.VSnpAllvarsCountrefmismatch;
import org.irri.iric.portal.domain.SnpsAllvarsRefMismatch;
import org.irri.iric.portal.genotype.views.IViewCountVarrefMismatchHome;
import org.irri.iric.portal.genotype.views.ViewCount2linesMismatchId;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnpAllvarsCountrefmismatch entities.
 * 
 */
@Repository("SnpsAllvarsRefMismatchDAO")
@Transactional
public class VSnpAllvarsCountrefmismatchDAOImpl extends AbstractJpaDao<VSnpAllvarsCountrefmismatch>
		implements VSnpAllvarsCountrefmismatchDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VSnpAllvarsCountrefmismatch.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnpAllvarsCountrefmismatchDAOImpl
	 *
	 */
	public VSnpAllvarsCountrefmismatchDAOImpl() {
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
	 * JPQL Query - findAllVSnpAllvarsCountrefmismatchs
	 *
	 */
	@Transactional
	public Set<VSnpAllvarsCountrefmismatch> findAllVSnpAllvarsCountrefmismatchs() throws DataAccessException {

		return findAllVSnpAllvarsCountrefmismatchs(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnpAllvarsCountrefmismatchs
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvarsCountrefmismatch> findAllVSnpAllvarsCountrefmismatchs(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVSnpAllvarsCountrefmismatchs", startResult, maxRows);
		return new LinkedHashSet<VSnpAllvarsCountrefmismatch>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpAllvarsCountrefmismatchByVar
	 *
	 */
	@Transactional
	public VSnpAllvarsCountrefmismatch findVSnpAllvarsCountrefmismatchByVar(Integer var) throws DataAccessException {

		return findVSnpAllvarsCountrefmismatchByVar(var, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsCountrefmismatchByVar
	 *
	 */

	@Transactional
	public VSnpAllvarsCountrefmismatch findVSnpAllvarsCountrefmismatchByVar(Integer var, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpAllvarsCountrefmismatchByVar", startResult, maxRows, var);
			return (org.irri.iric.portal.chado.domain.VSnpAllvarsCountrefmismatch) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpAllvarsCountrefmismatchByPrimaryKey
	 *
	 */
	@Transactional
	public VSnpAllvarsCountrefmismatch findVSnpAllvarsCountrefmismatchByPrimaryKey(Integer var) throws DataAccessException {

		return findVSnpAllvarsCountrefmismatchByPrimaryKey(var, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsCountrefmismatchByPrimaryKey
	 *
	 */

	@Transactional
	public VSnpAllvarsCountrefmismatch findVSnpAllvarsCountrefmismatchByPrimaryKey(Integer var, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpAllvarsCountrefmismatchByPrimaryKey", startResult, maxRows, var);
			return (org.irri.iric.portal.chado.domain.VSnpAllvarsCountrefmismatch) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpAllvarsCountrefmismatchByMismatch
	 *
	 */
	@Transactional
	public Set<VSnpAllvarsCountrefmismatch> findVSnpAllvarsCountrefmismatchByMismatch(java.math.BigDecimal mismatch) throws DataAccessException {

		return findVSnpAllvarsCountrefmismatchByMismatch(mismatch, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpAllvarsCountrefmismatchByMismatch
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpAllvarsCountrefmismatch> findVSnpAllvarsCountrefmismatchByMismatch(java.math.BigDecimal mismatch, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpAllvarsCountrefmismatchByMismatch", startResult, maxRows, mismatch);
		return new LinkedHashSet<VSnpAllvarsCountrefmismatch>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VSnpAllvarsCountrefmismatch entity) {
		return true;
	}

	@Override
	public List countMismatches(String chr, Integer start, Integer end) {
		// TODO Auto-generated method stub

			String sql="select var, count(*) as mismatch from IRIC.VL_SNP_ALLVARS where (REFNUC<>VARNUC) and REFNUC is not null AND VARNUC is not null and " 
					+ " chr=" + chr
					+ " and pos between " + start + " and " + end
					+ " group by var order by mismatch desc, var ";
		return executeSQL(sql) ;
	}
	
	private List<SnpsAllvarsRefMismatch> executeSQL(String sql) {
		//System.out.println("executing :" + sql);
		//log.info("executing :" + sql);
		return getSession().createSQLQuery(sql).addEntity(VSnpAllvarsCountrefmismatch.class).list();
	}
	
	private Session getSession() {
		
		return entityManager.unwrap(Session.class);
	}
}
