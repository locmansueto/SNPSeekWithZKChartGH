package org.irri.iric.portal.chado.postgres.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.ConvertposAny2Allrefs;

/**
 */
@IdClass(org.irri.iric.portal.chado.postgres.domain.MvConvertposAny2allrefsPK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllMvConvertposAny2allrefss", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByAlleleIndex", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.alleleIndex = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByDj123AlignCount", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.dj123AlignCount = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByDj123ContigId", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.dj123ContigId = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByDj123ContigName", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.dj123ContigName = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByDj123ContigNameContaining", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.dj123ContigName like ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByDj123Position", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.dj123Position = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByDj123Refcall", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.dj123Refcall = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByDj123RefcallContaining", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.dj123Refcall like ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByFromContigId", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.fromContigId = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByFromOrganismId", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.fromOrganismId = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByFromPosition", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.fromPosition = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByFromRefcall", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.fromRefcall = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByFromRefcallContaining", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.fromRefcall like ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByIr64AlignCount", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.ir64AlignCount = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByIr64ContigId", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.ir64ContigId = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByIr64ContigName", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.ir64ContigName = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByIr64ContigNameContaining", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.ir64ContigName like ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByIr64Position", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.ir64Position = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByIr64Refcall", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.ir64Refcall = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByIr64RefcallContaining", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.ir64Refcall like ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByKasalathAlignCount", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.kasalathAlignCount = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByKasalathContigId", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.kasalathContigId = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByKasalathContigName", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.kasalathContigName = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByKasalathContigNameContaining", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.kasalathContigName like ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByKasalathPosition", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.kasalathPosition = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByKasalathRefcall", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.kasalathRefcall = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByKasalathRefcallContaining", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.kasalathRefcall like ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByNbAlignCount", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.nbAlignCount = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByNbContigId", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.nbContigId = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByNbContigName", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.nbContigName = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByNbContigNameContaining", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.nbContigName like ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByNbPosition", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.nbPosition = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByNbRefcall", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.nbRefcall = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByNbRefcallContaining", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.nbRefcall like ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByPrimaryKey", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.snpFeatureId = ?1 and myMvConvertposAny2allrefs.fromOrganismId = ?2"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByRice9311AlignCount", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.rice9311AlignCount = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByRice9311ContigId", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.rice9311ContigId = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByRice9311ContigName", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.rice9311ContigName = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByRice9311ContigNameContaining", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.rice9311ContigName like ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByRice9311Position", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.rice9311Position = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByRice9311Refcall", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.rice9311Refcall = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByRice9311RefcallContaining", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.rice9311Refcall like ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsBySnpFeatureId", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.snpFeatureId = ?1"),
		@NamedQuery(name = "findMvConvertposAny2allrefsByTypeId", query = "select myMvConvertposAny2allrefs from MvConvertposAny2allrefs myMvConvertposAny2allrefs where myMvConvertposAny2allrefs.typeId = ?1") })
