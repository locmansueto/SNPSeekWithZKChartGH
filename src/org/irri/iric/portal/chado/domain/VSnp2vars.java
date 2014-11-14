package org.irri.iric.portal.chado.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.Snps2Vars;

/**
 */
@IdClass(org.irri.iric.portal.chado.domain.VSnp2varsPK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVSnp2varss", query = "select myVSnp2vars from VSnp2vars myVSnp2vars"),
		@NamedQuery(name = "findVSnp2varsByChr", query = "select myVSnp2vars from VSnp2vars myVSnp2vars where myVSnp2vars.chr = ?1"),
		@NamedQuery(name = "findVSnp2varsByPos", query = "select myVSnp2vars from VSnp2vars myVSnp2vars where myVSnp2vars.pos = ?1-1"),
		
		@NamedQuery(name = "findVSnp2varsByVarsChrPosBetweenAll", query = "select myVSnp2vars from VSnp2vars myVSnp2vars where  myVSnp2vars.chr = (?1+2) " +
														" and myVSnp2vars.pos between ?2-1 and ?3 and myVSnp2vars.var1 = ?4 and myVSnp2vars.var2 = ?5 order by myVSnp2vars.pos"),
		@NamedQuery(name = "findVSnp2varsByVarsChrPosBetweenMismatch", query = "select myVSnp2vars from VSnp2vars myVSnp2vars where  myVSnp2vars.chr = (?1+2) " +
																" and myVSnp2vars.pos between ?2-1 and ?3 and myVSnp2vars.var1 = ?4 and myVSnp2vars.var2 = ?5 " + 
																" and myVSnp2vars.var1nuc<>myVSnp2vars.var2nuc order by myVSnp2vars.pos"),
		
		
		@NamedQuery(name = "findVSnp2varsByPrimaryKey", query = "select myVSnp2vars from VSnp2vars myVSnp2vars where myVSnp2vars.var1 = ?1 and myVSnp2vars.var2 = ?2 and myVSnp2vars.snpFeatureId = ?3"),
		@NamedQuery(name = "findVSnp2varsByRefnuc", query = "select myVSnp2vars from VSnp2vars myVSnp2vars where myVSnp2vars.refnuc = ?1"),
		@NamedQuery(name = "findVSnp2varsByRefnucContaining", query = "select myVSnp2vars from VSnp2vars myVSnp2vars where myVSnp2vars.refnuc like ?1"),
		@NamedQuery(name = "findVSnp2varsBySnpFeatureId", query = "select myVSnp2vars from VSnp2vars myVSnp2vars where myVSnp2vars.snpFeatureId = ?1"),
		@NamedQuery(name = "findVSnp2varsByVar1", query = "select myVSnp2vars from VSnp2vars myVSnp2vars where myVSnp2vars.var1 = ?1"),
		@NamedQuery(name = "findVSnp2varsByVar1nuc", query = "select myVSnp2vars from VSnp2vars myVSnp2vars where myVSnp2vars.var1nuc = ?1"),
		@NamedQuery(name = "findVSnp2varsByVar1nucContaining", query = "select myVSnp2vars from VSnp2vars myVSnp2vars where myVSnp2vars.var1nuc like ?1"),
		@NamedQuery(name = "findVSnp2varsByVar2", query = "select myVSnp2vars from VSnp2vars myVSnp2vars where myVSnp2vars.var2 = ?1"),
		@NamedQuery(name = "findVSnp2varsByVar2nuc", query = "select myVSnp2vars from VSnp2vars myVSnp2vars where myVSnp2vars.var2nuc = ?1"),
		@NamedQuery(name = "findVSnp2varsByVar2nucContaining", query = "select myVSnp2vars from VSnp2vars myVSnp2vars where myVSnp2vars.var2nuc like ?1") })
@Table(schema = "IRIC", name = "V_SNP_2VARS")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VSnp2vars")
public class VSnp2vars implements Serializable, Snps2Vars {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "VAR1", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
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

	@Column(name = "SNP_FEATURE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal snpFeatureId;
	/**
	 */

	@Column(name = "PARTITION_ID")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer chr;
	/**
	 */

	@Column(name = "POS", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal pos;
	/**
	 */

	@Column(name = "REFNUC", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String refnuc;
	/**
	 */

	@Column(name = "VAR1NUC", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String var1nuc;
	/**
	 */

	@Column(name = "VAR2NUC", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String var2nuc;

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
	public void setSnpFeatureId(BigDecimal snpFeatureId) {
		this.snpFeatureId = snpFeatureId;
	}

	/**
	 */
	public BigDecimal getSnpFeatureId() {
		return this.snpFeatureId;
	}

	/**
	 */
	public void setChr(Integer chr) {
		this.chr = chr+2;
	}

	/**
	 */
	public Integer getChr() {
		return this.chr-2;
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
	public void setRefnuc(String refnuc) {
		this.refnuc = refnuc;
	}

	/**
	 */
	public String getRefnuc() {
		return this.refnuc;
	}

	/**
	 */
	public void setVar1nuc(String var1nuc) {
		this.var1nuc = var1nuc;
	}

	/**
	 */
	public String getVar1nuc() {
		return this.var1nuc;
	}

	/**
	 */
	public void setVar2nuc(String var2nuc) {
		this.var2nuc = var2nuc;
	}

	/**
	 */
	public String getVar2nuc() {
		return this.var2nuc;
	}

	/**
	 */
	public VSnp2vars() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VSnp2vars that) {
		setVar1(that.getVar1());
		setVar2(that.getVar2());
		setSnpFeatureId(that.getSnpFeatureId());
		setChr(that.getChr());
		setPos(that.getPos());
		setRefnuc(that.getRefnuc());
		setVar1nuc(that.getVar1nuc());
		setVar2nuc(that.getVar2nuc());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("var1=[").append(var1).append("] ");
		buffer.append("var2=[").append(var2).append("] ");
		buffer.append("snpFeatureId=[").append(snpFeatureId).append("] ");
		buffer.append("chr=[").append(chr).append("] ");
		buffer.append("pos=[").append(pos).append("] ");
		buffer.append("refnuc=[").append(refnuc).append("] ");
		buffer.append("var1nuc=[").append(var1nuc).append("] ");
		buffer.append("var2nuc=[").append(var2nuc).append("] ");

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
		result = (int) (prime * result + ((snpFeatureId == null) ? 0 : snpFeatureId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VSnp2vars))
			return false;
		VSnp2vars equalCheck = (VSnp2vars) obj;
		if ((var1 == null && equalCheck.var1 != null) || (var1 != null && equalCheck.var1 == null))
			return false;
		if (var1 != null && !var1.equals(equalCheck.var1))
			return false;
		if ((var2 == null && equalCheck.var2 != null) || (var2 != null && equalCheck.var2 == null))
			return false;
		if (var2 != null && !var2.equals(equalCheck.var2))
			return false;
		if ((snpFeatureId == null && equalCheck.snpFeatureId != null) || (snpFeatureId != null && equalCheck.snpFeatureId == null))
			return false;
		if (snpFeatureId != null && !snpFeatureId.equals(equalCheck.snpFeatureId))
			return false;
		return true;
	}
}
