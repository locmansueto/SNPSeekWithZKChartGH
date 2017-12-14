package org.irri.iric.portal.chado.postgres.domain;

import java.io.Serializable;

import javax.persistence.Id;

import javax.persistence.*;

/**
 */
public class VIricstockPhenotypeQualvalPK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public VIricstockPhenotypeQualvalPK() {
	}

	/**
	 */

	@Column(name = "phenotype_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public Integer phenotypeId;
	/**
	 */

	@Column(name = "qual_value", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public String qualValue;

	/**
	 */
	public void setPhenotypeId(Integer phenotypeId) {
		this.phenotypeId = phenotypeId;
	}

	/**
	 */
	public Integer getPhenotypeId() {
		return this.phenotypeId;
	}

	/**
	 */
	public void setQualValue(String qualValue) {
		this.qualValue = qualValue;
	}

	/**
	 */
	public String getQualValue() {
		return this.qualValue;
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((phenotypeId == null) ? 0 : phenotypeId.hashCode()));
		result = (int) (prime * result + ((qualValue == null) ? 0 : qualValue.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VIricstockPhenotypeQualvalPK))
			return false;
		VIricstockPhenotypeQualvalPK equalCheck = (VIricstockPhenotypeQualvalPK) obj;
		if ((phenotypeId == null && equalCheck.phenotypeId != null) || (phenotypeId != null && equalCheck.phenotypeId == null))
			return false;
		if (phenotypeId != null && !phenotypeId.equals(equalCheck.phenotypeId))
			return false;
		if ((qualValue == null && equalCheck.qualValue != null) || (qualValue != null && equalCheck.qualValue == null))
			return false;
		if (qualValue != null && !qualValue.equals(equalCheck.qualValue))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("VIricstockPhenotypeQualvalPK");
		sb.append(" phenotypeId: ").append(getPhenotypeId());
		sb.append(" qualValue: ").append(getQualValue());
		return sb.toString();
	}
}
