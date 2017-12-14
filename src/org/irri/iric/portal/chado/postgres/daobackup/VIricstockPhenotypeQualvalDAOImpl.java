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

import org.irri.iric.portal.chado.postgres.domain.VIricstockPhenotypeQualval;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VIricstockPhenotypeQualval entities.
 * 
 */
@Repository("VIricstockPhenotypeQualvalDAO")
@Primary()
@Transactional
public class VIricstockPhenotypeQualvalDAOImpl extends AbstractJpaDao<VIricstockPhenotypeQualval>
		implements VIricstockPhenotypeQualvalDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VIricstockPhenotypeQualval.class }));

	/**
	 * EntityManager injected by Spring for persistence unit VM_IRIC
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VIricstockPhenotypeQualvalDAOImpl
	 *
	 */
	public VIricstockPhenotypeQualvalDAOImpl() {
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
	 * JPQL Query - findVIricstockPhenotypeQualvalByPhenotypeId
	 *
	 */
	@Transactional
	public Set<VIricstockPhenotypeQualval> findVIricstockPhenotypeQualvalByPhenotypeId(Integer phenotypeId) throws DataAccessException {

		return findVIricstockPhenotypeQualvalByPhenotypeId(phenotypeId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByPhenotypeId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPhenotypeQualval> findVIricstockPhenotypeQualvalByPhenotypeId(Integer phenotypeId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPhenotypeQualvalByPhenotypeId", startResult, maxRows, phenotypeId);
		return new LinkedHashSet<VIricstockPhenotypeQualval>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVIricstockPhenotypeQualvals
	 *
	 */
	@Transactional
	public Set<VIricstockPhenotypeQualval> findAllVIricstockPhenotypeQualvals() throws DataAccessException {

		return findAllVIricstockPhenotypeQualvals(-1, -1);
	}

	/**
	 * JPQL Query - findAllVIricstockPhenotypeQualvals
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPhenotypeQualval> findAllVIricstockPhenotypeQualvals(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVIricstockPhenotypeQualvals", startResult, maxRows);
		return new LinkedHashSet<VIricstockPhenotypeQualval>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByPrimaryKey
	 *
	 */
	@Transactional
	public VIricstockPhenotypeQualval findVIricstockPhenotypeQualvalByPrimaryKey(Integer phenotypeId, String qualValue) throws DataAccessException {

		return findVIricstockPhenotypeQualvalByPrimaryKey(phenotypeId, qualValue, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByPrimaryKey
	 *
	 */

	@Transactional
	public VIricstockPhenotypeQualval findVIricstockPhenotypeQualvalByPrimaryKey(Integer phenotypeId, String qualValue, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVIricstockPhenotypeQualvalByPrimaryKey", startResult, maxRows, phenotypeId, qualValue);
			return (org.irri.iric.portal.chado.postgres.domain.VIricstockPhenotypeQualval) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByQualValueContaining
	 *
	 */
	@Transactional
	public Set<VIricstockPhenotypeQualval> findVIricstockPhenotypeQualvalByQualValueContaining(String qualValue) throws DataAccessException {

		return findVIricstockPhenotypeQualvalByQualValueContaining(qualValue, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByQualValueContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPhenotypeQualval> findVIricstockPhenotypeQualvalByQualValueContaining(String qualValue, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPhenotypeQualvalByQualValueContaining", startResult, maxRows, qualValue);
		return new LinkedHashSet<VIricstockPhenotypeQualval>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByQualValue
	 *
	 */
	@Transactional
	public Set<VIricstockPhenotypeQualval> findVIricstockPhenotypeQualvalByQualValue(String qualValue) throws DataAccessException {

		return findVIricstockPhenotypeQualvalByQualValue(qualValue, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeQualvalByQualValue
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPhenotypeQualval> findVIricstockPhenotypeQualvalByQualValue(String qualValue, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPhenotypeQualvalByQualValue", startResult, maxRows, qualValue);
		return new LinkedHashSet<VIricstockPhenotypeQualval>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VIricstockPhenotypeQualval entity) {
		return true;
	}
	
	
	

	@Override
	public Set getUniqueValues(BigDecimal typeId) {
		// TODO Auto-generated method stub
		return this.findVIricstockPhenotypeQualvalByPhenotypeId(typeId.intValue());
	}
	
}
