package org.irri.iric.portal.domain;

import java.math.BigDecimal;

import org.irri.iric.portal.AppContext;

/**
 * Implementation for SnpsAllvarsPos
 * @author LMansueto
 *
 */
public class SnpsAllvarsPosImpl implements SnpsAllvarsPos, Comparable {

	private BigDecimal pos;
	private String refnuc;
	private String contig;
	private String altcall;
	
	
	public SnpsAllvarsPosImpl(BigDecimal pos, String refnuc, String contig) {
		super();
		this.pos = pos;
		this.refnuc = refnuc;
		this.contig= contig;
	}
//
//	@Override
//	public BigDecimal getPosition() {
//		// TODO Auto-generated method stub
//		return pos;
//	}

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
	public String toString() {
		//return "[pos=" + pos + ", nuc=" + refnuc + "]";
		//return "pos=" + pos + ", nuc=" + refnuc + "]";
		return  getClass() + " contig=" + contig + ", pos=" + pos + ", nuc=" + refnuc + "]";
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
		return null;
	}

	@Override
	public Long getChr() {
		// TODO Auto-generated method stub
		return Long.valueOf(AppContext.guessChrFromString(contig)); 
	}
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		//SnpsAllvarsPosImpl obj=(SnpsAllvarsPosImpl)o;
		Position obj=(Position)o;
		int ret= this.contig.compareTo(obj.getContig());
		if(ret!=0) return ret;
		ret=this.pos.compareTo(obj.getPosition());
		if(ret!=0) return ret;
		
		if(o instanceof SnpsAllvarsPosImpl) {
			if(ret==0) ret=this.refnuc.compareTo( ((SnpsAllvarsPosImpl)obj).refnuc);
		}
		return ret;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		   
			final int prime = 31;
			int result = 1;
			result = (int) (prime * result + ((  contig == null) ? 0 :contig.hashCode()));
			result = (int) (prime * result + (( pos == null) ? 0 : pos.hashCode()));
			result = (int) (prime * result + (( refnuc == null) ? 0 : refnuc.hashCode()));
			return result;
	}

	@Override
	public BigDecimal getPosition() {
		// TODO Auto-generated method stub
		return this.pos;
	}

//	@Override
//	public String getRefnuc() {
//		// TODO Auto-generated method stub
//		return this.refnuc;
//	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return compareTo(obj) ==0;
	}

	

	@Override
	public void setAltnuc(String altnuc) {
		// TODO Auto-generated method stub
		this.altcall=altnuc;
	}

	@Override
	public String getAltnuc() {
		// TODO Auto-generated method stub
		return altcall;
	}

}
