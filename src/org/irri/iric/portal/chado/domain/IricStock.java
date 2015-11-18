package org.irri.iric.portal.chado.domain;

import java.io.Serializable;

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
		@NamedQuery(name = "findAllIricStocks", query = "select myIricStock from IricStock myIricStock"),
		@NamedQuery(name = "findIricStockByDbxrefId", query = "select myIricStock from IricStock myIricStock where myIricStock.dbxrefId = ?1"),
		@NamedQuery(name = "findIricStockByIricStockGeolocationId", query = "select myIricStock from IricStock myIricStock where myIricStock.iricStockGeolocationId = ?1"),
		@NamedQuery(name = "findIricStockByIricStockId", query = "select myIricStock from IricStock myIricStock where myIricStock.iricStockId = ?1"),
		@NamedQuery(name = "findIricStockByName", query = "select myIricStock from IricStock myIricStock where upper(myIricStock.name) = upper(?1)"),
		@NamedQuery(name = "findIricStockByNameContaining", query = "select myIricStock from IricStock myIricStock where upper(myIricStock.name) like upper(?1)"),
		@NamedQuery(name = "findIricStockByOrganismId", query = "select myIricStock from IricStock myIricStock where myIricStock.organismId = ?1"),
		@NamedQuery(name = "findIricStockByPrimaryKey", query = "select myIricStock from IricStock myIricStock where myIricStock.iricStockId = ?1"),
		@NamedQuery(name = "findIricStockByTypeId", query = "select myIricStock from IricStock myIricStock where myIricStock.typeId = ?1") })
@Table(schema = "IRIC", name = "IRIC_STOCK")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "IricStock")
public class IricStock implements Serializable , Variety {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "IRIC_STOCK_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal iricStockId;
	/**
	 */

	@Column(name = "DBXREF_ID", scale = 10, precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal dbxrefId;
	/**
	 */

	@Column(name = "ORGANISM_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal organismId;
	/**
	 */

	@Column(name = "TYPE_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal typeId;
	/**
	 */

	@Column(name = "NAME")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 */

	@Column(name = "IRIC_STOCK_GEOLOCATION_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal iricStockGeolocationId;



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
	public void setDbxrefId(BigDecimal dbxrefId) {
		this.dbxrefId = dbxrefId;
	}

	/**
	 */
	public BigDecimal getDbxrefId() {
		return this.dbxrefId;
	}

	/**
	 */
	public void setOrganismId(BigDecimal organismId) {
		this.organismId = organismId;
	}

	/**
	 */
	public BigDecimal getOrganismId() {
		return this.organismId;
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
	public void setIricStockGeolocationId(BigDecimal iricStockGeolocationId) {
		this.iricStockGeolocationId = iricStockGeolocationId;
	}

	/**
	 */
	public BigDecimal getIricStockGeolocationId() {
		return this.iricStockGeolocationId;
	}

	/**
	 */
	public IricStock() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(IricStock that) {
		setIricStockId(that.getIricStockId());
		setDbxrefId(that.getDbxrefId());
		setOrganismId(that.getOrganismId());
		setTypeId(that.getTypeId());
		setName(that.getName());
		setIricStockGeolocationId(that.getIricStockGeolocationId());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("iricStockId=[").append(iricStockId).append("] ");
		buffer.append("dbxrefId=[").append(dbxrefId).append("] ");
		buffer.append("organismId=[").append(organismId).append("] ");
		buffer.append("typeId=[").append(typeId).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("iricStockGeolocationId=[").append(iricStockGeolocationId).append("] ");

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
		if (!(obj instanceof IricStock))
			return false;
		IricStock equalCheck = (IricStock) obj;
		if ((iricStockId == null && equalCheck.iricStockId != null) || (iricStockId != null && equalCheck.iricStockId == null))
			return false;
		if (iricStockId != null && !iricStockId.equals(equalCheck.iricStockId))
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
		return null;
	}

	@Override
	public String getCountry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSubpopulation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCountry(String country) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSubpopulation(String subpopulation) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub

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

	@Override
	public String getBoxCode() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
