package org.irri.iric.portal.chado.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.Variety;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVIricstockBasicprops", query = "select myVIricstockBasicprop from VIricstockBasicprop myVIricstockBasicprop order by myVIricstockBasicprop.name"),
		@NamedQuery(name = "findVIricstockBasicpropByIricStockId", query = "select myVIricstockBasicprop from VIricstockBasicprop myVIricstockBasicprop where myVIricstockBasicprop.iricStockId = ?1"),
		@NamedQuery(name = "findVIricstockBasicpropByIrisUniqueId", query = "select myVIricstockBasicprop from VIricstockBasicprop myVIricstockBasicprop where upper(myVIricstockBasicprop.irisUniqueId) = upper(?1)"),
		@NamedQuery(name = "findVIricstockBasicpropByIrisUniqueIdContaining", query = "select myVIricstockBasicprop from VIricstockBasicprop myVIricstockBasicprop where upper(myVIricstockBasicprop.irisUniqueId) like upper(?1)"),
		@NamedQuery(name = "findVIricstockBasicpropByName", query = "select myVIricstockBasicprop from VIricstockBasicprop myVIricstockBasicprop where upper(myVIricstockBasicprop.name) = upper(?1)"),
		@NamedQuery(name = "findVIricstockBasicpropByNameContaining", query = "select myVIricstockBasicprop from VIricstockBasicprop myVIricstockBasicprop where upper(myVIricstockBasicprop.name) like upper(?1)"),
		@NamedQuery(name = "findVIricstockBasicpropByOriCountry", query = "select myVIricstockBasicprop from VIricstockBasicprop myVIricstockBasicprop where upper(myVIricstockBasicprop.oriCountry) = upper(?1)  order by myVIricstockBasicprop.name"),
		@NamedQuery(name = "findVIricstockBasicpropByOriCountryContaining", query = "select myVIricstockBasicprop from VIricstockBasicprop myVIricstockBasicprop where upper(myVIricstockBasicprop.oriCountry) like upper(?1)"),
		@NamedQuery(name = "findVIricstockBasicpropByPrimaryKey", query = "select myVIricstockBasicprop from VIricstockBasicprop myVIricstockBasicprop where myVIricstockBasicprop.iricStockId = ?1"),

		@NamedQuery(name = "findVIricstockBasicpropBySubpopulation", query = "select myVIricstockBasicprop from VIricstockBasicprop myVIricstockBasicprop where upper(myVIricstockBasicprop.subpopulation) = upper(?1)  order by myVIricstockBasicprop.name"),
		@NamedQuery(name = "findVIricstockBasicpropByOriCountrySubpopulation", query = "select myVIricstockBasicprop from VIricstockBasicprop myVIricstockBasicprop where upper(myVIricstockBasicprop.oriCountry) = upper(?1) and upper(myVIricstockBasicprop.subpopulation) = upper(?2)  order by myVIricstockBasicprop.name") })


@Table(schema = "IRIC", name = "VL_IRICSTOCK_BASICPROP")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VIricstockBasicprop")
public class VIricstockBasicprop implements Serializable, Variety {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "IRIC_STOCK_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal iricStockId;
	/**
	 */

	@Column(name = "NAME")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 */

	@Column(name = "IRIS_UNIQUE_ID", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String irisUniqueId;
	/**
	 */

	@Column(name = "ORI_COUNTRY", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String oriCountry;

	
	@Column(name = "SUBPOPULATION", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String subpopulation;

	
	/**
	 */
	public void setIricStockId(BigDecimal iricStockId) {
		this.iricStockId = iricStockId;
	}

	/**
	 */
	public BigDecimal getIricStockId() {
		return this.iricStockId;
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
	public void setIrisUniqueId(String irisUniqueId) {
		this.irisUniqueId = irisUniqueId;
	}

	/**
	 */
	public String getIrisUniqueId() {
		return this.irisUniqueId;
	}

	/**
	 */
	public void setOriCountry(String oriCountry) {
		this.oriCountry = oriCountry;
	}

	/**
	 */
	public String getOriCountry() {
		return this.oriCountry;
	}

	/**
	 */
	public VIricstockBasicprop() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VIricstockBasicprop that) {
		setIricStockId(that.getIricStockId());
		setName(that.getName());
		setIrisUniqueId(that.getIrisUniqueId());
		setOriCountry(that.getOriCountry());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("iricStockId=[").append(iricStockId).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("irisUniqueId=[").append(irisUniqueId).append("] ");
		buffer.append("oriCountry=[").append(oriCountry).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((iricStockId == null) ? 0 : iricStockId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VIricstockBasicprop))
			return false;
		VIricstockBasicprop equalCheck = (VIricstockBasicprop) obj;
		if ((iricStockId == null && equalCheck.iricStockId != null) || (iricStockId != null && equalCheck.iricStockId == null))
			return false;
		if (iricStockId != null && !iricStockId.equals(equalCheck.iricStockId))
			return false;
		return true;
	}

	
	
	
	@Override
	public String getIrisId() {
		// TODO Auto-generated method stub
		return this.getIrisUniqueId();
	}

	@Override
	public String getCountry() {
		// TODO Auto-generated method stub
		return this.getOriCountry();
	}

	@Override
	public String getSubpopulation() {
		// TODO Auto-generated method stub
		return this.subpopulation;
	}

	@Override
	public void setCountry(String country) {
		// TODO Auto-generated method stub
		this.oriCountry=country;
		
	}

	@Override
	public void setSubpopulation(String subpopulation) {
		// TODO Auto-generated method stub
		this.subpopulation=subpopulation;
	}

	@Override
	public BigDecimal getVarietyId() {
		// TODO Auto-generated method stub
		return this.getIricStockId();
	}
	
	
	
	
}
