package org.irri.iric.portal.chado.oracle.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.Locus;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVLocusIntxnPrinexpts", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByCommonName", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.commonName = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByCommonNameContaining", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.commonName like ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByContigId", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.contigId = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByContigName", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.contigName = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByContigNameContaining", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.contigName like ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByFeatureId", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.featureId = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByFmax", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.fmax = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByFmin", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.fmin = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByName", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.name = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByNameContaining", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.name like ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByNotes", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.notes = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByNotesContaining", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.notes like ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByOrganismId", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.organismId = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByPccDenserank", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.pccDenserank = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByPccRank", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.pccRank = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByPccScore", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.pccScore = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByPrimaryKey", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.featureId = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByQfeatureId", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.qfeatureId = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByQfeatureName", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.qfeatureName = ?1"),

		@NamedQuery(name = "findVLocusIntxnPrinexptByQfeatureIdIn", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.qfeatureId in (?1) order by myVLocusIntxnPrinexpt.pccScore desc"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByQfeatureNameIn", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.qfeatureName in (?1) order by myVLocusIntxnPrinexpt.pccScore desc"),

		@NamedQuery(name = "findVLocusIntxnPrinexptByQfeatureNameContaining", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.qfeatureName like ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByRssbpDenserank", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.rssbpDenserank = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByRssbpRank", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.rssbpRank = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByRssbpScore", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.rssbpScore = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByRssccDenserank", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.rssccDenserank = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByRssccRank", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.rssccRank = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByRssccScore", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.rssccScore = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByRssmfDenserank", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.rssmfDenserank = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByRssmfRank", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.rssmfRank = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByRssmfScore", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.rssmfScore = ?1"),
		@NamedQuery(name = "findVLocusIntxnPrinexptByStrand", query = "select myVLocusIntxnPrinexpt from VLocusIntxnPrinexpt myVLocusIntxnPrinexpt where myVLocusIntxnPrinexpt.strand = ?1") })
@Table(name = "V_LOCUS_INTXN_PRINEXPT")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/oracle/domain", name = "VLocusIntxnPrinexpt")
public class VLocusIntxnPrinexpt implements Serializable, Locus {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "FEATURE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal featureId;
	/**
	 */

