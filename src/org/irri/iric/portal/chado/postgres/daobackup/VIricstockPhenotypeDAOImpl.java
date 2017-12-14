package org.irri.iric.portal.chado.postgres.daobackup;

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
import org.irri.iric.portal.chado.postgres.domain.VIricstockPhenotype;
import org.irri.iric.portal.domain.Phenotype;
import org.irri.iric.portal.domain.Variety;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VIricstockPhenotype entities.
 * 
 */
//@Repository("VIricstockPhenotypeDAOPostges")
@Repository("PhenotypeDAO")
@Primary()
@Transactional
public class VIricstockPhenotypeDAOImpl extends AbstractJpaDao<VIricstockPhenotype>
		implements VIricstockPhenotypeDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VIricstockPhenotype.class }));

	/**
	 * EntityManager injected by Spring for persistence unit VM_IRIC
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VIricstockPhenotypeDAOImpl
	 *
	 */
	public VIricstockPhenotypeDAOImpl() {
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
	 * JPQL Query - findVIricstockPhenotypeByNameContaining
	 *
	 */
	@Transactional
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByNameContaining(String name) throws DataAccessException {

		return findVIricstockPhenotypeByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPhenotypeByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VIricstockPhenotype>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeByIricStockId
	 *
	 */
	@Transactional
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByIricStockId(Integer iricStockId) throws DataAccessException {

		return findVIricstockPhenotypeByIricStockId(iricStockId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeByIricStockId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByIricStockId(Integer iricStockId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPhenotypeByIricStockId", startResult, maxRows, iricStockId);
		return new LinkedHashSet<VIricstockPhenotype>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeByName
	 *
	 */
	@Transactional
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByName(String name) throws DataAccessException {

		return findVIricstockPhenotypeByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPhenotypeByName", startResult, maxRows, name);
		return new LinkedHashSet<VIricstockPhenotype>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeByPrimaryKey
	 *
	 */
	@Transactional
	public VIricstockPhenotype findVIricstockPhenotypeByPrimaryKey(Integer iricStockPhenotypeId) throws DataAccessException {

		return findVIricstockPhenotypeByPrimaryKey(iricStockPhenotypeId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeByPrimaryKey
	 *
	 */

	@Transactional
	public VIricstockPhenotype findVIricstockPhenotypeByPrimaryKey(Integer iricStockPhenotypeId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVIricstockPhenotypeByPrimaryKey", startResult, maxRows, iricStockPhenotypeId);
			return (org.irri.iric.portal.chado.postgres.domain.VIricstockPhenotype) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeByQualValue
	 *
	 */
	@Transactional
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByQualValue(String qualValue) throws DataAccessException {

		return findVIricstockPhenotypeByQualValue(qualValue, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeByQualValue
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByQualValue(String qualValue, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPhenotypeByQualValue", startResult, maxRows, qualValue);
		return new LinkedHashSet<VIricstockPhenotype>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeByDefinition
	 *
	 */
	@Transactional
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByDefinition(String definition) throws DataAccessException {

		return findVIricstockPhenotypeByDefinition(definition, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeByDefinition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByDefinition(String definition, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPhenotypeByDefinition", startResult, maxRows, definition);
		return new LinkedHashSet<VIricstockPhenotype>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVIricstockPhenotypes
	 *
	 */
	@Transactional
	public Set<VIricstockPhenotype> findAllVIricstockPhenotypes() throws DataAccessException {

		return findAllVIricstockPhenotypes(-1, -1);
	}

	/**
	 * JPQL Query - findAllVIricstockPhenotypes
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPhenotype> findAllVIricstockPhenotypes(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVIricstockPhenotypes", startResult, maxRows);
		return new LinkedHashSet<VIricstockPhenotype>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeByIricStockPhenotypeId
	 *
	 */
	@Transactional
	public VIricstockPhenotype findVIricstockPhenotypeByIricStockPhenotypeId(Integer iricStockPhenotypeId) throws DataAccessException {

		return findVIricstockPhenotypeByIricStockPhenotypeId(iricStockPhenotypeId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeByIricStockPhenotypeId
	 *
	 */

	@Transactional
	public VIricstockPhenotype findVIricstockPhenotypeByIricStockPhenotypeId(Integer iricStockPhenotypeId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVIricstockPhenotypeByIricStockPhenotypeId", startResult, maxRows, iricStockPhenotypeId);
			return (org.irri.iric.portal.chado.postgres.domain.VIricstockPhenotype) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeByQualValueContaining
	 *
	 */
	@Transactional
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByQualValueContaining(String qualValue) throws DataAccessException {

		return findVIricstockPhenotypeByQualValueContaining(qualValue, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeByQualValueContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByQualValueContaining(String qualValue, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPhenotypeByQualValueContaining", startResult, maxRows, qualValue);
		return new LinkedHashSet<VIricstockPhenotype>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeByQuanValue
	 *
	 */
	@Transactional
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByQuanValue(java.math.BigDecimal quanValue) throws DataAccessException {

		return findVIricstockPhenotypeByQuanValue(quanValue, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeByQuanValue
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByQuanValue(java.math.BigDecimal quanValue, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPhenotypeByQuanValue", startResult, maxRows, quanValue);
		return new LinkedHashSet<VIricstockPhenotype>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeByDefinitionContaining
	 *
	 */
	@Transactional
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByDefinitionContaining(String definition) throws DataAccessException {

		return findVIricstockPhenotypeByDefinitionContaining(definition, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockPhenotypeByDefinitionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockPhenotype> findVIricstockPhenotypeByDefinitionContaining(String definition, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockPhenotypeByDefinitionContaining", startResult, maxRows, definition);
		return new LinkedHashSet<VIricstockPhenotype>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VIricstockPhenotype entity) {
		return true;
	}
	
	
	
	

	@Override
	public List findPhenotypesByVariety(Variety var) {
		// TODO Auto-generated method stub
		
		AppContext.debug("findPhenotypesByVariety: " + var);
		
		List list = new java.util.ArrayList();
		list.addAll(this.findVIricstockPhenotypeByIricStockId(var.getVarietyId().intValue()));
		
		AppContext.debug(list.size() + " phenotypes");
		
		return list;
	
	}
	
	public List findPhenotypesByVarietyNameLike(String name) {
		// TODO Auto-generated method stub
		AppContext.debug("findPhenotypesByVariety: " + name);
		List list = new java.util.ArrayList();
		list.addAll(this.findVIricstockPhenotypeByNameContaining(name));
		
		AppContext.debug(list.size() + " phenotypes");
		
		return list;
	
	}
	
	
}
