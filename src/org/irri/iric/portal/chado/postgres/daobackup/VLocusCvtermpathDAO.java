package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.Set;

import org.irri.iric.portal.chado.postgres.domain.VLocusCvtermpath;
import org.irri.iric.portal.dao.LocusCvTermPathDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VLocusCvtermpath entities.
 * 
 */
public interface VLocusCvtermpathDAO extends JpaDao<VLocusCvtermpath>, LocusCvTermPathDAO {

	/**
	 * JPQL Query - findVLocusCvtermpathByOrganismId
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByOrganismId(Integer organismId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByOrganismId
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByOrganismId(Integer organismId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByName
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByName
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByName(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathBySubjCvterm
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathBySubjCvterm(String subjCvterm) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathBySubjCvterm
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathBySubjCvterm(String subjCvterm, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByContigNameContaining
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByContigNameContaining(String contigName) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByContigNameContaining
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByContigNameContaining(String contigName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByObjAccContaining
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByObjAccContaining(String objAcc) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByObjAccContaining
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByObjAccContaining(String objAcc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByNotesContaining
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByNotesContaining(String notes) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByNotesContaining
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByNotesContaining(String notes, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByStrand
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByStrand(Integer strand) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByStrand
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByStrand(Integer strand, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByCvNameContaining
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByCvNameContaining(String cvName) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByCvNameContaining
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByCvNameContaining(String cvName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByFmin
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByFmin(Integer fmin) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByFmin
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByFmin(Integer fmin, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByCvId
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByCvId(Integer cvId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByCvId
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByCvId(Integer cvId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByFeatureId
	 *
	 */
	public VLocusCvtermpath findVLocusCvtermpathByFeatureId(Integer featureId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByFeatureId
	 *
	 */
	public VLocusCvtermpath findVLocusCvtermpathByFeatureId(Integer featureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByNameContaining
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByNameContaining(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByNameContaining
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByNameContaining(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVLocusCvtermpaths
	 *
	 */
	public Set<VLocusCvtermpath> findAllVLocusCvtermpaths() throws DataAccessException;

	/**
	 * JPQL Query - findAllVLocusCvtermpaths
	 *
	 */
	public Set<VLocusCvtermpath> findAllVLocusCvtermpaths(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByFmax
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByFmax(Integer fmax) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByFmax
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByFmax(Integer fmax, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathBySubjAcc
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathBySubjAcc(String subjAcc) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathBySubjAcc
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathBySubjAcc(String subjAcc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByNotes
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByNotes(String notes_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByNotes
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByNotes(String notes_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByContigName
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByContigName(String contigName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByContigName
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByContigName(String contigName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByObjAcc
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByObjAcc(String objAcc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByObjAcc
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByObjAcc(String objAcc_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByCommonNameContaining
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByCommonNameContaining(String commonName) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByCommonNameContaining
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByCommonNameContaining(String commonName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathBySubjCvtermContaining
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathBySubjCvtermContaining(String subjCvterm_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathBySubjCvtermContaining
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathBySubjCvtermContaining(String subjCvterm_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByPathdistance
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByPathdistance(Integer pathdistance) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByPathdistance
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByPathdistance(Integer pathdistance, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathBySubjAccContaining
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathBySubjAccContaining(String subjAcc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathBySubjAccContaining
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathBySubjAccContaining(String subjAcc_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByObjCvtermContaining
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByObjCvtermContaining(String objCvterm) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByObjCvtermContaining
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByObjCvtermContaining(String objCvterm, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByPrimaryKey
	 *
	 */
	public VLocusCvtermpath findVLocusCvtermpathByPrimaryKey(Integer featureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByPrimaryKey
	 *
	 */
	public VLocusCvtermpath findVLocusCvtermpathByPrimaryKey(Integer featureId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByCvtermId
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByCvtermId(Integer cvtermId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByCvtermId
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByCvtermId(Integer cvtermId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByCvName
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByCvName(String cvName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByCvName
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByCvName(String cvName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByContigId
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByContigId(Integer contigId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByContigId
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByContigId(Integer contigId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByCommonName
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByCommonName(String commonName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByCommonName
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByCommonName(String commonName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByObjCvterm
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByObjCvterm(String objCvterm_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermpathByObjCvterm
	 *
	 */
	public Set<VLocusCvtermpath> findVLocusCvtermpathByObjCvterm(String objCvterm_1, int startResult, int maxRows) throws DataAccessException;

}