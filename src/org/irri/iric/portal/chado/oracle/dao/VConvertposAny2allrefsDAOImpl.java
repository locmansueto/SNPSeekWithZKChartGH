package org.irri.iric.portal.chado.oracle.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.oracle.domain.VConvertposAny2allrefs;
import org.irri.iric.portal.chado.oracle.domain.VConvertposAny2allrefsMV;
import org.irri.iric.portal.chado.oracle.domain.VConvertposNb2allrefs;
import org.irri.iric.portal.chado.oracle.domain.VSnpRefposindex;
import org.irri.iric.portal.dao.OrganismDAO;
import org.irri.iric.portal.dao.ScaffoldDAO;
import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.MultiReferenceConversion;
import org.irri.iric.portal.domain.MultiReferenceLocus;
import org.irri.iric.portal.domain.MultiReferenceLocusImpl;
import org.irri.iric.portal.domain.MultiReferencePosition;
import org.irri.iric.portal.domain.MultiReferencePositionImpl;
import org.irri.iric.portal.domain.Organism;
import org.irri.iric.portal.domain.Scaffold;
import org.irri.iric.portal.domain.SnpsAllvarsMultirefsPos;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.genotype.VariantStringData;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VConvertposAny2allrefs entities.
 * 
 */
// @Repository("VConvertposAny2allrefsDAO")
@Repository("MultipleReferenceConverterDAO_VIEW")
@Transactional
public class VConvertposAny2allrefsDAOImpl extends AbstractJpaDao<VConvertposAny2allrefs>
		implements VConvertposAny2allrefsDAO {

	@Autowired
	OrganismDAO orgdao;
	@Autowired
	ScaffoldDAO scaffolddao;

	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a single
	 * entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { VConvertposAny2allrefs.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VConvertposAny2allrefsDAOImpl
	 *
	 */
	public VConvertposAny2allrefsDAOImpl() {
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
	 * JPQL Query - findVConvertposAny2allrefsByIr64Position
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByIr64Position(BigDecimal ir64Position)
			throws DataAccessException {

		return findVConvertposAny2allrefsByIr64Position(ir64Position, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByIr64Position
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByIr64Position(BigDecimal ir64Position,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByIr64Position", startResult, maxRows, ir64Position);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByFromOrganismId
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByFromOrganismId(java.math.BigDecimal fromOrganismId)
			throws DataAccessException {

		return findVConvertposAny2allrefsByFromOrganismId(fromOrganismId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByFromOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByFromOrganismId(java.math.BigDecimal fromOrganismId,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByFromOrganismId", startResult, maxRows,
				fromOrganismId);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByFromRefcallContaining
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByFromRefcallContaining(String fromRefcall)
			throws DataAccessException {

		return findVConvertposAny2allrefsByFromRefcallContaining(fromRefcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByFromRefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByFromRefcallContaining(String fromRefcall,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByFromRefcallContaining", startResult, maxRows,
				fromRefcall);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByNbRefcallContaining
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByNbRefcallContaining(String nbRefcall)
			throws DataAccessException {

		return findVConvertposAny2allrefsByNbRefcallContaining(nbRefcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByNbRefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByNbRefcallContaining(String nbRefcall,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByNbRefcallContaining", startResult, maxRows,
				nbRefcall);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByKasalathContigId
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByKasalathContigId(BigDecimal kasalathContigId)
			throws DataAccessException {

		return findVConvertposAny2allrefsByKasalathContigId(kasalathContigId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByKasalathContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByKasalathContigId(BigDecimal kasalathContigId,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByKasalathContigId", startResult, maxRows,
				kasalathContigId);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByDj123Position
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByDj123Position(BigDecimal dj123Position)
			throws DataAccessException {

		return findVConvertposAny2allrefsByDj123Position(dj123Position, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByDj123Position
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByDj123Position(BigDecimal dj123Position,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByDj123Position", startResult, maxRows,
				dj123Position);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByPrimaryKey
	 *
	 */
	@Transactional
	public VConvertposAny2allrefs findVConvertposAny2allrefsByPrimaryKey(BigDecimal snpFeatureId)
			throws DataAccessException {

		return findVConvertposAny2allrefsByPrimaryKey(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByPrimaryKey
	 *
	 */

	@Transactional
	public VConvertposAny2allrefs findVConvertposAny2allrefsByPrimaryKey(BigDecimal snpFeatureId, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVConvertposAny2allrefsByPrimaryKey", startResult, maxRows,
					snpFeatureId);
			return (org.irri.iric.portal.chado.oracle.domain.VConvertposAny2allrefs) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByDj123ContigId
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByDj123ContigId(BigDecimal dj123ContigId)
			throws DataAccessException {

		return findVConvertposAny2allrefsByDj123ContigId(dj123ContigId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByDj123ContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByDj123ContigId(BigDecimal dj123ContigId,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByDj123ContigId", startResult, maxRows,
				dj123ContigId);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByNbContigId
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByNbContigId(BigDecimal nbContigId)
			throws DataAccessException {

		return findVConvertposAny2allrefsByNbContigId(nbContigId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByNbContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByNbContigId(BigDecimal nbContigId, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByNbContigId", startResult, maxRows, nbContigId);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByRice9311RefcallContaining
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByRice9311RefcallContaining(String rice9311Refcall)
			throws DataAccessException {

		return findVConvertposAny2allrefsByRice9311RefcallContaining(rice9311Refcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByRice9311RefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByRice9311RefcallContaining(String rice9311Refcall,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByRice9311RefcallContaining", startResult, maxRows,
				rice9311Refcall);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByIr64Refcall
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByIr64Refcall(String ir64Refcall)
			throws DataAccessException {

		return findVConvertposAny2allrefsByIr64Refcall(ir64Refcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByIr64Refcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByIr64Refcall(String ir64Refcall, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByIr64Refcall", startResult, maxRows, ir64Refcall);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByAlleleIndex
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByAlleleIndex(BigDecimal alleleIndex)
			throws DataAccessException {

		return findVConvertposAny2allrefsByAlleleIndex(alleleIndex, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByAlleleIndex
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByAlleleIndex(BigDecimal alleleIndex, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByAlleleIndex", startResult, maxRows, alleleIndex);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByKasalathRefcall
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByKasalathRefcall(String kasalathRefcall)
			throws DataAccessException {

		return findVConvertposAny2allrefsByKasalathRefcall(kasalathRefcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByKasalathRefcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByKasalathRefcall(String kasalathRefcall,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByKasalathRefcall", startResult, maxRows,
				kasalathRefcall);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByDj123Refcall
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByDj123Refcall(String dj123Refcall)
			throws DataAccessException {

		return findVConvertposAny2allrefsByDj123Refcall(dj123Refcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByDj123Refcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByDj123Refcall(String dj123Refcall, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByDj123Refcall", startResult, maxRows, dj123Refcall);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByIr64RefcallContaining
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByIr64RefcallContaining(String ir64Refcall)
			throws DataAccessException {

		return findVConvertposAny2allrefsByIr64RefcallContaining(ir64Refcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByIr64RefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByIr64RefcallContaining(String ir64Refcall,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByIr64RefcallContaining", startResult, maxRows,
				ir64Refcall);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByRice9311Refcall
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByRice9311Refcall(String rice9311Refcall)
			throws DataAccessException {

		return findVConvertposAny2allrefsByRice9311Refcall(rice9311Refcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByRice9311Refcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByRice9311Refcall(String rice9311Refcall,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByRice9311Refcall", startResult, maxRows,
				rice9311Refcall);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVConvertposAny2allrefss
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findAllVConvertposAny2allrefss() throws DataAccessException {

		return findAllVConvertposAny2allrefss(-1, -1);
	}

	/**
	 * JPQL Query - findAllVConvertposAny2allrefss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findAllVConvertposAny2allrefss(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllVConvertposAny2allrefss", startResult, maxRows);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByNbPosition
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByNbPosition(BigDecimal nbPosition)
			throws DataAccessException {

		return findVConvertposAny2allrefsByNbPosition(nbPosition, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByNbPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByNbPosition(BigDecimal nbPosition, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByNbPosition", startResult, maxRows, nbPosition);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByKasalathRefcallContaining
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByKasalathRefcallContaining(String kasalathRefcall)
			throws DataAccessException {

		return findVConvertposAny2allrefsByKasalathRefcallContaining(kasalathRefcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByKasalathRefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByKasalathRefcallContaining(String kasalathRefcall,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByKasalathRefcallContaining", startResult, maxRows,
				kasalathRefcall);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByRice9311Position
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByRice9311Position(BigDecimal rice9311Position)
			throws DataAccessException {

		return findVConvertposAny2allrefsByRice9311Position(rice9311Position, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByRice9311Position
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByRice9311Position(BigDecimal rice9311Position,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByRice9311Position", startResult, maxRows,
				rice9311Position);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByRice9311ContigId
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByRice9311ContigId(BigDecimal rice9311ContigId)
			throws DataAccessException {

		return findVConvertposAny2allrefsByRice9311ContigId(rice9311ContigId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByRice9311ContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByRice9311ContigId(BigDecimal rice9311ContigId,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByRice9311ContigId", startResult, maxRows,
				rice9311ContigId);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsBySnpFeatureId
	 *
	 */
	@Transactional
	public VConvertposAny2allrefs findVConvertposAny2allrefsBySnpFeatureId(BigDecimal snpFeatureId)
			throws DataAccessException {

		return findVConvertposAny2allrefsBySnpFeatureId(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsBySnpFeatureId
	 *
	 */

	@Transactional
	public VConvertposAny2allrefs findVConvertposAny2allrefsBySnpFeatureId(BigDecimal snpFeatureId, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVConvertposAny2allrefsBySnpFeatureId", startResult, maxRows,
					snpFeatureId);
			return (org.irri.iric.portal.chado.oracle.domain.VConvertposAny2allrefs) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByNbRefcall
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByNbRefcall(String nbRefcall)
			throws DataAccessException {

		return findVConvertposAny2allrefsByNbRefcall(nbRefcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByNbRefcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByNbRefcall(String nbRefcall, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByNbRefcall", startResult, maxRows, nbRefcall);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByOrganismId
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByOrganismId(BigDecimal organismId)
			throws DataAccessException {

		return findVConvertposAny2allrefsByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByOrganismId(BigDecimal organismId, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByFromContigId
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByFromContigId(BigDecimal fromContigId)
			throws DataAccessException {

		return findVConvertposAny2allrefsByFromContigId(fromContigId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByFromContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByFromContigId(BigDecimal fromContigId,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByFromContigId", startResult, maxRows, fromContigId);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByDj123RefcallContaining
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByDj123RefcallContaining(String dj123Refcall)
			throws DataAccessException {

		return findVConvertposAny2allrefsByDj123RefcallContaining(dj123Refcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByDj123RefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByDj123RefcallContaining(String dj123Refcall,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByDj123RefcallContaining", startResult, maxRows,
				dj123Refcall);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByFromPosition
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByFromPosition(BigDecimal fromPosition)
			throws DataAccessException {

		return findVConvertposAny2allrefsByFromPosition(fromPosition, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByFromPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByFromPosition(BigDecimal fromPosition,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByFromPosition", startResult, maxRows, fromPosition);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByKasalathPosition
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByKasalathPosition(BigDecimal kasalathPosition)
			throws DataAccessException {

		return findVConvertposAny2allrefsByKasalathPosition(kasalathPosition, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByKasalathPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByKasalathPosition(BigDecimal kasalathPosition,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByKasalathPosition", startResult, maxRows,
				kasalathPosition);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByIr64ContigId
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByIr64ContigId(BigDecimal ir64ContigId)
			throws DataAccessException {

		return findVConvertposAny2allrefsByIr64ContigId(ir64ContigId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByIr64ContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByIr64ContigId(BigDecimal ir64ContigId,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByIr64ContigId", startResult, maxRows, ir64ContigId);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByFromRefcall
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByFromRefcall(String fromRefcall)
			throws DataAccessException {

		return findVConvertposAny2allrefsByFromRefcall(fromRefcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByFromRefcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByFromRefcall(String fromRefcall, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByFromRefcall", startResult, maxRows, fromRefcall);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByTypeId
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByTypeId(BigDecimal typeId)
			throws DataAccessException {

		return findVConvertposAny2allrefsByTypeId(typeId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByTypeId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByTypeId(BigDecimal typeId, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByTypeId", startResult, maxRows, typeId);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity
	 * when calling Store
	 * 
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VConvertposAny2allrefs entity) {
		return true;
	}

	@SuppressWarnings("unchecked")
	// @Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByFromOrgIdContigIdPosBetween(
			java.math.BigDecimal fromOrganismId, BigDecimal contigId, BigDecimal start, BigDecimal end)
			throws DataAccessException {
		/*
		 * Query query =
		 * createNamedQuery("findVConvertposAny2allrefsByFromOrgIdContigIdPosBetween",
		 * -1, -1, fromOrganismId, contigId, start, end); return new
		 * LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
		 */
		String sql = "select * from " + AppContext.getDefaultSchema()
				+ ".V_CONVERTPOS_ANY2ALLREFS where from_organism_id=" + fromOrganismId + " and organism_id="
				+ fromOrganismId + " and from_contig_id=" + contigId + " and from_position between " + start + " and "
				+ end + " order by from_contig_id, from_position";
		return new LinkedHashSet(executeSQL(sql));

	}

	@SuppressWarnings("unchecked")
	// @Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByFromOrgIdContigIdPosBetween(String fromOrganism,
			String contig, BigDecimal start, BigDecimal end) throws DataAccessException {

		orgdao = (OrganismDAO) AppContext.checkBean(orgdao, "OrganismDAO");
		scaffolddao = (ScaffoldDAO) AppContext.checkBean(scaffolddao, "ScaffoldDAO");

		Organism fromorg = orgdao.getMapName2Organism().get(fromOrganism);

		/*
		 * Map<BigDecimal, String> mapOrgId2Name=new HashMap(); Iterator<Organism>
		 * itOrgs = orgdao.getMapName2Organism().values().iterator();
		 * while(itOrgs.hasNext()) { Organism org = itOrgs.next(); mapOrgId2Name.put(
		 * org.getOrganismId(), org.getName()); }
		 */
		Scaffold fromcontig = scaffolddao.getScaffold(contig, fromorg.getOrganismId());
		return findVConvertposAny2allrefsByFromOrgIdContigIdPosBetween(fromorg.getOrganismId(),
				fromcontig.getFeatureId(), start, end);
	}

	@Override
	public MultiReferenceConversion convertPosition(MultiReferenceConversion fromPos, String toReference,
			String toContig) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MultiReferenceLocus convertLocus(MultiReferenceLocus fromLocus, String toReference, String toContig)
			throws Exception {

		Set setConv = findVConvertposAny2allrefsByFromOrgIdContigIdPosBetween(fromLocus.getOrganism(),
				fromLocus.getContig(), BigDecimal.valueOf(fromLocus.getFmin()),
				BigDecimal.valueOf(fromLocus.getFmax()));
		List listConv = new ArrayList();
		listConv.addAll(setConv);

		if (listConv.size() < 2)
			throw new RuntimeException("No reference coordinate conversion at start (" + fromLocus.getFmin() + ") to "
					+ toReference + " found");

		// Collections.sort(listConv, new MultiReferenceToPositionSorter());

		VConvertposNb2allrefs coversionStart = (VConvertposNb2allrefs) listConv.get(0);
		VConvertposNb2allrefs coversionEnd = (VConvertposNb2allrefs) listConv.get(listConv.size() - 1);

		BigDecimal actualToContigStart = null;
		BigDecimal actualToContigEnd = null;
		BigDecimal toStart = null;
		BigDecimal toEnd = null;
		if (toReference.equals(Organism.REFERENCE_9311)) {
			if (toContig != null) {
				actualToContigStart = coversionStart.get_311ContigId();
				actualToContigEnd = coversionEnd.get_311ContigId();
			}
			toStart = coversionStart.get_311Position();
			toEnd = coversionEnd.get_311Position();
		} else if (toReference.equals(Organism.REFERENCE_IR64)) {
			if (toContig != null) {
				actualToContigStart = coversionStart.getIr64ContigId();
				actualToContigEnd = coversionEnd.getIr64ContigId();
			}
			toStart = coversionStart.getIr64Position();
			toEnd = coversionEnd.getIr64Position();
		} else if (toReference.equals(Organism.REFERENCE_DJ123)) {
			if (toContig != null) {
				actualToContigStart = coversionStart.getDj123ContigId();
				actualToContigEnd = coversionEnd.getDj123ContigId();
			}
			toStart = coversionStart.getDj123Position();
			toEnd = coversionEnd.getDj123Position();
		} else if (toReference.equals(Organism.REFERENCE_KASALATH)) {
			if (toContig != null) {
				actualToContigStart = coversionStart.getKasalathContigId();
				actualToContigEnd = coversionEnd.getKasalathContigId();
			}
			toStart = coversionStart.getKasalathPosition();
			toEnd = coversionEnd.getKasalathPosition();
		}

		/*
		 * if(!actualToContigStart.equals(actualToContigEnd )) { throw new
		 * RuntimeException("Aligned contig at start (" + coversionStart.getToContig() +
		 * ") is not the same as aligned contig at end (" + coversionEnd.getToContig() +
		 * " )" + " in " + coversionEnd.getToOrganism()); }
		 */

		scaffolddao = (ScaffoldDAO) AppContext.checkBean(scaffolddao, "ScaffoldDAO");
		String strToContig1 = scaffolddao.getScaffold(actualToContigStart).getName();
		String strToContig2 = scaffolddao.getScaffold(actualToContigEnd).getName();

		if (toContig != null) {
			if (toContig.equals(strToContig1) || toContig.equals(strToContig2)) {
			} else {
				throw new RuntimeException("Aligned contig " + strToContig1 + "," + strToContig2
						+ " did not matched with specified contig " + toContig + " in " + toReference);
			}
		}

		Long strand = 1L;
		if (toStart.compareTo(toEnd) < 0)
			strand = -1L;

		MultiReferenceLocus toLocus = new MultiReferenceLocusImpl(toReference, strToContig1, toStart.intValue(),
				toEnd.intValue(), strand.intValue());
		AppContext.debug("convert locus:" + fromLocus + " to " + toLocus);
		return toLocus;
	}

	@Override
	public VariantStringData convertReferencePositions(VariantStringData variantstringdataNPB,
			MultiReferenceLocus fromMultirefLocus, MultiReferenceLocus origMultiReferenceLocus, String toContig,
			boolean isOtherRefs) throws Exception {

		scaffolddao = (ScaffoldDAO) AppContext.checkBean(scaffolddao, "ScaffoldDAO");

		orgdao = (OrganismDAO) AppContext.checkBean(orgdao, "OrganismDAO");
		Map mapOrgname2Org = orgdao.getMapName2Organism();
		BigDecimal orgid = null;

		/*
		 * Scaffold fromcontig = null; if(fromMultirefLocus!=null)
		 * fromcontig=scaffolddao.getScaffold( fromMultirefLocus.getContig() ,
		 * fromMultirefLocus.getOrganism() ); else if(origMultiReferenceLocus!=null)
		 * fromcontig=scaffolddao.getScaffold( origMultiReferenceLocus.getContig() ,
		 * origMultiReferenceLocus.getOrganism() );
		 */

		Scaffold fromcontig = null;
		if (fromMultirefLocus != null) {
			orgid = ((Organism) mapOrgname2Org.get(fromMultirefLocus.getOrganism())).getOrganismId();
			fromcontig = scaffolddao.getScaffold(fromMultirefLocus.getContig(), orgid);
		} else if (origMultiReferenceLocus != null) {
			orgid = ((Organism) mapOrgname2Org.get(origMultiReferenceLocus.getOrganism())).getOrganismId();
			fromcontig = scaffolddao.getScaffold(origMultiReferenceLocus.getContig(), orgid);

		}

		// if(origMultiReferenceLocus!=null) throw new
		// RuntimeException("origMultiReferenceLocus!=null: " +
		// origMultiReferenceLocus);
		// if(toContig!=null) throw new RuntimeException("toContig!=null: " + toContig);
		// if(isOtherRefs) throw new RuntimeException("toContig!=null: " + toContig);
		//
		//
		//
		// orgdao = (OrganismDAO)AppContext.checkBean(orgdao, "OrganismDAO");
		// scaffolddao = (ScaffoldDAO)AppContext.checkBean(scaffolddao, "ScaffoldDAO");
		//
		// Map<BigDecimal, String> mapOrgId2Name=new HashMap();
		// Organism fromorg = orgdao.getMapName2Organism().get(
		// fromMultirefLocus.getOrganism());
		//
		// Iterator<Organism> itOrgs = orgdao.getMapName2Organism().values().iterator();
		// while(itOrgs.hasNext()) {
		// Organism org = itOrgs.next();
		// mapOrgId2Name.put( org.getOrganismId(), org.getName());
		// }
		//
		// Scaffold fromcontig = scaffolddao.getScaffold( fromMultirefLocus.getContig()
		// , fromMultirefLocus.getOrganism() );
		//
		//
		// Iterator<SnpsAllvarsPos> itPos =
		// variantstringdataNPB.getListPos().iterator();
		// while(itPos.hasNext()) {
		//
		// SnpsAllvarsPos ipos=itPos.next();
		// if(ipos instanceof MultiReferencePosition) {
		//
		// }
		// }

		Map<BigDecimal, String> mapContigId2Name = new HashMap();

		Map mapRefPos2ConvertedPos_9311 = new HashMap();
		Map mapRefPos2ConvertedPos_IR64 = new HashMap();
		Map mapRefPos2ConvertedPos_DJ123 = new HashMap();
		Map mapRefPos2ConvertedPos_Kas = new HashMap();
		Map mapRefPos2ConvertedPos_NB = new HashMap();

		Iterator<SnpsAllvarsPos> itConv = variantstringdataNPB.getListPos().iterator();

		while (itConv.hasNext()) {
			VConvertposAny2allrefs posconv = (VConvertposAny2allrefs) itConv.next();

			BigDecimal refPos = posconv.getFromPosition();

			if (posconv.getRice9311ContigId() != null) {
				String cont_9311 = mapContigId2Name.get(posconv.getRice9311ContigId());
				if (cont_9311 == null) {

					cont_9311 = scaffolddao.getScaffold(posconv.getRice9311ContigId()).getName();
					if (cont_9311 == null)
						AppContext.debug("scaffolddao.getScaffold( posconv.get_311ContigId())==null: "
								+ posconv.getRice9311ContigId());
					else {
						mapContigId2Name.put(posconv.getRice9311ContigId(), cont_9311);
					}
				}
				MultiReferencePosition conv = new MultiReferencePositionImpl(Organism.REFERENCE_9311, cont_9311,
						posconv.getRice9311ContigId(), posconv.getRice9311Refcall());
				mapRefPos2ConvertedPos_9311.put(refPos, conv);
			}

			if (posconv.getIr64ContigId() != null) {
				String cont_IR64 = mapContigId2Name.get(posconv.getIr64ContigId());
				if (cont_IR64 == null) {
					cont_IR64 = scaffolddao.getScaffold(posconv.getIr64ContigId()).getName();
					if (cont_IR64 == null)
						AppContext.debug("scaffolddao.getScaffold( posconv.getIr64ContigId())==null: "
								+ posconv.getIr64ContigId());
					else {
						mapContigId2Name.put(posconv.getIr64ContigId(), cont_IR64);
					}
				}
				MultiReferencePosition conv = new MultiReferencePositionImpl(Organism.REFERENCE_IR64, cont_IR64,
						posconv.getIr64Position(), posconv.getIr64Refcall());
				mapRefPos2ConvertedPos_IR64.put(refPos, conv);
			}

			if (posconv.getDj123ContigId() != null) {
				String cont_DJ123 = mapContigId2Name.get(posconv.getDj123ContigId());
				if (cont_DJ123 == null) {
					cont_DJ123 = scaffolddao.getScaffold(posconv.getDj123ContigId()).getName();
					if (cont_DJ123 == null)
						AppContext.debug("scaffolddao.getScaffold( posconv.getDj123ContigId())==null: "
								+ posconv.getDj123ContigId());
					else {
						mapContigId2Name.put(posconv.getDj123ContigId(), cont_DJ123);
					}
				}
				MultiReferencePosition conv = new MultiReferencePositionImpl(Organism.REFERENCE_DJ123, cont_DJ123,
						posconv.getDj123Position(), posconv.getDj123Refcall());
				mapRefPos2ConvertedPos_DJ123.put(refPos, conv);
			}

			if (posconv.getKasalathContigId() != null) {
				String cont_Kasalath = mapContigId2Name.get(posconv.getKasalathContigId());
				if (cont_Kasalath == null) {
					cont_Kasalath = scaffolddao.getScaffold(posconv.getKasalathContigId()).getName();
					if (cont_Kasalath == null)
						AppContext.debug("scaffolddao.getScaffold( posconv.getKasalathContigId())==null: "
								+ posconv.getKasalathContigId());
					else {
						mapContigId2Name.put(posconv.getKasalathContigId(), cont_Kasalath);
					}
				}
				MultiReferencePosition conv = new MultiReferencePositionImpl(Organism.REFERENCE_KASALATH, cont_Kasalath,
						posconv.getKasalathPosition(), posconv.getKasalathRefcall());
				mapRefPos2ConvertedPos_Kas.put(refPos, conv);
			}

			if (posconv.getNbContigId() != null) {
				String cont_Nb = mapContigId2Name.get(posconv.getNbContigId());
				if (cont_Nb == null) {
					cont_Nb = scaffolddao.getScaffold(posconv.getNbContigId()).getName();
					if (cont_Nb == null)
						AppContext.debug(
								"scaffolddao.getScaffold( posconv.getNbContigId())==null: " + posconv.getNbContigId());
					else {
						mapContigId2Name.put(posconv.getNbContigId(), cont_Nb);
					}
				}
				MultiReferencePosition conv = new MultiReferencePositionImpl(Organism.REFERENCE_NIPPONBARE, cont_Nb,
						posconv.getNbPosition(), posconv.getNbRefcall());
				mapRefPos2ConvertedPos_NB.put(refPos, conv);
			}

		}

		AppContext.debug(mapContigId2Name.toString() + "\n size=" + mapContigId2Name.size());

		// variantstringdataNPB.

		if (fromcontig != null) {
			variantstringdataNPB.addMapOrg2RefPosConverterPos(Organism.REFERENCE_9311, mapRefPos2ConvertedPos_9311,
					fromcontig.getName());
			variantstringdataNPB.addMapOrg2RefPosConverterPos(Organism.REFERENCE_IR64, mapRefPos2ConvertedPos_IR64,
					fromcontig.getName());
			variantstringdataNPB.addMapOrg2RefPosConverterPos(Organism.REFERENCE_DJ123, mapRefPos2ConvertedPos_DJ123,
					fromcontig.getName());
			variantstringdataNPB.addMapOrg2RefPosConverterPos(Organism.REFERENCE_KASALATH, mapRefPos2ConvertedPos_Kas,
					fromcontig.getName());
			variantstringdataNPB.addMapOrg2RefPosConverterPos(Organism.REFERENCE_NIPPONBARE, mapRefPos2ConvertedPos_NB,
					fromcontig.getName());
		} else {
			variantstringdataNPB.addMapOrg2RefPosConverterPos(Organism.REFERENCE_9311, mapRefPos2ConvertedPos_9311);
			variantstringdataNPB.addMapOrg2RefPosConverterPos(Organism.REFERENCE_IR64, mapRefPos2ConvertedPos_IR64);
			variantstringdataNPB.addMapOrg2RefPosConverterPos(Organism.REFERENCE_DJ123, mapRefPos2ConvertedPos_DJ123);
			variantstringdataNPB.addMapOrg2RefPosConverterPos(Organism.REFERENCE_KASALATH, mapRefPos2ConvertedPos_Kas);
			variantstringdataNPB.addMapOrg2RefPosConverterPos(Organism.REFERENCE_NIPPONBARE, mapRefPos2ConvertedPos_NB);

		}

		// variantstringdataNPB.getMapOrg2RefPos2ConvertedPos().remove(fromMultirefLocus.getOrganism());

		return variantstringdataNPB;
	}

	/*
	 * @Override public List getSNPs(String chromosome, Integer startPos, Integer
	 * endPos, BigDecimal type, BigDecimal organism) { StringBuffer buff=new
	 * StringBuffer(); return getSNPs(chromosome, startPos, endPos, type, organism,
	 * buff); }
	 */

	@Override
	public List getSNPs(String chromosome, Integer startPos, Integer endPos, Set type, BigDecimal organism,
			StringBuffer buff) {

		scaffolddao = (ScaffoldDAO) AppContext.checkBean(scaffolddao, "ScaffoldDAO");
		Set result = findVConvertposAny2allrefsByFromOrgIdContigIdPosBetween(organism,
				scaffolddao.getScaffold(chromosome, organism).getFeatureId(), BigDecimal.valueOf(startPos),
				BigDecimal.valueOf(endPos));
		return mergeSamePosDifferentSnpFeatureId(result, buff);
	}

	private List mergeSamePosDifferentSnpFeatureId(Set inset, StringBuffer strMsg) {

		orgdao = (OrganismDAO) AppContext.checkBean(orgdao, "OrganismDAO");
		List list = new ArrayList();
		VConvertposAny2allrefs prevpos = null;
		Iterator<VConvertposAny2allrefs> itPos = inset.iterator();
		int icount = 0;

		Map<String, Map<String, Set<String>>> mapFrompos2Org2Topos = new LinkedHashMap();

		// sort by type-group-alleleindex
		Map<BigDecimal, Map<Integer, List<SnpsAllvarsMultirefsPos>>> mapType2Group2Pos = new TreeMap();
		while (itPos.hasNext()) {
			SnpsAllvarsMultirefsPos vpos = itPos.next();
			Map<Integer, List<SnpsAllvarsMultirefsPos>> mapGroup2Pos = mapType2Group2Pos.get(vpos.getFileId());
			List listPos = null;
			if (mapGroup2Pos == null) {
				mapGroup2Pos = new LinkedHashMap();
				mapType2Group2Pos.put(vpos.getFileId(), mapGroup2Pos);
			} else {
				// same type
			}

			// get last group
			int ngroups = mapGroup2Pos.size();
			List<SnpsAllvarsMultirefsPos> listppos = null;
			if (ngroups == 0) {
				// firstgroup
				listppos = new ArrayList();
				mapGroup2Pos.put(1, listppos);
				listppos.add(vpos);
			} else {
				// get lastgroup
				listppos = mapGroup2Pos.get(ngroups);
				SnpsAllvarsMultirefsPos lastgrouppos = listppos.get(listppos.size() - 1);
				if (lastgrouppos.getAlleleIndex().intValue() + 1 == vpos.getAlleleIndex().intValue()) {
					// same group
					listppos.add(vpos);
				} else {
					// new group
					listppos = new ArrayList();
					mapGroup2Pos.put(1 + ngroups, listppos);
					listppos.add(vpos);
				}
			}

		}

		//
		if (AppContext.isLocalhost())
			AppContext.debug("mapType2Group2Pos=" + mapType2Group2Pos);

		itPos = inset.iterator();
		while (itPos.hasNext()) {
			VConvertposAny2allrefs vpos = itPos.next();
			boolean keepprev = false;

			if (prevpos != null) {

				if (vpos.getContig().equals(prevpos.getContig()) && vpos.getPosition().equals(prevpos.getPosition())) {

					AppContext.debug("compiling multiple aligned positions " + icount + " [" + vpos.getSnpFeatureId()
							+ " :" + vpos.getFileId() + ":" + vpos.getAlleleIndex() + "] " + vpos.getContig() + "-"
							+ vpos.getPosition() + " to prevpos [" + prevpos.getSnpFeatureId() + ":"
							+ prevpos.getFileId() + ": " + prevpos.getAlleleIndex() + "] " + prevpos.getContig() + "-"
							+ prevpos.getPosition());
					strMsg.append("Merged snp " + vpos.getSnpFeatureId() + "-" + vpos.getFileId() + "-"
							+ vpos.getAlleleIndex() + " to " + prevpos.getSnpFeatureId() + "-" + prevpos.getFileId()
							+ "-" + prevpos.getAlleleIndex() + ": both alinged with "
							+ orgdao.getOrganismByID(vpos.getOrganism().intValue()).getName() + "-" + vpos.getContig()
							+ "-" + vpos.getPosition() + "\n");

					String prevstrpos = prevpos.getContig() + "-" + prevpos.getPosition();
					Map<String, Set<String>> mapOrg2Topos = mapFrompos2Org2Topos.get(prevstrpos);

					if (prevpos.getDj123ContigId() != null) {
						Set listPos = mapOrg2Topos.get(Organism.REFERENCE_DJ123);
						if (listPos == null)
							listPos = new LinkedHashSet();
						listPos.add(prevpos.getDJ123ContigName() + "-" + prevpos.getDj123Position() + "-"
								+ prevpos.getDj123Refcall());
					}
					if (vpos.getDj123ContigId() != null) {
						Set listPos = mapOrg2Topos.get(Organism.REFERENCE_DJ123);
						if (listPos == null)
							listPos = new LinkedHashSet();
						listPos.add(vpos.getDJ123ContigName() + "-" + vpos.getDj123Position() + "-"
								+ vpos.getDj123Refcall());
					}

					if (prevpos.getNbContigId() != null) {
						Set listPos = mapOrg2Topos.get(Organism.REFERENCE_NIPPONBARE);
						if (listPos == null)
							listPos = new LinkedHashSet();
						listPos.add(prevpos.getNBContigName() + "-" + prevpos.getNbPosition() + "-"
								+ prevpos.getNbRefcall());
					}
					if (vpos.getNbContigId() != null) {
						Set listPos = mapOrg2Topos.get(Organism.REFERENCE_NIPPONBARE);
						if (listPos == null)
							listPos = new LinkedHashSet();
						listPos.add(vpos.getNBContigName() + "-" + vpos.getNbPosition() + "-" + vpos.getNbRefcall());
					}

					if (prevpos.getRice9311ContigId() != null) {
						Set listPos = mapOrg2Topos.get(Organism.REFERENCE_9311);
						if (listPos == null)
							listPos = new LinkedHashSet();
						listPos.add(prevpos.get9311ContigName() + "-" + prevpos.getRice9311Position() + "-"
								+ prevpos.getRice9311Refcall());
					}
					if (vpos.getRice9311ContigId() != null) {
						Set listPos = mapOrg2Topos.get(Organism.REFERENCE_9311);
						if (listPos == null)
							listPos = new LinkedHashSet();
						listPos.add(vpos.get9311ContigName() + "-" + vpos.getRice9311Position() + "-"
								+ vpos.getRice9311Refcall());
					}

					if (prevpos.getKasalathContigId() != null) {
						Set listPos = mapOrg2Topos.get(Organism.REFERENCE_KASALATH);
						if (listPos == null)
							listPos = new LinkedHashSet();
						listPos.add(prevpos.getKasalathContigName() + "-" + prevpos.getKasalathPosition() + "-"
								+ prevpos.getKasalathRefcall());
					}
					if (vpos.getKasalathContigId() != null) {
						Set listPos = mapOrg2Topos.get(Organism.REFERENCE_KASALATH);
						if (listPos == null)
							listPos = new LinkedHashSet();
						listPos.add(vpos.getKasalathContigName() + "-" + vpos.getKasalathPosition() + "-"
								+ vpos.getKasalathRefcall());
					}

					if (prevpos.getIr64ContigId() != null) {
						Set listPos = mapOrg2Topos.get(Organism.REFERENCE_IR64);
						if (listPos == null)
							listPos = new LinkedHashSet();
						listPos.add(prevpos.getIR64ContigName() + "-" + prevpos.getIr64Position() + "-"
								+ prevpos.getIr64Refcall());
					}
					if (vpos.getIr64ContigId() != null) {
						Set listPos = mapOrg2Topos.get(Organism.REFERENCE_IR64);
						if (listPos == null)
							listPos = new LinkedHashSet();
						listPos.add(
								vpos.getIR64ContigName() + "-" + vpos.getIr64Position() + "-" + vpos.getIr64Refcall());
					}

					/*
					 * AppContext.debug("merging position " + icount+ " [" + vpos.getSnpFeatureId()
					 * + " :" + vpos.getFileId() +":" + vpos.getAlleleIndex() +"] " +
					 * vpos.getContig() + "-" + vpos.getPosition() + " to prevpos [" +
					 * prevpos.getSnpFeatureId() +":" + prevpos.getFileId() + ": "+
					 * prevpos.getAlleleIndex() +"] " + prevpos.getContig() + "-" +
					 * prevpos.getPosition()); strMsg.append("Merged snp " +
					 * vpos.getSnpFeatureId()+"-"+vpos.getFileId() + "-" + vpos.getAlleleIndex() +
					 * " to " + prevpos.getSnpFeatureId()+"-"+ prevpos.getFileId() + "-" +
					 * prevpos.getAlleleIndex() + ": both alinged with " +
					 * orgdao.getOrganismByID(vpos.getOrganism().intValue()).getName() + "-" +
					 * vpos.getContig() + "-" + vpos.getPosition() +"\n");
					 * 
					 * if(prevpos.getDj123ContigId()==null && vpos.getDj123ContigId()!=null ) {
					 * prevpos.setDj123ContigId( vpos.getDj123ContigId() );
					 * prevpos.setDj123Position( vpos.getDj123Position() ); prevpos.setDj123Refcall(
					 * vpos.getDj123Refcall() ); AppContext.debug("dj123 copied to prev");
					 * keepprev=true; } if(prevpos.getNbContigId()==null &&
					 * vpos.getNbContigId()!=null ) { prevpos.setNbContigId( vpos.getNbContigId() );
					 * prevpos.setNbPosition( vpos.getNbPosition() ); prevpos.setNbRefcall(
					 * vpos.getNbRefcall() ); AppContext.debug("nb copied to prev"); keepprev=true;
					 * } if(prevpos.getRice9311ContigId()==null && vpos.getRice9311ContigId()!=null
					 * ) { prevpos.setRice9311ContigId( vpos.getRice9311ContigId() );
					 * prevpos.setRice9311Position( vpos.getRice9311Position() );
					 * prevpos.setRice9311Refcall( vpos.getRice9311Refcall() );
					 * AppContext.debug("9311 copied to prev"); keepprev=true; }
					 * if(prevpos.getIr64ContigId()==null && vpos.getIr64ContigId()!=null ) {
					 * prevpos.setIr64ContigId( vpos.getIr64ContigId() ); prevpos.setIr64Position(
					 * vpos.getIr64Position() ); prevpos.setIr64Refcall( vpos.getIr64Refcall() );
					 * AppContext.debug("ir64 copied to prev"); keepprev=true; }
					 * if(prevpos.getKasalathContigId()==null && vpos.getKasalathContigId()!=null )
					 * { prevpos.setKasalathContigId( vpos.getKasalathContigId() );
					 * prevpos.setKasalathPosition( vpos.getKasalathPosition() );
					 * prevpos.setKasalathRefcall( vpos.getKasalathRefcall() );
					 * AppContext.debug("kasalath copied to prev"); keepprev=true; }
					 * 
					 * if(keepprev) vpos=prevpos;
					 * 
					 */

				} else
					list.add(prevpos);
			}
			prevpos = vpos;
			icount++;
		}
		list.add(prevpos);

		AppContext.debug("merging same pos: orig=" + inset.size() + ", merged=" + list.size());

		return list;

	}

	/*
	 * @Override public List getSNPs(String chromosome, Integer startPos, Integer
	 * endPos, BigDecimal type) { return getSNPs( chromosome, startPos, endPos,
	 * type, new StringBuffer()); }
	 * 
	 * @Override public List getSNPs(String chromosome, Integer startPos, Integer
	 * endPos, BigDecimal type, StringBuffer buff) {
	 * 
	 * scaffolddao=(ScaffoldDAO)AppContext.checkBean(scaffolddao, "ScaffoldDAO");
	 * Set result =
	 * findVConvertposAny2allrefsByFromOrgIdContigIdPosBetween(Organism.
	 * REFERENCE_NIPPONBARE_ID, scaffolddao.getScaffold(chromosome,
	 * Organism.REFERENCE_NIPPONBARE_ID).getFeatureId(),
	 * BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos)); return
	 * mergeSamePosDifferentSnpFeatureId(result,buff); }
	 * 
	 * @Override public List getSNPs(String chromosome, Integer startPos, Integer
	 * endPos, BigDecimal type, int firstRow, int maxRows) {
	 * 
	 * return getSNPs(chromosome, startPos, endPos, type); }
	 * 
	 */

	@Override
	public List getSNPs(String chromosome, Integer startPos, Integer endPos, Set type) {
		return getSNPs(chromosome, startPos, endPos, type, new StringBuffer());
	}

	@Override
	public List getSNPs(String chromosome, Integer startPos, Integer endPos, Set type, StringBuffer buff) {

		scaffolddao = (ScaffoldDAO) AppContext.checkBean(scaffolddao, "ScaffoldDAO");
		Set result = findVConvertposAny2allrefsByFromOrgIdContigIdPosBetween(Organism.REFERENCE_NIPPONBARE_ID,
				scaffolddao.getScaffold(chromosome, Organism.REFERENCE_NIPPONBARE_ID).getFeatureId(),
				BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos));
		return mergeSamePosDifferentSnpFeatureId(result, buff);
	}

	@Override
	public List getSNPs(String chromosome, Integer startPos, Integer endPos, Set type, int firstRow, int maxRows) {

		return getSNPs(chromosome, startPos, endPos, type);
	}

	@Override
	public long countSNPsInChromosome(String chr, Collection posset, Set type) {

		return -1;
	}

	@Override
	public long countSNPs(String chr, Integer startPos, Integer endPos, Set type) {

		return -1;
	}

	private List executeSQL(String sql) {
		if (AppContext.isLocalhost())
			AppContext.debug("executing :" + sql);
		// log.info("executing :" + sql);
		return getSession().createSQLQuery(sql).addEntity(VConvertposAny2allrefs.class).list();
	}

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	@Override
	public List getSNPsInChromosome(String chr, Collection posset, Set type) {

		return getSNPsInChromosome(chr, posset, type, Organism.REFERENCE_NIPPONBARE_ID);
	}

	// @Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List getSNPsInChromosome(String chr, Collection posset, Set type, BigDecimal organism, StringBuffer buff) {

		Set result = new LinkedHashSet(getSNPsInChromosome(chr, posset, type, organism));
		return mergeSamePosDifferentSnpFeatureId(result, buff);
	}

	// @SuppressWarnings("unchecked")
	@Override
	public List getSNPsInChromosome(String chr, Collection posset, Set variantset, BigDecimal organism) {

		scaffolddao = (ScaffoldDAO) AppContext.checkBean(scaffolddao, "ScaffoldDAO");

		String sqlcols = " srp.SNP_FEATURE_ID, srp.FROM_ORGANISM_ID, srp.FROM_CONTIG_ID, srp.FROM_POSITION, srp.FROM_REFCALL, srp.NB_CONTIG_ID, srp.NB_POSITION, srp.NB_REFCALL, srp.IR64_CONTIG_ID, srp.IR64_POSITION, srp.IR64_REFCALL, srp.RICE9311_CONTIG_ID, srp.RICE9311_POSITION, srp.RICE9311_REFCALL, srp.DJ123_CONTIG_ID, srp.DJ123_POSITION, srp.DJ123_REFCALL, srp.KASALATH_CONTIG_ID, srp.KASALATH_POSITION, srp.KASALATH_REFCALL, srp.ORGANISM_ID, srp.TYPE_ID, srp.ALLELE_INDEX, srp.NB_CONTIG_NAME, srp.RICE9311_CONTIG_NAME, srp.IR64_CONTIG_NAME, srp.DJ123_CONTIG_NAME, srp.KASALATH_CONTIG_NAME, srp.NB_ALIGN_COUNT, srp.RICE9311_ALIGN_COUNT, srp.IR64_ALIGN_COUNT, srp.DJ123_ALIGN_COUNT, srp.KASALATH_ALIGN_COUNT ";

		BigDecimal bdChr = null;
		if (chr.equalsIgnoreCase("any")) {

			if (organism.intValue() != 9)
				throw new RuntimeException("organism=" + organism);

			long poscount = 0;
			List listPresent = new ArrayList();
			AppContext.debug("checking " + posset.size() + " snp positions");
			Map mapChr2Pos = MultiReferencePositionImpl.getMapContig2SNPPos(posset);

			// String sql = ""; //select " + sqlcols + " from " +
			// AppContext.getDefaultSchema() + ".v_convertpos_any2allrefs WHERE
			// from_organism_id=" + organism + " and organism_id=" + organism + " and (";

			String sql = "select ut.*, 9 ORGANISM_ID,  mv.type_id, mv.allele_index from (\r\n"
					+ " SELECT \"SNP_FEATURE_ID\",\"FROM_ORGANISM_ID\",\"FROM_CONTIG_ID\",\"FROM_POSITION\",\"FROM_REFCALL\",\"NB_CONTIG_ID\",\"NB_POSITION\",\"NB_REFCALL\",  \r\n"
					+ "case when length(NB_CONTIG_NAME)=5 then lower(NB_CONTIG_NAME)\r\n"
					+ "else replace(lower(NB_CONTIG_NAME),'chr','chr0') end  \"NB_CONTIG_NAME\",\"NB_ALIGN_COUNT\",\"IR64_CONTIG_ID\",\"IR64_POSITION\",\"IR64_REFCALL\",\"IR64_CONTIG_NAME\",\"IR64_ALIGN_COUNT\",\"RICE9311_CONTIG_ID\",\"RICE9311_POSITION\",\"RICE9311_REFCALL\",\"RICE9311_CONTIG_NAME\",\"RICE9311_ALIGN_COUNT\",\"DJ123_CONTIG_ID\",\"DJ123_POSITION\",\"DJ123_REFCALL\",\"DJ123_CONTIG_NAME\",\"DJ123_ALIGN_COUNT\",\"KASALATH_CONTIG_ID\",\"KASALATH_POSITION\",\"KASALATH_REFCALL\",\"KASALATH_CONTIG_NAME\",\"KASALATH_ALIGN_COUNT\" \r\n"
					+ "from (\r\n";

			Iterator<String> itContig = mapChr2Pos.keySet().iterator();
			while (itContig.hasNext()) {
				String contigstr = itContig.next();
				// String contig = contigstr.toUpperCase().replace("CHR0","").replace("CHR","");
				Collection setPos = (Collection) mapChr2Pos.get(contigstr);
				poscount += setPos.size();

				// sql += "select * from (select " + sqlcols + " from " +
				// AppContext.getDefaultSchema() + ".v_convertpos_any2allrefs srp WHERE
				// srp.from_organism_id=" + organism + " and srp.organism_id=" + organism + "
				// and (";
				// sql+= "( srp.TYPE_ID=" + type + " and srp.from_contig_id=" +
				// scaffolddao.getScaffold(contigstr, organism).getFeatureId() + " and exists
				// (select column_value from table(sys.odcinumberlist(" +
				// setPos.toString().replace("[", "").replace("]", "") + " )) t where
				// t.column_value=srp.from_position ))) order by srp.from_position) ";

				BigDecimal contigid = scaffolddao.getScaffold(contigstr, organism).getFeatureId();
				String posstr = setPos.toString().replace("[", "").replace("]", "");

				sql += "SELECT SL.SNP_FEATURE_ID,  SL.ORGANISM_ID FROM_ORGANISM_ID, SL.SRCFEATURE_ID FROM_CONTIG_ID, SL.POSITION+1 FROM_POSITION, SL.REFCALL FROM_REFCALL,\r\n"
						+ " SLX.ORGANISM_ID TO_ORGANISM_ID, SLX.SRCFEATURE_ID TO_CONTIG_ID, SLX.POSITION+1 TO_POSITION, SLX.REFCALL TO_REFCALL, FTO.NAME TO_CONTIG_NAME, FTO.SEQLEN TO_CONTIG_LEN\r\n"
						+ "FROM " + AppContext.getDefaultSchema() + ".FEATURE FTO, " + AppContext.getDefaultSchema()
						+ ".SNP_FEATURELOC SL \r\n" + "    left join " + AppContext.getDefaultSchema()
						+ ".SNP_FEATURELOC_INTXN_NB SLX\r\n" + "    on SL.SNP_FEATURE_ID=SLX.SNP_FEATURE_ID\r\n"
						+ "where FTO.FEATURE_ID=SLX.SRCFEATURE_ID\r\n" + " and  SL.ORGANISM_ID=" + organism
						+ " and FTO.ORGANISM_ID=" + organism + " " + "and SLX.SRCFEATURE_ID=" + contigid + "\r\n"
						+ "and  exists (select column_value from table(sys.odcinumberlist(" + posstr
						+ " )) t where t.column_value=SLX.POSITION+1)\r\n" + "UNION\r\n"
						+ "SELECT SL.SNP_FEATURE_ID,  SL.ORGANISM_ID FROM_ORGANISM_ID, SL.SRCFEATURE_ID FROM_CONTIG_ID, SL.POSITION+1 FROM_POSITION, SL.REFCALL FROM_REFCALL,\r\n"
						+ " SL.ORGANISM_ID TO_ORGANISM_ID, SL.SRCFEATURE_ID TO_CONTIG_ID, SL.POSITION+1 TO_POSITION, SL.REFCALL TO_REFCALL, FTO.NAME TO_CONTIG_NAME, FTO.SEQLEN TO_CONTIG_LEN\r\n"
						+ "FROM " + AppContext.getDefaultSchema() + ".SNP_FEATURELOC SL, "
						+ AppContext.getDefaultSchema() + ".FEATURE FTO\r\n"
						+ "WHERE FTO.FEATURE_ID=SL.SRCFEATURE_ID\r\n" + " and  SL.ORGANISM_ID=" + organism
						+ " and FTO.ORGANISM_ID=" + organism + " " + "and SL.SRCFEATURE_ID=" + contigid + "\r\n"
						+ "and  exists (select column_value from table(sys.odcinumberlist(" + posstr
						+ " )) t where t.column_value=SL.POSITION+1) \r\n";

				if (itContig.hasNext())
					sql += " union \r\n";
			}

			sql += ")  pivot\r\n" + "( \r\n"
					+ "    min(to_contig_id) keep (dense_rank first order by to_contig_len desc, to_contig_id, to_position) as contig_id,\r\n"
					+ "    min(to_position) keep (dense_rank first order by to_contig_len desc, to_contig_id, to_position) as position,\r\n"
					+ "    min(to_refcall) keep (dense_rank first order by to_contig_len desc, to_contig_id, to_position) as refcall,\r\n"
					+ "    min(to_contig_name) keep (dense_rank first order by  to_contig_len desc, to_contig_id, to_position) as contig_name,\r\n"
					+ "    count(to_contig_id) as align_count\r\n"
					+ "    for to_organism_id in (9 as nb, 13 as ir64, 14  as RICE9311, 15  as dj123 , 16  as kasalath)\r\n"
					+ ") ) ut " + "left join " + AppContext.getDefaultSchema() + ".mv_snp_refposindex2 mv\r\n"
					+ "      on ut.snp_feature_id= mv.snp_feature_id\r\n" + "      and mv.variantset in ("
					+ AppContext.toCSVquoted(variantset, "'") + ")\r\n"
					+ "order by ut.from_contig_id, ut.from_position";

			/*
			 * long poscount=0; List listPresent = new ArrayList();
			 * AppContext.debug("checking " + posset.size() + " snp positions"); Map
			 * mapChr2Pos = MultiReferencePositionImpl.getMapContig2SNPPos(posset); String
			 * sql = ""; //select " + sqlcols + " from
			 * " + AppContext.getDefaultSchema() + ".v_convertpos_any2allrefs WHERE
			 * from_organism_id=" + organism + " and organism_id=" + organism + " and (";
			 * 
			 * Iterator<String> itContig= mapChr2Pos.keySet().iterator();
			 * while(itContig.hasNext()) { String contigstr = itContig.next(); //String
			 * contig = contigstr.toUpperCase().replace("CHR0","").replace("CHR","");
			 * Collection setPos = (Collection)mapChr2Pos.get(contigstr);
			 * poscount+=setPos.size();
			 * 
			 * //sql +=
			 * " select * from ( select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME, POSITION , REFCALL , ALLELE_INDEX from "
			 * + AppContext.getDefaultSchema() + ".V_SNP_REFPOSINDEX srp WHERE 1=1 and (";
			 * sql += "select * from (select " + sqlcols + " from " +
			 * AppContext.getDefaultSchema() +
			 * ".v_convertpos_any2allrefs srp WHERE srp.from_organism_id=" + organism +
			 * " and srp.organism_id=" + organism + " and (";
			 * 
			 * sql+= "( srp.TYPE_ID=" + type + " and srp.from_contig_id=" +
			 * scaffolddao.getScaffold(contigstr, organism).getFeatureId() +
			 * "  and exists (select column_value from table(sys.odcinumberlist(" +
			 * setPos.toString().replace("[", "").replace("]", "") +
			 * " )) t where t.column_value=srp.from_position ))) order by srp.from_position) "
			 * ; if(itContig.hasNext()) sql += " union ";
			 * 
			 * }; sql += " order by 3,4"; //sql += ") and TYPE_ID=" + type +
			 * " order by from_contig_id, from_position";
			 */

			/*
			 * long poscount=0; List listPresent = new ArrayList();
			 * AppContext.debug("checking " + posset.size() + " snp positions"); Map
			 * mapChr2Pos = MultiReferencePositionImpl.getMapContig2SNPPos(posset); String
			 * sql = "select " + sqlcols + " from " + AppContext.getDefaultSchema() +
			 * ".v_convertpos_any2allrefs WHERE from_organism_id=" + organism +
			 * " and organism_id=" + organism + " and (";
			 * 
			 * Iterator<String> itContig= mapChr2Pos.keySet().iterator();
			 * while(itContig.hasNext()) { String contigstr = itContig.next(); //String
			 * contig = contigstr.toUpperCase().replace("CHR0","").replace("CHR","");
			 * Collection setPos = (Collection)mapChr2Pos.get(contigstr);
			 * poscount+=setPos.size(); Set slicedset[] = AppContext.setSlicer(new
			 * TreeSet(setPos)); for(int iset=0; iset<slicedset.length; iset++) { //sql+=
			 * "( chromosome=" + contig + " and position in (" +
			 * setPos.toString().replace("[", "").replace("]", "") + ")) "; sql+=
			 * "( from_contig_id=" + scaffolddao.getScaffold(contigstr,
			 * organism).getFeatureId() + " and from_position in (" +
			 * slicedset[iset].toString().replace("[", "").replace("]", "") + ")) ";
			 * if(iset<slicedset.length-1) { sql+= " or "; } } if(itContig.hasNext()) sql +=
			 * " or "; };
			 * 
			 * sql += ") and TYPE_ID=" + type + " order by from_contig_id, from_position";
			 * 
			 */

			AppContext.debug("querying  v_convertpos_any2allrefs with " + poscount + " positions");
			return executeSQL(sql);
		} else if (chr.toLowerCase().equals("loci")) {

			long poscount = 0;
			AppContext.debug("checking " + posset.size() + " loci");
			Map mapChr2Pos = MultiReferencePositionImpl.getMapContig2Loci(posset);
			String sql = "select " + sqlcols + "  from " + AppContext.getDefaultSchema()
					+ ".v_convertpos_any2allrefs WHERE from_organism_id=" + organism + " and organism_id=" + organism
					+ " and (";
			Iterator<String> itContig = mapChr2Pos.keySet().iterator();
			while (itContig.hasNext()) {
				String contigstr = itContig.next();
				// String contig = contigstr.toUpperCase().replace("CHR0","").replace("CHR","");
				Collection<Locus> setLocus = (Collection) mapChr2Pos.get(contigstr);
				poscount += setLocus.size();
				Iterator<Locus> itLocus = setLocus.iterator();
				while (itLocus.hasNext()) {
					Locus loc = itLocus.next();
					sql += "( from_contig_id=" + scaffolddao.getScaffold(contigstr, organism).getFeatureId()
							+ " and from_position between " + loc.getFmin() + " and " + loc.getFmax() + ") ";
					if (itLocus.hasNext())
						sql += " or ";
				}
				if (itContig.hasNext())
					sql += " or ";

			}
			;

			sql += ") and variantset in (" + AppContext.toCSVquoted(variantset, "'")
					+ ") order by from_contig_id, from_position";

			AppContext.debug("querying  v_convertpos_any2allrefs with " + poscount + " loci");

			return executeSQL(sql);
		} else {

			AppContext.debug("querying  v_convertpos_any2allrefs with " + posset.size() + " positions");
			try {

				String sql = "select " + sqlcols + "  from " + AppContext.getDefaultSchema()
						+ ".v_convertpos_any2allrefs WHERE from_organism_id=" + organism + " and organism_id="
						+ organism + " and (";

				Set slicedset[] = AppContext.setSlicer(new TreeSet(posset));
				List listPresent = new ArrayList();
				for (int iset = 0; iset < slicedset.length; iset++) {

					sql += "( from_contig_id=" + scaffolddao.getScaffold(chr, organism).getFeatureId()
							+ " and from_position in (" + slicedset[iset].toString().replace("[", "").replace("]", "")
							+ ")) ";
					if (iset < slicedset.length - 1) {
						sql += " or ";
					}
				}
				sql += ") and variantset in (" + AppContext.toCSVquoted(variantset, "'")
						+ ") order by from_contig_id, from_position";

				return executeSQL(sql);

				/*
				 * chr = chr.toUpperCase().replace("CHR0","").replace("CHR",""); bdChr =
				 * BigDecimal.valueOf(Long.valueOf(chr)); Query query =
				 * createNamedQuery("findVSnpRefposindexByChrPosIn", -1, -1, bdChr , posset,
				 * type); return query.getResultList();
				 */
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return new ArrayList();
	}

	/*
	 * @Override public List getSNPsInChromosome(String chr, Collection posset,
	 * BigDecimal type) { Set slicedset[] = AppContext.setSlicer(new
	 * HashSet(posset)); List listPresent = new ArrayList(); for(int iset=0;
	 * iset<slicedset.length; iset++) { listPresent.addAll(
	 * _getSNPsInChromosome(chr, slicedset[iset], type) ); } return listPresent; }
	 * 
	 * 
	 * 
	 * private List _getSNPsInChromosome(String chr, Collection posset, BigDecimal
	 * type) {
	 * 
	 * BigDecimal bdChr=null; if(chr.toLowerCase().equals("any")) {
	 * 
	 * AppContext.debug("checking " + posset.size() + " snp positions"); Map
	 * mapChr2Pos = MultiReferencePositionImpl.getMapContig2SNPPos(posset); String
	 * sql =
	 * "select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME, POSITION , REFCALL , ALLELE_INDEX from "
	 * + AppContext.getDefaultSchema() + ".V_SNP_REFPOSINDEX WHERE 1=1 and (";
	 * Iterator<String> itContig= mapChr2Pos.keySet().iterator();
	 * while(itContig.hasNext()) { String contigstr = itContig.next(); String contig
	 * = contigstr.toUpperCase().replace("CHR0","").replace("CHR",""); Collection
	 * setPos = (Collection)mapChr2Pos.get(contigstr); sql+= "( chromosome=" +
	 * contig + " and position in (" + setPos.toString().replace("[",
	 * "").replace("]", "") + ")) "; if(itContig.hasNext()) sql += " or "; };
	 * 
	 * sql += ") and TYPE_ID=" + type + " order by CHROMOSOME, POSITION"; return
	 * executeSQL(sql); } else if(chr.toLowerCase().equals("loci")) {
	 * 
	 * AppContext.debug("checking " + posset.size() + " loci"); Map mapChr2Pos =
	 * MultiReferencePositionImpl.getMapContig2Loci(posset); String sql =
	 * "select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME, POSITION , REFCALL , ALLELE_INDEX from "
	 * + AppContext.getDefaultSchema() + ".V_SNP_REFPOSINDEX WHERE 1=1 and (";
	 * Iterator<String> itContig= mapChr2Pos.keySet().iterator();
	 * while(itContig.hasNext()) { String contigstr = itContig.next(); String contig
	 * = contigstr.toUpperCase().replace("CHR0","").replace("CHR","");
	 * Collection<Locus> setLocus = (Collection)mapChr2Pos.get(contigstr);
	 * Iterator<Locus> itLocus=setLocus.iterator(); while(itLocus.hasNext()) { Locus
	 * loc=itLocus.next(); sql+= "( chromosome=" + contig + " and position between "
	 * + loc.getFmin() + " and " + loc.getFmax() + ") "; if(itLocus.hasNext()) sql
	 * += " or "; } if(itContig.hasNext()) sql += " or ";
	 * 
	 * };
	 * 
	 * sql += ") and TYPE_ID=" + type + " order by CHROMOSOME, POSITION"; return
	 * executeSQL(sql); } else { AppContext.debug("checking " + posset.size() +
	 * " snp positions"); try { chr =
	 * chr.toUpperCase().replace("CHR0","").replace("CHR",""); bdChr =
	 * BigDecimal.valueOf(Long.valueOf(chr)); Query query =
	 * createNamedQuery("findVSnpRefposindexByChrPosIn", -1, -1, bdChr , posset,
	 * type); return query.getResultList(); } catch ( Exception ex) {
	 * ex.printStackTrace(); } }
	 * 
	 * return new ArrayList(); }
	 */
}
