package org.irri.iric.portal.chado.oracle.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.portal.chado.oracle.domain.VSnpGenotypeHdra;
import org.irri.iric.portal.dao.SnpsStringDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VSnpGenotypeHdra entities.
 * 
 */
public interface VSnpGenotypeHdraDAO extends JpaDao<VSnpGenotypeHdra>, SnpsStringDAO {

	/**
	 * JPQL Query - findVSnpGenotypeHdraByHdraStockId
	 *
	 */
	public VSnpGenotypeHdra findVSnpGenotypeHdraByHdraStockId(BigDecimal hdraStockId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeHdraByHdraStockId
	 *
	 */
	public VSnpGenotypeHdra findVSnpGenotypeHdraByHdraStockId(BigDecimal hdraStockId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeHdraByRefcallContaining
	 *
	 */
	public Set<VSnpGenotypeHdra> findVSnpGenotypeHdraByRefcallContaining(String refcall) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeHdraByRefcallContaining
	 *
	 */
	public Set<VSnpGenotypeHdra> findVSnpGenotypeHdraByRefcallContaining(String refcall, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpGenotypeHdras
	 *
	 */
	public Set<VSnpGenotypeHdra> findAllVSnpGenotypeHdras() throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpGenotypeHdras
	 *
	 */
	public Set<VSnpGenotypeHdra> findAllVSnpGenotypeHdras(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeHdraByAllele2Containing
	 *
	 */
	public Set<VSnpGenotypeHdra> findVSnpGenotypeHdraByAllele2Containing(String allele2) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeHdraByAllele2Containing
	 *
	 */
	public Set<VSnpGenotypeHdra> findVSnpGenotypeHdraByAllele2Containing(String allele2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeHdraByMismatch
	 *
	 */
	public Set<VSnpGenotypeHdra> findVSnpGenotypeHdraByMismatch(BigDecimal mismatch) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeHdraByMismatch
	 *
	 */
	public Set<VSnpGenotypeHdra> findVSnpGenotypeHdraByMismatch(BigDecimal mismatch, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeHdraByAllele1Containing
	 *
	 */
	public Set<VSnpGenotypeHdra> findVSnpGenotypeHdraByAllele1Containing(String allele1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeHdraByAllele1Containing
	 *
	 */
	public Set<VSnpGenotypeHdra> findVSnpGenotypeHdraByAllele1Containing(String allele1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeHdraByAllele2
	 *
	 */
	public Set<VSnpGenotypeHdra> findVSnpGenotypeHdraByAllele2(String allele2_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeHdraByAllele2
	 *
	 */
	public Set<VSnpGenotypeHdra> findVSnpGenotypeHdraByAllele2(String allele2_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeHdraByRefcall
	 *
	 */
	public Set<VSnpGenotypeHdra> findVSnpGenotypeHdraByRefcall(String refcall_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeHdraByRefcall
	 *
	 */
	public Set<VSnpGenotypeHdra> findVSnpGenotypeHdraByRefcall(String refcall_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeHdraByPrimaryKey
	 *
	 */
	public VSnpGenotypeHdra findVSnpGenotypeHdraByPrimaryKey(BigDecimal hdraStockId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeHdraByPrimaryKey
	 *
	 */
	public VSnpGenotypeHdra findVSnpGenotypeHdraByPrimaryKey(BigDecimal hdraStockId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeHdraByAllele1
	 *
	 */
	public Set<VSnpGenotypeHdra> findVSnpGenotypeHdraByAllele1(String allele1_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpGenotypeHdraByAllele1
	 *
	 */
	public Set<VSnpGenotypeHdra> findVSnpGenotypeHdraByAllele1(String allele1_1, int startResult, int maxRows) throws DataAccessException;

}