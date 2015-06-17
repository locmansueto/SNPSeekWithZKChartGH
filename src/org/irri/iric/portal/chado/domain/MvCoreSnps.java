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
		@NamedQuery(name = "findAllMvCoreSnpss", query = "select myMvCoreSnps from MvCoreSnps myMvCoreSnps"),
		@NamedQuery(name = "findMvCoreSnpsByName", query = "select myMvCoreSnps from MvCoreSnps myMvCoreSnps where myMvCoreSnps.name = ?1"),
		@NamedQuery(name = "findMvCoreSnpsByNameContaining", query = "select myMvCoreSnps from MvCoreSnps myMvCoreSnps where myMvCoreSnps.name like ?1"),
		@NamedQuery(name = "findMvCoreSnpsByPosition", query = "select myMvCoreSnps from MvCoreSnps myMvCoreSnps where myMvCoreSnps.position = ?1"),
		
		
		@NamedQuery(name = "findVSnpAllvarsPosBySrcfeatureidPosBetween", query = "select myMvCoreSnps from MvCoreSnps myMvCoreSnps where myMvCoreSnps.srcfeatureId = ?1 "
							+ " and myMvCoreSnps.position between ?2 and ?3 order by myMvCoreSnps.position"),

				
		@NamedQuery(name = "findMvCoreSnpsByPrimaryKey", query = "select myMvCoreSnps from MvCoreSnps myMvCoreSnps where myMvCoreSnps.snpFeatureId = ?1"),
		@NamedQuery(name = "findMvCoreSnpsByRefcall", query = "select myMvCoreSnps from MvCoreSnps myMvCoreSnps where myMvCoreSnps.refcall = ?1"),
		@NamedQuery(name = "findMvCoreSnpsByRefcallContaining", query = "select myMvCoreSnps from MvCoreSnps myMvCoreSnps where myMvCoreSnps.refcall like ?1"),
		@NamedQuery(name = "findMvCoreSnpsBySnpFeatureId", query = "select myMvCoreSnps from MvCoreSnps myMvCoreSnps where myMvCoreSnps.snpFeatureId = ?1"),
		@NamedQuery(name = "findMvCoreSnpsBySrcfeatureId", query = "select myMvCoreSnps from MvCoreSnps myMvCoreSnps where myMvCoreSnps.srcfeatureId = ?1") })
//@Table(schema = "IRIC", name = "MV_CORE_SNPS")
@Table(schema = "IRIC", name = "CORE_SNP")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "MvCoreSnps")
public class MvCoreSnps implements Serializable, SnpsAllvarsPos {
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

	@Column(name = "NAME")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 */

	@Column(name = "REFCALL", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String refcall;
	/**
	 */

	@Column(name = "POSITION", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal position;
	/**
	 */

	@Column(name = "SRCFEATURE_ID")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer srcfeatureId;

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
	public void setName(String name) {
		this.name = name;
	}

	/**
	 */
	public String getName() {
		return this.name;
	}

	/**
	 */
	public void setRefcall(String refcall) {
		this.refcall = refcall;
	}

	/**
	 */
	public String getRefcall() {
		return this.refcall;
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
	public MvCoreSnps() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(MvCoreSnps that) {
		setSnpFeatureId(that.getSnpFeatureId());
		setName(that.getName());
		setRefcall(that.getRefcall());
		setPosition(that.getPosition());
		setSrcfeatureId(that.getSrcfeatureId());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("snpFeatureId=[").append(snpFeatureId).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("refcall=[").append(refcall).append("] ");
		buffer.append("position=[").append(position).append("] ");
		buffer.append("srcfeatureId=[").append(srcfeatureId).append("] ");

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
		if (!(obj instanceof MvCoreSnps))
			return false;
		MvCoreSnps equalCheck = (MvCoreSnps) obj;
		if ((snpFeatureId == null && equalCheck.snpFeatureId != null) || (snpFeatureId != null && equalCheck.snpFeatureId == null))
			return false;
		if (snpFeatureId != null && !snpFeatureId.equals(equalCheck.snpFeatureId))
			return false;
		return true;
	}

	@Override
	public BigDecimal getPos() {
		// TODO Auto-generated method stub
		return getPosition();
	}

	@Override
	public String getRefnuc() {
		// TODO Auto-generated method stub
		return getRefcall();
	}

	@Override
	public void setRefnuc(String refnuc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getContig() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
