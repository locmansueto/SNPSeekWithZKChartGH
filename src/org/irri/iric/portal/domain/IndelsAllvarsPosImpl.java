package org.irri.iric.portal.domain;

import java.math.BigDecimal;

import org.irri.iric.portal.AppContext;

public class IndelsAllvarsPosImpl implements IndelsAllvarsPos {

	private BigDecimal pos;
	//private BigDecimal alleleIndex;
	private BigDecimal snpFeatureId;
	private Integer maxdellength;
	private Integer maxinslength;
	
	private String refnuc;
	private String contig;
	private String insString;
	
	
	
	public IndelsAllvarsPosImpl(BigDecimal pos, BigDecimal snpFeatureId, Integer maxdellength, String refnuc,
			String contig, String insString, Integer maxinslength) {
		super();
		this.pos = pos;
		//this.alleleIndex = alleleIndex;
		this.snpFeatureId = snpFeatureId;
		this.maxdellength = maxdellength;
		this.refnuc = refnuc;
		this.contig = contig;
		this.insString = insString;
		this.maxinslength = maxinslength;
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
		return null;
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

	@Override
	public String getInsString() {
		// TODO Auto-generated method stub
		return insString;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		IndelsAllvarsPosImpl o=(IndelsAllvarsPosImpl)obj;
		
		//boolean equalinsert = (insString==null && o.getInsString()==null) || insString.equals(o.getInsString());
		//boolean equaldel = (dellength==null && o.getDellength()==null) || dellength.equals(o.getDellength());
		//return pos.equals(o.getPos()) && contig.equals(o.getContig()) && equalinsert && equaldel;
		//return toString().equals(o.toString());
		return compareTo(obj)==0;
	}
	
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getClass() + ": " + contig + "-" +  pos ;
				
	}
	
	

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((contig == null) ? 0 : contig.hashCode()));
		result = (int) (prime * result + ((pos == null) ? 0 : pos.hashCode()));
		result = (int) (prime * result + ((snpFeatureId == null) ? 0 : snpFeatureId.hashCode()));
		return result;
	}

	@Override
	public Integer getMaxInsLength() {
		// TODO Auto-generated method stub
		return maxinslength;
	}

	@Override
	public Integer getDellength() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getPosition() {
		// TODO Auto-generated method stub
		return this.pos;
	}

	@Override
	public String getRefcall() {
		// TODO Auto-generated method stub
		return this.refnuc ;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		IndelsAllvarsPos obj=(IndelsAllvarsPos)o;
		int ret= getContig().compareTo(obj.getContig());
		if(ret==0) ret= getPosition().compareTo(obj.getPosition());
		
		if(o instanceof IndelsAllvarsPosImpl) {
			if(ret==0) ret=this.snpFeatureId.compareTo(  obj.getSnpFeatureId() );
		}
		
		return ret;
	}

	@Override
	public Long getChr() {
		// TODO Auto-generated method stub
		return Long.valueOf( AppContext.guessChrFromString(contig));
	}

	
	
	
}
