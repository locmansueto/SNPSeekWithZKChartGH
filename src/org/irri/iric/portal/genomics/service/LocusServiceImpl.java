package org.irri.iric.portal.genomics.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;



import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.CvDAO;

import org.irri.iric.portal.dao.LocusCvTermDAO;
import org.irri.iric.portal.dao.LocusDAO;
import org.irri.iric.portal.dao.OrganismDAO;
import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.LocalAlignmentImpl;
import org.irri.iric.portal.domain.MultiReferencePositionImpl;
import org.irri.iric.portal.genomics.LocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("LocusService")
public class LocusServiceImpl implements LocusService {

	@Autowired
	private OrganismDAO orgdao;

	@Autowired
	private CvDAO cvdao;

	
	@Autowired
	@Qualifier("LocusNotesDAO")
	private LocusDAO locusnotesDAO;
	
	@Autowired
	@Qualifier("LocusCvtermCvtermpathDAO")
	//@Qualifier("VLocusCvtermDAO")
	private LocusCvTermDAO locuscvtermDAO;
	//private CvTermDAO locuscvtermDAO;

	@Autowired
	@Qualifier("VLocusCvtermpathIricDAO")
	//@Qualifier("VLocusCvtermDAO")
	private LocusCvTermDAO locusiriccvtermDAO;
	
	@Autowired
	@Qualifier("VLocusCvtermpathMsu7DAO")
	private LocusCvTermDAO locusmsu7cvtermDAO;
	
	@Autowired
	@Qualifier("VLocusCvtermpathRapDAO")
	private LocusCvTermDAO locusrapcvtermDAO;

	
	//@Autowired
	//@Qualifier("VLocusCvtermDAO")
	//private LocusDAO locuspatotermDAO;
	
	
	
	@Override
	public List getLocusByNotes(String note, String organism) {
		// TODO Auto-generated method stub
		locusnotesDAO = (LocusDAO)AppContext.checkBean(locusnotesDAO,"LocusNotesDAO");
		return locusnotesDAO.getLocusByDescription(note,organism);
	}


	@Override
	public List getLocusByCvTerm(String goterm, String organism, String cvname) {
		// TODO Auto-generated method stub
		AppContext.debug("getting locus for " + goterm + "  "  + organism );
		//locuscvtermDAO = (LocusDAO)AppContext.checkBean(locuscvtermDAO,"VLocusCvtermCvtermpathDAO");
		locuscvtermDAO = (LocusCvTermDAO)AppContext.checkBean(locuscvtermDAO,"LocusCvtermCvtermpathDAO");
		//locuscvtermDAO = (LocusCvTermDAO)AppContext.checkBean(locuscvtermDAO,"LocusCvtermDAO");
		return locuscvtermDAO.getLocusByDescription(goterm, orgdao.getMapName2Organism().get(organism).getOrganismId().intValue(), 
				cvdao.getMapName2Cv().get(cvname).getCvId().intValue());
	}

	@Override
	public List getLocusByCvTerm(String goterm, String organism, String cvname, String genemodel) {
		// TODO Auto-generated method stub
		AppContext.debug("getting locus for " + goterm + "  "  + organism + " " + genemodel);
		//locuscvtermDAO = (LocusDAO)AppContext.checkBean(locuscvtermDAO,"VLocusCvtermCvtermpathDAO");
		
		if(genemodel.equals(LocusCvTermDAO.GENEMODEL_ALL))
			return getLocusByCvTerm( goterm, organism, cvname);

		
		LocusCvTermDAO locusgenemodelcvtermDAO=null;
		
		if(genemodel.equals(LocusCvTermDAO.GENEMODEL_IRIC))
			locusgenemodelcvtermDAO = (LocusCvTermDAO)AppContext.checkBean(locusiriccvtermDAO,"VLocusCvtermpathIricDAO");
		else if(genemodel.equals(LocusCvTermDAO.GENEMODEL_MSU7))
			locusgenemodelcvtermDAO = (LocusCvTermDAO)AppContext.checkBean(locusmsu7cvtermDAO,"VLocusCvtermpathMsu7DAO");
		if(genemodel.equals(LocusCvTermDAO.GENEMODEL_RAP))
			locusgenemodelcvtermDAO = (LocusCvTermDAO)AppContext.checkBean(locusrapcvtermDAO,"VLocusCvtermpathRapDAO");
		
		//locuscvtermDAO = (LocusCvTermDAO)AppContext.checkBean(locuscvtermDAO,"LocusCvtermDAO");
		return locusgenemodelcvtermDAO.getLocusByDescription(goterm, orgdao.getMapName2Organism().get(organism).getOrganismId().intValue(), 
				cvdao.getMapName2Cv().get(cvname).getCvId().intValue());
	}

	
	

	@Override
	public Locus getLocusByName(String name) throws Exception {
		// TODO Auto-generated method stub
		locusnotesDAO = (LocusDAO)AppContext.checkBean(locusnotesDAO,"LocusNotesDAO");
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
		locusnotesDAO = (LocusDAO)AppContext.checkBean(locusnotesDAO,"LocusNotesDAO");
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
	public List<Locus> getLocusByRegion(String contig, Long start, Long end,
			String organism, String genemodel) {
		// TODO Auto-generated method stub
		locusnotesDAO = (LocusDAO)AppContext.checkBean(locusnotesDAO,"LocusNotesDAO");
		return locusnotesDAO.getLocusByRegion(contig, start, end, organism, genemodel);
	}


	@Override
	public List<Locus> getLocusByNotes(String description, String organism,
			String genemodel) {
		// TODO Auto-generated method stub
		locusnotesDAO = (LocusDAO)AppContext.checkBean(locusnotesDAO,"LocusNotesDAO");
		return locusnotesDAO.getLocusByDescription(description,organism, genemodel);
	}


	@Override
	public List getLocusByContigPositions(String contig, Collection posset,
			String organism, String genemodel, Integer plusminus) {
		// TODO Auto-generated method stub
		
		locusnotesDAO = (LocusDAO)AppContext.checkBean(locusnotesDAO,"LocusNotesDAO");
		
		if(contig.toLowerCase().equals("any")) {
			List listLoci = new ArrayList();
			Map<String, Set<BigDecimal>> mapChr2Pos = MultiReferencePositionImpl.getMapContig2SNPPos(posset);
			Iterator<String> itChr = mapChr2Pos.keySet().iterator();
			while(itChr.hasNext()) {
				String chr = itChr.next();
				listLoci.addAll( locusnotesDAO.getLocusByContigPositions(chr, mapChr2Pos.get(chr) ,  organism, genemodel, plusminus) );
			}
			return listLoci;
			
		} else {
			
			return locusnotesDAO.getLocusByContigPositions(contig, posset,  organism, genemodel, plusminus);
		}
	}


	@Override
	public List getLocusByContigPositions(String contig, Collection posset,
			String organism, Integer plusminus) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
