package org.irri.iric.portal.chado.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.SnpsNonsynAllele;

/**
 */
@IdClass(org.irri.iric.portal.chado.domain.VSnpNonsynAllelePK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVSnpNonsynAlleles", query = "select myVSnpNonsynAllele from VSnpNonsynAllele myVSnpNonsynAllele"),
		@NamedQuery(name = "findVSnpNonsynAlleleByNonSynAllele", query = "select myVSnpNonsynAllele from VSnpNonsynAllele myVSnpNonsynAllele where myVSnpNonsynAllele.nonSynAllele = ?1"),
		@NamedQuery(name = "findVSnpNonsynAlleleByNonSynAlleleContaining", query = "select myVSnpNonsynAllele from VSnpNonsynAllele myVSnpNonsynAllele where myVSnpNonsynAllele.nonSynAllele like ?1"),
		@NamedQuery(name = "findVSnpNonsynAlleleByPrimaryKey", query = "select myVSnpNonsynAllele from VSnpNonsynAllele myVSnpNonsynAllele where myVSnpNonsynAllele.snpFeatureId = ?1 and myVSnpNonsynAllele.nonSynAllele = ?2"),

		@NamedQuery(name = "findVSnpNonsynAlleleBySnpFeatureIdBetween", query = "select myVSnpNonsynAllele from VSnpNonsynAllele myVSnpNonsynAllele where myVSnpNonsynAllele.snpFeatureId between ?1 and ?2"),
		@NamedQuery(name = "findVSnpNonsynAlleleBySnpFeatureIdIn", query = "select myVSnpNonsynAllele from VSnpNonsynAllele myVSnpNonsynAllele where myVSnpNonsynAllele.snpFeatureId in (?1) "),
		
		
		
		@NamedQuery(name = "findVSnpNonsynAlleleBySnpFeatureId", query = "select myVSnpNonsynAllele from VSnpNonsynAllele myVSnpNonsynAllele where myVSnpNonsynAllele.snpFeatureId = ?1") })
@Table(schema = "IRIC", name = "V_SNP_NONSYN_ALLELE")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VSnpNonsynAllele")
public class VSnpNonsynAllele implements Serializable, SnpsNonsynAllele {
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

	@Column(name = "NON_SYN_ALLELE", length = 1, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String nonSynAllele;

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
	public void setNonSynAllele(String nonSynAllele) {
		this.nonSynAllele = nonSynAllele;
	}

	/**
	 */
	public String getNonSynAllele() {
		return this.nonSynAllele;
	}

	/**
	 */
	public VSnpNonsynAllele() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VSnpNonsynAllele that) {
		setSnpFeatureId(that.getSnpFeatureId());
		setNonSynAllele(that.getNonSynAllele());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("snpFeatureId=[").append(snpFeatureId).append("] ");
		buffer.append("nonSynAllele=[").append(nonSynAllele).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((snpFeatureId == null) ? 0 : snpFeatureId.hashCode()));
		result = (int) (prime * result + ((nonSynAllele == null) ? 0 : nonSynAllele.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VSnpNonsynAllele))
			return false;
		VSnpNonsynAllele equalCheck = (VSnpNonsynAllele) obj;
		if ((snpFeatureId == null && equalCheck.snpFeatureId != null) || (snpFeatureId != null && equalCheck.snpFeatureId == null))
			return false;
		if (snpFeatureId != null && !snpFeatureId.equals(equalCheck.snpFeatureId))
			return false;
		if ((nonSynAllele == null && equalCheck.nonSynAllele != null) || (nonSynAllele != null && equalCheck.nonSynAllele == null))
			return false;
		if (nonSynAllele != null && !nonSynAllele.equals(equalCheck.nonSynAllele))
			return false;
		return true;
	}

	@Override
	public BigDecimal getSnp() {
		// TODO Auto-generated method stub
		return this.getSnpFeatureId();
	}

	@Override
	public char getAllele() {
		// TODO Auto-generated method stub
		return this.getNonSynAllele().charAt(0)  ;
	}
	
	
	
	
}
