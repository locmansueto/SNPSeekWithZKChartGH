package org.irri.iric.portal.chado.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 */
public class VSnpAllvarsMinPK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public VSnpAllvarsMinPK() {
	}

	/**
	 */

	@Column(name = "VAR", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public BigDecimal var;
	/**
	 */

	@Column(name = "POS", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public BigDecimal pos;
	/**
	 */

	@Column(name = "CHR", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public Long chr;

	/**
	 */
	public void setVar(BigDecimal var) {
		this.var = var;
	}

	/**
	 */
	public BigDecimal getVar() {
		return this.var;
	}

	/**
	 */
	public void setPos(BigDecimal pos) {
		this.pos = pos;
	}

	/**
	 */
	public BigDecimal getPos() {
		return this.pos;
	}

	/**
	 */
	public void setChr(Long chr) {
		this.chr = chr;
	}

	/**
	 */
	public Long getChr() {
		return this.chr;
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((var == null) ? 0 : var.hashCode()));
		result = (int) (prime * result + ((pos == null) ? 0 : pos.hashCode()));
		result = (int) (prime * result + ((chr == null) ? 0 : chr.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VSnpAllvarsMinPK))
			return false;
		VSnpAllvarsMinPK equalCheck = (VSnpAllvarsMinPK) obj;
		if ((var == null && equalCheck.var != null) || (var != null && equalCheck.var == null))
			return false;
		if (var != null && !var.equals(equalCheck.var))
			return false;
		if ((pos == null && equalCheck.pos != null) || (pos != null && equalCheck.pos == null))
			return false;
		if (pos != null && !pos.equals(equalCheck.pos))
			return false;
		if ((chr == null && equalCheck.chr != null) || (chr != null && equalCheck.chr == null))
			return false;
		if (chr != null && !chr.equals(equalCheck.chr))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("VSnpAllvarsMinPK");
		sb.append(" var: ").append(getVar());
		sb.append(" pos: ").append(getPos());
		sb.append(" chr: ").append(getChr());
		return sb.toString();
	}
}
