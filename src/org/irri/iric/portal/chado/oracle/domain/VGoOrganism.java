package org.irri.iric.portal.chado.oracle.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.CvTerm;

/**
 */

@Entity(name="VGoOrganism")
@NamedQueries({
		@NamedQuery(name = "findAllVGoOrganisms", query = "select myVGoOrganism from VGoOrganism myVGoOrganism"),
		@NamedQuery(name = "findVGoOrganismByAccession", query = "select myVGoOrganism from VGoOrganism myVGoOrganism where myVGoOrganism.accession = ?1"),
		@NamedQuery(name = "findVGoOrganismByAccessionContaining", query = "select myVGoOrganism from VGoOrganism myVGoOrganism where myVGoOrganism.accession like ?1"),
		
		//@NamedQuery(name = "findVGoOrganismByCvCommonName", query = "select myVGoOrganism from VGoOrganism myVGoOrganism where  myVGoOrganism.cvName = ?1 and myVGoOrganism.commonName = ?2"),

		@NamedQuery(name = "findVGoOrganismByCvtermCvOrganism", query = "select myVGoOrganism from VGoOrganism myVGoOrganism where myVGoOrganism.cvterm = ?1 and myVGoOrganism.cvId= ?2 and   myVGoOrganism.organismId = ?3"),
		//@NamedQuery(name = "findVGoOrganismByCvtermCvOrganism", query = "select myVGoOrganism from VGoOrganism myVGoOrganism where myVGoOrganism.cvterm = ?1 and myVGoOrganism.cvName= ?2 and   myVGoOrganism.organismId = ?3"),
		@NamedQuery(name = "findVGoOrganismByCvOrganism", query = "select myVGoOrganism from VGoOrganism myVGoOrganism where myVGoOrganism.cvId=?1 and myVGoOrganism.organismId=?2"),
		
		
		//@NamedQuery(name = "findVGoOrganismByCommonName", query = "select myVGoOrganism from VGoOrganism myVGoOrganism where myVGoOrganism.commonName = ?1"),
		//@NamedQuery(name = "findVGoOrganismByCommonNameContaining", query = "select myVGoOrganism from VGoOrganism myVGoOrganism where myVGoOrganism.commonName like ?1"),
		//@NamedQuery(name = "findVGoOrganismByCvName", query = "select myVGoOrganism from VGoOrganism myVGoOrganism where myVGoOrganism.cvName = ?1"),
		//@NamedQuery(name = "findVGoOrganismByCvNameContaining", query = "select myVGoOrganism from VGoOrganism myVGoOrganism where myVGoOrganism.cvName like ?1"),
		@NamedQuery(name = "findVGoOrganismByCvterm", query = "select myVGoOrganism from VGoOrganism myVGoOrganism where myVGoOrganism.cvterm = ?1"),
		@NamedQuery(name = "findVGoOrganismByCvtermContaining", query = "select myVGoOrganism from VGoOrganism myVGoOrganism where myVGoOrganism.cvterm like ?1"),
		@NamedQuery(name = "findVGoOrganismByCvtermId", query = "select myVGoOrganism from VGoOrganism myVGoOrganism where myVGoOrganism.cvtermId = ?1"),
		@NamedQuery(name = "findVGoOrganismByOrganismId", query = "select myVGoOrganism from VGoOrganism myVGoOrganism where myVGoOrganism.organismId = ?1"),
		@NamedQuery(name = "findVGoOrganismByPrimaryKey", query = "select myVGoOrganism from VGoOrganism myVGoOrganism where myVGoOrganism.cvtermId = ?1") })
//@Table(schema = "IRIC", name = "V_GO_ORGANISM")
//@Table(schema = "IRIC", name = "V_GO_CVTERMPATH_ORGANISM")
//@Table(schema = "IRIC", name = "V_LOCUS_CVTERM_CVTERMPATH")
@Table(schema = "IRIC", name = "V_CVTERM_CVTERMPATH_ORGANISM")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VGoOrganism")
public class VGoOrganism implements Serializable, CvTerm {
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

	//@Column(name = "ACCESSION")
	@Column(name = "SUBJ_ACC")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String accession;
	/**
	 */

//	@Column(name = "CV_NAME")
//	@Basic(fetch = FetchType.EAGER)
//	@XmlElement
//	String cvName;
	/**
	 */

	@Column(name = "CV_ID")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal cvId;

	
	//@Column(name = "CVTERM", length = 1024)
	@Column(name = "SUBJ_CVTERM", length = 1024)
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

//	@Column(name = "COMMON_NAME")
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
	public VGoOrganism() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VGoOrganism that) {
		setCvtermId(that.getCvtermId());
		setAccession(that.getAccession());
//		setCvName(that.getCvName());
		setCvterm(that.getCvterm());
		setOrganismId(that.getOrganismId());
//		setCommonName(that.getCommonName());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("cvtermId=[").append(cvtermId).append("] ");
		buffer.append("accession=[").append(accession).append("] ");
//		buffer.append("cvName=[").append(cvName).append("] ");
		buffer.append("cvterm=[").append(cvterm).append("] ");
		buffer.append("organismId=[").append(organismId).append("] ");
//		buffer.append("commonName=[").append(commonName).append("] ");

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
		if (!(obj instanceof VGoOrganism))
			return false;
		VGoOrganism equalCheck = (VGoOrganism) obj;
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
//
//	@Override
//	public int compareTo(LocusCvTerm o) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public String getUniquename() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String getChr() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Integer getFmin() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Integer getFmax() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Integer getStrand() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String getContig() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String getDescription() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
	
}
