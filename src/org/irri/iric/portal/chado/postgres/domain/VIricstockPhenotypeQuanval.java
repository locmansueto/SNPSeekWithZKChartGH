package org.irri.iric.portal.chado.postgres.domain;

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
@IdClass(org.irri.iric.portal.chado.postgres.domain.VIricstockPhenotypeQuanvalPK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVIricstockPhenotypeQuanvals", query = "select myVIricstockPhenotypeQuanval from VIricstockPhenotypeQuanval myVIricstockPhenotypeQuanval"),
		@NamedQuery(name = "findVIricstockPhenotypeQuanvalByPhenotypeId", query = "select myVIricstockPhenotypeQuanval from VIricstockPhenotypeQuanval myVIricstockPhenotypeQuanval where myVIricstockPhenotypeQuanval.phenotypeId = ?1"),
		@NamedQuery(name = "findVIricstockPhenotypeQuanvalByPrimaryKey", query = "select myVIricstockPhenotypeQuanval from VIricstockPhenotypeQuanval myVIricstockPhenotypeQuanval where myVIricstockPhenotypeQuanval.phenotypeId = ?1 and myVIricstockPhenotypeQuanval.quanValue = ?2"),
		@NamedQuery(name = "findVIricstockPhenotypeQuanvalByQuanValue", query = "select myVIricstockPhenotypeQuanval from VIricstockPhenotypeQuanval myVIricstockPhenotypeQuanval where myVIricstockPhenotypeQuanval.quanValue = ?1") })
@Table(schema = "public", name = "v_iricstock_phenotype_quanval")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/postgres/domain", name = "VIricstockPhenotypeQuanval")
public class VIricstockPhenotypeQuanval implements Serializable, CvTermUniqueValues {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "phenotype_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer phenotypeId;
	/**
	 */

	@Column(name = "quan_value", scale = 17, precision = 17, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal quanValue;

	/**
	 */
	public void setPhenotypeId(Integer phenotypeId) {
		this.phenotypeId = phenotypeId;
	}

	/**
	 */
	public Integer getPhenotypeId() {
		return this.phenotypeId;
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
	public VIricstockPhenotypeQuanval() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VIricstockPhenotypeQuanval that) {
		setPhenotypeId(that.getPhenotypeId());
		setQuanValue(that.getQuanValue());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("phenotypeId=[").append(phenotypeId).append("] ");
		buffer.append("quanValue=[").append(quanValue).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((phenotypeId == null) ? 0 : phenotypeId.hashCode()));
		result = (int) (prime * result + ((quanValue == null) ? 0 : quanValue.hashCode()));
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
		if ((phenotypeId == null && equalCheck.phenotypeId != null) || (phenotypeId != null && equalCheck.phenotypeId == null))
			return false;
		if (phenotypeId != null && !phenotypeId.equals(equalCheck.phenotypeId))
			return false;
		if ((quanValue == null && equalCheck.quanValue != null) || (quanValue != null && equalCheck.quanValue == null))
			return false;
		if (quanValue != null && !quanValue.equals(equalCheck.quanValue))
			return false;
		return true;
	}
	
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		//return this.getQuanValue().toString();
		return String.format("%.3f", getQuanValue() ).replaceAll("\\.?0+$","");
	}
	
}
