package org.irri.iric.portal.genomics.service;

import java.util.Collection;
import java.util.List;

import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.LocalAlignmentImpl;

public interface LocusService {
	
	public Locus getLocusByName(String name) throws Exception;
	public List<Locus> getLocusByNotes(String note,  String organism);
	public List<Locus> getLocusByGODefinition(String godef,  String organism);
	public List<Locus> getLocusByGOTerm(String goterm, String organism);
	public List<Locus> getLocusFromAlignment(Collection<LocalAlignmentImpl> alignments) throws Exception;
	List<Locus> getLocusByRegion(String contig, Long start, Long end,
			String organism);
	public List getLocusByContigPositions(String contig, Collection posset,
			String organism);

}
