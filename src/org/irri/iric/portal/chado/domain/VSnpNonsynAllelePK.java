package org.irri.iric.portal.chado.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 */
public class VSnpNonsynAllelePK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public VSnpNonsynAllelePK() {
	}

	/**
	 */

	@Column(name = "SNP_FEATURE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public BigDecimal snpFeatureId;
	/**
	 */

	@Column(name = "NON_SYN_ALLELE", length = 1, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public String nonSynAllele;

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
	public void setNonSynAllele(String nonSynAllele) {
		this.nonSynAllele = nonSynAllele;
	}

	/**
	 */
	public String getNonSynAllele() {
		return this.nonSynAllele;
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((snpFeatureId == null) ? 0 : snpFeatureId.hashCode()));
		result = (int) (prime * result + ((nonSynAllele == null) ? 0 : nonSynAllele.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VSnpNonsynAllelePK))
			return false;
		VSnpNonsynAllelePK equalCheck = (VSnpNonsynAllelePK) obj;
		if ((snpFeatureId == null && equalCheck.snpFeatureId != null) || (snpFeatureId != null && equalCheck.snpFeatureId == null))
			return false;
		if (snpFeatureId != null && !snpFeatureId.equals(equalCheck.snpFeatureId))
			return false;
		if ((nonSynAllele == null && equalCheck.nonSynAllele != null) || (nonSynAllele != null && equalCheck.nonSynAllele == null))
			return false;
		if (nonSynAllele != null && !nonSynAllele.equals(equalCheck.nonSynAllele))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("VSnpNonsynAllelePK");
		sb.append(" snpFeatureId: ").append(getSnpFeatureId());
		sb.append(" nonSynAllele: ").append(getNonSynAllele());
		return sb.toString();
	}
}
