package org.irri.iric.portal.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.irri.iric.portal.dao.IndelsAllvarsDAO;

public class IndelsStringAllvarsImpl implements IndelsStringAllvars {

	private BigDecimal mismatch;
	private Map<BigDecimal, IndelsAllvars> mapPos2IndelCalls;
	private BigDecimal var;
	//private String varnuc;
	//private Map mapPosidx2Allele2;
	private Long chr;

	private SnpsStringAllvars snpstring;
	
	public IndelsStringAllvarsImpl(BigDecimal var, Map mapPos2IndelCalls, BigDecimal mismatch, Long chr) {
		super();
		this.var = var;
		this.mismatch = mismatch;
		this.mapPos2IndelCalls = mapPos2IndelCalls;
		this.chr = chr;
	}

	
	@Override
	public BigDecimal getAllele1(BigDecimal pos) {
		// TODO Auto-generated method stub
		IndelsAllvars indel = mapPos2IndelCalls.get(pos);
		if(indel!=null) return indel.getAllele1();
		return null;
	}

	@Override
	public BigDecimal getAllele2(BigDecimal pos) {
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

	@Override
	public BigDecimal getPos() {
		// TODO Auto-generated method stub
		if(snpstring==null) return null;
		return snpstring.getPos();
	}

	@Override
	public String getRefnuc() {
		// TODO Auto-generated method stub
		if(snpstring==null) return null;
		return snpstring.getRefnuc();
	}

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


	@Override
	public Map<BigDecimal, IndelsAllvars> getMapPos2Indels() {
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
		if(getChr()>9)
			return "chr" + getChr();
		else 
			return "chr0" + getChr();
	}

	
	
	
}
