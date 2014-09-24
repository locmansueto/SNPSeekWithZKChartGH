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
import org.irri.iric.portal.domain.VarietyPlus;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVIricstocksByPassports", query = "select myVIricstocksByPassport from VIricstocksByPassport myVIricstocksByPassport"),
		@NamedQuery(name = "findVIricstocksByPassportByIricStockId", query = "select myVIricstocksByPassport from VIricstocksByPassport myVIricstocksByPassport where myVIricstocksByPassport.iricStockId = ?1"),
		@NamedQuery(name = "findVIricstocksByPassportByIricStockpropId", query = "select myVIricstocksByPassport from VIricstocksByPassport myVIricstocksByPassport where myVIricstocksByPassport.iricStockpropId = ?1"),
		@NamedQuery(name = "findVIricstocksByPassportByIrisUniqueId", query = "select myVIricstocksByPassport from VIricstocksByPassport myVIricstocksByPassport where myVIricstocksByPassport.irisUniqueId = ?1"),
		@NamedQuery(name = "findVIricstocksByPassportByIrisUniqueIdContaining", query = "select myVIricstocksByPassport from VIricstocksByPassport myVIricstocksByPassport where myVIricstocksByPassport.irisUniqueId like ?1"),
		@NamedQuery(name = "findVIricstocksByPassportByName", query = "select myVIricstocksByPassport from VIricstocksByPassport myVIricstocksByPassport where myVIricstocksByPassport.name = ?1"),
		@NamedQuery(name = "findVIricstocksByPassportByNameContaining", query = "select myVIricstocksByPassport from VIricstocksByPassport myVIricstocksByPassport where myVIricstocksByPassport.name like ?1"),
		@NamedQuery(name = "findVIricstocksByPassportByOriCountry", query = "select myVIricstocksByPassport from VIricstocksByPassport myVIricstocksByPassport where myVIricstocksByPassport.oriCountry = ?1"),
		@NamedQuery(name = "findVIricstocksByPassportByOriCountryContaining", query = "select myVIricstocksByPassport from VIricstocksByPassport myVIricstocksByPassport where myVIricstocksByPassport.oriCountry like ?1"),
		@NamedQuery(name = "findVIricstocksByPassportByPrimaryKey", query = "select myVIricstocksByPassport from VIricstocksByPassport myVIricstocksByPassport where myVIricstocksByPassport.iricStockpropId = ?1"),
		@NamedQuery(name = "findVIricstocksByPassportBySubpopulation", query = "select myVIricstocksByPassport from VIricstocksByPassport myVIricstocksByPassport where myVIricstocksByPassport.subpopulation = ?1"),
		@NamedQuery(name = "findVIricstocksByPassportBySubpopulationContaining", query = "select myVIricstocksByPassport from VIricstocksByPassport myVIricstocksByPassport where myVIricstocksByPassport.subpopulation like ?1"),
		@NamedQuery(name = "findVIricstocksByPassportByTypeId", query = "select myVIricstocksByPassport from VIricstocksByPassport myVIricstocksByPassport where myVIricstocksByPassport.typeId = ?1"),
		@NamedQuery(name = "findVIricstocksByPassportByValue", query = "select myVIricstocksByPassport from VIricstocksByPassport myVIricstocksByPassport where myVIricstocksByPassport.value = ?1"),

		@NamedQuery(name = "findVIricstocksByPassportByTypeIdValueEquals", query = "select myVIricstocksByPassport from VIricstocksByPassport myVIricstocksByPassport where myVIricstocksByPassport.typeId = ?1 and upper(myVIricstocksByPassport.value) = upper(?2)"),
		@NamedQuery(name = "findVIricstocksByPassportByTypeIdValueContaining", query = "select myVIricstocksByPassport from VIricstocksByPassport myVIricstocksByPassport where myVIricstocksByPassport.typeId = ?1 and upper(myVIricstocksByPassport.value) like upper(?2)"),
		
		
		@NamedQuery(name = "findVIricstocksByPassportByValueContaining", query = "select myVIricstocksByPassport from VIricstocksByPassport myVIricstocksByPassport where myVIricstocksByPassport.value like ?1") })




@Table(schema = "IRIC", name = "VL_IRICSTOCKS_BY_PASSPORT")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VIricstocksByPassport")
public class VIricstocksByPassport implements Serializable, VarietyPlus {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "IRIC_STOCKPROP_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal iricStockpropId;
	/**
	 */

	@Column(name = "IRIC_STOCK_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
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
	/**
	 */

	@Column(name = "SUBPOPULATION", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String subpopulation;
	/**
	 */

	@Column(name = "VALUE", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String value;
	/**
	 */

	@Column(name = "TYPE_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal typeId;

	/**
	 */
	public void setIricStockpropId(BigDecimal iricStockpropId) {
		this.iricStockpropId = iricStockpropId;
	}

	/**
	 */
	public BigDecimal getIricStockpropId() {
		return this.iricStockpropId;
	}

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
	public void setValue(Object object) {
		this.value = object.toString();
	}

	/**
	 */
	public Object getValue() {
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
	public VIricstocksByPassport() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VIricstocksByPassport that) {
		setIricStockpropId(that.getIricStockpropId());
		setIricStockId(that.getIricStockId());
		setName(that.getName());
		setIrisUniqueId(that.getIrisUniqueId());
		setOriCountry(that.getOriCountry());
		setSubpopulation(that.getSubpopulation());
		setValue(that.getValue());
		setTypeId(that.getTypeId());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("iricStockpropId=[").append(iricStockpropId).append("] ");
		buffer.append("iricStockId=[").append(iricStockId).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("irisUniqueId=[").append(irisUniqueId).append("] ");
		buffer.append("oriCountry=[").append(oriCountry).append("] ");
		buffer.append("subpopulation=[").append(subpopulation).append("] ");
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
		result = (int) (prime * result + ((iricStockpropId == null) ? 0 : iricStockpropId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VIricstocksByPassport))
			return false;
		VIricstocksByPassport equalCheck = (VIricstocksByPassport) obj;
		if ((iricStockpropId == null && equalCheck.iricStockpropId != null) || (iricStockpropId != null && equalCheck.iricStockpropId == null))
			return false;
		if (iricStockpropId != null && !iricStockpropId.equals(equalCheck.iricStockpropId))
			return false;
		return true;
	}

	@Override
	public BigDecimal getVarietyId() {
		// TODO Auto-generated method stub
		return this.getIricStockId();
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
	public void setCountry(String country) {
		// TODO Auto-generated method stub
	
		this.setOriCountry(country);
	}
	
	
	
	
	
}
