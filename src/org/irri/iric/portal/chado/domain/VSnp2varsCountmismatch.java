package org.irri.iric.portal.chado.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.Snps2VarsCountmismatch;

/**
 */
@IdClass(org.irri.iric.portal.chado.domain.VSnp2varsCountmismatchPK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVSnp2varsCountmismatchs", query = "select myVSnp2varsCountmismatch from VSnp2varsCountmismatch myVSnp2varsCountmismatch"),
		@NamedQuery(name = "findVSnp2varsCountmismatchByMismatch", query = "select myVSnp2varsCountmismatch from VSnp2varsCountmismatch myVSnp2varsCountmismatch where myVSnp2varsCountmismatch.mismatch = ?1"),
		@NamedQuery(name = "findVSnp2varsCountmismatchByPrimaryKey", query = "select myVSnp2varsCountmismatch from VSnp2varsCountmismatch myVSnp2varsCountmismatch where myVSnp2varsCountmismatch.var1 = ?1 and myVSnp2varsCountmismatch.var2 = ?2"),
		@NamedQuery(name = "findVSnp2varsCountmismatchByVar1", query = "select myVSnp2varsCountmismatch from VSnp2varsCountmismatch myVSnp2varsCountmismatch where myVSnp2varsCountmismatch.var1 = ?1"),
		@NamedQuery(name = "findVSnp2varsCountmismatchByVar2", query = "select myVSnp2varsCountmismatch from VSnp2varsCountmismatch myVSnp2varsCountmismatch where myVSnp2varsCountmismatch.var2 = ?1") })
@Table(schema = "IRIC", name = "VL_SNP_2VARS_COUNTMISMATCH")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VSnp2varsCountmismatch")
public class VSnp2varsCountmismatch implements Serializable, Snps2VarsCountmismatch  {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "VAR1", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	//Integer var1;
	BigDecimal var1;
	/**
	 */

	@Column(name = "VAR2", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal var2;
	/**
	 */

	@Column(name = "MISMATCH")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal mismatch;

	/**
	 */
	public void setVar1(BigDecimal var1) {
		this.var1 = var1;
	}

	/**
	 */
	public BigDecimal getVar1() {
		return this.var1;
	}

	/**
	 */
	public void setVar2(BigDecimal var2) {
		this.var2 = var2;
	}

	/**
	 */
	public BigDecimal getVar2() {
		return this.var2;
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
	public VSnp2varsCountmismatch() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VSnp2varsCountmismatch that) {
		setVar1(that.getVar1());
		setVar2(that.getVar2());
		setMismatch(that.getMismatch());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("var1=[").append(var1).append("] ");
		buffer.append("var2=[").append(var2).append("] ");
		buffer.append("mismatch=[").append(mismatch).append("] ");

		return buffer.toString();
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
		if (!(obj instanceof VSnp2varsCountmismatch))
			return false;
		VSnp2varsCountmismatch equalCheck = (VSnp2varsCountmismatch) obj;
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
}
