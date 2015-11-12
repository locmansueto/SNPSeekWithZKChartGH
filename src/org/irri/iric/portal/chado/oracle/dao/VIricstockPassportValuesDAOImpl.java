package org.irri.iric.portal.chado.oracle.dao;

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

import org.irri.iric.portal.chado.oracle.domain.VIricstockPassportValues;
import org.irri.iric.portal.dao.CvTermUniqueValuesDAO;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VIricstockPassportValues entities.
 * 
 */
//@Repository("VIricstockPassportValuesDAO")
//@Repository("CvTermUniqueValuesDAO")
@Repository("VCvPassportValuesDAO")
@Transactional
public class VIricstockPassportValuesDAOImpl extends AbstractJpaDao<VIricstockPassportValues>
		implements VIricstockPassportValuesDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VIricstockPassportValues.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VIricstockPassportValuesDAOImpl
	 *
	 */
	public VIricstockPassportValuesDAOImpl() {
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
	 * JPQL Query - findVIricstockPassportValuesByTypeId
	 *
	 */
	@Transactional
	public Set<VIricstockPassportValues> findVIricstockPassportValuesByTypeId(BigDecimal typeId) throws DataAccessException {

		return findVIricstockPassportValuesByTypeId(typeId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPassportValuesByTypeId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPassportValues> findVIricstockPassportValuesByTypeId(BigDecimal typeId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPassportValuesByTypeId", startResult, maxRows, typeId);
		return new LinkedHashSet<VIricstockPassportValues>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockPassportValuesByPrimaryKey
	 *
	 */
	@Transactional
	public VIricstockPassportValues findVIricstockPassportValuesByPrimaryKey(String value, BigDecimal typeId) throws DataAccessException {

		return findVIricstockPassportValuesByPrimaryKey(value, typeId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPassportValuesByPrimaryKey
	 *
	 */

	@Transactional
	public VIricstockPassportValues findVIricstockPassportValuesByPrimaryKey(String value, BigDecimal typeId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVIricstockPassportValuesByPrimaryKey", startResult, maxRows, value, typeId);
			return (org.irri.iric.portal.chado.oracle.domain.VIricstockPassportValues) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVIricstockPassportValuesByValueContaining
	 *
	 */
	@Transactional
	public Set<VIricstockPassportValues> findVIricstockPassportValuesByValueContaining(String value) throws DataAccessException {

		return findVIricstockPassportValuesByValueContaining(value, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPassportValuesByValueContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPassportValues> findVIricstockPassportValuesByValueContaining(String value, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPassportValuesByValueContaining", startResult, maxRows, value);
		return new LinkedHashSet<VIricstockPassportValues>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVIricstockPassportValuess
	 *
	 */
	@Transactional
	public Set<VIricstockPassportValues> findAllVIricstockPassportValuess() throws DataAccessException {

		return findAllVIricstockPassportValuess(-1, -1);
	}

	/**
	 * JPQL Query - findAllVIricstockPassportValuess
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPassportValues> findAllVIricstockPassportValuess(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVIricstockPassportValuess", startResult, maxRows);
		return new LinkedHashSet<VIricstockPassportValues>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockPassportValuesByValue
	 *
	 */
	@Transactional
	public Set<VIricstockPassportValues> findVIricstockPassportValuesByValue(String value) throws DataAccessException {

		return findVIricstockPassportValuesByValue(value, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPassportValuesByValue
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPassportValues> findVIricstockPassportValuesByValue(String value, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPassportValuesByValue", startResult, maxRows, value);
		return new LinkedHashSet<VIricstockPassportValues>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VIricstockPassportValues entity) {
		return true;
	}

	@Override
	public Set getUniqueValues(BigDecimal typeId) {
		// TODO Auto-generated method stub
		return this.findVIricstockPassportValuesByTypeId(typeId);
	}
	
	
	
}
