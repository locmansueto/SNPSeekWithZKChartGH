package org.irri.iric.portal.chado.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.MultiReferenceConversion;

/**
 */
@IdClass(org.irri.iric.portal.chado.domain.VConvertRefposPrecompPK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVConvertRefposPrecomps", query = "select myVConvertRefposPrecomp from VConvertRefposPrecomp myVConvertRefposPrecomp"),
		@NamedQuery(name = "findVConvertRefposPrecompByFromContig", query = "select myVConvertRefposPrecomp from VConvertRefposPrecomp myVConvertRefposPrecomp where myVConvertRefposPrecomp.fromContig = ?1"),
		@NamedQuery(name = "findVConvertRefposPrecompByFromContigContaining", query = "select myVConvertRefposPrecomp from VConvertRefposPrecomp myVConvertRefposPrecomp where myVConvertRefposPrecomp.fromContig like ?1"),
		@NamedQuery(name = "findVConvertRefposPrecompByFromContigFeatureId", query = "select myVConvertRefposPrecomp from VConvertRefposPrecomp myVConvertRefposPrecomp where myVConvertRefposPrecomp.fromContigFeatureId = ?1"),
		@NamedQuery(name = "findVConvertRefposPrecompByFromOrganismId", query = "select myVConvertRefposPrecomp from VConvertRefposPrecomp myVConvertRefposPrecomp where myVConvertRefposPrecomp.fromOrganismId = ?1"),
		@NamedQuery(name = "findVConvertRefposPrecompByFromPosition", query = "select myVConvertRefposPrecomp from VConvertRefposPrecomp myVConvertRefposPrecomp where myVConvertRefposPrecomp.fromPosition = ?1"),
		@NamedQuery(name = "findVConvertRefposPrecompByFromRefcall", query = "select myVConvertRefposPrecomp from VConvertRefposPrecomp myVConvertRefposPrecomp where myVConvertRefposPrecomp.fromRefcall = ?1"),
		@NamedQuery(name = "findVConvertRefposPrecompByFromRefcallContaining", query = "select myVConvertRefposPrecomp from VConvertRefposPrecomp myVConvertRefposPrecomp where myVConvertRefposPrecomp.fromRefcall like ?1"),
		@NamedQuery(name = "findVConvertRefposPrecompByPrimaryKey", query = "select myVConvertRefposPrecomp from VConvertRefposPrecomp myVConvertRefposPrecomp where myVConvertRefposPrecomp.fromContigFeatureId = ?1 and myVConvertRefposPrecomp.fromPosition = ?2 and myVConvertRefposPrecomp.toContigFeatureId = ?3 and myVConvertRefposPrecomp.toPosition = ?4"),
		
		
//		@NamedQuery(name = "findVConvertRefposPrecompByFromOrgContigPosToOrg", query = "select myVConvertRefposPrecomp from VConvertRefposPrecomp myVConvertRefposPrecomp where myVConvertRefposPrecomp.fromOrganismId=1? and myVConvertRefposPrecomp.fromContig=?2 and myVConvertRefposPrecomp.fromPosition=?3 and myVConvertRefposPrecomp.toOrganismId=?4"),
//		@NamedQuery(name = "findVConvertRefposPrecompByFromOrgContigPosToOrgContig", query = "select myVConvertRefposPrecomp from VConvertRefposPrecomp myVConvertRefposPrecomp where myVConvertRefposPrecomp.fromOrganismId=1? and myVConvertRefposPrecomp.fromContig=?2 and myVConvertRefposPrecomp.fromPosition=?3 and myVConvertRefposPrecomp.toOrganismId=?4 and myVConvertRefposPrecomp.toContig=?5"),
//		@NamedQuery(name = "findVConvertRefposPrecompByFromOrgContigPosrangeToOrg", query = "select myVConvertRefposPrecomp from VConvertRefposPrecomp myVConvertRefposPrecomp where myVConvertRefposPrecomp.fromOrganismId=1? and myVConvertRefposPrecomp.fromContig=?2 and myVConvertRefposPrecomp.fromPosition between ?3 and ?4 and myVConvertRefposPrecomp.toOrganismId=?5 order by  myVConvertRefposPrecomp.toContig, myVConvertRefposPrecomp.toPosition"),
//		@NamedQuery(name = "findVConvertRefposPrecompByFromOrgContigPosrangeToOrgContig", query = "select myVConvertRefposPrecomp from VConvertRefposPrecomp myVConvertRefposPrecomp where myVConvertRefposPrecomp.fromOrganismId=1? and myVConvertRefposPrecomp.fromContig=?2 and myVConvertRefposPrecomp.fromPosition= between ?3 and ?4 and myVConvertRefposPrecomp.toOrganismId=?5 and myVConvertRefposPrecomp.toContig=?6 and myVConvertRefposPrecomp.toPosition between ?7 and ?8 order by myVConvertRefposPrecomp.toPosition"),

		
		@NamedQuery(name = "findVConvertRefposPrecompByToContig", query = "select myVConvertRefposPrecomp from VConvertRefposPrecomp myVConvertRefposPrecomp where myVConvertRefposPrecomp.toContig = ?1"),
		@NamedQuery(name = "findVConvertRefposPrecompByToContigContaining", query = "select myVConvertRefposPrecomp from VConvertRefposPrecomp myVConvertRefposPrecomp where myVConvertRefposPrecomp.toContig like ?1"),
		@NamedQuery(name = "findVConvertRefposPrecompByToContigFeatureId", query = "select myVConvertRefposPrecomp from VConvertRefposPrecomp myVConvertRefposPrecomp where myVConvertRefposPrecomp.toContigFeatureId = ?1"),
		@NamedQuery(name = "findVConvertRefposPrecompByToOrganismId", query = "select myVConvertRefposPrecomp from VConvertRefposPrecomp myVConvertRefposPrecomp where myVConvertRefposPrecomp.toOrganismId = ?1"),
		@NamedQuery(name = "findVConvertRefposPrecompByToPosition", query = "select myVConvertRefposPrecomp from VConvertRefposPrecomp myVConvertRefposPrecomp where myVConvertRefposPrecomp.toPosition = ?1"),
		@NamedQuery(name = "findVConvertRefposPrecompByToRefcall", query = "select myVConvertRefposPrecomp from VConvertRefposPrecomp myVConvertRefposPrecomp where myVConvertRefposPrecomp.toRefcall = ?1"),
		@NamedQuery(name = "findVConvertRefposPrecompByToRefcallContaining", query = "select myVConvertRefposPrecomp from VConvertRefposPrecomp myVConvertRefposPrecomp where myVConvertRefposPrecomp.toRefcall like ?1") })
