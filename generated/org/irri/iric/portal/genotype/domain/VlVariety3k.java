package org.irri.iric.portal.genotype.domain;

import java.io.Serializable;
import java.lang.StringBuilder;

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
		@NamedQuery(name = "findAllVlVariety3ks", query = "select myVlVariety3k from VlVariety3k myVlVariety3k"),
		@NamedQuery(name = "findVlVariety3kByIrisUniqueId", query = "select myVlVariety3k from VlVariety3k myVlVariety3k where upper(myVlVariety3k.irisUniqueId) = upper(?1)"),
		@NamedQuery(name = "findVlVariety3kByIrisUniqueIdContaining", query = "select myVlVariety3k from VlVariety3k myVlVariety3k where upper(myVlVariety3k.irisUniqueId) like upper(?1)"),
		@NamedQuery(name = "findVlVariety3kByName", query = "select myVlVariety3k from VlVariety3k myVlVariety3k where upper(myVlVariety3k.name) = upper(?1)"),
		@NamedQuery(name = "findVlVariety3kByNameContaining", query = "select myVlVariety3k from VlVariety3k myVlVariety3k where upper(myVlVariety3k.name) like upper(?1)"),
		@NamedQuery(name = "findVlVariety3kByOriCountry", query = "select myVlVariety3k from VlVariety3k myVlVariety3k where upper(myVlVariety3k.oriCountry) = upper(?1)"),
		@NamedQuery(name = "findVlVariety3kByOriCountryContaining", query = "select myVlVariety3k from VlVariety3k myVlVariety3k where upper(myVlVariety3k.oriCountry) like upper(?1)"),
		@NamedQuery(name = "findVlVariety3kByPrimaryKey", query = "select myVlVariety3k from VlVariety3k myVlVariety3k where myVlVariety3k.varId = ?1"),
		@NamedQuery(name = "findVlVariety3kByVarId", query = "select myVlVariety3k from VlVariety3k myVlVariety3k where myVlVariety3k.varId = ?1") })
@Table(schema = "LMANSUETO", name = "VL_VARIETY3K")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/genotype/domain", name = "VlVariety3k")
public class VlVariety3k implements Serializable, Variety {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "VAR_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer varId;
	/**
	 */

	@Column(name = "NAME", length = 128)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 */

	@Column(name = "IRIS_UNIQUE_ID", length = 16)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String irisUniqueId;
	/**
	 */

	@Column(name = "ORI_COUNTRY", length = 32)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String oriCountry;

	/**
	 */
	public void setVarId(Integer varId) {
		this.varId = varId;
	}

	/**
	 */
	public Integer getVarId() {
		return this.varId;
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
	public VlVariety3k() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VlVariety3k that) {
		setVarId(that.getVarId());
		setName(that.getName());
		setIrisUniqueId(that.getIrisUniqueId());
		setOriCountry(that.getOriCountry());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("varId=[").append(varId).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("irisUniqueId=[").append(irisUniqueId).append("] ");
		buffer.append("oriCountry=[").append(oriCountry).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((varId == null) ? 0 : varId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VlVariety3k))
			return false;
		VlVariety3k equalCheck = (VlVariety3k) obj;
		if ((varId == null && equalCheck.varId != null) || (varId != null && equalCheck.varId == null))
			return false;
		if (varId != null && !varId.equals(equalCheck.varId))
			return false;
		return true;
	}

	@Override
	public Integer getVarietyId() {
		// TODO Auto-generated method stub
		return this.getVarId();
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
		return null;
	}

	@Override
	public void setCountry(String country) {
		// TODO Auto-generated method stub
		this.setOriCountry(country);
	}

	@Override
	public void setSubpopulation(String subpopulation) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
