package org.irri.iric.portal.chado.domain;

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

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVSnpAllvarsPoss", query = "select myVSnpAllvarsPos from VSnpAllvarsPos myVSnpAllvarsPos"),
		@NamedQuery(name = "findVSnpAllvarsPosByChr", query = "select myVSnpAllvarsPos from VSnpAllvarsPos myVSnpAllvarsPos where myVSnpAllvarsPos.chr = ?1+2"),
		@NamedQuery(name = "findVSnpAllvarsPosByPos", query = "select myVSnpAllvarsPos from VSnpAllvarsPos myVSnpAllvarsPos where myVSnpAllvarsPos.pos = ?1"),
		
		@NamedQuery(name = "findVSnpAllvarsPosByChrPosBetween", query = "select myVSnpAllvarsPos from VSnpAllvarsPos myVSnpAllvarsPos where  myVSnpAllvarsPos.chr = ?1+2 " +
						" and myVSnpAllvarsPos.pos between ?2 and ?3 order by myVSnpAllvarsPos.pos"),
		
		
		@NamedQuery(name = "findVSnpAllvarsPosByPrimaryKey", query = "select myVSnpAllvarsPos from VSnpAllvarsPos myVSnpAllvarsPos where myVSnpAllvarsPos.snpFeatureId = ?1"),
		@NamedQuery(name = "findVSnpAllvarsPosByRefnuc", query = "select myVSnpAllvarsPos from VSnpAllvarsPos myVSnpAllvarsPos where myVSnpAllvarsPos.refnuc = ?1"),
		@NamedQuery(name = "findVSnpAllvarsPosByRefnucContaining", query = "select myVSnpAllvarsPos from VSnpAllvarsPos myVSnpAllvarsPos where myVSnpAllvarsPos.refnuc like ?1"),
		@NamedQuery(name = "findVSnpAllvarsPosBySnpFeatureId", query = "select myVSnpAllvarsPos from VSnpAllvarsPos myVSnpAllvarsPos where myVSnpAllvarsPos.snpFeatureId = ?1") })
@Table(schema = "IRIC", name = "V_SNP_ALLVARS_POS")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VSnpAllvarsPos")
public class VSnpAllvarsPos implements Serializable, SnpsAllvarsPos {
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

	@Column(name = "CHR")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String chr;
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
		this.chr = "Chr" + chr;
	}

	/**
	 */
	public Integer getChr() {
		return Integer.valueOf( chr.replace("Chr","") );
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
	public VSnpAllvarsPos() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VSnpAllvarsPos that) {
		setSnpFeatureId(that.getSnpFeatureId());
		setChr(that.getChr());
		setPos(that.getPos());
		setRefnuc(that.getRefnuc());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("snpFeatureId=[").append(snpFeatureId).append("] ");
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
		result = (int) (prime * result + ((snpFeatureId == null) ? 0 : snpFeatureId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VSnpAllvarsPos))
			return false;
		VSnpAllvarsPos equalCheck = (VSnpAllvarsPos) obj;
		if ((snpFeatureId == null && equalCheck.snpFeatureId != null) || (snpFeatureId != null && equalCheck.snpFeatureId == null))
			return false;
		if (snpFeatureId != null && !snpFeatureId.equals(equalCheck.snpFeatureId))
			return false;
		return true;
	}

	@Override
	public String getContig() {
		// TODO Auto-generated method stub
		return this.chr;
	}
	
	
	
}
