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
 * A controlled vocabulary or ontology. A cv is composed of cvterms (AKA terms, classes, types, universals - relations and properties are also stored in cvterm) and the relationships between them.
 * 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllCvs", query = "select myCv from Cv myCv"),
		@NamedQuery(name = "findCvByCvId", query = "select myCv from Cv myCv where myCv.cvId = ?1"),
		@NamedQuery(name = "findCvByDefinition", query = "select myCv from Cv myCv where myCv.definition = ?1"),
		@NamedQuery(name = "findCvByDefinitionContaining", query = "select myCv from Cv myCv where myCv.definition like ?1"),
		@NamedQuery(name = "findCvByName", query = "select myCv from Cv myCv where myCv.name = ?1"),
		@NamedQuery(name = "findCvByNameContaining", query = "select myCv from Cv myCv where myCv.name like ?1"),
		@NamedQuery(name = "findCvByPrimaryKey", query = "select myCv from Cv myCv where myCv.cvId = ?1") })
@Table(schema = "public", name = "v_cv")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/postgres/domain", name = "Cv")
public class Cv implements Serializable, org.irri.iric.portal.domain.Cv {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "cv_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer cvId;
	/**
	 * The name of the ontology. This corresponds to the obo-format -namespace-. cv names uniquely identify the cv. In OBO file format, the cv.name is known as the namespace.
	 * 
	 */

	@Column(name = "name", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 * A text description of the criteria for membership of this ontology.
	 * 
	 */

	@Column(name = "definition")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String definition;

	/**
	 */
	public void setCvId(Integer cvId) {
		this.cvId = cvId;
	}

	/**
	 */
	public BigDecimal getCvId() {
		return BigDecimal.valueOf(cvId);
	}

	/**
	 * The name of the ontology. This corresponds to the obo-format -namespace-. cv names uniquely identify the cv. In OBO file format, the cv.name is known as the namespace.
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * The name of the ontology. This corresponds to the obo-format -namespace-. cv names uniquely identify the cv. In OBO file format, the cv.name is known as the namespace.
	 * 
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * A text description of the criteria for membership of this ontology.
	 * 
	 */
	public void setDefinition(String definition) {
		this.definition = definition;
	}

	/**
	 * A text description of the criteria for membership of this ontology.
	 * 
	 */
	public String getDefinition() {
		return this.definition;
	}

	/**
	 */
	public Cv() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Cv that) {
		setCvId(that.getCvId().intValue());
		setName(that.getName());
		setDefinition(that.getDefinition());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("cvId=[").append(cvId).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("definition=[").append(definition).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((cvId == null) ? 0 : cvId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Cv))
			return false;
		Cv equalCheck = (Cv) obj;
		if ((cvId == null && equalCheck.cvId != null) || (cvId != null && equalCheck.cvId == null))
			return false;
		if (cvId != null && !cvId.equals(equalCheck.cvId))
			return false;
		return true;
	}
}
