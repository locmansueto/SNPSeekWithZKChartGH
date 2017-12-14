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

import org.irri.iric.portal.chado.postgres.domain.VCvPhenotype;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VCvPhenotype entities.
 * 
 */
@Repository("VCvPhenotypeDAO")
@Primary()
@Transactional
public class VCvPhenotypeDAOImpl extends AbstractJpaDao<VCvPhenotype> implements
		VCvPhenotypeDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VCvPhenotype.class }));

	/**
	 * EntityManager injected by Spring for persistence unit VM_IRIC
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VCvPhenotypeDAOImpl
	 *
	 */
	public VCvPhenotypeDAOImpl() {
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
	 * JPQL Query - findVCvPhenotypeByPrimaryKey
	 *
	 */
	@Transactional
	public VCvPhenotype findVCvPhenotypeByPrimaryKey(Integer cvtermId) throws DataAccessException {

		return findVCvPhenotypeByPrimaryKey(cvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByPrimaryKey
	 *
	 */

	@Transactional
	public VCvPhenotype findVCvPhenotypeByPrimaryKey(Integer cvtermId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVCvPhenotypeByPrimaryKey", startResult, maxRows, cvtermId);
			return (org.irri.iric.portal.chado.postgres.domain.VCvPhenotype) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVCvPhenotypeByNameContaining
	 *
	 */
	@Transactional
	public Set<VCvPhenotype> findVCvPhenotypeByNameContaining(String name) throws DataAccessException {

		return findVCvPhenotypeByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotype> findVCvPhenotypeByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VCvPhenotype>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVCvPhenotypes
	 *
	 */
	@Transactional
	public List<VCvPhenotype> findAllVCvPhenotypes() throws DataAccessException {

		return findAllVCvPhenotypes(-1, -1);
	}

	/**
	 * JPQL Query - findAllVCvPhenotypes
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public List<VCvPhenotype> findAllVCvPhenotypes(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVCvPhenotypes", startResult, maxRows);
		//return new LinkedHashSet<VCvPhenotype>(query.getResultList());
		return query.getResultList();
	}

	/**
	 * JPQL Query - findVCvPhenotypeByDefinitionContaining
	 *
	 */
	@Transactional
	public Set<VCvPhenotype> findVCvPhenotypeByDefinitionContaining(String definition) throws DataAccessException {

		return findVCvPhenotypeByDefinitionContaining(definition, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByDefinitionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotype> findVCvPhenotypeByDefinitionContaining(String definition, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByDefinitionContaining", startResult, maxRows, definition);
		return new LinkedHashSet<VCvPhenotype>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByName
	 *
	 */
	@Transactional
	public Set<VCvPhenotype> findVCvPhenotypeByName(String name) throws DataAccessException {

		return findVCvPhenotypeByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotype> findVCvPhenotypeByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByName", startResult, maxRows, name);
		return new LinkedHashSet<VCvPhenotype>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByDefinition
	 *
	 */
	@Transactional
	public Set<VCvPhenotype> findVCvPhenotypeByDefinition(String definition) throws DataAccessException {

		return findVCvPhenotypeByDefinition(definition, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByDefinition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvPhenotype> findVCvPhenotypeByDefinition(String definition, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvPhenotypeByDefinition", startResult, maxRows, definition);
		return new LinkedHashSet<VCvPhenotype>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvPhenotypeByCvtermId
	 *
	 */
	@Transactional
	public VCvPhenotype findVCvPhenotypeByCvtermId(Integer cvtermId) throws DataAccessException {

		return findVCvPhenotypeByCvtermId(cvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVCvPhenotypeByCvtermId
	 *
	 */

	@Transactional
	public VCvPhenotype findVCvPhenotypeByCvtermId(Integer cvtermId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVCvPhenotypeByCvtermId", startResult, maxRows, cvtermId);
			return (org.irri.iric.portal.chado.postgres.domain.VCvPhenotype) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VCvPhenotype entity) {
		return true;
	}
	
	
	@Override
	public List getAllTerms() {
		// TODO Auto-generated method stub
		return this.findAllVCvPhenotypes();
	}

	@Override
	public List getAllTerms(String organism) {
		// TODO Auto-generated method stub
		return findAllVCvPhenotypes();
	}

	@Override
	public List getAllTerms(String cv, String organism) {
		// TODO Auto-generated method stub
		return findAllVCvPhenotypes();
	}
	
}
