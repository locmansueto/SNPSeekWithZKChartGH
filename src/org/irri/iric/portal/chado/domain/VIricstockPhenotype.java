package org.irri.iric.portal.chado.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.Phenotype;

/**
 */



@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVIricstockPhenotypes", query = "select myVIricstockPhenotype from VIricstockPhenotype myVIricstockPhenotype"),
		@NamedQuery(name = "findVIricstockPhenotypeByDefinition", query = "select myVIricstockPhenotype from VIricstockPhenotype myVIricstockPhenotype where myVIricstockPhenotype.definition = ?1"),
		@NamedQuery(name = "findVIricstockPhenotypeByDefinitionContaining", query = "select myVIricstockPhenotype from VIricstockPhenotype myVIricstockPhenotype where myVIricstockPhenotype.definition like ?1"),

		@NamedQuery(name = "findVIricstockPhenotypeByIricStockId", query = "select myVIricstockPhenotype from VIricstockPhenotype myVIricstockPhenotype where myVIricstockPhenotype.iricStockId = ?1"),
		
		@NamedQuery(name = "findVIricstockPhenotypeByIricStockPhenotypeId", query = "select myVIricstockPhenotype from VIricstockPhenotype myVIricstockPhenotype where myVIricstockPhenotype.iricStockPhenotypeId = ?1"),
		@NamedQuery(name = "findVIricstockPhenotypeByName", query = "select myVIricstockPhenotype from VIricstockPhenotype myVIricstockPhenotype where myVIricstockPhenotype.name = ?1"),
		
		@NamedQuery(name = "findVIricstockPhenotypeByNameContaining", query = "select myVIricstockPhenotype from VIricstockPhenotype myVIricstockPhenotype where upper(myVIricstockPhenotype.name) like upper(?1)"),
		
		@NamedQuery(name = "findVIricstockPhenotypeByPrimaryKey", query = "select myVIricstockPhenotype from VIricstockPhenotype myVIricstockPhenotype where myVIricstockPhenotype.iricStockPhenotypeId = ?1"),
		@NamedQuery(name = "findVIricstockPhenotypeByQualValue", query = "select myVIricstockPhenotype from VIricstockPhenotype myVIricstockPhenotype where myVIricstockPhenotype.qualValue = ?1"),
		@NamedQuery(name = "findVIricstockPhenotypeByQualValueContaining", query = "select myVIricstockPhenotype from VIricstockPhenotype myVIricstockPhenotype where myVIricstockPhenotype.qualValue like ?1"),
		@NamedQuery(name = "findVIricstockPhenotypeByQuanValue", query = "select myVIricstockPhenotype from VIricstockPhenotype myVIricstockPhenotype where myVIricstockPhenotype.quanValue = ?1") })
		
@Table(schema = "IRIC", name = "VL_IRICSTOCK_PHENOTYPE")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VIricstockPhenotype")
public class VIricstockPhenotype implements Serializable, Phenotype {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "IRIC_STOCK_PHENOTYPE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal iricStockPhenotypeId;
	/**
	 */

	@Column(name = "IRIC_STOCK_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal iricStockId;
	/**
	 */

	@Column(name = "NAME", length = 1024)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 */

	@Column(name = "DEFINITION", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String definition;
	/**
	 */

	@Column(name = "QUAN_VALUE", scale = 10, precision = 15)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal quanValue;
	/**
	 */

	@Column(name = "QUAL_VALUE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String qualValue;

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
	public void setDefinition(String definition) {
		this.definition = definition;
	}

	/**
	 */
	public String getDefinition() {
		return this.definition;
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
	public VIricstockPhenotype() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VIricstockPhenotype that) {
		setIricStockPhenotypeId(that.getIricStockPhenotypeId());
		setIricStockId(that.getIricStockId());
		setName(that.getName());
		setDefinition(that.getDefinition());
		setQuanValue(that.getQuanValue());
		setQualValue(that.getQualValue());
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
		buffer.append("definition=[").append(definition).append("] ");
		buffer.append("quanValue=[").append(quanValue).append("] ");
		buffer.append("qualValue=[").append(qualValue).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((iricStockPhenotypeId == null) ? 0 : iricStockPhenotypeId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VIricstockPhenotype))
			return false;
		VIricstockPhenotype equalCheck = (VIricstockPhenotype) obj;
		if ((iricStockPhenotypeId == null && equalCheck.iricStockPhenotypeId != null) || (iricStockPhenotypeId != null && equalCheck.iricStockPhenotypeId == null))
			return false;
		if (iricStockPhenotypeId != null && !iricStockPhenotypeId.equals(equalCheck.iricStockPhenotypeId))
			return false;
		return true;
	}

	@Override
	public BigDecimal getPhenotypeId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
