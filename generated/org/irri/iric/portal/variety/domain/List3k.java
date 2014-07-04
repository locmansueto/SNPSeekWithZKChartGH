package org.irri.iric.portal.variety.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllList3ks", query = "select myList3k from List3k myList3k"),
		@NamedQuery(name = "findList3kByAccessionOfGenStockSource", query = "select myList3k from List3k myList3k where myList3k.accessionOfGenStockSource = ?1"),
		@NamedQuery(name = "findList3kByAccessionOfGenStockSourceContaining", query = "select myList3k from List3k myList3k where myList3k.accessionOfGenStockSource like ?1"),
		@NamedQuery(name = "findList3kByAltAccessOfGenStockSrc", query = "select myList3k from List3k myList3k where myList3k.altAccessOfGenStockSrc = ?1"),
		@NamedQuery(name = "findList3kByAltAccessOfGenStockSrcContaining", query = "select myList3k from List3k myList3k where myList3k.altAccessOfGenStockSrc like ?1"),
		@NamedQuery(name = "findList3kByBgiShipment1", query = "select myList3k from List3k myList3k where myList3k.bgiShipment1 = ?1"),
		@NamedQuery(name = "findList3kByBgiShipment1Containing", query = "select myList3k from List3k myList3k where myList3k.bgiShipment1 like ?1"),
		@NamedQuery(name = "findList3kByBgiShipment2", query = "select myList3k from List3k myList3k where myList3k.bgiShipment2 = ?1"),
		@NamedQuery(name = "findList3kByBgiShipment2Containing", query = "select myList3k from List3k myList3k where myList3k.bgiShipment2 like ?1"),
		@NamedQuery(name = "findList3kByBoxPositionCode", query = "select myList3k from List3k myList3k where myList3k.boxPositionCode = ?1"),
		@NamedQuery(name = "findList3kByBoxPositionCodeContaining", query = "select myList3k from List3k myList3k where myList3k.boxPositionCode like ?1"),
		@NamedQuery(name = "findList3kByCountryOfOriginOfSource", query = "select myList3k from List3k myList3k where myList3k.countryOfOriginOfSource = ?1"),
		@NamedQuery(name = "findList3kByCountryOfOriginOfSourceContaining", query = "select myList3k from List3k myList3k where myList3k.countryOfOriginOfSource like ?1"),
		@NamedQuery(name = "findList3kByDesignationDnaSample", query = "select myList3k from List3k myList3k where myList3k.designationDnaSample = ?1"),
		@NamedQuery(name = "findList3kByDesignationDnaSampleContaining", query = "select myList3k from List3k myList3k where myList3k.designationDnaSample like ?1"),
		@NamedQuery(name = "findList3kByDuplication", query = "select myList3k from List3k myList3k where myList3k.duplication = ?1"),
		@NamedQuery(name = "findList3kByDuplicationContaining", query = "select myList3k from List3k myList3k where myList3k.duplication like ?1"),
		@NamedQuery(name = "findList3kByEntryCode", query = "select myList3k from List3k myList3k where myList3k.entryCode = ?1"),
		@NamedQuery(name = "findList3kByEntryCodeContaining", query = "select myList3k from List3k myList3k where myList3k.entryCode like ?1"),
		@NamedQuery(name = "findList3kByFundingSrc", query = "select myList3k from List3k myList3k where myList3k.fundingSrc = ?1"),
		@NamedQuery(name = "findList3kByFundingSrcContaining", query = "select myList3k from List3k myList3k where myList3k.fundingSrc like ?1"),
		@NamedQuery(name = "findList3kByGeneticStockForDna", query = "select myList3k from List3k myList3k where myList3k.geneticStockForDna = ?1"),
		@NamedQuery(name = "findList3kByGeneticStockForDnaContaining", query = "select myList3k from List3k myList3k where myList3k.geneticStockForDna like ?1"),
		@NamedQuery(name = "findList3kByGidForDna", query = "select myList3k from List3k myList3k where myList3k.gidForDna = ?1"),
		@NamedQuery(name = "findList3kByInternalRpocessingSet", query = "select myList3k from List3k myList3k where myList3k.internalRpocessingSet = ?1"),
		@NamedQuery(name = "findList3kByInternalRpocessingSetContaining", query = "select myList3k from List3k myList3k where myList3k.internalRpocessingSet like ?1"),
		@NamedQuery(name = "findList3kByIrisUniqueId", query = "select myList3k from List3k myList3k where myList3k.irisUniqueId = ?1"),
		@NamedQuery(name = "findList3kByIrisUniqueIdContaining", query = "select myList3k from List3k myList3k where myList3k.irisUniqueId like ?1"),
		@NamedQuery(name = "findList3kByIsClustered", query = "select myList3k from List3k myList3k where myList3k.isClustered = ?1"),
		@NamedQuery(name = "findList3kByIsDataAvailable", query = "select myList3k from List3k myList3k where myList3k.isDataAvailable = ?1"),
		@NamedQuery(name = "findList3kByIsSequenced", query = "select myList3k from List3k myList3k where myList3k.isSequenced = ?1"),
		@NamedQuery(name = "findList3kByLev", query = "select myList3k from List3k myList3k where myList3k.lev = ?1"),
		@NamedQuery(name = "findList3kByLevContaining", query = "select myList3k from List3k myList3k where myList3k.lev like ?1"),
		@NamedQuery(name = "findList3kByListName", query = "select myList3k from List3k myList3k where myList3k.listName = ?1"),
		@NamedQuery(name = "findList3kByListNameContaining", query = "select myList3k from List3k myList3k where myList3k.listName like ?1"),
		@NamedQuery(name = "findList3kByOrigId", query = "select myList3k from List3k myList3k where myList3k.origId = ?1"),
		@NamedQuery(name = "findList3kByOrigIdContaining", query = "select myList3k from List3k myList3k where myList3k.origId like ?1"),
		@NamedQuery(name = "findList3kByOverlapAffyBgi", query = "select myList3k from List3k myList3k where myList3k.overlapAffyBgi = ?1"),
		@NamedQuery(name = "findList3kByOverlapAffyBgiContaining", query = "select myList3k from List3k myList3k where myList3k.overlapAffyBgi like ?1"),
		@NamedQuery(name = "findList3kByPrimaryKey", query = "select myList3k from List3k myList3k where myList3k.irisUniqueId = ?1"),
		@NamedQuery(name = "findList3kByQa", query = "select myList3k from List3k myList3k where myList3k.qa = ?1"),
		@NamedQuery(name = "findList3kByQaContaining", query = "select myList3k from List3k myList3k where myList3k.qa like ?1"),
		@NamedQuery(name = "findList3kBySequencing", query = "select myList3k from List3k myList3k where myList3k.sequencing = ?1"),
		@NamedQuery(name = "findList3kBySequencingContaining", query = "select myList3k from List3k myList3k where myList3k.sequencing like ?1"),
		@NamedQuery(name = "findList3kBySortCode", query = "select myList3k from List3k myList3k where myList3k.sortCode = ?1"),
		@NamedQuery(name = "findList3kByTenkSelect200", query = "select myList3k from List3k myList3k where myList3k.tenkSelect200 = ?1"),
		@NamedQuery(name = "findList3kByTenkSelect200Containing", query = "select myList3k from List3k myList3k where myList3k.tenkSelect200 like ?1"),
		@NamedQuery(name = "findList3kByVarietygroupOfSource", query = "select myList3k from List3k myList3k where myList3k.varietygroupOfSource = ?1"),
		@NamedQuery(name = "findList3kByVarietygroupOfSourceContaining", query = "select myList3k from List3k myList3k where myList3k.varietygroupOfSource like ?1"),
		@NamedQuery(name = "findList3kByVarnameOfGenStockSrc", query = "select myList3k from List3k myList3k where myList3k.varnameOfGenStockSrc = ?1"),
		@NamedQuery(name = "findList3kByVarnameOfGenStockSrcContaining", query = "select myList3k from List3k myList3k where myList3k.varnameOfGenStockSrc like ?1") })
