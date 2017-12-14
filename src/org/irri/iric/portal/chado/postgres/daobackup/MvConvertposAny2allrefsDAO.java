package org.irri.iric.portal.chado.postgres.daobackup;

import java.util.Set;

import org.irri.iric.portal.chado.postgres.domain.MvConvertposAny2allrefs;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage MvConvertposAny2allrefs entities.
 * 
 */
public interface MvConvertposAny2allrefsDAO extends
		JpaDao<MvConvertposAny2allrefs> {

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByRice9311ContigNameContaining
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByRice9311ContigNameContaining(String rice9311ContigName) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByRice9311ContigNameContaining
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByRice9311ContigNameContaining(String rice9311ContigName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByKasalathContigName
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByKasalathContigName(String kasalathContigName) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByKasalathContigName
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByKasalathContigName(String kasalathContigName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByFromRefcallContaining
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByFromRefcallContaining(String fromRefcall) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByFromRefcallContaining
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByFromRefcallContaining(String fromRefcall, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByNbPosition
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByNbPosition(Integer nbPosition) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByNbPosition
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByNbPosition(Integer nbPosition, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByRice9311Refcall
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByRice9311Refcall(String rice9311Refcall) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByRice9311Refcall
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByRice9311Refcall(String rice9311Refcall, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByNbContigId
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByNbContigId(Integer nbContigId) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByNbContigId
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByNbContigId(Integer nbContigId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByKasalathContigNameContaining
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByKasalathContigNameContaining(String kasalathContigName_1) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByKasalathContigNameContaining
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByKasalathContigNameContaining(String kasalathContigName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByDj123RefcallContaining
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByDj123RefcallContaining(String dj123Refcall) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByDj123RefcallContaining
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByDj123RefcallContaining(String dj123Refcall, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByKasalathAlignCount
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByKasalathAlignCount(Integer kasalathAlignCount) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByKasalathAlignCount
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByKasalathAlignCount(Integer kasalathAlignCount, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByNbRefcallContaining
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByNbRefcallContaining(String nbRefcall) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByNbRefcallContaining
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByNbRefcallContaining(String nbRefcall, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByRice9311ContigName
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByRice9311ContigName(String rice9311ContigName_1) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByRice9311ContigName
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByRice9311ContigName(String rice9311ContigName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByKasalathPosition
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByKasalathPosition(Integer kasalathPosition) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByKasalathPosition
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByKasalathPosition(Integer kasalathPosition, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByFromContigId
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByFromContigId(Integer fromContigId) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByFromContigId
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByFromContigId(Integer fromContigId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByIr64Refcall
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByIr64Refcall(String ir64Refcall) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByIr64Refcall
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByIr64Refcall(String ir64Refcall, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByDj123ContigId
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByDj123ContigId(Integer dj123ContigId) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByDj123ContigId
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByDj123ContigId(Integer dj123ContigId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByNbContigName
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByNbContigName(String nbContigName) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByNbContigName
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByNbContigName(String nbContigName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByIr64Position
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByIr64Position(Integer ir64Position) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByIr64Position
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByIr64Position(Integer ir64Position, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByTypeId
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByTypeId(Integer typeId) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByTypeId
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByTypeId(Integer typeId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByRice9311AlignCount
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByRice9311AlignCount(Integer rice9311AlignCount) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByRice9311AlignCount
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByRice9311AlignCount(Integer rice9311AlignCount, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByIr64ContigId
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByIr64ContigId(Integer ir64ContigId) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByIr64ContigId
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByIr64ContigId(Integer ir64ContigId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByFromRefcall
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByFromRefcall(String fromRefcall_1) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByFromRefcall
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByFromRefcall(String fromRefcall_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByPrimaryKey
	 *
	 */
	public MvConvertposAny2allrefs findMvConvertposAny2allrefsByPrimaryKey(Integer snpFeatureId, Integer fromOrganismId) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByPrimaryKey
	 *
	 */
	public MvConvertposAny2allrefs findMvConvertposAny2allrefsByPrimaryKey(Integer snpFeatureId, Integer fromOrganismId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByDj123Refcall
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByDj123Refcall(String dj123Refcall_1) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByDj123Refcall
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByDj123Refcall(String dj123Refcall_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByIr64ContigNameContaining
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByIr64ContigNameContaining(String ir64ContigName) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByIr64ContigNameContaining
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByIr64ContigNameContaining(String ir64ContigName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByKasalathContigId
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByKasalathContigId(Integer kasalathContigId) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByKasalathContigId
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByKasalathContigId(Integer kasalathContigId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByIr64ContigName
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByIr64ContigName(String ir64ContigName_1) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByIr64ContigName
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByIr64ContigName(String ir64ContigName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByDj123ContigNameContaining
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByDj123ContigNameContaining(String dj123ContigName) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByDj123ContigNameContaining
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByDj123ContigNameContaining(String dj123ContigName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsBySnpFeatureId
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsBySnpFeatureId(Integer snpFeatureId_1) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsBySnpFeatureId
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsBySnpFeatureId(Integer snpFeatureId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByNbContigNameContaining
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByNbContigNameContaining(String nbContigName_1) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByNbContigNameContaining
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByNbContigNameContaining(String nbContigName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByNbRefcall
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByNbRefcall(String nbRefcall_1) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByNbRefcall
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByNbRefcall(String nbRefcall_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllMvConvertposAny2allrefss
	 *
	 */
	public Set<MvConvertposAny2allrefs> findAllMvConvertposAny2allrefss() throws DataAccessException;

	/**
	 * JPQL Query - findAllMvConvertposAny2allrefss
	 *
	 */
	public Set<MvConvertposAny2allrefs> findAllMvConvertposAny2allrefss(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByDj123ContigName
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByDj123ContigName(String dj123ContigName_1) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByDj123ContigName
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByDj123ContigName(String dj123ContigName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByDj123AlignCount
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByDj123AlignCount(Integer dj123AlignCount) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByDj123AlignCount
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByDj123AlignCount(Integer dj123AlignCount, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByRice9311Position
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByRice9311Position(Integer rice9311Position) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByRice9311Position
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByRice9311Position(Integer rice9311Position, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByFromPosition
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByFromPosition(Integer fromPosition) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByFromPosition
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByFromPosition(Integer fromPosition, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByIr64RefcallContaining
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByIr64RefcallContaining(String ir64Refcall_1) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByIr64RefcallContaining
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByIr64RefcallContaining(String ir64Refcall_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByFromOrganismId
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByFromOrganismId(Integer fromOrganismId_1) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByFromOrganismId
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByFromOrganismId(Integer fromOrganismId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByNbAlignCount
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByNbAlignCount(Integer nbAlignCount) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByNbAlignCount
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByNbAlignCount(Integer nbAlignCount, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByKasalathRefcall
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByKasalathRefcall(String kasalathRefcall) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByKasalathRefcall
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByKasalathRefcall(String kasalathRefcall, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByAlleleIndex
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByAlleleIndex(Integer alleleIndex) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByAlleleIndex
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByAlleleIndex(Integer alleleIndex, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByKasalathRefcallContaining
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByKasalathRefcallContaining(String kasalathRefcall_1) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByKasalathRefcallContaining
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByKasalathRefcallContaining(String kasalathRefcall_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByDj123Position
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByDj123Position(Integer dj123Position) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByDj123Position
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByDj123Position(Integer dj123Position, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByIr64AlignCount
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByIr64AlignCount(Integer ir64AlignCount) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByIr64AlignCount
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByIr64AlignCount(Integer ir64AlignCount, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByRice9311RefcallContaining
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByRice9311RefcallContaining(String rice9311Refcall_1) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByRice9311RefcallContaining
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByRice9311RefcallContaining(String rice9311Refcall_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByRice9311ContigId
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByRice9311ContigId(Integer rice9311ContigId) throws DataAccessException;

	/**
	 * JPQL Query - findMvConvertposAny2allrefsByRice9311ContigId
	 *
	 */
	public Set<MvConvertposAny2allrefs> findMvConvertposAny2allrefsByRice9311ContigId(Integer rice9311ContigId, int startResult, int maxRows) throws DataAccessException;

}