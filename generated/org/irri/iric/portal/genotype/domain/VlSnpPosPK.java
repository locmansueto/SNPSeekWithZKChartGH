package org.irri.iric.portal.genotype.domain;

import java.io.Serializable;

import javax.persistence.Id;

import javax.persistence.*;

/**
 */
public class VlSnpPosPK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public VlSnpPosPK() {
	}

	/**
	 */

	@Column(name = "CHR", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public Integer chr;
	/**
	 */

	@Column(name = "POS", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	public Integer pos;

	/**
	 */
	public void setChr(Integer chr) {
		this.chr = chr;
	}

	/**
	 */
	public Integer getChr() {
		return this.chr;
	}

	/**
	 */
	public void setPos(Integer pos) {
		this.pos = pos;
	}

	/**
	 */
	public Integer getPos() {
		return this.pos;
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((chr == null) ? 0 : chr.hashCode()));
		result = (int) (prime * result + ((pos == null) ? 0 : pos.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VlSnpPosPK))
			return false;
		VlSnpPosPK equalCheck = (VlSnpPosPK) obj;
		if ((chr == null && equalCheck.chr != null) || (chr != null && equalCheck.chr == null))
			return false;
		if (chr != null && !chr.equals(equalCheck.chr))
			return false;
		if ((pos == null && equalCheck.pos != null) || (pos != null && equalCheck.pos == null))
			return false;
		if (pos != null && !pos.equals(equalCheck.pos))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("VlSnpPosPK");
		sb.append(" chr: ").append(getChr());
		sb.append(" pos: ").append(getPos());
		return sb.toString();
	}
}
