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

import org.irri.iric.portal.chado.postgres.domain.VIricstockBasicprop;
import org.irri.iric.portal.domain.Variety;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VIricstockBasicprop entities.
 * 
 */
//@Repository("VIricstockBasicpropDAO")

@Repository("VarietyDAOOld2")
@Primary()
@Transactional
public class VIricstockBasicpropDAOImpl extends AbstractJpaDao<VIricstockBasicprop>
		implements VIricstockBasicpropDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VIricstockBasicprop.class }));

	/**
	 * EntityManager injected by Spring for persistence unit VM_IRIC
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VIricstockBasicpropDAOImpl
	 *
	 */
	public VIricstockBasicpropDAOImpl() {
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
	 * JPQL Query - findVIricstockBasicpropByBoxCode
	 *
	 */
	@Transactional
	public Set<VIricstockBasicprop> findVIricstockBasicpropByBoxCode(String boxCode) throws DataAccessException {

		return findVIricstockBasicpropByBoxCode(boxCode, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBasicpropByBoxCode
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBasicprop> findVIricstockBasicpropByBoxCode(String boxCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBasicpropByBoxCode", startResult, maxRows, boxCode);
		return new LinkedHashSet<VIricstockBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockBasicpropByIrisUniqueId
	 *
	 */
	@Transactional
	public Set<VIricstockBasicprop> findVIricstockBasicpropByIrisUniqueId(String irisUniqueId) throws DataAccessException {

		return findVIricstockBasicpropByIrisUniqueId(irisUniqueId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBasicpropByIrisUniqueId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBasicprop> findVIricstockBasicpropByIrisUniqueId(String irisUniqueId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBasicpropByIrisUniqueId", startResult, maxRows, irisUniqueId);
		return new LinkedHashSet<VIricstockBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockBasicpropByIrisUniqueIdContaining
	 *
	 */
	@Transactional
	public Set<VIricstockBasicprop> findVIricstockBasicpropByIrisUniqueIdContaining(String irisUniqueId) throws DataAccessException {

		return findVIricstockBasicpropByIrisUniqueIdContaining(irisUniqueId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBasicpropByIrisUniqueIdContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBasicprop> findVIricstockBasicpropByIrisUniqueIdContaining(String irisUniqueId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBasicpropByIrisUniqueIdContaining", startResult, maxRows, irisUniqueId);
		return new LinkedHashSet<VIricstockBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockBasicpropByPrimaryKey
	 *
	 */
	@Transactional
	public VIricstockBasicprop findVIricstockBasicpropByPrimaryKey(Integer iricStockId) throws DataAccessException {

		return findVIricstockBasicpropByPrimaryKey(iricStockId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBasicpropByPrimaryKey
	 *
	 */

	@Transactional
	public VIricstockBasicprop findVIricstockBasicpropByPrimaryKey(Integer iricStockId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVIricstockBasicpropByPrimaryKey", startResult, maxRows, iricStockId);
			return (org.irri.iric.portal.chado.postgres.domain.VIricstockBasicprop) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVIricstockBasicpropByName
	 *
	 */
	@Transactional
	public Set<VIricstockBasicprop> findVIricstockBasicpropByName(String name) throws DataAccessException {

		return findVIricstockBasicpropByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBasicpropByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBasicprop> findVIricstockBasicpropByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBasicpropByName", startResult, maxRows, name);
		return new LinkedHashSet<VIricstockBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockBasicpropByNameContaining
	 *
	 */
	@Transactional
	public Set<VIricstockBasicprop> findVIricstockBasicpropByNameContaining(String name) throws DataAccessException {

		return findVIricstockBasicpropByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBasicpropByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBasicprop> findVIricstockBasicpropByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBasicpropByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VIricstockBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockBasicpropByOriCountryContaining
	 *
	 */
	@Transactional
	public Set<VIricstockBasicprop> findVIricstockBasicpropByOriCountryContaining(String oriCountry) throws DataAccessException {

		return findVIricstockBasicpropByOriCountryContaining(oriCountry, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBasicpropByOriCountryContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBasicprop> findVIricstockBasicpropByOriCountryContaining(String oriCountry, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBasicpropByOriCountryContaining", startResult, maxRows, oriCountry);
		return new LinkedHashSet<VIricstockBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockBasicpropByIricStockId
	 *
	 */
	@Transactional
	public VIricstockBasicprop findVIricstockBasicpropByIricStockId(Integer iricStockId) throws DataAccessException {

		return findVIricstockBasicpropByIricStockId(iricStockId, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBasicpropByIricStockId
	 *
	 */

	@Transactional
	public VIricstockBasicprop findVIricstockBasicpropByIricStockId(Integer iricStockId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVIricstockBasicpropByIricStockId", startResult, maxRows, iricStockId);
			return (org.irri.iric.portal.chado.postgres.domain.VIricstockBasicprop) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllVIricstockBasicprops
	 *
	 */
	@Transactional
	public Set<VIricstockBasicprop> findAllVIricstockBasicprops() throws DataAccessException {

		return findAllVIricstockBasicprops(-1, -1);
	}

	/**
	 * JPQL Query - findAllVIricstockBasicprops
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBasicprop> findAllVIricstockBasicprops(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVIricstockBasicprops", startResult, maxRows);
		return new LinkedHashSet<VIricstockBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockBasicpropByBoxCodeContaining
	 *
	 */
	@Transactional
	public Set<VIricstockBasicprop> findVIricstockBasicpropByBoxCodeContaining(String boxCode) throws DataAccessException {

		return findVIricstockBasicpropByBoxCodeContaining(boxCode, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBasicpropByBoxCodeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBasicprop> findVIricstockBasicpropByBoxCodeContaining(String boxCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBasicpropByBoxCodeContaining", startResult, maxRows, boxCode);
		return new LinkedHashSet<VIricstockBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockBasicpropBySubpopulation
	 *
	 */
	@Transactional
	public Set<VIricstockBasicprop> findVIricstockBasicpropBySubpopulation(String subpopulation) throws DataAccessException {

		return findVIricstockBasicpropBySubpopulation(subpopulation, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBasicpropBySubpopulation
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBasicprop> findVIricstockBasicpropBySubpopulation(String subpopulation, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBasicpropBySubpopulation", startResult, maxRows, subpopulation);
		return new LinkedHashSet<VIricstockBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockBasicpropBySubpopulationContaining
	 *
	 */
	@Transactional
	public Set<VIricstockBasicprop> findVIricstockBasicpropBySubpopulationContaining(String subpopulation) throws DataAccessException {

		return findVIricstockBasicpropBySubpopulationContaining(subpopulation, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBasicpropBySubpopulationContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBasicprop> findVIricstockBasicpropBySubpopulationContaining(String subpopulation, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBasicpropBySubpopulationContaining", startResult, maxRows, subpopulation);
		return new LinkedHashSet<VIricstockBasicprop>(query.getResultList());
	}

	/**
	 * JPQL Query - findVIricstockBasicpropByOriCountry
	 *
	 */
	@Transactional
	public Set<VIricstockBasicprop> findVIricstockBasicpropByOriCountry(String oriCountry) throws DataAccessException {

		return findVIricstockBasicpropByOriCountry(oriCountry, -1, -1);
	}

	/**
	 * JPQL Query - findVIricstockBasicpropByOriCountry
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBasicprop> findVIricstockBasicpropByOriCountry(String oriCountry, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBasicpropByOriCountry", startResult, maxRows, oriCountry);
		return new LinkedHashSet<VIricstockBasicprop>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VIricstockBasicprop entity) {
		return true;
	}
	
	
	
	

	@Override
	public Set findAllVariety() {
		// TODO Auto-generated method stub
		return this.findAllVIricstockBasicprops();
	}

	@Override
	public Set findAllVarietyByCountry(String country) {
		// TODO Auto-generated method stub
		return this.findVIricstockBasicpropByOriCountry(country);
	}

	@Override
	public Set findAllVarietyBySubpopulation(String subpopulation) {
		// TODO Auto-generated method stub
		return this.findVIricstockBasicpropBySubpopulation(subpopulation);
	}
	
	
	@Override
	public Set findAllVarietyByCountryAndSubpopulation(String country, String subpopulation) {
		// TODO Auto-generated method stub
		return this.findVIricstockBasicpropByOriCountrySubpopulation(country, subpopulation);
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
		
		Set setVarieties =  this.findVIricstockBasicpropByIrisUniqueId(name);
		if(setVarieties.size()>1)
			throw new RuntimeException("Multiple varities with name " + name);
		else if(setVarieties.isEmpty())
			return null;
		return (Variety)setVarieties.iterator().next();		
		
	}

	@Override
	public Variety findVarietyByName(String name) {
		// TODO Auto-generated method stub
		Set setNames =  this.findVIricstockBasicpropByName(name);
		if(setNames.size()>1)
			throw new RuntimeException("Multiple varities with name " + name);
		else if(setNames.isEmpty())
			return null;
		return (Variety)setNames.iterator().next();
	}

	@Override
	public Variety findVarietyById(BigDecimal id) {
		// TODO Auto-generated method stub
		return this.findVIricstockBasicpropByPrimaryKey(id.intValue());
		//return this.fin
	}
	
	
	@Override
	public Variety findVarietyByNameLike(String name) {
		// TODO Auto-generated method stub
		Set setNames =  this.findVIricstockBasicpropByNameContaining(name);
		if(setNames.size()>1)
			throw new RuntimeException("Multiple varities with name " + name);
		else if(setNames.isEmpty())
			return null;
		return (Variety)setNames.iterator().next();
	}

	
/*
	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBasicprop> findVIricstockBasicpropBySubpopulation(String subpopulation) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBasicpropBySubpopulation", -1, -1, subpopulation);
		return new LinkedHashSet<VIricstockBasicprop>(query.getResultList());
	}
	*/
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VIricstockBasicprop> findVIricstockBasicpropByOriCountrySubpopulation(String country, String subpopulation) throws DataAccessException {
		Query query = createNamedQuery("findVIricstockBasicpropByOriCountrySubpopulation", -1, -1, country, subpopulation);
		return new LinkedHashSet<VIricstockBasicprop>(query.getResultList());
	}
	
	
}
