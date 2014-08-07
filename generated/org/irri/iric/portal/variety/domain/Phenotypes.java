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
@IdClass(org.irri.iric.portal.variety.domain.PhenotypesPK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllPhenotypess", query = "select myPhenotypes from Phenotypes myPhenotypes"),
		@NamedQuery(name = "findPhenotypesByNsftvId", query = "select myPhenotypes from Phenotypes myPhenotypes where myPhenotypes.nsftvId = ?1"),
		@NamedQuery(name = "findPhenotypesByPrimaryKey", query = "select myPhenotypes from Phenotypes myPhenotypes where myPhenotypes.nsftvId = ?1 and myPhenotypes.trait = ?2"),
		@NamedQuery(name = "findPhenotypesByTrait", query = "select myPhenotypes from Phenotypes myPhenotypes where myPhenotypes.trait = ?1"),
		@NamedQuery(name = "findPhenotypesByTraitContaining", query = "select myPhenotypes from Phenotypes myPhenotypes where myPhenotypes.trait like ?1"),
		@NamedQuery(name = "findPhenotypesByVal", query = "select myPhenotypes from Phenotypes myPhenotypes where myPhenotypes.val = ?1") })
@Table(schema = "NICKA", name = "PHENOTYPES")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "dev_crud_maker/org/irri/iric/portal/variety/domain", name = "Phenotypes")
public class Phenotypes implements Serializable , Comparable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "NSFTV_ID")
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer nsftvId;
	/**
	 */

	@Column(name = "TRAIT", length = 32)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String trait;
	/**
	 */

	@Column(name = "VAL")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer val;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "NSFTV_ID", referencedColumnName = "NSFTV_ID", insertable = false, updatable = false) })
	@XmlTransient
	Germplasm germplasm;

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
	public void setTrait(String trait) {
		this.trait = trait;
	}

	/**
	 */
	public String getTrait() {
		return this.trait;
	}

	/**
	 */
	public void setVal(Integer val) {
		this.val = val;
	}

	/**
	 */
	public Integer getVal() {
		return this.val;
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
	public Phenotypes() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Phenotypes that) {
		setNsftvId(that.getNsftvId());
		setTrait(that.getTrait());
		setVal(that.getVal());
		setGermplasm(that.getGermplasm());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("nsftvId=[").append(nsftvId).append("] ");
		buffer.append("trait=[").append(trait).append("] ");
		buffer.append("val=[").append(val).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((nsftvId == null) ? 0 : nsftvId.hashCode()));
		result = (int) (prime * result + ((trait == null) ? 0 : trait.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Phenotypes))
			return false;
		Phenotypes equalCheck = (Phenotypes) obj;
		if ((nsftvId == null && equalCheck.nsftvId != null) || (nsftvId != null && equalCheck.nsftvId == null))
			return false;
		if (nsftvId != null && !nsftvId.equals(equalCheck.nsftvId))
			return false;
		if ((trait == null && equalCheck.trait != null) || (trait != null && equalCheck.trait == null))
			return false;
		if (trait != null && !trait.equals(equalCheck.trait))
			return false;
		return true;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return this.trait.compareToIgnoreCase(  ((Phenotypes)o).trait  );
		//return 0;
	}
	
	
}
