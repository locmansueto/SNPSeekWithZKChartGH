package org.irri.iric.portal.variety.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.irri.iric.portal.domain.Variety;

import javax.xml.bind.annotation.*;
import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllGermplasms", query = "select myGermplasm from Germplasm myGermplasm"),
		@NamedQuery(name = "findGermplasmByAccession", query = "select myGermplasm from Germplasm myGermplasm where upper(myGermplasm.accession) = upper(?1)"),
		@NamedQuery(name = "findGermplasmByAccessionContaining", query = "select myGermplasm from Germplasm myGermplasm where myGermplasm.accession like ?1"),
		@NamedQuery(name = "findGermplasmByCountry", query = "select myGermplasm from Germplasm myGermplasm where myGermplasm.country = ?1"),
		@NamedQuery(name = "findGermplasmByCountryContaining", query = "select myGermplasm from Germplasm myGermplasm where myGermplasm.country like ?1"),
		@NamedQuery(name = "findGermplasmByLatitude", query = "select myGermplasm from Germplasm myGermplasm where myGermplasm.latitude = ?1"),
		@NamedQuery(name = "findGermplasmByLongitude", query = "select myGermplasm from Germplasm myGermplasm where myGermplasm.longitude = ?1"),
		@NamedQuery(name = "findGermplasmByNsftvId", query = "select myGermplasm from Germplasm myGermplasm where myGermplasm.nsftvId = ?1"),
		@NamedQuery(name = "findGermplasmByPrimaryKey", query = "select myGermplasm from Germplasm myGermplasm where myGermplasm.nsftvId = ?1"),
		@NamedQuery(name = "findGermplasmBySubpopulation", query = "select myGermplasm from Germplasm myGermplasm where myGermplasm.subpopulation = ?1"),
		@NamedQuery(name = "findGermplasmBySubpopulationContaining", query = "select myGermplasm from Germplasm myGermplasm where myGermplasm.subpopulation like ?1") })
@Table(schema = "NICKA", name = "GERMPLASM")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "dev_crud_maker/org/irri/iric/portal/variety/domain", name = "Germplasm")
@XmlRootElement(namespace = "dev_crud_maker/org/irri/iric/portal/variety/domain")
public class Germplasm implements Serializable, Comparable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "NSFTV_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer nsftvId;
	/**
	 */

	@Column(name = "ACCESSION", length = 32)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String accession;
	/**
	 */

	@Column(name = "COUNTRY", length = 32)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String country;
	/**
	 */

	@Column(name = "LATITUDE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer latitude;
	/**
	 */

	@Column(name = "LONGITUDE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer longitude;
	/**
	 */

	@Column(name = "SUBPOPULATION", length = 16)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String subpopulation;

	/**
	 */
	@OneToMany(mappedBy = "germplasm", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<org.irri.iric.portal.variety.domain.Genotyping> genotypings;
	/**
	 */
	@OneToMany(mappedBy = "germplasm", cascade = { CascadeType.REMOVE }, fetch = FetchType.EAGER)
	@XmlElement(name = "", namespace = "")
	java.util.Set<org.irri.iric.portal.variety.domain.Phenotypes> phenotypeses;

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
	public void setAccession(String accession) {
		this.accession = accession;
	}

	/**
	 */
	public String getAccession() {
		return this.accession;
	}

	/**
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 */
	public String getCountry() {
		return this.country;
	}

	/**
	 */
	public void setLatitude(Integer latitude) {
		this.latitude = latitude;
	}

	/**
	 */
	public Integer getLatitude() {
		return this.latitude;
	}

	/**
	 */
	public void setLongitude(Integer longitude) {
		this.longitude = longitude;
	}

	/**
	 */
	public Integer getLongitude() {
		return this.longitude;
	}

	/**
	 */
	public void setSubpopulation(String subpopulation) {
		this.subpopulation = subpopulation;
	}

	/**
	 */
	public String getSubpopulation() {
		return this.subpopulation;
	}

	/**
	 */
	public void setGenotypings(Set<Genotyping> genotypings) {
		this.genotypings = genotypings;
	}

	/**
	 */
	@JsonIgnore
	public Set<Genotyping> getGenotypings() {
		if (genotypings == null) {
			genotypings = new java.util.LinkedHashSet<org.irri.iric.portal.variety.domain.Genotyping>();
		}
		return genotypings;
	}

	/**
	 */
	public void setPhenotypeses(Set<Phenotypes> phenotypeses) {
		this.phenotypeses = phenotypeses;
	}

	/**
	 */
	@OneToMany(fetch=FetchType.EAGER)
	@JsonIgnore
	public Set<Phenotypes> getPhenotypeses() {
		if (phenotypeses == null) {
			phenotypeses = new java.util.LinkedHashSet<org.irri.iric.portal.variety.domain.Phenotypes>();
		}
		return phenotypeses;
	}

	/**
	 */
	public Germplasm() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Germplasm that) {
		setNsftvId(that.getNsftvId());
		setAccession(that.getAccession());
		setCountry(that.getCountry());
		setLatitude(that.getLatitude());
		setLongitude(that.getLongitude());
		setSubpopulation(that.getSubpopulation());
		setGenotypings(new java.util.LinkedHashSet<org.irri.iric.portal.variety.domain.Genotyping>(that.getGenotypings()));
		setPhenotypeses(new java.util.LinkedHashSet<org.irri.iric.portal.variety.domain.Phenotypes>(that.getPhenotypeses()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("nsftvId=[").append(nsftvId).append("] ");
		buffer.append("accession=[").append(accession).append("] ");
		buffer.append("country=[").append(country).append("] ");
		buffer.append("latitude=[").append(latitude).append("] ");
		buffer.append("longitude=[").append(longitude).append("] ");
		buffer.append("subpopulation=[").append(subpopulation).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((nsftvId == null) ? 0 : nsftvId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Germplasm))
			return false;
		Germplasm equalCheck = (Germplasm) obj;
		if ((nsftvId == null && equalCheck.nsftvId != null) || (nsftvId != null && equalCheck.nsftvId == null))
			return false;
		if (nsftvId != null && !nsftvId.equals(equalCheck.nsftvId))
			return false;
		return true;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		
		return  this.getAccession().compareToIgnoreCase( ((Germplasm)o).getAccession()) ;
		
	}
	
	
	
}
