package org.irri.iric.portal.chado.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VIndelAllvars;
import org.irri.iric.portal.dao.IndelsAllvarsDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VIndelAllvars entities.
 * 
 */
public interface VIndelAllvarsDAO extends JpaDao<VIndelAllvars>, IndelsAllvarsDAO {

	/**
	 * JPQL Query - findVIndelAllvarsByVar
	 *
	 */
	public Set<VIndelAllvars> findVIndelAllvarsByVar(java.math.BigDecimal var) throws DataAccessException;

	/**
	 * JPQL Query - findVIndelAllvarsByVar
	 *
	 */
	public Set<VIndelAllvars> findVIndelAllvarsByVar(BigDecimal var, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIndelAllvarsByIndelCallId
	 *
	 */
	public VIndelAllvars findVIndelAllvarsByIndelCallId(Integer indelCallId) throws DataAccessException;

	/**
	 * JPQL Query - findVIndelAllvarsByIndelCallId
	 *
	 */
	public VIndelAllvars findVIndelAllvarsByIndelCallId(Integer indelCallId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIndelAllvarsByPos
	 *
	 */
	public Set<VIndelAllvars> findVIndelAllvarsByPos(Integer pos) throws DataAccessException;

	/**
	 * JPQL Query - findVIndelAllvarsByPos
	 *
	 */
	public Set<VIndelAllvars> findVIndelAllvarsByPos(Integer pos, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIndelAllvarsByAllele1Id
	 *
	 */
	public Set<VIndelAllvars> findVIndelAllvarsByAllele1Id(Integer allele1Id) throws DataAccessException;

	/**
	 * JPQL Query - findVIndelAllvarsByAllele1Id
	 *
	 */
	public Set<VIndelAllvars> findVIndelAllvarsByAllele1Id(Integer allele1Id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVIndelAllvarss
	 *
	 */
	public Set<VIndelAllvars> findAllVIndelAllvarss() throws DataAccessException;

	/**
	 * JPQL Query - findAllVIndelAllvarss
	 *
	 */
	public Set<VIndelAllvars> findAllVIndelAllvarss(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIndelAllvarsByPrimaryKey
	 *
	 */
	public VIndelAllvars findVIndelAllvarsByPrimaryKey(Integer indelCallId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVIndelAllvarsByPrimaryKey
	 *
	 */
	public VIndelAllvars findVIndelAllvarsByPrimaryKey(Integer indelCallId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIndelAllvarsByPartitionId
	 *
	 */
	public Set<VIndelAllvars> findVIndelAllvarsByPartitionId(java.math.BigDecimal partitionId) throws DataAccessException;

	/**
	 * JPQL Query - findVIndelAllvarsByPartitionId
	 *
	 */
	public Set<VIndelAllvars> findVIndelAllvarsByPartitionId(BigDecimal partitionId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVIndelAllvarsByAllele2Id
	 *
	 */
	public Set<VIndelAllvars> findVIndelAllvarsByAllele2Id(Integer allele2Id) throws DataAccessException;

	/**
	 * JPQL Query - findVIndelAllvarsByAllele2Id
	 *
	 */
	public Set<VIndelAllvars> findVIndelAllvarsByAllele2Id(Integer allele2Id, int startResult, int maxRows) throws DataAccessException;

}