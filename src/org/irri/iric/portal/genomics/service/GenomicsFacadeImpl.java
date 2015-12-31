package org.irri.iric.portal.genomics.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.dao.VariantSequenceDAO;
import org.irri.iric.portal.domain.CvTermLocusCount;
import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.MarkerAnnotation;
import org.irri.iric.portal.domain.Organism;
import org.irri.iric.portal.genomics.GeneOntologyService;
import org.irri.iric.portal.genomics.GenomicsFacade;
import org.irri.iric.portal.genomics.LocalAlignmentQuery;
import org.irri.iric.portal.genomics.LocalAlignmentService;
import org.irri.iric.portal.genomics.LocusService;
import org.irri.iric.portal.genomics.OntologyService;
import org.irri.iric.portal.genomics.VariantSequenceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("GenomicsFacade")
public class GenomicsFacadeImpl implements GenomicsFacade {

	@Autowired
	private LocusService locusService;
	@Autowired
	@Qualifier("GeneOntologyService")
	private OntologyService goService;
	
	@Autowired
	@Qualifier("PATOGenesOntologyService")
	private OntologyService patoService;
	
	@Autowired
	private VariantSequenceDAO variantsequenceService;


	
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
		return getLociByDescription(description, AppContext.getDefaultOrganism());
	}
	
	@Override
	public List<Locus> getLociByDescription(String description, String organism) {
		// TODO Auto-generated method stub
		locusService = (LocusService)AppContext.checkBean(locusService, "LocusService");
		return locusService.getLocusByNotes(description,organism);
	}


	@Override
	public List<Locus> getLociByRegion(String contig, Long start, Long end,
			String organism, String genemodel) {
		// TODO Auto-generated method stub
		locusService = (LocusService)AppContext.checkBean(locusService, "LocusService");
		return locusService.getLocusByRegion(contig, start, end,organism, genemodel);
	}

	@Override
	public List<Locus> getLociByDescription(String description,
			String organism, String genemodel) {
		// TODO Auto-generated method stub
		locusService = (LocusService)AppContext.checkBean(locusService, "LocusService");
		return locusService.getLocusByNotes(description,organism, genemodel);
	}
	
	
	@Override
	public List<String> getGotermsByOrganism(String cv, String organism) {
		listitemsdao = (ListItemsDAO)AppContext.checkBean(listitemsdao, "ListItems");
		return listitemsdao.getGOTermsWithLoci(cv, organism);
	}
	
	@Override
	public List<String> getPatotermsByOrganism(String cv, String organism) {
		listitemsdao = (ListItemsDAO)AppContext.checkBean(listitemsdao, "ListItems");
		return listitemsdao.getPATOTermsWithLoci(cv, organism);
	}
	
	
	@Override
	public List<String> getCVtermAncestors(String cv, String cvterm) {
		// TODO Auto-generated method stub
		if(cv.equals("molecular_function") || cv.equals("biological_process") || cv.equals("cellular_component")) { 
			goService = (OntologyService)AppContext.checkBean(goService, "GeneOntologyService");
			return goService.getCVtermAncestors(cv, cvterm);
		} else {
			patoService = (OntologyService)AppContext.checkBean(patoService, "PATOntologyService");
			return patoService.getCVtermAncestors(cv, cvterm);
	
		}
	}

	@Override
	public List<String> getCVtermDescendants(String cv, String cvterm) {
		// TODO Auto-generated method stub
		
		if(cv.equals("molecular_function") || cv.equals("biological_process") || cv.equals("cellular_component")) { 
			goService = (OntologyService)AppContext.checkBean(goService, "GeneOntologyService");
			return goService.getCVtermDescendants(cv, cvterm);
		} else {
			patoService = (OntologyService)AppContext.checkBean(patoService, "PATOntologyService");
			return patoService.getCVtermDescendants(cv, cvterm);

		}
			
	}
	
	
	

	@Override
	public List getContigsByOrganism(String organism) {
		listitemsdao = (ListItemsDAO)AppContext.checkBean(listitemsdao, "ListItems");
		return listitemsdao.getContigs(organism);
	}

	@Override
	public List getLociByRegion(String contig, Long start, Long end, String organism) {
		locusService = (LocusService)AppContext.checkBean(locusService, "LocusService");
		return locusService.getLocusByRegion( contig,  start, end,  organism);
	}

	
	
	
	
	@Override
	public List getLociByContigPositions(String contig, Collection posset,
			String organism, Integer plusminus) {
		// TODO Auto-generated method stub
		locusService = (LocusService)AppContext.checkBean(locusService, "LocusService");
		return locusService.getLocusByContigPositions( contig,  posset,  organism, plusminus);
	}

	@Override
	public List getLociByCvTerm(String goterm, String organism, String cvname, String genemodel) {
		// TODO Auto-generated method stub
		locusService = (LocusService)AppContext.checkBean(locusService, "LocusService");
		return locusService.getLocusByCvTerm(goterm, organism, cvname, genemodel);
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
		String dbname = "msu7";
		
		if(organism.toLowerCase().equals(AppContext.getDefaultOrganism().toLowerCase())) dbname = "msu7";
		else if(organism.toLowerCase().equals("ir64-21")) dbname = "ir6421v1";
		else if(organism.toLowerCase().equals("93-11")) dbname = "9311v1";
		else if(organism.toLowerCase().equals("dj123")) dbname = "dj123v1";
		else if(organism.toLowerCase().equals("kasalath")) dbname = "kasrapv1";
		else if(organism.toLowerCase().equals("all")) dbname = "allosav1";
		
		if(dbtype.equals("dna")) dbname+="dna";  
		else if(dbtype.equals("cdna")) dbname+="cdna";
		else if(dbtype.equals("cds")) dbname+="cds";
		else if(dbtype.equals("pep")) dbname+="pep";
		else if(dbtype.equals("upstream3000")) dbname+="up3k";
			
		LocalAlignmentQuery query = new LocalAlignmentQuery(sequence, dbname, querytype);
		query.setEvalue(evalue);
		return localalignmentService.alignWithDB(query);
	}
	
	
	
	@Override
	public List getOrganisms() throws Exception {
		// TODO Auto-generated method stub
		listitemsdao = (ListItemsDAO)AppContext.checkBean(listitemsdao, "ListItems");
		Collection orgs = listitemsdao.getOrganisms();
		Iterator<Organism> itOrgs = orgs.iterator();
		List listNames= new ArrayList();
		while(itOrgs.hasNext()) {
		listNames.add(itOrgs.next().getName());
		}
		
		return listNames;
	}
	
	
	@Override
	public String queryGO(String term) throws Exception {
		goService = (OntologyService)AppContext.checkBean(goService, "GeneOntologyService");
		return goService.queryAccession(term);
	}
	
	@Override
	public String queryPATO(String term) throws Exception {
		patoService = (OntologyService)AppContext.checkBean(patoService, "PATOntologyService");
		return patoService.queryAccession(term);
	}

	
	
	
	@Override
	public String overRepresentationTest(String organism, Collection genelist, String enrichmentType)  throws Exception {
		goService = (GeneOntologyService)AppContext.checkBean(goService, "GeneOntologyService");
		return goService.overRepresentationTest(organism, genelist, enrichmentType);
	}

	@Override
	public List<CvTermLocusCount> getGOTermLociCounts(String organism, Set loci, String cv)  throws Exception {
		// TODO Auto-generated method stub
		goService = (GeneOntologyService)AppContext.checkBean(goService, "GeneOntologyService");
		return goService.countLociInTerms(organism, loci, cv);
	}
	
	@Override
	public String createVariantsFasta(VariantSequenceQuery query) throws Exception {
		
		variantsequenceService = (VariantSequenceDAO)AppContext.checkBean(variantsequenceService, "VariantSeuqenceService");
		return variantsequenceService.getFile(query);

	}

	@Override
	public List<Locus> getLociByContigPositions(String contig, Collection colPos,
			String org, String genemodel, Integer plusminus) {
		// TODO Auto-generated method stub
		locusService = (LocusService)AppContext.checkBean(locusService, "LocusService");
		return locusService.getLocusByContigPositions( contig,  colPos,  org, genemodel, plusminus);
	}

	@Override
	public List<MarkerAnnotation> getMarkerAnnotsByContigPositions(String contig,
			Collection colPos, String organism, String genemodel,
			Integer plusminus) {
		locusService = (LocusService)AppContext.checkBean(locusService, "LocusService");
		return locusService.getMarkerAnnotsByContigPositions( contig,  colPos,  genemodel, plusminus);
	}
	
	
}
