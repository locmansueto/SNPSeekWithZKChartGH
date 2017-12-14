package org.irri.iric.portal.chado.postgres.domain;

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

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVCvPassports", query = "select myVCvPassport from VCvPassport myVCvPassport"),
		@NamedQuery(name = "findVCvPassportByCvtermId", query = "select myVCvPassport from VCvPassport myVCvPassport where myVCvPassport.cvtermId = ?1"),
		@NamedQuery(name = "findVCvPassportByDefinition", query = "select myVCvPassport from VCvPassport myVCvPassport where myVCvPassport.definition = ?1"),
		@NamedQuery(name = "findVCvPassportByDefinitionContaining", query = "select myVCvPassport from VCvPassport myVCvPassport where myVCvPassport.definition like ?1"),
		@NamedQuery(name = "findVCvPassportByName", query = "select myVCvPassport from VCvPassport myVCvPassport where myVCvPassport.name = ?1"),
		@NamedQuery(name = "findVCvPassportByNameContaining", query = "select myVCvPassport from VCvPassport myVCvPassport where myVCvPassport.name like ?1"),
		@NamedQuery(name = "findVCvPassportByPrimaryKey", query = "select myVCvPassport from VCvPassport myVCvPassport where myVCvPassport.cvtermId = ?1") })
@Table(schema = "public", name = "v_cv_passport")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/postgres/domain", name = "VCvPassport")
public class VCvPassport implements Serializable, CvTerm {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "cvterm_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer cvtermId;
	/**
	 */

	@Column(name = "name", length = 1024)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 */

	@Column(name = "definition")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String definition;

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
	public VCvPassport() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VCvPassport that) {
		setCvtermId(that.getCvtermId());
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
		if (!(obj instanceof VCvPassport))
			return false;
		VCvPassport equalCheck = (VCvPassport) obj;
		if ((cvtermId == null && equalCheck.cvtermId != null) || (cvtermId != null && equalCheck.cvtermId == null))
			return false;
		if (cvtermId != null && !cvtermId.equals(equalCheck.cvtermId))
			return false;
		return true;
	}

	@Override
	public BigDecimal getCvTermId() {
		// TODO Auto-generated method stub
		return BigDecimal.valueOf( this.getCvtermId() );
	}

	@Override
	public String getAccession() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
