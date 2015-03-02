package org.irri.iric.portal.domain;

import java.math.BigDecimal;

public class SnpsAllvarsPosImpl implements SnpsAllvarsPos {

	private BigDecimal pos;
	private String refnuc;
	
	
	
	
	public SnpsAllvarsPosImpl(BigDecimal pos, String refnuc) {
		super();
		this.pos = pos;
		this.refnuc = refnuc;
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

	
	
}
