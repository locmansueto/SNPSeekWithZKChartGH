package org.irri.iric.portal.genomics.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.Organism;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("GenomicsFacade")
public class GenomicsFacadeImpl implements GenomicsFacade {

	@Autowired
	private LocusService locusService;
	@Autowired
	private GeneOntologyService goService;
	@Autowired
	private LocalAlignmentService localalignmentService;
	
	@Autowired
	private ListItemsDAO listitemsdao;
	

	
	@Override
	public Locus getLocusByName(String name) throws Exception {
		// TODO Auto-generated method stub
		
		locusService = (LocusService)AppContext.checkBean(locusService, "LocusService");
		return locusService.getLocusByName(name);
	}

	@Override
	public List<Locus> getLociByDescription(String description) {
		// TODO Auto-generated method stub
		locusService = (LocusService)AppContext.checkBean(locusService, "LocusService");
		return locusService.getLocusByNotes(description, AppContext.getDefaultOrganism());
	}
	
	@Override
	public List<String> getGotermsByOrganism(String organism) {
		listitemsdao = (ListItemsDAO)AppContext.checkBean(listitemsdao, "ListItemsDAO");
		return listitemsdao.getGOTermsWithLoci(organism);
	}

	@Override
	public List getContigsByOrganism(String organism) {
		listitemsdao = (ListItemsDAO)AppContext.checkBean(listitemsdao, "ListItemsDAO");
		return listitemsdao.getContigs(organism);
	}

	@Override
	public List getLociByRegion(String contig, Long start, Long end, String organism) {
		locusService = (LocusService)AppContext.checkBean(locusService, "LocusService");
		return locusService.getLocusByRegion( contig,  start, end,  organism);
	}

	
	@Override
	public List getLociByGOTerm(String goterm, String organism) {
		// TODO Auto-generated method stub
		locusService = (LocusService)AppContext.checkBean(locusService, "LocusService");
		return locusService.getLocusByGOTerm(goterm, organism);
	}


	@Override
	public List getLociFromAlignment(Collection alignments) throws Exception {
		locusService = (LocusService)AppContext.checkBean(locusService, "LocusService");
		return locusService.getLocusFromAlignment(alignments);
	}
	
	

	@Override
	public List getLociBySequence(String sequence, String organism, String querytype, String dbtype, double evalue) throws Exception {
	
		localalignmentService = (LocalAlignmentService)AppContext.checkBean(localalignmentService, "LocalAlignmentService" );
		//String queryseq, String dbname, String querytype
		String dbname = "msu";
		
		if(organism.toLowerCase().equals("rice")) dbname = "msu7";
		else if(organism.toLowerCase().equals("ir64-21")) dbname = "ir6421v1";
		else if(organism.toLowerCase().equals("93-11")) dbname = "9311v1";
		else if(organism.toLowerCase().equals("dj123")) dbname = "dj123v1";
		else if(organism.toLowerCase().equals("kasalath")) dbname = "kasv1";
		
		if(dbtype.equals("dna")) dbname+="dna";  
		else if(dbtype.equals("cdna")) dbname+="cdna";
		else if(dbtype.equals("cds")) dbname+="cds";
		else if(dbtype.equals("pep")) dbname+="pep";
			
		LocalAlignmentQuery query = new LocalAlignmentQuery(sequence, dbname, querytype);
		query.setEvalue(evalue);
		return localalignmentService.alignWithDB(query);
	}
	
	
	
	@Override
	public List getOrganisms() {
		// TODO Auto-generated method stub
		listitemsdao = (ListItemsDAO)AppContext.checkBean(listitemsdao, "ListItemsDAO");
		Iterator<Organism> itOrgs = listitemsdao.getOrganisms().iterator();
		List listNames= new ArrayList();
		while(itOrgs.hasNext()) {
		listNames.add(itOrgs.next().getName());
		}
		
		return listNames;
	}
	
	
	@Override
	public String queryGO(String term) throws Exception {
		goService = (GeneOntologyService)AppContext.checkBean(goService, "GeneOntologyService");
		return goService.queryGO(term);
	}
	
	@Override
	public String overRepresentationTest(String organism, Collection genelist, String enrichmentType)  throws Exception {
		goService = (GeneOntologyService)AppContext.checkBean(goService, "GeneOntologyService");
		return goService.overRepresentationTest(organism, genelist, enrichmentType);
	}
}
