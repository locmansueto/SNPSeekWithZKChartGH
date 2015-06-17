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
@IdClass(org.irri.iric.portal.chado.domain.VSnpAllvarsMinPK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVSnpAllvarsMins", query = "select myVSnpAllvarsMin from VSnpAllvarsMin myVSnpAllvarsMin"),
		@NamedQuery(name = "findVSnpAllvarsMinByChr", query = "select myVSnpAllvarsMin from VSnpAllvarsMin myVSnpAllvarsMin where myVSnpAllvarsMin.chr = ?1+2"),
		
		@NamedQuery(name = "findVSnpAllvarsMinByChrVar", query = "select myVSnpAllvarsMin from VSnpAllvarsMin myVSnpAllvarsMin where myVSnpAllvarsMin.chr = ?1+2 and myVSnpAllvarsMin.var = ?2"),
		
		
		@NamedQuery(name = "findVSnpAllvarsMinByPos", query = "select myVSnpAllvarsMin from VSnpAllvarsMin myVSnpAllvarsMin where myVSnpAllvarsMin.pos = ?1"),

		
		@NamedQuery(name = "findVSnpAllvarsMinByChrPosBetween", query = "select myVSnpAllvarsMin from VSnpAllvarsMin myVSnpAllvarsMin where  myVSnpAllvarsMin.chr = ?1+2 and myVSnpAllvarsMin.pos between ?2 and ?3 " ),
			//	+ " and (myVSnpAllvars.refnuc<>myVSnpAllvars.varnuc) and (myVSnpAllvars.refnuc is not null or myVSnpAllvars.varnuc is not null)"  ),
		@NamedQuery(name = "findVSnpAllvarsMinByVarsChrPosBetween", query = "select myVSnpAllvarsMin from VSnpAllvarsMin myVSnpAllvarsMin where  myVSnpAllvarsMin.chr = ?1+2 and myVSnpAllvarsMin.pos between ?2 and ?3 " +
		//	" and (myVSnpAllvars.refnuc<>myVSnpAllvars.varnuc) and (myVSnpAllvars.refnuc is not null or myVSnpAllvars.varnuc is not null) "  +	
			" and myVSnpAllvarsMin.var in (?4)" ),

			@NamedQuery(name = "findVSnpAllvarsMinByVarsChrPosBetween2", query = "select myVSnpAllvarsMin from VSnpAllvarsMin myVSnpAllvarsMin where  myVSnpAllvarsMin.chr = ?1+2 and myVSnpAllvarsMin.pos between ?2 and ?3 " +
					//	" and (myVSnpAllvars.refnuc<>myVSnpAllvars.varnuc) and (myVSnpAllvars.refnuc is not null or myVSnpAllvars.varnuc is not null) "  +	
						" and (myVSnpAllvarsMin.var in (?4) or myVSnpAllvarsMin.var in (?5) ) " ),
			@NamedQuery(name = "findVSnpAllvarsMinByVarsChrPosBetween3", query = "select myVSnpAllvarsMin from VSnpAllvarsMin myVSnpAllvarsMin where  myVSnpAllvarsMin.chr = ?1+2 and myVSnpAllvarsMin.pos between ?2 and ?3 " +
					//	" and (myVSnpAllvars.refnuc<>myVSnpAllvars.varnuc) and (myVSnpAllvars.refnuc is not null or myVSnpAllvars.varnuc is not null) "  +	
						" and (myVSnpAllvarsMin.var in (?4) or myVSnpAllvarsMin.var in (?5) or myVSnpAllvarsMin.var in (?6))" ),
			
			

		@NamedQuery(name = "findVSnpAllvarsMinByChrPosBetweenRefmismatch", query = "select myVSnpAllvarsMin from VSnpAllvarsMin myVSnpAllvarsMin where  myVSnpAllvarsMin.chr = ?1+2 and myVSnpAllvarsMin.pos between ?2 and ?3 " 
				+ " and ( (myVSnpAllvarsMin.refnuc<>myVSnpAllvarsMin.varnuc) or (myVSnpAllvarsMin.varnuc is null) )"  ),
		@NamedQuery(name = "findVSnpAllvarsMinByVarsChrPosBetweenRefmismatch", query = "select myVSnpAllvarsMin from VSnpAllvarsMin myVSnpAllvarsMin where  myVSnpAllvarsMin.chr = ?1+2 and myVSnpAllvarsMin.pos between ?2 and ?3 " 
				+ " and ( (myVSnpAllvarsMin.refnuc<>myVSnpAllvarsMin.varnuc) or (myVSnpAllvarsMin.varnuc is null) )" 
				+ " and myVSnpAllvarsMin.var in (?4)" ),
		@NamedQuery(name = "findVSnpAllvarsMinByVarsChrPosBetweenRefmismatch2", query = "select myVSnpAllvarsMin from VSnpAllvarsMin myVSnpAllvarsMin where  myVSnpAllvarsMin.chr = ?1+2 and myVSnpAllvarsMin.pos between ?2 and ?3 " 
				+ " and ( (myVSnpAllvarsMin.refnuc<>myVSnpAllvarsMin.varnuc) or (myVSnpAllvarsMin.varnuc is null) )" 
				+ " and (myVSnpAllvarsMin.var in (?4) or myVSnpAllvarsMin.var in (?5))" ),
		@NamedQuery(name = "findVSnpAllvarsMinByVarsChrPosBetweenRefmismatch3", query = "select myVSnpAllvarsMin from VSnpAllvarsMin myVSnpAllvarsMin where  myVSnpAllvarsMin.chr = ?1+2 and myVSnpAllvarsMin.pos between ?2 and ?3 " 
				+ " and ( (myVSnpAllvarsMin.refnuc<>myVSnpAllvarsMin.varnuc) or (myVSnpAllvarsMin.varnuc is null) )" 
				+ " and (myVSnpAllvarsMin.var in (?4) or myVSnpAllvarsMin.var in (?5) or myVSnpAllvarsMin.var in (?6))" ),
		
				

						
		
		@NamedQuery(name = "findVSnpAllvarsMinByPrimaryKey", query = "select myVSnpAllvarsMin from VSnpAllvarsMin myVSnpAllvarsMin where myVSnpAllvarsMin.var = ?1 and myVSnpAllvarsMin.pos = ?2 and myVSnpAllvarsMin.chr = ?3"),
		@NamedQuery(name = "findVSnpAllvarsMinByRefnuc", query = "select myVSnpAllvarsMin from VSnpAllvarsMin myVSnpAllvarsMin where myVSnpAllvarsMin.refnuc = ?1"),
		@NamedQuery(name = "findVSnpAllvarsMinByRefnucContaining", query = "select myVSnpAllvarsMin from VSnpAllvarsMin myVSnpAllvarsMin where myVSnpAllvarsMin.refnuc like ?1"),
		@NamedQuery(name = "findVSnpAllvarsMinByVar", query = "select myVSnpAllvarsMin from VSnpAllvarsMin myVSnpAllvarsMin where myVSnpAllvarsMin.var = ?1"),
		@NamedQuery(name = "findVSnpAllvarsMinByVarnuc", query = "select myVSnpAllvarsMin from VSnpAllvarsMin myVSnpAllvarsMin where myVSnpAllvarsMin.varnuc = ?1"),
		@NamedQuery(name = "findVSnpAllvarsMinByVarnucContaining", query = "select myVSnpAllvarsMin from VSnpAllvarsMin myVSnpAllvarsMin where myVSnpAllvarsMin.varnuc like ?1") })
