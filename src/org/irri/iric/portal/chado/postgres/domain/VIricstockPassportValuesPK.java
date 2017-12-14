package org.irri.iric.portal.chado.postgres.domain;

import java.io.Serializable;

import javax.persistence.Id;

import javax.persistence.*;

/**
 */
public class VIricstockPassportValuesPK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public VIricstockPassportValuesPK() {
	}

	/**
	 */

	@Column(name = "type_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public Integer typeId;
	/**
	 */

	@Column(name = "value", length = 4000, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public String value;

	/**
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	/**
	 */
	public Integer getTypeId() {
		return this.typeId;
	}

	/**
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((typeId == null) ? 0 : typeId.hashCode()));
		result = (int) (prime * result + ((value == null) ? 0 : value.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VIricstockPassportValuesPK))
			return false;
		VIricstockPassportValuesPK equalCheck = (VIricstockPassportValuesPK) obj;
		if ((typeId == null && equalCheck.typeId != null) || (typeId != null && equalCheck.typeId == null))
			return false;
		if (typeId != null && !typeId.equals(equalCheck.typeId))
			return false;
		if ((value == null && equalCheck.value != null) || (value != null && equalCheck.value == null))
			return false;
		if (value != null && !value.equals(equalCheck.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("VIricstockPassportValuesPK");
		sb.append(" typeId: ").append(getTypeId());
		sb.append(" value: ").append(getValue());
		return sb.toString();
	}
}
