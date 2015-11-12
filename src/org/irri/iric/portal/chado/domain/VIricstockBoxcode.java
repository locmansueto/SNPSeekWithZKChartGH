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
		@NamedQuery(name = "findAllVIricstockBoxcodes", query = "select myVIricstockBoxcode from VIricstockBoxcode myVIricstockBoxcode"),
		@NamedQuery(name = "findVIricstockBoxcodeByBoxCode", query = "select myVIricstockBoxcode from VIricstockBoxcode myVIricstockBoxcode where upper(myVIricstockBoxcode.boxCode) = upper(?1)"),
		@NamedQuery(name = "findVIricstockBoxcodeByBoxCodeContaining", query = "select myVIricstockBoxcode from VIricstockBoxcode myVIricstockBoxcode where upper(myVIricstockBoxcode.boxCode) like upper(?1)"),
		@NamedQuery(name = "findVIricstockBoxcodeByDbxrefId", query = "select myVIricstockBoxcode from VIricstockBoxcode myVIricstockBoxcode where myVIricstockBoxcode.dbxrefId = ?1"),
		@NamedQuery(name = "findVIricstockBoxcodeByIricStockGeolocationId", query = "select myVIricstockBoxcode from VIricstockBoxcode myVIricstockBoxcode where myVIricstockBoxcode.iricStockGeolocationId = ?1"),
		@NamedQuery(name = "findVIricstockBoxcodeByIricStockId", query = "select myVIricstockBoxcode from VIricstockBoxcode myVIricstockBoxcode where myVIricstockBoxcode.iricStockId = ?1"),
		@NamedQuery(name = "findVIricstockBoxcodeByName", query = "select myVIricstockBoxcode from VIricstockBoxcode myVIricstockBoxcode where upper(myVIricstockBoxcode.name) = upper(?1)"),
		@NamedQuery(name = "findVIricstockBoxcodeByNameContaining", query = "select myVIricstockBoxcode from VIricstockBoxcode myVIricstockBoxcode where upper(myVIricstockBoxcode.name) like upper(?1)"),
		@NamedQuery(name = "findVIricstockBoxcodeByOrganismId", query = "select myVIricstockBoxcode from VIricstockBoxcode myVIricstockBoxcode where myVIricstockBoxcode.organismId = ?1"),
		@NamedQuery(name = "findVIricstockBoxcodeByPrimaryKey", query = "select myVIricstockBoxcode from VIricstockBoxcode myVIricstockBoxcode where myVIricstockBoxcode.iricStockId = ?1"),
		@NamedQuery(name = "findVIricstockBoxcodeByTypeId", query = "select myVIricstockBoxcode from VIricstockBoxcode myVIricstockBoxcode where myVIricstockBoxcode.typeId = ?1") })
@Table(schema = "IRIC", name = "V_IRICSTOCK_BOXCODE_3024")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VIricstockBoxcode")
public class VIricstockBoxcode implements Serializable, Variety {
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

	@Column(name = "DBXREF_ID", precision = 10)
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
	public VIricstockBoxcode() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VIricstockBoxcode that) {
		setIricStockId(that.getIricStockId());
		setDbxrefId(that.getDbxrefId());
		setOrganismId(that.getOrganismId());
		setTypeId(that.getTypeId());
		setName(that.getName());
		setIricStockGeolocationId(that.getIricStockGeolocationId());
		setBoxCode(that.getBoxCode());
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
		if (!(obj instanceof VIricstockBoxcode))
			return false;
		VIricstockBoxcode equalCheck = (VIricstockBoxcode) obj;
		if ((iricStockId == null && equalCheck.iricStockId != null) || (iricStockId != null && equalCheck.iricStockId == null))
			return false;
		if (iricStockId != null && !iricStockId.equals(equalCheck.iricStockId))
			return false;
		return true;
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
	public BigDecimal getVarietyId() {
		// TODO Auto-generated method stub
		return this.iricStockId ;
	}

	@Override
	public String getIrisId() {
		// TODO Auto-generated method stub
		return getBoxCode();
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
	public String printFields(String delimiter) {
		// TODO Auto-generated method stub
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
