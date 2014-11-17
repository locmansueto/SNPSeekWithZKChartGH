package org.irri.iric.portal.chado.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */
@IdClass(org.irri.iric.portal.chado.domain.VSnpAllvars2PK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVSnpAllvars2s", query = "select myVSnpAllvars2 from VSnpAllvars2 myVSnpAllvars2"),
		@NamedQuery(name = "findVSnpAllvars2ByChr", query = "select myVSnpAllvars2 from VSnpAllvars2 myVSnpAllvars2 where myVSnpAllvars2.chr = ?1"),
		@NamedQuery(name = "findVSnpAllvars2ByIricStockId", query = "select myVSnpAllvars2 from VSnpAllvars2 myVSnpAllvars2 where myVSnpAllvars2.iricStockId = ?1"),
		@NamedQuery(name = "findVSnpAllvars2ByPos", query = "select myVSnpAllvars2 from VSnpAllvars2 myVSnpAllvars2 where myVSnpAllvars2.pos = ?1"),
		@NamedQuery(name = "findVSnpAllvars2ByPrimaryKey", query = "select myVSnpAllvars2 from VSnpAllvars2 myVSnpAllvars2 where myVSnpAllvars2.snpFeatureId = ?1 and myVSnpAllvars2.iricStockId = ?2"),
		@NamedQuery(name = "findVSnpAllvars2ByRefnuc", query = "select myVSnpAllvars2 from VSnpAllvars2 myVSnpAllvars2 where myVSnpAllvars2.refnuc = ?1"),
		@NamedQuery(name = "findVSnpAllvars2ByRefnucContaining", query = "select myVSnpAllvars2 from VSnpAllvars2 myVSnpAllvars2 where myVSnpAllvars2.refnuc like ?1"),
		@NamedQuery(name = "findVSnpAllvars2BySnpFeatureId", query = "select myVSnpAllvars2 from VSnpAllvars2 myVSnpAllvars2 where myVSnpAllvars2.snpFeatureId = ?1"),
		@NamedQuery(name = "findVSnpAllvars2ByVarnuc", query = "select myVSnpAllvars2 from VSnpAllvars2 myVSnpAllvars2 where myVSnpAllvars2.varnuc = ?1"),
		@NamedQuery(name = "findVSnpAllvars2ByVarnucContaining", query = "select myVSnpAllvars2 from VSnpAllvars2 myVSnpAllvars2 where myVSnpAllvars2.varnuc like ?1") })
@Table(schema = "IRIC", name = "V_SNP_ALLVARS2")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VSnpAllvars2")
public class VSnpAllvars2 implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "SNP_FEATURE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal snpFeatureId;
	/**
	 */

	@Column(name = "IRIC_STOCK_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal iricStockId;
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
	public void setIricStockId(BigDecimal iricStockId) {
		this.iricStockId = iricStockId;
	}

	/**
	 */
	public BigDecimal getIricStockId() {
		return this.iricStockId;
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
	public VSnpAllvars2() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VSnpAllvars2 that) {
		setSnpFeatureId(that.getSnpFeatureId());
		setIricStockId(that.getIricStockId());
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

		buffer.append("snpFeatureId=[").append(snpFeatureId).append("] ");
		buffer.append("iricStockId=[").append(iricStockId).append("] ");
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
		result = (int) (prime * result + ((snpFeatureId == null) ? 0 : snpFeatureId.hashCode()));
		result = (int) (prime * result + ((iricStockId == null) ? 0 : iricStockId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VSnpAllvars2))
			return false;
		VSnpAllvars2 equalCheck = (VSnpAllvars2) obj;
		if ((snpFeatureId == null && equalCheck.snpFeatureId != null) || (snpFeatureId != null && equalCheck.snpFeatureId == null))
			return false;
		if (snpFeatureId != null && !snpFeatureId.equals(equalCheck.snpFeatureId))
			return false;
		if ((iricStockId == null && equalCheck.iricStockId != null) || (iricStockId != null && equalCheck.iricStockId == null))
			return false;
		if (iricStockId != null && !iricStockId.equals(equalCheck.iricStockId))
			return false;
		return true;
	}
}
