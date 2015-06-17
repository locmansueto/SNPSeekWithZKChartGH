package org.irri.iric.portal.chado.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.SnpsAllvars;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVSnpAllvarss", query = "select myVSnpAllvars from VSnpAllvars myVSnpAllvars"),
		@NamedQuery(name = "findVSnpAllvarsByChr", query = "select myVSnpAllvars from VSnpAllvars myVSnpAllvars where myVSnpAllvars.chr = ?1"),
		@NamedQuery(name = "findVSnpAllvarsByPos", query = "select myVSnpAllvars from VSnpAllvars myVSnpAllvars where myVSnpAllvars.pos = ?1"),

		@NamedQuery(name = "findVSnpAllvarsByChrPosBetween", query = "select myVSnpAllvars from VSnpAllvars myVSnpAllvars where  myVSnpAllvars.chr = ?1 and myVSnpAllvars.pos between ?2 and ?3 " ),
				 										//	+ " and (myVSnpAllvars.refnuc<>myVSnpAllvars.varnuc) and (myVSnpAllvars.refnuc is not null or myVSnpAllvars.varnuc is not null)"  ),
		@NamedQuery(name = "findVSnpAllvarsByVarsChrPosBetween", query = "select myVSnpAllvars from VSnpAllvars myVSnpAllvars where  myVSnpAllvars.chr = ?1 and myVSnpAllvars.pos between ?2 and ?3 " +
															" and myVSnpAllvars.var in (?4)" ),
		@NamedQuery(name = "findVSnpAllvarsByVarsChrPosBetween2", query = "select myVSnpAllvars from VSnpAllvars myVSnpAllvars where  myVSnpAllvars.chr = ?1 and myVSnpAllvars.pos between ?2 and ?3 " +
					" and ( myVSnpAllvars.var in (?4) or myVSnpAllvars.var in (?5))" ),
		@NamedQuery(name = "findVSnpAllvarsByVarsChrPosBetween3", query = "select myVSnpAllvars from VSnpAllvars myVSnpAllvars where  myVSnpAllvars.chr = ?1 and myVSnpAllvars.pos between ?2 and ?3 " +
					" and ( myVSnpAllvars.var in (?4) or myVSnpAllvars.var in (?5)  or  myVSnpAllvars.var in (?6))" ),
													 
		
		@NamedQuery(name = "findVSnpAllvarsByPrimaryKey", query = "select myVSnpAllvars from VSnpAllvars myVSnpAllvars where myVSnpAllvars.snpGenotypeId = ?1"),
		@NamedQuery(name = "findVSnpAllvarsByRefnuc", query = "select myVSnpAllvars from VSnpAllvars myVSnpAllvars where myVSnpAllvars.refnuc = ?1"),
		@NamedQuery(name = "findVSnpAllvarsByRefnucContaining", query = "select myVSnpAllvars from VSnpAllvars myVSnpAllvars where myVSnpAllvars.refnuc like ?1"),
		@NamedQuery(name = "findVSnpAllvarsBySnpGenotypeId", query = "select myVSnpAllvars from VSnpAllvars myVSnpAllvars where myVSnpAllvars.snpGenotypeId = ?1"),
		@NamedQuery(name = "findVSnpAllvarsByVar", query = "select myVSnpAllvars from VSnpAllvars myVSnpAllvars where myVSnpAllvars.var = ?1"),
		@NamedQuery(name = "findVSnpAllvarsByVarnuc", query = "select myVSnpAllvars from VSnpAllvars myVSnpAllvars where myVSnpAllvars.varnuc = ?1"),
		@NamedQuery(name = "findVSnpAllvarsByVarnucContaining", query = "select myVSnpAllvars from VSnpAllvars myVSnpAllvars where myVSnpAllvars.varnuc like ?1") })
@Table(schema = "IRIC", name = "V_SNP_ALLVARS")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VSnpAllvars")
public class VSnpAllvars implements Serializable, SnpsAllvars {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "SNP_GENOTYPE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	//Integer snpGenotypeId;
	BigDecimal snpGenotypeId;
	/**
	 */

	@Column(name = "VAR", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal var;
	/**
	 */

	@Column(name = "PARTITION_ID")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Long chr;
	/**
	 */

	@Column(name = "POS", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal pos;
	/**
	 */

	@Column(name = "REFNUC", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String refnuc;
	/**
	 */

	@Column(name = "VARNUC", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String varnuc;

	/**
	 */
	public void setSnpGenotypeId(BigDecimal snpGenotypeId) {
		this.snpGenotypeId = snpGenotypeId;
	}

	/**
	 */
	public BigDecimal getSnpGenotypeId() {
		return this.snpGenotypeId;
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
	public void setChr(Long chr) {
		this.chr = chr+2;
	}

	/**
	 */
	public Long getChr() {
		return this.chr-2;
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
	public void setRefnuc(String refnuc) {
		this.refnuc = refnuc;
	}

	/**
	 */
	public String getRefnuc() {
		return this.refnuc;
	}

	/**
	 */
	public void setVarnuc(String varnuc) {
		this.varnuc = varnuc;
	}

	/**
	 */
	public String getVarnuc() {
		return this.varnuc;
	}

	/**
	 */
	public VSnpAllvars() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VSnpAllvars that) {
		setSnpGenotypeId(that.getSnpGenotypeId());
		setVar(that.getVar());
		setChr(that.getChr());
		setPos(that.getPos());
		setRefnuc(that.getRefnuc());
		setVarnuc(that.getVarnuc());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("snpGenotypeId=[").append(snpGenotypeId).append("] ");
		buffer.append("var=[").append(var).append("] ");
		buffer.append("chr=[").append(chr).append("] ");
		buffer.append("pos=[").append(pos).append("] ");
		buffer.append("refnuc=[").append(refnuc).append("] ");
		buffer.append("varnuc=[").append(varnuc).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((snpGenotypeId == null) ? 0 : snpGenotypeId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VSnpAllvars))
			return false;
		VSnpAllvars equalCheck = (VSnpAllvars) obj;
		if ((snpGenotypeId == null && equalCheck.snpGenotypeId != null) || (snpGenotypeId != null && equalCheck.snpGenotypeId == null))
			return false;
		if (snpGenotypeId != null && !snpGenotypeId.equals(equalCheck.snpGenotypeId))
			return false;
		return true;
	}

	@Override
	public String getContig() {
		// TODO Auto-generated method stub
		if(getChr()>9)
			return "chr" + getChr();
		else 
			return "chr0" + getChr();
	}
	
	
	
}
