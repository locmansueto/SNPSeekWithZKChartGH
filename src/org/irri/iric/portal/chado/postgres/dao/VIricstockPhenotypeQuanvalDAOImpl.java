package org.irri.iric.portal.chado.oracle.dao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.chado.oracle.domain.VIricstockPhenotypeQuanval;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VIricstockPhenotypeQuanval entities.
 * 
 */
//@Repository("VIricstockPhenotypeQuanvalDAO")
@Repository("VCvPhenotypeQuanValuesDAO")
@Transactional
public class VIricstockPhenotypeQuanvalDAOImpl extends AbstractJpaDao<VIricstockPhenotypeQuanval>
		implements VIricstockPhenotypeQuanvalDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VIricstockPhenotypeQuanval.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VIricstockPhenotypeQuanvalDAOImpl
	 *
	 */
	public VIricstockPhenotypeQuanvalDAOImpl() {
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
	 * JPQL Query - findVIricstockPhenotypeQuanvalByQuanValue
	 *
	 */
	@Transactional
	public Set<VIricstockPhenotypeQuanval> findVIricstockPhenotypeQuanvalByQuanValue(BigDecimal quanValue) throws DataAccessException {

		return findVIricstockPhenotypeQuanvalByQuanValue(quanValue, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeQuanvalByQuanValue
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPhenotypeQuanval> findVIricstockPhenotypeQuanvalByQuanValue(BigDecimal quanValue, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPhenotypeQuanvalByQuanValue", startResult, maxRows, quanValue);
		return new LinkedHashSet<VIricstockPhenotypeQuanval>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeQuanvalByPrimaryKey
	 *
	 */
	@Transactional
	public VIricstockPhenotypeQuanval findVIricstockPhenotypeQuanvalByPrimaryKey(BigDecimal quanValue, BigDecimal phenotypeId) throws DataAccessException {

		return findVIricstockPhenotypeQuanvalByPrimaryKey(quanValue, phenotypeId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeQuanvalByPrimaryKey
	 *
	 */

	@Transactional
	public VIricstockPhenotypeQuanval findVIricstockPhenotypeQuanvalByPrimaryKey(BigDecimal quanValue, BigDecimal phenotypeId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVIricstockPhenotypeQuanvalByPrimaryKey", startResult, maxRows, quanValue, phenotypeId);
			return (org.irri.iric.portal.chado.oracle.domain.VIricstockPhenotypeQuanval) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeQuanvalByPhenotypeId
	 *
	 */
	@Transactional
	public Set<VIricstockPhenotypeQuanval> findVIricstockPhenotypeQuanvalByPhenotypeId(BigDecimal phenotypeId, String dataset) throws DataAccessException {

		return findVIricstockPhenotypeQuanvalByPhenotypeId(phenotypeId,dataset,  -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeQuanvalByPhenotypeId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPhenotypeQuanval> findVIricstockPhenotypeQuanvalByPhenotypeId(BigDecimal phenotypeId,  String dataset, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPhenotypeQuanvalByPhenotypeId", startResult, maxRows, phenotypeId, dataset);
		return new LinkedHashSet<VIricstockPhenotypeQuanval>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVIricstockPhenotypeQuanvals
	 *
	 */
	@Transactional
	public Set<VIricstockPhenotypeQuanval> findAllVIricstockPhenotypeQuanvals() throws DataAccessException {

		return findAllVIricstockPhenotypeQuanvals(-1, -1);
	}

	/**
	 * JPQL Query - findAllVIricstockPhenotypeQuanvals
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPhenotypeQuanval> findAllVIricstockPhenotypeQuanvals(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVIricstockPhenotypeQuanvals", startResult, maxRows);
		return new LinkedHashSet<VIricstockPhenotypeQuanval>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VIricstockPhenotypeQuanval entity) {
		return true;
	}

	@Override
	public Set getUniqueValues(BigDecimal typeId, String dataset) {
		// TODO Auto-generated method stub
		return this.findVIricstockPhenotypeQuanvalByPhenotypeId(typeId, dataset);
	}

	@Override
	public Set<VIricstockPhenotypeQuanval> findVIricstockPhenotypeQuanvalByPhenotypeId(
			BigDecimal phenotypeId_1) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<VIricstockPhenotypeQuanval> findVIricstockPhenotypeQuanvalByPhenotypeId(
			BigDecimal phenotypeId_1, int startResult, int maxRows)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	
}
