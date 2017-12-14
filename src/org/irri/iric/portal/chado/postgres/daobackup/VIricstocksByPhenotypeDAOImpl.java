package org.irri.iric.portal.chado.postgres.daobackup;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.chado.postgres.domain.VIricstocksByPhenotype;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VIricstocksByPhenotype entities.
 * 
 */
@Repository("VIricstocksByPhenotypeDAO")
@Primary()
@Transactional
public class VIricstocksByPhenotypeDAOImpl extends AbstractJpaDao<VIricstocksByPhenotype>
		implements VIricstocksByPhenotypeDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VIricstocksByPhenotype.class }));

	/**
	 * EntityManager injected by Spring for persistence unit VM_IRIC
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VIricstocksByPhenotypeDAOImpl
	 *
	 */
	public VIricstocksByPhenotypeDAOImpl() {
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
	 * JPQL Query - findVIricstocksByPhenotypeByIricStockPhenotypeId
	 *
	 */
	@Transactional
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByIricStockPhenotypeId(Integer iricStockPhenotypeId) throws DataAccessException {

		return findVIricstocksByPhenotypeByIricStockPhenotypeId(iricStockPhenotypeId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByIricStockPhenotypeId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByIricStockPhenotypeId(Integer iricStockPhenotypeId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPhenotypeByIricStockPhenotypeId", startResult, maxRows, iricStockPhenotypeId);
		return new LinkedHashSet<VIricstocksByPhenotype>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeBySubpopulationContaining
	 *
	 */
	@Transactional
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeBySubpopulationContaining(String subpopulation) throws DataAccessException {

		return findVIricstocksByPhenotypeBySubpopulationContaining(subpopulation, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeBySubpopulationContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeBySubpopulationContaining(String subpopulation, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPhenotypeBySubpopulationContaining", startResult, maxRows, subpopulation);
		return new LinkedHashSet<VIricstocksByPhenotype>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByOriCountryContaining
	 *
	 */
	@Transactional
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByOriCountryContaining(String oriCountry) throws DataAccessException {

		return findVIricstocksByPhenotypeByOriCountryContaining(oriCountry, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByOriCountryContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByOriCountryContaining(String oriCountry, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPhenotypeByOriCountryContaining", startResult, maxRows, oriCountry);
		return new LinkedHashSet<VIricstocksByPhenotype>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVIricstocksByPhenotypes
	 *
	 */
	@Transactional
	public Set<VIricstocksByPhenotype> findAllVIricstocksByPhenotypes() throws DataAccessException {

		return findAllVIricstocksByPhenotypes(-1, -1);
	}

	/**
	 * JPQL Query - findAllVIricstocksByPhenotypes
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPhenotype> findAllVIricstocksByPhenotypes(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVIricstocksByPhenotypes", startResult, maxRows);
		return new LinkedHashSet<VIricstocksByPhenotype>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByIrisUniqueIdContaining
	 *
	 */
	@Transactional
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByIrisUniqueIdContaining(String irisUniqueId) throws DataAccessException {

		return findVIricstocksByPhenotypeByIrisUniqueIdContaining(irisUniqueId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByIrisUniqueIdContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByIrisUniqueIdContaining(String irisUniqueId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPhenotypeByIrisUniqueIdContaining", startResult, maxRows, irisUniqueId);
		return new LinkedHashSet<VIricstocksByPhenotype>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByQuanValue
	 *
	 */
	@Transactional
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByQuanValue(java.math.BigDecimal quanValue) throws DataAccessException {

		return findVIricstocksByPhenotypeByQuanValue(quanValue, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByQuanValue
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByQuanValue(java.math.BigDecimal quanValue, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPhenotypeByQuanValue", startResult, maxRows, quanValue);
		return new LinkedHashSet<VIricstocksByPhenotype>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByQualValue
	 *
	 */
	@Transactional
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByQualValue(String qualValue) throws DataAccessException {

		return findVIricstocksByPhenotypeByQualValue(qualValue, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByQualValue
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByQualValue(String qualValue, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPhenotypeByQualValue", startResult, maxRows, qualValue);
		return new LinkedHashSet<VIricstocksByPhenotype>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByPhenotypeId
	 *
	 */
	@Transactional
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByPhenotypeId(Integer phenotypeId) throws DataAccessException {

		return findVIricstocksByPhenotypeByPhenotypeId(phenotypeId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByPhenotypeId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByPhenotypeId(Integer phenotypeId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPhenotypeByPhenotypeId", startResult, maxRows, phenotypeId);
		return new LinkedHashSet<VIricstocksByPhenotype>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByIrisUniqueId
	 *
	 */
	@Transactional
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByIrisUniqueId(String irisUniqueId) throws DataAccessException {

		return findVIricstocksByPhenotypeByIrisUniqueId(irisUniqueId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByIrisUniqueId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByIrisUniqueId(String irisUniqueId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPhenotypeByIrisUniqueId", startResult, maxRows, irisUniqueId);
		return new LinkedHashSet<VIricstocksByPhenotype>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByNameContaining
	 *
	 */
	@Transactional
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByNameContaining(String name) throws DataAccessException {

		return findVIricstocksByPhenotypeByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPhenotypeByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VIricstocksByPhenotype>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByName
	 *
	 */
	@Transactional
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByName(String name) throws DataAccessException {

		return findVIricstocksByPhenotypeByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPhenotypeByName", startResult, maxRows, name);
		return new LinkedHashSet<VIricstocksByPhenotype>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByIricStockId
	 *
	 */
	@Transactional
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByIricStockId(Integer iricStockId) throws DataAccessException {

		return findVIricstocksByPhenotypeByIricStockId(iricStockId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByIricStockId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByIricStockId(Integer iricStockId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPhenotypeByIricStockId", startResult, maxRows, iricStockId);
		return new LinkedHashSet<VIricstocksByPhenotype>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByPrimaryKey
	 *
	 */
	@Transactional
	public VIricstocksByPhenotype findVIricstocksByPhenotypeByPrimaryKey(Integer iricStockPhenotypeId, Integer iricStockId) throws DataAccessException {

		return findVIricstocksByPhenotypeByPrimaryKey(iricStockPhenotypeId, iricStockId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByPrimaryKey
	 *
	 */

	@Transactional
	public VIricstocksByPhenotype findVIricstocksByPhenotypeByPrimaryKey(Integer iricStockPhenotypeId, Integer iricStockId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVIricstocksByPhenotypeByPrimaryKey", startResult, maxRows, iricStockPhenotypeId, iricStockId);
			return (org.irri.iric.portal.chado.postgres.domain.VIricstocksByPhenotype) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeBySubpopulation
	 *
	 */
	@Transactional
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeBySubpopulation(String subpopulation) throws DataAccessException {

		return findVIricstocksByPhenotypeBySubpopulation(subpopulation, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeBySubpopulation
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeBySubpopulation(String subpopulation, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPhenotypeBySubpopulation", startResult, maxRows, subpopulation);
		return new LinkedHashSet<VIricstocksByPhenotype>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByOriCountry
	 *
	 */
	@Transactional
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByOriCountry(String oriCountry) throws DataAccessException {

		return findVIricstocksByPhenotypeByOriCountry(oriCountry, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByOriCountry
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByOriCountry(String oriCountry, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPhenotypeByOriCountry", startResult, maxRows, oriCountry);
		return new LinkedHashSet<VIricstocksByPhenotype>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByQualValueContaining
	 *
	 */
	@Transactional
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByQualValueContaining(String qualValue) throws DataAccessException {

		return findVIricstocksByPhenotypeByQualValueContaining(qualValue, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstocksByPhenotypeByQualValueContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstocksByPhenotype> findVIricstocksByPhenotypeByQualValueContaining(String qualValue, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstocksByPhenotypeByQualValueContaining", startResult, maxRows, qualValue);
		return new LinkedHashSet<VIricstocksByPhenotype>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VIricstocksByPhenotype entity) {
		return true;
	}
	
	
	@Override
	@Transactional
	public List findVarietyByQuanPhenotypeLessThan(BigDecimal phen, double value) {
		// TODO Auto-generated method stub
		Query query = createNamedQuery("findVIricstocksByPhenotypeByPhenotypeIdQuanLessthan", -1,-1, phen.intValue(), BigDecimal.valueOf(Double.valueOf(value)));
		return query.getResultList();
	}

	@Override
	@Transactional
	public List findVarietyByQuanPhenotypeGreaterThan(BigDecimal phen,
			double value) {
		// TODO Auto-generated method stub
		Query query = createNamedQuery("findVIricstocksByPhenotypeByPhenotypeIdQuanGreaterthan", -1, -1, phen.intValue(), BigDecimal.valueOf(value));
		return query.getResultList();
	}

	@Override
	@Transactional
	public List findVarietyByQuanPhenotypeEquals(BigDecimal phen, double value) {
		// TODO Auto-generated method stub
		Query query = createNamedQuery("findVIricstocksByPhenotypeByPhenotypeIdQuanEquals", -1, -1, phen.intValue(), BigDecimal.valueOf(value));
		return query.getResultList();
	}

	@Override
	@Transactional
	public List findVarietyByQualPhenotypeEquals(BigDecimal phen, String value) {
		// TODO Auto-generated method stub
		Query query = createNamedQuery("findVIricstocksByPhenotypeByPhenotypeIdQualEquals", -1, -1, phen.intValue(), value);
		return query.getResultList();
	}

	@Override
	public List findVarietyByPhenotype(BigDecimal phen) {
		// TODO Auto-generated method stub
		
		List listVars = new ArrayList();
		listVars.addAll( findVIricstocksByPhenotypeByPhenotypeId(phen.intValue()));
		return listVars;
		
	}
	
	
	
	
}
