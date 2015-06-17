package org.irri.iric.portal.chado.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 */
public class VConvertRefposPrecompPK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public VConvertRefposPrecompPK() {
	}

	/**
	 */

	@Column(name = "FROM_CONTIG_FEATURE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public BigDecimal fromContigFeatureId;
	/**
	 */

	@Column(name = "FROM_POSITION", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public BigDecimal fromPosition;
	/**
	 */

	@Column(name = "TO_CONTIG_FEATURE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public BigDecimal toContigFeatureId;
	/**
	 */

	@Column(name = "TO_POSITION", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public BigDecimal toPosition;

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
		if (!(obj instanceof VConvertRefposPrecompPK))
			return false;
		VConvertRefposPrecompPK equalCheck = (VConvertRefposPrecompPK) obj;
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
	public String toString() {
		StringBuilder sb = new StringBuilder("VConvertRefposPrecompPK");
		sb.append(" fromContigFeatureId: ").append(getFromContigFeatureId());
		sb.append(" fromPosition: ").append(getFromPosition());
		sb.append(" toContigFeatureId: ").append(getToContigFeatureId());
		sb.append(" toPosition: ").append(getToPosition());
		return sb.toString();
	}
}
