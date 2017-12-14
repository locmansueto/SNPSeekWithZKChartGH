package org.irri.iric.portal.chado.postgres.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;


import org.irri.iric.portal.dao.SnpsInExonDAO;
import org.irri.iric.portal.domain.Snp;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVSnpInExons", query = "select myVSnpInExon from VSnpInExon myVSnpInExon"),
		@NamedQuery(name = "findVSnpInExonByPrimaryKey", query = "select myVSnpInExon from VSnpInExon myVSnpInExon where myVSnpInExon.snpFeatureId = ?1"),
		
		@NamedQuery(name = "findVSnpInExonBySnpFeatureIdIn", query = "select myVSnpInExon from VSnpInExon myVSnpInExon where myVSnpInExon.snpFeatureId in (?1)"),
		@NamedQuery(name = "findVSnpInExonBySnpFeatureIdBetween", query = "select myVSnpInExon from VSnpInExon myVSnpInExon where myVSnpInExon.snpFeatureId between ?1 and ?2"),
		
		
		@NamedQuery(name = "findVSnpInExonBySnpFeatureId", query = "select myVSnpInExon from VSnpInExon myVSnpInExon where myVSnpInExon.snpFeatureId = ?1") })
@Table(schema = "public", name = "v_snp_in_exon")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/postgres/domain", name = "VSnpInExon")
public class VSnpInExon implements Serializable,  Snp, Comparable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "snp_feature_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Long snpFeatureId;

	/**
	 */
	public void setSnpFeatureId(Long snpFeatureId) {
		this.snpFeatureId = snpFeatureId;
	}

	/**
	 */
	public BigDecimal getSnpFeatureId() {
		return BigDecimal.valueOf(snpFeatureId);
	}

	/**
	 */
	public VSnpInExon() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VSnpInExon that) {
		setSnpFeatureId(that.getSnpFeatureId().longValue());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("snpFeatureId=[").append(snpFeatureId).append("] ");

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
	/*
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VSnpInExon))
			return false;
		VSnpInExon equalCheck = (VSnpInExon) obj;
		if ((snpFeatureId == null && equalCheck.snpFeatureId != null) || (snpFeatureId != null && equalCheck.snpFeatureId == null))
			return false;
		if (snpFeatureId != null && !snpFeatureId.equals(equalCheck.snpFeatureId))
			return false;
		return true;
	}
	*/
	


	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return this.getSnpFeatureId().compareTo( ((VSnpInExon)o).getSnpFeatureId());
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return compareTo(obj)==0;
	}
	
	
	
	
}
