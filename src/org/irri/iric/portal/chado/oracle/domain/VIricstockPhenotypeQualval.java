package org.irri.iric.portal.chado.oracle.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.CvTermUniqueValues;

/**
 */
@IdClass(org.irri.iric.portal.chado.oracle.domain.VIricstockPhenotypeQualvalPK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVIricstockPhenotypeQualvals", query = "select myVIricstockPhenotypeQualval from VIricstockPhenotypeQualval myVIricstockPhenotypeQualval"),
		@NamedQuery(name = "findVIricstockPhenotypeQualvalByPhenotypeId", query = "select myVIricstockPhenotypeQualval from VIricstockPhenotypeQualval myVIricstockPhenotypeQualval where myVIricstockPhenotypeQualval.phenotypeId = ?1"),
		@NamedQuery(name = "findVIricstockPhenotypeQualvalByPrimaryKey", query = "select myVIricstockPhenotypeQualval from VIricstockPhenotypeQualval myVIricstockPhenotypeQualval where myVIricstockPhenotypeQualval.qualValue = ?1 and myVIricstockPhenotypeQualval.phenotypeId = ?2"),
		@NamedQuery(name = "findVIricstockPhenotypeQualvalByQualValue", query = "select myVIricstockPhenotypeQualval from VIricstockPhenotypeQualval myVIricstockPhenotypeQualval where myVIricstockPhenotypeQualval.qualValue = ?1"),
		@NamedQuery(name = "findVIricstockPhenotypeQualvalByQualValueContaining", query = "select myVIricstockPhenotypeQualval from VIricstockPhenotypeQualval myVIricstockPhenotypeQualval where myVIricstockPhenotypeQualval.qualValue like ?1") })
@Table(schema = "IRIC", name = "V_IRICSTOCK_PHENOTYPE_QUALVAL")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VIricstockPhenotypeQualval")
public class VIricstockPhenotypeQualval implements Serializable, CvTermUniqueValues {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "QUAL_VALUE", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String qualValue;
	/**
	 */

	@Column(name = "PHENOTYPE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal phenotypeId;

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
	public VIricstockPhenotypeQualval() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VIricstockPhenotypeQualval that) {
		setQualValue(that.getQualValue());
		setPhenotypeId(that.getPhenotypeId());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

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
		result = (int) (prime * result + ((qualValue == null) ? 0 : qualValue.hashCode()));
		result = (int) (prime * result + ((phenotypeId == null) ? 0 : phenotypeId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VIricstockPhenotypeQualval))
			return false;
		VIricstockPhenotypeQualval equalCheck = (VIricstockPhenotypeQualval) obj;
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
		return this.getQualValue();
	}
	
	
	
	
}
