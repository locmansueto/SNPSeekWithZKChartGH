package org.irri.iric.portal.chado.dao;

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

import org.irri.iric.portal.chado.domain.VCvtermDbxref;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VCvtermDbxref entities.
 * 
 */
@Repository("VCvtermDbxrefDAO")
@Transactional
public class VCvtermDbxrefDAOImpl extends AbstractJpaDao<VCvtermDbxref>
		implements VCvtermDbxrefDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VCvtermDbxref.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VCvtermDbxrefDAOImpl
	 *
	 */
	public VCvtermDbxrefDAOImpl() {
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
	 * JPQL Query - findVCvtermDbxrefByDefinition
	 *
	 */
	@Transactional
	public Set<VCvtermDbxref> findVCvtermDbxrefByDefinition(String definition) throws DataAccessException {

		return findVCvtermDbxrefByDefinition(definition, -1, -1);
	}

	/**
	 * JPQL Query - findVCvtermDbxrefByDefinition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvtermDbxref> findVCvtermDbxrefByDefinition(String definition, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvtermDbxrefByDefinition", startResult, maxRows, definition);
		return new LinkedHashSet<VCvtermDbxref>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvtermDbxrefByPrimaryKey
	 *
	 */
	@Transactional
	public VCvtermDbxref findVCvtermDbxrefByPrimaryKey(Integer cvtermId) throws DataAccessException {

		return findVCvtermDbxrefByPrimaryKey(cvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVCvtermDbxrefByPrimaryKey
	 *
	 */

	@Transactional
	public VCvtermDbxref findVCvtermDbxrefByPrimaryKey(Integer cvtermId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVCvtermDbxrefByPrimaryKey", startResult, maxRows, cvtermId);
			return (org.irri.iric.portal.chado.domain.VCvtermDbxref) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVCvtermDbxrefByCvName
	 *
	 */
	@Transactional
	public Set<VCvtermDbxref> findVCvtermDbxrefByCvName(String cvName) throws DataAccessException {

		return findVCvtermDbxrefByCvName(cvName, -1, -1);
	}

	/**
	 * JPQL Query - findVCvtermDbxrefByCvName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvtermDbxref> findVCvtermDbxrefByCvName(String cvName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvtermDbxrefByCvName", startResult, maxRows, cvName);
		return new LinkedHashSet<VCvtermDbxref>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVCvtermDbxrefs
	 *
	 */
	@Transactional
	public Set<VCvtermDbxref> findAllVCvtermDbxrefs() throws DataAccessException {

		return findAllVCvtermDbxrefs(-1, -1);
	}

	/**
	 * JPQL Query - findAllVCvtermDbxrefs
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvtermDbxref> findAllVCvtermDbxrefs(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVCvtermDbxrefs", startResult, maxRows);
		return new LinkedHashSet<VCvtermDbxref>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvtermDbxrefByCvtermId
	 *
	 */
	@Transactional
	public VCvtermDbxref findVCvtermDbxrefByCvtermId(Integer cvtermId) throws DataAccessException {

		return findVCvtermDbxrefByCvtermId(cvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVCvtermDbxrefByCvtermId
	 *
	 */

	@Transactional
	public VCvtermDbxref findVCvtermDbxrefByCvtermId(Integer cvtermId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVCvtermDbxrefByCvtermId", startResult, maxRows, cvtermId);
			return (org.irri.iric.portal.chado.domain.VCvtermDbxref) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVCvtermDbxrefByName
	 *
	 */
	@Transactional
	public Set<VCvtermDbxref> findVCvtermDbxrefByName(String name) throws DataAccessException {

		return findVCvtermDbxrefByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVCvtermDbxrefByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvtermDbxref> findVCvtermDbxrefByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvtermDbxrefByName", startResult, maxRows, name);
		return new LinkedHashSet<VCvtermDbxref>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvtermDbxrefByAccessionContaining
	 *
	 */
	@Transactional
	public Set<VCvtermDbxref> findVCvtermDbxrefByAccessionContaining(String accession) throws DataAccessException {

		return findVCvtermDbxrefByAccessionContaining(accession, -1, -1);
	}

	/**
	 * JPQL Query - findVCvtermDbxrefByAccessionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvtermDbxref> findVCvtermDbxrefByAccessionContaining(String accession, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvtermDbxrefByAccessionContaining", startResult, maxRows, accession);
		return new LinkedHashSet<VCvtermDbxref>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvtermDbxrefByNameContaining
	 *
	 */
	@Transactional
	public Set<VCvtermDbxref> findVCvtermDbxrefByNameContaining(String name) throws DataAccessException {

		return findVCvtermDbxrefByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVCvtermDbxrefByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvtermDbxref> findVCvtermDbxrefByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvtermDbxrefByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VCvtermDbxref>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvtermDbxrefByDefinitionContaining
	 *
	 */
	@Transactional
	public Set<VCvtermDbxref> findVCvtermDbxrefByDefinitionContaining(String definition) throws DataAccessException {

		return findVCvtermDbxrefByDefinitionContaining(definition, -1, -1);
	}

	/**
	 * JPQL Query - findVCvtermDbxrefByDefinitionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvtermDbxref> findVCvtermDbxrefByDefinitionContaining(String definition, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvtermDbxrefByDefinitionContaining", startResult, maxRows, definition);
		return new LinkedHashSet<VCvtermDbxref>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvtermDbxrefByCvNameContaining
	 *
	 */
	@Transactional
	public Set<VCvtermDbxref> findVCvtermDbxrefByCvNameContaining(String cvName) throws DataAccessException {

		return findVCvtermDbxrefByCvNameContaining(cvName, -1, -1);
	}

	/**
	 * JPQL Query - findVCvtermDbxrefByCvNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvtermDbxref> findVCvtermDbxrefByCvNameContaining(String cvName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvtermDbxrefByCvNameContaining", startResult, maxRows, cvName);
		return new LinkedHashSet<VCvtermDbxref>(query.getResultList());
	}

	/**
	 * JPQL Query - findVCvtermDbxrefByAccession
	 *
	 */
	@Transactional
	public Set<VCvtermDbxref> findVCvtermDbxrefByAccession(String accession) throws DataAccessException {

		return findVCvtermDbxrefByAccession(accession, -1, -1);
	}

	/**
	 * JPQL Query - findVCvtermDbxrefByAccession
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VCvtermDbxref> findVCvtermDbxrefByAccession(String accession, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVCvtermDbxrefByAccession", startResult, maxRows, accession);
		return new LinkedHashSet<VCvtermDbxref>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VCvtermDbxref entity) {
		return true;
	}

	@Override
	public List getAllTerms() {
		// TODO Auto-generated method stub
		List list=new ArrayList();
		list.addAll(findAllVCvtermDbxrefs());
		return list;
	}

	@Override
	public List getAllTerms(String organism) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getAllTerms(String cv, String organism) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
