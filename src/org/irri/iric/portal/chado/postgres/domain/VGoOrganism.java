package org.irri.iric.portal.chado.postgres.domain;

import java.io.Serializable;
import java.lang.StringBuilder;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.CvTerm;
import org.irri.iric.portal.domain.CvTermUniqueValues;

/**
 */

@Entity(name="VGoOrganism")
@NamedQueries({
		@NamedQuery(name = "PGfindAllVGoOrganisms", query = "select myVGoOrganism from VGoOrganism myVGoOrganism"),
		@NamedQuery(name = "PGfindVGoOrganismByAccession", query = "select myVGoOrganism from VGoOrganism myVGoOrganism where myVGoOrganism.accession = ?1"),
		@NamedQuery(name = "PGfindVGoOrganismByAccessionContaining", query = "select myVGoOrganism from VGoOrganism myVGoOrganism where myVGoOrganism.accession like ?1"),
		
		@NamedQuery(name = "PGfindVGoOrganismByCvCommonName", query = "select myVGoOrganism from VGoOrganism myVGoOrganism where  myVGoOrganism.cvName = ?1 and myVGoOrganism.commonName = ?2"),
		
		@NamedQuery(name = "PGfindVGoOrganismByCommonName", query = "select myVGoOrganism from VGoOrganism myVGoOrganism where myVGoOrganism.commonName = ?1"),
		@NamedQuery(name = "PGfindVGoOrganismByCommonNameContaining", query = "select myVGoOrganism from VGoOrganism myVGoOrganism where myVGoOrganism.commonName like ?1"),
		@NamedQuery(name = "PGfindVGoOrganismByCvName", query = "select myVGoOrganism from VGoOrganism myVGoOrganism where myVGoOrganism.cvName = ?1"),
		@NamedQuery(name = "PGfindVGoOrganismByCvNameContaining", query = "select myVGoOrganism from VGoOrganism myVGoOrganism where myVGoOrganism.cvName like ?1"),
		@NamedQuery(name = "PGfindVGoOrganismByCvterm", query = "select myVGoOrganism from VGoOrganism myVGoOrganism where myVGoOrganism.cvterm = ?1"),
		@NamedQuery(name = "PGfindVGoOrganismByCvtermContaining", query = "select myVGoOrganism from VGoOrganism myVGoOrganism where myVGoOrganism.cvterm like ?1"),
		@NamedQuery(name = "PGfindVGoOrganismByCvtermId", query = "select myVGoOrganism from VGoOrganism myVGoOrganism where myVGoOrganism.cvtermId = ?1"),
		@NamedQuery(name = "PGfindVGoOrganismByOrganismId", query = "select myVGoOrganism from VGoOrganism myVGoOrganism where myVGoOrganism.organismId = ?1"),
		@NamedQuery(name = "PGfindVGoOrganismByPrimaryKey", query = "select myVGoOrganism from VGoOrganism myVGoOrganism where myVGoOrganism.cvtermId = ?1") })
@Table(schema = "public", name = "v_go_organism")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/postgres/domain", name = "VGoOrganism")
public class VGoOrganism implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "cvterm_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Long cvtermId;
	/**
	 */

	@Column(name = "accession")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String accession;
	/**
	 */

	@Column(name = "cv_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String cvName;
	/**
	 */

	@Column(name = "cvterm", length = 1024)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String cvterm;
	/**
	 */

	@Column(name = "organism_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Long organismId;
	/**
	 */

	@Column(name = "common_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String commonName;

	/**
	 */
	public void setCvtermId(Long cvtermId) {
		this.cvtermId = cvtermId;
	}

	/**
	 */
	public Long getCvtermId() {
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
	public void setOrganismId(Long organismId) {
		this.organismId = organismId;
	}

	/**
	 */
	public Long getOrganismId() {
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
	public VGoOrganism() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VGoOrganism that) {
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

	
	
	
	
}
