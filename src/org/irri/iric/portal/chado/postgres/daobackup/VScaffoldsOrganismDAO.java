package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.Set;

import org.irri.iric.portal.chado.postgres.domain.VScaffoldsOrganism;
import org.irri.iric.portal.dao.ScaffoldDAO;
import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage VScaffoldsOrganism entities.
 * 
 */
public interface VScaffoldsOrganismDAO extends JpaDao<VScaffoldsOrganism>, ScaffoldDAO {

	/**
	 * JPQL Query - findVScaffoldsOrganismByCommonName
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByCommonName(String commonName) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByCommonName
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByCommonName(String commonName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllVScaffoldsOrganisms
	 *
	 */
	public Set<VScaffoldsOrganism> findAllVScaffoldsOrganisms() throws DataAccessException;

	/**
	 * JPQL Query - findAllVScaffoldsOrganisms
	 *
	 */
	public Set<VScaffoldsOrganism> findAllVScaffoldsOrganisms(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByNameContaining
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByNameContaining
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByNameContaining(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByName
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByName
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByName(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByFeatureId
	 *
	 */
	public VScaffoldsOrganism findVScaffoldsOrganismByFeatureId(Long featureId) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByFeatureId
	 *
	 */
	public VScaffoldsOrganism findVScaffoldsOrganismByFeatureId(Long featureId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByCommonNameContaining
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByCommonNameContaining(String commonName_1) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByCommonNameContaining
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByCommonNameContaining(String commonName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByFtype
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByFtype(String ftype) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByFtype
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByFtype(String ftype, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismBySeqlen
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismBySeqlen(Long seqlen) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismBySeqlen
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismBySeqlen(Long seqlen, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByPrimaryKey
	 *
	 */
	public VScaffoldsOrganism findVScaffoldsOrganismByPrimaryKey(Long featureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByPrimaryKey
	 *
	 */
	public VScaffoldsOrganism findVScaffoldsOrganismByPrimaryKey(Long featureId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByFtypeContaining
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByFtypeContaining(String ftype_1) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByFtypeContaining
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByFtypeContaining(String ftype_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByOrganismId
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByOrganismId(Long organismId) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByOrganismId
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByOrganismId(Long organismId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByUniquenameContaining
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByUniquenameContaining(String uniquename) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByUniquenameContaining
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByUniquenameContaining(String uniquename, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByUniquename
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByUniquename(String uniquename_1) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByUniquename
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByUniquename(String uniquename_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByTypeId
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByTypeId(Long typeId) throws DataAccessException;

	/**
	 * JPQL Query - findVScaffoldsOrganismByTypeId
	 *
	 */
	public Set<VScaffoldsOrganism> findVScaffoldsOrganismByTypeId(Long typeId, int startResult, int maxRows) throws DataAccessException;

}