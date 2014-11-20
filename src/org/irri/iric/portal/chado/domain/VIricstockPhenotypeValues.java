package org.irri.iric.portal.chado.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.CvTermUniqueValues;

/**
 */
@IdClass(org.irri.iric.portal.chado.domain.VIricstockPhenotypeValuesPK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVIricstockPhenotypeValuess", query = "select myVIricstockPhenotypeValues from VIricstockPhenotypeValues myVIricstockPhenotypeValues"),
		@NamedQuery(name = "findVIricstockPhenotypeValuesByPhenotypeId", query = "select myVIricstockPhenotypeValues from VIricstockPhenotypeValues myVIricstockPhenotypeValues where myVIricstockPhenotypeValues.phenotypeId = ?1"),
		@NamedQuery(name = "findVIricstockPhenotypeValuesByPrimaryKey", query = "select myVIricstockPhenotypeValues from VIricstockPhenotypeValues myVIricstockPhenotypeValues where myVIricstockPhenotypeValues.quanValue = ?1 and myVIricstockPhenotypeValues.qualValue = ?2 and myVIricstockPhenotypeValues.phenotypeId = ?3"),
		@NamedQuery(name = "findVIricstockPhenotypeValuesByQualValue", query = "select myVIricstockPhenotypeValues from VIricstockPhenotypeValues myVIricstockPhenotypeValues where myVIricstockPhenotypeValues.qualValue = ?1"),
		@NamedQuery(name = "findVIricstockPhenotypeValuesByQualValueContaining", query = "select myVIricstockPhenotypeValues from VIricstockPhenotypeValues myVIricstockPhenotypeValues where myVIricstockPhenotypeValues.qualValue like ?1"),
		@NamedQuery(name = "findVIricstockPhenotypeValuesByQuanValue", query = "select myVIricstockPhenotypeValues from VIricstockPhenotypeValues myVIricstockPhenotypeValues where myVIricstockPhenotypeValues.quanValue = ?1") })
@Table(schema = "IRIC", name = "V_IRICSTOCK_PHENOTYPE_VALUES")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VIricstockPhenotypeValues")
public class VIricstockPhenotypeValues implements Serializable, CvTermUniqueValues {
	private static final long serialVersionUID = 1L;

	/**
	 */

	
	@Column(name = "QUAN_VALUE")
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal quanValue;
	/**
	 */

	@Column(name = "QUAL_VALUE")
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String qualValue;
	/**
	 */

	@Column(name = "PHENOTYPE_ID", precision = 10, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal phenotypeId;

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
	public VIricstockPhenotypeValues() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VIricstockPhenotypeValues that) {
		setQuanValue(that.getQuanValue());
		setQualValue(that.getQualValue());
		setPhenotypeId(that.getPhenotypeId());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("quanValue=[").append(quanValue).append("] ");
		buffer.append("qualValue=[").append(qualValue).append("] ");
		buffer.append("phenotypeId=[").append(phenotypeId).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((quanValue == null) ? 0 : quanValue.hashCode()));
		result = (int) (prime * result + ((qualValue == null) ? 0 : qualValue.hashCode()));
		result = (int) (prime * result + ((phenotypeId == null) ? 0 : phenotypeId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VIricstockPhenotypeValues))
			return false;
		VIricstockPhenotypeValues equalCheck = (VIricstockPhenotypeValues) obj;
		if ((quanValue == null && equalCheck.quanValue != null) || (quanValue != null && equalCheck.quanValue == null))
			return false;
		if (quanValue != null && !quanValue.equals(equalCheck.quanValue))
			return false;
		if ((qualValue == null && equalCheck.qualValue != null) || (qualValue != null && equalCheck.qualValue == null))
			return false;
		if (qualValue != null && !qualValue.equals(equalCheck.qualValue))
			return false;
		if ((phenotypeId == null && equalCheck.phenotypeId != null) || (phenotypeId != null && equalCheck.phenotypeId == null))
			return false;
		if (phenotypeId != null && !phenotypeId.equals(equalCheck.phenotypeId))
			return false;
		return true;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		if(quanValue != null) return quanValue.toString();
		if(qualValue != null) return qualValue;
		
		return "";
	}
	
	
	
	
}
