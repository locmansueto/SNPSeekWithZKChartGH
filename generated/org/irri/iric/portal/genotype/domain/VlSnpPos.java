package org.irri.iric.portal.genotype.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.SnpsAllvarsPos;

/**
 */
@IdClass(org.irri.iric.portal.genotype.domain.VlSnpPosPK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVlSnpPoss", query = "select myVlSnpPos from VlSnpPos myVlSnpPos"),
		@NamedQuery(name = "findVlSnpPosByChr", query = "select myVlSnpPos from VlSnpPos myVlSnpPos where myVlSnpPos.chr = ?1"),
		@NamedQuery(name = "findVlSnpPosByPos", query = "select myVlSnpPos from VlSnpPos myVlSnpPos where myVlSnpPos.pos = ?1"),
		@NamedQuery(name = "findVlSnpPosByPrimaryKey", query = "select myVlSnpPos from VlSnpPos myVlSnpPos where myVlSnpPos.chr = ?1 and myVlSnpPos.pos = ?2"),
		@NamedQuery(name = "findVlSnpPosByRefnuc", query = "select myVlSnpPos from VlSnpPos myVlSnpPos where myVlSnpPos.refnuc = ?1"),
		@NamedQuery(name = "findVlSnpPosByRefnucContaining", query = "select myVlSnpPos from VlSnpPos myVlSnpPos where myVlSnpPos.refnuc like ?1") })
@Table(schema = "LMANSUETO", name = "VL_SNP_POS")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/genotype/domain", name = "VlSnpPos")
public class VlSnpPos implements Serializable , SnpsAllvarsPos {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "CHR", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer chr;
	/**
	 */

	@Column(name = "POS", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer pos;
	/**
	 */

	@Column(name = "REFNUC", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String refnuc;

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
	public BigDecimal getPos() {
		return new BigDecimal(this.pos);
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
	public VlSnpPos() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VlSnpPos that) {
		setChr(that.getChr());
		setPos(that.getPos().intValue());
		setRefnuc(that.getRefnuc());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("chr=[").append(chr).append("] ");
		buffer.append("pos=[").append(pos).append("] ");
		buffer.append("refnuc=[").append(refnuc).append("] ");

		return buffer.toString();
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
		if (!(obj instanceof VlSnpPos))
			return false;
		VlSnpPos equalCheck = (VlSnpPos) obj;
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
	
	
}
