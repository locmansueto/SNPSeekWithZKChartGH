package org.irri.iric.portal.chado.postgres.domain;

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
		@NamedQuery(name = "findAllVSnpRefposindexs", query = "select myVSnpRefposindex from VSnpRefposindex myVSnpRefposindex"),
		@NamedQuery(name = "findVSnpRefposindexByAlleleIndex", query = "select myVSnpRefposindex from VSnpRefposindex myVSnpRefposindex where myVSnpRefposindex.alleleIndex = ?1"),
		@NamedQuery(name = "findVSnpRefposindexByChromosome", query = "select myVSnpRefposindex from VSnpRefposindex myVSnpRefposindex where myVSnpRefposindex.chromosome = ?1"),
		@NamedQuery(name = "findVSnpRefposindexByPosition", query = "select myVSnpRefposindex from VSnpRefposindex myVSnpRefposindex where myVSnpRefposindex.position = ?1"),
		
		@NamedQuery(name = "findVSnpRefposindexByChrPosBetween", query = "select mySnpcoreRefposindex from VSnpRefposindex mySnpcoreRefposindex where mySnpcoreRefposindex.chromosome = ?1 and mySnpcoreRefposindex.position between ?2 and ?3 and  mySnpcoreRefposindex.typeId=?4 order by mySnpcoreRefposindex.position" ),
		@NamedQuery(name = "findVSnpRefposindexByChrPosIn", query = "select mySnpcoreRefposindex from VSnpRefposindex mySnpcoreRefposindex where mySnpcoreRefposindex.chromosome = ?1 and mySnpcoreRefposindex.position in(?2) and mySnpcoreRefposindex.typeId=?3  order by mySnpcoreRefposindex.position" ),
		
		
		@NamedQuery(name = "findVSnpRefposindexByPrimaryKey", query = "select myVSnpRefposindex from VSnpRefposindex myVSnpRefposindex where myVSnpRefposindex.snpFeatureId = ?1"),
		@NamedQuery(name = "findVSnpRefposindexByRefcall", query = "select myVSnpRefposindex from VSnpRefposindex myVSnpRefposindex where myVSnpRefposindex.refcall = ?1"),
		@NamedQuery(name = "findVSnpRefposindexByRefcallContaining", query = "select myVSnpRefposindex from VSnpRefposindex myVSnpRefposindex where myVSnpRefposindex.refcall like ?1"),
		@NamedQuery(name = "findVSnpRefposindexBySnpFeatureId", query = "select myVSnpRefposindex from VSnpRefposindex myVSnpRefposindex where myVSnpRefposindex.snpFeatureId = ?1"),
		@NamedQuery(name = "findVSnpRefposindexByTypeId", query = "select myVSnpRefposindex from VSnpRefposindex myVSnpRefposindex where myVSnpRefposindex.typeId = ?1") })
@Table(schema = "public", name = "v_snp_refposindex")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/postgres/domain", name = "VSnpRefposindex")
public class VSnpRefposindex implements Serializable , SnpsAllvarsPos, Comparable {
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

	@Column(name = "chromosome")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer chromosome;
	/**
	 */

	@Column(name = "position")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer position;
	/**
	 */

	@Column(name = "refcall", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String refcall;
	/**
	 */

	@Column(name = "allele_index")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer alleleIndex;
	/**
	 */

	@Column(name = "type_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer typeId;

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
	public void setChromosome(Integer chromosome) {
		this.chromosome = chromosome;
	}

	/**
	 */
	public Integer getChromosome() {
		return this.chromosome;
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
	public void setAlleleIndex(Integer alleleIndex) {
		this.alleleIndex = alleleIndex;
	}

	/**
	 */
	public BigDecimal getAlleleIndex() {
		return BigDecimal.valueOf(alleleIndex);
	}

	/**
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	/**
	 */
	public Integer getTypeId() {
		return this.typeId;
	}

	/**
	 */
	public VSnpRefposindex() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VSnpRefposindex that) {
		setSnpFeatureId(that.getSnpFeatureId().longValue());
		setChromosome(that.getChromosome());
		setPosition(that.getPosition());
		setRefcall(that.getRefcall());
		setAlleleIndex(that.getAlleleIndex().intValue());
		setTypeId(that.getTypeId());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("snpFeatureId=[").append(snpFeatureId).append("] ");
		buffer.append("chromosome=[").append(chromosome).append("] ");
		buffer.append("position=[").append(position).append("] ");
		buffer.append("refcall=[").append(refcall).append("] ");
		buffer.append("alleleIndex=[").append(alleleIndex).append("] ");
		buffer.append("typeId=[").append(typeId).append("] ");

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
		if (!(obj instanceof VSnpRefposindex))
			return false;
		VSnpRefposindex equalCheck = (VSnpRefposindex) obj;
		if ((snpFeatureId == null && equalCheck.snpFeatureId != null) || (snpFeatureId != null && equalCheck.snpFeatureId == null))
			return false;
		if (snpFeatureId != null && !snpFeatureId.equals(equalCheck.snpFeatureId))
			return false;
		return true;
	}
	*/


	@Override
	public BigDecimal getPos() {
		// TODO Auto-generated method stub
		return BigDecimal.valueOf(getPosition());
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
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		VSnpRefposindex pos=(VSnpRefposindex)o;
		int ret=this.getChromosome().compareTo(pos.getChromosome());
		if(ret==0)
			ret=this.getPosition().compareTo(pos.getPosition());
		return ret;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return compareTo(obj)==0;
	}

	@Override
	public String getContig() {
		// TODO Auto-generated method stub
		if(getChromosome().intValue()>9)
			return "chr" + getChromosome();
		else
			return "chr0" + getChromosome();
	}
	
}
