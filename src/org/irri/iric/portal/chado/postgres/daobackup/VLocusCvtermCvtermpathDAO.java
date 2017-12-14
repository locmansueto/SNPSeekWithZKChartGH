package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.Set;

import org.irri.iric.portal.chado.postgres.domain.VLocusCvtermCvtermpath;
import org.irri.iric.portal.dao.LocusCvTermPathDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VLocusCvtermCvtermpath entities.
 * 
 */
public interface VLocusCvtermCvtermpathDAO extends
		JpaDao<VLocusCvtermCvtermpath>, LocusCvTermPathDAO {

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByFmin
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByFmin(Integer fmin) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByFmin
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByFmin(Integer fmin, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByNotesContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByNotesContaining(String notes) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByNotesContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByNotesContaining(String notes, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjCvterm
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjCvterm(String subjCvterm) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjCvterm
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjCvterm(String subjCvterm, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjCvtermContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjCvtermContaining(String subjCvterm_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjCvtermContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjCvtermContaining(String subjCvterm_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByStrand
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByStrand(Integer strand) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByStrand
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByStrand(Integer strand, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCommonNameContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCommonNameContaining(String commonName) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCommonNameContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCommonNameContaining(String commonName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByContigName
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByContigName(String contigName) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByContigName
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByContigName(String contigName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByPrimaryKey
	 *
	 */
	public VLocusCvtermCvtermpath findVLocusCvtermCvtermpathByPrimaryKey(Integer featureId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByPrimaryKey
	 *
	 */
	public VLocusCvtermCvtermpath findVLocusCvtermCvtermpathByPrimaryKey(Integer featureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVLocusCvtermCvtermpaths
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findAllVLocusCvtermCvtermpaths() throws DataAccessException;

	/**
	 * JPQL Query - findAllVLocusCvtermCvtermpaths
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findAllVLocusCvtermCvtermpaths(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCommonName
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCommonName(String commonName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCommonName
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCommonName(String commonName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjCvterm
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjCvterm(String objCvterm) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjCvterm
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjCvterm(String objCvterm, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjAcc
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjAcc(String subjAcc) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjAcc
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjAcc(String subjAcc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByFmax
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByFmax(Integer fmax) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByFmax
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByFmax(Integer fmax, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjAccContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjAccContaining(String subjAcc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathBySubjAccContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathBySubjAccContaining(String subjAcc_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByFeatureId
	 *
	 */
	public VLocusCvtermCvtermpath findVLocusCvtermCvtermpathByFeatureId(Integer featureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByFeatureId
	 *
	 */
	public VLocusCvtermCvtermpath findVLocusCvtermCvtermpathByFeatureId(Integer featureId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByNotes
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByNotes(String notes_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByNotes
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByNotes(String notes_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByName
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByName
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByName(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByPathdistance
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByPathdistance(Integer pathdistance) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByPathdistance
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByPathdistance(Integer pathdistance, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByNameContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByNameContaining(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByNameContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByNameContaining(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjCvtermContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjCvtermContaining(String objCvterm_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjCvtermContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjCvtermContaining(String objCvterm_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByContigNameContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByContigNameContaining(String contigName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByContigNameContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByContigNameContaining(String contigName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjAccContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjAccContaining(String objAcc) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjAccContaining
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjAccContaining(String objAcc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCvtermId
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCvtermId(Integer cvtermId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByCvtermId
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByCvtermId(Integer cvtermId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjAcc
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjAcc(String objAcc_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByObjAcc
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByObjAcc(String objAcc_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByContigId
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByContigId(Integer contigId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByContigId
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByContigId(Integer contigId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByOrganismId
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByOrganismId(Integer organismId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusCvtermCvtermpathByOrganismId
	 *
	 */
	public Set<VLocusCvtermCvtermpath> findVLocusCvtermCvtermpathByOrganismId(Integer organismId, int startResult, int maxRows) throws DataAccessException;

}