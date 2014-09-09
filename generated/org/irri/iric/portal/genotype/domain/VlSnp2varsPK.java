package org.irri.iric.portal.genotype.domain;

import java.io.Serializable;

import javax.persistence.Id;

import javax.persistence.*;

/**
 */
public class VlSnp2varsPK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public VlSnp2varsPK() {
	}

	/**
	 */

	@Column(name = "VAR1", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public Integer var1;
	/**
	 */

	@Column(name = "VAR2", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public Integer var2;
	/**
	 */

	@Column(name = "SNP_FEATURE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public Integer snpFeatureId;

	/**
	 */
	public void setVar1(Integer var1) {
		this.var1 = var1;
	}

	/**
	 */
	public Integer getVar1() {
		return this.var1;
	}

	/**
	 */
	public void setVar2(Integer var2) {
		this.var2 = var2;
	}

	/**
	 */
	public Integer getVar2() {
		return this.var2;
	}

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
		if (!(obj instanceof VlSnp2varsPK))
			return false;
		VlSnp2varsPK equalCheck = (VlSnp2varsPK) obj;
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
		StringBuilder sb = new StringBuilder("VlSnp2varsPK");
		sb.append(" var1: ").append(getVar1());
		sb.append(" var2: ").append(getVar2());
		sb.append(" snpFeatureId: ").append(getSnpFeatureId());
		return sb.toString();
	}
}
