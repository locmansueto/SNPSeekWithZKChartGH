package org.irri.iric.portal.chado.postgres.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.VarietyDistance;

/**
 */
@IdClass(org.irri.iric.portal.chado.postgres.domain.VIricstockDistancePK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVIricstockDistances", query = "select myVIricstockDistance from VIricstockDistance myVIricstockDistance"),
		@NamedQuery(name = "findVIricstockDistanceByDistance", query = "select myVIricstockDistance from VIricstockDistance myVIricstockDistance where myVIricstockDistance.distance = ?1"),
		@NamedQuery(name = "findVIricstockDistanceByPrimaryKey", query = "select myVIricstockDistance from VIricstockDistance myVIricstockDistance where myVIricstockDistance.var1 = ?1 and myVIricstockDistance.var2 = ?2"),
		
		
		@NamedQuery(name = "findAllVIricstockDistancesTopN", query = "select myVIricstockDistance from VIricstockDistance myVIricstockDistance where myVIricstockDistance.var1 <= myVIricstockDistance.var2 order by myVIricstockDistance.distance desc"),
		@NamedQuery(name = "findVIricstockDistanceByVarieties", query = "select myVIricstockDistance from VIricstockDistance myVIricstockDistance where myVIricstockDistance.var1 <= myVIricstockDistance.var2 and myVIricstockDistance.var1 in (?1) and  myVIricstockDistance.var2 in (?1)"),
		@NamedQuery(name = "findVIricstockDistanceByVarieties2", query = "select myVIricstockDistance from VIricstockDistance myVIricstockDistance where myVIricstockDistance.var1 <= myVIricstockDistance.var2 and (myVIricstockDistance.var1 in (?1) or myVIricstockDistance.var1 in (?2)) and  (myVIricstockDistance.var2 in (?1) or myVIricstockDistance.var1 in (?2))"),
		@NamedQuery(name = "findVIricstockDistanceByVarieties3", query = "select myVIricstockDistance from VIricstockDistance myVIricstockDistance where myVIricstockDistance.var1 <= myVIricstockDistance.var2 and (myVIricstockDistance.var1 in (?1) or myVIricstockDistance.var1 in (?2) or myVIricstockDistance.var1 in (?3)) and  (myVIricstockDistance.var2 in (?1) or myVIricstockDistance.var2 in (?2) or myVIricstockDistance.var2 in (?3))"),

		
		@NamedQuery(name = "findVIricstockDistanceByVar1", query = "select myVIricstockDistance from VIricstockDistance myVIricstockDistance where myVIricstockDistance.var1 = ?1"),
		@NamedQuery(name = "findVIricstockDistanceByVar2", query = "select myVIricstockDistance from VIricstockDistance myVIricstockDistance where myVIricstockDistance.var2 = ?1") })
@Table(schema = "public", name = "v_iricstock_distance")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/postgres/domain", name = "VIricstockDistance")
public class VIricstockDistance implements Serializable , VarietyDistance {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "var1", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer var1;
	/**
	 */

	@Column(name = "var2", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer var2;
	/**
	 */

	@Column(name = "distance", scale = 8, precision = 8)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal distance;

	/**
	 */
	public void setVar1(Integer var1) {
		this.var1 = var1;
	}

	/**
	 */
	public BigDecimal  getVar1() {
		return BigDecimal.valueOf(this.var1);
	}

	/**
	 */
	public void setVar2(Integer var2) {
		this.var2 = var2;
	}

	/**
	 */
	public BigDecimal getVar2() {
		return BigDecimal.valueOf(this.var2 );
	}

	/**
	 */
	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}

	/**
	 */
	public BigDecimal getDistance() {
		return this.distance;
	}

	/**
	 */
	public VIricstockDistance() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VIricstockDistance that) {
		setVar1(that.getVar1().intValue());
		setVar2(that.getVar2().intValue());
		setDistance(that.getDistance());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("var1=[").append(var1).append("] ");
		buffer.append("var2=[").append(var2).append("] ");
		buffer.append("distance=[").append(distance).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((var1 == null) ? 0 : var1.hashCode()));
		result = (int) (prime * result + ((var2 == null) ? 0 : var2.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VIricstockDistance))
			return false;
		VIricstockDistance equalCheck = (VIricstockDistance) obj;
		if ((var1 == null && equalCheck.var1 != null) || (var1 != null && equalCheck.var1 == null))
			return false;
		if (var1 != null && !var1.equals(equalCheck.var1))
			return false;
		if ((var2 == null && equalCheck.var2 != null) || (var2 != null && equalCheck.var2 == null))
			return false;
		if (var2 != null && !var2.equals(equalCheck.var2))
			return false;
		return true;
	}

	@Override
	public BigDecimal getDist() {
		// TODO Auto-generated method stub
		return this.getDistance();
	}
	
	
	
}
