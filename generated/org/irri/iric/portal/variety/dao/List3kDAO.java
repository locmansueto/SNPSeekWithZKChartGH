package org.irri.iric.portal.variety.dao;

import java.util.Set;

import org.irri.iric.portal.variety.domain.List3k;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage List3k entities.
 * 
 */
public interface List3kDAO extends JpaDao<List3k> {

	
	public Set getGermplasmByExample(List3k germplasm);
	
	/**
	 * JPQL Query - findList3kByIrisUniqueIdContaining
	 *
	 */
	public Set<List3k> findList3kByIrisUniqueIdContaining(String irisUniqueId) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByIrisUniqueIdContaining
	 *
	 */
	public Set<List3k> findList3kByIrisUniqueIdContaining(String irisUniqueId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByGeneticStockForDnaContaining
	 *
	 */
	public Set<List3k> findList3kByGeneticStockForDnaContaining(String geneticStockForDna) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByGeneticStockForDnaContaining
	 *
	 */
	public Set<List3k> findList3kByGeneticStockForDnaContaining(String geneticStockForDna, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByDuplication
	 *
	 */
	public Set<List3k> findList3kByDuplication(String duplication) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByDuplication
	 *
	 */
	public Set<List3k> findList3kByDuplication(String duplication, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByLevContaining
	 *
	 */
	public Set<List3k> findList3kByLevContaining(String lev) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByLevContaining
	 *
	 */
	public Set<List3k> findList3kByLevContaining(String lev, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByAltAccessOfGenStockSrc
	 *
	 */
	public Set<List3k> findList3kByAltAccessOfGenStockSrc(String altAccessOfGenStockSrc) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByAltAccessOfGenStockSrc
	 *
	 */
	public Set<List3k> findList3kByAltAccessOfGenStockSrc(String altAccessOfGenStockSrc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByDuplicationContaining
	 *
	 */
	public Set<List3k> findList3kByDuplicationContaining(String duplication_1) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByDuplicationContaining
	 *
	 */
	public Set<List3k> findList3kByDuplicationContaining(String duplication_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByBgiShipment2Containing
	 *
	 */
	public Set<List3k> findList3kByBgiShipment2Containing(String bgiShipment2) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByBgiShipment2Containing
	 *
	 */
	public Set<List3k> findList3kByBgiShipment2Containing(String bgiShipment2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByDesignationDnaSample
	 *
	 */
	public Set<List3k> findList3kByDesignationDnaSample(String designationDnaSample) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByDesignationDnaSample
	 *
	 */
	public Set<List3k> findList3kByDesignationDnaSample(String designationDnaSample, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByTenkSelect200
	 *
	 */
	public Set<List3k> findList3kByTenkSelect200(String tenkSelect200) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByTenkSelect200
	 *
	 */
	public Set<List3k> findList3kByTenkSelect200(String tenkSelect200, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByIsSequenced
	 *
	 */
	public Set<List3k> findList3kByIsSequenced(Boolean isSequenced) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByIsSequenced
	 *
	 */
	public Set<List3k> findList3kByIsSequenced(Boolean isSequenced, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByOverlapAffyBgi
	 *
	 */
	public Set<List3k> findList3kByOverlapAffyBgi(String overlapAffyBgi) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByOverlapAffyBgi
	 *
	 */
	public Set<List3k> findList3kByOverlapAffyBgi(String overlapAffyBgi, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByBgiShipment2
	 *
	 */
	public Set<List3k> findList3kByBgiShipment2(String bgiShipment2_1) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByBgiShipment2
	 *
	 */
	public Set<List3k> findList3kByBgiShipment2(String bgiShipment2_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByDesignationDnaSampleContaining
	 *
	 */
	public Set<List3k> findList3kByDesignationDnaSampleContaining(String designationDnaSample_1) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByDesignationDnaSampleContaining
	 *
	 */
	public Set<List3k> findList3kByDesignationDnaSampleContaining(String designationDnaSample_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByIsClustered
	 *
	 */
	public Set<List3k> findList3kByIsClustered(Boolean isClustered) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByIsClustered
	 *
	 */
	public Set<List3k> findList3kByIsClustered(Boolean isClustered, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByBoxPositionCodeContaining
	 *
	 */
	public Set<List3k> findList3kByBoxPositionCodeContaining(String boxPositionCode) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByBoxPositionCodeContaining
	 *
	 */
	public Set<List3k> findList3kByBoxPositionCodeContaining(String boxPositionCode, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByGeneticStockForDna
	 *
	 */
	public Set<List3k> findList3kByGeneticStockForDna(String geneticStockForDna_1) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByGeneticStockForDna
	 *
	 */
	public Set<List3k> findList3kByGeneticStockForDna(String geneticStockForDna_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByEntryCodeContaining
	 *
	 */
	public Set<List3k> findList3kByEntryCodeContaining(String entryCode) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByEntryCodeContaining
	 *
	 */
	public Set<List3k> findList3kByEntryCodeContaining(String entryCode, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByPrimaryKey
	 *
	 */
	public List3k findList3kByPrimaryKey(String irisUniqueId_1) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByPrimaryKey
	 *
	 */
	public List3k findList3kByPrimaryKey(String irisUniqueId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByVarnameOfGenStockSrc
	 *
	 */
	public Set<List3k> findList3kByVarnameOfGenStockSrc(String varnameOfGenStockSrc) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByVarnameOfGenStockSrc
	 *
	 */
	public Set<List3k> findList3kByVarnameOfGenStockSrc(String varnameOfGenStockSrc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByLev
	 *
	 */
	public Set<List3k> findList3kByLev(String lev_1) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByLev
	 *
	 */
	public Set<List3k> findList3kByLev(String lev_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByBgiShipment1
	 *
	 */
	public Set<List3k> findList3kByBgiShipment1(String bgiShipment1) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByBgiShipment1
	 *
	 */
	public Set<List3k> findList3kByBgiShipment1(String bgiShipment1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByCountryOfOriginOfSourceContaining
	 *
	 */
	public Set<List3k> findList3kByCountryOfOriginOfSourceContaining(String countryOfOriginOfSource) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByCountryOfOriginOfSourceContaining
	 *
	 */
	public Set<List3k> findList3kByCountryOfOriginOfSourceContaining(String countryOfOriginOfSource, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByVarietygroupOfSource
	 *
	 */
	public Set<List3k> findList3kByVarietygroupOfSource(String varietygroupOfSource) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByVarietygroupOfSource
	 *
	 */
	public Set<List3k> findList3kByVarietygroupOfSource(String varietygroupOfSource, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByAccessionOfGenStockSourceContaining
	 *
	 */
	public Set<List3k> findList3kByAccessionOfGenStockSourceContaining(String accessionOfGenStockSource) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByAccessionOfGenStockSourceContaining
	 *
	 */
	public Set<List3k> findList3kByAccessionOfGenStockSourceContaining(String accessionOfGenStockSource, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kBySequencingContaining
	 *
	 */
	public Set<List3k> findList3kBySequencingContaining(String sequencing) throws DataAccessException;

	/**
	 * JPQL Query - findList3kBySequencingContaining
	 *
	 */
	public Set<List3k> findList3kBySequencingContaining(String sequencing, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByVarnameOfGenStockSrcContaining
	 *
	 */
	public Set<List3k> findList3kByVarnameOfGenStockSrcContaining(String varnameOfGenStockSrc_1) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByVarnameOfGenStockSrcContaining
	 *
	 */
	public Set<List3k> findList3kByVarnameOfGenStockSrcContaining(String varnameOfGenStockSrc_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByInternalRpocessingSetContaining
	 *
	 */
	public Set<List3k> findList3kByInternalRpocessingSetContaining(String internalRpocessingSet) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByInternalRpocessingSetContaining
	 *
	 */
	public Set<List3k> findList3kByInternalRpocessingSetContaining(String internalRpocessingSet, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByBoxPositionCode
	 *
	 */
	public Set<List3k> findList3kByBoxPositionCode(String boxPositionCode_1) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByBoxPositionCode
	 *
	 */
	public Set<List3k> findList3kByBoxPositionCode(String boxPositionCode_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kBySequencing
	 *
	 */
	public Set<List3k> findList3kBySequencing(String sequencing_1) throws DataAccessException;

	/**
	 * JPQL Query - findList3kBySequencing
	 *
	 */
	public Set<List3k> findList3kBySequencing(String sequencing_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByListNameContaining
	 *
	 */
	public Set<List3k> findList3kByListNameContaining(String listName) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByListNameContaining
	 *
	 */
	public Set<List3k> findList3kByListNameContaining(String listName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByFundingSrcContaining
	 *
	 */
	public Set<List3k> findList3kByFundingSrcContaining(String fundingSrc) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByFundingSrcContaining
	 *
	 */
	public Set<List3k> findList3kByFundingSrcContaining(String fundingSrc, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kBySortCode
	 *
	 */
	public Set<List3k> findList3kBySortCode(Integer sortCode) throws DataAccessException;

	/**
	 * JPQL Query - findList3kBySortCode
	 *
	 */
	public Set<List3k> findList3kBySortCode(Integer sortCode, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByVarietygroupOfSourceContaining
	 *
	 */
	public Set<List3k> findList3kByVarietygroupOfSourceContaining(String varietygroupOfSource_1) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByVarietygroupOfSourceContaining
	 *
	 */
	public Set<List3k> findList3kByVarietygroupOfSourceContaining(String varietygroupOfSource_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByIrisUniqueId
	 *
	 */
	public List3k findList3kByIrisUniqueId(String irisUniqueId_2) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByIrisUniqueId
	 *
	 */
	public List3k findList3kByIrisUniqueId(String irisUniqueId_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByGidForDna
	 *
	 */
	public Set<List3k> findList3kByGidForDna(Integer gidForDna) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByGidForDna
	 *
	 */
	public Set<List3k> findList3kByGidForDna(Integer gidForDna, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByListName
	 *
	 */
	public Set<List3k> findList3kByListName(String listName_1) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByListName
	 *
	 */
	public Set<List3k> findList3kByListName(String listName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByBgiShipment1Containing
	 *
	 */
	public Set<List3k> findList3kByBgiShipment1Containing(String bgiShipment1_1) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByBgiShipment1Containing
	 *
	 */
	public Set<List3k> findList3kByBgiShipment1Containing(String bgiShipment1_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByTenkSelect200Containing
	 *
	 */
	public Set<List3k> findList3kByTenkSelect200Containing(String tenkSelect200_1) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByTenkSelect200Containing
	 *
	 */
	public Set<List3k> findList3kByTenkSelect200Containing(String tenkSelect200_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByOrigId
	 *
	 */
	public Set<List3k> findList3kByOrigId(String origId) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByOrigId
	 *
	 */
	public Set<List3k> findList3kByOrigId(String origId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByInternalRpocessingSet
	 *
	 */
	public Set<List3k> findList3kByInternalRpocessingSet(String internalRpocessingSet_1) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByInternalRpocessingSet
	 *
	 */
	public Set<List3k> findList3kByInternalRpocessingSet(String internalRpocessingSet_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByAltAccessOfGenStockSrcContaining
	 *
	 */
	public Set<List3k> findList3kByAltAccessOfGenStockSrcContaining(String altAccessOfGenStockSrc_1) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByAltAccessOfGenStockSrcContaining
	 *
	 */
	public Set<List3k> findList3kByAltAccessOfGenStockSrcContaining(String altAccessOfGenStockSrc_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByQa
	 *
	 */
	public Set<List3k> findList3kByQa(String qa) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByQa
	 *
	 */
	public Set<List3k> findList3kByQa(String qa, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByAccessionOfGenStockSource
	 *
	 */
	public Set<List3k> findList3kByAccessionOfGenStockSource(String accessionOfGenStockSource_1) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByAccessionOfGenStockSource
	 *
	 */
	public Set<List3k> findList3kByAccessionOfGenStockSource(String accessionOfGenStockSource_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByFundingSrc
	 *
	 */
	public Set<List3k> findList3kByFundingSrc(String fundingSrc_1) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByFundingSrc
	 *
	 */
	public Set<List3k> findList3kByFundingSrc(String fundingSrc_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllList3ks
	 *
	 */
	public Set<List3k> findAllList3ks() throws DataAccessException;

	/**
	 * JPQL Query - findAllList3ks
	 *
	 */
	public Set<List3k> findAllList3ks(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByOverlapAffyBgiContaining
	 *
	 */
	public Set<List3k> findList3kByOverlapAffyBgiContaining(String overlapAffyBgi_1) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByOverlapAffyBgiContaining
	 *
	 */
	public Set<List3k> findList3kByOverlapAffyBgiContaining(String overlapAffyBgi_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByEntryCode
	 *
	 */
	public Set<List3k> findList3kByEntryCode(String entryCode_1) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByEntryCode
	 *
	 */
	public Set<List3k> findList3kByEntryCode(String entryCode_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByQaContaining
	 *
	 */
	public Set<List3k> findList3kByQaContaining(String qa_1) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByQaContaining
	 *
	 */
	public Set<List3k> findList3kByQaContaining(String qa_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByIsDataAvailable
	 *
	 */
	public Set<List3k> findList3kByIsDataAvailable(Boolean isDataAvailable) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByIsDataAvailable
	 *
	 */
	public Set<List3k> findList3kByIsDataAvailable(Boolean isDataAvailable, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByOrigIdContaining
	 *
	 */
	public Set<List3k> findList3kByOrigIdContaining(String origId_1) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByOrigIdContaining
	 *
	 */
	public Set<List3k> findList3kByOrigIdContaining(String origId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByCountryOfOriginOfSource
	 *
	 */
	public Set<List3k> findList3kByCountryOfOriginOfSource(String countryOfOriginOfSource_1) throws DataAccessException;

	/**
	 * JPQL Query - findList3kByCountryOfOriginOfSource
	 *
	 */
	public Set<List3k> findList3kByCountryOfOriginOfSource(String countryOfOriginOfSource_1, int startResult, int maxRows) throws DataAccessException;

	public Set findAllList3kByExample(List3k germplasm);

}