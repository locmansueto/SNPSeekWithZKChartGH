package org.irri.iric.portal.genotype.domain;

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
		@NamedQuery(name = "findAllVlSnpAllvarsCntrefmismatchs", query = "select myVlSnpAllvarsCntrefmismatch from VlSnpAllvarsCntrefmismatch myVlSnpAllvarsCntrefmismatch"),
		@NamedQuery(name = "findVlSnpAllvarsCntrefmismatchByMismatch", query = "select myVlSnpAllvarsCntrefmismatch from VlSnpAllvarsCntrefmismatch myVlSnpAllvarsCntrefmismatch where myVlSnpAllvarsCntrefmismatch.mismatch = ?1"),
		@NamedQuery(name = "findVlSnpAllvarsCntrefmismatchByPrimaryKey", query = "select myVlSnpAllvarsCntrefmismatch from VlSnpAllvarsCntrefmismatch myVlSnpAllvarsCntrefmismatch where myVlSnpAllvarsCntrefmismatch.var = ?1"),
		@NamedQuery(name = "findVlSnpAllvarsCntrefmismatchByVar", query = "select myVlSnpAllvarsCntrefmismatch from VlSnpAllvarsCntrefmismatch myVlSnpAllvarsCntrefmismatch where myVlSnpAllvarsCntrefmismatch.var = ?1") })
@Table(schema = "LMANSUETO", name = "VL_SNP_ALLVARS_CNTREFMISMATCH")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/genotype/domain", name = "VlSnpAllvarsCntrefmismatch")
public class VlSnpAllvarsCntrefmismatch implements Serializable, SnpsAllvarsRefMismatch {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "VAR", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer var;
	/**
	 */

	@Column(name = "MISMATCH")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer mismatch;

	/**
	 */
	public void setVar(Integer var) {
		this.var = var;
	}

	/**
	 */
	public Integer getVar() {
		return this.var;
	}

	/**
	 */
	public void setMismatch(Integer mismatch) {
		this.mismatch = mismatch;
	}

	/**
	 */
	public BigDecimal getMismatch() {
		return new BigDecimal(this.mismatch);
	}

	/**
	 */
	public VlSnpAllvarsCntrefmismatch() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VlSnpAllvarsCntrefmismatch that) {
		setVar(that.getVar());
		setMismatch(that.getMismatch().intValue());
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
		if (!(obj instanceof VlSnpAllvarsCntrefmismatch))
			return false;
		VlSnpAllvarsCntrefmismatch equalCheck = (VlSnpAllvarsCntrefmismatch) obj;
		if ((var == null && equalCheck.var != null) || (var != null && equalCheck.var == null))
			return false;
		if (var != null && !var.equals(equalCheck.var))
			return false;
		return true;
	}
}
