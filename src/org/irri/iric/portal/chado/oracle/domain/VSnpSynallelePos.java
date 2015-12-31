package org.irri.iric.portal.chado.oracle.domain;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.SnpsSynAllele;

/**
 */
@IdClass(org.irri.iric.portal.chado.oracle.domain.VSnpSynallelePosPK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVSnpSynallelePoss", query = "select myVSnpSynallelePos from VSnpSynallelePos myVSnpSynallelePos"),
		@NamedQuery(name = "findVSnpSynallelePosByAlleleSyn", query = "select myVSnpSynallelePos from VSnpSynallelePos myVSnpSynallelePos where myVSnpSynallelePos.alleleSyn = ?1"),
		@NamedQuery(name = "findVSnpSynallelePosByAlleleSynContaining", query = "select myVSnpSynallelePos from VSnpSynallelePos myVSnpSynallelePos where myVSnpSynallelePos.alleleSyn like ?1"),
		@NamedQuery(name = "findVSnpSynallelePosByChr", query = "select myVSnpSynallelePos from VSnpSynallelePos myVSnpSynallelePos where myVSnpSynallelePos.chr = ?1"),
		@NamedQuery(name = "findVSnpSynallelePosByPosition", query = "select myVSnpSynallelePos from VSnpSynallelePos myVSnpSynallelePos where myVSnpSynallelePos.position = ?1"),
		@NamedQuery(name = "findVSnpSynallelePosByPrimaryKey", query = "select myVSnpSynallelePos from VSnpSynallelePos myVSnpSynallelePos where myVSnpSynallelePos.chr = ?1 and myVSnpSynallelePos.position = ?2 and myVSnpSynallelePos.alleleSyn = ?3"),
		@NamedQuery(name = "findVSnpSynallelePosBySnpFeatureId", query = "select myVSnpSynallelePos from VSnpSynallelePos myVSnpSynallelePos where myVSnpSynallelePos.snpFeatureId = ?1"),
		
		@NamedQuery(name = "findVSnpSynallelePosByPositionBetween", query = "select myVSnpSynallelePos from VSnpSynallelePos myVSnpSynallelePos where myVSnpSynallelePos.chr = ?1 and myVSnpSynallelePos.position between ?2 and ?3 and myVSnpSynallelePos.typeId=?4"),
		@NamedQuery(name = "findVSnpSynallelePosByPositionIn", query = "select myVSnpSynallelePos from VSnpSynallelePos myVSnpSynallelePos where myVSnpSynallelePos.chr = ?1 and  myVSnpSynallelePos.position in(?2)  and myVSnpSynallelePos.typeId=?3"),
		@NamedQuery(name = "findVSnpSynallelePosBySnpFeatureIdBetween", query = "select myVSnpSynallelePos from VSnpSynallelePos myVSnpSynallelePos where myVSnpSynallelePos.snpFeatureId between ?1 and ?2 and myVSnpSynallelePos.typeId=?3"),
		@NamedQuery(name = "findVSnpSynallelePosBySnpFeatureIdIn", query = "select myVSnpSynallelePos from VSnpSynallelePos myVSnpSynallelePos where myVSnpSynallelePos.snpFeatureId in (?1) and myVSnpSynallelePos.typeId=?2"),
		
		
		
		@NamedQuery(name = "findVSnpSynallelePosByTypeId", query = "select myVSnpSynallelePos from VSnpSynallelePos myVSnpSynallelePos where myVSnpSynallelePos.typeId = ?1") })
@Table(schema = "IRIC", name = "V_SNP_SYNALLELE_POS")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/oracle/domain", name = "VSnpSynallelePos")
public class VSnpSynallelePos implements Serializable, SnpsSynAllele , Comparable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "SNP_FEATURE_ID")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal snpFeatureId;
	/**
	 */

	@Column(name = "CHR")
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal chr;
	/**
	 */

	@Column(name = "POSITION")
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal position;
	/**
	 */

	@Column(name = "TYPE_ID")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal typeId;
	/**
	 */

	@Column(name = "ALLELE_SYN")
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String alleleSyn;

	/**
	 */
	public void setSnpFeatureId(BigDecimal snpFeatureId) {
		this.snpFeatureId = snpFeatureId;
	}



	/**
	 */
	public void setChr(BigDecimal chr) {
		this.chr = chr;
	}

	/**
	 */
	public BigDecimal getChr() {
		return this.chr;
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
	public void setAlleleSyn(String alleleSyn) {
		this.alleleSyn = alleleSyn;
	}

	/**
	 */
	public String getAlleleSyn() {
		return this.alleleSyn;
	}

	/**
	 */
	public VSnpSynallelePos() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VSnpSynallelePos that) {
		setSnpFeatureId(that.getSnpFeatureId());
		setChr(that.getChr());
		setPosition(that.getPosition());
		setTypeId(that.getTypeId());
		setAlleleSyn(that.getAlleleSyn());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("snpFeatureId=[").append(snpFeatureId).append("] ");
		buffer.append("chr=[").append(chr).append("] ");
		buffer.append("position=[").append(position).append("] ");
		buffer.append("typeId=[").append(typeId).append("] ");
		buffer.append("alleleSyn=[").append(alleleSyn).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((chr == null) ? 0 : chr.hashCode()));
		result = (int) (prime * result + ((position == null) ? 0 : position.hashCode()));
		result = (int) (prime * result + ((alleleSyn == null) ? 0 : alleleSyn.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VSnpSynallelePos))
			return false;
		VSnpSynallelePos equalCheck = (VSnpSynallelePos) obj;
		if ((chr == null && equalCheck.chr != null) || (chr != null && equalCheck.chr == null))
			return false;
		if (chr != null && !chr.equals(equalCheck.chr))
			return false;
		if ((position == null && equalCheck.position != null) || (position != null && equalCheck.position == null))
			return false;
		if (position != null && !position.equals(equalCheck.position))
			return false;
		if ((alleleSyn == null && equalCheck.alleleSyn != null) || (alleleSyn != null && equalCheck.alleleSyn == null))
			return false;
		if (alleleSyn != null && !alleleSyn.equals(equalCheck.alleleSyn))
			return false;
		return true;
	}

	@Override
	public BigDecimal getSnpFeatureId() {
		// TODO Auto-generated method stub
		return this.snpFeatureId;
	}

	@Override
	public char getAllele() {
		// TODO Auto-generated method stub
		return this.alleleSyn.charAt(0);
	}
	
	

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		//return this.snpFeatureId.compareTo( ((VSnpSynAllele)o).snpFeatureId );
		
/*		
		int ret= this.snpFeatureId.compareTo( ((VSnpSynallelePos)o).snpFeatureId );
		if(ret==0) this.alleleSyn.compareTo( ((VSnpSynallelePos)o).alleleSyn );
		if(ret==0) this.typeId.compareTo( ((VSnpSynallelePos)o).typeId );
		if(ret==0) AppContext.debug("VSnpSynallelePos.compareTo this=" + toString() + "; o=" + o);

		*/
		
		int ret= this.chr.compareTo( ((VSnpSynallelePos)o).chr );
		if(ret==0) ret = this.position.compareTo( ((VSnpSynallelePos)o).position );
		if(ret==0) ret = this.alleleSyn.compareTo( ((VSnpSynallelePos)o).alleleSyn );

		return ret;
	}
	
}
