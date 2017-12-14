package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.chado.postgres.domain.VLocusCvtermpath;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VLocusCvtermpath entities.
 * 
 */
@Repository("LocusCvtermpathDAO")
@Primary()
@Transactional
public class VLocusCvtermpathDAOImpl extends AbstractJpaDao<VLocusCvtermpath>
		implements VLocusCvtermpathDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VLocusCvtermpath.class }));

	/**
	 * EntityManager injected by Spring for persistence unit VM_IRIC
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VLocusCvtermpathDAOImpl
	 *
	 */
	public VLocusCvtermpathDAOImpl() {
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
	 * JPQL Query - findVLocusCvtermpathByOrganismId
	 *
	 */
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByOrganismId(Integer organismId) throws DataAccessException {

		return findVLocusCvtermpathByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByOrganismId(Integer organismId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermpathByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<VLocusCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByName
	 *
	 */
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByName(String name) throws DataAccessException {

		return findVLocusCvtermpathByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermpathByName", startResult, maxRows, name);
		return new LinkedHashSet<VLocusCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermpathBySubjCvterm
	 *
	 */
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathBySubjCvterm(String subjCvterm) throws DataAccessException {

		return findVLocusCvtermpathBySubjCvterm(subjCvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermpathBySubjCvterm
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathBySubjCvterm(String subjCvterm, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermpathBySubjCvterm", startResult, maxRows, subjCvterm);
		return new LinkedHashSet<VLocusCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByContigNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByContigNameContaining(String contigName) throws DataAccessException {

		return findVLocusCvtermpathByContigNameContaining(contigName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByContigNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByContigNameContaining(String contigName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermpathByContigNameContaining", startResult, maxRows, contigName);
		return new LinkedHashSet<VLocusCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByObjAccContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByObjAccContaining(String objAcc) throws DataAccessException {

		return findVLocusCvtermpathByObjAccContaining(objAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByObjAccContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByObjAccContaining(String objAcc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermpathByObjAccContaining", startResult, maxRows, objAcc);
		return new LinkedHashSet<VLocusCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByNotesContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByNotesContaining(String notes) throws DataAccessException {

		return findVLocusCvtermpathByNotesContaining(notes, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByNotesContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByNotesContaining(String notes, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermpathByNotesContaining", startResult, maxRows, notes);
		return new LinkedHashSet<VLocusCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByStrand
	 *
	 */
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByStrand(Integer strand) throws DataAccessException {

		return findVLocusCvtermpathByStrand(strand, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByStrand
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByStrand(Integer strand, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermpathByStrand", startResult, maxRows, strand);
		return new LinkedHashSet<VLocusCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByCvNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByCvNameContaining(String cvName) throws DataAccessException {

		return findVLocusCvtermpathByCvNameContaining(cvName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByCvNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByCvNameContaining(String cvName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermpathByCvNameContaining", startResult, maxRows, cvName);
		return new LinkedHashSet<VLocusCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByFmin
	 *
	 */
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByFmin(Integer fmin) throws DataAccessException {

		return findVLocusCvtermpathByFmin(fmin, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByFmin
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByFmin(Integer fmin, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermpathByFmin", startResult, maxRows, fmin);
		return new LinkedHashSet<VLocusCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByCvId
	 *
	 */
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByCvId(Integer cvId) throws DataAccessException {

		return findVLocusCvtermpathByCvId(cvId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByCvId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByCvId(Integer cvId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermpathByCvId", startResult, maxRows, cvId);
		return new LinkedHashSet<VLocusCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByFeatureId
	 *
	 */
	@Transactional
	public VLocusCvtermpath findVLocusCvtermpathByFeatureId(Integer featureId) throws DataAccessException {

		return findVLocusCvtermpathByFeatureId(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByFeatureId
	 *
	 */

	@Transactional
	public VLocusCvtermpath findVLocusCvtermpathByFeatureId(Integer featureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVLocusCvtermpathByFeatureId", startResult, maxRows, featureId);
			return (org.irri.iric.portal.chado.postgres.domain.VLocusCvtermpath) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByNameContaining(String name) throws DataAccessException {

		return findVLocusCvtermpathByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermpathByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VLocusCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVLocusCvtermpaths
	 *
	 */
	@Transactional
	public Set<VLocusCvtermpath> findAllVLocusCvtermpaths() throws DataAccessException {

		return findAllVLocusCvtermpaths(-1, -1);
	}

	/**
	 * JPQL Query - findAllVLocusCvtermpaths
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermpath> findAllVLocusCvtermpaths(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVLocusCvtermpaths", startResult, maxRows);
		return new LinkedHashSet<VLocusCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByFmax
	 *
	 */
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByFmax(Integer fmax) throws DataAccessException {

		return findVLocusCvtermpathByFmax(fmax, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByFmax
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByFmax(Integer fmax, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermpathByFmax", startResult, maxRows, fmax);
		return new LinkedHashSet<VLocusCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermpathBySubjAcc
	 *
	 */
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathBySubjAcc(String subjAcc) throws DataAccessException {

		return findVLocusCvtermpathBySubjAcc(subjAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermpathBySubjAcc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathBySubjAcc(String subjAcc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermpathBySubjAcc", startResult, maxRows, subjAcc);
		return new LinkedHashSet<VLocusCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByNotes
	 *
	 */
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByNotes(String notes) throws DataAccessException {

		return findVLocusCvtermpathByNotes(notes, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByNotes
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByNotes(String notes, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermpathByNotes", startResult, maxRows, notes);
		return new LinkedHashSet<VLocusCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByContigName
	 *
	 */
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByContigName(String contigName) throws DataAccessException {

		return findVLocusCvtermpathByContigName(contigName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByContigName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByContigName(String contigName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermpathByContigName", startResult, maxRows, contigName);
		return new LinkedHashSet<VLocusCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByObjAcc
	 *
	 */
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByObjAcc(String objAcc) throws DataAccessException {

		return findVLocusCvtermpathByObjAcc(objAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByObjAcc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByObjAcc(String objAcc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermpathByObjAcc", startResult, maxRows, objAcc);
		return new LinkedHashSet<VLocusCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByCommonNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByCommonNameContaining(String commonName) throws DataAccessException {

		return findVLocusCvtermpathByCommonNameContaining(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByCommonNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByCommonNameContaining(String commonName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermpathByCommonNameContaining", startResult, maxRows, commonName);
		return new LinkedHashSet<VLocusCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermpathBySubjCvtermContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathBySubjCvtermContaining(String subjCvterm) throws DataAccessException {

		return findVLocusCvtermpathBySubjCvtermContaining(subjCvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermpathBySubjCvtermContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathBySubjCvtermContaining(String subjCvterm, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermpathBySubjCvtermContaining", startResult, maxRows, subjCvterm);
		return new LinkedHashSet<VLocusCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByPathdistance
	 *
	 */
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByPathdistance(Integer pathdistance) throws DataAccessException {

		return findVLocusCvtermpathByPathdistance(pathdistance, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByPathdistance
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByPathdistance(Integer pathdistance, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermpathByPathdistance", startResult, maxRows, pathdistance);
		return new LinkedHashSet<VLocusCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermpathBySubjAccContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathBySubjAccContaining(String subjAcc) throws DataAccessException {

		return findVLocusCvtermpathBySubjAccContaining(subjAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermpathBySubjAccContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathBySubjAccContaining(String subjAcc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermpathBySubjAccContaining", startResult, maxRows, subjAcc);
		return new LinkedHashSet<VLocusCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByObjCvtermContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByObjCvtermContaining(String objCvterm) throws DataAccessException {

		return findVLocusCvtermpathByObjCvtermContaining(objCvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByObjCvtermContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByObjCvtermContaining(String objCvterm, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermpathByObjCvtermContaining", startResult, maxRows, objCvterm);
		return new LinkedHashSet<VLocusCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByPrimaryKey
	 *
	 */
	@Transactional
	public VLocusCvtermpath findVLocusCvtermpathByPrimaryKey(Integer featureId) throws DataAccessException {

		return findVLocusCvtermpathByPrimaryKey(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByPrimaryKey
	 *
	 */

	@Transactional
	public VLocusCvtermpath findVLocusCvtermpathByPrimaryKey(Integer featureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVLocusCvtermpathByPrimaryKey", startResult, maxRows, featureId);
			return (org.irri.iric.portal.chado.postgres.domain.VLocusCvtermpath) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByCvtermId
	 *
	 */
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByCvtermId(Integer cvtermId) throws DataAccessException {

		return findVLocusCvtermpathByCvtermId(cvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByCvtermId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByCvtermId(Integer cvtermId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermpathByCvtermId", startResult, maxRows, cvtermId);
		return new LinkedHashSet<VLocusCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByCvName
	 *
	 */
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByCvName(String cvName) throws DataAccessException {

		return findVLocusCvtermpathByCvName(cvName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByCvName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByCvName(String cvName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermpathByCvName", startResult, maxRows, cvName);
		return new LinkedHashSet<VLocusCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByContigId
	 *
	 */
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByContigId(Integer contigId) throws DataAccessException {

		return findVLocusCvtermpathByContigId(contigId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByContigId(Integer contigId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermpathByContigId", startResult, maxRows, contigId);
		return new LinkedHashSet<VLocusCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByCommonName
	 *
	 */
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByCommonName(String commonName) throws DataAccessException {

		return findVLocusCvtermpathByCommonName(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByCommonName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByCommonName(String commonName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermpathByCommonName", startResult, maxRows, commonName);
		return new LinkedHashSet<VLocusCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByObjCvterm
	 *
	 */
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByObjCvterm(String objCvterm) throws DataAccessException {

		return findVLocusCvtermpathByObjCvterm(objCvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermpathByObjCvterm
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermpath> findVLocusCvtermpathByObjCvterm(String objCvterm, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermpathByObjCvterm", startResult, maxRows, objCvterm);
		return new LinkedHashSet<VLocusCvtermpath>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VLocusCvtermpath entity) {
		return true;
	}
}
