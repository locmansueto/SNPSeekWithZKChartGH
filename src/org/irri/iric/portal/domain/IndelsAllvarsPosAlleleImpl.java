package org.irri.iric.portal.domain;

import java.math.BigDecimal;

import org.irri.iric.portal.AppContext;

/**
 * Implementation for IndelsAllvarsPos
 * @author LMansueto
 *
 */
public class IndelsAllvarsPosAlleleImpl implements IndelsAllvarsPos {

	private BigDecimal pos;
	private BigDecimal alleleIndex;
	private BigDecimal snpFeatureId;
	//private Integer dellength;
	private Integer maxdellength;
	private Integer maxinslength;
	
	private String refnuc;
	private String contig;
	private String insString;
	
	
	
	public IndelsAllvarsPosAlleleImpl(BigDecimal pos, BigDecimal alleleIndex,
			BigDecimal snpFeatureId, Integer maxdellength, String refnuc,
			String contig, String insString, Integer maxinslength) {
		super();
		this.pos = pos;
		this.alleleIndex = alleleIndex;
		this.snpFeatureId = snpFeatureId;
		//this.dellength = dellength;
		this.refnuc = refnuc;
		this.contig = contig;
		this.insString = insString;
		this.maxinslength = maxinslength;
		this.maxdellength=maxdellength;
	}

	@Override
	public BigDecimal getPos() {
		// TODO Auto-generated method stub
		return pos;
	}

	@Override
	public String getRefnuc() {
		// TODO Auto-generated method stub
		return refnuc;
	}

	@Override
	public void setRefnuc(String refnuc) {
		// TODO Auto-generated method stub
		this.refnuc=refnuc;
	}

	@Override
	public String getContig() {
		// TODO Auto-generated method stub
		return contig;
	}

	@Override
	public BigDecimal getAlleleIndex() {
		// TODO Auto-generated method stub
		return alleleIndex;
	}

	@Override
	public BigDecimal getSnpFeatureId() {
		// TODO Auto-generated method stub
		return snpFeatureId;
	}

	@Override
	public Integer getMaxDellength() {
		// TODO Auto-generated method stub
		return maxdellength;
	}
	
//	@Override
//	public Integer getDellength() {
//		// TODO Auto-generated method stub
//		try {
//			int dellen=Integer.valueOf(insString);
//			return dellen;
//		} catch(Exception ex) {}
//		return 0;
//	}


	@Override
	public String getInsString() {
		// TODO Auto-generated method stub
		return insString;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		/*
		IndelsAllvarsPosAlleleImpl o=(IndelsAllvarsPosAlleleImpl)obj;
		
		//boolean equalinsert = (insString==null && o.getInsString()==null) || insString.equals(o.getInsString());
		//boolean equaldel = (dellength==null && o.getDellength()==null) || dellength.equals(o.getDellength());
		//return pos.equals(o.getPos()) && contig.equals(o.getContig()) && equalinsert && equaldel;
		//return toString().equals(o.toString());
		
		if (obj == this)
			return true;
		if (!(obj instanceof IndelsAllvarsPosAlleleImpl))
			return false;
		IndelsAllvarsPosAlleleImpl equalCheck = (IndelsAllvarsPosAlleleImpl) obj;
		if ((contig == null && equalCheck.contig != null) || (contig != null && equalCheck.contig == null))
			return false;
		if (contig != null && !contig.equals(equalCheck.contig))
			return false;
		if ((pos == null && equalCheck.pos != null) || (pos != null && equalCheck.pos == null))
			return false;
		if (pos != null && !pos.equals(equalCheck.pos))
			
		if ((insString == null && equalCheck.insString != null) || (insString != null && equalCheck.insString == null))
			return false;
		if (insString != null && !insString.equals(equalCheck.insString))
			return false;
		
		return true;
		*/
		return compareTo(obj)==0;
	}
	
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getClass() + ": " + contig + "-" +  pos + "-" + insString.toLowerCase();
				
	}
	
	

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		//return toString().hashCode();
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((contig == null) ? 0 : contig.hashCode()));
		result = (int) (prime * result + ((pos == null) ? 0 : pos.hashCode()));
		result = (int) (prime * result + ((insString == null) ? 0 : insString.hashCode()));
		return result;
	}

	@Override
	public Integer getMaxInsLength() {
		// TODO Auto-generated method stub
		return maxinslength;
	}

	@Override
	public BigDecimal getPosition() {
		// TODO Auto-generated method stub
		return this.pos;
	}

	@Override
	public String getRefcall() {
		// TODO Auto-generated method stub
		return this.refnuc;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		IndelsAllvarsPos obj=(IndelsAllvarsPos)o;
		int ret= getContig().compareTo(obj.getContig());
		if(ret==0) ret= getPosition().compareTo(obj.getPosition());
		
		if(o instanceof IndelsAllvarsPosAlleleImpl)
		{
			if(ret==0) ret=insString.compareTo( ((IndelsAllvarsPosAlleleImpl)o).getInsString() );
		}
		
		return ret;
	}

	@Override
	public Long getChr() {
		// TODO Auto-generated method stub
		return  Long.valueOf( AppContext.guessChrFromString(contig));
	}

	
	
	
}
