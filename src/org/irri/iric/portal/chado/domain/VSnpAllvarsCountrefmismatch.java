package org.irri.iric.portal.chado.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.SnpsAllvarsRefMismatch;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVSnpAllvarsCountrefmismatchs", query = "select myVSnpAllvarsCountrefmismatch from VSnpAllvarsCountrefmismatch myVSnpAllvarsCountrefmismatch"),
		@NamedQuery(name = "findVSnpAllvarsCountrefmismatchByMismatch", query = "select myVSnpAllvarsCountrefmismatch from VSnpAllvarsCountrefmismatch myVSnpAllvarsCountrefmismatch where myVSnpAllvarsCountrefmismatch.mismatch = ?1"),
		@NamedQuery(name = "findVSnpAllvarsCountrefmismatchByPrimaryKey", query = "select myVSnpAllvarsCountrefmismatch from VSnpAllvarsCountrefmismatch myVSnpAllvarsCountrefmismatch where myVSnpAllvarsCountrefmismatch.var = ?1"),
		@NamedQuery(name = "findVSnpAllvarsCountrefmismatchByVar", query = "select myVSnpAllvarsCountrefmismatch from VSnpAllvarsCountrefmismatch myVSnpAllvarsCountrefmismatch where myVSnpAllvarsCountrefmismatch.var = ?1") })
@Table(schema = "IRIC", name = "VL_SNPALLVARS_COUNTREFMISMATCH")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VSnpAllvarsCountrefmismatch")
public class VSnpAllvarsCountrefmismatch implements Serializable ,  SnpsAllvarsRefMismatch {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "VAR", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal var;
	/**
	 */

	@Column(name = "MISMATCH", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal mismatch;

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
	public void setMismatch(BigDecimal mismatch) {
		this.mismatch = mismatch;
	}

	/**
	 */
	public BigDecimal getMismatch() {
		return this.mismatch;
	}

	/**
	 */
	public VSnpAllvarsCountrefmismatch() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VSnpAllvarsCountrefmismatch that) {
		setVar(that.getVar());
		setMismatch(that.getMismatch());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("var=[").append(var).append("] ");
		buffer.append("mismatch=[").append(mismatch).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((var == null) ? 0 : var.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VSnpAllvarsCountrefmismatch))
			return false;
		VSnpAllvarsCountrefmismatch equalCheck = (VSnpAllvarsCountrefmismatch) obj;
		if ((var == null && equalCheck.var != null) || (var != null && equalCheck.var == null))
			return false;
		if (var != null && !var.equals(equalCheck.var))
			return false;
		return true;
	}
}
