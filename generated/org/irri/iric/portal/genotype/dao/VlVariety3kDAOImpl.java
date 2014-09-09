package org.irri.iric.portal.genotype.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.genotype.domain.VlVariety3k;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VlVariety3k entities.
 * 
 */
@Repository("VlVariety3kDAO")
@Transactional
public class VlVariety3kDAOImpl extends AbstractJpaDao<VlVariety3k> implements
		VlVariety3kDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VlVariety3k.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VlVariety3kDAOImpl
	 *
	 */
	public VlVariety3kDAOImpl() {
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
	 * JPQL Query - findAllVlVariety3ks
	 *
	 */
	@Transactional
	public Set<VlVariety3k> findAllVlVariety3ks() throws DataAccessException {

		return findAllVlVariety3ks(-1, -1);
	}

	/**
	 * JPQL Query - findAllVlVariety3ks
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlVariety3k> findAllVlVariety3ks(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVlVariety3ks", startResult, maxRows);
		return new LinkedHashSet<VlVariety3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlVariety3kByNameContaining
	 *
	 */
	@Transactional
	public Set<VlVariety3k> findVlVariety3kByNameContaining(String name) throws DataAccessException {

		return findVlVariety3kByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVlVariety3kByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlVariety3k> findVlVariety3kByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlVariety3kByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VlVariety3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlVariety3kByIrisUniqueIdContaining
	 *
	 */
	@Transactional
	public Set<VlVariety3k> findVlVariety3kByIrisUniqueIdContaining(String irisUniqueId) throws DataAccessException {

		return findVlVariety3kByIrisUniqueIdContaining(irisUniqueId, -1, -1);
	}

	/**
	 * JPQL Query - findVlVariety3kByIrisUniqueIdContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlVariety3k> findVlVariety3kByIrisUniqueIdContaining(String irisUniqueId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlVariety3kByIrisUniqueIdContaining", startResult, maxRows, irisUniqueId);
		return new LinkedHashSet<VlVariety3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlVariety3kByOriCountryContaining
	 *
	 */
	@Transactional
	public Set<VlVariety3k> findVlVariety3kByOriCountryContaining(String oriCountry) throws DataAccessException {

		return findVlVariety3kByOriCountryContaining(oriCountry, -1, -1);
	}

	/**
	 * JPQL Query - findVlVariety3kByOriCountryContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlVariety3k> findVlVariety3kByOriCountryContaining(String oriCountry, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlVariety3kByOriCountryContaining", startResult, maxRows, oriCountry);
		return new LinkedHashSet<VlVariety3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlVariety3kByPrimaryKey
	 *
	 */
	@Transactional
	public VlVariety3k findVlVariety3kByPrimaryKey(Integer varId) throws DataAccessException {

		return findVlVariety3kByPrimaryKey(varId, -1, -1);
	}

	/**
	 * JPQL Query - findVlVariety3kByPrimaryKey
	 *
	 */

	@Transactional
	public VlVariety3k findVlVariety3kByPrimaryKey(Integer varId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVlVariety3kByPrimaryKey", startResult, maxRows, varId);
			return (org.irri.iric.portal.genotype.domain.VlVariety3k) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVlVariety3kByIrisUniqueId
	 *
	 */
	@Transactional
	public Set<VlVariety3k> findVlVariety3kByIrisUniqueId(String irisUniqueId) throws DataAccessException {

		return findVlVariety3kByIrisUniqueId(irisUniqueId, -1, -1);
	}

	/**
	 * JPQL Query - findVlVariety3kByIrisUniqueId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlVariety3k> findVlVariety3kByIrisUniqueId(String irisUniqueId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlVariety3kByIrisUniqueId", startResult, maxRows, irisUniqueId);
		return new LinkedHashSet<VlVariety3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlVariety3kByOriCountry
	 *
	 */
	@Transactional
	public Set<VlVariety3k> findVlVariety3kByOriCountry(String oriCountry) throws DataAccessException {

		return findVlVariety3kByOriCountry(oriCountry, -1, -1);
	}

	/**
	 * JPQL Query - findVlVariety3kByOriCountry
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlVariety3k> findVlVariety3kByOriCountry(String oriCountry, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlVariety3kByOriCountry", startResult, maxRows, oriCountry);
		return new LinkedHashSet<VlVariety3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findVlVariety3kByVarId
	 *
	 */
	@Transactional
	public VlVariety3k findVlVariety3kByVarId(Integer varId) throws DataAccessException {

		return findVlVariety3kByVarId(varId, -1, -1);
	}

	/**
	 * JPQL Query - findVlVariety3kByVarId
	 *
	 */

	@Transactional
	public VlVariety3k findVlVariety3kByVarId(Integer varId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVlVariety3kByVarId", startResult, maxRows, varId);
			return (org.irri.iric.portal.genotype.domain.VlVariety3k) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVlVariety3kByName
	 *
	 */
	@Transactional
	public Set<VlVariety3k> findVlVariety3kByName(String name) throws DataAccessException {

		return findVlVariety3kByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVlVariety3kByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VlVariety3k> findVlVariety3kByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVlVariety3kByName", startResult, maxRows, name);
		return new LinkedHashSet<VlVariety3k>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VlVariety3k entity) {
		return true;
	}

	@Override
	public Set findAllVariety() {
		// TODO Auto-generated method stub
		return this.findAllVlVariety3ks();
	}

	@Override
	public Set findAllVarietyByCountry(String country) {
		// TODO Auto-generated method stub
		return this.findVlVariety3kByOriCountry(country);
	}

	@Override
	public Set findAllVarietyBySubpopulation(String subpopulation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set findAllVarietyByExample(Variety germplasm) {
		// TODO Auto-generated method stub
		  CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		  CriteriaQuery q = cb.createQuery( VlVariety3k.class);
		  Root<VlVariety3k> c = q.from(VlVariety3k.class);
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
			 
			  return new java.util.LinkedHashSet<VlVariety3k>( testQuery.getResultList() );
	}

	@Override
	public Variety findVarietyByName(String name) {
		// TODO Auto-generated method stub
		Set setNames =  this.findVlVariety3kByName(name);
		if(setNames.size()>1)
			throw new RuntimeException("Multiple varities with name " + name);
		else if(setNames.isEmpty())
			return null;
		return (Variety)setNames.iterator().next();
	}

	@Override
	public Variety findVarietykByIrisId(String name) {
		// TODO Auto-generated method stub
		Set setNames =  this.findVlVariety3kByIrisUniqueId(name);
		if(setNames.size()>1)
			throw new RuntimeException("Multiple varities with name " + name);
		else if(setNames.isEmpty())
			return null;
		return (Variety)setNames.iterator().next();
	}

	@Override
	public Variety findVarietykById(Integer id) {
		// TODO Auto-generated method stub
		return this.findVlVariety3kByPrimaryKey(id);
	}
	
	
	
}
