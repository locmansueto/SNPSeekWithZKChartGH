package org.irri.iric.portal.chado.postgres.domain;

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
@IdClass(org.irri.iric.portal.chado.postgres.domain.VIricstocksByPassportPK.class)
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
		@NamedQuery(name = "findVIricstocksByPassportByPrimaryKey", query = "select myVIricstocksByPassport from VIricstocksByPassport myVIricstocksByPassport where myVIricstocksByPassport.iricStockpropId = ?1 and myVIricstocksByPassport.iricStockId = ?2"),
		@NamedQuery(name = "findVIricstocksByPassportBySubpopulation", query = "select myVIricstocksByPassport from VIricstocksByPassport myVIricstocksByPassport where myVIricstocksByPassport.subpopulation = ?1"),
		@NamedQuery(name = "findVIricstocksByPassportBySubpopulationContaining", query = "select myVIricstocksByPassport from VIricstocksByPassport myVIricstocksByPassport where myVIricstocksByPassport.subpopulation like ?1"),
		@NamedQuery(name = "findVIricstocksByPassportByTypeId", query = "select myVIricstocksByPassport from VIricstocksByPassport myVIricstocksByPassport where myVIricstocksByPassport.typeId = ?1"),
		@NamedQuery(name = "findVIricstocksByPassportByValue", query = "select myVIricstocksByPassport from VIricstocksByPassport myVIricstocksByPassport where myVIricstocksByPassport.value = ?1"),
		
		@NamedQuery(name = "findVIricstocksByPassportByTypeIdValueEquals", query = "select myVIricstocksByPassport from VIricstocksByPassport myVIricstocksByPassport where myVIricstocksByPassport.typeId = ?1 and upper(myVIricstocksByPassport.value) = upper(?2)"),
		@NamedQuery(name = "findVIricstocksByPassportByTypeIdValueContaining", query = "select myVIricstocksByPassport from VIricstocksByPassport myVIricstocksByPassport where myVIricstocksByPassport.typeId = ?1 and upper(myVIricstocksByPassport.value) like upper(?2)"),
		
		
		@NamedQuery(name = "findVIricstocksByPassportByValueContaining", query = "select myVIricstocksByPassport from VIricstocksByPassport myVIricstocksByPassport where myVIricstocksByPassport.value like ?1") })
@Table(schema = "public", name = "v_iricstocks_by_passport")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/postgres/domain", name = "VIricstocksByPassport")
public class VIricstocksByPassport implements Serializable, VarietyPlus {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "iric_stockprop_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer iricStockpropId;
	/**
	 */

	@Column(name = "iric_stock_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer iricStockId;
	/**
	 */

	@Column(name = "name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 */

	@Column(name = "iris_unique_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String irisUniqueId;
	/**
	 */

	@Column(name = "ori_country")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String oriCountry;
	/**
	 */

	@Column(name = "subpopulation")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String subpopulation;
	/**
	 */

	@Column(name = "value", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String value;
	/**
	 */

	@Column(name = "type_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer typeId;

	/**
	 */
	public void setIricStockpropId(Integer iricStockpropId) {
		this.iricStockpropId = iricStockpropId;
	}

	/**
	 */
	public Integer getIricStockpropId() {
		return this.iricStockpropId;
	}

	/**
	 */
	public void setIricStockId(Integer iricStockId) {
		this.iricStockId = iricStockId;
	}

	/**
	 */
	public Integer getIricStockId() {
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

//	/**
//	 */
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = (int) (prime * result + ((iricStockpropId == null) ? 0 : iricStockpropId.hashCode()));
//		result = (int) (prime * result + ((iricStockId == null) ? 0 : iricStockId.hashCode()));
//		return result;
//	}
//
//	/**
//	 */
//	public boolean equals(Object obj) {
//		if (obj == this)
//			return true;
//		if (!(obj instanceof VIricstocksByPassport))
//			return false;
//		VIricstocksByPassport equalCheck = (VIricstocksByPassport) obj;
//		if ((iricStockpropId == null && equalCheck.iricStockpropId != null) || (iricStockpropId != null && equalCheck.iricStockpropId == null))
//			return false;
//		if (iricStockpropId != null && !iricStockpropId.equals(equalCheck.iricStockpropId))
//			return false;
//		if ((iricStockId == null && equalCheck.iricStockId != null) || (iricStockId != null && equalCheck.iricStockId == null))
//			return false;
//		if (iricStockId != null && !iricStockId.equals(equalCheck.iricStockId))
//			return false;
//		return true;
//	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((getVarietyId() == null) ? 0 : getVarietyId().hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Variety)) //VIricstockBasicprop))
			return false;
		//VIricstockBasicprop equalCheck = (VIricstockBasicprop) obj;
		Variety equalCheck = (Variety) obj;
		
		//return iricStockId.equals(equalCheck.getIricStockId());
		
		
		if ((getVarietyId() == null && equalCheck.getVarietyId() != null) || (getVarietyId() != null && equalCheck.getVarietyId() == null))
			return false;
		if (getVarietyId() != null && !getVarietyId().equals(equalCheck.getVarietyId()))
			return false;
		return true;
		
	}
	
	
	@Override
	public BigDecimal getVarietyId() {
		// TODO Auto-generated method stub
		return BigDecimal.valueOf(this.getIricStockId());
	}

	@Override
	public String getIrisId() {
		// TODO Auto-generated method stub
		if(this.getIrisUniqueId()==null || this.getIrisUniqueId().isEmpty())
			return this.getBoxCode();
		else return this.getIrisUniqueId();
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

	/*
	@Override
	public String getValueName() {
		// TODO Auto-generated method stub
		return valuename;
	}

	@Override
	public void setValueName(String valuename) {
		// TODO Auto-generated method stub
		this.valuename=valuename;
	}
	*/

	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		int ret = getName().compareTo( ((Variety)o).getName() ); 
		if(ret==0)
			ret = getVarietyId().compareTo( ((Variety)o).getVarietyId() );
			
		return ret;
	}
	@Override
	public String printFields(String delimiter) {
		// TODO Auto-generated method stub
		String irisid = getIrisId();
		if(irisid==null) irisid="";
		String subpop = getSubpopulation();
		if(subpop==null) subpop="";
		String cntr = getCountry();
		if(cntr==null) cntr="";
		String strvalue = value;
		if(strvalue==null) strvalue = "";
		return this.getName() + delimiter + irisid + delimiter + subpop + delimiter + cntr + delimiter + strvalue;
	}

	@Override
	public String getBoxCode() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}