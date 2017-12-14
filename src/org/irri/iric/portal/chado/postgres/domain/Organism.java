package org.irri.iric.portal.chado.postgres.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.math.BigDecimal;

import javax.xml.bind.annotation.*;
import javax.persistence.*;

/**
 * The organismal taxonomic classification. Note that phylogenies are represented using the phylogeny module, and taxonomies can be represented using the cvterm module or the phylogeny module.
 * 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "PGfindAllOrganisms", query = "select myOrganism from Organism myOrganism"),
		@NamedQuery(name = "PGfindOrganismByAbbreviation", query = "select myOrganism from Organism myOrganism where myOrganism.abbreviation = ?1"),
		@NamedQuery(name = "PGfindOrganismByAbbreviationContaining", query = "select myOrganism from Organism myOrganism where myOrganism.abbreviation like ?1"),
		@NamedQuery(name = "PGfindOrganismByComment", query = "select myOrganism from Organism myOrganism where myOrganism.comment = ?1"),
		@NamedQuery(name = "PGfindOrganismByCommentContaining", query = "select myOrganism from Organism myOrganism where myOrganism.comment like ?1"),
		@NamedQuery(name = "PGfindOrganismByCommonName", query = "select myOrganism from Organism myOrganism where myOrganism.commonName = ?1"),
		@NamedQuery(name = "PGfindOrganismByCommonNameContaining", query = "select myOrganism from Organism myOrganism where myOrganism.commonName like ?1"),
		@NamedQuery(name = "PGfindOrganismByGenus", query = "select myOrganism from Organism myOrganism where myOrganism.genus = ?1"),
		@NamedQuery(name = "PGfindOrganismByGenusContaining", query = "select myOrganism from Organism myOrganism where myOrganism.genus like ?1"),
		@NamedQuery(name = "PGfindOrganismByOrganismId", query = "select myOrganism from Organism myOrganism where myOrganism.organismId = ?1"),
		@NamedQuery(name = "PGfindOrganismByPrimaryKey", query = "select myOrganism from Organism myOrganism where myOrganism.organismId = ?1"),
		@NamedQuery(name = "PGfindOrganismBySpecies", query = "select myOrganism from Organism myOrganism where myOrganism.species = ?1"),
		@NamedQuery(name = "PGfindOrganismBySpeciesContaining", query = "select myOrganism from Organism myOrganism where myOrganism.species like ?1") })
@Table(schema = "public", name = "v_organism")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/postgres/domain", name = "Organism")
@XmlRootElement(namespace = "iric_prod_crud/org/irri/iric/portal/chado/postgres/domain")
public class Organism implements Serializable,   org.irri.iric.portal.domain.Organism  {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "organism_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Long organismId;
	/**
	 */

	@Column(name = "abbreviation")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String abbreviation;
	/**
	 */

	@Column(name = "genus", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String genus;
	/**
	 * A type of organism is always uniquely identified by genus and species. When mapping from the NCBI taxonomy names.dmp file, this column must be used where it is present, as the common_name column is not always unique (e.g. environmental samples). If a particular strain or subspecies is to be represented, this is appended onto the species name. Follows standard NCBI taxonomy pattern.
	 * 
	 */

	@Column(name = "species", nullable = false)
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
	@OneToMany(mappedBy = "organism", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<org.irri.iric.portal.chado.postgres.domain.Organismprop> organismprops;
	/**
	 */
	@OneToMany(mappedBy = "organism", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<org.irri.iric.portal.chado.postgres.domain.OrganismDbxref> organismDbxrefs;

	/**
	 */
	public void setOrganismId(Long organismId) {
		this.organismId = organismId;
	}

	
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
	 * A type of organism is always uniquely identified by genus and species. When mapping from the NCBI taxonomy names.dmp file, this column must be used where it is present, as the common_name column is not always unique (e.g. environmental samples). If a particular strain or subspecies is to be represented, this is appended onto the species name. Follows standard NCBI taxonomy pattern.
	 * 
	 */
	public void setSpecies(String species) {
		this.species = species;
	}

	/**
	 * A type of organism is always uniquely identified by genus and species. When mapping from the NCBI taxonomy names.dmp file, this column must be used where it is present, as the common_name column is not always unique (e.g. environmental samples). If a particular strain or subspecies is to be represented, this is appended onto the species name. Follows standard NCBI taxonomy pattern.
	 * 
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
	public void setOrganismprops(Set<Organismprop> organismprops) {
		this.organismprops = organismprops;
	}

	/**
	 */
	@JsonIgnore
	public Set<Organismprop> getOrganismprops() {
		if (organismprops == null) {
			organismprops = new java.util.LinkedHashSet<org.irri.iric.portal.chado.postgres.domain.Organismprop>();
		}
		return organismprops;
	}

	/**
	 */
	public void setOrganismDbxrefs(Set<OrganismDbxref> organismDbxrefs) {
		this.organismDbxrefs = organismDbxrefs;
	}

	/**
	 */
	@JsonIgnore
	public Set<OrganismDbxref> getOrganismDbxrefs() {
		if (organismDbxrefs == null) {
			organismDbxrefs = new java.util.LinkedHashSet<org.irri.iric.portal.chado.postgres.domain.OrganismDbxref>();
		}
		return organismDbxrefs;
	}

	/**
	 */
	public Organism() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Organism that) {
		setOrganismId(that.getOrganismId().longValue());
		setAbbreviation(that.getAbbreviation());
		setGenus(that.getGenus());
		setSpecies(that.getSpecies());
		setCommonName(that.getCommonName());
		setComment(that.getComment());
		setOrganismprops(new java.util.LinkedHashSet<org.irri.iric.portal.chado.postgres.domain.Organismprop>(that.getOrganismprops()));
		setOrganismDbxrefs(new java.util.LinkedHashSet<org.irri.iric.portal.chado.postgres.domain.OrganismDbxref>(that.getOrganismDbxrefs()));
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
		if (!(obj instanceof Organism))
			return false;
		Organism equalCheck = (Organism) obj;
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
		//return this.getAbbreviation();
	}

}
