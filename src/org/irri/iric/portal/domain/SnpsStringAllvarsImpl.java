package org.irri.iric.portal.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.irri.iric.portal.AppContext;


/**
 * Implementation of SnpsStringAllvars
 * @author LMansueto
 *
 */

//@Component("SnpsStringAllvarsImpl")
public class SnpsStringAllvarsImpl implements SnpsStringAllvars {

	private BigDecimal var;
	//private Long chr;
	private Long chr;
	private String contig;
	private BigDecimal pos;
	private String refnuc;
	private String varnuc;
	private BigDecimal mismatch;
	//private boolean isnonsyn[];
	//private Set nonsynInonsynPossetdxset;
	private Set<Position> synPosset;
	private Set<Position> nonsynPosset;
	private Map<Position, Character> mapPos2Allele2;
	private String allele2;
	
	private Set acceptorPosset;
	private Set donorPosset;
	
	public SnpsStringAllvarsImpl(BigDecimal var, String chr,  String varnuc,
			BigDecimal mismatch, Map mapPos2Allele2, Set nonsynPosset, Set synPosset, Set donorPoset,  Set acceptorPosset) {
		super();
		this.var = var;
		try {
			this.chr = Long.valueOf(AppContext.guessChrFromString(chr));
		} catch(Exception ex) {
			
		}
		this.contig=chr;
		
		this.varnuc = varnuc;
		this.mismatch = mismatch;
		//this.isnonsyn = isnonsyn;
		//this.nonsynIdxset = nonsynIdxset;
		this.nonsynPosset = nonsynPosset;
		//this.mapPosIdx2Allele2 = mapPosIdx2Allele2;
		this.mapPos2Allele2 = mapPos2Allele2;
		this.synPosset=synPosset;
		
		this.acceptorPosset=acceptorPosset;
		this.donorPosset = donorPosset;
	}

	@Override
	public Long getChr() {
		// TODO Auto-generated method stub
		return chr;
	}

//	@Override
//	public BigDecimal getPosition() {
//		// TODO Auto-generated method stub
//		return pos;
//	}
//
//	@Override
//	public String getRefcall() {
//		// TODO Auto-generated method stub
//		return refnuc;
//	}

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
	public Map<Position, Character> getMapPos2Allele2() {
		return mapPos2Allele2;
	}

	@Override
	public Set getNonsynPosset() {
		return nonsynPosset;
	}
	
	@Override
	public Set getSynPosset() {
		return synPosset;
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

	@Override
	public String getContig() {
		// TODO Auto-generated method stub
		return this.contig;
	}

	@Override
	public SnpsStringAllvars copy() {
		// TODO Auto-generated method stub
		return new SnpsStringAllvarsImpl( var,  (chr==null?null:chr.toString()),   varnuc,
				 mismatch, (mapPos2Allele2==null?null:new HashMap(mapPos2Allele2)),  (nonsynPosset==null?null:new HashSet(nonsynPosset)),
				 (synPosset==null?null:new HashSet(synPosset)),
				 (donorPosset==null?null:new HashSet(donorPosset)), (acceptorPosset==null?null:new HashSet(acceptorPosset)));
		/*
		{
			super();
			this.var = var;
			try {
				this.chr = Long.valueOf(AppContext.guessChrFromString(chr));
			} catch(Exception ex) {
				
			}
			this.contig=chr;
			
			this.varnuc = varnuc;
			this.mismatch = mismatch;
			//this.isnonsyn = isnonsyn;
			//this.nonsynIdxset = nonsynIdxset;
			this.nonsynPosset = nonsynPosset;
			//this.mapPosIdx2Allele2 = mapPosIdx2Allele2;
			this.mapPos2Allele2 = mapPos2Allele2;
			
			this.acceptorPosset=acceptorPosset;
			this.donorPosset = donorPosset;
			*/
	}

	@Override
	public void setMismatch(BigDecimal mismatch) {
		// TODO Auto-generated method stub
		this.mismatch=mismatch;
	}


	@Override
	public void setVarnuc(String varnuc) {
		// TODO Auto-generated method stub
		this.varnuc=varnuc;
	}
	
	
	
}
