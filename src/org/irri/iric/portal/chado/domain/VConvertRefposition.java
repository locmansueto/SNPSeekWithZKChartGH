package org.irri.iric.portal.chado.domain;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.MultiReferenceConversion;

/**
 */
@IdClass(org.irri.iric.portal.chado.domain.VConvertRefpositionPK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVConvertRefpositions", query = "select myVConvertRefposition from VConvertRefposition myVConvertRefposition"),
		@NamedQuery(name = "findVConvertRefpositionByFromContig", query = "select myVConvertRefposition from VConvertRefposition myVConvertRefposition where myVConvertRefposition.fromContig = ?1"),
		@NamedQuery(name = "findVConvertRefpositionByFromContigContaining", query = "select myVConvertRefposition from VConvertRefposition myVConvertRefposition where myVConvertRefposition.fromContig like ?1"),
		@NamedQuery(name = "findVConvertRefpositionByFromContigFeatureId", query = "select myVConvertRefposition from VConvertRefposition myVConvertRefposition where myVConvertRefposition.fromContigFeatureId = ?1"),
		@NamedQuery(name = "findVConvertRefpositionByFromOrganismId", query = "select myVConvertRefposition from VConvertRefposition myVConvertRefposition where myVConvertRefposition.fromOrganismId = ?1"),
		@NamedQuery(name = "findVConvertRefpositionByFromPosition", query = "select myVConvertRefposition from VConvertRefposition myVConvertRefposition where myVConvertRefposition.fromPosition = ?1"),
		@NamedQuery(name = "findVConvertRefpositionByPrimaryKey", query = "select myVConvertRefposition from VConvertRefposition myVConvertRefposition where myVConvertRefposition.fromOrganismId = ?1 and myVConvertRefposition.fromContigFeatureId = ?2 and myVConvertRefposition.fromPosition = ?3"),
		@NamedQuery(name = "findVConvertRefpositionByToContig", query = "select myVConvertRefposition from VConvertRefposition myVConvertRefposition where myVConvertRefposition.toContig = ?1"),
		@NamedQuery(name = "findVConvertRefpositionByToContigContaining", query = "select myVConvertRefposition from VConvertRefposition myVConvertRefposition where myVConvertRefposition.toContig like ?1"),
		@NamedQuery(name = "findVConvertRefpositionByToContigFeatureId", query = "select myVConvertRefposition from VConvertRefposition myVConvertRefposition where myVConvertRefposition.toContigFeatureId = ?1"),
		@NamedQuery(name = "findVConvertRefpositionByToOrganismId", query = "select myVConvertRefposition from VConvertRefposition myVConvertRefposition where myVConvertRefposition.toOrganismId = ?1"),
		@NamedQuery(name = "findVConvertRefpositionByToPosition", query = "select myVConvertRefposition from VConvertRefposition myVConvertRefposition where myVConvertRefposition.toPosition = ?1") })
@Table(schema = "IRIC", name = "V_CONVERT_REFPOSITION")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VConvertRefposition")
public class VConvertRefposition implements Serializable , MultiReferenceConversion {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "FROM_ORGANISM_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal fromOrganismId;
	/**
	 */

	@Column(name = "FROM_CONTIG_FEATURE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal fromContigFeatureId;
	/**
	 */

	@Column(name = "FROM_POSITION", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal fromPosition;
	/**
	 */

	@Column(name = "FROM_CONTIG", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String fromContig;
	/**
	 */
	

	@Column(name = "FROM_REFCALL", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String fromRefcall;
	/**
	 */

	@Column(name = "TO_CONTIG", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String toContig;
	/**
	 */

	@Column(name = "TO_ORGANISM_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal toOrganismId;
	/**
	 */

	@Column(name = "TO_CONTIG_FEATURE_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal toContigFeatureId;
	/**
	 */

	@Column(name = "TO_POSITION", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal toPosition;
	
	@Column(name = "TO_REFCALL", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String toRefcall;


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
	public void setFromContigFeatureId(BigDecimal fromContigFeatureId) {
		this.fromContigFeatureId = fromContigFeatureId;
	}

	/**
	 */
	public BigDecimal getFromContigFeatureId() {
		return this.fromContigFeatureId;
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
	public void setFromContig(String fromContig) {
		this.fromContig = fromContig;
	}

	/**
	 */
	public String getFromContig() {
		return this.fromContig;
	}

	/**
	 */
	public void setToContig(String toContig) {
		this.toContig = toContig;
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
	public void setToContigFeatureId(BigDecimal toContigFeatureId) {
		this.toContigFeatureId = toContigFeatureId;
	}

	/**
	 */
	public BigDecimal getToContigFeatureId() {
		return this.toContigFeatureId;
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
	public VConvertRefposition() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VConvertRefposition that) {
		setFromOrganismId(that.getFromOrganismId());
		setFromContigFeatureId(that.getFromContigFeatureId());
		setFromPosition(that.getFromPosition());
		setFromContig(that.getFromContig());
		setToContig(that.getToContig());
		setToOrganismId(that.getToOrganismId());
		setToContigFeatureId(that.getToContigFeatureId());
		setToPosition(that.getToPosition());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("fromOrganismId=[").append(fromOrganismId).append("] ");
		buffer.append("fromContigFeatureId=[").append(fromContigFeatureId).append("] ");
		buffer.append("fromPosition=[").append(fromPosition).append("] ");
		buffer.append("fromContig=[").append(fromContig).append("] ");
		buffer.append("toContig=[").append(toContig).append("] ");
		buffer.append("toOrganismId=[").append(toOrganismId).append("] ");
		buffer.append("toContigFeatureId=[").append(toContigFeatureId).append("] ");
		buffer.append("toPosition=[").append(toPosition).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((fromOrganismId == null) ? 0 : fromOrganismId.hashCode()));
		result = (int) (prime * result + ((fromContigFeatureId == null) ? 0 : fromContigFeatureId.hashCode()));
		result = (int) (prime * result + ((fromPosition == null) ? 0 : fromPosition.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VConvertRefposition))
			return false;
		VConvertRefposition equalCheck = (VConvertRefposition) obj;
		if ((fromOrganismId == null && equalCheck.fromOrganismId != null) || (fromOrganismId != null && equalCheck.fromOrganismId == null))
			return false;
		if (fromOrganismId != null && !fromOrganismId.equals(equalCheck.fromOrganismId))
			return false;
		if ((fromContigFeatureId == null && equalCheck.fromContigFeatureId != null) || (fromContigFeatureId != null && equalCheck.fromContigFeatureId == null))
			return false;
		if (fromContigFeatureId != null && !fromContigFeatureId.equals(equalCheck.fromContigFeatureId))
			return false;
		if ((fromPosition == null && equalCheck.fromPosition != null) || (fromPosition != null && equalCheck.fromPosition == null))
			return false;
		if (fromPosition != null && !fromPosition.equals(equalCheck.fromPosition))
			return false;
		return true;
	}

	@Override
	public String getToOrganism() {
		// TODO Auto-generated method stub
		return this.getToOrganismId().toString();
	}

	@Override
	public String getToContig() {
		// TODO Auto-generated method stub
		return this.toContig;
	}


	@Override
	public String getFromOrganism() {
		// TODO Auto-generated method stub
		return this.fromOrganismId.toString();
	}

	@Override
	public String getFromRefcall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToRefcall() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setFromRefcall(String fromRefcall) {
		this.fromRefcall = fromRefcall;
	}

	public void setToRefcall(String toRefcall) {
		this.toRefcall = toRefcall;
	}
	
	
	
	
}
