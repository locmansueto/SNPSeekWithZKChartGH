package org.irri.iric.portal.chado.postgres.domain;

import java.io.Serializable;
import java.lang.StringBuilder;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;


import org.irri.iric.portal.domain.Locus;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "PGfindAllVLocusNotess", query = "select myVLocusNotes from VLocusNotes myVLocusNotes"),
		@NamedQuery(name = "PGfindVLocusNotesByCommonName", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.commonName = ?1"),
		@NamedQuery(name = "PGfindVLocusNotesByCommonNameContaining", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.commonName like ?1"),
		@NamedQuery(name = "PGfindVLocusNotesByContigId", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.contigId = ?1"),
		@NamedQuery(name = "PGfindVLocusNotesByContigName", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.contigName = ?1"),
		@NamedQuery(name = "PGfindVLocusNotesByContigNameContaining", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.contigName like ?1"),
		@NamedQuery(name = "PGfindVLocusNotesByFeatureId", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.featureId = ?1"),
		@NamedQuery(name = "PGfindVLocusNotesByFmax", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.fmax = ?1"),
		@NamedQuery(name = "PGfindVLocusNotesByFmin", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.fmin = ?1"),
		@NamedQuery(name = "PGfindVLocusNotesByNotes", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.notes = ?1"),
		@NamedQuery(name = "PGfindVLocusNotesByNotesContaining", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.notes like ?1"),
		@NamedQuery(name = "PGfindVLocusNotesByOrganismId", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.organismId = ?1"),
		@NamedQuery(name = "PGfindVLocusNotesByPrimaryKey", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.featureId = ?1"),
		@NamedQuery(name = "PGfindVLocusNotesByStrand", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.strand = ?1") })
@Table(schema = "public", name = "v_locus_notes")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/postgres/domain", name = "VLocusNotes")
public class VLocusNotes implements Serializable, Locus, Comparable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "feature_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Long featureId;
	/**
	 */

	@Column(name = "common_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String commonName;
	/**
	 */

	@Column(name = "contig_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Long contigId;
	/**
	 */

	@Column(name = "contig_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String contigName;
	/**
	 */
	
	@Column(name = "name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;


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

	@Column(name = "organism_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Long organismId;
	/**
	 */

	@Column(name = "notes")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String notes;

	/**
	 */
	public void setFeatureId(Long featureId) {
		this.featureId = featureId;
	}

	/**
	 */
	public Long getFeatureId() {
		return this.featureId;
	}

	/**
	 */
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	/**
	 */
	public String getCommonName() {
		return this.commonName;
	}

	/**
	 */
	public void setContigId(Long contigId) {
		this.contigId = contigId;
	}

	/**
	 */
	public Long getContigId() {
		return this.contigId;
	}

	/**
	 */
	public void setContigName(String contigName) {
		this.contigName = contigName;
	}

	/**
	 */
	public String getContigName() {
		return this.contigName;
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
	public void setOrganismId(Long organismId) {
		this.organismId = organismId;
	}

	/**
	 */
	public Long getOrganismId() {
		return this.organismId;
	}

	/**
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 */
	public String getNotes() {
		return this.notes;
	}

	/**
	 */
	public VLocusNotes() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VLocusNotes that) {
		setFeatureId(that.getFeatureId());
		setCommonName(that.getCommonName());
		setContigId(that.getContigId());
		setContigName(that.getContigName());
		setFmin(that.getFmin());
		setFmax(that.getFmax());
		setStrand(that.getStrand());
		setOrganismId(that.getOrganismId());
		setNotes(that.getNotes());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("featureId=[").append(featureId).append("] ");
		buffer.append("commonName=[").append(commonName).append("] ");
		buffer.append("contigId=[").append(contigId).append("] ");
		buffer.append("contigName=[").append(contigName).append("] ");
		buffer.append("fmin=[").append(fmin).append("] ");
		buffer.append("fmax=[").append(fmax).append("] ");
		buffer.append("strand=[").append(strand).append("] ");
		buffer.append("organismId=[").append(organismId).append("] ");
		buffer.append("notes=[").append(notes).append("] ");

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
	/*
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VLocusNotes))
			return false;
		VLocusNotes equalCheck = (VLocusNotes) obj;
		if ((featureId == null && equalCheck.featureId != null) || (featureId != null && equalCheck.featureId == null))
			return false;
		if (featureId != null && !featureId.equals(equalCheck.featureId))
			return false;
		return true;
	}
	*/
	

	

	@Override
	public String getUniquename() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return compareTo(obj)==0;
	}

	@Override
	public String getChr() {
		// TODO Auto-generated method stub
		return getContig();
		/*
		try {
			return Integer.valueOf( this.getContig().toLowerCase().replace("chr0","").replace("chr","") );
		} catch(Exception ex) {
			ex.printStackTrace();
			AppContext.debug("can't convert contig " + getContig() + " to chromosome");
		}
		return null;
		*/
	}

	@Override
	public String getContig() {
		// TODO Auto-generated method stub
		return this.contigName;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return this.getNotes();
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		VLocusNotes l1=(VLocusNotes)this;
		VLocusNotes l2=(VLocusNotes)o;
		int ret = l1.getContig().compareTo(l2.getContig());
		if(ret!=0) return ret;
		ret = l1.getFmin().compareTo(l2.getFmin());
		if(ret!=0) return ret;
		ret = l1.getFmax().compareTo(l2.getFmax());
		if(ret!=0) return ret;
		ret = l1.getStrand().compareTo(l2.getStrand());
		if(ret!=0) return ret;
		ret = l1.name.compareTo(l2.name);
		return ret;
		
	}
	
}
