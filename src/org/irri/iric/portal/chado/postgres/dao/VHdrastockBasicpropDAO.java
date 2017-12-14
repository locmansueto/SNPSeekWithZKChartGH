package org.irri.iric.portal.chado.oracle.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.portal.chado.oracle.domain.VHdrastockBasicprop;
import org.irri.iric.portal.dao.VarietyDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VHdrastockBasicprop entities.
 * 
 */
public interface VHdrastockBasicpropDAO extends JpaDao<VHdrastockBasicprop> , VarietyDAO {

	/**
	 * JPQL Query - findVHdrastockBasicpropByOrganismId
	 *
	 */
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropByOrganismId(java.math.BigDecimal organismId) throws DataAccessException;

	/**
	 * JPQL Query - findVHdrastockBasicpropByOrganismId
	 *
	 */
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropByOrganismId(BigDecimal organismId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVHdrastockBasicpropByFsSubpop
	 *
	 */
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropByFsSubpop(String fsSubpop) throws DataAccessException;

	/**
	 * JPQL Query - findVHdrastockBasicpropByFsSubpop
	 *
	 */
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropByFsSubpop(String fsSubpop, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVHdrastockBasicprops
	 *
	 */
	public Set<VHdrastockBasicprop> findAllVHdrastockBasicprops() throws DataAccessException;

	/**
	 * JPQL Query - findAllVHdrastockBasicprops
	 *
	 */
	public Set<VHdrastockBasicprop> findAllVHdrastockBasicprops(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVHdrastockBasicpropByNameContaining
	 *
	 */
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVHdrastockBasicpropByNameContaining
	 *
	 */
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropByNameContaining(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVHdrastockBasicpropByHdraStockId
	 *
	 */
	public VHdrastockBasicprop findVHdrastockBasicpropByHdraStockId(BigDecimal hdraStockId) throws DataAccessException;

	/**
	 * JPQL Query - findVHdrastockBasicpropByHdraStockId
	 *
	 */
	public VHdrastockBasicprop findVHdrastockBasicpropByHdraStockId(BigDecimal hdraStockId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVHdrastockBasicpropByAccessionIdContaining
	 *
	 */
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropByAccessionIdContaining(String accessionId) throws DataAccessException;

	/**
	 * JPQL Query - findVHdrastockBasicpropByAccessionIdContaining
	 *
	 */
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropByAccessionIdContaining(String accessionId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVHdrastockBasicpropByName
	 *
	 */
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVHdrastockBasicpropByName
	 *
	 */
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropByName(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVHdrastockBasicpropBySampleSetContaining
	 *
	 */
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropBySampleSetContaining(String sampleSet) throws DataAccessException;

	/**
	 * JPQL Query - findVHdrastockBasicpropBySampleSetContaining
	 *
	 */
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropBySampleSetContaining(String sampleSet, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVHdrastockBasicpropByAccessionId
	 *
	 */
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropByAccessionId(String accessionId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVHdrastockBasicpropByAccessionId
	 *
	 */
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropByAccessionId(String accessionId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVHdrastockBasicpropBySampleSet
	 *
	 */
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropBySampleSet(String sampleSet_1) throws DataAccessException;

	/**
	 * JPQL Query - findVHdrastockBasicpropBySampleSet
	 *
	 */
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropBySampleSet(String sampleSet_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVHdrastockBasicpropByPrimaryKey
	 *
	 */
	public VHdrastockBasicprop findVHdrastockBasicpropByPrimaryKey(BigDecimal hdraStockId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVHdrastockBasicpropByPrimaryKey
	 *
	 */
	public VHdrastockBasicprop findVHdrastockBasicpropByPrimaryKey(BigDecimal hdraStockId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVHdrastockBasicpropByFsSubpopContaining
	 *
	 */
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropByFsSubpopContaining(String fsSubpop_1) throws DataAccessException;

	/**
	 * JPQL Query - findVHdrastockBasicpropByFsSubpopContaining
	 *
	 */
	public Set<VHdrastockBasicprop> findVHdrastockBasicpropByFsSubpopContaining(String fsSubpop_1, int startResult, int maxRows) throws DataAccessException;

}