package org.irri.iric.portal.chado.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
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
		@NamedQuery(name = "findAllVIricstockBasicprop2s", query = "select myVIricstockBasicprop2 from VIricstockBasicprop2 myVIricstockBasicprop2"),
		@NamedQuery(name = "findVIricstockBasicprop2ByBoxCode", query = "select myVIricstockBasicprop2 from VIricstockBasicprop2 myVIricstockBasicprop2 where upper(myVIricstockBasicprop2.boxCode) = upper(?1)"),
		@NamedQuery(name = "findVIricstockBasicprop2ByBoxCodeContaining", query = "select myVIricstockBasicprop2 from VIricstockBasicprop2 myVIricstockBasicprop2 where upper(myVIricstockBasicprop2.boxCode) like upper(?1)"),
		@NamedQuery(name = "findVIricstockBasicprop2ByIricStockId", query = "select myVIricstockBasicprop2 from VIricstockBasicprop2 myVIricstockBasicprop2 where upper(myVIricstockBasicprop2.iricStockId) = upper(?1)"),
		@NamedQuery(name = "findVIricstockBasicprop2ByIrisUniqueId", query = "select myVIricstockBasicprop2 from VIricstockBasicprop2 myVIricstockBasicprop2 where upper(myVIricstockBasicprop2.irisUniqueId) = upper(?1) or upper(myVIricstockBasicprop2.boxCode) = upper(?1)"),
		@NamedQuery(name = "findVIricstockBasicprop2ByIrisUniqueIdContaining", query = "select myVIricstockBasicprop2 from VIricstockBasicprop2 myVIricstockBasicprop2 where upper(myVIricstockBasicprop2.irisUniqueId) like upper(?1)"),
		@NamedQuery(name = "findVIricstockBasicprop2ByName", query = "select myVIricstockBasicprop2 from VIricstockBasicprop2 myVIricstockBasicprop2 where upper(myVIricstockBasicprop2.name) = upper(?1)"),
		@NamedQuery(name = "findVIricstockBasicprop2ByNameContaining", query = "select myVIricstockBasicprop2 from VIricstockBasicprop2 myVIricstockBasicprop2 where upper(myVIricstockBasicprop2.name) like upper(?1)"),
		@NamedQuery(name = "findVIricstockBasicprop2ByOriCountry", query = "select myVIricstockBasicprop2 from VIricstockBasicprop2 myVIricstockBasicprop2 where upper(myVIricstockBasicprop2.oriCountry) = upper(?1)"),
		@NamedQuery(name = "findVIricstockBasicprop2ByOriCountryContaining", query = "select myVIricstockBasicprop2 from VIricstockBasicprop2 myVIricstockBasicprop2 where upper(myVIricstockBasicprop2.oriCountry) like upper(?1)"),
		@NamedQuery(name = "findVIricstockBasicprop2ByPrimaryKey", query = "select myVIricstockBasicprop2 from VIricstockBasicprop2 myVIricstockBasicprop2 where upper(myVIricstockBasicprop2.iricStockId) = upper(?1)") ,


	@NamedQuery(name = "findVIricstockBasicprop2BySubpopulation", query = "select myVIricstockBasicprop2 from VIricstockBasicprop2 myVIricstockBasicprop2 where upper(myVIricstockBasicprop2.subpopulation) = upper(?1)  order by myVIricstockBasicprop2.name"),
	@NamedQuery(name = "findVIricstockBasicprop2ByOriCountrySubpopulation", query = "select myVIricstockBasicprop2 from VIricstockBasicprop2 myVIricstockBasicprop2 where upper(myVIricstockBasicprop2.oriCountry) = upper(?1) and upper(myVIricstockBasicprop2.subpopulation) = upper(?2)  order by myVIricstockBasicprop2.name") })


@Table(schema = "IRIC", name = "V_IRICSTOCK_BASICPROP2")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VIricstockBasicprop2")
public class VIricstockBasicprop2 implements Serializable , Variety {
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
	/**
	 */


	@Column(name = "SUBPOPULATION", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String subpopulation;
	
	@Column(name = "BOX_CODE", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String boxCode;

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
		if(this.irisUniqueId==null || this.irisUniqueId.isEmpty())
			return getBoxCode();
		else return this.irisUniqueId;
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
	public void setBoxCode(String boxCode) {
		this.boxCode = boxCode;
	}

	/**
	 */
	public String getBoxCode() {
		return this.boxCode;
	}

	/**
	 */
	public VIricstockBasicprop2() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VIricstockBasicprop2 that) {
		setIricStockId(that.getIricStockId());
		setName(that.getName());
		setIrisUniqueId(that.getIrisUniqueId());
		setOriCountry(that.getOriCountry());
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
		buffer.append("irisUniqueId=[").append(irisUniqueId).append("] ");
		buffer.append("oriCountry=[").append(oriCountry).append("] ");
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
		if (!(obj instanceof Variety))
			return false;
		VIricstockBasicprop2 equalCheck = (VIricstockBasicprop2) obj;
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
