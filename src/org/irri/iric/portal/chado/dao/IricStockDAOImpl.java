package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.chado.domain.IricStock;
import org.irri.iric.portal.domain.Variety;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage IricStock entities.
 * 
 */
//@Repository("IricStockDAO")
@Repository("IricStockDAOOrig")
@Transactional
public class IricStockDAOImpl extends AbstractJpaDao<IricStock> implements
		IricStockDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { IricStock.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new IricStockDAOImpl
	 *
	 */
	public IricStockDAOImpl() {
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
	 * JPQL Query - findAllIricStocks
	 *
	 */
	@Transactional
	public Set<IricStock> findAllIricStocks() throws DataAccessException {

		return findAllIricStocks(-1, -1);
	}

	/**
	 * JPQL Query - findAllIricStocks
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IricStock> findAllIricStocks(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllIricStocks", startResult, maxRows);
		return new LinkedHashSet<IricStock>(query.getResultList());
	}

	/**
	 * JPQL Query - findIricStockByPrimaryKey
	 *
	 */
	@Transactional
	public IricStock findIricStockByPrimaryKey(java.math.BigDecimal iricStockId) throws DataAccessException {

		return findIricStockByPrimaryKey(iricStockId, -1, -1);
	}

	/**
	 * JPQL Query - findIricStockByPrimaryKey
	 *
	 */

	@Transactional
	public IricStock findIricStockByPrimaryKey(java.math.BigDecimal iricStockId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findIricStockByPrimaryKey", startResult, maxRows, iricStockId);
			return (org.irri.iric.portal.chado.domain.IricStock) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findIricStockByName
	 *
	 */
	@Transactional
	public Set<IricStock> findIricStockByName(String name) throws DataAccessException {

		return findIricStockByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findIricStockByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IricStock> findIricStockByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIricStockByName", startResult, maxRows, name);
		return new LinkedHashSet<IricStock>(query.getResultList());
	}

	/**
	 * JPQL Query - findIricStockByNameContaining
	 *
	 */
	@Transactional
	public Set<IricStock> findIricStockByNameContaining(String name) throws DataAccessException {

		return findIricStockByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findIricStockByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IricStock> findIricStockByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIricStockByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<IricStock>(query.getResultList());
	}

	/**
	 * JPQL Query - findIricStockByOrganismId
	 *
	 */
	@Transactional
	public Set<IricStock> findIricStockByOrganismId(java.math.BigDecimal organismId) throws DataAccessException {

		return findIricStockByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findIricStockByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IricStock> findIricStockByOrganismId(java.math.BigDecimal organismId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIricStockByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<IricStock>(query.getResultList());
	}

	/**
	 * JPQL Query - findIricStockByIricStockGeolocationId
	 *
	 */
	@Transactional
	public Set<IricStock> findIricStockByIricStockGeolocationId(java.math.BigDecimal iricStockGeolocationId) throws DataAccessException {

		return findIricStockByIricStockGeolocationId(iricStockGeolocationId, -1, -1);
	}

	/**
	 * JPQL Query - findIricStockByIricStockGeolocationId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IricStock> findIricStockByIricStockGeolocationId(java.math.BigDecimal iricStockGeolocationId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIricStockByIricStockGeolocationId", startResult, maxRows, iricStockGeolocationId);
		return new LinkedHashSet<IricStock>(query.getResultList());
	}

	/**
	 * JPQL Query - findIricStockByDbxrefId
	 *
	 */
	@Transactional
	public Set<IricStock> findIricStockByDbxrefId(java.math.BigDecimal dbxrefId) throws DataAccessException {

		return findIricStockByDbxrefId(dbxrefId, -1, -1);
	}

	/**
	 * JPQL Query - findIricStockByDbxrefId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IricStock> findIricStockByDbxrefId(java.math.BigDecimal dbxrefId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIricStockByDbxrefId", startResult, maxRows, dbxrefId);
		return new LinkedHashSet<IricStock>(query.getResultList());
	}

	/**
	 * JPQL Query - findIricStockByPrimaryKey
	 *
	 */
	@Transactional
	public IricStock findIricStockByPrimaryKey(Integer iricStockId) throws DataAccessException {

		return findIricStockByPrimaryKey(iricStockId, -1, -1);
	}

	/**
	 * JPQL Query - findIricStockByPrimaryKey
	 *
	 */

	@Transactional
	public IricStock findIricStockByPrimaryKey(Integer iricStockId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findIricStockByPrimaryKey", startResult, maxRows, iricStockId);
			return (org.irri.iric.portal.chado.domain.IricStock) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findIricStockByIricStockId
	 *
	 */
	@Transactional
	public IricStock findIricStockByIricStockId(Integer iricStockId) throws DataAccessException {

		return findIricStockByIricStockId(iricStockId, -1, -1);
	}

	/**
	 * JPQL Query - findIricStockByIricStockId
	 *
	 */

	@Transactional
	public IricStock findIricStockByIricStockId(Integer iricStockId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findIricStockByIricStockId", startResult, maxRows, iricStockId);
			return (org.irri.iric.portal.chado.domain.IricStock) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findIricStockByIricStockId
	 *
	 */
	@Transactional
	public IricStock findIricStockByIricStockId(java.math.BigDecimal iricStockId) throws DataAccessException {

		return findIricStockByIricStockId(iricStockId, -1, -1);
	}

	/**
	 * JPQL Query - findIricStockByIricStockId
	 *
	 */

	@Transactional
	public IricStock findIricStockByIricStockId(java.math.BigDecimal iricStockId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findIricStockByIricStockId", startResult, maxRows, iricStockId);
			return (org.irri.iric.portal.chado.domain.IricStock) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findIricStockByTypeId
	 *
	 */
	@Transactional
	public Set<IricStock> findIricStockByTypeId(java.math.BigDecimal typeId) throws DataAccessException {

		return findIricStockByTypeId(typeId, -1, -1);
	}

	/**
	 * JPQL Query - findIricStockByTypeId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<IricStock> findIricStockByTypeId(java.math.BigDecimal typeId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findIricStockByTypeId", startResult, maxRows, typeId);
		return new LinkedHashSet<IricStock>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(IricStock entity) {
		return true;
	}

	@Override
	public Set findAllVariety() {
		// TODO Auto-generated method stub
		return this.findAllIricStocks();
	}

	@Override
	public Set findAllVarietyByCountry(String country) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set findAllVarietyBySubpopulation(String subpopulation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set findAllVarietyByCountryAndSubpopulation(String country,
			String subpopulation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Variety findVarietyByName(String name) {
		// TODO Auto-generated method stub

		Set setNames =  this.findIricStockByName(name);
		if(setNames.size()>1)
			throw new RuntimeException("Multiple varities with name " + name);
		else if(setNames.isEmpty())
			return null;
		return (Variety)setNames.iterator().next();
	}

	@Override
	public Variety findVarietyByNameLike(String name) {
		// TODO Auto-generated method stub
		Set setNames =  this.findIricStockByNameContaining(name);
		if(setNames.size()>1)
			throw new RuntimeException("Multiple varities with name " + name);
		else if(setNames.isEmpty())
			return null;
		return (Variety)setNames.iterator().next();
	}

	@Override
	public Variety findVarietyByIrisId(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Variety findVarietyById(BigDecimal id) {
		// TODO Auto-generated method stub
		return this.findIricStockByIricStockId(id);
	}
	
	
	
}
