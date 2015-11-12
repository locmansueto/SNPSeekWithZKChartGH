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
@IdClass(org.irri.iric.portal.chado.oracle.domain.VIricstockPhenotypeQuanvalPK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVIricstockPhenotypeQuanvals", query = "select myVIricstockPhenotypeQuanval from VIricstockPhenotypeQuanval myVIricstockPhenotypeQuanval"),
		@NamedQuery(name = "findVIricstockPhenotypeQuanvalByPhenotypeId", query = "select myVIricstockPhenotypeQuanval from VIricstockPhenotypeQuanval myVIricstockPhenotypeQuanval where myVIricstockPhenotypeQuanval.phenotypeId = ?1"),
		@NamedQuery(name = "findVIricstockPhenotypeQuanvalByPrimaryKey", query = "select myVIricstockPhenotypeQuanval from VIricstockPhenotypeQuanval myVIricstockPhenotypeQuanval where myVIricstockPhenotypeQuanval.quanValue = ?1 and myVIricstockPhenotypeQuanval.phenotypeId = ?2"),
		@NamedQuery(name = "findVIricstockPhenotypeQuanvalByQuanValue", query = "select myVIricstockPhenotypeQuanval from VIricstockPhenotypeQuanval myVIricstockPhenotypeQuanval where myVIricstockPhenotypeQuanval.quanValue = ?1") })
@Table(schema = "IRIC", name = "V_IRICSTOCK_PHENOTYPE_QUANVAL")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VIricstockPhenotypeQuanval")
public class VIricstockPhenotypeQuanval implements Serializable, CvTermUniqueValues {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "QUAN_VALUE", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal quanValue;
	/**
	 */

	@Column(name = "PHENOTYPE_ID", nullable = false)
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
	public VIricstockPhenotypeQuanval() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VIricstockPhenotypeQuanval that) {
		setQuanValue(that.getQuanValue());
		setPhenotypeId(that.getPhenotypeId());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("quanValue=[").append(quanValue).append("] ");
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
		result = (int) (prime * result + ((phenotypeId == null) ? 0 : phenotypeId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VIricstockPhenotypeQuanval))
			return false;
		VIricstockPhenotypeQuanval equalCheck = (VIricstockPhenotypeQuanval) obj;
		if ((quanValue == null && equalCheck.quanValue != null) || (quanValue != null && equalCheck.quanValue == null))
			return false;
		if (quanValue != null && !quanValue.equals(equalCheck.quanValue))
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
		return this.getQuanValue().toString();
	}
	
	
	
	
}