@Table(schema = "IRIC", name = "V_SNP_ALLVARS")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VSnpAllvarsMin")
public class VSnpAllvarsMin implements Serializable , SnpsAllvars {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "VAR", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal var;
	/**
	 */

	@Column(name = "POS", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal pos;
	/**
	 */

	@Column(name = "PARTITION_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Long chr;
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
	public VSnpAllvarsMin() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VSnpAllvarsMin that) {
		setVar(that.getVar());
		setPos(that.getPos());
		setChr(that.getChr());
		setRefnuc(that.getRefnuc());
		setVarnuc(that.getVarnuc());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("var=[").append(var).append("] ");
		buffer.append("pos=[").append(pos).append("] ");
		buffer.append("chr=[").append(chr).append("] ");
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
		result = (int) (prime * result + ((var == null) ? 0 : var.hashCode()));
		result = (int) (prime * result + ((pos == null) ? 0 : pos.hashCode()));
		result = (int) (prime * result + ((chr == null) ? 0 : chr.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VSnpAllvarsMin))
			return false;
		VSnpAllvarsMin equalCheck = (VSnpAllvarsMin) obj;
		if ((var == null && equalCheck.var != null) || (var != null && equalCheck.var == null))
			return false;
		if (var != null && !var.equals(equalCheck.var))
			return false;
		if ((pos == null && equalCheck.pos != null) || (pos != null && equalCheck.pos == null))
			return false;
		if (pos != null && !pos.equals(equalCheck.pos))
			return false;
		if ((chr == null && equalCheck.chr != null) || (chr != null && equalCheck.chr == null))
			return false;
		if (chr != null && !chr.equals(equalCheck.chr))
			return false;
		return true;
	}

	@Override
	public String getContig() {
		// TODO Auto-generated method stub
		if(getChr()>9)
			return "chr" + getChr();
		else return "chr0" + getChr();
	}
	
	
	
}
