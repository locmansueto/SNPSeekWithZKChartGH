package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.chado.postgres.domain.MvConvertposAny2allrefs;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage MvConvertposAny2allrefs entities.
 * 
 */
//@Repository("MvConvertposAny2allrefsDAO")
@Repository("MultipleReferenceConverterDAO")

@Transactional
public class MvConvertposAny2allrefsDAOImpl extends AbstractJpaDao<MvConvertposAny2allrefs>
		implements MvConvertposAny2allrefsDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { MvConvertposAny2allrefs.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_PG_VM
	 *
	 */
	@PersistenceContext(unitName = "IRIC_PG_VM")
	private EntityManager entityManager;

	/**
	 * Instantiates a new MvConvertposAny2allrefsDAOImpl
	 *
	 */
	public MvConvertposAny2allrefsDAOImpl() {
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
	 * JPQL Query - findMvConvertposAny2allrefsByRice9311ContigNameContaining
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByRice9311ContigNameContaining(String rice9311ContigName) throws DataAccessException {

		return findMvConvertposAny2allrefsByRice9311ContigNameContaining(rice9311ContigName, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByRice9311ContigNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByRice9311ContigNameContaining(String rice9311ContigName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByRice9311ContigNameContaining", startResult, maxRows, rice9311ContigName);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByKasalathContigName
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByKasalathContigName(String kasalathContigName) throws DataAccessException {

		return findMvConvertposAny2allrefsByKasalathContigName(kasalathContigName, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByKasalathContigName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByKasalathContigName(String kasalathContigName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByKasalathContigName", startResult, maxRows, kasalathContigName);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByFromRefcallContaining
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByFromRefcallContaining(String fromRefcall) throws DataAccessException {

		return findMvConvertposAny2allrefsByFromRefcallContaining(fromRefcall, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByFromRefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByFromRefcallContaining(String fromRefcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByFromRefcallContaining", startResult, maxRows, fromRefcall);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByNbPosition
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByNbPosition(Integer nbPosition) throws DataAccessException {

		return findMvConvertposAny2allrefsByNbPosition(nbPosition, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByNbPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByNbPosition(Integer nbPosition, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByNbPosition", startResult, maxRows, nbPosition);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByRice9311Refcall
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByRice9311Refcall(String rice9311Refcall) throws DataAccessException {

		return findMvConvertposAny2allrefsByRice9311Refcall(rice9311Refcall, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByRice9311Refcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByRice9311Refcall(String rice9311Refcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByRice9311Refcall", startResult, maxRows, rice9311Refcall);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByNbContigId
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByNbContigId(Integer nbContigId) throws DataAccessException {

		return findMvConvertposAny2allrefsByNbContigId(nbContigId, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByNbContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByNbContigId(Integer nbContigId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByNbContigId", startResult, maxRows, nbContigId);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByKasalathContigNameContaining
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByKasalathContigNameContaining(String kasalathContigName) throws DataAccessException {

		return findMvConvertposAny2allrefsByKasalathContigNameContaining(kasalathContigName, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByKasalathContigNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByKasalathContigNameContaining(String kasalathContigName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByKasalathContigNameContaining", startResult, maxRows, kasalathContigName);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByDj123RefcallContaining
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByDj123RefcallContaining(String dj123Refcall) throws DataAccessException {

		return findMvConvertposAny2allrefsByDj123RefcallContaining(dj123Refcall, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByDj123RefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByDj123RefcallContaining(String dj123Refcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByDj123RefcallContaining", startResult, maxRows, dj123Refcall);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByKasalathAlignCount
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByKasalathAlignCount(Integer kasalathAlignCount) throws DataAccessException {

		return findMvConvertposAny2allrefsByKasalathAlignCount(kasalathAlignCount, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByKasalathAlignCount
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByKasalathAlignCount(Integer kasalathAlignCount, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByKasalathAlignCount", startResult, maxRows, kasalathAlignCount);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByNbRefcallContaining
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByNbRefcallContaining(String nbRefcall) throws DataAccessException {

		return findMvConvertposAny2allrefsByNbRefcallContaining(nbRefcall, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByNbRefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByNbRefcallContaining(String nbRefcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByNbRefcallContaining", startResult, maxRows, nbRefcall);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByRice9311ContigName
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByRice9311ContigName(String rice9311ContigName) throws DataAccessException {

		return findMvConvertposAny2allrefsByRice9311ContigName(rice9311ContigName, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByRice9311ContigName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByRice9311ContigName(String rice9311ContigName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByRice9311ContigName", startResult, maxRows, rice9311ContigName);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByKasalathPosition
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByKasalathPosition(Integer kasalathPosition) throws DataAccessException {

		return findMvConvertposAny2allrefsByKasalathPosition(kasalathPosition, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByKasalathPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByKasalathPosition(Integer kasalathPosition, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByKasalathPosition", startResult, maxRows, kasalathPosition);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByFromContigId
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByFromContigId(Integer fromContigId) throws DataAccessException {

		return findMvConvertposAny2allrefsByFromContigId(fromContigId, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByFromContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByFromContigId(Integer fromContigId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByFromContigId", startResult, maxRows, fromContigId);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByIr64Refcall
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByIr64Refcall(String ir64Refcall) throws DataAccessException {

		return findMvConvertposAny2allrefsByIr64Refcall(ir64Refcall, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByIr64Refcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByIr64Refcall(String ir64Refcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByIr64Refcall", startResult, maxRows, ir64Refcall);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByDj123ContigId
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByDj123ContigId(Integer dj123ContigId) throws DataAccessException {

		return findMvConvertposAny2allrefsByDj123ContigId(dj123ContigId, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByDj123ContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByDj123ContigId(Integer dj123ContigId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByDj123ContigId", startResult, maxRows, dj123ContigId);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByNbContigName
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByNbContigName(String nbContigName) throws DataAccessException {

		return findMvConvertposAny2allrefsByNbContigName(nbContigName, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByNbContigName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByNbContigName(String nbContigName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByNbContigName", startResult, maxRows, nbContigName);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByIr64Position
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByIr64Position(Integer ir64Position) throws DataAccessException {

		return findMvConvertposAny2allrefsByIr64Position(ir64Position, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByIr64Position
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByIr64Position(Integer ir64Position, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByIr64Position", startResult, maxRows, ir64Position);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByTypeId
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByTypeId(Integer typeId) throws DataAccessException {

		return findMvConvertposAny2allrefsByTypeId(typeId, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByTypeId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByTypeId(Integer typeId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByTypeId", startResult, maxRows, typeId);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByRice9311AlignCount
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByRice9311AlignCount(Integer rice9311AlignCount) throws DataAccessException {

		return findMvConvertposAny2allrefsByRice9311AlignCount(rice9311AlignCount, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByRice9311AlignCount
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByRice9311AlignCount(Integer rice9311AlignCount, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByRice9311AlignCount", startResult, maxRows, rice9311AlignCount);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByIr64ContigId
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByIr64ContigId(Integer ir64ContigId) throws DataAccessException {

		return findMvConvertposAny2allrefsByIr64ContigId(ir64ContigId, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByIr64ContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByIr64ContigId(Integer ir64ContigId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByIr64ContigId", startResult, maxRows, ir64ContigId);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByFromRefcall
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByFromRefcall(String fromRefcall) throws DataAccessException {

		return findMvConvertposAny2allrefsByFromRefcall(fromRefcall, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByFromRefcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByFromRefcall(String fromRefcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByFromRefcall", startResult, maxRows, fromRefcall);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByPrimaryKey
	 *
	 */
	@Transactional
	public MvConvertposAny2allrefs findMvConvertposAny2allrefsByPrimaryKey(Integer snpFeatureId, Integer fromOrganismId) throws DataAccessException {

		return findMvConvertposAny2allrefsByPrimaryKey(snpFeatureId, fromOrganismId, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByPrimaryKey
	 *
	 */

	@Transactional
	public MvConvertposAny2allrefs findMvConvertposAny2allrefsByPrimaryKey(Integer snpFeatureId, Integer fromOrganismId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findMvConvertposAny2allrefsByPrimaryKey", startResult, maxRows, snpFeatureId, fromOrganismId);
			return (org.irri.iric.portal.chado.postgres.domain.MvConvertposAny2allrefs) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByDj123Refcall
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByDj123Refcall(String dj123Refcall) throws DataAccessException {

		return findMvConvertposAny2allrefsByDj123Refcall(dj123Refcall, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByDj123Refcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByDj123Refcall(String dj123Refcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByDj123Refcall", startResult, maxRows, dj123Refcall);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByIr64ContigNameContaining
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByIr64ContigNameContaining(String ir64ContigName) throws DataAccessException {

		return findMvConvertposAny2allrefsByIr64ContigNameContaining(ir64ContigName, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByIr64ContigNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByIr64ContigNameContaining(String ir64ContigName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByIr64ContigNameContaining", startResult, maxRows, ir64ContigName);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByKasalathContigId
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByKasalathContigId(Integer kasalathContigId) throws DataAccessException {

		return findMvConvertposAny2allrefsByKasalathContigId(kasalathContigId, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByKasalathContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByKasalathContigId(Integer kasalathContigId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByKasalathContigId", startResult, maxRows, kasalathContigId);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByIr64ContigName
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByIr64ContigName(String ir64ContigName) throws DataAccessException {

		return findMvConvertposAny2allrefsByIr64ContigName(ir64ContigName, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByIr64ContigName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByIr64ContigName(String ir64ContigName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByIr64ContigName", startResult, maxRows, ir64ContigName);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByDj123ContigNameContaining
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByDj123ContigNameContaining(String dj123ContigName) throws DataAccessException {

		return findMvConvertposAny2allrefsByDj123ContigNameContaining(dj123ContigName, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByDj123ContigNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByDj123ContigNameContaining(String dj123ContigName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByDj123ContigNameContaining", startResult, maxRows, dj123ContigName);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsBySnpFeatureId
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsBySnpFeatureId(Integer snpFeatureId) throws DataAccessException {

		return findMvConvertposAny2allrefsBySnpFeatureId(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsBySnpFeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsBySnpFeatureId(Integer snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsBySnpFeatureId", startResult, maxRows, snpFeatureId);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByNbContigNameContaining
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByNbContigNameContaining(String nbContigName) throws DataAccessException {

		return findMvConvertposAny2allrefsByNbContigNameContaining(nbContigName, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByNbContigNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByNbContigNameContaining(String nbContigName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByNbContigNameContaining", startResult, maxRows, nbContigName);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByNbRefcall
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByNbRefcall(String nbRefcall) throws DataAccessException {

		return findMvConvertposAny2allrefsByNbRefcall(nbRefcall, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByNbRefcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByNbRefcall(String nbRefcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByNbRefcall", startResult, maxRows, nbRefcall);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllMvConvertposAny2allrefss
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findAllMvConvertposAny2allrefss() throws DataAccessException {

		return findAllMvConvertposAny2allrefss(-1, -1);
	}

	/**
	 * JPQL Query - findAllMvConvertposAny2allrefss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findAllMvConvertposAny2allrefss(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllMvConvertposAny2allrefss", startResult, maxRows);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByDj123ContigName
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByDj123ContigName(String dj123ContigName) throws DataAccessException {

		return findMvConvertposAny2allrefsByDj123ContigName(dj123ContigName, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByDj123ContigName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByDj123ContigName(String dj123ContigName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByDj123ContigName", startResult, maxRows, dj123ContigName);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByDj123AlignCount
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByDj123AlignCount(Integer dj123AlignCount) throws DataAccessException {

		return findMvConvertposAny2allrefsByDj123AlignCount(dj123AlignCount, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByDj123AlignCount
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByDj123AlignCount(Integer dj123AlignCount, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByDj123AlignCount", startResult, maxRows, dj123AlignCount);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByRice9311Position
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByRice9311Position(Integer rice9311Position) throws DataAccessException {

		return findMvConvertposAny2allrefsByRice9311Position(rice9311Position, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByRice9311Position
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByRice9311Position(Integer rice9311Position, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByRice9311Position", startResult, maxRows, rice9311Position);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByFromPosition
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByFromPosition(Integer fromPosition) throws DataAccessException {

		return findMvConvertposAny2allrefsByFromPosition(fromPosition, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByFromPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByFromPosition(Integer fromPosition, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByFromPosition", startResult, maxRows, fromPosition);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByIr64RefcallContaining
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByIr64RefcallContaining(String ir64Refcall) throws DataAccessException {

		return findMvConvertposAny2allrefsByIr64RefcallContaining(ir64Refcall, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByIr64RefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByIr64RefcallContaining(String ir64Refcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByIr64RefcallContaining", startResult, maxRows, ir64Refcall);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByFromOrganismId
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByFromOrganismId(Integer fromOrganismId) throws DataAccessException {

		return findMvConvertposAny2allrefsByFromOrganismId(fromOrganismId, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByFromOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByFromOrganismId(Integer fromOrganismId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByFromOrganismId", startResult, maxRows, fromOrganismId);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByNbAlignCount
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByNbAlignCount(Integer nbAlignCount) throws DataAccessException {

		return findMvConvertposAny2allrefsByNbAlignCount(nbAlignCount, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByNbAlignCount
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByNbAlignCount(Integer nbAlignCount, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByNbAlignCount", startResult, maxRows, nbAlignCount);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByKasalathRefcall
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByKasalathRefcall(String kasalathRefcall) throws DataAccessException {

		return findMvConvertposAny2allrefsByKasalathRefcall(kasalathRefcall, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByKasalathRefcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByKasalathRefcall(String kasalathRefcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByKasalathRefcall", startResult, maxRows, kasalathRefcall);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByAlleleIndex
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByAlleleIndex(Integer alleleIndex) throws DataAccessException {

		return findMvConvertposAny2allrefsByAlleleIndex(alleleIndex, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByAlleleIndex
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByAlleleIndex(Integer alleleIndex, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByAlleleIndex", startResult, maxRows, alleleIndex);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByKasalathRefcallContaining
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByKasalathRefcallContaining(String kasalathRefcall) throws DataAccessException {

		return findMvConvertposAny2allrefsByKasalathRefcallContaining(kasalathRefcall, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByKasalathRefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByKasalathRefcallContaining(String kasalathRefcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByKasalathRefcallContaining", startResult, maxRows, kasalathRefcall);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByDj123Position
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByDj123Position(Integer dj123Position) throws DataAccessException {

		return findMvConvertposAny2allrefsByDj123Position(dj123Position, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByDj123Position
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByDj123Position(Integer dj123Position, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByDj123Position", startResult, maxRows, dj123Position);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByIr64AlignCount
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByIr64AlignCount(Integer ir64AlignCount) throws DataAccessException {

		return findMvConvertposAny2allrefsByIr64AlignCount(ir64AlignCount, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByIr64AlignCount
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByIr64AlignCount(Integer ir64AlignCount, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByIr64AlignCount", startResult, maxRows, ir64AlignCount);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByRice9311RefcallContaining
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByRice9311RefcallContaining(String rice9311Refcall) throws DataAccessException {

		return findMvConvertposAny2allrefsByRice9311RefcallContaining(rice9311Refcall, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByRice9311RefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByRice9311RefcallContaining(String rice9311Refcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByRice9311RefcallContaining", startResult, maxRows, rice9311Refcall);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByRice9311ContigId
	 *
	 */
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByRice9311ContigId(Integer rice9311ContigId) throws DataAccessException {

		return findMvConvertposAny2allrefsByRice9311ContigId(rice9311ContigId, -1, -1);
	}

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByRice9311ContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByRice9311ContigId(Integer rice9311ContigId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMvConvertposAny2allrefsByRice9311ContigId", startResult, maxRows, rice9311ContigId);
		return new LinkedHashSet<MvConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(MvConvertposAny2allrefs entity) {
		return true;
	}
}
