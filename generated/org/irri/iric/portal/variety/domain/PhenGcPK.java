package org.irri.iric.portal.variety.domain;

import java.io.Serializable;

import javax.persistence.Id;

import javax.persistence.*;

/**
 */
public class PhenGcPK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public PhenGcPK() {
	}

	/**
	 */

	@Column(name = "ENTNO")
	@Basic(fetch = FetchType.EAGER)
	@Id
	public Integer entno;
	/**
	 */

	@Column(name = "GID")
	@Basic(fetch = FetchType.EAGER)
	@Id
	public Integer gid;

	/**
	 */
	public void setEntno(Integer entno) {
		this.entno = entno;
	}

	/**
	 */
	public Integer getEntno() {
		return this.entno;
	}

	/**
	 */
	public void setGid(Integer gid) {
		this.gid = gid;
	}

	/**
	 */
	public Integer getGid() {
		return this.gid;
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((entno == null) ? 0 : entno.hashCode()));
		result = (int) (prime * result + ((gid == null) ? 0 : gid.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof PhenGcPK))
			return false;
		PhenGcPK equalCheck = (PhenGcPK) obj;
		if ((entno == null && equalCheck.entno != null) || (entno != null && equalCheck.entno == null))
			return false;
		if (entno != null && !entno.equals(equalCheck.entno))
			return false;
		if ((gid == null && equalCheck.gid != null) || (gid != null && equalCheck.gid == null))
			return false;
		if (gid != null && !gid.equals(equalCheck.gid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("PhenGcPK");
		sb.append(" entno: ").append(getEntno());
		sb.append(" gid: ").append(getGid());
		return sb.toString();
	}
}
