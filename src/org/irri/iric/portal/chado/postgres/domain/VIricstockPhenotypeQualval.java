package org.irri.iric.portal.chado.postgres.domain;

import java.io.Serializable;
import java.lang.StringBuilder;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.CvTermUniqueValues;

/**
 */
@IdClass(org.irri.iric.portal.chado.postgres.domain.VIricstockPhenotypeQualvalPK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVIricstockPhenotypeQualvals", query = "select myVIricstockPhenotypeQualval from VIricstockPhenotypeQualval myVIricstockPhenotypeQualval"),
		@NamedQuery(name = "findVIricstockPhenotypeQualvalByPhenotypeId", query = "select myVIricstockPhenotypeQualval from VIricstockPhenotypeQualval myVIricstockPhenotypeQualval where myVIricstockPhenotypeQualval.phenotypeId = ?1"),
		@NamedQuery(name = "findVIricstockPhenotypeQualvalByPrimaryKey", query = "select myVIricstockPhenotypeQualval from VIricstockPhenotypeQualval myVIricstockPhenotypeQualval where myVIricstockPhenotypeQualval.phenotypeId = ?1 and myVIricstockPhenotypeQualval.qualValue = ?2"),
		@NamedQuery(name = "findVIricstockPhenotypeQualvalByQualValue", query = "select myVIricstockPhenotypeQualval from VIricstockPhenotypeQualval myVIricstockPhenotypeQualval where myVIricstockPhenotypeQualval.qualValue = ?1"),
		@NamedQuery(name = "findVIricstockPhenotypeQualvalByQualValueContaining", query = "select myVIricstockPhenotypeQualval from VIricstockPhenotypeQualval myVIricstockPhenotypeQualval where myVIricstockPhenotypeQualval.qualValue like ?1") })
@Table(schema = "public", name = "v_iricstock_phenotype_qualval")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/postgres/domain", name = "VIricstockPhenotypeQualval")
public class VIricstockPhenotypeQualval implements Serializable, CvTermUniqueValues {
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

	@Column(name = "qual_value", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String qualValue;

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
	public VIricstockPhenotypeQualval() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VIricstockPhenotypeQualval that) {
		setPhenotypeId(that.getPhenotypeId());
		setQualValue(that.getQualValue());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("phenotypeId=[").append(phenotypeId).append("] ");
		buffer.append("qualValue=[").append(qualValue).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((phenotypeId == null) ? 0 : phenotypeId.hashCode()));
		result = (int) (prime * result + ((qualValue == null) ? 0 : qualValue.hashCode()));
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
		if ((phenotypeId == null && equalCheck.phenotypeId != null) || (phenotypeId != null && equalCheck.phenotypeId == null))
			return false;
		if (phenotypeId != null && !phenotypeId.equals(equalCheck.phenotypeId))
			return false;
		if ((qualValue == null && equalCheck.qualValue != null) || (qualValue != null && equalCheck.qualValue == null))
			return false;
		if (qualValue != null && !qualValue.equals(equalCheck.qualValue))
			return false;
		return true;
	}
	
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return this.getQualValue();
	}
	
	
}
