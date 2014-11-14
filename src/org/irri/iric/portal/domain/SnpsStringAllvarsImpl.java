package org.irri.iric.portal.domain;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

//@Component("SnpsStringAllvarsImpl")
public class SnpsStringAllvarsImpl implements SnpsStringAllvars {

	private BigDecimal var;
	private Long chr;
	private BigDecimal pos;
	private String refnuc;
	private String varnuc;
	private BigDecimal mismatch;
	
	
	
	public SnpsStringAllvarsImpl(BigDecimal var, Long chr,  String varnuc,
			BigDecimal mismatch) {
		super();
		this.var = var;
		this.chr = chr;
		this.varnuc = varnuc;
		this.mismatch = mismatch;
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

	
	
}
