package org.irri.iric.portal.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.irri.iric.portal.AppContext;

public class MarkerAnnotationImpl implements MarkerAnnotation {
	
	private String contig;
	private Long chr;
	private BigDecimal pos;
	//private Double logpval;
	
	
	private Map<String, Set<Locus>> mapQTLs;
	private Map<String, Set<Locus>> mapGenes;
	private Map<String, Set<Locus>> mapTraitGenes;
	private Map<String, Set<Locus>> mapNetworkGenes;
	private Map<String, Set<Locus>> mapOntologyGenes;
	private Map<String, Set<Locus>> mapPromoterGenes;
	private Map<String, Set<Position>> mapMarkers;

	private List<Collection> listAnnots=null;
	
	

	public MarkerAnnotationImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MarkerAnnotationImpl(String contig, Long chr, BigDecimal pos) {
		this(contig, chr,  pos, null);
	}

	public MarkerAnnotationImpl(String contig, Long chr, BigDecimal pos, Double minuslogp) {
		super();
		this.contig = contig;
		this.chr = chr;
		this.pos = pos;
		
		//logpval=minuslogp;
		
		if(contig==null && chr==null) throw new RuntimeException("contig==null && chr==null");
		if(pos==null) throw new RuntimeException("pos==null");

		mapMarkers=new HashMap();
		Set setmarks=new HashSet();
		
		//setmarks.add(new PositionImpl( contig, pos , (chr==null?null:pos.intValue())));
		setmarks.add(new PositionLogPvalueImpl( contig, pos , (chr==null?null:pos.intValue()), minuslogp ));
		
		/*
		if(minuslogp==null)
			setmarks.add(new PositionImpl( contig, pos , (chr==null?null:pos.intValue())));
		else 
			setmarks.add(new PositionLogPvalueImpl( contig, pos , (chr==null?null:pos.intValue()), minuslogp ));
			*/
		
		mapMarkers.put(MARKER_POSITION, setmarks);
		
	}
//	
//	public void addGene(Locus loc) {
//		if(listGenes==null) listGenes=new HashSet();
//		listGenes.add(loc);
//		
//		this.addGene("OVERLAPPING GENES", loc);
//	}
//	
//	public void addQTL1(Locus loc) {
//		if(listQTL1==null) listQTL1=new ArrayList();
//		listQTL1.add(loc);
//		this.addQTL("Q-TARO QTL", loc);
//	}
//
//	public void addQTL2(Locus loc) {
//		if(listQTL2==null) listQTL2=new ArrayList();
//		listQTL2.add(loc);
//		addQTL("GRAMENE QTL", loc);
//	}

