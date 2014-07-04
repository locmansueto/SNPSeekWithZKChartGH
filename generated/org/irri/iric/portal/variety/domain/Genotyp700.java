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
@IdClass(org.irri.iric.portal.variety.domain.Genotyp700PK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllGenotyp700s", query = "select myGenotyp700 from Genotyp700 myGenotyp700"),
		@NamedQuery(name = "findGenotyp700ByAl1", query = "select myGenotyp700 from Genotyp700 myGenotyp700 where myGenotyp700.al1 = ?1"),
		@NamedQuery(name = "findGenotyp700ByAl2", query = "select myGenotyp700 from Genotyp700 myGenotyp700 where myGenotyp700.al2 = ?1"),
		@NamedQuery(name = "findGenotyp700ByPrimaryKey", query = "select myGenotyp700 from Genotyp700 myGenotyp700 where myGenotyp700.varId = ?1 and myGenotyp700.snpId = ?2"),
		@NamedQuery(name = "findGenotyp700ByQs", query = "select myGenotyp700 from Genotyp700 myGenotyp700 where myGenotyp700.qs = ?1"),
		@NamedQuery(name = "findGenotyp700BySnpId", query = "select myGenotyp700 from Genotyp700 myGenotyp700 where myGenotyp700.snpId = ?1"),
		@NamedQuery(name = "findGenotyp700ByTyp", query = "select myGenotyp700 from Genotyp700 myGenotyp700 where myGenotyp700.typ = ?1"),
		@NamedQuery(name = "findGenotyp700ByTypContaining", query = "select myGenotyp700 from Genotyp700 myGenotyp700 where myGenotyp700.typ like ?1"),
		@NamedQuery(name = "findGenotyp700ByVarId", query = "select myGenotyp700 from Genotyp700 myGenotyp700 where myGenotyp700.varId = ?1") })
@Table(schema = "NICKA", name = "GENOTYP700")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "dev_crud_maker/org/irri/iric/portal/variety/domain", name = "Genotyp700")
public class Genotyp700 implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "VAR_ID")
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer varId;
	/**
	 */

	@Column(name = "SNP_ID")
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer snpId;
	/**
	 */

	@Column(name = "AL1")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Boolean al1;
	/**
	 */

	@Column(name = "AL2")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Boolean al2;
	/**
	 */

	@Column(name = "QS")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer qs;
	/**
	 */

	@Column(name = "TYP", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String typ;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "SNP_ID", referencedColumnName = "ID", insertable = false, updatable = false) })
	@XmlTransient
	Snp700 snp700;
	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "VAR_ID", referencedColumnName = "ID", insertable = false, updatable = false) })
	@XmlTransient
	Var700 var700;

	/**
	 */
	public void setVarId(Integer varId) {
		this.varId = varId;
	}

	/**
	 */
	public Integer getVarId() {
		return this.varId;
	}

	/**
	 */
	public void setSnpId(Integer snpId) {
		this.snpId = snpId;
	}

	/**
	 */
	public Integer getSnpId() {
		return this.snpId;
	}

	/**
	 */
	public void setAl1(Boolean al1) {
		this.al1 = al1;
	}

	/**
	 */
	public Boolean getAl1() {
		return this.al1;
	}

	/**
	 */
	public void setAl2(Boolean al2) {
		this.al2 = al2;
	}

	/**
	 */
	public Boolean getAl2() {
		return this.al2;
	}

	/**
	 */
	public void setQs(Integer qs) {
		this.qs = qs;
	}

	/**
	 */
	public Integer getQs() {
		return this.qs;
	}

	/**
	 */
	public void setTyp(String typ) {
		this.typ = typ;
	}

	/**
	 */
	public String getTyp() {
		return this.typ;
	}

	/**
	 */
	public void setSnp700(Snp700 snp700) {
		this.snp700 = snp700;
	}

	/**
	 */
	@JsonIgnore
	public Snp700 getSnp700() {
		return snp700;
	}

	/**
	 */
	public void setVar700(Var700 var700) {
		this.var700 = var700;
	}

	/**
	 */
	@JsonIgnore
	public Var700 getVar700() {
		return var700;
	}

	/**
	 */
	public Genotyp700() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Genotyp700 that) {
		setVarId(that.getVarId());
		setSnpId(that.getSnpId());
		setAl1(that.getAl1());
		setAl2(that.getAl2());
		setQs(that.getQs());
		setTyp(that.getTyp());
		setSnp700(that.getSnp700());
		setVar700(that.getVar700());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("varId=[").append(varId).append("] ");
		buffer.append("snpId=[").append(snpId).append("] ");
		buffer.append("al1=[").append(al1).append("] ");
		buffer.append("al2=[").append(al2).append("] ");
		buffer.append("qs=[").append(qs).append("] ");
		buffer.append("typ=[").append(typ).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((varId == null) ? 0 : varId.hashCode()));
		result = (int) (prime * result + ((snpId == null) ? 0 : snpId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Genotyp700))
			return false;
		Genotyp700 equalCheck = (Genotyp700) obj;
		if ((varId == null && equalCheck.varId != null) || (varId != null && equalCheck.varId == null))
			return false;
		if (varId != null && !varId.equals(equalCheck.varId))
			return false;
		if ((snpId == null && equalCheck.snpId != null) || (snpId != null && equalCheck.snpId == null))
			return false;
		if (snpId != null && !snpId.equals(equalCheck.snpId))
			return false;
		return true;
	}
}
