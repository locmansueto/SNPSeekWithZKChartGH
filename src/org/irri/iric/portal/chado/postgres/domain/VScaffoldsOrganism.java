package org.irri.iric.portal.chado.postgres.domain;

import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.Scaffold;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "PGfindAllVScaffoldsOrganisms", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism"),
		@NamedQuery(name = "PGfindVScaffoldsOrganismByCommonName", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where myVScaffoldsOrganism.commonName = ?1"),
		@NamedQuery(name = "PGfindVScaffoldsOrganismByCommonNameContaining", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where myVScaffoldsOrganism.commonName like ?1"),
		@NamedQuery(name = "PGfindVScaffoldsOrganismByFeatureId", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where myVScaffoldsOrganism.featureId = ?1"),
		@NamedQuery(name = "PGfindVScaffoldsOrganismByFtype", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where myVScaffoldsOrganism.ftype = ?1"),
		@NamedQuery(name = "PGfindVScaffoldsOrganismByFtypeContaining", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where myVScaffoldsOrganism.ftype like ?1"),

		@NamedQuery(name = "PGfindVScaffoldsOrganismByNameCommonName", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where myVScaffoldsOrganism.name = ?1 and myVScaffoldsOrganism.commonName = ?2"),
		@NamedQuery(name = "PGfindVScaffoldsOrganismByUniquenameCommonName", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where upper(myVScaffoldsOrganism.uniquename) = upper(?1)  and myVScaffoldsOrganism.commonName = ?2"),
		@NamedQuery(name = "PGfindVScaffoldsOrganismByUniquenameOrganismId", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where upper(myVScaffoldsOrganism.uniquename) = upper(?1)  and myVScaffoldsOrganism.organismId = ?2"),

		@NamedQuery(name = "PGfindVScaffoldsOrganismByName", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where myVScaffoldsOrganism.name = ?1"),
		@NamedQuery(name = "PGfindVScaffoldsOrganismByNameContaining", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where myVScaffoldsOrganism.name like ?1"),
		@NamedQuery(name = "PGfindVScaffoldsOrganismByOrganismId", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where myVScaffoldsOrganism.organismId = ?1"),
		@NamedQuery(name = "PGfindVScaffoldsOrganismByPrimaryKey", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where myVScaffoldsOrganism.featureId = ?1"),
		@NamedQuery(name = "PGfindVScaffoldsOrganismBySeqlen", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where myVScaffoldsOrganism.seqlen = ?1"),
		@NamedQuery(name = "PGfindVScaffoldsOrganismByTypeId", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where myVScaffoldsOrganism.typeId = ?1"),
		@NamedQuery(name = "PGfindVScaffoldsOrganismByUniquename", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where myVScaffoldsOrganism.uniquename = ?1"),
		@NamedQuery(name = "PGfindVScaffoldsOrganismByUniquenameContaining", query = "select myVScaffoldsOrganism from VScaffoldsOrganism myVScaffoldsOrganism where myVScaffoldsOrganism.uniquename like ?1") })



@Table(schema = "public", name = "v_scaffolds_organism")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/postgres/domain", name = "VScaffoldsOrganism")
public class VScaffoldsOrganism implements Serializable, Scaffold {
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

	@Column(name = "name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;
	/**
	 */

	@Column(name = "organism_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Long organismId;
	/**
	 */

	@Column(name = "seqlen")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer seqlen;
	/**
	 */

	@Column(name = "ftype", length = 1024)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String ftype;
	/**
	 */

	@Column(name = "type_id")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Long typeId;
	/**
	 */

	@Column(name = "uniquename")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String uniquename;

	/**
	 */
	public void setFeatureId(Long featureId) {
		this.featureId = featureId;
	}

	/**
	 */
	public BigDecimal getFeatureId() {
		return BigDecimal.valueOf(this.featureId);
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
	public void setSeqlen(Integer seqlen) {
		this.seqlen = seqlen;
	}

	/**
	 */
	public Integer getSeqlen() {
		return this.seqlen;
	}

	/**
	 */
	public void setFtype(String ftype) {
		this.ftype = ftype;
	}

	/**
	 */
	public String getFtype() {
		return this.ftype;
	}

	/**
	 */
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	/**
	 */
	public Long getTypeId() {
		return this.typeId;
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
	public VScaffoldsOrganism() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VScaffoldsOrganism that) {
		setFeatureId(that.getFeatureId().longValue());
		setCommonName(that.getCommonName());
		setName(that.getName());
		setOrganismId(that.getOrganismId());
		setSeqlen(that.getSeqlen());
		setFtype(that.getFtype());
		setTypeId(that.getTypeId());
		setUniquename(that.getUniquename());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("featureId=[").append(featureId).append("] ");
		buffer.append("commonName=[").append(commonName).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("organismId=[").append(organismId).append("] ");
		buffer.append("seqlen=[").append(seqlen).append("] ");
		buffer.append("ftype=[").append(ftype).append("] ");
		buffer.append("typeId=[").append(typeId).append("] ");
		buffer.append("uniquename=[").append(uniquename).append("] ");

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
		if (!(obj instanceof VScaffoldsOrganism))
			return false;
		VScaffoldsOrganism equalCheck = (VScaffoldsOrganism) obj;
		if ((featureId == null && equalCheck.featureId != null) || (featureId != null && equalCheck.featureId == null))
			return false;
		if (featureId != null && !featureId.equals(equalCheck.featureId))
			return false;
		return true;
	}

	@Override
	public long getLength() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
}
