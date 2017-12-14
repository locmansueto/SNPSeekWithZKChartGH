package org.irri.iric.portal.chado.postgres.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.SnpsSpliceAcceptor;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVSnpSpliceacceptors", query = "select myVSnpSpliceacceptor from VSnpSpliceacceptor myVSnpSpliceacceptor"),
		@NamedQuery(name = "findVSnpSpliceacceptorByChr", query = "select myVSnpSpliceacceptor from VSnpSpliceacceptor myVSnpSpliceacceptor where myVSnpSpliceacceptor.chr = ?1"),
		@NamedQuery(name = "findVSnpSpliceacceptorByOrganismId", query = "select myVSnpSpliceacceptor from VSnpSpliceacceptor myVSnpSpliceacceptor where myVSnpSpliceacceptor.organismId = ?1"),
		@NamedQuery(name = "findVSnpSpliceacceptorByPosition", query = "select myVSnpSpliceacceptor from VSnpSpliceacceptor myVSnpSpliceacceptor where myVSnpSpliceacceptor.position = ?1"),
		
		@NamedQuery(name = "findVSnpSpliceacceptorByChrPositionBetween", query = "select myVSnpSpliceacceptor from VSnpSpliceacceptor myVSnpSpliceacceptor where myVSnpSpliceacceptor.chr=?1 and myVSnpSpliceacceptor.position between ?2 and ?3"),
		@NamedQuery(name = "findVSnpSpliceacceptorByChrPositionIn", query = "select myVSnpSpliceacceptor from VSnpSpliceacceptor myVSnpSpliceacceptor where myVSnpSpliceacceptor.chr=?1 and myVSnpSpliceacceptor.position in (?2)"),
		
		
		@NamedQuery(name = "findVSnpSpliceacceptorByPrimaryKey", query = "select myVSnpSpliceacceptor from VSnpSpliceacceptor myVSnpSpliceacceptor where myVSnpSpliceacceptor.snpFeatureId = ?1"),
		@NamedQuery(name = "findVSnpSpliceacceptorBySnpFeatureId", query = "select myVSnpSpliceacceptor from VSnpSpliceacceptor myVSnpSpliceacceptor where myVSnpSpliceacceptor.snpFeatureId = ?1"),
		@NamedQuery(name = "findVSnpSpliceacceptorBySrcfeatureId", query = "select myVSnpSpliceacceptor from VSnpSpliceacceptor myVSnpSpliceacceptor where myVSnpSpliceacceptor.srcfeatureId = ?1") })
@Table(schema = "public", name = "v_snp_spliceacceptor")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/postgres/domain", name = "VSnpSpliceacceptor")
public class VSnpSpliceacceptor implements Serializable, SnpsSpliceAcceptor, Comparable {
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

	@Column(name = "srcfeature_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer srcfeatureId;
	/**
	 */

	@Column(name = "position")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer position;
	/**
	 */

	@Column(name = "chr")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer chr;
	/**
	 */

	@Column(name = "organism_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer organismId;

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
	public void setSrcfeatureId(Integer srcfeatureId) {
		this.srcfeatureId = srcfeatureId;
	}

	/**
	 */
	public Integer getSrcfeatureId() {
		return this.srcfeatureId;
	}

	/**
	 */
	public void setPosition(Integer position) {
		this.position = position;
	}

	/**
	 */
	public Integer getPosition() {
		return this.position;
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
	public void setOrganismId(Integer organismId) {
		this.organismId = organismId;
	}

	/**
	 */
	public Integer getOrganismId() {
		return this.organismId;
	}

	/**
	 */
	public VSnpSpliceacceptor() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VSnpSpliceacceptor that) {
		setSnpFeatureId(that.getSnpFeatureId().longValue());
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
		if (!(obj instanceof VSnpSpliceacceptor))
			return false;
		VSnpSpliceacceptor equalCheck = (VSnpSpliceacceptor) obj;
		if ((snpFeatureId == null && equalCheck.snpFeatureId != null) || (snpFeatureId != null && equalCheck.snpFeatureId == null))
			return false;
		if (snpFeatureId != null && !snpFeatureId.equals(equalCheck.snpFeatureId))
			return false;
		return true;
	}
	

	@Override
	public BigDecimal getSnp() {
		// TODO Auto-generated method stub
		return BigDecimal.valueOf(snpFeatureId);
	}

	@Override
	public BigDecimal getPos() {
		// TODO Auto-generated method stub
		return BigDecimal.valueOf(position);
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		VSnpSpliceacceptor acc=(VSnpSpliceacceptor)o;
		int ret = this.getChr().compareTo( acc.getChr() );
		if(ret==0)
			ret = this.getPosition().compareTo( acc.getPosition() );
		return ret;
	}
	
	
}