	@Column(name = "COMMON_NAME", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String commonName;
	/**
	 */

	@Column(name = "CONTIG_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal contigId;
	/**
	 */

	@Column(name = "CONTIG_NAME", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String contigName;
	/**
	 */

	@Column(name = "NAME", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 */

	@Column(name = "FMIN", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer fmin;
	/**
	 */

	@Column(name = "FMAX", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer fmax;
	/**
	 */

	@Column(name = "STRAND", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer strand;
	/**
	 */

	@Column(name = "ORGANISM_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal organismId;
	/**
	 */

	@Column(name = "NOTES", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String notes;
	/**
	 */

	@Column(name = "QFEATURE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal qfeatureId;
	/**
	 */

	@Column(name = "QFEATURE_NAME", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String qfeatureName;
	/**
	 */

	@Column(name = "RSSCC_SCORE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal rssccScore;
	/**
	 */

	@Column(name = "RSSCC_RANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer rssccRank;
	/**
	 */

	@Column(name = "RSSCC_DENSERANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer rssccDenserank;
	/**
	 */

	@Column(name = "RSSBP_SCORE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal rssbpScore;
	/**
	 */

	@Column(name = "RSSBP_RANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer rssbpRank;
	/**
	 */

	@Column(name = "RSSBP_DENSERANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer rssbpDenserank;
	/**
	 */

	@Column(name = "RSSMF_SCORE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal rssmfScore;
	/**
	 */

	@Column(name = "RSSMF_RANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer rssmfRank;
	/**
	 */

	@Column(name = "RSSMF_DENSERANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer rssmfDenserank;
	/**
	 */

	@Column(name = "PCC_SCORE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal pccScore;
	/**
	 */

	@Column(name = "PCC_RANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer pccRank;
	/**
	 */

	@Column(name = "PCC_DENSERANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer pccDenserank;

	@Column(name = "PCC_MAXSCORE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal pccMaxscore;
	/**
	 */

	@Column(name = "PCC_MAXRANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer pccMaxrank;

	@Column(name = "RSSCC_MAXSCORE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal rssccMaxscore;
	/**
	 */

	@Column(name = "RSSCC_MAXRANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer rssccMaxrank;

	@Column(name = "RSSBP_MAXSCORE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal rssbpMaxscore;
	/**
	 */

	@Column(name = "RSSBP_MAXRANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer rssbpMaxrank;

	@Column(name = "RSSMF_MAXSCORE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal rssmfMaxscore;
	/**
	 */

	@Column(name = "RSSMF_MAXRANK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer rssmfMaxrank;

	/**
	 */
	public void setFeatureId(BigDecimal featureId) {
		this.featureId = featureId;
	}

	/**
	 */
	public BigDecimal getFeatureId() {
		return this.featureId;
	}

	/**
	 */
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	/**
	 */
	public String getCommonName() {
		return this.commonName;
	}

	/**
	 */
	public void setContigId(BigDecimal contigId) {
		this.contigId = contigId;
	}

	/**
	 */
	public BigDecimal getContigId() {
		return this.contigId;
	}

	/**
	 */
	public void setContigName(String contigName) {
		this.contigName = contigName;
	}

	/**
	 */
	public String getContigName() {
		return this.contigName;
	}

	/**
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 */
	public String getName() {
		return this.name;
	}

	/**
	 */
	public void setFmin(Integer fmin) {
		this.fmin = fmin;
	}

	/**
	 */
	public Integer getFmin() {
		return this.fmin;
	}

	/**
	 */
	public void setFmax(Integer fmax) {
		this.fmax = fmax;
	}

	/**
	 */
	public Integer getFmax() {
		return this.fmax;
	}

	/**
	 */
	public void setStrand(Integer strand) {
		this.strand = strand;
	}

	/**
	 */
	public Integer getStrand() {
		return this.strand;
	}

	/**
	 */
	public void setOrganismId(BigDecimal organismId) {
		this.organismId = organismId;
	}

	/**
	 */
	public BigDecimal getOrganismId() {
		return this.organismId;
	}

	/**
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 */
	public String getNotes() {
		return this.notes;
	}

	/**
	 */
	public void setQfeatureId(BigDecimal qfeatureId) {
		this.qfeatureId = qfeatureId;
	}

	/**
	 */
	public BigDecimal getQfeatureId() {
		return this.qfeatureId;
	}

	/**
	 */
	public void setQfeatureName(String qfeatureName) {
		this.qfeatureName = qfeatureName;
	}

	/**
	 */
	public String getQfeatureName() {
		return this.qfeatureName;
	}

	/**
	 */
	public void setRssccScore(BigDecimal rssccScore) {
		this.rssccScore = rssccScore;
	}

	/**
	 */
	public BigDecimal getRssccScore() {
		return this.rssccScore;
	}

	/**
	 */
	public void setRssccRank(Integer rssccRank) {
		this.rssccRank = rssccRank;
	}

	/**
	 */
	public Integer getRssccRank() {
		return this.rssccRank;
	}

	/**
	 */
	public void setRssccDenserank(Integer rssccDenserank) {
		this.rssccDenserank = rssccDenserank;
	}

	/**
	 */
	public Integer getRssccDenserank() {
		return this.rssccDenserank;
	}

	/**
	 */
	public void setRssbpScore(BigDecimal rssbpScore) {
		this.rssbpScore = rssbpScore;
	}

	/**
	 */
	public BigDecimal getRssbpScore() {
		return this.rssbpScore;
	}

	/**
	 */
	public void setRssbpRank(Integer rssbpRank) {
		this.rssbpRank = rssbpRank;
	}

	/**
	 */
	public Integer getRssbpRank() {
		return this.rssbpRank;
	}

	/**
	 */
	public void setRssbpDenserank(Integer rssbpDenserank) {
		this.rssbpDenserank = rssbpDenserank;
	}

	/**
	 */
	public Integer getRssbpDenserank() {
		return this.rssbpDenserank;
	}

	/**
	 */
	public void setRssmfScore(BigDecimal rssmfScore) {
		this.rssmfScore = rssmfScore;
	}

	/**
	 */
	public BigDecimal getRssmfScore() {
		return this.rssmfScore;
	}

	/**
	 */
	public void setRssmfRank(Integer rssmfRank) {
		this.rssmfRank = rssmfRank;
	}

	/**
	 */
	public Integer getRssmfRank() {
		return this.rssmfRank;
	}

	/**
	 */
	public void setRssmfDenserank(Integer rssmfDenserank) {
		this.rssmfDenserank = rssmfDenserank;
	}

	/**
	 */
	public Integer getRssmfDenserank() {
		return this.rssmfDenserank;
	}

	/**
	 */
	public void setPccScore(BigDecimal pccScore) {
		this.pccScore = pccScore;
	}

	/**
	 */
	public BigDecimal getPccScore() {
		return this.pccScore;
	}

	/**
	 */
	public void setPccRank(Integer pccRank) {
		this.pccRank = pccRank;
	}

	/**
	 */
	public Integer getPccRank() {
		return this.pccRank;
	}

	/**
	 */
	public void setPccDenserank(Integer pccDenserank) {
		this.pccDenserank = pccDenserank;
	}

	/**
	 */
	public Integer getPccDenserank() {
		return this.pccDenserank;
	}

	/**
	 */
	public VLocusIntxnPrinexpt() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VLocusIntxnPrinexpt that) {
		setFeatureId(that.getFeatureId());
		setCommonName(that.getCommonName());
		setContigId(that.getContigId());
		setContigName(that.getContigName());
		setName(that.getName());
		setFmin(that.getFmin());
		setFmax(that.getFmax());
		setStrand(that.getStrand());
		setOrganismId(that.getOrganismId());
		setNotes(that.getNotes());
		setQfeatureId(that.getQfeatureId());
		setQfeatureName(that.getQfeatureName());
		setRssccScore(that.getRssccScore());
		setRssccRank(that.getRssccRank());
		setRssccDenserank(that.getRssccDenserank());
		setRssbpScore(that.getRssbpScore());
		setRssbpRank(that.getRssbpRank());
		setRssbpDenserank(that.getRssbpDenserank());
		setRssmfScore(that.getRssmfScore());
		setRssmfRank(that.getRssmfRank());
		setRssmfDenserank(that.getRssmfDenserank());
		setPccScore(that.getPccScore());
		setPccRank(that.getPccRank());
		setPccDenserank(that.getPccDenserank());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("featureId=[").append(featureId).append("] ");
		buffer.append("commonName=[").append(commonName).append("] ");
		buffer.append("contigId=[").append(contigId).append("] ");
		buffer.append("contigName=[").append(contigName).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("fmin=[").append(fmin).append("] ");
		buffer.append("fmax=[").append(fmax).append("] ");
		buffer.append("strand=[").append(strand).append("] ");
		buffer.append("organismId=[").append(organismId).append("] ");
		buffer.append("notes=[").append(notes).append("] ");
		buffer.append("qfeatureId=[").append(qfeatureId).append("] ");
		buffer.append("qfeatureName=[").append(qfeatureName).append("] ");
		buffer.append("rssccScore=[").append(rssccScore).append("] ");
		buffer.append("rssccRank=[").append(rssccRank).append("] ");
		buffer.append("rssccDenserank=[").append(rssccDenserank).append("] ");
		buffer.append("rssbpScore=[").append(rssbpScore).append("] ");
		buffer.append("rssbpRank=[").append(rssbpRank).append("] ");
		buffer.append("rssbpDenserank=[").append(rssbpDenserank).append("] ");
		buffer.append("rssmfScore=[").append(rssmfScore).append("] ");
		buffer.append("rssmfRank=[").append(rssmfRank).append("] ");
		buffer.append("rssmfDenserank=[").append(rssmfDenserank).append("] ");
		buffer.append("pccScore=[").append(pccScore).append("] ");
		buffer.append("pccRank=[").append(pccRank).append("] ");
		buffer.append("pccDenserank=[").append(pccDenserank).append("] ");

		return buffer.toString();
	}

	// /**
	// */
	// @Override
	// public int hashCode() {
	// final int prime = 31;
	// int result = 1;
	// result = (int) (prime * result + ((featureId == null) ? 0 :
	// featureId.hashCode()));
	// return result;
	// }
	//
	// /**
	// */
	// public boolean equals(Object obj) {
	// if (obj == this)
	// return true;
	// if (!(obj instanceof VLocusIntxnPrinexpt))
	// return false;
	// VLocusIntxnPrinexpt equalCheck = (VLocusIntxnPrinexpt) obj;
	// if ((featureId == null && equalCheck.featureId != null) || (featureId != null
	// && equalCheck.featureId == null))
	// return false;
	// if (featureId != null && !featureId.equals(equalCheck.featureId))
	// return false;
	// return true;
	// }

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		// result = (int) (prime * result + ((featureId == null) ? 0 :
		// featureId.hashCode()));
		result = (int) (prime * result + ((organismId == null) ? 0 : organismId.hashCode()));
		result = (int) (prime * result + ((contigId == null) ? 0 : contigId.hashCode()));
		result = (int) (prime * result + ((fmin == null) ? 0 : fmin.hashCode()));
		result = (int) (prime * result + ((fmax == null) ? 0 : fmax.hashCode()));
		return result;
	}

	@Override
	public int compareTo(Object o) {
		
		Locus l1 = (Locus) this;
		Locus l2 = (Locus) o;
		int ret = l1.getContig().compareTo(l2.getContig());
		if (ret != 0)
			return ret;
		ret = l1.getFmin().compareTo(l2.getFmin());
		if (ret != 0)
			return ret;
		ret = l1.getFmax().compareTo(l2.getFmax());
		return ret;

	}

	@Override
	public boolean equals(Object obj) {
		
		return compareTo(obj) == 0;
	}

	@Override
	public String getUniquename() {
		
		return name;
	}

	@Override
	public Long getChr() {
		
		return Long.valueOf(getContig());
	}

	@Override
	public String getContig() {
		
		return this.contigName;
	}

	@Override
	public String getDescription() {

		StringBuffer buff = new StringBuffer();
		if (pccScore != null)
			buff.append(" PCC:" + String.format("%.2f", pccScore) + "/" + String.format("%.2f", pccMaxscore) + " "
					+ pccRank + "/" + pccMaxrank);
		if (rssccScore != null)
			buff.append(" CC" + String.format("%.2f", rssccScore) + "/" + String.format("%.2f", rssccMaxscore) + " "
					+ rssccRank + "/" + rssccMaxrank);
		if (rssbpScore != null)
			buff.append(" BP" + String.format("%.2f", rssbpScore) + "/" + String.format("%.2f", rssbpMaxscore) + " "
					+ rssbpRank + "/" + rssbpMaxrank);
		if (rssmfScore != null)
			buff.append(" MF" + String.format("%.2f", rssmfScore) + "/" + String.format("%.2f", rssmfMaxscore) + " "
					+ rssmfRank + "/" + rssmfMaxrank);

		
		return buff + " " + this.notes;
	}

	@Override
	public String getFeatureType() {
		return null;
	}

}