@Table(schema = "public", name = "mv_convertpos_any2allrefs")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/postgres/domain", name = "MvConvertposAny2allrefs")
public class MvConvertposAny2allrefs implements ConvertposAny2Allrefs {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "snp_feature_id")
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal snpFeatureId;
	/**
	 */

	@Column(name = "from_organism_id")
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal fromOrganismId;
	/**
	 */

	@Column(name = "from_contig_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal fromContigId;
	/**
	 */

	@Column(name = "from_position")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal fromPosition;
	/**
	 */

	@Column(name = "from_refcall", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String fromRefcall;
	/**
	 */

	@Column(name = "nb_contig_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal nbContigId;
	/**
	 */

	@Column(name = "nb_position")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal nbPosition;
	/**
	 */

	@Column(name = "nb_refcall", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String nbRefcall;
	/**
	 */

	@Column(name = "ir64_contig_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal ir64ContigId;
	/**
	 */

	@Column(name = "ir64_position")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal ir64Position;
	/**
	 */

	@Column(name = "ir64_refcall", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String ir64Refcall;
	/**
	 */

	@Column(name = "rice9311_contig_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal rice9311ContigId;
	/**
	 */

	@Column(name = "rice9311_position")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal rice9311Position;
	/**
	 */

	@Column(name = "rice9311_refcall", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String rice9311Refcall;
	/**
	 */

	@Column(name = "dj123_contig_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal dj123ContigId;
	/**
	 */

	@Column(name = "dj123_position")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal dj123Position;
	/**
	 */

	@Column(name = "dj123_refcall", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String dj123Refcall;
	/**
	 */

	@Column(name = "kasalath_contig_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal kasalathContigId;
	/**
	 */

	@Column(name = "kasalath_position")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal kasalathPosition;
	/**
	 */

	@Column(name = "kasalath_refcall", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String kasalathRefcall;
	/**
	 */

	@Column(name = "type_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal typeId;
	/**
	 */

	@Column(name = "allele_index")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal alleleIndex;
	/**
	 */

	@Column(name = "nb_contig_name", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String nbContigName;
	/**
	 */

	@Column(name = "rice9311_contig_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String rice9311ContigName;
	/**
	 */

	@Column(name = "ir64_contig_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String ir64ContigName;
	/**
	 */

	@Column(name = "dj123_contig_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String dj123ContigName;
	/**
	 */

	@Column(name = "kasalath_contig_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String kasalathContigName;
	/**
	 */

	@Column(name = "nb_align_count")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal nbAlignCount;
	/**
	 */

	@Column(name = "rice9311_align_count")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal rice9311AlignCount;
	/**
	 */

	@Column(name = "ir64_align_count")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal ir64AlignCount;
	/**
	 */

	@Column(name = "dj123_align_count")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal dj123AlignCount;
	/**
	 */

	@Column(name = "kasalath_align_count")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal kasalathAlignCount;

	/**
	 */
	public void setSnpFeatureId(BigDecimal snpFeatureId) {
		this.snpFeatureId = snpFeatureId;
	}

	/**
	 */
	public BigDecimal getSnpFeatureId() {
		return this.snpFeatureId;
	}

	/**
	 */
	public void setFromOrganismId(BigDecimal fromOrganismId) {
		this.fromOrganismId = fromOrganismId;
	}

	/**
	 */
	public BigDecimal getFromOrganismId() {
		return this.fromOrganismId;
	}

	/**
	 */
	public void setFromContigId(BigDecimal fromContigId) {
		this.fromContigId = fromContigId;
	}

	/**
	 */
	public BigDecimal getFromContigId() {
		return this.fromContigId;
	}

	/**
	 */
	public void setFromPosition(BigDecimal fromPosition) {
		this.fromPosition = fromPosition;
	}

	/**
	 */
	public BigDecimal getFromPosition() {
		return this.fromPosition;
	}

	/**
	 */
	public void setFromRefcall(String fromRefcall) {
		this.fromRefcall = fromRefcall;
	}

	/**
	 */
	public String getFromRefcall() {
		return this.fromRefcall;
	}

	/**
	 */
	public void setNbContigId(BigDecimal nbContigId) {
		this.nbContigId = nbContigId;
	}

	/**
	 */
	public BigDecimal getNbContigId() {
		return this.nbContigId;
	}

	/**
	 */
	public void setNbPosition(BigDecimal nbPosition) {
		this.nbPosition = nbPosition;
	}

	/**
	 */
	public BigDecimal getNbPosition() {
		return this.nbPosition;
	}

	/**
	 */
	public void setNbRefcall(String nbRefcall) {
		this.nbRefcall = nbRefcall;
	}

	/**
	 */
	public String getNbRefcall() {
		return this.nbRefcall;
	}

	/**
	 */
	public void setIr64ContigId(BigDecimal ir64ContigId) {
		this.ir64ContigId = ir64ContigId;
	}

	/**
	 */
	public BigDecimal getIr64ContigId() {
		return this.ir64ContigId;
	}

	/**
	 */
	public void setIr64Position(BigDecimal ir64Position) {
		this.ir64Position = ir64Position;
	}

	/**
	 */
	public BigDecimal getIr64Position() {
		return this.ir64Position;
	}

	/**
	 */
	public void setIr64Refcall(String ir64Refcall) {
		this.ir64Refcall = ir64Refcall;
	}

	/**
	 */
	public String getIr64Refcall() {
		return this.ir64Refcall;
	}

	/**
	 */
	public void setRice9311ContigId(BigDecimal rice9311ContigId) {
		this.rice9311ContigId = rice9311ContigId;
	}

	/**
	 */
	public BigDecimal getRice9311ContigId() {
		return this.rice9311ContigId;
	}

	/**
	 */
	public void setRice9311Position(BigDecimal rice9311Position) {
		this.rice9311Position = rice9311Position;
	}

	/**
	 */
	public BigDecimal getRice9311Position() {
		return this.rice9311Position;
	}

	/**
	 */
	public void setRice9311Refcall(String rice9311Refcall) {
		this.rice9311Refcall = rice9311Refcall;
	}

	/**
	 */
	public String getRice9311Refcall() {
		return this.rice9311Refcall;
	}

	/**
	 */
	public void setDj123ContigId(BigDecimal dj123ContigId) {
		this.dj123ContigId = dj123ContigId;
	}

	/**
	 */
	public BigDecimal getDj123ContigId() {
		return this.dj123ContigId;
	}

	/**
	 */
	public void setDj123Position(BigDecimal dj123Position) {
		this.dj123Position = dj123Position;
	}

	/**
	 */
	public BigDecimal getDj123Position() {
		return this.dj123Position;
	}

	/**
	 */
	public void setDj123Refcall(String dj123Refcall) {
		this.dj123Refcall = dj123Refcall;
	}

	/**
	 */
	public String getDj123Refcall() {
		return this.dj123Refcall;
	}

	/**
	 */
	public void setKasalathContigId(BigDecimal kasalathContigId) {
		this.kasalathContigId = kasalathContigId;
	}

	/**
	 */
	public BigDecimal getKasalathContigId() {
		return this.kasalathContigId;
	}

	/**
	 */
	public void setKasalathPosition(BigDecimal kasalathPosition) {
		this.kasalathPosition = kasalathPosition;
	}

	/**
	 */
	public BigDecimal getKasalathPosition() {
		return this.kasalathPosition;
	}

	/**
	 */
	public void setKasalathRefcall(String kasalathRefcall) {
		this.kasalathRefcall = kasalathRefcall;
	}

	/**
	 */
	public String getKasalathRefcall() {
		return this.kasalathRefcall;
	}

	/**
	 */
	public void setTypeId(BigDecimal typeId) {
		this.typeId = typeId;
	}

	/**
	 */
	public BigDecimal getTypeId() {
		return this.typeId;
	}

	/**
	 */
	public void setAlleleIndex(BigDecimal alleleIndex) {
		this.alleleIndex = alleleIndex;
	}

	/**
	 */
	public BigDecimal getAlleleIndex() {
		return this.alleleIndex;
	}

	/**
	 */
	public void setNbContigName(String nbContigName) {
		this.nbContigName = nbContigName;
	}

	/**
	 */
	public String getNbContigName() {
		return this.nbContigName;
	}

	/**
	 */
	public void setRice9311ContigName(String rice9311ContigName) {
		this.rice9311ContigName = rice9311ContigName;
	}

	/**
	 */
	public String getRice9311ContigName() {
		return this.rice9311ContigName;
	}

	/**
	 */
	public void setIr64ContigName(String ir64ContigName) {
		this.ir64ContigName = ir64ContigName;
	}

	/**
	 */
	public String getIr64ContigName() {
		return this.ir64ContigName;
	}

	/**
	 */
	public void setDj123ContigName(String dj123ContigName) {
		this.dj123ContigName = dj123ContigName;
	}

	/**
	 */
	public String getDj123ContigName() {
		return this.dj123ContigName;
	}

	/**
	 */
	public void setKasalathContigName(String kasalathContigName) {
		this.kasalathContigName = kasalathContigName;
	}

	/**
	 */
	public String getKasalathContigName() {
		return this.kasalathContigName;
	}

	/**
	 */
	public void setNbAlignCount(BigDecimal nbAlignCount) {
		this.nbAlignCount = nbAlignCount;
	}

	/**
	 */
	public BigDecimal getNbAlignCount() {
		return this.nbAlignCount;
	}

	/**
	 */
	public void setRice9311AlignCount(BigDecimal rice9311AlignCount) {
		this.rice9311AlignCount = rice9311AlignCount;
	}

	/**
	 */
	public BigDecimal getRice9311AlignCount() {
		return this.rice9311AlignCount;
	}

	/**
	 */
	public void setIr64AlignCount(BigDecimal ir64AlignCount) {
		this.ir64AlignCount = ir64AlignCount;
	}

	/**
	 */
	public BigDecimal getIr64AlignCount() {
		return this.ir64AlignCount;
	}

	/**
	 */
	public void setDj123AlignCount(BigDecimal dj123AlignCount) {
		this.dj123AlignCount = dj123AlignCount;
	}

	/**
	 */
	public BigDecimal getDj123AlignCount() {
		return this.dj123AlignCount;
	}

	/**
	 */
	public void setKasalathAlignCount(BigDecimal kasalathAlignCount) {
		this.kasalathAlignCount = kasalathAlignCount;
	}

	/**
	 */
	public BigDecimal getKasalathAlignCount() {
		return this.kasalathAlignCount;
	}

	/**
	 */
	public MvConvertposAny2allrefs() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(MvConvertposAny2allrefs that) {
		setSnpFeatureId(that.getSnpFeatureId());
		setFromOrganismId(that.getFromOrganismId());
		setFromContigId(that.getFromContigId());
		setFromPosition(that.getFromPosition());
		setFromRefcall(that.getFromRefcall());
		setNbContigId(that.getNbContigId());
		setNbPosition(that.getNbPosition());
		setNbRefcall(that.getNbRefcall());
		setIr64ContigId(that.getIr64ContigId());
		setIr64Position(that.getIr64Position());
		setIr64Refcall(that.getIr64Refcall());
		setRice9311ContigId(that.getRice9311ContigId());
		setRice9311Position(that.getRice9311Position());
		setRice9311Refcall(that.getRice9311Refcall());
		setDj123ContigId(that.getDj123ContigId());
		setDj123Position(that.getDj123Position());
		setDj123Refcall(that.getDj123Refcall());
		setKasalathContigId(that.getKasalathContigId());
		setKasalathPosition(that.getKasalathPosition());
		setKasalathRefcall(that.getKasalathRefcall());
		setTypeId(that.getTypeId());
		setAlleleIndex(that.getAlleleIndex());
		setNbContigName(that.getNbContigName());
		setRice9311ContigName(that.getRice9311ContigName());
		setIr64ContigName(that.getIr64ContigName());
		setDj123ContigName(that.getDj123ContigName());
		setKasalathContigName(that.getKasalathContigName());
		setNbAlignCount(that.getNbAlignCount());
		setRice9311AlignCount(that.getRice9311AlignCount());
		setIr64AlignCount(that.getIr64AlignCount());
		setDj123AlignCount(that.getDj123AlignCount());
		setKasalathAlignCount(that.getKasalathAlignCount());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("snpFeatureId=[").append(snpFeatureId).append("] ");
		buffer.append("fromOrganismId=[").append(fromOrganismId).append("] ");
		buffer.append("fromContigId=[").append(fromContigId).append("] ");
		buffer.append("fromPosition=[").append(fromPosition).append("] ");
		buffer.append("fromRefcall=[").append(fromRefcall).append("] ");
		buffer.append("nbContigId=[").append(nbContigId).append("] ");
		buffer.append("nbPosition=[").append(nbPosition).append("] ");
		buffer.append("nbRefcall=[").append(nbRefcall).append("] ");
		buffer.append("ir64ContigId=[").append(ir64ContigId).append("] ");
		buffer.append("ir64Position=[").append(ir64Position).append("] ");
		buffer.append("ir64Refcall=[").append(ir64Refcall).append("] ");
		buffer.append("rice9311ContigId=[").append(rice9311ContigId).append("] ");
		buffer.append("rice9311Position=[").append(rice9311Position).append("] ");
		buffer.append("rice9311Refcall=[").append(rice9311Refcall).append("] ");
		buffer.append("dj123ContigId=[").append(dj123ContigId).append("] ");
		buffer.append("dj123Position=[").append(dj123Position).append("] ");
		buffer.append("dj123Refcall=[").append(dj123Refcall).append("] ");
		buffer.append("kasalathContigId=[").append(kasalathContigId).append("] ");
		buffer.append("kasalathPosition=[").append(kasalathPosition).append("] ");
		buffer.append("kasalathRefcall=[").append(kasalathRefcall).append("] ");
		buffer.append("typeId=[").append(typeId).append("] ");
		buffer.append("alleleIndex=[").append(alleleIndex).append("] ");
		buffer.append("nbContigName=[").append(nbContigName).append("] ");
		buffer.append("rice9311ContigName=[").append(rice9311ContigName).append("] ");
		buffer.append("ir64ContigName=[").append(ir64ContigName).append("] ");
		buffer.append("dj123ContigName=[").append(dj123ContigName).append("] ");
		buffer.append("kasalathContigName=[").append(kasalathContigName).append("] ");
		buffer.append("nbAlignCount=[").append(nbAlignCount).append("] ");
		buffer.append("rice9311AlignCount=[").append(rice9311AlignCount).append("] ");
		buffer.append("ir64AlignCount=[").append(ir64AlignCount).append("] ");
		buffer.append("dj123AlignCount=[").append(dj123AlignCount).append("] ");
		buffer.append("kasalathAlignCount=[").append(kasalathAlignCount).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((snpFeatureId == null) ? 0 : snpFeatureId.hashCode()));
		result = (int) (prime * result + ((fromOrganismId == null) ? 0 : fromOrganismId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof MvConvertposAny2allrefs))
			return false;
		MvConvertposAny2allrefs equalCheck = (MvConvertposAny2allrefs) obj;
		if ((snpFeatureId == null && equalCheck.snpFeatureId != null) || (snpFeatureId != null && equalCheck.snpFeatureId == null))
			return false;
		if (snpFeatureId != null && !snpFeatureId.equals(equalCheck.snpFeatureId))
			return false;
		if ((fromOrganismId == null && equalCheck.fromOrganismId != null) || (fromOrganismId != null && equalCheck.fromOrganismId == null))
			return false;
		if (fromOrganismId != null && !fromOrganismId.equals(equalCheck.fromOrganismId))
			return false;
		return true;
	}

	@Override
	public BigDecimal getOrganism() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getFileId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContigName(String organism) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getPosition(String organism) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAlignCount(String organism) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAllele(String organism) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRefnuc(String refnuc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getContig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRefnuc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getChr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getNBContigName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
}
