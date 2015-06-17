package org.irri.iric.portal.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class MultiReferenceConversionImpl implements MultiReferenceConversion, Comparable {

	//private String organism;
	private String organism;
	private String contig;
	private BigDecimal position;
	private String refcall;
	
	
	
	public MultiReferenceConversionImpl(String organism, String contig,
			BigDecimal position) {
		super();
		this.organism = organism;
		this.contig = contig;
		this.position = position;
	}

	public MultiReferenceConversionImpl(String organism, String contig,
			BigDecimal position, String refcall) {
		super();
		this.organism = organism;
		this.contig = contig;
		this.position = position;
		this.refcall = refcall;
	}


	
	@Override
	public String getToOrganism() {
		// TODO Auto-generated method stub
		return organism;
	}

	@Override
	public String getToContig() {
		// TODO Auto-generated method stub
		return contig;
	}

	@Override
	public BigDecimal getToPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + organism + " " + contig + " " + position + ")";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(!( obj instanceof MultiReferenceConversion)) return false;
		
		MultiReferenceConversion  posobj=(MultiReferenceConversion)obj;
		return this.contig.equals(posobj.getToContig()) && this.organism.equals(posobj.getToOrganism()) && this.position.equals(posobj.getToPosition()); 
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		MultiReferenceConversion  posobj=(MultiReferenceConversion)o;
		int ret = this.organism.compareToIgnoreCase( posobj.getToOrganism());
		if(ret==0) ret= this.contig.compareToIgnoreCase( posobj.getToContig());
		if(ret==0) ret = this.position.compareTo( posobj.getToPosition());
		return ret;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return toString().hashCode();
	}
	
	

	@Override
	public String getFromOrganism() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFromContig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getFromPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFromRefcall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToRefcall() {
		// TODO Auto-generated method stub
		return this.refcall;
	}
	
	
	
	
	
}
