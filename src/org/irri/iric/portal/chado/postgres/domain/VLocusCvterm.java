package org.irri.iric.portal.chado.postgres.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.LocusCvTerm;
import org.irri.iric.portal.domain.LocusCvTermPath;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVLocusCvterms", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm"),
		@NamedQuery(name = "findVLocusCvtermByCommonName", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.commonName = ?1"),
		@NamedQuery(name = "findVLocusCvtermByCommonNameContaining", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.commonName like ?1"),
		@NamedQuery(name = "findVLocusCvtermByContigId", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.contigId = ?1"),
		@NamedQuery(name = "findVLocusCvtermByContigName", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.contigName = ?1"),
		@NamedQuery(name = "findVLocusCvtermByContigNameContaining", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.contigName like ?1"),
		@NamedQuery(name = "findVLocusCvtermByCvId", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.cvId = ?1"),
		@NamedQuery(name = "findVLocusCvtermByCvName", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.cvName = ?1"),
		@NamedQuery(name = "findVLocusCvtermByCvNameContaining", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.cvName like ?1"),
		@NamedQuery(name = "findVLocusCvtermByCvtermId", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.cvtermId = ?1"),
		@NamedQuery(name = "findVLocusCvtermByFeatureId", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.featureId = ?1"),
		@NamedQuery(name = "findVLocusCvtermByFmax", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.fmax = ?1"),
		@NamedQuery(name = "findVLocusCvtermByFmin", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.fmin = ?1"),
		@NamedQuery(name = "findVLocusCvtermByName", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.name = ?1"),
		@NamedQuery(name = "findVLocusCvtermByNameContaining", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.name like ?1"),
		@NamedQuery(name = "findVLocusCvtermByNotes", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.notes = ?1"),
		@NamedQuery(name = "findVLocusCvtermByNotesContaining", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.notes like ?1"),
		@NamedQuery(name = "findVLocusCvtermByObjAcc", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.objAcc = ?1"),
		@NamedQuery(name = "findVLocusCvtermByObjAccContaining", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.objAcc like ?1"),
		@NamedQuery(name = "findVLocusCvtermByObjCvterm", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.objCvterm = ?1"),
		@NamedQuery(name = "findVLocusCvtermByObjCvtermContaining", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.objCvterm like ?1"),
		@NamedQuery(name = "findVLocusCvtermByOrganismId", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.organismId = ?1"),
		@NamedQuery(name = "findVLocusCvtermByPathdistance", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.pathdistance = ?1"),
		@NamedQuery(name = "findVLocusCvtermByPrimaryKey", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.featureId = ?1"),
		
		@NamedQuery(name = "findVLocusCvtermByCvtermOrganismIdCvid", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.objCvterm=?1 and myVLocusCvterm.organismId = ?2 and myVLocusCvterm.cvId=?3"),

		
		@NamedQuery(name = "findVLocusCvtermByStrand", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.strand = ?1"),
		@NamedQuery(name = "findVLocusCvtermBySubjAcc", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.subjAcc = ?1"),
		@NamedQuery(name = "findVLocusCvtermBySubjAccContaining", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.subjAcc like ?1"),
		@NamedQuery(name = "findVLocusCvtermBySubjCvterm", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.subjCvterm = ?1"),
		@NamedQuery(name = "findVLocusCvtermBySubjCvtermContaining", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.subjCvterm like ?1") })
@Table(schema = "public", name = "v_locus_cvterm")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/postgres/domain", name = "VLocusCvterm")
public class VLocusCvterm implements Serializable , LocusCvTerm {
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
	public VLocusCvterm() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VLocusCvterm that) {
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
		if (!(obj instanceof VLocusCvterm))
			return false;
		VLocusCvterm equalCheck = (VLocusCvterm) obj;
		if ((featureId == null && equalCheck.featureId != null) || (featureId != null && equalCheck.featureId == null))
			return false;
		if (featureId != null && !featureId.equals(equalCheck.featureId))
			return false;
		return true;
	}
	*/
	


	@Override
	public boolean equals(Object obj) {

		return compareTo((LocusCvTerm)obj)==0;
		
		/*if (!(obj instanceof VLocusCvtermCvtermpath))
			return false;
		VLocusCvtermCvtermpath c= (VLocusCvtermCvtermpath)obj;
		return c.getUniquename().equals(this.getUniquename()) && c.getOrganismId().equals(this.getOrganismId());
		*/
	}

	@Override
	public BigDecimal getCvTermId() {
		// TODO Auto-generated method stub
		return BigDecimal.valueOf(this.cvtermId);
	}

	@Override
	public int compareTo(LocusCvTerm o) {
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
			return   this.subjAcc + " " + this.subjCvterm +  " -*-> " + this.objAcc + " " + this.objCvterm + " (" + mynotes + ")" ;
		else if(pathdistance==0)
			 return  this.subjAcc + " " + this.subjCvterm +  " (" + mynotes + ")" ;
		else
			return  this.subjAcc + " " + this.subjCvterm +  " --" + pathdistance + "-> " + this.objAcc + " " + this.objCvterm + " (" + mynotes + ")" ;

		
		/*
		if(pathdistance>10000)
			return  "GO:" + this.subjAcc + " " + this.subjCvterm +  " -*-> GO:" + this.objAcc + " " + this.objCvterm + " (" + mynotes + ")" ;
		else if(pathdistance==0)
			 return "GO:" + this.subjAcc + " " + this.subjCvterm +  " (" + mynotes + ")" ;
		else
			return  "GO:" + this.subjAcc + " " + this.subjCvterm +  " --" + pathdistance + "-> GO:" + this.objAcc + " " + this.objCvterm + " (" + mynotes + ")" ;
			*/
	}

	
	
}
