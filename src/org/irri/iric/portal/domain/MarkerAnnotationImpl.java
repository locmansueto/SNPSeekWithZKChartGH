package org.irri.iric.portal.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MarkerAnnotationImpl implements MarkerAnnotation {
	
	private String contig;
	private Long chr;
	private BigDecimal pos;
	
	List listGenes;
	List listQTL1;
	List listQTL2;
	
	
	public MarkerAnnotationImpl(String contig, Long chr, BigDecimal pos) {
		super();
		this.contig = contig;
		this.chr = chr;
		this.pos = pos;
	}
	
	public void addGene(Locus loc) {
		if(listGenes==null) listGenes=new ArrayList();
		listGenes.add(loc);
	}
	
	public void addQTL1(Locus loc) {
		if(listQTL1==null) listQTL1=new ArrayList();
		listQTL1.add(loc);
	}

	public void addQTL2(Locus loc) {
		if(listQTL2==null) listQTL2=new ArrayList();
		listQTL2.add(loc);
	}

	@Override
	public String getContig() {
		// TODO Auto-generated method stub
		return contig;
	}

	@Override
	public BigDecimal getPosition() {
		// TODO Auto-generated method stub
		return pos;
	}

	@Override
	public String getRefnuc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getChr() {
		// TODO Auto-generated method stub
		return chr;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		
		
		int ret = this.chr.compareTo( ((MarkerAnnotation)o).getChr() );
		if(ret==0)
			 ret = this.pos.compareTo( ((MarkerAnnotation)o).getPosition() );
		return ret;
	}

	@Override
	public List<Locus> getQTL1() {
		// TODO Auto-generated method stub
		return listQTL1;
	}

	@Override
	public List<Locus> getQTL2() {
		// TODO Auto-generated method stub
		return listQTL2;
	}

	@Override
	public List<Locus> getGenes() {
		// TODO Auto-generated method stub
		return listGenes;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.toString().hashCode();
		
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return compareTo(obj)==0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getChr() + "-" + this.getPosition();
	}

	
	
}
