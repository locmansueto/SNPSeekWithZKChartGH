package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.Set;

import org.irri.iric.portal.chado.postgres.domain.VGoCvtermpath;
import org.irri.iric.portal.dao.CvTermPathDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VGoCvtermpath entities.
 * 
 */
public interface VGoCvtermpathDAO extends JpaDao<VGoCvtermpath>  , CvTermPathDAO {

	/**
	 * JPQL Query - findVGoCvtermpathByPrimaryKey
	 *
	 */
	public VGoCvtermpath findVGoCvtermpathByPrimaryKey(Long cvtermId) throws DataAccessException;

	/**
	 * JPQL Query - findVGoCvtermpathByPrimaryKey
	 *
	 */
	public VGoCvtermpath findVGoCvtermpathByPrimaryKey(Long cvtermId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGoCvtermpathByPathdistance
	 *
	 */
	public Set<VGoCvtermpath> findVGoCvtermpathByPathdistance(Integer pathdistance) throws DataAccessException;

	/**
	 * JPQL Query - findVGoCvtermpathByPathdistance
	 *
	 */
	public Set<VGoCvtermpath> findVGoCvtermpathByPathdistance(Integer pathdistance, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGoCvtermpathByObjAccContaining
	 *
	 */
	public Set<VGoCvtermpath> findVGoCvtermpathByObjAccContaining(String objAcc) throws DataAccessException;

	/**
	 * JPQL Query - findVGoCvtermpathByObjAccContaining
	 *
	 */
	public Set<VGoCvtermpath> findVGoCvtermpathByObjAccContaining(String objAcc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGoCvtermpathByCvtermId
	 *
	 */
	public VGoCvtermpath findVGoCvtermpathByCvtermId(Long cvtermId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGoCvtermpathByCvtermId
	 *
	 */
	public VGoCvtermpath findVGoCvtermpathByCvtermId(Long cvtermId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGoCvtermpathBySubjCvterm
	 *
	 */
	public Set<VGoCvtermpath> findVGoCvtermpathBySubjCvterm(String subjCvterm) throws DataAccessException;

	/**
	 * JPQL Query - findVGoCvtermpathBySubjCvterm
	 *
	 */
	public Set<VGoCvtermpath> findVGoCvtermpathBySubjCvterm(String subjCvterm, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGoCvtermpathBySubjAcc
	 *
	 */
	public Set<VGoCvtermpath> findVGoCvtermpathBySubjAcc(String subjAcc) throws DataAccessException;

	/**
	 * JPQL Query - findVGoCvtermpathBySubjAcc
	 *
	 */
	public Set<VGoCvtermpath> findVGoCvtermpathBySubjAcc(String subjAcc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGoCvtermpathByObjCvterm
	 *
	 */
	public Set<VGoCvtermpath> findVGoCvtermpathByObjCvterm(String objCvterm) throws DataAccessException;

	/**
	 * JPQL Query - findVGoCvtermpathByObjCvterm
	 *
	 */
	public Set<VGoCvtermpath> findVGoCvtermpathByObjCvterm(String objCvterm, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGoCvtermpathByCvName
	 *
	 */
	public Set<VGoCvtermpath> findVGoCvtermpathByCvName(String cvName) throws DataAccessException;

	/**
	 * JPQL Query - findVGoCvtermpathByCvName
	 *
	 */
	public Set<VGoCvtermpath> findVGoCvtermpathByCvName(String cvName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGoCvtermpathByObjAcc
	 *
	 */
	public Set<VGoCvtermpath> findVGoCvtermpathByObjAcc(String objAcc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGoCvtermpathByObjAcc
	 *
	 */
	public Set<VGoCvtermpath> findVGoCvtermpathByObjAcc(String objAcc_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGoCvtermpathBySubjAccContaining
	 *
	 */
	public Set<VGoCvtermpath> findVGoCvtermpathBySubjAccContaining(String subjAcc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGoCvtermpathBySubjAccContaining
	 *
	 */
	public Set<VGoCvtermpath> findVGoCvtermpathBySubjAccContaining(String subjAcc_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVGoCvtermpaths
	 *
	 */
	public Set<VGoCvtermpath> findAllVGoCvtermpaths() throws DataAccessException;

	/**
	 * JPQL Query - findAllVGoCvtermpaths
	 *
	 */
	public Set<VGoCvtermpath> findAllVGoCvtermpaths(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGoCvtermpathByObjCvtermContaining
	 *
	 */
	public Set<VGoCvtermpath> findVGoCvtermpathByObjCvtermContaining(String objCvterm_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGoCvtermpathByObjCvtermContaining
	 *
	 */
	public Set<VGoCvtermpath> findVGoCvtermpathByObjCvtermContaining(String objCvterm_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGoCvtermpathByCvNameContaining
	 *
	 */
	public Set<VGoCvtermpath> findVGoCvtermpathByCvNameContaining(String cvName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGoCvtermpathByCvNameContaining
	 *
	 */
	public Set<VGoCvtermpath> findVGoCvtermpathByCvNameContaining(String cvName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVGoCvtermpathBySubjCvtermContaining
	 *
	 */
	public Set<VGoCvtermpath> findVGoCvtermpathBySubjCvtermContaining(String subjCvterm_1) throws DataAccessException;

	/**
	 * JPQL Query - findVGoCvtermpathBySubjCvtermContaining
	 *
	 */
	public Set<VGoCvtermpath> findVGoCvtermpathBySubjCvtermContaining(String subjCvterm_1, int startResult, int maxRows) throws DataAccessException;

}