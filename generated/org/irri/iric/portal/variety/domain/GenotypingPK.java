package org.irri.iric.portal.variety.domain;

import java.io.Serializable;

import javax.persistence.Id;

import javax.persistence.*;

/**
 */
public class GenotypingPK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public GenotypingPK() {
	}

	/**
	 */

	@Column(name = "SNP_ID", length = 10)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public String snpId;
	/**
	 */

	@Column(name = "NSFTV_ID")
	@Basic(fetch = FetchType.EAGER)
	@Id
	public Integer nsftvId;

	/**
	 */
	public void setSnpId(String snpId) {
		this.snpId = snpId;
	}

	/**
	 */
	public String getSnpId() {
		return this.snpId;
	}

	/**
	 */
	public void setNsftvId(Integer nsftvId) {
		this.nsftvId = nsftvId;
	}

	/**
	 */
	public Integer getNsftvId() {
		return this.nsftvId;
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((snpId == null) ? 0 : snpId.hashCode()));
		result = (int) (prime * result + ((nsftvId == null) ? 0 : nsftvId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof GenotypingPK))
			return false;
		GenotypingPK equalCheck = (GenotypingPK) obj;
		if ((snpId == null && equalCheck.snpId != null) || (snpId != null && equalCheck.snpId == null))
			return false;
		if (snpId != null && !snpId.equals(equalCheck.snpId))
			return false;
		if ((nsftvId == null && equalCheck.nsftvId != null) || (nsftvId != null && equalCheck.nsftvId == null))
			return false;
		if (nsftvId != null && !nsftvId.equals(equalCheck.nsftvId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("GenotypingPK");
		sb.append(" snpId: ").append(getSnpId());
		sb.append(" nsftvId: ").append(getNsftvId());
		return sb.toString();
	}
}
