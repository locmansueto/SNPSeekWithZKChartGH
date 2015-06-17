package org.irri.iric.portal.chado.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.IndelsAllvars;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVIndelAllvarss", query = "select myVIndelAllvars from VIndelAllvars myVIndelAllvars"),
		@NamedQuery(name = "findVIndelAllvarsByAllele1Id", query = "select myVIndelAllvars from VIndelAllvars myVIndelAllvars where myVIndelAllvars.allele1Id = ?1"),
		@NamedQuery(name = "findVIndelAllvarsByAllele2Id", query = "select myVIndelAllvars from VIndelAllvars myVIndelAllvars where myVIndelAllvars.allele2Id = ?1"),
		@NamedQuery(name = "findVIndelAllvarsByIndelCallId", query = "select myVIndelAllvars from VIndelAllvars myVIndelAllvars where myVIndelAllvars.indelCallId = ?1"),
		@NamedQuery(name = "findVIndelAllvarsByPartitionId", query = "select myVIndelAllvars from VIndelAllvars myVIndelAllvars where myVIndelAllvars.partitionId = ?1"),
		@NamedQuery(name = "findVIndelAllvarsByPos", query = "select myVIndelAllvars from VIndelAllvars myVIndelAllvars where myVIndelAllvars.pos = ?1"),
		
		@NamedQuery(name = "findVIndelAllvarsByChrPosBetween", query = "select myVIndelAllvars from VIndelAllvars myVIndelAllvars where  myVIndelAllvars.partitionId = ?1 and " +
					" myVIndelAllvars.pos between ?2 and ?3"),

		@NamedQuery(name = "findVIndelAllvarsByVarChrPosBetween", query = "select myVIndelAllvars from VIndelAllvars myVIndelAllvars where  myVIndelAllvars.partitionId = ?1 and " +
							" myVIndelAllvars.pos between ?2 and ?3 and myVIndelAllvars.var in (?4)"),

		@NamedQuery(name = "findVIndelAllvarsByChrPosIn", query = "select myVIndelAllvars from VIndelAllvars myVIndelAllvars where  myVIndelAllvars.partitionId = ?1 and " +
				" myVIndelAllvars.pos in(?2)"),
		@NamedQuery(name = "findVIndelAllvarsByVarChrPosIn", query = "select myVIndelAllvars from VIndelAllvars myVIndelAllvars where  myVIndelAllvars.partitionId = ?1 and " +
				" myVIndelAllvars.var in (?2) and myVIndelAllvars.pos in (?3)"),

				
		@NamedQuery(name = "findVIndelAllvarsByPrimaryKey", query = "select myVIndelAllvars from VIndelAllvars myVIndelAllvars where myVIndelAllvars.indelCallId = ?1"),
		@NamedQuery(name = "findVIndelAllvarsByVar", query = "select myVIndelAllvars from VIndelAllvars myVIndelAllvars where myVIndelAllvars.var = ?1") })
@Table(schema = "IRIC", name = "V_INDEL_ALLVARS")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VIndelAllvars")
public class VIndelAllvars implements Serializable, IndelsAllvars {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "INDEL_CALL_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal indelCallId;
	/**
	 */

	@Column(name = "VAR", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal var;
	/**
	 */

	@Column(name = "PARTITION_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal partitionId;
	/**
	 */

	@Column(name = "POS")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal pos;
	/**
	 */

	@Column(name = "ALLELE1_ID")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal allele1Id;
	/**
	 */

	@Column(name = "ALLELE2_ID")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal allele2Id;

	/**
	 */
	public void setIndelCallId(BigDecimal indelCallId) {
		this.indelCallId = indelCallId;
	}

	/**
	 */
	public BigDecimal getIndelCallId() {
		return this.indelCallId;
	}

	/**
	 */
	public void setVar(BigDecimal var) {
		this.var = var;
	}

	/**
	 */
	public BigDecimal getVar() {
		return this.var;
	}

	/**
	 */
	public void setPartitionId(BigDecimal partitionId) {
		this.partitionId = partitionId;
	}

	/**
	 */
	public BigDecimal getPartitionId() {
		return this.partitionId;
	}

	/**
	 */
	public void setPos(BigDecimal pos) {
		this.pos = pos;
	}

	/**
	 */
	public BigDecimal getPos() {
		return this.pos;
	}

	/**
	 */
	public void setAllele1Id(BigDecimal allele1Id) {
		this.allele1Id = allele1Id;
	}

	/**
	 */
	public BigDecimal getAllele1Id() {
		return this.allele1Id;
	}

	/**
	 */
	public void setAllele2Id(BigDecimal allele2Id) {
		this.allele2Id = allele2Id;
	}

	/**
	 */
	public BigDecimal getAllele2Id() {
		return this.allele2Id;
	}

	/**
	 */
	public VIndelAllvars() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VIndelAllvars that) {
		setIndelCallId(that.getIndelCallId());
		setVar(that.getVar());
		setPartitionId(that.getPartitionId());
		setPos(that.getPos());
		setAllele1Id(that.getAllele1Id());
		setAllele2Id(that.getAllele2Id());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("indelCallId=[").append(indelCallId).append("] ");
		buffer.append("var=[").append(var).append("] ");
		buffer.append("partitionId=[").append(partitionId).append("] ");
		buffer.append("pos=[").append(pos).append("] ");
		buffer.append("allele1Id=[").append(allele1Id).append("] ");
		buffer.append("allele2Id=[").append(allele2Id).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((indelCallId == null) ? 0 : indelCallId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VIndelAllvars))
			return false;
		VIndelAllvars equalCheck = (VIndelAllvars) obj;
		if ((indelCallId == null && equalCheck.indelCallId != null) || (indelCallId != null && equalCheck.indelCallId == null))
			return false;
		if (indelCallId != null && !indelCallId.equals(equalCheck.indelCallId))
			return false;
		return true;
	}

	@Override
	public Long getChr() {
		// TODO Auto-generated method stub
		return this.partitionId.longValue()-2;
	}

	@Override
	public String getRefnuc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVarnuc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getAllele1() {
		// TODO Auto-generated method stub
		return this.allele1Id;
	}

	@Override
	public BigDecimal getAllele2() {
		// TODO Auto-generated method stub
		return this.allele2Id;
	}

	@Override
	public String getContig() {
		// TODO Auto-generated method stub
		if(getChr()>9)
			return "chr" +  getChr();
		else 
			return "chr0" +  getChr();
	}
	
	
	
	
}
