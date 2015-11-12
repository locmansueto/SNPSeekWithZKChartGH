package org.irri.iric.portal.chado.domain;

import java.io.BufferedReader;
import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;
import java.sql.Clob;
import java.util.Map;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

import org.irri.iric.portal.domain.Position;
import org.irri.iric.portal.domain.SnpsStringAllvars;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllSnpcoreMismatchAlleless", query = "select mySnpcoreMismatchAlleles from SnpcoreMismatchAlleles mySnpcoreMismatchAlleles"),
		@NamedQuery(name = "findSnpcoreMismatchAllelesByIricStockId", query = "select mySnpcoreMismatchAlleles from SnpcoreMismatchAlleles mySnpcoreMismatchAlleles where mySnpcoreMismatchAlleles.iricStockId = ?1"),
		@NamedQuery(name = "findSnpcoreMismatchAllelesByMismatch", query = "select mySnpcoreMismatchAlleles from SnpcoreMismatchAlleles mySnpcoreMismatchAlleles where mySnpcoreMismatchAlleles.mismatch = ?1"),
		@NamedQuery(name = "findSnpcoreMismatchAllelesByPrimaryKey", query = "select mySnpcoreMismatchAlleles from SnpcoreMismatchAlleles mySnpcoreMismatchAlleles where mySnpcoreMismatchAlleles.iricStockId = ?1") })




@Table(schema = "LMANSUETO", name = "SNPCORE_MISMATCH_ALLELES")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "SnpcoreMismatchAlleles")
public class SnpcoreMismatchAlleles implements Serializable, SnpsStringAllvars {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "IRIC_STOCK_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	BigDecimal iricStockId;
	/**
	 */

	@Column(name = "MISMATCH")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal mismatch;
	/**
	 */

	@Column(name = "ALLELES")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	//byte[] alleles;
	Clob alleles;

	/**
	 */
	public void setIricStockId(BigDecimal iricStockId) {
		this.iricStockId = iricStockId;
	}

	/**
	 */
	public BigDecimal getIricStockId() {
		return this.iricStockId;
	}

	/**
	 */
	public void setMismatch(BigDecimal mismatch) {
		this.mismatch = mismatch;
	}

	/**
	 */
	public BigDecimal getMismatch() {
		return this.mismatch;
	}

	/**
	 */
	public void setAlleles(Clob alleles) {
		this.alleles = alleles;
	}

	/**
	 */
	public Clob getAlleles() {
		return this.alleles;
	}

	/**
	 */
	public SnpcoreMismatchAlleles() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(SnpcoreMismatchAlleles that) {
		setIricStockId(that.getIricStockId());
		setMismatch(that.getMismatch());
		setAlleles(that.getAlleles());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("iricStockId=[").append(iricStockId).append("] ");
		buffer.append("mismatch=[").append(mismatch).append("] ");
		buffer.append("alleles=[").append(alleles).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((iricStockId == null) ? 0 : iricStockId.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof SnpcoreMismatchAlleles))
			return false;
		SnpcoreMismatchAlleles equalCheck = (SnpcoreMismatchAlleles) obj;
		if ((iricStockId == null && equalCheck.iricStockId != null) || (iricStockId != null && equalCheck.iricStockId == null))
			return false;
		if (iricStockId != null && !iricStockId.equals(equalCheck.iricStockId))
			return false;
		return true;
	}

	@Override
	public BigDecimal getVar() {
		// TODO Auto-generated method stub
		return this.getIricStockId();
	}

	@Override
	public Long getChr() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public BigDecimal getPos() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String getRefnuc() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public String getVarnuc() {
		// TODO Auto-generated method stub
		/*
		StringBuffer buff= new StringBuffer();
		for(int i=0; i<alleles.length; i++)
			buff.append(  Byte.toString(alleles[i]));
		System.out.println(buff.length() + " positions from ALLELE String");
		return buff.toString();
		*/
//		String text1 = new String(data, "UTF-8");   // if the charset is UTF-8  
		//String text2 = new String(data, "ISO-8859-1");   // if the charset is ISO Latin 1  
		/*
		try {
		System.out.println("UTF-8: " +  new String(getAlleles() , "UTF-8"));
		System.out.println("ISO-8859-1: "+ new String(getAlleles() , "ISO-8859-1"));
		System.out.println("US-ASCII: "+ new String(getAlleles() , "US-ASCII"));
		System.out.println("UTF-16: " + new String(getAlleles() , "UTF-16"));
		
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		*/
		
		
		Clob clb = getAlleles();
		
		if (clb == null) return "";

		StringBuffer str = new StringBuffer();
		String strng;
		try {
			BufferedReader bufferRead = new BufferedReader(clb.getCharacterStream());
			while ((strng=bufferRead.readLine())!=null)
				str.append(strng);
			//System.out.println(str.length() + " positions from ALLELE String");
			return str.toString();
		} catch(Exception ex) {
			ex.printStackTrace();
			return "";
		}
		
			 
	}



	@Override
	public Set getNonsynPosset() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Position, Character> getMapPos2Allele2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set getDonorPosset() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set getAcceptorPosset() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContig() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
}
