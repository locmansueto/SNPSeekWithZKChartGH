package org.irri.iric.portal.variety.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

//import org.irri.iric.portal.variety.domain.Germplasm;
import org.irri.iric.portal.variety.domain.List3k;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage List3k entities.
 * 
 */
@Repository("List3kDAO")
@Transactional
public class List3kDAOImpl extends AbstractJpaDao<List3k> implements List3kDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { List3k.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Development
	 *
	 */
	@PersistenceContext(unitName = "Development")
	//@Resource(name =  "Development")
	private EntityManager entityManager;

	/**
	 * Instantiates a new List3kDAOImpl
	 *
	 */
	public List3kDAOImpl() {
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
	 * JPQL Query - findList3kByIrisUniqueIdContaining
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByIrisUniqueIdContaining(String irisUniqueId) throws DataAccessException {

		return findList3kByIrisUniqueIdContaining(irisUniqueId, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByIrisUniqueIdContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByIrisUniqueIdContaining(String irisUniqueId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByIrisUniqueIdContaining", startResult, maxRows, irisUniqueId);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByGeneticStockForDnaContaining
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByGeneticStockForDnaContaining(String geneticStockForDna) throws DataAccessException {

		return findList3kByGeneticStockForDnaContaining(geneticStockForDna, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByGeneticStockForDnaContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByGeneticStockForDnaContaining(String geneticStockForDna, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByGeneticStockForDnaContaining", startResult, maxRows, geneticStockForDna);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByDuplication
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByDuplication(String duplication) throws DataAccessException {

		return findList3kByDuplication(duplication, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByDuplication
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByDuplication(String duplication, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByDuplication", startResult, maxRows, duplication);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByLevContaining
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByLevContaining(String lev) throws DataAccessException {

		return findList3kByLevContaining(lev, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByLevContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByLevContaining(String lev, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByLevContaining", startResult, maxRows, lev);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByAltAccessOfGenStockSrc
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByAltAccessOfGenStockSrc(String altAccessOfGenStockSrc) throws DataAccessException {

		return findList3kByAltAccessOfGenStockSrc(altAccessOfGenStockSrc, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByAltAccessOfGenStockSrc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByAltAccessOfGenStockSrc(String altAccessOfGenStockSrc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByAltAccessOfGenStockSrc", startResult, maxRows, altAccessOfGenStockSrc);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByDuplicationContaining
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByDuplicationContaining(String duplication) throws DataAccessException {

		return findList3kByDuplicationContaining(duplication, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByDuplicationContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByDuplicationContaining(String duplication, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByDuplicationContaining", startResult, maxRows, duplication);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByBgiShipment2Containing
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByBgiShipment2Containing(String bgiShipment2) throws DataAccessException {

		return findList3kByBgiShipment2Containing(bgiShipment2, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByBgiShipment2Containing
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByBgiShipment2Containing(String bgiShipment2, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByBgiShipment2Containing", startResult, maxRows, bgiShipment2);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByDesignationDnaSample
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByDesignationDnaSample(String designationDnaSample) throws DataAccessException {

		return findList3kByDesignationDnaSample(designationDnaSample, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByDesignationDnaSample
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByDesignationDnaSample(String designationDnaSample, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByDesignationDnaSample", startResult, maxRows, designationDnaSample);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByTenkSelect200
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByTenkSelect200(String tenkSelect200) throws DataAccessException {

		return findList3kByTenkSelect200(tenkSelect200, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByTenkSelect200
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByTenkSelect200(String tenkSelect200, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByTenkSelect200", startResult, maxRows, tenkSelect200);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByIsSequenced
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByIsSequenced(Boolean isSequenced) throws DataAccessException {

		return findList3kByIsSequenced(isSequenced, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByIsSequenced
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByIsSequenced(Boolean isSequenced, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByIsSequenced", startResult, maxRows, isSequenced);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByOverlapAffyBgi
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByOverlapAffyBgi(String overlapAffyBgi) throws DataAccessException {

		return findList3kByOverlapAffyBgi(overlapAffyBgi, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByOverlapAffyBgi
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByOverlapAffyBgi(String overlapAffyBgi, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByOverlapAffyBgi", startResult, maxRows, overlapAffyBgi);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByBgiShipment2
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByBgiShipment2(String bgiShipment2) throws DataAccessException {

		return findList3kByBgiShipment2(bgiShipment2, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByBgiShipment2
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByBgiShipment2(String bgiShipment2, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByBgiShipment2", startResult, maxRows, bgiShipment2);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByDesignationDnaSampleContaining
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByDesignationDnaSampleContaining(String designationDnaSample) throws DataAccessException {

		return findList3kByDesignationDnaSampleContaining(designationDnaSample, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByDesignationDnaSampleContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByDesignationDnaSampleContaining(String designationDnaSample, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByDesignationDnaSampleContaining", startResult, maxRows, designationDnaSample);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByIsClustered
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByIsClustered(Boolean isClustered) throws DataAccessException {

		return findList3kByIsClustered(isClustered, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByIsClustered
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByIsClustered(Boolean isClustered, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByIsClustered", startResult, maxRows, isClustered);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByBoxPositionCodeContaining
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByBoxPositionCodeContaining(String boxPositionCode) throws DataAccessException {

		return findList3kByBoxPositionCodeContaining(boxPositionCode, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByBoxPositionCodeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByBoxPositionCodeContaining(String boxPositionCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByBoxPositionCodeContaining", startResult, maxRows, boxPositionCode);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByGeneticStockForDna
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByGeneticStockForDna(String geneticStockForDna) throws DataAccessException {

		return findList3kByGeneticStockForDna(geneticStockForDna, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByGeneticStockForDna
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByGeneticStockForDna(String geneticStockForDna, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByGeneticStockForDna", startResult, maxRows, geneticStockForDna);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByEntryCodeContaining
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByEntryCodeContaining(String entryCode) throws DataAccessException {

		return findList3kByEntryCodeContaining(entryCode, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByEntryCodeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByEntryCodeContaining(String entryCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByEntryCodeContaining", startResult, maxRows, entryCode);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByPrimaryKey
	 *
	 */
	@Transactional
	public List3k findList3kByPrimaryKey(String irisUniqueId) throws DataAccessException {

		return findList3kByPrimaryKey(irisUniqueId, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByPrimaryKey
	 *
	 */

	@Transactional
	public List3k findList3kByPrimaryKey(String irisUniqueId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findList3kByPrimaryKey", startResult, maxRows, irisUniqueId);
			return (org.irri.iric.portal.variety.domain.List3k) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findList3kByVarnameOfGenStockSrc
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByVarnameOfGenStockSrc(String varnameOfGenStockSrc) throws DataAccessException {

		return findList3kByVarnameOfGenStockSrc(varnameOfGenStockSrc, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByVarnameOfGenStockSrc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByVarnameOfGenStockSrc(String varnameOfGenStockSrc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByVarnameOfGenStockSrc", startResult, maxRows, varnameOfGenStockSrc);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByLev
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByLev(String lev) throws DataAccessException {

		return findList3kByLev(lev, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByLev
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByLev(String lev, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByLev", startResult, maxRows, lev);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByBgiShipment1
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByBgiShipment1(String bgiShipment1) throws DataAccessException {

		return findList3kByBgiShipment1(bgiShipment1, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByBgiShipment1
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByBgiShipment1(String bgiShipment1, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByBgiShipment1", startResult, maxRows, bgiShipment1);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByCountryOfOriginOfSourceContaining
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByCountryOfOriginOfSourceContaining(String countryOfOriginOfSource) throws DataAccessException {

		return findList3kByCountryOfOriginOfSourceContaining(countryOfOriginOfSource, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByCountryOfOriginOfSourceContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByCountryOfOriginOfSourceContaining(String countryOfOriginOfSource, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByCountryOfOriginOfSourceContaining", startResult, maxRows, countryOfOriginOfSource);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByVarietygroupOfSource
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByVarietygroupOfSource(String varietygroupOfSource) throws DataAccessException {

		return findList3kByVarietygroupOfSource(varietygroupOfSource, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByVarietygroupOfSource
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByVarietygroupOfSource(String varietygroupOfSource, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByVarietygroupOfSource", startResult, maxRows, varietygroupOfSource);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByAccessionOfGenStockSourceContaining
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByAccessionOfGenStockSourceContaining(String accessionOfGenStockSource) throws DataAccessException {

		return findList3kByAccessionOfGenStockSourceContaining(accessionOfGenStockSource, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByAccessionOfGenStockSourceContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByAccessionOfGenStockSourceContaining(String accessionOfGenStockSource, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByAccessionOfGenStockSourceContaining", startResult, maxRows, accessionOfGenStockSource);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kBySequencingContaining
	 *
	 */
	@Transactional
	public Set<List3k> findList3kBySequencingContaining(String sequencing) throws DataAccessException {

		return findList3kBySequencingContaining(sequencing, -1, -1);
	}

	/**
	 * JPQL Query - findList3kBySequencingContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kBySequencingContaining(String sequencing, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kBySequencingContaining", startResult, maxRows, sequencing);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByVarnameOfGenStockSrcContaining
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByVarnameOfGenStockSrcContaining(String varnameOfGenStockSrc) throws DataAccessException {

		return findList3kByVarnameOfGenStockSrcContaining(varnameOfGenStockSrc, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByVarnameOfGenStockSrcContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByVarnameOfGenStockSrcContaining(String varnameOfGenStockSrc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByVarnameOfGenStockSrcContaining", startResult, maxRows, varnameOfGenStockSrc);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByInternalRpocessingSetContaining
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByInternalRpocessingSetContaining(String internalRpocessingSet) throws DataAccessException {

		return findList3kByInternalRpocessingSetContaining(internalRpocessingSet, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByInternalRpocessingSetContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByInternalRpocessingSetContaining(String internalRpocessingSet, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByInternalRpocessingSetContaining", startResult, maxRows, internalRpocessingSet);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByBoxPositionCode
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByBoxPositionCode(String boxPositionCode) throws DataAccessException {

		return findList3kByBoxPositionCode(boxPositionCode, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByBoxPositionCode
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByBoxPositionCode(String boxPositionCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByBoxPositionCode", startResult, maxRows, boxPositionCode);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kBySequencing
	 *
	 */
	@Transactional
	public Set<List3k> findList3kBySequencing(String sequencing) throws DataAccessException {

		return findList3kBySequencing(sequencing, -1, -1);
	}

	/**
	 * JPQL Query - findList3kBySequencing
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kBySequencing(String sequencing, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kBySequencing", startResult, maxRows, sequencing);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByListNameContaining
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByListNameContaining(String listName) throws DataAccessException {

		return findList3kByListNameContaining(listName, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByListNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByListNameContaining(String listName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByListNameContaining", startResult, maxRows, listName);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByFundingSrcContaining
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByFundingSrcContaining(String fundingSrc) throws DataAccessException {

		return findList3kByFundingSrcContaining(fundingSrc, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByFundingSrcContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByFundingSrcContaining(String fundingSrc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByFundingSrcContaining", startResult, maxRows, fundingSrc);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kBySortCode
	 *
	 */
	@Transactional
	public Set<List3k> findList3kBySortCode(Integer sortCode) throws DataAccessException {

		return findList3kBySortCode(sortCode, -1, -1);
	}

	/**
	 * JPQL Query - findList3kBySortCode
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kBySortCode(Integer sortCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kBySortCode", startResult, maxRows, sortCode);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByVarietygroupOfSourceContaining
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByVarietygroupOfSourceContaining(String varietygroupOfSource) throws DataAccessException {

		return findList3kByVarietygroupOfSourceContaining(varietygroupOfSource, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByVarietygroupOfSourceContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByVarietygroupOfSourceContaining(String varietygroupOfSource, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByVarietygroupOfSourceContaining", startResult, maxRows, varietygroupOfSource);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByIrisUniqueId
	 *
	 */
	@Transactional
	public List3k findList3kByIrisUniqueId(String irisUniqueId) throws DataAccessException {

		return findList3kByIrisUniqueId(irisUniqueId, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByIrisUniqueId
	 *
	 */

	@Transactional
	public List3k findList3kByIrisUniqueId(String irisUniqueId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findList3kByIrisUniqueId", startResult, maxRows, irisUniqueId);
			return (org.irri.iric.portal.variety.domain.List3k) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findList3kByGidForDna
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByGidForDna(Integer gidForDna) throws DataAccessException {

		return findList3kByGidForDna(gidForDna, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByGidForDna
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByGidForDna(Integer gidForDna, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByGidForDna", startResult, maxRows, gidForDna);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByListName
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByListName(String listName) throws DataAccessException {

		return findList3kByListName(listName, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByListName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByListName(String listName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByListName", startResult, maxRows, listName);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByBgiShipment1Containing
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByBgiShipment1Containing(String bgiShipment1) throws DataAccessException {

		return findList3kByBgiShipment1Containing(bgiShipment1, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByBgiShipment1Containing
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByBgiShipment1Containing(String bgiShipment1, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByBgiShipment1Containing", startResult, maxRows, bgiShipment1);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByTenkSelect200Containing
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByTenkSelect200Containing(String tenkSelect200) throws DataAccessException {

		return findList3kByTenkSelect200Containing(tenkSelect200, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByTenkSelect200Containing
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByTenkSelect200Containing(String tenkSelect200, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByTenkSelect200Containing", startResult, maxRows, tenkSelect200);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByOrigId
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByOrigId(String origId) throws DataAccessException {

		return findList3kByOrigId(origId, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByOrigId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByOrigId(String origId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByOrigId", startResult, maxRows, origId);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByInternalRpocessingSet
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByInternalRpocessingSet(String internalRpocessingSet) throws DataAccessException {

		return findList3kByInternalRpocessingSet(internalRpocessingSet, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByInternalRpocessingSet
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByInternalRpocessingSet(String internalRpocessingSet, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByInternalRpocessingSet", startResult, maxRows, internalRpocessingSet);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByAltAccessOfGenStockSrcContaining
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByAltAccessOfGenStockSrcContaining(String altAccessOfGenStockSrc) throws DataAccessException {

		return findList3kByAltAccessOfGenStockSrcContaining(altAccessOfGenStockSrc, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByAltAccessOfGenStockSrcContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByAltAccessOfGenStockSrcContaining(String altAccessOfGenStockSrc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByAltAccessOfGenStockSrcContaining", startResult, maxRows, altAccessOfGenStockSrc);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByQa
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByQa(String qa) throws DataAccessException {

		return findList3kByQa(qa, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByQa
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByQa(String qa, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByQa", startResult, maxRows, qa);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByAccessionOfGenStockSource
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByAccessionOfGenStockSource(String accessionOfGenStockSource) throws DataAccessException {

		return findList3kByAccessionOfGenStockSource(accessionOfGenStockSource, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByAccessionOfGenStockSource
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByAccessionOfGenStockSource(String accessionOfGenStockSource, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByAccessionOfGenStockSource", startResult, maxRows, accessionOfGenStockSource);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByFundingSrc
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByFundingSrc(String fundingSrc) throws DataAccessException {

		return findList3kByFundingSrc(fundingSrc, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByFundingSrc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByFundingSrc(String fundingSrc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByFundingSrc", startResult, maxRows, fundingSrc);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllList3ks
	 *
	 */
	@Transactional
	public Set<List3k> findAllList3ks() throws DataAccessException {

		return findAllList3ks(-1, -1);
	}

	/**
	 * JPQL Query - findAllList3ks
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findAllList3ks(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllList3ks", startResult, maxRows);
		return new LinkedHashSet<List3k>(query.getResultList());
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findAllList3kByExample(List3k germplasm) throws DataAccessException {
		//Query query = createNamedQuery("findAllList3ks", startResult, maxRows);
		//return new LinkedHashSet<List3k>(query.getResultList());
		
		  CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		  CriteriaQuery q = cb.createQuery(List3k.class);
		  Root<List3k> c = q.from(List3k.class);
		  q.select(c);

			  if(germplasm.getCountryOfOriginOfSource()!=null && germplasm.getVarietygroupOfSource()!=null)	
			  {
				  Expression<String> country = cb.literal(germplasm.getCountryOfOriginOfSource() );
				  Expression<String> subpop = cb.parameter(String.class);
				  q.where(  cb.and( cb.equal( cb.upper( c.<String>get("countryOfOriginOfSource")  ), germplasm.getCountryOfOriginOfSource().toUpperCase() ),
						  			cb.equal( cb.upper( c.<String>get("varietygroupOfSource")), cb.literal(germplasm.getVarietygroupOfSource().toUpperCase() ) ) ));
			  }
			  else if (germplasm.getCountryOfOriginOfSource()!=null)
			  {
					q.where(   cb.equal( cb.upper( c.<String>get("countryOfOriginOfSource")),  cb.literal(germplasm.getCountryOfOriginOfSource().toUpperCase() )) );
			  }
			  else if (germplasm.getVarietygroupOfSource()!=null)
					q.where(   cb.equal( cb.upper( c.<String>get("varietygroupOfSource")),  cb.literal(germplasm.getVarietygroupOfSource().toUpperCase() )  )  );
		 
			  Query testQuery = entityManager.createQuery(q);
			 
			  return new java.util.LinkedHashSet<List3k>( testQuery.getResultList() );
	
	}
	
	

	@Override
	public Set getGermplasmByExample(List3k germplasm) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * JPQL Query - findList3kByOverlapAffyBgiContaining
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByOverlapAffyBgiContaining(String overlapAffyBgi) throws DataAccessException {

		return findList3kByOverlapAffyBgiContaining(overlapAffyBgi, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByOverlapAffyBgiContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByOverlapAffyBgiContaining(String overlapAffyBgi, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByOverlapAffyBgiContaining", startResult, maxRows, overlapAffyBgi);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByEntryCode
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByEntryCode(String entryCode) throws DataAccessException {

		return findList3kByEntryCode(entryCode, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByEntryCode
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByEntryCode(String entryCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByEntryCode", startResult, maxRows, entryCode);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByQaContaining
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByQaContaining(String qa) throws DataAccessException {

		return findList3kByQaContaining(qa, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByQaContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByQaContaining(String qa, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByQaContaining", startResult, maxRows, qa);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByIsDataAvailable
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByIsDataAvailable(Boolean isDataAvailable) throws DataAccessException {

		return findList3kByIsDataAvailable(isDataAvailable, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByIsDataAvailable
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByIsDataAvailable(Boolean isDataAvailable, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByIsDataAvailable", startResult, maxRows, isDataAvailable);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByOrigIdContaining
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByOrigIdContaining(String origId) throws DataAccessException {

		return findList3kByOrigIdContaining(origId, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByOrigIdContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByOrigIdContaining(String origId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByOrigIdContaining", startResult, maxRows, origId);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * JPQL Query - findList3kByCountryOfOriginOfSource
	 *
	 */
	@Transactional
	public Set<List3k> findList3kByCountryOfOriginOfSource(String countryOfOriginOfSource) throws DataAccessException {

		return findList3kByCountryOfOriginOfSource(countryOfOriginOfSource, -1, -1);
	}

	/**
	 * JPQL Query - findList3kByCountryOfOriginOfSource
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<List3k> findList3kByCountryOfOriginOfSource(String countryOfOriginOfSource, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findList3kByCountryOfOriginOfSource", startResult, maxRows, countryOfOriginOfSource);
		return new LinkedHashSet<List3k>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(List3k entity) {
		return true;
	}
}
