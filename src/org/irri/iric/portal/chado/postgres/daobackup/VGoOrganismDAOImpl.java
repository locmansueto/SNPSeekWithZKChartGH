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
import org.irri.iric.portal.chado.postgres.domain.VGoOrganism;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VGoOrganism entities.
 * 
 */
@Repository("VGoOrganismDAO")
@Primary()
@Transactional
public class VGoOrganismDAOImpl extends AbstractJpaDao<VGoOrganism> implements
		VGoOrganismDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VGoOrganism.class }));

	/**
	 * EntityManager injected by Spring for persistence unit VM_IRIC
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VGoOrganismDAOImpl
	 *
	 */
	public VGoOrganismDAOImpl() {
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
	 * JPQL Query - findVGoOrganismByAccessionContaining
	 *
	 */
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByAccessionContaining(String accession) throws DataAccessException {

		return findVGoOrganismByAccessionContaining(accession, -1, -1);
	}

	/**
	 * JPQL Query - findVGoOrganismByAccessionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByAccessionContaining(String accession, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindVGoOrganismByAccessionContaining", startResult, maxRows, accession);
		return new LinkedHashSet<VGoOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGoOrganismByCvName
	 *
	 */
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByCvName(String cvName) throws DataAccessException {

		return findVGoOrganismByCvName(cvName, -1, -1);
	}

	/**
	 * JPQL Query - findVGoOrganismByCvName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByCvName(String cvName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindVGoOrganismByCvName", startResult, maxRows, cvName);
		return new LinkedHashSet<VGoOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGoOrganismByOrganismId
	 *
	 */
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByOrganismId(Long organismId) throws DataAccessException {

		return findVGoOrganismByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVGoOrganismByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByOrganismId(Long organismId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindVGoOrganismByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<VGoOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGoOrganismByCommonNameContaining
	 *
	 */
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByCommonNameContaining(String commonName) throws DataAccessException {

		return findVGoOrganismByCommonNameContaining(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVGoOrganismByCommonNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByCommonNameContaining(String commonName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindVGoOrganismByCommonNameContaining", startResult, maxRows, commonName);
		return new LinkedHashSet<VGoOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVGoOrganisms
	 *
	 */
	@Transactional
	public Set<VGoOrganism> findAllVGoOrganisms() throws DataAccessException {

		return findAllVGoOrganisms(-1, -1);
	}

	/**
	 * JPQL Query - findAllVGoOrganisms
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoOrganism> findAllVGoOrganisms(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindAllVGoOrganisms", startResult, maxRows);
		return new LinkedHashSet<VGoOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGoOrganismByPrimaryKey
	 *
	 */
	@Transactional
	public VGoOrganism findVGoOrganismByPrimaryKey(Long cvtermId) throws DataAccessException {

		return findVGoOrganismByPrimaryKey(cvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVGoOrganismByPrimaryKey
	 *
	 */

	@Transactional
	public VGoOrganism findVGoOrganismByPrimaryKey(Long cvtermId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("PGfindVGoOrganismByPrimaryKey", startResult, maxRows, cvtermId);
			return (org.irri.iric.portal.chado.postgres.domain.VGoOrganism) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVGoOrganismByCvNameContaining
	 *
	 */
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByCvNameContaining(String cvName) throws DataAccessException {

		return findVGoOrganismByCvNameContaining(cvName, -1, -1);
	}

	/**
	 * JPQL Query - findVGoOrganismByCvNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByCvNameContaining(String cvName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindVGoOrganismByCvNameContaining", startResult, maxRows, cvName);
		return new LinkedHashSet<VGoOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGoOrganismByCvtermId
	 *
	 */
	@Transactional
	public VGoOrganism findVGoOrganismByCvtermId(Long cvtermId) throws DataAccessException {

		return findVGoOrganismByCvtermId(cvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVGoOrganismByCvtermId
	 *
	 */

	@Transactional
	public VGoOrganism findVGoOrganismByCvtermId(Long cvtermId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("PGfindVGoOrganismByCvtermId", startResult, maxRows, cvtermId);
			return (org.irri.iric.portal.chado.postgres.domain.VGoOrganism) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVGoOrganismByCommonName
	 *
	 */
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByCommonName(String commonName) throws DataAccessException {

		return findVGoOrganismByCommonName(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVGoOrganismByCommonName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByCommonName(String commonName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindVGoOrganismByCommonName", startResult, maxRows, commonName);
		return new LinkedHashSet<VGoOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGoOrganismByAccession
	 *
	 */
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByAccession(String accession) throws DataAccessException {

		return findVGoOrganismByAccession(accession, -1, -1);
	}

	/**
	 * JPQL Query - findVGoOrganismByAccession
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByAccession(String accession, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindVGoOrganismByAccession", startResult, maxRows, accession);
		return new LinkedHashSet<VGoOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGoOrganismByCvtermContaining
	 *
	 */
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByCvtermContaining(String cvterm) throws DataAccessException {

		return findVGoOrganismByCvtermContaining(cvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVGoOrganismByCvtermContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByCvtermContaining(String cvterm, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindVGoOrganismByCvtermContaining", startResult, maxRows, cvterm);
		return new LinkedHashSet<VGoOrganism>(query.getResultList());
	}

	/**
	 * JPQL Query - findVGoOrganismByCvterm
	 *
	 */
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByCvterm(String cvterm) throws DataAccessException {

		return findVGoOrganismByCvterm(cvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVGoOrganismByCvterm
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VGoOrganism> findVGoOrganismByCvterm(String cvterm, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("PGfindVGoOrganismByCvterm", startResult, maxRows, cvterm);
		return new LinkedHashSet<VGoOrganism>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VGoOrganism entity) {
		return true;
	}
	
	@Override
	public List getAllTerms() {
		// TODO Auto-generated method stub
		List list=new ArrayList();
		list.addAll( this.findAllVGoOrganisms() );
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
		Iterator<VGoOrganism> itgoorg = findVGoOrganismByCommonName(organism).iterator(); 
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
		Iterator<VGoOrganism> itgoorg = findVGoOrganismByCvCommonName(cv,organism,-1,-1).iterator(); 
		while(itgoorg.hasNext())
			setTerms.add(  itgoorg.next().getCvterm() );
		
		List list=new ArrayList();
		list.addAll(setTerms );
		AppContext.debug(list.size() + " " + cv + " terms for " + organism);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	private Set<VGoOrganism> findVGoOrganismByCvCommonName(String cv, String commonName, int startResult, int maxRows) throws DataAccessException {
		AppContext.debug("PGfindVGoOrganismByCvCommonName " + cv +  "  , " + commonName);
		Query query = createNamedQuery("PGfindVGoOrganismByCvCommonName", startResult, maxRows, cv, commonName);
		return new LinkedHashSet<VGoOrganism>(query.getResultList());
	}

	
}
