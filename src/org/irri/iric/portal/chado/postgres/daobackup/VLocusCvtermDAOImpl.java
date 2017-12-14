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

import org.irri.iric.portal.chado.postgres.domain.VLocusCvterm;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VLocusCvterm entities.
 * 
 */
@Repository("LocusCvtermDAO")
@Primary()
@Transactional
public class VLocusCvtermDAOImpl extends AbstractJpaDao<VLocusCvterm> implements
		VLocusCvtermDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VLocusCvterm.class }));

	/**
	 * EntityManager injected by Spring for persistence unit VM_IRIC
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VLocusCvtermDAOImpl
	 *
	 */
	public VLocusCvtermDAOImpl() {
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
	 * JPQL Query - findVLocusCvtermByContigName
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByContigName(String contigName) throws DataAccessException {

		return findVLocusCvtermByContigName(contigName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByContigName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByContigName(String contigName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByContigName", startResult, maxRows, contigName);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByName
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByName(String name) throws DataAccessException {

		return findVLocusCvtermByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByName", startResult, maxRows, name);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermBySubjCvtermContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermBySubjCvtermContaining(String subjCvterm) throws DataAccessException {

		return findVLocusCvtermBySubjCvtermContaining(subjCvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermBySubjCvtermContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermBySubjCvtermContaining(String subjCvterm, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermBySubjCvtermContaining", startResult, maxRows, subjCvterm);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermBySubjAccContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermBySubjAccContaining(String subjAcc) throws DataAccessException {

		return findVLocusCvtermBySubjAccContaining(subjAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermBySubjAccContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermBySubjAccContaining(String subjAcc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermBySubjAccContaining", startResult, maxRows, subjAcc);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByCvNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByCvNameContaining(String cvName) throws DataAccessException {

		return findVLocusCvtermByCvNameContaining(cvName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByCvNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByCvNameContaining(String cvName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByCvNameContaining", startResult, maxRows, cvName);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByObjCvtermContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByObjCvtermContaining(String objCvterm) throws DataAccessException {

		return findVLocusCvtermByObjCvtermContaining(objCvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByObjCvtermContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByObjCvtermContaining(String objCvterm, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByObjCvtermContaining", startResult, maxRows, objCvterm);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByStrand
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByStrand(Integer strand) throws DataAccessException {

		return findVLocusCvtermByStrand(strand, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByStrand
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByStrand(Integer strand, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByStrand", startResult, maxRows, strand);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByObjCvterm
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByObjCvterm(String objCvterm) throws DataAccessException {

		return findVLocusCvtermByObjCvterm(objCvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByObjCvterm
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByObjCvterm(String objCvterm, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByObjCvterm", startResult, maxRows, objCvterm);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByNameContaining(String name) throws DataAccessException {

		return findVLocusCvtermByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByCommonName
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByCommonName(String commonName) throws DataAccessException {

		return findVLocusCvtermByCommonName(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByCommonName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByCommonName(String commonName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByCommonName", startResult, maxRows, commonName);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermBySubjCvterm
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermBySubjCvterm(String subjCvterm) throws DataAccessException {

		return findVLocusCvtermBySubjCvterm(subjCvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermBySubjCvterm
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermBySubjCvterm(String subjCvterm, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermBySubjCvterm", startResult, maxRows, subjCvterm);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByFeatureId
	 *
	 */
	@Transactional
	public VLocusCvterm findVLocusCvtermByFeatureId(Integer featureId) throws DataAccessException {

		return findVLocusCvtermByFeatureId(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByFeatureId
	 *
	 */

	@Transactional
	public VLocusCvterm findVLocusCvtermByFeatureId(Integer featureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVLocusCvtermByFeatureId", startResult, maxRows, featureId);
			return (org.irri.iric.portal.chado.postgres.domain.VLocusCvterm) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVLocusCvtermByPathdistance
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByPathdistance(Integer pathdistance) throws DataAccessException {

		return findVLocusCvtermByPathdistance(pathdistance, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByPathdistance
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByPathdistance(Integer pathdistance, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByPathdistance", startResult, maxRows, pathdistance);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByNotesContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByNotesContaining(String notes) throws DataAccessException {

		return findVLocusCvtermByNotesContaining(notes, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByNotesContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByNotesContaining(String notes, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByNotesContaining", startResult, maxRows, notes);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByContigId
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByContigId(Integer contigId) throws DataAccessException {

		return findVLocusCvtermByContigId(contigId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByContigId(Integer contigId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByContigId", startResult, maxRows, contigId);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByPrimaryKey
	 *
	 */
	@Transactional
	public VLocusCvterm findVLocusCvtermByPrimaryKey(Integer featureId) throws DataAccessException {

		return findVLocusCvtermByPrimaryKey(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByPrimaryKey
	 *
	 */

	@Transactional
	public VLocusCvterm findVLocusCvtermByPrimaryKey(Integer featureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVLocusCvtermByPrimaryKey", startResult, maxRows, featureId);
			return (org.irri.iric.portal.chado.postgres.domain.VLocusCvterm) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVLocusCvtermByNotes
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByNotes(String notes) throws DataAccessException {

		return findVLocusCvtermByNotes(notes, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByNotes
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByNotes(String notes, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByNotes", startResult, maxRows, notes);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByObjAcc
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByObjAcc(String objAcc) throws DataAccessException {

		return findVLocusCvtermByObjAcc(objAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByObjAcc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByObjAcc(String objAcc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByObjAcc", startResult, maxRows, objAcc);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVLocusCvterms
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findAllVLocusCvterms() throws DataAccessException {

		return findAllVLocusCvterms(-1, -1);
	}

	/**
	 * JPQL Query - findAllVLocusCvterms
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findAllVLocusCvterms(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVLocusCvterms", startResult, maxRows);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByObjAccContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByObjAccContaining(String objAcc) throws DataAccessException {

		return findVLocusCvtermByObjAccContaining(objAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByObjAccContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByObjAccContaining(String objAcc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByObjAccContaining", startResult, maxRows, objAcc);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByCommonNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByCommonNameContaining(String commonName) throws DataAccessException {

		return findVLocusCvtermByCommonNameContaining(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByCommonNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByCommonNameContaining(String commonName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByCommonNameContaining", startResult, maxRows, commonName);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByFmin
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByFmin(Integer fmin) throws DataAccessException {

		return findVLocusCvtermByFmin(fmin, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByFmin
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByFmin(Integer fmin, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByFmin", startResult, maxRows, fmin);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByContigNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByContigNameContaining(String contigName) throws DataAccessException {

		return findVLocusCvtermByContigNameContaining(contigName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByContigNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByContigNameContaining(String contigName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByContigNameContaining", startResult, maxRows, contigName);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByCvName
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByCvName(String cvName) throws DataAccessException {

		return findVLocusCvtermByCvName(cvName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByCvName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByCvName(String cvName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByCvName", startResult, maxRows, cvName);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByCvtermId
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByCvtermId(Integer cvtermId) throws DataAccessException {

		return findVLocusCvtermByCvtermId(cvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByCvtermId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByCvtermId(Integer cvtermId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByCvtermId", startResult, maxRows, cvtermId);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByOrganismId
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByOrganismId(Integer organismId) throws DataAccessException {

		return findVLocusCvtermByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByOrganismId(Integer organismId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermBySubjAcc
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermBySubjAcc(String subjAcc) throws DataAccessException {

		return findVLocusCvtermBySubjAcc(subjAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermBySubjAcc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermBySubjAcc(String subjAcc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermBySubjAcc", startResult, maxRows, subjAcc);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByCvId
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByCvId(Integer cvId) throws DataAccessException {

		return findVLocusCvtermByCvId(cvId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByCvId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByCvId(Integer cvId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByCvId", startResult, maxRows, cvId);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermByFmax
	 *
	 */
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByFmax(Integer fmax) throws DataAccessException {

		return findVLocusCvtermByFmax(fmax, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermByFmax
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvterm> findVLocusCvtermByFmax(Integer fmax, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermByFmax", startResult, maxRows, fmax);
		return new LinkedHashSet<VLocusCvterm>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VLocusCvterm entity) {
		return true;
	}
	
	@Override
	public List getLocusByDescription(String goterm, Integer organismId, Integer cvId) {
		// TODO Auto-generated method stub
		Query query = createNamedQuery("findVLocusCvtermByCvtermOrganismIdCvid", -1,-1, goterm, organismId, cvId);
		return query.getResultList();
	}
}
