package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.Set;

import org.irri.iric.portal.chado.postgres.domain.VLocusCvterm;
import org.irri.iric.portal.dao.LocusCvTermDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VLocusCvterm entities.
 * 
 */
public interface VLocusCvtermDAO extends JpaDao<VLocusCvterm>, LocusCvTermDAO {

	/**
	 * JPQL Query - findVLocusCvtermByContigName
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByContigName(String contigName) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByContigName
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByContigName(String contigName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByName
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByName
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByName(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermBySubjCvtermContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermBySubjCvtermContaining(String subjCvterm) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermBySubjCvtermContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermBySubjCvtermContaining(String subjCvterm, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermBySubjAccContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermBySubjAccContaining(String subjAcc) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermBySubjAccContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermBySubjAccContaining(String subjAcc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByCvNameContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByCvNameContaining(String cvName) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByCvNameContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByCvNameContaining(String cvName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByObjCvtermContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByObjCvtermContaining(String objCvterm) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByObjCvtermContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByObjCvtermContaining(String objCvterm, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByStrand
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByStrand(Integer strand) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByStrand
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByStrand(Integer strand, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByObjCvterm
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByObjCvterm(String objCvterm_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByObjCvterm
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByObjCvterm(String objCvterm_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByNameContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByNameContaining(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByNameContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByNameContaining(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByCommonName
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByCommonName(String commonName) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByCommonName
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByCommonName(String commonName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermBySubjCvterm
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermBySubjCvterm(String subjCvterm_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermBySubjCvterm
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermBySubjCvterm(String subjCvterm_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByFeatureId
	 *
	 */
	public VLocusCvterm findVLocusCvtermByFeatureId(Integer featureId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByFeatureId
	 *
	 */
	public VLocusCvterm findVLocusCvtermByFeatureId(Integer featureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByPathdistance
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByPathdistance(Integer pathdistance) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByPathdistance
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByPathdistance(Integer pathdistance, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByNotesContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByNotesContaining(String notes) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByNotesContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByNotesContaining(String notes, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByContigId
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByContigId(Integer contigId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByContigId
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByContigId(Integer contigId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByPrimaryKey
	 *
	 */
	public VLocusCvterm findVLocusCvtermByPrimaryKey(Integer featureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByPrimaryKey
	 *
	 */
	public VLocusCvterm findVLocusCvtermByPrimaryKey(Integer featureId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByNotes
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByNotes(String notes_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByNotes
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByNotes(String notes_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByObjAcc
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByObjAcc(String objAcc) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByObjAcc
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByObjAcc(String objAcc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVLocusCvterms
	 *
	 */
	public Set<VLocusCvterm> findAllVLocusCvterms() throws DataAccessException;

	/**
	 * JPQL Query - findAllVLocusCvterms
	 *
	 */
	public Set<VLocusCvterm> findAllVLocusCvterms(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByObjAccContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByObjAccContaining(String objAcc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByObjAccContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByObjAccContaining(String objAcc_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByCommonNameContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByCommonNameContaining(String commonName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByCommonNameContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByCommonNameContaining(String commonName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByFmin
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByFmin(Integer fmin) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByFmin
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByFmin(Integer fmin, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByContigNameContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByContigNameContaining(String contigName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByContigNameContaining
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByContigNameContaining(String contigName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByCvName
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByCvName(String cvName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByCvName
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByCvName(String cvName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByCvtermId
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByCvtermId(Integer cvtermId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByCvtermId
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByCvtermId(Integer cvtermId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByOrganismId
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByOrganismId(Integer organismId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByOrganismId
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByOrganismId(Integer organismId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermBySubjAcc
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermBySubjAcc(String subjAcc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermBySubjAcc
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermBySubjAcc(String subjAcc_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByCvId
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByCvId(Integer cvId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByCvId
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByCvId(Integer cvId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByFmax
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByFmax(Integer fmax) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermByFmax
	 *
	 */
	public Set<VLocusCvterm> findVLocusCvtermByFmax(Integer fmax, int startResult, int maxRows) throws DataAccessException;

}