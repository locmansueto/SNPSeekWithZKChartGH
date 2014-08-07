package org.irri.iric.portal.variety.dao;

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
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;


//import javax.persistence.criteria.Criteria;
//import org.hibernate.Session;
//import org.hibernate.criterion.Example;
import org.irri.iric.portal.variety.domain.Germplasm;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage Germplasm entities.
 * 
 */
@Repository("GermplasmDAO")
@Transactional
public class GermplasmDAOImpl extends AbstractJpaDao<Germplasm> implements
		GermplasmDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { Germplasm.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Development
	 *
	 */
	@PersistenceContext(unitName = "Development")
	private EntityManager entityManager;

	/**
	 * Instantiates a new GermplasmDAOImpl
	 *
	 */
	public GermplasmDAOImpl() {
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
	 * JPQL Query - findGermplasmByLongitude
	 *
	 */
	@Transactional
	public Set<Germplasm> findGermplasmByLongitude(Integer longitude) throws DataAccessException {

		return findGermplasmByLongitude(longitude, -1, -1);
	}

	/**
	 * JPQL Query - findGermplasmByLongitude
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Germplasm> findGermplasmByLongitude(Integer longitude, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGermplasmByLongitude", startResult, maxRows, longitude);
		return new LinkedHashSet<Germplasm>(query.getResultList());
	}

	/**
	 * JPQL Query - findGermplasmBySubpopulation
	 *
	 */
	@Transactional
	public Set<Germplasm> findGermplasmBySubpopulation(String subpopulation) throws DataAccessException {

		return findGermplasmBySubpopulation(subpopulation, -1, -1);
	}

	/**
	 * JPQL Query - findGermplasmBySubpopulation
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Germplasm> findGermplasmBySubpopulation(String subpopulation, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGermplasmBySubpopulation", startResult, maxRows, subpopulation);
		return new LinkedHashSet<Germplasm>(query.getResultList());
	}

	/**
	 * JPQL Query - findGermplasmByAccessionContaining
	 *
	 */
	@Transactional
	public Set<Germplasm> findGermplasmByAccessionContaining(String accession) throws DataAccessException {

		return findGermplasmByAccessionContaining(accession, -1, -1);
	}

	/**
	 * JPQL Query - findGermplasmByAccessionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Germplasm> findGermplasmByAccessionContaining(String accession, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGermplasmByAccessionContaining", startResult, maxRows, accession);
		return new LinkedHashSet<Germplasm>(query.getResultList());
	}

	/**
	 * JPQL Query - findGermplasmByAccession
	 *
	 */
	@Transactional
	public Set<Germplasm> findGermplasmByAccession(String accession) throws DataAccessException {

		return findGermplasmByAccession(accession, -1, -1);
	}

	/**
	 * JPQL Query - findGermplasmByAccession
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Germplasm> findGermplasmByAccession(String accession, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGermplasmByAccession", startResult, maxRows, accession);
		return new LinkedHashSet<Germplasm>(query.getResultList());
	}

	/**
	 * JPQL Query - findGermplasmByPrimaryKey
	 *
	 */
	@Transactional
	public Germplasm findGermplasmByPrimaryKey(Integer nsftvId) throws DataAccessException {

		return findGermplasmByPrimaryKey(nsftvId, -1, -1);
	}

	/**
	 * JPQL Query - findGermplasmByPrimaryKey
	 *
	 */

	@Transactional
	public Germplasm findGermplasmByPrimaryKey(Integer nsftvId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findGermplasmByPrimaryKey", startResult, maxRows, nsftvId);
			return (org.irri.iric.portal.variety.domain.Germplasm) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findGermplasmByCountry
	 *
	 */
	@Transactional
	public Set<Germplasm> findGermplasmByCountry(String country) throws DataAccessException {

		return findGermplasmByCountry(country, -1, -1);
	}

	/**
	 * JPQL Query - findGermplasmByCountry
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Germplasm> findGermplasmByCountry(String country, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGermplasmByCountry", startResult, maxRows, country);
		return new LinkedHashSet<Germplasm>(query.getResultList());
	}

	/**
	 * JPQL Query - findGermplasmBySubpopulationContaining
	 *
	 */
	@Transactional
	public Set<Germplasm> findGermplasmBySubpopulationContaining(String subpopulation) throws DataAccessException {

		return findGermplasmBySubpopulationContaining(subpopulation, -1, -1);
	}

	/**
	 * JPQL Query - findGermplasmBySubpopulationContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Germplasm> findGermplasmBySubpopulationContaining(String subpopulation, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGermplasmBySubpopulationContaining", startResult, maxRows, subpopulation);
		return new LinkedHashSet<Germplasm>(query.getResultList());
	}

	/**
	 * JPQL Query - findGermplasmByNsftvId
	 *
	 */
	@Transactional
	public Germplasm findGermplasmByNsftvId(Integer nsftvId) throws DataAccessException {

		return findGermplasmByNsftvId(nsftvId, -1, -1);
	}

	/**
	 * JPQL Query - findGermplasmByNsftvId
	 *
	 */

	@Transactional
	public Germplasm findGermplasmByNsftvId(Integer nsftvId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findGermplasmByNsftvId", startResult, maxRows, nsftvId);
			return (org.irri.iric.portal.variety.domain.Germplasm) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findGermplasmByCountryContaining
	 *
	 */
	@Transactional
	public Set<Germplasm> findGermplasmByCountryContaining(String country) throws DataAccessException {

		return findGermplasmByCountryContaining(country, -1, -1);
	}

	/**
	 * JPQL Query - findGermplasmByCountryContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Germplasm> findGermplasmByCountryContaining(String country, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGermplasmByCountryContaining", startResult, maxRows, country);
		return new LinkedHashSet<Germplasm>(query.getResultList());
	}

	/**
	 * JPQL Query - findGermplasmByLatitude
	 *
	 */
	@Transactional
	public Set<Germplasm> findGermplasmByLatitude(Integer latitude) throws DataAccessException {

		return findGermplasmByLatitude(latitude, -1, -1);
	}

	/**
	 * JPQL Query - findGermplasmByLatitude
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Germplasm> findGermplasmByLatitude(Integer latitude, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findGermplasmByLatitude", startResult, maxRows, latitude);
		return new LinkedHashSet<Germplasm>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllGermplasms
	 *
	 */
	@Transactional
	public Set<Germplasm> findAllGermplasms() throws DataAccessException {

		return findAllGermplasms(-1, -1);
	}

	/**
	 * JPQL Query - findAllGermplasms
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Germplasm> findAllGermplasms(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllGermplasms", startResult, maxRows);
		return new LinkedHashSet<Germplasm>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(Germplasm entity) {
		return true;
	}

	@Override
	public Set<Germplasm> findAllGermplasmByExample(Germplasm germplasm)
			throws DataAccessException {
		// TODO Auto-generated method stub
			
		  CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		  CriteriaQuery q = cb.createQuery(Germplasm.class);
		  Root<Germplasm> c = q.from(Germplasm.class);
		  q.select(c);


			  if(germplasm.getCountry()!=null && germplasm.getSubpopulation()!=null)	
			  {
				  Expression<String> country = cb.literal(germplasm.getCountry() );
				  Expression<String> subpop = cb.parameter(String.class);
				  q.where(  cb.and( cb.equal( cb.upper( c.<String>get("country")  ), germplasm.getCountry().toUpperCase() ),
						  			cb.equal( cb.upper( c.<String>get("subpopulation")), cb.literal(germplasm.getSubpopulation().toUpperCase() ) ) ));
			  }
			  else if (germplasm.getCountry()!=null)
			  {
					q.where(   cb.equal( cb.upper( c.<String>get("country")),  cb.literal(germplasm.getCountry().toUpperCase() )) );
			  }
			  else if (germplasm.getSubpopulation()!=null)
					q.where(   cb.equal( cb.upper( c.<String>get("subpopulation")),  cb.literal(germplasm.getSubpopulation().toUpperCase() )  )  );
		 
			  Query testQuery = entityManager.createQuery(q);
			 
			  return new java.util.LinkedHashSet<Germplasm>( testQuery.getResultList() );
			
		 
		/*
		Session session = (Session) entityManager.unwrap(Session.class);	
	
		Criteria criteria = session.createCriteria(Germplasm.class).add( Example.create(germplasm));
		
		return new java.util.LinkedHashSet<Germplasm>(criteria.list());
		*/
	}
	
	
	
}
