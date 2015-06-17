package org.irri.iric.portal.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class MultiReferencePositionImpl implements MultiReferencePosition, Comparable  {

	protected String organism;
	protected String contig;
	private String refcall;
	protected BigDecimal position;
	
	
	
	public MultiReferencePositionImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MultiReferencePositionImpl(String orgcontigpos) {
		super();
		orgcontigpos = orgcontigpos = orgcontigpos.replace("(","").replace(")", "");
		String fields[] = orgcontigpos.split(",");
		organism=fields[0].trim();
		contig = fields[1].trim();
		position = BigDecimal.valueOf(Long.valueOf(fields[2].trim()));
		if(fields.length>3)
			refcall = fields[3].trim();
	}
	
	public MultiReferencePositionImpl(String organism, String contig,
			 BigDecimal position)  {
		this(organism,contig, position, null);
	}

	public MultiReferencePositionImpl(String organism, String contig,
			 BigDecimal position, String refcall)  {
		super();
		this.organism = organism;
		this.contig = contig;
		this.refcall = refcall;
		this.position = position;
	}

	@Override
	public String getOrganism() {
		// TODO Auto-generated method stub
		return organism;
	}

	@Override
	public String getContig() {
		// TODO Auto-generated method stub
		return contig;
	}

	@Override
	public BigDecimal getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public String getRefcall() {
		// TODO Auto-generated method stub
		return refcall;
	}

	/*
	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		 MultiReferencePosition p1=(MultiReferencePosition)o1;
		 MultiReferencePosition p2=(MultiReferencePosition)o2;
		 int ret = p1.getOrganism().compareTo(p2.getOrganism());
		 if(ret==0)
			 ret = p1.getContig().compareTo(p2.getContig());
		 if(ret==0)
			 ret = p1.getPosition().compareTo(p2.getPosition());
		return ret;
	}
*/
	
	
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			 MultiReferencePosition p2=(MultiReferencePosition)o;
			 int ret = getOrganism().compareTo(p2.getOrganism());
			 if(ret==0)
				 ret = getContig().compareTo(p2.getContig());
			 if(ret==0)
				 ret = getPosition().compareTo(p2.getPosition());
			return ret;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return toString().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return compareTo(obj)==0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if(refcall==null)
			return "(" + organism + ", " + contig + "," + position + ")" ;
		else
			return "(" + organism + ", " + contig + "," + position + ","  + refcall + ")" ;
		
	}
	
	
	
	/**
	 * Convert Collection<MultiReferencePosition> to TreeMap<String, TreeSet> mapChr2Pos
	 * @param posset
	 * @return
	 */
	public static Map<String, Set<BigDecimal>> getMapContig2SNPPos(Collection<MultiReferencePosition> posset) {
		Iterator<MultiReferencePosition> itMultiPos = posset.iterator();
		Map<String, Set<BigDecimal>> mapChr2Pos = new TreeMap();
		while(itMultiPos.hasNext()) {
			MultiReferencePosition refpos = itMultiPos.next();
			Set setPos = mapChr2Pos.get(refpos.getContig());
			if(setPos==null) {
				setPos=new TreeSet();
				mapChr2Pos.put( refpos.getContig() , setPos);
			}
			setPos.add(refpos.getPosition() );
		}
		return mapChr2Pos;
	}
	
	/**
	 * Convert Collection<Locus> to TreeMap<String, TreeSet> mapChr2Loci
	 * @param posset
	 * @return
	 */
	public static Map<String,Set<Locus>> getMapContig2Loci(Collection<Locus> lociset) {
		Iterator<Locus> itMultiPos = lociset.iterator();
		Map<String, Set<Locus>> mapChr2Pos = new TreeMap();
		while(itMultiPos.hasNext()) {
			Locus refpos = itMultiPos.next();
			Set setPos = mapChr2Pos.get(refpos.getContig());
			if(setPos==null) {
				setPos=new TreeSet();
				mapChr2Pos.put( refpos.getContig() , setPos);
			}
			setPos.add(refpos);
		}
		return mapChr2Pos;
	}
	
	
	

	
	
}
