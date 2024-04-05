package org.irri.iric.portal.chado.oracle.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;

/**
 */
public class VSnpRefposindexV3ChrposPK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public VSnpRefposindexV3ChrposPK() {
	}

	/**
	 */

	@Column(name = "CHROMOSOME")
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String chromosome;
	/**
	 */

	@Column(name = "POSITION", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal position;

	/**
	 */
	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}

	/**
	 */
	public String getChromosome() {
		return this.chromosome;
	}

	/**
	 */
	public void setPosition(BigDecimal position) {
		this.position = position;
	}

	/**
	 */
	public BigDecimal getPosition() {
		return this.position;
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((chromosome == null) ? 0 : chromosome.hashCode()));
		result = (int) (prime * result + ((position == null) ? 0 : position.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {

		if (obj == this)
			return true;
		if (!(obj instanceof VSnpRefposindexV3ChrposPK))
			return false;
		VSnpRefposindexV3ChrposPK equalCheck = (VSnpRefposindexV3ChrposPK) obj;
		if ((chromosome == null && equalCheck.chromosome != null)
				|| (chromosome != null && equalCheck.chromosome == null))
			return false;
		if (chromosome != null && !chromosome.equals(equalCheck.chromosome))
			return false;
		if ((position == null && equalCheck.position != null) || (position != null && equalCheck.position == null))
			return false;
		if (position != null && !position.equals(equalCheck.position))
			return false;
		return true;

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("VSnpRefposindexPK");
		sb.append(" chromosome: ").append(getChromosome());
		sb.append(" position: ").append(getPosition());
		return sb.toString();
	}
}
