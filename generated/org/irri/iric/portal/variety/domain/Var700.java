package org.irri.iric.portal.variety.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.LinkedHashSet;
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
		@NamedQuery(name = "findAllVar700s", query = "select myVar700 from Var700 myVar700"),
		@NamedQuery(name = "findVar700ById", query = "select myVar700 from Var700 myVar700 where myVar700.id = ?1"),
		@NamedQuery(name = "findVar700ByName", query = "select myVar700 from Var700 myVar700 where myVar700.name = ?1"),
		@NamedQuery(name = "findVar700ByNameContaining", query = "select myVar700 from Var700 myVar700 where myVar700.name like ?1"),
		@NamedQuery(name = "findVar700ByPrimaryKey", query = "select myVar700 from Var700 myVar700 where myVar700.id = ?1") })
@Table(schema = "NICKA", name = "VAR700")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "dev_crud_maker/org/irri/iric/portal/variety/domain", name = "Var700")
@XmlRootElement(namespace = "dev_crud_maker/org/irri/iric/portal/variety/domain")
public class Var700 implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer id;
	/**
	 */

	@Column(name = "NAME", length = 64)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;

	/**
	 */
	@OneToMany(mappedBy = "var700", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<org.irri.iric.portal.variety.domain.Genotyp700> genotyp700s;

	/**
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 */
	public String getName() {
		return this.name;
	}

	/**
	 */
	public void setGenotyp700s(Set<Genotyp700> genotyp700s) {
		this.genotyp700s = genotyp700s;
	}

	/**
	 */
	@JsonIgnore
	public Set<Genotyp700> getGenotyp700s() {
		if (genotyp700s == null) {
			genotyp700s = new java.util.LinkedHashSet<org.irri.iric.portal.variety.domain.Genotyp700>();
		}
		return genotyp700s;
	}

	/**
	 */
	public Var700() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Var700 that) {
		setId(that.getId());
		setName(that.getName());
		setGenotyp700s(new java.util.LinkedHashSet<org.irri.iric.portal.variety.domain.Genotyp700>(that.getGenotyp700s()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("name=[").append(name).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((id == null) ? 0 : id.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Var700))
			return false;
		Var700 equalCheck = (Var700) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
