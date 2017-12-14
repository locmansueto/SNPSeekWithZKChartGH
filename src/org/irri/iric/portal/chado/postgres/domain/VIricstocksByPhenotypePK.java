package org.irri.iric.portal.chado.postgres.domain;

import java.io.Serializable;

import javax.persistence.Id;

import javax.persistence.*;

/**
 */
public class VIricstocksByPhenotypePK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public VIricstocksByPhenotypePK() {
	}

	/**
	 */

	@Column(name = "iric_stock_phenotype_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public Integer iricStockPhenotypeId;
	/**
	 */

	@Column(name = "iric_stock_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public Integer iricStockId;

	/**
	 */
	public void setIricStockPhenotypeId(Integer iricStockPhenotypeId) {
		this.iricStockPhenotypeId = iricStockPhenotypeId;
	}

	/**
	 */
	public Integer getIricStockPhenotypeId() {
		return this.iricStockPhenotypeId;
	}

	/**
	 */
	public void setIricStockId(Integer iricStockId) {
		this.iricStockId = iricStockId;
	}

	/**
	 */
	public Integer getIricStockId() {
		return this.iricStockId;
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((iricStockPhenotypeId == null) ? 0 : iricStockPhenotypeId.hashCode()));
		result = (int) (prime * result + ((iricStockId == null) ? 0 : iricStockId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VIricstocksByPhenotypePK))
			return false;
		VIricstocksByPhenotypePK equalCheck = (VIricstocksByPhenotypePK) obj;
		if ((iricStockPhenotypeId == null && equalCheck.iricStockPhenotypeId != null) || (iricStockPhenotypeId != null && equalCheck.iricStockPhenotypeId == null))
			return false;
		if (iricStockPhenotypeId != null && !iricStockPhenotypeId.equals(equalCheck.iricStockPhenotypeId))
			return false;
		if ((iricStockId == null && equalCheck.iricStockId != null) || (iricStockId != null && equalCheck.iricStockId == null))
			return false;
		if (iricStockId != null && !iricStockId.equals(equalCheck.iricStockId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("VIricstocksByPhenotypePK");
		sb.append(" iricStockPhenotypeId: ").append(getIricStockPhenotypeId());
		sb.append(" iricStockId: ").append(getIricStockId());
		return sb.toString();
	}
}
