package org.irri.iric.portal.chado.oracle.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.irri.iric.portal.chado.oracle.domain.VSnpRefposHdra;
import org.irri.iric.portal.dao.SnpsAllvarsPosDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VSnpRefposHdra entities.
 * 
 */
public interface VSnpRefposHdraDAO extends JpaDao<VSnpRefposHdra> , SnpsAllvarsPosDAO {

	/**
	 * JPQL Query - findVSnpRefposHdraByPrimaryKey
	 *
	 */
	public VSnpRefposHdra findVSnpRefposHdraByPrimaryKey(BigDecimal snpFeatureId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposHdraByPrimaryKey
	 *
	 */
	public VSnpRefposHdra findVSnpRefposHdraByPrimaryKey(BigDecimal snpFeatureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposHdraByRefcall
	 *
	 */
	public Set<VSnpRefposHdra> findVSnpRefposHdraByRefcall(String refcall) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposHdraByRefcall
	 *
	 */
	public Set<VSnpRefposHdra> findVSnpRefposHdraByRefcall(String refcall, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposHdraBySnpFeatureId
	 *
	 */
	public VSnpRefposHdra findVSnpRefposHdraBySnpFeatureId(BigDecimal snpFeatureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposHdraBySnpFeatureId
	 *
	 */
	public VSnpRefposHdra findVSnpRefposHdraBySnpFeatureId(BigDecimal snpFeatureId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposHdraByChromosome
	 *
	 */
	public Set<VSnpRefposHdra> findVSnpRefposHdraByChromosome(BigDecimal chromosome) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposHdraByChromosome
	 *
	 */
	public Set<VSnpRefposHdra> findVSnpRefposHdraByChromosome(BigDecimal chromosome, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposHdraByTypeId
	 *
	 */
	public Set<VSnpRefposHdra> findVSnpRefposHdraByTypeId(BigDecimal typeId) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposHdraByTypeId
	 *
	 */
	public Set<VSnpRefposHdra> findVSnpRefposHdraByTypeId(BigDecimal typeId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpRefposHdras
	 *
	 */
	public Set<VSnpRefposHdra> findAllVSnpRefposHdras() throws DataAccessException;

	/**
	 * JPQL Query - findAllVSnpRefposHdras
	 *
	 */
	public Set<VSnpRefposHdra> findAllVSnpRefposHdras(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposHdraByPosition
	 *
	 */
	public Set<VSnpRefposHdra> findVSnpRefposHdraByPosition(BigDecimal position) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposHdraByPosition
	 *
	 */
	public Set<VSnpRefposHdra> findVSnpRefposHdraByPosition(BigDecimal position, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposHdraByRefcallContaining
	 *
	 */
	public Set<VSnpRefposHdra> findVSnpRefposHdraByRefcallContaining(String refcall_1) throws DataAccessException;

	/**
	 * JPQL Query - findVSnpRefposHdraByRefcallContaining
	 *
	 */
	public Set<VSnpRefposHdra> findVSnpRefposHdraByRefcallContaining(String refcall_1, int startResult, int maxRows) throws DataAccessException;

}