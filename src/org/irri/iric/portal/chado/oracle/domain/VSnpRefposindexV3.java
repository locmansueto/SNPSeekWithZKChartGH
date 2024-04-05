package org.irri.iric.portal.chado.oracle.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.domain.Position;
import org.irri.iric.portal.domain.SnpsAllvarsPos;

/**
 */
@IdClass(org.irri.iric.portal.chado.oracle.domain.VSnpRefposindexV3ChrposPK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVSnpRefposindexV3s", query = "select myVSnpRefposindex from VSnpRefposindex myVSnpRefposindex  order by myVSnpRefposindex.chromosome, myVSnpRefposindex.position"),
		@NamedQuery(name = "findVSnpRefposindexV3ByAlleleIndex", query = "select myVSnpRefposindex from VSnpRefposindex myVSnpRefposindex where myVSnpRefposindex.alleleIndex = ?1  order by myVSnpRefposindex.chromosome, myVSnpRefposindex.position"),
		@NamedQuery(name = "findVSnpRefposindexV3ByChromosome", query = "select myVSnpRefposindex from VSnpRefposindex myVSnpRefposindex where myVSnpRefposindex.chromosome = ?1  order by myVSnpRefposindex.chromosome, myVSnpRefposindex.position"),
		@NamedQuery(name = "findVSnpRefposindexV3ByPosition", query = "select myVSnpRefposindex from VSnpRefposindex myVSnpRefposindex where myVSnpRefposindex.position = ?1  order by myVSnpRefposindex.chromosome, myVSnpRefposindex.position"),

		@NamedQuery(name = "findVSnpRefposindexV3ByChrPosBetween", query = "select mySnpcoreRefposindex from VSnpRefposindex mySnpcoreRefposindex where mySnpcoreRefposindex.chromosome = ?1 and mySnpcoreRefposindex.position between ?2 and ?3 and  mySnpcoreRefposindex.typeId=?4 order by mySnpcoreRefposindex.position"),
		@NamedQuery(name = "findVSnpRefposindexV3ByChr", query = "select mySnpcoreRefposindex from VSnpRefposindex mySnpcoreRefposindex where mySnpcoreRefposindex.chromosome = ?1 and  mySnpcoreRefposindex.typeId=?2 order by mySnpcoreRefposindex.position"),
		@NamedQuery(name = "findVSnpRefposindexV3ByChrPosIn", query = "select mySnpcoreRefposindex from VSnpRefposindex mySnpcoreRefposindex where mySnpcoreRefposindex.chromosome = ?1 and mySnpcoreRefposindex.position in(?2) and mySnpcoreRefposindex.typeId=?3  order by mySnpcoreRefposindex.position"),
		@NamedQuery(name = "countVSnpRefposindexV3ByChrPosBetween", query = "select count(mySnpcoreRefposindex.snpFeatureId) from VSnpRefposindex mySnpcoreRefposindex where mySnpcoreRefposindex.chromosome = ?1 and mySnpcoreRefposindex.position between ?2 and ?3 and  mySnpcoreRefposindex.typeId=?4"),
		@NamedQuery(name = "countVSnpRefposindexV3ByChrPosIn", query = "select count(mySnpcoreRefposindex.snpFeatureId) from VSnpRefposindex mySnpcoreRefposindex where mySnpcoreRefposindex.chromosome = ?1 and mySnpcoreRefposindex.position in(?2) and mySnpcoreRefposindex.typeId=?3"),
		@NamedQuery(name = "countVSnpRefposindexV3ByChr", query = "select count(mySnpcoreRefposindex.snpFeatureId) from VSnpRefposindex mySnpcoreRefposindex where mySnpcoreRefposindex.chromosome = ?1 and  mySnpcoreRefposindex.typeId=?2"),

		@NamedQuery(name = "findVSnpRefposindexV3BySrcfetureidPosBetweenVariantset", query = "select mySnpcoreRefposindex from VSnpRefposindex mySnpcoreRefposindex where mySnpcoreRefposindex.chromosome = ?1 and mySnpcoreRefposindex.position between ?2 and ?3 and  mySnpcoreRefposindex.variantset in (?4) order by mySnpcoreRefposindex.position"),
		@NamedQuery(name = "findVSnpRefposindexV3BySrcfetureidVariantset", query = "select mySnpcoreRefposindex from VSnpRefposindex mySnpcoreRefposindex where mySnpcoreRefposindex.chromosome = ?1 and  mySnpcoreRefposindex.variantset in (?2) order by mySnpcoreRefposindex.position"),

		
		@NamedQuery(name = "findVSnpRefposindexV3ByChrPosBetweenVariantset", query = "select mySnpcoreRefposindex from VSnpRefposindex mySnpcoreRefposindex where mySnpcoreRefposindex.chromosome = ?1 and mySnpcoreRefposindex.position between ?2 and ?3 and  mySnpcoreRefposindex.variantset in (?4) order by mySnpcoreRefposindex.position"),
		@NamedQuery(name = "findVSnpRefposindexV3ByChrVariantset", query = "select mySnpcoreRefposindex from VSnpRefposindex mySnpcoreRefposindex where mySnpcoreRefposindex.chromosome = ?1 and  mySnpcoreRefposindex.variantset in (?2) order by mySnpcoreRefposindex.position"),
		@NamedQuery(name = "findVSnpRefposindexV3ByChrPosInVariantset", query = "select mySnpcoreRefposindex from VSnpRefposindex mySnpcoreRefposindex where mySnpcoreRefposindex.chromosome = ?1 and mySnpcoreRefposindex.position in(?2) and mySnpcoreRefposindex.variantset in (?3)  order by mySnpcoreRefposindex.position"),
		@NamedQuery(name = "countVSnpRefposindexV3ByChrPosBetweenVariantset", query = "select count(mySnpcoreRefposindex.snpFeatureId) from VSnpRefposindex mySnpcoreRefposindex where mySnpcoreRefposindex.chromosome = ?1 and mySnpcoreRefposindex.position between ?2 and ?3 and  mySnpcoreRefposindex.variantset in (?4)"),
		@NamedQuery(name = "countVSnpRefposindexV3ByChrPosInVariantset", query = "select count(mySnpcoreRefposindex.snpFeatureId) from VSnpRefposindex mySnpcoreRefposindex where mySnpcoreRefposindex.chromosome = ?1 and mySnpcoreRefposindex.position in(?2) and mySnpcoreRefposindex.variantset in (?3)"),
		@NamedQuery(name = "countVSnpRefposindexV3ByChrVariantset", query = "select count(mySnpcoreRefposindex.snpFeatureId) from VSnpRefposindex mySnpcoreRefposindex where mySnpcoreRefposindex.chromosome = ?1 and  mySnpcoreRefposindex.variantset in (?2)"),

		@NamedQuery(name = "findVSnpRefposindexV3ByPrimaryKey", query = "select myVSnpRefposindex from VSnpRefposindex myVSnpRefposindex where myVSnpRefposindex.snpFeatureId = ?1 and myVSnpRefposindex.typeId = ?2  order by myVSnpRefposindex.chromosome, myVSnpRefposindex.position"),
		@NamedQuery(name = "findVSnpRefposindexV3ByRefcall", query = "select myVSnpRefposindex from VSnpRefposindex myVSnpRefposindex where myVSnpRefposindex.refcall = ?1  order by myVSnpRefposindex.chromosome, myVSnpRefposindex.position"),
		@NamedQuery(name = "findVSnpRefposindexV3ByRefcallContaining", query = "select myVSnpRefposindex from VSnpRefposindex myVSnpRefposindex where myVSnpRefposindex.refcall like ?1  order by myVSnpRefposindex.chromosome, myVSnpRefposindex.position"),
		@NamedQuery(name = "findVSnpRefposindexV3BySnpFeatureId", query = "select myVSnpRefposindex from VSnpRefposindex myVSnpRefposindex where myVSnpRefposindex.snpFeatureId = ?1  order by myVSnpRefposindex.chromosome, myVSnpRefposindex.position"),

		@NamedQuery(name = "findVSnpRefposindexV3ByTypeId", query = "select myVSnpRefposindex from VSnpRefposindex myVSnpRefposindex where myVSnpRefposindex.typeId = ?1  order by myVSnpRefposindex.chromosome, myVSnpRefposindex.position"),
		@NamedQuery(name = "findVSnpRefposindexV3ByVariantset", query = "select myVSnpRefposindex from VSnpRefposindex myVSnpRefposindex where myVSnpRefposindex.variantset in (?1)  order by myVSnpRefposindex.chromosome, myVSnpRefposindex.position"),
		@NamedQuery(name = "countVSnpRefposindexV3ByTypeId", query = "select count(myVSnpRefposindex.snpFeatureId) from VSnpRefposindex myVSnpRefposindex where myVSnpRefposindex.typeId = ?1"),
		@NamedQuery(name = "countVSnpRefposindexV3ByVariantset", query = "select count(myVSnpRefposindex.snpFeatureId) from VSnpRefposindex myVSnpRefposindex where myVSnpRefposindex.variantset in (?1)") })
