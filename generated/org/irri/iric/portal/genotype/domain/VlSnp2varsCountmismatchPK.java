package org.irri.iric.portal.genotype.domain;

import java.io.Serializable;

import javax.persistence.Id;

import javax.persistence.*;

/**
 */
public class VlSnp2varsCountmismatchPK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public VlSnp2varsCountmismatchPK() {
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((var1 == null) ? 0 : var1.hashCode()));
		result = (int) (prime * result + ((var2 == null) ? 0 : var2.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VlSnp2varsCountmismatchPK))
			return false;
		VlSnp2varsCountmismatchPK equalCheck = (VlSnp2varsCountmismatchPK) obj;
		if ((var1 == null && equalCheck.var1 != null) || (var1 != null && equalCheck.var1 == null))
			return false;
		if (var1 != null && !var1.equals(equalCheck.var1))
			return false;
		if ((var2 == null && equalCheck.var2 != null) || (var2 != null && equalCheck.var2 == null))
			return false;
		if (var2 != null && !var2.equals(equalCheck.var2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("VlSnp2varsCountmismatchPK");
		sb.append(" var1: ").append(getVar1());
		sb.append(" var2: ").append(getVar2());
		return sb.toString();
	}
}
