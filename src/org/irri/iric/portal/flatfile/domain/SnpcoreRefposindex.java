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

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllSnpcoreRefposindexs", query = "select mySnpcoreRefposindex from SnpcoreRefposindex mySnpcoreRefposindex"),
		@NamedQuery(name = "findSnpcoreRefposindexByAllelesIndex", query = "select mySnpcoreRefposindex from SnpcoreRefposindex mySnpcoreRefposindex where mySnpcoreRefposindex.allelesIndex = ?1"),
		@NamedQuery(name = "findSnpcoreRefposindexByChromosome", query = "select mySnpcoreRefposindex from SnpcoreRefposindex mySnpcoreRefposindex where mySnpcoreRefposindex.chromosome = ?1"),
		@NamedQuery(name = "findSnpcoreRefposindexByPosition", query = "select mySnpcoreRefposindex from SnpcoreRefposindex mySnpcoreRefposindex where mySnpcoreRefposindex.position = ?1"),

		@NamedQuery(name = "findSnpcoreRefposindexByChrPosBetween", query = "select mySnpcoreRefposindex from SnpcoreRefposindex mySnpcoreRefposindex where mySnpcoreRefposindex.chromosome = ?1 and mySnpcoreRefposindex.position between ?2 and ?3 and  mySnpcoreRefposindex.typeId=?4 order by mySnpcoreRefposindex.position" ),
		
		@NamedQuery(name = "findSnpcoreRefposindexByChrPosIn", query = "select mySnpcoreRefposindex from SnpcoreRefposindex mySnpcoreRefposindex where mySnpcoreRefposindex.chromosome = ?1 and mySnpcoreRefposindex.position in(?2) and mySnpcoreRefposindex.typeId=?3  order by mySnpcoreRefposindex.position" ),
		
		
		@NamedQuery(name = "findSnpcoreRefposindexByPrimaryKey", query = "select mySnpcoreRefposindex from SnpcoreRefposindex mySnpcoreRefposindex where mySnpcoreRefposindex.snpFeatureId = ?1"),
		@NamedQuery(name = "findSnpcoreRefposindexByRefcall", query = "select mySnpcoreRefposindex from SnpcoreRefposindex mySnpcoreRefposindex where mySnpcoreRefposindex.refcall = ?1"),
		@NamedQuery(name = "findSnpcoreRefposindexByRefcallContaining", query = "select mySnpcoreRefposindex from SnpcoreRefposindex mySnpcoreRefposindex where mySnpcoreRefposindex.refcall like ?1"),
		@NamedQuery(name = "findSnpcoreRefposindexBySnpFeatureId", query = "select mySnpcoreRefposindex from SnpcoreRefposindex mySnpcoreRefposindex where mySnpcoreRefposindex.snpFeatureId = ?1") })
//@Table(schema = "LMANSUETO", name = "SNPCORE_REFPOSINDEX")
//@Table(schema = "LMANSUETO", name = "SNP_REFPOSINDEX")
@Table(schema = "IRIC", name = "V_SNP_REFPOSINDEX")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "SnpcoreRefposindex")
public class SnpcoreRefposindex implements Serializable,  SnpsAllvarsPos {
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

	//@Column(name = "ALLELES_INDEX", precision = 10)
	@Column(name = "ALLELE_INDEX", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal allelesIndex;

	
	@Column(name = "TYPE_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal typeId;


	
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
	public void setAllelesIndex(BigDecimal allelesIndex) {
		this.allelesIndex = allelesIndex;
	}

	/**
	 */
	public BigDecimal getAllelesIndex() {
		return this.allelesIndex;
	}
	
	
	

	public BigDecimal getTypeId() {
		return typeId;
	}

	public void setTypeID(BigDecimal typeId) {
		this.typeId = typeId;
	}

	/**
	 */
	public SnpcoreRefposindex() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(SnpcoreRefposindex that) {
		setSnpFeatureId(that.getSnpFeatureId());
		setChromosome(that.getChromosome());
		setPosition(that.getPosition());
		setRefcall(that.getRefcall());
		setAllelesIndex(that.getAllelesIndex());
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
		buffer.append("allelesIndex=[").append(allelesIndex).append("] ");

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
		if (!(obj instanceof SnpcoreRefposindex))
			return false;
		SnpcoreRefposindex equalCheck = (SnpcoreRefposindex) obj;
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
		if(getChromosome().intValue()>9)
		 return "chr" + getChromosome();
		else 
		 return "chr0" + getChromosome();
	}
	
	
	
	
}
