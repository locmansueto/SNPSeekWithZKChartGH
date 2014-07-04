package org.irri.iric.portal.variety.domain;

import java.io.Serializable;

import javax.persistence.Id;

import javax.persistence.*;

/**
 */
public class PhenotypesPK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public PhenotypesPK() {
	}

	/**
	 */

	@Column(name = "NSFTV_ID")
	@Basic(fetch = FetchType.EAGER)
	@Id
	public Integer nsftvId;
	/**
	 */

	@Column(name = "TRAIT", length = 32)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public String trait;

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
	public void setTrait(String trait) {
		this.trait = trait;
	}

	/**
	 */
	public String getTrait() {
		return this.trait;
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((nsftvId == null) ? 0 : nsftvId.hashCode()));
		result = (int) (prime * result + ((trait == null) ? 0 : trait.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof PhenotypesPK))
			return false;
		PhenotypesPK equalCheck = (PhenotypesPK) obj;
		if ((nsftvId == null && equalCheck.nsftvId != null) || (nsftvId != null && equalCheck.nsftvId == null))
			return false;
		if (nsftvId != null && !nsftvId.equals(equalCheck.nsftvId))
			return false;
		if ((trait == null && equalCheck.trait != null) || (trait != null && equalCheck.trait == null))
			return false;
		if (trait != null && !trait.equals(equalCheck.trait))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("PhenotypesPK");
		sb.append(" nsftvId: ").append(getNsftvId());
		sb.append(" trait: ").append(getTrait());
		return sb.toString();
	}
}
