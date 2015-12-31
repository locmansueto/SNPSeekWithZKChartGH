package org.irri.iric.portal.chado.oracle.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.oracle.domain.VConvertposAny2allrefs;
import org.irri.iric.portal.chado.oracle.domain.VConvertposNb2allrefs;
import org.irri.iric.portal.dao.ScaffoldDAO;
import org.irri.iric.portal.domain.MultiReferenceConversion;
import org.irri.iric.portal.domain.MultiReferenceLocus;
import org.irri.iric.portal.domain.MultiReferenceLocusImpl;
import org.irri.iric.portal.domain.MultiReferencePosition;
import org.irri.iric.portal.domain.MultiReferencePositionImpl;
import org.irri.iric.portal.domain.Organism;
import org.irri.iric.portal.domain.Scaffold;
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
//@Repository("VConvertposAny2allrefsDAO")
@Repository("MultipleReferenceConverterDAO")
//@Transactional
public class VConvertposAny2allrefsDAOImpl extends AbstractJpaDao<VConvertposAny2allrefs>
		implements VConvertposAny2allrefsDAO {
	
	@Autowired
	OrganismDAO orgdao;
	@Autowired
	ScaffoldDAO scaffolddao;

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VConvertposAny2allrefs.class }));

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
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByIr64Position(BigDecimal ir64Position) throws DataAccessException {

		return findVConvertposAny2allrefsByIr64Position(ir64Position, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByIr64Position
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByIr64Position(BigDecimal ir64Position, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByIr64Position", startResult, maxRows, ir64Position);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByFromOrganismId
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByFromOrganismId(java.math.BigDecimal fromOrganismId) throws DataAccessException {

		return findVConvertposAny2allrefsByFromOrganismId(fromOrganismId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByFromOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByFromOrganismId(java.math.BigDecimal fromOrganismId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByFromOrganismId", startResult, maxRows, fromOrganismId);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByFromRefcallContaining
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByFromRefcallContaining(String fromRefcall) throws DataAccessException {

		return findVConvertposAny2allrefsByFromRefcallContaining(fromRefcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByFromRefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByFromRefcallContaining(String fromRefcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByFromRefcallContaining", startResult, maxRows, fromRefcall);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByNbRefcallContaining
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByNbRefcallContaining(String nbRefcall) throws DataAccessException {

		return findVConvertposAny2allrefsByNbRefcallContaining(nbRefcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByNbRefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByNbRefcallContaining(String nbRefcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByNbRefcallContaining", startResult, maxRows, nbRefcall);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByKasalathContigId
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByKasalathContigId(BigDecimal kasalathContigId) throws DataAccessException {

		return findVConvertposAny2allrefsByKasalathContigId(kasalathContigId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByKasalathContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByKasalathContigId(BigDecimal kasalathContigId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByKasalathContigId", startResult, maxRows, kasalathContigId);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByDj123Position
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByDj123Position(BigDecimal dj123Position) throws DataAccessException {

		return findVConvertposAny2allrefsByDj123Position(dj123Position, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByDj123Position
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByDj123Position(BigDecimal dj123Position, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByDj123Position", startResult, maxRows, dj123Position);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByPrimaryKey
	 *
	 */
	@Transactional
	public VConvertposAny2allrefs findVConvertposAny2allrefsByPrimaryKey(BigDecimal snpFeatureId) throws DataAccessException {

		return findVConvertposAny2allrefsByPrimaryKey(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByPrimaryKey
	 *
	 */

	@Transactional
	public VConvertposAny2allrefs findVConvertposAny2allrefsByPrimaryKey(BigDecimal snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVConvertposAny2allrefsByPrimaryKey", startResult, maxRows, snpFeatureId);
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
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByDj123ContigId(BigDecimal dj123ContigId) throws DataAccessException {

		return findVConvertposAny2allrefsByDj123ContigId(dj123ContigId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByDj123ContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByDj123ContigId(BigDecimal dj123ContigId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByDj123ContigId", startResult, maxRows, dj123ContigId);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByNbContigId
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByNbContigId(BigDecimal nbContigId) throws DataAccessException {

		return findVConvertposAny2allrefsByNbContigId(nbContigId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByNbContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByNbContigId(BigDecimal nbContigId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByNbContigId", startResult, maxRows, nbContigId);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByRice9311RefcallContaining
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByRice9311RefcallContaining(String rice9311Refcall) throws DataAccessException {

		return findVConvertposAny2allrefsByRice9311RefcallContaining(rice9311Refcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByRice9311RefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByRice9311RefcallContaining(String rice9311Refcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByRice9311RefcallContaining", startResult, maxRows, rice9311Refcall);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByIr64Refcall
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByIr64Refcall(String ir64Refcall) throws DataAccessException {

		return findVConvertposAny2allrefsByIr64Refcall(ir64Refcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByIr64Refcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByIr64Refcall(String ir64Refcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByIr64Refcall", startResult, maxRows, ir64Refcall);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByAlleleIndex
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByAlleleIndex(BigDecimal alleleIndex) throws DataAccessException {

		return findVConvertposAny2allrefsByAlleleIndex(alleleIndex, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByAlleleIndex
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByAlleleIndex(BigDecimal alleleIndex, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByAlleleIndex", startResult, maxRows, alleleIndex);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByKasalathRefcall
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByKasalathRefcall(String kasalathRefcall) throws DataAccessException {

		return findVConvertposAny2allrefsByKasalathRefcall(kasalathRefcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByKasalathRefcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByKasalathRefcall(String kasalathRefcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByKasalathRefcall", startResult, maxRows, kasalathRefcall);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByDj123Refcall
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByDj123Refcall(String dj123Refcall) throws DataAccessException {

		return findVConvertposAny2allrefsByDj123Refcall(dj123Refcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByDj123Refcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByDj123Refcall(String dj123Refcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByDj123Refcall", startResult, maxRows, dj123Refcall);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByIr64RefcallContaining
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByIr64RefcallContaining(String ir64Refcall) throws DataAccessException {

		return findVConvertposAny2allrefsByIr64RefcallContaining(ir64Refcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByIr64RefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByIr64RefcallContaining(String ir64Refcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByIr64RefcallContaining", startResult, maxRows, ir64Refcall);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByRice9311Refcall
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByRice9311Refcall(String rice9311Refcall) throws DataAccessException {

		return findVConvertposAny2allrefsByRice9311Refcall(rice9311Refcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByRice9311Refcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByRice9311Refcall(String rice9311Refcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByRice9311Refcall", startResult, maxRows, rice9311Refcall);
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
	public Set<VConvertposAny2allrefs> findAllVConvertposAny2allrefss(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVConvertposAny2allrefss", startResult, maxRows);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByNbPosition
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByNbPosition(BigDecimal nbPosition) throws DataAccessException {

		return findVConvertposAny2allrefsByNbPosition(nbPosition, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByNbPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByNbPosition(BigDecimal nbPosition, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByNbPosition", startResult, maxRows, nbPosition);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByKasalathRefcallContaining
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByKasalathRefcallContaining(String kasalathRefcall) throws DataAccessException {

		return findVConvertposAny2allrefsByKasalathRefcallContaining(kasalathRefcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByKasalathRefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByKasalathRefcallContaining(String kasalathRefcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByKasalathRefcallContaining", startResult, maxRows, kasalathRefcall);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByRice9311Position
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByRice9311Position(BigDecimal rice9311Position) throws DataAccessException {

		return findVConvertposAny2allrefsByRice9311Position(rice9311Position, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByRice9311Position
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByRice9311Position(BigDecimal rice9311Position, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByRice9311Position", startResult, maxRows, rice9311Position);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByRice9311ContigId
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByRice9311ContigId(BigDecimal rice9311ContigId) throws DataAccessException {

		return findVConvertposAny2allrefsByRice9311ContigId(rice9311ContigId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByRice9311ContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByRice9311ContigId(BigDecimal rice9311ContigId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByRice9311ContigId", startResult, maxRows, rice9311ContigId);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsBySnpFeatureId
	 *
	 */
	@Transactional
	public VConvertposAny2allrefs findVConvertposAny2allrefsBySnpFeatureId(BigDecimal snpFeatureId) throws DataAccessException {

		return findVConvertposAny2allrefsBySnpFeatureId(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsBySnpFeatureId
	 *
	 */

	@Transactional
	public VConvertposAny2allrefs findVConvertposAny2allrefsBySnpFeatureId(BigDecimal snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVConvertposAny2allrefsBySnpFeatureId", startResult, maxRows, snpFeatureId);
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
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByNbRefcall(String nbRefcall) throws DataAccessException {

		return findVConvertposAny2allrefsByNbRefcall(nbRefcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByNbRefcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByNbRefcall(String nbRefcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByNbRefcall", startResult, maxRows, nbRefcall);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByOrganismId
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByOrganismId(BigDecimal organismId) throws DataAccessException {

		return findVConvertposAny2allrefsByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByOrganismId(BigDecimal organismId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByFromContigId
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByFromContigId(BigDecimal fromContigId) throws DataAccessException {

		return findVConvertposAny2allrefsByFromContigId(fromContigId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByFromContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByFromContigId(BigDecimal fromContigId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByFromContigId", startResult, maxRows, fromContigId);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByDj123RefcallContaining
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByDj123RefcallContaining(String dj123Refcall) throws DataAccessException {

		return findVConvertposAny2allrefsByDj123RefcallContaining(dj123Refcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByDj123RefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByDj123RefcallContaining(String dj123Refcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByDj123RefcallContaining", startResult, maxRows, dj123Refcall);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByFromPosition
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByFromPosition(BigDecimal fromPosition) throws DataAccessException {

		return findVConvertposAny2allrefsByFromPosition(fromPosition, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByFromPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByFromPosition(BigDecimal fromPosition, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByFromPosition", startResult, maxRows, fromPosition);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByKasalathPosition
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByKasalathPosition(BigDecimal kasalathPosition) throws DataAccessException {

		return findVConvertposAny2allrefsByKasalathPosition(kasalathPosition, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByKasalathPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByKasalathPosition(BigDecimal kasalathPosition, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByKasalathPosition", startResult, maxRows, kasalathPosition);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByIr64ContigId
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByIr64ContigId(BigDecimal ir64ContigId) throws DataAccessException {

		return findVConvertposAny2allrefsByIr64ContigId(ir64ContigId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByIr64ContigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByIr64ContigId(BigDecimal ir64ContigId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByIr64ContigId", startResult, maxRows, ir64ContigId);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByFromRefcall
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByFromRefcall(String fromRefcall) throws DataAccessException {

		return findVConvertposAny2allrefsByFromRefcall(fromRefcall, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByFromRefcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByFromRefcall(String fromRefcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByFromRefcall", startResult, maxRows, fromRefcall);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByTypeId
	 *
	 */
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByTypeId(BigDecimal typeId) throws DataAccessException {

		return findVConvertposAny2allrefsByTypeId(typeId, -1, -1);
	}

	/**
	 * JPQL Query - findVConvertposAny2allrefsByTypeId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByTypeId(BigDecimal typeId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByTypeId", startResult, maxRows, typeId);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VConvertposAny2allrefs entity) {
		return true;
	}
	
	
	
	

	@SuppressWarnings("unchecked")
	//@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByFromOrgIdContigIdPosBetween(java.math.BigDecimal fromOrganismId, BigDecimal contigId, BigDecimal start, BigDecimal end) throws DataAccessException {
		Query query = createNamedQuery("findVConvertposAny2allrefsByFromOrgIdContigIdPosBetween", -1, -1, fromOrganismId, contigId, start, end);
		return new LinkedHashSet<VConvertposAny2allrefs>(query.getResultList());
	}
	
	@SuppressWarnings("unchecked")
	//@Transactional
	public Set<VConvertposAny2allrefs> findVConvertposAny2allrefsByFromOrgIdContigIdPosBetween(String fromOrganism, String contig, BigDecimal start, BigDecimal end) throws DataAccessException {
		
		
		orgdao = (OrganismDAO)AppContext.checkBean(orgdao, "OrganismDAO");
		scaffolddao = (ScaffoldDAO)AppContext.checkBean(scaffolddao, "ScaffoldDAO");
		
		Map<BigDecimal, String> mapOrgId2Name=new HashMap();
		Organism fromorg = orgdao.getMapName2Organism().get( fromOrganism );
		
		Iterator<Organism> itOrgs = orgdao.getMapName2Organism().values().iterator();
		while(itOrgs.hasNext()) {
			Organism org = itOrgs.next();
			mapOrgId2Name.put( org.getOrganismId(), org.getName());
		}
		
		Scaffold fromcontig = scaffolddao.getScaffold( contig  , fromOrganism );
		return findVConvertposAny2allrefsByFromOrgIdContigIdPosBetween(fromorg.getOrganismId(), fromcontig.getFeatureId(), start,  end );
	}
	
	
	

	@Override
	public MultiReferenceConversion convertPosition(
			MultiReferenceConversion fromPos, String toReference,
			String toContig) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MultiReferenceLocus convertLocus(MultiReferenceLocus fromLocus,
			String toReference, String toContig) throws Exception {
		// TODO Auto-generated method stub
		
		
		Set setConv = findVConvertposAny2allrefsByFromOrgIdContigIdPosBetween( fromLocus.getOrganism(),  fromLocus.getContig(), 
				BigDecimal.valueOf(fromLocus.getFmin()), BigDecimal.valueOf(fromLocus.getFmax()) );
		List listConv=new ArrayList();
		listConv.addAll(setConv);

		if(listConv.size()<2) throw new RuntimeException("No reference coordinate conversion at start (" + fromLocus.getFmin() + ") to " + toReference + " found");

		//Collections.sort(listConv, new MultiReferenceToPositionSorter()); 
		
		
		VConvertposNb2allrefs coversionStart= (VConvertposNb2allrefs)listConv.get(0);
		VConvertposNb2allrefs coversionEnd= (VConvertposNb2allrefs)listConv.get(listConv.size()-1);
		
		BigDecimal actualToContigStart=null;
		BigDecimal actualToContigEnd=null;
		BigDecimal toStart=null;
		BigDecimal toEnd=null;
		if(toReference.equals( Organism.REFERENCE_9311)) {
			if(toContig!=null) {
				actualToContigStart = coversionStart.get_311ContigId();
				actualToContigEnd = coversionEnd.get_311ContigId();
			}
			toStart=coversionStart.get_311Position();
			toEnd=coversionEnd.get_311Position();
		}
		else if(toReference.equals( Organism.REFERENCE_IR64)) {
				if(toContig!=null) {
					actualToContigStart = coversionStart.getIr64ContigId();
					actualToContigEnd = coversionEnd.getIr64ContigId();
				}
				toStart=coversionStart.getIr64Position();
				toEnd=coversionEnd.getIr64Position();
		}
		else if(toReference.equals( Organism.REFERENCE_DJ123)) {
			if(toContig!=null) {
				actualToContigStart = coversionStart.getDj123ContigId();
				actualToContigEnd = coversionEnd.getDj123ContigId();
			}
			toStart=coversionStart.getDj123Position();
			toEnd=coversionEnd.getDj123Position();
		}
		else if(toReference.equals( Organism.REFERENCE_KASALATH)) {
			if(toContig!=null) {
				actualToContigStart = coversionStart.getKasalathContigId();
				actualToContigEnd = coversionEnd.getKasalathContigId();
			}
			toStart=coversionStart.getKasalathPosition();
			toEnd=coversionEnd.getKasalathPosition();
		}

		/*
		if(!actualToContigStart.equals(actualToContigEnd )) {
			throw new RuntimeException("Aligned contig at start (" + coversionStart.getToContig() + ") is not the same as aligned contig at end (" + coversionEnd.getToContig() + " )" + " in " + coversionEnd.getToOrganism());
		}
		*/

		scaffolddao = (ScaffoldDAO)AppContext.checkBean(scaffolddao, "ScaffoldDAO");
		String strToContig1 = scaffolddao.getScaffold(actualToContigStart).getName();
		String strToContig2 = scaffolddao.getScaffold(actualToContigEnd).getName();

		
		if(toContig!=null) {
			if(toContig.equals(strToContig1) || toContig.equals(strToContig2)) { }
			else {
				throw new RuntimeException("Aligned contig " + strToContig1 + "," + strToContig2 + " did not matched with specified contig " +  toContig + " in " + toReference);
			}
		}
		

		Long strand = 1L;
		if(toStart.compareTo(toEnd)<0)  strand = -1L;
		
		MultiReferenceLocus toLocus = new MultiReferenceLocusImpl( toReference, strToContig1, toStart.intValue() , toEnd.intValue(), strand.intValue() );
		AppContext.debug("convert locus:" + fromLocus + " to " + toLocus );
		return toLocus;
	}

	@Override
	public VariantStringData convertReferencePositions(
			VariantStringData variantstringdataNPB,
			MultiReferenceLocus fromMultirefLocus,
			MultiReferenceLocus origMultiReferenceLocus, String toContig,
			boolean isOtherRefs) throws Exception {
		// TODO Auto-generated method stub
		
		scaffolddao=(ScaffoldDAO)AppContext.checkBean(scaffolddao,"ScaffoldDAO");
		
		Scaffold fromcontig = null;
		if(fromMultirefLocus!=null)
			fromcontig=scaffolddao.getScaffold( fromMultirefLocus.getContig()  , fromMultirefLocus.getOrganism() );
		else 
			fromcontig=scaffolddao.getScaffold( origMultiReferenceLocus.getContig()  , origMultiReferenceLocus.getOrganism() );
		
//		if(origMultiReferenceLocus!=null)  throw new RuntimeException("origMultiReferenceLocus!=null: " + origMultiReferenceLocus);
//		if(toContig!=null)  throw new RuntimeException("toContig!=null: " + toContig);
//		if(isOtherRefs)  throw new RuntimeException("toContig!=null: " + toContig);
//		
//		
//		
//		orgdao = (OrganismDAO)AppContext.checkBean(orgdao, "OrganismDAO");
//		scaffolddao = (ScaffoldDAO)AppContext.checkBean(scaffolddao, "ScaffoldDAO");
//		
//		Map<BigDecimal, String> mapOrgId2Name=new HashMap();
//		Organism fromorg = orgdao.getMapName2Organism().get( fromMultirefLocus.getOrganism());
//		
//		Iterator<Organism> itOrgs = orgdao.getMapName2Organism().values().iterator();
//		while(itOrgs.hasNext()) {
//			Organism org = itOrgs.next();
//			mapOrgId2Name.put( org.getOrganismId(), org.getName());
//		}
//		
//		Scaffold fromcontig = scaffolddao.getScaffold( fromMultirefLocus.getContig()  , fromMultirefLocus.getOrganism() );
//		
//		
//		Iterator<SnpsAllvarsPos> itPos = variantstringdataNPB.getListPos().iterator();
//		while(itPos.hasNext()) {
//			
//			SnpsAllvarsPos ipos=itPos.next();
//			if(ipos instanceof MultiReferencePosition) {
//				
//			}
//		}
		
		
		
		Map<BigDecimal,String> mapContigId2Name=new HashMap();
		
		Map mapRefPos2ConvertedPos_9311=new HashMap();
		Map mapRefPos2ConvertedPos_IR64=new HashMap();
		Map mapRefPos2ConvertedPos_DJ123=new HashMap();
		Map mapRefPos2ConvertedPos_Kas=new HashMap();
		Map mapRefPos2ConvertedPos_NB=new HashMap();
		
		Iterator<SnpsAllvarsPos> itConv=variantstringdataNPB.getListPos().iterator();
		
		while(itConv.hasNext()) {
			VConvertposAny2allrefs posconv =  (VConvertposAny2allrefs)itConv.next();
			
			BigDecimal refPos=posconv.getFromPosition();
			
			if(posconv.getRice9311ContigId()!=null) {
			String cont_9311 =  mapContigId2Name.get(posconv.getRice9311ContigId());
			if(cont_9311==null) {
				
				cont_9311= scaffolddao.getScaffold( posconv.getRice9311ContigId()).getName();
				if(cont_9311==null) AppContext.debug("scaffolddao.getScaffold( posconv.get_311ContigId())==null: " + posconv.getRice9311ContigId());
				else {
				mapContigId2Name.put(posconv.getRice9311ContigId(), cont_9311);
				}
			}
			MultiReferencePosition conv = new MultiReferencePositionImpl(Organism.REFERENCE_9311, cont_9311, posconv.getRice9311ContigId(), posconv.getRice9311Refcall());
				mapRefPos2ConvertedPos_9311.put( refPos  ,  conv);
			}

			if(posconv.getIr64ContigId()!=null) {
			String cont_IR64 =  mapContigId2Name.get(posconv.getIr64ContigId());
			if(cont_IR64==null) {
				cont_IR64=scaffolddao.getScaffold( posconv.getIr64ContigId()).getName();
				if(cont_IR64==null) AppContext.debug("scaffolddao.getScaffold( posconv.getIr64ContigId())==null: " + posconv.getIr64ContigId());
				else {
				mapContigId2Name.put(posconv.getIr64ContigId(), cont_IR64);
				}
			}
			MultiReferencePosition conv = new MultiReferencePositionImpl(Organism.REFERENCE_IR64, cont_IR64, posconv.getIr64Position(), posconv.getIr64Refcall());
			mapRefPos2ConvertedPos_IR64.put( refPos ,  conv);
			}
			
			if(posconv.getDj123ContigId()!=null) {
			String cont_DJ123 =  mapContigId2Name.get(posconv.getDj123ContigId());
			if(cont_DJ123==null) {
				cont_DJ123=scaffolddao.getScaffold( posconv.getDj123ContigId()).getName();
				if(cont_DJ123==null) AppContext.debug("scaffolddao.getScaffold( posconv.getDj123ContigId())==null: " + posconv.getDj123ContigId());
				else {
				mapContigId2Name.put(posconv.getDj123ContigId(), cont_DJ123);
				}
			}
			MultiReferencePosition conv = new MultiReferencePositionImpl(Organism.REFERENCE_DJ123, cont_DJ123, posconv.getDj123Position(), posconv.getDj123Refcall());
			mapRefPos2ConvertedPos_DJ123.put( refPos ,  conv);
			}
			
			if(posconv.getKasalathContigId()!=null) {
			String cont_Kasalath =  mapContigId2Name.get(posconv.getKasalathContigId());
			if(cont_Kasalath==null) {
				cont_Kasalath=scaffolddao.getScaffold( posconv.getKasalathContigId()).getName();
				if(cont_Kasalath==null) AppContext.debug("scaffolddao.getScaffold( posconv.getKasalathContigId())==null: " + posconv.getKasalathContigId());
				else {
				mapContigId2Name.put(posconv.getKasalathContigId(), cont_Kasalath);
				}
			}
			MultiReferencePosition conv = new MultiReferencePositionImpl(Organism.REFERENCE_KASALATH, cont_Kasalath, posconv.getKasalathPosition(), posconv.getKasalathRefcall());
			mapRefPos2ConvertedPos_Kas.put(  refPos  ,  conv);
			}

			if(posconv.getNbContigId()!=null) {
			String cont_Nb =  mapContigId2Name.get(posconv.getNbContigId());
			if(cont_Nb==null) {
				cont_Nb=scaffolddao.getScaffold( posconv.getNbContigId()).getName();
				if(cont_Nb==null) AppContext.debug("scaffolddao.getScaffold( posconv.getNbContigId())==null: " + posconv.getNbContigId());
				else {
					mapContigId2Name.put(posconv.getNbContigId(), cont_Nb);
				}
			}
			MultiReferencePosition conv = new MultiReferencePositionImpl(Organism.REFERENCE_NIPPONBARE, cont_Nb, posconv.getNbPosition(), posconv.getNbRefcall());
			mapRefPos2ConvertedPos_NB.put(  refPos  ,  conv);
			}
			
			
		}
		
		
		AppContext.debug(mapContigId2Name.toString() + "\n size=" + mapContigId2Name.size());
		
		//variantstringdataNPB.
		
		variantstringdataNPB.addMapOrg2RefPosConverterPos(Organism.REFERENCE_9311, mapRefPos2ConvertedPos_9311, fromcontig.getName());
		variantstringdataNPB.addMapOrg2RefPosConverterPos(Organism.REFERENCE_IR64, mapRefPos2ConvertedPos_IR64, fromcontig.getName());
		variantstringdataNPB.addMapOrg2RefPosConverterPos(Organism.REFERENCE_DJ123, mapRefPos2ConvertedPos_DJ123, fromcontig.getName());
		variantstringdataNPB.addMapOrg2RefPosConverterPos(Organism.REFERENCE_KASALATH, mapRefPos2ConvertedPos_Kas, fromcontig.getName());
		variantstringdataNPB.addMapOrg2RefPosConverterPos(Organism.REFERENCE_NIPPONBARE, mapRefPos2ConvertedPos_NB, fromcontig.getName());
		//variantstringdataNPB.getMapOrg2RefPos2ConvertedPos().remove(fromMultirefLocus.getOrganism());
		
		return variantstringdataNPB;
	}
	
	
	@Override
	public List getSNPs(String chromosome, Integer startPos, Integer endPos,
			BigDecimal type, BigDecimal organism) {
			StringBuffer buff=new StringBuffer();
			return getSNPs(chromosome, startPos, endPos, type,  organism, buff);
	}
	
	@Override
	public List getSNPs(String chromosome, Integer startPos, Integer endPos,
			BigDecimal type, BigDecimal organism, StringBuffer buff) {
		// TODO Auto-generated method stub
		
		scaffolddao=(ScaffoldDAO)AppContext.checkBean(scaffolddao, "ScaffoldDAO");
		Set result = findVConvertposAny2allrefsByFromOrgIdContigIdPosBetween(organism,  scaffolddao.getScaffold(chromosome, organism).getFeatureId(),
				BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos));
		return mergeSamePosDifferentSnpFeatureId(result, buff);
	}
	
	private List mergeSamePosDifferentSnpFeatureId(Set inset, StringBuffer strMsg) {
		
		
		orgdao = (OrganismDAO)AppContext.checkBean(orgdao, "OrganismDAO");
		List list = new ArrayList();
		VConvertposAny2allrefs prevpos=null;
		Iterator<VConvertposAny2allrefs> itPos=inset.iterator();
		int icount=0;
		while( itPos.hasNext() )  {
			VConvertposAny2allrefs vpos = itPos.next();
			boolean keepprev=false;
			
			if(prevpos!=null ) {
				
				if(vpos.getContig().equals(prevpos.getContig()) && 
						vpos.getPosition().equals(prevpos.getPosition()) ) {
					
					AppContext.debug("merging position " + icount+ " [" + vpos.getSnpFeatureId() + " :" + vpos.getFileId() +":" + vpos.getAlleleIndex() +"] " +  vpos.getContig() + "-" + vpos.getPosition() + " to prevpos [" + prevpos.getSnpFeatureId() +":" + prevpos.getFileId() + ": "+ prevpos.getAlleleIndex() +"] " +  prevpos.getContig() + "-" + prevpos.getPosition()); 
					strMsg.append("Merged snp " + vpos.getSnpFeatureId()+"-"+vpos.getFileId() + "-" + vpos.getAlleleIndex() + " to " + prevpos.getSnpFeatureId()+"-"+ prevpos.getFileId() + "-" + prevpos.getAlleleIndex() + ": both alinged with " + orgdao.getOrganismByID(vpos.getOrganism().intValue()).getName() + "-" + vpos.getContig() + "-" + vpos.getPosition() +"\n");
					
					if(prevpos.getDj123ContigId()==null && vpos.getDj123ContigId()!=null ) {
						prevpos.setDj123ContigId( vpos.getDj123ContigId() );
						prevpos.setDj123Position( vpos.getDj123Position() );
						prevpos.setDj123Refcall( vpos.getDj123Refcall() );
						AppContext.debug("dj123 copied to prev");
						keepprev=true;
					}
					if(prevpos.getNbContigId()==null && vpos.getNbContigId()!=null ) {
						prevpos.setNbContigId( vpos.getNbContigId() );
						prevpos.setNbPosition( vpos.getNbPosition() );
						prevpos.setNbRefcall( vpos.getNbRefcall() );
						AppContext.debug("nb copied to prev");
						keepprev=true;
					}
					if(prevpos.getRice9311ContigId()==null && vpos.getRice9311ContigId()!=null ) {
						prevpos.setRice9311ContigId( vpos.getRice9311ContigId() );
						prevpos.setRice9311Position( vpos.getRice9311Position() );
						prevpos.setRice9311Refcall( vpos.getRice9311Refcall() );
						AppContext.debug("9311 copied to prev");
						keepprev=true;
					}
					if(prevpos.getIr64ContigId()==null && vpos.getIr64ContigId()!=null ) {
						prevpos.setIr64ContigId( vpos.getIr64ContigId() );
						prevpos.setIr64Position( vpos.getIr64Position() );
						prevpos.setIr64Refcall( vpos.getIr64Refcall() );
						AppContext.debug("ir64 copied to prev");
						keepprev=true;
					}
					if(prevpos.getKasalathContigId()==null && vpos.getKasalathContigId()!=null ) {
						prevpos.setKasalathContigId( vpos.getKasalathContigId() );
						prevpos.setKasalathPosition( vpos.getKasalathPosition() );
						prevpos.setKasalathRefcall( vpos.getKasalathRefcall() );
						AppContext.debug("kasalath copied to prev");
						keepprev=true;
					}
					
					//prevpos.getAlleleIndex()
					//prevpos.getTypeId()
					
					
					if(keepprev) vpos=prevpos;
					
				} else list.add( prevpos );
			}
			prevpos = vpos;
			icount++;
		}
		list.add(prevpos);
		
		AppContext.debug("merging same pos: orig=" + inset.size() + ", merged=" + list.size() );
		
		return list;
		
	}

	@Override
	public List getSNPsInChromosome(String chr, Collection posset,
			BigDecimal type, BigDecimal organism) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public List getSNPsInChromosome(String chr, Collection posset,
			BigDecimal type, BigDecimal organism, StringBuffer buff) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getSNPs(String chromosome, Integer startPos, Integer endPos, BigDecimal type) {
		return getSNPs( chromosome,  startPos,  endPos,  type, new StringBuffer());
	}
	
	@Override
	public List getSNPs(String chromosome, Integer startPos, Integer endPos, BigDecimal type, StringBuffer buff) {
		// TODO Auto-generated method stub
		scaffolddao=(ScaffoldDAO)AppContext.checkBean(scaffolddao, "ScaffoldDAO");
		Set result = findVConvertposAny2allrefsByFromOrgIdContigIdPosBetween(Organism.REFERENCE_NIPPONBARE_ID,  scaffolddao.getScaffold(chromosome, Organism.REFERENCE_NIPPONBARE_ID).getFeatureId(),
				BigDecimal.valueOf(startPos), BigDecimal.valueOf(endPos));
		return mergeSamePosDifferentSnpFeatureId(result,buff); 
	}

	@Override
	public List getSNPs(String chromosome, Integer startPos, Integer endPos,
			BigDecimal type, int firstRow, int maxRows) {
		// TODO Auto-generated method stub
		return getSNPs(chromosome, startPos, endPos, type);
	}

	@Override
	public List getSNPsInChromosome(String chr, Collection posset, BigDecimal type) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