@Table(name = "V_SNP_REFPOSINDEX_V3")
// @Table( name = "MV_SNP_POSINDEX")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/flatfile/domain", name = "VSnpRefposindex")
public class VSnpRefposindexV3 implements Serializable, SnpsAllvarsPos, Comparable {
	
	// chr is string
	
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "SNP_FEATURE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	// @Id
	@XmlElement
	BigDecimal snpFeatureId;
	/**
	 */

	@Column(name = "VARIANTSET", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	// @Id
	@XmlElement
	String variantset;

	@Column(name = "TYPE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	// @Id
	@XmlElement
	BigDecimal typeId;
	/**
	 */

	@Column(name = "CHROMOSOME")
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String chromosome;
	/**
	 */

	@Column(name = "POSITION", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal position;
	/**
	 */

	@Column(name = "REFCALL", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String refcall;

	@Column(name = "ALTCALL", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String altcall;

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
	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}

	/**
	 */
	public String getChromosome() {
		return this.chromosome;
	}

	/**
	 */
	public void setPosition(BigDecimal position) {
		this.position = position;
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
	public VSnpRefposindexV3() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VSnpRefposindexV3 that) {
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

		buffer.append(getClass() + ": ");
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
		result = (int) (prime * result + ((chromosome == null) ? 0 : chromosome.hashCode()));
		result = (int) (prime * result + ((position == null) ? 0 : position.hashCode()));
		// result = (int) (prime * result + (( snpFeatureId== null) ? 0 :
		// snpFeatureId.hashCode()));
		return result;
	}

	/**
	 */
	/*
	 * public boolean equals(Object obj) { if (obj == this) return true; if (!(obj
	 * instanceof VSnpRefposindex)) return false; VSnpRefposindex equalCheck =
	 * (VSnpRefposindex) obj; if ((snpFeatureId == null && equalCheck.snpFeatureId
	 * != null) || (snpFeatureId != null && equalCheck.snpFeatureId == null)) return
	 * false; if (snpFeatureId != null &&
	 * !snpFeatureId.equals(equalCheck.snpFeatureId)) return false; if ((typeId ==
	 * null && equalCheck.typeId != null) || (typeId != null && equalCheck.typeId ==
	 * null)) return false; if (typeId != null && !typeId.equals(equalCheck.typeId))
	 * return false; return true; }
	 */

	@Override
	public BigDecimal getPosition() {

		return this.position;
	}

	@Override
	public String getRefnuc() {

		return this.refcall;
	}

	@Override
	public void setRefnuc(String refnuc) {

		this.refcall = refnuc;
	}

	@Override
	public int compareTo(Object o) {

		/*
		 * VSnpRefposindex pos=(VSnpRefposindex)o; int
		 * ret=this.getChromosome().compareTo(pos.getChromosome()); if(ret==0)
		 * ret=this.getPosition().compareTo(pos.getPosition()); return ret;
		 */
		Position pos = (Position) o;
		// int ret=this.getContig().compareTo(pos.getContig());
		int ret = 0;
		if (getChr() == null) {
			if(pos.getChr()==null) {
				ret=getContig().compareTo(pos.getContig());
			} else {
				ret=1;
			}
		} else {
			if(pos.getChr()==null) {
				ret=-1;
			} else {
				ret=getChr().compareTo(pos.getChr());
			}
		}

		if (ret == 0)
			ret = this.getPosition().compareTo(pos.getPosition());
		/*
		 * if(o instanceof VSnpRefposindex) { if(ret==0) ret= snpFeatureId.compareTo(
		 * ((VSnpRefposindex) o).getSnpFeatureId() ); }
		 */
		return ret;
	}

	@Override
	public boolean equals(Object obj) {

		return compareTo(obj) == 0;
	}

	@Override
	public String getContig() {

		return getChromosome();
		/*
		if (getChromosome().intValue() > 9)
			return "chr" + getChromosome();
		else
			return "chr0" + getChromosome();
			*/
	}

	@Override
	public Long getChr() {

		if (AppContext.guessChrFromString(getChromosome())!=null)
				return Long.valueOf(AppContext.guessChrFromString(getChromosome()));
		return null;
	}

	
	@Override
	public void setAltnuc(String altnuc) {

		this.altcall = altnuc;
	}

	@Override
	public String getAltnuc() {

		return altcall;
	}

}
