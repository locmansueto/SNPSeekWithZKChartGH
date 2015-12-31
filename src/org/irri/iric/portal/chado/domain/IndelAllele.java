package org.irri.iric.portal.chado.domain;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.IndelsAllvarsPos;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllIndelAlleles", query = "select myIndelAllele from IndelAllele myIndelAllele"),
		@NamedQuery(name = "findIndelAlleleByDelLen", query = "select myIndelAllele from IndelAllele myIndelAllele where myIndelAllele.delLen = ?1"),
		@NamedQuery(name = "findIndelAlleleByIndelId", query = "select myIndelAllele from IndelAllele myIndelAllele where myIndelAllele.indelId = ?1"),
		@NamedQuery(name = "findIndelAlleleByInsStr", query = "select myIndelAllele from IndelAllele myIndelAllele where myIndelAllele.insStr = ?1"),
		@NamedQuery(name = "findIndelAlleleByInsStrContaining", query = "select myIndelAllele from IndelAllele myIndelAllele where myIndelAllele.insStr like ?1"),
		@NamedQuery(name = "findIndelAlleleByMethod", query = "select myIndelAllele from IndelAllele myIndelAllele where myIndelAllele.method = ?1"),
		@NamedQuery(name = "findIndelAlleleByPos", query = "select myIndelAllele from IndelAllele myIndelAllele where myIndelAllele.pos = ?1"),
		
		@NamedQuery(name = "findIndelAlleleBySrcfeatureidPosBetween", query = "select myIndelAllele from IndelAllele myIndelAllele where myIndelAllele.srcFeatureId = ?1 and myIndelAllele.pos between ?2 and ?3 "), 
						//" order by  myIndelAllele.pos,  myIndelAllele.indelId"),

		@NamedQuery(name = "findIndelAlleleBySrcfeatureidPosIn", query = "select myIndelAllele from IndelAllele myIndelAllele where myIndelAllele.srcFeatureId = ?1 and myIndelAllele.pos in (?2) "),
							// " order by  myIndelAllele.pos,  myIndelAllele.indelId"),
						
		@NamedQuery(name = "findIndelAlleleByIndelIdIn", query = "select myIndelAllele from IndelAllele myIndelAllele where myIndelAllele.indelId in (?1)"),
		// " order by  myIndelAllele.pos,  myIndelAllele.indelId"),
						
						
		@NamedQuery(name = "findIndelAlleleByPrimaryKey", query = "select myIndelAllele from IndelAllele myIndelAllele where myIndelAllele.indelId = ?1"),
		@NamedQuery(name = "findIndelAlleleBySrcFeatureId", query = "select myIndelAllele from IndelAllele myIndelAllele where myIndelAllele.srcFeatureId = ?1") })
@Table(schema = "IRIC", name = "INDEL_ALLELE")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "IndelAllele")
public class IndelAllele implements Serializable, IndelsAllvarsPos {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "INDEL_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal indelId;
	/**
	 */

	@Column(name = "SRC_FEATURE_ID")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal srcFeatureId;
	/**
	 */

	@Column(name = "POS")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal pos;
	/**
	 */

	@Column(name = "DEL_LEN")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer delLen;
	/**
	 */

	@Column(name = "INS_STR", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String insStr;
	/**
	 */

	@Column(name = "METHOD")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer method;

	//private String refnuc;
	
	/**
	 */
	public void setIndelId(BigDecimal indelId) {
		this.indelId = indelId;
	}

	/**
	 */
	public BigDecimal getIndelId() {
		return this.indelId;
	}

	/**
	 */
	public void setSrcFeatureId(BigDecimal srcFeatureId) {
		this.srcFeatureId = srcFeatureId;
	}

	/**
	 */
	public BigDecimal getSrcFeatureId() {
		return this.srcFeatureId;
	}

	/**
	 */
	public void setPos(BigDecimal pos) {
		this.pos = pos.subtract(BigDecimal.ONE);
	}


	/**
	 */
	public void setDelLen(Integer delLen) {
		this.delLen = delLen;
	}

	/**
	 */
	public Integer getDelLen() {
		return this.delLen;
	}

	/**
	 */
	public void setInsStr(String insStr) {
		this.insStr = insStr;
	}

	/**
	 */
	public String getInsStr() {
		return this.insStr;
	}

	/**
	 */
	public void setMethod(Integer method) {
		this.method = method;
	}

	/**
	 */
	public Integer getMethod() {
		return this.method;
	}

	/**
	 */
	public IndelAllele() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(IndelAllele that) {
		setIndelId(that.getIndelId());
		setSrcFeatureId(that.getSrcFeatureId());
		setPos(that.getPosition());
		setDelLen(that.getDelLen());
		setInsStr(that.getInsStr());
		setMethod(that.getMethod());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("indelId=[").append(indelId).append("] ");
		buffer.append("srcFeatureId=[").append(srcFeatureId).append("] ");
		buffer.append("pos=[").append(pos).append("] ");
		buffer.append("delLen=[").append(delLen).append("] ");
		buffer.append("insStr=[").append(insStr).append("] ");
		buffer.append("method=[").append(method).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((indelId == null) ? 0 : indelId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof IndelAllele))
			return false;
		IndelAllele equalCheck = (IndelAllele) obj;
		if ((indelId == null && equalCheck.indelId != null) || (indelId != null && equalCheck.indelId == null))
			return false;
		if (indelId != null && !indelId.equals(equalCheck.indelId))
			return false;
		return true;
	}

	@Override
	public String getRefnuc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getMaxDellength() {
		// TODO Auto-generated method stub
		return this.delLen;
	}

	@Override
	public String getInsString() {
		// TODO Auto-generated method stub
		if(this.insStr==null)
			return "";
		else return this.insStr;
	}

	@Override
	public void setRefnuc(String refnuc) {
		// TODO Auto-generated method stub
	//	 this.refnuc=refnuc;
	}

	@Override
	public String getContig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getAlleleIndex() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getSnpFeatureId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getMaxInsLength() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Integer getDellength() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public BigDecimal getPosition() {
		// TODO Auto-generated method stub
		return this.pos;
	}



	
	@Override
	public Long getChr() {
		// TODO Auto-generated method stub
		if(srcFeatureId.intValue()<15) return srcFeatureId.longValue()-2 ;
		return null;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		//return 0;
		throw new RuntimeException("Unexpected IndelAllele.compareTo()");
	}
	
	
	
	
}
