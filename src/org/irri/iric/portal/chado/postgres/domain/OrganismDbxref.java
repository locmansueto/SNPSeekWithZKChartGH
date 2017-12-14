package org.irri.iric.portal.chado.postgres.domain;

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

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllOrganismDbxrefs", query = "select myOrganismDbxref from OrganismDbxref myOrganismDbxref"),
		@NamedQuery(name = "findOrganismDbxrefByPrimaryKey", query = "select myOrganismDbxref from OrganismDbxref myOrganismDbxref where myOrganismDbxref.organismDbxrefId = ?1") })
@Table(schema = "public", name = "organism_dbxref")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/postgres/domain", name = "OrganismDbxref")
public class OrganismDbxref implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "organism_dbxref_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer organismDbxrefId;
	/**
	 */

	@Column(name = "dbxref_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer dbxrefId;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "organism_id", referencedColumnName = "organism_id", nullable = false) })
	@XmlTransient
	Organism organism;

	/**
	 */
	public void setOrganismDbxrefId(Integer organismDbxrefId) {
		this.organismDbxrefId = organismDbxrefId;
	}

	/**
	 */
	public Integer getOrganismDbxrefId() {
		return this.organismDbxrefId;
	}

	/**
	 */
	public void setDbxrefId(Integer dbxrefId) {
		this.dbxrefId = dbxrefId;
	}

	/**
	 */
	public Integer getDbxrefId() {
		return this.dbxrefId;
	}

	/**
	 */
	public void setOrganism(Organism organism) {
		this.organism = organism;
	}

	/**
	 */
	@JsonIgnore
	public Organism getOrganism() {
		return organism;
	}

	/**
	 */
	public OrganismDbxref() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(OrganismDbxref that) {
		setOrganismDbxrefId(that.getOrganismDbxrefId());
		setDbxrefId(that.getDbxrefId());
		setOrganism(that.getOrganism());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("organismDbxrefId=[").append(organismDbxrefId).append("] ");
		buffer.append("dbxrefId=[").append(dbxrefId).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((organismDbxrefId == null) ? 0 : organismDbxrefId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof OrganismDbxref))
			return false;
		OrganismDbxref equalCheck = (OrganismDbxref) obj;
		if ((organismDbxrefId == null && equalCheck.organismDbxrefId != null) || (organismDbxrefId != null && equalCheck.organismDbxrefId == null))
			return false;
		if (organismDbxrefId != null && !organismDbxrefId.equals(equalCheck.organismDbxrefId))
			return false;
		return true;
	}
}
