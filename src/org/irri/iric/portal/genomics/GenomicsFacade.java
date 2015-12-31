package org.irri.iric.portal.genomics;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.irri.iric.portal.domain.CvTermLocusCount;
import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.MarkerAnnotation;

/**
 * API functions used mostly by Gene Locus Query
 * @author LMansueto
 *
 */

public interface GenomicsFacade {


	/**
	 * Get locus based on description (gene annotation)
	 * @param name
	 * @return
	 */
	public List<Locus> getLociByDescription(String name);
	public List<Locus> getLociByDescription(String description, String organism);
	
	/**
	 * Get locus by name
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Locus getLocusByName(String name) throws Exception;
	
	/**
	 * Get organisms (References)
	 * @return
	 * @throws Exception
	 */
	public List getOrganisms() throws Exception;
	
	/**
	 * 
	 * @param organism
	 * @return
	 */
	List getContigsByOrganism(String organism);
	

	/**
	 * Get GO terms associated with genes in organism 
	 * @param cv
	 * @param organism
	 * @return
	 */
	List<String> getGotermsByOrganism(String cv, String organism);
	
	/**
	 * Get TO, PO, QTARO terms associated with genes in organism
	 * @param cv
	 * @param organism
	 * @return
	 */
	List<String> getPatotermsByOrganism(String cv, String organism);
	
	/**
	 * Get GO definition
	 * @param term
	 * @return
	 * @throws Exception
	 */
	public String queryGO(String term) throws Exception;
	
	List<String> getCVtermAncestors(String cv, String cvterm);
	List<String> getCVtermDescendants(String cv, String cvterm);
	
	

	public String overRepresentationTest(String organism, Collection genelist, String enrichmentType) throws Exception;

	/**
	 * Perform blast
	 * @param sequence
	 * @param organism
	 * @param querytype
	 * @param dbtype
	 * @param evalue
	 * @return List<LocalAlignment>
	 * @throws Exception
	 */
	public List getLociBySequence(String sequence, String organism, String querytype, String dbtype, double evalue) throws Exception;
	
	/**
	 * 
	 * @param alignments Collection<LocalAlignment>
	 * @return List<Locus>
	 * @throws Exception
	 */
	public List getLociFromAlignment(Collection alignments) throws Exception;
	
	
	public List<CvTermLocusCount> getGOTermLociCounts(String organism, Set loci, String cv) throws Exception;

	/**
	 * Get TO,PO definition
	 * @param term
	 * @return
	 * @throws Exception
	 */
	String queryPATO(String term) throws Exception;
	

	/**
	 * 
	 * @param cvterm
	 * @param organism
	 * @param cvname
	 * @param genemodel
	 * @return
	 */
	public List getLociByCvTerm(String cvterm, String organism, String cvname, String genemodel);
	
	/**
	 * 
	 * @param contig
	 * @param start
	 * @param end
	 * @param organism
	 * @param genemodel
	 * @return
	 */
	public List<Locus> getLociByRegion(String contig, Long start, Long end, String organism, String genemodel);
	List getLociByRegion(String contig, Long start, Long end, String organism);
	
	/**
	 * 
	 * @param description
	 * @param orgenism
	 * @param genemodel
	 * @return
	 */
	public List<Locus> getLociByDescription(String description, String orgenism, String genemodel);
	
	/**
	 * 
	 * @param contig
	 * @param colPos
	 * @param organism
	 * @param genemodel
	 * @return
	 */
	public List<Locus> getLociByContigPositions(String contig, Collection colPos, String organism, String genemodel, Integer plusminus);
	List getLociByContigPositions(String contig, Collection posset, String organism, Integer plusminus);

	
	/**
	 * 
	 * @param contig
	 * @param colPos
	 * @param organism
	 * @param genemodel
	 * @return
	 */
	public List<MarkerAnnotation> getMarkerAnnotsByContigPositions(String contig, Collection colPos, String organism, String genemodel, Integer plusminus);

	
	/**
	 * Generate FASTA
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public String createVariantsFasta(VariantSequenceQuery query) throws Exception;

}
