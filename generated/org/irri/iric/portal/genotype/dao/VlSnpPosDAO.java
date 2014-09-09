package org.irri.iric.portal.genotype.dao;

import java.util.Set;

import org.irri.iric.portal.dao.SnpsAllvarsPosDAO;
import org.irri.iric.portal.genotype.domain.VlSnpPos;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VlSnpPos entities.
 * 
 */
public interface VlSnpPosDAO extends JpaDao<VlSnpPos>, SnpsAllvarsPosDAO {

	/**
	 * JPQL Query - findVlSnpPosByPos
	 *
	 */
	public Set<VlSnpPos> findVlSnpPosByPos(Integer pos) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpPosByPos
	 *
	 */
	public Set<VlSnpPos> findVlSnpPosByPos(Integer pos, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpPosByRefnuc
	 *
	 */
	public Set<VlSnpPos> findVlSnpPosByRefnuc(String refnuc) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpPosByRefnuc
	 *
	 */
	public Set<VlSnpPos> findVlSnpPosByRefnuc(String refnuc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVlSnpPoss
	 *
	 */
	public Set<VlSnpPos> findAllVlSnpPoss() throws DataAccessException;

	/**
	 * JPQL Query - findAllVlSnpPoss
	 *
	 */
	public Set<VlSnpPos> findAllVlSnpPoss(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpPosByRefnucContaining
	 *
	 */
	public Set<VlSnpPos> findVlSnpPosByRefnucContaining(String refnuc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpPosByRefnucContaining
	 *
	 */
	public Set<VlSnpPos> findVlSnpPosByRefnucContaining(String refnuc_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpPosByPrimaryKey
	 *
	 */
	public VlSnpPos findVlSnpPosByPrimaryKey(Integer chr, Integer pos_1) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpPosByPrimaryKey
	 *
	 */
	public VlSnpPos findVlSnpPosByPrimaryKey(Integer chr, Integer pos_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpPosByChr
	 *
	 */
	public Set<VlSnpPos> findVlSnpPosByChr(Integer chr_1) throws DataAccessException;

	/**
	 * JPQL Query - findVlSnpPosByChr
	 *
	 */
	public Set<VlSnpPos> findVlSnpPosByChr(Integer chr_1, int startResult, int maxRows) throws DataAccessException;

}