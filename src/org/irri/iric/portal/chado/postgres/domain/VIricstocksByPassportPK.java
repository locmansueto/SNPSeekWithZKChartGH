package org.irri.iric.portal.chado.postgres.domain;

import java.io.Serializable;

import javax.persistence.Id;

import javax.persistence.*;

/**
 */
public class VIricstocksByPassportPK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public VIricstocksByPassportPK() {
	}

	/**
	 */

	@Column(name = "iric_stockprop_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public Integer iricStockpropId;
	/**
	 */

	@Column(name = "iric_stock_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public Integer iricStockId;

	/**
	 */
	public void setIricStockpropId(Integer iricStockpropId) {
		this.iricStockpropId = iricStockpropId;
	}

	/**
	 */
	public Integer getIricStockpropId() {
		return this.iricStockpropId;
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
		result = (int) (prime * result + ((iricStockpropId == null) ? 0 : iricStockpropId.hashCode()));
		result = (int) (prime * result + ((iricStockId == null) ? 0 : iricStockId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VIricstocksByPassportPK))
			return false;
		VIricstocksByPassportPK equalCheck = (VIricstocksByPassportPK) obj;
		if ((iricStockpropId == null && equalCheck.iricStockpropId != null) || (iricStockpropId != null && equalCheck.iricStockpropId == null))
			return false;
		if (iricStockpropId != null && !iricStockpropId.equals(equalCheck.iricStockpropId))
			return false;
		if ((iricStockId == null && equalCheck.iricStockId != null) || (iricStockId != null && equalCheck.iricStockId == null))
			return false;
		if (iricStockId != null && !iricStockId.equals(equalCheck.iricStockId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("VIricstocksByPassportPK");
		sb.append(" iricStockpropId: ").append(getIricStockpropId());
		sb.append(" iricStockId: ").append(getIricStockId());
		return sb.toString();
	}
}
