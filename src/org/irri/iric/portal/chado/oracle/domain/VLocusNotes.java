package org.irri.iric.portal.chado.oracle.domain;

import java.io.Serializable;

import java.math.BigDecimal;


import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;



import org.irri.iric.portal.domain.MultiReferenceLocus;

/**
 */

@Entity(name="VLocusNotes")
@NamedQueries({
		@NamedQuery(name = "findAllVLocusNotess", query = "select myVLocusNotes from VLocusNotes myVLocusNotes"),
		@NamedQuery(name = "findVLocusNotesByCommonName", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.commonName = ?1"),
		@NamedQuery(name = "findVLocusNotesByCommonNameContaining", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.commonName like ?1"),
		@NamedQuery(name = "findVLocusNotesByContigId", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.contigId = ?1"),
		@NamedQuery(name = "findVLocusNotesByContigName", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.contigName = ?1"),
		@NamedQuery(name = "findVLocusNotesByContigNameContaining", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.contigName like ?1"),
		
		@NamedQuery(name = "findVLocusNotesByNotesContaining", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.notes like ?1"),		
		
		@NamedQuery(name = "findVLocusNotesByFeatureId", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.featureId = ?1"),
		@NamedQuery(name = "findVLocusNotesByFmax", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.fmax = ?1"),
		@NamedQuery(name = "findVLocusNotesByFmin", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.fmin = ?1"),
		@NamedQuery(name = "findVLocusNotesByName", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where lower(myVLocusNotes.name) = lower(?1)"),
		@NamedQuery(name = "findVLocusNotesByNameContaining", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where lower(myVLocusNotes.name) like lower(?1)"),
		@NamedQuery(name = "findVLocusNotesByOrganismId", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.organismId = ?1"),
		@NamedQuery(name = "findVLocusNotesByPrimaryKey", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.featureId = ?1"),
		@NamedQuery(name = "findVLocusNotesByStrand", query = "select myVLocusNotes from VLocusNotes myVLocusNotes where myVLocusNotes.strand = ?1") })
@Table(schema = "IRIC", name = "V_LOCUS_NOTES")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VLocusNotes")
public class VLocusNotes implements Serializable, MultiReferenceLocus, Comparable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "FEATURE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal featureId;
	/**
	 */

	@Column(name = "NAME")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
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

	@Column(name = "STRAND", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer strand;
	/**
	 */

	@Column(name = "CONTIG_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal contigId;
	/**
	 */

	@Column(name = "CONTIG_NAME")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String contigName;
	/**
	 */

	/*
	@Column(name = "NOTES")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	//byte[] notes;
	Clob notes;
	 */
	@Column(name = "NOTES")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String notes;

	
	@Column(name = "ORGANISM_ID", precision = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal organismId;
	/**
	 */

	@Column(name = "COMMON_NAME")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String commonName;

	/**
	 */
	public void setFeatureId(BigDecimal featureId) {
		this.featureId = featureId;
	}

	/**
	 */
	public BigDecimal getFeatureId() {
		return this.featureId;
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
	public void setFmin(BigDecimal fmin) {
		this.fmin = fmin;
	}

	/**
	 */
	public Integer getFmin() {
		return this.fmin.intValue();
	}

	/**
	 */
	public void setFmax(BigDecimal fmax) {
		this.fmax = fmax;
	}

	/**
	 */
	public Integer getFmax() {
		return this.fmax.intValue();
	}

	/**
	 */
	public void setStrand(Integer strand) {
		this.strand = strand.intValue();
	}

	/**
	 */
	public Integer getStrand() {
		return this.strand;
	}

	/**
	 */
	public void setContigId(BigDecimal contigId) {
		this.contigId = contigId;
	}

	/**
	 */
	public BigDecimal getContigId() {
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
	//public void setNotes(byte[] notes) {
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 */
	/*
	public byte[] getNotes() {
		return this.notes;
	}
	*/
	public String getNotes() {
		return this.notes;
		/*
		try {
		return AppContext.clobStringConversion( this.notes );
		} catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
		*/
	}
	
	 
	/**
	 */
	public void setOrganismId(BigDecimal organismId) {
		this.organismId = organismId;
	}

	/**
	 */
	public BigDecimal getOrganismId() {
		return this.organismId;
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
	public VLocusNotes() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VLocusNotes that) {
		setFeatureId(that.getFeatureId());
		setName(that.getName());
		setFmin(BigDecimal.valueOf( that.getFmin() ));
		setFmax(BigDecimal.valueOf( that.getFmax()) );
		setStrand(that.getStrand());
		setContigId(that.getContigId());
		setContigName(that.getContigName());
		//setNotes( new Clob( that.getNotes() ));
		setNotes( that.getNotes() );
		setOrganismId(that.getOrganismId());
		setCommonName(that.getCommonName());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("featureId=[").append(featureId).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("fmin=[").append(fmin).append("] ");
		buffer.append("fmax=[").append(fmax).append("] ");
		buffer.append("strand=[").append(strand).append("] ");
		buffer.append("contigId=[").append(contigId).append("] ");
		buffer.append("contigName=[").append(contigName).append("] ");
		buffer.append("notes=[").append(notes).append("] ");
		buffer.append("organismId=[").append(organismId).append("] ");
		buffer.append("commonName=[").append(commonName).append("] ");

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
		int ret = l1.getOrganismId().compareTo(l2.getOrganismId());
		if(ret!=0) return ret;
		ret = l1.getContig().compareTo(l2.getContig());
		if(ret!=0) return ret;
		ret = l1.getFmin().compareTo(l2.getFmin());
		if(ret!=0) return ret;
		ret = l1.getFmax().compareTo(l2.getFmax());
		return ret;
		
	}

	@Override
	public String getOrganism() {
		// TODO Auto-generated method stub
		return this.organismId.toString();
	}
	
	
	
}
