package org.irri.iric.portal.chado.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.CvTerm;
import org.irri.iric.portal.domain.Locus;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVLocusCvterms", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm"),
		@NamedQuery(name = "findVLocusCvtermByAccession", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.accession = ?1"),
		@NamedQuery(name = "findVLocusCvtermByAccessionContaining", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.accession like ?1"),
		@NamedQuery(name = "findVLocusCvtermByCommonName", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.commonName = ?1"),
		@NamedQuery(name = "findVLocusCvtermByCommonNameContaining", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.commonName like ?1"),
		@NamedQuery(name = "findVLocusCvtermByContigId", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.contigId = ?1"),
		@NamedQuery(name = "findVLocusCvtermByContigName", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.contigName = ?1"),
		@NamedQuery(name = "findVLocusCvtermByContigNameContaining", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.contigName like ?1"),
		@NamedQuery(name = "findVLocusCvtermByCvName", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.cvName = ?1"),
		@NamedQuery(name = "findVLocusCvtermByCvNameContaining", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.cvName like ?1"),
		@NamedQuery(name = "findVLocusCvtermByCvterm", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.cvterm = ?1"),
		@NamedQuery(name = "findVLocusCvtermByCvtermContaining", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.cvterm like ?1"),
		@NamedQuery(name = "findVLocusCvtermByCvtermId", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.cvtermId = ?1"),
		@NamedQuery(name = "findVLocusCvtermByFeatureId", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.featureId = ?1"),
		@NamedQuery(name = "findVLocusCvtermByFmax", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.fmax = ?1"),
		@NamedQuery(name = "findVLocusCvtermByFmin", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.fmin = ?1"),
		@NamedQuery(name = "findVLocusCvtermByName", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where lower(myVLocusCvterm.name) = lower(?1)"),
		@NamedQuery(name = "findVLocusCvtermByNameContaining", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where lower(myVLocusCvterm.name) like lower(?1)"),
		@NamedQuery(name = "findVLocusCvtermByOrganismId", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.organismId = ?1"),
		@NamedQuery(name = "findVLocusCvtermByPrimaryKey", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.featureId = ?1"),
		@NamedQuery(name = "findVLocusCvtermByStrand", query = "select myVLocusCvterm from VLocusCvterm myVLocusCvterm where myVLocusCvterm.strand = ?1") })
@Table(schema = "IRIC", name = "V_LOCUS_CVTERM")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VLocusCvterm")
public class VLocusCvterm implements Serializable, Locus, CvTerm {
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

	@Column(name = "NAME", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 */

	@Column(name = "FMIN", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal fmin;
	/**
	 */

	@Column(name = "FMAX", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal fmax;
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

	@Column(name = "CVTERM_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal cvtermId;
	/**
	 */

	@Column(name = "ACCESSION")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String accession;
	/**
	 */

	@Column(name = "CV_NAME")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String cvName;
	/**
	 */

	@Column(name = "CVTERM", length = 1024)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String cvterm;
	/**
	 */

	@Column(name = "ORGANISM_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal organismId;
	/**
	 */

	@Column(name = "COMMON_NAME")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String commonName;

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
	public void setFmin(BigDecimal fmin) {
		this.fmin = fmin;
	}

	/**
	 */
	public Integer getFmin() {
		return this.fmin.intValue();
	}

	/**
	 */
	public void setFmax(BigDecimal fmax) {
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
	public void setAccession(String accession) {
		this.accession = accession;
	}

	/**
	 */
	public String getAccession() {
		return this.accession;
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
	public void setCvterm(String cvterm) {
		this.cvterm = cvterm;
	}

	/**
	 */
	public String getCvterm() {
		return this.cvterm;
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
		setFmin(BigDecimal.valueOf(that.getFmin()));
		setFmax(BigDecimal.valueOf(that.getFmax()));
		setStrand(that.getStrand());
		setContigId(that.getContigId());
		setContigName(that.getContigName());
		setCvtermId(that.getCvtermId());
		setAccession(that.getAccession());
		setCvName(that.getCvName());
		setCvterm(that.getCvterm());
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
		buffer.append("cvtermId=[").append(cvtermId).append("] ");
		buffer.append("accession=[").append(accession).append("] ");
		buffer.append("cvName=[").append(cvName).append("] ");
		buffer.append("cvterm=[").append(cvterm).append("] ");
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

	@Override
	public String getUniquename() {
		// TODO Auto-generated method stub
		return this.getName();
	}

	@Override
	public String getChr() {
		// TODO Auto-generated method stub
		return getContig();
		/*
		Integer chr = null;
		try {
			chr = Integer.valueOf( getContig().toUpperCase().replace("CHR",""));
		} catch(Exception ex) {
			
		}
		return chr;
		*/
	}

	@Override
	public String getContig() {
		// TODO Auto-generated method stub
		return this.getContigName();
	}

	@Override
	public BigDecimal getCvTermId() {
		// TODO Auto-generated method stub
		return cvtermId;
	}

	@Override
	public String getDefinition() {
		// TODO Auto-generated method stub
		return this.cvterm;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return cvterm;
	}
	
	
	
	
}
