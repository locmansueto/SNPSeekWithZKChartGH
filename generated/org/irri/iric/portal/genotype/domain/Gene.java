package org.irri.iric.portal.genotype.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllGenes", query = "select myGene from Gene myGene"),
		@NamedQuery(name = "findGeneByChr", query = "select myGene from Gene myGene where myGene.chr = ?1"),
		@NamedQuery(name = "findGeneByChrContaining", query = "select myGene from Gene myGene where myGene.chr like ?1"),
		@NamedQuery(name = "findGeneByFeatureId", query = "select myGene from Gene myGene where myGene.featureId = ?1"),
		@NamedQuery(name = "findGeneByFmax", query = "select myGene from Gene myGene where myGene.fmax = ?1"),
		@NamedQuery(name = "findGeneByFmin", query = "select myGene from Gene myGene where myGene.fmin = ?1"),
		@NamedQuery(name = "findGeneByGeneField", query = "select myGene from Gene myGene where myGene.geneField = ?1"),
		@NamedQuery(name = "findGeneByGeneFieldContaining", query = "select myGene from Gene myGene where myGene.geneField like ?1"),
		@NamedQuery(name = "findGeneByPrimaryKey", query = "select myGene from Gene myGene where myGene.featureId = ?1"),
		@NamedQuery(name = "findGeneByUniquename", query = "select myGene from Gene myGene where myGene.uniquename = ?1"),
		@NamedQuery(name = "findGeneByUniquenameContaining", query = "select myGene from Gene myGene where myGene.uniquename like ?1") })
@Table(schema = "LMANSUETO", name = "GENE")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "zkspring7/org/irri/iric/portal/genotype/domain", name = "Gene")
public class Gene implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "FEATURE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer featureId;
	/**
	 */

	@Column(name = "GENE", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String geneField;
	/**
	 */

	@Column(name = "UNIQUENAME", length = 4000)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String uniquename;
	/**
	 */

	@Column(name = "FMIN", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal fmin;
	/**
	 */

	@Column(name = "FMAX", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal fmax;
	/**
	 */

	@Column(name = "CHR", length = 1020)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String chr;

	/**
	 */
	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}

	/**
	 */
	public Integer getFeatureId() {
		return this.featureId;
	}

	/**
	 */
	public void setGeneField(String geneField) {
		this.geneField = geneField;
	}

	/**
	 */
	public String getGeneField() {
		return this.geneField;
	}

	/**
	 */
	public void setUniquename(String uniquename) {
		this.uniquename = uniquename;
	}

	/**
	 */
	public String getUniquename() {
		return this.uniquename;
	}

	/**
	 */
	public void setFmin(BigDecimal fmin) {
		this.fmin = fmin;
	}

	/**
	 */
	public BigDecimal getFmin() {
		return this.fmin;
	}

	/**
	 */
	public void setFmax(BigDecimal fmax) {
		this.fmax = fmax;
	}

	/**
	 */
	public BigDecimal getFmax() {
		return this.fmax;
	}

	/**
	 */
	public void setChr(String chr) {
		this.chr = chr;
	}

	/**
	 */
	public String getChr() {
		return this.chr;
	}

	/**
	 */
	public Gene() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Gene that) {
		setFeatureId(that.getFeatureId());
		setGeneField(that.getGeneField());
		setUniquename(that.getUniquename());
		setFmin(that.getFmin());
		setFmax(that.getFmax());
		setChr(that.getChr());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("featureId=[").append(featureId).append("] ");
		buffer.append("geneField=[").append(geneField).append("] ");
		buffer.append("uniquename=[").append(uniquename).append("] ");
		buffer.append("fmin=[").append(fmin).append("] ");
		buffer.append("fmax=[").append(fmax).append("] ");
		buffer.append("chr=[").append(chr).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((featureId == null) ? 0 : featureId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Gene))
			return false;
		Gene equalCheck = (Gene) obj;
		if ((featureId == null && equalCheck.featureId != null) || (featureId != null && equalCheck.featureId == null))
			return false;
		if (featureId != null && !featureId.equals(equalCheck.featureId))
			return false;
		return true;
	}
}
