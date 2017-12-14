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

import org.irri.iric.portal.chado.domain.VIricstockBasicprop;
import org.irri.iric.portal.chado.postgres.domain.VIricstockBasicprop2;
import org.irri.iric.portal.domain.Variety;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VIricstockBasicprop2 entities.
 * 
 */
//@Repository("VIricstockBasicprop2DAO")
//@Repository("VarietyBasicprop2DAO")
@Repository("VarietyDAO")
@Transactional
public class VIricstockBasicprop2DAOImpl extends AbstractJpaDao<VIricstockBasicprop2>
		implements VIricstockBasicprop2DAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VIricstockBasicprop2.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VIricstockBasicprop2DAOImpl
	 *
	 */
	public VIricstockBasicprop2DAOImpl() {
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
	 * JPQL Query - findAllVIricstockBasicprop2s
	 *
	 */
	@Transactional
	public Set<VIricstockBasicprop2> findAllVIricstockBasicprop2s() throws DataAccessException {

		return findAllVIricstockBasicprop2s(-1, -1);
	}

	/**
	 * JPQL Query - findAllVIricstockBasicprop2s
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBasicprop2> findAllVIricstockBasicprop2s(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVIricstockBasicprop2s", startResult, maxRows);
		return new LinkedHashSet<VIricstockBasicprop2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockBasicprop2ByIrisUniqueIdContaining
	 *
	 */
	@Transactional
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByIrisUniqueIdContaining(String irisUniqueId) throws DataAccessException {

		return findVIricstockBasicprop2ByIrisUniqueIdContaining(irisUniqueId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBasicprop2ByIrisUniqueIdContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByIrisUniqueIdContaining(String irisUniqueId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBasicprop2ByIrisUniqueIdContaining", startResult, maxRows, irisUniqueId);
		return new LinkedHashSet<VIricstockBasicprop2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockBasicprop2ByOriCountryContaining
	 *
	 */
	@Transactional
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByOriCountryContaining(String oriCountry) throws DataAccessException {

		return findVIricstockBasicprop2ByOriCountryContaining(oriCountry, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBasicprop2ByOriCountryContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByOriCountryContaining(String oriCountry, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBasicprop2ByOriCountryContaining", startResult, maxRows, oriCountry);
		return new LinkedHashSet<VIricstockBasicprop2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockBasicprop2ByBoxCode
	 *
	 */
	@Transactional
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByBoxCode(String boxCode) throws DataAccessException {

		return findVIricstockBasicprop2ByBoxCode(boxCode, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBasicprop2ByBoxCode
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByBoxCode(String boxCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBasicprop2ByBoxCode", startResult, maxRows, boxCode);
		return new LinkedHashSet<VIricstockBasicprop2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockBasicprop2ByNameContaining
	 *
	 */
	@Transactional
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByNameContaining(String name) throws DataAccessException {

		return findVIricstockBasicprop2ByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBasicprop2ByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBasicprop2ByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VIricstockBasicprop2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockBasicprop2ByBoxCodeContaining
	 *
	 */
	@Transactional
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByBoxCodeContaining(String boxCode) throws DataAccessException {

		return findVIricstockBasicprop2ByBoxCodeContaining(boxCode, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBasicprop2ByBoxCodeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByBoxCodeContaining(String boxCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBasicprop2ByBoxCodeContaining", startResult, maxRows, boxCode);
		return new LinkedHashSet<VIricstockBasicprop2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockBasicprop2ByPrimaryKey
	 *
	 */
	@Transactional
	public VIricstockBasicprop2 findVIricstockBasicprop2ByPrimaryKey(BigDecimal iricStockId) throws DataAccessException {

		return findVIricstockBasicprop2ByPrimaryKey(iricStockId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBasicprop2ByPrimaryKey
	 *
	 */

	@Transactional
	public VIricstockBasicprop2 findVIricstockBasicprop2ByPrimaryKey(BigDecimal iricStockId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVIricstockBasicprop2ByPrimaryKey", startResult, maxRows, iricStockId);
			return (org.irri.iric.portal.chado.postgres.domain.VIricstockBasicprop2) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVIricstockBasicprop2ByIrisUniqueId
	 *
	 */
	@Transactional
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByIrisUniqueId(String irisUniqueId) throws DataAccessException {

		return findVIricstockBasicprop2ByIrisUniqueId(irisUniqueId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBasicprop2ByIrisUniqueId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByIrisUniqueId(String irisUniqueId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBasicprop2ByIrisUniqueId", startResult, maxRows, irisUniqueId);
		return new LinkedHashSet<VIricstockBasicprop2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockBasicprop2ByOriCountry
	 *
	 */
	@Transactional
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByOriCountry(String oriCountry) throws DataAccessException {

		return findVIricstockBasicprop2ByOriCountry(oriCountry, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBasicprop2ByOriCountry
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByOriCountry(String oriCountry, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBasicprop2ByOriCountry", startResult, maxRows, oriCountry);
		return new LinkedHashSet<VIricstockBasicprop2>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockBasicprop2ByIricStockId
	 *
	 */
	@Transactional
	public VIricstockBasicprop2 findVIricstockBasicprop2ByIricStockId(BigDecimal iricStockId) throws DataAccessException {

		return findVIricstockBasicprop2ByIricStockId(iricStockId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBasicprop2ByIricStockId
	 *
	 */

	@Transactional
	public VIricstockBasicprop2 findVIricstockBasicprop2ByIricStockId(BigDecimal iricStockId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVIricstockBasicprop2ByIricStockId", startResult, maxRows, iricStockId);
			return (org.irri.iric.portal.chado.postgres.domain.VIricstockBasicprop2) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVIricstockBasicprop2ByName
	 *
	 */
	@Transactional
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByName(String name) throws DataAccessException {

		return findVIricstockBasicprop2ByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBasicprop2ByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBasicprop2> findVIricstockBasicprop2ByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBasicprop2ByName", startResult, maxRows, name);
		return new LinkedHashSet<VIricstockBasicprop2>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VIricstockBasicprop2 entity) {
		return true;
	}
	
	

	@Override
	public Set findAllVariety() {
		// TODO Auto-generated method stub
		return this.findAllVIricstockBasicprop2s();
	}

	@Override
	public Set findAllVarietyByCountry(String country) {
		// TODO Auto-generated method stub
		return this.findVIricstockBasicprop2ByOriCountry(country);
	}

	@Override
	public Set findAllVarietyBySubpopulation(String subpopulation) {
		// TODO Auto-generated method stub
		return this.findVIricstockBasicprop2BySubpopulation(subpopulation);
	}
	
	
	@Override
	public Set findAllVarietyByCountryAndSubpopulation(String country, String subpopulation) {
		// TODO Auto-generated method stub
		return this.findVIricstockBasicprop2ByOriCountrySubpopulation(country, subpopulation);
	}

	//@Override
	/*
	public Set findAllVarietyByExample(Variety germplasm) throws DataAccessException {
		//Query query = createNamedQuery("findAllList3ks", startResult, maxRows);
		//return new LinkedHashSet<List3k>(query.getResultList());
		
		  CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		  CriteriaQuery q = cb.createQuery(VIricstockBasicprop.class);
		  Root<List3k> c = q.from(VIricstockBasicprop.class);
		  q.select(c);

			  if(germplasm.getCountry()!=null && germplasm.getSubpopulation()!=null)	
			  {
				  Expression<String> country = cb.literal(germplasm.getCountry() );
				  Expression<String> subpop = cb.parameter(String.class);
				  q.where(  cb.and( cb.equal( cb.upper( c.<String>get("oriCountry")  ), germplasm.getCountry().toUpperCase() ),
						  			cb.equal( cb.upper( c.<String>get("subpopulation")), cb.literal(germplasm.getSubpopulation().toUpperCase() ) ) ));
			  }
			  else if (germplasm.getCountry()!=null)
			  {
					q.where(   cb.equal( cb.upper( c.<String>get("oriCountry")),  cb.literal(germplasm.getCountry().toUpperCase() )) );
			  }
			  else if (germplasm.getSubpopulation()!=null)
					q.where(   cb.equal( cb.upper( c.<String>get("subpopulation")),  cb.literal(germplasm.getSubpopulation().toUpperCase() )  )  );
		 
			  Query testQuery = entityManager.createQuery(q);
			 
			  return new java.util.LinkedHashSet<List3k>( testQuery.getResultList() );
	}
	*/

	@Override
	public Variety findVarietyByIrisId(String name) {
		// TODO Auto-generated method stub
		//return this.findVIricstockBasicpropByIrisUniqueId(name);
		
		Set setVarieties =  this.findVIricstockBasicprop2ByIrisUniqueId(name);
		if(setVarieties.size()>1)
			throw new RuntimeException("Multiple varities with name " + name);
		else if(setVarieties.isEmpty())
			return null;
		return (Variety)setVarieties.iterator().next();		
		
	}

	@Override
	public Variety findVarietyByName(String name) {
		// TODO Auto-generated method stub
		Set setNames =  this.findVIricstockBasicprop2ByName(name);
		if(setNames.size()>1)
			throw new RuntimeException("Multiple varities with name " + name);
		else if(setNames.isEmpty())
			return null;
		return (Variety)setNames.iterator().next();
	}

	@Override
	public Variety findVarietyById(BigDecimal id) {
		// TODO Auto-generated method stub
		return this.findVIricstockBasicprop2ByPrimaryKey(id);
	}
	
	
	@Override
	public Variety findVarietyByNameLike(String name) {
		// TODO Auto-generated method stub
		Set setNames =  this.findVIricstockBasicprop2ByNameContaining(name);
		if(setNames.size()>1)
			throw new RuntimeException("Multiple varities with name " + name);
		else if(setNames.isEmpty())
			return null;
		return (Variety)setNames.iterator().next();
	}

	

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBasicprop> findVIricstockBasicprop2BySubpopulation(String subpopulation) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBasicprop2BySubpopulation", -1, -1, subpopulation);
		return new LinkedHashSet<VIricstockBasicprop>(query.getResultList());
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBasicprop> findVIricstockBasicprop2ByOriCountrySubpopulation(String country, String subpopulation) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBasicprop2ByOriCountrySubpopulation", -1, -1, country, subpopulation);
		return new LinkedHashSet<VIricstockBasicprop>(query.getResultList());
	}
	
}
