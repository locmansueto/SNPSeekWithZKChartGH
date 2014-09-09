package org.irri.iric.portal.genotype.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.SnpsAllvars;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVlSnpAllvarss", query = "select myVlSnpAllvars from VlSnpAllvars myVlSnpAllvars"),
		@NamedQuery(name = "findVlSnpAllvarsByChr", query = "select myVlSnpAllvars from VlSnpAllvars myVlSnpAllvars where myVlSnpAllvars.chr = ?1"),
		@NamedQuery(name = "findVlSnpAllvarsByPos", query = "select myVlSnpAllvars from VlSnpAllvars myVlSnpAllvars where myVlSnpAllvars.pos = ?1"),
		@NamedQuery(name = "findVlSnpAllvarsByPrimaryKey", query = "select myVlSnpAllvars from VlSnpAllvars myVlSnpAllvars where myVlSnpAllvars.snpGenotypeId = ?1"),
		@NamedQuery(name = "findVlSnpAllvarsByRefnuc", query = "select myVlSnpAllvars from VlSnpAllvars myVlSnpAllvars where myVlSnpAllvars.refnuc = ?1"),
		@NamedQuery(name = "findVlSnpAllvarsByRefnucContaining", query = "select myVlSnpAllvars from VlSnpAllvars myVlSnpAllvars where myVlSnpAllvars.refnuc like ?1"),
		@NamedQuery(name = "findVlSnpAllvarsBySnpGenotypeId", query = "select myVlSnpAllvars from VlSnpAllvars myVlSnpAllvars where myVlSnpAllvars.snpGenotypeId = ?1"),
		@NamedQuery(name = "findVlSnpAllvarsByVar", query = "select myVlSnpAllvars from VlSnpAllvars myVlSnpAllvars where myVlSnpAllvars.var = ?1"),
		@NamedQuery(name = "findVlSnpAllvarsByVarnuc", query = "select myVlSnpAllvars from VlSnpAllvars myVlSnpAllvars where myVlSnpAllvars.varnuc = ?1"),
		@NamedQuery(name = "findVlSnpAllvarsByVarnucContaining", query = "select myVlSnpAllvars from VlSnpAllvars myVlSnpAllvars where myVlSnpAllvars.varnuc like ?1") })
@Table(schema = "LMANSUETO", name = "VL_SNP_ALLVARS")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/genotype/domain", name = "VlSnpAllvars")
public class VlSnpAllvars implements Serializable, SnpsAllvars {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "SNP_GENOTYPE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer snpGenotypeId;
	/**
	 */

	@Column(name = "VAR", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal var;
	/**
	 */

	@Column(name = "CHR")
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

	@Column(name = "VARNUC", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String varnuc;

	/**
	 */
	public void setSnpGenotypeId(Integer snpGenotypeId) {
		this.snpGenotypeId = snpGenotypeId;
	}

	/**
	 */
	public Integer getSnpGenotypeId() {
		return this.snpGenotypeId;
	}

	/**
	 */
	public void setVar(BigDecimal var) {
		this.var = var;
	}

	/**
	 */
	public Integer getVar() {
		return this.var.intValue();
	}

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
	public void setVarnuc(String varnuc) {
		this.varnuc = varnuc;
	}

	/**
	 */
	public String getVarnuc() {
		return this.varnuc;
	}

	/**
	 */
	public VlSnpAllvars() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VlSnpAllvars that) {
		setSnpGenotypeId(that.getSnpGenotypeId());
		setVar(new BigDecimal(that.getVar()));
		setChr(that.getChr());
		setPos(that.getPos());
		setRefnuc(that.getRefnuc());
		setVarnuc(that.getVarnuc());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("snpGenotypeId=[").append(snpGenotypeId).append("] ");
		buffer.append("var=[").append(var).append("] ");
		buffer.append("chr=[").append(chr).append("] ");
		buffer.append("pos=[").append(pos).append("] ");
		buffer.append("refnuc=[").append(refnuc).append("] ");
		buffer.append("varnuc=[").append(varnuc).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((snpGenotypeId == null) ? 0 : snpGenotypeId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VlSnpAllvars))
			return false;
		VlSnpAllvars equalCheck = (VlSnpAllvars) obj;
		if ((snpGenotypeId == null && equalCheck.snpGenotypeId != null) || (snpGenotypeId != null && equalCheck.snpGenotypeId == null))
			return false;
		if (snpGenotypeId != null && !snpGenotypeId.equals(equalCheck.snpGenotypeId))
			return false;
		return true;
	}
}
