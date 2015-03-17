package org.irri.iric.portal.dao;

import java.util.List;

import org.irri.iric.portal.domain.Locus;

public interface LocusDAO {

	public List<Locus> getLocusByName(String name);
	public List<Locus> getLocusByDescription(String name);
	public List<Locus> getLocusByRegion(String contig, Long start, Long end, String organism);
	
}
