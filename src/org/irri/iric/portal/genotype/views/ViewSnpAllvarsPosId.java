package org.irri.iric.portal.genotype.views;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

// Generated Jul 10, 2014 8:32:19 PM by Hibernate Tools 3.4.0.CR1

/**
 * ViewSnpAllvarsPosId generated by hbm2java
 */
@Entity @IdClass(ViewSnpAllvarsPosId.class)
public class ViewSnpAllvarsPosId implements java.io.Serializable {

	@Id
	private Short chr;
	@Id
	private Long pos;
	@Id
	private Character refnuc;

	public ViewSnpAllvarsPosId() {
	}

	public ViewSnpAllvarsPosId(Short chr, Long pos, Character refnuc) {
		this.chr = chr;
		this.pos = pos;
		this.refnuc = refnuc;
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

	public Character getRefnuc() {
		return this.refnuc;
	}

	public void setRefnuc(Character refnuc) {
		this.refnuc = refnuc;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ViewSnpAllvarsPosId))
			return false;
		ViewSnpAllvarsPosId castOther = (ViewSnpAllvarsPosId) other;

		return ((this.getChr() == castOther.getChr()) || (this.getChr() != null
				&& castOther.getChr() != null && this.getChr().equals(
				castOther.getChr())))
				&& ((this.getPos() == castOther.getPos()) || (this.getPos() != null
						&& castOther.getPos() != null && this.getPos().equals(
						castOther.getPos())))
				&& ((this.getRefnuc() == castOther.getRefnuc()) || (this
						.getRefnuc() != null && castOther.getRefnuc() != null && this
						.getRefnuc().equals(castOther.getRefnuc())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getChr() == null ? 0 : this.getChr().hashCode());
		result = 37 * result
				+ (getPos() == null ? 0 : this.getPos().hashCode());
		result = 37 * result
				+ (getRefnuc() == null ? 0 : this.getRefnuc().hashCode());
		return result;
	}

}
