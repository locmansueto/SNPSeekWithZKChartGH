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

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVIricstockBasicprops", query = "select myVIricstockBasicprop from VIricstockBasicprop myVIricstockBasicprop"),
		@NamedQuery(name = "findVIricstockBasicpropByBoxCode", query = "select myVIricstockBasicprop from VIricstockBasicprop myVIricstockBasicprop where upper(myVIricstockBasicprop.boxCode) = upper(?1)"),
		@NamedQuery(name = "findVIricstockBasicpropByBoxCodeContaining", query = "select myVIricstockBasicprop from VIricstockBasicprop myVIricstockBasicprop where upper(myVIricstockBasicprop.boxCode) like upper(?1)"),
		@NamedQuery(name = "findVIricstockBasicpropByIricStockId", query = "select myVIricstockBasicprop from VIricstockBasicprop myVIricstockBasicprop where myVIricstockBasicprop.iricStockId = ?1"),
		@NamedQuery(name = "findVIricstockBasicpropByIrisUniqueId", query = "select myVIricstockBasicprop from VIricstockBasicprop myVIricstockBasicprop where upper(myVIricstockBasicprop.irisUniqueId) = upper(?1)"),
		@NamedQuery(name = "findVIricstockBasicpropByIrisUniqueIdContaining", query = "select myVIricstockBasicprop from VIricstockBasicprop myVIricstockBasicprop where upper(myVIricstockBasicprop.irisUniqueId) like upper(?1)"),
		@NamedQuery(name = "findVIricstockBasicpropByName", query = "select myVIricstockBasicprop from VIricstockBasicprop myVIricstockBasicprop where upper(myVIricstockBasicprop.name) = upper(?1)"),
		@NamedQuery(name = "findVIricstockBasicpropByNameContaining", query = "select myVIricstockBasicprop from VIricstockBasicprop myVIricstockBasicprop where upper(myVIricstockBasicprop.name) like upper(?1)"),
		
		@NamedQuery(name = "findVIricstockBasicpropByOriCountrySubpopulation", query = "select myVIricstockBasicprop from VIricstockBasicprop myVIricstockBasicprop where upper(myVIricstockBasicprop.oriCountry) = upper(?1) and upper(myVIricstockBasicprop.subpopulation)= upper(?2)"),
		
		@NamedQuery(name = "findVIricstockBasicpropByOriCountry", query = "select myVIricstockBasicprop from VIricstockBasicprop myVIricstockBasicprop where upper(myVIricstockBasicprop.oriCountry) = upper(?1)"),
		@NamedQuery(name = "findVIricstockBasicpropByOriCountryContaining", query = "select myVIricstockBasicprop from VIricstockBasicprop myVIricstockBasicprop where upper(myVIricstockBasicprop.oriCountry) like upper(?1)"),
		@NamedQuery(name = "findVIricstockBasicpropByPrimaryKey", query = "select myVIricstockBasicprop from VIricstockBasicprop myVIricstockBasicprop where myVIricstockBasicprop.iricStockId = ?1"),
		@NamedQuery(name = "findVIricstockBasicpropBySubpopulation", query = "select myVIricstockBasicprop from VIricstockBasicprop myVIricstockBasicprop where upper(myVIricstockBasicprop.subpopulation) = upper(?1)"),
		@NamedQuery(name = "findVIricstockBasicpropBySubpopulationContaining", query = "select myVIricstockBasicprop from VIricstockBasicprop myVIricstockBasicprop where upper(myVIricstockBasicprop.subpopulation) like upper(?1)") })
@Table(schema = "public", name = "v_iricstock_basicprop")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/postgres/domain", name = "VIricstockBasicprop")
public class VIricstockBasicprop implements Serializable , Variety {
	private static final long serialVersionUID = 1L;

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

	@Column(name = "iris_unique_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String irisUniqueId;
	/**
	 */

	@Column(name = "box_code")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String boxCode;

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
	public void setOriCountry(String oriCountry) {
		this.oriCountry = oriCountry;
	}

	/**
	 */
	public String getOriCountry() {
		if(oriCountry==null) return "";
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
		if(subpopulation==null) return "";
		return this.subpopulation;
	}

	/**
	 */
	public void setIrisUniqueId(String irisUniqueId) {
		this.irisUniqueId = irisUniqueId;
	}

	/**
	 */
	public String getIrisUniqueId() {
		if(irisUniqueId==null) return "";
		return this.irisUniqueId;
	}

	/**
	 */
	public void setBoxCode(String boxCode) {
		
		this.boxCode = boxCode;
	}

	/**
	 */
	public String getBoxCode() {
		
		if(boxCode==null) return "";
		return this.boxCode;
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
		setOriCountry(that.getOriCountry());
		setSubpopulation(that.getSubpopulation());
		setIrisUniqueId(that.getIrisUniqueId());
		setBoxCode(that.getBoxCode());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("iricStockId=[").append(iricStockId).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("oriCountry=[").append(oriCountry).append("] ");
		buffer.append("subpopulation=[").append(subpopulation).append("] ");
		buffer.append("irisUniqueId=[").append(irisUniqueId).append("] ");
		buffer.append("boxCode=[").append(boxCode).append("] ");

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
	public void setCountry(String country) {
		// TODO Auto-generated method stub
		this.oriCountry=country;
		
	}


	@Override
	public BigDecimal getVarietyId() {
		// TODO Auto-generated method stub
		return BigDecimal.valueOf(getIricStockId());
	}
	
	
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
		return this.getName() + delimiter + irisid + delimiter + subpop + delimiter + cntr;
	}
	
	
}
