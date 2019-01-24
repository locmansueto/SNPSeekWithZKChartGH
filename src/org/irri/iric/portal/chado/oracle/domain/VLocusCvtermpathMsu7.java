package org.irri.iric.portal.chado.oracle.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.domain.CvTerm;
import org.irri.iric.portal.domain.MergedLoci;

/**
 */

@Entity
@NamedQueries({
		/*
		 * @NamedQuery(name = "findAllVLocusCvtermpathIrics", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric"
		 * ), // @NamedQuery(name = "findVLocusCvtermpathIricByCommonName", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.commonName = ?1"
		 * ), // @NamedQuery(name = "findVLocusCvtermpathIricByCommonNameContaining",
		 * query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.commonName like ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByContigId", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.contigId = ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByContigName", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.contigName = ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByContigNameContaining", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.contigName like ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByCvId", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.cvId = ?1"
		 * ), // @NamedQuery(name = "findVLocusCvtermpathIricByCvName", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.cvName = ?1"
		 * ), // @NamedQuery(name = "findVLocusCvtermpathIricByCvNameContaining", query
		 * =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.cvName like ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByCvtermId", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.cvtermId = ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByDb", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.db = ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByDbContaining", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.db like ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByFeatureId", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.featureId = ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByFgenesh", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.fgenesh = ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByFgeneshContaining", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.fgenesh like ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByFmax", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.fmax = ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByFmin", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.fmin = ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByIric", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.iric = ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByIricContaining", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.iric like ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByMsu7", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.msu7 = ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByMsu7Containing", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.msu7 like ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByName", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.name = ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByNameContaining", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.name like ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByNotes", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.notes = ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByNotesContaining", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.notes like ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByObjAcc", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.objAcc = ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByObjAccContaining", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.objAcc like ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByObjCvterm", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.objCvterm = ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByObjCvtermContaining", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.objCvterm like ?1"
		 * ),
		 */
		@NamedQuery(name = "findVLocusCvtermpathMsu7ByObjCvtermCvOrg", query = "select myVLocusCvtermpathIric from VLocusCvtermpathMsu7 myVLocusCvtermpathIric where myVLocusCvtermpathIric.objCvterm = ?1 and myVLocusCvtermpathIric.cvId=?2 and myVLocusCvtermpathIric.organismId=?3"),
		@NamedQuery(name = "findVLocusCvtermpathMsu7ByCvOrgInFeatureId", query = "select myVLocusCvtermpathIric from VLocusCvtermpathMsu7 myVLocusCvtermpathIric where myVLocusCvtermpathIric.cvId=?1 and myVLocusCvtermpathIric.organismId=?2 and myVLocusCvtermpathIric.featureId in (?3)"),

		/*
		 * @NamedQuery(name = "findVLocusCvtermpathIricByOrganismId", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.organismId = ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByPathdistance", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.pathdistance = ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByPrimaryKey", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.featureId = ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByRapPred", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.rapPred = ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByRapPredContaining", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.rapPred like ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByRapRep", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.rapRep = ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByRapRepContaining", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.rapRep like ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricByStrand", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.strand = ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricBySubjAcc", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.subjAcc = ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricBySubjAccContaining", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.subjAcc like ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricBySubjCvterm", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.subjCvterm = ?1"
		 * ),
		 * 
		 * @NamedQuery(name = "findVLocusCvtermpathIricBySubjCvtermContaining", query =
		 * "select myVLocusCvtermpathIric from VLocusCvtermpathIric myVLocusCvtermpathIric where myVLocusCvtermpathIric.subjCvterm like ?1"
		 * )
		 */ })
@Table(name = "V_LOCUS_CVTERM_CVTERMPATH_MSU7")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/oracle/domain", name = "VLocusCvtermpathMsu7")
public class VLocusCvtermpathMsu7 implements Serializable, MergedLoci, CvTerm, Comparable {
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

	@Column(name = "CONTIG_ID", precision = 10)
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

