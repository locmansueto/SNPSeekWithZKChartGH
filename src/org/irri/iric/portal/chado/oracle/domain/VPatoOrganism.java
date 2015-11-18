package org.irri.iric.portal.chado.oracle.domain;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.CvTerm;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVPatoOrganisms", query = "select myVPatoOrganism from VPatoOrganism myVPatoOrganism"),
		@NamedQuery(name = "findVPatoOrganismByAccession", query = "select myVPatoOrganism from VPatoOrganism myVPatoOrganism where myVPatoOrganism.accession = ?1"),
		@NamedQuery(name = "findVPatoOrganismByAccessionContaining", query = "select myVPatoOrganism from VPatoOrganism myVPatoOrganism where myVPatoOrganism.accession like ?1"),
		//@NamedQuery(name = "findVPatoOrganismByCommonName", query = "select myVPatoOrganism from VPatoOrganism myVPatoOrganism where myVPatoOrganism.commonName = ?1"),

		//@NamedQuery(name = "findVPatoOrganismByCvCommonName", query = "select myVPatoOrganism from VPatoOrganism myVPatoOrganism where myVPatoOrganism.cvName = ?1 and myVPatoOrganism.commonName = ?2"),
		
		@NamedQuery(name = "findVPatoOrganismByCvOrganism", query = "select myVPatoOrganism from VPatoOrganism myVPatoOrganism where myVPatoOrganism.cvId = ?1 and myVPatoOrganism.organismId = ?2"),
		
		//@NamedQuery(name = "findVPatoOrganismByCommonNameContaining", query = "select myVPatoOrganism from VPatoOrganism myVPatoOrganism where myVPatoOrganism.commonName like ?1"),
		//@NamedQuery(name = "findVPatoOrganismByCvName", query = "select myVPatoOrganism from VPatoOrganism myVPatoOrganism where myVPatoOrganism.cvName = ?1"),
		//@NamedQuery(name = "findVPatoOrganismByCvNameContaining", query = "select myVPatoOrganism from VPatoOrganism myVPatoOrganism where myVPatoOrganism.cvName like ?1"),
		@NamedQuery(name = "findVPatoOrganismByCvterm", query = "select myVPatoOrganism from VPatoOrganism myVPatoOrganism where myVPatoOrganism.cvterm = ?1"),
		@NamedQuery(name = "findVPatoOrganismByCvtermContaining", query = "select myVPatoOrganism from VPatoOrganism myVPatoOrganism where myVPatoOrganism.cvterm like ?1"),
		@NamedQuery(name = "findVPatoOrganismByCvtermId", query = "select myVPatoOrganism from VPatoOrganism myVPatoOrganism where myVPatoOrganism.cvtermId = ?1"),
		@NamedQuery(name = "findVPatoOrganismByOrganismId", query = "select myVPatoOrganism from VPatoOrganism myVPatoOrganism where myVPatoOrganism.organismId = ?1"),
		@NamedQuery(name = "findVPatoOrganismByPrimaryKey", query = "select myVPatoOrganism from VPatoOrganism myVPatoOrganism where myVPatoOrganism.cvtermId = ?1") })
//@Table(schema = "IRIC", name = "V_PATO_ORGANISM")
//@Table(schema = "IRIC", name = "V_LOCUS_CVTERM_CVTERMPATH")

@Table(schema = "IRIC", name = "V_CVTERM_CVTERMPATH_ORGANISM")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VPatoOrganism")
public class VPatoOrganism implements Serializable, CvTerm {
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

	//@Column(name = "ACCESSION", length = 1020, nullable = false)
	@Column(name = "SUBJ_ACC", length = 1020, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String accession;
	/**
	 */

//	@Column(name = "CV_NAME", length = 1020, nullable = false)
//	@Basic(fetch = FetchType.EAGER)
//	@XmlElement
//	String cvName;
//	/**
//	 */

	
	@Column(name = "CV_ID", length = 1020, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal cvId;

	
	@Column(name = "SUBJ_CVTERM", length = 1024)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String cvterm;
	/**
	 */

	@Column(name = "ORGANISM_ID")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal organismId;
	/**
	 */

//	@Column(name = "COMMON_NAME", length = 4)
//	@Basic(fetch = FetchType.EAGER)
//	@XmlElement
//	String commonName;

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

//	/**
//	 */
//	public void setCvName(String cvName) {
//		this.cvName = cvName;
//	}
//
//	/**
//	 */
//	public String getCvName() {
//		return this.cvName;
//	}

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
//
//	/**
//	 */
//	public void setCommonName(String commonName) {
//		this.commonName = commonName;
//	}
//
//	/**
//	 */
//	public String getCommonName() {
//		return this.commonName;
//	}

	/**
	 */
	public VPatoOrganism() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VPatoOrganism that) {
		setCvtermId(that.getCvtermId());
		setAccession(that.getAccession());
		//setCvName(that.getCvName());
		setCvterm(that.getCvterm());
		setOrganismId(that.getOrganismId());
		//setCommonName(that.getCommonName());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("cvtermId=[").append(cvtermId).append("] ");
		buffer.append("accession=[").append(accession).append("] ");
		//buffer.append("cvName=[").append(cvName).append("] ");
		buffer.append("cvterm=[").append(cvterm).append("] ");
		buffer.append("organismId=[").append(organismId).append("] ");
		//buffer.append("commonName=[").append(commonName).append("] ");

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
		if (!(obj instanceof VPatoOrganism))
			return false;
		VPatoOrganism equalCheck = (VPatoOrganism) obj;
		if ((cvtermId == null && equalCheck.cvtermId != null) || (cvtermId != null && equalCheck.cvtermId == null))
			return false;
		if (cvtermId != null && !cvtermId.equals(equalCheck.cvtermId))
			return false;
		return true;
	}

	@Override
	public BigDecimal getCvTermId() {
		// TODO Auto-generated method stub
		return this.cvtermId;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.cvterm;
	}

	@Override
	public String getDefinition() {
		// TODO Auto-generated method stub
		return this.accession;
	}

	
	
}
