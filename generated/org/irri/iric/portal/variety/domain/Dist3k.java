package org.irri.iric.portal.variety.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */
@IdClass(org.irri.iric.portal.variety.domain.Dist3kPK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllDist3ks", query = "select myDist3k from Dist3k myDist3k"),
		@NamedQuery(name = "findDist3kByDist", query = "select myDist3k from Dist3k myDist3k where myDist3k.dist = ?1"),
		@NamedQuery(name = "findDist3kByNam1", query = "select myDist3k from Dist3k myDist3k where myDist3k.nam1 = ?1"),
		@NamedQuery(name = "findDist3kByNam1Containing", query = "select myDist3k from Dist3k myDist3k where myDist3k.nam1 like ?1"),
		@NamedQuery(name = "findDist3kByNam2", query = "select myDist3k from Dist3k myDist3k where myDist3k.nam2 = ?1"),
		@NamedQuery(name = "findDist3kByNam2Containing", query = "select myDist3k from Dist3k myDist3k where myDist3k.nam2 like ?1"),
		@NamedQuery(name = "findDist3kByPrimaryKey", query = "select myDist3k from Dist3k myDist3k where myDist3k.nam1 = ?1 and myDist3k.nam2 = ?2") })
@Table(schema = "NICKA", name = "DIST_3K")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "dev_crud_maker/org/irri/iric/portal/variety/domain", name = "Dist3k")
public class Dist3k implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "NAM1", length = 16)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String nam1;
	/**
	 */

	@Column(name = "NAM2", length = 16)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String nam2;
	/**
	 */

	@Column(name = "DIST")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer dist;

	/**
	 */
	public void setNam1(String nam1) {
		this.nam1 = nam1;
	}

	/**
	 */
	public String getNam1() {
		return this.nam1;
	}

	/**
	 */
	public void setNam2(String nam2) {
		this.nam2 = nam2;
	}

	/**
	 */
	public String getNam2() {
		return this.nam2;
	}

	/**
	 */
	public void setDist(Integer dist) {
		this.dist = dist;
	}

	/**
	 */
	public Integer getDist() {
		return this.dist;
	}

	/**
	 */
	public Dist3k() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Dist3k that) {
		setNam1(that.getNam1());
		setNam2(that.getNam2());
		setDist(that.getDist());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("nam1=[").append(nam1).append("] ");
		buffer.append("nam2=[").append(nam2).append("] ");
		buffer.append("dist=[").append(dist).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((nam1 == null) ? 0 : nam1.hashCode()));
		result = (int) (prime * result + ((nam2 == null) ? 0 : nam2.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Dist3k))
			return false;
		Dist3k equalCheck = (Dist3k) obj;
		if ((nam1 == null && equalCheck.nam1 != null) || (nam1 != null && equalCheck.nam1 == null))
			return false;
		if (nam1 != null && !nam1.equals(equalCheck.nam1))
			return false;
		if ((nam2 == null && equalCheck.nam2 != null) || (nam2 != null && equalCheck.nam2 == null))
			return false;
		if (nam2 != null && !nam2.equals(equalCheck.nam2))
			return false;
		return true;
	}
}
