package org.irri.iric.portal.chado.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.SnpsAllvars;
import org.irri.iric.portal.domain.SnpsHeteroAllele2;

/**
 */
@IdClass(org.irri.iric.portal.chado.domain.VSnpgenotypeAllele2PK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVSnpgenotypeAllele2s", query = "select myVSnpgenotypeAllele2 from VSnpgenotypeAllele2 myVSnpgenotypeAllele2"),
		@NamedQuery(name = "findVSnpgenotypeAllele2ByAllele2", query = "select myVSnpgenotypeAllele2 from VSnpgenotypeAllele2 myVSnpgenotypeAllele2 where myVSnpgenotypeAllele2.allele2 = ?1"),
		@NamedQuery(name = "findVSnpgenotypeAllele2ByAllele2Containing", query = "select myVSnpgenotypeAllele2 from VSnpgenotypeAllele2 myVSnpgenotypeAllele2 where myVSnpgenotypeAllele2.allele2 like ?1"),
		@NamedQuery(name = "findVSnpgenotypeAllele2ByIricStockId", query = "select myVSnpgenotypeAllele2 from VSnpgenotypeAllele2 myVSnpgenotypeAllele2 where myVSnpgenotypeAllele2.iricStockId = ?1"),
		@NamedQuery(name = "findVSnpgenotypeAllele2ByPrimaryKey", query = "select myVSnpgenotypeAllele2 from VSnpgenotypeAllele2 myVSnpgenotypeAllele2 where myVSnpgenotypeAllele2.snpFeatureId = ?1 and myVSnpgenotypeAllele2.iricStockId = ?2"),
		@NamedQuery(name = "findVSnpgenotypeAllele2BySnpFeatureId", query = "select myVSnpgenotypeAllele2 from VSnpgenotypeAllele2 myVSnpgenotypeAllele2 where myVSnpgenotypeAllele2.snpFeatureId = ?1") })
@Table(schema = "IRIC", name = "V_SNPGENOTYPE_ALLELE2")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VSnpgenotypeAllele2")
public class VSnpgenotypeAllele2 implements Serializable , SnpsHeteroAllele2 {
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

	@Column(name = "ALLELE2", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String allele2;

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
	public void setAllele2(String allele2) {
		this.allele2 = allele2;
	}

	/**
	 */
	public String getAllele2() {
		return this.allele2;
	}

	/**
	 */
	public VSnpgenotypeAllele2() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VSnpgenotypeAllele2 that) {
		setSnpFeatureId(that.getSnpFeatureId());
		setIricStockId(that.getIricStockId());
		setAllele2(that.getAllele2());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("snpFeatureId=[").append(snpFeatureId).append("] ");
		buffer.append("iricStockId=[").append(iricStockId).append("] ");
		buffer.append("allele2=[").append(allele2).append("] ");

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
		if (!(obj instanceof VSnpgenotypeAllele2))
			return false;
		VSnpgenotypeAllele2 equalCheck = (VSnpgenotypeAllele2) obj;
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

	@Override
	public BigDecimal getVar() {
		// TODO Auto-generated method stub
		return getIricStockId();
	}

	@Override
	public BigDecimal getSnp() {
		// TODO Auto-generated method stub
		return getSnpFeatureId();
	}

	@Override
	public char getNuc() {
		// TODO Auto-generated method stub
		return allele2.charAt(0);
	}

	
	
	
}
