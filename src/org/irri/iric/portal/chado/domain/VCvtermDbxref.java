package org.irri.iric.portal.chado.domain;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.CvTerm;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVCvtermDbxrefs", query = "select myVCvtermDbxref from VCvtermDbxref myVCvtermDbxref"),
		@NamedQuery(name = "findVCvtermDbxrefByAccession", query = "select myVCvtermDbxref from VCvtermDbxref myVCvtermDbxref where myVCvtermDbxref.accession = ?1"),
		@NamedQuery(name = "findVCvtermDbxrefByAccessionContaining", query = "select myVCvtermDbxref from VCvtermDbxref myVCvtermDbxref where myVCvtermDbxref.accession like ?1"),
		@NamedQuery(name = "findVCvtermDbxrefByCvName", query = "select myVCvtermDbxref from VCvtermDbxref myVCvtermDbxref where myVCvtermDbxref.cvName = ?1"),
		@NamedQuery(name = "findVCvtermDbxrefByCvNameContaining", query = "select myVCvtermDbxref from VCvtermDbxref myVCvtermDbxref where myVCvtermDbxref.cvName like ?1"),
		@NamedQuery(name = "findVCvtermDbxrefByCvtermId", query = "select myVCvtermDbxref from VCvtermDbxref myVCvtermDbxref where myVCvtermDbxref.cvtermId = ?1"),
		@NamedQuery(name = "findVCvtermDbxrefByDefinition", query = "select myVCvtermDbxref from VCvtermDbxref myVCvtermDbxref where myVCvtermDbxref.definition = ?1"),
		@NamedQuery(name = "findVCvtermDbxrefByDefinitionContaining", query = "select myVCvtermDbxref from VCvtermDbxref myVCvtermDbxref where myVCvtermDbxref.definition like ?1"),
		@NamedQuery(name = "findVCvtermDbxrefByName", query = "select myVCvtermDbxref from VCvtermDbxref myVCvtermDbxref where myVCvtermDbxref.name = ?1"),
		@NamedQuery(name = "findVCvtermDbxrefByNameContaining", query = "select myVCvtermDbxref from VCvtermDbxref myVCvtermDbxref where myVCvtermDbxref.name like ?1"),
		@NamedQuery(name = "findVCvtermDbxrefByPrimaryKey", query = "select myVCvtermDbxref from VCvtermDbxref myVCvtermDbxref where myVCvtermDbxref.cvtermId = ?1") })
@Table(schema = "IRIC", name = "V_CVTERM_DBXREF")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VCvtermDbxref")
public class VCvtermDbxref implements Serializable, CvTerm {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "CVTERM_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal cvtermId;
	/**
	 */

	@Column(name = "CV_NAME")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String cvName;
	/**
	 */

	@Column(name = "ACCESSION")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String accession;
	/**
	 */

	@Column(name = "NAME", length = 1024)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 */

	@Column(name = "DEFINITION", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String definition;

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
	public void setAccession(String accession) {
		this.accession = accession;
	}

	/**
	 */
	public String getAccession() {
		return "GO:" + this.accession;
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
	public void setDefinition(String definition) {
		this.definition = definition;
	}

	/**
	 */
	public String getDefinition() {
		return this.definition;
	}

	/**
	 */
	public VCvtermDbxref() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VCvtermDbxref that) {
		setCvtermId(that.getCvtermId());
		setCvName(that.getCvName());
		setAccession(that.getAccession());
		setName(that.getName());
		setDefinition(that.getDefinition());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("cvtermId=[").append(cvtermId).append("] ");
		buffer.append("cvName=[").append(cvName).append("] ");
		buffer.append("accession=[").append(accession).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("definition=[").append(definition).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((cvtermId == null) ? 0 : cvtermId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VCvtermDbxref))
			return false;
		VCvtermDbxref equalCheck = (VCvtermDbxref) obj;
		if ((cvtermId == null && equalCheck.cvtermId != null) || (cvtermId != null && equalCheck.cvtermId == null))
			return false;
		if (cvtermId != null && !cvtermId.equals(equalCheck.cvtermId))
			return false;
		return true;
	}

	@Override
	public BigDecimal getCvTermId() {
		// TODO Auto-generated method stub
		return this.getCvtermId();
	}
	
	
	
	
}
