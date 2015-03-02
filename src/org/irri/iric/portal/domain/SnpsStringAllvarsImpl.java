package org.irri.iric.portal.domain;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

//@Component("SnpsStringAllvarsImpl")
public class SnpsStringAllvarsImpl implements SnpsStringAllvars {

	private BigDecimal var;
	private Long chr;
	private BigDecimal pos;
	private String refnuc;
	private String varnuc;
	private BigDecimal mismatch;
	//private boolean isnonsyn[];
	private Set nonsynIdxset;
	private Map<Integer, Character> mapPosIdx2Allele2;
	private String allele2;
	
	private Set acceptorPosset;
	private Set donorPosset;
	
	public SnpsStringAllvarsImpl(BigDecimal var, Long chr,  String varnuc,
			BigDecimal mismatch, Map mapPosIdx2Allele2, Set nonsynIdxset, Set donorPoset,  Set acceptorPosset) {
		super();
		this.var = var;
		this.chr = chr;
		this.varnuc = varnuc;
		this.mismatch = mismatch;
		//this.isnonsyn = isnonsyn;
		this.nonsynIdxset = nonsynIdxset;
		this.mapPosIdx2Allele2 = mapPosIdx2Allele2;
		
		this.acceptorPosset=acceptorPosset;
		this.donorPosset = donorPosset;
	}

	@Override
	public Long getChr() {
		// TODO Auto-generated method stub
		return chr;
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
	public String getVarnuc() {
		// TODO Auto-generated method stub
		return varnuc;
	}

	@Override
	public BigDecimal getVar() {
		// TODO Auto-generated method stub
		return var;
	}

	@Override
	public BigDecimal getMismatch() {
		// TODO Auto-generated method stub
		return mismatch;
	}

	/*
	@Override
	public boolean[] getIsnonsyn() {
		return isnonsyn;
	}
	*/
	
	

	@Override
	public Map<Integer, Character> getMapPosIdx2Allele2() {
		return mapPosIdx2Allele2;
	}

	@Override
	public Set getNonsynIdxset() {
		return nonsynIdxset;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		 SnpsStringAllvarsImpl o = (SnpsStringAllvarsImpl)obj;
		return var.equals(o.getVar());
	}

	@Override
	public Set getDonorPosset() {
		// TODO Auto-generated method stub
		return this.donorPosset;
	}

	@Override
	public Set getAcceptorPosset() {
		// TODO Auto-generated method stub
		return this.acceptorPosset;
	}

	
	
	
}
