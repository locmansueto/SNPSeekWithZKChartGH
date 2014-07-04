package org.irri.iric.portal.variety.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Set;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */
@IdClass(org.irri.iric.portal.variety.domain.GenotypingPK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllGenotypings", query = "select myGenotyping from Genotyping myGenotyping"),
		@NamedQuery(name = "findGenotypingByNsftvId", query = "select myGenotyping from Genotyping myGenotyping where myGenotyping.nsftvId = ?1"),
		@NamedQuery(name = "findGenotypingByPrimaryKey", query = "select myGenotyping from Genotyping myGenotyping where myGenotyping.snpId = ?1 and myGenotyping.nsftvId = ?2"),
		@NamedQuery(name = "findGenotypingBySnpId", query = "select myGenotyping from Genotyping myGenotyping where myGenotyping.snpId = ?1"),
		@NamedQuery(name = "findGenotypingBySnpIdContaining", query = "select myGenotyping from Genotyping myGenotyping where myGenotyping.snpId like ?1"),
		@NamedQuery(name = "findGenotypingByVar1", query = "select myGenotyping from Genotyping myGenotyping where myGenotyping.var1 = ?1"),
		@NamedQuery(name = "findGenotypingByVar1Containing", query = "select myGenotyping from Genotyping myGenotyping where myGenotyping.var1 like ?1"),
		@NamedQuery(name = "findGenotypingByVar2", query = "select myGenotyping from Genotyping myGenotyping where myGenotyping.var2 = ?1"),
		@NamedQuery(name = "findGenotypingByVar2Containing", query = "select myGenotyping from Genotyping myGenotyping where myGenotyping.var2 like ?1") })
@Table(schema = "NICKA", name = "GENOTYPING")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "dev_crud_maker/org/irri/iric/portal/variety/domain", name = "Genotyping")
public class Genotyping implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "SNP_ID", length = 10)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String snpId;
	/**
	 */

	@Column(name = "NSFTV_ID")
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer nsftvId;
	/**
	 */

	@Column(name = "VAR1", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String var1;
	/**
	 */

	@Column(name = "VAR2", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String var2;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "SNP_ID", referencedColumnName = "SNP_ID", insertable = false, updatable = false) })
	@XmlTransient
	Snps snps;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "NSFTV_ID", referencedColumnName = "NSFTV_ID", insertable = false, updatable = false) })
	@XmlTransient
	Germplasm germplasm;

	/**
	 */
	public void setSnpId(String snpId) {
		this.snpId = snpId;
	}

	/**
	 */
	public String getSnpId() {
		return this.snpId;
	}

	/**
	 */
	public void setNsftvId(Integer nsftvId) {
		this.nsftvId = nsftvId;
	}

	/**
	 */
	public Integer getNsftvId() {
		return this.nsftvId;
	}

	/**
	 */
	public void setVar1(String var1) {
		this.var1 = var1;
	}

	/**
	 */
	public String getVar1() {
		return this.var1;
	}

	/**
	 */
	public void setVar2(String var2) {
		this.var2 = var2;
	}

	/**
	 */
	public String getVar2() {
		return this.var2;
	}

	/**
	 */
	public void setSnps(Snps snps) {
		this.snps = snps;
	}

	/**
	 */
	@JsonIgnore
	public Snps getSnps() {
		return snps;
	}

	/**
	 */
	public void setGermplasm(Germplasm germplasm) {
		this.germplasm = germplasm;
	}

	/**
	 */
	@JsonIgnore
	public Germplasm getGermplasm() {
		return germplasm;
	}

	/**
	 */
	public Genotyping() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Genotyping that) {
		setSnpId(that.getSnpId());
		setNsftvId(that.getNsftvId());
		setVar1(that.getVar1());
		setVar2(that.getVar2());
		setSnps(that.getSnps());
		setGermplasm(that.getGermplasm());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("snpId=[").append(snpId).append("] ");
		buffer.append("nsftvId=[").append(nsftvId).append("] ");
		buffer.append("var1=[").append(var1).append("] ");
		buffer.append("var2=[").append(var2).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((snpId == null) ? 0 : snpId.hashCode()));
		result = (int) (prime * result + ((nsftvId == null) ? 0 : nsftvId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Genotyping))
			return false;
		Genotyping equalCheck = (Genotyping) obj;
		if ((snpId == null && equalCheck.snpId != null) || (snpId != null && equalCheck.snpId == null))
			return false;
		if (snpId != null && !snpId.equals(equalCheck.snpId))
			return false;
		if ((nsftvId == null && equalCheck.nsftvId != null) || (nsftvId != null && equalCheck.nsftvId == null))
			return false;
		if (nsftvId != null && !nsftvId.equals(equalCheck.nsftvId))
			return false;
		return true;
	}
}
