package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.postgres.domain.VPatoOrganism;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VPatoOrganism entities.
 * 
 */
@Repository("VPatoOrganismDAO")
@Primary()
@Transactional
public class VPatoOrganismDAOImpl extends AbstractJpaDao<VPatoOrganism>
		implements VPatoOrganismDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VPatoOrganism.class }));

	/**
	 * EntityManager injected by Spring for persistence unit VM_IRIC
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VPatoOrganismDAOImpl
	 *
	 */
	public VPatoOrganismDAOImpl() {
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
	 * JPQL Query - findVPatoOrganismByCvName
	 *
	 */
	@Transactional
	public Set<VPatoOrganism> findVPatoOrganismByCvName(String cvName) throws DataAccessException {

		return findVPatoOrganismByCvName(cvName, -1, -1);
	}

	/**
	 * JPQL Query - findVPatoOrganismByCvName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VPatoOrganism> findVPatoOrganismByCvName(String cvName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVPatoOrganismByCvName", startResult, maxRows, cvName);
		return new LinkedHashSet<VPatoOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVPatoOrganismByAccession
	 *
	 */
	@Transactional
	public Set<VPatoOrganism> findVPatoOrganismByAccession(String accession) throws DataAccessException {

		return findVPatoOrganismByAccession(accession, -1, -1);
	}

	/**
	 * JPQL Query - findVPatoOrganismByAccession
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VPatoOrganism> findVPatoOrganismByAccession(String accession, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVPatoOrganismByAccession", startResult, maxRows, accession);
		return new LinkedHashSet<VPatoOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVPatoOrganismByCvterm
	 *
	 */
	@Transactional
	public Set<VPatoOrganism> findVPatoOrganismByCvterm(String cvterm) throws DataAccessException {

		return findVPatoOrganismByCvterm(cvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVPatoOrganismByCvterm
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VPatoOrganism> findVPatoOrganismByCvterm(String cvterm, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVPatoOrganismByCvterm", startResult, maxRows, cvterm);
		return new LinkedHashSet<VPatoOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVPatoOrganismByOrganismId
	 *
	 */
	@Transactional
	public Set<VPatoOrganism> findVPatoOrganismByOrganismId(Long organismId) throws DataAccessException {

		return findVPatoOrganismByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVPatoOrganismByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VPatoOrganism> findVPatoOrganismByOrganismId(Long organismId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVPatoOrganismByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<VPatoOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVPatoOrganismByPrimaryKey
	 *
	 */
	@Transactional
	public VPatoOrganism findVPatoOrganismByPrimaryKey(Long cvtermId) throws DataAccessException {

		return findVPatoOrganismByPrimaryKey(cvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVPatoOrganismByPrimaryKey
	 *
	 */

	@Transactional
	public VPatoOrganism findVPatoOrganismByPrimaryKey(Long cvtermId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVPatoOrganismByPrimaryKey", startResult, maxRows, cvtermId);
			return (org.irri.iric.portal.chado.postgres.domain.VPatoOrganism) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVPatoOrganismByCommonNameContaining
	 *
	 */
	@Transactional
	public Set<VPatoOrganism> findVPatoOrganismByCommonNameContaining(String commonName) throws DataAccessException {

		return findVPatoOrganismByCommonNameContaining(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVPatoOrganismByCommonNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VPatoOrganism> findVPatoOrganismByCommonNameContaining(String commonName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVPatoOrganismByCommonNameContaining", startResult, maxRows, commonName);
		return new LinkedHashSet<VPatoOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVPatoOrganismByCvNameContaining
	 *
	 */
	@Transactional
	public Set<VPatoOrganism> findVPatoOrganismByCvNameContaining(String cvName) throws DataAccessException {

		return findVPatoOrganismByCvNameContaining(cvName, -1, -1);
	}

	/**
	 * JPQL Query - findVPatoOrganismByCvNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VPatoOrganism> findVPatoOrganismByCvNameContaining(String cvName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVPatoOrganismByCvNameContaining", startResult, maxRows, cvName);
		return new LinkedHashSet<VPatoOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVPatoOrganisms
	 *
	 */
	@Transactional
	public Set<VPatoOrganism> findAllVPatoOrganisms() throws DataAccessException {

		return findAllVPatoOrganisms(-1, -1);
	}

	/**
	 * JPQL Query - findAllVPatoOrganisms
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VPatoOrganism> findAllVPatoOrganisms(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVPatoOrganisms", startResult, maxRows);
		return new LinkedHashSet<VPatoOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVPatoOrganismByCommonName
	 *
	 */
	@Transactional
	public Set<VPatoOrganism> findVPatoOrganismByCommonName(String commonName) throws DataAccessException {

		return findVPatoOrganismByCommonName(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVPatoOrganismByCommonName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VPatoOrganism> findVPatoOrganismByCommonName(String commonName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVPatoOrganismByCommonName", startResult, maxRows, commonName);
		return new LinkedHashSet<VPatoOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVPatoOrganismByCvtermId
	 *
	 */
	@Transactional
	public VPatoOrganism findVPatoOrganismByCvtermId(Long cvtermId) throws DataAccessException {

		return findVPatoOrganismByCvtermId(cvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVPatoOrganismByCvtermId
	 *
	 */

	@Transactional
	public VPatoOrganism findVPatoOrganismByCvtermId(Long cvtermId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVPatoOrganismByCvtermId", startResult, maxRows, cvtermId);
			return (org.irri.iric.portal.chado.postgres.domain.VPatoOrganism) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVPatoOrganismByAccessionContaining
	 *
	 */
	@Transactional
	public Set<VPatoOrganism> findVPatoOrganismByAccessionContaining(String accession) throws DataAccessException {

		return findVPatoOrganismByAccessionContaining(accession, -1, -1);
	}

	/**
	 * JPQL Query - findVPatoOrganismByAccessionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VPatoOrganism> findVPatoOrganismByAccessionContaining(String accession, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVPatoOrganismByAccessionContaining", startResult, maxRows, accession);
		return new LinkedHashSet<VPatoOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVPatoOrganismByCvtermContaining
	 *
	 */
	@Transactional
	public Set<VPatoOrganism> findVPatoOrganismByCvtermContaining(String cvterm) throws DataAccessException {

		return findVPatoOrganismByCvtermContaining(cvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVPatoOrganismByCvtermContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VPatoOrganism> findVPatoOrganismByCvtermContaining(String cvterm, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVPatoOrganismByCvtermContaining", startResult, maxRows, cvterm);
		return new LinkedHashSet<VPatoOrganism>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VPatoOrganism entity) {
		return true;
	}
	
	

	@Override
	public List getAllTerms() {
		// TODO Auto-generated method stub
		List list=new ArrayList();
		list.addAll( this.findAllVPatoOrganisms() );
		return list;
	}
	/*
	@Override
	public List getAllTermsByOrganism(String organism) {
		// TODO Auto-generated method stub
		
		Set setTerms = new TreeSet();
		Iterator<VGoOrganism> itgoorg = findVGoOrganismByCommonName(organism).iterator(); 
		while(itgoorg.hasNext())
			setTerms.add(  itgoorg.next().getCvterm() );
		
		List list=new ArrayList();
		list.addAll(setTerms );
		return list;
	}
*/
	@Override
	public List getAllTerms(String organism) {
		// TODO Auto-generated method stub

		Set setTerms = new TreeSet();
		Iterator<VPatoOrganism> itgoorg = findVPatoOrganismByCommonName(organism).iterator(); 
		while(itgoorg.hasNext())
			setTerms.add(  itgoorg.next().getCvterm() );
		
		List list=new ArrayList();
		list.addAll(setTerms );
		AppContext.debug(list.size() + " cv terms for " + organism);
		return list;
	}

	@Override
	public List getAllTerms(String cv, String organism) {
		// TODO Auto-generated method stub
		Set setTerms = new TreeSet();
		Iterator<VPatoOrganism> itgoorg = findVPatoOrganismByCvCommonName(cv,organism,-1,-1).iterator(); 
		while(itgoorg.hasNext())
			setTerms.add(  itgoorg.next().getCvterm() );
		
		List list=new ArrayList();
		list.addAll(setTerms );
		AppContext.debug(list.size() + " " + cv + " terms for " + organism);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	private Set<VPatoOrganism> findVPatoOrganismByCvCommonName(String cv, String commonName, int startResult, int maxRows) throws DataAccessException {
		AppContext.debug("PGfindVPatoOrganismByCvCommonName " + cv +  "  , " + commonName);
		Query query = createNamedQuery("PGfindVPatoOrganismByCvCommonName", startResult, maxRows, cv, commonName);
		return new LinkedHashSet<VPatoOrganism>(query.getResultList());
	}

}