@Table(schema = "NICKA", name = "LIST_3K")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "dev_crud_maker/org/irri/iric/portal/variety/domain", name = "List3k")
public class List3k implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "SORT_CODE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer sortCode;
	/**
	 */

	@Column(name = "BOX_POSITION_CODE", length = 8)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String boxPositionCode;
	/**
	 */

	@Column(name = "GID_FOR_DNA")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer gidForDna;
	/**
	 */

	@Column(name = "IRIS_UNIQUE_ID", length = 16)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String irisUniqueId;
	/**
	 */

	@Column(name = "DESIGNATION_DNA_SAMPLE", length = 64)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String designationDnaSample;
	/**
	 */

	@Column(name = "GENETIC_STOCK_FOR_DNA", length = 64)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String geneticStockForDna;
	/**
	 */

	@Column(name = "VARNAME_OF_GEN_STOCK_SRC", length = 128)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String varnameOfGenStockSrc;
	/**
	 */

	@Column(name = "ACCESSION_OF_GEN_STOCK_SOURCE", length = 64)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String accessionOfGenStockSource;
	/**
	 */

	@Column(name = "ALT_ACCESS_OF_GEN_STOCK_SRC", length = 32)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String altAccessOfGenStockSrc;
	/**
	 */

	@Column(name = "COUNTRY_OF_ORIGIN_OF_SOURCE", length = 32)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String countryOfOriginOfSource;
	/**
	 */

	@Column(name = "VARIETYGROUP_OF_SOURCE", length = 32)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String varietygroupOfSource;
	/**
	 */

	@Column(name = "INTERNAL_RPOCESSING_SET", length = 32)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String internalRpocessingSet;
	/**
	 */

	@Column(name = "BGI_SHIPMENT_1", length = 8)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String bgiShipment1;
	/**
	 */

	@Column(name = "QA", length = 16)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String qa;
	/**
	 */

	@Column(name = "LEV", length = 32)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String lev;
	/**
	 */

	@Column(name = "BGI_SHIPMENT_2", length = 8)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String bgiShipment2;
	/**
	 */

	@Column(name = "OVERLAP_AFFY_BGI", length = 16)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String overlapAffyBgi;
	/**
	 */

	@Column(name = "DUPLICATION", length = 16)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String duplication;
	/**
	 */

	@Column(name = "ORIG_ID", length = 16)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String origId;
	/**
	 */

	@Column(name = "FUNDING_SRC", length = 16)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String fundingSrc;
	/**
	 */

	@Column(name = "SEQUENCING", length = 64)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String sequencing;
	/**
	 */

	@Column(name = "LIST_NAME", length = 64)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String listName;
	/**
	 */

	@Column(name = "ENTRY_CODE", length = 16)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String entryCode;
	/**
	 */

	@Column(name = "TENK_SELECT200", length = 32)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String tenkSelect200;
	/**
	 */

	@Column(name = "IS_SEQUENCED")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Boolean isSequenced;
	/**
	 */

	@Column(name = "IS_DATA_AVAILABLE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Boolean isDataAvailable;
	/**
	 */

	@Column(name = "IS_CLUSTERED")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Boolean isClustered;

	/**
	 */
	public void setSortCode(Integer sortCode) {
		this.sortCode = sortCode;
	}

	/**
	 */
	public Integer getSortCode() {
		return this.sortCode;
	}

	/**
	 */
	public void setBoxPositionCode(String boxPositionCode) {
		this.boxPositionCode = boxPositionCode;
	}

	/**
	 */
	public String getBoxPositionCode() {
		return this.boxPositionCode;
	}

	/**
	 */
	public void setGidForDna(Integer gidForDna) {
		this.gidForDna = gidForDna;
	}

	/**
	 */
	public Integer getGidForDna() {
		return this.gidForDna;
	}

	/**
	 */
	public void setIrisUniqueId(String irisUniqueId) {
		this.irisUniqueId = irisUniqueId;
	}

	/**
	 */
	public String getIrisUniqueId() {
		return this.irisUniqueId;
	}

	/**
	 */
	public void setDesignationDnaSample(String designationDnaSample) {
		this.designationDnaSample = designationDnaSample;
	}

	/**
	 */
	public String getDesignationDnaSample() {
		return this.designationDnaSample;
	}

	/**
	 */
	public void setGeneticStockForDna(String geneticStockForDna) {
		this.geneticStockForDna = geneticStockForDna;
	}

	/**
	 */
	public String getGeneticStockForDna() {
		return this.geneticStockForDna;
	}

	/**
	 */
	public void setVarnameOfGenStockSrc(String varnameOfGenStockSrc) {
		this.varnameOfGenStockSrc = varnameOfGenStockSrc;
	}

	/**
	 */
	public String getVarnameOfGenStockSrc() {
		return this.varnameOfGenStockSrc;
	}

	/**
	 */
	public void setAccessionOfGenStockSource(String accessionOfGenStockSource) {
		this.accessionOfGenStockSource = accessionOfGenStockSource;
	}

	/**
	 */
	public String getAccessionOfGenStockSource() {
		return this.accessionOfGenStockSource;
	}

	/**
	 */
	public void setAltAccessOfGenStockSrc(String altAccessOfGenStockSrc) {
		this.altAccessOfGenStockSrc = altAccessOfGenStockSrc;
	}

	/**
	 */
	public String getAltAccessOfGenStockSrc() {
		return this.altAccessOfGenStockSrc;
	}

	/**
	 */
	public void setCountryOfOriginOfSource(String countryOfOriginOfSource) {
		this.countryOfOriginOfSource = countryOfOriginOfSource;
	}

	/**
	 */
	public String getCountryOfOriginOfSource() {
		return this.countryOfOriginOfSource;
	}

	/**
	 */
	public void setVarietygroupOfSource(String varietygroupOfSource) {
		this.varietygroupOfSource = varietygroupOfSource;
	}

	/**
	 */
	public String getVarietygroupOfSource() {
		return this.varietygroupOfSource;
	}

	/**
	 */
	public void setInternalRpocessingSet(String internalRpocessingSet) {
		this.internalRpocessingSet = internalRpocessingSet;
	}

	/**
	 */
	public String getInternalRpocessingSet() {
		return this.internalRpocessingSet;
	}

	/**
	 */
	public void setBgiShipment1(String bgiShipment1) {
		this.bgiShipment1 = bgiShipment1;
	}

	/**
	 */
	public String getBgiShipment1() {
		return this.bgiShipment1;
	}

	/**
	 */
	public void setQa(String qa) {
		this.qa = qa;
	}

	/**
	 */
	public String getQa() {
		return this.qa;
	}

	/**
	 */
	public void setLev(String lev) {
		this.lev = lev;
	}

	/**
	 */
	public String getLev() {
		return this.lev;
	}

	/**
	 */
	public void setBgiShipment2(String bgiShipment2) {
		this.bgiShipment2 = bgiShipment2;
	}

	/**
	 */
	public String getBgiShipment2() {
		return this.bgiShipment2;
	}

	/**
	 */
	public void setOverlapAffyBgi(String overlapAffyBgi) {
		this.overlapAffyBgi = overlapAffyBgi;
	}

	/**
	 */
	public String getOverlapAffyBgi() {
		return this.overlapAffyBgi;
	}

	/**
	 */
	public void setDuplication(String duplication) {
		this.duplication = duplication;
	}

	/**
	 */
	public String getDuplication() {
		return this.duplication;
	}

	/**
	 */
	public void setOrigId(String origId) {
		this.origId = origId;
	}

	/**
	 */
	public String getOrigId() {
		return this.origId;
	}

	/**
	 */
	public void setFundingSrc(String fundingSrc) {
		this.fundingSrc = fundingSrc;
	}

	/**
	 */
	public String getFundingSrc() {
		return this.fundingSrc;
	}

	/**
	 */
	public void setSequencing(String sequencing) {
		this.sequencing = sequencing;
	}

	/**
	 */
	public String getSequencing() {
		return this.sequencing;
	}

	/**
	 */
	public void setListName(String listName) {
		this.listName = listName;
	}

	/**
	 */
	public String getListName() {
		return this.listName;
	}

	/**
	 */
	public void setEntryCode(String entryCode) {
		this.entryCode = entryCode;
	}

	/**
	 */
	public String getEntryCode() {
		return this.entryCode;
	}

	/**
	 */
	public void setTenkSelect200(String tenkSelect200) {
		this.tenkSelect200 = tenkSelect200;
	}

	/**
	 */
	public String getTenkSelect200() {
		return this.tenkSelect200;
	}

	/**
	 */
	public void setIsSequenced(Boolean isSequenced) {
		this.isSequenced = isSequenced;
	}

	/**
	 */
	public Boolean getIsSequenced() {
		return this.isSequenced;
	}

	/**
	 */
	public void setIsDataAvailable(Boolean isDataAvailable) {
		this.isDataAvailable = isDataAvailable;
	}

	/**
	 */
	public Boolean getIsDataAvailable() {
		return this.isDataAvailable;
	}

	/**
	 */
	public void setIsClustered(Boolean isClustered) {
		this.isClustered = isClustered;
	}

	/**
	 */
	public Boolean getIsClustered() {
		return this.isClustered;
	}

	/**
	 */
	public List3k() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(List3k that) {
		setSortCode(that.getSortCode());
		setBoxPositionCode(that.getBoxPositionCode());
		setGidForDna(that.getGidForDna());
		setIrisUniqueId(that.getIrisUniqueId());
		setDesignationDnaSample(that.getDesignationDnaSample());
		setGeneticStockForDna(that.getGeneticStockForDna());
		setVarnameOfGenStockSrc(that.getVarnameOfGenStockSrc());
		setAccessionOfGenStockSource(that.getAccessionOfGenStockSource());
		setAltAccessOfGenStockSrc(that.getAltAccessOfGenStockSrc());
		setCountryOfOriginOfSource(that.getCountryOfOriginOfSource());
		setVarietygroupOfSource(that.getVarietygroupOfSource());
		setInternalRpocessingSet(that.getInternalRpocessingSet());
		setBgiShipment1(that.getBgiShipment1());
		setQa(that.getQa());
		setLev(that.getLev());
		setBgiShipment2(that.getBgiShipment2());
		setOverlapAffyBgi(that.getOverlapAffyBgi());
		setDuplication(that.getDuplication());
		setOrigId(that.getOrigId());
		setFundingSrc(that.getFundingSrc());
		setSequencing(that.getSequencing());
		setListName(that.getListName());
		setEntryCode(that.getEntryCode());
		setTenkSelect200(that.getTenkSelect200());
		setIsSequenced(that.getIsSequenced());
		setIsDataAvailable(that.getIsDataAvailable());
		setIsClustered(that.getIsClustered());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("sortCode=[").append(sortCode).append("] ");
		buffer.append("boxPositionCode=[").append(boxPositionCode).append("] ");
		buffer.append("gidForDna=[").append(gidForDna).append("] ");
		buffer.append("irisUniqueId=[").append(irisUniqueId).append("] ");
		buffer.append("designationDnaSample=[").append(designationDnaSample).append("] ");
		buffer.append("geneticStockForDna=[").append(geneticStockForDna).append("] ");
		buffer.append("varnameOfGenStockSrc=[").append(varnameOfGenStockSrc).append("] ");
		buffer.append("accessionOfGenStockSource=[").append(accessionOfGenStockSource).append("] ");
		buffer.append("altAccessOfGenStockSrc=[").append(altAccessOfGenStockSrc).append("] ");
		buffer.append("countryOfOriginOfSource=[").append(countryOfOriginOfSource).append("] ");
		buffer.append("varietygroupOfSource=[").append(varietygroupOfSource).append("] ");
		buffer.append("internalRpocessingSet=[").append(internalRpocessingSet).append("] ");
		buffer.append("bgiShipment1=[").append(bgiShipment1).append("] ");
		buffer.append("qa=[").append(qa).append("] ");
		buffer.append("lev=[").append(lev).append("] ");
		buffer.append("bgiShipment2=[").append(bgiShipment2).append("] ");
		buffer.append("overlapAffyBgi=[").append(overlapAffyBgi).append("] ");
		buffer.append("duplication=[").append(duplication).append("] ");
		buffer.append("origId=[").append(origId).append("] ");
		buffer.append("fundingSrc=[").append(fundingSrc).append("] ");
		buffer.append("sequencing=[").append(sequencing).append("] ");
		buffer.append("listName=[").append(listName).append("] ");
		buffer.append("entryCode=[").append(entryCode).append("] ");
		buffer.append("tenkSelect200=[").append(tenkSelect200).append("] ");
		buffer.append("isSequenced=[").append(isSequenced).append("] ");
		buffer.append("isDataAvailable=[").append(isDataAvailable).append("] ");
		buffer.append("isClustered=[").append(isClustered).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((irisUniqueId == null) ? 0 : irisUniqueId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof List3k))
			return false;
		List3k equalCheck = (List3k) obj;
		if ((irisUniqueId == null && equalCheck.irisUniqueId != null) || (irisUniqueId != null && equalCheck.irisUniqueId == null))
			return false;
		if (irisUniqueId != null && !irisUniqueId.equals(equalCheck.irisUniqueId))
			return false;
		return true;
	}
}
