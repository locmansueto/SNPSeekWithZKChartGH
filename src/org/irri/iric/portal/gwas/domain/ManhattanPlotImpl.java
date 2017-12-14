package org.irri.iric.portal.gwas.domain;

import java.math.BigDecimal;

public class ManhattanPlotImpl implements ManhattanPlot {

	//private Long chr;
	private Byte chr;
	private BigDecimal minp;
	private BigDecimal position;
	
	public ManhattanPlotImpl(Byte chr,  BigDecimal position, BigDecimal minp) {
		super();
		this.chr = chr;
		this.minp = minp;
		this.position = position;
	}

	@Override
	public String getContig() {
		// TODO Auto-generated method stub
		if(chr>9) return "chr" + chr;
		else  return "chr0" + chr;
	}

	@Override
	public BigDecimal getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public String getRefnuc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getChr() {
		// TODO Auto-generated method stub
		return Long.valueOf(chr);
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		ManhattanPlot obj=(ManhattanPlot)o;
		int ret = Long.valueOf(chr).compareTo(obj.getChr());
		if(ret==0) ret = this.position.compareTo(obj.getPosition());
		return ret;
	}
	

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((chr == null) ? 0 : chr.hashCode()));
		result = (int) (prime * result + ((position == null) ? 0 : position.hashCode()));
		return result;
		
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.compareTo(obj)==0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return chr + "-" + position + " " + minp;
	}

	@Override
	public BigDecimal getMarkerId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getMinusLogPvalue() {
		// TODO Auto-generated method stub
		return minp.doubleValue();
	}

	@Override
	public void setMinusLogPvalue(Double pval) {
		// TODO Auto-generated method stub
		minp=BigDecimal.valueOf(pval);
	}


}
