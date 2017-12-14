package org.irri.iric.portal.chado.postgres.domain;

import java.io.Serializable;

import javax.persistence.Id;

import javax.persistence.*;

/**
 */
public class MvConvertposAny2allrefsPK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public MvConvertposAny2allrefsPK() {
	}

	/**
	 */

	@Column(name = "snp_feature_id")
	@Basic(fetch = FetchType.EAGER)
	@Id
	public Integer snpFeatureId;
	/**
	 */

	@Column(name = "from_organism_id")
	@Basic(fetch = FetchType.EAGER)
	@Id
	public Integer fromOrganismId;

	/**
	 */
	public void setSnpFeatureId(Integer snpFeatureId) {
		this.snpFeatureId = snpFeatureId;
	}

	/**
	 */
	public Integer getSnpFeatureId() {
		return this.snpFeatureId;
	}

	/**
	 */
	public void setFromOrganismId(Integer fromOrganismId) {
		this.fromOrganismId = fromOrganismId;
	}

	/**
	 */
	public Integer getFromOrganismId() {
		return this.fromOrganismId;
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((snpFeatureId == null) ? 0 : snpFeatureId.hashCode()));
		result = (int) (prime * result + ((fromOrganismId == null) ? 0 : fromOrganismId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof MvConvertposAny2allrefsPK))
			return false;
		MvConvertposAny2allrefsPK equalCheck = (MvConvertposAny2allrefsPK) obj;
		if ((snpFeatureId == null && equalCheck.snpFeatureId != null) || (snpFeatureId != null && equalCheck.snpFeatureId == null))
			return false;
		if (snpFeatureId != null && !snpFeatureId.equals(equalCheck.snpFeatureId))
			return false;
		if ((fromOrganismId == null && equalCheck.fromOrganismId != null) || (fromOrganismId != null && equalCheck.fromOrganismId == null))
			return false;
		if (fromOrganismId != null && !fromOrganismId.equals(equalCheck.fromOrganismId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("MvConvertposAny2allrefsPK");
		sb.append(" snpFeatureId: ").append(getSnpFeatureId());
		sb.append(" fromOrganismId: ").append(getFromOrganismId());
		return sb.toString();
	}
}
