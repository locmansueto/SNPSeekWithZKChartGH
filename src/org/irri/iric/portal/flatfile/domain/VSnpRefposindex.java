package org.irri.iric.portal.flatfile.domain;

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
@IdClass(org.irri.iric.portal.flatfile.domain.VSnpRefposindexPK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVSnpRefposindexs", query = "select myVSnpRefposindex from VSnpRefposindex myVSnpRefposindex"),
		@NamedQuery(name = "findVSnpRefposindexByAlleleIndex", query = "select myVSnpRefposindex from VSnpRefposindex myVSnpRefposindex where myVSnpRefposindex.alleleIndex = ?1"),
		@NamedQuery(name = "findVSnpRefposindexByChromosome", query = "select myVSnpRefposindex from VSnpRefposindex myVSnpRefposindex where myVSnpRefposindex.chromosome = ?1"),
		@NamedQuery(name = "findVSnpRefposindexByPosition", query = "select myVSnpRefposindex from VSnpRefposindex myVSnpRefposindex where myVSnpRefposindex.position = ?1"),
		
		
		@NamedQuery(name = "findVSnpRefposindexByChrPosBetween", query = "select mySnpcoreRefposindex from VSnpRefposindex mySnpcoreRefposindex where mySnpcoreRefposindex.chromosome = ?1 and mySnpcoreRefposindex.position between ?2 and ?3 and  mySnpcoreRefposindex.typeId=?4 order by mySnpcoreRefposindex.position" ),
		@NamedQuery(name = "findVSnpRefposindexByChrPosIn", query = "select mySnpcoreRefposindex from VSnpRefposindex mySnpcoreRefposindex where mySnpcoreRefposindex.chromosome = ?1 and mySnpcoreRefposindex.position in(?2) and mySnpcoreRefposindex.typeId=?3  order by mySnpcoreRefposindex.position" ),
		
		
		@NamedQuery(name = "findVSnpRefposindexByPrimaryKey", query = "select myVSnpRefposindex from VSnpRefposindex myVSnpRefposindex where myVSnpRefposindex.snpFeatureId = ?1 and myVSnpRefposindex.typeId = ?2"),
		@NamedQuery(name = "findVSnpRefposindexByRefcall", query = "select myVSnpRefposindex from VSnpRefposindex myVSnpRefposindex where myVSnpRefposindex.refcall = ?1"),
		@NamedQuery(name = "findVSnpRefposindexByRefcallContaining", query = "select myVSnpRefposindex from VSnpRefposindex myVSnpRefposindex where myVSnpRefposindex.refcall like ?1"),
		@NamedQuery(name = "findVSnpRefposindexBySnpFeatureId", query = "select myVSnpRefposindex from VSnpRefposindex myVSnpRefposindex where myVSnpRefposindex.snpFeatureId = ?1"),
		@NamedQuery(name = "findVSnpRefposindexByTypeId", query = "select myVSnpRefposindex from VSnpRefposindex myVSnpRefposindex where myVSnpRefposindex.typeId = ?1") })
@Table(schema = "IRIC", name = "V_SNP_REFPOSINDEX")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/flatfile/domain", name = "VSnpRefposindex")
public class VSnpRefposindex implements Serializable , SnpsAllvarsPos, Comparable {
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

	@Column(name = "TYPE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal typeId;
	/**
	 */

	@Column(name = "CHROMOSOME", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal chromosome;
	/**
	 */

	@Column(name = "POSITION", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal position;
	/**
	 */

	@Column(name = "REFCALL", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String refcall;
	/**
	 */

	@Column(name = "ALLELE_INDEX", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal alleleIndex;

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
	public void setTypeId(BigDecimal typeId) {
		this.typeId = typeId;
	}

	/**
	 */
	public BigDecimal getTypeId() {
		return this.typeId;
	}

	/**
	 */
	public void setChromosome(BigDecimal chromosome) {
		this.chromosome = chromosome;
	}

	/**
	 */
	public BigDecimal getChromosome() {
		return this.chromosome;
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
	public void setAlleleIndex(BigDecimal alleleIndex) {
		this.alleleIndex = alleleIndex;
	}

	/**
	 */
	public BigDecimal getAlleleIndex() {
		return this.alleleIndex;
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
		setSnpFeatureId(that.getSnpFeatureId());
		setTypeId(that.getTypeId());
		setChromosome(that.getChromosome());
		setPosition(that.getPosition());
		setRefcall(that.getRefcall());
		setAlleleIndex(that.getAlleleIndex());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("snpFeatureId=[").append(snpFeatureId).append("] ");
		buffer.append("typeId=[").append(typeId).append("] ");
		buffer.append("chromosome=[").append(chromosome).append("] ");
		buffer.append("position=[").append(position).append("] ");
		buffer.append("refcall=[").append(refcall).append("] ");
		buffer.append("alleleIndex=[").append(alleleIndex).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((snpFeatureId == null) ? 0 : snpFeatureId.hashCode()));
		result = (int) (prime * result + ((typeId == null) ? 0 : typeId.hashCode()));
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
		if ((typeId == null && equalCheck.typeId != null) || (typeId != null && equalCheck.typeId == null))
			return false;
		if (typeId != null && !typeId.equals(equalCheck.typeId))
			return false;
		return true;
	}
	*/
	
	
	

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
