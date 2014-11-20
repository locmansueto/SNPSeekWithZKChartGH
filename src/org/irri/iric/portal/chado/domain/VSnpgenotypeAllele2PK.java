package org.irri.iric.portal.chado.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 */
public class VSnpgenotypeAllele2PK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public VSnpgenotypeAllele2PK() {
	}

	/**
	 */

	@Column(name = "SNP_FEATURE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public BigDecimal snpFeatureId;
	/**
	 */

	@Column(name = "IRIC_STOCK_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public BigDecimal iricStockId;

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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((snpFeatureId == null) ? 0 : snpFeatureId.hashCode()));
		result = (int) (prime * result + ((iricStockId == null) ? 0 : iricStockId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VSnpgenotypeAllele2PK))
			return false;
		VSnpgenotypeAllele2PK equalCheck = (VSnpgenotypeAllele2PK) obj;
		if ((snpFeatureId == null && equalCheck.snpFeatureId != null) || (snpFeatureId != null && equalCheck.snpFeatureId == null))
			return false;
		if (snpFeatureId != null && !snpFeatureId.equals(equalCheck.snpFeatureId))
			return false;
		if ((iricStockId == null && equalCheck.iricStockId != null) || (iricStockId != null && equalCheck.iricStockId == null))
			return false;
		if (iricStockId != null && !iricStockId.equals(equalCheck.iricStockId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("VSnpgenotypeAllele2PK");
		sb.append(" snpFeatureId: ").append(getSnpFeatureId());
		sb.append(" iricStockId: ").append(getIricStockId());
		return sb.toString();
	}
}
