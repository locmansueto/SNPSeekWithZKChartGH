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

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.domain.VIricstockDistance;
import org.irri.iric.portal.dao.DAOLongQueryProcessor;
import org.irri.iric.portal.domain.VarietyDistance;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VIricstockDistance entities.
 * 
 */
//@Repository("VIricstockDistanceDAO")
@Repository("VarietyDistanceDAO")
@Transactional
public class VIricstockDistanceDAOImpl extends AbstractJpaDao<VIricstockDistance>
		implements VIricstockDistanceDAO {
	
	
	private String requestid = null;
	
	
	

	@Override
	public void setRequestId(String requestid) {
		// TODO Auto-generated method stub
		this.requestid = requestid;
	}

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VIricstockDistance.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VIricstockDistanceDAOImpl
	 *
	 */
	public VIricstockDistanceDAOImpl() {
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
	 * JPQL Query - findAllVIricstockDistances
	 *
	 */
	@Transactional
	public Set<VIricstockDistance> findAllVIricstockDistances() throws DataAccessException {

		return findAllVIricstockDistances(-1, -1);
	}

	/**
	 * JPQL Query - findAllVIricstockDistances
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockDistance> findAllVIricstockDistances(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVIricstockDistances", startResult, maxRows);

		DAOLongQueryProcessor longquery = new  DAOLongQueryProcessor( getEntityManager(),  requestid);
		return new LinkedHashSet<VIricstockDistance>( longquery.executeQuery(query) );
	}

	/**
	 * JPQL Query - findVIricstockDistanceByVar1
	 *
	 */
	@Transactional
	public Set<VIricstockDistance> findVIricstockDistanceByVar1(Integer var1) throws DataAccessException {

		return findVIricstockDistanceByVar1(var1, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockDistanceByVar1
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockDistance> findVIricstockDistanceByVar1(Integer var1, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockDistanceByVar1", startResult, maxRows, var1);
		return new LinkedHashSet<VIricstockDistance>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockDistanceByVar2
	 *
	 */
	@Transactional
	public Set<VIricstockDistance> findVIricstockDistanceByVar2(Integer var2) throws DataAccessException {

		return findVIricstockDistanceByVar2(var2, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockDistanceByVar2
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockDistance> findVIricstockDistanceByVar2(Integer var2, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockDistanceByVar2", startResult, maxRows, var2);
		return new LinkedHashSet<VIricstockDistance>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockDistanceByPrimaryKey
	 *
	 */
	@Transactional
	public VIricstockDistance findVIricstockDistanceByPrimaryKey(Integer var1, Integer var2) throws DataAccessException {

		return findVIricstockDistanceByPrimaryKey(var1, var2, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockDistanceByPrimaryKey
	 *
	 */

	@Transactional
	public VIricstockDistance findVIricstockDistanceByPrimaryKey(Integer var1, Integer var2, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVIricstockDistanceByPrimaryKey", startResult, maxRows, var1, var2);
			return (org.irri.iric.portal.chado.domain.VIricstockDistance) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVIricstockDistanceByDist
	 *
	 */
	@Transactional
	public Set<VIricstockDistance> findVIricstockDistanceByDist(Integer dist) throws DataAccessException {

		return findVIricstockDistanceByDist(dist, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockDistanceByDist
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockDistance> findVIricstockDistanceByDist(Integer dist, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockDistanceByDist", startResult, maxRows, dist);
		return new LinkedHashSet<VIricstockDistance>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VIricstockDistance entity) {
		return true;
	}

	@Override
	public List<VarietyDistance> findVarieties(Set<BigDecimal> germplasms) {
		// TODO Auto-generated method stub
		
		Set[] slicedSet = AppContext.setSlicer(germplasms);
		if(slicedSet.length==1)
			return createNamedQuery("findVIricstockDistanceByVarieties", -1, -1 , slicedSet[0]).getResultList();
		else if(slicedSet.length==2)
			return createNamedQuery("findVIricstockDistanceByVarieties2", -1, -1 , slicedSet[0], slicedSet[1]).getResultList();
		else if(slicedSet.length==3)
			return createNamedQuery("findVIricstockDistanceByVarieties3", -1, -1 , slicedSet[0], slicedSet[1],  slicedSet[2]).getResultList();
		return null;
	}

	@Override
	public List<VarietyDistance> findAllVarieties() {
		// TODO Auto-generated method stub
		Query query = createNamedQuery("findAllVIricstockDistances", -1, -1);
		return query.getResultList();
	}
	
	@Override
	public List<VarietyDistance> findAllVarietiesTopN(Integer topN) {
		// TODO Auto-generated method stub
		//String sql = "select * from  (select * from VIricstockDistance where var1 <= var2 order by dist desc) where rownum <= ?1 ) myVIricstockDistance"),
		
		//Query query = createNamedQuery("findAllVIricstockDistancesTopN", -1, -1,topN);
		Query query = createNamedQuery("findAllVIricstockDistancesTopN", 0, topN);
		return query.getResultList();
	}
	
}
