package org.irri.iric.portal.genotype.domain;

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
@IdClass(org.irri.iric.portal.genotype.domain.VlSnp2varsCountmismatchPK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVlSnp2varsCountmismatchs", query = "select myVlSnp2varsCountmismatch from VlSnp2varsCountmismatch myVlSnp2varsCountmismatch"),
		@NamedQuery(name = "findVlSnp2varsCountmismatchByMismatch", query = "select myVlSnp2varsCountmismatch from VlSnp2varsCountmismatch myVlSnp2varsCountmismatch where myVlSnp2varsCountmismatch.mismatch = ?1"),
		@NamedQuery(name = "findVlSnp2varsCountmismatchByPrimaryKey", query = "select myVlSnp2varsCountmismatch from VlSnp2varsCountmismatch myVlSnp2varsCountmismatch where myVlSnp2varsCountmismatch.var1 = ?1 and myVlSnp2varsCountmismatch.var2 = ?2"),
		@NamedQuery(name = "findVlSnp2varsCountmismatchByVar1", query = "select myVlSnp2varsCountmismatch from VlSnp2varsCountmismatch myVlSnp2varsCountmismatch where myVlSnp2varsCountmismatch.var1 = ?1"),
		@NamedQuery(name = "findVlSnp2varsCountmismatchByVar2", query = "select myVlSnp2varsCountmismatch from VlSnp2varsCountmismatch myVlSnp2varsCountmismatch where myVlSnp2varsCountmismatch.var2 = ?1") })
@Table(schema = "LMANSUETO", name = "VL_SNP_2VARS_COUNTMISMATCH")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/genotype/domain", name = "VlSnp2varsCountmismatch")
public class VlSnp2varsCountmismatch implements Serializable, Snps2VarsCountmismatch {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "VAR1", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer var1;
	/**
	 */

	@Column(name = "VAR2", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer var2;
	/**
	 */

	@Column(name = "MISMATCH")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer mismatch;

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
	public VlSnp2varsCountmismatch() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VlSnp2varsCountmismatch that) {
		setVar1(that.getVar1());
		setVar2(that.getVar2());
		setMismatch(that.getMismatch().intValue());
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
		if (!(obj instanceof VlSnp2varsCountmismatch))
			return false;
		VlSnp2varsCountmismatch equalCheck = (VlSnp2varsCountmismatch) obj;
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
