package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.Set;

import org.irri.iric.portal.chado.postgres.domain.VLocusNotes;
import org.irri.iric.portal.dao.LocusDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VLocusNotes entities.
 * 
 */
public interface VLocusNotesDAO extends JpaDao<VLocusNotes>, LocusDAO {

	/**
	 * JPQL Query - findVLocusNotesByFeatureId
	 *
	 */
	public VLocusNotes findVLocusNotesByFeatureId(Long featureId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusNotesByFeatureId
	 *
	 */
	public VLocusNotes findVLocusNotesByFeatureId(Long featureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVLocusNotess
	 *
	 */
	public Set<VLocusNotes> findAllVLocusNotess() throws DataAccessException;

	/**
	 * JPQL Query - findAllVLocusNotess
	 *
	 */
	public Set<VLocusNotes> findAllVLocusNotess(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusNotesByContigNameContaining
	 *
	 */
	public Set<VLocusNotes> findVLocusNotesByContigNameContaining(String contigName) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusNotesByContigNameContaining
	 *
	 */
	public Set<VLocusNotes> findVLocusNotesByContigNameContaining(String contigName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusNotesByContigId
	 *
	 */
	public Set<VLocusNotes> findVLocusNotesByContigId(Long contigId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusNotesByContigId
	 *
	 */
	public Set<VLocusNotes> findVLocusNotesByContigId(Long contigId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusNotesByStrand
	 *
	 */
	public Set<VLocusNotes> findVLocusNotesByStrand(Integer strand) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusNotesByStrand
	 *
	 */
	public Set<VLocusNotes> findVLocusNotesByStrand(Integer strand, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusNotesByCommonName
	 *
	 */
	public Set<VLocusNotes> findVLocusNotesByCommonName(String commonName) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusNotesByCommonName
	 *
	 */
	public Set<VLocusNotes> findVLocusNotesByCommonName(String commonName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusNotesByContigName
	 *
	 */
	public Set<VLocusNotes> findVLocusNotesByContigName(String contigName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusNotesByContigName
	 *
	 */
	public Set<VLocusNotes> findVLocusNotesByContigName(String contigName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusNotesByFmax
	 *
	 */
	public Set<VLocusNotes> findVLocusNotesByFmax(Integer fmax) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusNotesByFmax
	 *
	 */
	public Set<VLocusNotes> findVLocusNotesByFmax(Integer fmax, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusNotesByNotesContaining
	 *
	 */
	public Set<VLocusNotes> findVLocusNotesByNotesContaining(String notes) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusNotesByNotesContaining
	 *
	 */
	public Set<VLocusNotes> findVLocusNotesByNotesContaining(String notes, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusNotesByOrganismId
	 *
	 */
	public Set<VLocusNotes> findVLocusNotesByOrganismId(Long organismId) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusNotesByOrganismId
	 *
	 */
	public Set<VLocusNotes> findVLocusNotesByOrganismId(Long organismId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusNotesByPrimaryKey
	 *
	 */
	public VLocusNotes findVLocusNotesByPrimaryKey(Long featureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusNotesByPrimaryKey
	 *
	 */
	public VLocusNotes findVLocusNotesByPrimaryKey(Long featureId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusNotesByFmin
	 *
	 */
	public Set<VLocusNotes> findVLocusNotesByFmin(Integer fmin) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusNotesByFmin
	 *
	 */
	public Set<VLocusNotes> findVLocusNotesByFmin(Integer fmin, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusNotesByNotes
	 *
	 */
	public Set<VLocusNotes> findVLocusNotesByNotes(String notes_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusNotesByNotes
	 *
	 */
	public Set<VLocusNotes> findVLocusNotesByNotes(String notes_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusNotesByCommonNameContaining
	 *
	 */
	public Set<VLocusNotes> findVLocusNotesByCommonNameContaining(String commonName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVLocusNotesByCommonNameContaining
	 *
	 */
	public Set<VLocusNotes> findVLocusNotesByCommonNameContaining(String commonName_1, int startResult, int maxRows) throws DataAccessException;

}