	@Column(name = "NOTES", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String notes;
	/**
	 */

	@Column(name = "CV_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal cvId;
	/**
	 */

	// @Column(name = "CV_NAME", length = 1020)
	// @Basic(fetch = FetchType.EAGER)
	// @XmlElement
	// String cvName;
	// /**
	// */

	@Column(name = "DB", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String db;
	/**
	 */

	@Column(name = "CVTERM_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal cvtermId;
	/**
	 */

	@Column(name = "SUBJ_ACC", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String subjAcc;
	/**
	 */

	@Column(name = "SUBJ_CVTERM", length = 1024)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String subjCvterm;
	/**
	 */

	@Column(name = "OBJ_ACC", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String objAcc;
	/**
	 */

	@Column(name = "OBJ_CVTERM", length = 1024)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String objCvterm;
	/**
	 */

	@Column(name = "PATHDISTANCE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer pathdistance;
	/**
	 */

	@Column(name = "ORGANISM_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal organismId;
	/**
	 */
	//
	// @Column(name = "COMMON_NAME", length = 1020)
	// @Basic(fetch = FetchType.EAGER)
	// @XmlElement
	// String commonName;
	// /**
	// */

	@Column(name = "IRIC", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String iric;
	/**
	 */

	@Column(name = "MSU7", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String msu7;
	/**
	 */

	@Column(name = "RAP_REP", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String rapRep;
	/**
	 */

	@Column(name = "RAP_PRED", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String rapPred;
	/**
	 */

	@Column(name = "FGENESH", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String fgenesh;

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
		return this.fmax.intValue();
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
	public void setCvId(BigDecimal cvId) {
		this.cvId = cvId;
	}

	/**
	 */
	public BigDecimal getCvId() {
		return this.cvId;
	}

	// /**
	// */
	// public void setCvName(String cvName) {
	// this.cvName = cvName;
	// }
	//
	// /**
	// */
	// public String getCvName() {
	// return this.cvName;
	// }

	/**
	 */
	public void setDb(String db) {
		this.db = db;
	}

	/**
	 */
	public String getDb() {
		return this.db;
	}

	/**
	 */
	public void setCvtermId(BigDecimal cvtermId) {
		this.cvtermId = cvtermId;
	}

	/**
	 */
	public BigDecimal getCvtermId() {
		return this.cvtermId;
	}

	/**
	 */
	public void setSubjAcc(String subjAcc) {
		this.subjAcc = subjAcc;
	}

	/**
	 */
	public String getSubjAcc() {
		return this.subjAcc;
	}

	/**
	 */
	public void setSubjCvterm(String subjCvterm) {
		this.subjCvterm = subjCvterm;
	}

	/**
	 */
	public String getSubjCvterm() {
		return this.subjCvterm;
	}

	/**
	 */
	public void setObjAcc(String objAcc) {
		this.objAcc = objAcc;
	}

	/**
	 */
	public String getObjAcc() {
		return this.objAcc;
	}

	/**
	 */
	public void setObjCvterm(String objCvterm) {
		this.objCvterm = objCvterm;
	}

	/**
	 */
	public String getObjCvterm() {
		return this.objCvterm;
	}

	/**
	 */
	public void setPathdistance(Integer pathdistance) {
		this.pathdistance = pathdistance;
	}

	/**
	 */
	public Integer getPathdistance() {
		return this.pathdistance;
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
	//
	// /**
	// */
	// public void setCommonName(String commonName) {
	// this.commonName = commonName;
	// }
	//
	// /**
	// */
	// public String getCommonName() {
	// return this.commonName;
	// }

	/**
	 */
	public void setIric(String iric) {
		this.iric = iric;
	}

	/**
	 */
	public String getIric() {
		return this.iric;
	}

	/**
	 */
	public void setMsu7(String msu7) {
		this.msu7 = msu7;
	}

	/**
	 */
	public String getMsu7() {
		return this.msu7;
	}

	/**
	 */
	public void setRapRep(String rapRep) {
		this.rapRep = rapRep;
	}

	/**
	 */
	public String getRapRep() {
		return this.rapRep;
	}

	/**
	 */
	public void setRapPred(String rapPred) {
		this.rapPred = rapPred;
	}

	/**
	 */
	public String getRapPred() {
		return this.rapPred;
	}

	/**
	 */
	public void setFgenesh(String fgenesh) {
		this.fgenesh = fgenesh;
	}

	/**
	 */
	public String getFgenesh() {
		return this.fgenesh;
	}

	/**
	 */
	public VLocusCvtermpathMsu7() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VLocusCvtermpathMsu7 that) {
		setFeatureId(that.getFeatureId());
		setName(that.getName());
		setFmin(that.getFmin());
		setFmax(that.getFmax());
		setStrand(that.getStrand());
		setContigId(that.getContigId());
		setContigName(that.getContigName());
		setNotes(that.getNotes());
		setCvId(that.getCvId());
		// setCvName(that.getCvName());
		setDb(that.getDb());
		setCvtermId(that.getCvtermId());
		setSubjAcc(that.getSubjAcc());
		setSubjCvterm(that.getSubjCvterm());
		setObjAcc(that.getObjAcc());
		setObjCvterm(that.getObjCvterm());
		setPathdistance(that.getPathdistance());
		setOrganismId(that.getOrganismId());
		// setCommonName(that.getCommonName());
		setIric(that.getIric());
		setMsu7(that.getMsu7());
		setRapRep(that.getRapRep());
		setRapPred(that.getRapPred());
		setFgenesh(that.getFgenesh());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("featureId=[").append(featureId).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("fmin=[").append(fmin).append("] ");
		buffer.append("fmax=[").append(fmax).append("] ");
		buffer.append("strand=[").append(strand).append("] ");
		buffer.append("contigId=[").append(contigId).append("] ");
		buffer.append("contigName=[").append(contigName).append("] ");
		buffer.append("notes=[").append(notes).append("] ");
		buffer.append("cvId=[").append(cvId).append("] ");
		// buffer.append("cvName=[").append(cvName).append("] ");
		buffer.append("db=[").append(db).append("] ");
		buffer.append("cvtermId=[").append(cvtermId).append("] ");
		buffer.append("subjAcc=[").append(subjAcc).append("] ");
		buffer.append("subjCvterm=[").append(subjCvterm).append("] ");
		buffer.append("objAcc=[").append(objAcc).append("] ");
		buffer.append("objCvterm=[").append(objCvterm).append("] ");
		buffer.append("pathdistance=[").append(pathdistance).append("] ");
		buffer.append("organismId=[").append(organismId).append("] ");
		// buffer.append("commonName=[").append(commonName).append("] ");
		buffer.append("iric=[").append(iric).append("] ");
		buffer.append("msu7=[").append(msu7).append("] ");
		buffer.append("rapRep=[").append(rapRep).append("] ");
		buffer.append("rapPred=[").append(rapPred).append("] ");
		buffer.append("fgenesh=[").append(fgenesh).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((featureId == null) ? 0 : featureId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		return compareTo(obj) == 0;
		/*
		 * if (obj == this) return true; if (!(obj instanceof VLocusCvtermpathIric))
		 * return false; VLocusCvtermpathIric equalCheck = (VLocusCvtermpathIric) obj;
		 * if ((featureId == null && equalCheck.featureId != null) || (featureId != null
		 * && equalCheck.featureId == null)) return false; if (featureId != null &&
		 * !featureId.equals(equalCheck.featureId)) return false; return true;
		 */
	}

	@Override
	public int compareTo(Object o) {
		
		VLocusCvtermpathMsu7 l1 = (VLocusCvtermpathMsu7) this;
		VLocusCvtermpathMsu7 l2 = (VLocusCvtermpathMsu7) o;
		int ret = l1.getContig().compareTo(l2.getContig());
		if (ret != 0)
			return ret;
		ret = l1.getFmin().compareTo(l2.getFmin());
		if (ret != 0)
			return ret;
		ret = l1.getFmax().compareTo(l2.getFmax());
		if (ret != 0)
			return ret;
		ret = l1.getStrand().compareTo(l2.getStrand());
		return 0;
	}

	@Override
	public BigDecimal getCvTermId() {
		
		return this.cvtermId;
	}

	@Override
	public String getDefinition() {
		
		return subjCvterm;
	}

	@Override
	public String getAccession() {
		
		return subjAcc;
	}

	@Override
	public String getUniquename() {
		
		return name;
	}

	@Override
	public Long getChr() {
		
		return Long.valueOf(AppContext.guessChrFromString(getContig()));
		// return Long.valueOf(getContig());
	}

	@Override
	public String getContig() {
		
		return contigName;
	}

	@Override
	public String getDescription() {
		
		String mynotes = "no description";
		if (this.notes != null)
			mynotes = this.notes;
		String strdb = "";
		if (db == null || db.isEmpty()) {

		} else
			strdb = db.toUpperCase() + ":";
		if (pathdistance > 10000)
			return strdb + this.subjAcc + " " + this.subjCvterm + " -*-> " + strdb + this.objAcc + " " + this.objCvterm
					+ " (" + mynotes + ")";
		else if (pathdistance == 0)
			return strdb + this.subjAcc + " " + this.subjCvterm + " (" + mynotes + ")";
		else
			return strdb + this.subjAcc + " " + this.subjCvterm + " --" + pathdistance + "-> " + strdb + this.objAcc
					+ " " + this.objCvterm + " (" + mynotes + ")";

	}

	@Override
	public String getIRICName() {
		
		return this.iric;
	}

	@Override
	public String getMSU7Name() {
		
		return this.msu7;
	}

	@Override
	public String getRAPPredName() {
		
		return this.rapPred;
	}

	@Override
	public String getRAPRepName() {
		
		return this.rapRep;
	}

	@Override
	public String getFGeneshName() {
		
		return this.fgenesh;
	}

	@Override
	public Set<String> getOverlappingGenes() {
		

		StringBuffer buff = new StringBuffer();
		buff.append(this.name);
		if (getMSU7Name() != null && !getMSU7Name().isEmpty())
			buff.append(" " + getMSU7Name());
		if (getRAPRepName() != null && !getRAPRepName().isEmpty())
			buff.append(" " + getRAPRepName());
		if (getRAPPredName() != null && !getRAPPredName().isEmpty())
			buff.append(" " + getRAPPredName());

		Set locnamesets = new HashSet();
		String names[] = buff.toString().trim().split("\\s+|,");
		for (int i = 0; i < names.length; i++)
			locnamesets.add(names[i].trim());
		locnamesets.remove("");
		return locnamesets;

	}

	@Override
	public String getFeatureType() {
		// TODO Auto-generated method stub
		return null;
	}

}
