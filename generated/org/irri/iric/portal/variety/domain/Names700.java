package org.irri.iric.portal.variety.domain;

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
		@NamedQuery(name = "findAllNames700s", query = "select myNames700 from Names700 myNames700"),
		@NamedQuery(name = "findNames700ByAltId", query = "select myNames700 from Names700 myNames700 where myNames700.altId = ?1"),
		@NamedQuery(name = "findNames700ByAltIdContaining", query = "select myNames700 from Names700 myNames700 where myNames700.altId like ?1"),
		@NamedQuery(name = "findNames700ByAssayId", query = "select myNames700 from Names700 myNames700 where myNames700.assayId = ?1"),
		@NamedQuery(name = "findNames700ByAssayIdContaining", query = "select myNames700 from Names700 myNames700 where myNames700.assayId like ?1"),
		@NamedQuery(name = "findNames700ById", query = "select myNames700 from Names700 myNames700 where myNames700.id = ?1"),
		@NamedQuery(name = "findNames700ByIdContaining", query = "select myNames700 from Names700 myNames700 where myNames700.id like ?1"),
		@NamedQuery(name = "findNames700ByPrimaryKey", query = "select myNames700 from Names700 myNames700 where myNames700.id = ?1"),
		@NamedQuery(name = "findNames700BySampleId", query = "select myNames700 from Names700 myNames700 where myNames700.sampleId = ?1"),
		@NamedQuery(name = "findNames700BySampleIdContaining", query = "select myNames700 from Names700 myNames700 where myNames700.sampleId like ?1") })
@Table(schema = "NICKA", name = "NAMES700")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "dev_crud_maker/org/irri/iric/portal/variety/domain", name = "Names700")
public class Names700 implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "ID", length = 64)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String id;
	/**
	 */

	@Column(name = "ASSAY_ID", length = 64)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String assayId;
	/**
	 */

	@Column(name = "SAMPLE_ID", length = 64)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String sampleId;
	/**
	 */

	@Column(name = "ALT_ID", length = 64)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String altId;

	/**
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 */
	public String getId() {
		return this.id;
	}

	/**
	 */
	public void setAssayId(String assayId) {
		this.assayId = assayId;
	}

	/**
	 */
	public String getAssayId() {
		return this.assayId;
	}

	/**
	 */
	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

	/**
	 */
	public String getSampleId() {
		return this.sampleId;
	}

	/**
	 */
	public void setAltId(String altId) {
		this.altId = altId;
	}

	/**
	 */
	public String getAltId() {
		return this.altId;
	}

	/**
	 */
	public Names700() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Names700 that) {
		setId(that.getId());
		setAssayId(that.getAssayId());
		setSampleId(that.getSampleId());
		setAltId(that.getAltId());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("assayId=[").append(assayId).append("] ");
		buffer.append("sampleId=[").append(sampleId).append("] ");
		buffer.append("altId=[").append(altId).append("] ");

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
		if (!(obj instanceof Names700))
			return false;
		Names700 equalCheck = (Names700) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
