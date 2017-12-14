package org.irri.iric.portal.chado.postgres.domain;

import java.io.Serializable;
import java.lang.StringBuilder;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.CvTermUniqueValues;

/**
 */
@IdClass(org.irri.iric.portal.chado.postgres.domain.VIricstockPassportValuesPK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVIricstockPassportValuess", query = "select myVIricstockPassportValues from VIricstockPassportValues myVIricstockPassportValues"),
		@NamedQuery(name = "findVIricstockPassportValuesByPrimaryKey", query = "select myVIricstockPassportValues from VIricstockPassportValues myVIricstockPassportValues where myVIricstockPassportValues.typeId = ?1 and myVIricstockPassportValues.value = ?2"),
		@NamedQuery(name = "findVIricstockPassportValuesByTypeId", query = "select myVIricstockPassportValues from VIricstockPassportValues myVIricstockPassportValues where myVIricstockPassportValues.typeId = ?1"),
		@NamedQuery(name = "findVIricstockPassportValuesByValue", query = "select myVIricstockPassportValues from VIricstockPassportValues myVIricstockPassportValues where myVIricstockPassportValues.value = ?1"),
		@NamedQuery(name = "findVIricstockPassportValuesByValueContaining", query = "select myVIricstockPassportValues from VIricstockPassportValues myVIricstockPassportValues where myVIricstockPassportValues.value like ?1") })
@Table(schema = "public", name = "v_iricstock_passport_values")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/postgres/domain", name = "VIricstockPassportValues")
public class VIricstockPassportValues implements Serializable, CvTermUniqueValues {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "type_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer typeId;
	/**
	 */

	@Column(name = "value", length = 4000, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String value;

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
	public VIricstockPassportValues() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VIricstockPassportValues that) {
		setTypeId(that.getTypeId());
		setValue(that.getValue());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("typeId=[").append(typeId).append("] ");
		buffer.append("value=[").append(value).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((typeId == null) ? 0 : typeId.hashCode()));
		result = (int) (prime * result + ((value == null) ? 0 : value.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VIricstockPassportValues))
			return false;
		VIricstockPassportValues equalCheck = (VIricstockPassportValues) obj;
		if ((typeId == null && equalCheck.typeId != null) || (typeId != null && equalCheck.typeId == null))
			return false;
		if (typeId != null && !typeId.equals(equalCheck.typeId))
			return false;
		if ((value == null && equalCheck.value != null) || (value != null && equalCheck.value == null))
			return false;
		if (value != null && !value.equals(equalCheck.value))
			return false;
		return true;
	}
}
