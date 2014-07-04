package org.irri.iric.portal.variety.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllSnpss", query = "select mySnps from Snps mySnps"),
		@NamedQuery(name = "findSnpsByChrom", query = "select mySnps from Snps mySnps where mySnps.chrom = ?1"),
		@NamedQuery(name = "findSnpsByMaxal", query = "select mySnps from Snps mySnps where mySnps.maxal = ?1"),
		@NamedQuery(name = "findSnpsByMinal", query = "select mySnps from Snps mySnps where mySnps.minal = ?1"),
		@NamedQuery(name = "findSnpsByPos", query = "select mySnps from Snps mySnps where mySnps.pos = ?1"),
		@NamedQuery(name = "findSnpsByPrimaryKey", query = "select mySnps from Snps mySnps where mySnps.snpId = ?1"),
		@NamedQuery(name = "findSnpsBySnpId", query = "select mySnps from Snps mySnps where mySnps.snpId = ?1"),
		@NamedQuery(name = "findSnpsBySnpIdContaining", query = "select mySnps from Snps mySnps where mySnps.snpId like ?1") })
@Table(schema = "NICKA", name = "SNPS")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "dev_crud_maker/org/irri/iric/portal/variety/domain", name = "Snps")
@XmlRootElement(namespace = "dev_crud_maker/org/irri/iric/portal/variety/domain")
public class Snps implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "SNP_ID", length = 10, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	String snpId;
	/**
	 */

	@Column(name = "CHROM")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer chrom;
	/**
	 */

	@Column(name = "POS", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal pos;
	/**
	 */

	@Column(name = "MAXAL")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer maxal;
	/**
	 */

	@Column(name = "MINAL")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer minal;

	/**
	 */
	@OneToMany(mappedBy = "snps", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<org.irri.iric.portal.variety.domain.Genotyping> genotypings;

	/**
	 */
	public void setSnpId(String snpId) {
		this.snpId = snpId;
	}

	/**
	 */
	public String getSnpId() {
		return this.snpId;
	}

	/**
	 */
	public void setChrom(Integer chrom) {
		this.chrom = chrom;
	}

	/**
	 */
	public Integer getChrom() {
		return this.chrom;
	}

	/**
	 */
	public void setPos(BigDecimal pos) {
		this.pos = pos;
	}

	/**
	 */
	public BigDecimal getPos() {
		return this.pos;
	}

	/**
	 */
	public void setMaxal(Integer maxal) {
		this.maxal = maxal;
	}

	/**
	 */
	public Integer getMaxal() {
		return this.maxal;
	}

	/**
	 */
	public void setMinal(Integer minal) {
		this.minal = minal;
	}

	/**
	 */
	public Integer getMinal() {
		return this.minal;
	}

	/**
	 */
	public void setGenotypings(Set<Genotyping> genotypings) {
		this.genotypings = genotypings;
	}

	/**
	 */
	@JsonIgnore
	public Set<Genotyping> getGenotypings() {
		if (genotypings == null) {
			genotypings = new java.util.LinkedHashSet<org.irri.iric.portal.variety.domain.Genotyping>();
		}
		return genotypings;
	}

	/**
	 */
	public Snps() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Snps that) {
		setSnpId(that.getSnpId());
		setChrom(that.getChrom());
		setPos(that.getPos());
		setMaxal(that.getMaxal());
		setMinal(that.getMinal());
		setGenotypings(new java.util.LinkedHashSet<org.irri.iric.portal.variety.domain.Genotyping>(that.getGenotypings()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("snpId=[").append(snpId).append("] ");
		buffer.append("chrom=[").append(chrom).append("] ");
		buffer.append("pos=[").append(pos).append("] ");
		buffer.append("maxal=[").append(maxal).append("] ");
		buffer.append("minal=[").append(minal).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((snpId == null) ? 0 : snpId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Snps))
			return false;
		Snps equalCheck = (Snps) obj;
		if ((snpId == null && equalCheck.snpId != null) || (snpId != null && equalCheck.snpId == null))
			return false;
		if (snpId != null && !snpId.equals(equalCheck.snpId))
			return false;
		return true;
	}
}
