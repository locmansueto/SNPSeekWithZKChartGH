package org.irri.iric.portal.genomics.service;

import java.util.Collection;
import java.util.List;

import org.irri.iric.portal.domain.Locus;

public interface GenomicsFacade {


	public List<Locus> getLociByDescription(String name);
	public Locus getLocusByName(String name) throws Exception;
	public List getOrganisms();
	public String queryGO(String term) throws Exception;
	List<String> getGotermsByOrganism(String organism);
	public List getLociByGOTerm(String goterm, String organism);
	public String overRepresentationTest(String organism, Collection genelist,
			String enrichmentType) throws Exception;
	public List getLociBySequence(String sequence, String organism, String querytype, String dbtype, double evalue) throws Exception;
	public List getLociFromAlignment(Collection alignments) throws Exception;
	List getContigsByOrganism(String organism);
	List getLociByRegion(String contig, Long start, Long end, String organism);
	
}
