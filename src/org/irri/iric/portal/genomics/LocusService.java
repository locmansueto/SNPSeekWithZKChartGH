package org.irri.iric.portal.genomics;

import java.util.Collection;
import java.util.List;

import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.LocalAlignmentImpl;

/**
 * Gene loci query 
 * @author LMansueto
 *
 */
public interface LocusService {
	
	/**
	 * Get locu with name
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Locus getLocusByName(String name) throws Exception;
	
	/**
	 * Get loci with note (description, gene function, annotation)
	 * @param note
	 * @param organism
	 * @return
	 */
	public List<Locus> getLocusByNotes(String note,  String organism);
	public List<Locus> getLocusByNotes(String description, String organism, String genemodel);

	/**
	 * Get loci from LocalAlignment result
	 * @param alignments
	 * @return
	 * @throws Exception
	 */
	public List<Locus> getLocusFromAlignment(Collection<LocalAlignmentImpl> alignments) throws Exception;
	
	/**
	 * Get loci in region
	 * @param contig
	 * @param start
	 * @param end
	 * @param organism
	 * @return
	 */
	List<Locus> getLocusByRegion(String contig, Long start, Long end, String organism);
	public List<Locus> getLocusByRegion(String contig, Long start, Long end, String organism, String genemodel);
	
	/**
	 * Get loci in list of positions
	 * @param contig
	 * @param posset
	 * @param organism
	 * @return
	 */
	public List getLocusByContigPositions(String contig, Collection posset, String organism, Integer plusminus);
	List getLocusByContigPositions(String contig, Collection posset, String organism, String genemodel, Integer plusminus);

	/**
	 * Get loci linked to cvterm
	 * @param goterm
	 * @param organism
	 * @param cvname
	 * @return
	 */
	public List getLocusByCvTerm(String goterm, String organism, String cvname);
	List getLocusByCvTerm(String goterm, String organism, String cvname, String genemodel);
	

}
