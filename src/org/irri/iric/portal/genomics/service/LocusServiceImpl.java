package org.irri.iric.portal.genomics.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.LocusDAO;
import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.LocalAlignmentImpl;
import org.irri.iric.portal.domain.MultiReferencePositionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("LocusService")
public class LocusServiceImpl implements LocusService {

	@Autowired
	@Qualifier("VLocusNotesDAO")
	private LocusDAO locusnotesDAO;
	
	@Autowired
	@Qualifier("VLocusCvtermCvtermpathDAO")
	private LocusDAO locuscvtermDAO;

	@Override
	public List getLocusByNotes(String note, String organism) {
		// TODO Auto-generated method stub
		locusnotesDAO = (LocusDAO)AppContext.checkBean(locusnotesDAO,"VLocusNotesDAO");
		return locusnotesDAO.getLocusByDescription(note,organism);
	}

	@Override
	public List getLocusByGODefinition(String godef, String organism) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getLocusByGOTerm(String goterm, String organism) {
		// TODO Auto-generated method stub
		locuscvtermDAO = (LocusDAO)AppContext.checkBean(locuscvtermDAO,"VLocusCvtermCvtermpathDAO");
		return locuscvtermDAO.getLocusByDescription(goterm,organism);
	}

	@Override
	public Locus getLocusByName(String name) throws Exception {
		// TODO Auto-generated method stub
		locusnotesDAO = (LocusDAO)AppContext.checkBean(locusnotesDAO,"VLocusNotesDAO");
		List listlocus = locusnotesDAO.getLocusByName(name);
		
		if(listlocus.size()==0) return null;
		if(listlocus.size()>1) throw new RuntimeException("Multiple values for locus name " + name);
		
		//AppContext.debug("LocusServiceImpl:" + listlocus);
		
		return (Locus)listlocus.get(0);

	}

	@Override
	public List<Locus> getLocusFromAlignment(Collection<LocalAlignmentImpl> alignments) throws Exception  {
		// TODO Auto-generated method stub
		
		Set setLocusNames = new LinkedHashSet();
		Set setLocus = new LinkedHashSet();
		Iterator<LocalAlignmentImpl> itAlign = alignments.iterator();
		while(itAlign.hasNext()) {
			String locusname=itAlign.next().getSacc().toUpperCase();
			
			if(locusname.contains("."))
				locusname = locusname.split("\\.")[0];
			
			if(setLocusNames.contains(locusname)) continue;
			
			setLocus.add(getLocusByName( locusname ));
			setLocusNames.add(locusname);
			
		}
		List listLocus = new ArrayList();
		listLocus.addAll(setLocus);
		return listLocus;
	}
	
	@Override
	public List<Locus> getLocusByRegion( String contig,  Long start, Long end,  String organism) {
		locusnotesDAO = (LocusDAO)AppContext.checkBean(locusnotesDAO,"VLocusNotesDAO");
		return locusnotesDAO.getLocusByRegion(contig, start, end, organism);
	}
	/*
	private Map getMapContig2SNPPos(Collection<MultiReferencePosition> posset) {
		Iterator<MultiReferencePosition> itMultiPos = posset.iterator();
		Map<String, Set> mapChr2Pos = new TreeMap();
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
	*/

	@Override
	public List getLocusByContigPositions(String contig, Collection posset,
			String organism) {
		// TODO Auto-generated method stub
		
		locusnotesDAO = (LocusDAO)AppContext.checkBean(locusnotesDAO,"VLocusNotesDAO");
		
		if(contig.toLowerCase().equals("any")) {
			List listLoci = new ArrayList();
			Map<String, Set<BigDecimal>> mapChr2Pos = MultiReferencePositionImpl.getMapContig2SNPPos(posset);
			Iterator<String> itChr = mapChr2Pos.keySet().iterator();
			while(itChr.hasNext()) {
				String chr = itChr.next();
				listLoci.addAll( locusnotesDAO.getLocusByContigPositions(chr, mapChr2Pos.get(chr) ,  organism) );
			}
			return listLoci;
			
		} else {
			
			return locusnotesDAO.getLocusByContigPositions(contig, posset,  organism);
		}
	}
	
	
	
	

}
