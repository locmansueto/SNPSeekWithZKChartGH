package org.irri.iric.portal.variety.domain;

import java.io.Serializable;

import javax.persistence.Id;

import javax.persistence.*;

/**
 */
public class Dist3kPK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public Dist3kPK() {
	}

	/**
	 */

	@Column(name = "NAM1", length = 16)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public String nam1;
	/**
	 */

	@Column(name = "NAM2", length = 16)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public String nam2;

	/**
	 */
	public void setNam1(String nam1) {
		this.nam1 = nam1;
	}

	/**
	 */
	public String getNam1() {
		return this.nam1;
	}

	/**
	 */
	public void setNam2(String nam2) {
		this.nam2 = nam2;
	}

	/**
	 */
	public String getNam2() {
		return this.nam2;
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((nam1 == null) ? 0 : nam1.hashCode()));
		result = (int) (prime * result + ((nam2 == null) ? 0 : nam2.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Dist3kPK))
			return false;
		Dist3kPK equalCheck = (Dist3kPK) obj;
		if ((nam1 == null && equalCheck.nam1 != null) || (nam1 != null && equalCheck.nam1 == null))
			return false;
		if (nam1 != null && !nam1.equals(equalCheck.nam1))
			return false;
		if ((nam2 == null && equalCheck.nam2 != null) || (nam2 != null && equalCheck.nam2 == null))
			return false;
		if (nam2 != null && !nam2.equals(equalCheck.nam2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Dist3kPK");
		sb.append(" nam1: ").append(getNam1());
		sb.append(" nam2: ").append(getNam2());
		return sb.toString();
	}
}
