package org.irri.iric.portal.variety.dao;

import java.util.Set;

import org.irri.iric.portal.variety.domain.PhenGc;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage PhenGc entities.
 * 
 */
public interface PhenGcDAO extends JpaDao<PhenGc> {

	/**
	 * JPQL Query - findPhenGcByEntno
	 *
	 */
	public Set<PhenGc> findPhenGcByEntno(Integer entno) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByEntno
	 *
	 */
	public Set<PhenGc> findPhenGcByEntno(Integer entno, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByMpsChk
	 *
	 */
	public Set<PhenGc> findPhenGcByMpsChk(Integer mpsChk) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByMpsChk
	 *
	 */
	public Set<PhenGc> findPhenGcByMpsChk(Integer mpsChk, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByName2Containing
	 *
	 */
	public Set<PhenGc> findPhenGcByName2Containing(String name2) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByName2Containing
	 *
	 */
	public Set<PhenGc> findPhenGcByName2Containing(String name2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByBarcodeContaining
	 *
	 */
	public Set<PhenGc> findPhenGcByBarcodeContaining(String barcode) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByBarcodeContaining
	 *
	 */
	public Set<PhenGc> findPhenGcByBarcodeContaining(String barcode, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByName2
	 *
	 */
	public Set<PhenGc> findPhenGcByName2(String name2_1) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByName2
	 *
	 */
	public Set<PhenGc> findPhenGcByName2(String name2_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByAvgLength
	 *
	 */
	public Set<PhenGc> findPhenGcByAvgLength(Integer avgLength) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByAvgLength
	 *
	 */
	public Set<PhenGc> findPhenGcByAvgLength(Integer avgLength, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByWaxy
	 *
	 */
	public Set<PhenGc> findPhenGcByWaxy(Integer waxy) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByWaxy
	 *
	 */
	public Set<PhenGc> findPhenGcByWaxy(Integer waxy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByRemarks
	 *
	 */
	public Set<PhenGc> findPhenGcByRemarks(String remarks) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByRemarks
	 *
	 */
	public Set<PhenGc> findPhenGcByRemarks(String remarks, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByShape
	 *
	 */
	public Set<PhenGc> findPhenGcByShape(Integer shape) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByShape
	 *
	 */
	public Set<PhenGc> findPhenGcByShape(Integer shape, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByChkGt75
	 *
	 */
	public Set<PhenGc> findPhenGcByChkGt75(Integer chkGt75) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByChkGt75
	 *
	 */
	public Set<PhenGc> findPhenGcByChkGt75(Integer chkGt75, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByAc
	 *
	 */
	public Set<PhenGc> findPhenGcByAc(Integer ac) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByAc
	 *
	 */
	public Set<PhenGc> findPhenGcByAc(Integer ac, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByChk2550
	 *
	 */
	public Set<PhenGc> findPhenGcByChk2550(Integer chk2550) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByChk2550
	 *
	 */
	public Set<PhenGc> findPhenGcByChk2550(Integer chk2550, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByChk5075
	 *
	 */
	public Set<PhenGc> findPhenGcByChk5075(Integer chk5075) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByChk5075
	 *
	 */
	public Set<PhenGc> findPhenGcByChk5075(Integer chk5075, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByGc
	 *
	 */
	public Set<PhenGc> findPhenGcByGc(Integer gc) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByGc
	 *
	 */
	public Set<PhenGc> findPhenGcByGc(Integer gc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByChk010
	 *
	 */
	public Set<PhenGc> findPhenGcByChk010(Integer chk010) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByChk010
	 *
	 */
	public Set<PhenGc> findPhenGcByChk010(Integer chk010, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByName1Containing
	 *
	 */
	public Set<PhenGc> findPhenGcByName1Containing(String name1) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByName1Containing
	 *
	 */
	public Set<PhenGc> findPhenGcByName1Containing(String name1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByGtDsc
	 *
	 */
	public Set<PhenGc> findPhenGcByGtDsc(Integer gtDsc) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByGtDsc
	 *
	 */
	public Set<PhenGc> findPhenGcByGtDsc(Integer gtDsc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByBarcode
	 *
	 */
	public Set<PhenGc> findPhenGcByBarcode(String barcode_1) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByBarcode
	 *
	 */
	public Set<PhenGc> findPhenGcByBarcode(String barcode_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByRemarksContaining
	 *
	 */
	public Set<PhenGc> findPhenGcByRemarksContaining(String remarks_1) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByRemarksContaining
	 *
	 */
	public Set<PhenGc> findPhenGcByRemarksContaining(String remarks_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllPhenGcs
	 *
	 */
	public Set<PhenGc> findAllPhenGcs() throws DataAccessException;

	/**
	 * JPQL Query - findAllPhenGcs
	 *
	 */
	public Set<PhenGc> findAllPhenGcs(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByName1
	 *
	 */
	public Set<PhenGc> findPhenGcByName1(String name1_1) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByName1
	 *
	 */
	public Set<PhenGc> findPhenGcByName1(String name1_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByTotalGrains
	 *
	 */
	public Set<PhenGc> findPhenGcByTotalGrains(Integer totalGrains) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByTotalGrains
	 *
	 */
	public Set<PhenGc> findPhenGcByTotalGrains(Integer totalGrains, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByStdLength
	 *
	 */
	public Set<PhenGc> findPhenGcByStdLength(Integer stdLength) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByStdLength
	 *
	 */
	public Set<PhenGc> findPhenGcByStdLength(Integer stdLength, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByDesignationContaining
	 *
	 */
	public Set<PhenGc> findPhenGcByDesignationContaining(String designation) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByDesignationContaining
	 *
	 */
	public Set<PhenGc> findPhenGcByDesignationContaining(String designation, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByAvgWidth
	 *
	 */
	public Set<PhenGc> findPhenGcByAvgWidth(Integer avgWidth) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByAvgWidth
	 *
	 */
	public Set<PhenGc> findPhenGcByAvgWidth(Integer avgWidth, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByGid
	 *
	 */
	public Set<PhenGc> findPhenGcByGid(Integer gid) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByGid
	 *
	 */
	public Set<PhenGc> findPhenGcByGid(Integer gid, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByPrimaryKey
	 *
	 */
	public PhenGc findPhenGcByPrimaryKey(Integer entno_1, Integer gid_1) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByPrimaryKey
	 *
	 */
	public PhenGc findPhenGcByPrimaryKey(Integer entno_1, Integer gid_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByChalkiness
	 *
	 */
	public Set<PhenGc> findPhenGcByChalkiness(Integer chalkiness) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByChalkiness
	 *
	 */
	public Set<PhenGc> findPhenGcByChalkiness(Integer chalkiness, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByMps
	 *
	 */
	public Set<PhenGc> findPhenGcByMps(Integer mps) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByMps
	 *
	 */
	public Set<PhenGc> findPhenGcByMps(Integer mps, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByStdWidth
	 *
	 */
	public Set<PhenGc> findPhenGcByStdWidth(Integer stdWidth) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByStdWidth
	 *
	 */
	public Set<PhenGc> findPhenGcByStdWidth(Integer stdWidth, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByDesignation
	 *
	 */
	public Set<PhenGc> findPhenGcByDesignation(String designation_1) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByDesignation
	 *
	 */
	public Set<PhenGc> findPhenGcByDesignation(String designation_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByChk1025
	 *
	 */
	public Set<PhenGc> findPhenGcByChk1025(Integer chk1025) throws DataAccessException;

	/**
	 * JPQL Query - findPhenGcByChk1025
	 *
	 */
	public Set<PhenGc> findPhenGcByChk1025(Integer chk1025, int startResult, int maxRows) throws DataAccessException;

}