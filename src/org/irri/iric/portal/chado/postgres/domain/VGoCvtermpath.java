package org.irri.iric.portal.chado.postgres.domain;

import java.io.Serializable;
import java.lang.StringBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.CvTermUniqueValues;

/**
 */

@Entity(name="VGoCvtermpath")
@NamedQueries({
		@NamedQuery(name = "PGfindAllVGoCvtermpaths", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath"),
		@NamedQuery(name = "PGfindVGoCvtermpathByCvName", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.cvName = ?1"),
		@NamedQuery(name = "PGfindVGoCvtermpathByCvNameContaining", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.cvName like ?1"),
		
		@NamedQuery(name = "PGfindVGoCvtermpathByObjCvtermPosMaxdist", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.cvName=?1 and  myVGoCvtermpath.objCvterm = ?2 and ((myVGoCvtermpath.pathdistance>0 and myVGoCvtermpath.pathdistance<=?3) or myVGoCvtermpath.pathdistance is null) order by myVGoCvtermpath.pathdistance, myVGoCvtermpath.subjCvterm"),
		@NamedQuery(name = "PGfindVGoCvtermpathByObjCvtermNegMaxdist", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where  myVGoCvtermpath.cvName=?1 and  myVGoCvtermpath.objCvterm = ?2 and myVGoCvtermpath.pathdistance<0 and myVGoCvtermpath.pathdistance>=?3 order by myVGoCvtermpath.pathdistance desc, myVGoCvtermpath.subjCvterm"),
		@NamedQuery(name = "PGfindVGoCvtermpathByObjCvtermPosDist", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.cvName=?1 and  myVGoCvtermpath.objCvterm = ?2 and ( myVGoCvtermpath.pathdistance>0  or  myVGoCvtermpath.pathdistance is null) order by myVGoCvtermpath.pathdistance, myVGoCvtermpath.subjCvterm"),
		@NamedQuery(name = "PGfindVGoCvtermpathByObjCvtermNegDist", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where  myVGoCvtermpath.cvName=?1 and myVGoCvtermpath.objCvterm = ?2 and myVGoCvtermpath.pathdistance<0  order by myVGoCvtermpath.pathdistance desc, myVGoCvtermpath.subjCvterm"),
		
		
		@NamedQuery(name = "PGfindVGoCvtermpathByCvtermId", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.cvtermId = ?1"),
		@NamedQuery(name = "PGfindVGoCvtermpathByObjAcc", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.objAcc = ?1"),
		@NamedQuery(name = "PGfindVGoCvtermpathByObjAccContaining", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.objAcc like ?1"),
		@NamedQuery(name = "PGfindVGoCvtermpathByObjCvterm", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.objCvterm = ?1"),
		@NamedQuery(name = "PGfindVGoCvtermpathByObjCvtermContaining", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.objCvterm like ?1"),
		@NamedQuery(name = "PGfindVGoCvtermpathByPathdistance", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.pathdistance = ?1"),
		@NamedQuery(name = "PGfindVGoCvtermpathByPrimaryKey", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.cvtermId = ?1"),
		@NamedQuery(name = "PGfindVGoCvtermpathBySubjAcc", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.subjAcc = ?1"),
		@NamedQuery(name = "PGfindVGoCvtermpathBySubjAccContaining", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.subjAcc like ?1"),
		@NamedQuery(name = "PGfindVGoCvtermpathBySubjCvterm", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.subjCvterm = ?1"),
		@NamedQuery(name = "PGfindVGoCvtermpathBySubjCvtermContaining", query = "select myVGoCvtermpath from VGoCvtermpath myVGoCvtermpath where myVGoCvtermpath.subjCvterm like ?1") })
@Table(schema = "public", name = "v_go_cvtermpath")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/postgres/domain", name = "VGoCvtermpath")
public class VGoCvtermpath implements Serializable, CvTermUniqueValues {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "cvterm_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Long cvtermId;
	/**
	 */

	@Column(name = "cv_name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String cvName;
	/**
	 */

	@Column(name = "obj_acc")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String objAcc;
	/**
	 */

	@Column(name = "obj_cvterm", length = 1024)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String objCvterm;
	/**
	 */

	@Column(name = "subj_acc")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String subjAcc;
	/**
	 */

	@Column(name = "subj_cvterm", length = 1024)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String subjCvterm;
	/**
	 */

	@Column(name = "pathdistance")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer pathdistance;

	/**
	 */
	public void setCvtermId(Long cvtermId) {
		this.cvtermId = cvtermId;
	}

	/**
	 */
	public Long getCvtermId() {
		return this.cvtermId;
	}

	/**
	 */
	public void setCvName(String cvName) {
		this.cvName = cvName;
	}

	/**
	 */
	public String getCvName() {
		return this.cvName;
	}

	/**
	 */
	public void setObjAcc(String objAcc) {
		this.objAcc = objAcc;
	}

	/**
	 */
	public String getObjAcc() {
		return this.objAcc;
	}

	/**
	 */
	public void setObjCvterm(String objCvterm) {
		this.objCvterm = objCvterm;
	}

	/**
	 */
	public String getObjCvterm() {
		return this.objCvterm;
	}

	/**
	 */
	public void setSubjAcc(String subjAcc) {
		this.subjAcc = subjAcc;
	}

	/**
	 */
	public String getSubjAcc() {
		return this.subjAcc;
	}

	/**
	 */
	public void setSubjCvterm(String subjCvterm) {
		this.subjCvterm = subjCvterm;
	}

	/**
	 */
	public String getSubjCvterm() {
		return this.subjCvterm;
	}

	/**
	 */
	public void setPathdistance(Integer pathdistance) {
		this.pathdistance = pathdistance;
	}

	/**
	 */
	public Integer getPathdistance() {
		return this.pathdistance;
	}

	/**
	 */
	public VGoCvtermpath() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(VGoCvtermpath that) {
		setCvtermId(that.getCvtermId());
		setCvName(that.getCvName());
		setObjAcc(that.getObjAcc());
		setObjCvterm(that.getObjCvterm());
		setSubjAcc(that.getSubjAcc());
		setSubjCvterm(that.getSubjCvterm());
		setPathdistance(that.getPathdistance());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("cvtermId=[").append(cvtermId).append("] ");
		buffer.append("cvName=[").append(cvName).append("] ");
		buffer.append("objAcc=[").append(objAcc).append("] ");
		buffer.append("objCvterm=[").append(objCvterm).append("] ");
		buffer.append("subjAcc=[").append(subjAcc).append("] ");
		buffer.append("subjCvterm=[").append(subjCvterm).append("] ");
		buffer.append("pathdistance=[").append(pathdistance).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((cvtermId == null) ? 0 : cvtermId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof VGoCvtermpath))
			return false;
		VGoCvtermpath equalCheck = (VGoCvtermpath) obj;
		if ((cvtermId == null && equalCheck.cvtermId != null) || (cvtermId != null && equalCheck.cvtermId == null))
			return false;
		if (cvtermId != null && !cvtermId.equals(equalCheck.cvtermId))
			return false;
		return true;
	}
	
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return this.getSubjCvterm();
	}
	
	
	
}
