package org.irri.iric.portal.domain;

import java.math.BigDecimal;



import java.util.Map;
import java.util.Set;




public class IndelsStringAllvarsImpl implements IndelsStringAllvars {

	private BigDecimal mismatch;
	private Map<Position, IndelsAllvars> mapPos2IndelCalls;
	private BigDecimal var;
	//private String varnuc;
	//private Map mapPosidx2Allele2;
	private Long chr;
	private String contig;

	private SnpsStringAllvars snpstring;
	
	public IndelsStringAllvarsImpl(BigDecimal var, Map<Position, IndelsAllvars> mapPos2IndelCalls, BigDecimal mismatch,  String contig) {
		super();
		this.var = var;
		this.mismatch = mismatch;
		this.mapPos2IndelCalls = mapPos2IndelCalls;
		
		try {
			this.chr = Long.valueOf(contig);
		} catch(Exception ex) {
			this.contig = contig;	
		}
	}

	
	@Override
	public BigDecimal getAllele1(Position pos) {
		// TODO Auto-generated method stub
		IndelsAllvars indel = mapPos2IndelCalls.get(pos);
		if(indel!=null) return indel.getAllele1();
		return null;
	}

	@Override
	public BigDecimal getAllele2(Position pos) {
		// TODO Auto-generated method stub
		IndelsAllvars indel = mapPos2IndelCalls.get(pos);
		if(indel!=null) return indel.getAllele2();
		return null;
	}



	@Override
	public Long getChr() {
		// TODO Auto-generated method stub
		
		return this.chr;
	}
//
//	@Override
//	public BigDecimal getPosition() {
//		// TODO Auto-generated method stub
//		if(snpstring==null) return null;
//		return snpstring.getPosition();
//	}
//
//	@Override
//	public String getRefcall() {
//		// TODO Auto-generated method stub
//		if(snpstring==null) return null;
//		return snpstring.getRefcall();
//	}

	@Override
	public String getVarnuc() {
		// TODO Auto-generated method stub
		if(snpstring==null) return null;
		return snpstring.getVarnuc();
	}

	@Override
	public BigDecimal getVar() {
		// TODO Auto-generated method stub
		return this.var;
	}

	@Override
	public BigDecimal getMismatch() {
		// TODO Auto-generated method stub
		if(snpstring==null) return mismatch;
		return snpstring.getMismatch().add(mismatch);
	}

	/*
	@Override
	public Map<Integer, Character> getMapPosIdx2Allele2() {
		// TODO Auto-generated method stub
		if(snpstring==null) return null;
		return snpstring.getMapPosIdx2Allele2();
	}
	

	@Override
	public Set getNonsynIdxset() {
		// TODO Auto-generated method stub
		if(snpstring==null) return null;
		return snpstring.getNonsynIdxset();
	}
	*/


	@Override
	public Map<Position, IndelsAllvars> getMapPos2Indels() {
		// TODO Auto-generated method stub
		
		return mapPos2IndelCalls;
	}


//	@Override
//	public void setVarnuc(String varnuc) {
//		// TODO Auto-generated method stub
//		this.varnuc = varnuc;
//		
//	}
//
//
//	@Override
//	public void setMismatch(BigDecimal mismatch) {
//		// TODO Auto-generated method stub
//		this.mismatch = mismatch;
//	}
//
//
//	@Override
//	public void setMapPosIdx2Allele2(Map mapPosidx2Allele2) {
//		// TODO Auto-generated method stub
//		this.mapPosidx2Allele2 = mapPosidx2Allele2;
//	}


	@Override
	public void delegateSnpString(SnpsStringAllvars snpstring) {
		// TODO Auto-generated method stub
		this.snpstring = snpstring;
	}


	@Override
	public Set getDonorPosset() {
		// TODO Auto-generated method stub
		if(snpstring==null) return null;
		return  snpstring.getDonorPosset() ;
	}


	@Override
	public Set getAcceptorPosset() {
		// TODO Auto-generated method stub
		if(snpstring==null) return null;
		return snpstring.getAcceptorPosset();
	}
	
	


	@Override
	public Map<Position, Character> getMapPos2Allele2() {
		// TODO Auto-generated method stub
		if(snpstring==null) return null;
		return snpstring.getMapPos2Allele2();
	}


	@Override
	public Set getNonsynPosset() {
		// TODO Auto-generated method stub
		if(snpstring==null) return null;
		return snpstring.getNonsynPosset();
	}


	@Override
	public String getContig() {
		// TODO Auto-generated method stub
		if(chr==null) return contig;
		if(getChr()>9)
			return "chr" + getChr();
		else 
			return "chr0" + getChr();
	}


	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return var.toString() + ", " + getContig() + " " + getChr() + " " + mapPos2IndelCalls;
	}


	

	
	
	
}
