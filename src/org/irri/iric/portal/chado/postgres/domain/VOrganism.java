package org.irri.iric.portal.chado.postgres.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVOrganisms", query = "select myVOrganism from VOrganism myVOrganism"),
		@NamedQuery(name = "findVOrganismByAbbreviation", query = "select myVOrganism from VOrganism myVOrganism where myVOrganism.abbreviation = ?1"),
		@NamedQuery(name = "findVOrganismByAbbreviationContaining", query = "select myVOrganism from VOrganism myVOrganism where myVOrganism.abbreviation like ?1"),
		@NamedQuery(name = "findVOrganismByComment", query = "select myVOrganism from VOrganism myVOrganism where myVOrganism.comment = ?1"),
		@NamedQuery(name = "findVOrganismByCommentContaining", query = "select myVOrganism from VOrganism myVOrganism where myVOrganism.comment like ?1"),
		@NamedQuery(name = "findVOrganismByCommonName", query = "select myVOrganism from VOrganism myVOrganism where myVOrganism.commonName = ?1"),
		@NamedQuery(name = "findVOrganismByCommonNameContaining", query = "select myVOrganism from VOrganism myVOrganism where myVOrganism.commonName like ?1"),
		@NamedQuery(name = "findVOrganismByGenus", query = "select myVOrganism from VOrganism myVOrganism where myVOrganism.genus = ?1"),
		@NamedQuery(name = "findVOrganismByGenusContaining", query = "select myVOrganism from VOrganism myVOrganism where myVOrganism.genus like ?1"),
		@NamedQuery(name = "findVOrganismByOrganismId", query = "select myVOrganism from VOrganism myVOrganism where myVOrganism.organismId = ?1"),
		@NamedQuery(name = "findVOrganismByPrimaryKey", query = "select myVOrganism from VOrganism myVOrganism where myVOrganism.organismId = ?1"),
		@NamedQuery(name = "findVOrganismBySpecies", query = "select myVOrganism from VOrganism myVOrganism where myVOrganism.species = ?1"),
		@NamedQuery(name = "findVOrganismBySpeciesContaining", query = "select myVOrganism from VOrganism myVOrganism where myVOrganism.species like ?1") })
@Table(schema = "public", name = "v_organism")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/postgres/domain", name = "VOrganism")
public class VOrganism implements Serializable,  org.irri.iric.portal.domain.Organism {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "organism_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer organismId;
	/**
	 */

	@Column(name = "abbreviation")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String abbreviation;
	/**
	 */

	@Column(name = "genus")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String genus;
	/**
	 */

	@Column(name = "species")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String species;
	/**
	 */

	@Column(name = "common_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String commonName;
	/**
	 */

	@Column(name = "comment")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String comment;

	/**
	 */
	public void setOrganismId(Integer organismId) {
		this.organismId = organismId;
	}

	/**
	 */
	public BigDecimal getOrganismId() {
		return BigDecimal.valueOf(this.organismId);
	}

	/**
	 */
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	/**
	 */
	public String getAbbreviation() {
		return this.abbreviation;
	}

	/**
	 */
	public void setGenus(String genus) {
		this.genus = genus;
	}

	/**
	 */
	public String getGenus() {
		return this.genus;
	}

	/**
	 */
	public void setSpecies(String species) {
		this.species = species;
	}

	/**
	 */
	public String getSpecies() {
		return this.species;
	}

	/**
	 */
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	/**
	 */
	public String getCommonName() {
		return this.commonName;
	}

	/**
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 */
	public String getComment() {
		return this.comment;
	}

	/**
	 */
	public VOrganism() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VOrganism that) {
		setOrganismId(that.getOrganismId().intValue());
		setAbbreviation(that.getAbbreviation());
		setGenus(that.getGenus());
		setSpecies(that.getSpecies());
		setCommonName(that.getCommonName());
		setComment(that.getComment());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("organismId=[").append(organismId).append("] ");
		buffer.append("abbreviation=[").append(abbreviation).append("] ");
		buffer.append("genus=[").append(genus).append("] ");
		buffer.append("species=[").append(species).append("] ");
		buffer.append("commonName=[").append(commonName).append("] ");
		buffer.append("comment=[").append(comment).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((organismId == null) ? 0 : organismId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VOrganism))
			return false;
		VOrganism equalCheck = (VOrganism) obj;
		if ((organismId == null && equalCheck.organismId != null) || (organismId != null && equalCheck.organismId == null))
			return false;
		if (organismId != null && !organismId.equals(equalCheck.organismId))
			return false;
		return true;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.getCommonName();
	}
	
	
}
