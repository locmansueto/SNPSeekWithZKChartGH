package org.irri.iric.portal.genotype.views;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

// Generated Jun 27, 2014 8:48:08 PM by Hibernate Tools 3.4.0.CR1

@Entity @IdClass(Snp2linesId.class)

/**
 * Snp2linesId generated by hbm2java
 */
public class Snp2linesId implements java.io.Serializable {

	@Id
	private String var1;
	@Id
	private String var2;
	@Id
	private Short chr;
	@Id
	private Long pos;
	@Id
	private String refnuc;
	@Id
	private String var1nuc;
	@Id
	private String var2nuc;

	public Snp2linesId() {
	}

	public Snp2linesId(String var1, String var2, Short chr, Long pos,
			String refnuc, String var1nuc, String var2nuc) {
		this.var1 = var1;
		this.var2 = var2;
		this.chr = chr;
		this.pos = pos;
		this.refnuc = refnuc;
		this.var1nuc = var1nuc;
		this.var2nuc = var2nuc;
	}

	public String getVar1() {
		return this.var1;
	}

	public void setVar1(String var1) {
		this.var1 = var1;
	}

	public String getVar2() {
		return this.var2;
	}

	public void setVar2(String var2) {
		this.var2 = var2;
	}

	public Short getChr() {
		return this.chr;
	}

	public void setChr(Short chr) {
		this.chr = chr;
	}

	public Long getPos() {
		return this.pos;
	}

	public void setPos(Long pos) {
		this.pos = pos;
	}

	public String getRefnuc() {
		return this.refnuc;
	}

	public void setRefnuc(String refnuc) {
		this.refnuc = refnuc;
	}

	public String getVar1nuc() {
		return this.var1nuc;
	}

	public void setVar1nuc(String var1nuc) {
		this.var1nuc = var1nuc;
	}

	public String getVar2nuc() {
		return this.var2nuc;
	}

	public void setVar2nuc(String var2nuc) {
		this.var2nuc = var2nuc;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Snp2linesId))
			return false;
		Snp2linesId castOther = (Snp2linesId) other;

		return ((this.getVar1() == castOther.getVar1()) || (this.getVar1() != null
				&& castOther.getVar1() != null && this.getVar1().equals(
				castOther.getVar1())))
				&& ((this.getVar2() == castOther.getVar2()) || (this.getVar2() != null
						&& castOther.getVar2() != null && this.getVar2()
						.equals(castOther.getVar2())))
				&& ((this.getChr() == castOther.getChr()) || (this.getChr() != null
						&& castOther.getChr() != null && this.getChr().equals(
						castOther.getChr())))
				&& ((this.getPos() == castOther.getPos()) || (this.getPos() != null
						&& castOther.getPos() != null && this.getPos().equals(
						castOther.getPos())))
				&& ((this.getRefnuc() == castOther.getRefnuc()) || (this
						.getRefnuc() != null && castOther.getRefnuc() != null && this
						.getRefnuc().equals(castOther.getRefnuc())))
				&& ((this.getVar1nuc() == castOther.getVar1nuc()) || (this
						.getVar1nuc() != null && castOther.getVar1nuc() != null && this
						.getVar1nuc().equals(castOther.getVar1nuc())))
				&& ((this.getVar2nuc() == castOther.getVar2nuc()) || (this
						.getVar2nuc() != null && castOther.getVar2nuc() != null && this
						.getVar2nuc().equals(castOther.getVar2nuc())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getVar1() == null ? 0 : this.getVar1().hashCode());
		result = 37 * result
				+ (getVar2() == null ? 0 : this.getVar2().hashCode());
		result = 37 * result
				+ (getChr() == null ? 0 : this.getChr().hashCode());
		result = 37 * result
				+ (getPos() == null ? 0 : this.getPos().hashCode());
		result = 37 * result
				+ (getRefnuc() == null ? 0 : this.getRefnuc().hashCode());
		result = 37 * result
				+ (getVar1nuc() == null ? 0 : this.getVar1nuc().hashCode());
		result = 37 * result
				+ (getVar2nuc() == null ? 0 : this.getVar2nuc().hashCode());
		return result;
	}

}
