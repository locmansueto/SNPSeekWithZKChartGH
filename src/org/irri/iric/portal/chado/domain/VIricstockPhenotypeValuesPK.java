package org.irri.iric.portal.chado.domain;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.persistence.Id;

import javax.persistence.*;

/**
 */
public class VIricstockPhenotypeValuesPK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public VIricstockPhenotypeValuesPK() {
	}

	/**
	 */

	@Column(name = "QUAN_VALUE")
	@Basic(fetch = FetchType.EAGER)
	@Id
	public BigDecimal quanValue;
	/**
	 */

	@Column(name = "QUAL_VALUE")
	@Basic(fetch = FetchType.EAGER)
	@Id
	public String qualValue;
	/**
	 */

	@Column(name = "PHENOTYPE_ID", precision = 10, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public BigDecimal phenotypeId;

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
	public void setPhenotypeId(BigDecimal phenotypeId) {
		this.phenotypeId = phenotypeId;
	}

	/**
	 */
	public BigDecimal getPhenotypeId() {
		return this.phenotypeId;
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((quanValue == null) ? 0 : quanValue.hashCode()));
		result = (int) (prime * result + ((qualValue == null) ? 0 : qualValue.hashCode()));
		result = (int) (prime * result + ((phenotypeId == null) ? 0 : phenotypeId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VIricstockPhenotypeValuesPK))
			return false;
		VIricstockPhenotypeValuesPK equalCheck = (VIricstockPhenotypeValuesPK) obj;
		if ((quanValue == null && equalCheck.quanValue != null) || (quanValue != null && equalCheck.quanValue == null))
			return false;
		if (quanValue != null && !quanValue.equals(equalCheck.quanValue))
			return false;
		if ((qualValue == null && equalCheck.qualValue != null) || (qualValue != null && equalCheck.qualValue == null))
			return false;
		if (qualValue != null && !qualValue.equals(equalCheck.qualValue))
			return false;
		if ((phenotypeId == null && equalCheck.phenotypeId != null) || (phenotypeId != null && equalCheck.phenotypeId == null))
			return false;
		if (phenotypeId != null && !phenotypeId.equals(equalCheck.phenotypeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("VIricstockPhenotypeValuesPK");
		sb.append(" quanValue: ").append(getQuanValue());
		sb.append(" qualValue: ").append(getQualValue());
		sb.append(" phenotypeId: ").append(getPhenotypeId());
		return sb.toString();
	}
}
