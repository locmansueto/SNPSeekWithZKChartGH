package org.irri.iric.portal.chado.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 */
public class VConvertRefpositionPK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public VConvertRefpositionPK() {
	}

	/**
	 */

	@Column(name = "FROM_ORGANISM_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public BigDecimal fromOrganismId;
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
		if (!(obj instanceof VConvertRefpositionPK))
			return false;
		VConvertRefpositionPK equalCheck = (VConvertRefpositionPK) obj;
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
	public String toString() {
		StringBuilder sb = new StringBuilder("VConvertRefpositionPK");
		sb.append(" fromOrganismId: ").append(getFromOrganismId());
		sb.append(" fromContigFeatureId: ").append(getFromContigFeatureId());
		sb.append(" fromPosition: ").append(getFromPosition());
		return sb.toString();
	}
}
