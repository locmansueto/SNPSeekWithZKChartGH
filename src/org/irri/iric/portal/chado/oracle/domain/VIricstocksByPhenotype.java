package org.irri.iric.portal.chado.oracle.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.Phenotype;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.domain.VarietyPlus;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVIricstocksByPhenotypes", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByIricStockId", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.iricStockId = ?1"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByIricStockPhenotypeId", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.iricStockPhenotypeId = ?1"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByIrisUniqueId", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.irisUniqueId = ?1"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByIrisUniqueIdContaining", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.irisUniqueId like ?1"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByName", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.name = ?1"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByNameContaining", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.name like ?1"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByOriCountry", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.oriCountry = ?1"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByOriCountryContaining", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.oriCountry like ?1"),

		
		@NamedQuery(name = "findVIricstocksByPhenotypeByPhenotypeIdQuanLessthan", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.phenotypeId = ?1 and " +
				" myVIricstocksByPhenotype.quanValue < ?2 order by myVIricstocksByPhenotype.quanValue, myVIricstocksByPhenotype.name"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByPhenotypeIdQuanGreaterthan", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.phenotypeId = ?1 and " +
				" myVIricstocksByPhenotype.quanValue > ?2 order by myVIricstocksByPhenotype.quanValue, myVIricstocksByPhenotype.name"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByPhenotypeIdQuanEquals", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.phenotypeId = ?1 and " +
				" myVIricstocksByPhenotype.quanValue = ?2 order by  myVIricstocksByPhenotype.name"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByPhenotypeIdQualEquals", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.phenotypeId = ?1 and " +
				" myVIricstocksByPhenotype.qualValue = ?2 order by  myVIricstocksByPhenotype.name"),
					

				
				
		@NamedQuery(name = "findVIricstocksByPhenotypeByPhenotypeId", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.phenotypeId = ?1"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByPrimaryKey", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.iricStockPhenotypeId = ?1"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByQualValue", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.qualValue = ?1"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByQualValueContaining", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.qualValue like ?1"),
		@NamedQuery(name = "findVIricstocksByPhenotypeByQuanValue", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.quanValue = ?1"),

		
		
		
		@NamedQuery(name = "findVIricstocksByPhenotypeBySubpopulation", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.subpopulation = ?1"),
		@NamedQuery(name = "findVIricstocksByPhenotypeBySubpopulationContaining", query = "select myVIricstocksByPhenotype from VIricstocksByPhenotype myVIricstocksByPhenotype where myVIricstocksByPhenotype.subpopulation like ?1") })


@Table(schema = "IRIC", name = "V_IRICSTOCKS_BY_PHENOTYPE")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VIricstocksByPhenotype")
public class VIricstocksByPhenotype implements Serializable, VarietyPlus {
	private static final long serialVersionUID = 1L;
	
	//private String valuename;

	/**
	 */

	@Column(name = "IRIC_STOCK_PHENOTYPE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal iricStockPhenotypeId;
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

	@Column(name = "QUAL_VALUE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String qualValue;
	/**
	 */

	@Column(name = "QUAN_VALUE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal quanValue;
	/**
	 */

	@Column(name = "PHENOTYPE_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal phenotypeId;

	/**
	 */
	public void setIricStockPhenotypeId(BigDecimal iricStockPhenotypeId) {
		this.iricStockPhenotypeId = iricStockPhenotypeId;
	}

	/**
	 */
	public BigDecimal getIricStockPhenotypeId() {
		return this.iricStockPhenotypeId;
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
	public void setQualValue(String qualValue) {
		this.qualValue = qualValue;
	}

	/**
	 */
	public String getQualValue() {
		return this.qualValue;
	}

	/**
	 */
	public void setQuanValue(BigDecimal quanValue) {
		this.quanValue = quanValue;
	}

	/**
	 */
	public BigDecimal getQuanValue() {
		return this.quanValue;
	}

	/**
	 */
	public void setPhenotypeId(BigDecimal phenotypeId) {
		this.phenotypeId = phenotypeId;
	}

	/**
	 */
	public BigDecimal getPhenotypeId() {
		return this.phenotypeId;
	}

	/**
	 */
	public VIricstocksByPhenotype() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VIricstocksByPhenotype that) {
		setIricStockPhenotypeId(that.getIricStockPhenotypeId());
		setIricStockId(that.getIricStockId());
		setName(that.getName());
		setIrisUniqueId(that.getIrisUniqueId());
		setOriCountry(that.getOriCountry());
		setSubpopulation(that.getSubpopulation());
		setQualValue(that.getQualValue());
		setQuanValue(that.getQuanValue());
		setPhenotypeId(that.getPhenotypeId());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("iricStockPhenotypeId=[").append(iricStockPhenotypeId).append("] ");
		buffer.append("iricStockId=[").append(iricStockId).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("irisUniqueId=[").append(irisUniqueId).append("] ");
		buffer.append("oriCountry=[").append(oriCountry).append("] ");
		buffer.append("subpopulation=[").append(subpopulation).append("] ");
		buffer.append("qualValue=[").append(qualValue).append("] ");
		buffer.append("quanValue=[").append(quanValue).append("] ");
		buffer.append("phenotypeId=[").append(phenotypeId).append("] ");

		return buffer.toString();
	}

	/*
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((iricStockPhenotypeId == null) ? 0 : iricStockPhenotypeId.hashCode()));
		return result;
	}
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VIricstocksByPhenotype))
			return false;
		VIricstocksByPhenotype equalCheck = (VIricstocksByPhenotype) obj;
		if ((iricStockPhenotypeId == null && equalCheck.iricStockPhenotypeId != null) || (iricStockPhenotypeId != null && equalCheck.iricStockPhenotypeId == null))
			return false;
		if (iricStockPhenotypeId != null && !iricStockPhenotypeId.equals(equalCheck.iricStockPhenotypeId))
			return false;
		return true;
	}
	*/
	
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
		return this.getIricStockId();
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

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		if(this.quanValue!=null) return this.quanValue;
		else if(this.qualValue!=null) return this.qualValue;
		return null;
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
		// TODO Auto-generated method stub
				String irisid = getIrisId();
				if(irisid==null) irisid="";
				String subpop = getSubpopulation();
				if(subpop==null) subpop="";
				String cntr = getCountry();
				if(cntr==null) cntr="";
				String strvalue = qualValue;
				if(strvalue==null) strvalue = quanValue.toString();
				
				return this.getName() + delimiter + irisid + delimiter + subpop + delimiter + cntr + delimiter + strvalue;
	}

	@Override
	public String getBoxCode() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
