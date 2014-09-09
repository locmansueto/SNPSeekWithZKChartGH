package org.irri.iric.portal.chado.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 */
public class VSnp2varsPK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public VSnp2varsPK() {
	}

	/**
	 */

	@Column(name = "VAR1", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public BigDecimal var1;
	/**
	 */

	@Column(name = "VAR2", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public BigDecimal var2;
	/**
	 */

	@Column(name = "SNP_FEATURE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public BigDecimal snpFeatureId;

	/**
	 */
	public void setVar1(BigDecimal var1) {
		this.var1 = var1;
	}

	/**
	 */
	public BigDecimal getVar1() {
		return this.var1;
	}

	/**
	 */
	public void setVar2(BigDecimal var2) {
		this.var2 = var2;
	}

	/**
	 */
	public BigDecimal getVar2() {
		return this.var2;
	}

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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((var1 == null) ? 0 : var1.hashCode()));
		result = (int) (prime * result + ((var2 == null) ? 0 : var2.hashCode()));
		result = (int) (prime * result + ((snpFeatureId == null) ? 0 : snpFeatureId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VSnp2varsPK))
			return false;
		VSnp2varsPK equalCheck = (VSnp2varsPK) obj;
		if ((var1 == null && equalCheck.var1 != null) || (var1 != null && equalCheck.var1 == null))
			return false;
		if (var1 != null && !var1.equals(equalCheck.var1))
			return false;
		if ((var2 == null && equalCheck.var2 != null) || (var2 != null && equalCheck.var2 == null))
			return false;
		if (var2 != null && !var2.equals(equalCheck.var2))
			return false;
		if ((snpFeatureId == null && equalCheck.snpFeatureId != null) || (snpFeatureId != null && equalCheck.snpFeatureId == null))
			return false;
		if (snpFeatureId != null && !snpFeatureId.equals(equalCheck.snpFeatureId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("VSnp2varsPK");
		sb.append(" var1: ").append(getVar1());
		sb.append(" var2: ").append(getVar2());
		sb.append(" snpFeatureId: ").append(getSnpFeatureId());
		return sb.toString();
	}
}
