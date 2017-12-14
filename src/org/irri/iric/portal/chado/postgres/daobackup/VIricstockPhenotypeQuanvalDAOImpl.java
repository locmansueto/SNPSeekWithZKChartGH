package org.irri.iric.portal.chado.postgres.daobackup;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.chado.postgres.domain.VIricstockPhenotypeQuanval;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VIricstockPhenotypeQuanval entities.
 * 
 */
@Repository("VIricstockPhenotypeQuanvalDAO")
@Primary()
@Transactional
public class VIricstockPhenotypeQuanvalDAOImpl extends AbstractJpaDao<VIricstockPhenotypeQuanval>
		implements VIricstockPhenotypeQuanvalDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VIricstockPhenotypeQuanval.class }));

	/**
	 * EntityManager injected by Spring for persistence unit VM_IRIC
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
	 * JPQL Query - findVIricstockPhenotypeQuanvalByPrimaryKey
	 *
	 */
	@Transactional
	public VIricstockPhenotypeQuanval findVIricstockPhenotypeQuanvalByPrimaryKey(Integer phenotypeId, java.math.BigDecimal quanValue) throws DataAccessException {

		return findVIricstockPhenotypeQuanvalByPrimaryKey(phenotypeId, quanValue, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeQuanvalByPrimaryKey
	 *
	 */

	@Transactional
	public VIricstockPhenotypeQuanval findVIricstockPhenotypeQuanvalByPrimaryKey(Integer phenotypeId, java.math.BigDecimal quanValue, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVIricstockPhenotypeQuanvalByPrimaryKey", startResult, maxRows, phenotypeId, quanValue);
			return (org.irri.iric.portal.chado.postgres.domain.VIricstockPhenotypeQuanval) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeQuanvalByQuanValue
	 *
	 */
	@Transactional
	public Set<VIricstockPhenotypeQuanval> findVIricstockPhenotypeQuanvalByQuanValue(java.math.BigDecimal quanValue) throws DataAccessException {

		return findVIricstockPhenotypeQuanvalByQuanValue(quanValue, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeQuanvalByQuanValue
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPhenotypeQuanval> findVIricstockPhenotypeQuanvalByQuanValue(java.math.BigDecimal quanValue, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPhenotypeQuanvalByQuanValue", startResult, maxRows, quanValue);
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
	 * JPQL Query - findVIricstockPhenotypeQuanvalByPhenotypeId
	 *
	 */
	@Transactional
	public Set<VIricstockPhenotypeQuanval> findVIricstockPhenotypeQuanvalByPhenotypeId(Integer phenotypeId) throws DataAccessException {

		return findVIricstockPhenotypeQuanvalByPhenotypeId(phenotypeId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeQuanvalByPhenotypeId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPhenotypeQuanval> findVIricstockPhenotypeQuanvalByPhenotypeId(Integer phenotypeId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPhenotypeQuanvalByPhenotypeId", startResult, maxRows, phenotypeId);
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
	public Set getUniqueValues(BigDecimal typeId) {
		// TODO Auto-generated method stub
		return this.findVIricstockPhenotypeQuanvalByPhenotypeId(typeId.intValue());
	}
	
	
	
}
