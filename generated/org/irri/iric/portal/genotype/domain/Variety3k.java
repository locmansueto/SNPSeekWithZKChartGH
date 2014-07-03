package org.irri.iric.portal.genotype.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVariety3ks", query = "select myVariety3k from Variety3k myVariety3k"),
		@NamedQuery(name = "findVariety3kByPrimaryKey", query = "select myVariety3k from Variety3k myVariety3k where myVariety3k.varname = ?1"),
		@NamedQuery(name = "findVariety3kByVarname", query = "select myVariety3k from Variety3k myVariety3k where myVariety3k.varname = ?1"),
		@NamedQuery(name = "findVariety3kByVarnameContaining", query = "select myVariety3k from Variety3k myVariety3k where myVariety3k.varname like ?1") })
@Table(schema = "LMANSUETO", name = "VARIETY3K")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "zkspring6/org/irri/iric/portal/genotype/domain", name = "Variety3k")
public class Variety3k implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "VARNAME", length = 128, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String varname;

	/**
	 */
	public void setVarname(String varname) {
		this.varname = varname;
	}

	/**
	 */
	public String getVarname() {
		return this.varname;
	}

	/**
	 */
	public Variety3k() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Variety3k that) {
		setVarname(that.getVarname());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("varname=[").append(varname).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((varname == null) ? 0 : varname.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Variety3k))
			return false;
		Variety3k equalCheck = (Variety3k) obj;
		if ((varname == null && equalCheck.varname != null) || (varname != null && equalCheck.varname == null))
			return false;
		if (varname != null && !varname.equals(equalCheck.varname))
			return false;
		return true;
	}
}
