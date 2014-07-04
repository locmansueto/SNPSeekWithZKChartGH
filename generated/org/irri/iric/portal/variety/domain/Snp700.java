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
		@NamedQuery(name = "findAllSnp700s", query = "select mySnp700 from Snp700 mySnp700"),
		@NamedQuery(name = "findSnp700ByAlt", query = "select mySnp700 from Snp700 mySnp700 where mySnp700.alt = ?1"),
		@NamedQuery(name = "findSnp700ByAltContaining", query = "select mySnp700 from Snp700 mySnp700 where mySnp700.alt like ?1"),
		@NamedQuery(name = "findSnp700ByAvgQscore", query = "select mySnp700 from Snp700 mySnp700 where mySnp700.avgQscore = ?1"),
		@NamedQuery(name = "findSnp700ByChr", query = "select mySnp700 from Snp700 mySnp700 where mySnp700.chr = ?1"),
		@NamedQuery(name = "findSnp700ByChrContaining", query = "select mySnp700 from Snp700 mySnp700 where mySnp700.chr like ?1"),
		@NamedQuery(name = "findSnp700ByFilter", query = "select mySnp700 from Snp700 mySnp700 where mySnp700.filter = ?1"),
		@NamedQuery(name = "findSnp700ByFilterContaining", query = "select mySnp700 from Snp700 mySnp700 where mySnp700.filter like ?1"),
		@NamedQuery(name = "findSnp700ByFormat", query = "select mySnp700 from Snp700 mySnp700 where mySnp700.format = ?1"),
		@NamedQuery(name = "findSnp700ByFormatContaining", query = "select mySnp700 from Snp700 mySnp700 where mySnp700.format like ?1"),
		@NamedQuery(name = "findSnp700ByHet", query = "select mySnp700 from Snp700 mySnp700 where mySnp700.het = ?1"),
		@NamedQuery(name = "findSnp700ById", query = "select mySnp700 from Snp700 mySnp700 where mySnp700.id = ?1"),
		@NamedQuery(name = "findSnp700ByInfo", query = "select mySnp700 from Snp700 mySnp700 where mySnp700.info = ?1"),
		@NamedQuery(name = "findSnp700ByInfoContaining", query = "select mySnp700 from Snp700 mySnp700 where mySnp700.info like ?1"),
		@NamedQuery(name = "findSnp700ByName", query = "select mySnp700 from Snp700 mySnp700 where mySnp700.name = ?1"),
		@NamedQuery(name = "findSnp700ByNameContaining", query = "select mySnp700 from Snp700 mySnp700 where mySnp700.name like ?1"),
		@NamedQuery(name = "findSnp700ByPos", query = "select mySnp700 from Snp700 mySnp700 where mySnp700.pos = ?1"),
		@NamedQuery(name = "findSnp700ByPrimaryKey", query = "select mySnp700 from Snp700 mySnp700 where mySnp700.id = ?1"),
		@NamedQuery(name = "findSnp700ByQscore", query = "select mySnp700 from Snp700 mySnp700 where mySnp700.qscore = ?1"),
		@NamedQuery(name = "findSnp700ByRef", query = "select mySnp700 from Snp700 mySnp700 where mySnp700.ref = ?1"),
		@NamedQuery(name = "findSnp700ByRefContaining", query = "select mySnp700 from Snp700 mySnp700 where mySnp700.ref like ?1") })
@Table(schema = "NICKA", name = "SNP700")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "dev_crud_maker/org/irri/iric/portal/variety/domain", name = "Snp700")
@XmlRootElement(namespace = "dev_crud_maker/org/irri/iric/portal/variety/domain")
public class Snp700 implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer id;
	/**
	 */

	@Column(name = "CHR", length = 8)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String chr;
	/**
	 */

	@Column(name = "POS", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal pos;
	/**
	 */

	@Column(name = "NAME", length = 24)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 */

	@Column(name = "REF", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String ref;
	/**
	 */

	@Column(name = "ALT", length = 1)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String alt;
	/**
	 */

	@Column(name = "QSCORE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer qscore;
	/**
	 */

	@Column(name = "FILTER", length = 16)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String filter;
	/**
	 */

	@Column(name = "INFO", length = 64)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String info;
	/**
	 */

	@Column(name = "FORMAT", length = 8)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String format;
	/**
	 */

	@Column(name = "HET")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer het;
	/**
	 */

	@Column(name = "AVG_QSCORE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer avgQscore;

	/**
	 */
	@OneToMany(mappedBy = "snp700", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<org.irri.iric.portal.variety.domain.Genotyp700> genotyp700s;

	/**
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 */
	public Integer getId() {
		return this.id;
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
	public void setRef(String ref) {
		this.ref = ref;
	}

	/**
	 */
	public String getRef() {
		return this.ref;
	}

	/**
	 */
	public void setAlt(String alt) {
		this.alt = alt;
	}

	/**
	 */
	public String getAlt() {
		return this.alt;
	}

	/**
	 */
	public void setQscore(Integer qscore) {
		this.qscore = qscore;
	}

	/**
	 */
	public Integer getQscore() {
		return this.qscore;
	}

	/**
	 */
	public void setFilter(String filter) {
		this.filter = filter;
	}

	/**
	 */
	public String getFilter() {
		return this.filter;
	}

	/**
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 */
	public String getInfo() {
		return this.info;
	}

	/**
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 */
	public String getFormat() {
		return this.format;
	}

	/**
	 */
	public void setHet(Integer het) {
		this.het = het;
	}

	/**
	 */
	public Integer getHet() {
		return this.het;
	}

	/**
	 */
	public void setAvgQscore(Integer avgQscore) {
		this.avgQscore = avgQscore;
	}

	/**
	 */
	public Integer getAvgQscore() {
		return this.avgQscore;
	}

	/**
	 */
	public void setGenotyp700s(Set<Genotyp700> genotyp700s) {
		this.genotyp700s = genotyp700s;
	}

	/**
	 */
	@JsonIgnore
	public Set<Genotyp700> getGenotyp700s() {
		if (genotyp700s == null) {
			genotyp700s = new java.util.LinkedHashSet<org.irri.iric.portal.variety.domain.Genotyp700>();
		}
		return genotyp700s;
	}

	/**
	 */
	public Snp700() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Snp700 that) {
		setId(that.getId());
		setChr(that.getChr());
		setPos(that.getPos());
		setName(that.getName());
		setRef(that.getRef());
		setAlt(that.getAlt());
		setQscore(that.getQscore());
		setFilter(that.getFilter());
		setInfo(that.getInfo());
		setFormat(that.getFormat());
		setHet(that.getHet());
		setAvgQscore(that.getAvgQscore());
		setGenotyp700s(new java.util.LinkedHashSet<org.irri.iric.portal.variety.domain.Genotyp700>(that.getGenotyp700s()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("chr=[").append(chr).append("] ");
		buffer.append("pos=[").append(pos).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("ref=[").append(ref).append("] ");
		buffer.append("alt=[").append(alt).append("] ");
		buffer.append("qscore=[").append(qscore).append("] ");
		buffer.append("filter=[").append(filter).append("] ");
		buffer.append("info=[").append(info).append("] ");
		buffer.append("format=[").append(format).append("] ");
		buffer.append("het=[").append(het).append("] ");
		buffer.append("avgQscore=[").append(avgQscore).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((id == null) ? 0 : id.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Snp700))
			return false;
		Snp700 equalCheck = (Snp700) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