	@Override
	public String getContig() {
		// TODO Auto-generated method stub
		if(chr==null && contig==null) throw new RuntimeException("chr==null && contig==null");
		if(contig!=null) return contig;
		if(this.chr<10) return "chr0" + chr;
		return "chr" + chr;
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
		
		try {
		Position p1=this;
		Position p2=(Position)o;
		int ret=0;
		if(p1.getChr()!=null && p2.getChr()!=null)
			ret=p1.getChr().compareTo(p2.getChr());
		else 
			ret=p1.getContig().compareTo(p2.getContig());
		if(ret==0) ret=p1.getPosition().compareTo(p1.getPosition());
		return ret;
		} catch (Exception ex) {
			AppContext.debug(this.getClass() + ": " +  toString() +"\n" + o.getClass() + ": " + o.toString());
			throw new RuntimeException(ex);
		}
		
		/*
		int ret = this.chr.compareTo( ((MarkerAnnotation)o).getChr() );
		if(ret==0)
			 ret = this.pos.compareTo( ((MarkerAnnotation)o).getPosition() );
		return ret;
		*/
	}
//
//	@Override
//	public List<Locus> getQTL1() {
//		// TODO Auto-generated method stub
//		return listQTL1;
//	}
//
//	@Override
//	public List<Locus> getQTL2() {
//		// TODO Auto-generated method stub
//		return listQTL2;
//	}
//
//	@Override
//	public Set<Locus> getGenes() {
//		// TODO Auto-generated method stub
//		//return listGenes;
//		return (Set)getAnnotation("GENE MODELS");
//	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		//return  (this.getChr() + "-" + this.getPosition()).toString().hashCode();
		
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((this.chr == null) ? 0 : chr.hashCode()));
		result = (int) (prime * result + ((this.contig == null) ? 0 : contig.hashCode()));
		result = (int) (prime * result + ((this.pos == null) ? 0 : pos.hashCode()));
		return result;
		
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return compareTo(obj)==0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		//if(logpval!=null) return this.getChr() + "-" + this.getPosition() + "  -logP " +  AppContext.decf.format(logpval) + ": " +  getAnnotations();
		//else return this.getChr() + "-" + this.getPosition() + "  " +  getAnnotations();
		 return this.getChr() + "-" + this.getPosition() + "  " +  getAnnotations();
	}

	@Override
	public void addGene(String name, Locus loc) {
		// TODO Auto-generated method stub
		if(mapGenes==null) mapGenes=new LinkedHashMap();
		Set list=mapGenes.get(name);
		if(list==null) { list=new LinkedHashSet(); mapGenes.put(name,  list); }
		list.add(loc);
	}

	@Override
	public void addQTL(String name, Locus loc) {
		// TODO Auto-generated method stub
		if(mapQTLs==null) mapQTLs=new LinkedHashMap();
		Set list=mapQTLs.get(name);
		if(list==null) { list=new LinkedHashSet(); mapQTLs.put(name,  list); }
		list.add(loc);
		
	}

	@Override
	public void addTraitGene(String name, Locus loc) {
		// TODO Auto-generated method stub
		if(mapTraitGenes==null) mapTraitGenes=new LinkedHashMap();
		Set list=mapTraitGenes.get(name);
		if(list==null) { list=new LinkedHashSet(); mapTraitGenes.put(name,  list); }
		list.add(loc);
		
	}

	@Override
	public void addNetworkGene(String name, Locus loc) {
		// TODO Auto-generated method stub
		if(mapNetworkGenes==null) mapNetworkGenes=new LinkedHashMap();
		Set list=mapNetworkGenes.get(name);
		if(list==null) { list=new LinkedHashSet(); mapNetworkGenes.put(name,  list); }
		list.add(loc);
		
	}

	@Override
	public void addOntologyGene(String name, Locus loc) {
		// TODO Auto-generated method stub
		if(mapOntologyGenes==null) mapOntologyGenes=new LinkedHashMap();
		Set list=mapOntologyGenes.get(name);
		if(list==null) { list=new LinkedHashSet(); mapOntologyGenes.put(name,  list); }
		list.add(loc);
	}

	@Override
	public void addPromoterGene(String name, Locus loc) {
		// TODO Auto-generated method stub
		if(mapPromoterGenes==null) mapPromoterGenes=new LinkedHashMap();
		Set list=mapPromoterGenes.get(name);
		if(list==null) { list=new LinkedHashSet(); mapPromoterGenes.put(name,  list); }
		list.add(loc);
		
	}
	

	@Override
	public void addNetworkGene(String name, Collection loc) {
		// TODO Auto-generated method stub
		if(mapNetworkGenes==null) mapNetworkGenes=new LinkedHashMap();
		Set list=mapNetworkGenes.get(name);
		if(list==null) { list=new LinkedHashSet(); mapNetworkGenes.put(name,  list); }
		list.addAll(loc);
		
	}

	@Override
	public void addOntologyGene(String name, Collection loc) {
		// TODO Auto-generated method stub
		if(mapOntologyGenes==null) mapOntologyGenes=new LinkedHashMap();
		Set list=mapOntologyGenes.get(name);
		if(list==null) { list=new LinkedHashSet(); mapOntologyGenes.put(name,  list); }
		list.addAll(loc);
	}

	@Override
	public void addPromoterGene(String name, Collection loc) {
		// TODO Auto-generated method stub
		if(mapPromoterGenes==null) mapPromoterGenes=new LinkedHashMap();
		Set list=mapPromoterGenes.get(name);
		if(list==null) { list=new LinkedHashSet(); mapPromoterGenes.put(name,  list); }
		list.addAll(loc);
		
	}
	
	


	@Override
	public void addMarker(String name, Collection loc) {
		// TODO Auto-generated method stub
		if(mapMarkers==null) mapMarkers=new LinkedHashMap();
		Set list=mapMarkers.get(name);
		if(list==null) { list=new LinkedHashSet(); mapMarkers.put(name,  list); }
		list.addAll(loc);
		
	}


	@Override
	public void addQTL(String name, Collection loc) {
		// TODO Auto-generated method stub
		if(mapQTLs==null) mapQTLs=new LinkedHashMap();
		Set list=mapQTLs.get(name);
		if(list==null) { list=new LinkedHashSet(); mapQTLs.put(name,  list); }
		list.addAll(loc);
	}


	@Override
	public void addTraitGene(String name, Collection loc) {
		// TODO Auto-generated method stub
		if(mapTraitGenes==null) mapTraitGenes=new LinkedHashMap();
		Set list=mapTraitGenes.get(name);
		if(list==null) { list=new LinkedHashSet(); mapTraitGenes.put(name,  list); }
		list.addAll(loc);

	}

	@Override
	public void addGene(String name, Collection loc) {
		// TODO Auto-generated method stub
		if(mapGenes==null) mapGenes=new LinkedHashMap();
		Set list=mapGenes.get(name);
		if(list==null) { list=new LinkedHashSet(); mapGenes.put(name,  list); }
		list.addAll(loc);
	}

	@Override
	public Collection getAnnotation(String name) {
		// TODO Auto-generated method stub
		Collection list = null;
		if(mapGenes!=null) { list=mapGenes.get(name); if(list!=null) return list; };
		if(mapQTLs!=null) { list=mapQTLs.get(name); if(list!=null) return list;};
		if(mapTraitGenes!=null) {list=mapTraitGenes.get(name); if(list!=null) return list;};
		if(mapNetworkGenes!=null) {list=mapNetworkGenes.get(name); if(list!=null) return list;};
		if(mapOntologyGenes!=null) {list=mapOntologyGenes.get(name); if(list!=null) return list;};
		if(mapPromoterGenes!=null) {list=mapPromoterGenes.get(name); if(list!=null) return list;};
		if(mapMarkers!=null) {list=mapMarkers.get(name); if(list!=null) return list;};
		
		
		return new ArrayList();
	}
	
	@Override
	public Collection<Collection> getAnnotation(int index) {
		// TODO Auto-generated method stub
		if(listAnnots==null)
		{
			 listAnnots=new ArrayList();
			 Iterator<String> itannots = getAnnotations().iterator();
			 listAnnots.add(getAnnotation(GENE_MODEL));
			 while(itannots.hasNext()) {
				 String anotname=itannots.next();
				 if(anotname.equals(GENE_MODEL)) continue;
				 listAnnots.add(getAnnotation(anotname)); 
			 }
		}
		return listAnnots.get(index);
	}


	@Override
	public Set<String> getAnnotations() {
		// TODO Auto-generated method stub
		Set<String> names = new LinkedHashSet();
		if(mapGenes!=null) names.addAll(mapGenes.keySet()); 
		if(mapQTLs!=null) names.addAll(mapQTLs.keySet()); 
		if(mapTraitGenes!=null) names.addAll(mapTraitGenes.keySet()); 
		if(mapNetworkGenes!=null) names.addAll(mapNetworkGenes.keySet()); 
		if(mapOntologyGenes!=null) names.addAll(mapOntologyGenes.keySet()); 
		if(mapPromoterGenes!=null) names.addAll(mapPromoterGenes.keySet()); 
		if(mapMarkers!=null) names.addAll(mapMarkers.keySet()); 
		
		return names;
	}

	@Override
	public Set<Locus> getGene() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Locus> getQTL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Locus> getTraitGene() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Locus> getNetworkGene() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Locus> getOntologyGene() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Locus> getPromoterGene() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position getContigPosition() {
		// TODO Auto-generated method stub
		//return new PositionImpl(contig,  pos);
		
		 return mapMarkers.get(MARKER_POSITION).iterator().next();
		 
		/*
		if(logpval==null)
			return new PositionImpl( contig, pos , (chr==null?null:pos.intValue()));
		else 
			return new PositionLogPvalueImpl( contig, pos , (chr==null?null:pos.intValue()), logpval );
			*/
		//return new PositionImpl(contig,  pos);
	}

	@Override
	public void addMarker(String name, Locus pos) {
		// TODO Auto-generated method stub
		if(mapMarkers==null) mapMarkers=new HashMap();
		
		Set colMarks = mapMarkers.get(name);
		if(colMarks==null) {
			colMarks=new TreeSet();
			mapMarkers.put(name, colMarks);
		}
		colMarks.add(pos);
	}

	
	
	@Override
	public void  setMinusLogPvalue(Double logp) {
		// TODO Auto-generated method stub
		
		if(mapMarkers.get(MARKER_POSITION).size()!=1)
			throw new RuntimeException("cannot add pvalue, mapMarkers.get(MARKER_POSITION).size()!=1");
		
		Iterator itPos = mapMarkers.get(MARKER_POSITION).iterator();
		while(itPos.hasNext()) {
			PositionLogPvalue pos=(PositionLogPvalue)itPos.next();
			pos.setMinusLogPvalue(logp);
		}
	}

	@Override
	public Double getMinusLogPvalue() {
		// TODO Auto-generated method stub
		if(mapMarkers.get(MARKER_POSITION).size()!=1) return null; //throw new RuntimeException("cannot read pvalue, mapMarkers.get(MARKER_POSITION).size()!=1");
		return ((PositionLogPvalue)mapMarkers.get(MARKER_POSITION).iterator().next()).getMinusLogPvalue();
		
	}
	
	
	
}
