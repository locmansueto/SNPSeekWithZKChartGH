package org.irri.iric.portal.dao;

import java.util.Collection;
import java.util.List;

import org.irri.iric.portal.domain.Locus;

public interface LocusDAO {

	public List<Locus> getLocusByName(String name);
	//public List<Locus> getLocusByDescription(String name);
	public List<Locus> getLocusByRegion(String contig, Long start, Long end, String organism);
	public List getLocusByContigPositions(String contig, Collection posset, String organism);
	List<Locus> getLocusByDescription(String goterm, String organism);
	List<Locus> getLocusByRegion(String contig, Long start, Long end,
			String organism, String genemodel);
	List<Locus> getLocusByDescription(String description, String organism,
			String genemodel);
	List getLocusByContigPositions(String contig, Collection posset,
			String organism, String genemodel);
	
}
