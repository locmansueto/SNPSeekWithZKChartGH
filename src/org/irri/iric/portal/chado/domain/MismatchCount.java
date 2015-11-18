package org.irri.iric.portal.chado.domain;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.SnpsAllvarsRefMismatch;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllMismatchCounts", query = "select myMismatchCount from MismatchCount myMismatchCount"),
		@NamedQuery(name = "findMismatchCountByFragEnd", query = "select myMismatchCount from MismatchCount myMismatchCount where myMismatchCount.fragEnd = ?1"),
		@NamedQuery(name = "findMismatchCountByFragStart", query = "select myMismatchCount from MismatchCount myMismatchCount where myMismatchCount.fragStart = ?1"),
		
		@NamedQuery(name = "findMismatchCountByFragBetween", query = "select myMismatchCount from MismatchCount myMismatchCount where myMismatchCount.fragStart <= ?1 and myMismatchCount.fragEnd>=?2 order by myMismatchCount.mismatch desc,  myMismatchCount.iricStockId"),

		
		@NamedQuery(name = "findMismatchCountByIricStockId", query = "select myMismatchCount from MismatchCount myMismatchCount where myMismatchCount.iricStockId = ?1"),
		@NamedQuery(name = "findMismatchCountByMismatch", query = "select myMismatchCount from MismatchCount myMismatchCount where myMismatchCount.mismatch = ?1"),
		@NamedQuery(name = "findMismatchCountByMismatchCountId", query = "select myMismatchCount from MismatchCount myMismatchCount where myMismatchCount.mismatchCountId = ?1"),
		@NamedQuery(name = "findMismatchCountByPrimaryKey", query = "select myMismatchCount from MismatchCount myMismatchCount where myMismatchCount.mismatchCountId = ?1") })
@Table(schema = "LMANSUETO", name = "MISMATCH_COUNT")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "MismatchCount")
public class MismatchCount implements Serializable, SnpsAllvarsRefMismatch {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "MISMATCH_COUNT_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal mismatchCountId;
	/**
	 */

	@Column(name = "FRAG_START")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal fragStart;
	/**
	 */

	@Column(name = "IRIC_STOCK_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal iricStockId;
	/**
	 */

	@Column(name = "MISMATCH")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal mismatch;
	/**
	 */

	@Column(name = "FRAG_END")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal fragEnd;

	/**
	 */
	public void setMismatchCountId(BigDecimal mismatchCountId) {
		this.mismatchCountId = mismatchCountId;
	}

	/**
	 */
	public BigDecimal getMismatchCountId() {
		return this.mismatchCountId;
	}

	/**
	 */
	public void setFragStart(BigDecimal fragStart) {
		this.fragStart = fragStart;
	}

	/**
	 */
	public BigDecimal getFragStart() {
		return this.fragStart;
	}

	/**
	 */
	public void setIricStockId(BigDecimal iricStockId) {
		this.iricStockId = iricStockId;
	}

	/**
	 */
	public BigDecimal getIricStockId() {
		return this.iricStockId;
	}

	/**
	 */
	public void setMismatch(BigDecimal mismatch) {
		this.mismatch = mismatch;
	}

	/**
	 */
	public BigDecimal getMismatch() {
		return this.mismatch;
	}

	/**
	 */
	public void setFragEnd(BigDecimal fragEnd) {
		this.fragEnd = fragEnd;
	}

	/**
	 */
	public BigDecimal getFragEnd() {
		return this.fragEnd;
	}

	/**
	 */
	public MismatchCount() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(MismatchCount that) {
		setMismatchCountId(that.getMismatchCountId());
		setFragStart(that.getFragStart());
		setIricStockId(that.getIricStockId());
		setMismatch(that.getMismatch());
		setFragEnd(that.getFragEnd());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("mismatchCountId=[").append(mismatchCountId).append("] ");
		buffer.append("fragStart=[").append(fragStart).append("] ");
		buffer.append("iricStockId=[").append(iricStockId).append("] ");
		buffer.append("mismatch=[").append(mismatch).append("] ");
		buffer.append("fragEnd=[").append(fragEnd).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((mismatchCountId == null) ? 0 : mismatchCountId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof MismatchCount))
			return false;
		MismatchCount equalCheck = (MismatchCount) obj;
		if ((mismatchCountId == null && equalCheck.mismatchCountId != null) || (mismatchCountId != null && equalCheck.mismatchCountId == null))
			return false;
		if (mismatchCountId != null && !mismatchCountId.equals(equalCheck.mismatchCountId))
			return false;
		return true;
	}

	@Override
	public BigDecimal getVar() {
		// TODO Auto-generated method stub
		return this.iricStockId;
	}
	
	
	
	
}
