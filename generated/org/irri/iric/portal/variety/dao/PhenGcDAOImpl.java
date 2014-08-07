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

import org.irri.iric.portal.variety.domain.PhenGc;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage PhenGc entities.
 * 
 */
@Repository("PhenGcDAO")
@Transactional
public class PhenGcDAOImpl extends AbstractJpaDao<PhenGc> implements PhenGcDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { PhenGc.class }));

	/**
	 * EntityManager injected by Spring for persistence unit Development
	 *
	 */
	@PersistenceContext(unitName = "Development")
	//@Resource(name =  "Development")
	private EntityManager entityManager;

	/**
	 * Instantiates a new PhenGcDAOImpl
	 *
	 */
	public PhenGcDAOImpl() {
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
	 * JPQL Query - findPhenGcByEntno
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByEntno(Integer entno) throws DataAccessException {

		return findPhenGcByEntno(entno, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByEntno
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByEntno(Integer entno, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByEntno", startResult, maxRows, entno);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByMpsChk
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByMpsChk(Integer mpsChk) throws DataAccessException {

		return findPhenGcByMpsChk(mpsChk, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByMpsChk
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByMpsChk(Integer mpsChk, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByMpsChk", startResult, maxRows, mpsChk);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByName2Containing
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByName2Containing(String name2) throws DataAccessException {

		return findPhenGcByName2Containing(name2, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByName2Containing
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByName2Containing(String name2, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByName2Containing", startResult, maxRows, name2);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByBarcodeContaining
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByBarcodeContaining(String barcode) throws DataAccessException {

		return findPhenGcByBarcodeContaining(barcode, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByBarcodeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByBarcodeContaining(String barcode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByBarcodeContaining", startResult, maxRows, barcode);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByName2
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByName2(String name2) throws DataAccessException {

		return findPhenGcByName2(name2, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByName2
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByName2(String name2, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByName2", startResult, maxRows, name2);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByAvgLength
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByAvgLength(Integer avgLength) throws DataAccessException {

		return findPhenGcByAvgLength(avgLength, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByAvgLength
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByAvgLength(Integer avgLength, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByAvgLength", startResult, maxRows, avgLength);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByWaxy
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByWaxy(Integer waxy) throws DataAccessException {

		return findPhenGcByWaxy(waxy, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByWaxy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByWaxy(Integer waxy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByWaxy", startResult, maxRows, waxy);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByRemarks
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByRemarks(String remarks) throws DataAccessException {

		return findPhenGcByRemarks(remarks, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByRemarks
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByRemarks(String remarks, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByRemarks", startResult, maxRows, remarks);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByShape
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByShape(Integer shape) throws DataAccessException {

		return findPhenGcByShape(shape, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByShape
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByShape(Integer shape, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByShape", startResult, maxRows, shape);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByChkGt75
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByChkGt75(Integer chkGt75) throws DataAccessException {

		return findPhenGcByChkGt75(chkGt75, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByChkGt75
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByChkGt75(Integer chkGt75, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByChkGt75", startResult, maxRows, chkGt75);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByAc
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByAc(Integer ac) throws DataAccessException {

		return findPhenGcByAc(ac, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByAc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByAc(Integer ac, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByAc", startResult, maxRows, ac);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByChk2550
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByChk2550(Integer chk2550) throws DataAccessException {

		return findPhenGcByChk2550(chk2550, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByChk2550
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByChk2550(Integer chk2550, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByChk2550", startResult, maxRows, chk2550);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByChk5075
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByChk5075(Integer chk5075) throws DataAccessException {

		return findPhenGcByChk5075(chk5075, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByChk5075
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByChk5075(Integer chk5075, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByChk5075", startResult, maxRows, chk5075);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByGc
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByGc(Integer gc) throws DataAccessException {

		return findPhenGcByGc(gc, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByGc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByGc(Integer gc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByGc", startResult, maxRows, gc);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByChk010
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByChk010(Integer chk010) throws DataAccessException {

		return findPhenGcByChk010(chk010, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByChk010
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByChk010(Integer chk010, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByChk010", startResult, maxRows, chk010);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByName1Containing
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByName1Containing(String name1) throws DataAccessException {

		return findPhenGcByName1Containing(name1, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByName1Containing
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByName1Containing(String name1, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByName1Containing", startResult, maxRows, name1);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByGtDsc
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByGtDsc(Integer gtDsc) throws DataAccessException {

		return findPhenGcByGtDsc(gtDsc, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByGtDsc
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByGtDsc(Integer gtDsc, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByGtDsc", startResult, maxRows, gtDsc);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByBarcode
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByBarcode(String barcode) throws DataAccessException {

		return findPhenGcByBarcode(barcode, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByBarcode
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByBarcode(String barcode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByBarcode", startResult, maxRows, barcode);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByRemarksContaining
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByRemarksContaining(String remarks) throws DataAccessException {

		return findPhenGcByRemarksContaining(remarks, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByRemarksContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByRemarksContaining(String remarks, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByRemarksContaining", startResult, maxRows, remarks);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllPhenGcs
	 *
	 */
	@Transactional
	public Set<PhenGc> findAllPhenGcs() throws DataAccessException {

		return findAllPhenGcs(-1, -1);
	}

	/**
	 * JPQL Query - findAllPhenGcs
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findAllPhenGcs(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllPhenGcs", startResult, maxRows);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByName1
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByName1(String name1) throws DataAccessException {

		return findPhenGcByName1(name1, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByName1
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByName1(String name1, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByName1", startResult, maxRows, name1);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByTotalGrains
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByTotalGrains(Integer totalGrains) throws DataAccessException {

		return findPhenGcByTotalGrains(totalGrains, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByTotalGrains
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByTotalGrains(Integer totalGrains, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByTotalGrains", startResult, maxRows, totalGrains);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByStdLength
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByStdLength(Integer stdLength) throws DataAccessException {

		return findPhenGcByStdLength(stdLength, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByStdLength
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByStdLength(Integer stdLength, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByStdLength", startResult, maxRows, stdLength);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByDesignationContaining
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByDesignationContaining(String designation) throws DataAccessException {

		return findPhenGcByDesignationContaining(designation, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByDesignationContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByDesignationContaining(String designation, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByDesignationContaining", startResult, maxRows, designation);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByAvgWidth
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByAvgWidth(Integer avgWidth) throws DataAccessException {

		return findPhenGcByAvgWidth(avgWidth, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByAvgWidth
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByAvgWidth(Integer avgWidth, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByAvgWidth", startResult, maxRows, avgWidth);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByGid
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByGid(Integer gid) throws DataAccessException {

		return findPhenGcByGid(gid, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByGid
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByGid(Integer gid, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByGid", startResult, maxRows, gid);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByPrimaryKey
	 *
	 */
	@Transactional
	public PhenGc findPhenGcByPrimaryKey(Integer entno, Integer gid) throws DataAccessException {

		return findPhenGcByPrimaryKey(entno, gid, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByPrimaryKey
	 *
	 */

	@Transactional
	public PhenGc findPhenGcByPrimaryKey(Integer entno, Integer gid, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findPhenGcByPrimaryKey", startResult, maxRows, entno, gid);
			return (org.irri.iric.portal.variety.domain.PhenGc) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findPhenGcByChalkiness
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByChalkiness(Integer chalkiness) throws DataAccessException {

		return findPhenGcByChalkiness(chalkiness, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByChalkiness
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByChalkiness(Integer chalkiness, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByChalkiness", startResult, maxRows, chalkiness);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByMps
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByMps(Integer mps) throws DataAccessException {

		return findPhenGcByMps(mps, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByMps
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByMps(Integer mps, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByMps", startResult, maxRows, mps);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByStdWidth
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByStdWidth(Integer stdWidth) throws DataAccessException {

		return findPhenGcByStdWidth(stdWidth, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByStdWidth
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByStdWidth(Integer stdWidth, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByStdWidth", startResult, maxRows, stdWidth);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByDesignation
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByDesignation(String designation) throws DataAccessException {

		return findPhenGcByDesignation(designation, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByDesignation
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByDesignation(String designation, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByDesignation", startResult, maxRows, designation);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * JPQL Query - findPhenGcByChk1025
	 *
	 */
	@Transactional
	public Set<PhenGc> findPhenGcByChk1025(Integer chk1025) throws DataAccessException {

		return findPhenGcByChk1025(chk1025, -1, -1);
	}

	/**
	 * JPQL Query - findPhenGcByChk1025
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<PhenGc> findPhenGcByChk1025(Integer chk1025, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findPhenGcByChk1025", startResult, maxRows, chk1025);
		return new LinkedHashSet<PhenGc>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(PhenGc entity) {
		return true;
	}
}
