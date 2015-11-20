package org.irri.iric.portal.chado.oracle.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVConvertposNb2allrefs", query = "select myVConvertposNb2allref from VConvertposNb2allref myVConvertposNb2allref"),
		@NamedQuery(name = "findVConvertposNb2allrefByFromContigId", query = "select myVConvertposNb2allref from VConvertposNb2allref myVConvertposNb2allref where myVConvertposNb2allref.fromContigId = ?1"),
		@NamedQuery(name = "findVConvertposNb2allrefByFromOrganismId", query = "select myVConvertposNb2allref from VConvertposNb2allref myVConvertposNb2allref where myVConvertposNb2allref.fromOrganismId = ?1"),
		@NamedQuery(name = "findVConvertposNb2allrefByFromPosition", query = "select myVConvertposNb2allref from VConvertposNb2allref myVConvertposNb2allref where myVConvertposNb2allref.fromPosition = ?1"),
		@NamedQuery(name = "findVConvertposNb2allrefByFromRefcall", query = "select myVConvertposNb2allref from VConvertposNb2allref myVConvertposNb2allref where myVConvertposNb2allref.fromRefcall = ?1"),
		@NamedQuery(name = "findVConvertposNb2allrefByFromRefcallContaining", query = "select myVConvertposNb2allref from VConvertposNb2allref myVConvertposNb2allref where myVConvertposNb2allref.fromRefcall like ?1"),
		@NamedQuery(name = "findVConvertposNb2allrefByPrimaryKey", query = "select myVConvertposNb2allref from VConvertposNb2allref myVConvertposNb2allref where myVConvertposNb2allref.snpFeatureId = ?1"),
		@NamedQuery(name = "findVConvertposNb2allrefBySnpFeatureId", query = "select myVConvertposNb2allref from VConvertposNb2allref myVConvertposNb2allref where myVConvertposNb2allref.snpFeatureId = ?1"),
		@NamedQuery(name = "findVConvertposNb2allrefByToContigId", query = "select myVConvertposNb2allref from VConvertposNb2allref myVConvertposNb2allref where myVConvertposNb2allref.toContigId = ?1"),
		@NamedQuery(name = "findVConvertposNb2allrefByToOrganismId", query = "select myVConvertposNb2allref from VConvertposNb2allref myVConvertposNb2allref where myVConvertposNb2allref.toOrganismId = ?1"),
		@NamedQuery(name = "findVConvertposNb2allrefByToPosition", query = "select myVConvertposNb2allref from VConvertposNb2allref myVConvertposNb2allref where myVConvertposNb2allref.toPosition = ?1"),
		@NamedQuery(name = "findVConvertposNb2allrefByToRefcall", query = "select myVConvertposNb2allref from VConvertposNb2allref myVConvertposNb2allref where myVConvertposNb2allref.toRefcall = ?1"),
		@NamedQuery(name = "findVConvertposNb2allrefByToRefcallContaining", query = "select myVConvertposNb2allref from VConvertposNb2allref myVConvertposNb2allref where myVConvertposNb2allref.toRefcall like ?1") })
@Table(schema = "IRIC", name = "V_CONVERTPOS_NB2ALLREF")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/oracle/domain", name = "VConvertposNb2allref")
public class VConvertposNb2allref implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "SNP_FEATURE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal snpFeatureId;
	/**
	 */

	@Column(name = "FROM_ORGANISM_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal fromOrganismId;
	/**
	 */

	@Column(name = "FROM_CONTIG_ID")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal fromContigId;
	/**
	 */

	@Column(name = "FROM_POSITION")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal fromPosition;
	/**
	 */

	@Column(name = "FROM_REFCALL", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String fromRefcall;
	/**
	 */

	@Column(name = "TO_ORGANISM_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal toOrganismId;
	/**
	 */

	@Column(name = "TO_CONTIG_ID")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal toContigId;
	/**
	 */

	@Column(name = "TO_POSITION")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal toPosition;
	/**
	 */

	@Column(name = "TO_REFCALL", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String toRefcall;

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
	public void setToOrganismId(BigDecimal toOrganismId) {
		this.toOrganismId = toOrganismId;
	}

	/**
	 */
	public BigDecimal getToOrganismId() {
		return this.toOrganismId;
	}

	/**
	 */
	public void setToContigId(BigDecimal toContigId) {
		this.toContigId = toContigId;
	}

	/**
	 */
	public BigDecimal getToContigId() {
		return this.toContigId;
	}

	/**
	 */
	public void setToPosition(BigDecimal toPosition) {
		this.toPosition = toPosition;
	}

	/**
	 */
	public BigDecimal getToPosition() {
		return this.toPosition;
	}

	/**
	 */
	public void setToRefcall(String toRefcall) {
		this.toRefcall = toRefcall;
	}

	/**
	 */
	public String getToRefcall() {
		return this.toRefcall;
	}

	/**
	 */
	public VConvertposNb2allref() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VConvertposNb2allref that) {
		setSnpFeatureId(that.getSnpFeatureId());
		setFromOrganismId(that.getFromOrganismId());
		setFromContigId(that.getFromContigId());
		setFromPosition(that.getFromPosition());
		setFromRefcall(that.getFromRefcall());
		setToOrganismId(that.getToOrganismId());
		setToContigId(that.getToContigId());
		setToPosition(that.getToPosition());
		setToRefcall(that.getToRefcall());
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
		buffer.append("toOrganismId=[").append(toOrganismId).append("] ");
		buffer.append("toContigId=[").append(toContigId).append("] ");
		buffer.append("toPosition=[").append(toPosition).append("] ");
		buffer.append("toRefcall=[").append(toRefcall).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((snpFeatureId == null) ? 0 : snpFeatureId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VConvertposNb2allref))
			return false;
		VConvertposNb2allref equalCheck = (VConvertposNb2allref) obj;
		if ((snpFeatureId == null && equalCheck.snpFeatureId != null) || (snpFeatureId != null && equalCheck.snpFeatureId == null))
			return false;
		if (snpFeatureId != null && !snpFeatureId.equals(equalCheck.snpFeatureId))
			return false;
		return true;
	}
}
