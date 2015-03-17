package org.irri.iric.portal.genomics.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.LocusDAO;
import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.LocusLocalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("LocusService")
public class LocusServiceImpl implements LocusService {

	@Autowired
	@Qualifier("VLocusNotesDAO")
	private LocusDAO locusnotesDAO;
	
	@Autowired
	@Qualifier("VLocusCvtermDAO")
	private LocusDAO locuscvtermDAO;

	@Override
	public List getLocusByNotes(String note, String organism) {
		// TODO Auto-generated method stub
		locusnotesDAO = (LocusDAO)AppContext.checkBean(locusnotesDAO,"VLocusNotesDAO");
		return locusnotesDAO.getLocusByDescription(note);
	}

	@Override
	public List getLocusByGODefinition(String godef, String organism) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getLocusByGOTerm(String goterm, String organism) {
		// TODO Auto-generated method stub
		locusnotesDAO = (LocusDAO)AppContext.checkBean(locusnotesDAO,"VLocusNotesDAO");
		return locusnotesDAO.getLocusByDescription(goterm);
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
	public List<Locus> getLocusFromAlignment(Collection<LocusLocalAlignment> alignments) throws Exception  {
		// TODO Auto-generated method stub
		
		Set setLocusNames = new LinkedHashSet();
		Set setLocus = new LinkedHashSet();
		Iterator<LocusLocalAlignment> itAlign = alignments.iterator();
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
	

}
