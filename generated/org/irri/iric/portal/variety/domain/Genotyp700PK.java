package org.irri.iric.portal.variety.domain;

import java.io.Serializable;

import javax.persistence.Id;

import javax.persistence.*;

/**
 */
public class Genotyp700PK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public Genotyp700PK() {
	}

	/**
	 */

	@Column(name = "VAR_ID")
	@Basic(fetch = FetchType.EAGER)
	@Id
	public Integer varId;
	/**
	 */

	@Column(name = "SNP_ID")
	@Basic(fetch = FetchType.EAGER)
	@Id
	public Integer snpId;

	/**
	 */
	public void setVarId(Integer varId) {
		this.varId = varId;
	}

	/**
	 */
	public Integer getVarId() {
		return this.varId;
	}

	/**
	 */
	public void setSnpId(Integer snpId) {
		this.snpId = snpId;
	}

	/**
	 */
	public Integer getSnpId() {
		return this.snpId;
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((varId == null) ? 0 : varId.hashCode()));
		result = (int) (prime * result + ((snpId == null) ? 0 : snpId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Genotyp700PK))
			return false;
		Genotyp700PK equalCheck = (Genotyp700PK) obj;
		if ((varId == null && equalCheck.varId != null) || (varId != null && equalCheck.varId == null))
			return false;
		if (varId != null && !varId.equals(equalCheck.varId))
			return false;
		if ((snpId == null && equalCheck.snpId != null) || (snpId != null && equalCheck.snpId == null))
			return false;
		if (snpId != null && !snpId.equals(equalCheck.snpId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Genotyp700PK");
		sb.append(" varId: ").append(getVarId());
		sb.append(" snpId: ").append(getSnpId());
		return sb.toString();
	}
}
