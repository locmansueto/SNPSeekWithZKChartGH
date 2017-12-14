package org.irri.iric.portal.chado.postgres.domain;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.persistence.Id;

import javax.persistence.*;

/**
 */
public class VIricstockPhenotypeQuanvalPK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public VIricstockPhenotypeQuanvalPK() {
	}

	/**
	 */

	@Column(name = "phenotype_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public Integer phenotypeId;
	/**
	 */

	@Column(name = "quan_value", scale = 17, precision = 17, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public BigDecimal quanValue;

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
	public void setQuanValue(BigDecimal quanValue) {
		this.quanValue = quanValue;
	}

	/**
	 */
	public BigDecimal getQuanValue() {
		return this.quanValue;
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((phenotypeId == null) ? 0 : phenotypeId.hashCode()));
		result = (int) (prime * result + ((quanValue == null) ? 0 : quanValue.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VIricstockPhenotypeQuanvalPK))
			return false;
		VIricstockPhenotypeQuanvalPK equalCheck = (VIricstockPhenotypeQuanvalPK) obj;
		if ((phenotypeId == null && equalCheck.phenotypeId != null) || (phenotypeId != null && equalCheck.phenotypeId == null))
			return false;
		if (phenotypeId != null && !phenotypeId.equals(equalCheck.phenotypeId))
			return false;
		if ((quanValue == null && equalCheck.quanValue != null) || (quanValue != null && equalCheck.quanValue == null))
			return false;
		if (quanValue != null && !quanValue.equals(equalCheck.quanValue))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("VIricstockPhenotypeQuanvalPK");
		sb.append(" phenotypeId: ").append(getPhenotypeId());
		sb.append(" quanValue: ").append(getQuanValue());
		return sb.toString();
	}
}
