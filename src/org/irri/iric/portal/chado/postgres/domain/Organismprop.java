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
 * Tag-value properties - follows standard chado model.
 * 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllOrganismprops", query = "select myOrganismprop from Organismprop myOrganismprop"),
		@NamedQuery(name = "findOrganismpropByPrimaryKey", query = "select myOrganismprop from Organismprop myOrganismprop where myOrganismprop.organismpropId = ?1") })
@Table(schema = "public", name = "organismprop")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/postgres/domain", name = "Organismprop")
public class Organismprop implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "organismprop_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer organismpropId;
	/**
	 */

	@Column(name = "type_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer typeId;
	/**
	 */

	@Column(name = "value")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String value;
	/**
	 */

	@Column(name = "rank", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer rank;

	/**
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "organism_id", referencedColumnName = "organism_id", nullable = false) })
	@XmlTransient
	Organism organism;

	/**
	 */
	public void setOrganismpropId(Integer organismpropId) {
		this.organismpropId = organismpropId;
	}

	/**
	 */
	public Integer getOrganismpropId() {
		return this.organismpropId;
	}

	/**
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	/**
	 */
	public Integer getTypeId() {
		return this.typeId;
	}

	/**
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 */
	public void setRank(Integer rank) {
		this.rank = rank;
	}

	/**
	 */
	public Integer getRank() {
		return this.rank;
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
	public Organismprop() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Organismprop that) {
		setOrganismpropId(that.getOrganismpropId());
		setTypeId(that.getTypeId());
		setValue(that.getValue());
		setRank(that.getRank());
		setOrganism(that.getOrganism());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("organismpropId=[").append(organismpropId).append("] ");
		buffer.append("typeId=[").append(typeId).append("] ");
		buffer.append("value=[").append(value).append("] ");
		buffer.append("rank=[").append(rank).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((organismpropId == null) ? 0 : organismpropId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Organismprop))
			return false;
		Organismprop equalCheck = (Organismprop) obj;
		if ((organismpropId == null && equalCheck.organismpropId != null) || (organismpropId != null && equalCheck.organismpropId == null))
			return false;
		if (organismpropId != null && !organismpropId.equals(equalCheck.organismpropId))
			return false;
		return true;
	}
}
