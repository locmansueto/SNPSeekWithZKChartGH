package org.irri.iric.portal.chado.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.SnpsSpliceDonor;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVSnpSplicedonors", query = "select myVSnpSplicedonor from VSnpSplicedonor myVSnpSplicedonor"),
		@NamedQuery(name = "findVSnpSplicedonorByChr", query = "select myVSnpSplicedonor from VSnpSplicedonor myVSnpSplicedonor where myVSnpSplicedonor.chr = ?1"),
		@NamedQuery(name = "findVSnpSplicedonorByChrContaining", query = "select myVSnpSplicedonor from VSnpSplicedonor myVSnpSplicedonor where myVSnpSplicedonor.chr like ?1"),
		@NamedQuery(name = "findVSnpSplicedonorByOrganismId", query = "select myVSnpSplicedonor from VSnpSplicedonor myVSnpSplicedonor where myVSnpSplicedonor.organismId = ?1"),
		@NamedQuery(name = "findVSnpSplicedonorByPosition", query = "select myVSnpSplicedonor from VSnpSplicedonor myVSnpSplicedonor where myVSnpSplicedonor.position = ?1"),

		
		@NamedQuery(name = "findVSnpSplicedonorByChrPositionBetween", query = "select myVSnpSplicedonor from VSnpSplicedonor myVSnpSplicedonor where myVSnpSplicedonor.chr=?1 and myVSnpSplicedonor.position between ?2 and  ?3"),
		@NamedQuery(name = "findVSnpSplicedonorByChrPositionIn", query = "select myVSnpSplicedonor from VSnpSplicedonor myVSnpSplicedonor where myVSnpSplicedonor.chr=?1 and myVSnpSplicedonor.position in (?2)"),

		
		@NamedQuery(name = "findVSnpSplicedonorByPrimaryKey", query = "select myVSnpSplicedonor from VSnpSplicedonor myVSnpSplicedonor where myVSnpSplicedonor.snpFeatureId = ?1"),
		@NamedQuery(name = "findVSnpSplicedonorBySnpFeatureId", query = "select myVSnpSplicedonor from VSnpSplicedonor myVSnpSplicedonor where myVSnpSplicedonor.snpFeatureId = ?1"),
		@NamedQuery(name = "findVSnpSplicedonorBySrcfeatureId", query = "select myVSnpSplicedonor from VSnpSplicedonor myVSnpSplicedonor where myVSnpSplicedonor.srcfeatureId = ?1") })



@Table(schema = "IRIC", name = "V_SNP_SPLICEDONOR")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VSnpSplicedonor")
public class VSnpSplicedonor implements Serializable, SnpsSpliceDonor {
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

	@Column(name = "SRCFEATURE_ID")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal srcfeatureId;
	/**
	 */

	@Column(name = "POSITION")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal position;
	/**
	 */

	@Column(name = "CHR", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String chr;
	/**
	 */

	@Column(name = "ORGANISM_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal organismId;

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
	public void setSrcfeatureId(BigDecimal srcfeatureId) {
		this.srcfeatureId = srcfeatureId;
	}

	/**
	 */
	public BigDecimal getSrcfeatureId() {
		return this.srcfeatureId;
	}

	/**
	 */
	public void setPosition(BigDecimal position) {
		this.position = position;
	}

	/**
	 */
	public BigDecimal getPosition() {
		return this.position;
	}

	/**
	 */
	public void setChr(String chr) {
		this.chr = chr;
	}

	/**
	 */
	public String getChr() {
		return this.chr;
	}

	/**
	 */
	public void setOrganismId(BigDecimal organismId) {
		this.organismId = organismId;
	}

	/**
	 */
	public BigDecimal getOrganismId() {
		return this.organismId;
	}

	/**
	 */
	public VSnpSplicedonor() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VSnpSplicedonor that) {
		setSnpFeatureId(that.getSnpFeatureId());
		setSrcfeatureId(that.getSrcfeatureId());
		setPosition(that.getPosition());
		setChr(that.getChr());
		setOrganismId(that.getOrganismId());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("snpFeatureId=[").append(snpFeatureId).append("] ");
		buffer.append("srcfeatureId=[").append(srcfeatureId).append("] ");
		buffer.append("position=[").append(position).append("] ");
		buffer.append("chr=[").append(chr).append("] ");
		buffer.append("organismId=[").append(organismId).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((snpFeatureId == null) ? 0 : snpFeatureId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VSnpSplicedonor))
			return false;
		VSnpSplicedonor equalCheck = (VSnpSplicedonor) obj;
		if ((snpFeatureId == null && equalCheck.snpFeatureId != null) || (snpFeatureId != null && equalCheck.snpFeatureId == null))
			return false;
		if (snpFeatureId != null && !snpFeatureId.equals(equalCheck.snpFeatureId))
			return false;
		return true;
	}

	@Override
	public BigDecimal getSnp() {
		// TODO Auto-generated method stub
		return snpFeatureId;
	}

	@Override
	public BigDecimal getPos() {
		// TODO Auto-generated method stub
		return this.position;
	}
	
	
	
	
}
