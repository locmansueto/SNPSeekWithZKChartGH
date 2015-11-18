package org.irri.iric.portal.dao;

import java.util.Collection;
import java.util.List;

import org.irri.iric.portal.domain.Locus;

public interface LocusDAO {

	/**
	 * Get locis by name
	 * @param name
	 * @return
	 */
	public List<Locus> getLocusByName(String name);

	/**
	 * Get loci within region
	 * @param contig
	 * @param start
	 * @param end
	 * @param organism
	 * @return
	 */
	public List<Locus> getLocusByRegion(String contig, Long start, Long end, String organism);
	public List<Locus> getLocusByRegion(String contig, Long start, Long end, String organism, String genemodel);

	
	/**
	 * Get loci from list of positions
	 * @param contig
	 * @param posset
	 * @param organism
	 * @return
	 */
	public List getLocusByContigPositions(String contig, Collection posset, String organism);
	public List getLocusByContigPositions(String contig, Collection posset, String organism, String genemodel);
	
	/**
	 * Get loci with description
	 * @param desc
	 * @param organism
	 * @return
	 */
	public List<Locus> getLocusByDescription(String desc, String organism);
	public List<Locus> getLocusByDescription(String description, String organism, String genemodel);
	
	
	
}
