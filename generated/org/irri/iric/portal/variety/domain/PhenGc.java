package org.irri.iric.portal.variety.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */
@IdClass(org.irri.iric.portal.variety.domain.PhenGcPK.class)
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllPhenGcs", query = "select myPhenGc from PhenGc myPhenGc"),
		@NamedQuery(name = "findPhenGcByAc", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.ac = ?1"),
		@NamedQuery(name = "findPhenGcByAvgLength", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.avgLength = ?1"),
		@NamedQuery(name = "findPhenGcByAvgWidth", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.avgWidth = ?1"),
		@NamedQuery(name = "findPhenGcByBarcode", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.barcode = ?1"),
		@NamedQuery(name = "findPhenGcByBarcodeContaining", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.barcode like ?1"),
		@NamedQuery(name = "findPhenGcByChalkiness", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.chalkiness = ?1"),
		@NamedQuery(name = "findPhenGcByChk010", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.chk010 = ?1"),
		@NamedQuery(name = "findPhenGcByChk1025", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.chk1025 = ?1"),
		@NamedQuery(name = "findPhenGcByChk2550", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.chk2550 = ?1"),
		@NamedQuery(name = "findPhenGcByChk5075", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.chk5075 = ?1"),
		@NamedQuery(name = "findPhenGcByChkGt75", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.chkGt75 = ?1"),
		@NamedQuery(name = "findPhenGcByDesignation", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.designation = ?1"),
		@NamedQuery(name = "findPhenGcByDesignationContaining", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.designation like ?1"),
		@NamedQuery(name = "findPhenGcByEntno", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.entno = ?1"),
		@NamedQuery(name = "findPhenGcByGc", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.gc = ?1"),
		@NamedQuery(name = "findPhenGcByGid", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.gid = ?1"),
		@NamedQuery(name = "findPhenGcByGtDsc", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.gtDsc = ?1"),
		@NamedQuery(name = "findPhenGcByMps", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.mps = ?1"),
		@NamedQuery(name = "findPhenGcByMpsChk", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.mpsChk = ?1"),
		@NamedQuery(name = "findPhenGcByName1", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.name1 = ?1"),
		@NamedQuery(name = "findPhenGcByName1Containing", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.name1 like ?1"),
		@NamedQuery(name = "findPhenGcByName2", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.name2 = ?1"),
		@NamedQuery(name = "findPhenGcByName2Containing", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.name2 like ?1"),
		@NamedQuery(name = "findPhenGcByPrimaryKey", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.entno = ?1 and myPhenGc.gid = ?2"),
		@NamedQuery(name = "findPhenGcByRemarks", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.remarks = ?1"),
		@NamedQuery(name = "findPhenGcByRemarksContaining", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.remarks like ?1"),
		@NamedQuery(name = "findPhenGcByShape", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.shape = ?1"),
		@NamedQuery(name = "findPhenGcByStdLength", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.stdLength = ?1"),
		@NamedQuery(name = "findPhenGcByStdWidth", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.stdWidth = ?1"),
		@NamedQuery(name = "findPhenGcByTotalGrains", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.totalGrains = ?1"),
		@NamedQuery(name = "findPhenGcByWaxy", query = "select myPhenGc from PhenGc myPhenGc where myPhenGc.waxy = ?1") })
@Table(schema = "NICKA", name = "PHEN_GC")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "dev_crud_maker/org/irri/iric/portal/variety/domain", name = "PhenGc")
public class PhenGc implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "ENTNO")
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer entno;
	/**
	 */

	@Column(name = "GID")
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer gid;
	/**
	 */

	@Column(name = "DESIGNATION", length = 128)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String designation;
	/**
	 */

	@Column(name = "BARCODE", length = 128)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String barcode;
	/**
	 */

	@Column(name = "TOTAL_GRAINS")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer totalGrains;
	/**
	 */

	@Column(name = "CHALKINESS")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer chalkiness;
	/**
	 */

	@Column(name = "CHK_0_10")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer chk010;
	/**
	 */

	@Column(name = "CHK_10_25")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer chk1025;
	/**
	 */

	@Column(name = "CHK_25_50")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer chk2550;
	/**
	 */

	@Column(name = "CHK_50_75")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer chk5075;
	/**
	 */

	@Column(name = "CHK_GT_75")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer chkGt75;
	/**
	 */

	@Column(name = "AVG_LENGTH")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer avgLength;
	/**
	 */

	@Column(name = "STD_LENGTH")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer stdLength;
	/**
	 */

	@Column(name = "AVG_WIDTH")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer avgWidth;
	/**
	 */

	@Column(name = "STD_WIDTH")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer stdWidth;
	/**
	 */

	@Column(name = "SHAPE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer shape;
	/**
	 */

	@Column(name = "WAXY")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer waxy;
	/**
	 */

	@Column(name = "AC")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer ac;
	/**
	 */

	@Column(name = "GT_DSC")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer gtDsc;
	/**
	 */

	@Column(name = "GC")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer gc;
	/**
	 */

	@Column(name = "MPS")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer mps;
	/**
	 */

	@Column(name = "MPS_CHK")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer mpsChk;
	/**
	 */

	@Column(name = "REMARKS", length = 256)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String remarks;
	/**
	 */

	@Column(name = "NAME1", length = 64)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name1;
	/**
	 */

	@Column(name = "NAME2", length = 64)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name2;

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
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 */
	public String getDesignation() {
		return this.designation;
	}

	/**
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	/**
	 */
	public String getBarcode() {
		return this.barcode;
	}

	/**
	 */
	public void setTotalGrains(Integer totalGrains) {
		this.totalGrains = totalGrains;
	}

	/**
	 */
	public Integer getTotalGrains() {
		return this.totalGrains;
	}

	/**
	 */
	public void setChalkiness(Integer chalkiness) {
		this.chalkiness = chalkiness;
	}

	/**
	 */
	public Integer getChalkiness() {
		return this.chalkiness;
	}

	/**
	 */
	public void setChk010(Integer chk010) {
		this.chk010 = chk010;
	}

	/**
	 */
	public Integer getChk010() {
		return this.chk010;
	}

	/**
	 */
	public void setChk1025(Integer chk1025) {
		this.chk1025 = chk1025;
	}

	/**
	 */
	public Integer getChk1025() {
		return this.chk1025;
	}

	/**
	 */
	public void setChk2550(Integer chk2550) {
		this.chk2550 = chk2550;
	}

	/**
	 */
	public Integer getChk2550() {
		return this.chk2550;
	}

	/**
	 */
	public void setChk5075(Integer chk5075) {
		this.chk5075 = chk5075;
	}

	/**
	 */
	public Integer getChk5075() {
		return this.chk5075;
	}

	/**
	 */
	public void setChkGt75(Integer chkGt75) {
		this.chkGt75 = chkGt75;
	}

	/**
	 */
	public Integer getChkGt75() {
		return this.chkGt75;
	}

	/**
	 */
	public void setAvgLength(Integer avgLength) {
		this.avgLength = avgLength;
	}

	/**
	 */
	public Integer getAvgLength() {
		return this.avgLength;
	}

	/**
	 */
	public void setStdLength(Integer stdLength) {
		this.stdLength = stdLength;
	}

	/**
	 */
	public Integer getStdLength() {
		return this.stdLength;
	}

	/**
	 */
	public void setAvgWidth(Integer avgWidth) {
		this.avgWidth = avgWidth;
	}

	/**
	 */
	public Integer getAvgWidth() {
		return this.avgWidth;
	}

	/**
	 */
	public void setStdWidth(Integer stdWidth) {
		this.stdWidth = stdWidth;
	}

	/**
	 */
	public Integer getStdWidth() {
		return this.stdWidth;
	}

	/**
	 */
	public void setShape(Integer shape) {
		this.shape = shape;
	}

	/**
	 */
	public Integer getShape() {
		return this.shape;
	}

	/**
	 */
	public void setWaxy(Integer waxy) {
		this.waxy = waxy;
	}

	/**
	 */
	public Integer getWaxy() {
		return this.waxy;
	}

	/**
	 */
	public void setAc(Integer ac) {
		this.ac = ac;
	}

	/**
	 */
	public Integer getAc() {
		return this.ac;
	}

	/**
	 */
	public void setGtDsc(Integer gtDsc) {
		this.gtDsc = gtDsc;
	}

	/**
	 */
	public Integer getGtDsc() {
		return this.gtDsc;
	}

	/**
	 */
	public void setGc(Integer gc) {
		this.gc = gc;
	}

	/**
	 */
	public Integer getGc() {
		return this.gc;
	}

	/**
	 */
	public void setMps(Integer mps) {
		this.mps = mps;
	}

	/**
	 */
	public Integer getMps() {
		return this.mps;
	}

	/**
	 */
	public void setMpsChk(Integer mpsChk) {
		this.mpsChk = mpsChk;
	}

	/**
	 */
	public Integer getMpsChk() {
		return this.mpsChk;
	}

	/**
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 */
	public String getRemarks() {
		return this.remarks;
	}

	/**
	 */
	public void setName1(String name1) {
		this.name1 = name1;
	}

	/**
	 */
	public String getName1() {
		return this.name1;
	}

	/**
	 */
	public void setName2(String name2) {
		this.name2 = name2;
	}

	/**
	 */
	public String getName2() {
		return this.name2;
	}

	/**
	 */
	public PhenGc() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(PhenGc that) {
		setEntno(that.getEntno());
		setGid(that.getGid());
		setDesignation(that.getDesignation());
		setBarcode(that.getBarcode());
		setTotalGrains(that.getTotalGrains());
		setChalkiness(that.getChalkiness());
		setChk010(that.getChk010());
		setChk1025(that.getChk1025());
		setChk2550(that.getChk2550());
		setChk5075(that.getChk5075());
		setChkGt75(that.getChkGt75());
		setAvgLength(that.getAvgLength());
		setStdLength(that.getStdLength());
		setAvgWidth(that.getAvgWidth());
		setStdWidth(that.getStdWidth());
		setShape(that.getShape());
		setWaxy(that.getWaxy());
		setAc(that.getAc());
		setGtDsc(that.getGtDsc());
		setGc(that.getGc());
		setMps(that.getMps());
		setMpsChk(that.getMpsChk());
		setRemarks(that.getRemarks());
		setName1(that.getName1());
		setName2(that.getName2());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("entno=[").append(entno).append("] ");
		buffer.append("gid=[").append(gid).append("] ");
		buffer.append("designation=[").append(designation).append("] ");
		buffer.append("barcode=[").append(barcode).append("] ");
		buffer.append("totalGrains=[").append(totalGrains).append("] ");
		buffer.append("chalkiness=[").append(chalkiness).append("] ");
		buffer.append("chk010=[").append(chk010).append("] ");
		buffer.append("chk1025=[").append(chk1025).append("] ");
		buffer.append("chk2550=[").append(chk2550).append("] ");
		buffer.append("chk5075=[").append(chk5075).append("] ");
		buffer.append("chkGt75=[").append(chkGt75).append("] ");
		buffer.append("avgLength=[").append(avgLength).append("] ");
		buffer.append("stdLength=[").append(stdLength).append("] ");
		buffer.append("avgWidth=[").append(avgWidth).append("] ");
		buffer.append("stdWidth=[").append(stdWidth).append("] ");
		buffer.append("shape=[").append(shape).append("] ");
		buffer.append("waxy=[").append(waxy).append("] ");
		buffer.append("ac=[").append(ac).append("] ");
		buffer.append("gtDsc=[").append(gtDsc).append("] ");
		buffer.append("gc=[").append(gc).append("] ");
		buffer.append("mps=[").append(mps).append("] ");
		buffer.append("mpsChk=[").append(mpsChk).append("] ");
		buffer.append("remarks=[").append(remarks).append("] ");
		buffer.append("name1=[").append(name1).append("] ");
		buffer.append("name2=[").append(name2).append("] ");

		return buffer.toString();
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
		if (!(obj instanceof PhenGc))
			return false;
		PhenGc equalCheck = (PhenGc) obj;
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
}