@Table(schema = "IRIC", name = "V_CONVERT_REFPOS_PRECOMP")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VConvertRefposPrecomp")
public class VConvertRefposPrecomp implements Serializable, MultiReferenceConversion {
	private static final long serialVersionUID = 1L;

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

	@Column(name = "TO_CONTIG_FEATURE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal toContigFeatureId;
	/**
	 */

	@Column(name = "TO_POSITION", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal toPosition;
	/**
	 */

	@Column(name = "FROM_CONTIG", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String fromContig;
	/**
	 */

	@Column(name = "FROM_ORGANISM_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal fromOrganismId;
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

	@Column(name = "TO_REFCALL", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String toRefcall;

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
	public void setToContig(String toContig) {
		this.toContig = toContig;
	}

	/**
	 */
	public String getToContig() {
		return this.toContig;
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
	public VConvertRefposPrecomp() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VConvertRefposPrecomp that) {
		setFromContigFeatureId(that.getFromContigFeatureId());
		setFromPosition(that.getFromPosition());
		setToContigFeatureId(that.getToContigFeatureId());
		setToPosition(that.getToPosition());
		setFromContig(that.getFromContig());
		setFromOrganismId(that.getFromOrganismId());
		setFromRefcall(that.getFromRefcall());
		setToContig(that.getToContig());
		setToOrganismId(that.getToOrganismId());
		setToRefcall(that.getToRefcall());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("fromContigFeatureId=[").append(fromContigFeatureId).append("] ");
		buffer.append("fromPosition=[").append(fromPosition).append("] ");
		buffer.append("toContigFeatureId=[").append(toContigFeatureId).append("] ");
		buffer.append("toPosition=[").append(toPosition).append("] ");
		buffer.append("fromContig=[").append(fromContig).append("] ");
		buffer.append("fromOrganismId=[").append(fromOrganismId).append("] ");
		buffer.append("fromRefcall=[").append(fromRefcall).append("] ");
		buffer.append("toContig=[").append(toContig).append("] ");
		buffer.append("toOrganismId=[").append(toOrganismId).append("] ");
		buffer.append("toRefcall=[").append(toRefcall).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((fromContigFeatureId == null) ? 0 : fromContigFeatureId.hashCode()));
		result = (int) (prime * result + ((fromPosition == null) ? 0 : fromPosition.hashCode()));
		result = (int) (prime * result + ((toContigFeatureId == null) ? 0 : toContigFeatureId.hashCode()));
		result = (int) (prime * result + ((toPosition == null) ? 0 : toPosition.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VConvertRefposPrecomp))
			return false;
		VConvertRefposPrecomp equalCheck = (VConvertRefposPrecomp) obj;
		if ((fromContigFeatureId == null && equalCheck.fromContigFeatureId != null) || (fromContigFeatureId != null && equalCheck.fromContigFeatureId == null))
			return false;
		if (fromContigFeatureId != null && !fromContigFeatureId.equals(equalCheck.fromContigFeatureId))
			return false;
		if ((fromPosition == null && equalCheck.fromPosition != null) || (fromPosition != null && equalCheck.fromPosition == null))
			return false;
		if (fromPosition != null && !fromPosition.equals(equalCheck.fromPosition))
			return false;
		if ((toContigFeatureId == null && equalCheck.toContigFeatureId != null) || (toContigFeatureId != null && equalCheck.toContigFeatureId == null))
			return false;
		if (toContigFeatureId != null && !toContigFeatureId.equals(equalCheck.toContigFeatureId))
			return false;
		if ((toPosition == null && equalCheck.toPosition != null) || (toPosition != null && equalCheck.toPosition == null))
			return false;
		if (toPosition != null && !toPosition.equals(equalCheck.toPosition))
			return false;
		return true;
	}

	@Override
	public String getToOrganism() {
		// TODO Auto-generated method stub
		return this.toOrganismId.toString();
	}
	@Override
	public String getFromOrganism() {
		// TODO Auto-generated method stub
		return this.fromOrganismId.toString();
	}

	
	
	
	
	
}
