package org.irri.iric.portal.chado.oracle.domain;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.CvTermUniqueValues;

/**
 */
@IdClass(org.irri.iric.portal.chado.oracle.domain.VIricstockPassportValuesPK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVIricstockPassportValuess", query = "select myVIricstockPassportValues from VIricstockPassportValues myVIricstockPassportValues"),
		@NamedQuery(name = "findVIricstockPassportValuesByPrimaryKey", query = "select myVIricstockPassportValues from VIricstockPassportValues myVIricstockPassportValues where myVIricstockPassportValues.value = ?1 and myVIricstockPassportValues.typeId = ?2"),
		@NamedQuery(name = "findVIricstockPassportValuesByTypeId", query = "select myVIricstockPassportValues from VIricstockPassportValues myVIricstockPassportValues where myVIricstockPassportValues.typeId = ?1"),

		
		@NamedQuery(name = "findVIricstockPassportValuesByValue", query = "select myVIricstockPassportValues from VIricstockPassportValues myVIricstockPassportValues where myVIricstockPassportValues.value = ?1"),
		@NamedQuery(name = "findVIricstockPassportValuesByValueContaining", query = "select myVIricstockPassportValues from VIricstockPassportValues myVIricstockPassportValues where myVIricstockPassportValues.value like ?1") })






@Table(schema = "IRIC", name = "V_IRICSTOCK_PASSPORT_VALUES")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VIricstockPassportValues")
public class VIricstockPassportValues implements Serializable, CvTermUniqueValues {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "VALUE", length = 4000, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String value;
	/**
	 */

	@Column(name = "TYPE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal typeId;

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
	public void setTypeId(BigDecimal typeId) {
		this.typeId = typeId;
	}

	/**
	 */
	public BigDecimal getTypeId() {
		return this.typeId;
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
		setValue(that.getValue());
		setTypeId(that.getTypeId());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("value=[").append(value).append("] ");
		buffer.append("typeId=[").append(typeId).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((value == null) ? 0 : value.hashCode()));
		result = (int) (prime * result + ((typeId == null) ? 0 : typeId.hashCode()));
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
		if ((value == null && equalCheck.value != null) || (value != null && equalCheck.value == null))
			return false;
		if (value != null && !value.equals(equalCheck.value))
			return false;
		if ((typeId == null && equalCheck.typeId != null) || (typeId != null && equalCheck.typeId == null))
			return false;
		if (typeId != null && !typeId.equals(equalCheck.typeId))
			return false;
		return true;
	}
}
