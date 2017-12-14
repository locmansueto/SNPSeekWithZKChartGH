package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.chado.postgres.domain.VLocusCvtermCvtermpath;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VLocusCvtermCvtermpath entities.
 * 
 */
@Repository("VLocusCvtermCvtermpathDAO")
@Primary
@Transactional
public class VLocusCvtermCvtermpathDAOImpl extends AbstractJpaDao<VLocusCvtermCvtermpath>
		implements VLocusCvtermCvtermpathDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VLocusCvtermCvtermpath.class }));

	/**
	 * EntityManager injected by Spring for persistence unit VM_IRIC
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VLocusCvtermCvtermpathDAOImpl
	 *
	 */
	public VLocusCvtermCvtermpathDAOImpl() {
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
	 * JPQL Query - findVLocusCvtermCvtermpathByFmin
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByFmin(Integer fmin) throws DataAccessException {

		return findVLocusCvtermCvtermpathByFmin(fmin, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByFmin
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByFmin(Integer fmin, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByFmin", startResult, maxRows, fmin);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByNotesContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByNotesContaining(String notes) throws DataAccessException {

		return findVLocusCvtermCvtermpathByNotesContaining(notes, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByNotesContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByNotesContaining(String notes, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByNotesContaining", startResult, maxRows, notes);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjCvterm
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjCvterm(String subjCvterm) throws DataAccessException {

		return findVLocusCvtermCvtermpathBySubjCvterm(subjCvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjCvterm
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjCvterm(String subjCvterm, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathBySubjCvterm", startResult, maxRows, subjCvterm);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjCvtermContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjCvtermContaining(String subjCvterm) throws DataAccessException {

		return findVLocusCvtermCvtermpathBySubjCvtermContaining(subjCvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjCvtermContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjCvtermContaining(String subjCvterm, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathBySubjCvtermContaining", startResult, maxRows, subjCvterm);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByStrand
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByStrand(Integer strand) throws DataAccessException {

		return findVLocusCvtermCvtermpathByStrand(strand, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByStrand
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByStrand(Integer strand, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByStrand", startResult, maxRows, strand);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCommonNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCommonNameContaining(String commonName) throws DataAccessException {

		return findVLocusCvtermCvtermpathByCommonNameContaining(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCommonNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCommonNameContaining(String commonName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByCommonNameContaining", startResult, maxRows, commonName);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByContigName
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByContigName(String contigName) throws DataAccessException {

		return findVLocusCvtermCvtermpathByContigName(contigName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByContigName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByContigName(String contigName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByContigName", startResult, maxRows, contigName);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByPrimaryKey
	 *
	 */
	@Transactional
	public VLocusCvtermCvtermpath findVLocusCvtermCvtermpathByPrimaryKey(Integer featureId) throws DataAccessException {

		return findVLocusCvtermCvtermpathByPrimaryKey(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByPrimaryKey
	 *
	 */

	@Transactional
	public VLocusCvtermCvtermpath findVLocusCvtermCvtermpathByPrimaryKey(Integer featureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVLocusCvtermCvtermpathByPrimaryKey", startResult, maxRows, featureId);
			return (org.irri.iric.portal.chado.postgres.domain.VLocusCvtermCvtermpath) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllVLocusCvtermCvtermpaths
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findAllVLocusCvtermCvtermpaths() throws DataAccessException {

		return findAllVLocusCvtermCvtermpaths(-1, -1);
	}

	/**
	 * JPQL Query - findAllVLocusCvtermCvtermpaths
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findAllVLocusCvtermCvtermpaths(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVLocusCvtermCvtermpaths", startResult, maxRows);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCommonName
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCommonName(String commonName) throws DataAccessException {

		return findVLocusCvtermCvtermpathByCommonName(commonName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCommonName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCommonName(String commonName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByCommonName", startResult, maxRows, commonName);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjCvterm
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjCvterm(String objCvterm) throws DataAccessException {

		return findVLocusCvtermCvtermpathByObjCvterm(objCvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjCvterm
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjCvterm(String objCvterm, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByObjCvterm", startResult, maxRows, objCvterm);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjAcc
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjAcc(String subjAcc) throws DataAccessException {

		return findVLocusCvtermCvtermpathBySubjAcc(subjAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjAcc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjAcc(String subjAcc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathBySubjAcc", startResult, maxRows, subjAcc);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByFmax
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByFmax(Integer fmax) throws DataAccessException {

		return findVLocusCvtermCvtermpathByFmax(fmax, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByFmax
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByFmax(Integer fmax, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByFmax", startResult, maxRows, fmax);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjAccContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjAccContaining(String subjAcc) throws DataAccessException {

		return findVLocusCvtermCvtermpathBySubjAccContaining(subjAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjAccContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjAccContaining(String subjAcc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathBySubjAccContaining", startResult, maxRows, subjAcc);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByFeatureId
	 *
	 */
	@Transactional
	public VLocusCvtermCvtermpath findVLocusCvtermCvtermpathByFeatureId(Integer featureId) throws DataAccessException {

		return findVLocusCvtermCvtermpathByFeatureId(featureId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByFeatureId
	 *
	 */

	@Transactional
	public VLocusCvtermCvtermpath findVLocusCvtermCvtermpathByFeatureId(Integer featureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVLocusCvtermCvtermpathByFeatureId", startResult, maxRows, featureId);
			return (org.irri.iric.portal.chado.postgres.domain.VLocusCvtermCvtermpath) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByNotes
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByNotes(String notes) throws DataAccessException {

		return findVLocusCvtermCvtermpathByNotes(notes, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByNotes
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByNotes(String notes, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByNotes", startResult, maxRows, notes);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByName
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByName(String name) throws DataAccessException {

		return findVLocusCvtermCvtermpathByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByName", startResult, maxRows, name);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByPathdistance
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByPathdistance(Integer pathdistance) throws DataAccessException {

		return findVLocusCvtermCvtermpathByPathdistance(pathdistance, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByPathdistance
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByPathdistance(Integer pathdistance, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByPathdistance", startResult, maxRows, pathdistance);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByNameContaining(String name) throws DataAccessException {

		return findVLocusCvtermCvtermpathByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjCvtermContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjCvtermContaining(String objCvterm) throws DataAccessException {

		return findVLocusCvtermCvtermpathByObjCvtermContaining(objCvterm, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjCvtermContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjCvtermContaining(String objCvterm, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByObjCvtermContaining", startResult, maxRows, objCvterm);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByContigNameContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByContigNameContaining(String contigName) throws DataAccessException {

		return findVLocusCvtermCvtermpathByContigNameContaining(contigName, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByContigNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByContigNameContaining(String contigName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByContigNameContaining", startResult, maxRows, contigName);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjAccContaining
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjAccContaining(String objAcc) throws DataAccessException {

		return findVLocusCvtermCvtermpathByObjAccContaining(objAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjAccContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjAccContaining(String objAcc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByObjAccContaining", startResult, maxRows, objAcc);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCvtermId
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCvtermId(Integer cvtermId) throws DataAccessException {

		return findVLocusCvtermCvtermpathByCvtermId(cvtermId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCvtermId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCvtermId(Integer cvtermId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByCvtermId", startResult, maxRows, cvtermId);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjAcc
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjAcc(String objAcc) throws DataAccessException {

		return findVLocusCvtermCvtermpathByObjAcc(objAcc, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjAcc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjAcc(String objAcc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByObjAcc", startResult, maxRows, objAcc);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByContigId
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByContigId(Integer contigId) throws DataAccessException {

		return findVLocusCvtermCvtermpathByContigId(contigId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByContigId(Integer contigId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByContigId", startResult, maxRows, contigId);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByOrganismId
	 *
	 */
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByOrganismId(Integer organismId) throws DataAccessException {

		return findVLocusCvtermCvtermpathByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByOrganismId(Integer organismId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVLocusCvtermCvtermpathByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<VLocusCvtermCvtermpath>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VLocusCvtermCvtermpath entity) {
		return true;
	}
}
