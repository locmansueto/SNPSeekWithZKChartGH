package org.irri.iric.portal.chado.postgres.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.LocusCvTermPath;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVLocusCvtermpaths", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath"),
		@NamedQuery(name = "findVLocusCvtermpathByCommonName", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath where myVLocusCvtermpath.commonName = ?1"),
		@NamedQuery(name = "findVLocusCvtermpathByCommonNameContaining", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath where myVLocusCvtermpath.commonName like ?1"),
		@NamedQuery(name = "findVLocusCvtermpathByContigId", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath where myVLocusCvtermpath.contigId = ?1"),
		@NamedQuery(name = "findVLocusCvtermpathByContigName", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath where myVLocusCvtermpath.contigName = ?1"),
		@NamedQuery(name = "findVLocusCvtermpathByContigNameContaining", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath where myVLocusCvtermpath.contigName like ?1"),
		@NamedQuery(name = "findVLocusCvtermpathByCvId", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath where myVLocusCvtermpath.cvId = ?1"),
		@NamedQuery(name = "findVLocusCvtermpathByCvName", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath where myVLocusCvtermpath.cvName = ?1"),
		@NamedQuery(name = "findVLocusCvtermpathByCvNameContaining", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath where myVLocusCvtermpath.cvName like ?1"),
		@NamedQuery(name = "findVLocusCvtermpathByCvtermId", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath where myVLocusCvtermpath.cvtermId = ?1"),
		@NamedQuery(name = "findVLocusCvtermpathByFeatureId", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath where myVLocusCvtermpath.featureId = ?1"),
		@NamedQuery(name = "findVLocusCvtermpathByFmax", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath where myVLocusCvtermpath.fmax = ?1"),
		@NamedQuery(name = "findVLocusCvtermpathByFmin", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath where myVLocusCvtermpath.fmin = ?1"),
		@NamedQuery(name = "findVLocusCvtermpathByName", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath where myVLocusCvtermpath.name = ?1"),
		@NamedQuery(name = "findVLocusCvtermpathByNameContaining", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath where myVLocusCvtermpath.name like ?1"),
		@NamedQuery(name = "findVLocusCvtermpathByNotes", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath where myVLocusCvtermpath.notes = ?1"),
		@NamedQuery(name = "findVLocusCvtermpathByNotesContaining", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath where myVLocusCvtermpath.notes like ?1"),
		@NamedQuery(name = "findVLocusCvtermpathByObjAcc", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath where myVLocusCvtermpath.objAcc = ?1"),
		@NamedQuery(name = "findVLocusCvtermpathByObjAccContaining", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath where myVLocusCvtermpath.objAcc like ?1"),
		@NamedQuery(name = "findVLocusCvtermpathByObjCvterm", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath where myVLocusCvtermpath.objCvterm = ?1"),
		@NamedQuery(name = "findVLocusCvtermpathByObjCvtermContaining", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath where myVLocusCvtermpath.objCvterm like ?1"),
		@NamedQuery(name = "findVLocusCvtermpathByOrganismId", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath where myVLocusCvtermpath.organismId = ?1"),
		@NamedQuery(name = "findVLocusCvtermpathByPathdistance", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath where myVLocusCvtermpath.pathdistance = ?1"),
		@NamedQuery(name = "findVLocusCvtermpathByPrimaryKey", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath where myVLocusCvtermpath.featureId = ?1"),
		@NamedQuery(name = "findVLocusCvtermpathByStrand", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath where myVLocusCvtermpath.strand = ?1"),
		@NamedQuery(name = "findVLocusCvtermpathBySubjAcc", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath where myVLocusCvtermpath.subjAcc = ?1"),
		@NamedQuery(name = "findVLocusCvtermpathBySubjAccContaining", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath where myVLocusCvtermpath.subjAcc like ?1"),
		@NamedQuery(name = "findVLocusCvtermpathBySubjCvterm", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath where myVLocusCvtermpath.subjCvterm = ?1"),
		@NamedQuery(name = "findVLocusCvtermpathBySubjCvtermContaining", query = "select myVLocusCvtermpath from VLocusCvtermpath myVLocusCvtermpath where myVLocusCvtermpath.subjCvterm like ?1") })
@Table(schema = "public", name = "v_locus_cvtermpath")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/postgres/domain", name = "VLocusCvtermpath")
public class VLocusCvtermpath implements Serializable, LocusCvTermPath {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "feature_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer featureId;
	/**
	 */

	@Column(name = "name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 */

	@Column(name = "fmin")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer fmin;
	/**
	 */

	@Column(name = "fmax")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer fmax;
	/**
	 */

	@Column(name = "strand")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer strand;
	/**
	 */

	@Column(name = "contig_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer contigId;
	/**
	 */

	@Column(name = "contig_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String contigName;
	/**
	 */

	@Column(name = "notes")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String notes;
	/**
	 */

	@Column(name = "cv_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer cvId;
	/**
	 */

	@Column(name = "cv_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String cvName;
	/**
	 */

	@Column(name = "cvterm_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer cvtermId;
	/**
	 */

	@Column(name = "subj_acc")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String subjAcc;
	/**
	 */

	@Column(name = "subj_cvterm", length = 1024)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String subjCvterm;
	/**
	 */

	@Column(name = "obj_acc")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String objAcc;
	/**
	 */

	@Column(name = "obj_cvterm", length = 1024)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String objCvterm;
	/**
	 */

	@Column(name = "pathdistance")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer pathdistance;
	/**
	 */

	@Column(name = "organism_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer organismId;
	/**
	 */

	@Column(name = "common_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String commonName;

	/**
	 */
	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}

	/**
	 */
	public Integer getFeatureId() {
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
	public void setContigId(Integer contigId) {
		this.contigId = contigId;
	}

	/**
	 */
	public Integer getContigId() {
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
	public void setCvId(Integer cvId) {
		this.cvId = cvId;
	}

	/**
	 */
	public Integer getCvId() {
		return this.cvId;
	}

	/**
	 */
	public void setCvName(String cvName) {
		this.cvName = cvName;
	}

	/**
	 */
	public String getCvName() {
		return this.cvName;
	}

	/**
	 */
	public void setCvtermId(Integer cvtermId) {
		this.cvtermId = cvtermId;
	}

	/**
	 */
	public Integer getCvtermId() {
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
	public void setOrganismId(Integer organismId) {
		this.organismId = organismId;
	}

	/**
	 */
	public Integer getOrganismId() {
		return this.organismId;
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
	public VLocusCvtermpath() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VLocusCvtermpath that) {
		setFeatureId(that.getFeatureId());
		setName(that.getName());
		setFmin(that.getFmin());
		setFmax(that.getFmax());
		setStrand(that.getStrand());
		setContigId(that.getContigId());
		setContigName(that.getContigName());
		setNotes(that.getNotes());
		setCvId(that.getCvId());
		setCvName(that.getCvName());
		setCvtermId(that.getCvtermId());
		setSubjAcc(that.getSubjAcc());
		setSubjCvterm(that.getSubjCvterm());
		setObjAcc(that.getObjAcc());
		setObjCvterm(that.getObjCvterm());
		setPathdistance(that.getPathdistance());
		setOrganismId(that.getOrganismId());
		setCommonName(that.getCommonName());
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
		buffer.append("cvName=[").append(cvName).append("] ");
		buffer.append("cvtermId=[").append(cvtermId).append("] ");
		buffer.append("subjAcc=[").append(subjAcc).append("] ");
		buffer.append("subjCvterm=[").append(subjCvterm).append("] ");
		buffer.append("objAcc=[").append(objAcc).append("] ");
		buffer.append("objCvterm=[").append(objCvterm).append("] ");
		buffer.append("pathdistance=[").append(pathdistance).append("] ");
		buffer.append("organismId=[").append(organismId).append("] ");
		buffer.append("commonName=[").append(commonName).append("] ");

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
	/*
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VLocusCvtermpath))
			return false;
		VLocusCvtermpath equalCheck = (VLocusCvtermpath) obj;
		if ((featureId == null && equalCheck.featureId != null) || (featureId != null && equalCheck.featureId == null))
			return false;
		if (featureId != null && !featureId.equals(equalCheck.featureId))
			return false;
		return true;
	}
	*/
	


	@Override
	public boolean equals(Object obj) {

		return compareTo((LocusCvTermPath)obj)==0;
		
		/*if (!(obj instanceof VLocusCvtermCvtermpath))
			return false;
		VLocusCvtermCvtermpath c= (VLocusCvtermCvtermpath)obj;
		return c.getUniquename().equals(this.getUniquename()) && c.getOrganismId().equals(this.getOrganismId());
		*/
	}

	/**
	 */
//	public boolean equals(Object obj) {
//		if (obj == this)
//			return true;
//		if (!(obj instanceof VLocusCvtermCvtermpath))
//			return false;
//		VLocusCvtermCvtermpath equalCheck = (VLocusCvtermCvtermpath) obj;
//		if ((featureId == null && equalCheck.featureId != null) || (featureId != null && equalCheck.featureId == null))
//			return false;
//		if (featureId != null && !featureId.equals(equalCheck.featureId))
//			return false;
//		if ((cvtermId == null && equalCheck.cvtermId != null) || (cvtermId != null && equalCheck.cvtermId == null))
//			return false;
//		if (cvtermId != null && !cvtermId.equals(equalCheck.cvtermId))
//			return false;
//		if ((organismId == null && equalCheck.organismId != null) || (organismId != null && equalCheck.organismId == null))
//			return false;
//		if (organismId != null && !organismId.equals(equalCheck.organismId))
//			return false;
//		return true;
//	}
	
	
	

	@Override
	public BigDecimal getCvTermId() {
		// TODO Auto-generated method stub
		return BigDecimal.valueOf(this.cvtermId);
	}

	@Override
	public int compareTo(LocusCvTermPath o) {
		// TODO Auto-generated method stub
			// TODO Auto-generated method stub
		//VLocusCvtermCvtermpath l1=(VLocusCvtermCvtermpath)this;
		//VLocusCvtermCvtermpath l2=(VLocusCvtermCvtermpath)o;
		int ret = getContig().compareTo(o.getContig());
		if(ret!=0) return ret;
		ret = getFmin().compareTo(o.getFmin());
		if(ret!=0) return ret;
		ret = getFmax().compareTo(o.getFmax());
		return 0;
	}

	@Override
	public String getDefinition() {
		// TODO Auto-generated method stub
		return this.subjCvterm;
	}

	@Override
	public String getAccession() {
		// TODO Auto-generated method stub
		return this.subjAcc;
	}

	@Override
	public String getUniquename() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public String getChr() {
		// TODO Auto-generated method stub
		return this.contigName;
	}

	@Override
	public String getContig() {
		// TODO Auto-generated method stub
		return this.contigName;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		String mynotes = "no description";
		if(this.notes!=null) mynotes=this.notes;
		if(pathdistance>10000)
			return  "GO:" + this.subjAcc + " " + this.subjCvterm +  " -*-> GO:" + this.objAcc + " " + this.objCvterm + " (" + mynotes + ")" ;
		else if(pathdistance==0)
			 return "GO:" + this.subjAcc + " " + this.subjCvterm +  " (" + mynotes + ")" ;
		else
			return  "GO:" + this.subjAcc + " " + this.subjCvterm +  " --" + pathdistance + "-> GO:" + this.objAcc + " " + this.objCvterm + " (" + mynotes + ")" ;
	}

	
}
