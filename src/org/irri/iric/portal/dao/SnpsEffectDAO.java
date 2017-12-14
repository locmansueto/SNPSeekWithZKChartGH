package org.irri.iric.portal.dao;

import java.util.Collection;
import java.util.Set;

import org.irri.iric.portal.domain.SnpsEffect;
import org.irri.iric.portal.domain.SnpsSpliceAcceptor;
import org.springframework.dao.DataAccessException;

public interface SnpsEffectDAO {

	/*
	public Set<SnpsEffect> getSNPsIn(String chr, Collection listpos)  throws DataAccessException;
	public Set<SnpsEffect> getSNPsBetween(String chr, Integer start, Integer end) throws DataAccessException;
	*/
	public Set<SnpsEffect> getSNPsIn(String chr, Collection listpos, Set variantset)  throws DataAccessException;
	public Set<SnpsEffect> getSNPsBetween(String chr, Integer start, Integer end, Set variantset) throws DataAccessException;
	
	public Set<SnpsEffect> getSNPsByFeatureidIn(Collection featureid) throws DataAccessException;
	public Object getSNPsIn(String contig, Collection colPos);

}
