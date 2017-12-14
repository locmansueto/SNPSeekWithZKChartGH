package org.irri.iric.portal.chado.postgres.domain;

import java.io.Serializable;
import java.lang.StringBuilder;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.Gene;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllVGenes", query = "select myVGene from VGene myVGene"),
		@NamedQuery(name = "findVGeneByChr", query = "select myVGene from VGene myVGene where myVGene.chr = ?1"),
		@NamedQuery(name = "findVGeneByChrContaining", query = "select myVGene from VGene myVGene where myVGene.chr like ?1"),
		@NamedQuery(name = "findVGeneByFmax", query = "select myVGene from VGene myVGene where myVGene.fmax = ?1"),
		@NamedQuery(name = "findVGeneByFmin", query = "select myVGene from VGene myVGene where myVGene.fmin = ?1"),
		@NamedQuery(name = "findVGeneByGeneId", query = "select myVGene from VGene myVGene where myVGene.geneId = ?1"),
		@NamedQuery(name = "findVGeneByName", query = "select myVGene from VGene myVGene where myVGene.name = ?1"),
		@NamedQuery(name = "findVGeneByNameContaining", query = "select myVGene from VGene myVGene where myVGene.name like ?1"),
		@NamedQuery(name = "findVGeneByOrganismId", query = "select myVGene from VGene myVGene where myVGene.organismId = ?1"),
		
		
		@NamedQuery(name = "findVGeneByNameOrg", query = "select myVGene from VGene myVGene where upper(myVGene.name) = upper(?1) and myVGene.organismId=?2"),
		@NamedQuery(name = "findVGeneByOrg", query = "select myVGene from VGene myVGene where myVGene.organismId= ?1"),
		
		
		@NamedQuery(name = "findVGeneByPhase", query = "select myVGene from VGene myVGene where myVGene.phase = ?1"),
		@NamedQuery(name = "findVGeneByPrimaryKey", query = "select myVGene from VGene myVGene where myVGene.geneId = ?1"),
		@NamedQuery(name = "findVGeneByStrand", query = "select myVGene from VGene myVGene where myVGene.strand = ?1") })
@Table(schema = "public", name = "v_gene")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/postgres/domain", name = "VGene")
public class VGene implements Serializable, Gene {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "gene_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer geneId;
	/**
	 */

	@Column(name = "name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 */

	@Column(name = "chr")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String chr;
	/**
	 */

	@Column(name = "fmin")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer fmin;
	/**
	 */

	@Column(name = "fmax")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer fmax;
	/**
	 */

	@Column(name = "strand")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer strand;
	/**
	 */

	@Column(name = "phase")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer phase;
	/**
	 */

	@Column(name = "organism_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer organismId;

	/**
	 */
	public void setGeneId(Integer geneId) {
		this.geneId = geneId;
	}

	/**
	 */
	public Integer getGeneId() {
		return this.geneId;
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
	public void setFmin(Integer fmin) {
		this.fmin = fmin;
	}

	/**
	 */
	public Integer getFmin() {
		return this.fmin;
	}

	/**
	 */
	public void setFmax(Integer fmax) {
		this.fmax = fmax;
	}

	/**
	 */
	public Integer getFmax() {
		return this.fmax;
	}

	/**
	 */
	public void setStrand(Integer strand) {
		this.strand = strand;
	}

	/**
	 */
	public Integer getStrand() {
		return this.strand;
	}

	/**
	 */
	public void setPhase(Integer phase) {
		this.phase = phase;
	}

	/**
	 */
	public Integer getPhase() {
		return this.phase;
	}

	/**
	 */
	public void setOrganismId(Integer organismId) {
		this.organismId = organismId;
	}

	/**
	 */
	public Integer getOrganismId() {
		return this.organismId;
	}

	/**
	 */
	public VGene() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VGene that) {
		setGeneId(that.getGeneId());
		setName(that.getName());
		setChr(that.getChr());
		setFmin(that.getFmin());
		setFmax(that.getFmax());
		setStrand(that.getStrand());
		setPhase(that.getPhase());
		setOrganismId(that.getOrganismId());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("geneId=[").append(geneId).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("chr=[").append(chr).append("] ");
		buffer.append("fmin=[").append(fmin).append("] ");
		buffer.append("fmax=[").append(fmax).append("] ");
		buffer.append("strand=[").append(strand).append("] ");
		buffer.append("phase=[").append(phase).append("] ");
		buffer.append("organismId=[").append(organismId).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((geneId == null) ? 0 : geneId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VGene))
			return false;
		VGene equalCheck = (VGene) obj;
		if ((geneId == null && equalCheck.geneId != null) || (geneId != null && equalCheck.geneId == null))
			return false;
		if (geneId != null && !geneId.equals(equalCheck.geneId))
			return false;
		return true;
	}
	
	
	@Override
	public String getUniquename() {
		// TODO Auto-generated method stub
		return this.getName();
	}

	@Override
	public String getContig() {
		// TODO Auto-generated method stub
		try {
			Integer chrnum = Integer.valueOf(chr);
			if(chrnum>9)
				return "chr0" + chrnum;
			else return "chr" + chrnum;
		} catch (Exception ex) {
			//ex.printStackTrace();
		}
		return chr;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
